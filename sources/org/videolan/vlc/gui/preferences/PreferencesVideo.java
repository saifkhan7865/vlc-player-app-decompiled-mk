package org.videolan.vlc.gui.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.preference.Preference;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0005H\u0014J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001c\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/preferences/PreferencesVideo;", "Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "getTitleId", "", "getXml", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "onStop", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesVideo.kt */
public final class PreferencesVideo extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_video;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.video_prefs_category;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Preference findPreference = findPreference(SettingsKt.POPUP_FORCE_LEGACY);
        if (findPreference != null) {
            findPreference.setVisible(AndroidUtil.isOOrLater);
        }
    }

    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences != null && str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -1860253089) {
                if (hashCode != 682149968) {
                    if (hashCode == 830838570 && str.equals("preferred_resolution")) {
                        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PreferencesVideo$onSharedPreferenceChanged$1((Continuation<? super PreferencesVideo$onSharedPreferenceChanged$1>) null), 3, (Object) null);
                    }
                } else if (str.equals(SettingsKt.POPUP_FORCE_LEGACY)) {
                    if (sharedPreferences.getBoolean(str, false)) {
                        Permissions permissions = Permissions.INSTANCE;
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                        if (!permissions.canDrawOverlays(requireActivity)) {
                            Permissions permissions2 = Permissions.INSTANCE;
                            FragmentActivity requireActivity2 = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                            permissions2.checkDrawOverlaysPermission(requireActivity2);
                        }
                    }
                    if (!sharedPreferences.getBoolean(str, false)) {
                        Permissions permissions3 = Permissions.INSTANCE;
                        FragmentActivity requireActivity3 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                        if (!permissions3.isPiPAllowed(requireActivity3)) {
                            Permissions permissions4 = Permissions.INSTANCE;
                            FragmentActivity requireActivity4 = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity(...)");
                            permissions4.checkPiPPermission(requireActivity4);
                        }
                    }
                }
            } else if (str.equals(SettingsKt.KEY_PLAYBACK_SPEED_PERSIST_VIDEO)) {
                SettingsKt.putSingle(sharedPreferences, SettingsKt.KEY_PLAYBACK_RATE_VIDEO, Float.valueOf(1.0f));
            }
        }
    }
}
