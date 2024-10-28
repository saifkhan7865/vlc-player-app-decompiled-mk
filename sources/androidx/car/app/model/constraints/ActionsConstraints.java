package androidx.car.app.model.constraints;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarText;
import j$.util.Objects;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.fusesource.jansi.AnsiRenderer;

public final class ActionsConstraints {
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_BODY;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_BODY_WITH_PRIMARY_ACTION;
    private static final ActionsConstraints ACTIONS_CONSTRAINTS_CONSERVATIVE;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_HEADER;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_MAP;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_MULTI_HEADER = new Builder().setMaxActions(2).setRequireActionIcons(true).setOnClickListenerAllowed(true).build();
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_NAVIGATION;
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_ROW = new Builder().setMaxActions(1).addAllowedActionType(1).setRequireActionIcons(true).setOnClickListenerAllowed(true).build();
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_SIMPLE;
    @RequiresCarApi(6)
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_TABS;
    private final Set<Integer> mAllowedActionTypes;
    private final Set<Integer> mDisallowedActionTypes;
    private final int mMaxActions;
    private final int mMaxCustomTitles;
    private final int mMaxPrimaryActions;
    private final boolean mOnClickListenerAllowed;
    private final boolean mRequireActionIcons;
    private final Set<Integer> mRequiredActionTypes;
    private final CarTextConstraints mTitleTextConstraints;

    static {
        ActionsConstraints build = new Builder().setMaxActions(1).setRequireActionIcons(true).setOnClickListenerAllowed(false).build();
        ACTIONS_CONSTRAINTS_HEADER = build;
        ActionsConstraints build2 = new Builder().setTitleTextConstraints(CarTextConstraints.CONSERVATIVE).setMaxActions(2).build();
        ACTIONS_CONSTRAINTS_CONSERVATIVE = build2;
        ACTIONS_CONSTRAINTS_BODY = new Builder(build2).setTitleTextConstraints(CarTextConstraints.COLOR_ONLY).setMaxCustomTitles(2).setOnClickListenerAllowed(true).build();
        ACTIONS_CONSTRAINTS_BODY_WITH_PRIMARY_ACTION = new Builder(build2).setTitleTextConstraints(CarTextConstraints.COLOR_ONLY).setMaxCustomTitles(2).setMaxPrimaryActions(1).setOnClickListenerAllowed(true).build();
        ACTIONS_CONSTRAINTS_SIMPLE = new Builder(build2).setMaxCustomTitles(1).setTitleTextConstraints(CarTextConstraints.TEXT_ONLY).setOnClickListenerAllowed(true).build();
        ACTIONS_CONSTRAINTS_NAVIGATION = new Builder(build2).setMaxActions(4).setMaxCustomTitles(4).setTitleTextConstraints(CarTextConstraints.TEXT_AND_ICON).setOnClickListenerAllowed(true).build();
        ACTIONS_CONSTRAINTS_MAP = new Builder(build2).setMaxActions(4).setOnClickListenerAllowed(true).build();
        ACTIONS_CONSTRAINTS_TABS = new Builder(build).addRequiredActionType(Action.TYPE_APP_ICON).build();
    }

    ActionsConstraints(Builder builder) {
        int i = builder.mMaxActions;
        this.mMaxActions = i;
        this.mMaxPrimaryActions = builder.mMaxPrimaryActions;
        this.mMaxCustomTitles = builder.mMaxCustomTitles;
        this.mTitleTextConstraints = builder.mTitleTextConstraints;
        this.mRequireActionIcons = builder.mRequireActionIcons;
        this.mOnClickListenerAllowed = builder.mOnClickListenerAllowed;
        HashSet hashSet = new HashSet(builder.mRequiredActionTypes);
        this.mRequiredActionTypes = hashSet;
        HashSet hashSet2 = new HashSet(builder.mAllowedActionTypes);
        this.mAllowedActionTypes = hashSet2;
        HashSet hashSet3 = new HashSet(builder.mDisallowedActionTypes);
        hashSet3.retainAll(hashSet);
        if (!hashSet3.isEmpty()) {
            throw new IllegalArgumentException("Disallowed action types cannot also be in the required set");
        } else if (builder.mDisallowedActionTypes.isEmpty() || hashSet2.isEmpty()) {
            this.mDisallowedActionTypes = new HashSet(builder.mDisallowedActionTypes);
            if (hashSet.size() > i) {
                throw new IllegalArgumentException("Required action types exceeded max allowed actions");
            }
        } else {
            throw new IllegalArgumentException("Both disallowed and allowed action type set cannot be defined.");
        }
    }

    public int getMaxActions() {
        return this.mMaxActions;
    }

    public int getMaxPrimaryActions() {
        return this.mMaxPrimaryActions;
    }

    public int getMaxCustomTitles() {
        return this.mMaxCustomTitles;
    }

    public CarTextConstraints getTitleTextConstraints() {
        return this.mTitleTextConstraints;
    }

    public Set<Integer> getRequiredActionTypes() {
        return this.mRequiredActionTypes;
    }

    public Set<Integer> getDisallowedActionTypes() {
        return this.mDisallowedActionTypes;
    }

    public Set<Integer> getAllowedActionTypes() {
        return this.mAllowedActionTypes;
    }

    public boolean areActionIconsRequired() {
        return this.mRequireActionIcons;
    }

    public boolean isOnClickListenerAllowed() {
        return this.mOnClickListenerAllowed;
    }

    public void validateOrThrow(List<Action> list) {
        Set<Integer> set;
        int i = this.mMaxActions;
        int i2 = this.mMaxPrimaryActions;
        int i3 = this.mMaxCustomTitles;
        if (this.mRequiredActionTypes.isEmpty()) {
            set = Collections.emptySet();
        } else {
            set = new HashSet<>(this.mRequiredActionTypes);
        }
        for (Action next : list) {
            if (!this.mDisallowedActionTypes.isEmpty() && this.mDisallowedActionTypes.contains(Integer.valueOf(next.getType()))) {
                throw new IllegalArgumentException(Action.typeToString(next.getType()) + " is disallowed");
            } else if (this.mAllowedActionTypes.isEmpty() || this.mAllowedActionTypes.contains(Integer.valueOf(next.getType()))) {
                set.remove(Integer.valueOf(next.getType()));
                CarText title = next.getTitle();
                if (title != null && !title.isEmpty()) {
                    i3--;
                    if (i3 >= 0) {
                        this.mTitleTextConstraints.validateOrThrow(title);
                    } else {
                        throw new IllegalArgumentException("Action list exceeded max number of " + this.mMaxCustomTitles + " actions with custom titles");
                    }
                }
                i--;
                if (i < 0) {
                    throw new IllegalArgumentException("Action list exceeded max number of " + this.mMaxActions + " actions");
                } else if ((next.getFlags() & 1) != 0 && i2 - 1 < 0) {
                    throw new IllegalArgumentException("Action list exceeded max number of " + this.mMaxPrimaryActions + " primary actions");
                } else if (this.mRequireActionIcons && next.getIcon() == null && !next.isStandard()) {
                    throw new IllegalArgumentException("Non-standard actions without an icon are disallowed");
                } else if (!this.mOnClickListenerAllowed && next.getOnClickDelegate() != null && !next.isStandard()) {
                    throw new IllegalArgumentException("Setting a click listener for a custom action is disallowed");
                }
            } else {
                throw new IllegalArgumentException(Action.typeToString(next.getType()) + " is not allowed");
            }
        }
        if (!set.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Integer intValue : set) {
                sb.append(Action.typeToString(intValue.intValue()));
                sb.append(AnsiRenderer.CODE_LIST_SEPARATOR);
            }
            throw new IllegalArgumentException("Missing required action types: " + sb);
        }
    }

    public static final class Builder {
        final Set<Integer> mAllowedActionTypes;
        final Set<Integer> mDisallowedActionTypes;
        int mMaxActions;
        int mMaxCustomTitles;
        int mMaxPrimaryActions;
        boolean mOnClickListenerAllowed;
        boolean mRequireActionIcons;
        final Set<Integer> mRequiredActionTypes;
        CarTextConstraints mTitleTextConstraints;

        public Builder() {
            this.mRequiredActionTypes = new HashSet();
            this.mDisallowedActionTypes = new HashSet();
            this.mAllowedActionTypes = new HashSet();
            this.mMaxActions = Integer.MAX_VALUE;
            this.mMaxPrimaryActions = 0;
            this.mTitleTextConstraints = CarTextConstraints.UNCONSTRAINED;
        }

        public Builder(ActionsConstraints actionsConstraints) {
            HashSet hashSet = new HashSet();
            this.mRequiredActionTypes = hashSet;
            HashSet hashSet2 = new HashSet();
            this.mDisallowedActionTypes = hashSet2;
            HashSet hashSet3 = new HashSet();
            this.mAllowedActionTypes = hashSet3;
            this.mMaxActions = Integer.MAX_VALUE;
            this.mMaxPrimaryActions = 0;
            this.mTitleTextConstraints = CarTextConstraints.UNCONSTRAINED;
            Objects.requireNonNull(actionsConstraints);
            this.mMaxActions = actionsConstraints.getMaxActions();
            this.mMaxPrimaryActions = actionsConstraints.getMaxPrimaryActions();
            this.mMaxCustomTitles = actionsConstraints.getMaxCustomTitles();
            this.mTitleTextConstraints = actionsConstraints.getTitleTextConstraints();
            hashSet.addAll(actionsConstraints.getRequiredActionTypes());
            hashSet2.addAll(actionsConstraints.getDisallowedActionTypes());
            hashSet3.addAll(actionsConstraints.getAllowedActionTypes());
            this.mRequireActionIcons = actionsConstraints.areActionIconsRequired();
            this.mOnClickListenerAllowed = actionsConstraints.isOnClickListenerAllowed();
        }

        public Builder setMaxActions(int i) {
            this.mMaxActions = i;
            return this;
        }

        public Builder setRequireActionIcons(boolean z) {
            this.mRequireActionIcons = z;
            return this;
        }

        public Builder setOnClickListenerAllowed(boolean z) {
            this.mOnClickListenerAllowed = z;
            return this;
        }

        public Builder setMaxPrimaryActions(int i) {
            this.mMaxPrimaryActions = i;
            return this;
        }

        public Builder setMaxCustomTitles(int i) {
            this.mMaxCustomTitles = i;
            return this;
        }

        public Builder setTitleTextConstraints(CarTextConstraints carTextConstraints) {
            this.mTitleTextConstraints = carTextConstraints;
            return this;
        }

        public Builder addRequiredActionType(int i) {
            this.mRequiredActionTypes.add(Integer.valueOf(i));
            return this;
        }

        public Builder addDisallowedActionType(int i) {
            this.mDisallowedActionTypes.add(Integer.valueOf(i));
            return this;
        }

        public Builder addAllowedActionType(int i) {
            this.mAllowedActionTypes.add(Integer.valueOf(i));
            return this;
        }

        public ActionsConstraints build() {
            return new ActionsConstraints(this);
        }
    }
}
