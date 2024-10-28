package androidx.car.app.navigation.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.Template;
import androidx.car.app.model.Toggle;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarColorConstraints;
import j$.util.Objects;

public final class NavigationTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final CarColor mBackgroundColor;
    private final TravelEstimate mDestinationTravelEstimate;
    private final ActionStrip mMapActionStrip;
    private final NavigationInfo mNavigationInfo;
    private final PanModeDelegate mPanModeDelegate;
    private final Toggle mPanModeToggle;

    public interface NavigationInfo {
    }

    public ActionStrip getActionStrip() {
        return (ActionStrip) Objects.requireNonNull(this.mActionStrip);
    }

    @RequiresCarApi(2)
    public ActionStrip getMapActionStrip() {
        return this.mMapActionStrip;
    }

    @Deprecated
    @RequiresCarApi(2)
    public Toggle getPanModeToggle() {
        return this.mPanModeToggle;
    }

    @RequiresCarApi(2)
    public PanModeDelegate getPanModeDelegate() {
        return this.mPanModeDelegate;
    }

    public NavigationInfo getNavigationInfo() {
        return this.mNavigationInfo;
    }

    public CarColor getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public TravelEstimate getDestinationTravelEstimate() {
        return this.mDestinationTravelEstimate;
    }

    public String toString() {
        return "NavigationTemplate";
    }

    public int hashCode() {
        return Objects.hash(this.mNavigationInfo, this.mBackgroundColor, this.mDestinationTravelEstimate, this.mActionStrip, this.mMapActionStrip, this.mPanModeToggle, Boolean.valueOf(this.mPanModeDelegate == null));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NavigationTemplate)) {
            return false;
        }
        NavigationTemplate navigationTemplate = (NavigationTemplate) obj;
        if (Objects.equals(this.mNavigationInfo, navigationTemplate.mNavigationInfo) && Objects.equals(this.mBackgroundColor, navigationTemplate.mBackgroundColor) && Objects.equals(this.mDestinationTravelEstimate, navigationTemplate.mDestinationTravelEstimate) && Objects.equals(this.mActionStrip, navigationTemplate.mActionStrip) && Objects.equals(this.mMapActionStrip, navigationTemplate.mMapActionStrip) && Objects.equals(this.mPanModeToggle, navigationTemplate.mPanModeToggle)) {
            if (Objects.equals(Boolean.valueOf(this.mPanModeDelegate == null), Boolean.valueOf(navigationTemplate.mPanModeDelegate == null))) {
                return true;
            }
        }
        return false;
    }

    NavigationTemplate(Builder builder) {
        this.mNavigationInfo = builder.mNavigationInfo;
        this.mBackgroundColor = builder.mBackgroundColor;
        this.mDestinationTravelEstimate = builder.mDestinationTravelEstimate;
        this.mActionStrip = builder.mActionStrip;
        this.mMapActionStrip = builder.mMapActionStrip;
        this.mPanModeToggle = builder.mPanModeToggle;
        this.mPanModeDelegate = builder.mPanModeDelegate;
    }

    private NavigationTemplate() {
        this.mNavigationInfo = null;
        this.mBackgroundColor = null;
        this.mDestinationTravelEstimate = null;
        this.mActionStrip = null;
        this.mMapActionStrip = null;
        this.mPanModeToggle = null;
        this.mPanModeDelegate = null;
    }

    public static final class Builder {
        ActionStrip mActionStrip;
        CarColor mBackgroundColor;
        TravelEstimate mDestinationTravelEstimate;
        ActionStrip mMapActionStrip;
        NavigationInfo mNavigationInfo;
        PanModeDelegate mPanModeDelegate;
        Toggle mPanModeToggle;

        public Builder setNavigationInfo(NavigationInfo navigationInfo) {
            this.mNavigationInfo = (NavigationInfo) Objects.requireNonNull(navigationInfo);
            return this;
        }

        public Builder setBackgroundColor(CarColor carColor) {
            CarColorConstraints.UNCONSTRAINED.validateOrThrow((CarColor) Objects.requireNonNull(carColor));
            this.mBackgroundColor = carColor;
            return this;
        }

        public Builder setDestinationTravelEstimate(TravelEstimate travelEstimate) {
            if (((TravelEstimate) Objects.requireNonNull(travelEstimate)).getRemainingTimeSeconds() >= 0) {
                this.mDestinationTravelEstimate = travelEstimate;
                return this;
            }
            throw new IllegalArgumentException("The destination travel estimate's remaining time must be greater or equal to zero");
        }

        public Builder setActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_NAVIGATION.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mActionStrip = actionStrip;
            return this;
        }

        @RequiresCarApi(2)
        public Builder setMapActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_MAP.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mMapActionStrip = actionStrip;
            return this;
        }

        @RequiresCarApi(2)
        public Builder setPanModeListener(PanModeListener panModeListener) {
            Objects.requireNonNull(panModeListener);
            this.mPanModeToggle = new Toggle.Builder(new NavigationTemplate$Builder$$ExternalSyntheticLambda0(panModeListener)).build();
            this.mPanModeDelegate = PanModeDelegateImpl.create(panModeListener);
            return this;
        }

        public NavigationTemplate build() {
            if (this.mActionStrip != null) {
                return new NavigationTemplate(this);
            }
            throw new IllegalStateException("Action strip for this template must be set");
        }
    }
}
