package androidx.car.app.model.constraints;

import androidx.car.app.messaging.model.ConversationItem;
import androidx.car.app.model.Item;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Pane;
import androidx.car.app.model.Row;
import androidx.car.app.model.SectionedItemList;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;

public final class RowListConstraints {
    public static final RowListConstraints MAP_ROW_LIST_CONSTRAINTS_ALLOW_SELECTABLE;
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_CONSERVATIVE;
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_FULL_LIST;
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_PANE;
    @Deprecated
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_ROUTE_PREVIEW;
    public static final RowListConstraints ROW_LIST_CONSTRAINTS_SIMPLE;
    private final boolean mAllowSelectableLists;
    private final int mMaxActions;
    private final RowConstraints mRowConstraints;

    static {
        RowListConstraints build = new Builder().setMaxActions(0).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_CONSERVATIVE).setAllowSelectableLists(false).build();
        ROW_LIST_CONSTRAINTS_CONSERVATIVE = build;
        ROW_LIST_CONSTRAINTS_PANE = new Builder(build).setMaxActions(2).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_PANE).setAllowSelectableLists(false).build();
        ROW_LIST_CONSTRAINTS_SIMPLE = new Builder(build).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_SIMPLE).build();
        ROW_LIST_CONSTRAINTS_ROUTE_PREVIEW = new Builder(build).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_SIMPLE).setAllowSelectableLists(true).build();
        MAP_ROW_LIST_CONSTRAINTS_ALLOW_SELECTABLE = new Builder(build).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_SIMPLE).setAllowSelectableLists(true).build();
        ROW_LIST_CONSTRAINTS_FULL_LIST = new Builder(build).setRowConstraints(RowConstraints.ROW_CONSTRAINTS_FULL_LIST).setAllowSelectableLists(true).build();
    }

    public int getMaxActions() {
        return this.mMaxActions;
    }

    public RowConstraints getRowConstraints() {
        return this.mRowConstraints;
    }

    public boolean isAllowSelectableLists() {
        return this.mAllowSelectableLists;
    }

    public void validateOrThrow(ItemList itemList) {
        if (itemList.getOnSelectedDelegate() == null || this.mAllowSelectableLists) {
            validateRows(itemList.getItems());
            return;
        }
        throw new IllegalArgumentException("Selectable lists are not allowed");
    }

    public void validateOrThrow(List<SectionedItemList> list) {
        ArrayList arrayList = new ArrayList();
        for (SectionedItemList itemList : list) {
            ItemList itemList2 = itemList.getItemList();
            if (itemList2.getOnSelectedDelegate() == null || this.mAllowSelectableLists) {
                arrayList.addAll(itemList2.getItems());
            } else {
                throw new IllegalArgumentException("Selectable lists are not allowed");
            }
        }
        validateRows(arrayList);
    }

    public void validateOrThrow(Pane pane) {
        if (pane.getActions().size() <= this.mMaxActions) {
            validateRows(pane.getRows());
            return;
        }
        throw new IllegalArgumentException("The number of actions on the pane exceeded the supported max of " + this.mMaxActions);
    }

    private void validateRows(List<? extends Item> list) {
        for (Item item : list) {
            if (item instanceof Row) {
                this.mRowConstraints.validateOrThrow((Row) item);
            } else if (!(item instanceof ConversationItem)) {
                throw new IllegalArgumentException(String.format("Unsupported item type: %s", new Object[]{item.getClass().getSimpleName()}));
            }
        }
    }

    RowListConstraints(Builder builder) {
        this.mMaxActions = builder.mMaxActions;
        this.mRowConstraints = builder.mRowConstraints;
        this.mAllowSelectableLists = builder.mAllowSelectableLists;
    }

    public static final class Builder {
        boolean mAllowSelectableLists;
        int mMaxActions;
        RowConstraints mRowConstraints = RowConstraints.UNCONSTRAINED;

        public Builder setMaxActions(int i) {
            this.mMaxActions = i;
            return this;
        }

        public Builder setRowConstraints(RowConstraints rowConstraints) {
            this.mRowConstraints = rowConstraints;
            return this;
        }

        public Builder setAllowSelectableLists(boolean z) {
            this.mAllowSelectableLists = z;
            return this;
        }

        public RowListConstraints build() {
            return new RowListConstraints(this);
        }

        public Builder() {
        }

        public Builder(RowListConstraints rowListConstraints) {
            Objects.requireNonNull(rowListConstraints);
            this.mMaxActions = rowListConstraints.getMaxActions();
            this.mRowConstraints = rowListConstraints.getRowConstraints();
            this.mAllowSelectableLists = rowListConstraints.isAllowSelectableLists();
        }
    }
}
