/**
 * EnchantedCastle class demonstrating castle variations and defense systems
 * Extends MagicalStructure with castle-specific functionality
 */
public class EnchantedCastle extends MagicalStructure {
    // === Final fields ===
    private final String castleType;
    
    // === Mutable state ===
    private int defenseRating;
    private boolean hasDrawbridge;
    private String currentLord;
    private int garrisonSize;

    // === Constructor variations ===
    
    // Simple fort constructor
    public EnchantedCastle(String name, String location) {
        super(name, location, 150, true);
        this.castleType = "Simple Fort";
        this.defenseRating = 100;
        this.hasDrawbridge = false;
        this.currentLord = "None";
        this.garrisonSize = 10;
    }
    
    // Royal castle constructor
    public EnchantedCastle(String name, String location, String lord) {
        super(name, location, 300, true);
        this.castleType = "Royal Castle";
        this.defenseRating = 250;
        this.hasDrawbridge = true;
        setCurrentLord(lord);
        this.garrisonSize = 50;
    }
    
    // Impregnable fortress constructor
    public EnchantedCastle(String name, String location, String lord, String fortressType) {
        super(name, location, 500, true);
        if (fortressType == null || fortressType.trim().isEmpty()) {
            this.castleType = "Impregnable Fortress";
        } else {
            this.castleType = fortressType.trim();
        }
        this.defenseRating = 400;
        this.hasDrawbridge = true;
        setCurrentLord(lord);
        this.garrisonSize = 100;
    }
    
    // Full specification constructor
    public EnchantedCastle(String name, String location, String lord, String type, 
                          int defenseRating, boolean hasDrawbridge, int garrison) {
        super(name, location, 400, true);
        this.castleType = (type != null && !type.trim().isEmpty()) ? type.trim() : "Custom Castle";
        setDefenseRating(defenseRating);
        this.hasDrawbridge = hasDrawbridge;
        setCurrentLord(lord);
        setGarrisonSize(garrison);
    }

    // === Getters and Setters ===
    public String getCastleType() {
        return castleType;
    }
    
    public int getDefenseRating() {
        return defenseRating;
    }
    
    public void setDefenseRating(int defenseRating) {
        if (defenseRating < 0 || defenseRating > 1000) {
            throw new IllegalArgumentException("Defense rating must be between 0 and 1000");
        }
        this.defenseRating = defenseRating;
    }
    
    public boolean hasDrawbridge() {
        return hasDrawbridge;
    }
    
    public void setHasDrawbridge(boolean hasDrawbridge) {
        this.hasDrawbridge = hasDrawbridge;
        if (hasDrawbridge) {
            enhanceDefenses(10);
        }
    }
    
    public String getCurrentLord() {
        return currentLord;
    }
    
    public void setCurrentLord(String lord) {
        if (lord == null || lord.trim().isEmpty()) {
            this.currentLord = "None";
            setActive(false);
        } else {
            this.currentLord = lord.trim();
            setActive(true);
            setCurrentMaintainer(lord);
        }
    }
    
    public int getGarrisonSize() {
        return garrisonSize;
    }
    
    public void setGarrisonSize(int garrisonSize) {
        if (garrisonSize < 0 || garrisonSize > 500) {
            throw new IllegalArgumentException("Garrison size must be between 0 and 500");
        }
        this.garrisonSize = garrisonSize;
    }

    // === Castle Management ===
    public void raiseDrawbridge() {
        if (!hasDrawbridge) {
            System.out.println(getStructureName() + " has no drawbridge to raise!");
            return;
        }
        System.out.println("Drawbridge raised at " + getStructureName() + " - castle secured!");
        enhanceDefenses(20);
    }
    
    public void lowerDrawbridge() {
        if (!hasDrawbridge) {
            System.out.println(getStructureName() + " has no drawbridge to lower!");
            return;
        }
        System.out.println("Drawbridge lowered at " + getStructureName() + " - castle accessible.");
        reduceDefenses(20);
    }
    
    public void enhanceDefenses(int enhancement) {
        int newRating = Math.min(1000, defenseRating + enhancement);
        setDefenseRating(newRating);
        System.out.println(getStructureName() + " defenses enhanced to " + defenseRating);
    }
    
    public void reduceDefenses(int reduction) {
        int newRating = Math.max(0, defenseRating - reduction);
        setDefenseRating(newRating);
        if (newRating > 0) {
            System.out.println(getStructureName() + " defenses reduced to " + defenseRating);
        } else {
            System.out.println(getStructureName() + " defenses have been completely breached!");
        }
    }
    
    public void trainGarrison() {
        if (garrisonSize == 0) {
            System.out.println("No garrison to train at " + getStructureName());
            return;
        }
        
        System.out.println("Training garrison of " + garrisonSize + " at " + getStructureName());
        enhanceDefenses(garrisonSize / 10);
        enhanceMagicPower(5);
    }
    
    public void recruitSoldiers(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Recruitment count must be positive");
        }
        
        int newSize = Math.min(500, garrisonSize + count);
        int recruited = newSize - garrisonSize;
        setGarrisonSize(newSize);
        
        if (recruited > 0) {
            System.out.println("Recruited " + recruited + " soldiers at " + getStructureName());
            enhanceDefenses(recruited / 5);
        } else {
            System.out.println("Castle at maximum garrison capacity!");
        }
    }
    
    public boolean defendAgainstAttack(int attackPower) {
        int totalDefense = defenseRating + (garrisonSize * 2) + getMagicPower();
        
        System.out.println(getStructureName() + " defending with total power: " + totalDefense);
        System.out.println("Attack power: " + attackPower);
        
        if (totalDefense >= attackPower) {
            System.out.println(getStructureName() + " successfully defended!");
            return true;
        } else {
            System.out.println(getStructureName() + " was breached!");
            reduceDefenses(50);
            setGarrisonSize(Math.max(0, garrisonSize - 10));
            return false;
        }
    }

    // === Factory methods for castle types ===
    public static EnchantedCastle createWatchTower(String name, String location) {
        EnchantedCastle tower = new EnchantedCastle(name, location);
        tower.setDefenseRating(80);
        tower.setGarrisonSize(5);
        return tower;
    }
    
    public static EnchantedCastle createRoyalPalace(String name, String location, String king) {
        EnchantedCastle palace = new EnchantedCastle(name, location, king, "Royal Palace");
        palace.setDefenseRating(350);
        palace.setGarrisonSize(100);
        palace.enhanceMagicPower(200);
        return palace;
    }
    
    public static EnchantedCastle createMountainFortress(String name, String location, String commander) {
        EnchantedCastle fortress = new EnchantedCastle(name, location, commander, "Mountain Fortress");
        fortress.setDefenseRating(500);
        fortress.setGarrisonSize(150);
        fortress.setHasDrawbridge(true);
        return fortress;
    }
    
    public static EnchantedCastle createBorderKeep(String name, String location, String captain) {
        EnchantedCastle keep = new EnchantedCastle(name, location, captain, "Border Keep");
        keep.setDefenseRating(200);
        keep.setGarrisonSize(75);
        keep.setHasDrawbridge(true);
        return keep;
    }

    // === Utility methods ===
    public boolean isWellDefended() {
        return defenseRating >= 200 && garrisonSize >= 20;
    }
    
    public int getTotalDefensivePower() {
        return defenseRating + (garrisonSize * 2) + getMagicPower();
    }
    
    public String getDefenseStatus() {
        if (defenseRating >= 400) return "Impregnable";
        if (defenseRating >= 250) return "Strong";
        if (defenseRating >= 100) return "Moderate";
        if (defenseRating >= 50) return "Weak";
        return "Defenseless";
    }

    // === Override methods ===
    @Override
    public String getStructureInfo() {
        return String.format("EnchantedCastle: %s (%s, Lord: %s, Defense: %d, Garrison: %d)", 
            getStructureName(), castleType, currentLord, defenseRating, garrisonSize);
    }

    @Override
    public String toString() {
        return "EnchantedCastle{" +
                "name='" + getStructureName() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", type='" + castleType + '\'' +
                ", lord='" + currentLord + '\'' +
                ", defense=" + defenseRating +
                ", garrison=" + garrisonSize +
                ", drawbridge=" + hasDrawbridge +
                ", active=" + isActive() +
                '}';
    }
}