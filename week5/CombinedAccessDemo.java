// Note: Normally these would be in separate files and packages,
// but combined here for demonstration.

// ------------------------------
// Simulate com.company.security.AccessModifierDemo
class AccessModifierDemo {
    public int publicField = 1;
    protected int protectedField = 2;
    int defaultField = 3;    // package-private
    private int privateField = 4;

    public AccessModifierDemo() {}

    public void publicMethod() {
        System.out.println("Public method");
    }

    protected void protectedMethod() {
        System.out.println("Protected method");
    }

    void defaultMethod() {
        System.out.println("Default method");
    }

    private void privateMethod() {
        System.out.println("Private method");
    }

    protected void methodToOverride() {
        System.out.println("Parent protected method");
    }
}

// ------------------------------
// Simulate com.company.main.PackageTestMain
class PackageTestMain {
    public static void main(String[] args) {
        System.out.println("=== PackageTestMain ===");

        AccessModifierDemo demo = new AccessModifierDemo();

        System.out.println("Accessing fields and methods (no inheritance, different package):");

        System.out.println("publicField: " + demo.publicField);  // Works
        // System.out.println("protectedField: " + demo.protectedField); // ERROR if packages different
        // System.out.println("defaultField: " + demo.defaultField);     // ERROR if packages different
        // System.out.println("privateField: " + demo.privateField);     // ERROR always

        demo.publicMethod();         // Works
        // demo.protectedMethod();   // ERROR if packages different
        // demo.defaultMethod();     // ERROR if packages different
        // demo.privateMethod();     // ERROR always

        System.out.println("\nSummary:");
        System.out.println("Only public members accessible across packages without inheritance.");
        System.out.println("Protected, default, and private members are NOT accessible.");
        System.out.println();
    }
}

// ------------------------------
// Simulate com.company.extended.ExtendedDemo
class ExtendedDemo extends AccessModifierDemo {

    public ExtendedDemo() {
        super();
    }

    public void testInheritedAccess() {
        System.out.println("Testing inherited field access:");

        System.out.println("publicField: " + publicField);       // Works
        System.out.println("protectedField: " + protectedField); // Works
        // System.out.println("defaultField: " + defaultField);   // ERROR if packages different
        // System.out.println("privateField: " + privateField);   // ERROR not inherited

        System.out.println("\nTesting inherited method access:");

        publicMethod();           // Works
        protectedMethod();        // Works
        // defaultMethod();       // ERROR if packages different
        // privateMethod();       // ERROR not inherited
    }

    @Override
    protected void methodToOverride() {
        System.out.println("Overridden protected method in child class");
    }

    public static void main(String[] args) {
        System.out.println("=== ExtendedDemo ===");

        ExtendedDemo child = new ExtendedDemo();
        System.out.println("Child testing inherited access:");
        child.testInheritedAccess();

        System.out.println("\nTesting access from parent class reference:");

        AccessModifierDemo parent = new AccessModifierDemo();

        System.out.println("parent.publicField: " + parent.publicField);  // Works
        // System.out.println("parent.protectedField: " + parent.protectedField); // ERROR if packages different
        // System.out.println("parent.defaultField: " + parent.defaultField);     // ERROR
        // System.out.println("parent.privateField: " + parent.privateField);     // ERROR

        System.out.println("Calling methods on parent:");

        parent.publicMethod();      // Works
        // parent.protectedMethod();  // ERROR if packages different

        System.out.println("\nCalling overridden method via child:");
        child.methodToOverride();   // Calls overridden child method
        System.out.println();
    }
}

// ------------------------------
// Main entry point for combined demo

public class CombinedAccessDemo {
    public static void main(String[] args) {
        PackageTestMain.main(args);
        ExtendedDemo.main(args);
    }
}