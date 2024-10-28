package androidx.car.app.hardware.info;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;

@RequiresCarApi(3)
public final class Speed {
    private final CarValue<Float> mDisplaySpeedMetersPerSecond;
    private final CarValue<Float> mRawSpeedMetersPerSecond;
    private final CarValue<Integer> mSpeedDisplayUnit;

    public CarValue<Float> getRawSpeedMetersPerSecond() {
        return (CarValue) Objects.requireNonNull(this.mRawSpeedMetersPerSecond);
    }

    public CarValue<Float> getDisplaySpeedMetersPerSecond() {
        return (CarValue) Objects.requireNonNull(this.mDisplaySpeedMetersPerSecond);
    }

    public CarValue<Integer> getSpeedDisplayUnit() {
        return (CarValue) Objects.requireNonNull(this.mSpeedDisplayUnit);
    }

    public String toString() {
        return "[ raw speed: " + getRawSpeedMetersPerSecond() + ", display speed: " + getDisplaySpeedMetersPerSecond() + ", speed display unit: " + this.mSpeedDisplayUnit + "]";
    }

    public int hashCode() {
        return Objects.hash(getRawSpeedMetersPerSecond(), getDisplaySpeedMetersPerSecond(), this.mSpeedDisplayUnit);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Speed)) {
            return false;
        }
        Speed speed = (Speed) obj;
        if (!Objects.equals(getRawSpeedMetersPerSecond(), speed.getRawSpeedMetersPerSecond()) || !Objects.equals(getDisplaySpeedMetersPerSecond(), speed.getDisplaySpeedMetersPerSecond()) || !Objects.equals(this.mSpeedDisplayUnit, speed.mSpeedDisplayUnit)) {
            return false;
        }
        return true;
    }

    Speed(Builder builder) {
        this.mRawSpeedMetersPerSecond = (CarValue) Objects.requireNonNull(builder.mRawSpeedMetersPerSecond);
        this.mDisplaySpeedMetersPerSecond = (CarValue) Objects.requireNonNull(builder.mDisplaySpeedMetersPerSecond);
        this.mSpeedDisplayUnit = (CarValue) Objects.requireNonNull(builder.mSpeedDisplayUnit);
    }

    private Speed() {
        this.mRawSpeedMetersPerSecond = CarValue.UNKNOWN_FLOAT;
        this.mDisplaySpeedMetersPerSecond = CarValue.UNKNOWN_FLOAT;
        this.mSpeedDisplayUnit = CarValue.UNKNOWN_INTEGER;
    }

    public static final class Builder {
        CarValue<Float> mDisplaySpeedMetersPerSecond = CarValue.UNKNOWN_FLOAT;
        CarValue<Float> mRawSpeedMetersPerSecond = CarValue.UNKNOWN_FLOAT;
        CarValue<Integer> mSpeedDisplayUnit = CarValue.UNKNOWN_INTEGER;

        /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.lang.Float>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.Speed.Builder setRawSpeedMetersPerSecond(androidx.car.app.hardware.common.CarValue<java.lang.Float> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mRawSpeedMetersPerSecond = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Speed.Builder.setRawSpeedMetersPerSecond(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.Speed$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.lang.Float>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.Speed.Builder setDisplaySpeedMetersPerSecond(androidx.car.app.hardware.common.CarValue<java.lang.Float> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mDisplaySpeedMetersPerSecond = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Speed.Builder.setDisplaySpeedMetersPerSecond(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.Speed$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.lang.Integer>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.Speed.Builder setSpeedDisplayUnit(androidx.car.app.hardware.common.CarValue<java.lang.Integer> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mSpeedDisplayUnit = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.Speed.Builder.setSpeedDisplayUnit(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.Speed$Builder");
        }

        public Speed build() {
            return new Speed(this);
        }
    }
}
