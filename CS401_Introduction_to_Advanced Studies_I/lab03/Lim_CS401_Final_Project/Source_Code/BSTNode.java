public class BSTNode<T> {
    private T info; // Data stored in the node
    private BSTNode<T> left; // Reference to the left child
    private BSTNode<T> right; // Reference to the right child

    // Initializes a node with data and null children
    public BSTNode(T info) {
        this.info = info;
        left = right = null;
    }

    // Retrieves the node's data
    public T getInfo() {
        return info;
    }

    // Sets the node's data
    public void setInfo(T info) {
        this.info = info;
    }

    // Retrieves the left child
    public BSTNode<T> getLeft() {
        return left;
    }

    // Sets the left child
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    // Retrieves the right child
    public BSTNode<T> getRight() {
        return right;
    }

    // Sets the right child
    public void setRight(BSTNode<T> right) {
        this.right = right;
    }
}