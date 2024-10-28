package androidx.car.app.model;

import androidx.car.app.model.constraints.CarTextConstraints;
import j$.util.Objects;

public final class SectionedItemList {
    private final CarText mHeader;
    private final ItemList mItemList;

    public static SectionedItemList create(ItemList itemList, CharSequence charSequence) {
        CarText create = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
        CarTextConstraints.TEXT_ONLY.validateOrThrow(create);
        return new SectionedItemList((ItemList) Objects.requireNonNull(itemList), create);
    }

    public ItemList getItemList() {
        return (ItemList) Objects.requireNonNull(this.mItemList);
    }

    public CarText getHeader() {
        return (CarText) Objects.requireNonNull(this.mHeader);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ items: ");
        sb.append(this.mItemList);
        sb.append(", has header: ");
        sb.append(this.mHeader != null);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hash(this.mItemList, this.mHeader);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SectionedItemList)) {
            return false;
        }
        SectionedItemList sectionedItemList = (SectionedItemList) obj;
        if (!Objects.equals(this.mItemList, sectionedItemList.mItemList) || !Objects.equals(this.mHeader, sectionedItemList.mHeader)) {
            return false;
        }
        return true;
    }

    private SectionedItemList(ItemList itemList, CarText carText) {
        this.mItemList = itemList;
        this.mHeader = carText;
    }

    private SectionedItemList() {
        this.mItemList = null;
        this.mHeader = null;
    }
}
