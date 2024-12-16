public class Minimum {
    // Recursively find the minimum value in the array
    public static int minimum(int[] A, int size) {
        if (size == 0) {
            return -1; // Base case: if the array size is 0, return -1 (invalid input)
        } else if (size == 1) {
            return A[0]; // Base case: if the array size is 1, return the only element
        } else {
            // If the last element is smaller than the current minimum, update A[0]
            if (A[size - 1] < A[0]) {
                A[0] = A[size - 1];
            }
            // Reduce the array size and continue the recursion
            size--;
            return minimum(A, size);
        }
    }

    public static void main(String args[]) {
        int A[] = {10, -20, 1, 2, 0, 5, 100};
        int s = minimum(A, A.length);
        System.out.println(s); // Output the minimum value found
    }
}