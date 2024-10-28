package androidx.car.app.hardware.info;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;

@RequiresCarApi(3)
public final class EnergyLevel {
    private final CarValue<Float> mBatteryPercent;
    private final CarValue<Integer> mDistanceDisplayUnit;
    private final CarValue<Boolean> mEnergyIsLow;
    private final CarValue<Float> mFuelPercent;
    private final CarValue<Integer> mFuelVolumeDisplayUnit;
    private final CarValue<Float> mRangeRemainingMeters;

    public CarValue<Float> getBatteryPercent() {
        return (CarValue) Objects.requireNonNull(this.mBatteryPercent);
    }

    public CarValue<Float> getFuelPercent() {
        return (CarValue) Objects.requireNonNull(this.mFuelPercent);
    }

    public CarValue<Boolean> getEnergyIsLow() {
        return (CarValue) Objects.requireNonNull(this.mEnergyIsLow);
    }

    public CarValue<Float> getRangeRemainingMeters() {
        return (CarValue) Objects.requireNonNull(this.mRangeRemainingMeters);
    }

    public CarValue<Integer> getDistanceDisplayUnit() {
        return (CarValue) Objects.requireNonNull(this.mDistanceDisplayUnit);
    }

    public CarValue<Integer> getFuelVolumeDisplayUnit() {
        return (CarValue) Objects.requireNonNull(this.mFuelVolumeDisplayUnit);
    }

    public String toString() {
        return "[ battery percent: " + this.mBatteryPercent + ", fuel percent: " + this.mFuelPercent + ", energyIsLow: " + this.mEnergyIsLow + ", range remaining: " + getRangeRemainingMeters() + ", distance display unit: " + this.mDistanceDisplayUnit + ", fuel volume display unit: " + this.mFuelVolumeDisplayUnit + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mBatteryPercent, this.mFuelPercent, this.mEnergyIsLow, getRangeRemainingMeters(), this.mDistanceDisplayUnit, this.mFuelVolumeDisplayUnit);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EnergyLevel)) {
            return false;
        }
        EnergyLevel energyLevel = (EnergyLevel) obj;
        if (!Objects.equals(this.mBatteryPercent, energyLevel.mBatteryPercent) || !Objects.equals(this.mFuelPercent, energyLevel.mFuelPercent) || !Objects.equals(this.mEnergyIsLow, energyLevel.mEnergyIsLow) || !Objects.equals(getRangeRemainingMeters(), energyLevel.getRangeRemainingMeters()) || !Objects.equals(this.mDistanceDisplayUnit, energyLevel.mDistanceDisplayUnit) || !Objects.equals(this.mFuelVolumeDisplayUnit, energyLevel.mFuelVolumeDisplayUnit)) {
            return false;
        }
        return true;
    }

    EnergyLevel(Builder builder) {
        this.mBatteryPercent = (CarValue) Objects.requireNonNull(builder.mBatteryPercent);
        this.mFuelPercent = (CarValue) Objects.requireNonNull(builder.mFuelPercent);
        this.mEnergyIsLow = (CarValue) Objects.requireNonNull(builder.mEnergyIsLow);
        this.mRangeRemainingMeters = (CarValue) Objects.requireNonNull(builder.mRangeRemainingMeters);
        this.mDistanceDisplayUnit = (CarValue) Objects.requireNonNull(builder.mDistanceDisplayUnit);
        this.mFuelVolumeDisplayUnit = (CarValue) Objects.requireNonNull(builder.mFuelVolumeDisplayUnit);
    }

    private EnergyLevel() {
        this.mBatteryPercent = CarValue.UNKNOWN_FLOAT;
        this.mFuelPercent = CarValue.UNKNOWN_FLOAT;
        this.mEnergyIsLow = CarValue.UNKNOWN_BOOLEAN;
        this.mRangeRemainingMeters = CarValue.UNKNOWN_FLOAT;
        this.mDistanceDisplayUnit = CarValue.UNKNOWN_INTEGER;
        this.mFuelVolumeDisplayUnit = CarValue.UNKNOWN_INTEGER;
    }

    public static final class Builder {
        CarValue<Float> mBatteryPercent = CarValue.UNKNOWN_FLOAT;
        CarValue<Integer> mDistanceDisplayUnit = CarValue.UNKNOWN_INTEGER;
        CarValue<Boolean> mEnergyIsLow = CarValue.UNKNOWN_BOOLEAN;
        CarValue<Float> mFuelPercent = CarValue.UNKNOWN_FLOAT;
        CarValue<Integer> mFuelVolumeDisplayUnit = CarValue.UNKNOWN_INTEGER;
        CarValue<Float> mRangeRemainingMeters = CarValue.UNKNOWN_FLOAT;

        /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.lang.Float>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.EnergyLevel.Builder setBatteryPercent(androidx.car.app.hardware.common.CarValue<java.lang.Float> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mBatteryPercent = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.EnergyLevel.Builder.setBatteryPercent(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.EnergyLevel$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.lang.Float>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.EnergyLevel.Builder setFuelPercent(androidx.car.app.hardware.common.CarValue<java.lang.Float> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mFuelPercent = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.EnergyLevel.Builder.setFuelPercent(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.EnergyLevel$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.lang.Boolean>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.EnergyLevel.Builder setEnergyIsLow(androidx.car.app.hardware.common.CarValue<java.lang.Boolean> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mEnergyIsLow = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.EnergyLevel.Builder.setEnergyIsLow(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.EnergyLevel$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [androidx.car.app.hardware.common.CarValue<java.lang.Float>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.EnergyLevel.Builder setRangeRemainingMeters(androidx.car.app.hardware.common.CarValue<java.lang.Float> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mRangeRemainingMeters = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.EnergyLevel.Builder.setRangeRemainingMeters(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.EnergyLevel$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.lang.Integer>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.EnergyLevel.Builder setDistanceDisplayUnit(androidx.car.app.hardware.common.CarValue<java.lang.Integer> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mDistanceDisplayUnit = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.EnergyLevel.Builder.setDistanceDisplayUnit(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.EnergyLevel$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.lang.Integer>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.EnergyLevel.Builder setFuelVolumeDisplayUnit(androidx.car.app.hardware.common.CarValue<java.lang.Integer> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mFuelVolumeDisplayUnit = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.EnergyLevel.Builder.setFuelVolumeDisplayUnit(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.EnergyLevel$Builder");
        }

        public EnergyLevel build() {
            return new EnergyLevel(this);
        }
    }
}
