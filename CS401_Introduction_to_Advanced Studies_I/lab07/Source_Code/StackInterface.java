public interface StackInterface<T> 
{
    // Adds an element to the top of the stack. Throws an exception if the stack is full.
    void push(T element); // StackOverflow
    
    // Removes the top element from the stack. Throws an exception if the stack is empty.
    void pop(); // StackUnderflow
    
    // Returns the top element without removing it. Throws an exception if the stack is empty.
    T top(); // StackUnderflow
    
    // Checks if the stack is empty and returns true if it is.
    boolean isEmpty();
    
    // Checks if the stack is full and returns true if it is.
    boolean isFull();
}