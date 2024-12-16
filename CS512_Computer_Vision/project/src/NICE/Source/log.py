from argparse import Namespace
from typing import Any, List, Tuple
import os
import csv
from sklearn.metrics import f1_score, confusion_matrix, ConfusionMatrixDisplay
from Source.train_eval import test
from Source.helper import get_device, reduce_or_flat_convs
from Source.resnet18 import ResNet18
from torch.utils.data import DataLoader, ConcatDataset
from torchsummary import summary
import matplotlib.pyplot as plt

# Group two lists into subgroups based on unique values in the first list
def group_list_using_l1(l1, l2):
    groups_1 = []
    groups_2 = []
    for item_1, item_2 in zip(l1, l2):
        if not groups_1 or item_1 != groups_1[-1][-1]:
            groups_1.append([item_1])
            groups_2.append([item_2])
        else:
            groups_1[-1].append(item_1)
            groups_2[-1].append(item_2)
    return groups_1, groups_2

# Map dataset names to their corresponding input dimensions
def dataset2input(dataset_name):
    if dataset_name in ["MNIST"]:
        return (1, 28, 28)
    if dataset_name in ["CIFAR10", "CIFAR100"]:
        return (3, 32, 32)
    return (3, 64, 64)

# Calculate accuracy by comparing predictions and ground truths
def calculate_accuracy(preds, gts):
    if len(preds) != len(gts):
        raise ValueError("The length of preds and gts should be equal")
    return sum(p1 == p2 for p1, p2 in zip(preds, gts)) / len(gts)

# Write unit details (all, young, learner, mature) for a network into a CSV file
def write_units(writer, network: Any):
    all_units, young_neurons, learner_neurons, mature_neurons = [], [], [], []
    if isinstance(network, ResNet18):
        all_units = [list(range(a)) for a, _ in network.layers]
    else:
        weights = network.get_weight_bias_masks_numpy()
        all_units = [list(range(weights[0][0].shape[1]))] + [list(range(w[1].shape[0])) for w in weights]
    writer.writerow(["All Units"] + [len(u) for u in all_units])
    writer.writerow(["Young Neurons"] + [len((u == 0).nonzero()[0]) for u, _ in network.unit_ranks])
    writer.writerow(["Learner Neurons"] + [len((u == 1).nonzero()[0]) for u, _ in network.unit_ranks])
    writer.writerow(["Mature Neurons"] + [len((u > 1).nonzero()[0]) for u, _ in network.unit_ranks])

# Save confusion matrix as a plot
def save_confusion_matrix(y_true, y_pred, classes, save_path, title="Confusion Matrix"):
    cm = confusion_matrix(y_true, y_pred, labels=classes)
    disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=classes)
    plt.figure(figsize=(8, 8))
    disp.plot(cmap='Blues', values_format='d')
    plt.title(title)
    plt.tight_layout()
    plt.savefig(save_path)
    plt.close()

# Log task accuracies, F1 scores, and neuron states across episodes
def log_accuracies_and_neuron_states(
    writer, prev_task_accs, 
    all_episode_preds_train, 
    all_episode_preds_val, 
    all_episode_preds, 
    all_episode_train_gts, 
    all_episode_val_gts, 
    all_episode_gts, 
    full_test_acc=None
):
    writer.writerow(["Task Incremental Learning"])
    for task_classes, (train_acc, val_acc, test_acc) in prev_task_accs: 
        writer.writerow([str(task_classes), f"Train Acc: {train_acc:.2f}, Val Acc: {val_acc:.2f}, Test Acc: {test_acc:.2f}"])

    # Calculate F1 scores for each set
    train_f1 = f1_score(all_episode_train_gts, all_episode_preds_train, average='weighted')
    val_f1 = f1_score(all_episode_val_gts, all_episode_preds_val, average='weighted')
    test_f1 = f1_score(all_episode_gts, all_episode_preds, average='weighted')

    # Log accuracy and F1 scores
    writer.writerow([f"Episode Prediction Accuracy on Train Set: {calculate_accuracy(all_episode_preds_train, all_episode_train_gts):.2f}"])
    writer.writerow([f"Episode Prediction F1 Score on Train Set: {train_f1:.2f}"])
    writer.writerow([f"Episode Prediction Accuracy on Validation Set: {calculate_accuracy(all_episode_preds_val, all_episode_val_gts):.2f}"])
    writer.writerow([f"Episode Prediction F1 Score on Validation Set: {val_f1:.2f}"])
    writer.writerow([f"Episode Prediction Accuracy on Test Set: {calculate_accuracy(all_episode_preds, all_episode_gts):.2f}"])
    writer.writerow([f"Episode Prediction F1 Score on Test Set: {test_f1:.2f}"])

    if full_test_acc is not None:
        writer.writerow([f"Full Test Accuracy: {full_test_acc:.2f}"])

# Wrapper for writing CSV files with a custom function
def write_csv(file_path: str, write_func):
    with open(file_path, 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        write_func(writer)

# Evaluate task accuracy on previous tasks and return detailed metrics
def acc_prev_tasks(
    args: Namespace, 
    context_detector: Any, 
    task_index: int, 
    train_loaders: List[DataLoader],
    val_loaders: List[DataLoader], 
    test_loaders: List[DataLoader], 
    full_test_loader: DataLoader,
    network: Any, til_eval=False
) -> Tuple[List, List, List, List, List, List, List, List, List, float, List]:

    all_accuracies = []
    all_f1_scores = []
    all_preds, all_trues = [], []
    all_episode_preds_train, all_episode_preds_val, all_episode_preds = [], [], []
    all_episode_train_gts, all_episode_val_gts, all_episode_gts = [], [], []

    # Evaluate each previous task's accuracy using the corresponding data loaders
    for episode_id, (train_loader, val_loader, test_loader) in enumerate(zip(train_loaders[:task_index], val_loaders[:task_index], test_loaders[:task_index]), 1):
        task_classes = f"Task-{episode_id}"

        # Train set evaluation
        train_acc, train_preds, train_gts, episode_preds_train = test(network, context_detector, train_loader, return_preds=True, return_gts=True)
        train_f1 = f1_score(train_gts, train_preds, average='weighted')

        # Validation set evaluation
        val_acc, val_preds, val_gts, episode_preds_val = test(network, context_detector, val_loader, return_preds=True, return_gts=True)
        val_f1 = f1_score(val_gts, val_preds, average='weighted')

        # Test set evaluation
        test_acc, preds, ground_truths, episode_preds = test(network, context_detector, test_loader, return_preds=True, return_gts=True)
        test_f1 = f1_score(ground_truths, preds, average='weighted')

        # Append predictions, ground truths, and metrics
        all_preds.append(preds)
        all_trues.append(ground_truths)
        all_episode_preds_train.extend(episode_preds_train)
        all_episode_preds_val.extend(episode_preds_val)
        all_episode_preds.extend(episode_preds)
        # all_episode_train_gts.extend(train_gts)
        # all_episode_val_gts.extend(val_gts)
        # all_episode_gts.extend(ground_truths)
        all_episode_train_gts.extend([episode_id]*len(episode_preds_train))
        all_episode_val_gts.extend([episode_id]*len(episode_preds_val))
        all_episode_gts.extend([episode_id]*len(episode_preds))

        # Store accuracy and F1 scores
        all_accuracies.append((task_classes, [train_acc, val_acc, test_acc]))
        all_f1_scores.append((task_classes, [train_f1, val_f1, test_f1]))

    # Full test loader accuracy and F1 score, if available
    full_test_acc = None
    full_test_f1 = None
    if full_test_loader:
        full_test_acc, full_preds, full_gts, _ = test(network, context_detector, full_test_loader, return_preds=True, return_gts=True)
        full_test_f1 = f1_score(full_gts, full_preds, average='weighted')

    return all_accuracies, all_f1_scores, all_preds, all_trues, all_episode_preds, all_episode_preds_train, all_episode_preds_val, all_episode_train_gts, all_episode_val_gts, all_episode_gts, full_test_acc, full_test_f1

# Combine datasets from multiple dall_accuracies, all_f1_scores, all_preds, all_trues, all_episode_preds, all_episode_preds_train, all_episode_preds_val, all_episode_train_gts, all_episode_val_gts, all_episode_gts, full_test_acc, full_test_f1ata loaders into a single DataLoader
def combine_loaders(loaders):
    datasets = [loader.dataset for loader in loaders]
    combined_dataset = ConcatDataset(datasets)
    return DataLoader(combined_dataset, batch_size=loaders[0].batch_size, shuffle=False)

# Evaluate the model using all combined tasks' data
def evaluate_with_all_tasks(args, context_detector, network, train_loaders, val_loaders, test_loaders, episode_index):
    combined_train_loader = combine_loaders(train_loaders[:episode_index])
    combined_val_loader = combine_loaders(val_loaders[:episode_index])
    combined_test_loader = combine_loaders(test_loaders[:episode_index])

    train_acc, _, _, _ = test(network, context_detector, combined_train_loader, None, return_preds=True)
    val_acc, _, _, _ = test(network, context_detector, combined_val_loader, None, return_preds=True)
    test_acc, _, _, _ = test(network, context_detector, combined_test_loader, None, return_preds=True)

    return train_acc, val_acc, test_acc

# Log results at the end of an episode
def log_end_of_episode(
    args: Namespace, 
    network: Any, 
    context_detector: Any, 
    train_loaders: List[DataLoader],
    val_loaders: List[DataLoader], 
    test_loaders: List[DataLoader], 
    full_test_loader: DataLoader,
    episode_index: int, 
    dirpath: str
):
    dirpath = os.path.join(dirpath, f"Episode_{episode_index}")
    os.makedirs(dirpath, exist_ok=True)

    # Evaluate tasks and collect metrics
    prev_task_accs, prev_task_f1s, _, _, all_episode_preds, all_episode_preds_train, all_episode_preds_val, all_episode_train_gts, all_episode_val_gts, all_episode_gts, full_test_acc, full_test_f1 = acc_prev_tasks(
        args, context_detector, episode_index, train_loaders, val_loaders, test_loaders, full_test_loader, network)

    # Save confusion matrix for all combined tasks up to the current episode
    combined_train_loader = combine_loaders(train_loaders[:episode_index])
    combined_val_loader = combine_loaders(val_loaders[:episode_index])
    combined_test_loader = combine_loaders(test_loaders[:episode_index])

    _, train_preds, train_gts, _ = test(network, context_detector, combined_train_loader, None, return_preds=True, return_gts=True)
    save_confusion_matrix(
        train_gts, train_preds, classes=range(args.number_of_classes),
        save_path=os.path.join(dirpath, f"Confusion_Matrix_Train.png"),
        title=f"Combined Train Confusion Matrix"
    )

    _, val_preds, val_gts, _ = test(network, context_detector, combined_val_loader, None, return_preds=True, return_gts=True)
    save_confusion_matrix(
        val_gts, val_preds, classes=range(args.number_of_classes),
        save_path=os.path.join(dirpath, f"Confusion_Matrix_Val.png"),
        title=f"Combined Validation Confusion Matrix"
    )

    _, test_preds, test_gts, _ = test(network, context_detector, combined_test_loader, None, return_preds=True, return_gts=True)
    save_confusion_matrix(
        test_gts, test_preds, classes=range(args.number_of_classes),
        save_path=os.path.join(dirpath, f"Confusion_Matrix_Test.png"),
        title=f"Combined Test Confusion Matrix"
    )

    # Write metrics to a CSV file
    file_path = os.path.join(dirpath, f"Episode_{episode_index}.csv")
    write_csv(file_path, lambda writer: (
        write_units(writer, network),
        log_accuracies_and_neuron_states(writer, prev_task_accs, all_episode_preds_train, all_episode_preds_val, all_episode_preds, all_episode_train_gts, all_episode_val_gts, all_episode_gts, full_test_acc),
        writer.writerow([f"Full Test F1 Score: {full_test_f1:.2f}" if full_test_f1 is not None else "No Full Test F1"])
    ))

# Log results at the end of the sequence of episodes
def log_end_of_sequence(
    args: Namespace, 
    network: Any, 
    context_detector: Any, 
    train_loaders: List[DataLoader], 
    val_loaders: List[DataLoader], 
    test_loaders: List[DataLoader], 
    full_test_loader: DataLoader, 
    dirpath: str
):
    # Evaluate accuracy for all tasks
    prev_task_accs, _, _, _, all_episode_preds, all_episode_preds_train, all_episode_preds_val, all_episode_train_gts, all_episode_val_gts, all_episode_gts, full_test_acc, _ = acc_prev_tasks(args, context_detector, args.number_of_tasks, train_loaders, val_loaders, test_loaders, full_test_loader, network)
    
    # Write sequence results to a CSV file
    file_path = os.path.join(dirpath, "End_of_Sequence.csv")
    write_csv(file_path, lambda writer: (
        write_units(writer, network),
        log_accuracies_and_neuron_states(writer, prev_task_accs, all_episode_preds_train, all_episode_preds_val, all_episode_preds, all_episode_train_gts, all_episode_val_gts, all_episode_gts, full_test_acc)
    ))

    # Save network summary and sparsity information
    possible_params, actual_params, sparsity = network.compute_weight_sparsity_2()
    summary_str = str(summary(network, dataset2input(args.dataset)))

    with open(os.path.join(dirpath, "model_summary.txt"), 'w', newline='', encoding='utf-8') as file:
        file.write("Possible Parameters: {}  Actual Parameters: {} Sparsity: {} \n".format(possible_params, actual_params, sparsity))
        file.write(summary_str)

# Log results at the end of a phase within an episode
def log_end_of_phase(
    args: Namespace, network: Any, context_detector: Any, episode_index: int, phase_index: int,
    train_loader: DataLoader, val_loader: DataLoader, test_loader: DataLoader, dirpath: str
):
    # Create directory for phase results
    dirpath = os.path.join(dirpath, f"Episode_{episode_index}", f"Phase_{phase_index}")
    os.makedirs(dirpath, exist_ok=True)

    # Write phase-specific results to a CSV file
    file_path = os.path.join(dirpath, f"Phase_{phase_index}.csv")
    write_csv(file_path, lambda writer: (
        write_units(writer, network),
        writer.writerow(["Test Accuracy", test(network, context_detector, test_loader)]),
        writer.writerow(["Sparsitiy", network.compute_weight_sparsity()]),
    ))
