public class FixedFrontQueue<T> implements QueueInterface<T> 
{
    protected T[] elements;	// Array to hold queue elements
    protected int rear;	// Index to track the rear (end) of the queue
    protected int numElements = 0;	// Variable to keep track of the number of elements in the queue

    // Constructor to initialize the queue with a specified capacity
    @SuppressWarnings("unchecked")
    public FixedFrontQueue(int capacity) 
    {
        // Creating an array of generic type T with the given capacity
        elements = (T[]) new Object[capacity];
        rear = -1; // Initializing rear to -1 indicating an empty queue
    }

    // Method to add an element to the queue (enqueue)
    public boolean enqueue(T element)
    {
        // Check if the queue is full
        if (isFull()) 
        {
            System.out.println("Queue is full. Cannot add more elements.");
            return false; // Return false if no space to add more elements
        }
        else
        {
            // Increment rear in a circular manner to prevent overflow
            rear = (rear + 1) % elements.length;
            elements[rear] = element; // Place the element at the rear
            numElements++; // Increase the count of elements in the queue
            return true; // Successfully enqueued
        }
    }

    // Method to remove and return the front element of the queue (dequeue)
    public T dequeue()
    {
        // Check if the queue is empty
        if (isEmpty()) 
        {
            System.out.println("Queue is empty. Cannot remove elements.");
            return null; // Return null if there are no elements to remove
        }
        else 
        {
            // Store the front value (first element in the array)
            T frontValue = elements[0];
            
            // Shift all elements one position to the left
            for (int i = 1; i < numElements; i++) 
            {
                elements[i - 1] = elements[i];
            }
            elements[numElements - 1] = null; // Clear the last element
            numElements--; // Decrease the count of elements in the queue
            return frontValue; // Return the removed front element
        }
    }

    // Method to print all elements in the queue
    public void printQueue() 
    {
        // Check if the queue is empty
        if (isEmpty()) 
        {
            System.out.println("Queue is empty.");
        } 
        else 
        {
            // Print each element of the queue
            for (int i = 0; i < numElements; i++) 
            {
                System.out.println((i + 1) + ". " + elements[i]);
            }
        }
    }

    // Method to check if the queue is full
    public boolean isFull()
    {
        return (numElements == elements.length); // Return true if the number of elements equals the capacity
    }

    // Method to check if the queue is empty
    public boolean isEmpty()
    {
        return (numElements == 0); // Return true if there are no elements
    }

    // Method to return the current size of the queue
    public int size()
    {
        return numElements; // Return the number of elements in the queue
    }
}
