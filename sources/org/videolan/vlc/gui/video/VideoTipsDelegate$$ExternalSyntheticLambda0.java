package org.videolan.vlc.gui.video;

import android.animation.ValueAnimator;
import android.widget.TextView;
import org.videolan.vlc.gui.view.PlayerProgress;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoTipsDelegate$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PlayerProgress f$0;
    public final /* synthetic */ TextView f$1;

    public /* synthetic */ VideoTipsDelegate$$ExternalSyntheticLambda0(PlayerProgress playerProgress, TextView textView) {
        this.f$0 = playerProgress;
        this.f$1 = textView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        VideoTipsDelegate.swipe$lambda$5(this.f$0, this.f$1, valueAnimator);
    }
}
