package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.webserver.R;
import org.videolan.vlc.webserver.RemoteAccessOTP;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\rH\u0016J&\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u001a\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingOtpFragment;", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingFragment;", "()V", "access", "Landroid/widget/ImageView;", "animSet", "Landroid/animation/AnimatorSet;", "anims", "", "Landroid/animation/Animator;", "browserLink", "Landroid/view/View;", "browserOTP", "Landroid/widget/TextView;", "deviceOTP", "titleView", "getDefaultViewForTalkback", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "", "onResume", "onViewCreated", "view", "Companion", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessOnboardingOtpFragment.kt */
public final class RemoteAccessOnboardingOtpFragment extends RemoteAccessOnboardingFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public ImageView access;
    /* access modifiers changed from: private */
    public final AnimatorSet animSet = new AnimatorSet();
    private List<? extends Animator> anims;
    /* access modifiers changed from: private */
    public View browserLink;
    /* access modifiers changed from: private */
    public TextView browserOTP;
    /* access modifiers changed from: private */
    public TextView deviceOTP;
    private TextView titleView;

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
        return layoutInflater.inflate(R.layout.remote_access_onboarding_otp, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        View view2 = view;
        Intrinsics.checkNotNullParameter(view2, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view2.findViewById(R.id.welcome_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.titleView = (TextView) findViewById;
        View findViewById2 = view2.findViewById(R.id.browser_link);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.browserLink = findViewById2;
        View findViewById3 = view2.findViewById(R.id.otpDevice);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.deviceOTP = (TextView) findViewById3;
        View findViewById4 = view2.findViewById(R.id.otpBrowser);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.browserOTP = (TextView) findViewById4;
        View findViewById5 = view2.findViewById(R.id.access);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.access = (ImageView) findViewById5;
        if (Settings.INSTANCE.getShowTvUi()) {
            ((ImageView) view2.findViewById(R.id.deviceImage)).setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_tv));
        }
        TextView textView = this.deviceOTP;
        ImageView imageView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceOTP");
            textView = null;
        }
        textView.setText(RemoteAccessOTP.INSTANCE.generateCode());
        View view3 = this.browserLink;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserLink");
            view3 = null;
        }
        view3.setPivotX(0.0f);
        View view4 = this.browserLink;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserLink");
            view4 = null;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view4, View.SCALE_X, new float[]{0.0f, 1.0f});
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.setDuration(1000);
        TextView textView2 = this.deviceOTP;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceOTP");
            textView2 = null;
        }
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView2, View.ALPHA, new float[]{0.0f, 1.0f});
        ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat2.setStartDelay(300);
        ofFloat2.setDuration(300);
        ImageView imageView2 = this.access;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("access");
            imageView2 = null;
        }
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView2, View.ALPHA, new float[]{0.0f, 1.0f});
        ofFloat3.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat3.setDuration(500);
        ImageView imageView3 = this.access;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("access");
        } else {
            imageView = imageView3;
        }
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) KotlinExtensionsKt.getDp(16), 0.0f});
        ofFloat4.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat4.setDuration(500);
        int color = ContextCompat.getColor(requireActivity(), R.color.orange500);
        int color2 = ContextCompat.getColor(requireActivity(), R.color.red);
        int color3 = ContextCompat.getColor(requireActivity(), R.color.green400);
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(color), Integer.valueOf(color2)});
        ofObject.setDuration(500);
        ofObject.addUpdateListener(new RemoteAccessOnboardingOtpFragment$$ExternalSyntheticLambda0(this));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat3, ofFloat4, ofObject});
        Ref.IntRef intRef = new Ref.IntRef();
        Intrinsics.checkNotNull(ofFloat3);
        ofFloat3.addListener(new RemoteAccessOnboardingOtpFragment$onViewCreated$$inlined$doOnEnd$1(this, color, intRef));
        this.animSet.addListener(new RemoteAccessOnboardingOtpFragment$onViewCreated$$inlined$doOnEnd$2(this, intRef, ofObject, color, color3, color2, animatorSet));
        this.anims = CollectionsKt.listOf(this.animSet, animatorSet);
        this.animSet.playSequentially(new Animator[]{ofFloat, ofFloat2});
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(RemoteAccessOnboardingOtpFragment remoteAccessOnboardingOtpFragment, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(remoteAccessOnboardingOtpFragment, "this$0");
        Intrinsics.checkNotNullParameter(valueAnimator, "animator");
        View view = remoteAccessOnboardingOtpFragment.browserLink;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserLink");
            view = null;
        }
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        view.setBackgroundColor(((Integer) animatedValue).intValue());
    }

    public void onResume() {
        super.onResume();
        this.animSet.start();
    }

    public void onPause() {
        super.onPause();
        List<? extends Animator> list = this.anims;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("anims");
            list = null;
        }
        for (Animator cancel : list) {
            cancel.cancel();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingOtpFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingOtpFragment;", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RemoteAccessOnboardingOtpFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RemoteAccessOnboardingOtpFragment newInstance() {
            return new RemoteAccessOnboardingOtpFragment();
        }
    }
}
