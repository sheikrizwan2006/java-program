// File: UniversitySystem.java
public class Person {
    protected String name;
    protected int age;
    protected String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Introduce method
    public void introduce() {
        System.out.println("Hi! I'm " + name + ", " + age + " years old.");
    }

    // Get contact info
    public void getContactInfo() {
        System.out.println("Email: " + email);
    }
}

class Student extends Person {
    private String studentId;
    private String major;

    public Student(String name, int age, String email, String studentId, String major) {
        super(name, age, email);
        this.studentId = studentId;
        this.major = major;
    }

    public void attendLecture() {
        System.out.println(name + " is attending " + major + " lecture.");
    }

    public void submitAssignment() {
        System.out.println("Assignment submitted by " + studentId);
    }
}

class Professor extends Person {
    private String department;

    public Professor(String name, int age, String email, String department) {
        super(name, age, email);
        this.department = department;
    }

    public void conductClass() {
        System.out.println("Prof. " + name + " is teaching " + department);
    }
}

public class UniversitySystem {
    public static void main(String[] args) {
        // Student object
        Student s = new Student("Alice", 20, "alice@uni.edu", "CS2021", "Computer Science");

        // Upcasting
        Person p = s;

        // Accessible methods from Person
        p.introduce();
        p.getContactInfo();

        // ❌ p.attendLecture();  // Compile-time error (not accessible via Person reference)

        // Access common field
        System.out.println("Accessing field through upcast: " + p.name);

        /*
         * 🔑 Explanation:
         * Upcasting is safe because Student IS-A Person.
         * But only Person's methods/fields are accessible.
         * Subclass-specific methods (attendLecture, submitAssignment)
         * are hidden unless we downcast back to Student.
         */
    }
}
