class Light {
    protected String type;
    protected int wattage;

    public Light() {
        this("Unknown", 0);
        System.out.println("Light default constructor called");
    }

    public Light(String type) {
        this(type, 0);
        System.out.println("Light constructor with type called");
    }

    public Light(String type, int wattage) {
        this.type = type;
        this.wattage = wattage;
        System.out.println("Light parameterized constructor called");
    }
}

class LED extends Light {
    private String color;

    public LED() {
        this("Unknown", 0, "White");
        System.out.println("LED default constructor called");
    }

    public LED(String type) {
        this(type, 0, "White");
        System.out.println("LED constructor with type called");
    }

    public LED(String type, int wattage, String color) {
        super(type, wattage);
        this.color = color;
        System.out.println("LED parameterized constructor called");
    }
}

public class HwProblem1 {
    public static void main(String[] args) {
        System.out.println("Creating LED with default constructor:");
        LED led1 = new LED();

        System.out.println("\nCreating LED with constructor (type):");
        LED led2 = new LED("Halogen");

        System.out.println("\nCreating LED with full parameters:");
        LED led3 = new LED("Incandescent", 60, "Yellow");
    }
}
