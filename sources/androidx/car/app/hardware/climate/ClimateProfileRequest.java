package androidx.car.app.hardware.climate;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.climate.CarClimateFeature;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@RequiresCarApi(5)
public final class ClimateProfileRequest {
    static final Set<Integer> ALL_FEATURES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17})));
    public static final int FEATURE_CABIN_TEMPERATURE = 4;
    public static final int FEATURE_CAR_ZONE_MAPPING = 17;
    public static final int FEATURE_FAN_DIRECTION = 6;
    public static final int FEATURE_FAN_SPEED = 5;
    public static final int FEATURE_HVAC_AC = 2;
    public static final int FEATURE_HVAC_AUTO_MODE = 12;
    public static final int FEATURE_HVAC_AUTO_RECIRCULATION = 11;
    public static final int FEATURE_HVAC_DEFROSTER = 14;
    public static final int FEATURE_HVAC_DUAL_MODE = 13;
    public static final int FEATURE_HVAC_ELECTRIC_DEFROSTER = 16;
    public static final int FEATURE_HVAC_MAX_AC = 3;
    public static final int FEATURE_HVAC_MAX_DEFROSTER = 15;
    public static final int FEATURE_HVAC_POWER = 1;
    public static final int FEATURE_HVAC_RECIRCULATION = 10;
    public static final int FEATURE_SEAT_TEMPERATURE_LEVEL = 7;
    public static final int FEATURE_SEAT_VENTILATION_LEVEL = 8;
    public static final int FEATURE_STEERING_WHEEL_HEAT = 9;
    private final List<CarClimateFeature> mRequestFeatures;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ClimateProfileFeature {
    }

    ClimateProfileRequest(Builder builder) {
        if (builder.mAllProfiles) {
            this.mRequestFeatures = Collections.unmodifiableList(constructAllFeatures());
        } else {
            this.mRequestFeatures = Collections.unmodifiableList(builder.mFeatures);
        }
    }

    public List<CarClimateFeature> getClimateProfileFeatures() {
        return this.mRequestFeatures;
    }

    public Set<Integer> getAllClimateProfiles() {
        return ALL_FEATURES;
    }

    public String toString() {
        return "ClimateProfileRequest{mRequestFeatures=" + this.mRequestFeatures + AbstractJsonLexerKt.END_OBJ;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.mRequestFeatures, ((ClimateProfileRequest) obj).mRequestFeatures);
    }

    public int hashCode() {
        return Objects.hash(this.mRequestFeatures);
    }

    private List<CarClimateFeature> constructAllFeatures() {
        Set<Integer> set = ALL_FEATURES;
        ArrayList arrayList = new ArrayList(set.size());
        for (Integer intValue : set) {
            arrayList.add(new CarClimateFeature.Builder(intValue.intValue()).build());
        }
        return arrayList;
    }

    public static final class Builder {
        boolean mAllProfiles = false;
        List<CarClimateFeature> mFeatures = new ArrayList();

        public Builder setAllClimateProfiles() {
            this.mAllProfiles = true;
            return this;
        }

        public Builder addClimateProfileFeatures(CarClimateFeature... carClimateFeatureArr) {
            int length = carClimateFeatureArr.length;
            int i = 0;
            while (i < length) {
                CarClimateFeature carClimateFeature = carClimateFeatureArr[i];
                int feature = carClimateFeature.getFeature();
                if (!ClimateProfileRequest.ALL_FEATURES.contains(Integer.valueOf(feature))) {
                    throw new IllegalArgumentException("Invalid flag for climate profile request: " + feature);
                } else if (!this.mFeatures.contains(carClimateFeature)) {
                    this.mFeatures.add(carClimateFeature);
                    i++;
                } else {
                    throw new IllegalArgumentException("Flag already registered in climate profile request: " + feature);
                }
            }
            return this;
        }

        public ClimateProfileRequest build() {
            return new ClimateProfileRequest(this);
        }
    }
}
