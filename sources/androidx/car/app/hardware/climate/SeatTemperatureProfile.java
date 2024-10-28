package androidx.car.app.hardware.climate;

import android.util.Pair;
import androidx.car.app.hardware.common.CarZone;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class SeatTemperatureProfile {
    private final Map<Set<CarZone>, Pair<Integer, Integer>> mCarZoneSetsToSeatTemperatureValues;

    public Map<Set<CarZone>, Pair<Integer, Integer>> getCarZoneSetsToSeatTemperatureValues() {
        return this.mCarZoneSetsToSeatTemperatureValues;
    }

    SeatTemperatureProfile(Builder builder) {
        this.mCarZoneSetsToSeatTemperatureValues = Collections.unmodifiableMap(builder.mCarZoneSetsToSeatTemperatureValues);
    }

    public static final class Builder {
        Map<Set<CarZone>, Pair<Integer, Integer>> mCarZoneSetsToSeatTemperatureValues;

        public Builder(Map<Set<CarZone>, Pair<Integer, Integer>> map) {
            this.mCarZoneSetsToSeatTemperatureValues = Collections.unmodifiableMap(map);
        }

        public SeatTemperatureProfile build() {
            return new SeatTemperatureProfile(this);
        }
    }
}
