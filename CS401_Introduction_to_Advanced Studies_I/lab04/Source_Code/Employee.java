public class Employee {
    private String firstName;
    private String lastName;
    private String id;

    // Constructor
    public Employee(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    // Getter methods
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    // toString method
    @Override
    public String toString() {
        return "| Name: " + getFirstName() + " " + getLastName() + " | ID: " + getId() + " |";
    }
}
