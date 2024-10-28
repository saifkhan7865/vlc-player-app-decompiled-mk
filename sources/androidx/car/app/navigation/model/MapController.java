package androidx.car.app.navigation.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.constraints.ActionsConstraints;
import j$.util.Objects;

@RequiresCarApi(5)
public final class MapController {
    private final ActionStrip mMapActionStrip;
    private final PanModeDelegate mPanModeDelegate;

    MapController(Builder builder) {
        this.mPanModeDelegate = builder.mPanModeDelegate;
        this.mMapActionStrip = builder.mMapActionStrip;
    }

    private MapController() {
        this.mPanModeDelegate = null;
        this.mMapActionStrip = null;
    }

    public ActionStrip getMapActionStrip() {
        return this.mMapActionStrip;
    }

    public PanModeDelegate getPanModeDelegate() {
        return this.mPanModeDelegate;
    }

    public int hashCode() {
        return Objects.hash(this.mPanModeDelegate, this.mMapActionStrip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MapController)) {
            return false;
        }
        MapController mapController = (MapController) obj;
        if (!Objects.equals(Boolean.valueOf(this.mPanModeDelegate == null), Boolean.valueOf(mapController.mPanModeDelegate == null)) || !Objects.equals(this.mMapActionStrip, mapController.mMapActionStrip)) {
            return false;
        }
        return true;
    }

    public static final class Builder {
        ActionStrip mMapActionStrip;
        PanModeDelegate mPanModeDelegate;

        public Builder setPanModeListener(PanModeListener panModeListener) {
            Objects.requireNonNull(panModeListener);
            this.mPanModeDelegate = PanModeDelegateImpl.create(panModeListener);
            return this;
        }

        public Builder setMapActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_MAP.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mMapActionStrip = actionStrip;
            return this;
        }

        public MapController build() {
            return new MapController(this);
        }
    }
}
