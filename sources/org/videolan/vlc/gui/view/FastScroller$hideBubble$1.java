package org.videolan.vlc.gui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.util.LifecycleAwareScheduler;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"org/videolan/vlc/gui/view/FastScroller$hideBubble$1", "Landroid/animation/AnimatorListenerAdapter;", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FastScroller.kt */
public final class FastScroller$hideBubble$1 extends AnimatorListenerAdapter {
    final /* synthetic */ FastScroller this$0;

    FastScroller$hideBubble$1(FastScroller fastScroller) {
        this.this$0 = fastScroller;
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        super.onAnimationEnd(animator);
        TextView access$getBubble$p = this.this$0.bubble;
        if (access$getBubble$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
            access$getBubble$p = null;
        }
        access$getBubble$p.setVisibility(8);
        this.this$0.currentAnimator = null;
        LifecycleAwareScheduler.scheduleAction$default(this.this$0.getScheduler(), "hide_scroller", 3000, (Bundle) null, 4, (Object) null);
    }

    public void onAnimationCancel(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        super.onAnimationCancel(animator);
        TextView access$getBubble$p = this.this$0.bubble;
        if (access$getBubble$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bubble");
            access$getBubble$p = null;
        }
        access$getBubble$p.setVisibility(4);
        this.this$0.currentAnimator = null;
        LifecycleAwareScheduler.scheduleAction$default(this.this$0.getScheduler(), "hide_scroller", 3000, (Bundle) null, 4, (Object) null);
    }
}
