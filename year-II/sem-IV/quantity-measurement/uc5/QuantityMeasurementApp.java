/**
 * UC5 Quantity Measurement application.
 *
 * Provides value-object based length comparison and explicit unit-to-unit conversion
 * using a common base unit (feet).
 */
public class QuantityMeasurementApp {
    /**
     * Supported length units with conversion factor to feet.
     */
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

        public double getToFeetFactor() {
            return toFeetFactor;
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

    /**
     * Immutable value object representing a length with unit.
     */
    public static final class QuantityLength {
        private static final double EPSILON = 1e-6;

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            validateFinite(value);
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

        /**
         * Converts this instance to a target unit and returns a new QuantityLength.
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {
            double converted = QuantityMeasurementApp.convert(this.value, this.unit, targetUnit);
            return new QuantityLength(converted, targetUnit);
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

    /**
     * Converts a numeric value from source unit to target unit.
     *
     * Formula: result = value * (source.factor / target.factor)
     */
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        validateFinite(value);
        if (sourceUnit == null) {
            throw new IllegalArgumentException("Source unit cannot be null.");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }

        double valueInFeet = sourceUnit.toFeet(value);
        return valueInFeet / targetUnit.getToFeetFactor();
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

    public static void demonstrateLengthEquality(QuantityLength first, QuantityLength second) {
        System.out.println("Input: " + first + " and " + second);
        System.out.println("Output: Equal (" + first.equals(second) + ")");
    }

    public static void demonstrateLengthComparison(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit) {
        QuantityLength first = new QuantityLength(firstValue, firstUnit);
        QuantityLength second = new QuantityLength(secondValue, secondUnit);
        demonstrateLengthEquality(first, second);
    }

    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        double result = convert(value, fromUnit, toUnit);
        System.out.println("Input: convert(" + value + ", " + fromUnit.name() + ", " + toUnit.name() + ")");
        System.out.println("Output: " + result);
    }

    public static void demonstrateLengthConversion(QuantityLength quantityLength, LengthUnit toUnit) {
        if (quantityLength == null) {
            throw new IllegalArgumentException("Quantity length cannot be null.");
        }
        QuantityLength converted = quantityLength.convertTo(toUnit);
        System.out.println("Input: " + quantityLength + " -> " + toUnit.name());
        System.out.println("Output: " + converted.getValue());
    }

    private static void validateFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
    }

    public static void main(String[] args) {
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);

        QuantityLength lengthInYards = new QuantityLength(2.0, LengthUnit.YARDS);
        demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);
    }
}
