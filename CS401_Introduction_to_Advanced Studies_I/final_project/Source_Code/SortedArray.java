public class SortedArray<T extends Comparable<T>> implements CollectionInterface<T>{
    private T[] elements; // Stores sorted elements
    private int numElements; // Number of elements in the array

    protected boolean found; // Indicates if the target was found
    protected int location; // Index of the found element
    private int comparisonCount; // Number of comparisons in search

    @SuppressWarnings("unchecked")
    public SortedArray(int maxSize) {
        elements = (T[]) new Comparable[maxSize];
        numElements = 0;
        comparisonCount = 0;
    }

    // Adds an element while maintaining sorted order
    public boolean add(T element) {
        if (isFull()) {
            throw new IllegalStateException("Array is full.");
        }
        int insertIndex = 0;
        // Find the insertion point
        while (insertIndex < numElements && elements[insertIndex].compareTo(element) < 0) {
            insertIndex++;
        }
        // Shift elements to make space
        for (int i = numElements; i > insertIndex; i--) {
            elements[i] = elements[i - 1];
        }
        elements[insertIndex] = element;
        numElements++;
        
        return true;
    }

    // Retrieves the element at the specified index
    public T getElementAt(int index) {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return elements[index];
    }

    // Searches for a target element using binary search
    protected void find(T target) {
        if (target == null) {
            throw new IllegalArgumentException("Target cannot be null.");
        }

        resetComparisonCount();
        int low = 0, high = numElements - 1;
        found = false;

        while (low <= high) {
            comparisonCount++;
            int mid = (low + high) / 2;
            int comparison = elements[mid].compareTo(target);

            if (comparison == 0) {
                found = true;
                location = mid;
                return;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        location = -1;
    }

    // Removes the target element, if found
    public boolean remove(T target) {
        find(target);
        if (found) {
            // Shift elements to fill the gap
            for (int i = location; i < numElements - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[numElements - 1] = null;
            numElements--;
        }
        return found;
    }

    // Checks if the array contains the target element
    public boolean contains(T target) {
        find(target);
        return found;
    }

    // Retrieves the target element, or null if not found
    public T get(T target) {
        find(target);
        return found ? elements[location] : null;
    }

    // Checks if the array is full
    public boolean isFull() {
        return elements.length == numElements;
    }

    // Checks if the array is empty
    public boolean isEmpty() {
        return numElements == 0;
    }

    // Returns the number of elements in the array
    public int size() {
        return numElements;
    }

    // Resets the comparison counter
    public void resetComparisonCount() {
        comparisonCount = 0;
    }

    // Returns the number of comparisons in the last search
    public int getComparisonCount() {
        return comparisonCount;
    }
}
