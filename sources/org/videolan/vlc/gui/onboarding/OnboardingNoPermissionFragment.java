package org.videolan.vlc.gui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0016J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingNoPermissionFragment;", "Lorg/videolan/vlc/gui/onboarding/OnboardingFragment;", "()V", "titleView", "Landroid/widget/TextView;", "viewModel", "Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "getViewModel", "()Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getDefaultViewForTalkback", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingNoPermissionFragment.kt */
public final class OnboardingNoPermissionFragment extends OnboardingFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private TextView titleView;
    private final Lazy viewModel$delegate;

    public OnboardingNoPermissionFragment() {
        Fragment fragment = this;
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(OnboardingViewModel.class), new OnboardingNoPermissionFragment$special$$inlined$activityViewModels$default$1(fragment), new OnboardingNoPermissionFragment$special$$inlined$activityViewModels$default$2((Function0) null, fragment), new OnboardingNoPermissionFragment$special$$inlined$activityViewModels$default$3(fragment));
    }

    private final OnboardingViewModel getViewModel() {
        return (OnboardingViewModel) this.viewModel$delegate.getValue();
    }

    public TextView getDefaultViewForTalkback() {
        TextView textView = this.titleView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("titleView");
        return null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.onboarding_no_permission, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.no_permission_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.titleView = (TextView) findViewById;
        ((Button) view.findViewById(R.id.grant_permission_button)).setOnClickListener(new OnboardingNoPermissionFragment$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(OnboardingNoPermissionFragment onboardingNoPermissionFragment, View view) {
        Intrinsics.checkNotNullParameter(onboardingNoPermissionFragment, "this$0");
        onboardingNoPermissionFragment.getViewModel().setPermissionAlreadyAsked(false);
        FragmentActivity requireActivity = onboardingNoPermissionFragment.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type org.videolan.vlc.gui.onboarding.OnboardingActivity");
        ((OnboardingActivity) requireActivity).showFragment(FragmentName.ASK_PERMISSION, true);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingNoPermissionFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/onboarding/OnboardingNoPermissionFragment;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OnboardingNoPermissionFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OnboardingNoPermissionFragment newInstance() {
            return new OnboardingNoPermissionFragment();
        }
    }
}
