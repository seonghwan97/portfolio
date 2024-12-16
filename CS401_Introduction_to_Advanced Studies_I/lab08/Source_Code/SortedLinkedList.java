public class SortedLinkedList<T extends Comparable<T>> implements SortedLinkedListInterface<T>
{
    // Reference to the first node in the list
    protected LLNode<T> list;
    // Stores the number of elements in the list
    protected int numElements;

    // Helper variables for searching and modifying list elements
    protected boolean found;
    protected LLNode<T> location;
    protected LLNode<T> previous;

    // Constructor to initialize an empty linked list
    public SortedLinkedList()
    {
        list = null;
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
            // Update the list references to remove the target node
            if (location == list)
            {
                list = list.getLink();
            }
            else
            {
                previous.setLink(location.getLink());
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

    // Helper method to find the target in the list; sets `found`, `location`, and `previous`
    protected void find(int target)
    {
        location = list;
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
 
    // Adds a new element in sorted order
    public void add(T element)
    {
        LLNode<T> newNode = new LLNode<>(element);
        location = list;
        previous = null;

        if (list == null) // If the list is empty
        {
            list = newNode;
        }
        else
        {
            // Traverse to find the correct position for the new node
            while (location != null && element.compareTo(location.getInfo()) >= 0)
            {
                previous = location;
                location = location.getLink();
            }

            // Insert the new node at the beginning or between nodes
            if (previous == null)
            {
                newNode.setLink(list);
                list = newNode;
            }
            else
            {
                previous.setLink(newNode);
                newNode.setLink(location);
            }
        }
        numElements++;
    }

    // Returns a string representation of the list
    public String toString()
    {
        String listString = "";
        LLNode<T> currNode = list;
        while (currNode != null)
        {
            listString += " " + currNode.getInfo() + "\n";
            currNode = currNode.getLink();
        }
        return listString;
    }
}