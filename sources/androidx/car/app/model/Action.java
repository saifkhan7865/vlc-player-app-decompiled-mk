package androidx.car.app.model;

import android.text.TextUtils;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.CarColorConstraints;
import androidx.car.app.model.constraints.CarIconConstraints;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Action {
    public static final Action APP_ICON = new Action((int) TYPE_APP_ICON);
    public static final Action BACK = new Action((int) TYPE_BACK);
    @RequiresCarApi(5)
    public static final int FLAG_DEFAULT = 4;
    @RequiresCarApi(5)
    public static final int FLAG_IS_PERSISTENT = 2;
    @RequiresCarApi(4)
    public static final int FLAG_PRIMARY = 1;
    public static final Action PAN = new Action(65540);
    public static final int TYPE_APP_ICON = 65538;
    public static final int TYPE_BACK = 65539;
    public static final int TYPE_CUSTOM = 1;
    public static final int TYPE_PAN = 65540;
    static final int TYPE_STANDARD = 65536;
    private final CarColor mBackgroundColor;
    private final int mFlags;
    private final CarIcon mIcon;
    private final boolean mIsEnabled;
    private final OnClickDelegate mOnClickDelegate;
    private final CarText mTitle;
    private final int mType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActionFlag {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActionType {
    }

    static boolean isStandardActionType(int i) {
        return (i & 65536) != 0;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public CarColor getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public int getType() {
        return this.mType;
    }

    @RequiresCarApi(4)
    public int getFlags() {
        return this.mFlags;
    }

    public boolean isStandard() {
        return isStandardActionType(this.mType);
    }

    public OnClickDelegate getOnClickDelegate() {
        return this.mOnClickDelegate;
    }

    @RequiresCarApi(5)
    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public String toString() {
        return "[type: " + typeToString(this.mType) + ", icon: " + this.mIcon + ", bkg: " + this.mBackgroundColor + ", isEnabled: " + this.mIsEnabled + "]";
    }

    public static String typeToString(int i) {
        if (i == 1) {
            return "CUSTOM";
        }
        switch (i) {
            case TYPE_APP_ICON /*65538*/:
                return "APP_ICON";
            case TYPE_BACK /*65539*/:
                return "BACK";
            case 65540:
                return "PAN";
            default:
                return "<unknown>";
        }
    }

    private Action(int i) {
        if (i != 1) {
            this.mTitle = null;
            this.mIcon = null;
            this.mBackgroundColor = CarColor.DEFAULT;
            this.mOnClickDelegate = null;
            this.mType = i;
            this.mFlags = 0;
            this.mIsEnabled = true;
            return;
        }
        throw new IllegalArgumentException("Standard action constructor used with non standard type");
    }

    Action(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mIcon = builder.mIcon;
        this.mBackgroundColor = builder.mBackgroundColor;
        this.mOnClickDelegate = builder.mOnClickDelegate;
        this.mType = builder.mType;
        this.mFlags = builder.mFlags;
        this.mIsEnabled = builder.mIsEnabled;
    }

    private Action() {
        this.mTitle = null;
        this.mIcon = null;
        this.mBackgroundColor = CarColor.DEFAULT;
        this.mOnClickDelegate = null;
        this.mType = 1;
        this.mFlags = 0;
        this.mIsEnabled = true;
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, Integer.valueOf(this.mType), Boolean.valueOf(this.mOnClickDelegate == null), Boolean.valueOf(this.mIcon == null), Boolean.valueOf(this.mIsEnabled));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Action)) {
            return false;
        }
        Action action = (Action) obj;
        if (Objects.equals(this.mTitle, action.mTitle) && this.mType == action.mType && Objects.equals(this.mIcon, action.mIcon)) {
            if (!Objects.equals(Boolean.valueOf(this.mOnClickDelegate == null), Boolean.valueOf(action.mOnClickDelegate == null)) || !Objects.equals(Integer.valueOf(this.mFlags), Integer.valueOf(action.mFlags)) || this.mIsEnabled != action.mIsEnabled) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static final class Builder {
        CarColor mBackgroundColor = CarColor.DEFAULT;
        int mFlags = 0;
        CarIcon mIcon;
        boolean mIsEnabled = true;
        OnClickDelegate mOnClickDelegate;
        CarText mTitle;
        int mType = 1;

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            return this;
        }

        public Builder setTitle(CarText carText) {
            this.mTitle = (CarText) Objects.requireNonNull(carText);
            return this;
        }

        public Builder setIcon(CarIcon carIcon) {
            CarIconConstraints.DEFAULT.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mIcon = carIcon;
            return this;
        }

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.mOnClickDelegate = OnClickDelegateImpl.create(onClickListener);
            return this;
        }

        public Builder setBackgroundColor(CarColor carColor) {
            CarColorConstraints.UNCONSTRAINED.validateOrThrow((CarColor) Objects.requireNonNull(carColor));
            this.mBackgroundColor = carColor;
            return this;
        }

        @RequiresCarApi(5)
        public Builder setEnabled(boolean z) {
            this.mIsEnabled = z;
            return this;
        }

        @RequiresCarApi(4)
        public Builder setFlags(int i) {
            this.mFlags = i | this.mFlags;
            return this;
        }

        public Action build() {
            CarText carText;
            CarText carText2;
            if (Action.isStandardActionType(this.mType) || this.mIcon != null || ((carText2 = this.mTitle) != null && !TextUtils.isEmpty(carText2.toString()))) {
                int i = this.mType;
                if (i == 65538 || i == 65539) {
                    if (this.mOnClickDelegate != null) {
                        throw new IllegalStateException("An on-click listener can't be set on the standard back or app-icon action");
                    } else if (this.mIcon != null || ((carText = this.mTitle) != null && !TextUtils.isEmpty(carText.toString()))) {
                        throw new IllegalStateException("An icon or title can't be set on the standard back or app-icon action");
                    }
                }
                if (this.mType != 65540 || this.mOnClickDelegate == null) {
                    return new Action(this);
                }
                throw new IllegalStateException("An on-click listener can't be set on the pan mode action");
            }
            throw new IllegalStateException("An action must have either an icon or a title");
        }

        public Builder() {
        }

        @RequiresCarApi(2)
        public Builder(Action action) {
            Objects.requireNonNull(action);
            this.mType = action.getType();
            this.mIcon = action.getIcon();
            this.mTitle = action.getTitle();
            this.mOnClickDelegate = action.getOnClickDelegate();
            CarColor backgroundColor = action.getBackgroundColor();
            this.mBackgroundColor = backgroundColor == null ? CarColor.DEFAULT : backgroundColor;
            this.mFlags = action.getFlags();
            this.mIsEnabled = action.isEnabled();
        }
    }
}
