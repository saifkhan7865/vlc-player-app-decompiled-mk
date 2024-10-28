package androidx.car.app.utils;

import androidx.car.app.IOnDoneCallback;
import androidx.car.app.utils.RemoteUtils;
import androidx.lifecycle.Lifecycle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ Lifecycle f$0;
    public final /* synthetic */ IOnDoneCallback f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ RemoteUtils.HostCall f$3;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda4(Lifecycle lifecycle, IOnDoneCallback iOnDoneCallback, String str, RemoteUtils.HostCall hostCall) {
        this.f$0 = lifecycle;
        this.f$1 = iOnDoneCallback;
        this.f$2 = str;
        this.f$3 = hostCall;
    }

    public final void run() {
        RemoteUtils.lambda$dispatchCallFromHost$1(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
