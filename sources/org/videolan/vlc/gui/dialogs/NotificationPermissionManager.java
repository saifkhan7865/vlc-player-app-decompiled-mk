package org.videolan.vlc.gui.dialogs;

import android.content.SharedPreferences;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/NotificationPermissionManager;", "", "()V", "launchIfNeeded", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationPermissionDialog.kt */
public final class NotificationPermissionManager {
    public static final NotificationPermissionManager INSTANCE = new NotificationPermissionManager();

    private NotificationPermissionManager() {
    }

    public final boolean launchIfNeeded(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        if (Permissions.INSTANCE.canSendNotifications(fragmentActivity) || ((SharedPreferences) Settings.INSTANCE.getInstance(fragmentActivity)).getBoolean(SettingsKt.NOTIFICATION_PERMISSION_ASKED, false)) {
            return false;
        }
        new NotificationPermissionDialog().show(fragmentActivity.getSupportFragmentManager(), "fragment_notification_permission");
        return true;
    }
}
