package androidx.car.app.navigation.model;

import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class LaneDirection {
    public static final int SHAPE_NORMAL_LEFT = 5;
    public static final int SHAPE_NORMAL_RIGHT = 6;
    public static final int SHAPE_SHARP_LEFT = 7;
    public static final int SHAPE_SHARP_RIGHT = 8;
    public static final int SHAPE_SLIGHT_LEFT = 3;
    public static final int SHAPE_SLIGHT_RIGHT = 4;
    public static final int SHAPE_STRAIGHT = 2;
    public static final int SHAPE_UNKNOWN = 1;
    public static final int SHAPE_U_TURN_LEFT = 9;
    public static final int SHAPE_U_TURN_RIGHT = 10;
    private final boolean mIsRecommended;
    private final int mShape;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape {
    }

    public static LaneDirection create(int i, boolean z) {
        return new LaneDirection(i, z);
    }

    public int getShape() {
        return this.mShape;
    }

    public boolean isRecommended() {
        return this.mIsRecommended;
    }

    public String toString() {
        return "[shape: " + this.mShape + ", isRecommended: " + this.mIsRecommended + "]";
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mShape), Boolean.valueOf(this.mIsRecommended));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LaneDirection)) {
            return false;
        }
        LaneDirection laneDirection = (LaneDirection) obj;
        if (this.mShape == laneDirection.mShape && this.mIsRecommended == laneDirection.mIsRecommended) {
            return true;
        }
        return false;
    }

    private LaneDirection(int i, boolean z) {
        this.mShape = i;
        this.mIsRecommended = z;
    }

    private LaneDirection() {
        this.mShape = 1;
        this.mIsRecommended = false;
    }
}
