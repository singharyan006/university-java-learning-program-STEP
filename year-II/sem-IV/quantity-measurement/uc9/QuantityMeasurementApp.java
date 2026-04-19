/**
 * UC9 Quantity Measurement application.
 *
 * Supports both length and weight measurements using standalone unit enums.
 * Length and weight remain separate, incomparable categories.
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

        public QuantityLength add(QuantityLength other) {
            return QuantityMeasurementApp.add(this, other, this.unit);
        }

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

    /**
     * Immutable weight value object.
     */
    public static final class QuantityWeight {
        private static final double EPSILON = 1e-6;

        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {
            validateFinite(value);
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null.");
            }
            this.value = value;
            this.unit = unit;
        }

        public static QuantityWeight of(double value, String unitText) {
            return new QuantityWeight(value, WeightUnit.fromString(unitText));
        }

        public double getValue() {
            return value;
        }

        public WeightUnit getUnit() {
            return unit;
        }

        public double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        public QuantityWeight convertTo(WeightUnit targetUnit) {
            double convertedValue = targetUnit.convertFromBaseUnit(toBaseUnit());
            return new QuantityWeight(convertedValue, targetUnit);
        }

        public QuantityWeight add(QuantityWeight other) {
            return QuantityMeasurementApp.add(this, other, this.unit);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
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
            QuantityWeight other = (QuantityWeight) obj;
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

    public static double convert(double value, WeightUnit sourceUnit, WeightUnit targetUnit) {
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

    public static QuantityWeight add(QuantityWeight first, QuantityWeight second, WeightUnit targetUnit) {
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
        return new QuantityWeight(sumInTargetUnit, targetUnit);
    }

    public static QuantityLength add(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit, LengthUnit targetUnit) {
        QuantityLength first = new QuantityLength(firstValue, firstUnit);
        QuantityLength second = new QuantityLength(secondValue, secondUnit);
        return add(first, second, targetUnit);
    }

    public static QuantityWeight add(double firstValue, WeightUnit firstUnit, double secondValue, WeightUnit secondUnit, WeightUnit targetUnit) {
        QuantityWeight first = new QuantityWeight(firstValue, firstUnit);
        QuantityWeight second = new QuantityWeight(secondValue, secondUnit);
        return add(first, second, targetUnit);
    }

    public static boolean compareFeet(double firstFeet, double secondFeet) {
        return new QuantityLength(firstFeet, LengthUnit.FEET).equals(new QuantityLength(secondFeet, LengthUnit.FEET));
    }

    public static boolean compareInches(double firstInches, double secondInches) {
        return new QuantityLength(firstInches, LengthUnit.INCHES).equals(new QuantityLength(secondInches, LengthUnit.INCHES));
    }

    public static boolean compareFeetAndInches(double feetValue, double inchesValue) {
        return new QuantityLength(feetValue, LengthUnit.FEET).equals(new QuantityLength(inchesValue, LengthUnit.INCHES));
    }

    public static boolean compareLengths(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit) {
        return new QuantityLength(firstValue, firstUnit).equals(new QuantityLength(secondValue, secondUnit));
    }

    public static boolean compareWeights(double firstValue, WeightUnit firstUnit, double secondValue, WeightUnit secondUnit) {
        return new QuantityWeight(firstValue, firstUnit).equals(new QuantityWeight(secondValue, secondUnit));
    }

    public static void demonstrateLengthAddition(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {
        QuantityLength result = add(first, second, targetUnit);
        System.out.println("Input: add(" + first + ", " + second + ", " + targetUnit.name() + ")");
        System.out.println("Output: " + result);
    }

    public static void demonstrateWeightAddition(QuantityWeight first, QuantityWeight second, WeightUnit targetUnit) {
        QuantityWeight result = add(first, second, targetUnit);
        System.out.println("Input: add(" + first + ", " + second + ", " + targetUnit.name() + ")");
        System.out.println("Output: " + result);
    }

    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        double result = convert(value, fromUnit, toUnit);
        System.out.println("Input: convert(" + value + ", " + fromUnit.name() + ", " + toUnit.name() + ")");
        System.out.println("Output: " + result);
    }

    public static void demonstrateWeightConversion(double value, WeightUnit fromUnit, WeightUnit toUnit) {
        double result = convert(value, fromUnit, toUnit);
        System.out.println("Input: convert(" + value + ", " + fromUnit.name() + ", " + toUnit.name() + ")");
        System.out.println("Output: " + result);
    }

    private static void validateFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
    }

    public static void main(String[] args) {
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthAddition(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        demonstrateLengthAddition(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        demonstrateWeightConversion(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM);
        demonstrateWeightConversion(2.0, WeightUnit.POUND, WeightUnit.KILOGRAM);
        demonstrateWeightAddition(new QuantityWeight(1.0, WeightUnit.KILOGRAM), new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        demonstrateWeightAddition(new QuantityWeight(1.0, WeightUnit.POUND), new QuantityWeight(453.592, WeightUnit.GRAM), WeightUnit.POUND);
    }
}
