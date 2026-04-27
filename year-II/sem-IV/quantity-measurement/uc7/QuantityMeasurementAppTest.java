public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testAddition_ExplicitTargetUnit_Feet", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Feet);
        runTest("testAddition_ExplicitTargetUnit_Inches", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Inches);
        runTest("testAddition_ExplicitTargetUnit_Yards", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Yards);
        runTest("testAddition_ExplicitTargetUnit_Centimeters", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Centimeters);
        runTest("testAddition_ExplicitTargetUnit_SameAsFirstOperand", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_SameAsFirstOperand);
        runTest("testAddition_ExplicitTargetUnit_SameAsSecondOperand", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_SameAsSecondOperand);
        runTest("testAddition_ExplicitTargetUnit_Commutativity", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Commutativity);
        runTest("testAddition_ExplicitTargetUnit_WithZero", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_WithZero);
        runTest("testAddition_ExplicitTargetUnit_NegativeValues", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_NegativeValues);
        runTest("testAddition_ExplicitTargetUnit_NullTargetUnit", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_NullTargetUnit);
        runTest("testAddition_ExplicitTargetUnit_LargeToSmallScale", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_LargeToSmallScale);
        runTest("testAddition_ExplicitTargetUnit_SmallToLargeScale", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_SmallToLargeScale);
        runTest("testAddition_ExplicitTargetUnit_AllUnitCombinations", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_AllUnitCombinations);
        runTest("testAddition_ExplicitTargetUnit_PrecisionTolerance", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_PrecisionTolerance);
        runTest("testAddition_ExplicitTargetUnit_InvalidUnitRawValues", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_InvalidUnitRawValues);
        runTest("testBackwardCompatibility_UC6ImplicitAddition", QuantityMeasurementAppTest::testBackwardCompatibility_UC6ImplicitAddition);
        runTest("testBackwardCompatibility_ConversionAndEquality", QuantityMeasurementAppTest::testBackwardCompatibility_ConversionAndEquality);

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

    private static QuantityMeasurementApp.QuantityLength q(double value, QuantityMeasurementApp.LengthUnit unit) {
        return new QuantityMeasurementApp.QuantityLength(value, unit);
    }

    private static void testAddition_ExplicitTargetUnit_Feet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                q(12.0, QuantityMeasurementApp.LengthUnit.INCHES), QuantityMeasurementApp.LengthUnit.FEET);
        assertApproximatelyEquals(2.0, result.getValue(), "1ft + 12in should be 2ft.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.FEET, "Result unit should be feet.");
    }

    private static void testAddition_ExplicitTargetUnit_Inches() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                q(12.0, QuantityMeasurementApp.LengthUnit.INCHES), QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(24.0, result.getValue(), "1ft + 12in should be 24in.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.INCHES, "Result unit should be inches.");
    }

    private static void testAddition_ExplicitTargetUnit_Yards() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                q(12.0, QuantityMeasurementApp.LengthUnit.INCHES), QuantityMeasurementApp.LengthUnit.YARDS);
        assertApproximatelyEquals(2.0 / 3.0, result.getValue(), "1ft + 12in should be about 0.667yd.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.YARDS, "Result unit should be yards.");
    }

    private static void testAddition_ExplicitTargetUnit_Centimeters() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(1.0, QuantityMeasurementApp.LengthUnit.INCHES),
                q(1.0, QuantityMeasurementApp.LengthUnit.INCHES), QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertApproximatelyEquals(5.08, result.getValue(), "1in + 1in should be about 5.08cm.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.CENTIMETERS, "Result unit should be centimeters.");
    }

    private static void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(2.0, QuantityMeasurementApp.LengthUnit.YARDS),
                q(3.0, QuantityMeasurementApp.LengthUnit.FEET), QuantityMeasurementApp.LengthUnit.YARDS);
        assertApproximatelyEquals(3.0, result.getValue(), "2yd + 3ft should be 3yd.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.YARDS, "Result unit should match explicit target yards.");
    }

    private static void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(2.0, QuantityMeasurementApp.LengthUnit.YARDS),
                q(3.0, QuantityMeasurementApp.LengthUnit.FEET), QuantityMeasurementApp.LengthUnit.FEET);
        assertApproximatelyEquals(9.0, result.getValue(), "2yd + 3ft should be 9ft.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.FEET, "Result unit should match explicit target feet.");
    }

    private static void testAddition_ExplicitTargetUnit_Commutativity() {
        QuantityMeasurementApp.QuantityLength sumAB = QuantityMeasurementApp.add(q(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                q(12.0, QuantityMeasurementApp.LengthUnit.INCHES), QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength sumBA = QuantityMeasurementApp.add(q(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                q(1.0, QuantityMeasurementApp.LengthUnit.FEET), QuantityMeasurementApp.LengthUnit.YARDS);
        assertApproximatelyEquals(sumAB.getValue(), sumBA.getValue(), "Addition should remain commutative for explicit target unit.");
        assertTrue(sumAB.getUnit() == QuantityMeasurementApp.LengthUnit.YARDS, "Target unit should be yards.");
        assertTrue(sumBA.getUnit() == QuantityMeasurementApp.LengthUnit.YARDS, "Target unit should be yards.");
    }

    private static void testAddition_ExplicitTargetUnit_WithZero() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                q(0.0, QuantityMeasurementApp.LengthUnit.INCHES), QuantityMeasurementApp.LengthUnit.YARDS);
        assertApproximatelyEquals(5.0 / 3.0, result.getValue(), "5ft + 0in should be about 1.667yd.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.YARDS, "Result unit should be yards.");
    }

    private static void testAddition_ExplicitTargetUnit_NegativeValues() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                q(-2.0, QuantityMeasurementApp.LengthUnit.FEET), QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(36.0, result.getValue(), "5ft + (-2ft) should be 36in.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.INCHES, "Result unit should be inches.");
    }

    private static void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        assertThrows(() -> QuantityMeasurementApp.add(q(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                q(12.0, QuantityMeasurementApp.LengthUnit.INCHES), null), "Null target unit should throw IllegalArgumentException.");
    }

    private static void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(1000.0, QuantityMeasurementApp.LengthUnit.FEET,
                500.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(18000.0, result.getValue(), "1500ft should be 18000in.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.INCHES, "Result unit should be inches.");
    }

    private static void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                q(12.0, QuantityMeasurementApp.LengthUnit.INCHES), QuantityMeasurementApp.LengthUnit.YARDS);
        assertApproximatelyEquals(2.0 / 3.0, result.getValue(), "24in should be about 0.667yd.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.YARDS, "Result unit should be yards.");
    }

    private static void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
        QuantityMeasurementApp.QuantityLength feetInYards = QuantityMeasurementApp.add(q(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                q(1.0, QuantityMeasurementApp.LengthUnit.YARDS), QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.QuantityLength yardsInCm = QuantityMeasurementApp.add(q(1.0, QuantityMeasurementApp.LengthUnit.YARDS),
                q(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS), QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.QuantityLength inchesInFeet = QuantityMeasurementApp.add(q(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                q(1.0, QuantityMeasurementApp.LengthUnit.FEET), QuantityMeasurementApp.LengthUnit.FEET);

        assertApproximatelyEquals(48.0, feetInYards.getValue(), "1ft + 1yd = 48in.");
        assertApproximatelyEquals(91.44 + 1.0, yardsInCm.getValue(), "1yd + 1cm should be about 92.44cm.");
        assertApproximatelyEquals(2.0, inchesInFeet.getValue(), "12in + 1ft = 2ft.");
    }

    private static void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS),
                q(1.0, QuantityMeasurementApp.LengthUnit.INCHES), QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertApproximatelyEquals(5.08, result.getValue(), "Precision should be maintained for centimeter addition.");
    }

    private static void testAddition_ExplicitTargetUnit_InvalidUnitRawValues() {
        assertThrows(() -> QuantityMeasurementApp.add(1.0, null, 2.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.FEET),
                "Null source unit should throw IllegalArgumentException.");
        assertThrows(() -> QuantityMeasurementApp.add(1.0, QuantityMeasurementApp.LengthUnit.FEET, 2.0, null, QuantityMeasurementApp.LengthUnit.FEET),
                "Null second unit should throw IllegalArgumentException.");
    }

    private static void testBackwardCompatibility_UC6ImplicitAddition() {
        QuantityMeasurementApp.QuantityLength result = q(36.0, QuantityMeasurementApp.LengthUnit.INCHES)
                .add(q(1.0, QuantityMeasurementApp.LengthUnit.YARDS));
        assertApproximatelyEquals(72.0, result.getValue(), "UC6 instance add should remain in first operand unit.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.INCHES, "UC6 implicit result unit should remain inches.");
    }

    private static void testBackwardCompatibility_ConversionAndEquality() {
        double converted = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(12.0, converted, "UC5 conversion behavior should remain intact.");
        assertTrue(QuantityMeasurementApp.compareFeetAndInches(1.0, 12.0), "UC2 equality behavior should remain intact.");
    }
}
