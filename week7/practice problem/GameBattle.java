// File: GameBattle.java
public class GameBattle {

    // 1. Basic attack
    public void attack(int damage) {
        System.out.println("Basic attack for " + damage + " points!");
    }

    // 2. Attack with weapon
    public void attack(int damage, String weapon) {
        System.out.println("Attacking with " + weapon + " for " + damage + " points!");
    }

    // 3. Critical hit attack
    public void attack(int damage, String weapon, boolean isCritical) {
        if (isCritical) {
            System.out.println("CRITICAL HIT! " + weapon + " deals " + (damage * 2) + " points!");
        } else {
            // fall back to weapon attack
            attack(damage, weapon);
        }
    }

    // 4. Team attack
    public void attack(int damage, String[] teammates) {
        int totalDamage = damage * teammates.length;
        System.out.print("Team attack with ");
        for (int i = 0; i < teammates.length; i++) {
            System.out.print(teammates[i]);
            if (i < teammates.length - 1) System.out.print(", ");
        }
        System.out.println(" for " + totalDamage + " total damage!");
    }

    public static void main(String[] args) {
        GameBattle battle = new GameBattle();

        // Test overloaded methods
        battle.attack(50);  // Basic
        battle.attack(75, "Sword");  // Weapon attack
        battle.attack(60, "Bow", true);  // Critical attack
        battle.attack(40, new String[]{"Alice", "Bob"});  // Team attack
    }
}
