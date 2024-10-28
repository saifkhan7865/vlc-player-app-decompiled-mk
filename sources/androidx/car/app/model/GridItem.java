package androidx.car.app.model;

import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class GridItem implements Item {
    public static final int IMAGE_TYPE_ICON = 1;
    public static final int IMAGE_TYPE_LARGE = 2;
    private final CarIcon mImage;
    private final int mImageType;
    private final boolean mIsLoading;
    private final OnClickDelegate mOnClickDelegate;
    private final CarText mText;
    private final CarText mTitle;

    @Retention(RetentionPolicy.SOURCE)
    public @interface GridItemImageType {
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public CarText getText() {
        return this.mText;
    }

    public CarIcon getImage() {
        return this.mImage;
    }

    public int getImageType() {
        return this.mImageType;
    }

    public OnClickDelegate getOnClickDelegate() {
        return this.mOnClickDelegate;
    }

    public String toString() {
        return "[title: " + CarText.toShortString(this.mTitle) + ", text: " + CarText.toShortString(this.mText) + ", image: " + this.mImage + ", isLoading: " + this.mIsLoading + "]";
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsLoading), this.mTitle, this.mImage, Integer.valueOf(this.mImageType), Boolean.valueOf(this.mOnClickDelegate == null));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GridItem)) {
            return false;
        }
        GridItem gridItem = (GridItem) obj;
        if (this.mIsLoading == gridItem.mIsLoading && Objects.equals(this.mTitle, gridItem.mTitle) && Objects.equals(this.mText, gridItem.mText) && Objects.equals(this.mImage, gridItem.mImage)) {
            if (!Objects.equals(Boolean.valueOf(this.mOnClickDelegate == null), Boolean.valueOf(gridItem.mOnClickDelegate == null)) || this.mImageType != gridItem.mImageType) {
                return false;
            }
            return true;
        }
        return false;
    }

    GridItem(Builder builder) {
        this.mIsLoading = builder.mIsLoading;
        this.mTitle = builder.mTitle;
        this.mText = builder.mText;
        this.mImage = builder.mImage;
        this.mImageType = builder.mImageType;
        this.mOnClickDelegate = builder.mOnClickDelegate;
    }

    private GridItem() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mText = null;
        this.mImage = null;
        this.mImageType = 2;
        this.mOnClickDelegate = null;
    }

    public static final class Builder {
        CarIcon mImage;
        int mImageType = 2;
        boolean mIsLoading;
        OnClickDelegate mOnClickDelegate;
        CarText mText;
        CarText mTitle;

        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            CarText create = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            if (!create.isEmpty()) {
                CarTextConstraints.TEXT_ONLY.validateOrThrow(create);
                this.mTitle = create;
                return this;
            }
            throw new IllegalArgumentException("The title cannot be null or empty");
        }

        public Builder setTitle(CarText carText) {
            if (!CarText.isNullOrEmpty(carText)) {
                CarTextConstraints.TEXT_ONLY.validateOrThrow(carText);
                this.mTitle = carText;
                return this;
            }
            throw new IllegalArgumentException("The title cannot be null or empty");
        }

        public Builder setText(CharSequence charSequence) {
            this.mText = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_WITH_COLORS.validateOrThrow(this.mText);
            return this;
        }

        public Builder setText(CarText carText) {
            this.mText = (CarText) Objects.requireNonNull(carText);
            CarTextConstraints.TEXT_WITH_COLORS.validateOrThrow(this.mText);
            return this;
        }

        public Builder setImage(CarIcon carIcon) {
            return setImage((CarIcon) Objects.requireNonNull(carIcon), 2);
        }

        public Builder setImage(CarIcon carIcon, int i) {
            CarIconConstraints.UNCONSTRAINED.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mImage = carIcon;
            this.mImageType = i;
            return this;
        }

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.mOnClickDelegate = OnClickDelegateImpl.create(onClickListener);
            return this;
        }

        public GridItem build() {
            if (this.mTitle != null) {
                boolean z = this.mIsLoading;
                if (z == (this.mImage != null)) {
                    throw new IllegalStateException("When a grid item is loading, the image must not be set and vice versa");
                } else if (!z || this.mOnClickDelegate == null) {
                    return new GridItem(this);
                } else {
                    throw new IllegalStateException("The click listener must not be set on the grid item when it is loading");
                }
            } else {
                throw new IllegalStateException("A title must be set on the grid item");
            }
        }
    }
}
