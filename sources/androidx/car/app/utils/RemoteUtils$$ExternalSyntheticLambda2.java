package androidx.car.app.utils;

import androidx.car.app.IOnDoneCallback;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda2 implements RemoteUtils.RemoteCall {
    public final /* synthetic */ IOnDoneCallback f$0;
    public final /* synthetic */ Throwable f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda2(IOnDoneCallback iOnDoneCallback, Throwable th, String str) {
        this.f$0 = iOnDoneCallback;
        this.f$1 = th;
        this.f$2 = str;
    }

    public final Object call() {
        return RemoteUtils.lambda$sendFailureResponseToHost$4(this.f$0, this.f$1, this.f$2);
    }
}
