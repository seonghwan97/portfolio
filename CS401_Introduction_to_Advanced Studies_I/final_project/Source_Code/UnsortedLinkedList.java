public class UnsortedLinkedList<T> implements CollectionInterface<T>{
    private LLNode<T> head; // First node of the list
    private int numElements; // Number of elements in the list
    private int comparisonCount; // Counts comparisons in search operations

    protected boolean found; // Indicates if the target was found
    protected LLNode<T> location; // Tracks current node during traversal
    protected LLNode<T> previous; // Tracks the previous node

    // Constructor: Initialize an empty linked list
    public UnsortedLinkedList() {
        head = null;
        numElements = 0;
        comparisonCount = 0;
    }

    // Returns the head of the list
    public LLNode<T> getHead() {
        return head;
    }

    // Adds a new element to the end of the list
    public boolean add(T element) {
        LLNode<T> newNode = new LLNode<>(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            location = head;
            while (location.getLink() != null) { // Traverse to the last node
                location = location.getLink();
            }
            location.setLink(newNode); // Append the new node
        }
        numElements++;
        return true;
    }

    // Searches for a target element
    public void find(T target) {
        resetComparisonCount();
        location = head;
        found = false;
        while (location != null) {
            comparisonCount++;
            if (location.getInfo().equals(target)) { // Check current node's data
                found = true;
                return;
            }
            previous = location;
            location = location.getLink(); // Move to the next node
        }
    }

    // Removes a node containing the target element, if found
    public boolean remove(T target) {
        find(target); // Locate the target
        if (found) {
            if (head == location) { // If target is at the head
                head = head.getLink();
            } else {
                previous.setLink(location.getLink()); // Skip the target node
            }
            numElements--;
        }
        return found;
    }

    // Checks if the list contains the target element
    public boolean contains(T target) {
        find(target);
        return found;
    }

    // Retrieves the element matching the target, or null if not found
    public T get(T target) {
        find(target);
        return found ? location.getInfo() : null;
    }

    // Returns whether the list is full (always false)
    public boolean isFull() {
        return false;
    }

    // Checks if the list is empty
    public boolean isEmpty() {
        return numElements == 0;
    }

    // Returns the size of the list
    public int size() {
        return numElements;
    }

    // Resets the comparison counter
    public void resetComparisonCount() {
        comparisonCount = 0;
    }

    // Returns the comparison count of the last search
    public int getComparisonCount() {
        return comparisonCount;
    }
}
