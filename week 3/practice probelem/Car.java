public class Car {
    // Instance variables (attributes)
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning;

    // Constructor
    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false; // Initially engine is off
    }

    // Methods
    public void startEngine() {
        isRunning = true;
        System.out.println(brand + " " + model + " engine started.");
    }

    public void stopEngine() {
        isRunning = false;
        System.out.println(brand + " " + model + " engine stopped.");
    }

    public void displayInfo() {
        System.out.println("Car Info: " + brand + " " + model + " (" + year + "), Color: " + color + ", Running: " + isRunning);
    }

    public int getAge(int currentYear) {
        return currentYear - year;
    }

    public static void main(String[] args) {
        // Creating 3 car objects
        Car car1 = new Car("Toyota", "Camry", 2020, "White");
        Car car2 = new Car("Honda", "Civic", 2018, "Black");
        Car car3 = new Car("Tesla", "Model S", 2022, "Red");

        // Demonstrating method calls
        car1.startEngine();
        car1.displayInfo();
        System.out.println("Age of Car1: " + car1.getAge(2025));

        car2.displayInfo();
        car2.startEngine();
        car2.stopEngine();

        car3.displayInfo();
        System.out.println("Age of Car3: " + car3.getAge(2025));

        /*
         * Explanation (Real World Analogy):
         * Each "Car" object has its own brand, model, year, color, and engine state.
         * Just like real cars, each object maintains its own state independent of others.
         */
    }
}
