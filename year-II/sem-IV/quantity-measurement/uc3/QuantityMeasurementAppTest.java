public class QuantityMeasurementAppTest {
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testEquality_FeetToFeet_SameValue", QuantityMeasurementAppTest::testEquality_FeetToFeet_SameValue);
        runTest("testEquality_InchToInch_SameValue", QuantityMeasurementAppTest::testEquality_InchToInch_SameValue);
        runTest("testEquality_FeetToInch_EquivalentValue", QuantityMeasurementAppTest::testEquality_FeetToInch_EquivalentValue);
        runTest("testEquality_InchToFeet_EquivalentValue", QuantityMeasurementAppTest::testEquality_InchToFeet_EquivalentValue);
        runTest("testEquality_FeetToFeet_DifferentValue", QuantityMeasurementAppTest::testEquality_FeetToFeet_DifferentValue);
        runTest("testEquality_InchToInch_DifferentValue", QuantityMeasurementAppTest::testEquality_InchToInch_DifferentValue);
        runTest("testEquality_InvalidUnit", QuantityMeasurementAppTest::testEquality_InvalidUnit);
        runTest("testEquality_NullUnit", QuantityMeasurementAppTest::testEquality_NullUnit);
        runTest("testEquality_SameReference", QuantityMeasurementAppTest::testEquality_SameReference);
        runTest("testEquality_NullComparison", QuantityMeasurementAppTest::testEquality_NullComparison);
        runTest("testEquality_NonNumericInput", QuantityMeasurementAppTest::testEquality_NonNumericInput);
        runTest("testEquality_SymmetricProperty", QuantityMeasurementAppTest::testEquality_SymmetricProperty);
        runTest("testEquality_TransitiveProperty", QuantityMeasurementAppTest::testEquality_TransitiveProperty);
        runTest("testEquality_ConsistentProperty", QuantityMeasurementAppTest::testEquality_ConsistentProperty);

        runTest("testBackwardCompatibility_CompareFeet", QuantityMeasurementAppTest::testBackwardCompatibility_CompareFeet);
        runTest("testBackwardCompatibility_CompareInches", QuantityMeasurementAppTest::testBackwardCompatibility_CompareInches);
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

    private static void testEquality_FeetToFeet_SameValue() {
        QuantityMeasurementApp.QuantityLength first = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength second = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(first.equals(second), "Expected Quantity(1.0, feet) to equal Quantity(1.0, feet).");
    }

    private static void testEquality_InchToInch_SameValue() {
        QuantityMeasurementApp.QuantityLength first = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength second = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(first.equals(second), "Expected Quantity(1.0, inch) to equal Quantity(1.0, inch).");
    }

    private static void testEquality_FeetToInch_EquivalentValue() {
        QuantityMeasurementApp.QuantityLength feet = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength inches = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(feet.equals(inches), "Expected Quantity(1.0, feet) to equal Quantity(12.0, inch).");
    }

    private static void testEquality_InchToFeet_EquivalentValue() {
        QuantityMeasurementApp.QuantityLength inches = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength feet = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(inches.equals(feet), "Expected Quantity(12.0, inch) to equal Quantity(1.0, feet).");
    }

    private static void testEquality_FeetToFeet_DifferentValue() {
        QuantityMeasurementApp.QuantityLength first = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength second = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(first.equals(second), "Expected Quantity(1.0, feet) to not equal Quantity(2.0, feet).");
    }

    private static void testEquality_InchToInch_DifferentValue() {
        QuantityMeasurementApp.QuantityLength first = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength second = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertFalse(first.equals(second), "Expected Quantity(1.0, inch) to not equal Quantity(2.0, inch).");
    }

    private static void testEquality_InvalidUnit() {
        assertThrows(() -> QuantityMeasurementApp.QuantityLength.of(1.0, "meter"), "Expected invalid unit to throw IllegalArgumentException.");
    }

    private static void testEquality_NullUnit() {
        assertThrows(() -> new QuantityMeasurementApp.QuantityLength(1.0, null), "Expected null unit to throw IllegalArgumentException.");
    }

    private static void testEquality_SameReference() {
        QuantityMeasurementApp.QuantityLength value = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(value.equals(value), "Expected same reference comparison to return true.");
    }

    private static void testEquality_NullComparison() {
        QuantityMeasurementApp.QuantityLength value = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(value.equals(null), "Expected comparison with null to return false.");
    }

    private static void testEquality_NonNumericInput() {
        QuantityMeasurementApp.QuantityLength value = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(value.equals("not-a-number"), "Expected comparison with non-quantity object to return false.");
    }

    private static void testEquality_SymmetricProperty() {
        QuantityMeasurementApp.QuantityLength first = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength second = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(first.equals(second), "Expected first to equal second.");
        assertTrue(second.equals(first), "Expected second to equal first.");
    }

    private static void testEquality_TransitiveProperty() {
        QuantityMeasurementApp.QuantityLength first = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength second = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength third = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(first.equals(second), "Expected first to equal second.");
        assertTrue(second.equals(third), "Expected second to equal third.");
        assertTrue(first.equals(third), "Expected first to equal third (transitive).");
    }

    private static void testEquality_ConsistentProperty() {
        QuantityMeasurementApp.QuantityLength first = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength second = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        boolean firstResult = first.equals(second);
        boolean secondResult = first.equals(second);

        assertTrue(firstResult == secondResult, "Expected equals result to be consistent across calls.");
    }

    private static void testBackwardCompatibility_CompareFeet() {
        assertTrue(QuantityMeasurementApp.compareFeet(3.0, 3.0), "Expected compareFeet(3.0, 3.0) to return true.");
        assertFalse(QuantityMeasurementApp.compareFeet(3.0, 4.0), "Expected compareFeet(3.0, 4.0) to return false.");
    }

    private static void testBackwardCompatibility_CompareInches() {
        assertTrue(QuantityMeasurementApp.compareInches(8.0, 8.0), "Expected compareInches(8.0, 8.0) to return true.");
        assertFalse(QuantityMeasurementApp.compareInches(8.0, 9.0), "Expected compareInches(8.0, 9.0) to return false.");
    }

    private static void testBackwardCompatibility_CompareFeetAndInches() {
        assertTrue(QuantityMeasurementApp.compareFeetAndInches(1.0, 12.0), "Expected 1.0 ft to equal 12.0 inches.");
        assertFalse(QuantityMeasurementApp.compareFeetAndInches(1.0, 10.0), "Expected 1.0 ft to not equal 10.0 inches.");
    }
}
