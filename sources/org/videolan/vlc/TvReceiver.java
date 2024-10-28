package org.videolan.vlc;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.util.TvChannelsKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0017J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/TvReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "scheduleRecommendationUpdate", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvReceiver.kt */
public final class TvReceiver extends BroadcastReceiver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long INITIAL_DELAY = 5000;
    private static final String TAG = "VLC/TvReceiver";

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action != null && AndroidDevices.INSTANCE.isAndroidTv()) {
            Log.d(TAG, "onReceive: " + action);
            switch (action.hashCode()) {
                case -489371415:
                    if (action.equals(TvContractCompat.ACTION_INITIALIZE_PROGRAMS)) {
                        Log.d(TAG, "onReceive: ACTION_INITIALIZE_PROGRAMS ");
                        TvChannelsKt.setChannel(context);
                        return;
                    }
                    return;
                case -160295064:
                    if (action.equals(TvContractCompat.ACTION_WATCH_NEXT_PROGRAM_BROWSABLE_DISABLED)) {
                        long longExtra = intent.getLongExtra(TvContractCompat.EXTRA_PREVIEW_PROGRAM_ID, -1);
                        long longExtra2 = intent.getLongExtra(TvContractCompat.EXTRA_WATCH_NEXT_PROGRAM_ID, -1);
                        Log.d(TAG, "onReceive: ACTION_WATCH_NEXT_PROGRAM_BROWSABLE_DISABLED, " + longExtra + ", " + longExtra2);
                        return;
                    }
                    return;
                case 798292259:
                    if (action.equals("android.intent.action.BOOT_COMPLETED")) {
                        Log.d(TAG, "onReceive: ACTION_BOOT_COMPLETED ");
                        if (!AndroidUtil.isOOrLater) {
                            scheduleRecommendationUpdate(context);
                        } else {
                            TvChannelsKt.launchChannelUpdate(context);
                        }
                        if (AndroidDevices.INSTANCE.getWatchDevices()) {
                            StoragesMonitorKt.enableStorageMonitoring(context);
                            return;
                        }
                        return;
                    }
                    return;
                case 1568780589:
                    if (action.equals(TvContractCompat.ACTION_PREVIEW_PROGRAM_BROWSABLE_DISABLED)) {
                        long longExtra3 = intent.getLongExtra(TvContractCompat.EXTRA_PREVIEW_PROGRAM_ID, -1);
                        long longExtra4 = intent.getLongExtra(TvContractCompat.EXTRA_WATCH_NEXT_PROGRAM_ID, -1);
                        Log.d(TAG, "onReceive: ACTION_PREVIEW_PROGRAM_BROWSABLE_DISABLED, " + longExtra3 + ", " + longExtra4);
                        return;
                    }
                    return;
                case 2011523553:
                    if (action.equals(TvContractCompat.ACTION_PREVIEW_PROGRAM_ADDED_TO_WATCH_NEXT)) {
                        long longExtra5 = intent.getLongExtra(TvContractCompat.EXTRA_PREVIEW_PROGRAM_ID, -1);
                        long longExtra6 = intent.getLongExtra(TvContractCompat.EXTRA_WATCH_NEXT_PROGRAM_ID, -1);
                        Log.d(TAG, "onReceive: ACTION_PREVIEW_PROGRAM_ADDED_TO_WATCH_NEXT" + longExtra5 + ", " + longExtra6);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private final void scheduleRecommendationUpdate(Context context) {
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        Object systemService = ContextCompat.getSystemService(applicationContext, AlarmManager.class);
        Intrinsics.checkNotNull(systemService);
        ((AlarmManager) systemService).setInexactRepeating(2, 5000, 3600000, PendingIntent.getService(context, 0, new Intent(context, RecommendationsService.class), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/TvReceiver$Companion;", "", "()V", "INITIAL_DELAY", "", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: TvReceiver.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
