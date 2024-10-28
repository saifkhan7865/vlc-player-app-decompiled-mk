package androidx.car.app.notification;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.car.app.CarContext;
import androidx.car.app.IStartCarApp;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

public class CarAppNotificationBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "CarApp.NBR";

    public void onReceive(Context context, Intent intent) {
        IBinder iBinder;
        intent.removeExtra("androidx.car.app.notification.COMPONENT_EXTRA_KEY");
        intent.setComponent((ComponentName) intent.getParcelableExtra("androidx.car.app.notification.COMPONENT_EXTRA_KEY"));
        Bundle extras = intent.getExtras();
        if (extras != null) {
            iBinder = extras.getBinder(CarContext.EXTRA_START_CAR_APP_BINDER_KEY);
            extras.remove(CarContext.EXTRA_START_CAR_APP_BINDER_KEY);
        } else {
            iBinder = null;
        }
        if (iBinder == null) {
            Log.e(TAG, "Notification intent missing expected extra: " + intent);
            return;
        }
        RemoteUtils.dispatchCallToHost("startCarApp from notification", new CarAppNotificationBroadcastReceiver$$ExternalSyntheticLambda0((IStartCarApp) Objects.requireNonNull(IStartCarApp.Stub.asInterface(iBinder)), intent));
    }
}
