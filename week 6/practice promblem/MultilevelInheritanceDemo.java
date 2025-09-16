public class MultilevelInheritanceDemo {

    // Base class Animal
    public static class Animal {
        // Protected fields
        protected String species;
        protected String habitat;
        protected int lifespan;
        protected boolean isWildlife;

        // Constructor
        public Animal(String species, String habitat, int lifespan, boolean isWildlife) {
            this.species = species;
            this.habitat = habitat;
            this.lifespan = lifespan;
            this.isWildlife = isWildlife;
            System.out.println("Animal constructor: Creating " + species);
        }

        // Methods
        public void eat() {
            System.out.println("Animal is eating");
        }

        public void sleep() {
            System.out.println("Animal is sleeping");
        }

        public void move() {
            System.out.println("Animal is moving");
        }

        public String getAnimalInfo() {
            return String.format(
                "Species: %s, Habitat: %s, Lifespan: %d years, Is Wildlife: %b",
                species, habitat, lifespan, isWildlife
            );
        }
    }

    // Intermediate class Mammal extends Animal
    public static class Mammal extends Animal {
        // Mammal-specific fields
        protected String furColor;
        protected boolean hasWarmBlood;
        protected int gestationPeriod; // days

        // Constructor
        public Mammal(String species, String habitat, int lifespan, boolean isWildlife,
                      String furColor, int gestationPeriod) {
            super(species, habitat, lifespan, isWildlife);
            this.furColor = furColor;
            this.hasWarmBlood = true;  // always true for mammals
            this.gestationPeriod = gestationPeriod;
            System.out.println("Mammal constructor: Adding mammal traits");
        }

        // Override move
        @Override
        public void move() {
            super.move();
            System.out.println("Mammal is walking/running");
        }

        // Mammal-specific methods
        public void nurse() {
            System.out.println("Mammal is nursing offspring");
        }

        public void regulateTemperature() {
            System.out.println("Maintaining body temperature");
        }
    }

    // Specific class Dog extends Mammal
    public static class Dog extends Mammal {
        // Dog-specific fields
        private String breed;
        private boolean isDomesticated;
        private int loyaltyLevel;  // 1-10 scale
        private String favoriteActivity;

        // Constructor 1: Basic dog with minimal parameters
        public Dog() {
            // Calls super with default values for animal/mammal
            super("Dog", "Domestic", 13, false, "Various", 60);
            this.breed = "Mixed";
            this.isDomesticated = true;
            this.loyaltyLevel = 7;
            this.favoriteActivity = "Playing fetch";
            System.out.println("Dog constructor: Creating default dog");
        }

        // Constructor 2: Detailed dog with all parameters
        public Dog(String species, String habitat, int lifespan, boolean isWildlife,
                   String furColor, int gestationPeriod,
                   String breed, boolean isDomesticated, int loyaltyLevel, String favoriteActivity) {
            super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
            this.breed = breed;
            this.isDomesticated = isDomesticated;
            this.loyaltyLevel = loyaltyLevel;
            this.favoriteActivity = favoriteActivity;
            System.out.println("Dog constructor: Creating " + breed + " dog");
        }

        // Constructor 3: Copy constructor
        public Dog(Dog other) {
            this(other.species, other.habitat, other.lifespan, other.isWildlife,
                 other.furColor, other.gestationPeriod,
                 other.breed, other.isDomesticated, other.loyaltyLevel, other.favoriteActivity);
            System.out.println("Dog constructor: Copy constructor called for " + other.breed);
        }

        // Override eat
        @Override
        public void eat() {
            super.eat();
            System.out.println("Wagging tail while eating");
        }

        // Override move
        @Override
        public void move() {
            System.out.println("Dog is running and playing");
        }

        // Override sleep
        @Override
        public void sleep() {
            System.out.println("Dog is sleeping in doghouse");
        }

        // Dog-specific methods
        public void bark() {
            System.out.println("Woof! Woof!");
        }

        public void fetch() {
            System.out.println("Dog is fetching the ball");
        }

        public void showLoyalty() {
            System.out.println("Loyalty level is " + loyaltyLevel + " out of 10");
        }

        // Demonstrate calling methods up the chain
        public void demonstrateInheritance() {
            System.out.println("--- Demonstrating inheritance chain ---");
            eat();          // Dog's overridden eat
            super.eat();    // Mammal's eat (which calls Animal's eat)
            sleep();        // Dog's overridden sleep
            move();         // Dog's overridden move
            super.move();   // Mammal's move (calls Animal.move + print)
            nurse();        // Mammal method
            regulateTemperature(); // Mammal method
            System.out.println(getAnimalInfo()); // Animal method
            bark();         // Dog-specific
            fetch();        // Dog-specific
            showLoyalty();  // Dog-specific
            System.out.println("--------------------------------------");
        }
    }

    // Main method to test all features
    public static void main(String[] args) {
        System.out.println("=== Testing constructor chaining ===");
        Dog dog1 = new Dog();
        System.out.println();

        Dog dog2 = new Dog("Dog", "Home", 12, false, "Brown", 58, "Beagle", true, 9, "Hunting");
        System.out.println();

        Dog dog3 = new Dog(dog2);
        System.out.println();

        System.out.println("=== Testing method overriding and super calls ===");
        dog2.eat();
        dog2.move();
        dog2.sleep();
        System.out.println();

        System.out.println("=== Testing access to inherited members ===");
        System.out.println("Species: " + dog2.species);
        System.out.println("Habitat: " + dog2.habitat);
        System.out.println("Fur Color: " + dog2.furColor);
        System.out.println("Breed: " + dog2.breed);
        System.out.println();

        System.out.println("=== Demonstrating full inheritance chain ===");
        dog2.demonstrateInheritance();
        System.out.println();

        System.out.println("=== Testing instanceof operator ===");
        System.out.println("dog2 instanceof Dog: " + (dog2 instanceof Dog));
        System.out.println("dog2 instanceof Mammal: " + (dog2 instanceof Mammal));
        System.out.println("dog2 instanceof Animal: " + (dog2 instanceof Animal));
        System.out.println();

        System.out.println("=== Testing multiple objects with different constructors ===");
        Dog dog4 = new Dog();
        Dog dog5 = new Dog("Dog", "Forest", 15, true, "Black", 63, "German Shepherd", true, 10, "Guarding");
        dog4.demonstrateInheritance();
        dog5.demonstrateInheritance();
    }
}
