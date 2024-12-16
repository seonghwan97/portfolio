import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Stack stack = new Stack(30);  // Set a stack size 30

        // Read emp.txt file
        try (Scanner scanner = new Scanner(new File("emp.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // Read each line
                String[] parts = line.split(" "); // divide by blank
                String firstName = parts[0];
                String lastName = parts[1];
                String id = parts[2];
                Employee emp = new Employee(firstName, lastName, id);
                stack.push(emp); // Push operations
            }
        } catch (FileNotFoundException e) { // Except when there is no file
        	System.out.println("Error: The file 'emp.txt' was not found.");
        }

        // Stack operations
        System.out.println("Top element of the stack: " + stack.top());

        // Pop operations
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Popped element: " + stack.pop());

        // Check the top element
        System.out.println("Top element after popping two elements: " + stack.top());

        // Manually push a new Employee
        Employee newEmp = new Employee("Seonghwan", "Lim", "1031");
        stack.push(newEmp);
        
        // Check the top element
        System.out.println("Top element after pushing new employee: " + stack.top());
    }
}
