package androidx.car.app.model;

import androidx.car.app.model.InputCallbackDelegateImpl;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class InputCallbackDelegateImpl$OnInputCallbackStub$$ExternalSyntheticLambda0 implements RemoteUtils.HostCall {
    public final /* synthetic */ InputCallbackDelegateImpl.OnInputCallbackStub f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ InputCallbackDelegateImpl$OnInputCallbackStub$$ExternalSyntheticLambda0(InputCallbackDelegateImpl.OnInputCallbackStub onInputCallbackStub, String str) {
        this.f$0 = onInputCallbackStub;
        this.f$1 = str;
    }

    public final Object dispatch() {
        return this.f$0.m39lambda$onInputTextChanged$1$androidxcarappmodelInputCallbackDelegateImpl$OnInputCallbackStub(this.f$1);
    }
}
