public class MainApplication 
{
    public static void main(String[] args) 
    {
        BST bst = new BST(); // Create a Binary Search Tree

        // Insert values into the BST
        int[] values = {25, 15, 50, 10, 22, 35, 70, 4, 12, 18, 24, 31, 44, 66, 90};
        for (int value : values) 
        {
            bst.insert(value);
        }

        // Perform and display tree traversals
        System.out.println("In-order Traversal:");
        bst.inorderTraversal(); // Left -> Root -> Right

        System.out.println("Pre-order Traversal:");
        bst.preorderTraversal(); // Root -> Left -> Right

        System.out.println("Post-order Traversal:");
        bst.postorderTraversal(); // Left -> Right -> Root

        // Display tree analysis results
        System.out.println("Maximum Depth: " + bst.getMaxDepth());
        System.out.println("Size (Recursive): " + bst.getSizeRecursive());
        System.out.println("Size (Iterative): " + bst.getSizeIterative());

        // Search for specific values
        int[] searchValues = {22, 70, 100}; // Values to search in the BST
        for (int target : searchValues) 
        {
            boolean found = bst.search(target); // Execute search
            System.out.println("Search for " + target + ": " + (found ? "Found" : "Not Found"));
        }
    }
}
