package org.videolan.vlc.gui.preferences;

import android.content.SharedPreferences;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.video.VideoPlayerActivity;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0005H\u0014J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/gui/preferences/PreferencesAudioControls;", "Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "getTitleId", "", "getXml", "onSharedPreferenceChanged", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "onStop", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesAudioControls.kt */
public final class PreferencesAudioControls extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_audio_controls;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.controls_prefs_category;
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
            FragmentActivity activity = getActivity();
            VideoPlayerActivity videoPlayerActivity = activity instanceof VideoPlayerActivity ? (VideoPlayerActivity) activity : null;
            if (videoPlayerActivity != null) {
                videoPlayerActivity.onChangedControlSetting(str);
            }
            if (Intrinsics.areEqual((Object) str, (Object) SettingsKt.KEY_AUDIO_JUMP_DELAY)) {
                Settings.INSTANCE.setAudioJumpDelay(sharedPreferences.getInt(SettingsKt.KEY_AUDIO_JUMP_DELAY, 10));
            } else if (Intrinsics.areEqual((Object) str, (Object) SettingsKt.KEY_AUDIO_LONG_JUMP_DELAY)) {
                Settings.INSTANCE.setAudioLongJumpDelay(sharedPreferences.getInt(SettingsKt.KEY_AUDIO_LONG_JUMP_DELAY, 20));
            }
            Settings.INSTANCE.onAudioControlsChanged();
        }
    }
}
