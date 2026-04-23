public class QuantityMeasurementAppUC12Test {
    private static final double EPSILON = 1e-6;
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testSubtraction_SameUnit_FeetMinusFeet", QuantityMeasurementAppUC12Test::testSubtraction_SameUnit_FeetMinusFeet);
        runTest("testSubtraction_SameUnit_LitreMinusLitre", QuantityMeasurementAppUC12Test::testSubtraction_SameUnit_LitreMinusLitre);
        runTest("testSubtraction_CrossUnit_FeetMinusInches", QuantityMeasurementAppUC12Test::testSubtraction_CrossUnit_FeetMinusInches);
        runTest("testSubtraction_CrossUnit_InchesMinusFeet", QuantityMeasurementAppUC12Test::testSubtraction_CrossUnit_InchesMinusFeet);
        runTest("testSubtraction_ExplicitTargetUnit_Feet", QuantityMeasurementAppUC12Test::testSubtraction_ExplicitTargetUnit_Feet);
        runTest("testSubtraction_ExplicitTargetUnit_Inches", QuantityMeasurementAppUC12Test::testSubtraction_ExplicitTargetUnit_Inches);
        runTest("testSubtraction_ExplicitTargetUnit_Millilitre", QuantityMeasurementAppUC12Test::testSubtraction_ExplicitTargetUnit_Millilitre);
        runTest("testSubtraction_ResultingInNegative", QuantityMeasurementAppUC12Test::testSubtraction_ResultingInNegative);
        runTest("testSubtraction_ResultingInZero", QuantityMeasurementAppUC12Test::testSubtraction_ResultingInZero);
        runTest("testSubtraction_WithZeroOperand", QuantityMeasurementAppUC12Test::testSubtraction_WithZeroOperand);
        runTest("testSubtraction_WithNegativeValues", QuantityMeasurementAppUC12Test::testSubtraction_WithNegativeValues);
        runTest("testSubtraction_NonCommutative", QuantityMeasurementAppUC12Test::testSubtraction_NonCommutative);
        runTest("testSubtraction_WithLargeValues", QuantityMeasurementAppUC12Test::testSubtraction_WithLargeValues);
        runTest("testSubtraction_WithSmallValues", QuantityMeasurementAppUC12Test::testSubtraction_WithSmallValues);
        runTest("testSubtraction_NullOperand", QuantityMeasurementAppUC12Test::testSubtraction_NullOperand);
        runTest("testSubtraction_NullTargetUnit", QuantityMeasurementAppUC12Test::testSubtraction_NullTargetUnit);
        runTest("testSubtraction_CrossCategory", QuantityMeasurementAppUC12Test::testSubtraction_CrossCategory);
        runTest("testSubtraction_AllMeasurementCategories", QuantityMeasurementAppUC12Test::testSubtraction_AllMeasurementCategories);
        runTest("testSubtraction_ChainedOperations", QuantityMeasurementAppUC12Test::testSubtraction_ChainedOperations);
        runTest("testDivision_SameUnit_FeetDividedByFeet", QuantityMeasurementAppUC12Test::testDivision_SameUnit_FeetDividedByFeet);
        runTest("testDivision_SameUnit_LitreDividedByLitre", QuantityMeasurementAppUC12Test::testDivision_SameUnit_LitreDividedByLitre);
        runTest("testDivision_CrossUnit_FeetDividedByInches", QuantityMeasurementAppUC12Test::testDivision_CrossUnit_FeetDividedByInches);
        runTest("testDivision_CrossUnit_KilogramDividedByGram", QuantityMeasurementAppUC12Test::testDivision_CrossUnit_KilogramDividedByGram);
        runTest("testDivision_RatioGreaterThanOne", QuantityMeasurementAppUC12Test::testDivision_RatioGreaterThanOne);
        runTest("testDivision_RatioLessThanOne", QuantityMeasurementAppUC12Test::testDivision_RatioLessThanOne);
        runTest("testDivision_RatioEqualToOne", QuantityMeasurementAppUC12Test::testDivision_RatioEqualToOne);
        runTest("testDivision_NonCommutative", QuantityMeasurementAppUC12Test::testDivision_NonCommutative);
        runTest("testDivision_ByZero", QuantityMeasurementAppUC12Test::testDivision_ByZero);
        runTest("testDivision_WithLargeRatio", QuantityMeasurementAppUC12Test::testDivision_WithLargeRatio);
        runTest("testDivision_WithSmallRatio", QuantityMeasurementAppUC12Test::testDivision_WithSmallRatio);
        runTest("testDivision_NullOperand", QuantityMeasurementAppUC12Test::testDivision_NullOperand);
        runTest("testDivision_CrossCategory", QuantityMeasurementAppUC12Test::testDivision_CrossCategory);
        runTest("testDivision_AllMeasurementCategories", QuantityMeasurementAppUC12Test::testDivision_AllMeasurementCategories);
        runTest("testDivision_Associativity", QuantityMeasurementAppUC12Test::testDivision_Associativity);
        runTest("testSubtractionAndDivision_Integration", QuantityMeasurementAppUC12Test::testSubtractionAndDivision_Integration);
        runTest("testSubtractionAddition_Inverse", QuantityMeasurementAppUC12Test::testSubtractionAddition_Inverse);
        runTest("testSubtraction_Immutability", QuantityMeasurementAppUC12Test::testSubtraction_Immutability);
        runTest("testDivision_Immutability", QuantityMeasurementAppUC12Test::testDivision_Immutability);
        runTest("testSubtraction_PrecisionAndRounding", QuantityMeasurementAppUC12Test::testSubtraction_PrecisionAndRounding);
        runTest("testDivision_PrecisionHandling", QuantityMeasurementAppUC12Test::testDivision_PrecisionHandling);

        System.out.println("\nUC12 Test Summary: " + passedTests + "/" + totalTests + " passed");
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
            throw new AssertionError(message + " - Expected: " + expected + ", Actual: " + actual);
        }
    }
    
    private static void assertEquals(Object expected, Object actual, String message) {
        if (expected == null && actual == null) return;
        if (expected == null || !expected.equals(actual)) {
            throw new AssertionError(message + " - Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void assertNotEquals(Object expected, Object actual, String message) {
        if (expected != null && expected.equals(actual)) {
            throw new AssertionError(message + " - Expected not to equal: " + actual);
        }
    }

    private static void assertNotApproximatelyEquals(double expected, double actual, String message) {
        if (Math.abs(expected - actual) <= EPSILON) {
            throw new AssertionError(message + " - Expected not to approximately equal: " + expected + ", Actual: " + actual);
        }
    }

    public static void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result, "10 ft - 5 ft should be 5 ft");
    }

    public static void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> result = new Quantity<>(10.0, VolumeUnit.LITRE).subtract(new Quantity<>(3.0, VolumeUnit.LITRE));
        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), result, "10 L - 3 L should be 7 L");
    }

    public static void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES));
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result, "10 ft - 6 in should be 9.5 ft");
    }

    public static void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> result = new Quantity<>(120.0, LengthUnit.INCHES).subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), result, "120 in - 5 ft should be 60 in");
    }

    public static void testSubtraction_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result, "10 ft - 6 in in feet should be 9.5 ft");
    }

    public static void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES);
        assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result, "10 ft - 6 in in inches should be 114 in");
    }

    public static void testSubtraction_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result = new Quantity<>(5.0, VolumeUnit.LITRE).subtract(new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);
        assertEquals(new Quantity<>(3000.0, VolumeUnit.MILLILITRE), result, "5 L - 2 L in ml should be 3000 ml");
    }

    public static void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result, "5 ft - 10 ft should be -5 ft");
    }

    public static void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(120.0, LengthUnit.INCHES));
        assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result, "10 ft - 120 in should be 0 ft");
    }

    public static void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(0.0, LengthUnit.INCHES));
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result, "5 ft - 0 in should be 5 ft");
    }

    public static void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(-2.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result, "5 ft - (-2 ft) should be 7 ft");
    }

    public static void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> res1 = a.subtract(b);
        Quantity<LengthUnit> res2 = b.subtract(a);
        assertNotEquals(res1, res2, "A - B != B - A");
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), res1, "A - B");
        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), res2, "B - A");
    }

    public static void testSubtraction_WithLargeValues() {
        Quantity<WeightUnit> result = new Quantity<>(1e6, WeightUnit.KILOGRAM).subtract(new Quantity<>(5e5, WeightUnit.KILOGRAM));
        assertEquals(new Quantity<>(5e5, WeightUnit.KILOGRAM), result, "1e6 kg - 5e5 kg should be 5e5 kg");
    }

    public static void testSubtraction_WithSmallValues() {
        Quantity<LengthUnit> result = new Quantity<>(0.001, LengthUnit.FEET).subtract(new Quantity<>(0.0005, LengthUnit.FEET));
        assertEquals(new Quantity<>(0.0005, LengthUnit.FEET), result, "0.001 ft - 0.0005 ft should be 0.0005 ft");
    }

    public static void testSubtraction_NullOperand() {
        try {
            new Quantity<>(10.0, LengthUnit.FEET).subtract(null);
            throw new AssertionError("Should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    public static void testSubtraction_NullTargetUnit() {
        try {
            new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(5.0, LengthUnit.FEET), null);
            throw new AssertionError("Should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    public static void testSubtraction_CrossCategory() {
        try {
            Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
            Quantity mixed = new Quantity<>(5.0, WeightUnit.KILOGRAM);
            length.subtract(mixed);
            throw new AssertionError("Should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    public static void testSubtraction_AllMeasurementCategories() {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(5.0, LengthUnit.FEET);
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), l1.subtract(l2), "Length sub");

        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertEquals(new Quantity<>(5.0, WeightUnit.KILOGRAM), w1.subtract(w2), "Weight sub");

        Quantity<VolumeUnit> v1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(5.0, VolumeUnit.LITRE);
        assertEquals(new Quantity<>(5.0, VolumeUnit.LITRE), v1.subtract(v2), "Volume sub");
    }

    public static void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
            .subtract(new Quantity<>(2.0, LengthUnit.FEET))
            .subtract(new Quantity<>(1.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result, "Chained subtractions");
    }

    public static void testDivision_SameUnit_FeetDividedByFeet() {
        double result = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertApproximatelyEquals(5.0, result, "10 / 2 = 5");
    }

    public static void testDivision_SameUnit_LitreDividedByLitre() {
        double result = new Quantity<>(10.0, VolumeUnit.LITRE).divide(new Quantity<>(5.0, VolumeUnit.LITRE));
        assertApproximatelyEquals(2.0, result, "10 / 5 = 2");
    }

    public static void testDivision_CrossUnit_FeetDividedByInches() {
        double result = new Quantity<>(24.0, LengthUnit.INCHES).divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertApproximatelyEquals(1.0, result, "24 in / 2 ft = 1.0");
    }

    public static void testDivision_CrossUnit_KilogramDividedByGram() {
        double result = new Quantity<>(2.0, WeightUnit.KILOGRAM).divide(new Quantity<>(2000.0, WeightUnit.GRAM));
        assertApproximatelyEquals(1.0, result, "2 kg / 2000 g = 1.0");
    }

    public static void testDivision_RatioGreaterThanOne() {
        double result = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertApproximatelyEquals(5.0, result, "10 ft / 2 ft = 5");
    }

    public static void testDivision_RatioLessThanOne() {
        double result = new Quantity<>(5.0, LengthUnit.FEET).divide(new Quantity<>(10.0, LengthUnit.FEET));
        assertApproximatelyEquals(0.5, result, "5 ft / 10 ft = 0.5");
    }

    public static void testDivision_RatioEqualToOne() {
        double result = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(10.0, LengthUnit.FEET));
        assertApproximatelyEquals(1.0, result, "10 ft / 10 ft = 1");
    }

    public static void testDivision_NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
        double res1 = a.divide(b);
        double res2 = b.divide(a);
        assertNotApproximatelyEquals(res1, res2, "A / B != B / A");
        assertApproximatelyEquals(2.0, res1, "A / B = 2.0");
        assertApproximatelyEquals(0.5, res2, "B / A = 0.5");
    }

    public static void testDivision_ByZero() {
        try {
            new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(0.0, LengthUnit.FEET));
            throw new AssertionError("Should throw ArithmeticException");
        } catch (ArithmeticException e) {
        }
    }

    public static void testDivision_WithLargeRatio() {
        double result = new Quantity<>(1e6, WeightUnit.KILOGRAM).divide(new Quantity<>(1.0, WeightUnit.KILOGRAM));
        assertApproximatelyEquals(1e6, result, "Large ratio");
    }

    public static void testDivision_WithSmallRatio() {
        double result = new Quantity<>(1.0, WeightUnit.KILOGRAM).divide(new Quantity<>(1e6, WeightUnit.KILOGRAM));
        assertApproximatelyEquals(1e-6, result, "Small ratio");
    }

    public static void testDivision_NullOperand() {
        try {
            new Quantity<>(10.0, LengthUnit.FEET).divide(null);
            throw new AssertionError("Should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    public static void testDivision_CrossCategory() {
        try {
            Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
            Quantity mixed = new Quantity<>(5.0, WeightUnit.KILOGRAM);
            length.divide(mixed);
            throw new AssertionError("Should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    public static void testDivision_AllMeasurementCategories() {
        double lRes = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(5.0, LengthUnit.FEET));
        assertApproximatelyEquals(2.0, lRes, "Length ratio");

        double wRes = new Quantity<>(10.0, WeightUnit.KILOGRAM).divide(new Quantity<>(5.0, WeightUnit.KILOGRAM));
        assertApproximatelyEquals(2.0, wRes, "Weight ratio");

        double vRes = new Quantity<>(10.0, VolumeUnit.LITRE).divide(new Quantity<>(5.0, VolumeUnit.LITRE));
        assertApproximatelyEquals(2.0, vRes, "Volume ratio");
    }

    public static void testDivision_Associativity() {
        Quantity<LengthUnit> a = new Quantity<>(20.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(4.0, LengthUnit.FEET);
        Quantity<LengthUnit> c = new Quantity<>(2.0, LengthUnit.FEET);
        double leftFirst = a.divide(b) / c.getValue(); 
        double rightFirst = a.getValue() / (b.divide(c));
        assertNotApproximatelyEquals(leftFirst, rightFirst, "A/(B/C) != (A/B)/C");
    }

    public static void testSubtractionAndDivision_Integration() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(4.0, LengthUnit.FEET);
        Quantity<LengthUnit> c = new Quantity<>(2.0, LengthUnit.FEET);
        double result = a.subtract(b).divide(c);
        assertApproximatelyEquals(3.0, result, "(10-4)/2 = 3");
    }

    public static void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(4.0, LengthUnit.FEET);
        Quantity<LengthUnit> res = a.add(b).subtract(b);
        assertEquals(a, res, "(A+B)-B = A");
    }

    public static void testSubtraction_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(4.0, LengthUnit.FEET);
        a.subtract(b);
        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a, "a remains");
        assertEquals(new Quantity<>(4.0, LengthUnit.FEET), b, "b remains");
    }

    public static void testDivision_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(4.0, LengthUnit.FEET);
        a.divide(b);
        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a, "a remains");
        assertEquals(new Quantity<>(4.0, LengthUnit.FEET), b, "b remains");
    }

    public static void testSubtraction_PrecisionAndRounding() {
        Quantity<LengthUnit> result = new Quantity<>(5.1234, LengthUnit.FEET).subtract(new Quantity<>(2.1234, LengthUnit.FEET));
        assertEquals(new Quantity<>(3.00, LengthUnit.FEET), result, "Should preserve or round properly");
    }

    public static void testDivision_PrecisionHandling() {
        double result = new Quantity<>(1.0, LengthUnit.FEET).divide(new Quantity<>(3.0, LengthUnit.FEET));
        assertApproximatelyEquals(0.33333333333, result, "Should keep high precision");
    }
}
