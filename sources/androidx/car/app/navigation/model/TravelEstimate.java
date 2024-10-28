package androidx.car.app.navigation.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.DateTimeWithZone;
import androidx.car.app.model.Distance;
import androidx.car.app.model.constraints.CarColorConstraints;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import j$.time.Duration;
import j$.time.ZonedDateTime;
import j$.util.Objects;

public final class TravelEstimate {
    public static final long REMAINING_TIME_UNKNOWN = -1;
    private final DateTimeWithZone mArrivalTimeAtDestination;
    private final Distance mRemainingDistance;
    private final CarColor mRemainingDistanceColor;
    private final CarColor mRemainingTimeColor;
    private final long mRemainingTimeSeconds;
    private final CarIcon mTripIcon;
    private final CarText mTripText;

    public Distance getRemainingDistance() {
        return this.mRemainingDistance;
    }

    public long getRemainingTimeSeconds() {
        long j = this.mRemainingTimeSeconds;
        if (j >= 0) {
            return j;
        }
        return -1;
    }

    public DateTimeWithZone getArrivalTimeAtDestination() {
        return this.mArrivalTimeAtDestination;
    }

    public CarColor getRemainingTimeColor() {
        return this.mRemainingTimeColor;
    }

    public CarColor getRemainingDistanceColor() {
        return this.mRemainingDistanceColor;
    }

    @RequiresCarApi(5)
    public CarText getTripText() {
        return this.mTripText;
    }

    @RequiresCarApi(5)
    public CarIcon getTripIcon() {
        return this.mTripIcon;
    }

    public String toString() {
        return "[ remaining distance: " + this.mRemainingDistance + ", time (s): " + this.mRemainingTimeSeconds + ", ETA: " + this.mArrivalTimeAtDestination + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mRemainingDistance, Long.valueOf(this.mRemainingTimeSeconds), this.mArrivalTimeAtDestination, this.mRemainingTimeColor, this.mRemainingDistanceColor, this.mTripText, this.mTripIcon);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TravelEstimate)) {
            return false;
        }
        TravelEstimate travelEstimate = (TravelEstimate) obj;
        if (!Objects.equals(this.mRemainingDistance, travelEstimate.mRemainingDistance) || this.mRemainingTimeSeconds != travelEstimate.mRemainingTimeSeconds || !Objects.equals(this.mArrivalTimeAtDestination, travelEstimate.mArrivalTimeAtDestination) || !Objects.equals(this.mRemainingTimeColor, travelEstimate.mRemainingTimeColor) || !Objects.equals(this.mRemainingDistanceColor, travelEstimate.mRemainingDistanceColor) || !Objects.equals(this.mTripText, travelEstimate.mTripText) || !Objects.equals(this.mTripIcon, travelEstimate.mTripIcon)) {
            return false;
        }
        return true;
    }

    private TravelEstimate() {
        this.mRemainingDistance = null;
        this.mRemainingTimeSeconds = 0;
        this.mArrivalTimeAtDestination = null;
        this.mRemainingTimeColor = CarColor.DEFAULT;
        this.mRemainingDistanceColor = CarColor.DEFAULT;
        this.mTripText = null;
        this.mTripIcon = null;
    }

    TravelEstimate(Builder builder) {
        this.mRemainingDistance = builder.mRemainingDistance;
        this.mRemainingTimeSeconds = builder.mRemainingTimeSeconds;
        this.mArrivalTimeAtDestination = builder.mArrivalTimeAtDestination;
        this.mRemainingTimeColor = builder.mRemainingTimeColor;
        this.mRemainingDistanceColor = builder.mRemainingDistanceColor;
        this.mTripText = builder.mTripText;
        this.mTripIcon = builder.mTripIcon;
    }

    public static final class Builder {
        final DateTimeWithZone mArrivalTimeAtDestination;
        final Distance mRemainingDistance;
        CarColor mRemainingDistanceColor = CarColor.DEFAULT;
        CarColor mRemainingTimeColor = CarColor.DEFAULT;
        long mRemainingTimeSeconds = -1;
        CarIcon mTripIcon;
        CarText mTripText;

        public Builder(Distance distance, DateTimeWithZone dateTimeWithZone) {
            this.mRemainingDistance = (Distance) Objects.requireNonNull(distance);
            this.mArrivalTimeAtDestination = (DateTimeWithZone) Objects.requireNonNull(dateTimeWithZone);
        }

        public Builder(Distance distance, ZonedDateTime zonedDateTime) {
            this.mRemainingDistance = (Distance) Objects.requireNonNull(distance);
            this.mArrivalTimeAtDestination = DateTimeWithZone.create((ZonedDateTime) Objects.requireNonNull(zonedDateTime));
        }

        public Builder setRemainingTimeSeconds(long j) {
            this.mRemainingTimeSeconds = validateRemainingTime(j);
            return this;
        }

        public Builder setRemainingTime(Duration duration) {
            return Api26Impl.setRemainingTime(this, duration);
        }

        public Builder setRemainingTimeColor(CarColor carColor) {
            CarColorConstraints.STANDARD_ONLY.validateOrThrow((CarColor) Objects.requireNonNull(carColor));
            this.mRemainingTimeColor = carColor;
            return this;
        }

        public Builder setRemainingDistanceColor(CarColor carColor) {
            CarColorConstraints.STANDARD_ONLY.validateOrThrow((CarColor) Objects.requireNonNull(carColor));
            this.mRemainingDistanceColor = carColor;
            return this;
        }

        @RequiresCarApi(5)
        public Builder setTripText(CarText carText) {
            this.mTripText = (CarText) Objects.requireNonNull(carText);
            CarTextConstraints.TEXT_WITH_COLORS.validateOrThrow(this.mTripText);
            return this;
        }

        @RequiresCarApi(5)
        public Builder setTripIcon(CarIcon carIcon) {
            CarIconConstraints.DEFAULT.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mTripIcon = carIcon;
            return this;
        }

        public TravelEstimate build() {
            return new TravelEstimate(this);
        }

        static long validateRemainingTime(long j) {
            if (j >= 0 || j == -1) {
                return j;
            }
            throw new IllegalArgumentException("Remaining time must be a larger than or equal to zero, or set to REMAINING_TIME_UNKNOWN");
        }

        private static final class Api26Impl {
            private Api26Impl() {
            }

            public static Builder setRemainingTime(Builder builder, Duration duration) {
                Objects.requireNonNull(duration);
                builder.mRemainingTimeSeconds = Builder.validateRemainingTime(duration.getSeconds());
                return builder;
            }
        }
    }
}
