/**
 * Standalone weight units with conversion responsibility delegated to the enum.
 */
public enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    KG(1.0),
    GRAM(0.001),
    GRAMS(0.001),
    POUND(0.453592),
    POUNDS(0.453592),
    LB(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    public static WeightUnit fromString(String unitText) {
        if (unitText == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        String normalized = unitText.trim().toUpperCase();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be empty.");
        }

        switch (normalized) {
            case "KILOGRAM":
            case "KILOGRAMS":
            case "KG":
                return KILOGRAM;
            case "GRAM":
            case "GRAMS":
            case "G":
                return GRAM;
            case "POUND":
            case "POUNDS":
            case "LB":
            case "LBS":
                return POUND;
            default:
                throw new IllegalArgumentException("Unsupported weight unit: " + unitText);
        }
    }
}
