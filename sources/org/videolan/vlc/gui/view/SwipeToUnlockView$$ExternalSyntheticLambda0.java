package org.videolan.vlc.gui.view;

import android.animation.ValueAnimator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SwipeToUnlockView$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SwipeToUnlockView f$0;

    public /* synthetic */ SwipeToUnlockView$$ExternalSyntheticLambda0(SwipeToUnlockView swipeToUnlockView) {
        this.f$0 = swipeToUnlockView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        SwipeToUnlockView.onKeyDown$lambda$4(this.f$0, valueAnimator);
    }
}
