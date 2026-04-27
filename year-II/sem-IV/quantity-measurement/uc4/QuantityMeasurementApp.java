public class QuantityMeasurementApp {
    public enum LengthUnit {
        FEET(1.0),
        FOOT(1.0),
        INCH(1.0 / 12.0),
        INCHES(1.0 / 12.0),
        YARD(3.0),
        YARDS(3.0),
        CENTIMETER(0.393701 / 12.0),
        CENTIMETERS(0.393701 / 12.0),
        CM(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public static LengthUnit fromString(String unitText) {
            if (unitText == null) {
                throw new IllegalArgumentException("Unit cannot be null.");
            }

            String normalized = unitText.trim().toUpperCase();
            if (normalized.isEmpty()) {
                throw new IllegalArgumentException("Unit cannot be empty.");
            }

            switch (normalized) {
                case "FEET":
                case "FOOT":
                    return FEET;
                case "INCH":
                    return INCH;
                case "INCHES":
                    return INCHES;
                case "YARD":
                    return YARD;
                case "YARDS":
                    return YARDS;
                case "CENTIMETER":
                    return CENTIMETER;
                case "CENTIMETERS":
                    return CENTIMETERS;
                case "CM":
                    return CM;
                default:
                    throw new IllegalArgumentException("Unsupported unit: " + unitText);
            }
        }
    }

    public static final class QuantityLength {
        private static final double EPSILON = 1e-4;

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null.");
            }
            this.value = value;
            this.unit = unit;
        }

        public static QuantityLength of(double value, String unitText) {
            return new QuantityLength(value, LengthUnit.fromString(unitText));
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        public double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            QuantityLength other = (QuantityLength) obj;
            return Math.abs(this.toFeet() - other.toFeet()) <= EPSILON;
        }

        @Override
        public int hashCode() {
            long normalized = Math.round(toFeet() / EPSILON);
            return Long.hashCode(normalized);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit.name() + ")";
        }
    }

    public static boolean compareFeet(double firstFeet, double secondFeet) {
        QuantityLength first = new QuantityLength(firstFeet, LengthUnit.FEET);
        QuantityLength second = new QuantityLength(secondFeet, LengthUnit.FEET);
        return first.equals(second);
    }

    public static boolean compareInches(double firstInches, double secondInches) {
        QuantityLength first = new QuantityLength(firstInches, LengthUnit.INCHES);
        QuantityLength second = new QuantityLength(secondInches, LengthUnit.INCHES);
        return first.equals(second);
    }

    public static boolean compareFeetAndInches(double feetValue, double inchesValue) {
        QuantityLength feet = new QuantityLength(feetValue, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(inchesValue, LengthUnit.INCHES);
        return feet.equals(inches);
    }

    public static boolean compareLengths(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit) {
        QuantityLength first = new QuantityLength(firstValue, firstUnit);
        QuantityLength second = new QuantityLength(secondValue, secondUnit);
        return first.equals(second);
    }

    public static void main(String[] args) {
        QuantityLength yardToFeetLeft = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength yardToFeetRight = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength yardToInchLeft = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength yardToInchRight = new QuantityLength(36.0, LengthUnit.INCHES);

        QuantityLength sameYardsLeft = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength sameYardsRight = new QuantityLength(2.0, LengthUnit.YARDS);

        QuantityLength sameCmLeft = new QuantityLength(2.0, LengthUnit.CENTIMETERS);
        QuantityLength sameCmRight = new QuantityLength(2.0, LengthUnit.CENTIMETERS);

        QuantityLength cmToInchLeft = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength cmToInchRight = new QuantityLength(0.393701, LengthUnit.INCHES);

        System.out.println("Input: " + yardToFeetLeft + " and " + yardToFeetRight);
        System.out.println("Output: Equal (" + yardToFeetLeft.equals(yardToFeetRight) + ")");

        System.out.println("Input: " + yardToInchLeft + " and " + yardToInchRight);
        System.out.println("Output: Equal (" + yardToInchLeft.equals(yardToInchRight) + ")");

        System.out.println("Input: " + sameYardsLeft + " and " + sameYardsRight);
        System.out.println("Output: Equal (" + sameYardsLeft.equals(sameYardsRight) + ")");

        System.out.println("Input: " + sameCmLeft + " and " + sameCmRight);
        System.out.println("Output: Equal (" + sameCmLeft.equals(sameCmRight) + ")");

        System.out.println("Input: " + cmToInchLeft + " and " + cmToInchRight);
        System.out.println("Output: Equal (" + cmToInchLeft.equals(cmToInchRight) + ")");
    }
}
