public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testAddition_SameUnit_FeetPlusFeet", QuantityMeasurementAppTest::testAddition_SameUnit_FeetPlusFeet);
        runTest("testAddition_SameUnit_InchPlusInch", QuantityMeasurementAppTest::testAddition_SameUnit_InchPlusInch);
        runTest("testAddition_CrossUnit_FeetPlusInches", QuantityMeasurementAppTest::testAddition_CrossUnit_FeetPlusInches);
        runTest("testAddition_CrossUnit_InchPlusFeet", QuantityMeasurementAppTest::testAddition_CrossUnit_InchPlusFeet);
        runTest("testAddition_CrossUnit_YardPlusFeet", QuantityMeasurementAppTest::testAddition_CrossUnit_YardPlusFeet);
        runTest("testAddition_CrossUnit_CentimeterPlusInch", QuantityMeasurementAppTest::testAddition_CrossUnit_CentimeterPlusInch);
        runTest("testAddition_Commutativity", QuantityMeasurementAppTest::testAddition_Commutativity);
        runTest("testAddition_WithZero", QuantityMeasurementAppTest::testAddition_WithZero);
        runTest("testAddition_NegativeValues", QuantityMeasurementAppTest::testAddition_NegativeValues);
        runTest("testAddition_NullSecondOperand", QuantityMeasurementAppTest::testAddition_NullSecondOperand);
        runTest("testAddition_NullFirstOperand", QuantityMeasurementAppTest::testAddition_NullFirstOperand);
        runTest("testAddition_NullTargetUnit", QuantityMeasurementAppTest::testAddition_NullTargetUnit);
        runTest("testAddition_LargeValues", QuantityMeasurementAppTest::testAddition_LargeValues);
        runTest("testAddition_SmallValues", QuantityMeasurementAppTest::testAddition_SmallValues);
        runTest("testAddition_OverloadWithRawValues", QuantityMeasurementAppTest::testAddition_OverloadWithRawValues);
        runTest("testAddition_ResultInFirstOperandUnit_InstanceMethod", QuantityMeasurementAppTest::testAddition_ResultInFirstOperandUnit_InstanceMethod);
        runTest("testAddition_OriginalObjectsUnchanged", QuantityMeasurementAppTest::testAddition_OriginalObjectsUnchanged);
        runTest("testAddition_InvalidValue_Throws", QuantityMeasurementAppTest::testAddition_InvalidValue_Throws);
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

    private static void testAddition_SameUnit_FeetPlusFeet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(q(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                q(2.0, QuantityMeasurementApp.LengthUnit.FEET), QuantityMeasurementApp.LengthUnit.FEET);
        assertApproximatelyEquals(3.0, result.getValue(), "1ft + 2ft should be 3ft.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.FEET, "Result unit should be feet.");
    }

    private static void testAddition_SameUnit_InchPlusInch() {
        QuantityMeasurementApp.QuantityLength result = q(6.0, QuantityMeasurementApp.LengthUnit.INCHES)
                .add(q(6.0, QuantityMeasurementApp.LengthUnit.INCHES));
        assertApproximatelyEquals(12.0, result.getValue(), "6in + 6in should be 12in.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.INCHES, "Result unit should be inches.");
    }

    private static void testAddition_CrossUnit_FeetPlusInches() {
        QuantityMeasurementApp.QuantityLength result = q(1.0, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(12.0, QuantityMeasurementApp.LengthUnit.INCHES));
        assertApproximatelyEquals(2.0, result.getValue(), "1ft + 12in should be 2ft.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.FEET, "Result unit should be feet.");
    }

    private static void testAddition_CrossUnit_InchPlusFeet() {
        QuantityMeasurementApp.QuantityLength result = q(12.0, QuantityMeasurementApp.LengthUnit.INCHES)
                .add(q(1.0, QuantityMeasurementApp.LengthUnit.FEET));
        assertApproximatelyEquals(24.0, result.getValue(), "12in + 1ft should be 24in.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.INCHES, "Result unit should be inches.");
    }

    private static void testAddition_CrossUnit_YardPlusFeet() {
        QuantityMeasurementApp.QuantityLength result = q(1.0, QuantityMeasurementApp.LengthUnit.YARDS)
                .add(q(3.0, QuantityMeasurementApp.LengthUnit.FEET));
        assertApproximatelyEquals(2.0, result.getValue(), "1yd + 3ft should be 2yd.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.YARDS, "Result unit should be yards.");
    }

    private static void testAddition_CrossUnit_CentimeterPlusInch() {
        QuantityMeasurementApp.QuantityLength result = q(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS)
                .add(q(1.0, QuantityMeasurementApp.LengthUnit.INCHES));
        assertApproximatelyEquals(5.08, result.getValue(), "2.54cm + 1in should be about 5.08cm.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.CENTIMETERS, "Result unit should be centimeters.");
    }

    private static void testAddition_Commutativity() {
        QuantityMeasurementApp.QuantityLength a = q(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength b = q(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength sumAB = QuantityMeasurementApp.add(a, b, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength sumBA = QuantityMeasurementApp.add(b, a, QuantityMeasurementApp.LengthUnit.FEET);

        assertApproximatelyEquals(sumAB.getValue(), sumBA.getValue(), "Addition should be commutative for same target unit.");
    }

    private static void testAddition_WithZero() {
        QuantityMeasurementApp.QuantityLength result = q(5.0, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(0.0, QuantityMeasurementApp.LengthUnit.INCHES));
        assertApproximatelyEquals(5.0, result.getValue(), "5ft + 0in should be 5ft.");
    }

    private static void testAddition_NegativeValues() {
        QuantityMeasurementApp.QuantityLength result = q(5.0, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(-2.0, QuantityMeasurementApp.LengthUnit.FEET));
        assertApproximatelyEquals(3.0, result.getValue(), "5ft + (-2ft) should be 3ft.");
    }

    private static void testAddition_NullSecondOperand() {
        assertThrows(() -> q(1.0, QuantityMeasurementApp.LengthUnit.FEET).add(null),
                "Null second operand should throw IllegalArgumentException.");
    }

    private static void testAddition_NullFirstOperand() {
        assertThrows(() -> QuantityMeasurementApp.add(null, q(1.0, QuantityMeasurementApp.LengthUnit.FEET), QuantityMeasurementApp.LengthUnit.FEET),
                "Null first operand should throw IllegalArgumentException.");
    }

    private static void testAddition_NullTargetUnit() {
        assertThrows(() -> QuantityMeasurementApp.add(q(1.0, QuantityMeasurementApp.LengthUnit.FEET), q(1.0, QuantityMeasurementApp.LengthUnit.FEET), null),
                "Null target unit should throw IllegalArgumentException.");
    }

    private static void testAddition_LargeValues() {
        QuantityMeasurementApp.QuantityLength result = q(1_000_000.0, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(1_000_000.0, QuantityMeasurementApp.LengthUnit.FEET));
        assertApproximatelyEquals(2_000_000.0, result.getValue(), "Large values should add correctly.");
    }

    private static void testAddition_SmallValues() {
        QuantityMeasurementApp.QuantityLength result = q(0.001, QuantityMeasurementApp.LengthUnit.FEET)
                .add(q(0.002, QuantityMeasurementApp.LengthUnit.FEET));
        assertApproximatelyEquals(0.003, result.getValue(), "Small values should add correctly.");
    }

    private static void testAddition_OverloadWithRawValues() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(1.0, QuantityMeasurementApp.LengthUnit.FEET,
                12.0, QuantityMeasurementApp.LengthUnit.INCHES, QuantityMeasurementApp.LengthUnit.FEET);
        assertApproximatelyEquals(2.0, result.getValue(), "Raw value overload should convert and add correctly.");
    }

    private static void testAddition_ResultInFirstOperandUnit_InstanceMethod() {
        QuantityMeasurementApp.QuantityLength first = q(36.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.QuantityLength second = q(1.0, QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.QuantityLength result = first.add(second);

        assertApproximatelyEquals(72.0, result.getValue(), "36in + 1yd should be 72in when first unit is inches.");
        assertTrue(result.getUnit() == QuantityMeasurementApp.LengthUnit.INCHES, "Instance add should return first operand unit.");
    }

    private static void testAddition_OriginalObjectsUnchanged() {
        QuantityMeasurementApp.QuantityLength first = q(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength second = q(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result = first.add(second);

        assertApproximatelyEquals(1.0, first.getValue(), "First operand should remain unchanged.");
        assertTrue(first.getUnit() == QuantityMeasurementApp.LengthUnit.FEET, "First unit should remain unchanged.");
        assertApproximatelyEquals(12.0, second.getValue(), "Second operand should remain unchanged.");
        assertTrue(second.getUnit() == QuantityMeasurementApp.LengthUnit.INCHES, "Second unit should remain unchanged.");
        assertApproximatelyEquals(2.0, result.getValue(), "Result should be a new computed object.");
    }

    private static void testAddition_InvalidValue_Throws() {
        assertThrows(() -> new QuantityMeasurementApp.QuantityLength(Double.NaN, QuantityMeasurementApp.LengthUnit.FEET),
                "NaN quantity should throw IllegalArgumentException.");
        assertThrows(() -> new QuantityMeasurementApp.QuantityLength(Double.POSITIVE_INFINITY, QuantityMeasurementApp.LengthUnit.FEET),
                "Infinite quantity should throw IllegalArgumentException.");
    }

    private static void testBackwardCompatibility_ConversionAndEquality() {
        double converted = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertApproximatelyEquals(12.0, converted, "UC5 conversion behavior should remain intact.");
        assertTrue(QuantityMeasurementApp.compareFeetAndInches(1.0, 12.0), "UC2 equality behavior should remain intact.");
    }
}
