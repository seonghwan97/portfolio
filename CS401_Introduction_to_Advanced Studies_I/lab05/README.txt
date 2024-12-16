CS401 Lab 5: Recursion and Algorithm Implementation


Part 1: Jacobsthal Sequence

- Description of the program
	This program calculates the Jacobsthal numbers using two methods: recursion and iteration. The Jacobsthal sequence follows the relation J(n) = J(n−1) + 2 × J(n−2) x J(n) with base cases J(0) = 0 and J(1) = 1. In recursive method, use if and base cases. In iterative method, use while loop and opeartes until the base case is met. 

- Instructions on how to compile and run the program and command to run the JAR file

	1. Compile and run in Eclipse:
		1) Open the project in Eclipse.
		2) Run the Main.java file.
		3) Input non-negative integer.
		4) Printed the results of two methods with taken time.
		
	2. Command to run the JAR file:
		1) From the command line, navigate to the directory containing the JAR file.
		2) Run the following command:
			java -jar Jacobsthal.jar
		3) The program will follow the same steps as described in the Eclipse instructions.


Part 2: Recursive Minimum Finder

- Description of the program
	If the array size is 0, it returns -1 to indicate an invalid input. If the array size is 1, it returns the only element in the array. For larger sizes, it compares the last element with the first element A[0] and updates A[0] if the last element is smaller. Then, it reduces the size by 1 and continues recursively. This method mutates the original array A to update the minimum value found in A[0].

- Instructions on how to compile and run the program and command to run the JAR file

	1. Compile and run in Eclipse:
		1) Open the project in Eclipse.
		2) Run the Minimum.java file.
		3) Compile the java.
		4) Printed the results of minimum vale.
		
	2. Command to run the JAR file:
		1) From the command line, navigate to the directory containing the JAR file.
		2) Run the following command:
			java -jar Minimum.jar
		3) The program will follow the same steps as described in the Eclipse instructions.