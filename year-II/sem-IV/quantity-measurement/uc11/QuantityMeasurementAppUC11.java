/**
 * UC11 demo application for the generic quantity model.
 */
public class QuantityMeasurementAppUC11 {
    public static void main(String[] args) {
        Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Equality Comparisons:");
        System.out.println("Input: " + volume1 + ".equals(" + volume2 + ")");
        System.out.println("Output: " + volume1.equals(volume2));
        System.out.println("Input: " + volume1 + ".equals(" + volume3 + ")");
        System.out.println("Output: " + volume1.equals(volume3));
        System.out.println("Input: " + volume3 + ".equals(" + volume1 + ")");
        System.out.println("Output: " + volume3.equals(volume1));

        System.out.println("Unit Conversions:");
        System.out.println("Input: " + volume1 + ".convertTo(MILLILITRE)");
        System.out.println("Output: " + volume1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println("Input: " + volume3 + ".convertTo(LITRE)");
        System.out.println("Output: " + volume3.convertTo(VolumeUnit.LITRE));
        System.out.println("Input: " + volume2 + ".convertTo(GALLON)");
        System.out.println("Output: " + volume2.convertTo(VolumeUnit.GALLON));

        System.out.println("Addition Operations:");
        System.out.println("Input: " + volume1 + ".add(" + volume2 + ")");
        System.out.println("Output: " + volume1.add(volume2));
        System.out.println("Input: " + volume1 + ".add(" + volume2 + ", MILLILITRE)");
        System.out.println("Output: " + volume1.add(volume2, VolumeUnit.MILLILITRE));
        System.out.println("Input: " + volume3 + ".add(" + volume1 + ", GALLON)");
        System.out.println("Output: " + volume3.add(volume1, VolumeUnit.GALLON));

        System.out.println("Category Incompatibility:");
        System.out.println("Input: " + volume1 + ".equals(new Quantity<>(1.0, LengthUnit.FEET))");
        System.out.println("Output: " + volume1.equals(new Quantity<>(1.0, LengthUnit.FEET)));
    }
}
