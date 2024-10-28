package androidx.car.app.hardware.climate;

import android.util.Pair;
import androidx.car.app.hardware.common.CarZone;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class SeatVentilationProfile {
    private final Map<Set<CarZone>, Pair<Integer, Integer>> mCarZoneSetsToSeatVentilationValues;

    public Map<Set<CarZone>, Pair<Integer, Integer>> getCarZoneSetsToSeatVentilationValues() {
        return this.mCarZoneSetsToSeatVentilationValues;
    }

    SeatVentilationProfile(Builder builder) {
        this.mCarZoneSetsToSeatVentilationValues = Collections.unmodifiableMap(builder.mCarZoneSetsToSeatVentilationValues);
    }

    public static final class Builder {
        Map<Set<CarZone>, Pair<Integer, Integer>> mCarZoneSetsToSeatVentilationValues;

        public Builder(Map<Set<CarZone>, Pair<Integer, Integer>> map) {
            this.mCarZoneSetsToSeatVentilationValues = Collections.unmodifiableMap(map);
        }

        public SeatVentilationProfile build() {
            return new SeatVentilationProfile(this);
        }
    }
}
