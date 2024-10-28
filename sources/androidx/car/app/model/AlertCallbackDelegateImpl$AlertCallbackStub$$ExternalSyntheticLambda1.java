package androidx.car.app.model;

import androidx.car.app.model.AlertCallbackDelegateImpl;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AlertCallbackDelegateImpl$AlertCallbackStub$$ExternalSyntheticLambda1 implements RemoteUtils.HostCall {
    public final /* synthetic */ AlertCallbackDelegateImpl.AlertCallbackStub f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ AlertCallbackDelegateImpl$AlertCallbackStub$$ExternalSyntheticLambda1(AlertCallbackDelegateImpl.AlertCallbackStub alertCallbackStub, int i) {
        this.f$0 = alertCallbackStub;
        this.f$1 = i;
    }

    public final Object dispatch() {
        return this.f$0.m36lambda$onAlertCancelled$0$androidxcarappmodelAlertCallbackDelegateImpl$AlertCallbackStub(this.f$1);
    }
}
