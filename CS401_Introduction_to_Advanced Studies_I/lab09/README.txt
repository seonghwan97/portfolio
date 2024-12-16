CS401 Lab 9: Binary Search Tree Implementation


- Description of the Program
	1. This program implements a Binary Search Tree (BST) to manage integer data. The program demonstrates various operations on a BST, including:
		1) Insertion: Adding values to the BST while maintaining its properties.
		2) Traversals:
			In-order Traversal: Outputs the values in ascending order. (Left -> Root -> Right)
			Pre-order Traversal: Outputs the root first, followed by left and right subtrees. (Root -> Left -> Right)
			Post-order Traversal: Outputs the left subtree, then the right subtree, and finally the root. (Left -> Right -> Root)
		3) Maximum Depth: Calculates the longest path from the root to a leaf node.
		4) Tree Size:
			Recursive Method: Uses recursion to count the number of nodes.
			Iterative Method: Uses a stack-based approach to count nodes.
		5) Search: Searches for specific values in the tree and displays whether they were found.


- Instructions to Compile and Run the Program
	1. Compile and run in Eclipse:
		1) Open the project in Eclipse.
		2) Run the MainApplication class, which contains the main method.
		3) View the output in the console, showing:
			Results of the tree traversals (In-order, Pre-order, Post-order).
			Tree analysis metrics (Maximum Depth, Size).
			Search results for specific values.
	2. Command to run the JAR file:
		1) From the command line, navigate to the directory containing the JAR file.
		2) Run the following command:
			java -jar MainApplication.jar
		3) The program will display the results same as complied in Eclipse.


