package androidx.car.app.model;

import androidx.car.app.model.constraints.CarColorConstraints;
import j$.util.Objects;

public final class ForegroundCarColorSpan extends CarSpan {
    private final CarColor mCarColor;

    public static ForegroundCarColorSpan create(CarColor carColor) {
        CarColorConstraints.UNCONSTRAINED.validateOrThrow(carColor);
        return new ForegroundCarColorSpan((CarColor) Objects.requireNonNull(carColor));
    }

    public CarColor getColor() {
        return this.mCarColor;
    }

    public String toString() {
        return "[color: " + this.mCarColor + "]";
    }

    public int hashCode() {
        return Objects.hashCode(this.mCarColor);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ForegroundCarColorSpan)) {
            return false;
        }
        return Objects.equals(this.mCarColor, ((ForegroundCarColorSpan) obj).mCarColor);
    }

    private ForegroundCarColorSpan(CarColor carColor) {
        this.mCarColor = carColor;
    }

    private ForegroundCarColorSpan() {
        this.mCarColor = CarColor.DEFAULT;
    }
}
