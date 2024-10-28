package androidx.car.app.constraints;

import androidx.car.app.HostCall;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConstraintManager$$ExternalSyntheticLambda1 implements HostCall {
    public final /* synthetic */ int f$0;

    public /* synthetic */ ConstraintManager$$ExternalSyntheticLambda1(int i) {
        this.f$0 = i;
    }

    public final Object dispatch(Object obj) {
        return Integer.valueOf(((IConstraintHost) obj).getContentLimit(this.f$0));
    }
}
