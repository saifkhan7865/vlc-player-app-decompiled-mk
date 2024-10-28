package org.videolan.vlc.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/util/FeatureFlagManager;", "", "()V", "enable", "", "context", "Landroid/content/Context;", "feature", "Lorg/videolan/vlc/util/FeatureFlag;", "enabled", "", "getByKey", "key", "", "isEnabled", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FeatureFlagManager.kt */
public final class FeatureFlagManager {
    public static final FeatureFlagManager INSTANCE = new FeatureFlagManager();

    private FeatureFlagManager() {
    }

    public final boolean isEnabled(Context context, FeatureFlag featureFlag) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(featureFlag, "feature");
        return ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getBoolean(featureFlag.getKey(), false);
    }

    public final void enable(Context context, FeatureFlag featureFlag, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(featureFlag, "feature");
        SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(context), featureFlag.getKey(), Boolean.valueOf(z));
    }

    public final FeatureFlag getByKey(String str) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        for (FeatureFlag featureFlag : FeatureFlag.values()) {
            if (Intrinsics.areEqual((Object) featureFlag.getKey(), (Object) str)) {
                return featureFlag;
            }
        }
        return null;
    }
}
