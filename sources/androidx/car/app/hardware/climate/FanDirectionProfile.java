package androidx.car.app.hardware.climate;

import androidx.car.app.hardware.common.CarZone;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class FanDirectionProfile {
    private final Map<Set<CarZone>, Set<Integer>> mCarZoneSetsToFanDirectionValues;

    public Map<Set<CarZone>, Set<Integer>> getCarZoneSetsToFanDirectionValues() {
        return this.mCarZoneSetsToFanDirectionValues;
    }

    FanDirectionProfile(Builder builder) {
        this.mCarZoneSetsToFanDirectionValues = Collections.unmodifiableMap(builder.mCarZoneSetsToFanDirectionValues);
    }

    public static final class Builder {
        Map<Set<CarZone>, Set<Integer>> mCarZoneSetsToFanDirectionValues;

        public Builder(Map<Set<CarZone>, Set<Integer>> map) {
            this.mCarZoneSetsToFanDirectionValues = Collections.unmodifiableMap(map);
        }

        public FanDirectionProfile build() {
            return new FanDirectionProfile(this);
        }
    }
}
