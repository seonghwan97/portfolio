# Import necessary modules related to argparse and dataset handling
import argparse
import os
import pickle
import shutil
import random
import numpy as np
import torch
from torch.backends import cudnn
from torch.utils.data import DataLoader, Subset, random_split
from torchvision import datasets, transforms

# Paths for datasets and logs
DATASET_PATH = os.path.join(os.path.abspath('..'), 'Datasets')
LOG_PATH = os.path.join(os.path.abspath('.'), 'Logs')

# Function to set random seeds for reproducibility
def set_seeds(seed: int) -> None:
    torch.manual_seed(seed)
    random.seed(seed)
    np.random.seed(seed)
    torch.use_deterministic_algorithms(True)
    cudnn.benchmark = False
    cudnn.deterministic = True

# Function to define and parse command-line arguments
def get_argument_parser() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description='Experiment')

    # Logging-related arguments
    parser.add_argument('--experiment_name', type=str, default='MNIST')

    # Context detector-related arguments
    parser.add_argument('--memo_per_class_context', type=int, default=50)
    parser.add_argument('--context_layers', type=int, nargs='+', default=[0, 1, 2, 3, 4])
    parser.add_argument('--context_learner', type=str, default="LogisticRegression(random_state=0, max_iter=50, C=0.4)")

    # Dataset-related arguments
    parser.add_argument('--dataset', type=str, default='MNIST')
    parser.add_argument('--number_of_tasks', type=int, default=5)
    parser.add_argument('--number_of_classes', type=int, default=10)

    # Model architecture-related arguments
    parser.add_argument('--model', type=str, default='CNN_MNIST')
    parser.add_argument('--resnet_multiplier', type=float, default=1.0)

    # Training-related arguments
    parser.add_argument('--seed', type=int, default=0)
    parser.add_argument('--optimizer', type=str, default='SGD')
    parser.add_argument('--sgd_momentum', type=float, default=0.90)
    parser.add_argument('--learning_rate', type=float, default=0.01)
    parser.add_argument('--batch_size', type=int, default=256)
    parser.add_argument('--weight_decay', type=float, default=0.0)

    # Algorithm-related arguments
    parser.add_argument('--phase_epochs', type=int, default=5)
    parser.add_argument("--activation_perc", type=float, default=95.0)
    parser.add_argument("--max_phases", type=int, default=5)
    parser.add_argument("--max_replay_buffer_size", type=int, default=100)
    parser.add_argument("--kd_temperature", type=float, default=3)
    parser.add_argument("--kd_alpha", type=float, default=0.5)

    return parser.parse_args()

# Function to create log directories for experiments
def create_log_dirs(args: argparse.Namespace) -> str:
    dirpath = os.path.join(LOG_PATH, args.experiment_name)
    
    # Delete existing directory if it exists and create a new one
    if os.path.exists(dirpath) and os.path.isdir(dirpath):
        shutil.rmtree(dirpath)
    os.makedirs(dirpath)

    # Save experiment arguments to a file
    with open(os.path.join(dirpath, 'args.pkl'), 'wb') as file:
        pickle.dump(args, file)

    # Create individual log directories for each task
    for task_id in range(1, args.number_of_tasks + 1):
        os.makedirs(os.path.join(dirpath, f"Episode_{task_id}"))

    return dirpath

# Function to split a dataset into training and validation sets
def split_data(dataset, batch_size, val_split=0.1):
    dataset_size = len(dataset)
    val_size = int(dataset_size * val_split)
    train_size = dataset_size - val_size

    # Split the dataset
    train_dataset, val_dataset = random_split(dataset, [train_size, val_size])

    # Create data loaders
    train_loader = DataLoader(train_dataset, batch_size=batch_size, shuffle=True)
    val_loader = DataLoader(val_dataset, batch_size=batch_size, shuffle=False)

    return train_loader, val_loader

# Function to split MNIST into tasks
def split_mnist(n_tasks=5, batch_size=256, root='./data'):
    transform = transforms.Compose([transforms.ToTensor()])
    mnist_train = datasets.MNIST(root=root, train=True, download=True, transform=transform)
    mnist_test = datasets.MNIST(root=root, train=False, download=True, transform=transform)

    class_per_task = 10 // n_tasks
    train_loaders, val_loaders, test_loaders = [], [], []
    task2classes = {}
    full_test_loader = DataLoader(mnist_test, batch_size=batch_size, shuffle=False)

    # Split data for each task
    for task in range(n_tasks):
        train_indices = [i for i, (_, label) in enumerate(mnist_train) if label in range(task * class_per_task, (task + 1) * class_per_task)]
        test_indices = [i for i, (_, label) in enumerate(mnist_test) if label in range(task * class_per_task, (task + 1) * class_per_task)]

        train_subset = Subset(mnist_train, train_indices)
        test_subset = Subset(mnist_test, test_indices)

        train_loader, val_loader = split_data(train_subset, batch_size)
        test_loader = DataLoader(test_subset, batch_size=batch_size, shuffle=False)

        train_loaders.append(train_loader)
        val_loaders.append(val_loader)
        test_loaders.append(test_loader)
        task2classes[task + 1] = list(range(task * class_per_task, (task + 1) * class_per_task))

    return train_loaders, val_loaders, test_loaders, full_test_loader, task2classes

# Function to split CIFAR-10 into tasks
def split_cifar10(n_tasks=5, batch_size=256, root='./data'):
    transform = transforms.Compose([transforms.ToTensor(), transforms.Normalize((0.4914, 0.4822, 0.4465), (0.247, 0.243, 0.261))])
    cifar10_train = datasets.CIFAR10(root=root, train=True, download=True, transform=transform)
    cifar10_test = datasets.CIFAR10(root=root, train=False, download=True, transform=transform)

    class_per_task = 10 // n_tasks
    train_loaders, val_loaders, test_loaders = [], [], []
    task2classes = {}
    full_test_loader = DataLoader(cifar10_test, batch_size=batch_size, shuffle=False)

    # Split data for each task
    for task in range(n_tasks):
        train_indices = [i for i, (_, label) in enumerate(cifar10_train) if label in range(task * class_per_task, (task + 1) * class_per_task)]
        test_indices = [i for i, (_, label) in enumerate(cifar10_test) if label in range(task * class_per_task, (task + 1) * class_per_task)]

        train_subset = Subset(cifar10_train, train_indices)
        test_subset = Subset(cifar10_test, test_indices)

        train_loader, val_loader = split_data(train_subset, batch_size)
        test_loader = DataLoader(test_subset, batch_size=batch_size, shuffle=False)

        train_loaders.append(train_loader)
        val_loaders.append(val_loader)
        test_loaders.append(test_loader)
        task2classes[task + 1] = list(range(task * class_per_task, (task + 1) * class_per_task))

    return train_loaders, val_loaders, test_loaders, full_test_loader, task2classes

# Function to load appropriate dataset streams based on the specified dataset
def get_experience_streams(args: argparse.Namespace):
    if args.dataset == "MNIST":
        return split_mnist(n_tasks=args.number_of_tasks, batch_size=args.batch_size, root=DATASET_PATH)
    if args.dataset == "CIFAR10":
        return split_cifar10(n_tasks=args.number_of_tasks, batch_size=args.batch_size, root=DATASET_PATH)
    raise Exception(f"Dataset {args.dataset} is not defined!")