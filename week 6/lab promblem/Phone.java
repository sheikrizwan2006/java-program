class Phone {
    protected String brand;
    protected String model;

    public Phone() {
        this.brand = "Unknown";
        this.model = "Unknown";
        System.out.println("Phone default constructor called");
    }

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone parameterized constructor called");
    }
}

class SmartPhone extends Phone {
    protected String operatingSystem;

    public SmartPhone() {
        super();
        this.operatingSystem = "Unknown OS";
        System.out.println("SmartPhone default constructor called");
    }

    public SmartPhone(String brand, String model, String operatingSystem) {
        super(brand, model);
        this.operatingSystem = operatingSystem;
        System.out.println("SmartPhone parameterized constructor called");
    }
}

public class LabProblem2 {
    public static void main(String[] args) {
        System.out.println("Creating SmartPhone with default constructor:");
        SmartPhone sp1 = new SmartPhone();

        System.out.println("\nCreating SmartPhone with parameterized constructor:");
        SmartPhone sp2 = new SmartPhone("Apple", "iPhone 13", "iOS");
    }
}
