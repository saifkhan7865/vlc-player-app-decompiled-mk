package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;

@RequiresCarApi(5)
public final class Alert {
    private static final int MAX_ACTION_COUNT = 2;
    private final List<Action> mActions;
    private final AlertCallbackDelegate mCallbackDelegate;
    private final long mDuration;
    private final CarIcon mIcon;
    private final int mId;
    private final CarText mSubtitle;
    private final CarText mTitle;

    public int getId() {
        return this.mId;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public CarText getSubtitle() {
        return this.mSubtitle;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public List<Action> getActions() {
        return this.mActions;
    }

    public long getDurationMillis() {
        return this.mDuration;
    }

    public AlertCallbackDelegate getCallbackDelegate() {
        return this.mCallbackDelegate;
    }

    public String toString() {
        return "[id: " + this.mId + ", title: " + this.mTitle + ", icon: " + this.mIcon + "]";
    }

    Alert(Builder builder) {
        this.mId = builder.mId;
        this.mTitle = builder.mTitle;
        this.mSubtitle = builder.mSubtitle;
        this.mIcon = builder.mIcon;
        this.mActions = CollectionUtils.unmodifiableCopy(builder.mActions);
        this.mDuration = builder.mDuration;
        this.mCallbackDelegate = builder.mCallbackDelegate;
    }

    private Alert() {
        this.mId = 0;
        this.mTitle = CarText.create("");
        this.mSubtitle = null;
        this.mIcon = null;
        this.mActions = new ArrayList();
        this.mDuration = 0;
        this.mCallbackDelegate = null;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mId));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Alert) && this.mId == ((Alert) obj).mId) {
            return true;
        }
        return false;
    }

    public static final class Builder {
        List<Action> mActions;
        AlertCallbackDelegate mCallbackDelegate;
        long mDuration;
        CarIcon mIcon;
        int mId;
        CarText mSubtitle;
        CarText mTitle;

        public Builder(int i, CarText carText, long j) {
            if (j > 0) {
                this.mId = i;
                this.mTitle = (CarText) Objects.requireNonNull(carText);
                this.mDuration = j;
                this.mActions = new ArrayList(2);
                return;
            }
            throw new IllegalArgumentException("Duration should be a positive number.");
        }

        public Builder setSubtitle(CarText carText) {
            this.mSubtitle = (CarText) Objects.requireNonNull(carText);
            return this;
        }

        public Builder setIcon(CarIcon carIcon) {
            CarIconConstraints.DEFAULT.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mIcon = carIcon;
            return this;
        }

        public Builder addAction(Action action) {
            if (this.mActions.size() < 2) {
                this.mActions.add(action);
                return this;
            }
            throw new IllegalStateException("Cannot add more than 2 actions.");
        }

        public Builder setCallback(AlertCallback alertCallback) {
            this.mCallbackDelegate = AlertCallbackDelegateImpl.create((AlertCallback) Objects.requireNonNull(alertCallback));
            return this;
        }

        public Alert build() {
            return new Alert(this);
        }
    }
}
