package model;

public class RobotPet {
    private boolean needsCharging;
    private int batteryLevel;
    private final VirtualPet corePet;

    public RobotPet(boolean needsCharging, int batteryLevel, VirtualPet corePet) {
        this.needsCharging = needsCharging;
        this.batteryLevel = batteryLevel;
        this.corePet = corePet;
    }

    public boolean isNeedsCharging() { return needsCharging; }
    public void setNeedsCharging(boolean needsCharging) { this.needsCharging = needsCharging; }

    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel < 0 || batteryLevel > 100) throw new IllegalArgumentException("Battery out of range");
        this.batteryLevel = batteryLevel;
    }

    public VirtualPet getCorePet() { return corePet; }

    @Override
    public String toString() {
        return "RobotPet{" + "batteryLevel=" + batteryLevel + ", needsCharging=" + needsCharging + "}";
    }
}
