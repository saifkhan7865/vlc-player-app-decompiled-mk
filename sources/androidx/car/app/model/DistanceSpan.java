package androidx.car.app.model;

import j$.util.Objects;

public final class DistanceSpan extends CarSpan {
    private final Distance mDistance;

    public static DistanceSpan create(Distance distance) {
        return new DistanceSpan((Distance) Objects.requireNonNull(distance));
    }

    public Distance getDistance() {
        return (Distance) Objects.requireNonNull(this.mDistance);
    }

    public String toString() {
        return "[distance: " + this.mDistance + "]";
    }

    public int hashCode() {
        return Objects.hashCode(this.mDistance);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DistanceSpan)) {
            return false;
        }
        return Objects.equals(this.mDistance, ((DistanceSpan) obj).mDistance);
    }

    private DistanceSpan(Distance distance) {
        this.mDistance = distance;
    }

    private DistanceSpan() {
        this.mDistance = null;
    }
}
