/**
 * Standalone volume units with conversion responsibility delegated to the enum.
 */
public enum VolumeUnit implements IMeasurable {
    LITRE(1.0),
    LITRES(1.0),
    MILLILITRE(0.001),
    MILLILITRES(0.001),
    GALLON(3.78541),
    GALLONS(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
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

    public static VolumeUnit fromString(String unitText) {
        if (unitText == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        String normalized = unitText.trim().toUpperCase();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be empty.");
        }

        switch (normalized) {
            case "LITRE":
            case "LITRES":
            case "LITER":
            case "LITERS":
                return LITRE;
            case "MILLILITRE":
            case "MILLILITRES":
            case "MILLILITER":
            case "MILLILITERS":
            case "ML":
                return MILLILITRE;
            case "GALLON":
            case "GALLONS":
            case "GAL":
                return GALLON;
            default:
                throw new IllegalArgumentException("Unsupported volume unit: " + unitText);
        }
    }
}
