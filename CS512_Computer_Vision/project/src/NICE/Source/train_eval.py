from argparse import Namespace
import torch
import torch.nn as nn
import torch.nn.functional as F
from torch.utils.data import DataLoader
from typing import Any
import numpy as np
from typing import List, Any
from Source.helper import get_device

# Function to evaluate the model's performance on a given dataset
def test(
    network: Any,
    context_detector: Any,
    data_loader: DataLoader,
    episode_id=None,
    return_preds=False,
    return_gts=False,
    task2classes=None
):
    network.eval()  # Set the network to evaluation mode
    predictions = []
    ground_truths = []
    episode_preds_all = []
    
    with torch.no_grad():  # Disable gradient computation
        for data, target in data_loader:
            data = data.to(get_device())
            target = target.to(get_device())
            output, activations = network.get_activations(data, return_output=True)
            
            # Use classes specific to the current episode if provided
            if task2classes:
                classes_in_this_episode = task2classes[episode_id]
            else:
                classes_in_this_episode = range(output.size(1))
            
            # Predict context for the current activations
            class_preds, episode_preds = context_detector.predict_context(activations, episode_id)
            for index, episode_pred in enumerate(class_preds):
                # Adjust output logits for classes in this episode
                if episode_pred in classes_in_this_episode:
                    output[index, episode_pred] += 99999
            preds = output.argmax(dim=1, keepdim=True)  # Get the predicted class
            predictions.extend(preds)
            ground_truths.extend(target)
            episode_preds_all.extend(episode_preds)
    
    predictions = np.array([int(p) for p in predictions])
    ground_truths = np.array([int(gt) for gt in ground_truths])
    network.train()  # Reset the network to training mode
    
    # Return accuracy and optionally predictions, ground truths, and episode predictions
    accuracy = sum(predictions == ground_truths) / len(predictions)
    if return_preds and return_gts:
        return accuracy, predictions, ground_truths, episode_preds_all
    elif return_preds:
        return accuracy, predictions, None, episode_preds_all
    else:
        return accuracy
        
# Function for phase-based training with cross-entropy and optional distillation loss
def phase_training_ce(
    network: Any,  # Student model
    phase_epochs: int,
    loss: nn.Module,
    optimizer: ...,
    train_loader: DataLoader,
    args: Namespace,
) -> Any:

    for epoch in range(phase_epochs):
        network.train()  # Set the network to training mode

        epoch_ce_loss = []  # Track cross-entropy loss for the epoch
        epoch_l2_loss = []  # Track L2 regularization loss for the epoch
        
        for data, target in train_loader:
            data = data.to(get_device())
            target = target.to(get_device())
            optimizer.zero_grad()  # Reset gradients

            # Forward pass for student network
            student_output = network.forward_output(data)

            # Compute cross-entropy loss
            ce_loss = loss(student_output, target.long())


            # Compute L2 regularization loss
            l2_loss = args.weight_decay * network.l2_loss()

            # Append losses for logging
            epoch_ce_loss.append(ce_loss.item())
            epoch_l2_loss.append(l2_loss.item())

            # Compute total loss
            batch_loss = ce_loss + l2_loss
            batch_loss.backward()  # Backpropagation

            # Reset gradients for frozen weights if necessary
            if network.freeze_masks:
                network.reset_frozen_gradients()

            # Update weights
            optimizer.step()

        # Log average losses for the epoch
        print("Epoch {}: Average CE loss: {:.4f}, Average L2 loss: {:.4f}".format(
            epoch + 1,
            np.mean(epoch_ce_loss),
            np.mean(epoch_l2_loss)
        ))

    return network