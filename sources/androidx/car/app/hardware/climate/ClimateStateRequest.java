package androidx.car.app.hardware.climate;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarZone;
import j$.util.Objects;
import java.util.Collections;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@RequiresCarApi(5)
public final class ClimateStateRequest<T> {
    private final List<CarZone> mCarZones;
    private final int mFeature;
    private final T mRequestedValue;

    public int getRequestedFeature() {
        return this.mFeature;
    }

    public List<CarZone> getCarZones() {
        return this.mCarZones;
    }

    public T getRequestedValue() {
        return this.mRequestedValue;
    }

    ClimateStateRequest(Builder<T> builder) {
        this.mFeature = builder.mRequestedFeature;
        this.mRequestedValue = builder.mRequestedValue;
        if (builder.mCarZones.isEmpty()) {
            this.mCarZones = Collections.singletonList(CarZone.CAR_ZONE_GLOBAL);
        } else {
            this.mCarZones = builder.mCarZones;
        }
    }

    public String toString() {
        return "ClimateStateRequest{mFeature='" + this.mFeature + "', mCarZones=" + this.mCarZones + ", mRequestedValue=" + this.mRequestedValue + AbstractJsonLexerKt.END_OBJ;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClimateStateRequest climateStateRequest = (ClimateStateRequest) obj;
        if (!Objects.equals(Integer.valueOf(this.mFeature), Integer.valueOf(climateStateRequest.mFeature)) || !Objects.equals(this.mCarZones, climateStateRequest.mCarZones) || !Objects.equals(this.mRequestedValue, climateStateRequest.mRequestedValue)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mFeature), this.mCarZones, this.mRequestedValue);
    }

    public static final class Builder<T> {
        List<CarZone> mCarZones = Collections.emptyList();
        final int mRequestedFeature;
        final T mRequestedValue;

        public Builder(int i, T t) {
            this.mRequestedValue = t;
            this.mRequestedFeature = i;
        }

        public Builder<T> addCarZones(CarZone carZone) {
            this.mCarZones.add(carZone);
            return this;
        }

        public ClimateStateRequest<T> build() {
            return new ClimateStateRequest<>(this);
        }
    }
}
