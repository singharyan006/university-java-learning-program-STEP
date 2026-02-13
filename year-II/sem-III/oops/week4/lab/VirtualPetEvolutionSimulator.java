// Virtual Pet Evolution Simulator 
// Topics Covered: Constructor Overloading, this() Chaining, final Keyword, static Usage 
// Theme: Create a Tamagotchi-style virtual pet that evolves based on care! 
// Requirements: Design a VirtualPet class that simulates pet evolution through different life 
// stages. 
// Hints: a. Create VirtualPet class with fields: 
// ● final String petId (generated using UUID-like system) 
// ● String petName, String species, int age, int happiness, int health 
// ● static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", 
// "Teen", "Adult", "Elder"} 
// ● static int totalPetsCreated 
// b. Implement evolution-based constructors: 
// ● Default constructor: Creates a mysterious egg with random species 
// ● Constructor with name only: Pet starts as baby stage 
// ● Constructor with name and species: Pet starts as child stage 
// ● Full constructor: Specify all initial stats and stage 
// c. Use this() chaining where all constructors eventually call the main constructor 
// d. Create unique methods: 
// ● evolvePet(): Changes evolution stage based on age and care 
// ● feedPet(), playWithPet(), healPet(): Affect happiness and health 
// ● simulateDay(): Ages pet and randomly affects stats 
// 3 
// ● getPetStatus(): Returns current evolution stage 
// ● static generatePetId(): Creates unique IDs 
// e. Twist: Pet dies if health reaches 0, becomes "Ghost" type that can't evolve but can haunt 
// other pets! 
// f. In main method: Create a pet daycare with multiple pets, simulate several days, show 
// evolution progress 



import java.util.*;

class VirtualPet {
    // Static and final fields
    private static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    private static final Random rand = new Random();
    private static int totalPetsCreated = 0;

    // Instance fields
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private int stageIndex;
    private boolean isGhost;

    // Default constructor
    public VirtualPet() {
        this("Mystery", getRandomSpecies(), 0, 50, 50, 0);
    }

    // Constructor with name only
    public VirtualPet(String petName) {
        this(petName, getRandomSpecies(), 0, 60, 60, 1);
    }

    // Constructor with name and species
    public VirtualPet(String petName, String species) {
        this(petName, species, 1, 70, 70, 2);
    }

    // Full constructor
    public VirtualPet(String petName, String species, int age, int happiness, int health, int stageIndex) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.stageIndex = stageIndex;
        this.isGhost = false;
        totalPetsCreated++;
    }

    // Static method to generate unique pet ID
    public static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    // Random species generator
    private static String getRandomSpecies() {
        String[] speciesList = {"Dragon", "Cat", "Alien", "Fox", "Penguin"};
        return speciesList[rand.nextInt(speciesList.length)];
    }

    // Pet actions
    public void feedPet() {
        if (!isGhost) happiness += 10;
    }

    public void playWithPet() {
        if (!isGhost) happiness += 15;
    }

    public void healPet() {
        if (!isGhost) health += 20;
    }

    public void simulateDay() {
        if (isGhost) return;

        age++;
        happiness -= rand.nextInt(10);
        health -= rand.nextInt(15);

        if (health <= 0) {
            becomeGhost();
        } else {
            evolvePet();
        }
    }

    public void evolvePet() {
        if (isGhost) return;

        if (age > stageIndex * 2 && happiness > 40 && health > 30 && stageIndex < EVOLUTION_STAGES.length - 1) {
            stageIndex++;
        }
    }

    public String getPetStatus() {
        return isGhost ? "Ghost" : EVOLUTION_STAGES[stageIndex];
    }

    private void becomeGhost() {
        isGhost = true;
        species = "Ghost";
        happiness = 0;
        health = 0;
    }

    public void displayPet() {
        System.out.printf("🐾 PetID: %s | Name: %s | Species: %s | Age: %d | Happiness: %d | Health: %d | Stage: %s%n",
                petId, petName, species, age, happiness, health, getPetStatus());
    }

    public static int getTotalPetsCreated() {
        return totalPetsCreated;
    }
}

public class VirtualPetEvolutionSimulator {
    public static void main(String[] args) {
        List<VirtualPet> daycare = new ArrayList<>();
        daycare.add(new VirtualPet());
        daycare.add(new VirtualPet("Fluffy"));
        daycare.add(new VirtualPet("Bolt", "Fox"));
        daycare.add(new VirtualPet("Zara", "Dragon", 3, 80, 90, 3));

        for (int day = 1; day <= 5; day++) {
            System.out.println("\n🌞 Day " + day + " Simulation:");
            for (VirtualPet pet : daycare) {
                pet.simulateDay();
                pet.displayPet();
            }
        }

        System.out.println("\nTotal pets created: " + VirtualPet.getTotalPetsCreated());
    }
}
