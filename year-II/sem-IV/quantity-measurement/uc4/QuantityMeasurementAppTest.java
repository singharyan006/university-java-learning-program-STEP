public class QuantityMeasurementAppTest {
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testEquality_YardToYard_SameValue", QuantityMeasurementAppTest::testEquality_YardToYard_SameValue);
        runTest("testEquality_YardToYard_DifferentValue", QuantityMeasurementAppTest::testEquality_YardToYard_DifferentValue);
        runTest("testEquality_YardToFeet_EquivalentValue", QuantityMeasurementAppTest::testEquality_YardToFeet_EquivalentValue);
        runTest("testEquality_FeetToYard_EquivalentValue", QuantityMeasurementAppTest::testEquality_FeetToYard_EquivalentValue);
        runTest("testEquality_YardToInches_EquivalentValue", QuantityMeasurementAppTest::testEquality_YardToInches_EquivalentValue);
        runTest("testEquality_InchesToYard_EquivalentValue", QuantityMeasurementAppTest::testEquality_InchesToYard_EquivalentValue);
        runTest("testEquality_YardToFeet_NonEquivalentValue", QuantityMeasurementAppTest::testEquality_YardToFeet_NonEquivalentValue);

        runTest("testEquality_CentimetersToCentimeters_SameValue", QuantityMeasurementAppTest::testEquality_CentimetersToCentimeters_SameValue);
        runTest("testEquality_CentimetersToCentimeters_DifferentValue", QuantityMeasurementAppTest::testEquality_CentimetersToCentimeters_DifferentValue);
        runTest("testEquality_CentimetersToInches_EquivalentValue", QuantityMeasurementAppTest::testEquality_CentimetersToInches_EquivalentValue);
        runTest("testEquality_InchesToCentimeters_EquivalentValue", QuantityMeasurementAppTest::testEquality_InchesToCentimeters_EquivalentValue);
        runTest("testEquality_CentimetersToFeet_NonEquivalentValue", QuantityMeasurementAppTest::testEquality_CentimetersToFeet_NonEquivalentValue);

        runTest("testEquality_MultiUnit_TransitiveProperty", QuantityMeasurementAppTest::testEquality_MultiUnit_TransitiveProperty);
        runTest("testEquality_AllUnits_ComplexScenario", QuantityMeasurementAppTest::testEquality_AllUnits_ComplexScenario);

        runTest("testEquality_YardWithNullUnit", QuantityMeasurementAppTest::testEquality_YardWithNullUnit);
        runTest("testEquality_CentimetersWithNullUnit", QuantityMeasurementAppTest::testEquality_CentimetersWithNullUnit);
        runTest("testEquality_YardSameReference", QuantityMeasurementAppTest::testEquality_YardSameReference);
        runTest("testEquality_CentimetersSameReference", QuantityMeasurementAppTest::testEquality_CentimetersSameReference);
        runTest("testEquality_YardNullComparison", QuantityMeasurementAppTest::testEquality_YardNullComparison);
        runTest("testEquality_CentimetersNullComparison", QuantityMeasurementAppTest::testEquality_CentimetersNullComparison);
        runTest("testEquality_InvalidUnit", QuantityMeasurementAppTest::testEquality_InvalidUnit);
        runTest("testEquality_NonNumericInput", QuantityMeasurementAppTest::testEquality_NonNumericInput);

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

    private static QuantityMeasurementApp.QuantityLength q(double value, QuantityMeasurementApp.LengthUnit unit) {
        return new QuantityMeasurementApp.QuantityLength(value, unit);
    }

    private static void testEquality_YardToYard_SameValue() {
        assertTrue(q(1.0, QuantityMeasurementApp.LengthUnit.YARDS).equals(q(1.0, QuantityMeasurementApp.LengthUnit.YARDS)),
                "Expected Quantity(1.0, YARDS) to equal Quantity(1.0, YARDS).");
    }

    private static void testEquality_YardToYard_DifferentValue() {
        assertFalse(q(1.0, QuantityMeasurementApp.LengthUnit.YARDS).equals(q(2.0, QuantityMeasurementApp.LengthUnit.YARDS)),
                "Expected Quantity(1.0, YARDS) to not equal Quantity(2.0, YARDS).");
    }

    private static void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(q(1.0, QuantityMeasurementApp.LengthUnit.YARDS).equals(q(3.0, QuantityMeasurementApp.LengthUnit.FEET)),
                "Expected Quantity(1.0, YARDS) to equal Quantity(3.0, FEET).");
    }

    private static void testEquality_FeetToYard_EquivalentValue() {
        assertTrue(q(3.0, QuantityMeasurementApp.LengthUnit.FEET).equals(q(1.0, QuantityMeasurementApp.LengthUnit.YARDS)),
                "Expected Quantity(3.0, FEET) to equal Quantity(1.0, YARDS).");
    }

    private static void testEquality_YardToInches_EquivalentValue() {
        assertTrue(q(1.0, QuantityMeasurementApp.LengthUnit.YARDS).equals(q(36.0, QuantityMeasurementApp.LengthUnit.INCHES)),
                "Expected Quantity(1.0, YARDS) to equal Quantity(36.0, INCHES).");
    }

    private static void testEquality_InchesToYard_EquivalentValue() {
        assertTrue(q(36.0, QuantityMeasurementApp.LengthUnit.INCHES).equals(q(1.0, QuantityMeasurementApp.LengthUnit.YARDS)),
                "Expected Quantity(36.0, INCHES) to equal Quantity(1.0, YARDS).");
    }

    private static void testEquality_YardToFeet_NonEquivalentValue() {
        assertFalse(q(1.0, QuantityMeasurementApp.LengthUnit.YARDS).equals(q(2.0, QuantityMeasurementApp.LengthUnit.FEET)),
                "Expected Quantity(1.0, YARDS) to not equal Quantity(2.0, FEET).");
    }

    private static void testEquality_CentimetersToCentimeters_SameValue() {
        assertTrue(q(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS).equals(q(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS)),
                "Expected Quantity(2.0, CENTIMETERS) to equal Quantity(2.0, CENTIMETERS).");
    }

    private static void testEquality_CentimetersToCentimeters_DifferentValue() {
        assertFalse(q(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS).equals(q(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS)),
                "Expected Quantity(1.0, CENTIMETERS) to not equal Quantity(2.0, CENTIMETERS).");
    }

    private static void testEquality_CentimetersToInches_EquivalentValue() {
        assertTrue(q(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS).equals(q(0.393701, QuantityMeasurementApp.LengthUnit.INCHES)),
                "Expected Quantity(1.0, CENTIMETERS) to equal Quantity(0.393701, INCHES).");
    }

    private static void testEquality_InchesToCentimeters_EquivalentValue() {
        assertTrue(q(0.393701, QuantityMeasurementApp.LengthUnit.INCHES).equals(q(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS)),
                "Expected Quantity(0.393701, INCHES) to equal Quantity(1.0, CENTIMETERS).");
    }

    private static void testEquality_CentimetersToFeet_NonEquivalentValue() {
        assertFalse(q(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS).equals(q(1.0, QuantityMeasurementApp.LengthUnit.FEET)),
                "Expected Quantity(1.0, CENTIMETERS) to not equal Quantity(1.0, FEET).");
    }

    private static void testEquality_MultiUnit_TransitiveProperty() {
        QuantityMeasurementApp.QuantityLength yard = q(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength feet = q(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength inches = q(36.0, QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(yard.equals(feet), "Expected yard to equal feet.");
        assertTrue(feet.equals(inches), "Expected feet to equal inches.");
        assertTrue(yard.equals(inches), "Expected yard to equal inches through transitive property.");
    }

    private static void testEquality_AllUnits_ComplexScenario() {
        QuantityMeasurementApp.QuantityLength yards = q(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength feet = q(6.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength inches = q(72.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.QuantityLength centimeters = q(182.88, QuantityMeasurementApp.LengthUnit.CENTIMETERS);

        assertTrue(yards.equals(feet), "Expected 2 yards to equal 6 feet.");
        assertTrue(feet.equals(inches), "Expected 6 feet to equal 72 inches.");
        assertTrue(inches.equals(centimeters), "Expected 72 inches to equal 182.88 centimeters.");
        assertTrue(yards.equals(centimeters), "Expected 2 yards to equal 182.88 centimeters.");
    }

    private static void testEquality_YardWithNullUnit() {
        assertThrows(() -> new QuantityMeasurementApp.QuantityLength(1.0, null),
                "Expected null yard unit to throw IllegalArgumentException.");
    }

    private static void testEquality_CentimetersWithNullUnit() {
        assertThrows(() -> new QuantityMeasurementApp.QuantityLength(1.0, null),
                "Expected null centimeter unit to throw IllegalArgumentException.");
    }

    private static void testEquality_YardSameReference() {
        QuantityMeasurementApp.QuantityLength yard = q(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertTrue(yard.equals(yard), "Expected yard to be equal to itself.");
    }

    private static void testEquality_CentimetersSameReference() {
        QuantityMeasurementApp.QuantityLength centimeters = q(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertTrue(centimeters.equals(centimeters), "Expected centimeters to be equal to itself.");
    }

    private static void testEquality_YardNullComparison() {
        QuantityMeasurementApp.QuantityLength yard = q(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertFalse(yard.equals(null), "Expected yard comparison with null to return false.");
    }

    private static void testEquality_CentimetersNullComparison() {
        QuantityMeasurementApp.QuantityLength centimeters = q(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertFalse(centimeters.equals(null), "Expected centimeters comparison with null to return false.");
    }

    private static void testEquality_InvalidUnit() {
        assertThrows(() -> QuantityMeasurementApp.QuantityLength.of(1.0, "meter"),
                "Expected invalid unit to throw IllegalArgumentException.");
    }

    private static void testEquality_NonNumericInput() {
        QuantityMeasurementApp.QuantityLength value = q(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertFalse(value.equals("not-a-number"), "Expected comparison with non-quantity object to return false.");
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
