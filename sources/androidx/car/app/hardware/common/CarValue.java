package androidx.car.app.hardware.common;

import androidx.car.app.annotations.RequiresCarApi;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;

@RequiresCarApi(3)
public final class CarValue<T> {
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_UNAVAILABLE = 3;
    public static final int STATUS_UNIMPLEMENTED = 2;
    public static final int STATUS_UNKNOWN = 0;
    public static final CarValue<List<Float>> UNIMPLEMENTED_FLOAT_LIST = unimplemented();
    public static final CarValue<Integer> UNIMPLEMENTED_INTEGER = unimplemented();
    public static final CarValue<Boolean> UNKNOWN_BOOLEAN = unknown();
    public static final CarValue<Float> UNKNOWN_FLOAT = unknown();
    public static final CarValue<List<Float>> UNKNOWN_FLOAT_LIST = unknown();
    public static final CarValue<Integer> UNKNOWN_INTEGER = unknown();
    public static final CarValue<List<Integer>> UNKNOWN_INTEGER_LIST = unknown();
    public static final CarValue<String> UNKNOWN_STRING = unknown();
    private final List<CarZone> mCarZones;
    private final int mStatus;
    private final long mTimestampMillis;
    private final T mValue;

    @Retention(RetentionPolicy.SOURCE)
    public @interface StatusCode {
    }

    private static <T> CarValue<T> unimplemented() {
        return new CarValue<>((Object) null, 0, 2);
    }

    private static <T> CarValue<T> unknown() {
        return new CarValue<>((Object) null, 0, 0);
    }

    public T getValue() {
        return this.mValue;
    }

    public long getTimestampMillis() {
        return this.mTimestampMillis;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public List<CarZone> getCarZones() {
        if (this.mStatus == 2) {
            return Collections.emptyList();
        }
        return this.mCarZones;
    }

    public String toString() {
        return "[value: " + this.mValue + ", timestamp: " + this.mTimestampMillis + ", Status: " + this.mStatus + ", CarZones: " + this.mCarZones + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mValue, Long.valueOf(this.mTimestampMillis), Integer.valueOf(this.mStatus), this.mCarZones);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CarValue)) {
            return false;
        }
        CarValue carValue = (CarValue) obj;
        if (!Objects.equals(this.mValue, carValue.mValue) || this.mTimestampMillis != carValue.mTimestampMillis || this.mStatus != carValue.mStatus || !Objects.equals(this.mCarZones, carValue.mCarZones)) {
            return false;
        }
        return true;
    }

    public CarValue(T t, long j, int i) {
        this.mValue = t;
        this.mTimestampMillis = j;
        this.mStatus = i;
        this.mCarZones = Collections.singletonList(CarZone.CAR_ZONE_GLOBAL);
    }

    public CarValue(T t, long j, int i, List<CarZone> list) {
        this.mValue = t;
        this.mTimestampMillis = j;
        this.mStatus = i;
        this.mCarZones = list;
    }

    private CarValue() {
        this.mValue = null;
        this.mTimestampMillis = 0;
        this.mStatus = 0;
        this.mCarZones = Collections.emptyList();
    }
}
