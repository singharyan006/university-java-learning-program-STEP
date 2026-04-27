/**
 * UC8 Quantity Measurement application.
 *
 * Refactors unit conversion responsibility into the standalone LengthUnit enum
 * and keeps QuantityLength focused on value semantics and arithmetic.
 */
public class QuantityMeasurementApp {
    /**
     * Immutable length value object.
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

        public double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            double convertedValue = targetUnit.convertFromBaseUnit(toBaseUnit());
            return new QuantityLength(convertedValue, targetUnit);
        }

        /**
         * Adds another length and returns the result in this object's unit.
         */
        public QuantityLength add(QuantityLength other) {
            return QuantityMeasurementApp.add(this, other, this.unit);
        }

        /**
         * Adds another length and returns the result in the requested target unit.
         */
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            return QuantityMeasurementApp.add(this, other, targetUnit);
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
            return Math.abs(this.toBaseUnit() - other.toBaseUnit()) <= EPSILON;
        }

        @Override
        public int hashCode() {
            long normalized = Math.round(toBaseUnit() / EPSILON);
            return Long.hashCode(normalized);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit.name() + ")";
        }
    }

    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        validateFinite(value);
        if (sourceUnit == null) {
            throw new IllegalArgumentException("Source unit cannot be null.");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }

        double valueInBaseUnit = sourceUnit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(valueInBaseUnit);
    }

    /**
     * Adds two quantity lengths and returns the result in the explicitly specified target unit.
     */
    public static QuantityLength add(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {
        if (first == null) {
            throw new IllegalArgumentException("First operand cannot be null.");
        }
        if (second == null) {
            throw new IllegalArgumentException("Second operand cannot be null.");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }

        double sumInBaseUnit = first.unit.convertToBaseUnit(first.value) + second.unit.convertToBaseUnit(second.value);
        double sumInTargetUnit = targetUnit.convertFromBaseUnit(sumInBaseUnit);
        return new QuantityLength(sumInTargetUnit, targetUnit);
    }

    /**
     * Overload using raw values and units.
     */
    public static QuantityLength add(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit, LengthUnit targetUnit) {
        QuantityLength first = new QuantityLength(firstValue, firstUnit);
        QuantityLength second = new QuantityLength(secondValue, secondUnit);
        return add(first, second, targetUnit);
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

    public static void demonstrateLengthAddition(QuantityLength first, QuantityLength second) {
        QuantityLength result = first.add(second);
        System.out.println("Input: add(" + first + ", " + second + ")");
        System.out.println("Output: " + result);
    }

    public static void demonstrateLengthAddition(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {
        QuantityLength result = add(first, second, targetUnit);
        System.out.println("Input: add(" + first + ", " + second + ", " + targetUnit.name() + ")");
        System.out.println("Output: " + result);
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
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.FEET);
        demonstrateLengthConversion(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        demonstrateLengthAddition(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        demonstrateLengthAddition(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        demonstrateLengthAddition(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        demonstrateLengthAddition(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(0.0, LengthUnit.INCHES), LengthUnit.FEET);
        demonstrateLengthAddition(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(-2.0, LengthUnit.FEET), LengthUnit.INCHES);
    }
}
