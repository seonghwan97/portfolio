CS401 Lab 6: Queue Data Structure Implementation


- Description of the program

	This program implements two different types of queue data structures: the Fixed Front Queue and the Floating Front Queue. A queue operates on the First In, First Out (FIFO) principle, meaning that elements are added to the rear and removed from the front in the order they were inserted.

	Enqueue: This operation adds an element to the rear of the queue. If the queue is full, it prevents further additions.

	Dequeue: This operation removes and returns the element at the front of the queue. If the queue is empty, it indicates that no elements can be removed.

	
- Instructions on how to compile and run the program and command to run the JAR file

	1. Compile and run in Eclipse:
		1) Open the project in Eclipse.
		2) Run the Main.java file.
		3) Printed the results.
		
	2. Command to run the JAR file:
		1) From the command line, navigate to the directory containing the JAR file.
		2) Run the following command:
			java -jar Main.jar
		3) The program will follow the same steps as described in the Eclipse instructions.


- Difference between Fixed Front and Floating Front implementations

	1. Fixed Front Queue:
		The Fixed Front Queue keeps the front of the queue at index 0, and when elements are dequeued, all the remaining elements are shifted one position to the left.

		Big-O Complexity:
			Enqueue: O(1) — Adding an element to the rear of the queue involves a simple increment of the rear index and assignment, so it takes constant time.
			Dequeue: O(n) — Removing an element requires shifting all the elements in the queue by one position to maintain the fixed front position. This means for n elements, the operation takes O(n) time.

	2. Floating Front Queue:
		In the Floating Front Queue, both the front and rear indices move in a circular manner, meaning no elements are shifted during dequeue operations. The front index is simply incremented after each dequeue, allowing operations to happen in constant time.

		Big-O Complexity:
			1) Enqueue: O(1) — Adding an element to the rear of the queue takes constant time, as it only involves moving the rear pointer and inserting the element.
			2) Dequeue: O(1) — Removing an element is also constant time, as the front index is incremented without shifting any elements.


- With various scenarios 

	1. Scenario 1: Queue Full
		If the queue is full, both implementations print "Queue is full" and stop further enqueues.

	2. Scenario 2: Queue Empty
		If the queue is empty, both implementations print "Queue is empty" and return null when trying to dequeue.

	3. Scenario 3: Enqueue and Dequeue Operations - The program enqueues five employees, then performs two dequeues.
		In the Fixed Front Queue, elements are shifted left after each dequeue.
		In the Floating Front Queue, the front index moves forward without shifting.
		Both queues will show the remaining three employees after the dequeues.