package org.videolan.vlc.gui.audio;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateInterpolator;
import android.widget.SeekBar;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t¸\u0006\n"}, d2 = {"androidx/core/animation/AnimatorKt$addListener$listener$1", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animator", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "core-ktx_release", "androidx/core/animation/AnimatorKt$doOnStart$$inlined$addListener$default$1"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Animator.kt */
public final class AudioPlaylistTipsDelegate$longTapSeek$$inlined$doOnStart$1 implements Animator.AnimatorListener {
    final /* synthetic */ SeekBar $seekView$inlined;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public AudioPlaylistTipsDelegate$longTapSeek$$inlined$doOnStart$1(SeekBar seekBar) {
        this.$seekView$inlined = seekBar;
    }

    public void onAnimationStart(Animator animator) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{660, 410});
        ofInt.setStartDelay(200);
        ofInt.setDuration(1400);
        ofInt.setInterpolator(new AccelerateInterpolator());
        ofInt.addUpdateListener(new AudioPlaylistTipsDelegate$longTapSeek$1$1(this.$seekView$inlined));
        ofInt.start();
    }
}
