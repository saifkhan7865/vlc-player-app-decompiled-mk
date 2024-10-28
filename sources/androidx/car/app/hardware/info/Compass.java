package androidx.car.app.hardware.info;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;
import java.util.List;

@RequiresCarApi(3)
public final class Compass {
    private final CarValue<List<Float>> mOrientations;

    public CarValue<List<Float>> getOrientations() {
        return this.mOrientations;
    }

    public String toString() {
        return "[ orientations: " + this.mOrientations + " ]";
    }

    public int hashCode() {
        return Objects.hash(this.mOrientations);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Compass)) {
            return false;
        }
        return Objects.equals(this.mOrientations, ((Compass) obj).mOrientations);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.util.List<java.lang.Float>>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Compass(androidx.car.app.hardware.common.CarValue<java.util.List<java.lang.Float>> r1) {
        /*
            r0 = this;
            r0.<init>()
            java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
            androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
            r0.mOrientations = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Compass.<init>(androidx.car.app.hardware.common.CarValue):void");
    }

    private Compass() {
        this.mOrientations = CarValue.UNKNOWN_FLOAT_LIST;
    }
}
