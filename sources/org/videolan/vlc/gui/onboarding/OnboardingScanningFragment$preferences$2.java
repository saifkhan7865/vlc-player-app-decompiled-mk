package org.videolan.vlc.gui.onboarding;

import android.content.SharedPreferences;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/content/SharedPreferences;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingScanningFragment.kt */
final class OnboardingScanningFragment$preferences$2 extends Lambda implements Function0<SharedPreferences> {
    final /* synthetic */ OnboardingScanningFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OnboardingScanningFragment$preferences$2(OnboardingScanningFragment onboardingScanningFragment) {
        super(0);
        this.this$0 = onboardingScanningFragment;
    }

    public final SharedPreferences invoke() {
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = this.this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        return (SharedPreferences) settings.getInstance(requireActivity);
    }
}
