abstract class Shape {
    public abstract double area();
    public void display() {
        System.out.println("Calculating area...");
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
}

public class ShapeMaster {
    public static void main(String[] args) {
        Shape s1 = new Circle(5);
        s1.display();
        System.out.println("Circle area: " + s1.area());

        Shape s2 = new Rectangle(4, 6);
        s2.display();
        System.out.println("Rectangle area: " + s2.area());
    }
}
