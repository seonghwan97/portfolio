public class Employee implements Comparable<Employee> {
    private int empID; // Employee ID
    private String firstName; // First name
    private String lastName; // Last name
    private String title; // Job title

    // Initialize Employee with ID, first name, last name, and title
    public Employee(int empID, String firstName, String lastName, String title) {
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }

    // Getters
    public int getEmpID() {
        return empID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    // Compare employees by empID
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.empID, other.empID);
    }

    // String representation of Employee
    @Override
    public String toString() {
        return String.format("EmpID: %d, Name: %s %s, Title: %s", empID, firstName, lastName, title);
    }

    // Compare employees for equality based on empID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same object reference
        if (obj == null || getClass() != obj.getClass()) return false; // Null or different class
        Employee employee = (Employee) obj;
        return empID == employee.empID; // Compare by empID
    }
}