public class Employee implements Comparable<Employee>
{
    // Private instance variables to store employee's first name, last name, and ID
    private String firstName;
    private String lastName;
    private int id;

    // Constructor to initialize an Employee object with first name, last name, and ID
    public Employee(String firstName, String lastName, int id) 
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
    public int getId() 
    {
        return id;
    }
    
    // Method to compare Employees based on ID, for sorting purposes
    @Override
    public int compareTo(Employee other) 
    {
        return Integer.compare(this.id, other.id);
    }
    
    // Checks equality based on the ID field, supports comparison with Integer type as well
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        else if (obj == null) 
        {
            return false;
        }
        else if (obj instanceof Employee) 
        {
            Employee other = (Employee) obj;
            return this.id == other.id;
        } 
        else if (obj instanceof Integer) 
        {
            return this.id == (Integer) obj;
        }
        return false;
    }

    // Overriding the toString() method to return a formatted string representing the Employee object
    @Override
    public String toString() 
    {
        return "[Employee Name: " + firstName + " " + lastName + ", ID: " + id + "]";
    }
}
