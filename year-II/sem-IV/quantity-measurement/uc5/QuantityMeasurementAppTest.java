public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testConversion_FeetToInches", QuantityMeasurementAppTest::testConversion_FeetToInches);
        runTest("testConversion_InchesToFeet", QuantityMeasurementAppTest::testConversion_InchesToFeet);
        runTest("testConversion_YardsToInches", QuantityMeasurementAppTest::testConversion_YardsToInches);
        runTest("testConversion_InchesToYards", QuantityMeasurementAppTest::testConversion_InchesToYards);
        runTest("testConversion_CentimetersToInches", QuantityMeasurementAppTest::testConversion_CentimetersToInches);
        runTest("testConversion_FeetToYard", QuantityMeasurementAppTest::testConversion_FeetToYard);
        runTest("testConversion_RoundTrip_PreservesValue", QuantityMeasurementAppTest::testConversion_RoundTrip_PreservesValue);
        runTest("testConversion_ZeroValue", QuantityMeasurementAppTest::testConversion_ZeroValue);
        runTest("testConversion_NegativeValue", QuantityMeasurementAppTest::testConversion_NegativeValue);
        runTest("testConversion_SameUnit", QuantityMeasurementAppTest::testConversion_SameUnit);
        runTest("testConversion_LargeValue", QuantityMeasurementAppTest::testConversion_LargeValue);
        runTest("testConversion_SmallValue", QuantityMeasurementAppTest::testConversion_SmallValue);
        runTest("testConversion_InvalidUnit_Throws", QuantityMeasurementAppTest::testConversion_InvalidUnit_Throws);
        runTest("testConversion_NaNOrInfinite_Throws", QuantityMeasurementAppTest::testConversion_NaNOrInfinite_Throws);
        runTest("testConversion_MathFormulaConsistency", QuantityMeasurementAppTest::testConversion_MathFormulaConsistency);
        runTest("testInstanceConversion_ReturnsNewObject", QuantityMeasurementAppTest::testInstanceConversion_ReturnsNewObject);
        runTest("testBackwardCompatibility_CompareFeetAndInches", QuantityMeasurementAppTest::testBackwardCompatibility_CompareFeetAndInches);

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

    private static void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(12.0, result, "1 foot should be 12 inches.");
    }

    private static void testConversion_InchesToFeet() {
        double result = QuantityMeasurementApp.convert(24.0, QuantityMeasurementApp.LengthUnit.INCHES, QuantityMeasurementApp.LengthUnit.FEET);
        assertApproximatelyEquals(2.0, result, "24 inches should be 2 feet.");
    }

    private static void testConversion_YardsToInches() {
        double result = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.YARDS, QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(36.0, result, "1 yard should be 36 inches.");
    }

    private static void testConversion_InchesToYards() {
        double result = QuantityMeasurementApp.convert(72.0, QuantityMeasurementApp.LengthUnit.INCHES, QuantityMeasurementApp.LengthUnit.YARDS);
        assertApproximatelyEquals(2.0, result, "72 inches should be 2 yards.");
    }

    private static void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.convert(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS, QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(1.0, result, "2.54 centimeters should be about 1 inch.");
    }

    private static void testConversion_FeetToYard() {
        double result = QuantityMeasurementApp.convert(6.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.YARDS);
        assertApproximatelyEquals(2.0, result, "6 feet should be 2 yards.");
    }

    private static void testConversion_RoundTrip_PreservesValue() {
        double original = 17.35;
        double inCentimeters = QuantityMeasurementApp.convert(original, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        double backToFeet = QuantityMeasurementApp.convert(inCentimeters, QuantityMeasurementApp.LengthUnit.CENTIMETERS, QuantityMeasurementApp.LengthUnit.FEET);
        assertApproximatelyEquals(original, backToFeet, "Round-trip conversion should preserve value.");
    }

    private static void testConversion_ZeroValue() {
        double result = QuantityMeasurementApp.convert(0.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(0.0, result, "Zero should remain zero across unit conversion.");
    }

    private static void testConversion_NegativeValue() {
        double result = QuantityMeasurementApp.convert(-1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(-12.0, result, "Negative sign should be preserved during conversion.");
    }

    private static void testConversion_SameUnit() {
        double result = QuantityMeasurementApp.convert(5.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.FEET);
        assertApproximatelyEquals(5.0, result, "Same-unit conversion should return original value.");
    }

    private static void testConversion_LargeValue() {
        double result = QuantityMeasurementApp.convert(1_000_000.0, QuantityMeasurementApp.LengthUnit.YARDS, QuantityMeasurementApp.LengthUnit.FEET);
        assertApproximatelyEquals(3_000_000.0, result, "Large values should convert correctly.");
    }

    private static void testConversion_SmallValue() {
        double result = QuantityMeasurementApp.convert(0.0001, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(0.0012, result, "Small values should convert correctly.");
    }

    private static void testConversion_InvalidUnit_Throws() {
        assertThrows(() -> QuantityMeasurementApp.convert(1.0, null, QuantityMeasurementApp.LengthUnit.FEET),
                "Null source unit should throw IllegalArgumentException.");
        assertThrows(() -> QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, null),
                "Null target unit should throw IllegalArgumentException.");
        assertThrows(() -> QuantityMeasurementApp.QuantityLength.of(1.0, "meter"),
                "Unsupported unit text should throw IllegalArgumentException.");
    }

    private static void testConversion_NaNOrInfinite_Throws() {
        assertThrows(() -> QuantityMeasurementApp.convert(Double.NaN, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES),
                "NaN input should throw IllegalArgumentException.");
        assertThrows(() -> QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES),
                "Positive infinity should throw IllegalArgumentException.");
        assertThrows(() -> QuantityMeasurementApp.convert(Double.NEGATIVE_INFINITY, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES),
                "Negative infinity should throw IllegalArgumentException.");
    }

    private static void testConversion_MathFormulaConsistency() {
        double value = 9.0;
        QuantityMeasurementApp.LengthUnit source = QuantityMeasurementApp.LengthUnit.FEET;
        QuantityMeasurementApp.LengthUnit target = QuantityMeasurementApp.LengthUnit.CENTIMETERS;

        double expected = value * (source.getToFeetFactor() / target.getToFeetFactor());
        double actual = QuantityMeasurementApp.convert(value, source, target);

        assertApproximatelyEquals(expected, actual, "Conversion formula consistency failed.");
    }

    private static void testInstanceConversion_ReturnsNewObject() {
        QuantityMeasurementApp.QuantityLength original = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength converted = original.convertTo(QuantityMeasurementApp.LengthUnit.INCHES);

        assertApproximatelyEquals(36.0, converted.getValue(), "3 feet should convert to 36 inches.");
        assertTrue(converted.getUnit() == QuantityMeasurementApp.LengthUnit.INCHES, "Converted unit should be inches.");
        assertApproximatelyEquals(3.0, original.getValue(), "Original object should remain unchanged.");
        assertTrue(original.getUnit() == QuantityMeasurementApp.LengthUnit.FEET, "Original unit should remain feet.");
    }

    private static void testBackwardCompatibility_CompareFeetAndInches() {
        assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0), "Expected compareFeet to remain functional.");
        assertTrue(QuantityMeasurementApp.compareInches(2.0, 2.0), "Expected compareInches to remain functional.");
        assertTrue(QuantityMeasurementApp.compareFeetAndInches(1.0, 12.0), "Expected feet-inch equality to remain functional.");
    }
}
