import os
from launch_utils import get_argument_parser, get_experience_streams  # Import argument parser and experience stream functions
from launch_utils import set_seeds, create_log_dirs  # Import functions for setting seeds and creating log directories
from Source import architecture, learner  # Import modules for model architecture and learning process

if __name__ == "__main__":  # Main execution block
    # Configure CUDA backend: Optimize memory management by setting CUBLAS workspace configuration
    os.environ["CUBLAS_WORKSPACE_CONFIG"] = ":4096:8"
    
    # Parse command-line arguments
    args = get_argument_parser()
    
    # Create directories to save experiment logs
    log_dirpath = create_log_dirs(args)
    
    # Set random seeds to ensure reproducibility
    set_seeds(args.seed)
    
    # Load training, validation, and testing data loaders, along with task-to-class mapping
    train_loaders, val_loader, test_loaders, full_test_loader, task2class = get_experience_streams(args)
    
    # Initialize the model backbone based on the dataset:
    # - For MNIST: input size is 28x28x1
    # - For CIFAR: input size is 32x32x3
    if args.dataset == "MNIST":
        backbone = architecture.get_backbone(args, input_size=28 * 28 * 1, output_size=10)
    else:
        backbone = architecture.get_backbone(args, input_size=32 * 32 * 3, output_size=10)

    # Create the Learner object:
    # - Uses parsed arguments, the model backbone, data loaders, task-class mapping, and log directory
    nice = learner.Learner(
        args,
        backbone,
        train_loaders,
        val_loader,
        test_loaders,
        full_test_loader,
        task2class,
        log_dirpath
    )

    # Train the model across all episodes (passing the replay buffer internally)
    nice.learn_all_episodes()