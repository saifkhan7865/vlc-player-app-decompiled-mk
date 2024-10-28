package androidx.car.app.navigation.model;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.Distance;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.navigation.model.NavigationTemplate;
import j$.util.Objects;

public final class RoutingInfo implements NavigationTemplate.NavigationInfo {
    private final Distance mCurrentDistance;
    private final Step mCurrentStep;
    private final boolean mIsLoading;
    private final CarIcon mJunctionImage;
    private final Step mNextStep;

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public Step getCurrentStep() {
        return this.mCurrentStep;
    }

    public Distance getCurrentDistance() {
        return this.mCurrentDistance;
    }

    public Step getNextStep() {
        return this.mNextStep;
    }

    public CarIcon getJunctionImage() {
        return this.mJunctionImage;
    }

    public String toString() {
        return "RoutingInfo";
    }

    public int hashCode() {
        return Objects.hash(this.mCurrentStep, this.mCurrentDistance, this.mNextStep, this.mJunctionImage, Boolean.valueOf(this.mIsLoading));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoutingInfo)) {
            return false;
        }
        RoutingInfo routingInfo = (RoutingInfo) obj;
        if (this.mIsLoading != routingInfo.mIsLoading || !Objects.equals(this.mCurrentStep, routingInfo.mCurrentStep) || !Objects.equals(this.mCurrentDistance, routingInfo.mCurrentDistance) || !Objects.equals(this.mNextStep, routingInfo.mNextStep) || !Objects.equals(this.mJunctionImage, routingInfo.mJunctionImage)) {
            return false;
        }
        return true;
    }

    RoutingInfo(Builder builder) {
        this.mCurrentStep = builder.mCurrentStep;
        this.mCurrentDistance = builder.mCurrentDistance;
        this.mNextStep = builder.mNextStep;
        this.mJunctionImage = builder.mJunctionImage;
        this.mIsLoading = builder.mIsLoading;
    }

    private RoutingInfo() {
        this.mCurrentStep = null;
        this.mCurrentDistance = null;
        this.mNextStep = null;
        this.mJunctionImage = null;
        this.mIsLoading = false;
    }

    public static final class Builder {
        Distance mCurrentDistance;
        Step mCurrentStep;
        boolean mIsLoading;
        CarIcon mJunctionImage;
        Step mNextStep;

        public Builder setCurrentStep(Step step, Distance distance) {
            this.mCurrentStep = (Step) Objects.requireNonNull(step);
            this.mCurrentDistance = (Distance) Objects.requireNonNull(distance);
            return this;
        }

        public Builder setNextStep(Step step) {
            this.mNextStep = (Step) Objects.requireNonNull(step);
            return this;
        }

        public Builder setJunctionImage(CarIcon carIcon) {
            CarIconConstraints.DEFAULT.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mJunctionImage = carIcon;
            return this;
        }

        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        public RoutingInfo build() {
            Step step = this.mCurrentStep;
            Distance distance = this.mCurrentDistance;
            if (this.mIsLoading) {
                if (!(step == null && distance == null && this.mNextStep == null && this.mJunctionImage == null)) {
                    throw new IllegalStateException("The routing info is set to loading but is not empty");
                }
            } else if (step == null || distance == null) {
                throw new IllegalStateException("Current step and distance must be set during the navigating state");
            } else if (!step.getLanes().isEmpty() && step.getLanesImage() == null) {
                throw new IllegalStateException("Current step must have a lanes image if the lane information is set");
            }
            return new RoutingInfo(this);
        }
    }
}
