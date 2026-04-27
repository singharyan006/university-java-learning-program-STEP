/**
 * UC10 Quantity Measurement application.
 *
 * Demonstrates the shared generic Quantity<U> API for multiple measurement
 * categories without category-specific duplication.
 */
public class QuantityMeasurementApp {
    public static <U extends IMeasurable> Quantity<U> add(Quantity<U> first, Quantity<U> second, U targetUnit) {
        return first.add(second, targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> add(Quantity<U> first, Quantity<U> second) {
        return first.add(second);
    }

    public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> first, Quantity<U> second) {
        System.out.println("Input: " + first + " equals " + second);
        System.out.println("Output: " + first.equals(second));
    }

    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        Quantity<U> converted = quantity.convertTo(targetUnit);
        System.out.println("Input: " + quantity + ".convertTo(" + targetUnit.getUnitName() + ")");
        System.out.println("Output: " + converted);
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> first, Quantity<U> second, U targetUnit) {
        Quantity<U> result = first.add(second, targetUnit);
        System.out.println("Input: add(" + first + ", " + second + ", " + targetUnit.getUnitName() + ")");
        System.out.println("Output: " + result);
    }

    public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> first, Quantity<U> second, U targetUnit) {
        Quantity<U> result = first.subtract(second, targetUnit);
        System.out.println("Input: " + first + ".subtract(" + second + ", " + targetUnit.getUnitName() + ")");
        System.out.println("Output: " + result);
    }

    public static <U extends IMeasurable> void demonstrateDivision(Quantity<U> first, Quantity<U> second) {
        double result = first.divide(second);
        System.out.println("Input: " + first + ".divide(" + second + ")");
        System.out.println("Output: " + result);
    }

    public static void main(String[] args) {
        demonstrateEquality(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(12.0, LengthUnit.INCHES));
        demonstrateConversion(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.INCHES);
        demonstrateAddition(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.FEET);

        demonstrateEquality(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(1000.0, WeightUnit.GRAM));
        demonstrateConversion(new Quantity<>(1.0, WeightUnit.KILOGRAM), WeightUnit.GRAM);
        demonstrateAddition(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        
        System.out.println("Cross-category equality: " + new Quantity<>(1.0, LengthUnit.FEET).equals(new Quantity<>(1.0, LengthUnit.FEET)));

        System.out.println("\n--- UC12 Demonstrations ---");
        // Subtraction
        demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.FEET);
        demonstrateSubtraction(new Quantity<>(5.0, VolumeUnit.LITRE), new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);
        demonstrateSubtraction(new Quantity<>(2.0, WeightUnit.KILOGRAM), new Quantity<>(5.0, WeightUnit.KILOGRAM), WeightUnit.KILOGRAM);
        
        // Division
        demonstrateDivision(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(2.0, LengthUnit.FEET));
        demonstrateDivision(new Quantity<>(24.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.FEET));
        demonstrateDivision(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), new Quantity<>(1.0, VolumeUnit.LITRE));
    }
}
