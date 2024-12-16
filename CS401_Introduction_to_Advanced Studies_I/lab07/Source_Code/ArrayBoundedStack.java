public class ArrayBoundedStack<T> implements StackInterface<T> 
{
    // Array to store stack elements
    protected T[] elements;
    // Index of the top element in the stack, initialized to -1 (empty stack)
    protected int top = -1;
    
    @SuppressWarnings("unchecked")
    ArrayBoundedStack(int capacity)
    {
        // Initialize the array with specified capacity, casting it to the generic type T
        elements = (T[]) new Object[capacity];
    }
    
    // Adds an element to the top of the stack
    public void push(T element)
    {
        // Check if the stack is full before adding
        if(isFull())
        {
            System.out.println("StackOverflowException");
        }
        else
        {
            top++; // Move top pointer up
            elements[top] = element; // Store the new element
        }
    }
    
    // Removes the top element from the stack
    public void pop()
    {
        // Check if the stack is empty before removing
        if(isEmpty())
        {
            System.out.println("StackUnderflowException");
        }
        else
        {
            elements[top] = null; // Remove the top element
            top--; // Move top pointer down
        }
    }
    
    // Returns the top element of the stack without removing it
    public T top()
    {
        T topValue = null;
        // Check if the stack is empty before accessing the top element
        if(isEmpty())
        {
            System.out.println("StackUnderflowException");
        }
        else
        {
            topValue = elements[top]; // Get the top element
        }
        return topValue;
    }
    
    // Checks if the stack is empty
    public boolean isEmpty()
    {
        return (top == -1);
    }
    
    // Checks if the stack is full
    public boolean isFull()
    {
        return (top == elements.length - 1);
    }
}