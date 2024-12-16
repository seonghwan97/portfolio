CS401 Lab2: Student Record Comparison

- Description of the program

	A program that compares the name and ID of Student 1 with those of Student 2 and Student 3 to determine their order. The comparison is based on alphabetical order of names, and if the names are the same, it compares by ID.


- Instructions on how to compile and run the program and command to run the JAR file

	1. Compile and run in Eclipse:
		1) Open the project in Eclipse.
		2) Run the Student.java.
		3) When the prompt "Enter details for Student 1:" appears, input the first studnet name and ID.
		4) When the prompt "Enter details for Student 2:" appears, input the second studnet name and ID.
		5) Rusults prompt comparing Student 1 and Student 2.
		6) When the prompt "Enter details for Student 3:" appears, input the third studnet name and ID.
		7) Rusults prompt comparing Student 1 and Student 3.

	2. Command to run the JAR file:
		1) From the command line, navigate to the directory containing the JAR file.
		2) Run the following command:

			java -jar Student.jar

		3) The same steps 3-7 from the "In Eclipse" section will appear


- Explanation of the comparison logic

	The name is compared using the compareTo method. The compareTo method compares characters by their ASCII values, returning a negative number if the reference value comes before the compared value in alphabetical order, and a positive number if the compared value comes before.

	The ID is compared using the Integer.compare method, which returns a negative number if the reference value comes before the compared value and a positive number if the compared value comes before.

	The compare method uses the compareTo method to compare names and, if the names are the same, it uses the Integer.compare method to compare IDs. If the compare method returns a negative number, the reference value comes before; if it returns a positive number, the compared value comes before.