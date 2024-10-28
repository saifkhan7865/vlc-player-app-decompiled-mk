package androidx.car.app.navigation.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarText;
import androidx.car.app.model.Header;
import androidx.car.app.model.Item;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ModelUtils;
import androidx.car.app.model.OnContentRefreshDelegate;
import androidx.car.app.model.OnContentRefreshDelegateImpl;
import androidx.car.app.model.OnContentRefreshListener;
import androidx.car.app.model.Template;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import j$.util.Objects;
import java.util.Collections;
import java.util.List;

public final class PlaceListNavigationTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Header mHeader;
    @Deprecated
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final ItemList mItemList;
    private final ActionStrip mMapActionStrip;
    private final OnContentRefreshDelegate mOnContentRefreshDelegate;
    private final PanModeDelegate mPanModeDelegate;
    @Deprecated
    private final CarText mTitle;

    @Deprecated
    public CarText getTitle() {
        return this.mTitle;
    }

    @RequiresCarApi(5)
    public Header getHeader() {
        return this.mHeader;
    }

    @Deprecated
    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    @RequiresCarApi(4)
    public ActionStrip getMapActionStrip() {
        return this.mMapActionStrip;
    }

    @RequiresCarApi(4)
    public PanModeDelegate getPanModeDelegate() {
        return this.mPanModeDelegate;
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public ItemList getItemList() {
        return this.mItemList;
    }

    public OnContentRefreshDelegate getOnContentRefreshDelegate() {
        return this.mOnContentRefreshDelegate;
    }

    public String toString() {
        return "PlaceListNavigationTemplate";
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, Boolean.valueOf(this.mIsLoading), this.mItemList, this.mHeaderAction, this.mActionStrip, this.mMapActionStrip, Boolean.valueOf(this.mPanModeDelegate == null), Boolean.valueOf(this.mOnContentRefreshDelegate == null), this.mHeader);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceListNavigationTemplate)) {
            return false;
        }
        PlaceListNavigationTemplate placeListNavigationTemplate = (PlaceListNavigationTemplate) obj;
        if (this.mIsLoading == placeListNavigationTemplate.mIsLoading && Objects.equals(this.mTitle, placeListNavigationTemplate.mTitle) && Objects.equals(this.mItemList, placeListNavigationTemplate.mItemList) && Objects.equals(this.mHeaderAction, placeListNavigationTemplate.mHeaderAction) && Objects.equals(this.mActionStrip, placeListNavigationTemplate.mActionStrip) && Objects.equals(this.mMapActionStrip, placeListNavigationTemplate.mMapActionStrip)) {
            if (Objects.equals(Boolean.valueOf(this.mPanModeDelegate == null), Boolean.valueOf(placeListNavigationTemplate.mPanModeDelegate == null))) {
                if (!Objects.equals(Boolean.valueOf(this.mOnContentRefreshDelegate == null), Boolean.valueOf(placeListNavigationTemplate.mOnContentRefreshDelegate == null)) || !Objects.equals(this.mHeader, placeListNavigationTemplate.mHeader)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    PlaceListNavigationTemplate(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mIsLoading = builder.mIsLoading;
        this.mItemList = builder.mItemList;
        this.mHeader = builder.mHeader;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionStrip = builder.mActionStrip;
        this.mMapActionStrip = builder.mMapActionStrip;
        this.mPanModeDelegate = builder.mPanModeDelegate;
        this.mOnContentRefreshDelegate = builder.mOnContentRefreshDelegate;
    }

    private PlaceListNavigationTemplate() {
        this.mTitle = null;
        this.mIsLoading = false;
        this.mItemList = null;
        this.mHeader = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mMapActionStrip = null;
        this.mPanModeDelegate = null;
        this.mOnContentRefreshDelegate = null;
    }

    public static final class Builder {
        ActionStrip mActionStrip;
        Header mHeader;
        Action mHeaderAction;
        boolean mIsLoading;
        ItemList mItemList;
        ActionStrip mMapActionStrip;
        OnContentRefreshDelegate mOnContentRefreshDelegate;
        PanModeDelegate mPanModeDelegate;
        CarText mTitle;

        @Deprecated
        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mTitle);
            return this;
        }

        @Deprecated
        public Builder setTitle(CarText carText) {
            this.mTitle = (CarText) Objects.requireNonNull(carText);
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mTitle);
            return this;
        }

        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        @Deprecated
        public Builder setHeaderAction(Action action) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_HEADER.validateOrThrow(Collections.singletonList((Action) Objects.requireNonNull(action)));
            this.mHeaderAction = action;
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

        @RequiresCarApi(5)
        public Builder setHeader(Header header) {
            this.mHeader = (Header) Objects.requireNonNull(header);
            return this;
        }

        public Builder setActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_NAVIGATION.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mActionStrip = actionStrip;
            return this;
        }

        @RequiresCarApi(4)
        public Builder setMapActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_MAP.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mMapActionStrip = actionStrip;
            return this;
        }

        @RequiresCarApi(4)
        public Builder setPanModeListener(PanModeListener panModeListener) {
            Objects.requireNonNull(panModeListener);
            this.mPanModeDelegate = PanModeDelegateImpl.create(panModeListener);
            return this;
        }

        public Builder setOnContentRefreshListener(OnContentRefreshListener onContentRefreshListener) {
            this.mOnContentRefreshDelegate = OnContentRefreshDelegateImpl.create(onContentRefreshListener);
            return this;
        }

        public PlaceListNavigationTemplate build() {
            if (this.mIsLoading != (this.mItemList != null)) {
                return new PlaceListNavigationTemplate(this);
            }
            throw new IllegalArgumentException("Template is in a loading state but a list is set, or vice versa");
        }
    }
}
