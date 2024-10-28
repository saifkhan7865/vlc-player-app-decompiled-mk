package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiresCarApi(5)
public final class Header {
    private final List<Action> mEndHeaderActions;
    private final Action mStartHeaderAction;
    private final CarText mTitle;

    Header(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mStartHeaderAction = builder.mStartHeaderAction;
        this.mEndHeaderActions = CollectionUtils.unmodifiableCopy(builder.mEndHeaderActions);
    }

    private Header() {
        this.mTitle = null;
        this.mStartHeaderAction = null;
        this.mEndHeaderActions = new ArrayList();
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public List<Action> getEndHeaderActions() {
        return this.mEndHeaderActions;
    }

    public Action getStartHeaderAction() {
        return this.mStartHeaderAction;
    }

    public String toString() {
        return "Header: " + this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, this.mEndHeaderActions, this.mStartHeaderAction);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Header)) {
            return false;
        }
        Header header = (Header) obj;
        if (!Objects.equals(this.mTitle, header.mTitle) || !Objects.equals(this.mEndHeaderActions, header.mEndHeaderActions) || !Objects.equals(this.mStartHeaderAction, header.mStartHeaderAction)) {
            return false;
        }
        return true;
    }

    public static final class Builder {
        final List<Action> mEndHeaderActions = new ArrayList();
        Action mStartHeaderAction;
        CarText mTitle;

        public Builder addEndHeaderAction(Action action) {
            this.mEndHeaderActions.add((Action) Objects.requireNonNull(action));
            return this;
        }

        public Builder setStartHeaderAction(Action action) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_HEADER.validateOrThrow(Collections.singletonList((Action) Objects.requireNonNull(action)));
            this.mStartHeaderAction = action;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            return setTitle(CarText.create(charSequence));
        }

        public Builder setTitle(CarText carText) {
            this.mTitle = (CarText) Objects.requireNonNull(carText);
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mTitle);
            return this;
        }

        public Header build() {
            ActionsConstraints.ACTIONS_CONSTRAINTS_MULTI_HEADER.validateOrThrow(this.mEndHeaderActions);
            if (!CarText.isNullOrEmpty(this.mTitle) || this.mStartHeaderAction != null) {
                return new Header(this);
            }
            throw new IllegalStateException("Either the title or start header action must be set");
        }
    }
}
