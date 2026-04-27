public class QuantityMeasurementAppTest {
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testEquality_SameValue", QuantityMeasurementAppTest::testEquality_SameValue);
        runTest("testEquality_DifferentValue", QuantityMeasurementAppTest::testEquality_DifferentValue);
        runTest("testEquality_NullComparison", QuantityMeasurementAppTest::testEquality_NullComparison);
        runTest("testEquality_NonNumericInput", QuantityMeasurementAppTest::testEquality_NonNumericInput);
        runTest("testEquality_SameReference", QuantityMeasurementAppTest::testEquality_SameReference);
        runTest("testEquality_SymmetricProperty", QuantityMeasurementAppTest::testEquality_SymmetricProperty);
        runTest("testEquality_TransitiveProperty", QuantityMeasurementAppTest::testEquality_TransitiveProperty);
        runTest("testEquality_ConsistentProperty", QuantityMeasurementAppTest::testEquality_ConsistentProperty);

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

    private static void testEquality_SameValue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(first.equals(second), "Expected 1.0 ft to be equal to 1.0 ft.");
    }

    private static void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(first.equals(second), "Expected 1.0 ft to not be equal to 2.0 ft.");
    }

    private static void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(value.equals(null), "Expected comparison with null to return false.");
    }

    private static void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(value.equals("not-a-number"), "Expected comparison with non-Feet object to return false.");
    }

    private static void testEquality_SameReference() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(value.equals(value), "Expected same reference comparison to return true.");
    }

    private static void testEquality_SymmetricProperty() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(3.5);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(3.5);

        assertTrue(first.equals(second), "Expected first to equal second.");
        assertTrue(second.equals(first), "Expected second to equal first.");
    }

    private static void testEquality_TransitiveProperty() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(4.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(4.0);
        QuantityMeasurementApp.Feet third = new QuantityMeasurementApp.Feet(4.0);

        assertTrue(first.equals(second), "Expected first to equal second.");
        assertTrue(second.equals(third), "Expected second to equal third.");
        assertTrue(first.equals(third), "Expected first to equal third (transitive).");
    }

    private static void testEquality_ConsistentProperty() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(5.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(5.0);

        boolean firstResult = first.equals(second);
        boolean secondResult = first.equals(second);

        assertTrue(firstResult == secondResult, "Expected equals result to be consistent across calls.");
    }
}
