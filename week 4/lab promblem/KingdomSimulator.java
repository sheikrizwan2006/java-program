import java.util.Arrays;

// Abstract base class
abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    public MagicalStructure(String structureName) {
        this(structureName, 0);
    }

    public MagicalStructure(String structureName, int magicPower) {
        this(structureName, magicPower, "Unknown", true);
    }

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    public abstract void castMagicSpell();

    public String getStructureName() { return structureName; }
    public int getMagicPower() { return magicPower; }
    public String getLocation() { return location; }
    public boolean isActive() { return isActive; }
}

// WizardTower class
class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private String[] knownSpells;

    public WizardTower() {
        this("Unnamed Tower", 50, 10, new String[]{});
    }

    public WizardTower(String structureName, String[] basicSpells) {
        this(structureName, 70, basicSpells.length * 5, basicSpells);
    }

    public WizardTower(String structureName, int magicPower, int spellCapacity, String[] knownSpells) {
        super(structureName, magicPower);
        this.spellCapacity = spellCapacity;
        this.knownSpells = knownSpells;
    }

    @Override
    public void castMagicSpell() {
        if (isActive) {
            System.out.println(structureName + " casts powerful spells: " + Arrays.toString(knownSpells));
        } else {
            System.out.println(structureName + " is inactive and cannot cast spells.");
        }
    }

    public int getSpellCapacity() { return spellCapacity; }
    public String[] getKnownSpells() { return knownSpells; }
}

// EnchantedCastle class
class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;

    public EnchantedCastle() {
        this("Simple Fort", 20, 5, false);
    }

    public EnchantedCastle(String structureName, int defenseRating, boolean hasDrawbridge) {
        super(structureName, 40);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    public EnchantedCastle(String structureName, int magicPower, int defenseRating, boolean hasDrawbridge) {
        super(structureName, magicPower);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    @Override
    public void castMagicSpell() {
        if (isActive) {
            System.out.println(structureName + " casts protective barriers with defense rating: " + defenseRating);
        } else {
            System.out.println(structureName + " is inactive and cannot defend.");
        }
    }

    public int getDefenseRating() { return defenseRating; }
    public boolean hasDrawbridge() { return hasDrawbridge; }
}

// MysticLibrary class
class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;

    public MysticLibrary() {
        this("Small Library", 30, 100, "Old Tongue");
    }

    public MysticLibrary(String structureName, int bookCount, String ancientLanguage) {
        super(structureName, 50);
        this.bookCount = bookCount;
        this.ancientLanguage = ancientLanguage;
    }

    @Override
    public void castMagicSpell() {
        if (isActive) {
            System.out.println(structureName + " casts wisdom spells from ancient texts in " + ancientLanguage);
        } else {
            System.out.println(structureName + " is inactive and cannot cast wisdom.");
        }
    }

    public int getBookCount() { return bookCount; }
    public String getAncientLanguage() { return ancientLanguage; }
}

// DragonLair class
class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;

    public DragonLair() {
        this("Basic Lair", "Young Dragon", 60, 1000);
    }

    public DragonLair(String structureName, String dragonType, int magicPower, int treasureValue) {
        super(structureName, magicPower);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    @Override
    public void castMagicSpell() {
        if (isActive) {
            System.out.println(structureName + " unleashes fiery magic from the " + dragonType);
        } else {
            System.out.println(structureName + " is inactive and the dragon sleeps.");
        }
    }

    public String getDragonType() { return dragonType; }
    public int getTreasureValue() { return treasureValue; }
}

// MagicalInteractions utility class
class MagicalInteractions {

    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        if (s1 == null || s2 == null) return false;
        if (!s1.isActive() || !s2.isActive()) return false;

        if ((s1 instanceof WizardTower && s2 instanceof MysticLibrary) ||
            (s2 instanceof WizardTower && s1 instanceof MysticLibrary)) {
            return true;
        }

        if ((s1 instanceof EnchantedCastle && s2 instanceof DragonLair) ||
            (s2 instanceof EnchantedCastle && s1 instanceof DragonLair)) {
            return true;
        }

        return false;
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (!attacker.isActive() || !defender.isActive()) {
            return "One or both structures are inactive. Battle cannot proceed.";
        }

        int attackPower = attacker.getMagicPower();
        int defensePower = defender.getMagicPower();

        if (attacker instanceof WizardTower && defender instanceof MysticLibrary) {
            attackPower *= 2; // knowledge boost
        }

        if (attacker instanceof EnchantedCastle && defender instanceof DragonLair) {
            defensePower *= 3; // dragon guard
        }

        String winner;
        if (attackPower > defensePower) {
            winner = attacker.getStructureName() + " wins the battle!";
        } else if (defensePower > attackPower) {
            winner = defender.getStructureName() + " defends successfully!";
        } else {
            winner = "It's a tie!";
        }

        return winner;
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int totalPower = 0;
        int wizardTowerCount = 0;

        for (MagicalStructure s : structures) {
            totalPower += s.getMagicPower();

            if (s instanceof WizardTower) wizardTowerCount++;
        }

        if (wizardTowerCount > 1) {
            totalPower += wizardTowerCount * 20; // magic network boost
        }

        return totalPower;
    }
}

// KingdomManager class
class KingdomManager {
    private MagicalStructure[] structures;

    public KingdomManager(MagicalStructure[] structures) {
        this.structures = structures;
    }

    public void categorizeStructures() {
        System.out.println("Categorizing structures:");
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) System.out.println(s.getStructureName() + " is a Wizard Tower.");
            else if (s instanceof EnchantedCastle) System.out.println(s.getStructureName() + " is an Enchanted Castle.");
            else if (s instanceof MysticLibrary) System.out.println(s.getStructureName() + " is a Mystic Library.");
            else if (s instanceof DragonLair) System.out.println(s.getStructureName() + " is a Dragon Lair.");
            else System.out.println(s.getStructureName() + " is an Unknown Structure.");
        }
    }

    public void calculateTaxes() {
        System.out.println("Calculating taxes for each structure:");

        for (MagicalStructure s : structures) {
            double taxRate = 0.05; // default 5%

            if (s instanceof WizardTower) taxRate = 0.08;
            else if (s instanceof EnchantedCastle) taxRate = 0.06;
            else if (s instanceof MysticLibrary) taxRate = 0.04;
            else if (s instanceof DragonLair) taxRate = 0.10;

            System.out.printf("%s pays tax rate: %.2f%%\n", s.getStructureName(), taxRate * 100);
        }
    }

    public void determineKingdomSpecialization() {
        int magicPowerSum = 0;
        int defenseSum = 0;
        int knowledgeSum = 0;

        for (MagicalStructure s : structures) {
            magicPowerSum += s.getMagicPower();

            if (s instanceof EnchantedCastle ec) {
                defenseSum += ec.getDefenseRating();
            }

            if (s instanceof MysticLibrary ml) {
                knowledgeSum += ml.getBookCount();
            }
        }

        System.out.println("Kingdom Specialization:");

        if (magicPowerSum >= defenseSum && magicPowerSum >= knowledgeSum) {
            System.out.println("Magic-Focused Kingdom");
        } else if (defenseSum >= magicPowerSum && defenseSum >= knowledgeSum) {
            System.out.println("Defense-Focused Kingdom");
        } else if (knowledgeSum >= magicPowerSum && knowledgeSum >= defenseSum) {
            System.out.println("Knowledge-Focused Kingdom");
        } else {
            System.out.println("Balanced Kingdom");
        }
    }
}

// Main simulator class
public class KingdomSimulator {
    public static void main(String[] args) {
        MagicalStructure[] structures = new MagicalStructure[] {
            new WizardTower("Arcane Spire", new String[]{"Fireball", "Teleport"}),
            new EnchantedCastle("Fort Ironwall", 60, true),
            new MysticLibrary("Grand Library", 500, "Ancient Elvish"),
            new DragonLair("Smoldering Cave", "Fire Drake", 80, 5000),
            new WizardTower(), // Empty tower
            new EnchantedCastle() // Simple fort
        };

        KingdomManager manager = new KingdomManager(structures);

        manager.categorizeStructures();
        System.out.println();

        manager.calculateTaxes();
        System.out.println();

        manager.determineKingdomSpecialization();
        System.out.println();

        System.out.println("Total Kingdom Magic Power: " + MagicalInteractions.calculateKingdomMagicPower(structures));

        System.out.println("\nTesting interactions and battles:");
        MagicalStructure s1 = structures[0]; // Arcane Spire (WizardTower)
        MagicalStructure s2 = structures[2]; // Grand Library (MysticLibrary)

        if (MagicalInteractions.canStructuresInteract(s1, s2)) {
            System.out.println(s1.getStructureName() + " can interact with " + s2.getStructureName());
        }

        System.out.println(MagicalInteractions.performMagicBattle(s1, s2));
    }
}