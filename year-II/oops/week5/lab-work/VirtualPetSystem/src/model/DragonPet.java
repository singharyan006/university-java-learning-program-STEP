package model;

public class DragonPet extends VirtualPet {
    private final String dragonType;
    private final String breathWeapon;
    private int firepower;
    private boolean canFly;

    // Default constructor
    public DragonPet() {
        super("Dragon", new PetSpecies("Dragon", new String[]{"Egg", "Hatchling", "Wyrmling", "Adult", "Ancient"}, 5000, "Mountain"));
        this.dragonType = "Fire Dragon";
        this.breathWeapon = "Fire Breath";
        this.firepower = 50;
        this.canFly = false;
    }

    public DragonPet(String petName, String dragonType, String breathWeapon) {
        super(petName, new PetSpecies("Dragon", new String[]{"Egg", "Hatchling", "Wyrmling", "Adult", "Ancient"}, 5000, "Mountain"));
        this.dragonType = dragonType;
        this.breathWeapon = breathWeapon;
        this.firepower = calculateInitialFirepower(dragonType);
        this.canFly = false; // Dragons learn to fly as they grow
    }

    // Dragon-specific getters
    public String getDragonType() { return dragonType; }
    public String getBreathWeapon() { return breathWeapon; }
    public int getFirepower() { return firepower; }
    public boolean canFly() { return canFly; }

    // Dragon-specific behaviors
    public void breatheFire() {
        if (getHealth() > 30) {
            System.out.println(getPetName() + " breathes " + breathWeapon + " with power " + firepower + "!");
            // Breathing fire consumes energy
            setHealth(getHealth() - 10);
            // But increases happiness if successful
            setHappiness(Math.min(100, getHappiness() + 15));
        } else {
            System.out.println(getPetName() + " is too weak to breathe fire!");
        }
    }

    public void learnToFly() {
        if (getAge() >= 100 && !canFly) {
            canFly = true;
            System.out.println(getPetName() + " has learned to fly!");
            setHappiness(Math.min(100, getHappiness() + 30));
        } else if (canFly) {
            System.out.println(getPetName() + " already knows how to fly!");
        } else {
            System.out.println(getPetName() + " is too young to learn flying!");
        }
    }

    public void hoardTreasure() {
        System.out.println(getPetName() + " is hoarding treasure!");
        setHappiness(Math.min(100, getHappiness() + 20));
        firepower += 5; // Getting stronger from hoarding
    }

    // Override feeding behavior for dragons
    @Override
    public void feedPet(String foodType) {
        if (foodType.equals("Meat") || foodType.equals("Gold")) {
            super.feedPet(foodType);
            if (foodType.equals("Gold")) {
                firepower += 10; // Gold makes dragons more powerful
                System.out.println(getPetName() + "'s firepower increased!");
            }
        } else {
            System.out.println("Dragons prefer meat or gold!");
        }
    }

    // Override play behavior
    @Override
    public void playWithPet(String gameType) {
        if (gameType.equals("Flying") && canFly) {
            setHappiness(Math.min(100, getHappiness() + 25));
            System.out.println(getPetName() + " soars through the skies!");
        } else if (gameType.equals("Treasure Hunt")) {
            hoardTreasure();
        } else {
            super.playWithPet(gameType);
        }
    }

    private int calculateInitialFirepower(String type) {
        switch (type.toLowerCase()) {
            case "fire dragon": return 60;
            case "ice dragon": return 55;
            case "lightning dragon": return 70;
            default: return 50;
        }
    }

    @Override
    public String toString() {
        return "DragonPet{" + "name='" + getPetName() + "', type='" + dragonType + 
               "', weapon='" + breathWeapon + "', firepower=" + firepower + 
               ", canFly=" + canFly + "}";
    }
}
