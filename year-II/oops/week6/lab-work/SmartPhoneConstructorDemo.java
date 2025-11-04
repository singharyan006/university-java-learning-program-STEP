// Phone and SmartPhone
// Constructors
// Topic: Constructor Chaining with super()
// Problem Statement:
// Create Phone class with brand and model. Create SmartPhone class extending Phone with
// operatingSystem field. Use constructor chaining.
// Hints:
// ● Add print statements in constructors to see execution order
// ● Use super() in child constructor
// ● Create objects using different constructor combinations


// Parent class
class Phone {
    protected String brand;
    protected String model;

    // Constructor
    public Phone(String brand, String model) {
        System.out.println("Phone constructor called");
        this.brand = brand;
        this.model = model;
    }

    public void displayPhoneInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
    }
}

// Child class
class SmartPhone extends Phone {
    private String operatingSystem;

    // Constructor chaining with super()
    public SmartPhone(String brand, String model, String operatingSystem) {
        super(brand, model); // Call parent constructor
        System.out.println("SmartPhone constructor called");
        this.operatingSystem = operatingSystem;
    }

    public void displaySmartPhoneInfo() {
        displayPhoneInfo(); // Inherited method
        System.out.println("Operating System: " + operatingSystem);
    }
}

// Test class
public class SmartPhoneConstructorDemo {
    public static void main(String[] args) {
        System.out.println("Creating SmartPhone object...");
        SmartPhone myPhone = new SmartPhone("Samsung", "Galaxy S21", "Android");
        myPhone.displaySmartPhoneInfo();
    }
}
