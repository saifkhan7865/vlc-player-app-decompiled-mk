package androidx.car.app.model;

import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public final class Distance {
    public static final int UNIT_FEET = 6;
    public static final int UNIT_KILOMETERS = 2;
    public static final int UNIT_KILOMETERS_P1 = 3;
    public static final int UNIT_METERS = 1;
    public static final int UNIT_MILES = 4;
    public static final int UNIT_MILES_P1 = 5;
    public static final int UNIT_YARDS = 7;
    private final double mDisplayDistance;
    private final int mDisplayUnit;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }

    public static Distance create(double d, int i) {
        if (d >= 0.0d) {
            return new Distance(d, i);
        }
        throw new IllegalArgumentException("displayDistance must be a positive value");
    }

    public double getDisplayDistance() {
        return this.mDisplayDistance;
    }

    public int getDisplayUnit() {
        return this.mDisplayUnit;
    }

    public String toString() {
        return String.format(Locale.US, "%.04f%s", new Object[]{Double.valueOf(this.mDisplayDistance), unitToString(this.mDisplayUnit)});
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.mDisplayDistance), Integer.valueOf(this.mDisplayUnit));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Distance)) {
            return false;
        }
        Distance distance = (Distance) obj;
        if (this.mDisplayUnit == distance.mDisplayUnit && this.mDisplayDistance == distance.mDisplayDistance) {
            return true;
        }
        return false;
    }

    private Distance(double d, int i) {
        this.mDisplayDistance = d;
        this.mDisplayUnit = i;
    }

    private Distance() {
        this.mDisplayDistance = 0.0d;
        this.mDisplayUnit = 1;
    }

    private static String unitToString(int i) {
        switch (i) {
            case 1:
                return "m";
            case 2:
                return "km";
            case 3:
                return "km_p1";
            case 4:
                return "mi";
            case 5:
                return "mi_p1";
            case 6:
                return "ft";
            case 7:
                return "yd";
            default:
                return "?";
        }
    }
}
