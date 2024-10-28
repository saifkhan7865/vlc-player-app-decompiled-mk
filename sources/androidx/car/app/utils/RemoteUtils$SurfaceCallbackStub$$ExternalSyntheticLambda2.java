package androidx.car.app.utils;

import android.graphics.Rect;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda2 implements RemoteUtils.HostCall {
    public final /* synthetic */ RemoteUtils.SurfaceCallbackStub f$0;
    public final /* synthetic */ Rect f$1;

    public /* synthetic */ RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda2(RemoteUtils.SurfaceCallbackStub surfaceCallbackStub, Rect rect) {
        this.f$0 = surfaceCallbackStub;
        this.f$1 = rect;
    }

    public final Object dispatch() {
        return this.f$0.m58lambda$onVisibleAreaChanged$1$androidxcarapputilsRemoteUtils$SurfaceCallbackStub(this.f$1);
    }
}
