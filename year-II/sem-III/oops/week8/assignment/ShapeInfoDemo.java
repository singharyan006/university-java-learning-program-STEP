// Abstract class Shape
abstract class Shape {
    // Abstract methods
    public abstract double area();
    public abstract double perimeter();

    // Concrete method
    public void displayInfo() {
        System.out.println("This is a geometric shape.");
    }
}

// Circle subclass
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    public void showDetails() {
        displayInfo();
        System.out.println("Shape: Circle");
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + area());
        System.out.println("Perimeter: " + perimeter());
    }
}

// Rectangle subclass
class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public double perimeter() {
        return 2 * (length + width);
    }

    public void showDetails() {
        displayInfo();
        System.out.println("Shape: Rectangle");
        System.out.println("Length: " + length + ", Width: " + width);
        System.out.println("Area: " + area());
        System.out.println("Perimeter: " + perimeter());
    }
}

// Main class to test the implementation
public class ShapeInfoDemo {
    public static void main(String[] args) {
        Circle circle = new Circle(4.5);
        Rectangle rectangle = new Rectangle(5.0, 3.0);

        circle.showDetails();
        System.out.println();
        rectangle.showDetails();
    }
}
