package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.TabsConstraints;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiresCarApi(6)
public class TabTemplate implements Template {
    private final Action mHeaderAction;
    private final boolean mIsLoading;
    private final TabCallbackDelegate mTabCallbackDelegate;
    private final TabContents mTabContents;
    private final List<Tab> mTabs;

    public interface TabCallback {

        /* renamed from: androidx.car.app.model.TabTemplate$TabCallback$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onTabSelected(TabCallback _this, String str) {
            }
        }

        void onTabSelected(String str);
    }

    public Action getHeaderAction() {
        return (Action) Objects.requireNonNull(this.mHeaderAction);
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public List<Tab> getTabs() {
        return CollectionUtils.emptyIfNull(this.mTabs);
    }

    public TabContents getTabContents() {
        return (TabContents) Objects.requireNonNull(this.mTabContents);
    }

    public TabCallbackDelegate getTabCallbackDelegate() {
        return (TabCallbackDelegate) Objects.requireNonNull(this.mTabCallbackDelegate);
    }

    public String toString() {
        return "TabTemplate";
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsLoading), this.mHeaderAction, this.mTabs, this.mTabContents);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TabTemplate)) {
            return false;
        }
        TabTemplate tabTemplate = (TabTemplate) obj;
        if (this.mIsLoading != tabTemplate.mIsLoading || !Objects.equals(this.mHeaderAction, tabTemplate.mHeaderAction) || !Objects.equals(this.mTabs, tabTemplate.mTabs) || !Objects.equals(this.mTabContents, tabTemplate.mTabContents)) {
            return false;
        }
        return true;
    }

    TabTemplate(Builder builder) {
        this.mIsLoading = builder.mIsLoading;
        this.mHeaderAction = builder.mHeaderAction;
        this.mTabs = CollectionUtils.unmodifiableCopy(builder.mTabs);
        this.mTabContents = builder.mTabContents;
        this.mTabCallbackDelegate = builder.mTabCallbackDelegate;
    }

    private TabTemplate() {
        this.mIsLoading = false;
        this.mHeaderAction = null;
        this.mTabs = Collections.emptyList();
        this.mTabContents = null;
        this.mTabCallbackDelegate = null;
    }

    public static final class Builder {
        Action mHeaderAction;
        boolean mIsLoading;
        final TabCallbackDelegate mTabCallbackDelegate;
        TabContents mTabContents;
        final List<Tab> mTabs = new ArrayList();

        public Builder setLoading(boolean z) {
            this.mIsLoading = z;
            return this;
        }

        public Builder setHeaderAction(Action action) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_TABS.validateOrThrow(Collections.singletonList((Action) Objects.requireNonNull(action)));
            this.mHeaderAction = action;
            return this;
        }

        public Builder setTabContents(TabContents tabContents) {
            this.mTabContents = (TabContents) Objects.requireNonNull(tabContents);
            return this;
        }

        public Builder addTab(Tab tab) {
            Objects.requireNonNull(tab);
            this.mTabs.add(tab);
            return this;
        }

        public TabTemplate build() {
            boolean z = this.mTabContents != null && !this.mTabs.isEmpty();
            boolean z2 = this.mIsLoading;
            if (z2 && z) {
                throw new IllegalStateException("Template is in a loading state but tabs are added");
            } else if (z2 || z) {
                if (z) {
                    TabsConstraints.DEFAULT.validateOrThrow(this.mTabs);
                }
                if (this.mIsLoading || this.mHeaderAction != null) {
                    return new TabTemplate(this);
                }
                throw new IllegalArgumentException("Template requires a Header Action of TYPE_APP_ICON when not in Loading state");
            } else {
                throw new IllegalStateException("Template is not in a loading state but does not contain tabs or tab contents");
            }
        }

        public Builder(TabCallback tabCallback) {
            this.mTabCallbackDelegate = TabCallbackDelegateImpl.create((TabCallback) Objects.requireNonNull(tabCallback));
        }
    }
}
