package androidx.car.app;

import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CarToast {
    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;
    private final CarContext mCarContext;
    private int mDuration;
    private CharSequence mText;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    CarToast(CarContext carContext) {
        this.mCarContext = (CarContext) Objects.requireNonNull(carContext);
    }

    public static CarToast makeText(CarContext carContext, int i, int i2) {
        return makeText((CarContext) Objects.requireNonNull(carContext), (CharSequence) i == 0 ? "" : carContext.getString(i), i2);
    }

    public static CarToast makeText(CarContext carContext, CharSequence charSequence, int i) {
        CarToast carToast = new CarToast((CarContext) Objects.requireNonNull(carContext));
        carToast.mText = (CharSequence) Objects.requireNonNull(charSequence);
        carToast.mDuration = i;
        return carToast;
    }

    public void setText(int i) {
        this.mText = i == 0 ? "" : this.mCarContext.getString(i);
    }

    public void setText(CharSequence charSequence) {
        this.mText = (CharSequence) Objects.requireNonNull(charSequence);
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public void show() {
        CharSequence charSequence = this.mText;
        if (charSequence != null) {
            ((AppManager) this.mCarContext.getCarService(AppManager.class)).showToast(charSequence, this.mDuration);
            return;
        }
        throw new IllegalStateException("setText must have been called");
    }
}
