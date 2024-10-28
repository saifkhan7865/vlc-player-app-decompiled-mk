package org.videolan.vlc.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.resources.Constants;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.StartActivity;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\b&\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH$J\b\u0010\u000e\u001a\u00020\fH$J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J \u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016¨\u0006\u001a"}, d2 = {"Lorg/videolan/vlc/widget/VLCAppWidgetProvider;", "Landroid/appwidget/AppWidgetProvider;", "()V", "applyUpdate", "", "context", "Landroid/content/Context;", "views", "Landroid/widget/RemoteViews;", "partial", "", "getPlayPauseImage", "", "isPlaying", "getlayout", "onDisabled", "onEnabled", "onReceive", "intent", "Landroid/content/Intent;", "onUpdate", "appWidgetManager", "Landroid/appwidget/AppWidgetManager;", "appWidgetIds", "", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCAppWidgetProvider.kt */
public abstract class VLCAppWidgetProvider extends AppWidgetProvider {
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_DISABLED;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_ENABLED;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_INIT;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_PREFIX;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_UPDATE;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_UPDATE_COVER;
    /* access modifiers changed from: private */
    public static final String ACTION_WIDGET_UPDATE_POSITION;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/VLCAppWidgetProvider";

    /* access modifiers changed from: protected */
    public abstract int getPlayPauseImage(boolean z);

    /* access modifiers changed from: protected */
    public abstract int getlayout();

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appWidgetManager, "appWidgetManager");
        Intrinsics.checkNotNullParameter(iArr, "appWidgetIds");
        super.onUpdate(context, appWidgetManager, iArr);
        String str = ACTION_WIDGET_INIT;
        onReceive(context, new Intent(str));
        context.sendBroadcast(new Intent(str).setPackage("org.videolan.vlc"));
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action == null || !StringsKt.startsWith$default(action, ACTION_WIDGET_PREFIX, false, 2, (Object) null)) {
            super.onReceive(context, intent);
            return;
        }
        RemoteViews remoteViews = new RemoteViews("org.videolan.vlc", getlayout());
        remoteViews.setOnClickPendingIntent(R.id.widget_container, PendingIntent.getActivity(context, 0, new Intent(context.getApplicationContext(), StartActivity.class), 201326592));
        applyUpdate(context, remoteViews, !Intrinsics.areEqual((Object) ACTION_WIDGET_INIT, (Object) action));
    }

    private final void applyUpdate(Context context, RemoteViews remoteViews, boolean z) {
        ComponentName componentName = new ComponentName(context, getClass());
        AppWidgetManager instance = AppWidgetManager.getInstance(context);
        if (z) {
            instance.partiallyUpdateAppWidget(instance.getAppWidgetIds(componentName), remoteViews);
        } else {
            instance.updateAppWidget(componentName, remoteViews);
        }
    }

    public void onEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onEnabled(context);
        context.sendBroadcast(new Intent(ACTION_WIDGET_ENABLED, (Uri) null, context.getApplicationContext(), PlaybackService.class));
    }

    public void onDisabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onDisabled(context);
        context.sendBroadcast(new Intent(ACTION_WIDGET_DISABLED, (Uri) null, context.getApplicationContext(), PlaybackService.class));
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/widget/VLCAppWidgetProvider$Companion;", "", "()V", "ACTION_WIDGET_DISABLED", "", "getACTION_WIDGET_DISABLED", "()Ljava/lang/String;", "ACTION_WIDGET_ENABLED", "getACTION_WIDGET_ENABLED", "ACTION_WIDGET_INIT", "getACTION_WIDGET_INIT", "ACTION_WIDGET_PREFIX", "getACTION_WIDGET_PREFIX", "ACTION_WIDGET_UPDATE", "getACTION_WIDGET_UPDATE", "ACTION_WIDGET_UPDATE_COVER", "getACTION_WIDGET_UPDATE_COVER", "ACTION_WIDGET_UPDATE_POSITION", "getACTION_WIDGET_UPDATE_POSITION", "TAG", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VLCAppWidgetProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getACTION_WIDGET_PREFIX() {
            return VLCAppWidgetProvider.ACTION_WIDGET_PREFIX;
        }

        public final String getACTION_WIDGET_INIT() {
            return VLCAppWidgetProvider.ACTION_WIDGET_INIT;
        }

        public final String getACTION_WIDGET_UPDATE() {
            return VLCAppWidgetProvider.ACTION_WIDGET_UPDATE;
        }

        public final String getACTION_WIDGET_UPDATE_COVER() {
            return VLCAppWidgetProvider.ACTION_WIDGET_UPDATE_COVER;
        }

        public final String getACTION_WIDGET_UPDATE_POSITION() {
            return VLCAppWidgetProvider.ACTION_WIDGET_UPDATE_POSITION;
        }

        public final String getACTION_WIDGET_ENABLED() {
            return VLCAppWidgetProvider.ACTION_WIDGET_ENABLED;
        }

        public final String getACTION_WIDGET_DISABLED() {
            return VLCAppWidgetProvider.ACTION_WIDGET_DISABLED;
        }
    }

    static {
        String buildPkgString = Constants.buildPkgString("widget.");
        ACTION_WIDGET_PREFIX = buildPkgString;
        ACTION_WIDGET_INIT = buildPkgString + "INIT";
        ACTION_WIDGET_UPDATE = buildPkgString + "UPDATE";
        ACTION_WIDGET_UPDATE_COVER = buildPkgString + "UPDATE_COVER";
        ACTION_WIDGET_UPDATE_POSITION = buildPkgString + "UPDATE_POSITION";
        ACTION_WIDGET_ENABLED = buildPkgString + "ENABLED";
        ACTION_WIDGET_DISABLED = buildPkgString + "DISABLED";
    }
}
