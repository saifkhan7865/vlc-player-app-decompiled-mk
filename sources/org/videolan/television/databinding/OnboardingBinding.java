package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import org.videolan.television.R;

public final class OnboardingBinding implements ViewBinding {
    public final FragmentContainerView onboradingFragment;
    private final FragmentContainerView rootView;

    private OnboardingBinding(FragmentContainerView fragmentContainerView, FragmentContainerView fragmentContainerView2) {
        this.rootView = fragmentContainerView;
        this.onboradingFragment = fragmentContainerView2;
    }

    public FragmentContainerView getRoot() {
        return this.rootView;
    }

    public static OnboardingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static OnboardingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.onboarding, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static OnboardingBinding bind(View view) {
        if (view != null) {
            FragmentContainerView fragmentContainerView = (FragmentContainerView) view;
            return new OnboardingBinding(fragmentContainerView, fragmentContainerView);
        }
        throw new NullPointerException("rootView");
    }
}
