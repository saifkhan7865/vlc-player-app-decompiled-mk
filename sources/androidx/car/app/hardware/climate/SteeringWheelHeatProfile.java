package androidx.car.app.hardware.climate;

import android.util.Pair;
import androidx.car.app.hardware.common.CarZone;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class SteeringWheelHeatProfile {
    private final Map<Set<CarZone>, Pair<Integer, Integer>> mCarZoneSetsToSteeringWheelHeatValues;

    public Map<Set<CarZone>, Pair<Integer, Integer>> getCarZoneSetsToSteeringWheelHeatValues() {
        return this.mCarZoneSetsToSteeringWheelHeatValues;
    }

    SteeringWheelHeatProfile(Builder builder) {
        this.mCarZoneSetsToSteeringWheelHeatValues = Collections.unmodifiableMap(builder.mCarZoneSetsToSteeringWheelHeatValues);
    }

    public static final class Builder {
        Map<Set<CarZone>, Pair<Integer, Integer>> mCarZoneSetsToSteeringWheelHeatValues;

        public Builder(Map<Set<CarZone>, Pair<Integer, Integer>> map) {
            this.mCarZoneSetsToSteeringWheelHeatValues = Collections.unmodifiableMap(map);
        }

        public SteeringWheelHeatProfile build() {
            return new SteeringWheelHeatProfile(this);
        }
    }
}
