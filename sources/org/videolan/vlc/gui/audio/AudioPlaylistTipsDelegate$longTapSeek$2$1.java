package org.videolan.vlc.gui.audio;

import android.animation.ValueAnimator;
import android.widget.SeekBar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "animator", "Landroid/animation/ValueAnimator;", "onAnimationUpdate"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlaylistTipsDelegate.kt */
final class AudioPlaylistTipsDelegate$longTapSeek$2$1 implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ SeekBar $seekView;

    AudioPlaylistTipsDelegate$longTapSeek$2$1(SeekBar seekBar) {
        this.$seekView = seekBar;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(valueAnimator, "animator");
        SeekBar seekBar = this.$seekView;
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        seekBar.setProgress(((Integer) animatedValue).intValue());
    }
}
