// HW PROBLEM 1: Light and LED Multiple Constructors
// Topic: Constructor Chaining with this() and super()
// Problem Statement:
// Create Light class with multiple constructors using this(). Create LED class with
// constructors using both this() and super().
// Hints:
// ● Chain constructors using this() in same class
// ● Chain to parent using super() from child class
// ● Add print statements to trace constructor calls


public class LightLEDTest {

    // Light class with constructor chaining using this()
    static class Light {
        private int brightness;
        private String color;

        public Light() {
            this(100); // Default brightness
            System.out.println("Light(): Default constructor called");
        }

        public Light(int brightness) {
            this(brightness, "White"); // Default color
            System.out.println("Light(int): Constructor with brightness called");
        }

        public Light(int brightness, String color) {
            this.brightness = brightness;
            this.color = color;
            System.out.println("Light(int, String): Constructor with brightness and color called");
        }
    }

    // LED class extending Light, using both this() and super()
    static class LED extends Light {
        private boolean isSmart;

        public LED() {
            this(false); // Default smart feature
            System.out.println("LED(): Default constructor called");
        }

        public LED(boolean isSmart) {
            super(); // Call Light's default constructor
            this.isSmart = isSmart;
            System.out.println("LED(boolean): Constructor with smart feature called");
        }

        public LED(int brightness, String color, boolean isSmart) {
            super(brightness, color); // Call Light's parameterized constructor
            this.isSmart = isSmart;
            System.out.println("LED(int, String, boolean): Full constructor called");
        }
    }

    // Main method to test constructor chaining
    public static void main(String[] args) {
        System.out.println("Creating Light with default constructor:");
        Light light1 = new Light();

        System.out.println("\nCreating Light with brightness only:");
        Light light2 = new Light(75);

        System.out.println("\nCreating Light with brightness and color:");
        Light light3 = new Light(60, "Blue");

        System.out.println("\nCreating LED with default constructor:");
        LED led1 = new LED();

        System.out.println("\nCreating LED with smart feature:");
        LED led2 = new LED(true);

        System.out.println("\nCreating LED with full parameters:");
        LED led3 = new LED(80, "Red", true);
    }
}
