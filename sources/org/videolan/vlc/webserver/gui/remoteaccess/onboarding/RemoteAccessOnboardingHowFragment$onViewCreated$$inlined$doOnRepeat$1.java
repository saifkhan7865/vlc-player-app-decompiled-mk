package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.animation.Animator;
import android.view.View;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.gui.view.MiniVisualizer;
import org.videolan.vlc.webserver.R;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t¸\u0006\n"}, d2 = {"androidx/core/animation/AnimatorKt$addListener$listener$1", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animator", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "core-ktx_release", "androidx/core/animation/AnimatorKt$doOnRepeat$$inlined$addListener$default$1"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Animator.kt */
public final class RemoteAccessOnboardingHowFragment$onViewCreated$$inlined$doOnRepeat$1 implements Animator.AnimatorListener {
    final /* synthetic */ Ref.IntRef $iteration$inlined;
    final /* synthetic */ RemoteAccessOnboardingHowFragment this$0;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public RemoteAccessOnboardingHowFragment$onViewCreated$$inlined$doOnRepeat$1(Ref.IntRef intRef, RemoteAccessOnboardingHowFragment remoteAccessOnboardingHowFragment) {
        this.$iteration$inlined = intRef;
        this.this$0 = remoteAccessOnboardingHowFragment;
    }

    public void onAnimationRepeat(Animator animator) {
        int i = this.$iteration$inlined.element % 4;
        if (i == 0) {
            MiniVisualizer access$getVizu$p = this.this$0.vizu;
            if (access$getVizu$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vizu");
                access$getVizu$p = null;
            }
            access$getVizu$p.start();
            ImageView access$getPlayPause$p = this.this$0.playPause;
            if (access$getPlayPause$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playPause");
                access$getPlayPause$p = null;
            }
            access$getPlayPause$p.setImageDrawable(ContextCompat.getDrawable(this.this$0.requireActivity(), R.drawable.ic_remote_access_onboarding_pause));
        } else if (i == 1) {
            MiniVisualizer access$getVizu$p2 = this.this$0.vizu;
            if (access$getVizu$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vizu");
                access$getVizu$p2 = null;
            }
            access$getVizu$p2.stop();
            ImageView access$getPlayPause$p2 = this.this$0.playPause;
            if (access$getPlayPause$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playPause");
                access$getPlayPause$p2 = null;
            }
            access$getPlayPause$p2.setImageDrawable(ContextCompat.getDrawable(this.this$0.requireActivity(), R.drawable.ic_remote_access_onboarding_file));
        } else if (i == 2) {
            View access$getBrowserLink$p = this.this$0.browserLink;
            if (access$getBrowserLink$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("browserLink");
                access$getBrowserLink$p = null;
            }
            View access$getBrowserLink$p2 = this.this$0.browserLink;
            if (access$getBrowserLink$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("browserLink");
                access$getBrowserLink$p2 = null;
            }
            access$getBrowserLink$p.setPivotX((float) access$getBrowserLink$p2.getWidth());
        } else if (i == 3) {
            View access$getBrowserLink$p3 = this.this$0.browserLink;
            if (access$getBrowserLink$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("browserLink");
                access$getBrowserLink$p3 = null;
            }
            access$getBrowserLink$p3.setPivotX(0.0f);
            ImageView access$getPlayPause$p3 = this.this$0.playPause;
            if (access$getPlayPause$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playPause");
                access$getPlayPause$p3 = null;
            }
            access$getPlayPause$p3.setImageDrawable(ContextCompat.getDrawable(this.this$0.requireActivity(), R.drawable.ic_remote_access_onboarding_play));
        }
        this.$iteration$inlined.element++;
        View access$getBrowserLink$p4 = this.this$0.browserLink;
        if (access$getBrowserLink$p4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserLink");
            access$getBrowserLink$p4 = null;
        }
        KotlinExtensionsKt.setGone(access$getBrowserLink$p4);
        ImageView access$getPlayPause$p4 = this.this$0.playPause;
        if (access$getPlayPause$p4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playPause");
            access$getPlayPause$p4 = null;
        }
        KotlinExtensionsKt.setGone(access$getPlayPause$p4);
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), (CoroutineStart) null, new RemoteAccessOnboardingHowFragment$onViewCreated$1$1(this.this$0, (Continuation<? super RemoteAccessOnboardingHowFragment$onViewCreated$1$1>) null), 2, (Object) null);
    }
}
