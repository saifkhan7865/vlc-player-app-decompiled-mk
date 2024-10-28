package androidx.car.app.utils;

import androidx.car.app.utils.RemoteUtils;
import androidx.lifecycle.Lifecycle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Lifecycle f$0;
    public final /* synthetic */ RemoteUtils.HostCall f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda0(Lifecycle lifecycle, RemoteUtils.HostCall hostCall, String str) {
        this.f$0 = lifecycle;
        this.f$1 = hostCall;
        this.f$2 = str;
    }

    public final void run() {
        RemoteUtils.lambda$dispatchCallFromHost$2(this.f$0, this.f$1, this.f$2);
    }
}
