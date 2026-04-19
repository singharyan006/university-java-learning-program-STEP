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
        runTest("testAddition_ExplicitTargetUnit_Yards", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Yards);
        runTest("testAddition_ExplicitTargetUnit_Centimeters", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Centimeters);
        runTest("testAddition_ExplicitTargetUnit_NullTargetUnit", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_NullTargetUnit);
        runTest("testAddition_ExplicitTargetUnit_Commutativity", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Commutativity);

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

    private static void assertThrows(Runnable action, String message) {
        try {
            action.run();
            throw new AssertionError(message);
        } catch (IllegalArgumentException expected) {
            // Expected path.
        }
    }

    private static QuantityMeasurementApp.QuantityLength q(double value, LengthUnit unit) {
        return new QuantityMeasurementApp.QuantityLength(value, unit);
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
        assertTrue(q(1.0, LengthUnit.FEET).equals(q(12.0, LengthUnit.INCHES)), "1 foot should equal 12 inches.");
    }

    private static void testQuantityLengthRefactored_ConvertTo() {
        QuantityMeasurementApp.QuantityLength result = q(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertApproximatelyEquals(12.0, result.getValue(), "1 foot should convert to 12 inches.");
        assertTrue(result.getUnit() == LengthUnit.INCHES, "Converted unit should be inches.");
    }

    private static void testQuantityLengthRefactored_Add() {
        QuantityMeasurementApp.QuantityLength result = q(1.0, LengthUnit.FEET).add(q(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertApproximatelyEquals(2.0, result.getValue(), "1 foot + 12 inches should be 2 feet.");
        assertTrue(result.getUnit() == LengthUnit.FEET, "Result unit should be feet.");
    }

    private static void testQuantityLengthRefactored_AddWithTargetUnit() {
        QuantityMeasurementApp.QuantityLength result = q(1.0, LengthUnit.FEET).add(q(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
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
        QuantityMeasurementApp.QuantityLength result = q(36.0, LengthUnit.INCHES).add(q(1.0, LengthUnit.YARDS));
        assertApproximatelyEquals(72.0, result.getValue(), "UC6 implicit add should still return first operand unit.");
        assertTrue(result.getUnit() == LengthUnit.INCHES, "UC6 implicit add unit should remain inches.");
    }

    private static void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(1.0, LengthUnit.FEET), q(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertApproximatelyEquals(2.0 / 3.0, result.getValue(), "UC7 explicit target add should remain intact.");
        assertTrue(result.getUnit() == LengthUnit.YARDS, "UC7 explicit target unit should remain yards.");
    }

    private static void testArchitecturalScalability_MultipleCategories() {
        assertTrue(LengthUnit.FEET != null, "Standalone LengthUnit should be available for future categories.");
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

    private static void testAddition_ExplicitTargetUnit_Yards() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(1.0, LengthUnit.FEET), q(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertApproximatelyEquals(2.0 / 3.0, result.getValue(), "1ft + 12in should be about 0.667yd.");
        assertTrue(result.getUnit() == LengthUnit.YARDS, "Result should be explicitly expressed in yards.");
    }

    private static void testAddition_ExplicitTargetUnit_Centimeters() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(1.0, LengthUnit.INCHES), q(1.0, LengthUnit.INCHES), LengthUnit.CENTIMETERS);
        assertApproximatelyEquals(5.08, result.getValue(), "1in + 1in should be about 5.08cm.");
        assertTrue(result.getUnit() == LengthUnit.CENTIMETERS, "Result should be explicitly expressed in centimeters.");
    }

    private static void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        assertThrows(() -> QuantityMeasurementApp.add(q(1.0, LengthUnit.FEET), q(12.0, LengthUnit.INCHES), null),
                "Null target unit should throw IllegalArgumentException.");
    }

    private static void testAddition_ExplicitTargetUnit_Commutativity() {
        QuantityMeasurementApp.QuantityLength sumAB = QuantityMeasurementApp.add(q(1.0, LengthUnit.FEET), q(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength sumBA = QuantityMeasurementApp.add(q(12.0, LengthUnit.INCHES), q(1.0, LengthUnit.FEET), LengthUnit.YARDS);
        assertApproximatelyEquals(sumAB.getValue(), sumBA.getValue(), "Addition should remain commutative with explicit target unit.");
        assertTrue(sumAB.getUnit() == LengthUnit.YARDS, "Target unit should be yards.");
        assertTrue(sumBA.getUnit() == LengthUnit.YARDS, "Target unit should be yards.");
    }
}
