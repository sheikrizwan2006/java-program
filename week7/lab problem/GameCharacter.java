// File: GameDemo.java
import java.util.*;

abstract class GameCharacter {
    String name;
    int health;

    GameCharacter(String name, int health) {
        this.name = name;
        this.health = health;
    }

    // overridden by subclasses
    public abstract int attack();

    @Override
    public String toString() {
        return name + " (" + this.getClass().getSimpleName() + ") HP:" + health;
    }
}

class Warrior extends GameCharacter {
    int weaponDamage;
    int defense;

    Warrior(String name, int health, int weaponDamage, int defense) {
        super(name, health);
        this.weaponDamage = weaponDamage;
        this.defense = defense;
    }

    @Override
    public int attack() {
        int damage = weaponDamage + (defense / 5); // simple formula
        System.out.println(name + " swings a sword for " + damage + " damage (high defense " + defense + ").");
        return damage;
    }
}

class Mage extends GameCharacter {
    int spellPower;
    int mana;

    Mage(String name, int health, int spellPower, int mana) {
        super(name, health);
        this.spellPower = spellPower;
        this.mana = mana;
    }

    @Override
    public int attack() {
        if (mana >= 20) {
            mana -= 20;
            int damage = spellPower * 2; // powerful spell cost
            System.out.println(name + " casts a spell for " + damage + " damage (mana left: " + mana + ").");
            return damage;
        } else {
            int damage = spellPower / 2;
            System.out.println(name + " has low mana and uses a weak magic attack for " + damage + " damage.");
            return damage;
        }
    }
}

class Archer extends GameCharacter {
    int arrowDamage;
    int arrows;
    int range;

    Archer(String name, int health, int arrowDamage, int arrows, int range) {
        super(name, health);
        this.arrowDamage = arrowDamage;
        this.arrows = arrows;
        this.range = range;
    }

    @Override
    public int attack() {
        if (arrows > 0) {
            arrows--;
            int damage = arrowDamage + (range / 10);
            System.out.println(name + " shoots an arrow for " + damage + " damage (arrows left: " + arrows + ").");
            return damage;
        } else {
            int damage = 4; // fallback melee
            System.out.println(name + " is out of arrows and does a small melee attack for " + damage + " damage.");
            return damage;
        }
    }
}

public class GameDemo {
    public static void main(String[] args) {
        GameCharacter[] army = new GameCharacter[] {
            new Warrior("Bjorn", 120, 18, 30),
            new Mage("Merlin", 80, 20, 50),
            new Archer("Legolas", 90, 15, 5, 60),
            new Mage("Morgana", 70, 18, 10)
        };

        System.out.println("=== Mixed Army Attacks (dynamic dispatch) ===");
        for (GameCharacter c : army) {
            System.out.println(c);
            int dmg = c.attack();          // same call, different behavior per subclass
            System.out.println("=> Damage produced: " + dmg);
            System.out.println();
        }
    }
}
