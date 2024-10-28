package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import j$.util.Objects;
import java.util.Collections;
import java.util.List;

public final class PlaceListMapTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Place mAnchor;
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final ItemList mItemList;
    private final OnContentRefreshDelegate mOnContentRefreshDelegate;
    private final boolean mShowCurrentLocation;
    private final CarText mTitle;

    public boolean isCurrentLocationEnabled() {
        return this.mShowCurrentLocation;
    }

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

    public ItemList getItemList() {
        return this.mItemList;
    }

    public Place getAnchor() {
        return this.mAnchor;
    }

    @RequiresCarApi(5)
    public OnContentRefreshDelegate getOnContentRefreshDelegate() {
        return this.mOnContentRefreshDelegate;
    }

    public String toString() {
        return "PlaceListMapTemplate";
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mShowCurrentLocation), Boolean.valueOf(this.mIsLoading), this.mTitle, this.mItemList, this.mHeaderAction, this.mActionStrip, this.mAnchor, Boolean.valueOf(this.mOnContentRefreshDelegate == null));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceListMapTemplate)) {
            return false;
        }
        PlaceListMapTemplate placeListMapTemplate = (PlaceListMapTemplate) obj;
        if (this.mShowCurrentLocation == placeListMapTemplate.mShowCurrentLocation && this.mIsLoading == placeListMapTemplate.mIsLoading && Objects.equals(this.mTitle, placeListMapTemplate.mTitle) && Objects.equals(this.mItemList, placeListMapTemplate.mItemList) && Objects.equals(this.mHeaderAction, placeListMapTemplate.mHeaderAction) && Objects.equals(this.mActionStrip, placeListMapTemplate.mActionStrip) && Objects.equals(this.mAnchor, placeListMapTemplate.mAnchor)) {
            if (Objects.equals(Boolean.valueOf(this.mOnContentRefreshDelegate == null), Boolean.valueOf(placeListMapTemplate.mOnContentRefreshDelegate == null))) {
                return true;
            }
        }
        return false;
    }

    PlaceListMapTemplate(Builder builder) {
        this.mShowCurrentLocation = builder.mShowCurrentLocation;
        this.mIsLoading = builder.mIsLoading;
        this.mTitle = builder.mTitle;
        this.mItemList = builder.mItemList;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionStrip = builder.mActionStrip;
        this.mAnchor = builder.mAnchor;
        this.mOnContentRefreshDelegate = builder.mOnContentRefreshDelegate;
    }

    private PlaceListMapTemplate() {
        this.mShowCurrentLocation = false;
        this.mIsLoading = false;
        this.mTitle = null;
        this.mItemList = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mAnchor = null;
        this.mOnContentRefreshDelegate = null;
    }

    public static final class Builder {
        ActionStrip mActionStrip;
        Place mAnchor;
        Action mHeaderAction;
        boolean mIsLoading;
        ItemList mItemList;
        @RequiresCarApi(5)
        OnContentRefreshDelegate mOnContentRefreshDelegate;
        boolean mShowCurrentLocation;
        CarText mTitle;

        public Builder setCurrentLocationEnabled(boolean z) {
            this.mShowCurrentLocation = z;
            return this;
        }

        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        public Builder setHeaderAction(Action action) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_HEADER.validateOrThrow(Collections.singletonList((Action) Objects.requireNonNull(action)));
            this.mHeaderAction = action;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mTitle);
            return this;
        }

        public Builder setTitle(CarText carText) {
            this.mTitle = (CarText) Objects.requireNonNull(carText);
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mTitle);
            return this;
        }

        public Builder setItemList(ItemList itemList) {
            List<Item> items = ((ItemList) Objects.requireNonNull(itemList)).getItems();
            RowListConstraints.ROW_LIST_CONSTRAINTS_SIMPLE.validateOrThrow(itemList);
            ModelUtils.validateAllNonBrowsableRowsHaveDistance(items);
            ModelUtils.validateAllRowsHaveOnlySmallImages(items);
            ModelUtils.validateNoRowsHaveBothMarkersAndImages(items);
            this.mItemList = itemList;
            return this;
        }

        public Builder setActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_NAVIGATION.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mActionStrip = actionStrip;
            return this;
        }

        public Builder setAnchor(Place place) {
            this.mAnchor = (Place) Objects.requireNonNull(place);
            return this;
        }

        @RequiresCarApi(5)
        public Builder setOnContentRefreshListener(OnContentRefreshListener onContentRefreshListener) {
            this.mOnContentRefreshDelegate = OnContentRefreshDelegateImpl.create(onContentRefreshListener);
            return this;
        }

        public PlaceListMapTemplate build() {
            if (this.mIsLoading != (this.mItemList != null)) {
                return new PlaceListMapTemplate(this);
            }
            throw new IllegalArgumentException("Template is in a loading state but a list is set, or vice versa");
        }
    }
}
