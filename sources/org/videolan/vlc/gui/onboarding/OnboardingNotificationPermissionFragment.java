package org.videolan.vlc.gui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016J&\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingNotificationPermissionFragment;", "Lorg/videolan/vlc/gui/onboarding/OnboardingFragment;", "()V", "titleView", "Landroid/widget/TextView;", "getDefaultViewForTalkback", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingNotificationPermissionFragment.kt */
public final class OnboardingNotificationPermissionFragment extends OnboardingFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
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
        return layoutInflater.inflate(R.layout.onboarding_notification_permission, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.permission_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.titleView = (TextView) findViewById;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingNotificationPermissionFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/onboarding/OnboardingNotificationPermissionFragment;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OnboardingNotificationPermissionFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OnboardingNotificationPermissionFragment newInstance() {
            return new OnboardingNotificationPermissionFragment();
        }
    }
}
