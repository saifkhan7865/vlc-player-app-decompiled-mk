package org.videolan.vlc.gui.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"org/videolan/vlc/gui/video/VideoPlayerActivity$btReceiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
public final class VideoPlayerActivity$btReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ VideoPlayerActivity this$0;

    VideoPlayerActivity$btReceiver$1(VideoPlayerActivity videoPlayerActivity) {
        this.this$0 = videoPlayerActivity;
    }

    public void onReceive(Context context, Intent intent) {
        PlaybackService service;
        Intrinsics.checkNotNullParameter(context, "context");
        if (intent != null && (service = this.this$0.getService()) != null) {
            VideoPlayerActivity videoPlayerActivity = this.this$0;
            String action = intent.getAction();
            if (action != null) {
                int hashCode = action.hashCode();
                if (hashCode != 545516589) {
                    if (hashCode != 1244161670 || !action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                        return;
                    }
                } else if (!action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                    return;
                }
                long j = videoPlayerActivity.getSettings().getLong(VideoPlayerActivity.KEY_BLUETOOTH_DELAY, 0);
                long audioDelay = service.getAudioDelay();
                if (j != 0) {
                    boolean z = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1) == 2;
                    if (z && audioDelay == 0) {
                        videoPlayerActivity.toggleBtDelay(true);
                    } else if (!z && j == audioDelay) {
                        videoPlayerActivity.toggleBtDelay(false);
                    }
                }
            }
        }
    }
}
