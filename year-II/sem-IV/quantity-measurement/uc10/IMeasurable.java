/**
 * Contract for measurable units that can convert to and from a shared base unit.
 */
public interface IMeasurable {
    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
}
