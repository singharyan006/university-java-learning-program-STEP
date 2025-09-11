package model;

public class Main {
    public static void main(String[] args) {
        PetSpecies dragonSpecies = new PetSpecies(
            "Dragon",
            new String[]{"Egg", "Hatchling", "Wyrmling", "Adult"},
            5000,
            "Volcano"
        );

        VirtualPet myPet = new VirtualPet("Draco", dragonSpecies);
        System.out.println(myPet);

        myPet.feedPet("Fruit");
        myPet.playWithPet("Fetch");

        System.out.println("After interaction:");
        System.out.println(myPet.getInternalState());
    }
}
