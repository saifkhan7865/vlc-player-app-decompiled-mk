package androidx.car.app.hardware.climate;

import androidx.car.app.hardware.common.CarZone;
import java.util.List;
import java.util.Set;

public final class HvacRecirculationProfile {
    private final List<Set<CarZone>> mSupportedCarZones;

    public List<Set<CarZone>> getSupportedCarZones() {
        return this.mSupportedCarZones;
    }

    HvacRecirculationProfile(Builder builder) {
        this.mSupportedCarZones = builder.mSupportedCarZones;
    }

    public static final class Builder {
        final List<Set<CarZone>> mSupportedCarZones;

        public Builder(List<Set<CarZone>> list) {
            this.mSupportedCarZones = list;
        }

        public HvacRecirculationProfile build() {
            return new HvacRecirculationProfile(this);
        }
    }
}
