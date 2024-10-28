package org.videolan.vlc.gui.video;

import android.view.MotionEvent;
import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;", "onTouch"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: VideoStatsDelegate.kt */
public final class VideoStatsDelegateKt$scrollState$1 implements View.OnTouchListener {
    final /* synthetic */ Function0<Unit> $idle;
    final /* synthetic */ Function0<Unit> $scrolling;

    public VideoStatsDelegateKt$scrollState$1(Function0<Unit> function0, Function0<Unit> function02) {
        this.$scrolling = function0;
        this.$idle = function02;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
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
            this.$idle.invoke();
            return false;
        }
        this.$scrolling.invoke();
        return false;
    }
}
