CS401 Lab 7: Binary Search data structure implementation


Part 1: Binary Search and Sorting

- Description of the program

	1. This program reads employee data from a file, sorts the employees by their IDs using selection sort, and allows for binary search to locate employees by ID. The program includes a Sorting class with two main functionalities:
		1) Selection Sort: Sorts an array of Employee objects by their ID field in ascending order. This sort algorithm iteratively finds the minimum element from the unsorted portion of the array and swaps it with the current position.
		2) Binary Search: Performs a binary search on the sorted array to find the index of an Employee object with a specific ID. If the ID is found, the employee's information is displayed; otherwise, a message indicates that the employee was not found.

	2. The program also includes a stack implementation, ArrayBoundedStack, which is used for evaluating postfix expressions in another part of the code. The StackInterface interface provides a template for common stack operations, including push, pop, top, isEmpty, and isFull.


- Instructions on how to compile and run the program and command to run the JAR file

	1. Compile and run in Eclipse:
		1) Open the project in Eclipse.
		2) Run the Sorting class, which contains the main method.
		3) View the results printed in the console.

	2. Command to run the JAR file:
		1) From the command line, navigate to the directory containing the JAR file.
		2) Run the following command:
			java -jar Sorting.jar
		3) The program will display the original list of employees, the sorted list, and the results of binary searches for specified IDs.


Part 2: Infix to Postfix Conversion and Evaluation Description of the program

- Description of the program

	1. This program converts infix expressions into postfix notation and evaluates the resulting postfix expression. The program is structured with two primary methods in the InfixPostfixEvaluator class:
	1) Infix to Postfix Conversion: This function converts a given infix expression to postfix notation using two stacks. One stack holds operators, managing their precedence, while the other builds the final postfix expression. Operands are added directly to the postfix output, while operators are managed by precedence until the expression is fully converted.
	2) Postfix Evaluation: This function evaluates a postfix expression using a stack. For each token in the postfix expression, if it’s an operand, it’s pushed onto the stack. If it’s an operator, two operands are popped from the stack, the operation is applied, and the result is pushed back onto the stack. The final result is the top of the stack once all tokens have been processed.

- Instructions on how to compile and run the program and command to run the JAR file

	1. Compile and run in Eclipse:
		1) Open the project in Eclipse.
		2) Run the InfixPostfixEvaluator class, which contains the main method.
		3) The program will display the infix expression, its converted postfix notation, and the evaluation result.

	2. Command to run the JAR file:
		1) From the command line, navigate to the directory containing the JAR file.
		2) Run the following command:
			java -jar InfixPostfixEvaluator.jar
		3) The program will display the results as described in the Eclipse instructions.


Part 3: Palindrome Checker

- Description of the program

	1. This program checks if a given string is a palindrome using a recursive method. A palindrome is a word, phrase, or sequence that reads the same backward as forward (e.g., "radar" or "level"). The program's main functionality is encapsulated in the PalindromeChecker class, which includes:

		1) Recursive Palindrome Check: This function takes a string and checks whether it reads the same from left to right and right to left. The function uses two pointers (left and right) that move towards the center of the string, comparing characters at each step. If all corresponding characters match, the string is confirmed as a palindrome.

		2) User Input: The program prompts the user to enter two separate strings and checks each for palindrome properties. Results are displayed, indicating whether each input is a palindrome.

- Instructions on how to compile and run the program and command to run the JAR file

	1. Compile and run in Eclipse:
		1) Open the project in Eclipse.
		2) Run the PalindromeChecker class, which contains the main method.
		3) Enter the strings when prompted, and view the results in the console.

	2.Command to run the JAR file:
		1)  From the command line, navigate to the directory containing the JAR file.
		2) Run the following command:
			java -jar PalindromeChecker.jar
		3) The program will prompt for input and display the results as described in the Eclipse instructions.