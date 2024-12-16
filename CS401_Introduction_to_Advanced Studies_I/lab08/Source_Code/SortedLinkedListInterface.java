public interface SortedLinkedListInterface<T>
{
    // Returns the number of elements in the linked list
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
}