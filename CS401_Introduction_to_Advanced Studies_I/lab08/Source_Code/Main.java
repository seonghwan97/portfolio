import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        SortedLinkedListInterface<Employee> employeeSortedLinkedList = new SortedLinkedList<Employee>();

        // Try reading data from the file "emp.txt"
        try (Scanner scanner = new Scanner(new File("emp.txt"))) 
        {
            // Loop to read each line from the file and create Employee objects
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" "); // Split each line into first name, last name, and ID
                String firstName = parts[0];
                String lastName = parts[1];
                int id = Integer.parseInt(parts[2]);

                Employee employee = new Employee(firstName, lastName, id);
                employeeSortedLinkedList.add(employee);
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Error: The file 'emp.txt' was not found.");
            System.exit(0); // Exit the program if the file is not found
        }
        catch (NumberFormatException e) 
        {
            System.out.println("Error: Invalid ID format in 'emp.txt'. Please ensure IDs are integers.");
            System.exit(0); // Exit the program if ID format is incorrect
        }
        
        System.out.println("Sorted Linked List Contents:");
        System.out.println(employeeSortedLinkedList);
        
        // Adding a new employee and checking if ID is already occupied
        System.out.println("Try to add an employee.");
        Employee newEmployee1 = new Employee("Seonghwan", "Lim", 1030);
        if (employeeSortedLinkedList.contains(newEmployee1.getId()))
        {
            System.out.println("This ID is already occupied.\n");
        }
        else
        {
            employeeSortedLinkedList.add(newEmployee1);
            System.out.println("Adding this ID is successful.");
            System.out.println("Sorted Linked List Contents After Adding ID 1030:");
            System.out.println(employeeSortedLinkedList);
        }
        
        Employee newEmployee2 = new Employee("Seonghwan", "Lim", 1031);
        if (employeeSortedLinkedList.contains(newEmployee2.getId()))
        {
            System.out.println("This ID is already occupied.");
        }
        else
        {
            employeeSortedLinkedList.add(newEmployee2);
            System.out.println("Adding this ID is successful.");
            System.out.println("Sorted Linked List Contents After Adding ID 1031:");
            System.out.println(employeeSortedLinkedList);
        }
        
        // Removing an employee by ID
        System.out.println("Try to remove an employee.");
        if (employeeSortedLinkedList.remove(1001))
        {
            System.out.println("Removing this employee is successful.");
            System.out.println("Sorted Linked List Contents After Deleting ID 1001:");
            System.out.println(employeeSortedLinkedList);
        }
        else
        {
            System.out.println("This employee does not exist.");
        }
        
        SortedLinkedListInterface<Employee> employeeSortedCircularLinkedList = new SortedCircularLinkedList<Employee>();

        // Read data from "emp.txt" and add the first 8 employees
        try (Scanner scanner = new Scanner(new File("emp.txt"))) 
        {
            int count = 0;
            // Loop to read each line from the file and create Employee objects
            while (scanner.hasNextLine() && count < 8) 
            {
                String line = scanner.nextLine();
                String[] parts = line.split(" "); // Split each line into first name, last name, and ID
                String firstName = parts[0];
                String lastName = parts[1];
                int id = Integer.parseInt(parts[2]);

                Employee employee = new Employee(firstName, lastName, id);
                employeeSortedCircularLinkedList.add(employee);
                count++;
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Error: The file 'emp.txt' was not found.");
            System.exit(0); // Exit the program if the file is not found
        }
        catch (NumberFormatException e) 
        {
            System.out.println("Error: Invalid ID format in 'emp.txt'. Please ensure IDs are integers.");
            System.exit(0); // Exit the program if ID format is incorrect
        }
        
        System.out.println("Initial Circular List:");
        System.out.println(employeeSortedCircularLinkedList);

        // Try to remove an employee from the circular linked list by ID
        System.out.println("Try to remove an employee with ID 1002.");
        if (employeeSortedCircularLinkedList.remove(1002))
        {
            System.out.println("Removing this employee is successful.");
            System.out.println("After Deleting ID 1002:");
            System.out.println(employeeSortedCircularLinkedList);
        }
        else
        {
            System.out.println("This employee does not exist.");
        }
        
        UnsortedDoublyLinkedListInterface<Integer> integerUnsortedLinkedList = new UnsortedDoublyLinkedList<>();
        
        // Adding integers to the unsorted doubly linked list
        integerUnsortedLinkedList.add(1);
        integerUnsortedLinkedList.add(2);
        integerUnsortedLinkedList.add(3);
        integerUnsortedLinkedList.add(4);
        integerUnsortedLinkedList.add(5);
        
        // Display the list in both forward and backward directions
        integerUnsortedLinkedList.displayForward();
        integerUnsortedLinkedList.displayBackward();
    }
}