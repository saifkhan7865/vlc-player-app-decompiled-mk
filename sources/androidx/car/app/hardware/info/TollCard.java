package androidx.car.app.hardware.info;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;
import j$.util.Objects;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RequiresCarApi(3)
public final class TollCard {
    public static final int TOLLCARD_STATE_INVALID = 2;
    public static final int TOLLCARD_STATE_NOT_INSERTED = 3;
    public static final int TOLLCARD_STATE_UNKNOWN = 0;
    public static final int TOLLCARD_STATE_VALID = 1;
    private final CarValue<Integer> mCardState;

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TollCardState {
    }

    public CarValue<Integer> getCardState() {
        return (CarValue) Objects.requireNonNull(this.mCardState);
    }

    public String toString() {
        return "[ tollcard state: " + this.mCardState + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mCardState);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TollCard)) {
            return false;
        }
        return Objects.equals(this.mCardState, ((TollCard) obj).mCardState);
    }

    TollCard(Builder builder) {
        this.mCardState = (CarValue) Objects.requireNonNull(builder.mCardState);
    }

    private TollCard() {
        this.mCardState = CarValue.UNKNOWN_INTEGER;
    }

    public static final class Builder {
        CarValue<Integer> mCardState = CarValue.UNKNOWN_INTEGER;

        /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.car.app.hardware.common.CarValue<java.lang.Integer>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.car.app.hardware.info.TollCard.Builder setCardState(androidx.car.app.hardware.common.CarValue<java.lang.Integer> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = j$.util.Objects.requireNonNull(r1)
                androidx.car.app.hardware.common.CarValue r1 = (androidx.car.app.hardware.common.CarValue) r1
                r0.mCardState = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.hardware.info.TollCard.Builder.setCardState(androidx.car.app.hardware.common.CarValue):androidx.car.app.hardware.info.TollCard$Builder");
        }

        public TollCard build() {
            return new TollCard(this);
        }
    }
}
