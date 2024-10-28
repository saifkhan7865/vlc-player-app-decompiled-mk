package org.videolan.vlc.gui.onboarding;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 &2\u00020\u00012\u00020\u0002:\u0001&B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u000bH\u0016J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u0018H\u0016J\u001a\u0010%\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014¨\u0006'"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingPermissionFragment;", "Lorg/videolan/vlc/gui/onboarding/OnboardingFragment;", "Landroid/view/View$OnClickListener;", "()V", "currentlySelected", "Landroid/widget/ImageView;", "oldSelected", "permAll", "Landroid/widget/FrameLayout;", "permAllImage", "permDescription", "Landroid/widget/TextView;", "permMedia", "permMediaImage", "permNone", "permNoneImage", "permissionTitle", "viewModel", "Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "getViewModel", "()Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "animateColor", "", "getDefaultViewForTalkback", "onClick", "view", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onViewCreated", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingPermissionFragment.kt */
public final class OnboardingPermissionFragment extends OnboardingFragment implements View.OnClickListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private ImageView currentlySelected;
    private ImageView oldSelected;
    private FrameLayout permAll;
    private ImageView permAllImage;
    private TextView permDescription;
    private FrameLayout permMedia;
    private ImageView permMediaImage;
    private FrameLayout permNone;
    private ImageView permNoneImage;
    private TextView permissionTitle;
    private final Lazy viewModel$delegate;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OnboardingPermissionFragment.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                org.videolan.vlc.gui.onboarding.PermissionType[] r0 = org.videolan.vlc.gui.onboarding.PermissionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.onboarding.PermissionType r1 = org.videolan.vlc.gui.onboarding.PermissionType.NONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.onboarding.PermissionType r1 = org.videolan.vlc.gui.onboarding.PermissionType.ALL     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.gui.onboarding.PermissionType r1 = org.videolan.vlc.gui.onboarding.PermissionType.MEDIA     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.onboarding.OnboardingPermissionFragment.WhenMappings.<clinit>():void");
        }
    }

    public OnboardingPermissionFragment() {
        Fragment fragment = this;
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(OnboardingViewModel.class), new OnboardingPermissionFragment$special$$inlined$activityViewModels$default$1(fragment), new OnboardingPermissionFragment$special$$inlined$activityViewModels$default$2((Function0) null, fragment), new OnboardingPermissionFragment$special$$inlined$activityViewModels$default$3(fragment));
    }

    private final OnboardingViewModel getViewModel() {
        return (OnboardingViewModel) this.viewModel$delegate.getValue();
    }

    public TextView getDefaultViewForTalkback() {
        TextView textView = this.permissionTitle;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("permissionTitle");
        return null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.onboarding_permission, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.permission_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.permissionTitle = (TextView) findViewById;
        View findViewById2 = view.findViewById(R.id.permNone);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.permNone = (FrameLayout) findViewById2;
        View findViewById3 = view.findViewById(R.id.permMedia);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.permMedia = (FrameLayout) findViewById3;
        View findViewById4 = view.findViewById(R.id.permAll);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.permAll = (FrameLayout) findViewById4;
        View findViewById5 = view.findViewById(R.id.permNoneImage);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.permNoneImage = (ImageView) findViewById5;
        View findViewById6 = view.findViewById(R.id.permMediaImage);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.permMediaImage = (ImageView) findViewById6;
        View findViewById7 = view.findViewById(R.id.permAllImage);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.permAllImage = (ImageView) findViewById7;
        View findViewById8 = view.findViewById(R.id.permDescription);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.permDescription = (TextView) findViewById8;
        FrameLayout frameLayout = this.permNone;
        ImageView imageView = null;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permNone");
            frameLayout = null;
        }
        View.OnClickListener onClickListener = this;
        frameLayout.setOnClickListener(onClickListener);
        FrameLayout frameLayout2 = this.permMedia;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permMedia");
            frameLayout2 = null;
        }
        frameLayout2.setOnClickListener(onClickListener);
        FrameLayout frameLayout3 = this.permAll;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permAll");
            frameLayout3 = null;
        }
        frameLayout3.setOnClickListener(onClickListener);
        ImageView imageView2 = this.permAllImage;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permAllImage");
            imageView2 = null;
        }
        this.currentlySelected = imageView2;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentlySelected");
        } else {
            imageView = imageView2;
        }
        imageView.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.orange500));
    }

    public void onResume() {
        FrameLayout frameLayout;
        super.onResume();
        int i = WhenMappings.$EnumSwitchMapping$0[getViewModel().getPermissionType().ordinal()];
        FrameLayout frameLayout2 = null;
        if (i == 1) {
            frameLayout = this.permNone;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permNone");
                frameLayout2.performClick();
            }
        } else if (i == 2) {
            frameLayout = this.permAll;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permAll");
                frameLayout2.performClick();
            }
        } else if (i == 3) {
            frameLayout = this.permMedia;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permMedia");
                frameLayout2.performClick();
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        frameLayout2 = frameLayout;
        frameLayout2.performClick();
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.theme_selection_rounded));
        view.animate().scaleX(1.0f).scaleY(1.0f);
        ImageView imageView = this.currentlySelected;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentlySelected");
            imageView = null;
        }
        this.oldSelected = imageView;
        FrameLayout frameLayout = this.permNone;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("permNone");
            frameLayout = null;
        }
        if (Intrinsics.areEqual((Object) view, (Object) frameLayout)) {
            FrameLayout frameLayout2 = this.permMedia;
            if (frameLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permMedia");
                frameLayout2 = null;
            }
            frameLayout2.setBackground((Drawable) null);
            FrameLayout frameLayout3 = this.permAll;
            if (frameLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permAll");
                frameLayout3 = null;
            }
            frameLayout3.setBackground((Drawable) null);
            FrameLayout frameLayout4 = this.permMedia;
            if (frameLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permMedia");
                frameLayout4 = null;
            }
            frameLayout4.animate().scaleX(0.8f).scaleY(0.8f);
            FrameLayout frameLayout5 = this.permAll;
            if (frameLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permAll");
                frameLayout5 = null;
            }
            frameLayout5.animate().scaleX(0.8f).scaleY(0.8f);
            TextView textView = this.permDescription;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permDescription");
                textView = null;
            }
            textView.setText(R.string.permission_onboarding_no_perm);
            ImageView imageView3 = this.permNoneImage;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permNoneImage");
            } else {
                imageView2 = imageView3;
            }
            this.currentlySelected = imageView2;
            getViewModel().setPermissionType(PermissionType.NONE);
        } else {
            FrameLayout frameLayout6 = this.permMedia;
            if (frameLayout6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permMedia");
                frameLayout6 = null;
            }
            if (Intrinsics.areEqual((Object) view, (Object) frameLayout6)) {
                TextView textView2 = this.permDescription;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("permDescription");
                    textView2 = null;
                }
                textView2.setText(R.string.permission_onboarding_perm_media);
                FrameLayout frameLayout7 = this.permNone;
                if (frameLayout7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("permNone");
                    frameLayout7 = null;
                }
                frameLayout7.setBackground((Drawable) null);
                FrameLayout frameLayout8 = this.permAll;
                if (frameLayout8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("permAll");
                    frameLayout8 = null;
                }
                frameLayout8.setBackground((Drawable) null);
                FrameLayout frameLayout9 = this.permNone;
                if (frameLayout9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("permNone");
                    frameLayout9 = null;
                }
                frameLayout9.animate().scaleX(0.8f).scaleY(0.8f);
                FrameLayout frameLayout10 = this.permAll;
                if (frameLayout10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("permAll");
                    frameLayout10 = null;
                }
                frameLayout10.animate().scaleX(0.8f).scaleY(0.8f);
                ImageView imageView4 = this.permMediaImage;
                if (imageView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("permMediaImage");
                } else {
                    imageView2 = imageView4;
                }
                this.currentlySelected = imageView2;
                getViewModel().setPermissionType(PermissionType.MEDIA);
            } else {
                FrameLayout frameLayout11 = this.permAll;
                if (frameLayout11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("permAll");
                    frameLayout11 = null;
                }
                if (Intrinsics.areEqual((Object) view, (Object) frameLayout11)) {
                    TextView textView3 = this.permDescription;
                    if (textView3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("permDescription");
                        textView3 = null;
                    }
                    textView3.setText(R.string.permission_onboarding_perm_all);
                    FrameLayout frameLayout12 = this.permNone;
                    if (frameLayout12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("permNone");
                        frameLayout12 = null;
                    }
                    frameLayout12.setBackground((Drawable) null);
                    FrameLayout frameLayout13 = this.permMedia;
                    if (frameLayout13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("permMedia");
                        frameLayout13 = null;
                    }
                    frameLayout13.setBackground((Drawable) null);
                    FrameLayout frameLayout14 = this.permNone;
                    if (frameLayout14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("permNone");
                        frameLayout14 = null;
                    }
                    frameLayout14.animate().scaleX(0.8f).scaleY(0.8f);
                    FrameLayout frameLayout15 = this.permMedia;
                    if (frameLayout15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("permMedia");
                        frameLayout15 = null;
                    }
                    frameLayout15.animate().scaleX(0.8f).scaleY(0.8f);
                    ImageView imageView5 = this.permAllImage;
                    if (imageView5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("permAllImage");
                    } else {
                        imageView2 = imageView5;
                    }
                    this.currentlySelected = imageView2;
                    getViewModel().setPermissionType(PermissionType.ALL);
                }
            }
        }
        animateColor();
    }

    private final void animateColor() {
        int color = ContextCompat.getColor(requireActivity(), R.color.orange500);
        int color2 = ContextCompat.getColor(requireActivity(), R.color.white);
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(color), Integer.valueOf(color2)});
        ofObject.setDuration(250);
        ofObject.addUpdateListener(new OnboardingPermissionFragment$$ExternalSyntheticLambda0(this));
        ofObject.start();
        int color3 = ContextCompat.getColor(requireActivity(), R.color.white);
        int color4 = ContextCompat.getColor(requireActivity(), R.color.orange500);
        ValueAnimator ofObject2 = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(color3), Integer.valueOf(color4)});
        ofObject2.setDuration(250);
        ofObject2.addUpdateListener(new OnboardingPermissionFragment$$ExternalSyntheticLambda1(this));
        ofObject2.start();
    }

    /* access modifiers changed from: private */
    public static final void animateColor$lambda$0(OnboardingPermissionFragment onboardingPermissionFragment, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(onboardingPermissionFragment, "this$0");
        Intrinsics.checkNotNullParameter(valueAnimator, "animator");
        ImageView imageView = onboardingPermissionFragment.oldSelected;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("oldSelected");
            imageView = null;
        }
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        imageView.setColorFilter(((Integer) animatedValue).intValue());
    }

    /* access modifiers changed from: private */
    public static final void animateColor$lambda$1(OnboardingPermissionFragment onboardingPermissionFragment, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(onboardingPermissionFragment, "this$0");
        Intrinsics.checkNotNullParameter(valueAnimator, "animator");
        ImageView imageView = onboardingPermissionFragment.currentlySelected;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentlySelected");
            imageView = null;
        }
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        imageView.setColorFilter(((Integer) animatedValue).intValue());
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingPermissionFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/onboarding/OnboardingPermissionFragment;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OnboardingPermissionFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OnboardingPermissionFragment newInstance() {
            return new OnboardingPermissionFragment();
        }
    }
}
