package androidx.car.app.model.signin;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.CarText;
import androidx.car.app.model.signin.SignInTemplate;
import j$.util.Objects;

@RequiresCarApi(2)
public final class PinSignInMethod implements SignInTemplate.SignInMethod {
    private static final int MAX_PIN_LENGTH = 12;
    private final CarText mPinCode;

    public PinSignInMethod(CharSequence charSequence) {
        int length = ((CharSequence) Objects.requireNonNull(charSequence)).length();
        if (length == 0) {
            throw new IllegalArgumentException("PIN must not be empty");
        } else if (length <= 12) {
            this.mPinCode = CarText.create(charSequence);
        } else {
            throw new IllegalArgumentException("PIN must not be longer than 12 characters");
        }
    }

    public CarText getPinCode() {
        return (CarText) Objects.requireNonNull(this.mPinCode);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PinSignInMethod)) {
            return false;
        }
        return Objects.equals(this.mPinCode, ((PinSignInMethod) obj).mPinCode);
    }

    public int hashCode() {
        return Objects.hash(this.mPinCode);
    }

    private PinSignInMethod() {
        this.mPinCode = null;
    }
}
