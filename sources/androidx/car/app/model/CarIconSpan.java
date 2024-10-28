package androidx.car.app.model;

import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.core.os.EnvironmentCompat;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CarIconSpan extends CarSpan {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    private final int mAlignment;
    private final CarIcon mIcon;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Alignment {
    }

    public static CarIconSpan create(CarIcon carIcon) {
        return create(carIcon, 1);
    }

    public static CarIconSpan create(CarIcon carIcon, int i) {
        CarIconConstraints.DEFAULT.validateOrThrow(carIcon);
        if (i == 1 || i == 0 || i == 2) {
            return new CarIconSpan((CarIcon) Objects.requireNonNull(carIcon), i);
        }
        throw new IllegalStateException("Invalid alignment value: " + i);
    }

    private CarIconSpan(CarIcon carIcon, int i) {
        this.mIcon = carIcon;
        this.mAlignment = i;
    }

    private CarIconSpan() {
        this.mIcon = null;
        this.mAlignment = 1;
    }

    public CarIcon getIcon() {
        return (CarIcon) Objects.requireNonNull(this.mIcon);
    }

    public int getAlignment() {
        return this.mAlignment;
    }

    public String toString() {
        return "[icon: " + this.mIcon + ", alignment: " + alignmentToString(this.mAlignment) + "]";
    }

    public int hashCode() {
        return Objects.hashCode(this.mIcon);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CarIconSpan)) {
            return false;
        }
        return Objects.equals(this.mIcon, ((CarIconSpan) obj).mIcon);
    }

    private static String alignmentToString(int i) {
        if (i == 0) {
            return "bottom";
        }
        if (i == 1) {
            return "baseline";
        }
        if (i != 2) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return "center";
    }
}
