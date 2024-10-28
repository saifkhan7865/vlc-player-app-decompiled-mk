package androidx.car.app.notification;

import android.content.Intent;
import androidx.car.app.IStartCarApp;
import androidx.car.app.utils.RemoteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CarAppNotificationBroadcastReceiver$$ExternalSyntheticLambda0 implements RemoteUtils.RemoteCall {
    public final /* synthetic */ IStartCarApp f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ CarAppNotificationBroadcastReceiver$$ExternalSyntheticLambda0(IStartCarApp iStartCarApp, Intent intent) {
        this.f$0 = iStartCarApp;
        this.f$1 = intent;
    }

    public final Object call() {
        return this.f$0.startCarApp(this.f$1);
    }
}
