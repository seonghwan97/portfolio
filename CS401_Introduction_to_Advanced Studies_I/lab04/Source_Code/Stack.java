public class Stack {
    private Employee[] stackArr;
    private int top;
    private int maxSize;

    // Constructor
    public Stack(int size) {
        maxSize = size;
        stackArr = new Employee[maxSize];
        top = -1;
    }

    // Push operation (Setter)
    public void push(Employee emp) {
        if (isFull()) {
            System.out.println("Stack overflow.");
        } else {
            stackArr[++top] = emp;
        }
    }

    // Pop operation (Getter)
    public Employee pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow.");
            return null;
        } else {
            return stackArr[top--];
        }
    }

    // Top operation
    public Employee top() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        } else {
            return stackArr[top];
        }
    }

    // isEmpty check
    public boolean isEmpty() {
        return top == -1;
    }

    // isFull check
    public boolean isFull() {
        return top == maxSize - 1;
    }
}
