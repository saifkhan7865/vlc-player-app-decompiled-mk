package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import j$.util.Objects;

@RequiresCarApi(6)
public final class Tab implements Content {
    private static final String EMPTY_TAB_CONTENT_ID = "EMPTY_TAB_CONTENT_ID";
    private final String mContentId;
    private final CarIcon mIcon;
    private final boolean mIsActive;
    private final CarText mTitle;

    public CarText getTitle() {
        return (CarText) Objects.requireNonNull(this.mTitle);
    }

    public String getContentId() {
        return (String) Objects.requireNonNull(this.mContentId);
    }

    public CarIcon getIcon() {
        return (CarIcon) Objects.requireNonNull(this.mIcon);
    }

    public boolean isActive() {
        return this.mIsActive;
    }

    public String toString() {
        return "[title: " + CarText.toShortString(this.mTitle) + ", contentId: " + this.mContentId + ", icon: " + this.mIcon + ", isActive " + this.mIsActive + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, this.mContentId, this.mIcon, Boolean.valueOf(this.mIsActive));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tab)) {
            return false;
        }
        Tab tab = (Tab) obj;
        if (!Objects.equals(this.mTitle, tab.mTitle) || !Objects.equals(this.mContentId, tab.mContentId) || !Objects.equals(this.mIcon, tab.mIcon) || this.mIsActive != tab.isActive()) {
            return false;
        }
        return true;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    Tab(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mIcon = builder.mIcon;
        this.mIsActive = builder.mIsActive;
        if (builder.mContentId != null) {
            this.mContentId = builder.mContentId;
        } else {
            this.mContentId = EMPTY_TAB_CONTENT_ID;
        }
    }

    private Tab() {
        this.mTitle = null;
        this.mContentId = EMPTY_TAB_CONTENT_ID;
        this.mIcon = null;
        this.mIsActive = false;
    }

    public static final class Builder {
        String mContentId;
        CarIcon mIcon;
        boolean mIsActive;
        CarText mTitle;

        public Builder setTitle(CharSequence charSequence) {
            CarText create = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            if (!create.isEmpty()) {
                CarTextConstraints.TEXT_AND_ICON.validateOrThrow(create);
                this.mTitle = create;
                return this;
            }
            throw new IllegalArgumentException("The title cannot be null or empty");
        }

        public Builder setContentId(String str) {
            if (!((String) Objects.requireNonNull(str)).isEmpty()) {
                this.mContentId = str;
                return this;
            }
            throw new IllegalArgumentException("The content ID cannot be null or empty");
        }

        public Builder setIcon(CarIcon carIcon) {
            CarIconConstraints.DEFAULT.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mIcon = carIcon;
            return this;
        }

        public Builder setActive(boolean z) {
            this.mIsActive = z;
            return this;
        }

        public Tab build() {
            if (this.mTitle == null) {
                throw new IllegalStateException("A title must be set for the tab");
            } else if (this.mIcon == null) {
                throw new IllegalStateException("A icon must be set for the tab");
            } else if (this.mContentId != null) {
                return new Tab(this);
            } else {
                throw new IllegalStateException("A content ID must be set for the tab");
            }
        }

        public Builder() {
        }

        Builder(Tab tab) {
            Objects.requireNonNull(tab);
            this.mIsActive = tab.isActive();
            this.mContentId = tab.getContentId();
            this.mIcon = tab.getIcon();
            this.mTitle = tab.getTitle();
        }
    }
}
