package androidx.car.app.model;

import j$.util.Objects;

public final class Place {
    private final CarLocation mLocation;
    private final PlaceMarker mMarker;

    public PlaceMarker getMarker() {
        return this.mMarker;
    }

    public CarLocation getLocation() {
        return (CarLocation) Objects.requireNonNull(this.mLocation);
    }

    public String toString() {
        return "[ location: " + this.mLocation + ", marker: " + this.mMarker + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mLocation, this.mMarker);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Place)) {
            return false;
        }
        Place place = (Place) obj;
        if (!Objects.equals(this.mLocation, place.mLocation) || !Objects.equals(this.mMarker, place.mMarker)) {
            return false;
        }
        return true;
    }

    Place(Builder builder) {
        this.mLocation = builder.mLocation;
        this.mMarker = builder.mMarker;
    }

    private Place() {
        this.mLocation = null;
        this.mMarker = null;
    }

    public static final class Builder {
        CarLocation mLocation;
        PlaceMarker mMarker;

        public Builder(CarLocation carLocation) {
            this.mLocation = (CarLocation) Objects.requireNonNull(carLocation);
        }

        public Builder setMarker(PlaceMarker placeMarker) {
            this.mMarker = (PlaceMarker) Objects.requireNonNull(placeMarker);
            return this;
        }

        public Builder(Place place) {
            Objects.requireNonNull(place);
            this.mLocation = place.getLocation();
            this.mMarker = place.getMarker();
        }

        public Place build() {
            return new Place(this);
        }
    }
}
