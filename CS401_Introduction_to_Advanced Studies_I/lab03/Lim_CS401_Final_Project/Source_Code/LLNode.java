public class LLNode<T> {
    protected T info; // Data stored in the node
    protected LLNode<T> link; // Reference to the next node

    // Initializes the node with data and null link
    public LLNode(T info) {
        if (info == null) {
            throw new IllegalArgumentException("Node data cannot be null.");
        }
        this.info = info;
        this.link = null;
    }

    // Sets the node's data
    public void setInfo(T info) {
        if (info == null) {
            throw new IllegalArgumentException("Node data cannot be null.");
        }
        this.info = info;
    }

    // Retrieves the node's data
    public T getInfo() {
        return info;
    }

    // Sets the link to the next node
    public void setLink(LLNode<T> link) {
        this.link = link;
    }

    // Retrieves the reference to the next node
    public LLNode<T> getLink() {
        return link;
    }
}