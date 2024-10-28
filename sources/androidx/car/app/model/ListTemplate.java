package androidx.car.app.model;

import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ListTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final List<SectionedItemList> mSectionedLists;
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

    public List<SectionedItemList> getSectionedLists() {
        return CollectionUtils.emptyIfNull(this.mSectionedLists);
    }

    public String toString() {
        return "ListTemplate";
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsLoading), this.mTitle, this.mHeaderAction, this.mSingleList, this.mSectionedLists, this.mActionStrip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListTemplate)) {
            return false;
        }
        ListTemplate listTemplate = (ListTemplate) obj;
        if (this.mIsLoading != listTemplate.mIsLoading || !Objects.equals(this.mTitle, listTemplate.mTitle) || !Objects.equals(this.mHeaderAction, listTemplate.mHeaderAction) || !Objects.equals(this.mSingleList, listTemplate.mSingleList) || !Objects.equals(this.mSectionedLists, listTemplate.mSectionedLists) || !Objects.equals(this.mActionStrip, listTemplate.mActionStrip)) {
            return false;
        }
        return true;
    }

    ListTemplate(Builder builder) {
        this.mIsLoading = builder.mIsLoading;
        this.mTitle = builder.mTitle;
        this.mHeaderAction = builder.mHeaderAction;
        this.mSingleList = builder.mSingleList;
        this.mSectionedLists = CollectionUtils.unmodifiableCopy(builder.mSectionedLists);
        this.mActionStrip = builder.mActionStrip;
    }

    private ListTemplate() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mHeaderAction = null;
        this.mSingleList = null;
        this.mSectionedLists = Collections.emptyList();
        this.mActionStrip = null;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        ActionStrip mActionStrip;
        boolean mHasSelectableList;
        Action mHeaderAction;
        boolean mIsLoading;
        final List<SectionedItemList> mSectionedLists;
        ItemList mSingleList;
        CarText mTitle;

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

        public Builder setSingleList(ItemList itemList) {
            this.mSingleList = (ItemList) Objects.requireNonNull(itemList);
            this.mSectionedLists.clear();
            this.mHasSelectableList = false;
            return this;
        }

        public Builder addSectionedList(SectionedItemList sectionedItemList) {
            if (((SectionedItemList) Objects.requireNonNull(sectionedItemList)).getHeader().toString().length() != 0) {
                ItemList itemList = sectionedItemList.getItemList();
                boolean z = itemList.getOnSelectedDelegate() != null;
                if (this.mHasSelectableList || (z && !this.mSectionedLists.isEmpty())) {
                    throw new IllegalArgumentException("A selectable list cannot be added alongside any other lists");
                }
                this.mHasSelectableList = z;
                if (itemList.getItems().isEmpty()) {
                    throw new IllegalArgumentException("List cannot be empty");
                } else if (itemList.getOnItemVisibilityChangedDelegate() == null) {
                    this.mSingleList = null;
                    this.mSectionedLists.add(sectionedItemList);
                    return this;
                } else {
                    throw new IllegalArgumentException("OnItemVisibilityChangedListener in the list is disallowed");
                }
            } else {
                throw new IllegalArgumentException("Header cannot be empty");
            }
        }

        public Builder clearSectionedLists() {
            this.mSectionedLists.clear();
            return this;
        }

        public Builder setActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_SIMPLE.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mActionStrip = actionStrip;
            return this;
        }

        public ListTemplate build() {
            boolean z = this.mSingleList != null || !this.mSectionedLists.isEmpty();
            if (this.mIsLoading != z) {
                if (z) {
                    if (!this.mSectionedLists.isEmpty()) {
                        RowListConstraints.ROW_LIST_CONSTRAINTS_FULL_LIST.validateOrThrow(this.mSectionedLists);
                    } else if (this.mSingleList != null) {
                        RowListConstraints.ROW_LIST_CONSTRAINTS_FULL_LIST.validateOrThrow(this.mSingleList);
                    }
                }
                return new ListTemplate(this);
            }
            throw new IllegalStateException("Template is in a loading state but lists are added, or vice versa");
        }

        public Builder() {
            this.mSectionedLists = new ArrayList();
        }

        Builder(ListTemplate listTemplate) {
            this.mIsLoading = listTemplate.isLoading();
            this.mHeaderAction = listTemplate.getHeaderAction();
            this.mTitle = listTemplate.getTitle();
            this.mSingleList = listTemplate.getSingleList();
            this.mSectionedLists = new ArrayList(listTemplate.getSectionedLists());
            this.mActionStrip = listTemplate.getActionStrip();
        }
    }
}
