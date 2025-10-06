// Abstract class Shape
abstract class Shape {
    protected double area;
    protected double perimeter;

    // Abstract methods to be implemented by subclasses
    public abstract void calculateArea();
    public abstract void calculatePerimeter();
}

// Interface Drawable
interface Drawable {
    void draw();
}

// Circle class extending Shape and implementing Drawable
class Circle extends Shape implements Drawable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculateArea() {
        area = Math.PI * radius * radius;
    }

    @Override
    public void calculatePerimeter() {
        perimeter = 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius: " + radius);
    }

    public void showDetails() {
        System.out.println("Circle Radius: " + radius);
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
    }
}

// Main class to test the implementation
public class ShapeDrawableDemo {
    public static void main(String[] args) {
        Circle myCircle = new Circle(5.0);
        myCircle.calculateArea();
        myCircle.calculatePerimeter();
        myCircle.draw();
        myCircle.showDetails();
    }
}
