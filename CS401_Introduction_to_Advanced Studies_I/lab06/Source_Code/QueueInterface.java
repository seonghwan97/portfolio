public interface QueueInterface<T> {
    
    // Adds an element to the queue. Returns true if successful, false otherwise.
    boolean enqueue(T element);

    // Removes and returns the front element of the queue. Returns null if the queue is empty.
    T dequeue();

    // Prints the elements of the queue.
    void printQueue();

    // Checks if the queue is full. Returns true if it is full, false otherwise.
    boolean isFull();

    // Checks if the queue is empty. Returns true if it is empty, false otherwise.
    boolean isEmpty();

    // Returns the current number of elements in the queue.
    int size();
}
