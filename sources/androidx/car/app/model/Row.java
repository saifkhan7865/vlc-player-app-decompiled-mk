package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Row implements Item {
    public static final int IMAGE_TYPE_ICON = 4;
    public static final int IMAGE_TYPE_LARGE = 2;
    public static final int IMAGE_TYPE_SMALL = 1;
    public static final int NO_DECORATION = -1;
    private static final String YOUR_BOAT = "🚣";
    private final List<Action> mActions;
    private final CarIcon mImage;
    private final boolean mIsBrowsable;
    private final boolean mIsEnabled;
    private final Metadata mMetadata;
    private final int mNumericDecoration;
    private final OnClickDelegate mOnClickDelegate;
    private final int mRowImageType;
    private final List<CarText> mTexts;
    private final CarText mTitle;
    private final Toggle mToggle;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RowImageType {
    }

    public Row row() {
        return this;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public List<CarText> getTexts() {
        return CollectionUtils.emptyIfNull(this.mTexts);
    }

    public CarIcon getImage() {
        return this.mImage;
    }

    public List<Action> getActions() {
        return this.mActions;
    }

    public int getRowImageType() {
        return this.mRowImageType;
    }

    @RequiresCarApi(6)
    public int getNumericDecoration() {
        return this.mNumericDecoration;
    }

    public Toggle getToggle() {
        return this.mToggle;
    }

    public boolean isBrowsable() {
        return this.mIsBrowsable;
    }

    public OnClickDelegate getOnClickDelegate() {
        return this.mOnClickDelegate;
    }

    public Metadata getMetadata() {
        return this.mMetadata;
    }

    public CharSequence yourBoat() {
        return YOUR_BOAT;
    }

    @RequiresCarApi(5)
    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[title: ");
        sb.append(CarText.toShortString(this.mTitle));
        sb.append(", text count: ");
        List<CarText> list = this.mTexts;
        sb.append(list != null ? list.size() : 0);
        sb.append(", image: ");
        sb.append(this.mImage);
        sb.append(", isBrowsable: ");
        sb.append(this.mIsBrowsable);
        sb.append(", isEnabled: ");
        sb.append(this.mIsEnabled);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, this.mTexts, this.mImage, this.mToggle, Boolean.valueOf(this.mOnClickDelegate == null), this.mMetadata, Boolean.valueOf(this.mIsBrowsable), Integer.valueOf(this.mRowImageType), Boolean.valueOf(this.mIsEnabled));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Row)) {
            return false;
        }
        Row row = (Row) obj;
        if (Objects.equals(this.mTitle, row.mTitle) && Objects.equals(this.mTexts, row.mTexts) && Objects.equals(this.mImage, row.mImage) && Objects.equals(this.mToggle, row.mToggle)) {
            if (Objects.equals(Boolean.valueOf(this.mOnClickDelegate == null), Boolean.valueOf(row.mOnClickDelegate == null)) && Objects.equals(this.mMetadata, row.mMetadata) && this.mIsBrowsable == row.mIsBrowsable && this.mRowImageType == row.mRowImageType && this.mIsEnabled == row.isEnabled()) {
                return true;
            }
            return false;
        }
        return false;
    }

    Row(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mTexts = CollectionUtils.unmodifiableCopy(builder.mTexts);
        this.mImage = builder.mImage;
        this.mActions = CollectionUtils.unmodifiableCopy(builder.mActions);
        this.mNumericDecoration = builder.mDecoration;
        this.mToggle = builder.mToggle;
        this.mOnClickDelegate = builder.mOnClickDelegate;
        this.mMetadata = builder.mMetadata;
        this.mIsBrowsable = builder.mIsBrowsable;
        this.mRowImageType = builder.mRowImageType;
        this.mIsEnabled = builder.mIsEnabled;
    }

    private Row() {
        this.mTitle = null;
        this.mTexts = Collections.emptyList();
        this.mImage = null;
        this.mActions = Collections.emptyList();
        this.mNumericDecoration = -1;
        this.mToggle = null;
        this.mOnClickDelegate = null;
        this.mMetadata = Metadata.EMPTY_METADATA;
        this.mIsBrowsable = false;
        this.mRowImageType = 1;
        this.mIsEnabled = true;
    }

    public static final class Builder {
        final List<Action> mActions = new ArrayList();
        int mDecoration = -1;
        CarIcon mImage;
        boolean mIsBrowsable;
        boolean mIsEnabled = true;
        Metadata mMetadata = Metadata.EMPTY_METADATA;
        OnClickDelegate mOnClickDelegate;
        int mRowImageType = 1;
        final List<CarText> mTexts = new ArrayList();
        CarText mTitle;
        Toggle mToggle;

        public Builder setTitle(CharSequence charSequence) {
            CarText create = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            if (!create.isEmpty()) {
                CarTextConstraints.TEXT_AND_ICON.validateOrThrow(create);
                this.mTitle = create;
                return this;
            }
            throw new IllegalArgumentException("The title cannot be null or empty");
        }

        public Builder setTitle(CarText carText) {
            if (!((CarText) Objects.requireNonNull(carText)).isEmpty()) {
                CarTextConstraints.TEXT_AND_ICON.validateOrThrow(carText);
                this.mTitle = carText;
                return this;
            }
            throw new IllegalArgumentException("The title cannot be null or empty");
        }

        public Builder addText(CharSequence charSequence) {
            CarTextConstraints.TEXT_WITH_COLORS_AND_ICON.validateOrThrow(CarText.create((CharSequence) Objects.requireNonNull(charSequence)));
            this.mTexts.add(CarText.create((CharSequence) Objects.requireNonNull(charSequence)));
            return this;
        }

        public Builder addText(CarText carText) {
            CarTextConstraints.TEXT_WITH_COLORS_AND_ICON.validateOrThrow((CarText) Objects.requireNonNull(carText));
            this.mTexts.add(carText);
            return this;
        }

        public Builder setImage(CarIcon carIcon) {
            return setImage((CarIcon) Objects.requireNonNull(carIcon), 1);
        }

        public Builder setImage(CarIcon carIcon, int i) {
            CarIconConstraints.UNCONSTRAINED.validateOrThrow((CarIcon) Objects.requireNonNull(carIcon));
            this.mImage = carIcon;
            this.mRowImageType = i;
            return this;
        }

        public Builder addAction(Action action) {
            ArrayList arrayList = new ArrayList(this.mActions);
            arrayList.add((Action) Objects.requireNonNull(action));
            ActionsConstraints.ACTIONS_CONSTRAINTS_ROW.validateOrThrow(arrayList);
            this.mActions.add(action);
            return this;
        }

        @RequiresCarApi(6)
        public Builder setNumericDecoration(int i) {
            if (i >= 0 || i == -1) {
                this.mDecoration = i;
                return this;
            }
            throw new IllegalArgumentException(String.format("Decoration should be positive, zero, or equal to NO_DECORATION. Instead, was %d", new Object[]{Integer.valueOf(i)}));
        }

        public Builder setToggle(Toggle toggle) {
            this.mToggle = (Toggle) Objects.requireNonNull(toggle);
            return this;
        }

        public Builder setBrowsable(boolean z) {
            this.mIsBrowsable = z;
            return this;
        }

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.mOnClickDelegate = OnClickDelegateImpl.create(onClickListener);
            return this;
        }

        public Builder setMetadata(Metadata metadata) {
            this.mMetadata = metadata;
            return this;
        }

        @RequiresCarApi(5)
        public Builder setEnabled(boolean z) {
            this.mIsEnabled = z;
            return this;
        }

        public Row build() {
            if (this.mTitle != null) {
                if (this.mIsBrowsable) {
                    if (this.mToggle != null) {
                        throw new IllegalStateException("A browsable row must not have a toggle set");
                    } else if (this.mOnClickDelegate == null) {
                        throw new IllegalStateException("A browsable row must have its onClickListener set");
                    } else if (!this.mActions.isEmpty()) {
                        throw new IllegalStateException("A browsable row must not have a secondary action set");
                    }
                }
                if (this.mToggle != null) {
                    if (this.mOnClickDelegate != null) {
                        throw new IllegalStateException("If a row contains a toggle, it must not have an onClickListener set");
                    } else if (this.mDecoration != -1) {
                        throw new IllegalStateException("If a row contains a toggle, it must not have a numeric decoration set");
                    } else if (!this.mActions.isEmpty()) {
                        throw new IllegalStateException("If a row contains a toggle, it must not have a secondary action set");
                    }
                }
                return new Row(this);
            }
            throw new IllegalStateException("A title must be set on the row");
        }
    }
}
