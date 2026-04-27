import java.util.Scanner;

public class QuantityMeasurementApp {
    public static final class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    private static Double readFeetValue(Scanner scanner, String label) {
        System.out.print("Enter " + label + " value in feet: ");
        if (!scanner.hasNextDouble()) {
            String invalidInput = scanner.next();
            System.out.println("Invalid input: '" + invalidInput + "'. Please enter a numeric value.");
            return null;
        }
        return scanner.nextDouble();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Double firstValue = readFeetValue(scanner, "first");
        if (firstValue == null) {
            return;
        }

        Double secondValue = readFeetValue(scanner, "second");
        if (secondValue == null) {
            return;
        }

        Feet firstFeet = new Feet(firstValue);
        Feet secondFeet = new Feet(secondValue);

        boolean isEqual = firstFeet.equals(secondFeet);

        System.out.println("Input: " + firstValue + " ft and " + secondValue + " ft");
        System.out.println("Output: Equal (" + isEqual + ")");
    }
}
