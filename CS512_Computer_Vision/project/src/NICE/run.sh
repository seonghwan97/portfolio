# Experiment 1: MNIST with Knowledge Distillation (MNIST_KD)
python launch.py \
    --experiment_name "MNIST_Standard" \
    --memo_per_class_context "100" \
    --context_layers 0 1 2 3 4 \
    --context_learner "LogisticRegression(random_state=0, max_iter=300, C=0.4)" \
    --dataset "MNIST" \
    --number_of_tasks "5" \
    --model "CNN_MNIST" \
    --seed "0" \
    --learning_rate "0.01" \
    --batch_size "32" \
    --weight_decay "0.0" \
    --phase_epochs "5" \
    --activation_perc "95.0" \
    --max_phases "5"

# Experiment 2: CIFAR10 with Knowledge Distillation (CIFAR10_KD)
python launch.py \
    --experiment_name "CIFAR10_Standard" \
    --memo_per_class_context "300" \
    --context_layers 0 1 2 3 4 5 6 7 8 9 10 11 \
    --context_learner "LogisticRegression(random_state=0, max_iter=300, C=0.01)" \
    --dataset "CIFAR10" \
    --number_of_tasks "5" \
    --model "VGG11_SLIM" \
    --seed "0" \
    --learning_rate "0.01" \
    --batch_size "128" \
    --weight_decay "0.0001" \
    --phase_epochs "15" \
    --activation_perc "95.0" \
    --max_phases "5"