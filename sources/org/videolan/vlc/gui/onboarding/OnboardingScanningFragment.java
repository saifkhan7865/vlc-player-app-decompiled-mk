package org.videolan.vlc.gui.onboarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import com.google.android.material.switchmaterial.SwitchMaterial;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.videolan.resources.Constants;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SecondaryActivity;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\nH\u0016J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\b\u001a\u0004\b\r\u0010\u000e¨\u0006\u001d"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingScanningFragment;", "Lorg/videolan/vlc/gui/onboarding/OnboardingFragment;", "()V", "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "()Landroid/content/SharedPreferences;", "preferences$delegate", "Lkotlin/Lazy;", "titleView", "Landroid/widget/TextView;", "viewModel", "Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "getViewModel", "()Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "viewModel$delegate", "getDefaultViewForTalkback", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingScanningFragment.kt */
public final class OnboardingScanningFragment extends OnboardingFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Lazy preferences$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new OnboardingScanningFragment$preferences$2(this));
    private TextView titleView;
    private final Lazy viewModel$delegate;

    public OnboardingScanningFragment() {
        Fragment fragment = this;
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(OnboardingViewModel.class), new OnboardingScanningFragment$special$$inlined$activityViewModels$default$1(fragment), new OnboardingScanningFragment$special$$inlined$activityViewModels$default$2((Function0) null, fragment), new OnboardingScanningFragment$special$$inlined$activityViewModels$default$3(fragment));
    }

    private final OnboardingViewModel getViewModel() {
        return (OnboardingViewModel) this.viewModel$delegate.getValue();
    }

    private final SharedPreferences getPreferences() {
        return (SharedPreferences) this.preferences$delegate.getValue();
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
        return layoutInflater.inflate(R.layout.onboarding_scanning, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Button button = (Button) view.findViewById(R.id.customizeButton);
        View findViewById = view.findViewById(R.id.scanning_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.titleView = (TextView) findViewById;
        ((SwitchMaterial) view.findViewById(R.id.scanningEnableSwitch)).setOnCheckedChangeListener(new OnboardingScanningFragment$$ExternalSyntheticLambda0(this, button));
        button.setOnClickListener(new OnboardingScanningFragment$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(OnboardingScanningFragment onboardingScanningFragment, Button button, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(onboardingScanningFragment, "this$0");
        SettingsKt.putSingle(onboardingScanningFragment.getPreferences(), SettingsKt.KEY_MEDIALIBRARY_SCAN, Integer.valueOf(z ^ true ? 1 : 0));
        onboardingScanningFragment.getViewModel().setScanStorages(z);
        button.setEnabled(z);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(OnboardingScanningFragment onboardingScanningFragment, View view) {
        Intrinsics.checkNotNullParameter(onboardingScanningFragment, "this$0");
        FragmentActivity requireActivity = onboardingScanningFragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Intent intent = new Intent(requireActivity.getApplicationContext(), SecondaryActivity.class);
        intent.putExtra(SecondaryActivity.KEY_FRAGMENT, SecondaryActivity.STORAGE_BROWSER_ONBOARDING);
        intent.putExtra(Constants.KEY_ANIMATED, true);
        onboardingScanningFragment.requireActivity().startActivity(intent);
        requireActivity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.no_animation);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingScanningFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/onboarding/OnboardingScanningFragment;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OnboardingScanningFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OnboardingScanningFragment newInstance() {
            return new OnboardingScanningFragment();
        }
    }
}
