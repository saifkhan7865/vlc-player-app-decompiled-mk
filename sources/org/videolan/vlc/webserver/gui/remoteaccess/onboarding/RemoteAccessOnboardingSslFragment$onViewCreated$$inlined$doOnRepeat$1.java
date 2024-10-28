package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.animation.Animator;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t¸\u0006\n"}, d2 = {"androidx/core/animation/AnimatorKt$addListener$listener$1", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animator", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "core-ktx_release", "androidx/core/animation/AnimatorKt$doOnRepeat$$inlined$addListener$default$1"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Animator.kt */
public final class RemoteAccessOnboardingSslFragment$onViewCreated$$inlined$doOnRepeat$1 implements Animator.AnimatorListener {
    final /* synthetic */ Ref.IntRef $iteration$inlined;
    final /* synthetic */ RemoteAccessOnboardingSslFragment this$0;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public RemoteAccessOnboardingSslFragment$onViewCreated$$inlined$doOnRepeat$1(Ref.IntRef intRef, RemoteAccessOnboardingSslFragment remoteAccessOnboardingSslFragment) {
        this.$iteration$inlined = intRef;
        this.this$0 = remoteAccessOnboardingSslFragment;
    }

    public void onAnimationRepeat(Animator animator) {
        int i = this.$iteration$inlined.element % 2;
        View view = null;
        if (i == 0) {
            View access$getBrowserLink$p = this.this$0.browserLink;
            if (access$getBrowserLink$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("browserLink");
                access$getBrowserLink$p = null;
            }
            View access$getBrowserLink$p2 = this.this$0.browserLink;
            if (access$getBrowserLink$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("browserLink");
            } else {
                view = access$getBrowserLink$p2;
            }
            access$getBrowserLink$p.setPivotX((float) view.getWidth());
        } else if (i == 1) {
            View access$getBrowserLink$p3 = this.this$0.browserLink;
            if (access$getBrowserLink$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("browserLink");
            } else {
                view = access$getBrowserLink$p3;
            }
            view.setPivotX(0.0f);
        }
        this.$iteration$inlined.element++;
    }
}
