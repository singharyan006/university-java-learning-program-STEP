/**
 * Standalone length units with responsibility for converting to and from the
 * shared base unit (feet).
 */
public enum LengthUnit {
    FEET(1.0),
    FOOT(1.0),
    INCH(1.0 / 12.0),
    INCHES(1.0 / 12.0),
    YARD(3.0),
    YARDS(3.0),
    CENTIMETER(1.0 / 30.48),
    CENTIMETERS(1.0 / 30.48),
    CM(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    public static LengthUnit fromString(String unitText) {
        if (unitText == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        String normalized = unitText.trim().toUpperCase();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be empty.");
        }

        switch (normalized) {
            case "FEET":
            case "FOOT":
                return FEET;
            case "INCH":
                return INCH;
            case "INCHES":
                return INCHES;
            case "YARD":
                return YARD;
            case "YARDS":
                return YARDS;
            case "CENTIMETER":
                return CENTIMETER;
            case "CENTIMETERS":
                return CENTIMETERS;
            case "CM":
                return CM;
            default:
                throw new IllegalArgumentException("Unsupported unit: " + unitText);
        }
    }
}
