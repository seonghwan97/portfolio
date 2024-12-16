public class SortedCircularLinkedList<T extends Comparable<T>> implements SortedLinkedListInterface<T>
{
    // Points to the start of the circular linked list
    protected CircularLLNode<T> list;
    // Used to keep track of the current position for iteration
    protected CircularLLNode<T> currentPos;
    // Number of elements in the list
    protected int numElements;

    // Helper variables for search and removal operations
    protected boolean found;
    protected CircularLLNode<T> location;
    protected CircularLLNode<T> previous;

    // Constructor to initialize an empty circular linked list
    public SortedCircularLinkedList()
    {
        list = null;
        currentPos = null;
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
            if (list == list.getLink()) // Only one node in the list
            {
                list = null;
            }
            else
            {
                if (location == list) // Removing the head node
                {
                    CircularLLNode<T> lastNode = list;
                    // Find the last node to update its link
                    while(lastNode.getLink() != list)
                    {
                        lastNode = lastNode.getLink();
                    }
                    lastNode.setLink(list.getLink());
                    list = list.getLink();
                }
                else
                {
                    previous.setLink(location.getLink());
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
        boolean moreToSearch;
        location = list;
        found = false;

        moreToSearch = (location != null);
        while (moreToSearch && !found)
        {
            if (location.getInfo().equals(target))
            {
                found = true;
            }
            else
            {
                previous = location;
                location = location.getLink();
                moreToSearch = (location != list); // Stop if we've looped back to the start
            }
        }
    }
    
    // Adds a new element in sorted order to maintain list's sorted property
    public void add(T element)
    {
        CircularLLNode<T> newNode = new CircularLLNode<>(element);

        if (list == null) // If list is empty
        {
            list = newNode;
        }
        else
        {
            location = list;
            previous = null;

            // Traverse to find the correct position for the new node
            do
            {
                if (element.compareTo(location.getInfo()) < 0)
                    break;

                previous = location;
                location = location.getLink();
            } while (location != list);

            if (previous == null) // Inserting at the head
            {
                CircularLLNode<T> lastNode = list;
                while (lastNode.getLink() != list)
                {
                    lastNode = lastNode.getLink();
                }
                newNode.setLink(list);
                lastNode.setLink(newNode);
                list = newNode;
            }
            else // Inserting between nodes or at the end
            {
                previous.setLink(newNode);
                newNode.setLink(location);
            }
        }
        numElements++;
    }

    // Returns a string representation of the list, showing circular structure
    public String toString()
    {
        String listString = "";
        if (list != null)
        {
            CircularLLNode<T> current = list;
            do
            {
                listString += current.getInfo().toString();
                current = current.getLink();
                if (current != list)
                {
                    listString += " -> ";
                }
            } while (current != list);

            listString += " -> (back to start)\n";
        }
        else
        {
            listString += "The list is empty.";
        }
        return listString;
    }

    // Resets the position for iterating through the list
    public void reset()
    {
        if (list != null)
        {
            currentPos = list.getLink();
        }
    }
    
    // Returns the next element in the list and advances the position
    public T getNext()
    {
        T next = currentPos.getInfo();
        currentPos = currentPos.getLink();
        return next;
    }
}