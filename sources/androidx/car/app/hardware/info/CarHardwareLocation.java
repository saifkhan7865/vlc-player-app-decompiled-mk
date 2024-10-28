package androidx.car.app.hardware.info;

import android.location.Location;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;

@RequiresCarApi(3)
public final class CarHardwareLocation {
    public static final CarValue<Location> UNIMPLEMENTED_LOCATION = new CarValue<>(null, 0, 2);
    public static final CarValue<Location> UNKNOWN_LOCATION = new CarValue<>(null, 0, 0);
    private final CarValue<Location> mLocation;

    public CarValue<Location> getLocation() {
        return this.mLocation;
    }

    public String toString() {
        return "[ location: " + this.mLocation + " ]";
    }

    public int hashCode() {
        return Objects.hash(this.mLocation);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CarHardwareLocation)) {
            return false;
        }
        return Objects.equals(this.mLocation, ((CarHardwareLocation) obj).mLocation);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<android.location.Location>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CarHardwareLocation(androidx.car.app.hardware.common.CarValue<android.location.Location> r1) {
        /*
            r0 = this;
            r0.<init>()
            java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
            androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
            r0.mLocation = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.CarHardwareLocation.<init>(androidx.car.app.hardware.common.CarValue):void");
    }

    private CarHardwareLocation() {
        this.mLocation = UNKNOWN_LOCATION;
    }
}
