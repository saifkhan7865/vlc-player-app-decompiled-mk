package androidx.car.app;

import android.content.Intent;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CarAppBinder$$ExternalSyntheticLambda6 implements RemoteUtils.HostCall {
    public final /* synthetic */ CarAppBinder f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ CarAppBinder$$ExternalSyntheticLambda6(CarAppBinder carAppBinder, Intent intent) {
        this.f$0 = carAppBinder;
        this.f$1 = intent;
    }

    public final Object dispatch() {
        return this.f$0.m16lambda$onNewIntent$5$androidxcarappCarAppBinder(this.f$1);
    }
}
