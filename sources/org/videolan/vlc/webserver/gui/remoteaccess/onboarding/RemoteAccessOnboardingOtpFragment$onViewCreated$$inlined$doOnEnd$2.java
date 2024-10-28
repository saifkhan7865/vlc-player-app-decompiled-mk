package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t¸\u0006\n"}, d2 = {"androidx/core/animation/AnimatorKt$addListener$listener$1", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animator", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "core-ktx_release", "androidx/core/animation/AnimatorKt$doOnEnd$$inlined$addListener$default$1"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Animator.kt */
public final class RemoteAccessOnboardingOtpFragment$onViewCreated$$inlined$doOnEnd$2 implements Animator.AnimatorListener {
    final /* synthetic */ AnimatorSet $accessAnims$inlined;
    final /* synthetic */ ValueAnimator $colorAnimation$inlined;
    final /* synthetic */ int $green$inlined;
    final /* synthetic */ Ref.IntRef $iteration$inlined;
    final /* synthetic */ int $orange$inlined;
    final /* synthetic */ int $red$inlined;
    final /* synthetic */ RemoteAccessOnboardingOtpFragment this$0;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public RemoteAccessOnboardingOtpFragment$onViewCreated$$inlined$doOnEnd$2(RemoteAccessOnboardingOtpFragment remoteAccessOnboardingOtpFragment, Ref.IntRef intRef, ValueAnimator valueAnimator, int i, int i2, int i3, AnimatorSet animatorSet) {
        this.this$0 = remoteAccessOnboardingOtpFragment;
        this.$iteration$inlined = intRef;
        this.$colorAnimation$inlined = valueAnimator;
        this.$orange$inlined = i;
        this.$green$inlined = i2;
        this.$red$inlined = i3;
        this.$accessAnims$inlined = animatorSet;
    }

    public void onAnimationEnd(Animator animator) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new RemoteAccessOnboardingOtpFragment$onViewCreated$3$1(this.this$0, this.$iteration$inlined, this.$colorAnimation$inlined, this.$orange$inlined, this.$green$inlined, this.$red$inlined, this.$accessAnims$inlined, (Continuation<? super RemoteAccessOnboardingOtpFragment$onViewCreated$3$1>) null), 3, (Object) null);
    }
}
