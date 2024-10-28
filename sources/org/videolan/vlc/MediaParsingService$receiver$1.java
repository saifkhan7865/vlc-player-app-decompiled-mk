package org.videolan.vlc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0017¨\u0006\b"}, d2 = {"org/videolan/vlc/MediaParsingService$receiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaParsingService.kt */
public final class MediaParsingService$receiver$1 extends BroadcastReceiver {
    final /* synthetic */ MediaParsingService this$0;

    MediaParsingService$receiver$1(MediaParsingService mediaParsingService) {
        this.this$0 = mediaParsingService;
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        SendChannel sendChannel = null;
        if (action != null) {
            int hashCode = action.hashCode();
            if (hashCode != -4454714) {
                if (hashCode == 509385935 && action.equals(Constants.ACTION_PAUSE_SCAN)) {
                    PowerManager.WakeLock access$getWakeLock$p = this.this$0.wakeLock;
                    if (access$getWakeLock$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                        access$getWakeLock$p = null;
                    }
                    if (access$getWakeLock$p.isHeld()) {
                        PowerManager.WakeLock access$getWakeLock$p2 = this.this$0.wakeLock;
                        if (access$getWakeLock$p2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                            access$getWakeLock$p2 = null;
                        }
                        access$getWakeLock$p2.release();
                    }
                    this.this$0.scanPaused = true;
                    Medialibrary access$getMedialibrary$p = this.this$0.medialibrary;
                    if (access$getMedialibrary$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                        access$getMedialibrary$p = null;
                    }
                    access$getMedialibrary$p.pauseBackgroundOperations();
                }
            } else if (action.equals(Constants.ACTION_RESUME_SCAN)) {
                PowerManager.WakeLock access$getWakeLock$p3 = this.this$0.wakeLock;
                if (access$getWakeLock$p3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                    access$getWakeLock$p3 = null;
                }
                if (!access$getWakeLock$p3.isHeld()) {
                    PowerManager.WakeLock access$getWakeLock$p4 = this.this$0.wakeLock;
                    if (access$getWakeLock$p4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                        access$getWakeLock$p4 = null;
                    }
                    access$getWakeLock$p4.acquire();
                }
                Medialibrary access$getMedialibrary$p2 = this.this$0.medialibrary;
                if (access$getMedialibrary$p2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
                    access$getMedialibrary$p2 = null;
                }
                access$getMedialibrary$p2.resumeBackgroundOperations();
                this.this$0.scanPaused = false;
            }
        }
        SendChannel access$getNotificationActor$p = this.this$0.notificationActor;
        if (access$getNotificationActor$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationActor");
        } else {
            sendChannel = access$getNotificationActor$p;
        }
        sendChannel.m1139trySendJP2dKIU(new Show(this.this$0.getLastDone(), this.this$0.getLastScheduled()));
    }
}
