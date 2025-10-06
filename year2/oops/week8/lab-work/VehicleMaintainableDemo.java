// Abstract class Vehicle
abstract class Vehicle {
    protected int speed;
    protected String fuelType;

    public Vehicle(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    // Abstract method to be implemented by subclasses
    public abstract void startEngine();
}

// Interface Maintainable
interface Maintainable {
    void serviceInfo();
}

// Car class extending Vehicle and implementing Maintainable
class Car extends Vehicle implements Maintainable {
    private String model;

    public Car(int speed, String fuelType, String model) {
        super(speed, fuelType);
        this.model = model;
    }

    @Override
    public void startEngine() {
        System.out.println("Starting engine of " + model + " car. Vroom!");
    }

    @Override
    public void serviceInfo() {
        System.out.println("Service Info: Regular maintenance every 10,000 km.");
    }

    public void showDetails() {
        System.out.println("Car Model: " + model);
        System.out.println("Speed: " + speed + " km/h");
        System.out.println("Fuel Type: " + fuelType);
    }
}

// Main class to test the implementation
public class VehicleMaintainableDemo {
    public static void main(String[] args) {
        Car myCar = new Car(180, "Petrol", "Sedan");
        myCar.startEngine();
        myCar.serviceInfo();
        myCar.showDetails();
    }
}
