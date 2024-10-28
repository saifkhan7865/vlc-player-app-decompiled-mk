package androidx.car.app.model.constraints;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.Tab;
import java.util.List;

@RequiresCarApi(6)
public class TabsConstraints {
    public static final TabsConstraints DEFAULT = new Builder().setMaxTabs(4).setMinTabs(2).build();
    private static final int MAXIMUM_ALLOWED_TABS = 4;
    private static final int MINIMUM_REQUIRED_TABS = 2;
    private final int mMaxTabs;
    private final int mMinTabs;

    public void validateOrThrow(List<Tab> list) {
        if (list.size() < this.mMinTabs) {
            throw new IllegalArgumentException("Number of tabs set do not meet the minimum requirement of " + this.mMinTabs + " tabs");
        } else if (list.size() <= this.mMaxTabs) {
            int i = 0;
            for (Tab isActive : list) {
                if (isActive.isActive()) {
                    i++;
                }
            }
            if (i == 0) {
                throw new IllegalArgumentException("An active tab is required");
            } else if (i > 1) {
                throw new IllegalArgumentException("Only one active tab is allowed");
            }
        } else {
            throw new IllegalArgumentException("Number of tabs set exceed the maximum allowed size of " + this.mMaxTabs);
        }
    }

    TabsConstraints(Builder builder) {
        this.mMaxTabs = builder.mMaxTabs;
        this.mMinTabs = builder.mMinTabs;
    }

    public static final class Builder {
        int mMaxTabs = Integer.MAX_VALUE;
        int mMinTabs = 0;

        public Builder setMaxTabs(int i) {
            this.mMaxTabs = i;
            return this;
        }

        public Builder setMinTabs(int i) {
            this.mMinTabs = i;
            return this;
        }

        public TabsConstraints build() {
            return new TabsConstraints(this);
        }
    }
}
