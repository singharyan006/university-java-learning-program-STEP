public class QuantityMeasurementAppTest {
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testFeetEquality_SameValue", QuantityMeasurementAppTest::testFeetEquality_SameValue);
        runTest("testFeetEquality_DifferentValue", QuantityMeasurementAppTest::testFeetEquality_DifferentValue);
        runTest("testFeetEquality_NullComparison", QuantityMeasurementAppTest::testFeetEquality_NullComparison);
        runTest("testFeetEquality_NonNumericInput", QuantityMeasurementAppTest::testFeetEquality_NonNumericInput);
        runTest("testFeetEquality_SameReference", QuantityMeasurementAppTest::testFeetEquality_SameReference);

        runTest("testInchesEquality_SameValue", QuantityMeasurementAppTest::testInchesEquality_SameValue);
        runTest("testInchesEquality_DifferentValue", QuantityMeasurementAppTest::testInchesEquality_DifferentValue);
        runTest("testInchesEquality_NullComparison", QuantityMeasurementAppTest::testInchesEquality_NullComparison);
        runTest("testInchesEquality_NonNumericInput", QuantityMeasurementAppTest::testInchesEquality_NonNumericInput);
        runTest("testInchesEquality_SameReference", QuantityMeasurementAppTest::testInchesEquality_SameReference);

        runTest("testCompareFeet_StaticMethod", QuantityMeasurementAppTest::testCompareFeet_StaticMethod);
        runTest("testCompareInches_StaticMethod", QuantityMeasurementAppTest::testCompareInches_StaticMethod);
        runTest("testCompareFeetAndInches_Equivalent", QuantityMeasurementAppTest::testCompareFeetAndInches_Equivalent);
        runTest("testCompareFeetAndInches_NotEquivalent", QuantityMeasurementAppTest::testCompareFeetAndInches_NotEquivalent);

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

    private static void testFeetEquality_SameValue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(first.equals(second), "Expected 1.0 ft to be equal to 1.0 ft.");
    }

    private static void testFeetEquality_DifferentValue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(first.equals(second), "Expected 1.0 ft to not be equal to 2.0 ft.");
    }

    private static void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(value.equals(null), "Expected feet comparison with null to return false.");
    }

    private static void testFeetEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(value.equals("not-a-number"), "Expected feet comparison with non-Feet object to return false.");
    }

    private static void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(value.equals(value), "Expected same Feet reference comparison to return true.");
    }

    private static void testInchesEquality_SameValue() {
        QuantityMeasurementApp.Inches first = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches second = new QuantityMeasurementApp.Inches(1.0);

        assertTrue(first.equals(second), "Expected 1.0 inch to be equal to 1.0 inch.");
    }

    private static void testInchesEquality_DifferentValue() {
        QuantityMeasurementApp.Inches first = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches second = new QuantityMeasurementApp.Inches(2.0);

        assertFalse(first.equals(second), "Expected 1.0 inch to not be equal to 2.0 inch.");
    }

    private static void testInchesEquality_NullComparison() {
        QuantityMeasurementApp.Inches value = new QuantityMeasurementApp.Inches(1.0);

        assertFalse(value.equals(null), "Expected inches comparison with null to return false.");
    }

    private static void testInchesEquality_NonNumericInput() {
        QuantityMeasurementApp.Inches value = new QuantityMeasurementApp.Inches(1.0);

        assertFalse(value.equals("not-a-number"), "Expected inches comparison with non-Inches object to return false.");
    }

    private static void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches value = new QuantityMeasurementApp.Inches(1.0);

        assertTrue(value.equals(value), "Expected same Inches reference comparison to return true.");
    }

    private static void testCompareFeet_StaticMethod() {
        assertTrue(QuantityMeasurementApp.compareFeet(3.0, 3.0), "Expected compareFeet(3.0, 3.0) to return true.");
        assertFalse(QuantityMeasurementApp.compareFeet(3.0, 4.0), "Expected compareFeet(3.0, 4.0) to return false.");
    }

    private static void testCompareInches_StaticMethod() {
        assertTrue(QuantityMeasurementApp.compareInches(8.0, 8.0), "Expected compareInches(8.0, 8.0) to return true.");
        assertFalse(QuantityMeasurementApp.compareInches(8.0, 9.0), "Expected compareInches(8.0, 9.0) to return false.");
    }

    private static void testCompareFeetAndInches_Equivalent() {
        assertTrue(QuantityMeasurementApp.compareFeetAndInches(1.0, 12.0), "Expected 1.0 ft to equal 12.0 inches.");
    }

    private static void testCompareFeetAndInches_NotEquivalent() {
        assertFalse(QuantityMeasurementApp.compareFeetAndInches(1.0, 10.0), "Expected 1.0 ft to not equal 10.0 inches.");
    }
}
