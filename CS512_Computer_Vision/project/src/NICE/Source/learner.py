from argparse import Namespace
import torch
import torch.nn as nn
from torch.utils.data import DataLoader
from typing import Dict, Any
from Source.helper import random_prune, get_device
from Source.context_detector import ContextDetector
from Source.nice_operations import increase_unit_ranks, update_freeze_masks, select_learner_units
from Source.nice_operations import drop_young_to_learner, grow_all_to_young
from Source.log import log_end_of_episode, log_end_of_sequence, log_end_of_phase
from Source.train_eval import test, phase_training_ce

class Learner():

    def __init__(
        self, 
        args: Namespace, 
        network: Any, 
        train_loaders: list, 
        val_loaders: list, 
        test_loaders: list,
        full_test_loader: DataLoader,
        task2classes: Dict, 
        log_dirpath: str
        ):
        # Initialize Learner with parameters, dataset loaders, and logging path
        self.args = args
        self.task2classes = task2classes
        self.log_dirpath = log_dirpath
        self.optim_obj = getattr(torch.optim, args.optimizer)
        
        # Randomly prune the network (no pruning by default)
        self.network = random_prune(network.to(get_device()), 0.0)
        
        # Initialize ContextDetector for episodic task handling
        self.context_detector = ContextDetector(args, network.penultimate_layer_size, task2classes)
        self.train_loaders = train_loaders
        self.val_loaders = val_loaders
        self.test_loaders = test_loaders
        self.full_test_loader = full_test_loader
        print("Model: \n", self.network)
        
    def start_episode(self, episode_index: int):
        # Start a new episode
        print(f"*************** Starting Episode-{episode_index} ***************")

    def end_episode(self, episode_index: int, train_loader: DataLoader):
        # End the current episode by updating masks, saving the model, and logging results
        print("*************** Ending Episode *************** ")
        self.context_detector.push_activations(self.network, train_loader, episode_index)
        self.network = increase_unit_ranks(self.network)
        self.network = update_freeze_masks(self.network)
        self.network.freeze_bn_layers()  # Freeze batch normalization layers
        
        # Log metrics and update the context detector
        log_end_of_episode(
            self.args, self.network, self.context_detector,
            self.train_loaders, self.val_loaders, self.test_loaders, self.full_test_loader, episode_index, self.log_dirpath
        )
        
    def learn_episode(self, train_loader: DataLoader, val_loader: DataLoader, test_loader: DataLoader, full_test_loader: DataLoader, episode_index: int):
        # Learning process for one episode, divided into multiple phases
        phase_index = 1
        selection_perc = 100.0  # Start with selecting all activations
        loss = torch.nn.CrossEntropyLoss()  # Use cross-entropy loss
        
        while True:
            # Select learner units based on activation statistics
            print(f'Selecting Units (Ratio: {selection_perc})')
            self.network = select_learner_units(
                self.network, selection_perc, train_loader, episode_index, self.task2classes, self.args.experiment_name
            )

            # Modify connections to align young and learner units
            print('Dropping connections')
            self.network = drop_young_to_learner(self.network)
            print('Fixing Young connections')
            self.network = grow_all_to_young(self.network)
            print(f"Sparsity phase-{phase_index}: {self.network.compute_weight_sparsity():.2f}")

            # Configure optimizer
            if self.args.optimizer == "SGD":
                optimizer = self.optim_obj(
                    self.network.parameters(), lr=self.args.learning_rate, weight_decay=0.0, momentum=self.args.sgd_momentum
                )
            else:
                optimizer = self.optim_obj(self.network.parameters(), lr=self.args.learning_rate, weight_decay=0.0)

            # Train network for the current phase
            self.network = phase_training_ce(
                network=self.network, 
                phase_epochs=self.args.phase_epochs,
                loss=loss,
                optimizer=optimizer,
                train_loader=train_loader,
                args=self.args
            )

            # Update context detector with current activations
            self.context_detector.push_activations(self.network, train_loader, episode_index)

            # Evaluate and log validation accuracy
            val_accuracy = test(self.network, self.context_detector, val_loader)
            print(f"Episode-{episode_index}, Phase-{phase_index}, Episode Validation Class Accuracy: {val_accuracy}")

            # Evaluate and log test accuracy
            test_accuracy = test(self.network, self.context_detector, test_loader)
            print(f"Episode-{episode_index}, Phase-{phase_index}, Episode Test Class Accuracy: {test_accuracy}")

            # Log metrics for the current phase
            log_end_of_phase(
                self.args, 
                self.network, 
                self.context_detector, 
                episode_index, 
                phase_index,
                train_loader, 
                val_loader, 
                test_loader,
                self.log_dirpath
            )
            
            phase_index += 1
            selection_perc = self.args.activation_perc  # Update activation percentage for the next phase

            # Stop if the maximum number of phases is reached
            if phase_index > self.args.max_phases:
                break

    def learn_all_episodes(self):
        # Sequentially learn through all episodes
        for episode_index, (train_loader, val_loader, test_loader) in enumerate(zip(self.train_loaders, self.val_loaders, self.test_loaders), 1):
            print(episode_index)
            self.start_episode(episode_index)
            self.learn_episode(train_loader, val_loader, test_loader, self.full_test_loader, episode_index)
            self.end_episode(episode_index, train_loader)
            
        # Log final metrics after completing all episodes
        log_end_of_sequence(
            self.args, 
            self.network, 
            self.context_detector, 
            self.train_loaders, 
            self.val_loaders, 
            self.test_loaders, 
            self.full_test_loader, 
            self.log_dirpath
        )