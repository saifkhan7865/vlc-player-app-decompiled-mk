package org.videolan.vlc.gui.onboarding;

import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.util.AccessibilityHelperKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "onboardingFragmentListener", "Lorg/videolan/vlc/gui/onboarding/OnboardingFragmentListener;", "getOnboardingFragmentListener", "()Lorg/videolan/vlc/gui/onboarding/OnboardingFragmentListener;", "setOnboardingFragmentListener", "(Lorg/videolan/vlc/gui/onboarding/OnboardingFragmentListener;)V", "getDefaultViewForTalkback", "Landroid/view/View;", "onResume", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingFragment.kt */
public abstract class OnboardingFragment extends Fragment {
    public OnboardingFragmentListener onboardingFragmentListener;

    public abstract View getDefaultViewForTalkback();

    public final OnboardingFragmentListener getOnboardingFragmentListener() {
        OnboardingFragmentListener onboardingFragmentListener2 = this.onboardingFragmentListener;
        if (onboardingFragmentListener2 != null) {
            return onboardingFragmentListener2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onboardingFragmentListener");
        return null;
    }

    public final void setOnboardingFragmentListener(OnboardingFragmentListener onboardingFragmentListener2) {
        Intrinsics.checkNotNullParameter(onboardingFragmentListener2, "<set-?>");
        this.onboardingFragmentListener = onboardingFragmentListener2;
    }

    public void onResume() {
        super.onResume();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (AccessibilityHelperKt.isTalkbackIsEnabled(requireActivity)) {
            getDefaultViewForTalkback().sendAccessibilityEvent(8);
        }
    }
}
