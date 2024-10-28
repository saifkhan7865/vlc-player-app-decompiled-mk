package org.videolan.vlc.gui.view;

import android.animation.Animator;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"org/videolan/vlc/gui/view/FadableImageView$fade$1", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "p0", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FadableImageView.kt */
public final class FadableImageView$fade$1 implements Animator.AnimatorListener {
    final /* synthetic */ FadableImageView this$0;

    public void onAnimationRepeat(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "p0");
    }

    public void onAnimationStart(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "p0");
    }

    FadableImageView$fade$1(FadableImageView fadableImageView) {
        this.this$0 = fadableImageView;
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "p0");
        AtomicBoolean access$getAnimationRunning$p = this.this$0.animationRunning;
        if (access$getAnimationRunning$p != null) {
            access$getAnimationRunning$p.set(false);
        }
        this.this$0.setAlpha(1.0f);
    }

    public void onAnimationCancel(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "p0");
        AtomicBoolean access$getAnimationRunning$p = this.this$0.animationRunning;
        if (access$getAnimationRunning$p != null) {
            access$getAnimationRunning$p.set(false);
        }
        this.this$0.setAlpha(1.0f);
    }
}
