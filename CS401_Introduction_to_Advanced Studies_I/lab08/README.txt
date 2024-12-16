CS401 Lab 8: Linked List Data Structure Implementations


Part 1: Sorted Linked List

	- Description of the program
		1. This program implements a sorted linked list to manage Employee objects. It reads employee data from a file ("emp.txt"), where each line contains an employee's first name, last name, and ID. The data is used to create Employee objects, which are then added to a sorted linked list that maintains the order based on employee IDs. The main features include:
        		1) Sorted Linked List: Maintains a list of employees sorted by their IDs in ascending order. Supports operations like add, remove, contains, and get.
        		2) Employee Management: Allows adding new employees, checking for existing IDs, and removing employees by ID. The program outputs the list contents after each operation.

	- Instructions on how to compile and run the program and command to run the JAR file

		1. Compile and run in Eclipse:
			1) Open the project in Eclipse.
			2) Run the Main class, which contains the main method.
			3) View the results printed in the console, showing the sorted list of employees and the results of add and remove operations.

		2. Command to run the JAR file:
			1) From the command line, navigate to the directory containing the JAR file.
        		2) Run the following command:
            			java -jar SortedLinkedList.jar
        		3) The program will display the sorted linked list contents and the outcomes of operations as described.


Part 2: Circular Linked List

	- Description of the program

    		1. This program implements a sorted circular linked list to manage Employee objects. It reads the first 8 entries from the "emp.txt" file and adds them to the list. The circular linked list ensures that the last node links back to the first node, creating a circular structure. The main features include:
        		1) Sorted Circular Linked List: Maintains employees in a sorted circular list based on their IDs. Supports add, remove, contains, and get operations.
        		2) Circular Structure: Demonstrates the circular nature of the list by showing traversal back to the start node after reaching the end.

	- Instructions on how to compile and run the program and command to run the JAR file

    		1. Compile and run in Eclipse:
        		1) Open the project in Eclipse.
        		2) Run the Main class, which contains the main method.
        		3) The console will display the initial circular list and the results of any removal operations.

    		2. Command to run the JAR file:
        		1) From the command line, navigate to the directory containing the JAR file.
        		2) Run the following command:
            			java -jar CircularLinkedList.jar
        		3) The program will display the circular linked list contents and the outcomes of operations as described.


Part 3: Doubly Linked List

	- Description of the program

    		1. This program implements an unsorted doubly linked list for managing integer elements. It demonstrates the fundamental operations of a doubly linked list, including forward and backward traversal. The main features include:
        		1) Unsorted Doubly Linked List: Supports adding elements to the end of the list, displaying the list from head to tail, and from tail to head.
        		2) Traversal: Provides methods to display the list in both forward and backward order, showcasing the doubly linked nature where each node links to both its previous and next nodes.

	- Instructions on how to compile and run the program and command to run the JAR file

    		1. Compile and run in Eclipse:
        		1) Open the project in Eclipse.
        		2) Run the Main class, which contains the main method.
        		3) The console will display the doubly linked list and results of forward and backward traversal outputs.

    		2. Command to run the JAR file:
        		1) From the command line, navigate to the directory containing the JAR file.
        		2) Run the following command:
            			java -jar CircularLinkedList.jar
        		3) The console will display the doubly linked list and results of forward and backward traversal outputs.

        
