package androidx.car.app.model;

import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ActionStrip {
    private final List<Action> mActions;

    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActions);
    }

    public Action getFirstActionOfType(int i) {
        for (Action next : this.mActions) {
            if (next instanceof Action) {
                Action action = next;
                if (action.getType() == i) {
                    return action;
                }
            }
        }
        return null;
    }

    public String toString() {
        return "[action count: " + this.mActions.size() + "]";
    }

    public int hashCode() {
        return Objects.hashCode(this.mActions);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActionStrip)) {
            return false;
        }
        return Objects.equals(this.mActions, ((ActionStrip) obj).mActions);
    }

    ActionStrip(Builder builder) {
        this.mActions = CollectionUtils.unmodifiableCopy(builder.mActions);
    }

    private ActionStrip() {
        this.mActions = Collections.emptyList();
    }

    public static final class Builder {
        final List<Action> mActions = new ArrayList();
        final Set<Integer> mAddedActionTypes = new HashSet();

        public Builder addAction(Action action) {
            Action action2 = (Action) Objects.requireNonNull(action);
            int type = action2.getType();
            if (type != 1 && this.mAddedActionTypes.contains(Integer.valueOf(type))) {
                throw new IllegalArgumentException("Duplicated action types are disallowed: " + action);
            } else if ((1 & action.getFlags()) != 0) {
                throw new IllegalArgumentException("Primary actions are disallowed: " + action);
            } else if (CarColor.DEFAULT.equals(action2.getBackgroundColor())) {
                CarText title = action.getTitle();
                if (title != null) {
                    CarTextConstraints.CONSERVATIVE.validateOrThrow(title);
                }
                this.mAddedActionTypes.add(Integer.valueOf(type));
                this.mActions.add(action);
                return this;
            } else {
                throw new IllegalArgumentException("Action strip actions don't support background colors");
            }
        }

        public ActionStrip build() {
            if (!this.mActions.isEmpty()) {
                return new ActionStrip(this);
            }
            throw new IllegalStateException("Action strip must contain at least one action");
        }
    }
}
