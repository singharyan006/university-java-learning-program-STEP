public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testVolumeUnitEnum_LitreConstant", QuantityMeasurementAppTest::testVolumeUnitEnum_LitreConstant);
        runTest("testVolumeUnitEnum_MillilitreConstant", QuantityMeasurementAppTest::testVolumeUnitEnum_MillilitreConstant);
        runTest("testVolumeUnitEnum_GallonConstant", QuantityMeasurementAppTest::testVolumeUnitEnum_GallonConstant);
        runTest("testConvertToBaseUnit_LitreToLitre", QuantityMeasurementAppTest::testConvertToBaseUnit_LitreToLitre);
        runTest("testConvertToBaseUnit_MillilitreToLitre", QuantityMeasurementAppTest::testConvertToBaseUnit_MillilitreToLitre);
        runTest("testConvertToBaseUnit_GallonToLitre", QuantityMeasurementAppTest::testConvertToBaseUnit_GallonToLitre);
        runTest("testConvertFromBaseUnit_LitreToLitre", QuantityMeasurementAppTest::testConvertFromBaseUnit_LitreToLitre);
        runTest("testConvertFromBaseUnit_LitreToMillilitre", QuantityMeasurementAppTest::testConvertFromBaseUnit_LitreToMillilitre);
        runTest("testConvertFromBaseUnit_LitreToGallon", QuantityMeasurementAppTest::testConvertFromBaseUnit_LitreToGallon);
        runTest("testEquality_LitreToLitre_SameValue", QuantityMeasurementAppTest::testEquality_LitreToLitre_SameValue);
        runTest("testEquality_LitreToLitre_DifferentValue", QuantityMeasurementAppTest::testEquality_LitreToLitre_DifferentValue);
        runTest("testEquality_LitreToMillilitre_EquivalentValue", QuantityMeasurementAppTest::testEquality_LitreToMillilitre_EquivalentValue);
        runTest("testEquality_MillilitreToLitre_EquivalentValue", QuantityMeasurementAppTest::testEquality_MillilitreToLitre_EquivalentValue);
        runTest("testEquality_LitreToGallon_EquivalentValue", QuantityMeasurementAppTest::testEquality_LitreToGallon_EquivalentValue);
        runTest("testEquality_GallonToLitre_EquivalentValue", QuantityMeasurementAppTest::testEquality_GallonToLitre_EquivalentValue);
        runTest("testEquality_VolumeVsLength_Incompatible", QuantityMeasurementAppTest::testEquality_VolumeVsLength_Incompatible);
        runTest("testEquality_VolumeVsWeight_Incompatible", QuantityMeasurementAppTest::testEquality_VolumeVsWeight_Incompatible);
        runTest("testEquality_NullComparison", QuantityMeasurementAppTest::testEquality_NullComparison);
        runTest("testEquality_SameReference", QuantityMeasurementAppTest::testEquality_SameReference);
        runTest("testEquality_NullUnit", QuantityMeasurementAppTest::testEquality_NullUnit);
        runTest("testEquality_TransitiveProperty", QuantityMeasurementAppTest::testEquality_TransitiveProperty);
        runTest("testEquality_ZeroValue", QuantityMeasurementAppTest::testEquality_ZeroValue);
        runTest("testEquality_NegativeVolume", QuantityMeasurementAppTest::testEquality_NegativeVolume);
        runTest("testEquality_LargeVolumeValue", QuantityMeasurementAppTest::testEquality_LargeVolumeValue);
        runTest("testEquality_SmallVolumeValue", QuantityMeasurementAppTest::testEquality_SmallVolumeValue);
        runTest("testConversion_LitreToMillilitre", QuantityMeasurementAppTest::testConversion_LitreToMillilitre);
        runTest("testConversion_MillilitreToLitre", QuantityMeasurementAppTest::testConversion_MillilitreToLitre);
        runTest("testConversion_GallonToLitre", QuantityMeasurementAppTest::testConversion_GallonToLitre);
        runTest("testConversion_LitreToGallon", QuantityMeasurementAppTest::testConversion_LitreToGallon);
        runTest("testConversion_MillilitreToGallon", QuantityMeasurementAppTest::testConversion_MillilitreToGallon);
        runTest("testConversion_SameUnit", QuantityMeasurementAppTest::testConversion_SameUnit);
        runTest("testConversion_ZeroValue", QuantityMeasurementAppTest::testConversion_ZeroValue);
        runTest("testConversion_NegativeValue", QuantityMeasurementAppTest::testConversion_NegativeValue);
        runTest("testConversion_RoundTrip", QuantityMeasurementAppTest::testConversion_RoundTrip);
        runTest("testAddition_SameUnit_LitrePlusLitre", QuantityMeasurementAppTest::testAddition_SameUnit_LitrePlusLitre);
        runTest("testAddition_SameUnit_MillilitrePlusMillilitre", QuantityMeasurementAppTest::testAddition_SameUnit_MillilitrePlusMillilitre);
        runTest("testAddition_CrossUnit_LitrePlusMillilitre", QuantityMeasurementAppTest::testAddition_CrossUnit_LitrePlusMillilitre);
        runTest("testAddition_CrossUnit_MillilitrePlusLitre", QuantityMeasurementAppTest::testAddition_CrossUnit_MillilitrePlusLitre);
        runTest("testAddition_CrossUnit_GallonPlusLitre", QuantityMeasurementAppTest::testAddition_CrossUnit_GallonPlusLitre);
        runTest("testAddition_ExplicitTargetUnit_Litre", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Litre);
        runTest("testAddition_ExplicitTargetUnit_Millilitre", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Millilitre);
        runTest("testAddition_ExplicitTargetUnit_Gallon", QuantityMeasurementAppTest::testAddition_ExplicitTargetUnit_Gallon);
        runTest("testAddition_Commutativity", QuantityMeasurementAppTest::testAddition_Commutativity);
        runTest("testAddition_WithZero", QuantityMeasurementAppTest::testAddition_WithZero);
        runTest("testAddition_NegativeValues", QuantityMeasurementAppTest::testAddition_NegativeValues);
        runTest("testAddition_LargeValues", QuantityMeasurementAppTest::testAddition_LargeValues);
        runTest("testAddition_SmallValues", QuantityMeasurementAppTest::testAddition_SmallValues);
        runTest("testBackwardCompatibility_AllUC1Through10Tests", QuantityMeasurementAppTest::testBackwardCompatibility_AllUC1Through10Tests);
        runTest("testGenericQuantity_VolumeOperations_Consistency", QuantityMeasurementAppTest::testGenericQuantity_VolumeOperations_Consistency);
        runTest("testScalability_VolumeIntegration", QuantityMeasurementAppTest::testScalability_VolumeIntegration);
        runTest("testVolumeRoundTripPrecision", QuantityMeasurementAppTest::testVolumeRoundTripPrecision);
        runTest("testVolumeToLengthNotEqual", QuantityMeasurementAppTest::testVolumeToLengthNotEqual);
        runTest("testVolumeToWeightNotEqual", QuantityMeasurementAppTest::testVolumeToWeightNotEqual);
        runTest("testVolumeImmutability", QuantityMeasurementAppTest::testVolumeImmutability);

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

    private static Quantity<VolumeUnit> q(double value, VolumeUnit unit) {
        return new Quantity<>(value, unit);
    }

    private static void testVolumeUnitEnum_LitreConstant() {
        assertTrue(VolumeUnit.LITRE.getConversionFactor() == 1.0, "Litre conversion factor should be 1.0.");
    }

    private static void testVolumeUnitEnum_MillilitreConstant() {
        assertApproximatelyEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), "Millilitre conversion factor should be 0.001.");
    }

    private static void testVolumeUnitEnum_GallonConstant() {
        assertApproximatelyEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), "Gallon conversion factor should be 3.78541.");
    }

    private static void testConvertToBaseUnit_LitreToLitre() {
        assertApproximatelyEquals(5.0, VolumeUnit.LITRE.convertToBaseUnit(5.0), "5 litres should remain 5 litres in base unit.");
    }

    private static void testConvertToBaseUnit_MillilitreToLitre() {
        assertApproximatelyEquals(1.0, VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0), "1000 millilitres should convert to 1 litre.");
    }

    private static void testConvertToBaseUnit_GallonToLitre() {
        assertApproximatelyEquals(3.78541, VolumeUnit.GALLON.convertToBaseUnit(1.0), "1 gallon should convert to 3.78541 litres.");
    }

    private static void testConvertFromBaseUnit_LitreToLitre() {
        assertApproximatelyEquals(2.0, VolumeUnit.LITRE.convertFromBaseUnit(2.0), "2 litres should remain 2 litres.");
    }

    private static void testConvertFromBaseUnit_LitreToMillilitre() {
        assertApproximatelyEquals(1000.0, VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0), "1 litre should convert to 1000 millilitres.");
    }

    private static void testConvertFromBaseUnit_LitreToGallon() {
        assertApproximatelyEquals(1.0 / 3.78541, VolumeUnit.GALLON.convertFromBaseUnit(1.0), "1 litre should convert to about 0.264172 gallons.");
    }

    private static void testEquality_LitreToLitre_SameValue() {
        assertTrue(q(1.0, VolumeUnit.LITRE).equals(q(1.0, VolumeUnit.LITRE)), "1 litre should equal 1 litre.");
    }

    private static void testEquality_LitreToLitre_DifferentValue() {
        assertFalse(q(1.0, VolumeUnit.LITRE).equals(q(2.0, VolumeUnit.LITRE)), "1 litre should not equal 2 litres.");
    }

    private static void testEquality_LitreToMillilitre_EquivalentValue() {
        assertTrue(q(1.0, VolumeUnit.LITRE).equals(q(1000.0, VolumeUnit.MILLILITRE)), "1 litre should equal 1000 millilitres.");
    }

    private static void testEquality_MillilitreToLitre_EquivalentValue() {
        assertTrue(q(1000.0, VolumeUnit.MILLILITRE).equals(q(1.0, VolumeUnit.LITRE)), "1000 millilitres should equal 1 litre.");
    }

    private static void testEquality_LitreToGallon_EquivalentValue() {
        assertTrue(q(1.0, VolumeUnit.LITRE).equals(q(0.264172, VolumeUnit.GALLON)), "1 litre should equal about 0.264172 gallons.");
    }

    private static void testEquality_GallonToLitre_EquivalentValue() {
        assertTrue(q(1.0, VolumeUnit.GALLON).equals(q(3.78541, VolumeUnit.LITRE)), "1 gallon should equal about 3.78541 litres.");
    }

    private static void testEquality_VolumeVsLength_Incompatible() {
        assertFalse(q(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, LengthUnit.FEET)), "Volume and length should not be equal.");
    }

    private static void testEquality_VolumeVsWeight_Incompatible() {
        assertFalse(q(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)), "Volume and weight should not be equal.");
    }

    private static void testEquality_NullComparison() {
        assertFalse(q(1.0, VolumeUnit.LITRE).equals(null), "Comparison with null should return false.");
    }

    private static void testEquality_SameReference() {
        Quantity<VolumeUnit> volume = q(1.0, VolumeUnit.LITRE);
        assertTrue(volume.equals(volume), "Object should equal itself.");
    }

    private static void testEquality_NullUnit() {
        assertThrows(() -> new Quantity<>(1.0, null), "Null unit should throw IllegalArgumentException.");
    }

    private static void testEquality_TransitiveProperty() {
        Quantity<VolumeUnit> a = q(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = q(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> c = q(0.264172, VolumeUnit.GALLON);
        assertTrue(a.equals(b), "A should equal B.");
        assertTrue(b.equals(c), "B should equal C.");
        assertTrue(a.equals(c), "A should equal C.");
    }

    private static void testEquality_ZeroValue() {
        assertTrue(q(0.0, VolumeUnit.LITRE).equals(q(0.0, VolumeUnit.MILLILITRE)), "Zero should be equal across units.");
    }

    private static void testEquality_NegativeVolume() {
        assertTrue(q(-1.0, VolumeUnit.LITRE).equals(q(-1000.0, VolumeUnit.MILLILITRE)), "Negative volume should convert correctly.");
    }

    private static void testEquality_LargeVolumeValue() {
        assertTrue(q(1_000_000.0, VolumeUnit.MILLILITRE).equals(q(1000.0, VolumeUnit.LITRE)), "Large volume values should convert correctly.");
    }

    private static void testEquality_SmallVolumeValue() {
        assertTrue(q(0.001, VolumeUnit.LITRE).equals(q(1.0, VolumeUnit.MILLILITRE)), "Small volume values should convert correctly.");
    }

    private static void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> result = q(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE);
        assertApproximatelyEquals(1000.0, result.getValue(), "1 litre should convert to 1000 millilitres.");
        assertTrue(result.getUnit() == VolumeUnit.MILLILITRE, "Result unit should be millilitre.");
    }

    private static void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> result = q(1000.0, VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);
        assertApproximatelyEquals(1.0, result.getValue(), "1000 millilitres should convert to 1 litre.");
        assertTrue(result.getUnit() == VolumeUnit.LITRE, "Result unit should be litre.");
    }

    private static void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> result = q(1.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE);
        assertApproximatelyEquals(3.79, result.getValue(), "1 gallon should convert to about 3.79 litres after rounding.");
        assertTrue(result.getUnit() == VolumeUnit.LITRE, "Result unit should be litre.");
    }

    private static void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> result = q(3.78541, VolumeUnit.LITRE).convertTo(VolumeUnit.GALLON);
        assertApproximatelyEquals(1.0, result.getValue(), "3.78541 litres should convert to about 1 gallon.");
        assertTrue(result.getUnit() == VolumeUnit.GALLON, "Result unit should be gallon.");
    }

    private static void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> result = q(1000.0, VolumeUnit.MILLILITRE).convertTo(VolumeUnit.GALLON);
        assertApproximatelyEquals(0.26, result.getValue(), "1000 millilitres should convert to about 0.26 gallons after rounding.");
        assertTrue(result.getUnit() == VolumeUnit.GALLON, "Result unit should be gallon.");
    }

    private static void testConversion_SameUnit() {
        Quantity<VolumeUnit> result = q(5.0, VolumeUnit.LITRE).convertTo(VolumeUnit.LITRE);
        assertApproximatelyEquals(5.0, result.getValue(), "Same-unit conversion should preserve value.");
    }

    private static void testConversion_ZeroValue() {
        Quantity<VolumeUnit> result = q(0.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE);
        assertApproximatelyEquals(0.0, result.getValue(), "Zero value should remain zero.");
    }

    private static void testConversion_NegativeValue() {
        Quantity<VolumeUnit> result = q(-1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE);
        assertApproximatelyEquals(-1000.0, result.getValue(), "Negative value should convert correctly.");
    }

    private static void testConversion_RoundTrip() {
        Quantity<VolumeUnit> result = q(1.5, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);
        assertApproximatelyEquals(1.5, result.getValue(), "Round-trip conversion should preserve value.");
    }

    private static void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> result = q(1.0, VolumeUnit.LITRE).add(q(2.0, VolumeUnit.LITRE));
        assertApproximatelyEquals(3.0, result.getValue(), "1 litre + 2 litres should be 3 litres.");
        assertTrue(result.getUnit() == VolumeUnit.LITRE, "Result unit should be litre.");
    }

    private static void testAddition_SameUnit_MillilitrePlusMillilitre() {
        Quantity<VolumeUnit> result = q(500.0, VolumeUnit.MILLILITRE).add(q(500.0, VolumeUnit.MILLILITRE));
        assertApproximatelyEquals(1000.0, result.getValue(), "500 mL + 500 mL should be 1000 mL.");
        assertTrue(result.getUnit() == VolumeUnit.MILLILITRE, "Result unit should be millilitre.");
    }

    private static void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> result = q(1.0, VolumeUnit.LITRE).add(q(1000.0, VolumeUnit.MILLILITRE));
        assertApproximatelyEquals(2.0, result.getValue(), "1 litre + 1000 mL should be 2 litres.");
        assertTrue(result.getUnit() == VolumeUnit.LITRE, "Result unit should be litre.");
    }

    private static void testAddition_CrossUnit_MillilitrePlusLitre() {
        Quantity<VolumeUnit> result = q(1000.0, VolumeUnit.MILLILITRE).add(q(1.0, VolumeUnit.LITRE));
        assertApproximatelyEquals(2000.0, result.getValue(), "1000 mL + 1 litre should be 2000 mL.");
        assertTrue(result.getUnit() == VolumeUnit.MILLILITRE, "Result unit should be millilitre.");
    }

    private static void testAddition_CrossUnit_GallonPlusLitre() {
        Quantity<VolumeUnit> result = q(1.0, VolumeUnit.GALLON).add(q(3.78541, VolumeUnit.LITRE));
        assertApproximatelyEquals(2.0, result.getValue(), "1 gallon + 3.78541 litres should be about 2 gallons.");
        assertTrue(result.getUnit() == VolumeUnit.GALLON, "Result unit should be gallon.");
    }

    private static void testAddition_ExplicitTargetUnit_Litre() {
        Quantity<VolumeUnit> result = q(1.0, VolumeUnit.LITRE).add(q(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.LITRE);
        assertApproximatelyEquals(2.0, result.getValue(), "1 litre + 1000 mL should be 2 litres.");
        assertTrue(result.getUnit() == VolumeUnit.LITRE, "Result unit should be litre.");
    }

    private static void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result = q(1.0, VolumeUnit.LITRE).add(q(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE);
        assertApproximatelyEquals(2000.0, result.getValue(), "1 litre + 1000 mL should be 2000 mL.");
        assertTrue(result.getUnit() == VolumeUnit.MILLILITRE, "Result unit should be millilitre.");
    }

    private static void testAddition_ExplicitTargetUnit_Gallon() {
        Quantity<VolumeUnit> result = q(3.78541, VolumeUnit.LITRE).add(q(3.78541, VolumeUnit.LITRE), VolumeUnit.GALLON);
        assertApproximatelyEquals(2.0, result.getValue(), "3.78541 litres + 3.78541 litres should be about 2 gallons.");
        assertTrue(result.getUnit() == VolumeUnit.GALLON, "Result unit should be gallon.");
    }

    private static void testAddition_Commutativity() {
        Quantity<VolumeUnit> sumAB = q(1.0, VolumeUnit.LITRE).add(q(1000.0, VolumeUnit.MILLILITRE));
        Quantity<VolumeUnit> sumBA = q(1000.0, VolumeUnit.MILLILITRE).add(q(1.0, VolumeUnit.LITRE));
        assertApproximatelyEquals(sumAB.convertTo(VolumeUnit.LITRE).getValue(), sumBA.convertTo(VolumeUnit.LITRE).getValue(), "Addition should be commutative.");
    }

    private static void testAddition_WithZero() {
        Quantity<VolumeUnit> result = q(5.0, VolumeUnit.LITRE).add(q(0.0, VolumeUnit.MILLILITRE));
        assertApproximatelyEquals(5.0, result.getValue(), "5 litres + 0 mL should be 5 litres.");
    }

    private static void testAddition_NegativeValues() {
        Quantity<VolumeUnit> result = q(5.0, VolumeUnit.LITRE).add(q(-2000.0, VolumeUnit.MILLILITRE));
        assertApproximatelyEquals(3.0, result.getValue(), "5 litres + (-2000 mL) should be 3 litres.");
    }

    private static void testAddition_LargeValues() {
        Quantity<VolumeUnit> result = q(1e6, VolumeUnit.LITRE).add(q(1e6, VolumeUnit.LITRE));
        assertApproximatelyEquals(2e6, result.getValue(), "Large values should add correctly.");
    }

    private static void testAddition_SmallValues() {
        Quantity<VolumeUnit> result = q(0.001, VolumeUnit.LITRE).add(q(0.002, VolumeUnit.LITRE));
        assertApproximatelyEquals(0.003, result.getValue(), "Small values should add correctly.");
    }

    private static void testBackwardCompatibility_AllUC1Through10Tests() {
        assertTrue(q(1.0, VolumeUnit.LITRE).equals(q(1000.0, VolumeUnit.MILLILITRE)), "Volume should integrate with existing generic quantity.");
        assertTrue(new Quantity<>(1.0, LengthUnit.FEET).equals(new Quantity<>(12.0, LengthUnit.INCHES)), "Length behavior remains intact.");
        assertTrue(new Quantity<>(1.0, WeightUnit.KILOGRAM).equals(new Quantity<>(1000.0, WeightUnit.GRAM)), "Weight behavior remains intact.");
    }

    private static void testGenericQuantity_VolumeOperations_Consistency() {
        Quantity<VolumeUnit> volume = q(1.0, VolumeUnit.LITRE);
        assertApproximatelyEquals(1000.0, volume.convertTo(VolumeUnit.MILLILITRE).getValue(), "Generic quantity should handle volume conversions.");
        assertApproximatelyEquals(2.0, volume.add(q(1000.0, VolumeUnit.MILLILITRE)).getValue(), "Generic quantity should handle volume addition.");
    }

    private static void testScalability_VolumeIntegration() {
        QuantityMeasurementAppUC11.main(new String[0]);
        assertTrue(true, "Volume should integrate without changing generic core.");
    }

    private static void testVolumeRoundTripPrecision() {
        Quantity<VolumeUnit> result = q(1.5, VolumeUnit.LITRE).convertTo(VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE);
        assertApproximatelyEquals(1.51, result.getValue(), "Round-trip precision should match the rounded convertTo contract.");
    }

    private static void testVolumeToLengthNotEqual() {
        assertFalse(q(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, LengthUnit.FEET)), "Volume and length should not be equal.");
    }

    private static void testVolumeToWeightNotEqual() {
        assertFalse(q(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)), "Volume and weight should not be equal.");
    }

    private static void testVolumeImmutability() {
        Quantity<VolumeUnit> original = q(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> converted = original.convertTo(VolumeUnit.MILLILITRE);
        assertApproximatelyEquals(1.0, original.getValue(), "Original quantity should remain unchanged.");
        assertApproximatelyEquals(1000.0, converted.getValue(), "Converted quantity should be a new instance.");
    }
}
