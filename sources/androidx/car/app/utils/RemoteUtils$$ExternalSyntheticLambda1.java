package androidx.car.app.utils;

import androidx.car.app.IOnDoneCallback;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda1 implements RemoteUtils.RemoteCall {
    public final /* synthetic */ IOnDoneCallback f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda1(IOnDoneCallback iOnDoneCallback, Object obj, String str) {
        this.f$0 = iOnDoneCallback;
        this.f$1 = obj;
        this.f$2 = str;
    }

    public final Object call() {
        return RemoteUtils.lambda$sendSuccessResponseToHost$3(this.f$0, this.f$1, this.f$2);
    }
}
