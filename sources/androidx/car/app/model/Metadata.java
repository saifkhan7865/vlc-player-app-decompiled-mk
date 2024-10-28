package androidx.car.app.model;

import j$.util.Objects;

public final class Metadata {
    public static final Metadata EMPTY_METADATA = new Builder().build();
    private final Place mPlace;

    public Place getPlace() {
        return this.mPlace;
    }

    public int hashCode() {
        return Objects.hashCode(this.mPlace);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Metadata)) {
            return false;
        }
        return Objects.equals(this.mPlace, ((Metadata) obj).mPlace);
    }

    Metadata(Builder builder) {
        this.mPlace = builder.mPlace;
    }

    private Metadata() {
        this.mPlace = null;
    }

    public static final class Builder {
        Place mPlace;

        public Builder setPlace(Place place) {
            this.mPlace = (Place) Objects.requireNonNull(place);
            return this;
        }

        public Metadata build() {
            return new Metadata(this);
        }

        public Builder() {
        }

        public Builder(Metadata metadata) {
            this.mPlace = ((Metadata) Objects.requireNonNull(metadata)).getPlace();
        }
    }
}
