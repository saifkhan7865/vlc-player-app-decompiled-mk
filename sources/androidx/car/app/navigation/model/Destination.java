package androidx.car.app.navigation.model;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.constraints.CarIconConstraints;
import j$.util.Objects;

public final class Destination {
    private final CarText mAddress;
    private final CarIcon mImage;
    private final CarText mName;

    public CarText getName() {
        return this.mName;
    }

    public CarText getAddress() {
        return this.mAddress;
    }

    public CarIcon getImage() {
        return this.mImage;
    }

    public String toString() {
        return "[name: " + CarText.toShortString(this.mName) + ", address: " + CarText.toShortString(this.mAddress) + ", image: " + this.mImage + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Destination)) {
            return false;
        }
        Destination destination = (Destination) obj;
        if (!Objects.equals(this.mName, destination.mName) || !Objects.equals(this.mAddress, destination.mAddress) || !Objects.equals(this.mImage, destination.mImage)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mAddress, this.mImage);
    }

    Destination(Builder builder) {
        this.mName = builder.mName;
        this.mAddress = builder.mAddress;
        this.mImage = builder.mImage;
    }

    private Destination() {
        this.mName = null;
        this.mAddress = null;
        this.mImage = null;
    }

    public static final class Builder {
        CarText mAddress;
        CarIcon mImage;
        CarText mName;

        public Builder setName(CharSequence charSequence) {
            this.mName = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            return this;
        }

        public Builder setAddress(CharSequence charSequence) {
            this.mAddress = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            return this;
        }

        public Builder setImage(CarIcon carIcon) {
            CarIconConstraints.DEFAULT.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mImage = carIcon;
            return this;
        }

        public Destination build() {
            CarText carText;
            CarText carText2 = this.mName;
            if ((carText2 != null && !carText2.isEmpty()) || ((carText = this.mAddress) != null && !carText.isEmpty())) {
                return new Destination(this);
            }
            throw new IllegalStateException("Both name and address cannot be null or empty");
        }
    }
}
