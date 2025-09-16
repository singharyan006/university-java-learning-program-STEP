public class Car extends Vehicle {
    // Car-specific fields
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;

    // Default constructor
    public Car() {
        super(); // Explicit call to parent default constructor
        this.numberOfDoors = 4;
        this.fuelType = "Gasoline";
        this.transmissionType = "Automatic";
        System.out.println("Car default constructor called");
    }

    // Parameterized constructor
    public Car(String brand, String model, int year, String engineType, int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType); // Explicit call to parent parameterized constructor
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    // Override start() method
    @Override
    public void start() {
        super.start(); // Call parent's start method
        System.out.println("Car-specific startup sequence: Checking all systems.");
    }

    // Override displaySpecs() method
    @Override
    public void displaySpecs() {
        super.displaySpecs(); // Call parent's displaySpecs method
        System.out.println("--- Car Specific Specifications ---");
        System.out.println("Number of Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Transmission Type: " + transmissionType);
    }

    // Car-specific method
    public void openTrunk() {
        System.out.println("Trunk opened");
    }

    // Car-specific method
    public void playRadio() {
        System.out.println("Radio playing music");
    }

    public static void main(String[] args) {
        System.out.println("--- Testing Constructor Chaining (Default) ---");
        Car defaultCar = new Car();
        System.out.println("\n--- Testing Constructor Chaining (Parameterized) ---");
        Car customCar = new Car("Toyota", "Camry", 2023, "V6", 4, "Gasoline", "Automatic");

        System.out.println("\n--- Testing Inheritance of Fields and Methods ---");
        System.out.println("Accessing protected field from parent: " + customCar.brand);
        System.out.println("Calling inherited method from parent:");
        System.out.println(customCar.getVehicleInfo());

        System.out.println("\n--- Testing super Keyword and Method Overriding ---");
        System.out.println("Calling overridden start() method:");
        customCar.start();
        System.out.println("Is vehicle running? " + customCar.isRunning());
        System.out.println("\nCalling overridden displaySpecs() method:");
        customCar.displaySpecs();

        System.out.println("\n--- Testing Method Resolution ---");
        System.out.println("Calling method that only exists in Car:");
        customCar.openTrunk();
        customCar.playRadio();

        System.out.println("\n--- Polymorphic Behavior ---");
        Vehicle myVehicle = new Car("Honda", "Civic", 2022, "I4", 4, "Petrol", "CVT");
        myVehicle.start(); // Calls the overridden start() method in Car
        myVehicle.displaySpecs(); // Calls the overridden displaySpecs() method in Car
        // myVehicle.openTrunk(); // This would cause a compile error because openTrunk() is not in Vehicle

        System.out.println("\n--- Final State of customCar ---");
        customCar.stop();
        System.out.println(customCar.getVehicleInfo());
    }
}
