public class QuantityMeasurementApp {
    public static final class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        public double toInches() {
            return value * 12.0;
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

    public static final class Inches {
        private final double value;

        public Inches(double value) {
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
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static boolean compareFeet(double firstFeet, double secondFeet) {
        Feet first = new Feet(firstFeet);
        Feet second = new Feet(secondFeet);
        return first.equals(second);
    }

    public static boolean compareInches(double firstInches, double secondInches) {
        Inches first = new Inches(firstInches);
        Inches second = new Inches(secondInches);
        return first.equals(second);
    }

    public static boolean compareFeetAndInches(double feetValue, double inchesValue) {
        Feet feet = new Feet(feetValue);
        Inches inches = new Inches(inchesValue);
        return Double.compare(feet.toInches(), inches.getValue()) == 0;
    }

    public static void main(String[] args) {
        double firstInch = 1.0;
        double secondInch = 1.0;
        double firstFeet = 1.0;
        double secondFeet = 1.0;

        boolean inchesEqual = compareInches(firstInch, secondInch);
        boolean feetEqual = compareFeet(firstFeet, secondFeet);

        System.out.println("Input: " + firstInch + " inch and " + secondInch + " inch");
        System.out.println("Output: Equal (" + inchesEqual + ")");
        System.out.println("Input: " + firstFeet + " ft and " + secondFeet + " ft");
        System.out.println("Output: Equal (" + feetEqual + ")");
    }
}
