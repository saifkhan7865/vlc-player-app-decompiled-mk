package androidx.car.app;

import android.location.Location;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppManager$$ExternalSyntheticLambda4 implements HostCall {
    public final /* synthetic */ Location f$0;

    public /* synthetic */ AppManager$$ExternalSyntheticLambda4(Location location) {
        this.f$0 = location;
    }

    public final Object dispatch(Object obj) {
        return ((IAppHost) obj).sendLocation(this.f$0);
    }
}
