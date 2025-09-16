import java.util.UUID;

public class Vehicle {
    // Protected fields for inheritance
    protected String brand;
    protected String model;
    protected int year;
    protected String engineType;

    // Private fields requiring getter/setter access
    private String registrationNumber;
    private boolean isRunning;

    // Default constructor
    public Vehicle() {
        this.brand = "Unknown";
        this.model = "Unknown";
        this.year = 0;
        this.engineType = "Unknown";
        this.registrationNumber = "N/A";
        this.isRunning = false;
        System.out.println("Vehicle default constructor called");
    }

    // Parameterized constructor
    public Vehicle(String brand, String model, int year, String engineType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.registrationNumber = UUID.randomUUID().toString();
        this.isRunning = false;
        System.out.println("Vehicle parameterized constructor called");
    }

    // Method to start the vehicle
    public void start() {
        this.isRunning = true;
        System.out.println("Vehicle started");
    }

    // Method to stop the vehicle
    public void stop() {
        this.isRunning = false;
        System.out.println("Vehicle stopped");
    }

    // Method to get vehicle information
    public String getVehicleInfo() {
        return "Brand: " + brand + ", Model: " + model + ", Year: " + year +
               ", Engine Type: " + engineType + ", Registration Number: " + registrationNumber +
               ", Is Running: " + isRunning;
    }

    // Method to display technical specifications
    public void displaySpecs() {
        System.out.println("--- Vehicle Specifications ---");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Engine Type: " + engineType);
    }

    // Getter for registrationNumber
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    // Setter for registrationNumber
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    // Getter for isRunning
    public boolean isRunning() {
        return isRunning;
    }
}
