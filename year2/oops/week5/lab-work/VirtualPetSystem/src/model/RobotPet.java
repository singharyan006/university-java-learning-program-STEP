package model;

public class RobotPet extends VirtualPet {
    private boolean needsCharging;
    private int batteryLevel;

    // Default constructor
    public RobotPet() {
        super("Robot Pet", new PetSpecies("Robot", new String[]{"Assembly", "Boot", "Active", "Advanced"}, 10000, "Laboratory"));
        this.needsCharging = false;
        this.batteryLevel = 100;
    }

    public RobotPet(String petName, PetSpecies species, boolean needsCharging, int batteryLevel) {
        super(petName, species);
        this.needsCharging = needsCharging;
        setBatteryLevel(batteryLevel);
    }

    // Robot-specific getters and setters
    public boolean isNeedsCharging() { return needsCharging; }
    public void setNeedsCharging(boolean needsCharging) { this.needsCharging = needsCharging; }

    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel < 0 || batteryLevel > 100) throw new IllegalArgumentException("Battery out of range");
        this.batteryLevel = batteryLevel;
        this.needsCharging = batteryLevel < 20; // Auto-set charging need
    }

    // Robot-specific behaviors
    public void charge() {
        if (batteryLevel < 100) {
            batteryLevel = Math.min(100, batteryLevel + 25);
            if (batteryLevel >= 20) {
                needsCharging = false;
            }
            System.out.println(getPetName() + " is charging. Battery: " + batteryLevel + "%");
        }
    }

    public void performDiagnostics() {
        System.out.println("Running diagnostics on " + getPetName());
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Charging needed: " + needsCharging);
        System.out.println("Health: " + getHealth() + "%");
    }

    // Override feeding behavior for robots
    @Override
    public void feedPet(String foodType) {
        if (foodType.equals("Electricity")) {
            charge();
        } else {
            System.out.println("Robots don't eat " + foodType + ". Try charging instead!");
        }
    }

    // Override play behavior to consume battery
    @Override
    public void playWithPet(String gameType) {
        if (batteryLevel > 10) {
            super.playWithPet(gameType);
            setBatteryLevel(batteryLevel - 5); // Playing consumes battery
        } else {
            System.out.println(getPetName() + " is too low on battery to play!");
        }
    }

    @Override
    public String toString() {
        return "RobotPet{" + "name='" + getPetName() + "', batteryLevel=" + batteryLevel + 
               ", needsCharging=" + needsCharging + ", happiness=" + getHappiness() + "}";
    }
}
