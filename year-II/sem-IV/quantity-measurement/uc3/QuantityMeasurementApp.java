public class QuantityMeasurementApp {
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        INCHES(1.0 / 12.0);

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

            if ("FOOT".equals(normalized)) {
                return FEET;
            }
            if ("INCH".equals(normalized)) {
                return INCH;
            }
            if ("INCHES".equals(normalized)) {
                return INCHES;
            }
            if ("FEET".equals(normalized)) {
                return FEET;
            }

            throw new IllegalArgumentException("Unsupported unit: " + unitText);
        }
    }

    public static final class QuantityLength {
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
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
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
        QuantityLength first = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength second = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength third = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength fourth = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println("Input: " + first + " and " + second);
        System.out.println("Output: Equal (" + first.equals(second) + ")");
        System.out.println("Input: " + third + " and " + fourth);
        System.out.println("Output: Equal (" + third.equals(fourth) + ")");
    }
}
