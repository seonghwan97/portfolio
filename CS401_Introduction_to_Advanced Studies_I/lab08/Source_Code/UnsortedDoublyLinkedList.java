public class UnsortedDoublyLinkedList<T extends Comparable<T>> implements UnsortedDoublyLinkedListInterface<T>
{
    // Points to the first node in the doubly linked list
    protected DoublyLLNode<T> head;
    // Points to the last node in the doubly linked list
    protected DoublyLLNode<T> tail;
    // Stores the number of elements in the list
    protected int numElements;

    // Helper variables for search and modification operations
    protected boolean found;
    protected DoublyLLNode<T> location;
    protected DoublyLLNode<T> previous;

    // Constructor to initialize an empty doubly linked list
    public UnsortedDoublyLinkedList()
    {
        head = null;
        tail = null;
        numElements = 0;
    }

    // Returns the current size of the list
    public int size()
    {
        return numElements;
    }

    // Checks if the list contains a node with the target value
    public boolean contains(int target)
    {
        find(target);
        return found;
    }

    // Removes the node with the target value if it exists
    public boolean remove(int target)
    {
        find(target);
        if (found)
        {
            if (location == head)  // Removing the first node
            {
                head = head.getLink();
                if (head != null)
                {
                    head.setBack(null);
                }
                else
                {
                    tail = null; // List becomes empty
                }
            }
            else if (location == tail)  // Removing the last node
            {
                tail = tail.getBack();
                if (tail != null)
                {
                    tail.setLink(null);
                }
                else
                {
                    head = null; // List becomes empty
                }
            }
            else  // Removing a middle node
            {
                previous.setLink(location.getLink());
                if (location.getLink() != null)
                {
                    location.getLink().setBack(previous);
                }
            }
            numElements--;
        }
        return found;
    }

    // Retrieves the element with the specified target value
    public T get(int element)
    {
        find(element);
        return found ? location.getInfo() : null;
    }

    // Helper method to locate the target element in the list
    protected void find(int target)
    {
        location = head;
        found = false;
        previous = null;
        
        while (location != null)
        {
            if (location.getInfo().equals(target))
            {
                found = true;
                return;
            }
            else
            {
                previous = location;
                location = location.getLink();
            }
        }
    }
 
    // Adds a new element to the end of the list
    public void add(T element)
    {
        DoublyLLNode<T> newNode = new DoublyLLNode<>(element);

        if (head == null) // If the list is empty
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.setLink(newNode);
            newNode.setBack(tail);
            tail = newNode;
        }
        numElements++;
    }

    // Returns a string representation of the list
    public String toString()
    {
        String listString = "";
        DoublyLLNode<T> currNode = head;
        while (currNode != null)
        {
            listString += " " + currNode.getInfo() + "\n";
            currNode = currNode.getLink();
        }
        return listString;
    }
    
    // Displays the list elements from the head to the tail
    public void displayForward()
    {
        System.out.println("Forward Traversal:");
        DoublyLLNode<T> currNode = head;
        while (currNode != null)
        {
            System.out.print(currNode.getInfo() + " ");
            currNode = currNode.getLink();
        }
        System.out.println();
    }
    
    // Displays the list elements from the tail to the head
    public void displayBackward()
    {
        System.out.println("Backward Traversal:");
        DoublyLLNode<T> currNode = tail;
        while (currNode != null)
        {
            System.out.print(currNode.getInfo() + " ");
            currNode = currNode.getBack();
        }
        System.out.println();
    }
}