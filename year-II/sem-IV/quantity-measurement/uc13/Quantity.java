import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

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

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (Math.abs(b) <= VALUE_EPSILON) {
                throw new ArithmeticException("Division by zero quantity is not allowed.");
            }
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) {
            throw new IllegalArgumentException("Second operand cannot be null.");
        }
        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        ensureCompatibleCategory(other);
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double thisBaseValue = this.unit.convertToBaseUnit(this.value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);
        return operation.compute(thisBaseValue, otherBaseValue);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double sumInBaseUnit = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double sumInTargetUnit = targetUnit.convertFromBaseUnit(sumInBaseUnit);
        return new Quantity<>(sumInTargetUnit, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double diffInBaseUnit = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double diffInTargetUnit = targetUnit.convertFromBaseUnit(diffInBaseUnit);
        return new Quantity<>(diffInTargetUnit, targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
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
            throw new IllegalArgumentException("Quantities from different categories cannot be operated on.");
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
