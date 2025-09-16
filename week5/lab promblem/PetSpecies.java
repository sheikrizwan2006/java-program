import java.util.Arrays;
import java.util.UUID;

// Immutable PetSpecies class
final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isBlank()) {
            throw new IllegalArgumentException("speciesName cannot be null or empty");
        }
        if (evolutionStages == null || evolutionStages.length == 0) {
            throw new IllegalArgumentException("evolutionStages must have at least one stage");
        }
        for (String stage : evolutionStages) {
            if (stage == null || stage.isBlank()) {
                throw new IllegalArgumentException("evolutionStages cannot contain null or empty elements");
            }
        }
        if (maxLifespan <= 0) {
            throw new IllegalArgumentException("maxLifespan must be positive");
        }
        if (habitat == null || habitat.isBlank()) {
            throw new IllegalArgumentException("habitat cannot be null or empty");
        }
        this.speciesName = speciesName;
        this.evolutionStages = evolutionStages.clone();  // defensive copy
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public String[] getEvolutionStages() {
        return evolutionStages.clone();  // defensive copy
    }

    public int getMaxLifespan() {
        return maxLifespan;
    }

    public String getHabitat() {
        return habitat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetSpecies)) return false;
        PetSpecies other = (PetSpecies) o;
        return maxLifespan == other.maxLifespan &&
                speciesName.equals(other.speciesName) &&
                habitat.equals(other.habitat) &&
                Arrays.equals(evolutionStages, other.evolutionStages);
    }

    @Override
    public int hashCode() {
        int result = speciesName.hashCode();
        result = 31 * result + Arrays.hashCode(evolutionStages);
        result = 31 * result + maxLifespan;
        result = 31 * result + habitat.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PetSpecies{" +
                "speciesName='" + speciesName + '\'' +
                ", evolutionStages=" + Arrays.toString(evolutionStages) +
                ", maxLifespan=" + maxLifespan +
                ", habitat='" + habitat + '\'' +
                '}';
    }
}


// VirtualPet class with access control and encapsulation
public class VirtualPet {
    // Immutable core
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    // Controlled mutable state
    private String petName;
    private int age;
    private int happiness;
    private int health;

    // Package accessible
    protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Egg", "Baby", "Teen", "Adult", "Elder"};

    // Package-private constants
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;

    // Public global constant
    public static final String PET_SYSTEM_VERSION = "2.0";

    // Main constructor
    public VirtualPet(String petId, String petName, PetSpecies species, long birthTimestamp,
                      int age, int happiness, int health) {
        if (petId == null || petId.isBlank()) {
            throw new IllegalArgumentException("petId cannot be null or empty");
        }
        if (petName == null || petName.isBlank()) {
            throw new IllegalArgumentException("petName cannot be null or empty");
        }
        if (species == null) {
            throw new IllegalArgumentException("species cannot be null");
        }
        if (birthTimestamp <= 0) {
            throw new IllegalArgumentException("birthTimestamp must be positive");
        }
        validateStat(age, 0, species.getMaxLifespan());
        validateStat(happiness, 0, MAX_HAPPINESS);
        validateStat(health, 0, MAX_HEALTH);

        this.petId = petId;
        this.petName = petName;
        this.species = species;
        this.birthTimestamp = birthTimestamp;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
    }

    // Default constructor
    public VirtualPet() {
        this(
            generatePetId(),
            "Unnamed Pet",
            getDefaultSpecies(),
            System.currentTimeMillis(),
            0,
            50,
            50
        );
    }

    // Constructor with name only
    public VirtualPet(String petName) {
        this(
            generatePetId(),
            petName,
            getDefaultSpecies(),
            System.currentTimeMillis(),
            0,
            50,
            50
        );
    }

    // Constructor with name and species
    public VirtualPet(String petName, PetSpecies species) {
        this(
            generatePetId(),
            petName,
            species,
            System.currentTimeMillis(),
            0,
            50,
            50
        );
    }

    private static PetSpecies getDefaultSpecies() {
        return new PetSpecies("DefaultSpecies", DEFAULT_EVOLUTION_STAGES, 100, "Unknown");
    }

    private static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    private static void validateStat(int value, int min, int max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException("Value " + value + " out of range: [" + min + "," + max + "]");
        }
    }

    // JavaBean getters and setters

    public String getPetId() {
        return petId;
    }

    public PetSpecies getSpecies() {
        return species;
    }

    public long getBirthTimestamp() {
        return birthTimestamp;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        if (petName == null || petName.isBlank()) {
            throw new IllegalArgumentException("petName cannot be null or empty");
        }
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        validateStat(age, 0, species.getMaxLifespan());
        this.age = age;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        validateStat(happiness, 0, MAX_HAPPINESS);
        this.happiness = happiness;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        validateStat(health, 0, MAX_HEALTH);
        this.health = health;
    }

    // Public methods
    public void feedPet(String foodType) {
        int foodBonus = calculateFoodBonus(foodType);
        modifyHealth(foodBonus);
        updateEvolutionStage();
    }

    public void playWithPet(String gameType) {
        int gameEffect = calculateGameEffect(gameType);
        modifyHappiness(gameEffect);
        updateEvolutionStage();
    }

    // Protected internal calculations
    protected int calculateFoodBonus(String foodType) {
        switch (foodType.toLowerCase()) {
            case "fruit": return 5;
            case "meat": return 10;
            case "candy": return -5;
            default: return 0;
        }
    }

    protected int calculateGameEffect(String gameType) {
        switch (gameType.toLowerCase()) {
            case "fetch": return 10;
            case "hide": return 7;
            case "puzzle": return 12;
            default: return 0;
        }
    }

    // Private internal logic
    private void modifyHappiness(int delta) {
        int newHappiness = happiness + delta;
        if (newHappiness > MAX_HAPPINESS) newHappiness = MAX_HAPPINESS;
        if (newHappiness < 0) newHappiness = 0;
        happiness = newHappiness;
    }

    private void modifyHealth(int delta) {
        int newHealth = health + delta;
        if (newHealth > MAX_HEALTH) newHealth = MAX_HEALTH;
        if (newHealth < 0) newHealth = 0;
        health = newHealth;
    }

    private void updateEvolutionStage() {
        // For example, just increment age by 1 day each evolution update.
        age = Math.min(age + 1, species.getMaxLifespan());
    }

    // Package-private method for debugging
    String getInternalState() {
        return String.format("PetId=%s, Name=%s, Age=%d, Happiness=%d, Health=%d",
                petId, petName, age, happiness, health);
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "VirtualPet{" +
                "petId='" + petId + '\'' +
                ", petName='" + petName + '\'' +
                ", species=" + species +
                ", birthTimestamp=" + birthTimestamp +
                ", age=" + age +
                ", happiness=" + happiness +
                ", health=" + health +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VirtualPet)) return false;
        VirtualPet other = (VirtualPet) o;
        return petId.equals(other.petId) &&
                petName.equals(other.petName) &&
                species.equals(other.species) &&
                birthTimestamp == other.birthTimestamp &&
                age == other.age &&
                happiness == other.happiness &&
                health == other.health;
    }

    @Override
    public int hashCode() {
        int result = petId.hashCode();
        result = 31 * result + petName.hashCode();
        result = 31 * result + species.hashCode();
        result = 31 * result + Long.hashCode(birthTimestamp);
        result = 31 * result + age;
        result = 31 * result + happiness;
        result = 31 * result + health;
        return result;
    }
}


// DragonPet class (no inheritance, uses composition)
class DragonPet {
    private final VirtualPet virtualPet;
    private final String dragonType;
    private final String breathWeapon;

    public DragonPet(String petName, PetSpecies species, String dragonType, String breathWeapon) {
        if (dragonType == null || dragonType.isBlank()) {
            throw new IllegalArgumentException("dragonType cannot be null or empty");
        }
        if (breathWeapon == null || breathWeapon.isBlank()) {
            throw new IllegalArgumentException("breathWeapon cannot be null or empty");
        }
        this.virtualPet = new VirtualPet(petName, species);
        this.dragonType = dragonType;
        this.breathWeapon = breathWeapon;
    }

    public String getDragonType() {
        return dragonType;
    }

    public String getBreathWeapon() {
        return breathWeapon;
    }

    // Delegate VirtualPet getters/setters and actions
    public String getPetName() {
        return virtualPet.getPetName();
    }

    public void setPetName(String petName) {
        virtualPet.setPetName(petName);
    }

    public void feedPet(String foodType) {
        virtualPet.feedPet(foodType);
    }

    public void playWithPet(String gameType) {
        virtualPet.playWithPet(gameType);
    }

    @Override
    public String toString() {
        return "DragonPet{" +
                "virtualPet=" + virtualPet +
                ", dragonType='" + dragonType + '\'' +
                ", breathWeapon='" + breathWeapon + '\'' +
                '}';
    }
}


// RobotPet class (no inheritance, uses composition)
class RobotPet {
    private final VirtualPet virtualPet;
    private boolean needsCharging;
    private int batteryLevel; // 0-100

    public RobotPet(String petName, PetSpecies species, boolean needsCharging, int batteryLevel) {
        this.virtualPet = new VirtualPet(petName, species);
        this.needsCharging = needsCharging;
        setBatteryLevel(batteryLevel);
    }

    public boolean isNeedsCharging() {
        return needsCharging;
    }

    public void setNeedsCharging(boolean needsCharging) {
        this.needsCharging = needsCharging;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("batteryLevel must be between 0 and 100");
        }
        this.batteryLevel = batteryLevel;
    }

    // Delegate VirtualPet getters/setters and actions
    public String getPetName() {
        return virtualPet.getPetName();
    }

    public void setPetName(String petName) {
        virtualPet.setPetName(petName);
    }

    public void feedPet(String foodType) {
        virtualPet.feedPet(foodType);
    }

    public void playWithPet(String gameType) {
        virtualPet.playWithPet(gameType);
    }

    @Override
    public String toString() {
        return "RobotPet{" +
                "virtualPet=" + virtualPet +
                ", needsCharging=" + needsCharging +
                ", batteryLevel=" + batteryLevel +
                '}';
    }
}