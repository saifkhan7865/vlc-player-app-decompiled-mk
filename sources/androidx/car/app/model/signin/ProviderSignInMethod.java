package androidx.car.app.model.signin;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.Action;
import androidx.car.app.model.OnClickDelegate;
import androidx.car.app.model.signin.SignInTemplate;
import j$.util.Objects;

@RequiresCarApi(2)
public final class ProviderSignInMethod implements SignInTemplate.SignInMethod {
    private final Action mAction;

    public ProviderSignInMethod(Action action) {
        if (((Action) Objects.requireNonNull(action)).getType() != 1) {
            throw new IllegalArgumentException("The action must not be a standard action");
        } else if (((OnClickDelegate) Objects.requireNonNull(action.getOnClickDelegate())).isParkedOnly()) {
            this.mAction = action;
        } else {
            throw new IllegalArgumentException("The action must use a ParkedOnlyOnClickListener");
        }
    }

    public Action getAction() {
        return (Action) Objects.requireNonNull(this.mAction);
    }

    public String toString() {
        return "[action:" + this.mAction + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProviderSignInMethod)) {
            return false;
        }
        return Objects.equals(this.mAction, ((ProviderSignInMethod) obj).mAction);
    }

    public int hashCode() {
        return Objects.hash(this.mAction);
    }

    private ProviderSignInMethod() {
        this.mAction = null;
    }
}
