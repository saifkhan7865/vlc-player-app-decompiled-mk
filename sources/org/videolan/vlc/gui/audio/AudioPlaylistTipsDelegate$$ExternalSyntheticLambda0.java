package org.videolan.vlc.gui.audio;

import android.animation.ValueAnimator;
import android.view.View;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioPlaylistTipsDelegate$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ View f$0;

    public /* synthetic */ AudioPlaylistTipsDelegate$$ExternalSyntheticLambda0(View view) {
        this.f$0 = view;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        AudioPlaylistTipsDelegate.dragAndDrop$lambda$3(this.f$0, valueAnimator);
    }
}
