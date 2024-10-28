package org.videolan.vlc.gui.video;

import android.animation.ValueAnimator;
import android.view.View;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoDelayDelegate$$ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ View f$0;
    public final /* synthetic */ View f$1;
    public final /* synthetic */ VideoDelayDelegate f$2;

    public /* synthetic */ VideoDelayDelegate$$ExternalSyntheticLambda2(View view, View view2, VideoDelayDelegate videoDelayDelegate) {
        this.f$0 = view;
        this.f$1 = view2;
        this.f$2 = videoDelayDelegate;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        VideoDelayDelegate.delayChanged$lambda$6$lambda$4(this.f$0, this.f$1, this.f$2, valueAnimator);
    }
}
