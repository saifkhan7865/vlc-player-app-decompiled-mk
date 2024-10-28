package androidx.car.app;

import android.content.Intent;
import android.content.res.Configuration;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CarAppBinder$$ExternalSyntheticLambda4 implements RemoteUtils.HostCall {
    public final /* synthetic */ CarAppBinder f$0;
    public final /* synthetic */ ICarHost f$1;
    public final /* synthetic */ Configuration f$2;
    public final /* synthetic */ Intent f$3;

    public /* synthetic */ CarAppBinder$$ExternalSyntheticLambda4(CarAppBinder carAppBinder, ICarHost iCarHost, Configuration configuration, Intent intent) {
        this.f$0 = carAppBinder;
        this.f$1 = iCarHost;
        this.f$2 = configuration;
        this.f$3 = intent;
    }

    public final Object dispatch() {
        return this.f$0.m10lambda$onAppCreate$0$androidxcarappCarAppBinder(this.f$1, this.f$2, this.f$3);
    }
}
