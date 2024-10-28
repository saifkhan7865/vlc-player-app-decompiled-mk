package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.gui.view.MiniVisualizer;
import org.videolan.vlc.webserver.R;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\nH\u0016J&\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\u001a\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingContentFragment;", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingFragment;", "()V", "animationLoop", "Landroid/animation/AnimatorSet;", "filesLL", "Landroid/widget/LinearLayout;", "medialibraryLL", "playbackLL", "titleView", "Landroid/widget/TextView;", "vizu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "getDefaultViewForTalkback", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "", "onResume", "onViewCreated", "view", "Companion", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessOnboardingContentFragment.kt */
public final class RemoteAccessOnboardingContentFragment extends RemoteAccessOnboardingFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final AnimatorSet animationLoop = new AnimatorSet();
    private LinearLayout filesLL;
    private LinearLayout medialibraryLL;
    private LinearLayout playbackLL;
    private TextView titleView;
    private MiniVisualizer vizu;

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
        return layoutInflater.inflate(R.layout.remote_access_onboarding_content, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.welcome_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.titleView = (TextView) findViewById;
        View findViewById2 = view.findViewById(R.id.files);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.filesLL = (LinearLayout) findViewById2;
        View findViewById3 = view.findViewById(R.id.playback);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.playbackLL = (LinearLayout) findViewById3;
        View findViewById4 = view.findViewById(R.id.medialibrary);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.medialibraryLL = (LinearLayout) findViewById4;
        View findViewById5 = view.findViewById(R.id.vizu);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        MiniVisualizer miniVisualizer = (MiniVisualizer) findViewById5;
        this.vizu = miniVisualizer;
        LinearLayout linearLayout = null;
        if (miniVisualizer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vizu");
            miniVisualizer = null;
        }
        miniVisualizer.start();
        ArrayList arrayList = new ArrayList();
        LinearLayout linearLayout2 = this.medialibraryLL;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibraryLL");
            linearLayout2 = null;
        }
        LinearLayout linearLayout3 = this.filesLL;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filesLL");
            linearLayout3 = null;
        }
        LinearLayout linearLayout4 = this.playbackLL;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackLL");
        } else {
            linearLayout = linearLayout4;
        }
        LinearLayout[] linearLayoutArr = {linearLayout2, linearLayout3, linearLayout};
        for (int i = 0; i < 3; i++) {
            LinearLayout linearLayout5 = linearLayoutArr[i];
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(linearLayout5, View.SCALE_X, new float[]{1.0f, 1.2f, 1.0f});
            ofFloat.setInterpolator(new OvershootInterpolator());
            ofFloat.setDuration(1500);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(linearLayout5, View.SCALE_Y, new float[]{1.0f, 1.2f, 1.0f});
            ofFloat2.setInterpolator(new OvershootInterpolator());
            ofFloat2.setDuration(1500);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            arrayList.add(animatorSet);
        }
        this.animationLoop.playSequentially(CollectionsKt.toMutableList(arrayList));
        this.animationLoop.addListener(new RemoteAccessOnboardingContentFragment$onViewCreated$$inlined$doOnEnd$1(this));
    }

    public void onResume() {
        super.onResume();
        this.animationLoop.start();
    }

    public void onPause() {
        super.onPause();
        MiniVisualizer miniVisualizer = this.vizu;
        if (miniVisualizer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vizu");
            miniVisualizer = null;
        }
        miniVisualizer.stop();
        this.animationLoop.cancel();
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingContentFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingContentFragment;", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RemoteAccessOnboardingContentFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RemoteAccessOnboardingContentFragment newInstance() {
            return new RemoteAccessOnboardingContentFragment();
        }
    }
}
