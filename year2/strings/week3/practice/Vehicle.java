public class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;

    public Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }

    public void startVehicle() {
        System.out.println(make + " " + model + " started.");
    }

    public void stopVehicle() {
        System.out.println(make + " " + model + " stopped.");
    }

    public void refuel(double amount) {
        fuelLevel += amount;
    }

    public void displayVehicleInfo() {
        System.out.println("Make: " + make + ", Model: " + model +
                           ", Year: " + year + ", Fuel Level: " + fuelLevel);
    }

    public static void main(String[] args) {
        Vehicle[] vehicles = {
            new Vehicle("Toyota", "Corolla", 2019, 50),
            new Vehicle("Ford", "F-150", 2020, 70),
            new Vehicle("Yamaha", "R15", 2022, 30)
        };

        for (Vehicle v : vehicles) {
            v.startVehicle();
            v.refuel(10);
            v.displayVehicleInfo();
            v.stopVehicle();
        }

        // Reusability: Same Vehicle class used for different types.
        // Extensibility: Can extend Vehicle to create Car, Truck, etc.
        // Benefit: Avoids code duplication, promotes maintainability.
    }
}
