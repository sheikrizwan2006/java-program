// File: LibraryDemo.java
import java.time.LocalTime;

class LibraryUser {
    String name;

    LibraryUser(String name) {
        this.name = name;
    }

    // common operation available to all users
    public void enterLibrary() {
        System.out.println(name + " (" + this.getClass().getSimpleName() + ") entered the library at " + LocalTime.now());
    }

    // basic info display (can be overridden for richer info)
    public void displayInfo() {
        System.out.println("User: " + name + " - Type: " + this.getClass().getSimpleName());
    }
}

class Student extends LibraryUser {
    int studentId;
    int borrowedCount = 0;

    Student(String name, int studentId) {
        super(name);
        this.studentId = studentId;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + name + " (ID: " + studentId + ") - Privileges: borrow books, use computers.");
    }

    // Student-specific method (not visible through base reference without downcast)
    public void borrowBook(String title) {
        borrowedCount++;
        System.out.println(name + " borrowed \"" + title + "\". Total borrowed: " + borrowedCount);
    }

    public void accessComputer() {
        System.out.println(name + " is using a library computer.");
    }
}

class Faculty extends LibraryUser {
    int facultyId;

    Faculty(String name, int facultyId) {
        super(name);
        this.facultyId = facultyId;
    }

    @Override
    public void displayInfo() {
        System.out.println("Faculty: " + name + " (ID: " + facultyId + ") - Privileges: reserve books, access research DBs.");
    }

    public void reserveBook(String title) {
        System.out.println(name + " reserved \"" + title + "\".");
    }

    public void accessResearchDatabase() {
        System.out.println(name + " is accessing research databases.");
    }
}

class Guest extends LibraryUser {
    Guest(String name) {
        super(name);
    }

    @Override
    public void displayInfo() {
        System.out.println("Guest: " + name + " - Privileges: browse books only.");
    }

    public void browseBooks() {
        System.out.println(name + " is browsing the book stacks.");
    }
}

public class LibraryDemo {
    public static void main(String[] args) {
        // Upcasting: store different user types in a LibraryUser array
        LibraryUser[] users = new LibraryUser[] {
            new Student("Anita", 101),
            new Faculty("Dr. Rao", 501),
            new Guest("Visitor X")
        };

        // common operations work uniformly
        System.out.println("=== Library Entry Log & Info ===");
        for (LibraryUser u : users) {
            u.enterLibrary();   // logged for all
            u.displayInfo();    // polymorphic display (overrides called)
            System.out.println();
        }

        // Example: we only have a LibraryUser reference but want to do student-only action:
        LibraryUser someUser = new Student("Ravi", 102); // upcast
        someUser.enterLibrary();        // works (common)
        someUser.displayInfo();         // runs Student's overridden displayInfo()

        // To call student-only methods we must downcast (safe-guarded with instanceof)
        if (someUser instanceof Student) {
            Student s = (Student) someUser;
            s.borrowBook("Data Structures");
            s.accessComputer();
        }
    }
}
