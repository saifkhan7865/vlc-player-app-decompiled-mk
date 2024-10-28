package androidx.car.app.model.constraints;

import androidx.car.app.model.CarIcon;
import androidx.core.graphics.drawable.IconCompat;

public final class CarIconConstraints {
    public static final CarIconConstraints DEFAULT = create(new int[]{1, 2});
    public static final CarIconConstraints UNCONSTRAINED = create(new int[]{1, 2, 4});
    private final int[] mAllowedTypes;

    private static CarIconConstraints create(int[] iArr) {
        return new CarIconConstraints(iArr);
    }

    public void validateOrThrow(CarIcon carIcon) {
        if (carIcon != null && carIcon.getType() == 1) {
            IconCompat icon = carIcon.getIcon();
            if (icon != null) {
                checkSupportedIcon(icon);
                return;
            }
            throw new IllegalStateException("Custom icon does not have a backing IconCompat");
        }
    }

    public IconCompat checkSupportedIcon(IconCompat iconCompat) {
        int type = iconCompat.getType();
        int[] iArr = this.mAllowedTypes;
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            if (type != iArr[i]) {
                i++;
            } else if (type != 4 || "content".equalsIgnoreCase(iconCompat.getUri().getScheme())) {
                return iconCompat;
            } else {
                throw new IllegalArgumentException("Unsupported URI scheme for: " + iconCompat);
            }
        }
        throw new IllegalArgumentException("Custom icon type is not allowed: " + type);
    }

    private CarIconConstraints(int[] iArr) {
        this.mAllowedTypes = iArr;
    }
}
