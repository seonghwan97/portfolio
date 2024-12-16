import java.util.Scanner;

public class Student implements Comparable<Student> {
    private int id;
    private String name;

    // Constructor
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // ID getter method
    public int getId() {
        return id;
    }

    // ID setters method
    public void setId(int id) {
        this.id = id;
    }

    // Name getter method
    public String getName() {
        return name;
    }

    // Name setter method
    public void setName(String name) {
        this.name = name;
    }

    // toString() method
    @Override
    public String toString() {
        return "Student{Name: '" + name + "', ID: " + id + "}";
    }

    // compareTo method
    @Override
    public int compareTo(Student other) {
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        } else {
        	int idComparison = Integer.compare(this.id, other.id);
            return idComparison;  // Compare by ID if names are the same
        }
    }

    // compare() method
    public static int compare(Student s1, Student s2) {
        return s1.compareTo(s2);
    }
    
    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Student 1
        System.out.println("Enter details for Student 1:");
        System.out.print("Name: ");
        String name1 = sc.nextLine();
        System.out.print("ID: ");
        int id1 = sc.nextInt();
        sc.nextLine();
        Student student1 = new Student(name1, id1);

        // Input Student 2
        System.out.println("Enter details for Student 2:");
        System.out.print("Name: ");
        String name2 = sc.nextLine();
        System.out.print("ID: ");
        int id2 = sc.nextInt();
        sc.nextLine();
        Student student2 = new Student(name2, id2);
        
        // Comparison between Student 1 and Student 2
        int comparisonResult1 = student1.compareTo(student2);
        if (comparisonResult1 < 0) {
            System.out.println(student1 + " comes before " + student2);
        } else if (comparisonResult1 > 0) {
            System.out.println(student2 + " comes before " + student1);
        } else {
            System.out.println(student1 + " and " + student2 + " are the same.");
        }

        // Input Student 3
        System.out.println("Enter details for Student 3:");
        System.out.print("Name: ");
        String name3 = sc.nextLine();
        System.out.print("ID: ");
        int id3 = sc.nextInt();
        sc.nextLine();
        Student student3 = new Student(name3, id3);

        // Comparison between Student 1 and Student 3
        int comparisonResult3 = student1.compareTo(student3);
        if (comparisonResult3 < 0) {
            System.out.println(student1 + " comes before " + student3);
        } else if (comparisonResult3 > 0) {
            System.out.println(student3 + " comes before " + student1);
        } else {
            System.out.println(student1 + " and " + student3 + " are the same.");
        }
        
        sc.close();
    }
}