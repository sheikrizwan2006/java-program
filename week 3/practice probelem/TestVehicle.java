// Base Class
class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;

    public Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }

    public void startVehicle() { System.out.println(make + " " + model + " started."); }
    public void stopVehicle() { System.out.println(make + " " + model + " stopped."); }
    public void refuel(double amount) {
        fuelLevel += amount;
        System.out.println(make + " " + model + " refueled by " + amount + " liters. Fuel Level: " + fuelLevel);
    }
    public void displayVehicleInfo() {
        System.out.println(year + " " + make + " " + model + " | Fuel: " + fuelLevel);
    }
}

// Derived Classes
class Car extends Vehicle {
    public Car(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}

class Truck extends Vehicle {
    public Truck(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}

public class TestVehicle {
    public static void main(String[] args) {
        Vehicle v1 = new Car("Toyota", "Corolla", 2020, 50);
        Vehicle v2 = new Truck("Ford", "F-150", 2019, 70);
        Vehicle v3 = new Motorcycle("Yamaha", "R15", 2022, 20);

        // Array of vehicles (Polymorphism)
        Vehicle[] vehicles = {v1, v2, v3};
        for (Vehicle v : vehicles) {
            v.startVehicle();
            v.displayVehicleInfo();
            v.refuel(10);
            v.stopVehicle();
        }

        /*
         * Reusability: The Vehicle class defines common code once,
         * and Car, Truck, Motorcycle reuse it without rewriting.
         *
         * Extensibility: We can easily add new types (Bus, Van, etc.) by extending Vehicle.
         *
         * Benefits over separate classes: Less duplicate code, easier maintenance,
         * and polymorphic behavior (treat all as Vehicle objects).
         */
    }
}
