package androidx.car.app.navigation;

import androidx.car.app.HostCall;
import androidx.car.app.serialization.Bundleable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NavigationManager$$ExternalSyntheticLambda0 implements HostCall {
    public final /* synthetic */ Bundleable f$0;

    public /* synthetic */ NavigationManager$$ExternalSyntheticLambda0(Bundleable bundleable) {
        this.f$0 = bundleable;
    }

    public final Object dispatch(Object obj) {
        return ((INavigationHost) obj).updateTrip(this.f$0);
    }
}
