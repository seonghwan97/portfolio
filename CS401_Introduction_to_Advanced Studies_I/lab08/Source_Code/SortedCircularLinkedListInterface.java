public interface SortedCircularLinkedListInterface<T>
{
    // Returns the number of elements in the circular linked list
    int size();

    // Checks if a specific target (based on ID or value) is in the list
    boolean contains(int target);
    
    // Removes the element with the specified target value from the list
    boolean remove(int target);

    // Retrieves the element with the specified target value
    T get(int target);
    
    // Adds a new element in the correct sorted position
    void add(T element);

    // Returns a string representation of the list
    String toString();
    
    // Resets the current position for traversing the list
    void reset();
    
    // Returns the next element in the list from the current position
    T getNext();
}