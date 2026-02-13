// Fruit and Apple Classes
// Topic: Basic Single Inheritance
// Problem Statement:
// Create a Fruit class with color and taste fields. Create an Apple class that extends
// Fruit and adds variety field.
// Hints:
// ● Use extends keyword for inheritance
// ● Make fields protected in parent class
// ● Test by creating Apple object and accessing inherited fields


// Parent class
class Fruit {
    protected String color;
    protected String taste;

    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    public void displayFruitInfo() {
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
    }
}

// Child class
class Apple extends Fruit {
    private String variety;

    public Apple(String color, String taste, String variety) {
        super(color, taste); // Call parent constructor
        this.variety = variety;
    }

    public void displayAppleInfo() {
        displayFruitInfo(); // Access inherited method
        System.out.println("Variety: " + variety);
    }
}

// Test class
public class AppleInheritanceDemo {
    public static void main(String[] args) {
        Apple myApple = new Apple("Red", "Sweet", "Fuji");
        myApple.displayAppleInfo();
    }
}
