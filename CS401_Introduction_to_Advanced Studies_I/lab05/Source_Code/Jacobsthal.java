package lab05;
import java.util.Scanner;

public class Jacobsthal {
    // Recursive calculation of the nth Jacobsthal number
    public static long jacobsthalRecursive(int n) {
        if (n == 0) 
        	return 0; // Base case J(0)
        else if (n == 1) 
        	return 1; // Base case J(1)
        else
        	return jacobsthalRecursive(n - 1) + 2 * jacobsthalRecursive(n - 2); // Recursive relation
    }

    // Iterative calculation of the nth Jacobsthal number
    public static long jacobsthalIterative(int n) {
        long jacobsthal = 0, valueBack = 0, valueFront = 1;

        if (n == 0) 
        	return valueBack; // J(0)
        if (n == 1) 
        	return valueFront; // J(1)

        // Iteratively calculate Jacobsthal number for n > 1
        while (n > 1) {
            jacobsthal = valueFront + 2 * valueBack;
            valueBack = valueFront;
            valueFront = jacobsthal;
            n--;
        }
        return jacobsthal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input non-negative integer");
        int n = scanner.nextInt();
        scanner.close();

        if (n < 0) 
        {
            System.out.println("Error: Please input a non-negative integer.");
            return;
        }

        // Recursive calculation and timing
        System.out.println("Recursive version:");
        double recursive_startTime = System.currentTimeMillis();
        for (int i = 0; i <= n; i++) {
            System.out.print(jacobsthalRecursive(i) + " ");
        }
        double recursive_endTime = System.currentTimeMillis() - recursive_startTime;
        System.out.println("\nTime taken: " + String.format("%.4f", recursive_endTime) + " ms\n");

        // Iterative calculation and timing
        System.out.println("Iterative version:");
        double iterative_startTime = System.currentTimeMillis();
        for (int i = 0; i <= n; i++) {
            System.out.print(jacobsthalIterative(i) + " ");
        }
        double iterative_endTime = System.currentTimeMillis() - iterative_startTime;
        System.out.println("\nTime taken: " + String.format("%.4f", iterative_endTime) + " ms");
    }
}
