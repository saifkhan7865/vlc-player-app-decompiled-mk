package androidx.car.app.model.signin;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.CarText;
import androidx.car.app.model.InputCallback;
import androidx.car.app.model.InputCallbackDelegate;
import androidx.car.app.model.InputCallbackDelegateImpl;
import androidx.car.app.model.signin.SignInTemplate;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresCarApi(2)
public final class InputSignInMethod implements SignInTemplate.SignInMethod {
    public static final int INPUT_TYPE_DEFAULT = 1;
    public static final int INPUT_TYPE_PASSWORD = 2;
    public static final int KEYBOARD_DEFAULT = 1;
    public static final int KEYBOARD_EMAIL = 2;
    public static final int KEYBOARD_NUMBER = 4;
    public static final int KEYBOARD_PHONE = 3;
    private final CarText mDefaultValue;
    private final CarText mErrorMessage;
    private final CarText mHint;
    private final InputCallbackDelegate mInputCallbackDelegate;
    private final int mInputType;
    private final int mKeyboardType;
    private final boolean mShowKeyboardByDefault;

    @Retention(RetentionPolicy.SOURCE)
    public @interface InputType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KeyboardType {
    }

    public CarText getHint() {
        return this.mHint;
    }

    public CarText getDefaultValue() {
        return this.mDefaultValue;
    }

    public int getInputType() {
        return this.mInputType;
    }

    public CarText getErrorMessage() {
        return this.mErrorMessage;
    }

    public int getKeyboardType() {
        return this.mKeyboardType;
    }

    public InputCallbackDelegate getInputCallbackDelegate() {
        return (InputCallbackDelegate) Objects.requireNonNull(this.mInputCallbackDelegate);
    }

    public boolean isShowKeyboardByDefault() {
        return this.mShowKeyboardByDefault;
    }

    public String toString() {
        return "[inputType:" + this.mInputType + ", keyboardType: " + this.mKeyboardType + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InputSignInMethod)) {
            return false;
        }
        InputSignInMethod inputSignInMethod = (InputSignInMethod) obj;
        if (this.mInputType != inputSignInMethod.mInputType || this.mKeyboardType != inputSignInMethod.mKeyboardType || this.mShowKeyboardByDefault != inputSignInMethod.mShowKeyboardByDefault || !Objects.equals(this.mHint, inputSignInMethod.mHint) || !Objects.equals(this.mDefaultValue, inputSignInMethod.mDefaultValue) || !Objects.equals(this.mErrorMessage, inputSignInMethod.mErrorMessage)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.mHint, this.mDefaultValue, Integer.valueOf(this.mInputType), this.mErrorMessage, Integer.valueOf(this.mKeyboardType), Boolean.valueOf(this.mShowKeyboardByDefault));
    }

    InputSignInMethod(Builder builder) {
        this.mHint = builder.mHint;
        this.mDefaultValue = builder.mDefaultValue;
        this.mInputType = builder.mInputType;
        this.mErrorMessage = builder.mErrorMessage;
        this.mKeyboardType = builder.mKeyboardType;
        this.mInputCallbackDelegate = builder.mInputCallbackDelegate;
        this.mShowKeyboardByDefault = builder.mShowKeyboardByDefault;
    }

    private InputSignInMethod() {
        this.mHint = null;
        this.mDefaultValue = null;
        this.mInputType = 1;
        this.mErrorMessage = null;
        this.mKeyboardType = 1;
        this.mInputCallbackDelegate = null;
        this.mShowKeyboardByDefault = false;
    }

    public static final class Builder {
        CarText mDefaultValue;
        CarText mErrorMessage;
        CarText mHint;
        final InputCallbackDelegate mInputCallbackDelegate;
        int mInputType = 1;
        int mKeyboardType = 1;
        boolean mShowKeyboardByDefault;

        public Builder setHint(CharSequence charSequence) {
            this.mHint = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            return this;
        }

        public Builder setDefaultValue(String str) {
            this.mDefaultValue = CarText.create((CharSequence) Objects.requireNonNull(str));
            return this;
        }

        public Builder setInputType(int i) {
            this.mInputType = validateInputType(i);
            return this;
        }

        public Builder setErrorMessage(CharSequence charSequence) {
            this.mErrorMessage = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            return this;
        }

        public Builder setKeyboardType(int i) {
            this.mKeyboardType = validateKeyboardType(i);
            return this;
        }

        public Builder setShowKeyboardByDefault(boolean z) {
            this.mShowKeyboardByDefault = z;
            return this;
        }

        public InputSignInMethod build() {
            return new InputSignInMethod(this);
        }

        public Builder(InputCallback inputCallback) {
            this.mInputCallbackDelegate = InputCallbackDelegateImpl.create((InputCallback) Objects.requireNonNull(inputCallback));
        }

        private static int validateKeyboardType(int i) {
            if (i == 1 || i == 2 || i == 4 || i == 3) {
                return i;
            }
            throw new IllegalArgumentException("Keyboard type is not supported: " + i);
        }

        private static int validateInputType(int i) {
            if (i == 1 || i == 2) {
                return i;
            }
            throw new IllegalArgumentException("Invalid input type: " + i);
        }
    }
}
