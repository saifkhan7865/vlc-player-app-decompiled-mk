package androidx.car.app.model;

import androidx.car.app.model.SearchCallbackDelegateImpl;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SearchCallbackDelegateImpl$SearchCallbackStub$$ExternalSyntheticLambda1 implements RemoteUtils.HostCall {
    public final /* synthetic */ SearchCallbackDelegateImpl.SearchCallbackStub f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ SearchCallbackDelegateImpl$SearchCallbackStub$$ExternalSyntheticLambda1(SearchCallbackDelegateImpl.SearchCallbackStub searchCallbackStub, String str) {
        this.f$0 = searchCallbackStub;
        this.f$1 = str;
    }

    public final Object dispatch() {
        return this.f$0.m46lambda$onSearchTextChanged$0$androidxcarappmodelSearchCallbackDelegateImpl$SearchCallbackStub(this.f$1);
    }
}
