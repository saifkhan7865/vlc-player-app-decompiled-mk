package androidx.car.app.model;

import androidx.car.app.model.constraints.CarColorConstraints;
import androidx.car.app.model.constraints.CarIconConstraints;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PlaceMarker {
    private static final int MAX_LABEL_LENGTH = 3;
    public static final int TYPE_ICON = 0;
    public static final int TYPE_IMAGE = 1;
    private final CarColor mColor;
    private final CarIcon mIcon;
    private final int mIconType;
    private final CarText mLabel;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MarkerIconType {
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public int getIconType() {
        return this.mIconType;
    }

    public CarText getLabel() {
        return this.mLabel;
    }

    public CarColor getColor() {
        return this.mColor;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("[");
        CarIcon carIcon = this.mIcon;
        if (carIcon != null) {
            str = carIcon.toString();
        } else {
            CarText carText = this.mLabel;
            str = carText != null ? CarText.toShortString(carText) : super.toString();
        }
        sb.append(str);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hash(this.mIcon, this.mLabel, this.mColor, Integer.valueOf(this.mIconType));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceMarker)) {
            return false;
        }
        PlaceMarker placeMarker = (PlaceMarker) obj;
        if (!Objects.equals(this.mIcon, placeMarker.mIcon) || !Objects.equals(this.mLabel, placeMarker.mLabel) || !Objects.equals(this.mColor, placeMarker.mColor) || this.mIconType != placeMarker.mIconType) {
            return false;
        }
        return true;
    }

    PlaceMarker(Builder builder) {
        this.mIcon = builder.mIcon;
        this.mIconType = builder.mIconType;
        this.mLabel = builder.mLabel;
        this.mColor = builder.mColor;
    }

    private PlaceMarker() {
        this.mIcon = null;
        this.mIconType = 0;
        this.mLabel = null;
        this.mColor = null;
    }

    public static final class Builder {
        CarColor mColor;
        CarIcon mIcon;
        int mIconType = 0;
        CarText mLabel;

        public Builder setIcon(CarIcon carIcon, int i) {
            CarIconConstraints.DEFAULT.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mIcon = carIcon;
            this.mIconType = i;
            return this;
        }

        public Builder setLabel(CharSequence charSequence) {
            if (((CharSequence) Objects.requireNonNull(charSequence)).length() <= 3) {
                this.mLabel = CarText.create(charSequence);
                return this;
            }
            throw new IllegalArgumentException("Marker label cannot contain more than 3 characters");
        }

        public Builder setColor(CarColor carColor) {
            CarColorConstraints.UNCONSTRAINED.validateOrThrow((CarColor) Objects.requireNonNull(carColor));
            this.mColor = carColor;
            return this;
        }

        public PlaceMarker build() {
            if (this.mColor == null || this.mIcon == null || this.mIconType != 1) {
                return new PlaceMarker(this);
            }
            throw new IllegalStateException("Color cannot be set for icon set with TYPE_IMAGE");
        }
    }
}
