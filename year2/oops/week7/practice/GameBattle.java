// File: GameBattle.java
public class GameBattle {

    // Method 1: Basic attack with integer damage
    public void attack(int damage) {
        System.out.println("Basic attack for " + damage + " points!");
    }

    // Method 2: Overloaded for weapon name
    public void attack(int damage, String weapon) {
        System.out.println("Attacking with " + weapon + " for " + damage + " points!");
    }

    // Method 3: Overloaded for critical hits
    public void attack(int damage, String weapon, boolean isCritical) {
        if (isCritical) {
            System.out.println("CRITICAL HIT! " + weapon + " deals " + (damage * 2) + " points!");
        } else {
            // Reuses the previous overloaded method
            attack(damage, weapon);
        }
    }

    // Method 4: Overloaded for team attacks
    public void attack(int damage, String[] teammates) {
        StringBuilder teamNames = new StringBuilder();
        int teamSize = teammates.length;
        for (int i = 0; i < teamSize; i++) {
            teamNames.append(teammates[i]);
            if (i < teamSize - 1) {
                teamNames.append(", ");
            }
        }
        System.out.println("Team attack with " + teamNames.toString() + " for " + (damage * teamSize) + " total damage!");
    }

    public static void main(String[] args) {
        // Gaming Battle Simulation
        System.out.println("Starting a new game battle simulation...");

        // 1. Create a GameBattle object
        GameBattle game = new GameBattle();

        // 2. Test all overloaded attack methods
        System.out.println("\nTesting different attack types:");
        // Test 1: Basic attack with 50 damage
        game.attack(50);

        // Test 2: Sword attack with 75 damage
        game.attack(75, "Sword");

        // Test 3: Critical bow attack with 60 damage
        game.attack(60, "Bow", true);

        // Test 4: Non-critical bow attack with 60 damage
        game.attack(60, "Bow", false);

        // Test 5: Team attack with {"Alice", "Bob"} for 40 base damage
        String[] team = {"Alice", "Bob"};
        game.attack(40, team);

        System.out.println("\nSimulation complete!");
    }
}