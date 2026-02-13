import java.util.*;

/**
 * Immutable configuration class for Medieval Kingdom Management
 * Demonstrates final class, defensive copying, and factory methods
 */
public final class KingdomConfig {
    // All fields are final for immutability
    private final String kingdomName;
    private final int foundingYear;
    private final String[] allowedStructureTypes;
    private final Map<String, Integer> resourceLimits;

    // Main constructor with full validation
    public KingdomConfig(String kingdomName, int foundingYear, 
                        String[] allowedStructureTypes, Map<String, Integer> resourceLimits) {
        // Validation
        if (kingdomName == null || kingdomName.trim().isEmpty()) {
            throw new IllegalArgumentException("Kingdom name cannot be null or empty");
        }
        if (foundingYear < 0 || foundingYear > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new IllegalArgumentException("Invalid founding year");
        }
        if (allowedStructureTypes == null || allowedStructureTypes.length == 0) {
            throw new IllegalArgumentException("Must have at least one allowed structure type");
        }
        if (resourceLimits == null || resourceLimits.isEmpty()) {
            throw new IllegalArgumentException("Resource limits cannot be null or empty");
        }

        // Defensive copying for immutability
        this.kingdomName = kingdomName.trim();
        this.foundingYear = foundingYear;
        this.allowedStructureTypes = Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length);
        this.resourceLimits = new HashMap<>(resourceLimits);
        
        // Validate structure types
        for (String type : this.allowedStructureTypes) {
            if (type == null || type.trim().isEmpty()) {
                throw new IllegalArgumentException("Structure type cannot be null or empty");
            }
        }
        
        // Validate resource limits
        for (Map.Entry<String, Integer> entry : this.resourceLimits.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null || entry.getValue() < 0) {
                throw new IllegalArgumentException("Invalid resource limit entry");
            }
        }
    }

    // Getters only (no setters for immutability)
    public String getKingdomName() {
        return kingdomName;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    // Return clones for mutable references
    public String[] getAllowedStructureTypes() {
        return Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length);
    }

    public Map<String, Integer> getResourceLimits() {
        return new HashMap<>(resourceLimits);
    }

    // Factory method: createDefaultKingdom()
    public static KingdomConfig createDefaultKingdom() {
        String[] defaultStructures = {"WizardTower", "EnchantedCastle", "MysticLibrary", "DragonLair"};
        Map<String, Integer> defaultResources = new HashMap<>();
        defaultResources.put("Magic", 1000);
        defaultResources.put("Gold", 5000);
        defaultResources.put("Mana", 2000);
        defaultResources.put("Crystals", 500);
        
        return new KingdomConfig("Avalon", 1200, defaultStructures, defaultResources);
    }

    // Factory method: createFromTemplate(String type)
    public static KingdomConfig createFromTemplate(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Template type cannot be null");
        }
        
        switch (type.toLowerCase()) {
            case "magical":
                return createMagicalKingdom();
            case "defensive":
                return createDefensiveKingdom();
            case "scholarly":
                return createScholarlyKingdom();
            case "dragon":
                return createDragonKingdom();
            default:
                throw new IllegalArgumentException("Unknown template type: " + type);
        }
    }

    private static KingdomConfig createMagicalKingdom() {
        String[] structures = {"WizardTower", "MysticLibrary"};
        Map<String, Integer> resources = new HashMap<>();
        resources.put("Magic", 2000);
        resources.put("Mana", 3000);
        resources.put("Scrolls", 1000);
        
        return new KingdomConfig("Mystrallia", 800, structures, resources);
    }

    private static KingdomConfig createDefensiveKingdom() {
        String[] structures = {"EnchantedCastle", "WizardTower"};
        Map<String, Integer> resources = new HashMap<>();
        resources.put("Stone", 5000);
        resources.put("Iron", 3000);
        resources.put("Magic", 1000);
        
        return new KingdomConfig("Fortressia", 1000, structures, resources);
    }

    private static KingdomConfig createScholarlyKingdom() {
        String[] structures = {"MysticLibrary", "WizardTower"};
        Map<String, Integer> resources = new HashMap<>();
        resources.put("Books", 10000);
        resources.put("Scrolls", 5000);
        resources.put("Knowledge", 3000);
        
        return new KingdomConfig("Scholaria", 600, structures, resources);
    }

    private static KingdomConfig createDragonKingdom() {
        String[] structures = {"DragonLair", "EnchantedCastle"};
        Map<String, Integer> resources = new HashMap<>();
        resources.put("Gold", 10000);
        resources.put("Treasure", 5000);
        resources.put("Territory", 2000);
        
        return new KingdomConfig("Draconum", 1500, structures, resources);
    }

    // Standard methods
    @Override
    public String toString() {
        return "KingdomConfig{" +
                "kingdomName='" + kingdomName + '\'' +
                ", foundingYear=" + foundingYear +
                ", allowedStructureTypes=" + Arrays.toString(allowedStructureTypes) +
                ", resourceLimits=" + resourceLimits +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof KingdomConfig)) return false;
        
        KingdomConfig other = (KingdomConfig) obj;
        return foundingYear == other.foundingYear &&
               Objects.equals(kingdomName, other.kingdomName) &&
               Arrays.equals(allowedStructureTypes, other.allowedStructureTypes) &&
               Objects.equals(resourceLimits, other.resourceLimits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(kingdomName, foundingYear, resourceLimits);
        result = 31 * result + Arrays.hashCode(allowedStructureTypes);
        return result;
    }
}