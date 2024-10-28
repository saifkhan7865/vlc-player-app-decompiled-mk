package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;

public class EvStatus {
    private final CarValue<Boolean> mEvChargePortConnected;
    private final CarValue<Boolean> mEvChargePortOpen;

    public CarValue<Boolean> getEvChargePortOpen() {
        return (CarValue) Objects.requireNonNull(this.mEvChargePortOpen);
    }

    public CarValue<Boolean> getEvChargePortConnected() {
        return (CarValue) Objects.requireNonNull(this.mEvChargePortConnected);
    }

    public String toString() {
        return "[ EV charge port open: " + this.mEvChargePortOpen + ", EV charge port connected: " + this.mEvChargePortConnected + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mEvChargePortOpen, this.mEvChargePortConnected);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EvStatus)) {
            return false;
        }
        EvStatus evStatus = (EvStatus) obj;
        if (!Objects.equals(this.mEvChargePortConnected, evStatus.mEvChargePortConnected) || !Objects.equals(this.mEvChargePortOpen, evStatus.mEvChargePortOpen)) {
            return false;
        }
        return true;
    }

    EvStatus(Builder builder) {
        this.mEvChargePortConnected = builder.mEvChargePortConnected;
        this.mEvChargePortOpen = builder.mEvChargePortOpen;
    }

    private EvStatus() {
        this.mEvChargePortOpen = CarValue.UNKNOWN_BOOLEAN;
        this.mEvChargePortConnected = CarValue.UNKNOWN_BOOLEAN;
    }

    public static final class Builder {
        CarValue<Boolean> mEvChargePortConnected = CarValue.UNKNOWN_BOOLEAN;
        CarValue<Boolean> mEvChargePortOpen = CarValue.UNKNOWN_BOOLEAN;

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.lang.Boolean>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.EvStatus.Builder setEvChargePortOpen(androidx.car.app.hardware.common.CarValue<java.lang.Boolean> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mEvChargePortOpen = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.EvStatus.Builder.setEvChargePortOpen(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.EvStatus$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.lang.Boolean>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.EvStatus.Builder setEvChargePortConnected(androidx.car.app.hardware.common.CarValue<java.lang.Boolean> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mEvChargePortConnected = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.EvStatus.Builder.setEvChargePortConnected(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.EvStatus$Builder");
        }

        public EvStatus build() {
            return new EvStatus(this);
        }
    }
}
