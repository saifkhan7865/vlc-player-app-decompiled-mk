package org.videolan.vlc.gui.video;

import android.view.MotionEvent;
import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;", "onTouch", "org/videolan/vlc/gui/video/VideoStatsDelegateKt$scrollState$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoStatsDelegate.kt */
public final class VideoStatsDelegate$initPlotView$$inlined$scrollState$1 implements View.OnTouchListener {
    final /* synthetic */ VideoStatsDelegate this$0;

    public VideoStatsDelegate$initPlotView$$inlined$scrollState$1(VideoStatsDelegate videoStatsDelegate, VideoStatsDelegate videoStatsDelegate2) {
        this.this$0 = videoStatsDelegate;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        Function0<Unit> function0;
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        if (action != 8) {
                            return false;
                        }
                    }
                }
            }
            function0 = this.this$0.getIdle();
            function0.invoke();
            return false;
        }
        function0 = this.this$0.getScrolling();
        function0.invoke();
        return false;
    }
}
