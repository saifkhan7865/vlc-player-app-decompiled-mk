package androidx.car.app.model;

import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import j$.util.Objects;
import java.util.Collections;

public final class GridTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final ItemList mSingleList;
    private final CarText mTitle;

    public CarText getTitle() {
        return this.mTitle;
    }

    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public ItemList getSingleList() {
        return this.mSingleList;
    }

    public String toString() {
        return "GridTemplate";
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsLoading), this.mTitle, this.mHeaderAction, this.mSingleList, this.mActionStrip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GridTemplate)) {
            return false;
        }
        GridTemplate gridTemplate = (GridTemplate) obj;
        if (this.mIsLoading != gridTemplate.mIsLoading || !Objects.equals(this.mTitle, gridTemplate.mTitle) || !Objects.equals(this.mHeaderAction, gridTemplate.mHeaderAction) || !Objects.equals(this.mSingleList, gridTemplate.mSingleList) || !Objects.equals(this.mActionStrip, gridTemplate.mActionStrip)) {
            return false;
        }
        return true;
    }

    GridTemplate(Builder builder) {
        this.mIsLoading = builder.mIsLoading;
        this.mTitle = builder.mTitle;
        this.mHeaderAction = builder.mHeaderAction;
        this.mSingleList = builder.mSingleList;
        this.mActionStrip = builder.mActionStrip;
    }

    private GridTemplate() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mHeaderAction = null;
        this.mSingleList = null;
        this.mActionStrip = null;
    }

    public static final class Builder {
        ActionStrip mActionStrip;
        Action mHeaderAction;
        boolean mIsLoading;
        ItemList mSingleList;
        CarText mTitle;

        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        public Builder setHeaderAction(Action action) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_HEADER.validateOrThrow(Collections.singletonList(action));
            this.mHeaderAction = action;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mTitle);
            return this;
        }

        public Builder setSingleList(ItemList itemList) {
            this.mSingleList = (ItemList) Objects.requireNonNull(itemList);
            return this;
        }

        public Builder setActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_SIMPLE.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mActionStrip = actionStrip;
            return this;
        }

        public GridTemplate build() {
            ItemList itemList = this.mSingleList;
            if (this.mIsLoading != (itemList != null)) {
                if (itemList != null) {
                    for (Item item : itemList.getItems()) {
                        if (!(item instanceof GridItem)) {
                            throw new IllegalArgumentException("All the items in grid template's item list must be grid items");
                        }
                    }
                }
                return new GridTemplate(this);
            }
            throw new IllegalStateException("Template is in a loading state but lists are added, or vice versa");
        }
    }
}
