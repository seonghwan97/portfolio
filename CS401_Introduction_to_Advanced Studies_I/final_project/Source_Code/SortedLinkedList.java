public class SortedLinkedList<T extends Comparable<T>> implements CollectionInterface<T>{
    private LLNode<T> head; // First node of the list
    private int numElements; // Number of elements in the list
    private LLNode<T> location; // Tracks current node during traversal
    private LLNode<T> previous; // Tracks the previous node
    private boolean found; // Indicates if the target was found
    private int comparisonCount; // Counts comparisons during search

    // Constructor: Initialize an empty sorted linked list
    public SortedLinkedList() {
        head = null;
        numElements = 0;
        comparisonCount = 0;
    }
    
    // Returns the head of the list
    public LLNode<T> getHead() {
        return head;
    }

    // Adds an element while maintaining sorted order
    public boolean add(T element) {
        if (element == null) throw new IllegalArgumentException("Element cannot be null.");

        LLNode<T> newNode = new LLNode<>(element);
        LLNode<T> location = head, previous = null;

        // Traverse to find insertion point
        while (location != null && location.getInfo().compareTo(element) < 0) {
            previous = location;
            location = location.getLink();
        }

        if (previous == null) {
            newNode.setLink(head); // Insert at the beginning
            head = newNode;
        } else {
            newNode.setLink(location); // Insert in the middle or end
            previous.setLink(newNode);
        }
        numElements++;
        
        return true;
    }

    // Searches for a target element
    public void find(T target) {
        if (target == null) throw new IllegalArgumentException("Target cannot be null.");

        resetComparisonCount();
        location = head;
        previous = null;
        found = false;

        while (location != null) {
            comparisonCount++;
            if (location.getInfo().equals(target)) {
                found = true;
                return; // Target found
            } else if (location.getInfo().compareTo(target) > 0) {
                return; // Stop search early due to sorted order
            }
            previous = location;
            location = location.getLink();
        }
    }

    // Removes the node containing the target element, if found
    public boolean remove(T target) {
        find(target);
        if (found) {
            if (head == location) {
                head = head.getLink(); // Remove the first node
            } else {
                previous.setLink(location.getLink()); // Bypass the target node
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

    // Returns the number of elements
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
