class Animal {}

class Dog extends Animal {}

class Cat extends Animal {}

public class TestInstanceof {
    public static void main(String[] args) {
        Animal[] animals = { new Dog(), new Cat(), new Dog(), new Animal() };

        int dogCount = 0;
        int catCount = 0;

        // Count number of Dog and Cat instances using instanceof
        for (Animal a : animals) {
            if (a instanceof Dog) {
                dogCount++;
            } else if (a instanceof Cat) {
                catCount++;
            }
        }

        // Print results
        System.out.println("Number of Dogs: " + dogCount);
        System.out.println("Number of Cats: " + catCount);
    }
}
