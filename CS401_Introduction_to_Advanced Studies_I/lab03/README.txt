CS401 Lab 3: Runtime Complexity and Selection Sort

- Description of the program

	This program generates an array of 10,000 random 5-digit integers, sorts it using Selection Sort, and measures the execution time in milliseconds. It displays the first 100 elements before and after sorting.


- Instructions on how to compile and run the program and command to run the JAR file

	1. Compile and run in Eclipse:
		1) Open the project in Eclipse.
		2) Run the SelectionSort.java file.
		3) The first 100 elements of the unsorted array will be displayed in the console.
		4) The program will sort the array using the Selection Sort algorithm.
		5) The execution time of the sorting process will be printed in milliseconds.
		6) The first 100 elements of the sorted array will be displayed in the console.

	2. Command to run the JAR file:
		1) From the command line, navigate to the directory containing the JAR file.
		2) Run the following command:

			java -jar SelectionSort.jar

		3) The same steps from 3-6 in the Eclipse instructions will occur in the command line.


- Explanation of the Selection Sort Implementation

	1) The outer loop iterates through the entire array.
	2) For each iteration, the minimum element in the unsorted portion is identified using an inner loop.
	3) Once the minimum element is found, it is swapped with the current element at the outer loop's index.
	4) The process repeats for the remaining unsorted elements until the entire array is sorted. 
	
	The time complexity is O(n²) since each element is compared with all others in the unsorted section.