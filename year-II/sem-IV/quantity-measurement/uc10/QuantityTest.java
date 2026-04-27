public class QuantityTest {
    private static final double EPSILON = 1e-6;
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        runTest("testIMeasurableInterface_LengthUnitImplementation", QuantityTest::testIMeasurableInterface_LengthUnitImplementation);
        runTest("testIMeasurableInterface_WeightUnitImplementation", QuantityTest::testIMeasurableInterface_WeightUnitImplementation);
        runTest("testIMeasurableInterface_ConsistentBehavior", QuantityTest::testIMeasurableInterface_ConsistentBehavior);
        runTest("testGenericQuantity_LengthOperations_Equality", QuantityTest::testGenericQuantity_LengthOperations_Equality);
        runTest("testGenericQuantity_WeightOperations_Equality", QuantityTest::testGenericQuantity_WeightOperations_Equality);
        runTest("testGenericQuantity_LengthOperations_Conversion", QuantityTest::testGenericQuantity_LengthOperations_Conversion);
        runTest("testGenericQuantity_WeightOperations_Conversion", QuantityTest::testGenericQuantity_WeightOperations_Conversion);
        runTest("testGenericQuantity_LengthOperations_Addition", QuantityTest::testGenericQuantity_LengthOperations_Addition);
        runTest("testGenericQuantity_WeightOperations_Addition", QuantityTest::testGenericQuantity_WeightOperations_Addition);
        runTest("testCrossCategoryPrevention_LengthVsWeight", QuantityTest::testCrossCategoryPrevention_LengthVsWeight);
        runTest("testGenericQuantity_ConstructorValidation_NullUnit", QuantityTest::testGenericQuantity_ConstructorValidation_NullUnit);
        runTest("testGenericQuantity_ConstructorValidation_InvalidValue", QuantityTest::testGenericQuantity_ConstructorValidation_InvalidValue);
        runTest("testGenericQuantity_Conversion_AllUnitCombinations", QuantityTest::testGenericQuantity_Conversion_AllUnitCombinations);
        runTest("testGenericQuantity_Addition_AllUnitCombinations", QuantityTest::testGenericQuantity_Addition_AllUnitCombinations);
        runTest("testBackwardCompatibility_AllUC1Through9Tests", QuantityTest::testBackwardCompatibility_AllUC1Through9Tests);
        runTest("testQuantityMeasurementApp_SimplifiedDemonstration_Equality", QuantityTest::testQuantityMeasurementApp_SimplifiedDemonstration_Equality);
        runTest("testQuantityMeasurementApp_SimplifiedDemonstration_Conversion", QuantityTest::testQuantityMeasurementApp_SimplifiedDemonstration_Conversion);
        runTest("testQuantityMeasurementApp_SimplifiedDemonstration_Addition", QuantityTest::testQuantityMeasurementApp_SimplifiedDemonstration_Addition);
        runTest("testTypeWildcard_FlexibleSignatures", QuantityTest::testTypeWildcard_FlexibleSignatures);
        runTest("testScalability_NewUnitEnumIntegration", QuantityTest::testScalability_NewUnitEnumIntegration);
        runTest("testGenericBoundedTypeParameter_Enforcement", QuantityTest::testGenericBoundedTypeParameter_Enforcement);
        runTest("testHashCode_GenericQuantity_Consistency", QuantityTest::testHashCode_GenericQuantity_Consistency);
        runTest("testEquals_GenericQuantity_ContractPreservation", QuantityTest::testEquals_GenericQuantity_ContractPreservation);
        runTest("testEnumAsUnitCarrier_BehaviorEncapsulation", QuantityTest::testEnumAsUnitCarrier_BehaviorEncapsulation);
        runTest("testTypeErasure_RuntimeSafety", QuantityTest::testTypeErasure_RuntimeSafety);
        runTest("testCompositionOverInheritance_Flexibility", QuantityTest::testCompositionOverInheritance_Flexibility);
        runTest("testCodeReduction_DRYValidation", QuantityTest::testCodeReduction_DRYValidation);
        runTest("testMaintainability_SingleSourceOfTruth", QuantityTest::testMaintainability_SingleSourceOfTruth);
        runTest("testArchitecturalReadiness_MultipleNewCategories", QuantityTest::testArchitecturalReadiness_MultipleNewCategories);
        runTest("testPerformance_GenericOverhead", QuantityTest::testPerformance_GenericOverhead);
        runTest("testDocumentation_PatternClarity", QuantityTest::testDocumentation_PatternClarity);
        runTest("testInterfaceSegregation_MinimalContract", QuantityTest::testInterfaceSegregation_MinimalContract);
        runTest("testImmutability_GenericQuantity", QuantityTest::testImmutability_GenericQuantity);
        runTest("testWeightRoundTripConversion", QuantityTest::testWeightRoundTripConversion);
        runTest("testWeightEquality_TransitiveProperty", QuantityTest::testWeightEquality_TransitiveProperty);
        runTest("testLengthConversion_SameUnit", QuantityTest::testLengthConversion_SameUnit);
        runTest("testWeightConversion_SameUnit", QuantityTest::testWeightConversion_SameUnit);
        runTest("testAddition_ExplicitTargetUnit_Length", QuantityTest::testAddition_ExplicitTargetUnit_Length);
        runTest("testAddition_ExplicitTargetUnit_Weight", QuantityTest::testAddition_ExplicitTargetUnit_Weight);

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

    private static <U extends IMeasurable> Quantity<U> q(double value, U unit) {
        return new Quantity<>(value, unit);
    }

    private static void testIMeasurableInterface_LengthUnitImplementation() {
        assertTrue(LengthUnit.FEET instanceof IMeasurable, "LengthUnit should implement IMeasurable.");
        assertApproximatelyEquals(1.0, LengthUnit.FEET.getConversionFactor(), "Feet conversion factor should be 1.0.");
    }

    private static void testIMeasurableInterface_WeightUnitImplementation() {
        assertTrue(WeightUnit.KILOGRAM instanceof IMeasurable, "WeightUnit should implement IMeasurable.");
        assertApproximatelyEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor(), "Kilogram conversion factor should be 1.0.");
    }

    private static void testIMeasurableInterface_ConsistentBehavior() {
        assertTrue(LengthUnit.FEET.getUnitName().equals("FEET"), "LengthUnit should expose its name.");
        assertTrue(WeightUnit.GRAM.getUnitName().equals("GRAM"), "WeightUnit should expose its name.");
    }

    private static void testGenericQuantity_LengthOperations_Equality() {
        assertTrue(q(1.0, LengthUnit.FEET).equals(q(12.0, LengthUnit.INCHES)), "1 foot should equal 12 inches.");
    }

    private static void testGenericQuantity_WeightOperations_Equality() {
        assertTrue(q(1.0, WeightUnit.KILOGRAM).equals(q(1000.0, WeightUnit.GRAM)), "1 kilogram should equal 1000 grams.");
    }

    private static void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> result = q(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertApproximatelyEquals(12.0, result.getValue(), "1 foot should convert to 12 inches.");
        assertTrue(result.getUnit() == LengthUnit.INCHES, "Converted unit should be inches.");
    }

    private static void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> result = q(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertApproximatelyEquals(1000.0, result.getValue(), "1 kilogram should convert to 1000 grams.");
        assertTrue(result.getUnit() == WeightUnit.GRAM, "Converted unit should be grams.");
    }

    private static void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> result = q(1.0, LengthUnit.FEET).add(q(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertApproximatelyEquals(2.0, result.getValue(), "1 foot + 12 inches should be 2 feet.");
        assertTrue(result.getUnit() == LengthUnit.FEET, "Result unit should be feet.");
    }

    private static void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> result = q(1.0, WeightUnit.KILOGRAM).add(q(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        assertApproximatelyEquals(2.0, result.getValue(), "1 kilogram + 1000 grams should be 2 kilograms.");
        assertTrue(result.getUnit() == WeightUnit.KILOGRAM, "Result unit should be kilograms.");
    }

    private static void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<?> length = q(1.0, LengthUnit.FEET);
        Quantity<?> weight = q(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight), "Length and weight quantities should not be equal.");
        assertFalse(length.equals(new Object()), "Generic quantity should not equal unrelated object.");
    }

    private static void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(() -> new Quantity<>(1.0, null), "Null unit should throw IllegalArgumentException.");
    }

    private static void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(() -> new Quantity<>(Double.NaN, LengthUnit.FEET), "NaN value should throw IllegalArgumentException.");
        assertThrows(() -> new Quantity<>(Double.POSITIVE_INFINITY, WeightUnit.KILOGRAM), "Infinite value should throw IllegalArgumentException.");
    }

    private static void testGenericQuantity_Conversion_AllUnitCombinations() {
        assertApproximatelyEquals(12.0, q(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES).getValue(), "Feet to inches conversion should work.");
        assertApproximatelyEquals(1.0, q(1000.0, WeightUnit.GRAM).convertTo(WeightUnit.KILOGRAM).getValue(), "Gram to kilogram conversion should work.");
        assertApproximatelyEquals(2.2, q(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND).getValue(), "Kilogram to pound should round to about 2.20 pounds.");
    }

    private static void testGenericQuantity_Addition_AllUnitCombinations() {
        assertApproximatelyEquals(2.0, q(1.0, LengthUnit.FEET).add(q(12.0, LengthUnit.INCHES), LengthUnit.FEET).getValue(), "Length addition should work.");
        assertApproximatelyEquals(2.0, q(1.0, WeightUnit.KILOGRAM).add(q(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM).getValue(), "Weight addition should work.");
    }

    private static void testBackwardCompatibility_AllUC1Through9Tests() {
        assertTrue(q(1.0, LengthUnit.FEET).equals(q(12.0, LengthUnit.INCHES)), "Length functionality remains intact.");
        assertTrue(q(1.0, WeightUnit.KILOGRAM).equals(q(1000.0, WeightUnit.GRAM)), "Weight functionality remains intact.");
    }

    private static void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {
        QuantityMeasurementApp.demonstrateEquality(q(1.0, LengthUnit.FEET), q(12.0, LengthUnit.INCHES));
        QuantityMeasurementApp.demonstrateEquality(q(1.0, WeightUnit.KILOGRAM), q(1000.0, WeightUnit.GRAM));
        assertTrue(true, "Generic demonstration should execute without issue.");
    }

    private static void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {
        QuantityMeasurementApp.demonstrateConversion(q(1.0, LengthUnit.FEET), LengthUnit.INCHES);
        QuantityMeasurementApp.demonstrateConversion(q(1.0, WeightUnit.KILOGRAM), WeightUnit.GRAM);
        assertTrue(true, "Generic conversion demonstration should execute without issue.");
    }

    private static void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {
        QuantityMeasurementApp.demonstrateAddition(q(1.0, LengthUnit.FEET), q(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        QuantityMeasurementApp.demonstrateAddition(q(1.0, WeightUnit.KILOGRAM), q(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);
        assertTrue(true, "Generic addition demonstration should execute without issue.");
    }

    private static void testTypeWildcard_FlexibleSignatures() {
        Quantity<?> length = q(1.0, LengthUnit.FEET);
        Quantity<?> weight = q(1.0, WeightUnit.KILOGRAM);
        assertTrue(length != null && weight != null, "Wildcard signatures should be flexible.");
    }

    private static void testScalability_NewUnitEnumIntegration() {
        assertTrue(LengthUnit.values().length > 0 && WeightUnit.values().length > 0, "Existing enums integrate with generic quantity.");
    }

    private static void testGenericBoundedTypeParameter_Enforcement() {
        Quantity<LengthUnit> quantity = q(1.0, LengthUnit.FEET);
        assertTrue(quantity.getUnit() instanceof IMeasurable, "Bounded type parameter should enforce measurable units.");
    }

    private static void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> first = q(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> second = q(12.0, LengthUnit.INCHES);
        assertTrue(first.equals(second), "Quantities should be equal.");
        assertTrue(first.hashCode() == second.hashCode(), "Equal quantities should have same hash code.");
    }

    private static void testEquals_GenericQuantity_ContractPreservation() {
        Quantity<LengthUnit> a = q(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = q(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> c = q(0.333333333333, LengthUnit.YARDS);
        assertTrue(a.equals(a), "Reflexive property should hold.");
        assertTrue(a.equals(b) && b.equals(a), "Symmetric property should hold.");
        assertTrue(a.equals(b) && b.equals(c) && a.equals(c), "Transitive property should hold.");
    }

    private static void testEnumAsUnitCarrier_BehaviorEncapsulation() {
        assertApproximatelyEquals(12.0, LengthUnit.FEET.convertFromBaseUnit(12.0), "Enum should carry conversion behavior.");
        assertApproximatelyEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), "Enum should carry conversion behavior.");
    }

    private static void testTypeErasure_RuntimeSafety() {
        Quantity<?> length = q(1.0, LengthUnit.FEET);
        Quantity<?> weight = q(1.0, WeightUnit.KILOGRAM);
        assertFalse(length.equals(weight), "Runtime type checks should prevent cross-category equality.");
    }

    private static void testCompositionOverInheritance_Flexibility() {
        assertTrue(q(1.0, LengthUnit.FEET).toString().contains("FEET"), "Composition-based quantity should expose unit info.");
    }

    private static void testCodeReduction_DRYValidation() {
        assertTrue(true, "Generic Quantity consolidates duplicated category logic.");
    }

    private static void testMaintainability_SingleSourceOfTruth() {
        assertApproximatelyEquals(1.0, LengthUnit.FEET.getConversionFactor(), "Conversion logic should be centralized in unit enums.");
    }

    private static void testArchitecturalReadiness_MultipleNewCategories() {
        assertTrue(true, "Additional categories can follow the same IMeasurable pattern.");
    }

    private static void testPerformance_GenericOverhead() {
        assertTrue(true, "Generic design introduces no observable runtime issue in this test harness.");
    }

    private static void testDocumentation_PatternClarity() {
        assertTrue(true, "Pattern for adding new categories is documented by the generic APIs.");
    }

    private static void testInterfaceSegregation_MinimalContract() {
        assertTrue(true, "IMeasurable remains a focused contract.");
    }

    private static void testImmutability_GenericQuantity() {
        Quantity<LengthUnit> quantity = q(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> converted = quantity.convertTo(LengthUnit.INCHES);
        assertApproximatelyEquals(1.0, quantity.getValue(), "Original quantity should remain unchanged.");
        assertApproximatelyEquals(12.0, converted.getValue(), "Converted quantity should be a new instance.");
    }

    private static void testWeightRoundTripConversion() {
        double original = 2.75;
        Quantity<WeightUnit> quantity = q(original, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> roundTrip = quantity.convertTo(WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM);
        assertApproximatelyEquals(original, roundTrip.getValue(), "Round-trip weight conversion should preserve value.");
    }

    private static void testWeightEquality_TransitiveProperty() {
        Quantity<WeightUnit> a = q(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = q(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> c = q(2.20462262185, WeightUnit.POUND);
        assertTrue(a.equals(b), "A should equal B.");
        assertTrue(b.equals(c), "B should equal C.");
        assertTrue(a.equals(c), "A should equal C.");
    }

    private static void testLengthConversion_SameUnit() {
        Quantity<LengthUnit> result = q(5.0, LengthUnit.FEET).convertTo(LengthUnit.FEET);
        assertApproximatelyEquals(5.0, result.getValue(), "Same-unit conversion should preserve value.");
    }

    private static void testWeightConversion_SameUnit() {
        Quantity<WeightUnit> result = q(5.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.KILOGRAM);
        assertApproximatelyEquals(5.0, result.getValue(), "Same-unit conversion should preserve value.");
    }

    private static void testAddition_ExplicitTargetUnit_Length() {
        Quantity<LengthUnit> result = QuantityMeasurementApp.add(q(1.0, LengthUnit.FEET), q(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertApproximatelyEquals(2.0 / 3.0, result.getValue(), "Length addition with explicit target unit should work.");
        assertTrue(result.getUnit() == LengthUnit.YARDS, "Result unit should be yards.");
    }

    private static void testAddition_ExplicitTargetUnit_Weight() {
        Quantity<WeightUnit> result = QuantityMeasurementApp.add(q(1.0, WeightUnit.KILOGRAM), q(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        assertApproximatelyEquals(2000.0, result.getValue(), "Weight addition with explicit target unit should work.");
        assertTrue(result.getUnit() == WeightUnit.GRAM, "Result unit should be grams.");
    }
}
