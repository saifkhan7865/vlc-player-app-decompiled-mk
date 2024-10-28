package androidx.car.app.utils;

import androidx.car.app.IOnDoneCallback;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ IOnDoneCallback f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ RemoteUtils.HostCall f$2;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda3(IOnDoneCallback iOnDoneCallback, String str, RemoteUtils.HostCall hostCall) {
        this.f$0 = iOnDoneCallback;
        this.f$1 = str;
        this.f$2 = hostCall;
    }

    public final void run() {
        RemoteUtils.lambda$dispatchCallFromHost$0(this.f$0, this.f$1, this.f$2);
    }
}
