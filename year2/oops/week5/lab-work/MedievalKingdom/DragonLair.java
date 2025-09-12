import java.util.*;

/**
 * DragonLair class demonstrating dragon types and treasure management
 * Extends MagicalStructure with dragon-specific functionality
 */
public class DragonLair extends MagicalStructure {
    // === Final fields ===
    private final String dragonType;
    
    // === Mutable state ===
    private long treasureValue;
    private int territorialRadius;
    private String currentDragon;
    private Map<String, Integer> treasureInventory;
    private boolean isHoarding;

    // === Constructor variations for different dragon types ===
    
    // Basic lair constructor
    public DragonLair(String name, String location) {
        super(name, location, 300, false); // High power, initially inactive
        this.dragonType = "Lesser Dragon";
        this.treasureValue = 1000;
        this.territorialRadius = 5;
        this.currentDragon = "None";
        this.treasureInventory = new HashMap<>();
        this.isHoarding = false;
        initializeBasicTreasure();
    }
    
    // Fire dragon lair
    public DragonLair(String name, String location, String dragonName) {
        super(name, location, 500, true);
        this.dragonType = "Fire Dragon";
        this.treasureValue = 10000;
        this.territorialRadius = 15;
        setCurrentDragon(dragonName);
        this.treasureInventory = new HashMap<>();
        this.isHoarding = true;
        initializeFireDragonTreasure();
    }
    
    // Ancient dragon lair with specified type
    public DragonLair(String name, String location, String dragonName, String type) {
        super(name, location, 700, true);
        this.dragonType = validateDragonType(type);
        this.treasureValue = calculateInitialTreasure(this.dragonType);
        this.territorialRadius = 25;
        setCurrentDragon(dragonName);
        this.treasureInventory = new HashMap<>();
        this.isHoarding = true;
        initializeTreasureByType(this.dragonType);
    }
    
    // Fully customized lair
    public DragonLair(String name, String location, String dragonName, String type, 
                     long treasureValue, int radius) {
        super(name, location, 600, true);
        this.dragonType = validateDragonType(type);
        setTreasureValue(treasureValue);
        setTerritorialRadius(radius);
        setCurrentDragon(dragonName);
        this.treasureInventory = new HashMap<>();
        this.isHoarding = true;
        initializeTreasureByType(this.dragonType);
    }

    // === Getters and Setters ===
    public String getDragonType() {
        return dragonType;
    }
    
    public long getTreasureValue() {
        return treasureValue;
    }
    
    public void setTreasureValue(long treasureValue) {
        if (treasureValue < 0) {
            throw new IllegalArgumentException("Treasure value cannot be negative");
        }
        this.treasureValue = treasureValue;
    }
    
    public int getTerritorialRadius() {
        return territorialRadius;
    }
    
    public void setTerritorialRadius(int territorialRadius) {
        if (territorialRadius < 1 || territorialRadius > 100) {
            throw new IllegalArgumentException("Territorial radius must be between 1 and 100");
        }
        this.territorialRadius = territorialRadius;
    }
    
    public String getCurrentDragon() {
        return currentDragon;
    }
    
    public void setCurrentDragon(String dragonName) {
        if (dragonName == null || dragonName.trim().isEmpty()) {
            this.currentDragon = "None";
            setActive(false);
            this.isHoarding = false;
        } else {
            this.currentDragon = dragonName.trim();
            setActive(true);
            setCurrentMaintainer(dragonName);
            this.isHoarding = true;
        }
    }
    
    public Map<String, Integer> getTreasureInventory() {
        return new HashMap<>(treasureInventory); // Defensive copy
    }
    
    public boolean isHoarding() {
        return isHoarding;
    }
    
    public void setHoarding(boolean hoarding) {
        this.isHoarding = hoarding;
    }

    // === Treasure Management ===
    public void addTreasure(String itemType, int quantity, long value) {
        if (itemType == null || itemType.trim().isEmpty()) {
            throw new IllegalArgumentException("Item type cannot be null or empty");
        }
        if (quantity <= 0 || value <= 0) {
            throw new IllegalArgumentException("Quantity and value must be positive");
        }
        
        String trimmedType = itemType.trim();
        treasureInventory.put(trimmedType, treasureInventory.getOrDefault(trimmedType, 0) + quantity);
        treasureValue += value;
        
        System.out.println("Added " + quantity + " " + trimmedType + " worth " + value + " gold to " + getStructureName());
        
        if (isHoarding) {
            enhanceMagicPower((int)(value / 100)); // More treasure = more power
        }
    }
    
    public boolean removeTreasure(String itemType, int quantity) {
        if (itemType == null || quantity <= 0) return false;
        
        String trimmedType = itemType.trim();
        Integer currentQuantity = treasureInventory.get(trimmedType);
        
        if (currentQuantity == null || currentQuantity < quantity) {
            System.out.println("Not enough " + trimmedType + " in the hoard.");
            return false;
        }
        
        if (currentQuantity.equals(quantity)) {
            treasureInventory.remove(trimmedType);
        } else {
            treasureInventory.put(trimmedType, currentQuantity - quantity);
        }
        
        // Estimate value lost
        long estimatedValue = calculateItemValue(trimmedType) * quantity;
        treasureValue = Math.max(0, treasureValue - estimatedValue);
        
        System.out.println("Removed " + quantity + " " + trimmedType + " from " + getStructureName());
        drainMagicPower((int)(estimatedValue / 200)); // Losing treasure weakens dragon
        return true;
    }
    
    public void sortTreasure() {
        if (!isActive() || "None".equals(currentDragon)) {
            System.out.println("No dragon available to sort treasure.");
            return;
        }
        
        System.out.println(currentDragon + " sorts the treasure hoard at " + getStructureName());
        enhanceMagicPower(20);
        treasureValue += treasureInventory.size() * 100; // Organization increases value
    }
    
    public void displayTreasureHoard() {
        System.out.println("=== Treasure Hoard of " + getStructureName() + " ===");
        System.out.println("Total Value: " + treasureValue + " gold");
        System.out.println("Dragon: " + currentDragon + " (" + dragonType + ")");
        System.out.println("Territory: " + territorialRadius + " km radius");
        
        if (treasureInventory.isEmpty()) {
            System.out.println("The hoard is empty!");
        } else {
            System.out.println("Treasure Contents:");
            for (Map.Entry<String, Integer> entry : treasureInventory.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
    
    public boolean defendHoard(int attackerPower) {
        if (!isActive()) {
            System.out.println("No dragon to defend the hoard!");
            return false;
        }
        
        int defensePower = getMagicPower() + (int)(treasureValue / 1000) + territorialRadius;
        System.out.println(currentDragon + " defends with power: " + defensePower);
        System.out.println("Attacker power: " + attackerPower);
        
        if (defensePower >= attackerPower) {
            System.out.println(currentDragon + " successfully defends the hoard!");
            enhanceMagicPower(10); // Victory strengthens the dragon
            return true;
        } else {
            System.out.println("The hoard has been raided!");
            long stolenValue = treasureValue / 4; // Lose 25% of treasure
            treasureValue -= stolenValue;
            drainMagicPower(50);
            System.out.println("Lost " + stolenValue + " gold worth of treasure!");
            return false;
        }
    }
    
    public void expandTerritory(int expansion) {
        if (expansion <= 0) {
            throw new IllegalArgumentException("Expansion must be positive");
        }
        
        if (!isActive()) {
            System.out.println("No dragon to expand territory.");
            return;
        }
        
        int newRadius = Math.min(100, territorialRadius + expansion);
        int actualExpansion = newRadius - territorialRadius;
        setTerritorialRadius(newRadius);
        
        if (actualExpansion > 0) {
            System.out.println(currentDragon + " expanded territory by " + actualExpansion + " km");
            enhanceMagicPower(actualExpansion * 5);
        } else {
            System.out.println("Territory already at maximum size!");
        }
    }

    // === Dragon Type Management ===
    private String validateDragonType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return "Unknown Dragon";
        }
        
        String trimmed = type.trim();
        String[] validTypes = {"Fire Dragon", "Ice Dragon", "Lightning Dragon", "Earth Dragon", 
                              "Shadow Dragon", "Crystal Dragon", "Ancient Dragon", "Lesser Dragon"};
        
        for (String validType : validTypes) {
            if (validType.equalsIgnoreCase(trimmed)) {
                return validType;
            }
        }
        
        return trimmed; // Allow custom types
    }
    
    private long calculateInitialTreasure(String type) {
        switch (type) {
            case "Ancient Dragon": return 50000;
            case "Fire Dragon": return 20000;
            case "Ice Dragon": return 18000;
            case "Lightning Dragon": return 22000;
            case "Earth Dragon": return 15000;
            case "Shadow Dragon": return 25000;
            case "Crystal Dragon": return 30000;
            case "Lesser Dragon": return 5000;
            default: return 10000;
        }
    }
    
    private long calculateItemValue(String itemType) {
        switch (itemType.toLowerCase()) {
            case "gold coins": return 1;
            case "silver": return 10;
            case "gems": return 100;
            case "artifacts": return 1000;
            case "magical items": return 500;
            case "precious metals": return 50;
            default: return 25;
        }
    }

    // === Initialization methods ===
    private void initializeBasicTreasure() {
        treasureInventory.put("Gold Coins", 1000);
        treasureInventory.put("Silver", 100);
    }
    
    private void initializeFireDragonTreasure() {
        treasureInventory.put("Gold Coins", 5000);
        treasureInventory.put("Fire Gems", 50);
        treasureInventory.put("Melted Weapons", 200);
        treasureInventory.put("Charred Artifacts", 20);
    }
    
    private void initializeTreasureByType(String type) {
        switch (type) {
            case "Ice Dragon":
                treasureInventory.put("Ice Crystals", 100);
                treasureInventory.put("Frozen Gems", 75);
                treasureInventory.put("Silver", 1000);
                break;
            case "Lightning Dragon":
                treasureInventory.put("Storm Gems", 80);
                treasureInventory.put("Electrified Metals", 150);
                treasureInventory.put("Lightning Rods", 25);
                break;
            case "Ancient Dragon":
                treasureInventory.put("Ancient Artifacts", 100);
                treasureInventory.put("Legendary Gems", 200);
                treasureInventory.put("Lost Treasures", 50);
                treasureInventory.put("Gold Coins", 10000);
                break;
            default:
                initializeFireDragonTreasure();
        }
    }

    // === Factory methods for specific dragon types ===
    public static DragonLair createYoungDragonLair(String name, String location, String dragonName) {
        DragonLair lair = new DragonLair(name, location, dragonName);
        lair.setTreasureValue(2000);
        lair.setTerritorialRadius(8);
        return lair;
    }
    
    public static DragonLair createAncientLair(String name, String location, String ancientDragon) {
        DragonLair lair = new DragonLair(name, location, ancientDragon, "Ancient Dragon");
        lair.expandTerritory(15); // Ancient dragons have large territories
        lair.addTreasure("Legendary Artifacts", 10, 50000);
        return lair;
    }
    
    public static DragonLair createElementalLair(String name, String location, String dragon, String element) {
        String dragonType = element + " Dragon";
        DragonLair lair = new DragonLair(name, location, dragon, dragonType);
        
        // Add element-specific treasures
        switch (element.toLowerCase()) {
            case "fire":
                lair.addTreasure("Fire Opals", 20, 5000);
                break;
            case "ice":
                lair.addTreasure("Frost Diamonds", 15, 6000);
                break;
            case "lightning":
                lair.addTreasure("Storm Sapphires", 18, 5500);
                break;
        }
        
        return lair;
    }

    // === Utility methods ===
    public int getTreasureTypes() {
        return treasureInventory.size();
    }
    
    public boolean hasTreasureType(String itemType) {
        return treasureInventory.containsKey(itemType);
    }
    
    public String getHoardStatus() {
        if (treasureValue >= 100000) return "Legendary Hoard";
        if (treasureValue >= 50000) return "Great Hoard";
        if (treasureValue >= 20000) return "Substantial Hoard";
        if (treasureValue >= 5000) return "Modest Hoard";
        return "Meager Collection";
    }
    
    public boolean isWealthyDragon() {
        return treasureValue >= 25000 && treasureInventory.size() >= 5;
    }

    // === Override methods ===
    @Override
    public String getStructureInfo() {
        return String.format("DragonLair: %s (%s, Dragon: %s, Treasure: %d gold, Territory: %d km)", 
            getStructureName(), dragonType, currentDragon, treasureValue, territorialRadius);
    }

    @Override
    public String toString() {
        return "DragonLair{" +
                "name='" + getStructureName() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", type='" + dragonType + '\'' +
                ", dragon='" + currentDragon + '\'' +
                ", treasure=" + treasureValue +
                ", territory=" + territorialRadius + "km" +
                ", hoarding=" + isHoarding +
                ", active=" + isActive() +
                '}';
    }
}