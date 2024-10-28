package androidx.car.app.hardware.climate;

import android.util.Pair;
import androidx.car.app.hardware.common.CarZone;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class FanSpeedLevelProfile {
    private final Map<Set<CarZone>, Pair<Integer, Integer>> mCarZoneSetsToFanSpeedLevelRanges;

    public Map<Set<CarZone>, Pair<Integer, Integer>> getCarZoneSetsToFanSpeedLevelRanges() {
        return this.mCarZoneSetsToFanSpeedLevelRanges;
    }

    FanSpeedLevelProfile(Builder builder) {
        this.mCarZoneSetsToFanSpeedLevelRanges = Collections.unmodifiableMap(builder.mCarZoneSetsToFanSpeedLevelRanges);
    }

    public static final class Builder {
        Map<Set<CarZone>, Pair<Integer, Integer>> mCarZoneSetsToFanSpeedLevelRanges;

        public Builder(Map<Set<CarZone>, Pair<Integer, Integer>> map) {
            this.mCarZoneSetsToFanSpeedLevelRanges = Collections.unmodifiableMap(map);
        }

        public FanSpeedLevelProfile build() {
            return new FanSpeedLevelProfile(this);
        }
    }
}
