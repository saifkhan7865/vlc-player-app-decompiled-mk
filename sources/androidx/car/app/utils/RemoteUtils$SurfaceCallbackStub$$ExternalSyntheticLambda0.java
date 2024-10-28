package androidx.car.app.utils;

import androidx.car.app.serialization.Bundleable;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda0 implements RemoteUtils.HostCall {
    public final /* synthetic */ RemoteUtils.SurfaceCallbackStub f$0;
    public final /* synthetic */ Bundleable f$1;

    public /* synthetic */ RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda0(RemoteUtils.SurfaceCallbackStub surfaceCallbackStub, Bundleable bundleable) {
        this.f$0 = surfaceCallbackStub;
        this.f$1 = bundleable;
    }

    public final Object dispatch() {
        return this.f$0.m57lambda$onSurfaceDestroyed$3$androidxcarapputilsRemoteUtils$SurfaceCallbackStub(this.f$1);
    }
}
