package androidx.car.app;

import androidx.car.app.serialization.Bundleable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppManager$$ExternalSyntheticLambda7 implements HostCall {
    public final /* synthetic */ Bundleable f$0;

    public /* synthetic */ AppManager$$ExternalSyntheticLambda7(Bundleable bundleable) {
        this.f$0 = bundleable;
    }

    public final Object dispatch(Object obj) {
        return ((IAppHost) obj).showAlert(this.f$0);
    }
}
