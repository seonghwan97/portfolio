# Seonghwan's Portfolio: CS401 & CS512 Projects

Hello! My name is **Seonghwan Lim**, and I am currently pursuing a **Master of Science in Computer Science** at the **Illinois Institute of Technology**. I am deeply passionate about **computer vision**, **deep learning**, and **artificial intelligence (AI)**. This portfolio highlights my academic journey and technical projects from 
- **CS401: Introduction to Advanced Studies I**
- **CS512: Computer Vision**
Through these projects, I have demonstrated my proficiency in **data structures**, **algorithms**, **object-oriented programming**, and **state-of-the-art AI techniques**. I invite you to explore my work and learn more about my technical skills and problem-solving abilities.

---

## 📘 **CS401: Introduction to Advanced Studies I**

This course focused on mastering core concepts in **Java programming**, **data structures**, and **algorithms**. Key topics include linked lists, binary search trees, and sorting algorithms. The projects emphasize conceptual understanding, practical application, and efficient algorithm design.

### 🔹 **Project: Data Structures GUI Application**
**Objective:** Develop an interactive application that visualizes and manages data structures, allowing users to load, visualize, and manipulate employee data.

**Key Features:**
- **Data Structures Implemented:** Unsorted Array, Sorted Array, Unsorted Linked List, Sorted Linked List, and Binary Search Tree (BST).
- **Data Operations:** Add, Remove, Contains, and Visualization of Data Structures.
- **Performance Analysis:** Compare performance metrics (like search comparisons) across different data structures.
- **Interactive GUI:** Visualize how data is stored and managed, with options to view sorted arrays, linked lists, and a balanced BST.

**Technologies Used:**
- **Programming Language:** Java
- **GUI:** Java Swing
- **Data Analysis:** Performance comparison of data structures

**Skills Demonstrated:**
- Implemented core data structures (Arrays, Linked Lists, and BSTs) from scratch.
- Designed an interactive GUI for user-friendly visualization and analysis.
- Applied sorting algorithms like Quick Sort and search techniques like Binary Search.
- Measured and compared computational performance of data structures.

**How to Run:**
1. Run the `Main.jar` file from the command line using:
   ```
   java -jar Main.jar
   ```
2. Use the GUI to **Load CSV Files**, **Create Data Structures**, and **View Performance Analysis**.

**Documentation:** The detailed technical documentation for this project is available at:
```
CS401_Introduction_to_Advanced_Studies_I/final_project/documentation.pdf
```

---

## 📘 **CS512: Computer Vision**

This course focused on advanced concepts in **image processing**, **deep learning for vision**, and **neural network architectures**. Key projects include the implementation of **image filtering**, **object detection**, and **vision transformer models (ViT)**. The final project explored innovative techniques for incremental learning using **NICE with Knowledge Distillation**.

### 🔹 **Project: Enhancing the Neurogenesis-Inspired NICE Method with Knowledge Distillation**
**Objective:** Improve the adaptability and performance of the NICE method for class-incremental learning using **Knowledge Distillation (KD)**.

**Problem Statement:**
Traditional class-incremental learning models suffer from **catastrophic forgetting**, where learning new classes erases knowledge of previous classes. The NICE model mitigates this by freezing neurons after specific age thresholds. However, frozen neurons reduce flexibility, and essential context information is sometimes lost during neuron transitions.

**Proposed Solution:**
Incorporate **Knowledge Distillation (KD)** into the NICE framework to transfer knowledge from a "teacher" model to a "student" model during class-incremental learning. This approach preserves **inter-class relationships** and **reduces neuron ambiguity** during early training.

**Key Features:**
- **Teacher-Student Training:** Use a previously trained model (teacher) to guide the activations of a new model (student) as it learns new tasks.
- **Knowledge Transfer:** Align the student’s output to the teacher’s output using soft labels, preserving inter-class relationships.
- **Memory Efficiency:** Maintain prior knowledge of earlier classes without requiring replay-based memory storage.
- **Layer-Specific KD Loss:** Apply layer-wise distillation loss, allowing fine-grained control over neuron activation alignment.

**Technologies Used:**
- **Programming Language:** Python
- **Frameworks:** PyTorch, NumPy, OpenCV
- **Neural Networks:** CNNs, VGG

**Skills Demonstrated:**
- Implemented knowledge distillation for neural networks.
- Modified PyTorch models to integrate KD loss for better generalization.
- Improved the **NICE** model by preserving class knowledge across incremental tasks.
- Evaluated performance on CIFAR-10 and MNIST datasets, demonstrating improved task retention.

**How to Run:**
1. Install necessary libraries from the `requirements.txt` file.
2. Run the training script using:
   ```
   bash run.sh
   ```
3. View the results, which include:
   - Performance on MNIST and CIFAR-10 datasets.
   - Accuracy comparisons of NICE vs. NICE + KD.
   - Feature visualizations for context and layer occupancy.

**Documentation:** The detailed technical documentation for this project is available at:
```
CS512_Computer_Vision/project/doc/report.pdf
```

---

## 🔎 **Skills Highlighted in this Portfolio**

- **Software Development:** Implemented complex data structures from scratch (Arrays, Linked Lists, Binary Search Trees) and designed interactive GUIs using **Java Swing**.
- **Object-Oriented Programming (OOP):** Applied OOP principles for reusability, modularity, and scalability in software design.
- **Data Structures & Algorithms:** Implemented sorting (Quick Sort, Selection Sort) and search algorithms (Binary Search) to improve computational efficiency.
- **Computer Vision:** Mastered image processing techniques using **OpenCV** for **image transformation** and **filtering**.
- **Deep Learning & Neural Networks:** Worked with state-of-the-art **Vision Transformers (ViT)** and mastered **ResNet** and **VGG** for robust classification and detection tasks.
- **Incremental Learning & Knowledge Distillation:** Enhanced the NICE method for class-incremental learning by integrating **Knowledge Distillation**, enabling better retention of prior knowledge.

---

## 🗂 **How to Use This Repository**

The repository is organized into two main folders:

- **`CS401_Projects/`**: Contains all projects and assignments related to **CS401: Introduction to Advanced Studies I**.
  - **Data Structures GUI**: Interactive tool to visualize and compare data structures (arrays, linked lists, and BSTs).
  - **Assignments**: Lab assignments for sorting, searching, and linked list operations.

- **`CS512_Projects/`**: Contains assignments and the final project for **CS512: Computer Vision**.
  - **Assignments**: Key assignments on image processing (image filters, semantic segmentation, ViT models).
  - **Final Project**: Project on **NICE with Knowledge Distillation**, which improves the ability of incremental learning models to retain prior knowledge.

---

## 💎 **Contact Information**

For inquiries about this portfolio or to discuss potential opportunities, please contact Seonghwan via email at **slim24@hawk.iit.edu**.

