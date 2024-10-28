package androidx.car.app.model;

import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import j$.util.Objects;
import java.util.Collections;

public final class SearchTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Action mHeaderAction;
    private final String mInitialSearchText;
    private final boolean mIsLoading;
    private final ItemList mItemList;
    private final SearchCallbackDelegate mSearchCallbackDelegate;
    private final String mSearchHint;
    private final boolean mShowKeyboardByDefault;

    public interface SearchCallback {

        /* renamed from: androidx.car.app.model.SearchTemplate$SearchCallback$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onSearchSubmitted(SearchCallback _this, String str) {
            }

            public static void $default$onSearchTextChanged(SearchCallback _this, String str) {
            }
        }

        void onSearchSubmitted(String str);

        void onSearchTextChanged(String str);
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

    public String getInitialSearchText() {
        return this.mInitialSearchText;
    }

    public String getSearchHint() {
        return this.mSearchHint;
    }

    public ItemList getItemList() {
        return this.mItemList;
    }

    public SearchCallbackDelegate getSearchCallbackDelegate() {
        return (SearchCallbackDelegate) Objects.requireNonNull(this.mSearchCallbackDelegate);
    }

    public boolean isShowKeyboardByDefault() {
        return this.mShowKeyboardByDefault;
    }

    public String toString() {
        return "SearchTemplate";
    }

    public int hashCode() {
        return Objects.hash(this.mInitialSearchText, Boolean.valueOf(this.mIsLoading), this.mSearchHint, this.mItemList, Boolean.valueOf(this.mShowKeyboardByDefault), this.mHeaderAction, this.mActionStrip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SearchTemplate)) {
            return false;
        }
        SearchTemplate searchTemplate = (SearchTemplate) obj;
        if (this.mIsLoading != searchTemplate.mIsLoading || !Objects.equals(this.mInitialSearchText, searchTemplate.mInitialSearchText) || !Objects.equals(this.mSearchHint, searchTemplate.mSearchHint) || !Objects.equals(this.mItemList, searchTemplate.mItemList) || !Objects.equals(this.mHeaderAction, searchTemplate.mHeaderAction) || !Objects.equals(this.mActionStrip, searchTemplate.mActionStrip) || this.mShowKeyboardByDefault != searchTemplate.mShowKeyboardByDefault) {
            return false;
        }
        return true;
    }

    SearchTemplate(Builder builder) {
        this.mInitialSearchText = builder.mInitialSearchText;
        this.mSearchHint = builder.mSearchHint;
        this.mIsLoading = builder.mIsLoading;
        this.mItemList = builder.mItemList;
        this.mSearchCallbackDelegate = builder.mSearchCallbackDelegate;
        this.mShowKeyboardByDefault = builder.mShowKeyboardByDefault;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionStrip = builder.mActionStrip;
    }

    private SearchTemplate() {
        this.mInitialSearchText = null;
        this.mSearchHint = null;
        this.mIsLoading = false;
        this.mItemList = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mSearchCallbackDelegate = null;
        this.mShowKeyboardByDefault = true;
    }

    public static final class Builder {
        ActionStrip mActionStrip;
        Action mHeaderAction;
        String mInitialSearchText;
        boolean mIsLoading;
        ItemList mItemList;
        final SearchCallbackDelegate mSearchCallbackDelegate;
        String mSearchHint;
        boolean mShowKeyboardByDefault = true;

        public Builder setHeaderAction(Action action) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_HEADER.validateOrThrow(Collections.singletonList((Action) Objects.requireNonNull(action)));
            this.mHeaderAction = action;
            return this;
        }

        public Builder setActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_SIMPLE.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mActionStrip = actionStrip;
            return this;
        }

        public Builder setInitialSearchText(String str) {
            this.mInitialSearchText = (String) Objects.requireNonNull(str);
            return this;
        }

        public Builder setSearchHint(String str) {
            this.mSearchHint = (String) Objects.requireNonNull(str);
            return this;
        }

        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        public Builder setItemList(ItemList itemList) {
            RowListConstraints.ROW_LIST_CONSTRAINTS_SIMPLE.validateOrThrow((ItemList) Objects.requireNonNull(itemList));
            this.mItemList = itemList;
            return this;
        }

        public Builder setShowKeyboardByDefault(boolean z) {
            this.mShowKeyboardByDefault = z;
            return this;
        }

        public SearchTemplate build() {
            if (!this.mIsLoading || this.mItemList == null) {
                return new SearchTemplate(this);
            }
            throw new IllegalArgumentException("Template is in a loading state but a list is set");
        }

        public Builder(SearchCallback searchCallback) {
            this.mSearchCallbackDelegate = SearchCallbackDelegateImpl.create(searchCallback);
        }
    }
}
