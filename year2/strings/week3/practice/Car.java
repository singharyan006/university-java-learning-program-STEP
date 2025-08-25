public class Car {
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning;

    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false;
    }

    public void startEngine() {
        isRunning = true;
        System.out.println(brand + " " + model + " engine started.");
    }

    public void stopEngine() {
        isRunning = false;
        System.out.println(brand + " " + model + " engine stopped.");
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Model: " + model + ", Year: " + year +
                           ", Color: " + color + ", Running: " + isRunning);
    }

    public int getAge() {
        return 2025 - year;
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Camry", 2018, "Red");
        Car car2 = new Car("Honda", "Civic", 2020, "Blue");
        Car car3 = new Car("Ford", "Mustang", 2015, "Black");

        car1.startEngine();
        car2.stopEngine();
        car3.displayInfo();

        System.out.println("Age of car1: " + car1.getAge());

        // Each car object maintains its own state, just like real cars do.
    }
}
