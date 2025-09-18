// Color Hierarchy Chain
// Topic: Multilevel Inheritance
// Problem Statement:
// Create inheritance chain: Color → PrimaryColor → RedColor. Each class adds specific
// properties and methods.
// Hints:
// ● Color has name field
// ● PrimaryColor adds intensity field
// ● RedColor adds shade field
// ● Show constructor chaining through all levels



// Base class
class Color {
    protected String name;

    public Color(String name) {
        this.name = name;
        System.out.println("Color constructor called");
    }

    public void displayColor() {
        System.out.println("Color Name: " + name);
    }
}

// Intermediate class
class PrimaryColor extends Color {
    protected int intensity;

    public PrimaryColor(String name, int intensity) {
        super(name); // Call Color constructor
        this.intensity = intensity;
        System.out.println("PrimaryColor constructor called");
    }

    public void displayPrimaryColor() {
        displayColor();
        System.out.println("Intensity: " + intensity);
    }
}

// Derived class
class RedColor extends PrimaryColor {
    private String shade;

    public RedColor(String name, int intensity, String shade) {
        super(name, intensity); // Call PrimaryColor constructor
        this.shade = shade;
        System.out.println("RedColor constructor called");
    }

    public void displayRedColor() {
        displayPrimaryColor();
        System.out.println("Shade: " + shade);
    }
}

// Test class
public class ColorHierarchyChainDemo {
    public static void main(String[] args) {
        System.out.println("Creating RedColor object...");
        RedColor red = new RedColor("Red", 85, "Crimson");
        red.displayRedColor();
    }
}
