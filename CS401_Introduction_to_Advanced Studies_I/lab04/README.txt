CS401 Lab 4: Stack Data Structure Implementation

- Description of the program

	This program reads employee data (first name, last name, and ID) from a text file emp.txt, stores it in a stack, and performs basic stack operations like push, pop, and top with error handling like  stack overflow or underflow.

- Instructions on how to compile and run the program and command to run the JAR file

	1. Compile and run in Eclipse:
		1) Open the project in Eclipse.
		2) Make sure the emp.txt file is in the project folder with the following format.
		3) Run the Main.java file.
		4) The program will read the emp.txt file, store employee data in the stack, and display the top element.
		5) Two pop operations will occur, and the new top element will be displayed.
		6) The program will then push a new employee (Seonghwan Lim 1031) manually and display the updated top element.
		
	2. Command to run the JAR file:
		1) From the command line, navigate to the directory containing the JAR file.
		2) Run the following command:
			java -jar Main.jar
		3) The program will follow the same steps as described in the Eclipse instructions.

- Explanation of the Stack Implementation

	1. Push: Adds a new Employee object to the stack. If the stack is full, a "Stack overflow" message is printed.
	2. Pop: Removes and returns the top Employee object from the stack. If the stack is empty, a "Stack underflow" message is printed.
	3. Top: Returns the top Employee object without removing it. If the stack is empty, a "Stack underflow" message is printed.