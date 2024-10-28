package androidx.car.app.hardware.info;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;
import java.util.List;

@RequiresCarApi(3)
public final class Accelerometer {
    private final CarValue<List<Float>> mForces;

    public CarValue<List<Float>> getForces() {
        return this.mForces;
    }

    public String toString() {
        return "[ forces: " + this.mForces + " ]";
    }

    public int hashCode() {
        return Objects.hash(this.mForces);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Accelerometer)) {
            return false;
        }
        return Objects.equals(this.mForces, ((Accelerometer) obj).mForces);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.util.List<java.lang.Float>>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Accelerometer(androidx.car.app.hardware.common.CarValue<java.util.List<java.lang.Float>> r1) {
        /*
            r0 = this;
            r0.<init>()
            java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
            androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
            r0.mForces = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Accelerometer.<init>(androidx.car.app.hardware.common.CarValue):void");
    }

    private Accelerometer() {
        this.mForces = CarValue.UNKNOWN_FLOAT_LIST;
    }
}
