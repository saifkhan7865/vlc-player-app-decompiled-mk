package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.CarColorConstraints;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.core.graphics.drawable.IconCompat;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CarIcon {
    public static final CarIcon ALERT = forStandardType(4);
    public static final CarIcon APP_ICON = forStandardType(5);
    public static final CarIcon BACK = forStandardType(3);
    public static final CarIcon ERROR = forStandardType(6);
    @RequiresCarApi(2)
    public static final CarIcon PAN = forStandardType(7);
    public static final int TYPE_ALERT = 4;
    public static final int TYPE_APP_ICON = 5;
    public static final int TYPE_BACK = 3;
    public static final int TYPE_CUSTOM = 1;
    public static final int TYPE_ERROR = 6;
    public static final int TYPE_PAN = 7;
    private static final int TYPE_RESOURCE = 2;
    private static final int TYPE_URI = 4;
    private final IconCompat mIcon;
    private final CarColor mTint;
    private final int mType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CarIconType {
    }

    public IconCompat getIcon() {
        return this.mIcon;
    }

    public CarColor getTint() {
        return this.mTint;
    }

    public int getType() {
        return this.mType;
    }

    public String toString() {
        return "[type: " + typeToString(this.mType) + ", tint: " + this.mTint + "]";
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mType), this.mTint, iconCompatHash());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CarIcon)) {
            return false;
        }
        CarIcon carIcon = (CarIcon) obj;
        if (this.mType != carIcon.mType || !Objects.equals(this.mTint, carIcon.mTint) || !iconCompatEquals(carIcon.mIcon)) {
            return false;
        }
        return true;
    }

    private Object iconCompatHash() {
        IconCompat iconCompat = this.mIcon;
        if (iconCompat == null) {
            return null;
        }
        int type = iconCompat.getType();
        if (type == 2) {
            return this.mIcon.getResPackage() + this.mIcon.getResId();
        } else if (type == 4) {
            return this.mIcon.getUri();
        } else {
            return Integer.valueOf(type);
        }
    }

    private boolean iconCompatEquals(IconCompat iconCompat) {
        int type;
        IconCompat iconCompat2 = this.mIcon;
        if (iconCompat2 == null) {
            return iconCompat == null;
        }
        if (iconCompat == null || (type = iconCompat2.getType()) != iconCompat.getType()) {
            return false;
        }
        if (type == 2) {
            if (!Objects.equals(this.mIcon.getResPackage(), iconCompat.getResPackage()) || this.mIcon.getResId() != iconCompat.getResId()) {
                return false;
            }
            return true;
        } else if (type == 4) {
            return Objects.equals(this.mIcon.getUri(), iconCompat.getUri());
        } else {
            return true;
        }
    }

    private static CarIcon forStandardType(int i) {
        return forStandardType(i, CarColor.DEFAULT);
    }

    private static CarIcon forStandardType(int i, CarColor carColor) {
        return new CarIcon((IconCompat) null, carColor, i);
    }

    private static String typeToString(int i) {
        if (i == 1) {
            return "CUSTOM";
        }
        if (i == 3) {
            return "BACK";
        }
        if (i == 4) {
            return "ALERT";
        }
        if (i == 5) {
            return "APP";
        }
        if (i == 6) {
            return "ERROR";
        }
        if (i != 7) {
            return "<unknown>";
        }
        return "PAN";
    }

    CarIcon(IconCompat iconCompat, CarColor carColor, int i) {
        this.mType = i;
        this.mIcon = iconCompat;
        this.mTint = carColor;
    }

    private CarIcon() {
        this.mType = 1;
        this.mIcon = null;
        this.mTint = null;
    }

    public static final class Builder {
        private IconCompat mIcon;
        private CarColor mTint;
        private int mType;

        public Builder setTint(CarColor carColor) {
            CarColorConstraints.UNCONSTRAINED.validateOrThrow((CarColor) Objects.requireNonNull(carColor));
            this.mTint = carColor;
            return this;
        }

        public CarIcon build() {
            return new CarIcon(this.mIcon, this.mTint, this.mType);
        }

        public Builder(IconCompat iconCompat) {
            CarIconConstraints.UNCONSTRAINED.checkSupportedIcon((IconCompat) Objects.requireNonNull(iconCompat));
            this.mType = 1;
            this.mIcon = iconCompat;
            this.mTint = null;
        }

        public Builder(CarIcon carIcon) {
            Objects.requireNonNull(carIcon);
            this.mType = carIcon.getType();
            this.mIcon = carIcon.getIcon();
            this.mTint = carIcon.getTint();
        }
    }
}
