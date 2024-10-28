package androidx.car.app.model;

import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.model.constraints.RowListConstraints;
import j$.util.Objects;
import java.util.Collections;

public final class PaneTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Action mHeaderAction;
    private final Pane mPane;
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

    public Pane getPane() {
        return (Pane) Objects.requireNonNull(this.mPane);
    }

    public String toString() {
        return "PaneTemplate";
    }

    public int hashCode() {
        return Objects.hash(this.mTitle, this.mPane, this.mHeaderAction, this.mActionStrip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PaneTemplate)) {
            return false;
        }
        PaneTemplate paneTemplate = (PaneTemplate) obj;
        if (!Objects.equals(this.mTitle, paneTemplate.mTitle) || !Objects.equals(this.mPane, paneTemplate.mPane) || !Objects.equals(this.mHeaderAction, paneTemplate.mHeaderAction) || !Objects.equals(this.mActionStrip, paneTemplate.mActionStrip)) {
            return false;
        }
        return true;
    }

    PaneTemplate(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mPane = builder.mPane;
        this.mHeaderAction = builder.mHeaderAction;
        this.mActionStrip = builder.mActionStrip;
    }

    private PaneTemplate() {
        this.mTitle = null;
        this.mPane = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
    }

    public static final class Builder {
        ActionStrip mActionStrip;
        Action mHeaderAction;
        Pane mPane;
        CarText mTitle;

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_AND_ICON.validateOrThrow(this.mTitle);
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

        public PaneTemplate build() {
            RowListConstraints.ROW_LIST_CONSTRAINTS_PANE.validateOrThrow(this.mPane);
            ActionsConstraints.ACTIONS_CONSTRAINTS_BODY_WITH_PRIMARY_ACTION.validateOrThrow(this.mPane.getActions());
            return new PaneTemplate(this);
        }

        public Builder(Pane pane) {
            this.mPane = (Pane) Objects.requireNonNull(pane);
        }
    }
}
