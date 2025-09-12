import java.util.*;

/**
 * KingdomManager class demonstrating instanceof usage and polymorphic interactions
 * Manages a collection of different magical structures with type-specific behavior
 */
public class KingdomManager {
    // === Fields ===
    private final List<Object> structures; // Stores different structure types
    private final KingdomConfig config;
    private String kingdomName;

    // === Constructor ===
    public KingdomManager(KingdomConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Kingdom config cannot be null");
        }
        this.config = config;
        this.structures = new ArrayList<>();
        this.kingdomName = config.getKingdomName();
    }

    // === Structure Management ===
    public boolean addStructure(Object structure) {
        if (structure == null) {
            throw new IllegalArgumentException("Structure cannot be null");
        }
        
        String structureType = determineStructureCategory(structure);
        if (!isStructureTypeAllowed(structureType)) {
            System.out.println("Structure type '" + structureType + "' is not allowed in " + kingdomName);
            return false;
        }
        
        structures.add(structure);
        System.out.println("Added " + structureType + " to " + kingdomName);
        return true;
    }
    
    public boolean removeStructure(Object structure) {
        boolean removed = structures.remove(structure);
        if (removed) {
            System.out.println("Removed structure from " + kingdomName);
        }
        return removed;
    }
    
    public List<Object> getAllStructures() {
        return new ArrayList<>(structures); // Defensive copy
    }

    // === instanceof Type Checking Methods ===
    
    /**
     * Determines the category of a structure using instanceof
     */
    private String determineStructureCategory(Object structure) {
        if (structure instanceof WizardTower) {
            return "WizardTower";
        } else if (structure instanceof EnchantedCastle) {
            return "EnchantedCastle";
        } else if (structure instanceof MysticLibrary) {
            return "MysticLibrary";
        } else if (structure instanceof DragonLair) {
            return "DragonLair";
        } else if (structure instanceof MagicalStructure) {
            return "MagicalStructure";
        } else {
            return "Unknown";
        }
    }
    
    /**
     * Checks if two structures can interact using instanceof
     */
    public static boolean canStructuresInteract(Object s1, Object s2) {
        if (s1 == null || s2 == null) return false;
        
        // Same type interactions
        if (s1.getClass().equals(s2.getClass())) {
            return true;
        }
        
        // WizardTower can interact with Libraries and Castles
        if (s1 instanceof WizardTower) {
            return s2 instanceof MysticLibrary || s2 instanceof EnchantedCastle;
        }
        if (s2 instanceof WizardTower) {
            return s1 instanceof MysticLibrary || s1 instanceof EnchantedCastle;
        }
        
        // Libraries can share knowledge with Towers
        if (s1 instanceof MysticLibrary && s2 instanceof WizardTower) {
            return true;
        }
        if (s2 instanceof MysticLibrary && s1 instanceof WizardTower) {
            return true;
        }
        
        // Castles can coordinate with all structures for defense
        if (s1 instanceof EnchantedCastle || s2 instanceof EnchantedCastle) {
            return true;
        }
        
        // Dragon lairs are territorial - limited interactions
        if (s1 instanceof DragonLair || s2 instanceof DragonLair) {
            // Dragons only interact with castles (treaties) or other dragons (rivalry)
            return (s1 instanceof DragonLair && s2 instanceof EnchantedCastle) ||
                   (s2 instanceof DragonLair && s1 instanceof EnchantedCastle) ||
                   (s1 instanceof DragonLair && s2 instanceof DragonLair);
        }
        
        return false;
    }
    
    /**
     * Performs a magic battle between structures using instanceof for different behaviors
     */
    public static String performMagicBattle(Object attacker, Object defender) {
        if (attacker == null || defender == null) {
            return "Invalid battle participants";
        }
        
        int attackPower = getStructureBattlePower(attacker, true);
        int defensePower = getStructureBattlePower(defender, false);
        
        StringBuilder result = new StringBuilder();
        result.append("MAGICAL BATTLE REPORT\n");
        result.append("Attacker: ").append(getStructureName(attacker)).append(" (Power: ").append(attackPower).append(")\n");
        result.append("Defender: ").append(getStructureName(defender)).append(" (Power: ").append(defensePower).append(")\n");
        
        // Apply type-specific battle modifiers
        int modifier = getBattleModifier(attacker, defender);
        attackPower += modifier;
        
        if (modifier != 0) {
            result.append("Type advantage modifier: ").append(modifier).append("\n");
        }
        
        result.append("Final attack power: ").append(attackPower).append("\n");
        
        if (attackPower > defensePower) {
            result.append("RESULT: Attacker WINS!");
            applyBattleEffects(attacker, defender, true);
        } else if (defensePower > attackPower) {
            result.append("RESULT: Defender WINS!");
            applyBattleEffects(attacker, defender, false);
        } else {
            result.append("RESULT: DRAW - No decisive winner");
        }
        
        return result.toString();
    }
    
    /**
     * Calculates total kingdom power using instanceof to handle different structure types
     */
    public static int calculateKingdomPower(Object[] structures) {
        if (structures == null || structures.length == 0) {
            return 0;
        }
        
        int totalPower = 0;
        int wizardTowers = 0, castles = 0, libraries = 0, dragonLairs = 0;
        
        for (Object structure : structures) {
            if (structure == null) continue;
            
            // Base power from structure
            totalPower += getStructureBasePower(structure);
            
            // Count structure types for synergy bonuses
            if (structure instanceof WizardTower) {
                wizardTowers++;
            } else if (structure instanceof EnchantedCastle) {
                castles++;
            } else if (structure instanceof MysticLibrary) {
                libraries++;
            } else if (structure instanceof DragonLair) {
                dragonLairs++;
            }
        }
        
        // Apply synergy bonuses
        totalPower += calculateSynergyBonuses(wizardTowers, castles, libraries, dragonLairs);
        
        return totalPower;
    }

    // === Helper Methods for instanceof Operations ===
    
    private static int getStructureBattlePower(Object structure, boolean isAttacker) {
        if (structure instanceof WizardTower) {
            WizardTower tower = (WizardTower) structure;
            int power = tower.getMagicPower() + (tower.getSpellCount() * 10);
            return isAttacker ? power + 50 : power; // Wizards are better attackers
        } else if (structure instanceof EnchantedCastle) {
            EnchantedCastle castle = (EnchantedCastle) structure;
            int power = castle.getMagicPower() + castle.getDefenseRating() + (castle.getGarrisonSize() * 2);
            return isAttacker ? power : power + 100; // Castles are better defenders
        } else if (structure instanceof MysticLibrary) {
            MysticLibrary library = (MysticLibrary) structure;
            int power = library.getMagicPower() + (library.getKnowledgeLevel() / 2) + (library.getBookCount() * 3);
            return power; // Libraries have consistent power
        } else if (structure instanceof DragonLair) {
            DragonLair lair = (DragonLair) structure;
            int power = lair.getMagicPower() + (int)(lair.getTreasureValue() / 100) + (lair.getTerritorialRadius() * 5);
            return isAttacker ? power + 200 : power + 50; // Dragons are fearsome attackers
        } else if (structure instanceof MagicalStructure) {
            MagicalStructure base = (MagicalStructure) structure;
            return base.getMagicPower();
        }
        return 0;
    }
    
    private static int getBattleModifier(Object attacker, Object defender) {
        // Type effectiveness system
        if (attacker instanceof WizardTower && defender instanceof DragonLair) {
            return 100; // Wizards are effective against dragons
        }
        if (attacker instanceof DragonLair && defender instanceof WizardTower) {
            return -50; // Dragons struggle against wizards
        }
        if (attacker instanceof EnchantedCastle && defender instanceof WizardTower) {
            return 75; // Military might vs magical power
        }
        if (attacker instanceof DragonLair && defender instanceof EnchantedCastle) {
            return 150; // Dragons excel at sieges
        }
        if (attacker instanceof MysticLibrary) {
            return -100; // Libraries are not combat-oriented
        }
        return 0;
    }
    
    private static void applyBattleEffects(Object attacker, Object defender, boolean attackerWon) {
        if (attackerWon) {
            enhanceStructure(attacker, 20);
            weakenStructure(defender, 30);
        } else {
            enhanceStructure(defender, 15);
            weakenStructure(attacker, 20);
        }
    }
    
    private static void enhanceStructure(Object structure, int amount) {
        if (structure instanceof MagicalStructure) {
            MagicalStructure ms = (MagicalStructure) structure;
            ms.enhanceMagicPower(amount);
        }
    }
    
    private static void weakenStructure(Object structure, int amount) {
        if (structure instanceof MagicalStructure) {
            MagicalStructure ms = (MagicalStructure) structure;
            ms.drainMagicPower(amount);
        }
    }
    
    private static String getStructureName(Object structure) {
        if (structure instanceof MagicalStructure) {
            MagicalStructure ms = (MagicalStructure) structure;
            return ms.getStructureName();
        }
        return "Unknown Structure";
    }
    
    private static int getStructureBasePower(Object structure) {
        if (structure instanceof WizardTower) {
            WizardTower tower = (WizardTower) structure;
            return tower.getMagicPower() + (tower.getSpellCount() * 15);
        } else if (structure instanceof EnchantedCastle) {
            EnchantedCastle castle = (EnchantedCastle) structure;
            return castle.getMagicPower() + (castle.getDefenseRating() / 2) + castle.getGarrisonSize();
        } else if (structure instanceof MysticLibrary) {
            MysticLibrary library = (MysticLibrary) structure;
            return library.getMagicPower() + library.getKnowledgeLevel() + (library.getBookCount() * 2);
        } else if (structure instanceof DragonLair) {
            DragonLair lair = (DragonLair) structure;
            return lair.getMagicPower() + (int)(lair.getTreasureValue() / 50) + (lair.getTerritorialRadius() * 3);
        } else if (structure instanceof MagicalStructure) {
            MagicalStructure base = (MagicalStructure) structure;
            return base.getMagicPower();
        }
        return 0;
    }
    
    private static int calculateSynergyBonuses(int towers, int castles, int libraries, int lairs) {
        int bonus = 0;
        
        // Tower-Library synergy (magical research)
        bonus += Math.min(towers, libraries) * 50;
        
        // Castle-Tower synergy (protected magical research)
        bonus += Math.min(castles, towers) * 30;
        
        // Multiple castles (defensive network)
        if (castles >= 2) bonus += castles * 25;
        
        // Dragon diversity bonus
        if (lairs >= 2) bonus += lairs * 40;
        
        // Complete kingdom bonus (all structure types)
        if (towers > 0 && castles > 0 && libraries > 0 && lairs > 0) {
            bonus += 200;
        }
        
        return bonus;
    }

    // === Kingdom Management Methods ===
    
    public void displayKingdomStatus() {
        System.out.println("=== KINGDOM STATUS: " + kingdomName + " ===");
        System.out.println("Founded: " + config.getFoundingYear());
        System.out.println("Total Structures: " + structures.size());
        
        Map<String, Integer> structureCount = new HashMap<>();
        for (Object structure : structures) {
            String type = determineStructureCategory(structure);
            structureCount.put(type, structureCount.getOrDefault(type, 0) + 1);
        }
        
        System.out.println("Structure Breakdown:");
        for (Map.Entry<String, Integer> entry : structureCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        
        Object[] structureArray = structures.toArray();
        int totalPower = calculateKingdomPower(structureArray);
        System.out.println("Total Kingdom Power: " + totalPower);
        
        System.out.println("Resource Limits: " + config.getResourceLimits());
    }
    
    public List<Object> findStructuresOfType(Class<?> type) {
        List<Object> result = new ArrayList<>();
        for (Object structure : structures) {
            if (type.isInstance(structure)) {
                result.add(structure);
            }
        }
        return result;
    }
    
    public void performKingdomwideOperation(String operation) {
        System.out.println("Performing kingdom-wide operation: " + operation);
        
        for (Object structure : structures) {
            if (structure instanceof WizardTower && operation.equals("ENHANCE_MAGIC")) {
                WizardTower tower = (WizardTower) structure;
                tower.practiceSpells();
            } else if (structure instanceof EnchantedCastle && operation.equals("DEFENSE_DRILL")) {
                EnchantedCastle castle = (EnchantedCastle) structure;
                castle.trainGarrison();
            } else if (structure instanceof MysticLibrary && operation.equals("ORGANIZE")) {
                MysticLibrary library = (MysticLibrary) structure;
                library.organizeLibrary();
            } else if (structure instanceof DragonLair && operation.equals("SORT_TREASURE")) {
                DragonLair lair = (DragonLair) structure;
                lair.sortTreasure();
            }
        }
    }
    
    private boolean isStructureTypeAllowed(String structureType) {
        String[] allowedTypes = config.getAllowedStructureTypes();
        for (String allowedType : allowedTypes) {
            if (allowedType.equals(structureType)) {
                return true;
            }
        }
        return false;
    }

    // === Getters ===
    public KingdomConfig getConfig() {
        return config;
    }
    
    public String getKingdomName() {
        return kingdomName;
    }
    
    public int getStructureCount() {
        return structures.size();
    }
}