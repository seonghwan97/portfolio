import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        // Define the capacity for the queues
        int capacity = 5;
        int enqueueCount = 0;

        // Create a FixedFrontQueue and a FloatingFrontQueue with the same capacity
        QueueInterface<Employee> fixedQueue = new FixedFrontQueue<>(capacity);
        QueueInterface<Employee> floatingQueue = new FloatingFrontQueue<>(capacity);
        
        // Try reading data from the file "emp.txt"
        try (Scanner scanner = new Scanner(new File("emp.txt"))) 
        {
            // Loop to read each line from the file and add employees to the queues
            while (scanner.hasNextLine() && enqueueCount < 5)
            {
                String line = scanner.nextLine();
                String[] parts = line.split(" "); // Split each line into first name, last name, and ID
                String firstName = parts[0];
                String lastName = parts[1];
                String id = parts[2];

                // Create a new Employee object
                Employee emp = new Employee(firstName, lastName, id);

                // Enqueue the employee to both the FixedFrontQueue and FloatingFrontQueue
                fixedQueue.enqueue(emp);
                floatingQueue.enqueue(emp);
                enqueueCount++;
            }
        } 
        catch (FileNotFoundException e) 
        {
            // Handle the case where the file is not found
            System.out.println("Error: The file 'emp.txt' was not found.");
            System.exit(0); // Exit the program if the file is not found
        }
        
        // Display operations for the FixedFrontQueue
        System.out.println("Fixed Front Queue:");
        System.out.println("Initial Queue Contents:");
        fixedQueue.printQueue(); // Print the initial contents of the FixedFrontQueue

        // Dequeue two elements from the FixedFrontQueue
        fixedQueue.dequeue();
        fixedQueue.dequeue();
        System.out.println("\nAfter dequeuing twice:");
        fixedQueue.printQueue(); // Print the contents of the FixedFrontQueue after two dequeues
        
        // Display operations for the FloatingFrontQueue
        System.out.println("\nFloating Front Queue:");
        System.out.println("Initial Queue Contents:");
        floatingQueue.printQueue(); // Print the initial contents of the FloatingFrontQueue

        // Dequeue two elements from the FloatingFrontQueue
        floatingQueue.dequeue();
        floatingQueue.dequeue();
        System.out.println("\nAfter dequeuing twice:");
        floatingQueue.printQueue(); // Print the contents of the FloatingFrontQueue after two dequeues
    }
}
