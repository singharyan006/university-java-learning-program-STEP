package model;

import java.util.UUID;

public class VirtualPet {
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    private String petName;
    private int age;
    private int happiness;
    private int health;

    protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Egg", "Baby", "Teen", "Adult"};
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;
    public static final String PET_SYSTEM_VERSION = "2.0";

    // Default constructor
    public VirtualPet() {
        this("Unnamed", new PetSpecies("DefaultSpecies", DEFAULT_EVOLUTION_STAGES, 1000, "Forest"), 0, 50, 50);
    }

    public VirtualPet(String petName) {
        this(petName, new PetSpecies("DefaultSpecies", DEFAULT_EVOLUTION_STAGES, 1000, "Forest"), 0, 50, 50);
    }

    public VirtualPet(String petName, PetSpecies species) {
        this(petName, species, 0, 50, 50);
    }

    public VirtualPet(String petName, PetSpecies species, int age, int happiness, int health) {
        validateStat(happiness);
        validateStat(health);
        this.petId = generatePetId();
        this.species = species;
        this.birthTimestamp = System.currentTimeMillis();
        this.petName = petName;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
    }

    // JavaBean Getters/Setters
    public String getPetId() { return petId; }
    public PetSpecies getSpecies() { return species; }
    public long getBirthTimestamp() { return birthTimestamp; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { validateStat(happiness); this.happiness = happiness; }
    public int getHealth() { return health; }
    public void setHealth(int health) { validateStat(health); this.health = health; }

    public void feedPet(String foodType) {
        modifyHealth(calculateFoodBonus(foodType));
    }

    public void playWithPet(String gameType) {
        modifyHappiness(calculateGameEffect(gameType));
    }

    protected int calculateFoodBonus(String foodType) {
        return foodType.equals("Fruit") ? 10 : 5;
    }

    protected int calculateGameEffect(String gameType) {
        return gameType.equals("Fetch") ? 15 : 8;
    }

    private void modifyHappiness(int delta) {
        happiness = Math.min(MAX_HAPPINESS, happiness + delta);
        checkEvolution();
    }

    private void modifyHealth(int delta) {
        health = Math.min(MAX_HEALTH, health + delta);
    }

    private void updateEvolutionStage() {
        String[] stages = species.getEvolutionStages();
        int currentStageIndex = age / 25; // Evolution every 25 age units
        
        if (currentStageIndex >= stages.length) {
            currentStageIndex = stages.length - 1; // Max stage
        }
        
        String newStage = stages[currentStageIndex];
        System.out.println(petName + " is now in stage: " + newStage);
    }

    public String getInternalState() {
        return "Pet[" + petId + "] Name: " + petName + ", Age: " + age + ", Health: " + health + ", Happiness: " + happiness;
    }

    private void validateStat(int stat) {
        if (stat < 0 || stat > 100) throw new IllegalArgumentException("Stat out of range");
    }

    private String generatePetId() {
        return UUID.randomUUID().toString();
    }

    private void checkEvolution() {
        // Check if pet should evolve based on age and happiness
        if (happiness >= 80 && age % 25 == 0 && age > 0) {
            updateEvolutionStage();
            // Bonus stats for evolution
            health = Math.min(MAX_HEALTH, health + 10);
            System.out.println(petName + " gained health from evolution!");
        }
    }

    @Override
    public String toString() {
        return "VirtualPet{" + "petName='" + petName + '\'' + ", species=" + species + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VirtualPet)) return false;
        VirtualPet other = (VirtualPet) obj;
        return petId.equals(other.petId);
    }

    @Override
    public int hashCode() {
        return petId.hashCode();
    }
}
