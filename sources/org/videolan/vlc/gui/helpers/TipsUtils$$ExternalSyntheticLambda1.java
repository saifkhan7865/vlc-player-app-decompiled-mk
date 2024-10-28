package org.videolan.vlc.gui.helpers;

import android.animation.ValueAnimator;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TipsUtils$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ TipsUtils$$ExternalSyntheticLambda1(Function1 function1) {
        this.f$0 = function1;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        TipsUtils.horizontalSwipe$lambda$1$lambda$0(this.f$0, valueAnimator);
    }
}
