import java.util.LinkedList;

public class BST 
{
    private TreeNode root; // Root node of the binary search tree
    
    // Constructor to initialize an empty tree
    public BST() 
    {
        root = null;
    }

    // Inserts a value into the binary search tree
    public void insert(int value) 
    {
        root = insertRec(root, value); // Helper function for recursive insertion
    }

    // Helper function to recursively insert a value into the tree
    private TreeNode insertRec(TreeNode node, int value) 
    {
        if (node == null) 
        {
            return new TreeNode(value); // Create a new node if the current node is null
        }
        if (value < node.getData()) 
        {
            node.setLeft(insertRec(node.getLeft(), value)); // Insert in the left subtree
        } 
        else if (value > node.getData()) 
        {
            node.setRight(insertRec(node.getRight(), value)); // Insert in the right subtree
        }
        return node; // Return the current node
    }

    // Deletes a value from the binary search tree
    public void delete(int value) 
    {
        root = deleteRec(root, value); // Helper function for recursive deletion
    }

    // Helper function to recursively delete a value from the tree
    private TreeNode deleteRec(TreeNode node, int value) 
    {
        if (node == null) return null; // If node is null, return null

        if (value < node.getData()) 
        {
            node.setLeft(deleteRec(node.getLeft(), value)); // Search in the left subtree
        } 
        else if (value > node.getData()) 
        {
            node.setRight(deleteRec(node.getRight(), value)); // Search in the right subtree
        } 
        else 
        {
            // Node to be deleted found
            if (node.getLeft() == null) return node.getRight(); // Replace with right child
            if (node.getRight() == null) return node.getLeft(); // Replace with left child

            // Node with two children: Replace with the minimum value in the right subtree
            int minValue = findMin(node.getRight());
            node.setData(minValue);
            node.setRight(deleteRec(node.getRight(), minValue)); // Delete the in-order successor
        }
        return node; // Return the current node
    }

    // Finds the minimum value in a subtree
    private int findMin(TreeNode node) 
    {
        while (node.getLeft() != null) 
        {
            node = node.getLeft(); // Traverse to the leftmost node
        }
        return node.getData();
    }

    // Searches for a value in the binary search tree
    public boolean search(int value) 
    {
        return searchRec(root, value); // Helper function for recursive search
    }

    // Helper function to recursively search for a value in the tree
    private boolean searchRec(TreeNode node, int value) 
    {
        if (node == null) 
        	return false; // Value not found
        if (value == node.getData()) 
        	return true; // Value found
        if (value < node.getData()) 
        	return searchRec(node.getLeft(), value); // Search in left subtree
        return searchRec(node.getRight(), value); // Search in right subtree
    }

    // Calculates the maximum depth of the binary search tree
    public int getMaxDepth() 
    {
        return maxDepthRec(root); // Helper function for recursive depth calculation
    }

    // Helper function to recursively calculate the depth of the tree
    private int maxDepthRec(TreeNode node) 
    {
        if (node == null) 
        	return -1; // Base case: null node has depth 0
        return 1 + Math.max(maxDepthRec(node.getLeft()), maxDepthRec(node.getRight()));
    }

    // Recursively calculates the size of the binary search tree
    public int getSizeRecursive() 
    {
        return sizeRec(root); // Helper function for recursive size calculation
    }

    // Helper function to recursively calculate the size of the tree
    private int sizeRec(TreeNode node) 
    {
        if (node == null) 
        	return 0; // Base case: null node has size 0
        return sizeRec(node.getLeft()) + sizeRec(node.getRight());
    }

    // Iteratively calculates the size of the binary search tree
    public int getSizeIterative() 
    {
        if (root == null) return 0; // If the tree is empty, size is 0

        int size = 0;
        LinkedList<TreeNode> stack = new LinkedList<>(); // Use LinkedList as a stack
        stack.push(root); // Start with the root node

        while (!stack.isEmpty()) 
        {
            TreeNode current = stack.pop(); // Pop the top node
            size++; // Count the current node
            if (current.getLeft() != null) stack.push(current.getLeft()); // Push left child to stack
            if (current.getRight() != null) stack.push(current.getRight()); // Push right child to stack
        }
        return size; // Return the total size
    }

    // Performs in-order traversal of the binary search tree
    public void inorderTraversal() 
    {
        inorderRec(root); // Helper function for recursive in-order traversal
        System.out.println();
    }

    // Helper function for recursive in-order traversal
    private void inorderRec(TreeNode node) 
    {
        if (node != null) 
        {
            inorderRec(node.getLeft()); // Visit left subtree
            System.out.print(node.getData() + " "); // Visit current node
            inorderRec(node.getRight()); // Visit right subtree
        }
    }

    // Performs pre-order traversal of the binary search tree
    public void preorderTraversal() 
    {
        preorderRec(root); // Helper function for recursive pre-order traversal
        System.out.println();
    }

    // Helper function for recursive pre-order traversal
    private void preorderRec(TreeNode node) 
    {
        if (node != null) 
        {
            System.out.print(node.getData() + " "); // Visit current node
            preorderRec(node.getLeft()); // Visit left subtree
            preorderRec(node.getRight()); // Visit right subtree
        }
    }

    // Performs post-order traversal of the binary search tree
    public void postorderTraversal() 
    {
        postorderRec(root); // Helper function for recursive post-order traversal
        System.out.println();
    }

    // Helper function for recursive post-order traversal
    private void postorderRec(TreeNode node) 
    {
        if (node != null) 
        {
            postorderRec(node.getLeft()); // Visit left subtree
            postorderRec(node.getRight()); // Visit right subtree
            System.out.print(node.getData() + " "); // Visit current node
        }
    }
}
