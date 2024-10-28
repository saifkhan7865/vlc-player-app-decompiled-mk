package org.videolan.vlc.util;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.dialogs.WhatsNewDialog;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/util/WhatsNewManager;", "", "()V", "launchIfNeeded", "", "context", "Landroidx/appcompat/app/AppCompatActivity;", "markAsShown", "preferences", "Landroid/content/SharedPreferences;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WhatsNewManager.kt */
public final class WhatsNewManager {
    public static final WhatsNewManager INSTANCE = new WhatsNewManager();

    private WhatsNewManager() {
    }

    public final void launchIfNeeded(AppCompatActivity appCompatActivity) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "context");
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(appCompatActivity);
        if (sharedPreferences.getBoolean(SettingsKt.KEY_SHOW_WHATS_NEW, true) && !Intrinsics.areEqual((Object) sharedPreferences.getString(SettingsKt.KEY_LAST_WHATS_NEW, ""), (Object) "3.6")) {
            markAsShown(sharedPreferences);
            new WhatsNewDialog().show(appCompatActivity.getSupportFragmentManager(), "fragment_whats_new");
        }
    }

    public final void markAsShown(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, Constants.ID_PREFERENCES);
        SettingsKt.putSingle(sharedPreferences, SettingsKt.KEY_LAST_WHATS_NEW, "3.6");
    }
}
