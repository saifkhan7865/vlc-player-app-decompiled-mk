package org.videolan.vlc.gui.video;

import android.view.ScaleGestureDetector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.videolan.libvlc.MediaPlayer;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"org/videolan/vlc/gui/video/VideoTouchDelegate$mScaleListener$1", "Landroid/view/ScaleGestureDetector$SimpleOnScaleGestureListener;", "savedScale", "Lorg/videolan/libvlc/MediaPlayer$ScaleType;", "onScale", "", "detector", "Landroid/view/ScaleGestureDetector;", "onScaleBegin", "onScaleEnd", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTouchDelegate.kt */
public final class VideoTouchDelegate$mScaleListener$1 extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    private MediaPlayer.ScaleType savedScale;
    final /* synthetic */ VideoTouchDelegate this$0;

    VideoTouchDelegate$mScaleListener$1(VideoTouchDelegate videoTouchDelegate) {
        this.this$0 = videoTouchDelegate;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        Intrinsics.checkNotNullParameter(scaleGestureDetector, "detector");
        if ((this.this$0.getTouchControls() & 64) != 64) {
            return false;
        }
        if (this.this$0.getScreenConfig().getXRange() != 0 || this.this$0.player.getFov$vlc_android_release() == 0.0f) {
            return true;
        }
        return false;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        Intrinsics.checkNotNullParameter(scaleGestureDetector, "detector");
        if (this.this$0.player.getFov$vlc_android_release() == 0.0f || this.this$0.player.isLocked() || (this.this$0.getTouchControls() & 64) != 64) {
            return false;
        }
        float scaleFactor = (((float) 1) - scaleGestureDetector.getScaleFactor()) * 80.0f;
        if (!this.this$0.player.updateViewpoint$vlc_android_release(0.0f, 0.0f, scaleFactor)) {
            return false;
        }
        this.this$0.player.setFov$vlc_android_release(RangesKt.coerceIn(this.this$0.player.getFov$vlc_android_release() + scaleFactor, 20.0f, 150.0f));
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        Intrinsics.checkNotNullParameter(scaleGestureDetector, "detector");
        if (this.this$0.player.getFov$vlc_android_release() == 0.0f && !this.this$0.player.isLocked() && (this.this$0.getTouchControls() & 64) == 64 && this.this$0.touchAction != 7) {
            boolean z = scaleGestureDetector.getScaleFactor() > 1.0f;
            if (z && this.this$0.player.getCurrentScaleType() != MediaPlayer.ScaleType.SURFACE_FIT_SCREEN) {
                this.savedScale = this.this$0.player.getCurrentScaleType();
                this.this$0.getResizeDelegate().setVideoScale(MediaPlayer.ScaleType.SURFACE_FIT_SCREEN);
            } else if (!z && this.savedScale != null) {
                VideoPlayerResizeDelegate access$getResizeDelegate = this.this$0.getResizeDelegate();
                MediaPlayer.ScaleType scaleType = this.savedScale;
                Intrinsics.checkNotNull(scaleType);
                access$getResizeDelegate.setVideoScale(scaleType);
                this.savedScale = null;
            } else if (!z && this.this$0.player.getCurrentScaleType() == MediaPlayer.ScaleType.SURFACE_FIT_SCREEN) {
                this.this$0.getResizeDelegate().setVideoScale(MediaPlayer.ScaleType.SURFACE_BEST_FIT);
            }
            this.this$0.setTouchAction(0);
        }
    }
}
