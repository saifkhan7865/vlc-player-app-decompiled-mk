package org.videolan.vlc.gui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\tH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J&\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingThemeFragment;", "Lorg/videolan/vlc/gui/onboarding/OnboardingFragment;", "Landroid/view/View$OnClickListener;", "()V", "darkTheme", "Landroid/view/View;", "dayNightTheme", "lightTheme", "themeDescription", "Landroid/widget/TextView;", "titleView", "viewModel", "Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "getViewModel", "()Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getDefaultViewForTalkback", "onClick", "", "view", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingThemeFragment.kt */
public final class OnboardingThemeFragment extends OnboardingFragment implements View.OnClickListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private View darkTheme;
    private View dayNightTheme;
    private View lightTheme;
    private TextView themeDescription;
    private TextView titleView;
    private final Lazy viewModel$delegate;

    public OnboardingThemeFragment() {
        Fragment fragment = this;
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(OnboardingViewModel.class), new OnboardingThemeFragment$special$$inlined$activityViewModels$default$1(fragment), new OnboardingThemeFragment$special$$inlined$activityViewModels$default$2((Function0) null, fragment), new OnboardingThemeFragment$special$$inlined$activityViewModels$default$3(fragment));
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
        return layoutInflater.inflate(R.layout.onboarding_theme, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.theme_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.titleView = (TextView) findViewById;
        View findViewById2 = view.findViewById(R.id.themeDescription);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.themeDescription = (TextView) findViewById2;
        View findViewById3 = view.findViewById(R.id.lightTheme);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.lightTheme = findViewById3;
        View findViewById4 = view.findViewById(R.id.darkTheme);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.darkTheme = findViewById4;
        View findViewById5 = view.findViewById(R.id.dayNightTheme);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.dayNightTheme = findViewById5;
        TextView textView = this.themeDescription;
        View view2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("themeDescription");
            textView = null;
        }
        textView.setText(AndroidDevices.INSTANCE.canUseSystemNightMode() ? R.string.daynight_system_explanation : R.string.daynight_legacy_explanation);
        View view3 = this.lightTheme;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lightTheme");
            view3 = null;
        }
        View.OnClickListener onClickListener = this;
        view3.setOnClickListener(onClickListener);
        View view4 = this.darkTheme;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("darkTheme");
            view4 = null;
        }
        view4.setOnClickListener(onClickListener);
        View view5 = this.dayNightTheme;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dayNightTheme");
        } else {
            view2 = view5;
        }
        view2.setOnClickListener(onClickListener);
    }

    /* JADX WARNING: type inference failed for: r8v12, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r8v30, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r8) {
        /*
            r7 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            androidx.fragment.app.FragmentActivity r0 = r7.requireActivity()
            android.content.Context r0 = (android.content.Context) r0
            int r1 = org.videolan.vlc.R.drawable.theme_selection_rounded
            android.graphics.drawable.Drawable r0 = androidx.core.content.ContextCompat.getDrawable(r0, r1)
            r8.setBackground(r0)
            android.view.ViewPropertyAnimator r0 = r8.animate()
            r1 = 1065353216(0x3f800000, float:1.0)
            android.view.ViewPropertyAnimator r0 = r0.scaleX(r1)
            r0.scaleY(r1)
            android.view.View r0 = r7.lightTheme
            java.lang.String r1 = "lightTheme"
            r2 = 0
            if (r0 != 0) goto L_0x002c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x002c:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r0)
            java.lang.String r3 = "themeDescription"
            java.lang.String r4 = "dayNightTheme"
            java.lang.String r5 = "darkTheme"
            r6 = 1061997773(0x3f4ccccd, float:0.8)
            if (r0 == 0) goto L_0x008f
            android.view.View r8 = r7.darkTheme
            if (r8 != 0) goto L_0x0043
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r8 = r2
        L_0x0043:
            r8.setBackground(r2)
            android.view.View r8 = r7.dayNightTheme
            if (r8 != 0) goto L_0x004e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r8 = r2
        L_0x004e:
            r8.setBackground(r2)
            android.view.View r8 = r7.darkTheme
            if (r8 != 0) goto L_0x0059
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r8 = r2
        L_0x0059:
            android.view.ViewPropertyAnimator r8 = r8.animate()
            android.view.ViewPropertyAnimator r8 = r8.scaleX(r6)
            r8.scaleY(r6)
            android.view.View r8 = r7.dayNightTheme
            if (r8 != 0) goto L_0x006c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r8 = r2
        L_0x006c:
            android.view.ViewPropertyAnimator r8 = r8.animate()
            android.view.ViewPropertyAnimator r8 = r8.scaleX(r6)
            r8.scaleY(r6)
            org.videolan.vlc.gui.onboarding.OnboardingViewModel r8 = r7.getViewModel()
            r0 = 1
            r8.setTheme(r0)
            android.widget.TextView r8 = r7.themeDescription
            if (r8 != 0) goto L_0x0087
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x0088
        L_0x0087:
            r2 = r8
        L_0x0088:
            int r8 = org.videolan.vlc.R.string.light_theme
            r2.setText(r8)
            goto L_0x0166
        L_0x008f:
            android.view.View r0 = r7.darkTheme
            if (r0 != 0) goto L_0x0097
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r0 = r2
        L_0x0097:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r0)
            if (r0 == 0) goto L_0x00f1
            android.widget.TextView r8 = r7.themeDescription
            if (r8 != 0) goto L_0x00a5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r8 = r2
        L_0x00a5:
            int r0 = org.videolan.vlc.R.string.enable_black_theme
            r8.setText(r0)
            android.view.View r8 = r7.lightTheme
            if (r8 != 0) goto L_0x00b2
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r8 = r2
        L_0x00b2:
            r8.setBackground(r2)
            android.view.View r8 = r7.dayNightTheme
            if (r8 != 0) goto L_0x00bd
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r8 = r2
        L_0x00bd:
            r8.setBackground(r2)
            android.view.View r8 = r7.lightTheme
            if (r8 != 0) goto L_0x00c8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r8 = r2
        L_0x00c8:
            android.view.ViewPropertyAnimator r8 = r8.animate()
            android.view.ViewPropertyAnimator r8 = r8.scaleX(r6)
            r8.scaleY(r6)
            android.view.View r8 = r7.dayNightTheme
            if (r8 != 0) goto L_0x00db
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x00dc
        L_0x00db:
            r2 = r8
        L_0x00dc:
            android.view.ViewPropertyAnimator r8 = r2.animate()
            android.view.ViewPropertyAnimator r8 = r8.scaleX(r6)
            r8.scaleY(r6)
            org.videolan.vlc.gui.onboarding.OnboardingViewModel r8 = r7.getViewModel()
            r0 = 2
            r8.setTheme(r0)
            goto L_0x0166
        L_0x00f1:
            android.view.View r0 = r7.dayNightTheme
            if (r0 != 0) goto L_0x00f9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r0 = r2
        L_0x00f9:
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r0)
            if (r8 == 0) goto L_0x0166
            android.widget.TextView r8 = r7.themeDescription
            if (r8 != 0) goto L_0x0107
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r8 = r2
        L_0x0107:
            org.videolan.resources.AndroidDevices r0 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r0 = r0.canUseSystemNightMode()
            if (r0 == 0) goto L_0x0112
            int r0 = org.videolan.vlc.R.string.daynight_system_explanation
            goto L_0x0114
        L_0x0112:
            int r0 = org.videolan.vlc.R.string.daynight_legacy_explanation
        L_0x0114:
            r8.setText(r0)
            android.view.View r8 = r7.lightTheme
            if (r8 != 0) goto L_0x011f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r8 = r2
        L_0x011f:
            r8.setBackground(r2)
            android.view.View r8 = r7.darkTheme
            if (r8 != 0) goto L_0x012a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r8 = r2
        L_0x012a:
            r8.setBackground(r2)
            android.view.View r8 = r7.lightTheme
            if (r8 != 0) goto L_0x0135
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r8 = r2
        L_0x0135:
            android.view.ViewPropertyAnimator r8 = r8.animate()
            android.view.ViewPropertyAnimator r8 = r8.scaleX(r6)
            r8.scaleY(r6)
            android.view.View r8 = r7.darkTheme
            if (r8 != 0) goto L_0x0148
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            goto L_0x0149
        L_0x0148:
            r2 = r8
        L_0x0149:
            android.view.ViewPropertyAnimator r8 = r2.animate()
            android.view.ViewPropertyAnimator r8 = r8.scaleX(r6)
            r8.scaleY(r6)
            org.videolan.vlc.gui.onboarding.OnboardingViewModel r8 = r7.getViewModel()
            org.videolan.resources.AndroidDevices r0 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r0 = r0.canUseSystemNightMode()
            if (r0 == 0) goto L_0x0162
            r0 = -1
            goto L_0x0163
        L_0x0162:
            r0 = 0
        L_0x0163:
            r8.setTheme(r0)
        L_0x0166:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.onboarding.OnboardingThemeFragment.onClick(android.view.View):void");
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingThemeFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/onboarding/OnboardingThemeFragment;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OnboardingThemeFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OnboardingThemeFragment newInstance() {
            return new OnboardingThemeFragment();
        }
    }
}
