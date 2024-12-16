public class CircularLLNode<T> extends LLNode<T> 
{
    // Constructor to initialize a circular linked node with itself as the link
    public CircularLLNode(T info) 
    {
        super(info);
        this.link = this; // Points to itself to create a circular reference
    }

    // Returns the next node in the circular linked list
    @Override
    public CircularLLNode<T> getLink() 
    {
        return (CircularLLNode<T>) link;
    }

    // Sets the link to another circular node
    public void setLink(CircularLLNode<T> link) 
    {
        this.link = link;
    }
}