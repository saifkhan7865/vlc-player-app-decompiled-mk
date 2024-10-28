package org.videolan.vlc.gui.onboarding;

import android.app.Activity;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"ONBOARDING_DONE_KEY", "", "startOnboarding", "", "Landroid/app/Activity;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingActivity.kt */
public final class OnboardingActivityKt {
    public static final String ONBOARDING_DONE_KEY = "app_onboarding_done";

    public static final void startOnboarding(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        activity.startActivityForResult(new Intent(activity, OnboardingActivity.class), 1);
    }
}
