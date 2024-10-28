package androidx.car.app.hardware.info;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;
import java.util.List;

@RequiresCarApi(3)
public final class Gyroscope {
    private final CarValue<List<Float>> mRotations;

    public CarValue<List<Float>> getRotations() {
        return this.mRotations;
    }

    public String toString() {
        return "[ rotations: " + this.mRotations + " ]";
    }

    public int hashCode() {
        return Objects.hash(this.mRotations);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Gyroscope)) {
            return false;
        }
        return Objects.equals(this.mRotations, ((Gyroscope) obj).mRotations);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.util.List<java.lang.Float>>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Gyroscope(androidx.car.app.hardware.common.CarValue<java.util.List<java.lang.Float>> r1) {
        /*
            r0 = this;
            r0.<init>()
            java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
            androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
            r0.mRotations = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Gyroscope.<init>(androidx.car.app.hardware.common.CarValue):void");
    }

    private Gyroscope() {
        this.mRotations = CarValue.UNKNOWN_FLOAT_LIST;
    }
}
