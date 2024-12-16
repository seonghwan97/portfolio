public class UnsortedArray<T extends Comparable<T>> implements CollectionInterface<T>{
    private T[] elements; // Array to store elements
    private int numElements; // Current number of elements
    private boolean found; // Indicates if a target is found
    private int location; // Index of the found target
    private int comparisonCount; // Counts comparisons during search

    @SuppressWarnings("unchecked")
    public UnsortedArray(int maxSize) {
        elements = (T[]) new Comparable[maxSize];
        numElements = 0;
        comparisonCount = 0;
    }

    // Linear search for the target
    public void find(T target) {
        resetComparisonCount();
        found = false;
        location = -1;
        for (int i = 0; i < numElements; i++) {
            comparisonCount++;
            if (elements[i].compareTo(target) == 0) {
                found = true;
                location = i;
                return;
            }
        }
    }

    // Retrieves the target element
    public T get(T target) {
        find(target);
        return found ? elements[location] : null;
    }

    // Checks if the array contains the target
    public boolean contains(T target) {
        find(target);
        return found;
    }

    // Resets the comparison count
    public void resetComparisonCount() {
        comparisonCount = 0;
    }

    // Returns the last search comparison count
    public int getComparisonCount() {
        return comparisonCount;
    }

    // Adds an element to the array if not full
    public boolean add(T element) {
        if (isFull()) {
            throw new IllegalStateException("Array is full.");
        }
        elements[numElements++] = element;
        return true;
    }

    // Gets the element at a specific index
    public T getElementAt(int index) {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return elements[index];
    }

    // Checks if the array is full
    public boolean isFull() {
        return elements.length == numElements;
    }

    // Checks if the array is empty
    public boolean isEmpty() {
        return numElements == 0;
    }

    // Returns the number of elements
    public int size() {
        return numElements;
    }

    // Searches for a target and returns its index
    public int search(T target) {
        resetComparisonCount();
        for (int i = 0; i < numElements; i++) {
            comparisonCount++;
            if (elements[i].compareTo(target) == 0) {
                return i;
            }
        }
        return -1;
    }

    // Sorts the array using quicksort
    public void quickSort() {
        if (numElements > 1) {
            quickSort(0, numElements - 1);
        }
    }

    private void quickSort(int first, int last) {
        if (first < last) {
            int splitPoint = split(first, last);
            quickSort(first, splitPoint - 1);
            quickSort(splitPoint + 1, last);
        }
    }

    private int split(int first, int last) {
        T pivot = elements[first];
        int left = first + 1;
        int right = last;

        while (true) {
            while (left <= right && elements[left].compareTo(pivot) <= 0) {
                left++;
            }
            while (left <= right && elements[right].compareTo(pivot) > 0) {
                right--;
            }
            if (left > right) {
                break;
            }
            swap(left, right);
        }
        swap(first, right);
        return right;
    }

    private void swap(int index1, int index2) {
        T temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    // Removes a target element
    public boolean remove(T target) {
        find(target);
        if (found) {
            elements[location] = elements[numElements - 1];
            elements[--numElements] = null;
            return true;
        }
        return false;
    }
}
