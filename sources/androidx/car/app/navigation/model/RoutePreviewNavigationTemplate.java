package androidx.car.app.navigation.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarText;
import androidx.car.app.model.Header;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ModelUtils;
import androidx.car.app.model.Template;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import j$.util.Objects;
import java.util.Collections;

public final class RoutePreviewNavigationTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Header mHeader;
    @Deprecated
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final ItemList mItemList;
    private final ActionStrip mMapActionStrip;
    private final Action mNavigateAction;
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

    public Action getNavigateAction() {
        return this.mNavigateAction;
    }

    public ItemList getItemList() {
        return this.mItemList;
    }

    public String toString() {
        return "RoutePreviewNavigationTemplate";
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, Boolean.valueOf(this.mIsLoading), this.mNavigateAction, this.mItemList, this.mHeaderAction, this.mActionStrip, this.mMapActionStrip, Boolean.valueOf(this.mPanModeDelegate == null), this.mHeader);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoutePreviewNavigationTemplate)) {
            return false;
        }
        RoutePreviewNavigationTemplate routePreviewNavigationTemplate = (RoutePreviewNavigationTemplate) obj;
        if (this.mIsLoading == routePreviewNavigationTemplate.mIsLoading && Objects.equals(this.mTitle, routePreviewNavigationTemplate.mTitle) && Objects.equals(this.mNavigateAction, routePreviewNavigationTemplate.mNavigateAction) && Objects.equals(this.mItemList, routePreviewNavigationTemplate.mItemList) && Objects.equals(this.mHeaderAction, routePreviewNavigationTemplate.mHeaderAction) && Objects.equals(this.mActionStrip, routePreviewNavigationTemplate.mActionStrip) && Objects.equals(this.mMapActionStrip, routePreviewNavigationTemplate.mMapActionStrip)) {
            if (!Objects.equals(Boolean.valueOf(this.mPanModeDelegate == null), Boolean.valueOf(routePreviewNavigationTemplate.mPanModeDelegate == null)) || !Objects.equals(this.mHeader, routePreviewNavigationTemplate.mHeader)) {
                return false;
            }
            return true;
        }
        return false;
    }

    RoutePreviewNavigationTemplate(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mIsLoading = builder.mIsLoading;
        this.mNavigateAction = builder.mNavigateAction;
        this.mItemList = builder.mItemList;
        this.mHeader = builder.mHeader;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionStrip = builder.mActionStrip;
        this.mMapActionStrip = builder.mMapActionStrip;
        this.mPanModeDelegate = builder.mPanModeDelegate;
    }

    private RoutePreviewNavigationTemplate() {
        this.mTitle = null;
        this.mIsLoading = false;
        this.mNavigateAction = null;
        this.mItemList = null;
        this.mHeader = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mMapActionStrip = null;
        this.mPanModeDelegate = null;
    }

    public static final class Builder {
        ActionStrip mActionStrip;
        Header mHeader;
        Action mHeaderAction;
        boolean mIsLoading;
        ItemList mItemList;
        ActionStrip mMapActionStrip;
        Action mNavigateAction;
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

        public Builder setNavigateAction(Action action) {
            if (!CarText.isNullOrEmpty(((Action) Objects.requireNonNull(action)).getTitle())) {
                this.mNavigateAction = (Action) Objects.requireNonNull(action);
                return this;
            }
            throw new IllegalArgumentException("The Action's title cannot be null or empty");
        }

        @RequiresCarApi(5)
        public Builder setHeader(Header header) {
            this.mHeader = (Header) Objects.requireNonNull(header);
            return this;
        }

        public Builder setItemList(ItemList itemList) {
            RowListConstraints.MAP_ROW_LIST_CONSTRAINTS_ALLOW_SELECTABLE.validateOrThrow((ItemList) Objects.requireNonNull(itemList));
            ModelUtils.validateAllRowsHaveDistanceOrDuration(itemList.getItems());
            ModelUtils.validateAllRowsHaveOnlySmallImages(itemList.getItems());
            if (itemList.getItems().isEmpty() || itemList.getOnSelectedDelegate() != null) {
                this.mItemList = itemList;
                return this;
            }
            throw new IllegalArgumentException("The OnSelectedListener must be set for the route list");
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

        public RoutePreviewNavigationTemplate build() {
            boolean z = this.mItemList != null;
            boolean z2 = this.mIsLoading;
            if (z2 == z) {
                throw new IllegalStateException("Template is in a loading state but a list is set, or vice versa");
            } else if (z2 || this.mNavigateAction != null) {
                return new RoutePreviewNavigationTemplate(this);
            } else {
                throw new IllegalStateException("The navigation action cannot be null when the list is not in a loading state");
            }
        }
    }
}
