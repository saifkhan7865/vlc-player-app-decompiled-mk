package org.videolan.vlc.gui.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"org/videolan/vlc/gui/video/VideoPlayerActivity$serviceReceiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
public final class VideoPlayerActivity$serviceReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ VideoPlayerActivity this$0;

    VideoPlayerActivity$serviceReceiver$1(VideoPlayerActivity videoPlayerActivity) {
        this.this$0 = videoPlayerActivity;
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (Intrinsics.areEqual((Object) intent.getAction(), (Object) Constants.PLAY_FROM_SERVICE)) {
            this.this$0.onNewIntent(intent);
        } else if (Intrinsics.areEqual((Object) intent.getAction(), (Object) Constants.EXIT_PLAYER)) {
            this.this$0.exitOK();
        }
    }
}
