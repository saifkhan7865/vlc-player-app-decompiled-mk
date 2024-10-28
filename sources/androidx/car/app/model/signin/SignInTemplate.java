package androidx.car.app.model.signin;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarText;
import androidx.car.app.model.OnClickDelegate;
import androidx.car.app.model.Template;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiresCarApi(2)
public final class SignInTemplate implements Template {
    private final List<Action> mActionList;
    private final ActionStrip mActionStrip;
    private final CarText mAdditionalText;
    private final Action mHeaderAction;
    private final CarText mInstructions;
    private final boolean mIsLoading;
    private final SignInMethod mSignInMethod;
    private final CarText mTitle;

    public interface SignInMethod {
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public CarText getInstructions() {
        return this.mInstructions;
    }

    public CarText getAdditionalText() {
        return this.mAdditionalText;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActionList);
    }

    public SignInMethod getSignInMethod() {
        return (SignInMethod) Objects.requireNonNull(this.mSignInMethod);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SignInTemplate)) {
            return false;
        }
        SignInTemplate signInTemplate = (SignInTemplate) obj;
        if (this.mIsLoading != signInTemplate.mIsLoading || !Objects.equals(this.mHeaderAction, signInTemplate.mHeaderAction) || !Objects.equals(this.mTitle, signInTemplate.mTitle) || !Objects.equals(this.mInstructions, signInTemplate.mInstructions) || !Objects.equals(this.mAdditionalText, signInTemplate.mAdditionalText) || !Objects.equals(this.mActionStrip, signInTemplate.mActionStrip) || !Objects.equals(this.mActionList, signInTemplate.mActionList) || !Objects.equals(this.mSignInMethod, signInTemplate.mSignInMethod)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsLoading), this.mHeaderAction, this.mTitle, this.mInstructions, this.mAdditionalText, this.mActionStrip, this.mActionList, this.mSignInMethod);
    }

    public String toString() {
        return "SignInTemplate";
    }

    SignInTemplate(Builder builder) {
        this.mIsLoading = builder.mIsLoading;
        this.mHeaderAction = builder.mHeaderAction;
        this.mTitle = builder.mTitle;
        this.mInstructions = builder.mInstructions;
        this.mAdditionalText = builder.mAdditionalText;
        this.mActionStrip = builder.mActionStrip;
        this.mActionList = CollectionUtils.unmodifiableCopy(builder.mActionList);
        this.mSignInMethod = builder.mSignInMethod;
    }

    private SignInTemplate() {
        this.mIsLoading = false;
        this.mHeaderAction = null;
        this.mTitle = null;
        this.mInstructions = null;
        this.mAdditionalText = null;
        this.mActionStrip = null;
        this.mActionList = Collections.emptyList();
        this.mSignInMethod = null;
    }

    @RequiresCarApi(2)
    public static final class Builder {
        List<Action> mActionList = new ArrayList();
        ActionStrip mActionStrip;
        CarText mAdditionalText;
        Action mHeaderAction;
        CarText mInstructions;
        boolean mIsLoading;
        final SignInMethod mSignInMethod;
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

        public Builder setActionStrip(ActionStrip actionStrip) {
            ActionsConstraints.ACTIONS_CONSTRAINTS_SIMPLE.validateOrThrow(((ActionStrip) Objects.requireNonNull(actionStrip)).getActions());
            this.mActionStrip = actionStrip;
            return this;
        }

        public Builder addAction(Action action) {
            Objects.requireNonNull(action);
            if (((OnClickDelegate) Objects.requireNonNull(action.getOnClickDelegate())).isParkedOnly()) {
                this.mActionList.add(action);
                ActionsConstraints.ACTIONS_CONSTRAINTS_BODY.validateOrThrow(this.mActionList);
                return this;
            }
            throw new IllegalArgumentException("The action must use a ParkedOnlyOnClickListener");
        }

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_ONLY.validateOrThrow(this.mTitle);
            return this;
        }

        public Builder setInstructions(CharSequence charSequence) {
            this.mInstructions = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.TEXT_WITH_COLORS.validateOrThrow(this.mInstructions);
            return this;
        }

        public Builder setAdditionalText(CharSequence charSequence) {
            this.mAdditionalText = CarText.create((CharSequence) Objects.requireNonNull(charSequence));
            CarTextConstraints.CLICKABLE_TEXT_ONLY.validateOrThrow(this.mAdditionalText);
            return this;
        }

        public SignInTemplate build() {
            return new SignInTemplate(this);
        }

        public Builder(SignInMethod signInMethod) {
            this.mSignInMethod = (SignInMethod) Objects.requireNonNull(signInMethod);
        }
    }
}
