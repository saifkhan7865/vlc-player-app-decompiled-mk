package androidx.car.app.hardware.climate;

import android.util.Pair;
import androidx.car.app.hardware.common.CarZone;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class CabinTemperatureProfile {
    static final Map<Set<CarZone>, Pair<Float, Float>> DEFAULT_CELSIUS_TEMPERATURE_MAP;
    static final float DEFAULT_TEMPERATURE_INCREMENT = -1.0f;
    static final Pair<Float, Float> DEFAULT_TEMPERATURE_RANGE;
    private final Map<Set<CarZone>, Pair<Float, Float>> mCarZoneSetsToCabinCelsiusTemperatureRanges;
    private final float mCelsiusSupportedIncrement;
    private final float mFahrenheitSupportedIncrement;
    private final Pair<Float, Float> mSupportedMinMaxCelsiusRange;
    private final Pair<Float, Float> mSupportedMinMaxFahrenheitRange;

    static {
        Float valueOf = Float.valueOf(-1.0f);
        DEFAULT_TEMPERATURE_RANGE = new Pair<>(valueOf, valueOf);
        DEFAULT_CELSIUS_TEMPERATURE_MAP = ImmutableMap.builder().put(Collections.singleton(CarZone.CAR_ZONE_GLOBAL), new Pair(valueOf, valueOf)).buildKeepingLast();
    }

    public boolean hasSupportedMinMaxCelsiusRange() {
        return !this.mSupportedMinMaxCelsiusRange.equals(DEFAULT_TEMPERATURE_RANGE);
    }

    public boolean hasSupportedMinMaxFahrenheitRange() {
        return !this.mSupportedMinMaxFahrenheitRange.equals(DEFAULT_TEMPERATURE_RANGE);
    }

    public boolean hasCarZoneSetsToCabinCelsiusTemperatureRanges() {
        return this.mCarZoneSetsToCabinCelsiusTemperatureRanges != DEFAULT_CELSIUS_TEMPERATURE_MAP;
    }

    public boolean hasCelsiusSupportedIncrement() {
        return this.mCelsiusSupportedIncrement != -1.0f;
    }

    public boolean hasFahrenheitSupportedIncrement() {
        return this.mFahrenheitSupportedIncrement != -1.0f;
    }

    public Pair<Float, Float> getSupportedMinMaxCelsiusRange() {
        if (hasSupportedMinMaxCelsiusRange()) {
            return this.mSupportedMinMaxCelsiusRange;
        }
        throw new IllegalStateException("Celsius min/max range is not available.");
    }

    public Pair<Float, Float> getSupportedMinMaxFahrenheitRange() {
        if (hasSupportedMinMaxFahrenheitRange()) {
            return this.mSupportedMinMaxFahrenheitRange;
        }
        throw new IllegalStateException("Fahrenheit min/max range is not available.");
    }

    public Map<Set<CarZone>, Pair<Float, Float>> getCarZoneSetsToCabinCelsiusTemperatureRanges() {
        if (hasCarZoneSetsToCabinCelsiusTemperatureRanges()) {
            return this.mCarZoneSetsToCabinCelsiusTemperatureRanges;
        }
        throw new IllegalStateException("Celsius min/max range corresponding to car zones is not available.");
    }

    public float getCelsiusSupportedIncrement() {
        if (hasCelsiusSupportedIncrement()) {
            return this.mCelsiusSupportedIncrement;
        }
        throw new IllegalStateException("Celsius increment value is not available.");
    }

    public float getFahrenheitSupportedIncrement() {
        if (hasFahrenheitSupportedIncrement()) {
            return this.mFahrenheitSupportedIncrement;
        }
        throw new IllegalStateException("Fahrenheit increment value is not available.");
    }

    CabinTemperatureProfile(Builder builder) {
        this.mSupportedMinMaxCelsiusRange = builder.mSupportedMinMaxCelsiusRange;
        this.mSupportedMinMaxFahrenheitRange = builder.mSupportedMinMaxFahrenheitRange;
        this.mCarZoneSetsToCabinCelsiusTemperatureRanges = builder.mCarZoneSetsToCabinCelsiusTemperatureRanges;
        this.mCelsiusSupportedIncrement = builder.mCelsiusSupportedIncrement;
        this.mFahrenheitSupportedIncrement = builder.mFahrenheitSupportedIncrement;
    }

    public static final class Builder {
        Map<Set<CarZone>, Pair<Float, Float>> mCarZoneSetsToCabinCelsiusTemperatureRanges = CabinTemperatureProfile.DEFAULT_CELSIUS_TEMPERATURE_MAP;
        float mCelsiusSupportedIncrement = -1.0f;
        float mFahrenheitSupportedIncrement = -1.0f;
        Pair<Float, Float> mSupportedMinMaxCelsiusRange = CabinTemperatureProfile.DEFAULT_TEMPERATURE_RANGE;
        Pair<Float, Float> mSupportedMinMaxFahrenheitRange = CabinTemperatureProfile.DEFAULT_TEMPERATURE_RANGE;

        public Builder setSupportedMinMaxCelsiusRange(Pair<Float, Float> pair) {
            this.mSupportedMinMaxCelsiusRange = pair;
            return this;
        }

        public Builder setSupportedMinMaxFahrenheitRange(Pair<Float, Float> pair) {
            this.mSupportedMinMaxFahrenheitRange = pair;
            return this;
        }

        public Builder setCarZoneSetsToCabinCelsiusTemperatureRanges(Map<Set<CarZone>, Pair<Float, Float>> map) {
            this.mCarZoneSetsToCabinCelsiusTemperatureRanges = map;
            return this;
        }

        public Builder setCelsiusSupportedIncrement(float f) {
            this.mCelsiusSupportedIncrement = f;
            return this;
        }

        public Builder setFahrenheitSupportedIncrement(float f) {
            this.mFahrenheitSupportedIncrement = f;
            return this;
        }

        public CabinTemperatureProfile build() {
            return new CabinTemperatureProfile(this);
        }
    }
}
