// Abstract class Vehicle
abstract class Vehicle {
    // Abstract method
    public abstract void start();

    // Concrete method
    public void stop() {
        System.out.println("Vehicle stopped.");
    }
}

// Interface Fuel
interface Fuel {
    void refuel();
}

// Car class extending Vehicle and implementing Fuel
class Car extends Vehicle implements Fuel {
    private String model;

    public Car(String model) {
        this.model = model;
    }

    @Override
    public void start() {
        System.out.println(model + " is starting...");
    }

    @Override
    public void refuel() {
        System.out.println(model + " is refueling with petrol.");
    }

    public void showDetails() {
        System.out.println("Car Model: " + model);
        start();
        refuel();
        stop();
    }
}

// Main class to test the implementation
public class VehicleFuelDemo {
    public static void main(String[] args) {
        Car myCar = new Car("Swift");
        myCar.showDetails();
    }
}
