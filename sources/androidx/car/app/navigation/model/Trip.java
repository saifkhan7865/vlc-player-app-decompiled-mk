package androidx.car.app.navigation.model;

import androidx.car.app.model.CarText;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Trip {
    private final CarText mCurrentRoad;
    private final List<TravelEstimate> mDestinationTravelEstimates;
    private final List<Destination> mDestinations;
    private final boolean mIsLoading;
    private final List<TravelEstimate> mStepTravelEstimates;
    private final List<Step> mSteps;

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public List<Destination> getDestinations() {
        return CollectionUtils.emptyIfNull(this.mDestinations);
    }

    public List<Step> getSteps() {
        return CollectionUtils.emptyIfNull(this.mSteps);
    }

    public List<TravelEstimate> getDestinationTravelEstimates() {
        return CollectionUtils.emptyIfNull(this.mDestinationTravelEstimates);
    }

    public List<TravelEstimate> getStepTravelEstimates() {
        return CollectionUtils.emptyIfNull(this.mStepTravelEstimates);
    }

    public CarText getCurrentRoad() {
        return this.mCurrentRoad;
    }

    public String toString() {
        return "[ destinations : " + this.mDestinations.toString() + ", steps: " + this.mSteps.toString() + ", dest estimates: " + this.mDestinationTravelEstimates.toString() + ", step estimates: " + this.mStepTravelEstimates.toString() + ", road: " + CarText.toShortString(this.mCurrentRoad) + ", isLoading: " + this.mIsLoading + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mDestinations, this.mSteps, this.mDestinationTravelEstimates, this.mStepTravelEstimates, this.mCurrentRoad);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Trip)) {
            return false;
        }
        Trip trip = (Trip) obj;
        if (!Objects.equals(this.mDestinations, trip.mDestinations) || !Objects.equals(this.mSteps, trip.mSteps) || !Objects.equals(this.mDestinationTravelEstimates, trip.mDestinationTravelEstimates) || !Objects.equals(this.mStepTravelEstimates, trip.mStepTravelEstimates) || !Objects.equals(this.mCurrentRoad, trip.mCurrentRoad) || !Objects.equals(Boolean.valueOf(this.mIsLoading), Boolean.valueOf(trip.mIsLoading))) {
            return false;
        }
        return true;
    }

    Trip(Builder builder) {
        this.mDestinations = CollectionUtils.unmodifiableCopy(builder.mDestinations);
        this.mSteps = CollectionUtils.unmodifiableCopy(builder.mSteps);
        this.mDestinationTravelEstimates = CollectionUtils.unmodifiableCopy(builder.mDestinationTravelEstimates);
        this.mStepTravelEstimates = CollectionUtils.unmodifiableCopy(builder.mStepTravelEstimates);
        this.mCurrentRoad = builder.mCurrentRoad;
        this.mIsLoading = builder.mIsLoading;
    }

    private Trip() {
        this.mDestinations = Collections.emptyList();
        this.mSteps = Collections.emptyList();
        this.mDestinationTravelEstimates = Collections.emptyList();
        this.mStepTravelEstimates = Collections.emptyList();
        this.mCurrentRoad = null;
        this.mIsLoading = false;
    }

    public static final class Builder {
        CarText mCurrentRoad;
        final List<TravelEstimate> mDestinationTravelEstimates = new ArrayList();
        final List<Destination> mDestinations = new ArrayList();
        boolean mIsLoading;
        final List<TravelEstimate> mStepTravelEstimates = new ArrayList();
        final List<Step> mSteps = new ArrayList();

        public Builder addDestination(Destination destination, TravelEstimate travelEstimate) {
            this.mDestinations.add((Destination) Objects.requireNonNull(destination));
            this.mDestinationTravelEstimates.add((TravelEstimate) Objects.requireNonNull(travelEstimate));
            return this;
        }

        public Builder addStep(Step step, TravelEstimate travelEstimate) {
            this.mSteps.add((Step) Objects.requireNonNull(step));
            this.mStepTravelEstimates.add((TravelEstimate) Objects.requireNonNull(travelEstimate));
            return this;
        }

        public Builder setCurrentRoad(CharSequence charSequence) {
            this.mCurrentRoad = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            return this;
        }

        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        public Trip build() {
            if (this.mDestinations.size() != this.mDestinationTravelEstimates.size()) {
                throw new IllegalArgumentException("Destinations and destination travel estimates sizes must match");
            } else if (this.mSteps.size() != this.mStepTravelEstimates.size()) {
                throw new IllegalArgumentException("Steps and step travel estimates sizes must match");
            } else if (!this.mIsLoading || this.mSteps.isEmpty()) {
                return new Trip(this);
            } else {
                throw new IllegalArgumentException("Step information may not be set while loading");
            }
        }
    }
}
