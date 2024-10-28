package androidx.car.app.hardware.climate;

import androidx.car.app.hardware.common.CarZone;
import java.util.List;
import java.util.Set;

public final class HvacPowerProfile {
    private final List<Set<CarZone>> mSupportedCarZoneSets;

    public List<Set<CarZone>> getSupportedCarZoneSets() {
        return this.mSupportedCarZoneSets;
    }

    HvacPowerProfile(Builder builder) {
        this.mSupportedCarZoneSets = builder.mSupportedCarZoneSets;
    }

    public static final class Builder {
        final List<Set<CarZone>> mSupportedCarZoneSets;

        public Builder(List<Set<CarZone>> list) {
            this.mSupportedCarZoneSets = list;
        }

        public HvacPowerProfile build() {
            return new HvacPowerProfile(this);
        }
    }
}
