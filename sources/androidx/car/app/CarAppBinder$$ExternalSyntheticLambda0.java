package androidx.car.app;

import android.content.res.Configuration;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CarAppBinder$$ExternalSyntheticLambda0 implements RemoteUtils.HostCall {
    public final /* synthetic */ CarAppBinder f$0;
    public final /* synthetic */ Configuration f$1;

    public /* synthetic */ CarAppBinder$$ExternalSyntheticLambda0(CarAppBinder carAppBinder, Configuration configuration) {
        this.f$0 = carAppBinder;
        this.f$1 = configuration;
    }

    public final Object dispatch() {
        return this.f$0.m15lambda$onConfigurationChanged$6$androidxcarappCarAppBinder(this.f$1);
    }
}
