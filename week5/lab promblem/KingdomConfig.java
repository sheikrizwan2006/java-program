import java.util.*;

// a. Immutable KingdomConfig class
final class KingdomConfig {
    private final String kingdomName;
    private final int foundingYear;
    private final String[] allowedStructureTypes;
    private final Map<String, Integer> resourceLimits;

    public KingdomConfig(String kingdomName, int foundingYear, String[] allowedStructureTypes, Map<String, Integer> resourceLimits) {
        if (kingdomName == null || kingdomName.isBlank()) {
            throw new IllegalArgumentException("kingdomName cannot be null or empty");
        }
        if (foundingYear <= 0) {
            throw new IllegalArgumentException("foundingYear must be positive");
        }
        if (allowedStructureTypes == null || allowedStructureTypes.length == 0) {
            throw new IllegalArgumentException("allowedStructureTypes must have at least one type");
        }
        for (String type : allowedStructureTypes) {
            if (type == null || type.isBlank()) {
                throw new IllegalArgumentException("allowedStructureTypes cannot contain null or empty types");
            }
        }
        if (resourceLimits == null) {
            throw new IllegalArgumentException("resourceLimits cannot be null");
        }
        for (Map.Entry<String, Integer> entry : resourceLimits.entrySet()) {
            if (entry.getKey() == null || entry.getKey().isBlank() || entry.getValue() == null || entry.getValue() < 0) {
                throw new IllegalArgumentException("resourceLimits must have non-null, non-empty keys and non-negative values");
            }
        }
        this.kingdomName = kingdomName;
        this.foundingYear = foundingYear;
        this.allowedStructureTypes = allowedStructureTypes.clone(); // defensive copy
        this.resourceLimits = Collections.unmodifiableMap(new HashMap<>(resourceLimits)); // defensive copy + immutable
    }

    public String getKingdomName() {
        return kingdomName;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public String[] getAllowedStructureTypes() {
        return allowedStructureTypes.clone(); // defensive copy
    }

    public Map<String, Integer> getResourceLimits() {
        return new HashMap<>(resourceLimits); // defensive copy
    }

    // Factory methods
    public static KingdomConfig createDefaultKingdom() {
        return new KingdomConfig("Default Kingdom", 1000,
                new String[]{"WizardTower", "EnchantedCastle", "MysticLibrary", "DragonLair"},
                Map.of("Gold", 10000, "Mana", 5000));
    }

    public static KingdomConfig createFromTemplate(String type) {
        switch (type.toLowerCase()) {
            case "royal":
                return new KingdomConfig("Royal Kingdom", 1500,
                        new String[]{"EnchantedCastle", "MysticLibrary"},
                        Map.of("Gold", 20000, "Mana", 8000));
            case "mystic":
                return new KingdomConfig("Mystic Kingdom", 1200,
                        new String[]{"WizardTower", "MysticLibrary"},
                        Map.of("Gold", 8000, "Mana", 12000));
            case "dragon":
                return new KingdomConfig("Dragon Kingdom", 1800,
                        new String[]{"DragonLair", "EnchantedCastle"},
                        Map.of("Gold", 15000, "Mana", 7000));
            default:
                return createDefaultKingdom();
        }
    }

    @Override
    public String toString() {
        return "KingdomConfig{" +
                "kingdomName='" + kingdomName + '\'' +
                ", foundingYear=" + foundingYear +
                ", allowedStructureTypes=" + Arrays.toString(allowedStructureTypes) +
                ", resourceLimits=" + resourceLimits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KingdomConfig)) return false;
        KingdomConfig that = (KingdomConfig) o;
        return foundingYear == that.foundingYear &&
                kingdomName.equals(that.kingdomName) &&
                Arrays.equals(allowedStructureTypes, that.allowedStructureTypes) &&
                resourceLimits.equals(that.resourceLimits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(kingdomName, foundingYear, resourceLimits);
        result = 31 * result + Arrays.hashCode(allowedStructureTypes);
        return result;
    }
}

// b. Base MagicalStructure class (no inheritance hierarchy)
class MagicalStructure {
    // Immutable identity
    private final String structureId;
    private final long constructionTimestamp;

    // Immutable properties
    private final String structureName;
    private final String location;

    // Controlled mutable state
    private int magicPower;
    private boolean isActive;
    private String currentMaintainer;

    // Package constants
    static final int MIN_MAGIC_POWER = 0;
    static final int MAX_MAGIC_POWER = 1000;

    // Global constant
    public static final String MAGIC_SYSTEM_VERSION = "3.0";

    // Main constructor
    public MagicalStructure(String structureName, String location, int magicPower, boolean isActive) {
        if (structureName == null || structureName.isBlank()) {
            throw new IllegalArgumentException("structureName cannot be null or empty");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("location cannot be null or empty");
        }
        validateMagicPower(magicPower);

        this.structureId = UUID.randomUUID().toString();
        this.constructionTimestamp = System.currentTimeMillis();
        this.structureName = structureName;
        this.location = location;
        this.magicPower = magicPower;
        this.isActive = isActive;
        this.currentMaintainer = "";
    }

    // Constructor chaining
    public MagicalStructure(String structureName, String location, int magicPower) {
        this(structureName, location, magicPower, true);
    }

    public MagicalStructure(String structureName, String location) {
        this(structureName, location, MIN_MAGIC_POWER, true);
    }

    private static void validateMagicPower(int power) {
        if (power < MIN_MAGIC_POWER || power > MAX_MAGIC_POWER) {
            throw new IllegalArgumentException("magicPower must be between " + MIN_MAGIC_POWER + " and " + MAX_MAGIC_POWER);
        }
    }

    // JavaBean getters and setters
    public String getStructureId() {
        return structureId;
    }

    public long getConstructionTimestamp() {
        return constructionTimestamp;
    }

    public String getStructureName() {
        return structureName;
    }

    public String getLocation() {
        return location;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        validateMagicPower(magicPower);
        this.magicPower = magicPower;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public String getCurrentMaintainer() {
        return currentMaintainer;
    }

    public void setCurrentMaintainer(String currentMaintainer) {
        if (currentMaintainer == null) currentMaintainer = "";
        this.currentMaintainer = currentMaintainer;
    }

    @Override
    public String toString() {
        return "MagicalStructure{" +
                "structureId='" + structureId + '\'' +
                ", constructionTimestamp=" + constructionTimestamp +
                ", structureName='" + structureName + '\'' +
                ", location='" + location + '\'' +
                ", magicPower=" + magicPower +
                ", isActive=" + isActive +
                ", currentMaintainer='" + currentMaintainer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MagicalStructure)) return false;
        MagicalStructure that = (MagicalStructure) o;
        return constructionTimestamp == that.constructionTimestamp &&
                magicPower == that.magicPower &&
                isActive == that.isActive &&
                structureId.equals(that.structureId) &&
                structureName.equals(that.structureName) &&
                location.equals(that.location) &&
                currentMaintainer.equals(that.currentMaintainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureId, constructionTimestamp, structureName, location, magicPower, isActive, currentMaintainer);
    }
}

// d. Four specialized structure classes (no inheritance, composition of MagicalStructure)

// WizardTower
class WizardTower {
    private final MagicalStructure base;
    private final int maxSpellCapacity;
    private final List<String> knownSpells;
    private String currentWizard;

    // Empty tower constructor
    public WizardTower(String name, String location) {
        this(name, location, 10, List.of("Basic Spell"), "");
    }

    // Basic spells constructor
    public WizardTower(String name, String location, int maxSpellCapacity, List<String> knownSpells, String currentWizard) {
        if (maxSpellCapacity < 0) throw new IllegalArgumentException("maxSpellCapacity cannot be negative");
        if (knownSpells == null) throw new IllegalArgumentException("knownSpells cannot be null");
        this.base = new MagicalStructure(name, location);
        this.maxSpellCapacity = maxSpellCapacity;
        this.knownSpells = new ArrayList<>(knownSpells);
        this.currentWizard = currentWizard == null ? "" : currentWizard;
    }

    public int getMaxSpellCapacity() {
        return maxSpellCapacity;
    }

    public List<String> getKnownSpells() {
        return new ArrayList<>(knownSpells);
    }

    public String getCurrentWizard() {
        return currentWizard;
    }

    public void setCurrentWizard(String currentWizard) {
        this.currentWizard = currentWizard == null ? "" : currentWizard;
    }

    // Delegate MagicalStructure getters/setters
    public String getStructureName() {
        return base.getStructureName();
    }

    public String getLocation() {
        return base.getLocation();
    }

    public int getMagicPower() {
        return base.getMagicPower();
    }

    public void setMagicPower(int power) {
        base.setMagicPower(power);
    }

    public boolean isActive() {
        return base.isActive();
    }

    public void setActive(boolean active) {
        base.setActive(active);
    }

    public String getCurrentMaintainer() {
        return base.getCurrentMaintainer();
    }

    public void setCurrentMaintainer(String maintainer) {
        base.setCurrentMaintainer(maintainer);
    }

    @Override
    public String toString() {
        return "WizardTower{" +
                "base=" + base +
                ", maxSpellCapacity=" + maxSpellCapacity +
                ", knownSpells=" + knownSpells +
                ", currentWizard='" + currentWizard + '\'' +
                '}';
    }
}

// EnchantedCastle
class EnchantedCastle {
    private final MagicalStructure base;
    private final String castleType;
    private int defenseRating;
    private boolean hasDrawbridge;

    // Simple fort constructor
    public EnchantedCastle(String name, String location) {
        this(name, location, "Simple Fort", 50, false);
    }

    // Royal castle constructor
    public EnchantedCastle(String name, String location, String castleType, int defenseRating, boolean hasDrawbridge) {
        if (castleType == null || castleType.isBlank()) throw new IllegalArgumentException("castleType cannot be null or empty");
        if (defenseRating < 0) throw new IllegalArgumentException("defenseRating cannot be negative");
        this.base = new MagicalStructure(name, location);
        this.castleType = castleType;
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    public String getCastleType() {
        return castleType;
    }

    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int defenseRating) {
        if (defenseRating < 0) throw new IllegalArgumentException("defenseRating cannot be negative");
        this.defenseRating = defenseRating;
    }

    public boolean isHasDrawbridge() {
        return hasDrawbridge;
    }

    public void setHasDrawbridge(boolean hasDrawbridge) {
        this.hasDrawbridge = hasDrawbridge;
    }

    // Delegate MagicalStructure getters/setters
    public String getStructureName() {
        return base.getStructureName();
    }

    public String getLocation() {
        return base.getLocation();
    }

    public int getMagicPower() {
        return base.getMagicPower();
    }

    public void setMagicPower(int power) {
        base.setMagicPower(power);
    }

    public boolean isActive() {
        return base.isActive();
    }

    public void setActive(boolean active) {
        base.setActive(active);
    }

    public String getCurrentMaintainer() {
        return base.getCurrentMaintainer();
    }

    public void setCurrentMaintainer(String maintainer) {
        base.setCurrentMaintainer(maintainer);
    }

    @Override
    public String toString() {
        return "EnchantedCastle{" +
                "base=" + base +
                ", castleType='" + castleType + '\'' +
                ", defenseRating=" + defenseRating +
                ", hasDrawbridge=" + hasDrawbridge +
                '}';
    }
}

// MysticLibrary
class MysticLibrary {
    private final MagicalStructure base;
    private final Map<String, String> bookCollection; // bookName -> author
    private int knowledgeLevel;

    // Few books constructor
    public MysticLibrary(String name, String location) {
        this(name, location, Map.of("Ancient Tome", "Unknown"), 10);
    }

    // Full constructor
    public MysticLibrary(String name, String location, Map<String, String> bookCollection, int knowledgeLevel) {
        if (bookCollection == null) throw new IllegalArgumentException("bookCollection cannot be null");
        if (knowledgeLevel < 0) throw new IllegalArgumentException("knowledgeLevel cannot be negative");
        this.base = new MagicalStructure(name, location);
        this.bookCollection = new HashMap<>(bookCollection);
        this.knowledgeLevel = knowledgeLevel;
    }

    public Map<String, String> getBookCollection() {
        return new HashMap<>(bookCollection); // defensive copy
    }

    public int getKnowledgeLevel() {
        return knowledgeLevel;
    }

    public void setKnowledgeLevel(int knowledgeLevel) {
        if (knowledgeLevel < 0) throw new IllegalArgumentException("knowledgeLevel cannot be negative");
        this.knowledgeLevel = knowledgeLevel;
    }

    // Delegate MagicalStructure getters/setters
    public String getStructureName() {
        return base.getStructureName();
    }

    public String getLocation() {
        return base.getLocation();
    }

    public int getMagicPower() {
        return base.getMagicPower();
    }

    public void setMagicPower(int power) {
        base.setMagicPower(power);
    }

    public boolean isActive() {
        return base.isActive();
    }

    public void setActive(boolean active) {
        base.setActive(active);
    }

    public String getCurrentMaintainer() {
        return base.getCurrentMaintainer();
    }

    public void setCurrentMaintainer(String maintainer) {
        base.setCurrentMaintainer(maintainer);
    }

    @Override
    public String toString() {
        return "MysticLibrary{" +
                "base=" + base +
                ", bookCollection=" + bookCollection +
                ", knowledgeLevel=" + knowledgeLevel +
                '}';
    }
}

// DragonLair
class DragonLair {
    private final MagicalStructure base;
    private final String dragonType;
    private final long treasureValue;
    private int territorialRadius;

    // Different dragon types constructor
    public DragonLair(String name, String location, String dragonType) {
        this(name, location, dragonType, 100000L, 50);
    }

    public DragonLair(String name, String location, String dragonType, long treasureValue, int territorialRadius) {
        if (dragonType == null || dragonType.isBlank()) throw new IllegalArgumentException("dragonType cannot be null or empty");
        if (treasureValue < 0) throw new IllegalArgumentException("treasureValue cannot be negative");
        if (territorialRadius < 0) throw new IllegalArgumentException("territorialRadius cannot be negative");
        this.base = new MagicalStructure(name, location);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
        this.territorialRadius = territorialRadius;
    }

    public String getDragonType() {
        return dragonType;
    }

    public long getTreasureValue() {
        return treasureValue;
    }

    public int getTerritorialRadius() {
        return territorialRadius;
    }

    public void setTerritorialRadius(int territorialRadius) {
        if (territorialRadius < 0) throw new IllegalArgumentException("territorialRadius cannot be negative");
        this.territorialRadius = territorialRadius;
    }

    // Delegate MagicalStructure getters/setters
    public String getStructureName() {
        return base.getStructureName();
    }

    public String getLocation() {
        return base.getLocation();
    }

    public int getMagicPower() {
        return base.getMagicPower();
    }

    public void setMagicPower(int power) {
        base.setMagicPower(power);
    }

    public boolean isActive() {
        return base.isActive();
    }

    public void setActive(boolean active) {
        base.setActive(active);
    }

    public String getCurrentMaintainer() {
        return base.getCurrentMaintainer();
    }

    public void setCurrentMaintainer(String maintainer) {
        base.setCurrentMaintainer(maintainer);
    }

    @Override
    public String toString() {
        return "DragonLair{" +
                "base=" + base +
                ", dragonType='" + dragonType + '\'' +
                ", treasureValue=" + treasureValue +
                ", territorialRadius=" + territorialRadius +
                '}';
    }
}

// f. KingdomManager with instanceof usage
class KingdomManager {
    private final List<Object> structures;
    private final KingdomConfig config;

    public KingdomManager(KingdomConfig config) {
        if (config == null) throw new IllegalArgumentException("config cannot be null");
        this.config = config;
        this.structures = new ArrayList<>();
    }

    public void addStructure(Object structure) {
        if (structure == null) throw new IllegalArgumentException("structure cannot be null");
        String category = determineStructureCategory(structure);
        if (category == null) {
            throw new IllegalArgumentException("Unknown structure type");
        }
        // Optional: check if allowed by config.allowedStructureTypes
        if (!Arrays.asList(config.getAllowedStructureTypes()).contains(category)) {
            throw new IllegalArgumentException("Structure type " + category + " not allowed by kingdom config");
        }
        structures.add(structure);
    }

    public List<Object> getStructures() {
        return Collections.unmodifiableList(structures);
    }

    public KingdomConfig getConfig() {
        return config;
    }

    public static boolean canStructuresInteract(Object s1, Object s2) {
        if (s1 == null || s2 == null) return false;

        // Example rule: WizardTower can interact with MysticLibrary and EnchantedCastle, but not DragonLair
        if (s1 instanceof WizardTower) {
            return s2 instanceof MysticLibrary || s2 instanceof EnchantedCastle || s2 instanceof WizardTower;
        }
        if (s1 instanceof EnchantedCastle) {
            return s2 instanceof WizardTower || s2 instanceof EnchantedCastle;
        }
        if (s1 instanceof MysticLibrary) {
            return s2 instanceof WizardTower || s2 instanceof MysticLibrary;
        }
        if (s1 instanceof DragonLair) {
            return s2 instanceof DragonLair;
        }
        return false;
    }

    public static String performMagicBattle(Object attacker, Object defender) {
        if (attacker == null || defender == null) return "Invalid battle";

        int attackerPower = extractMagicPower(attacker);
        int defenderPower = extractMagicPower(defender);

        if (attackerPower == -1 || defenderPower == -1) {
            return "Unknown structure types";
        }

        if (attackerPower > defenderPower) {
            return "Attacker wins with power " + attackerPower + " against " + defenderPower;
        } else if (attackerPower < defenderPower) {
            return "Defender wins with power " + defenderPower + " against " + attackerPower;
        } else {
            return "Draw with equal power " + attackerPower;
        }
    }

    public static int calculateKingdomPower(Object[] structures) {
        if (structures == null) return 0;
        int totalPower = 0;
        for (Object s : structures) {
            int power = extractMagicPower(s);
            if (power != -1) {
                totalPower += power;
            }
        }
        return totalPower;
    }

    private String determineStructureCategory(Object structure) {
        if (structure instanceof WizardTower) return "WizardTower";
        if (structure instanceof EnchantedCastle) return "EnchantedCastle";
        if (structure instanceof MysticLibrary) return "MysticLibrary";
        if (structure instanceof DragonLair) return "DragonLair";
        return null;
    }

    private static int extractMagicPower(Object s) {
        if (s instanceof WizardTower) {
            return ((WizardTower) s).getMagicPower();
        } else if (s instanceof EnchantedCastle) {
            return ((EnchantedCastle) s).getMagicPower();
        } else if (s instanceof MysticLibrary) {
            return ((MysticLibrary) s).getMagicPower();
        } else if (s instanceof DragonLair) {
            return ((DragonLair) s).getMagicPower();
        }
        return -1; // Unknown type
    }
}

// Test main method
public class MedievalKingdomManagement {
    public static void main(String[] args) {
        KingdomConfig config = KingdomConfig.createDefaultKingdom();
        KingdomManager manager = new KingdomManager(config);

        WizardTower tower = new WizardTower("High Tower", "North Hills", 20, List.of("Fireball", "Ice Blast"), "Gandalf");
        tower.setMagicPower(500);

        EnchantedCastle castle = new EnchantedCastle("Royal Castle", "Capital City", "Royal Castle", 200, true);
        castle.setMagicPower(300);

        MysticLibrary library = new MysticLibrary("Grand Library", "East Side", Map.of("Book of Spells", "Merlin"), 50);
        library.setMagicPower(150);

        DragonLair lair = new DragonLair("Dragon's Den", "Volcano Peak", "Fire Dragon", 500000L, 100);
        lair.setMagicPower(700);

        // Add structures to kingdom
        manager.addStructure(tower);
        manager.addStructure(castle);
        manager.addStructure(library);
        // The default config allows DragonLair, so this should work
        manager.addStructure(lair);

        System.out.println("Structures in kingdom:");
        for (Object s : manager.getStructures()) {
            System.out.println(s);
        }

        // Interactions
        System.out.println("\nCan tower interact with library? " + KingdomManager.canStructuresInteract(tower, library));
        System.out.println("Can lair interact with castle? " + KingdomManager.canStructuresInteract(lair, castle));

        // Magic battle
        System.out.println("\nBattle between tower and lair: " + KingdomManager.performMagicBattle(tower, lair));
        System.out.println("Battle between castle and library: " + KingdomManager.performMagicBattle(castle, library));

        // Calculate kingdom power
        Object[] allStructures = manager.getStructures().toArray();
        System.out.println("\nTotal kingdom power: " + KingdomManager.calculateKingdomPower(allStructures));
    }
}