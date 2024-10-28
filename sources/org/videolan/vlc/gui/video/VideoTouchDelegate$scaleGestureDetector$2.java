package org.videolan.vlc.gui.video;

import android.view.ScaleGestureDetector;
import androidx.core.view.ScaleGestureDetectorCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/view/ScaleGestureDetector;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTouchDelegate.kt */
final class VideoTouchDelegate$scaleGestureDetector$2 extends Lambda implements Function0<ScaleGestureDetector> {
    final /* synthetic */ VideoTouchDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoTouchDelegate$scaleGestureDetector$2(VideoTouchDelegate videoTouchDelegate) {
        super(0);
        this.this$0 = videoTouchDelegate;
    }

    public final ScaleGestureDetector invoke() {
        ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(this.this$0.player, this.this$0.mScaleListener);
        ScaleGestureDetectorCompat.setQuickScaleEnabled(scaleGestureDetector, false);
        return scaleGestureDetector;
    }
}
