import java.util.*;

/**
 * WizardTower class demonstrating constructor overloading and spell management
 * Extends MagicalStructure with wizard-specific functionality
 */
public class WizardTower extends MagicalStructure {
    // === Final fields ===
    private final int maxSpellCapacity;
    
    // === Mutable collections ===
    private List<String> knownSpells;
    private String currentWizard;

    // === Constructor patterns ===
    
    // Empty tower constructor
    public WizardTower(String name, String location) {
        super(name, location, 200, false); // Higher default power, inactive
        this.maxSpellCapacity = 10;
        this.knownSpells = new ArrayList<>();
        this.currentWizard = "None";
    }
    
    // Basic spells constructor
    public WizardTower(String name, String location, String wizardName) {
        super(name, location, 300, true); // Active with wizard
        this.maxSpellCapacity = 15;
        this.knownSpells = new ArrayList<>();
        initializeBasicSpells();
        setCurrentWizard(wizardName);
    }
    
    // Fully equipped constructor
    public WizardTower(String name, String location, String wizardName, 
                      int capacity, List<String> initialSpells) {
        super(name, location, 400, true); // High power, active
        if (capacity < 5 || capacity > 50) {
            throw new IllegalArgumentException("Spell capacity must be between 5 and 50");
        }
        this.maxSpellCapacity = capacity;
        this.knownSpells = new ArrayList<>();
        setCurrentWizard(wizardName);
        
        if (initialSpells != null) {
            for (String spell : initialSpells) {
                learnSpell(spell);
            }
        }
    }
    
    // Advanced constructor with power specification
    public WizardTower(String name, String location, String wizardName, 
                      int capacity, int magicPower, List<String> initialSpells) {
        super(name, location, magicPower, true);
        if (capacity < 5 || capacity > 50) {
            throw new IllegalArgumentException("Spell capacity must be between 5 and 50");
        }
        this.maxSpellCapacity = capacity;
        this.knownSpells = new ArrayList<>();
        setCurrentWizard(wizardName);
        
        if (initialSpells != null) {
            for (String spell : initialSpells) {
                learnSpell(spell);
            }
        }
    }

    // === Getters and Setters ===
    public int getMaxSpellCapacity() {
        return maxSpellCapacity;
    }
    
    public List<String> getKnownSpells() {
        return new ArrayList<>(knownSpells); // Defensive copy
    }
    
    public String getCurrentWizard() {
        return currentWizard;
    }
    
    public void setCurrentWizard(String wizardName) {
        if (wizardName == null || wizardName.trim().isEmpty()) {
            this.currentWizard = "None";
            setActive(false);
        } else {
            this.currentWizard = wizardName.trim();
            setActive(true);
            setCurrentMaintainer(wizardName);
        }
    }

    // === Spell Management ===
    public boolean learnSpell(String spell) {
        if (spell == null || spell.trim().isEmpty()) {
            throw new IllegalArgumentException("Spell name cannot be null or empty");
        }
        
        if (knownSpells.size() >= maxSpellCapacity) {
            System.out.println("Tower at capacity! Cannot learn more spells.");
            return false;
        }
        
        String trimmedSpell = spell.trim();
        if (knownSpells.contains(trimmedSpell)) {
            System.out.println("Spell '" + trimmedSpell + "' already known.");
            return false;
        }
        
        knownSpells.add(trimmedSpell);
        System.out.println("Learned new spell: " + trimmedSpell);
        enhanceMagicPower(10); // Learning spells increases power
        return true;
    }
    
    public boolean forgetSpell(String spell) {
        if (spell == null) return false;
        
        boolean removed = knownSpells.remove(spell.trim());
        if (removed) {
            System.out.println("Forgot spell: " + spell);
            drainMagicPower(5); // Forgetting spells reduces power
        }
        return removed;
    }
    
    public boolean castSpell(String spell) {
        if (!isActive()) {
            System.out.println("Tower is inactive! Cannot cast spells.");
            return false;
        }
        
        if (getMagicPower() < 20) {
            System.out.println("Insufficient magic power to cast spells!");
            return false;
        }
        
        if (!knownSpells.contains(spell)) {
            System.out.println("Spell '" + spell + "' is not known by this tower.");
            return false;
        }
        
        drainMagicPower(20);
        System.out.println(currentWizard + " casts " + spell + " from " + getStructureName() + "!");
        return true;
    }
    
    public void practiceSpells() {
        if (!isActive() || "None".equals(currentWizard)) {
            System.out.println("No wizard available to practice spells.");
            return;
        }
        
        if (knownSpells.isEmpty()) {
            System.out.println("No spells to practice.");
            return;
        }
        
        System.out.println(currentWizard + " practices spells at " + getStructureName());
        enhanceMagicPower(5);
    }

    // === Factory methods for common tower types ===
    public static WizardTower createApprenticeTower(String name, String location, String apprentice) {
        WizardTower tower = new WizardTower(name, location, apprentice);
        tower.learnSpell("Light");
        tower.learnSpell("Detect Magic");
        return tower;
    }
    
    public static WizardTower createBattleMageTower(String name, String location, String mage) {
        List<String> battleSpells = Arrays.asList(
            "Fireball", "Lightning Bolt", "Magic Missile", "Shield", "Heal"
        );
        return new WizardTower(name, location, mage, 20, battleSpells);
    }
    
    public static WizardTower createArchmageTower(String name, String location, String archmage) {
        List<String> masterSpells = Arrays.asList(
            "Meteor", "Time Stop", "Wish", "Gate", "Resurrection", 
            "Disintegrate", "Power Word Kill", "Mass Heal"
        );
        return new WizardTower(name, location, archmage, 30, 800, masterSpells);
    }

    // === Utility methods ===
    private void initializeBasicSpells() {
        knownSpells.add("Light");
        knownSpells.add("Detect Magic");
        knownSpells.add("Magic Missile");
    }
    
    public int getSpellCount() {
        return knownSpells.size();
    }
    
    public boolean hasSpell(String spell) {
        return knownSpells.contains(spell);
    }
    
    public boolean isAtCapacity() {
        return knownSpells.size() >= maxSpellCapacity;
    }

    // === Override methods ===
    @Override
    public String getStructureInfo() {
        return String.format("WizardTower: %s (Wizard: %s, Spells: %d/%d, Power: %d)", 
            getStructureName(), currentWizard, knownSpells.size(), maxSpellCapacity, getMagicPower());
    }

    @Override
    public String toString() {
        return "WizardTower{" +
                "name='" + getStructureName() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", wizard='" + currentWizard + '\'' +
                ", spells=" + knownSpells.size() + "/" + maxSpellCapacity +
                ", power=" + getMagicPower() +
                ", active=" + isActive() +
                '}';
    }
}