package androidx.car.app.hardware.climate;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.climate.CarClimateFeature;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@RequiresCarApi(5)
public final class RegisterClimateStateRequest {
    static final Set<Integer> ALL_FEATURES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16})));
    private final List<CarClimateFeature> mRequestFeatures;

    public List<CarClimateFeature> getClimateRegisterFeatures() {
        return this.mRequestFeatures;
    }

    RegisterClimateStateRequest(Builder builder) {
        if (builder.mRegisterAllFeatures) {
            this.mRequestFeatures = Collections.unmodifiableList(constructAllFeatures());
        } else {
            this.mRequestFeatures = Collections.unmodifiableList(builder.mFeatures);
        }
    }

    public String toString() {
        return "RegisterClimateStateRequest{mRequestFeatures=" + this.mRequestFeatures + AbstractJsonLexerKt.END_OBJ;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.mRequestFeatures, ((RegisterClimateStateRequest) obj).mRequestFeatures);
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
        List<CarClimateFeature> mFeatures = new ArrayList();
        final boolean mRegisterAllFeatures;

        public Builder(boolean z) {
            this.mRegisterAllFeatures = z;
        }

        public Builder addClimateRegisterFeatures(CarClimateFeature... carClimateFeatureArr) {
            int length = carClimateFeatureArr.length;
            int i = 0;
            while (i < length) {
                CarClimateFeature carClimateFeature = carClimateFeatureArr[i];
                if (RegisterClimateStateRequest.ALL_FEATURES.contains(Integer.valueOf(carClimateFeature.getFeature()))) {
                    this.mFeatures.add(carClimateFeature);
                    i++;
                } else {
                    throw new IllegalArgumentException("Invalid flag for registering climate request: " + carClimateFeature.getFeature());
                }
            }
            return this;
        }

        public RegisterClimateStateRequest build() {
            return new RegisterClimateStateRequest(this);
        }
    }
}
