public class FloatingFrontQueue<T> implements QueueInterface<T> 
{    
    protected T[] elements;	// Array to store the elements of the queue    
    protected int front = 0; // Index to track the front of the queue   
    protected int rear; // Index to track the rear (end) of the queue
    protected int numElements = 0; // Variable to keep track of the number of elements in the queue

    // Constructor to initialize the queue with a given capacity
    @SuppressWarnings("unchecked")
    public FloatingFrontQueue(int capacity) {
        elements = (T[]) new Object[capacity]; // Create an array for the queue
        rear = -1; // Initialize rear to -1, indicating the queue is empty
    }

    // Method to add an element to the queue (enqueue)
    public boolean enqueue(T element)
    {
        // Check if the queue is full
        if (isFull()) 
        {
            System.out.println("Queue is full. Cannot add more elements.");
            return false; // Return false if the queue is full
        }
        else
        {
            // Increment rear index in a circular manner to prevent overflow
            rear = (rear + 1) % elements.length;
            elements[rear] = element; // Add the element to the rear of the queue
            numElements++; // Increase the count of elements
            return true; // Return true if enqueued successfully
        }
    }

    // Method to remove and return the front element of the queue (dequeue)
    public T dequeue()
    {
        // Check if the queue is empty
        if (isEmpty()) 
        {
            System.out.println("Queue is empty. Cannot remove elements.");
            return null; // Return null if there are no elements to dequeue
        }
        else
        {
            // Get the front element from the queue
            T frontValue = elements[front];
            elements[front] = null; // Set the dequeued element position to null
            // Increment the front index in a circular manner
            front = (front + 1) % elements.length;
            numElements--; // Decrease the count of elements
            return frontValue; // Return the dequeued element
        }
    }

    // Method to print all elements currently in the queue
    public void printQueue() 
    {
        // Check if the queue is empty
        if (isEmpty()) 
        {
            System.out.println("Queue is empty.");
        } 
        else 
        {
            System.out.println("Current elements in the queue:");
            // Loop through the queue and print each element
            for (int i = 0; i < numElements; i++) 
            {
                // Calculate the index based on the circular nature of the queue
                int index = (front + i) % elements.length;
                System.out.println((i + 1) + ". " + elements[index]);
            }
        }
    }

    // Method to check if the queue is full
    public boolean isFull()
    {
        return (numElements == elements.length); // Return true if the queue is at full capacity
    }

    // Method to check if the queue is empty
    public boolean isEmpty()
    {
        return (numElements == 0); // Return true if there are no elements in the queue
    }

    // Method to return the current size of the queue
    public int size()
    {
        return numElements; // Return the number of elements in the queue
    }
}
