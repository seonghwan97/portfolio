public class LLNode<T>
{
    // Stores the data of this node
    protected T info;
    // Reference to the next node in the linked list
    protected LLNode<T> link;
    
    // Constructor to initialize the node with data
    public LLNode(T info) 
    {
        this.info = info;
        link = null; // Initialize link to null
    }
    
    // Sets the data for this node
    public void setInfo(T info) 
    {
        this.info = info; 
    }
    
    // Returns the data stored in this node
    public T getInfo() 
    {
        return info; 
    }
    
    // Sets the reference to the next node
    public void setLink(LLNode<T> link) 
    {
        this.link = link; 
    }
    
    // Returns the reference to the next node
    public LLNode<T> getLink() 
    {
        return link; 
    }
}