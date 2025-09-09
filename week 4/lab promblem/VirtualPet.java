import java.util.UUID;
import java.util.Random;

public class VirtualPet {

    // Fields
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private String evolutionStage;

    private boolean isGhost = false;

    // Static fields
    public static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    private static int totalPetsCreated = 0;

    private static Random rand = new Random();

    // Generate unique pet IDs
    public static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    // Main constructor (full)
    public VirtualPet(String petName, String species, int age, int happiness, int health, String evolutionStage) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.evolutionStage = evolutionStage;
        totalPetsCreated++;
    }

    // Constructor with name and species (starts as Child)
    public VirtualPet(String petName, String species) {
        this(petName, species, 5, 50, 50, EVOLUTION_STAGES[2]); // Age 5, average happiness & health
    }

    // Constructor with name only (starts as Baby)
    public VirtualPet(String petName) {
        // Random species from a few options
        this(petName, randomSpecies(), 1, 50, 50, EVOLUTION_STAGES[1]);
    }

    // Default constructor (mysterious egg)
    public VirtualPet() {
        this("???", randomSpecies(), 0, 0, 100, EVOLUTION_STAGES[0]);
    }

    // Helper for random species
    private static String randomSpecies() {
        String[] speciesOptions = {"Dragon", "Unicorn", "Phoenix", "Griffin", "Turtle"};
        return speciesOptions[rand.nextInt(speciesOptions.length)];
    }

    // Evolve pet based on age and care
    public void evolvePet() {
        if (isGhost) return; // Ghosts can't evolve

        int stageIndex = getStageIndex();
        if (stageIndex == -1) return;

        // Evolve to next stage if age >= threshold and health/happiness good enough
        // Let's define thresholds for age to evolve
        int[] ageThresholds = {0, 1, 5, 10, 20, 40}; // ages to evolve into each stage
        if (stageIndex < EVOLUTION_STAGES.length - 1) {
            if (age >= ageThresholds[stageIndex + 1] && health > 30 && happiness > 30) {
                evolutionStage = EVOLUTION_STAGES[stageIndex + 1];
                System.out.println(petName + " has evolved to " + evolutionStage + " stage!");
            }
        }
    }

    // Feed pet: increase health and happiness
    public void feedPet() {
        if (isGhost) {
            System.out.println(petName + " is a ghost and cannot be fed.");
            return;
        }
        health = Math.min(health + 10, 100);
        happiness = Math.min(happiness + 5, 100);
        System.out.println(petName + " was fed.");
    }

    // Play with pet: increase happiness, decrease health a bit
    public void playWithPet() {
        if (isGhost) {
            System.out.println(petName + " is a ghost and loves haunting!");
            return;
        }
        happiness = Math.min(happiness + 15, 100);
        health = Math.max(health - 5, 0);
        System.out.println(petName + " played and is happier but a bit tired.");
    }

    // Heal pet: increase health
    public void healPet() {
        if (isGhost) {
            System.out.println(petName + " is a ghost and cannot be healed.");
            return;
        }
        health = Math.min(health + 20, 100);
        System.out.println(petName + " was healed.");
    }

    // Simulate a day: age pet, randomly affect stats, possibly kill pet
    public void simulateDay() {
        if (isGhost) {
            // Ghosts haunt randomly - nothing else changes
            System.out.println(petName + " the ghost haunts the daycare...");
            return;
        }

        age++;

        // Random event: happiness -5 to +5
        happiness += rand.nextInt(11) - 5;
        happiness = clampStat(happiness);

        // Random event: health -10 to +5
        health += rand.nextInt(16) - 10;
        health = clampStat(health);

        // If health reaches 0, pet dies
        if (health <= 0) {
            health = 0;
            becomeGhost();
        }

        evolvePet();
    }

    private int clampStat(int val) {
        if (val > 100) return 100;
        if (val < 0) return 0;
        return val;
    }

    private void becomeGhost() {
        isGhost = true;
        evolutionStage = "Ghost";
        System.out.println(petName + " has died and become a ghost! It can no longer evolve.");
    }

    private int getStageIndex() {
        for (int i = 0; i < EVOLUTION_STAGES.length; i++) {
            if (EVOLUTION_STAGES[i].equals(evolutionStage)) return i;
        }
        if (evolutionStage.equals("Ghost")) return -1;
        return -1;
    }

    public String getPetStatus() {
        return String.format("%s [%s] - Age: %d, Health: %d, Happiness: %d, Stage: %s",
                petName, species, age, health, happiness, evolutionStage);
    }

    public String getPetId() {
        return petId;
    }

    public boolean isGhost() {
        return isGhost;
    }

    // Main method to demonstrate daycare simulation
    public static void main(String[] args) {
        VirtualPet[] daycare = new VirtualPet[3];

        // Create pets using different constructors
        daycare[0] = new VirtualPet(); // Mysterious egg
        daycare[1] = new VirtualPet("Fluffy"); // Name only
        daycare[2] = new VirtualPet("Sparky", "Dragon"); // Name and species

        System.out.println("Welcome to the Virtual Pet Daycare!");
        for (int day = 1; day <= 15; day++) {
            System.out.println("\nDay " + day + " at the daycare:");

            for (VirtualPet pet : daycare) {
                pet.simulateDay();

                // Randomly feed/play/heal pets
                int action = rand.nextInt(3);
                switch (action) {
                    case 0 -> pet.feedPet();
                    case 1 -> pet.playWithPet();
                    case 2 -> pet.healPet();
                }

                System.out.println(pet.getPetStatus());
            }
        }

        System.out.println("\nTotal pets created: " + totalPetsCreated);
    }
}