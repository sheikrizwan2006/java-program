// File: TransportSystem.java
class Vehicle {
    public void dispatch() {
        System.out.println("Generic vehicle dispatched.");
    }
}

class Bus extends Vehicle {
    public void dispatch() {
        System.out.println("Bus dispatched on fixed route. Capacity: 50 passengers.");
    }
}

class Taxi extends Vehicle {
    public void dispatch() {
        System.out.println("Taxi dispatched. Fare calculated by distance.");
    }
}

class Train extends Vehicle {
    public void dispatch() {
        System.out.println("Train dispatched. Multiple cars, scheduled timings.");
    }
}

class Bike extends Vehicle {
    public void dispatch() {
        System.out.println("Bike dispatched. Eco-friendly short trip.");
    }
}

public class TransportSystem {
    public static void main(String[] args) {
        Vehicle[] fleet = {
            new Bus(),
            new Taxi(),
            new Train(),
            new Bike()
        };

        for (Vehicle v : fleet) {
            v.dispatch(); // Dynamic Method Dispatch
        }
    }
}
