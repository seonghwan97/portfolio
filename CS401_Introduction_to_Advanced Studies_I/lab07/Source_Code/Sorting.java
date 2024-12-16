import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sorting<T extends Comparable<T>>
{
    // Selection sort to sort the array of elements between indices low and high
    void selectionSort(T[] array, int low, int high)
    {
        for (int i = low; i < high; i++) 
        {
            int minIndex = i; // Assume the minimum is at the current index
            // Find the minimum element in the remaining unsorted part
            for (int j = i + 1; j <= high; j++) 
            {
                if (array[j].compareTo(array[minIndex]) < 0) 
                {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the current element
            T temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
    
    // Binary search to find the index of the key in a sorted array
    int binarySearch(T[] array, int low, int high, T key)
    {
        while (low <= high) 
        {
            int mid = low + (high - low) / 2; // Calculate mid index
            int comparison = array[mid].compareTo(key);
            if (comparison == 0) 
            {
                return mid; // Key found at mid
            } else if (comparison < 0) 
            {
                low = mid + 1; // Search in the right half
            } else {
                high = mid - 1; // Search in the left half
            }
        }
        return -1; // Return -1 if key not found
    }

    public static void main(String[] args) 
    {
        int capacity = 50; // Maximum capacity for Employee array
        int num_employees = 0; // Counter for the number of employees
        
        Employee[] emp = new Employee[capacity]; // Array to hold Employee objects
        Sorting<Employee> sorter = new Sorting<>();
        
        // Try reading data from the file "emp.txt"
        try (Scanner scanner = new Scanner(new File("emp.txt"))) 
        {
            int index = 0;
            // Loop to read each line from the file and create Employee objects
            while (scanner.hasNextLine() && index < capacity) 
            {
                String line = scanner.nextLine();
                String[] parts = line.split(" "); // Split each line into first name, last name, and ID
                String firstName = parts[0];
                String lastName = parts[1];
                String id = parts[2];

                // Create a new Employee object and add it to the array
                emp[index] = new Employee(firstName, lastName, id);
                index++;
                num_employees++;
            }
        } catch (FileNotFoundException e) 
        {
            System.out.println("Error: The file 'emp.txt' was not found.");
            System.exit(0); // Exit the program if the file is not found
        }
        
        // Display the original list of employees
        System.out.println("Original Employee List:");
        for (int i = 0; i < num_employees; i++)
        {
            System.out.println(emp[i]);
        }
        
        // Sort the employee list using selection sort
        sorter.selectionSort(emp, 0, num_employees - 1);
        
        // Display the sorted list of employees
        System.out.println("\nSorted Employee List:");
        for (int i = 0; i < num_employees; i++)
        {
            System.out.println(emp[i]);
        }
        
        // Search for Employee with ID 1010
        System.out.println("\nFind Employee ID 1010");
        String searchId1 = "1010";
        Employee searchKey1 = new Employee("", "", searchId1);
        int index1 = sorter.binarySearch(emp, 0, num_employees - 1, searchKey1);
        if (index1 != -1) 
        {
            System.out.println("Employee found: " + emp[index1]);
        } 
        else 
        {
            System.out.println("Employee with ID " + searchId1 + " not found.");
        }
        
        // Search for Employee with ID 9999
        System.out.println("\nFind Employee ID 9999");
        String searchId2 = "9999";
        Employee searchKey2 = new Employee("", "", searchId2);
        int index2 = sorter.binarySearch(emp, 0, num_employees - 1, searchKey2);
        if (index2 != -1) 
        {
            System.out.println("Employee found: " + emp[index2]);
        } 
        else 
        {
            System.out.println("Employee with ID " + searchId2 + " not found.");
        }
    }
}