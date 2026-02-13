package main;

import model.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== VIRTUAL PET SYSTEM DEMO ===\n");

        // Create different types of pets
        PetSpecies dragonSpecies = new PetSpecies(
            "Dragon",
            new String[]{"Egg", "Hatchling", "Wyrmling", "Adult", "Ancient"},
            5000,
            "Volcano"
        );

        // Test VirtualPet
        System.out.println("1. Basic Virtual Pet:");
        VirtualPet myPet = new VirtualPet("Buddy", dragonSpecies);
        System.out.println(myPet);
        myPet.feedPet("Fruit");
        myPet.playWithPet("Fetch");
        System.out.println(myPet.getInternalState());
        System.out.println();

        // Test DragonPet
        System.out.println("2. Dragon Pet:");
        DragonPet dragon = new DragonPet("Draco", "Fire Dragon", "Fire Breath");
        System.out.println(dragon);
        dragon.feedPet("Meat");
        dragon.breatheFire();
        dragon.playWithPet("Treasure Hunt");
        dragon.setAge(100);
        dragon.learnToFly();
        dragon.playWithPet("Flying");
        System.out.println(dragon.getInternalState());
        System.out.println();

        // Test RobotPet
        System.out.println("3. Robot Pet:");
        RobotPet robot = new RobotPet("R2-D2", 
            new PetSpecies("Robot", new String[]{"Assembly", "Boot", "Active", "Advanced"}, 10000, "Laboratory"), 
            false, 75);
        System.out.println(robot);
        robot.playWithPet("Logic Games");
        robot.performDiagnostics();
        robot.feedPet("Electricity");
        robot.playWithPet("Logic Games");
        robot.performDiagnostics();
        System.out.println(robot.getInternalState());
        System.out.println();

        // Test evolution with proper progression
        System.out.println("4. Evolution Test:");
        VirtualPet evolvingPet = new VirtualPet("Evolvo");
        System.out.println("Initial state: " + evolvingPet.getInternalState());
        
        // Build up happiness first
        evolvingPet.setHappiness(85);
        System.out.println("After happiness boost: " + evolvingPet.getInternalState());
        
        // Age the pet to trigger evolution
        for (int i = 1; i <= 25; i++) {
            evolvingPet.setAge(i);
            if (i == 25) {
                evolvingPet.playWithPet("Fetch"); // This will trigger checkEvolution at age 25
            }
        }
        System.out.println("Final evolved state: " + evolvingPet.getInternalState());
        
        // Test polymorphism with array of different pets
        System.out.println("\n5. Polymorphism Test:");
        VirtualPet[] pets = {
            new VirtualPet("Basic Pet"),
            new DragonPet("Flame", "Fire Dragon", "Flame Breath"),
            new RobotPet("Robo", new PetSpecies("Robot", new String[]{"Boot", "Active"}, 5000, "Lab"), false, 90)
        };
        
        System.out.println("Feeding all pets the same food:");
        for (VirtualPet pet : pets) {
            System.out.println("Feeding " + pet.getPetName() + ":");
            pet.feedPet("Meat"); // Each pet handles this differently
            System.out.println(pet.getInternalState());
            System.out.println();
        }

        System.out.println("\n=== DEMO COMPLETE ===");
    }
}