// Interface defining basic collection operations
public interface CollectionInterface<T> 
{
    // Adds an element to the collection
    boolean add(T element);

    // Retrieves the specified element if found
    T get(T target);

    // Checks if the collection contains the specified element
    boolean contains(T target);

    // Removes the specified element from the collection
    boolean remove(T target);

    // Checks if the collection is full
    boolean isFull();

    // Checks if the collection is empty
    boolean isEmpty();

    // Returns the number of elements in the collection
    int size();
}