package org.videolan.vlc.gui.video;

import android.widget.SeekBar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.Tools;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"org/videolan/vlc/gui/video/VideoPlayerActivity$seekListener$1", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "onProgressChanged", "", "seekBar", "Landroid/widget/SeekBar;", "progress", "", "fromUser", "", "onStartTrackingTouch", "onStopTrackingTouch", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
public final class VideoPlayerActivity$seekListener$1 implements SeekBar.OnSeekBarChangeListener {
    final /* synthetic */ VideoPlayerActivity this$0;

    VideoPlayerActivity$seekListener$1(VideoPlayerActivity videoPlayerActivity) {
        this.this$0 = videoPlayerActivity;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
        this.this$0.isDragging = true;
        this.this$0.getOverlayDelegate().showOverlayTimeout(-1);
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
        this.this$0.isDragging = false;
        this.this$0.getOverlayDelegate().showOverlay(true);
        this.this$0.seek((long) seekBar.getProgress(), true, false);
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        PlaybackService service;
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
        if (!this.this$0.isFinishing() && z && (service = this.this$0.getService()) != null && service.isSeekable()) {
            VideoPlayerActivity videoPlayerActivity = this.this$0;
            long j = (long) i;
            videoPlayerActivity.seek(j, z, videoPlayerActivity.isDragging);
            PlaybackService service2 = this.this$0.getService();
            if (service2 == null || service2.getLength() != 0) {
                VideoPlayerOverlayDelegate overlayDelegate = this.this$0.getOverlayDelegate();
                String millisToString = Tools.millisToString(j);
                Intrinsics.checkNotNullExpressionValue(millisToString, "millisToString(...)");
                VideoPlayerOverlayDelegate.showInfo$default(overlayDelegate, millisToString, 1000, (String) null, 4, (Object) null);
            }
        }
        if (z) {
            this.this$0.getOverlayDelegate().showOverlay(true);
            this.this$0.getOverlayDelegate().getHudBinding().playerOverlaySeekbar.forceAccessibilityUpdate();
        }
    }
}
