package org.videolan.vlc.gui.video;

import android.animation.Animator;
import android.view.View;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t¸\u0006\n"}, d2 = {"androidx/core/animation/AnimatorKt$addListener$listener$1", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animator", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "core-ktx_release", "androidx/core/animation/AnimatorKt$doOnEnd$$inlined$addListener$default$1"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Animator.kt */
public final class VideoDelayDelegate$delayChanged$lambda$6$$inlined$doOnEnd$1 implements Animator.AnimatorListener {
    final /* synthetic */ View $button$inlined;
    final /* synthetic */ VideoDelayDelegate this$0;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public VideoDelayDelegate$delayChanged$lambda$6$$inlined$doOnEnd$1(View view, VideoDelayDelegate videoDelayDelegate) {
        this.$button$inlined = view;
        this.this$0 = videoDelayDelegate;
    }

    public void onAnimationEnd(Animator animator) {
        this.$button$inlined.setBackgroundTintList(ContextCompat.getColorStateList(this.this$0.player, R.color.player_delay_button_background_tint));
    }
}
