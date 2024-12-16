import java.util.Random;

public class SelectionSort {
    
    // Method generates an array of 10000 random 5-digit integers.
    public static int[] generateIntegerValues() {
        Random random = new Random();
        int[] arr = new int[10000];
        
        // Loop through the array to assign a random integer value to each index.
        for (int i = 0; i < 10000; i++) {
            arr[i] = random.nextInt(90000) + 10000;  // The range is [10000, 99999].
        }
        return arr;  // Return the generated array.
    }
    
    // Method sorts the given array using the selection sort algorithm.
    public static int[] selectionSort(int[] arr) {
        int n = arr.length;
        
        // Loop through the array to find the minimum element in each iteration.
        for (int i = 0; i < n; i++) {
            int min = i;  // Assume the current index holds the smallest value.
            
            // Inner loop to find the smallest value in the remaining unsorted portion.
            for (int j = i + 1; j < n; j++) {
                if(arr[j] < arr[min]) {  // If a smaller value is found, update the minimum index.
                    min = j;
                }
            }
            // Swap the smallest found value with the value at the current index.
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
        return arr;  // Return the sorted array.
    }
    
    public static void main(String[] args) {
        int[] arr = generateIntegerValues();  // Generate an array of random 5-digit integers.

        // Print the first 100 elements of the unsorted array.
        System.out.println("Before sorting, part of the array:");
        for (int i = 0; i < 100; i++) {
            System.out.print(arr[i] + " ");
            
            // Add a new line after every 10 elements for better readability.
            if (i % 10 == 9){
                System.out.print("\n");
            }
        }
        
        // Measure the start time.
        double startTime = System.currentTimeMillis();

        arr = selectionSort(arr);  // Sort the array using selection sort.

        // Measure the end time.
        double endTime = System.currentTimeMillis() - startTime;
        
        // Display the execution time in milliseconds.
        System.out.println("\nExecution time: " + endTime + " ms");
        
        // Print the first 100 elements of the sorted array.
        System.out.println("\nAfter sorting, part of the array:");
        for (int i = 0; i < 100; i++) {
            System.out.print(arr[i] + " ");
            
            // Add a new line after every 10 elements for better readability.
            if (i % 10 == 9){
                System.out.print("\n");
            }
        }
    }
}
