# CS512: Computer Vision

Welcome to the **CS512: Computer Vision**. This repository highlights key assignments, projects, and concepts from **CS512: Computer Vision**, a comprehensive course focused on essential topics such as **image processing**, **deep learning for vision**, and **neural network architectures**. This portfolio demonstrates my proficiency in foundational computer vision concepts and hands-on implementation skills.

## **Assignment Descriptions**

### **AS1: Image Formation & Projection**
- Homogeneous projection
- Image transformation using OpenCV

---

### **AS2: Image Filtering Techniques**
- Gaussian filters for smoothing
- Laplacian of Gaussian (LoG) for edge detection

---

### **AS3: Robust Estimation & Image Classification**
- Robust estimation methods
- Image classification with neural networks
- Use of Residual Blocks and Inception Blocks

---

### **AS4: Semantic Segmentation & Object Detection**
- Semantic segmentation for pixel-level classification
- Object detection to identify and localize objects

---

### **AS5: Transformer-based Vision Models**
- Vision Transformer (ViT) for image classification
- Application of transformer models in computer vision

---

## **Final Project**

**Project Title:** Enhancing the Accuracy of the Neurogenesis-Inspired NICE Method in Class Incremental Learning

**Objective:** Improve the adaptability and performance of the NICE method in class incremental learning through the integration of knowledge distillation (KD) techniques.

**Project Background:**
- **Class Incremental Learning Challenge:** Catastrophic forgetting occurs when models lose knowledge of previously learned classes while learning new ones. 
- **NICE Approach:** Inspired by neurogenesis, NICE addresses this issue by managing neuron states (young, mature, and frozen neurons) to preserve prior knowledge. However, NICE has limitations that affect adaptability and performance.
  
**Challenges in NICE:**
1. **Frozen Neurons:** Once neurons reach a frozen state (age 2 or older), they are no longer updated, limiting adaptability to new information.
2. **Contextual Information Loss:** During the transition from young to mature neurons, some fine-grained contextual information may be lost, especially for complex tasks.
3. **Suboptimal Activation:** Activation-based selection for young neurons does not explicitly use knowledge of inter-class relationships or previously learned distributions, leading to suboptimal capacity utilization.
  
**Solution with Knowledge Distillation (KD):**
- **Teacher-Student Model:** KD enables the transfer of knowledge from a teacher model (trained on previous classes) to a student model (learning new classes).
- **Softened Probability Distributions:** By aligning the student’s output with the teacher’s softened probabilities, KD guides neuron activations and reduces ambiguity in early training.
- **Inter-Class Relationships:** KD preserves inter-class relationships from the teacher model, supporting NICE's neuron management.
  
**Key Contributions:**
- Improved neuron adaptability by enabling frozen neurons to continue learning when necessary.
- Reduced ambiguity in early training phases using guidance from the teacher model’s softened output.
- Enhanced use of network capacity through the integration of KD and activation-based selection for young neurons.

This project showcases my ability to address critical challenges in class incremental learning using advanced knowledge distillation techniques.

---

## **How to Use This Repository**
- Each assignment is organized in its own folder (e.g., `AS1`, `AS2`, etc.).
- Each assignment folder contains source code, comments, and explanations of key concepts.
- The final project folder includes a comprehensive report and source code files with instructions for execution.