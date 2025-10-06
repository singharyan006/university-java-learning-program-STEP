// Abstract class Fruit
abstract class Fruit {
    protected String color;
    protected String taste;

    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    // Abstract method to be implemented by subclasses
    public abstract void showDetails();
}

// Interface Edible
interface Edible {
    void nutrientsInfo();
}

// Apple class extending Fruit and implementing Edible
class Apple extends Fruit implements Edible {
    private String variety;

    public Apple(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    @Override
    public void showDetails() {
        System.out.println("Apple Variety: " + variety);
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
    }

    @Override
    public void nutrientsInfo() {
        System.out.println("Nutrients: Rich in fiber, Vitamin C, and antioxidants.");
    }
}

// Main class to test the implementation
public class FruitEdibleDemo {
    public static void main(String[] args) {
        Apple myApple = new Apple("Red", "Sweet", "Fuji");
        myApple.showDetails();
        myApple.nutrientsInfo();
    }
}
