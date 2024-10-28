package androidx.car.app.hardware.info;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;

@RequiresCarApi(3)
public final class Mileage {
    private final CarValue<Integer> mDistanceDisplayUnit;
    private final CarValue<Float> mOdometerMeters;

    public CarValue<Float> getOdometerMeters() {
        return (CarValue) Objects.requireNonNull(this.mOdometerMeters);
    }

    public CarValue<Integer> getDistanceDisplayUnit() {
        return (CarValue) Objects.requireNonNull(this.mDistanceDisplayUnit);
    }

    public String toString() {
        return "[ odometer: " + getOdometerMeters() + ", distance display unit: " + this.mDistanceDisplayUnit + "]";
    }

    public int hashCode() {
        return Objects.hash(getOdometerMeters(), this.mDistanceDisplayUnit);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Mileage)) {
            return false;
        }
        Mileage mileage = (Mileage) obj;
        if (!Objects.equals(getOdometerMeters(), mileage.getOdometerMeters()) || !Objects.equals(this.mDistanceDisplayUnit, mileage.mDistanceDisplayUnit)) {
            return false;
        }
        return true;
    }

    Mileage(Builder builder) {
        this.mOdometerMeters = (CarValue) Objects.requireNonNull(builder.mOdometerMeters);
        this.mDistanceDisplayUnit = (CarValue) Objects.requireNonNull(builder.mDistanceDisplayUnit);
    }

    private Mileage() {
        this.mOdometerMeters = CarValue.UNKNOWN_FLOAT;
        this.mDistanceDisplayUnit = CarValue.UNKNOWN_INTEGER;
    }

    public static final class Builder {
        CarValue<Integer> mDistanceDisplayUnit = CarValue.UNKNOWN_INTEGER;
        CarValue<Float> mOdometerMeters = CarValue.UNKNOWN_FLOAT;

        /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.lang.Float>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.Mileage.Builder setOdometerMeters(androidx.car.app.hardware.common.CarValue<java.lang.Float> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mOdometerMeters = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Mileage.Builder.setOdometerMeters(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.Mileage$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.lang.Integer>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.Mileage.Builder setDistanceDisplayUnit(androidx.car.app.hardware.common.CarValue<java.lang.Integer> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mDistanceDisplayUnit = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Mileage.Builder.setDistanceDisplayUnit(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.Mileage$Builder");
        }

        public Mileage build() {
            return new Mileage(this);
        }
    }
}
