import java.util.Objects;

/**
 * Generic immutable quantity value object for any measurable unit category.
 */
public final class Quantity<U extends IMeasurable> {
    private static final double VALUE_EPSILON = 1e-6;
    private static final double DISPLAY_ROUNDING_FACTOR = 100.0;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        validateFinite(value);
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
        this.value = value;
        this.unit = unit;
    }

    public static <U extends IMeasurable> Quantity<U> of(double value, U unit) {
        return new Quantity<>(value, unit);
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        double convertedValue = roundToTwoDecimals(targetUnit.convertFromBaseUnit(toBaseUnit()));
        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Second operand cannot be null.");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        ensureCompatibleCategory(other);

        double sumInBaseUnit = this.unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);
        double sumInTargetUnit = targetUnit.convertFromBaseUnit(sumInBaseUnit);
        return new Quantity<>(sumInTargetUnit, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Quantity<?> other = (Quantity<?>) obj;
        if (this.unit.getClass() != other.unit.getClass()) {
            return false;
        }
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) <= VALUE_EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), Math.round(toBaseUnit() / VALUE_EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }

    private void ensureCompatibleCategory(Quantity<U> other) {
        if (this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException("Quantities from different categories cannot be added.");
        }
    }

    private static void validateFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
    }

    private static double roundToTwoDecimals(double value) {
        return Math.round(value * DISPLAY_ROUNDING_FACTOR) / DISPLAY_ROUNDING_FACTOR;
    }

}
