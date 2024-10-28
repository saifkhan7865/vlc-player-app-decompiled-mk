package androidx.car.app.navigation.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.Header;
import androidx.car.app.model.Item;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ModelUtils;
import androidx.car.app.model.Pane;
import androidx.car.app.model.Template;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import j$.util.Objects;
import java.util.List;

@RequiresCarApi(5)
public final class MapTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Header mHeader;
    private final ItemList mItemList;
    private final MapController mMapController;
    private final Pane mPane;

    MapTemplate(Builder builder) {
        this.mMapController = builder.mMapController;
        this.mPane = builder.mPane;
        this.mItemList = builder.mItemList;
        this.mHeader = builder.mHeader;
        this.mActionStrip = builder.mActionStrip;
    }

    private MapTemplate() {
        this.mMapController = null;
        this.mPane = null;
        this.mItemList = null;
        this.mHeader = null;
        this.mActionStrip = null;
    }

    public MapController getMapController() {
        return this.mMapController;
    }

    public Pane getPane() {
        return this.mPane;
    }

    public ItemList getItemList() {
        return this.mItemList;
    }

    public Header getHeader() {
        return this.mHeader;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public int hashCode() {
        return Objects.hash(this.mMapController, this.mPane, this.mItemList, this.mHeader, this.mActionStrip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MapTemplate)) {
            return false;
        }
        MapTemplate mapTemplate = (MapTemplate) obj;
        if (!Objects.equals(this.mPane, mapTemplate.mPane) || !Objects.equals(this.mItemList, mapTemplate.mItemList) || !Objects.equals(this.mHeader, mapTemplate.mHeader) || !Objects.equals(this.mMapController, mapTemplate.mMapController) || !Objects.equals(this.mActionStrip, mapTemplate.mActionStrip)) {
            return false;
        }
        return true;
    }

    public static final class Builder {
        ActionStrip mActionStrip;
        Header mHeader;
        ItemList mItemList;
        MapController mMapController;
        Pane mPane;

        public Builder setActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_NAVIGATION.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mActionStrip = actionStrip;
            return this;
        }

        public Builder setPane(Pane pane) {
            List<Action> actions = ((Pane) Objects.requireNonNull(pane)).getActions();
            RowListConstraints.ROW_LIST_CONSTRAINTS_PANE.validateOrThrow(pane);
            ActionsConstraints.ACTIONS_CONSTRAINTS_BODY_WITH_PRIMARY_ACTION.validateOrThrow(actions);
            this.mPane = pane;
            return this;
        }

        public Builder setItemList(ItemList itemList) {
            List<Item> items = ((ItemList) Objects.requireNonNull(itemList)).getItems();
            RowListConstraints.MAP_ROW_LIST_CONSTRAINTS_ALLOW_SELECTABLE.validateOrThrow(itemList);
            ModelUtils.validateAllRowsHaveOnlySmallImages(items);
            ModelUtils.validateNoRowsHaveBothMarkersAndImages(items);
            this.mItemList = itemList;
            return this;
        }

        public Builder setHeader(Header header) {
            this.mHeader = (Header) Objects.requireNonNull(header);
            return this;
        }

        public Builder setMapController(MapController mapController) {
            this.mMapController = (MapController) Objects.requireNonNull(mapController);
            return this;
        }

        public MapTemplate build() {
            boolean z = true;
            boolean z2 = this.mPane == null;
            if (this.mItemList != null) {
                z = false;
            }
            if (z2 != z) {
                return new MapTemplate(this);
            }
            throw new IllegalStateException("Either Pane or Item List must be set but not both");
        }
    }
}
