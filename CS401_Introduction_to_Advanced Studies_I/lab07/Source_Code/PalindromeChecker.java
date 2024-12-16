import java.util.Scanner;

public class PalindromeChecker 
{
    // Recursive function to check if a string is a palindrome
    public static boolean isPalindrome(String str, int left, int right) 
    {
        // Base case: If pointers cross or meet, it’s a palindrome
        if (left >= right) 
        {
            return true;
        }
        
        // Check if characters at current pointers are equal
        if (str.charAt(left) != str.charAt(right)) 
        {
            return false; // Return false if characters don't match
        }
        
        // Recursive call moving towards the center of the string
        return isPalindrome(str, left + 1, right - 1);
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user to enter the first string
        System.out.print("Enter a string: ");
        String input1 = scanner.nextLine();
        
        // Check if the first input string is a palindrome and display result
        if (isPalindrome(input1, 0, input1.length() - 1)) 
        {
            System.out.println("\"" + input1 + "\" is a palindrome.");
        } 
        else 
        {
            System.out.println("\"" + input1 + "\" is not a palindrome.");
        }
        
        // Prompt user to enter the second string
        System.out.print("\nEnter a string: ");
        String input2 = scanner.nextLine();
        
        // Check if the second input string is a palindrome and display result
        if (isPalindrome(input2, 0, input2.length() - 1)) 
        {
            System.out.println("\"" + input2 + "\" is a palindrome.");
        } 
        else 
        {
            System.out.println("\"" + input2 + "\" is not a palindrome.");
        }
        
        scanner.close(); // Close the scanner to release resources
    }
}