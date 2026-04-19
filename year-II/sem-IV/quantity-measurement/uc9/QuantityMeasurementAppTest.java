public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testLengthUnitEnum_FeetConstant", QuantityMeasurementAppTest::testLengthUnitEnum_FeetConstant);
        runTest("testLengthUnitEnum_InchesConstant", QuantityMeasurementAppTest::testLengthUnitEnum_InchesConstant);
        runTest("testLengthUnitEnum_YardsConstant", QuantityMeasurementAppTest::testLengthUnitEnum_YardsConstant);
        runTest("testLengthUnitEnum_CentimetersConstant", QuantityMeasurementAppTest::testLengthUnitEnum_CentimetersConstant);
        runTest("testConvertToBaseUnit_FeetToFeet", QuantityMeasurementAppTest::testConvertToBaseUnit_FeetToFeet);
        runTest("testConvertToBaseUnit_InchesToFeet", QuantityMeasurementAppTest::testConvertToBaseUnit_InchesToFeet);
        runTest("testConvertToBaseUnit_YardsToFeet", QuantityMeasurementAppTest::testConvertToBaseUnit_YardsToFeet);
        runTest("testConvertToBaseUnit_CentimetersToFeet", QuantityMeasurementAppTest::testConvertToBaseUnit_CentimetersToFeet);
        runTest("testConvertFromBaseUnit_FeetToFeet", QuantityMeasurementAppTest::testConvertFromBaseUnit_FeetToFeet);
        runTest("testConvertFromBaseUnit_FeetToInches", QuantityMeasurementAppTest::testConvertFromBaseUnit_FeetToInches);
        runTest("testConvertFromBaseUnit_FeetToYards", QuantityMeasurementAppTest::testConvertFromBaseUnit_FeetToYards);
        runTest("testConvertFromBaseUnit_FeetToCentimeters", QuantityMeasurementAppTest::testConvertFromBaseUnit_FeetToCentimeters);
        runTest("testQuantityLengthRefactored_Equality", QuantityMeasurementAppTest::testQuantityLengthRefactored_Equality);
        runTest("testQuantityLengthRefactored_ConvertTo", QuantityMeasurementAppTest::testQuantityLengthRefactored_ConvertTo);
        runTest("testQuantityLengthRefactored_Add", QuantityMeasurementAppTest::testQuantityLengthRefactored_Add);
        runTest("testQuantityLengthRefactored_AddWithTargetUnit", QuantityMeasurementAppTest::testQuantityLengthRefactored_AddWithTargetUnit);
        runTest("testQuantityLengthRefactored_NullUnit", QuantityMeasurementAppTest::testQuantityLengthRefactored_NullUnit);
        runTest("testQuantityLengthRefactored_InvalidValue", QuantityMeasurementAppTest::testQuantityLengthRefactored_InvalidValue);
        runTest("testBackwardCompatibility_UC1EqualityTests", QuantityMeasurementAppTest::testBackwardCompatibility_UC1EqualityTests);
        runTest("testBackwardCompatibility_UC5ConversionTests", QuantityMeasurementAppTest::testBackwardCompatibility_UC5ConversionTests);
        runTest("testBackwardCompatibility_UC6AdditionTests", QuantityMeasurementAppTest::testBackwardCompatibility_UC6AdditionTests);
        runTest("testBackwardCompatibility_UC7AdditionWithTargetUnitTests", QuantityMeasurementAppTest::testBackwardCompatibility_UC7AdditionWithTargetUnitTests);
        runTest("testArchitecturalScalability_MultipleCategories", QuantityMeasurementAppTest::testArchitecturalScalability_MultipleCategories);
        runTest("testRoundTripConversion_RefactoredDesign", QuantityMeasurementAppTest::testRoundTripConversion_RefactoredDesign);
        runTest("testUnitImmutability", QuantityMeasurementAppTest::testUnitImmutability);
        runTest("testWeightUnitEnum_KilogramConstant", QuantityMeasurementAppTest::testWeightUnitEnum_KilogramConstant);
        runTest("testWeightUnitEnum_GramConstant", QuantityMeasurementAppTest::testWeightUnitEnum_GramConstant);
        runTest("testWeightUnitEnum_PoundConstant", QuantityMeasurementAppTest::testWeightUnitEnum_PoundConstant);
        runTest("testWeightConvertToBaseUnit_GramToKilogram", QuantityMeasurementAppTest::testWeightConvertToBaseUnit_GramToKilogram);
        runTest("testWeightConvertToBaseUnit_PoundToKilogram", QuantityMeasurementAppTest::testWeightConvertToBaseUnit_PoundToKilogram);
        runTest("testWeightConvertFromBaseUnit_KilogramToGram", QuantityMeasurementAppTest::testWeightConvertFromBaseUnit_KilogramToGram);
        runTest("testWeightConvertFromBaseUnit_KilogramToPound", QuantityMeasurementAppTest::testWeightConvertFromBaseUnit_KilogramToPound);
        runTest("testQuantityWeightRefactored_Equality", QuantityMeasurementAppTest::testQuantityWeightRefactored_Equality);
        runTest("testQuantityWeightRefactored_ConvertTo", QuantityMeasurementAppTest::testQuantityWeightRefactored_ConvertTo);
        runTest("testQuantityWeightRefactored_Add", QuantityMeasurementAppTest::testQuantityWeightRefactored_Add);
        runTest("testQuantityWeightRefactored_AddWithTargetUnit", QuantityMeasurementAppTest::testQuantityWeightRefactored_AddWithTargetUnit);
        runTest("testQuantityWeightRefactored_NullUnit", QuantityMeasurementAppTest::testQuantityWeightRefactored_NullUnit);
        runTest("testQuantityWeightRefactored_InvalidValue", QuantityMeasurementAppTest::testQuantityWeightRefactored_InvalidValue);
        runTest("testQuantityWeightVsLength_Incompatible", QuantityMeasurementAppTest::testQuantityWeightVsLength_Incompatible);
        runTest("testWeightAddition_Commutativity", QuantityMeasurementAppTest::testWeightAddition_Commutativity);
        runTest("testWeightRoundTripConversion", QuantityMeasurementAppTest::testWeightRoundTripConversion);
        runTest("testBackwardCompatibility_WeightHelpers", QuantityMeasurementAppTest::testBackwardCompatibility_WeightHelpers);
        runTest("testAddition_ExplicitTargetUnit_Kilogram", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Kilogram);
        runTest("testAddition_ExplicitTargetUnit_Pound", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Pound);
        runTest("testAddition_ExplicitTargetUnit_ZeroAndNegative", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_ZeroAndNegative);

        System.out.println("\nTest Summary: " + passedTests + "/" + totalTests + " passed");
    }

    private static void runTest(String testName, Runnable testMethod) {
        totalTests++;
        try {
            testMethod.run();
            passedTests++;
            System.out.println("PASS: " + testName);
        } catch (AssertionError error) {
            System.out.println("FAIL: " + testName + " -> " + error.getMessage());
        }
    }

    private static void assertApproximatelyEquals(double expected, double actual, String message) {
        if (Math.abs(expected - actual) > EPSILON) {
            throw new AssertionError(message + " expected=" + expected + " actual=" + actual);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertThrows(Runnable action, String message) {
        try {
            action.run();
            throw new AssertionError(message);
        } catch (IllegalArgumentException expected) {
            // Expected path.
        }
    }

    private static QuantityMeasurementApp.QuantityLength ql(double value, LengthUnit unit) {
        return new QuantityMeasurementApp.QuantityLength(value, unit);
    }

    private static QuantityMeasurementApp.QuantityWeight qw(double value, WeightUnit unit) {
        return new QuantityMeasurementApp.QuantityWeight(value, unit);
    }

    private static void testLengthUnitEnum_FeetConstant() {
        assertTrue(LengthUnit.FEET.getConversionFactor() == 1.0, "Feet conversion factor should be 1.0.");
    }

    private static void testLengthUnitEnum_InchesConstant() {
        assertApproximatelyEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), "Inches conversion factor should be 1/12.");
    }

    private static void testLengthUnitEnum_YardsConstant() {
        assertTrue(LengthUnit.YARDS.getConversionFactor() == 3.0, "Yards conversion factor should be 3.0.");
    }

    private static void testLengthUnitEnum_CentimetersConstant() {
        assertApproximatelyEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), "Centimeters conversion factor should be 1/30.48.");
    }

    private static void testConvertToBaseUnit_FeetToFeet() {
        assertApproximatelyEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), "5 feet should remain 5 feet in base unit.");
    }

    private static void testConvertToBaseUnit_InchesToFeet() {
        assertApproximatelyEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), "12 inches should convert to 1 foot.");
    }

    private static void testConvertToBaseUnit_YardsToFeet() {
        assertApproximatelyEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), "1 yard should convert to 3 feet.");
    }

    private static void testConvertToBaseUnit_CentimetersToFeet() {
        assertApproximatelyEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), "30.48 centimeters should convert to 1 foot.");
    }

    private static void testConvertFromBaseUnit_FeetToFeet() {
        assertApproximatelyEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), "2 feet should remain 2 feet.");
    }

    private static void testConvertFromBaseUnit_FeetToInches() {
        assertApproximatelyEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), "1 foot should convert to 12 inches.");
    }

    private static void testConvertFromBaseUnit_FeetToYards() {
        assertApproximatelyEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), "3 feet should convert to 1 yard.");
    }

    private static void testConvertFromBaseUnit_FeetToCentimeters() {
        assertApproximatelyEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), "1 foot should convert to 30.48 centimeters.");
    }

    private static void testQuantityLengthRefactored_Equality() {
        assertTrue(ql(1.0, LengthUnit.FEET).equals(ql(12.0, LengthUnit.INCHES)), "1 foot should equal 12 inches.");
    }

    private static void testQuantityLengthRefactored_ConvertTo() {
        QuantityMeasurementApp.QuantityLength result = ql(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertApproximatelyEquals(12.0, result.getValue(), "1 foot should convert to 12 inches.");
        assertTrue(result.getUnit() == LengthUnit.INCHES, "Converted unit should be inches.");
    }

    private static void testQuantityLengthRefactored_Add() {
        QuantityMeasurementApp.QuantityLength result = ql(1.0, LengthUnit.FEET).add(ql(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertApproximatelyEquals(2.0, result.getValue(), "1 foot + 12 inches should be 2 feet.");
        assertTrue(result.getUnit() == LengthUnit.FEET, "Result unit should be feet.");
    }

    private static void testQuantityLengthRefactored_AddWithTargetUnit() {
        QuantityMeasurementApp.QuantityLength result = ql(1.0, LengthUnit.FEET).add(ql(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertApproximatelyEquals(2.0 / 3.0, result.getValue(), "1 foot + 12 inches should be about 0.667 yards.");
        assertTrue(result.getUnit() == LengthUnit.YARDS, "Result unit should be yards.");
    }

    private static void testQuantityLengthRefactored_NullUnit() {
        assertThrows(() -> new QuantityMeasurementApp.QuantityLength(1.0, null), "Null unit should throw IllegalArgumentException.");
    }

    private static void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(() -> new QuantityMeasurementApp.QuantityLength(Double.NaN, LengthUnit.FEET), "NaN value should throw IllegalArgumentException.");
    }

    private static void testBackwardCompatibility_UC1EqualityTests() {
        assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0), "UC1 feet equality should remain true.");
        assertTrue(QuantityMeasurementApp.compareInches(1.0, 1.0), "UC1 inches equality should remain true.");
        assertTrue(QuantityMeasurementApp.compareFeetAndInches(1.0, 12.0), "UC1 cross-check should remain true.");
    }

    private static void testBackwardCompatibility_UC5ConversionTests() {
        assertApproximatelyEquals(12.0, QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), "UC5 conversion should remain intact.");
        assertApproximatelyEquals(1.0, QuantityMeasurementApp.convert(12.0, LengthUnit.INCHES, LengthUnit.FEET), "UC5 reverse conversion should remain intact.");
    }

    private static void testBackwardCompatibility_UC6AdditionTests() {
        QuantityMeasurementApp.QuantityLength result = ql(36.0, LengthUnit.INCHES).add(ql(1.0, LengthUnit.YARDS));
        assertApproximatelyEquals(72.0, result.getValue(), "UC6 implicit add should still return first operand unit.");
        assertTrue(result.getUnit() == LengthUnit.INCHES, "UC6 implicit add unit should remain inches.");
    }

    private static void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(ql(1.0, LengthUnit.FEET), ql(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertApproximatelyEquals(2.0 / 3.0, result.getValue(), "UC7 explicit target add should remain intact.");
        assertTrue(result.getUnit() == LengthUnit.YARDS, "UC7 explicit target unit should remain yards.");
    }

    private static void testArchitecturalScalability_MultipleCategories() {
        assertTrue(LengthUnit.FEET != null, "Standalone LengthUnit should be available for future categories.");
        assertTrue(WeightUnit.KILOGRAM != null, "Standalone WeightUnit should be available for future categories.");
    }

    private static void testRoundTripConversion_RefactoredDesign() {
        double original = 17.35;
        double inches = QuantityMeasurementApp.convert(original, LengthUnit.FEET, LengthUnit.INCHES);
        double back = QuantityMeasurementApp.convert(inches, LengthUnit.INCHES, LengthUnit.FEET);
        assertApproximatelyEquals(original, back, "Round-trip conversion should preserve value within tolerance.");
    }

    private static void testUnitImmutability() {
        assertTrue(LengthUnit.FEET.getConversionFactor() == 1.0, "Enum conversion factor should remain immutable.");
        assertTrue(LengthUnit.YARDS.getConversionFactor() == 3.0, "Enum conversion factor should remain immutable.");
    }

    private static void testWeightUnitEnum_KilogramConstant() {
        assertTrue(WeightUnit.KILOGRAM.getConversionFactor() == 1.0, "Kilogram conversion factor should be 1.0.");
    }

    private static void testWeightUnitEnum_GramConstant() {
        assertTrue(WeightUnit.GRAM.getConversionFactor() == 0.001, "Gram conversion factor should be 0.001.");
    }

    private static void testWeightUnitEnum_PoundConstant() {
        assertApproximatelyEquals(0.453592, WeightUnit.POUND.getConversionFactor(), "Pound conversion factor should be 0.453592.");
    }

    private static void testWeightConvertToBaseUnit_GramToKilogram() {
        assertApproximatelyEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1000.0), "1000 grams should be 1 kilogram.");
    }

    private static void testWeightConvertToBaseUnit_PoundToKilogram() {
        assertApproximatelyEquals(0.453592, WeightUnit.POUND.convertToBaseUnit(1.0), "1 pound should be 0.453592 kilogram.");
    }

    private static void testWeightConvertFromBaseUnit_KilogramToGram() {
        assertApproximatelyEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), "1 kilogram should be 1000 grams.");
    }

    private static void testWeightConvertFromBaseUnit_KilogramToPound() {
        assertApproximatelyEquals(1.0 / 0.453592, WeightUnit.POUND.convertFromBaseUnit(1.0), "1 kilogram should convert to about 2.20462 pounds.");
    }

    private static void testQuantityWeightRefactored_Equality() {
        assertTrue(qw(1.0, WeightUnit.KILOGRAM).equals(qw(1000.0, WeightUnit.GRAM)), "1 kilogram should equal 1000 grams.");
    }

    private static void testQuantityWeightRefactored_ConvertTo() {
        QuantityMeasurementApp.QuantityWeight result = qw(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertApproximatelyEquals(1000.0, result.getValue(), "1 kilogram should convert to 1000 grams.");
        assertTrue(result.getUnit() == WeightUnit.GRAM, "Converted unit should be grams.");
    }

    private static void testQuantityWeightRefactored_Add() {
        QuantityMeasurementApp.QuantityWeight result = qw(1.0, WeightUnit.KILOGRAM).add(qw(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        assertApproximatelyEquals(2.0, result.getValue(), "1 kilogram + 1000 grams should be 2 kilograms.");
        assertTrue(result.getUnit() == WeightUnit.KILOGRAM, "Result unit should be kilograms.");
    }

    private static void testQuantityWeightRefactored_AddWithTargetUnit() {
        QuantityMeasurementApp.QuantityWeight result = qw(1.0, WeightUnit.KILOGRAM).add(qw(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        assertApproximatelyEquals(2000.0, result.getValue(), "1 kilogram + 1000 grams should be 2000 grams.");
        assertTrue(result.getUnit() == WeightUnit.GRAM, "Result unit should be grams.");
    }

    private static void testQuantityWeightRefactored_NullUnit() {
        assertThrows(() -> new QuantityMeasurementApp.QuantityWeight(1.0, null), "Null weight unit should throw IllegalArgumentException.");
    }

    private static void testQuantityWeightRefactored_InvalidValue() {
        assertThrows(() -> new QuantityMeasurementApp.QuantityWeight(Double.NaN, WeightUnit.KILOGRAM), "NaN weight should throw IllegalArgumentException.");
    }

    private static void testQuantityWeightVsLength_Incompatible() {
        assertFalse(qw(1.0, WeightUnit.KILOGRAM).equals(ql(1.0, LengthUnit.FEET)), "Weight and length should not be equal.");
        assertFalse(ql(1.0, LengthUnit.FEET).equals(qw(1.0, WeightUnit.KILOGRAM)), "Length and weight should not be equal.");
    }

    private static void testWeightAddition_Commutativity() {
        QuantityMeasurementApp.QuantityWeight sumAB = QuantityMeasurementApp.add(qw(1.0, WeightUnit.KILOGRAM), qw(1000.0, WeightUnit.GRAM), WeightUnit.POUND);
        QuantityMeasurementApp.QuantityWeight sumBA = QuantityMeasurementApp.add(qw(1000.0, WeightUnit.GRAM), qw(1.0, WeightUnit.KILOGRAM), WeightUnit.POUND);
        assertApproximatelyEquals(sumAB.getValue(), sumBA.getValue(), "Weight addition should be commutative with explicit target unit.");
        assertTrue(sumAB.getUnit() == WeightUnit.POUND, "Target unit should be pounds.");
        assertTrue(sumBA.getUnit() == WeightUnit.POUND, "Target unit should be pounds.");
    }

    private static void testWeightRoundTripConversion() {
        double original = 2.75;
        double pounds = QuantityMeasurementApp.convert(original, WeightUnit.KILOGRAM, WeightUnit.POUND);
        double back = QuantityMeasurementApp.convert(pounds, WeightUnit.POUND, WeightUnit.KILOGRAM);
        assertApproximatelyEquals(original, back, "Round-trip weight conversion should preserve value within tolerance.");
    }

    private static void testBackwardCompatibility_WeightHelpers() {
        assertTrue(QuantityMeasurementApp.compareWeights(1.0, WeightUnit.KILOGRAM, 1000.0, WeightUnit.GRAM), "Weight comparison helper should work.");
        assertApproximatelyEquals(1000.0, QuantityMeasurementApp.convert(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM), "Weight convert helper should work.");
    }

    private static void testAddition_ExplicitTargetUnit_Kilogram() {
        QuantityMeasurementApp.QuantityWeight result = QuantityMeasurementApp.add(qw(1.0, WeightUnit.KILOGRAM), qw(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        assertApproximatelyEquals(2.0, result.getValue(), "1kg + 1000g should be 2kg.");
        assertTrue(result.getUnit() == WeightUnit.KILOGRAM, "Result unit should be kilograms.");
    }

    private static void testAddition_ExplicitTargetUnit_Pound() {
        QuantityMeasurementApp.QuantityWeight result = QuantityMeasurementApp.add(qw(1.0, WeightUnit.POUND), qw(453.592, WeightUnit.GRAM), WeightUnit.POUND);
        assertApproximatelyEquals(2.0, result.getValue(), "1 lb + 453.592 g should be about 2 lb.");
        assertTrue(result.getUnit() == WeightUnit.POUND, "Result unit should be pounds.");
    }

    private static void testAddition_ExplicitTargetUnit_ZeroAndNegative() {
        QuantityMeasurementApp.QuantityWeight zeroResult = QuantityMeasurementApp.add(qw(5.0, WeightUnit.KILOGRAM), qw(0.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        QuantityMeasurementApp.QuantityWeight negativeResult = QuantityMeasurementApp.add(qw(5.0, WeightUnit.KILOGRAM), qw(-2000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        assertApproximatelyEquals(5.0, zeroResult.getValue(), "5kg + 0g should be 5kg.");
        assertApproximatelyEquals(3.0, negativeResult.getValue(), "5kg + (-2000g) should be 3kg.");
    }
}
