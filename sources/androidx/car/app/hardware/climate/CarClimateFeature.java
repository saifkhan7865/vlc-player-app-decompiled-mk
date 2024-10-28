package androidx.car.app.hardware.climate;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarZone;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@RequiresCarApi(5)
public final class CarClimateFeature {
    private final List<CarZone> mCarZones;
    private final int mFeature;

    public List<CarZone> getCarZones() {
        return this.mCarZones;
    }

    public int getFeature() {
        return this.mFeature;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CarClimateFeature carClimateFeature = (CarClimateFeature) obj;
        if (!Objects.equals(Integer.valueOf(this.mFeature), Integer.valueOf(carClimateFeature.mFeature)) || !Objects.equals(this.mCarZones, carClimateFeature.mCarZones)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mFeature), this.mCarZones);
    }

    public String toString() {
        return "ClimateProfileFeature{mFeature='" + this.mFeature + "', mCarZones=" + this.mCarZones + AbstractJsonLexerKt.END_OBJ;
    }

    CarClimateFeature(Builder builder) {
        this.mCarZones = Collections.unmodifiableList(builder.mCarZones);
        this.mFeature = builder.mFeature;
    }

    public static final class Builder {
        List<CarZone> mCarZones = new ArrayList();
        final int mFeature;

        public Builder(int i) {
            this.mFeature = i;
        }

        public Builder addCarZones(CarZone... carZoneArr) {
            this.mCarZones.addAll(Arrays.asList(carZoneArr));
            return this;
        }

        public CarClimateFeature build() {
            return new CarClimateFeature(this);
        }
    }
}
