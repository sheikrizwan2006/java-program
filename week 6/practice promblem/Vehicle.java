public class Vehicle {
    // Protected fields for inheritance
    protected String brand;
    protected String model;
    protected int year;
    protected String engineType;

    // Private fields with getter/setter access
    private String registrationNumber;
    private boolean isRunning;

    // Default constructor
    public Vehicle() {
        this.brand = "Unknown Brand";
        this.model = "Unknown Model";
        this.year = 0;
        this.engineType = "Unknown Engine";
        this.registrationNumber = generateRegistrationNumber();
        this.isRunning = false;
        System.out.println("Vehicle default constructor called");
    }

    // Parameterized constructor
    public Vehicle(String brand, String model, int year, String engineType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.registrationNumber = generateRegistrationNumber();
        this.isRunning = false;
        System.out.println("Vehicle parameterized constructor called");
    }

    // Private helper method to generate random registration number
    private String generateRegistrationNumber() {
        return "REG" + (int)(Math.random() * 10000);
    }

    // Methods for vehicle operations
    public void start() {
        isRunning = true;
        System.out.println("Vehicle started");
    }

    public void stop() {
        isRunning = false;
        System.out.println("Vehicle stopped");
    }

    public String getVehicleInfo() {
        return String.format(
            "Brand: %s, Model: %s, Year: %d, Engine Type: %s, Registration Number: %s, Is Running: %b",
            brand, model, year, engineType, registrationNumber, isRunning
        );
    }

    public void displaySpecs() {
        System.out.println("Vehicle Specs:");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Engine Type: " + engineType);
    }

    // Getter and setter for registrationNumber
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    // Getter for isRunning (no setter)
    public boolean isRunning() {
        return isRunning;
    }
}

public class Car extends Vehicle {
    // Car-specific fields
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;

    // Default constructor
    public Car() {
        super(); // Calls Vehicle default constructor
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
        System.out.println("Car default constructor called");
    }

    // Parameterized constructor
    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType); // Calls Vehicle parameterized constructor
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    // Override start method
    @Override
    public void start() {
        super.start(); // Call Vehicle start
        System.out.println("Car-specific startup sequence: checking doors and seatbelts...");
    }

    // Override displaySpecs method
    @Override
    public void displaySpecs() {
        super.displaySpecs();
        System.out.println("Car Specs:");
        System.out.println("Number of Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Transmission Type: " + transmissionType);
    }

    // Car-specific methods
    public void openTrunk() {
        System.out.println("Trunk opened");
    }

    public void playRadio() {
        System.out.println("Radio playing music");
    }

    // Main method to test everything
    public static void main(String[] args) {
        System.out.println("=== Testing default constructor ===");
        Car defaultCar = new Car(); // Test constructor chaining
        System.out.println();

        System.out.println("=== Testing parameterized constructor ===");
        Car paramCar = new Car("Toyota", "Corolla", 2023, "Hybrid", 4, "Gasoline", "Automatic");
        System.out.println();

        System.out.println("=== Testing inherited fields and methods ===");
        System.out.println("Brand of paramCar: " + paramCar.brand); // protected field access
        System.out.println("Model of paramCar: " + paramCar.model);
        System.out.println(paramCar.getVehicleInfo()); // inherited method
        System.out.println();

        System.out.println("=== Testing overridden methods ===");
        paramCar.start(); // overridden start calls super.start
        paramCar.displaySpecs(); // overridden displaySpecs
        paramCar.stop();
        System.out.println();

        System.out.println("=== Testing car-specific methods ===");
        paramCar.openTrunk();
        paramCar.playRadio();
        System.out.println();

        System.out.println("=== Demonstrating polymorphism ===");
        Vehicle vehicleRef = new Car("Honda", "Civic", 2022, "Gasoline", 4, "Petrol", "Manual");
        vehicleRef.start(); // Should call Car's start due to polymorphism
        vehicleRef.displaySpecs();

        // vehicleRef.openTrunk(); // Compile error: method not in Vehicle type
        // To call openTrunk, you need to cast:
        if (vehicleRef instanceof Car) {
            ((Car) vehicleRef).openTrunk();
        }
    }
}
