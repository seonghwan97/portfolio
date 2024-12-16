import java.util.Scanner;

public class MainApplication {
	public static void main(String[] args) {
		// Create a new instance of the Circle class
		Circle circle = new Circle();
		
		// Create a Scanner
		Scanner sc = new Scanner(System.in);
		
		// Enter a valid radius until a double is entered
        while (true) {
        	// Prompt the user to enter the radius of the circle
            System.out.println("Enter the radius of the circle: ");
            
            // Check if the input is a valid double
            if (sc.hasNextDouble()) {
            	// Set the radius using the user's input
            	circle.setRadius(sc.nextDouble());
                break;	// Exit the loop if a valid double is entered
            } else {
                System.out.println("Invalid input. Please enter a numeric value for the radius.");
                sc.next();	// Consume the invalid input
            }
        }
        
		// Close the scanner
		sc.close();
		
		// Display the area of the circle by the toString() method
		System.out.println(circle.toString());
	}
}
