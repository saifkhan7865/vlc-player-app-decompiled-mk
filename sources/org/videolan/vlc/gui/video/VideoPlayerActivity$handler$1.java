package org.videolan.vlc.gui.video;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/vlc/gui/video/VideoPlayerActivity$handler$1", "Landroid/os/Handler;", "handleMessage", "", "msg", "Landroid/os/Message;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
public final class VideoPlayerActivity$handler$1 extends Handler {
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$handler$1(VideoPlayerActivity videoPlayerActivity, Looper looper) {
        super(looper);
        this.this$0 = videoPlayerActivity;
    }

    public void handleMessage(Message message) {
        Intrinsics.checkNotNullParameter(message, NotificationCompat.CATEGORY_MESSAGE);
        PlaybackService service = this.this$0.getService();
        if (service != null) {
            VideoPlayerActivity videoPlayerActivity = this.this$0;
            switch (message.what) {
                case 1:
                    VideoPlayerOverlayDelegate.hideOverlay$default(videoPlayerActivity.getOverlayDelegate(), false, false, 2, (Object) null);
                    return;
                case 2:
                    videoPlayerActivity.getOverlayDelegate().fadeOutInfo(videoPlayerActivity.getOverlayDelegate().getOverlayInfo());
                    return;
                case 3:
                    videoPlayerActivity.startPlayback();
                    return;
                case 4:
                    videoPlayerActivity.exit(2);
                    return;
                case 5:
                    videoPlayerActivity.setLockBackButton(true);
                    return;
                case 6:
                    if (service.getVideoTracksCount() < 1 && service.getAudioTracksCount() > 0) {
                        Log.i("VLC/VideoPlayerActivity", "No video track, open in audio mode");
                        videoPlayerActivity.switchToAudioMode(true);
                        return;
                    }
                    return;
                case 7:
                    videoPlayerActivity.startLoading();
                    return;
                case 8:
                    VideoPlayerOverlayDelegate.showOverlay$default(videoPlayerActivity.getOverlayDelegate(), false, 1, (Object) null);
                    return;
                case 9:
                    VideoPlayerOverlayDelegate.hideOverlay$default(videoPlayerActivity.getOverlayDelegate(), true, false, 2, (Object) null);
                    return;
                case 10:
                    VideoTouchDelegate.hideSeekOverlay$default(videoPlayerActivity.getTouchDelegate(), false, 1, (Object) null);
                    return;
                case 11:
                    videoPlayerActivity.getDelayDelegate().endPlaybackSetting();
                    return;
                case 12:
                    videoPlayerActivity.getOverlayDelegate().fadeOutInfo(videoPlayerActivity.getOverlayDelegate().getOverlayBrightness());
                    return;
                case 13:
                    videoPlayerActivity.getOverlayDelegate().fadeOutInfo(videoPlayerActivity.getOverlayDelegate().getOverlayVolume());
                    return;
                case 14:
                    videoPlayerActivity.getScreenshotDelegate().hide();
                    return;
                default:
                    return;
            }
        }
    }
}
