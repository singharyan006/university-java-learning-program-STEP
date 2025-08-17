import java.util.Scanner;

public class LineDistanceCalculator {

    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public static double[] findLineEquation(double x1, double y1, double x2, double y2) {
        double m = (y2 - y1) / (x2 - x1);
        double b = y1 - m * x1;
        return new double[] { m, b };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter coordinates for first point (x1 y1): ");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();

        System.out.print("Enter coordinates for second point (x2 y2): ");
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();

        double distance = calculateDistance(x1, y1, x2, y2);
        System.out.println("Euclidean Distance: " + distance);

        if (x1 == x2) {
            System.out.println("Line Equation: x = " + x1 + " (Vertical line, slope is undefined)");
        } else {
            double[] line = findLineEquation(x1, y1, x2, y2);
            double m = line[0];
            double b = line[1];
            System.out.println("Line Equation: y = " + m + "x + " + b);
        }
    }
}
