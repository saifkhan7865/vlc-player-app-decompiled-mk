package androidx.car.app.model.constraints;

import androidx.car.app.model.CarIconSpan;
import androidx.car.app.model.CarSpan;
import androidx.car.app.model.CarText;
import androidx.car.app.model.ClickableSpan;
import androidx.car.app.model.DistanceSpan;
import androidx.car.app.model.DurationSpan;
import androidx.car.app.model.ForegroundCarColorSpan;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public final class CarTextConstraints {
    public static final CarTextConstraints CLICKABLE_TEXT_ONLY = new CarTextConstraints(Arrays.asList(new Class[]{ClickableSpan.class, DistanceSpan.class, DurationSpan.class}));
    public static final CarTextConstraints COLOR_ONLY = new CarTextConstraints(Arrays.asList(new Class[]{ForegroundCarColorSpan.class}));
    public static final CarTextConstraints CONSERVATIVE = new CarTextConstraints(Collections.emptyList());
    public static final CarTextConstraints TEXT_AND_ICON = new CarTextConstraints(Arrays.asList(new Class[]{DistanceSpan.class, DurationSpan.class, CarIconSpan.class}));
    public static final CarTextConstraints TEXT_ONLY = new CarTextConstraints(Arrays.asList(new Class[]{DistanceSpan.class, DurationSpan.class}));
    public static final CarTextConstraints TEXT_WITH_COLORS = new CarTextConstraints(Arrays.asList(new Class[]{DistanceSpan.class, DurationSpan.class, ForegroundCarColorSpan.class}));
    public static final CarTextConstraints TEXT_WITH_COLORS_AND_ICON = new CarTextConstraints(Arrays.asList(new Class[]{DistanceSpan.class, DurationSpan.class, ForegroundCarColorSpan.class, CarIconSpan.class}));
    public static final CarTextConstraints UNCONSTRAINED = new CarTextConstraints(Arrays.asList(new Class[]{CarIconSpan.class, ClickableSpan.class, DistanceSpan.class, DurationSpan.class, ForegroundCarColorSpan.class}));
    private final HashSet<Class<? extends CarSpan>> mAllowedTypes;

    public void validateOrThrow(CarText carText) {
        checkSupportedSpans(carText.getSpans());
        for (List<CarText.SpanWrapper> checkSupportedSpans : carText.getSpansForVariants()) {
            checkSupportedSpans(checkSupportedSpans);
        }
    }

    private void checkSupportedSpans(List<CarText.SpanWrapper> list) {
        for (CarText.SpanWrapper carSpan : list) {
            Class<?> cls = carSpan.getCarSpan().getClass();
            if (!this.mAllowedTypes.contains(cls)) {
                throw new IllegalArgumentException("CarSpan type is not allowed: " + cls.getSimpleName());
            }
        }
    }

    private CarTextConstraints(List<Class<? extends CarSpan>> list) {
        this.mAllowedTypes = new HashSet<>(list);
    }
}
