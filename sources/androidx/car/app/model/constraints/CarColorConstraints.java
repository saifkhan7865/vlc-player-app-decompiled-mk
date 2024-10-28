package androidx.car.app.model.constraints;

import androidx.car.app.model.CarColor;
import java.util.HashSet;

public final class CarColorConstraints {
    public static final CarColorConstraints STANDARD_ONLY = create(new int[]{1, 2, 3, 4, 5, 6, 7});
    public static final CarColorConstraints UNCONSTRAINED = create(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
    private final HashSet<Integer> mAllowedTypes = new HashSet<>();

    private static CarColorConstraints create(int[] iArr) {
        return new CarColorConstraints(iArr);
    }

    public void validateOrThrow(CarColor carColor) {
        if (!this.mAllowedTypes.contains(Integer.valueOf(carColor.getType()))) {
            throw new IllegalArgumentException("Car color type is not allowed: " + carColor);
        }
    }

    private CarColorConstraints(int[] iArr) {
        for (int valueOf : iArr) {
            this.mAllowedTypes.add(Integer.valueOf(valueOf));
        }
    }
}
