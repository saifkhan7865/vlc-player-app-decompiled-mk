package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import j$.util.Objects;

public final class Toggle {
    private final boolean mIsChecked;
    private final boolean mIsEnabled;
    private final OnCheckedChangeDelegate mOnCheckedChangeDelegate;

    public interface OnCheckedChangeListener {
        void onCheckedChange(boolean z);
    }

    public boolean isChecked() {
        return this.mIsChecked;
    }

    @RequiresCarApi(5)
    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public OnCheckedChangeDelegate getOnCheckedChangeDelegate() {
        return (OnCheckedChangeDelegate) Objects.requireNonNull(this.mOnCheckedChangeDelegate);
    }

    public String toString() {
        return "[ isChecked: " + this.mIsChecked + ", isEnabled: " + this.mIsEnabled + "]";
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsChecked), Boolean.valueOf(this.mIsEnabled));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Toggle)) {
            return false;
        }
        Toggle toggle = (Toggle) obj;
        if (this.mIsChecked == toggle.mIsChecked && this.mIsEnabled == toggle.mIsEnabled) {
            return true;
        }
        return false;
    }

    Toggle(Builder builder) {
        this.mIsChecked = builder.mIsChecked;
        this.mIsEnabled = builder.mIsEnabled;
        this.mOnCheckedChangeDelegate = builder.mOnCheckedChangeDelegate;
    }

    private Toggle() {
        this.mOnCheckedChangeDelegate = null;
        this.mIsChecked = false;
        this.mIsEnabled = true;
    }

    public static final class Builder {
        boolean mIsChecked;
        boolean mIsEnabled = true;
        OnCheckedChangeDelegate mOnCheckedChangeDelegate;

        public Builder setChecked(boolean z) {
            this.mIsChecked = z;
            return this;
        }

        @RequiresCarApi(5)
        public Builder setEnabled(boolean z) {
            this.mIsEnabled = z;
            return this;
        }

        public Toggle build() {
            return new Toggle(this);
        }

        public Builder(OnCheckedChangeListener onCheckedChangeListener) {
            this.mOnCheckedChangeDelegate = OnCheckedChangeDelegateImpl.create(onCheckedChangeListener);
        }
    }
}
