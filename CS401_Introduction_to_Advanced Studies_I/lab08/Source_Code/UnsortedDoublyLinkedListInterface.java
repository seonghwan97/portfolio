public interface UnsortedDoublyLinkedListInterface<T>
{
    // Returns the number of elements in the doubly linked list
    int size();

    // Checks if a specific target (based on ID or value) is in the list
    boolean contains(int target);
    
    // Removes the element with the specified target value from the list
    boolean remove(int target);

    // Retrieves the element with the specified target value
    T get(int target);
    
    // Adds a new element to the list without maintaining order
    void add(T element);

    // Returns a string representation of the list
    String toString();
    
    // Displays the list elements from the beginning to the end
    void displayForward();
    
    // Displays the list elements from the end to the beginning
    void displayBackward();
}