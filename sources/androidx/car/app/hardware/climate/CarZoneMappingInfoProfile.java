package androidx.car.app.hardware.climate;

import androidx.car.app.hardware.common.CarZone;
import java.util.List;
import java.util.Set;

public final class CarZoneMappingInfoProfile {
    private final List<Set<CarZone>> mSupportedCarZoneSets;

    public List<Set<CarZone>> getSupportedCarZoneSets() {
        return this.mSupportedCarZoneSets;
    }

    CarZoneMappingInfoProfile(Builder builder) {
        this.mSupportedCarZoneSets = builder.mSupportedCarZoneSets;
    }

    public static final class Builder {
        final List<Set<CarZone>> mSupportedCarZoneSets;

        public Builder(List<Set<CarZone>> list) {
            this.mSupportedCarZoneSets = list;
        }

        public CarZoneMappingInfoProfile build() {
            return new CarZoneMappingInfoProfile(this);
        }
    }
}
