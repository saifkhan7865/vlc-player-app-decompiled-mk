package androidx.car.app;

import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppManager$1$$ExternalSyntheticLambda0 implements RemoteUtils.HostCall {
    public final /* synthetic */ CarContext f$0;

    public /* synthetic */ AppManager$1$$ExternalSyntheticLambda0(CarContext carContext) {
        this.f$0 = carContext;
    }

    public final Object dispatch() {
        return this.f$0.getOnBackPressedDispatcher().onBackPressed();
    }
}
