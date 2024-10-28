package androidx.car.app;

import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppManager$1$$ExternalSyntheticLambda3 implements RemoteUtils.HostCall {
    public final /* synthetic */ CarContext f$0;

    public /* synthetic */ AppManager$1$$ExternalSyntheticLambda3(CarContext carContext) {
        this.f$0 = carContext;
    }

    public final Object dispatch() {
        return ((AppManager) this.f$0.getCarService(AppManager.class)).startLocationUpdates();
    }
}
