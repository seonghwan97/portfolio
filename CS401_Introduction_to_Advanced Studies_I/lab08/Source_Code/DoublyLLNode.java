public class DoublyLLNode<T> extends LLNode<T> 
{
    // Reference to the previous node in the doubly linked list
    private DoublyLLNode<T> back;
    
    // Constructor to initialize the node with data, with no back reference
    public DoublyLLNode(T info)
    {
        super(info);
        back = null;
    }
    
    // Returns the next node in the list, cast to DoublyLLNode type
    @Override
    public DoublyLLNode<T> getLink()
    {
        return (DoublyLLNode<T>) link;
    }
    
    // Sets the next node in the list
    public void setLink(DoublyLLNode<T> link)
    {
        this.link = link;
    }
    
    // Sets the previous node in the list
    public void setBack(DoublyLLNode<T> back)
    {
        this.back = back;
    }
    
    // Returns the previous node in the list
    public DoublyLLNode<T> getBack()
    {
        return back;
    }
}