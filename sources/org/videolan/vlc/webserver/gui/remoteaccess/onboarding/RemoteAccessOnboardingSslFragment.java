package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.animation.Animator;
import android.animation.AnimatorSet;
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
import java.security.SecureRandom;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.cache.DiskLruCache;
import org.videolan.resources.Constants;
import org.videolan.tools.Settings;
import org.videolan.vlc.webserver.R;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\bH\u0016J&\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u000bH\u0016J\u001a\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingSslFragment;", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingFragment;", "()V", "animSet", "Landroid/animation/AnimatorSet;", "browserLink", "Landroid/view/View;", "data", "Landroid/widget/TextView;", "titleView", "generateRandomData", "", "getDefaultViewForTalkback", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onViewCreated", "view", "Companion", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessOnboardingSslFragment.kt */
public final class RemoteAccessOnboardingSslFragment extends RemoteAccessOnboardingFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final AnimatorSet animSet = new AnimatorSet();
    /* access modifiers changed from: private */
    public View browserLink;
    private TextView data;
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
        return layoutInflater.inflate(R.layout.remote_access_onboarding_ssl, viewGroup, false);
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
        View findViewById3 = view.findViewById(R.id.data);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.data = (TextView) findViewById3;
        if (Settings.INSTANCE.getShowTvUi()) {
            ((ImageView) view.findViewById(R.id.deviceImage)).setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_tv));
        }
        Ref.IntRef intRef = new Ref.IntRef();
        View view2 = this.browserLink;
        View view3 = null;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserLink");
            view2 = null;
        }
        view2.setPivotX(0.0f);
        View view4 = this.browserLink;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserLink");
        } else {
            view3 = view4;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view3, View.SCALE_X, new float[]{0.0f, 1.0f});
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.setDuration(2000);
        ofFloat.setRepeatCount(-1);
        ofFloat.addUpdateListener(new RemoteAccessOnboardingSslFragment$$ExternalSyntheticLambda0(new Ref.LongRef(), this));
        Intrinsics.checkNotNull(ofFloat);
        ofFloat.addListener(new RemoteAccessOnboardingSslFragment$onViewCreated$$inlined$doOnRepeat$1(intRef, this));
        this.animSet.playTogether(new Animator[]{ofFloat});
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(Ref.LongRef longRef, RemoteAccessOnboardingSslFragment remoteAccessOnboardingSslFragment, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(longRef, "$last");
        Intrinsics.checkNotNullParameter(remoteAccessOnboardingSslFragment, "this$0");
        Intrinsics.checkNotNullParameter(valueAnimator, "it");
        if (System.currentTimeMillis() - longRef.element > 150) {
            remoteAccessOnboardingSslFragment.generateRandomData();
            longRef.element = System.currentTimeMillis();
        }
    }

    private final void generateRandomData() {
        TextView textView = this.data;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
            textView = null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(new SecureRandom().nextBoolean() ? DiskLruCache.VERSION_1 : Constants.GROUP_VIDEOS_FOLDER);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        textView.setText(sb2);
    }

    public void onResume() {
        super.onResume();
        this.animSet.start();
    }

    public void onPause() {
        this.animSet.cancel();
        super.onPause();
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingSslFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingSslFragment;", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RemoteAccessOnboardingSslFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RemoteAccessOnboardingSslFragment newInstance() {
            return new RemoteAccessOnboardingSslFragment();
        }
    }
}
