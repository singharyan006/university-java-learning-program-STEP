// Medieval Kingdom Builder with Magic System 
// Topics Covered: instanceof Type Checking, Inheritance, Constructor Chaining, this Keyword 
// Theme: Build a magical kingdom where different structures have unique powers! 
// Requirements: Create a kingdom building system with magical structures that interact with 
// each other. 
// Hints: a. Create abstract MagicalStructure base class: 
// ● Fields: String structureName, int magicPower, String location, boolean 
// isActive 
// ● Constructor overloading with this() chaining 
// ● Abstract method: castMagicSpell() 
// b. Create derived magical structures: 
// ● WizardTower (additional: int spellCapacity, String[] knownSpells) 
// ● EnchantedCastle (additional: int defenseRating, boolean hasDrawbridge) 
// ● MysticLibrary (additional: int bookCount, String ancientLanguage) 
// ● DragonLair (additional: String dragonType, int treasureValue) 
// c. Each structure type has unique constructor patterns: 
// ● WizardTower: Can be built empty, with basic spells, or fully equipped 
// ● Castle: Can be a simple fort, royal castle, or impregnable fortress 
// ● Library: Can start with few books or ancient collections 
// ● DragonLair: Different dragons have different lair requirements 
// d. Implement magical interactions using instanceof: 
// 4 
// ● static boolean canStructuresInteract(MagicalStructure s1, 
// MagicalStructure s2) 
// ● static String performMagicBattle(MagicalStructure attacker, 
// MagicalStructure defender) 
// ● static int calculateKingdomMagicPower(MagicalStructure[] 
// structures) 
// e. Twist: Some structure combinations create special effects: 
// ● WizardTower + Library = Knowledge boost (double spell capacity) 
// ● Castle + DragonLair = Dragon guard (triple defense) 
// ● Multiple towers = Magic network (shared spell pool) 
// f. Create a KingdomManager that uses instanceof to: 
// ● Categorize structures by type 
// ● Calculate different tax rates for each structure type 
// ● Determine kingdom specialization (Magic-focused, Defense-focused, etc.) 


import java.util.*;

abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    // Constructor chaining
    public MagicalStructure() {
        this("Unknown Structure", 50, "Unknown Realm", true);
    }

    public MagicalStructure(String structureName) {
        this(structureName, 75, "Central Kingdom", true);
    }

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    public abstract void castMagicSpell();
}

// WizardTower
class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private String[] knownSpells;

    public WizardTower() {
        this("Basic Tower", 100, "Highlands", true, 3, new String[]{"Fireball", "Shield", "Teleport"});
    }

    public WizardTower(String structureName, int spellCapacity) {
        this(structureName, 120, "Mystic Hill", true, spellCapacity, new String[]{"Spark", "Heal"});
    }

    public WizardTower(String structureName, int magicPower, String location, boolean isActive, int spellCapacity, String[] knownSpells) {
        super(structureName, magicPower, location, isActive);
        this.spellCapacity = spellCapacity;
        this.knownSpells = knownSpells;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " casts: " + Arrays.toString(knownSpells));
    }

    public void doubleSpellCapacity() {
        this.spellCapacity *= 2;
    }

    public int getSpellCapacity() {
        return spellCapacity;
    }
}

// EnchantedCastle
class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;

    public EnchantedCastle() {
        this("Simple Fort", 80, "Valley", true, 50, false);
    }

    public EnchantedCastle(String structureName, int defenseRating) {
        this(structureName, 90, "Royal Grounds", true, defenseRating, true);
    }

    public EnchantedCastle(String structureName, int magicPower, String location, boolean isActive, int defenseRating, boolean hasDrawbridge) {
        super(structureName, magicPower, location, isActive);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " activates magical shield with defense " + defenseRating);
    }

    public void tripleDefense() {
        this.defenseRating *= 3;
    }

    public int getDefenseRating() {
        return defenseRating;
    }
}

// MysticLibrary
class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;

    public MysticLibrary() {
        this("Small Library", 60, "Forest Edge", true, 100, "Elvish");
    }

    public MysticLibrary(String structureName, int bookCount) {
        this(structureName, 70, "Arcane Woods", true, bookCount, "Draconic");
    }

    public MysticLibrary(String structureName, int magicPower, String location, boolean isActive, int bookCount, String ancientLanguage) {
        super(structureName, magicPower, location, isActive);
        this.bookCount = bookCount;
        this.ancientLanguage = ancientLanguage;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " reads ancient spell in " + ancientLanguage);
    }
}

// DragonLair
class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;

    public DragonLair() {
        this("Cave of Ember", 150, "Volcano Ridge", true, "Fire Dragon", 1000);
    }

    public DragonLair(String dragonType, int treasureValue) {
        this("Lair of " + dragonType, 130, "Mountain Peak", true, dragonType, treasureValue);
    }

    public DragonLair(String structureName, int magicPower, String location, boolean isActive, String dragonType, int treasureValue) {
        super(structureName, magicPower, location, isActive);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(dragonType + " breathes magic flames from " + structureName);
    }
}

// KingdomManager
class KingdomManager {
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        return s1.isActive && s2.isActive;
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (attacker.magicPower > defender.magicPower) {
            return attacker.structureName + " wins the battle!";
        } else {
            return defender.structureName + " defends successfully!";
        }
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int total = 0;
        for (MagicalStructure s : structures) {
            total += s.magicPower;
        }
        return total;
    }

    public static void applySpecialEffects(MagicalStructure[] structures) {
        boolean hasTower = false, hasLibrary = false, hasCastle = false, hasLair = false;
        int towerCount = 0;

        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) {
                hasTower = true;
                towerCount++;
            }
            if (s instanceof MysticLibrary) hasLibrary = true;
            if (s instanceof EnchantedCastle) hasCastle = true;
            if (s instanceof DragonLair) hasLair = true;
        }

        for (MagicalStructure s : structures) {
            if (hasTower && hasLibrary && s instanceof WizardTower wt) {
                wt.doubleSpellCapacity();
                System.out.println("✨ Knowledge Boost applied to " + wt.structureName);
            }
            if (hasCastle && hasLair && s instanceof EnchantedCastle ec) {
                ec.tripleDefense();
                System.out.println("🛡️ Dragon Guard applied to " + ec.structureName);
            }
        }

        if (towerCount > 1) {
            System.out.println("🔗 Magic Network established among Wizard Towers!");
        }
    }

    public static void categorizeStructures(MagicalStructure[] structures) {
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) System.out.println(s.structureName + " is a Wizard Tower.");
            else if (s instanceof EnchantedCastle) System.out.println(s.structureName + " is an Enchanted Castle.");
            else if (s instanceof MysticLibrary) System.out.println(s.structureName + " is a Mystic Library.");
            else if (s instanceof DragonLair) System.out.println(s.structureName + " is a Dragon Lair.");
        }
    }

    public static void determineSpecialization(MagicalStructure[] structures) {
        int magic = 0, defense = 0;
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower || s instanceof MysticLibrary) magic++;
            if (s instanceof EnchantedCastle || s instanceof DragonLair) defense++;
        }

        if (magic > defense) System.out.println("🏰 Kingdom is Magic-focused.");
        else if (defense > magic) System.out.println("🛡️ Kingdom is Defense-focused.");
        else System.out.println("⚖️ Kingdom is Balanced.");
    }
}

// Main simulation
public class MagicalKingdomBuilder {
    public static void main(String[] args) {
        MagicalStructure[] kingdom = {
            new WizardTower(),
            new WizardTower("Arcane Spire", 5),
            new MysticLibrary("Grand Archive", 500),
            new EnchantedCastle("Royal Keep", 100),
            new DragonLair("Ice Dragon", 2000)
        };

        System.out.println("\n🔮 Kingdom Structures:");
        for (MagicalStructure s : kingdom) {
            s.castMagicSpell();
        }

        System.out.println("\n🧪 Magical Interactions:");
        KingdomManager.applySpecialEffects(kingdom);
        KingdomManager.categorizeStructures(kingdom);
        KingdomManager.determineSpecialization(kingdom);

        System.out.println("\n⚔️ Magic Battle:");
        System.out.println(KingdomManager.performMagicBattle(kingdom[0], kingdom[3]));

        System.out.println("\n🔢 Total Magic Power: " + KingdomManager.calculateKingdomMagicPower(kingdom));
    }
}

