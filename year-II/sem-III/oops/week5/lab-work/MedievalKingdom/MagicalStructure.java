import java.util.Objects;
import java.util.UUID;

/**
 * Base MagicalStructure class demonstrating constructor chaining,
 * access modifiers, and immutable identity with controlled state
 */
public class MagicalStructure {
    // === Immutable identity ===
    private final String structureId;
    private final long constructionTimestamp;
    
    // === Immutable properties ===
    private final String structureName;
    private final String location;
    
    // === Controlled state ===
    private int magicPower;
    private boolean isActive;
    private String currentMaintainer;
    
    // === Package constants ===
    static final int MIN_MAGIC_POWER = 0;
    static final int MAX_MAGIC_POWER = 1000;
    
    // === Global constant ===
    public static final String MAGIC_SYSTEM_VERSION = "3.0";

    // === Constructor chaining ===
    
    // Basic constructor
    public MagicalStructure(String name, String location) {
        this(name, location, 100); // Default power of 100
    }
    
    // Constructor with power
    public MagicalStructure(String name, String location, int power) {
        this(name, location, power, true); // Default active state
    }
    
    // Main constructor - all others chain to this
    public MagicalStructure(String name, String location, int power, boolean active) {
        // Validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Structure name cannot be null or empty");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (power < MIN_MAGIC_POWER || power > MAX_MAGIC_POWER) {
            throw new IllegalArgumentException("Magic power must be between " + 
                MIN_MAGIC_POWER + " and " + MAX_MAGIC_POWER);
        }
        
        // Set immutable identity
        this.structureId = UUID.randomUUID().toString();
        this.constructionTimestamp = System.currentTimeMillis();
        
        // Set immutable properties
        this.structureName = name.trim();
        this.location = location.trim();
        
        // Set controlled state
        this.magicPower = power;
        this.isActive = active;
        this.currentMaintainer = "Unknown";
    }

    // === Getters for immutable fields ===
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

    // === JavaBean getters/setters for controlled state ===
    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        if (magicPower < MIN_MAGIC_POWER || magicPower > MAX_MAGIC_POWER) {
            throw new IllegalArgumentException("Magic power must be between " + 
                MIN_MAGIC_POWER + " and " + MAX_MAGIC_POWER);
        }
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
        if (currentMaintainer == null || currentMaintainer.trim().isEmpty()) {
            throw new IllegalArgumentException("Maintainer cannot be null or empty");
        }
        this.currentMaintainer = currentMaintainer.trim();
    }

    // === Utility methods ===
    public void activateStructure() {
        this.isActive = true;
        System.out.println(structureName + " has been activated!");
    }
    
    public void deactivateStructure() {
        this.isActive = false;
        System.out.println(structureName + " has been deactivated.");
    }
    
    public void enhanceMagicPower(int enhancement) {
        int newPower = Math.min(MAX_MAGIC_POWER, magicPower + enhancement);
        setMagicPower(newPower);
        System.out.println(structureName + " magic power enhanced to " + magicPower);
    }
    
    public void drainMagicPower(int drain) {
        int newPower = Math.max(MIN_MAGIC_POWER, magicPower - drain);
        setMagicPower(newPower);
        System.out.println(structureName + " magic power drained to " + magicPower);
    }
    
    public long getAge() {
        return System.currentTimeMillis() - constructionTimestamp;
    }
    
    public String getStructureInfo() {
        return String.format("Structure: %s at %s (Power: %d, Active: %s, Maintainer: %s)",
            structureName, location, magicPower, isActive, currentMaintainer);
    }

    // === Standard methods ===
    @Override
    public String toString() {
        return "MagicalStructure{" +
                "id='" + structureId.substring(0, 8) + "...'" +
                ", name='" + structureName + '\'' +
                ", location='" + location + '\'' +
                ", magicPower=" + magicPower +
                ", isActive=" + isActive +
                ", maintainer='" + currentMaintainer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MagicalStructure)) return false;
        
        MagicalStructure other = (MagicalStructure) obj;
        return Objects.equals(structureId, other.structureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureId);
    }
}