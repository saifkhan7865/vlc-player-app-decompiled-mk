package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiresCarApi(2)
public final class LongMessageTemplate implements Template {
    private final List<Action> mActionList;
    private final ActionStrip mActionStrip;
    private final Action mHeaderAction;
    private final CarText mMessage;
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

    public CarText getMessage() {
        return (CarText) Objects.requireNonNull(this.mMessage);
    }

    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActionList);
    }

    public String toString() {
        return "LongMessageTemplate";
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, this.mMessage, this.mHeaderAction, this.mActionList, this.mActionStrip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LongMessageTemplate)) {
            return false;
        }
        LongMessageTemplate longMessageTemplate = (LongMessageTemplate) obj;
        if (!Objects.equals(this.mTitle, longMessageTemplate.mTitle) || !Objects.equals(this.mMessage, longMessageTemplate.mMessage) || !Objects.equals(this.mHeaderAction, longMessageTemplate.mHeaderAction) || !Objects.equals(this.mActionList, longMessageTemplate.mActionList) || !Objects.equals(this.mActionStrip, longMessageTemplate.mActionStrip)) {
            return false;
        }
        return true;
    }

    LongMessageTemplate(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mMessage = builder.mMessage;
        this.mActionStrip = builder.mActionStrip;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionList = CollectionUtils.unmodifiableCopy(builder.mActionList);
    }

    private LongMessageTemplate() {
        this.mTitle = null;
        this.mMessage = null;
        this.mActionStrip = null;
        this.mHeaderAction = null;
        this.mActionList = Collections.emptyList();
    }

    @RequiresCarApi(2)
    public static final class Builder {
        List<Action> mActionList = new ArrayList();
        ActionStrip mActionStrip;
        Action mHeaderAction;
        final CarText mMessage;
        CarText mTitle;

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mTitle);
            return this;
        }

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

        public Builder addAction(Action action) {
            Objects.requireNonNull(action);
            if (((OnClickDelegate) Objects.requireNonNull(action.getOnClickDelegate())).isParkedOnly()) {
                this.mActionList.add(action);
                ActionsConstraints.ACTIONS_CONSTRAINTS_BODY_WITH_PRIMARY_ACTION.validateOrThrow(this.mActionList);
                return this;
            }
            throw new IllegalArgumentException("The action must use a ParkedOnlyOnClickListener");
        }

        public LongMessageTemplate build() {
            if (!this.mMessage.isEmpty()) {
                return new LongMessageTemplate(this);
            }
            throw new IllegalStateException("Message cannot be empty");
        }

        public Builder(CharSequence charSequence) {
            this.mMessage = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
        }
    }
}
