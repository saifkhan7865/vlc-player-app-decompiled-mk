package androidx.car.app.hardware.info;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@RequiresCarApi(3)
public final class EnergyProfile {
    public static final int EVCONNECTOR_TYPE_CHADEMO = 3;
    public static final int EVCONNECTOR_TYPE_COMBO_1 = 4;
    public static final int EVCONNECTOR_TYPE_COMBO_2 = 5;
    public static final int EVCONNECTOR_TYPE_GBT = 9;
    public static final int EVCONNECTOR_TYPE_GBT_DC = 10;
    public static final int EVCONNECTOR_TYPE_J1772 = 1;
    public static final int EVCONNECTOR_TYPE_MENNEKES = 2;
    public static final int EVCONNECTOR_TYPE_OTHER = 101;
    public static final int EVCONNECTOR_TYPE_SCAME = 11;
    public static final int EVCONNECTOR_TYPE_TESLA_HPWC = 7;
    public static final int EVCONNECTOR_TYPE_TESLA_ROADSTER = 6;
    public static final int EVCONNECTOR_TYPE_TESLA_SUPERCHARGER = 8;
    public static final int EVCONNECTOR_TYPE_UNKNOWN = 0;
    public static final int FUEL_TYPE_BIODIESEL = 5;
    public static final int FUEL_TYPE_CNG = 8;
    public static final int FUEL_TYPE_DIESEL_1 = 3;
    public static final int FUEL_TYPE_DIESEL_2 = 4;
    public static final int FUEL_TYPE_E85 = 6;
    public static final int FUEL_TYPE_ELECTRIC = 10;
    public static final int FUEL_TYPE_HYDROGEN = 11;
    public static final int FUEL_TYPE_LEADED = 2;
    public static final int FUEL_TYPE_LNG = 9;
    public static final int FUEL_TYPE_LPG = 7;
    public static final int FUEL_TYPE_OTHER = 12;
    public static final int FUEL_TYPE_UNKNOWN = 0;
    public static final int FUEL_TYPE_UNLEADED = 1;
    private final CarValue<List<Integer>> mEvConnectorTypes;
    private final CarValue<List<Integer>> mFuelTypes;

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EvConnectorType {
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FuelType {
    }

    public CarValue<List<Integer>> getEvConnectorTypes() {
        return (CarValue) Objects.requireNonNull(this.mEvConnectorTypes);
    }

    public CarValue<List<Integer>> getFuelTypes() {
        return (CarValue) Objects.requireNonNull(this.mFuelTypes);
    }

    public String toString() {
        return "[ evConnectorTypes: " + this.mEvConnectorTypes + ", fuelTypes: " + this.mFuelTypes + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mEvConnectorTypes, this.mFuelTypes);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EnergyProfile)) {
            return false;
        }
        EnergyProfile energyProfile = (EnergyProfile) obj;
        if (!Objects.equals(this.mEvConnectorTypes, energyProfile.mEvConnectorTypes) || !Objects.equals(this.mFuelTypes, energyProfile.mFuelTypes)) {
            return false;
        }
        return true;
    }

    EnergyProfile(Builder builder) {
        this.mEvConnectorTypes = (CarValue) Objects.requireNonNull(builder.mEvConnectorTypes);
        this.mFuelTypes = (CarValue) Objects.requireNonNull(builder.mFuelTypes);
    }

    private EnergyProfile() {
        this.mEvConnectorTypes = CarValue.UNKNOWN_INTEGER_LIST;
        this.mFuelTypes = CarValue.UNKNOWN_INTEGER_LIST;
    }

    public static final class Builder {
        CarValue<List<Integer>> mEvConnectorTypes = CarValue.UNKNOWN_INTEGER_LIST;
        CarValue<List<Integer>> mFuelTypes = CarValue.UNKNOWN_INTEGER_LIST;

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.util.List<java.lang.Integer>>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.EnergyProfile.Builder setEvConnectorTypes(androidx.car.app.hardware.common.CarValue<java.util.List<java.lang.Integer>> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mEvConnectorTypes = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.EnergyProfile.Builder.setEvConnectorTypes(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.EnergyProfile$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.util.List<java.lang.Integer>>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.EnergyProfile.Builder setFuelTypes(androidx.car.app.hardware.common.CarValue<java.util.List<java.lang.Integer>> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mFuelTypes = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.EnergyProfile.Builder.setFuelTypes(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.EnergyProfile$Builder");
        }

        public EnergyProfile build() {
            return new EnergyProfile(this);
        }
    }
}
