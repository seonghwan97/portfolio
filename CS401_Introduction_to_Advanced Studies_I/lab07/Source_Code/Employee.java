public class Employee implements Comparable<Employee>
{
    // Private instance variables to store employee's first name, last name, and ID
    private String firstName;
    private String lastName;
    private String id;

    // Constructor to initialize an Employee object with first name, last name, and ID
    public Employee(String firstName, String lastName, String id) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    // Getter method to return the employee's first name
    public String getFirstName() 
    {
        return firstName;
    }

    // Getter method to return the employee's last name
    public String getLastName() 
    {
        return lastName;
    }

    // Getter method to return the employee's ID
    public String getId() 
    {
        return id;
    }
    
    // Overriding the compareTo() method to compare id elements
    @Override
    public int compareTo(Employee other) {
        return this.id.compareTo(other.id);
    }

    // Overriding the toString() method to return a formatted string representing the Employee object
    @Override
    public String toString() 
    {
        return "[Employee Name: " + firstName + " " + lastName + ", ID: " + id + "]";
    }
}
