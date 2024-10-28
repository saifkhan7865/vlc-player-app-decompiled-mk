package androidx.car.app.navigation.model;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Step {
    private final CarText mCue;
    private final List<Lane> mLanes;
    private final CarIcon mLanesImage;
    private final Maneuver mManeuver;
    private final CarText mRoad;

    public Maneuver getManeuver() {
        return this.mManeuver;
    }

    public List<Lane> getLanes() {
        return CollectionUtils.emptyIfNull(this.mLanes);
    }

    public CarIcon getLanesImage() {
        return this.mLanesImage;
    }

    public CarText getCue() {
        return this.mCue;
    }

    public CarText getRoad() {
        return this.mRoad;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[maneuver: ");
        sb.append(this.mManeuver);
        sb.append(", lane count: ");
        List<Lane> list = this.mLanes;
        sb.append(list != null ? list.size() : 0);
        sb.append(", lanes image: ");
        sb.append(this.mLanesImage);
        sb.append(", cue: ");
        sb.append(CarText.toShortString(this.mCue));
        sb.append(", road: ");
        sb.append(CarText.toShortString(this.mRoad));
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hash(this.mManeuver, this.mLanes, this.mLanesImage, this.mCue, this.mRoad);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Step)) {
            return false;
        }
        Step step = (Step) obj;
        if (!Objects.equals(this.mManeuver, step.mManeuver) || !Objects.equals(this.mLanes, step.mLanes) || !Objects.equals(this.mLanesImage, step.mLanesImage) || !Objects.equals(this.mCue, step.mCue) || !Objects.equals(this.mRoad, step.mRoad)) {
            return false;
        }
        return true;
    }

    Step(Maneuver maneuver, List<Lane> list, CarIcon carIcon, CarText carText, CarText carText2) {
        this.mManeuver = maneuver;
        this.mLanes = CollectionUtils.unmodifiableCopy(list);
        CarIconConstraints.DEFAULT.validateOrThrow(carIcon);
        this.mLanesImage = carIcon;
        this.mCue = carText;
        this.mRoad = carText2;
    }

    private Step() {
        this.mManeuver = null;
        this.mLanes = Collections.emptyList();
        this.mLanesImage = null;
        this.mCue = null;
        this.mRoad = null;
    }

    public static final class Builder {
        private CarText mCue;
        private final List<Lane> mLanes = new ArrayList();
        private CarIcon mLanesImage;
        private Maneuver mManeuver;
        private CarText mRoad;

        public Builder() {
        }

        public Builder(CharSequence charSequence) {
            this.mCue = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_AND_ICON.validateOrThrow(this.mCue);
        }

        public Builder(CarText carText) {
            this.mCue = (CarText) Objects.requireNonNull(carText);
            CarTextConstraints.TEXT_AND_ICON.validateOrThrow(this.mCue);
        }

        public Builder setManeuver(Maneuver maneuver) {
            this.mManeuver = (Maneuver) Objects.requireNonNull(maneuver);
            return this;
        }

        public Builder addLane(Lane lane) {
            this.mLanes.add((Lane) Objects.requireNonNull(lane));
            return this;
        }

        public Builder setLanesImage(CarIcon carIcon) {
            this.mLanesImage = (CarIcon) Objects.requireNonNull(carIcon);
            return this;
        }

        public Builder setCue(CharSequence charSequence) {
            this.mCue = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_AND_ICON.validateOrThrow(this.mCue);
            return this;
        }

        public Builder setRoad(CharSequence charSequence) {
            this.mRoad = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mRoad);
            return this;
        }

        public Step build() {
            if (this.mLanesImage == null || !this.mLanes.isEmpty()) {
                return new Step(this.mManeuver, this.mLanes, this.mLanesImage, this.mCue, this.mRoad);
            }
            throw new IllegalStateException("A step must have lane data when the lanes image is set");
        }
    }
}
