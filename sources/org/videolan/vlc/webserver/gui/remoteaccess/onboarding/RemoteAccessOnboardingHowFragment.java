package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.gui.view.MiniVisualizer;
import org.videolan.vlc.webserver.R;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\nH\u0016J&\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\u001a\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingHowFragment;", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingFragment;", "()V", "animSet", "Landroid/animation/AnimatorSet;", "browserLink", "Landroid/view/View;", "playPause", "Landroid/widget/ImageView;", "titleView", "Landroid/widget/TextView;", "vizu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "getDefaultViewForTalkback", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "", "onResume", "onViewCreated", "view", "Companion", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessOnboardingHowFragment.kt */
public final class RemoteAccessOnboardingHowFragment extends RemoteAccessOnboardingFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final AnimatorSet animSet = new AnimatorSet();
    /* access modifiers changed from: private */
    public View browserLink;
    /* access modifiers changed from: private */
    public ImageView playPause;
    private TextView titleView;
    /* access modifiers changed from: private */
    public MiniVisualizer vizu;

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
        return layoutInflater.inflate(R.layout.remote_access_onboarding_how, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.welcome_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.titleView = (TextView) findViewById;
        View findViewById2 = view.findViewById(R.id.browser_link);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.browserLink = findViewById2;
        View findViewById3 = view.findViewById(R.id.play_pause);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.playPause = (ImageView) findViewById3;
        View findViewById4 = view.findViewById(R.id.vizu);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.vizu = (MiniVisualizer) findViewById4;
        ImageView imageView = null;
        if (Settings.INSTANCE.getShowTvUi()) {
            ((ImageView) view.findViewById(R.id.deviceImage)).setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_tv));
            MiniVisualizer miniVisualizer = this.vizu;
            if (miniVisualizer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vizu");
                miniVisualizer = null;
            }
            ViewGroup.LayoutParams layoutParams = miniVisualizer.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            ((ConstraintLayout.LayoutParams) layoutParams).bottomMargin = KotlinExtensionsKt.getDp(12);
        }
        Ref.IntRef intRef = new Ref.IntRef();
        View view2 = this.browserLink;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserLink");
            view2 = null;
        }
        view2.setPivotX(0.0f);
        View view3 = this.browserLink;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserLink");
            view3 = null;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view3, View.SCALE_X, new float[]{0.0f, 1.0f});
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.setDuration(500);
        ofFloat.setRepeatCount(-1);
        ImageView imageView2 = this.playPause;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playPause");
        } else {
            imageView = imageView2;
        }
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.ALPHA, new float[]{0.0f, 1.0f});
        ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat2.setDuration(500);
        Intrinsics.checkNotNull(ofFloat2);
        ofFloat2.addListener(new RemoteAccessOnboardingHowFragment$onViewCreated$$inlined$doOnRepeat$1(intRef, this));
        ofFloat2.setRepeatCount(-1);
        this.animSet.playTogether(new Animator[]{ofFloat, ofFloat2});
    }

    public void onResume() {
        super.onResume();
        this.animSet.start();
    }

    public void onPause() {
        MiniVisualizer miniVisualizer = this.vizu;
        if (miniVisualizer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vizu");
            miniVisualizer = null;
        }
        miniVisualizer.stop();
        ArrayList<Animator> childAnimations = this.animSet.getChildAnimations();
        Intrinsics.checkNotNullExpressionValue(childAnimations, "getChildAnimations(...)");
        for (Animator removeAllListeners : childAnimations) {
            removeAllListeners.removeAllListeners();
        }
        this.animSet.cancel();
        super.onPause();
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingHowFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingHowFragment;", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RemoteAccessOnboardingHowFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RemoteAccessOnboardingHowFragment newInstance() {
            return new RemoteAccessOnboardingHowFragment();
        }
    }
}
