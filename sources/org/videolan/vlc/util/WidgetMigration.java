package org.videolan.vlc.util;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.dialogs.WidgetMigrationDialog;
import org.videolan.vlc.widget.VLCAppWidgetProviderBlack;
import org.videolan.vlc.widget.VLCAppWidgetProviderWhite;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/util/WidgetMigration;", "", "()V", "launchIfNeeded", "", "context", "Landroidx/appcompat/app/AppCompatActivity;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetMigration.kt */
public final class WidgetMigration {
    public static final WidgetMigration INSTANCE = new WidgetMigration();

    private WidgetMigration() {
    }

    public final boolean launchIfNeeded(AppCompatActivity appCompatActivity) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "context");
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(appCompatActivity);
        if (!sharedPreferences.getBoolean("widget_migration_key", false)) {
            Context context = appCompatActivity;
            AppWidgetManager instance = AppWidgetManager.getInstance(context);
            if (instance != null) {
                int[] appWidgetIds = instance.getAppWidgetIds(new ComponentName(context, VLCAppWidgetProviderWhite.class));
                Intrinsics.checkNotNullExpressionValue(appWidgetIds, "getAppWidgetIds(...)");
                if (!(!(appWidgetIds.length == 0))) {
                    int[] appWidgetIds2 = instance.getAppWidgetIds(new ComponentName(context, VLCAppWidgetProviderBlack.class));
                    Intrinsics.checkNotNullExpressionValue(appWidgetIds2, "getAppWidgetIds(...)");
                    if (!(!(appWidgetIds2.length == 0))) {
                        PackageManager packageManager = appCompatActivity.getApplication().getPackageManager();
                        Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
                        packageManager.setComponentEnabledSetting(new ComponentName(appCompatActivity.getApplication(), VLCAppWidgetProviderBlack.class), 2, 1);
                        packageManager.setComponentEnabledSetting(new ComponentName(appCompatActivity.getApplication(), VLCAppWidgetProviderWhite.class), 2, 1);
                    }
                }
                new WidgetMigrationDialog().show(appCompatActivity.getSupportFragmentManager(), "fragment_widget_migration");
                return true;
            }
            SettingsKt.putSingle(sharedPreferences, "widget_migration_key", true);
        }
        return false;
    }
}
