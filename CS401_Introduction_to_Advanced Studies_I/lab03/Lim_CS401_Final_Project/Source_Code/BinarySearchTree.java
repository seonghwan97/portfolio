public class BinarySearchTree<T extends Comparable<T>> {
    private BSTNode<T> root; // Root node of the tree
    private int size; // Number of nodes in the tree
    private int comparisonCount = 0; // Tracks comparisons in search operations

    // Constructor: Initializes an empty tree
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    // Checks if the tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Returns the number of nodes in the tree
    public int size() {
        return size;
    }

    // Returns the smallest value in the tree
    public T min() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMin(root).getInfo();
    }

    // Finds the node with the smallest value
    private BSTNode<T> findMin(BSTNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    // Returns the largest value in the tree
    public T max() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMax(root).getInfo();
    }

    // Finds the node with the largest value
    private BSTNode<T> findMax(BSTNode<T> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    // Adds a value to the tree
    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot add null value");
        }
        root = addRecursive(root, value);
        size++;
    }

    // Recursively adds a value to the tree
    private BSTNode<T> addRecursive(BSTNode<T> node, T value) {
        if (node == null) {
            return new BSTNode<>(value);
        }

        if (value.compareTo(node.getInfo()) <= 0) {
            node.setLeft(addRecursive(node.getLeft(), value));
        } else if (value.compareTo(node.getInfo()) > 0) {
            node.setRight(addRecursive(node.getRight(), value));
        }
        return node;
    }

    // Removes a value from the tree
    public boolean remove(T value) {
        if (isEmpty() || !contains(value)) {
            System.out.println("Value " + value + " not found, cannot remove.");
            return false;
        }

        root = removeRecursive(root, value);
        size--;
        return true;
    }

    // Recursively removes a value from the tree
    private BSTNode<T> removeRecursive(BSTNode<T> node, T value) {
        if (node == null) return null;

        if (value.compareTo(node.getInfo()) < 0) {
            node.setLeft(removeRecursive(node.getLeft(), value));
        } else if (value.compareTo(node.getInfo()) > 0) {
            node.setRight(removeRecursive(node.getRight(), value));
        } else {
            // Node to delete has been found
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

         // Replace the node's value with the in-order predecessor (largest value in the left subtree)
            BSTNode<T> predecessor = findMax(node.getLeft());
            node.setInfo(predecessor.getInfo());
            node.setLeft(removeRecursive(node.getLeft(), predecessor.getInfo()));
        }
        return node;
    }

    // Checks if the tree contains a value
    public boolean contains(T value) {
        resetComparisonCount();
        return findRecursive(root, value);
    }

    // Recursively searches for a value
    private boolean findRecursive(BSTNode<T> node, T value) {
        if (node == null) return false;

        comparisonCount++;
        if (value.compareTo(node.getInfo()) < 0) {
            return findRecursive(node.getLeft(), value);
        } else if (value.compareTo(node.getInfo()) > 0) {
            return findRecursive(node.getRight(), value);
        } else {
            return true;
        }
    }

    // Builds a balanced tree from a sorted array
    public void buildBalancedTree(T[] sortedArray) {
        root = buildBalancedTreeRecursive(sortedArray, 0, sortedArray.length - 1);
    }

    private BSTNode<T> buildBalancedTreeRecursive(T[] array, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        BSTNode<T> node = new BSTNode<>(array[mid]);
        node.setLeft(buildBalancedTreeRecursive(array, start, mid - 1));
        node.setRight(buildBalancedTreeRecursive(array, mid + 1, end));
        return node;
    }

    // Visualizes the tree structure
    public void visualizeTree() {
        visualizeTreeHelper(root, "", true);
    }

    private void visualizeTreeHelper(BSTNode<T> node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getInfo());
            visualizeTreeHelper(node.getLeft(), prefix + (isTail ? "    " : "│   "), false);
            visualizeTreeHelper(node.getRight(), prefix + (isTail ? "    " : "│   "), true);
        }
    }

    // Resets the comparison counter
    public void resetComparisonCount() {
        comparisonCount = 0;
    }

    // Returns the current comparison count
    public int getComparisonCount() {
        return comparisonCount;
    }

    // Returns the root node
    public BSTNode<T> getRoot() {
        return root;
    }
}