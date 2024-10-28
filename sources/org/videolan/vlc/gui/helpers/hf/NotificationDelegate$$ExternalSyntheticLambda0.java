package org.videolan.vlc.gui.helpers.hf;

import androidx.activity.result.ActivityResultCallback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NotificationDelegate$$ExternalSyntheticLambda0 implements ActivityResultCallback {
    public final /* synthetic */ NotificationDelegate f$0;

    public /* synthetic */ NotificationDelegate$$ExternalSyntheticLambda0(NotificationDelegate notificationDelegate) {
        this.f$0 = notificationDelegate;
    }

    public final void onActivityResult(Object obj) {
        NotificationDelegate.requestPermission$lambda$0(this.f$0, ((Boolean) obj).booleanValue());
    }
}
