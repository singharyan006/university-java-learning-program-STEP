package model;

public class DragonPet {
    private final String dragonType;
    private final String breathWeapon;
    private final VirtualPet corePet;

    public DragonPet(String dragonType, String breathWeapon, VirtualPet corePet) {
        this.dragonType = dragonType;
        this.breathWeapon = breathWeapon;
        this.corePet = corePet;
    }

    public String getDragonType() { return dragonType; }
    public String getBreathWeapon() { return breathWeapon; }
    public VirtualPet getCorePet() { return corePet; }

    @Override
    public String toString() {
        return "DragonPet{" + "type='" + dragonType + "', weapon='" + breathWeapon + "'}";
    }
}
