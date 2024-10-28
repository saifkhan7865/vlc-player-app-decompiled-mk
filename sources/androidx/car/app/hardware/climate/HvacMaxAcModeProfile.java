package androidx.car.app.hardware.climate;

import androidx.car.app.hardware.common.CarZone;
import java.util.List;
import java.util.Set;

public final class HvacMaxAcModeProfile {
    private final List<Set<CarZone>> mSupportedCarZoneSets;

    public List<Set<CarZone>> getSupportedCarZoneSets() {
        return this.mSupportedCarZoneSets;
    }

    HvacMaxAcModeProfile(Builder builder) {
        this.mSupportedCarZoneSets = builder.mSupportedCarZoneSets;
    }

    public static final class Builder {
        final List<Set<CarZone>> mSupportedCarZoneSets;

        public Builder(List<Set<CarZone>> list) {
            this.mSupportedCarZoneSets = list;
        }

        public HvacMaxAcModeProfile build() {
            return new HvacMaxAcModeProfile(this);
        }
    }
}
