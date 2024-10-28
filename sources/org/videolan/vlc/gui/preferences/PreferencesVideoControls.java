package org.videolan.vlc.gui.preferences;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.video.VideoPlayerActivity;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0005H\u0014J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001c\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\bH\u0002¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/gui/preferences/PreferencesVideoControls;", "Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "getTitleId", "", "getXml", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "onStop", "updateHudTimeoutSummary", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesVideoControls.kt */
public final class PreferencesVideoControls extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_video_controls;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.controls_prefs_category;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(SettingsKt.AUDIO_BOOST);
        CheckBoxPreference checkBoxPreference2 = (CheckBoxPreference) findPreference(SettingsKt.ENABLE_VOLUME_GESTURE);
        Preference findPreference = findPreference(SettingsKt.POPUP_KEEPSCREEN);
        if (findPreference != null) {
            findPreference.setVisible(!AndroidUtil.isOOrLater);
        }
        if (checkBoxPreference != null) {
            checkBoxPreference.setVisible(!AndroidDevices.INSTANCE.isAndroidTv());
        }
        Preference findPreference2 = findPreference(SettingsKt.ENABLE_DOUBLE_TAP_SEEK);
        if (findPreference2 != null) {
            findPreference2.setVisible(!AndroidDevices.INSTANCE.isAndroidTv());
        }
        Preference findPreference3 = findPreference(SettingsKt.ENABLE_DOUBLE_TAP_PLAY);
        if (findPreference3 != null) {
            findPreference3.setVisible(!AndroidDevices.INSTANCE.isAndroidTv());
        }
        Preference findPreference4 = findPreference(SettingsKt.ENABLE_SCALE_GESTURE);
        if (findPreference4 != null) {
            findPreference4.setVisible(!AndroidDevices.INSTANCE.isAndroidTv());
        }
        Preference findPreference5 = findPreference(SettingsKt.ENABLE_SWIPE_SEEK);
        if (findPreference5 != null) {
            findPreference5.setVisible(!AndroidDevices.INSTANCE.isAndroidTv());
        }
        Preference findPreference6 = findPreference(SettingsKt.ENABLE_FASTPLAY);
        if (findPreference6 != null) {
            findPreference6.setVisible(!AndroidDevices.INSTANCE.isAndroidTv());
        }
        Preference findPreference7 = findPreference(SettingsKt.FASTPLAY_SPEED);
        if (findPreference7 != null) {
            findPreference7.setVisible(!AndroidDevices.INSTANCE.isAndroidTv());
        }
        Preference findPreference8 = findPreference(SettingsKt.SCREENSHOT_MODE);
        if (findPreference8 != null) {
            findPreference8.setVisible(!AndroidDevices.INSTANCE.isAndroidTv());
        }
        if (checkBoxPreference2 != null) {
            checkBoxPreference2.setVisible(AndroidDevices.INSTANCE.getHasTsp());
        }
        Preference findPreference9 = findPreference(SettingsKt.ENABLE_BRIGHTNESS_GESTURE);
        if (findPreference9 != null) {
            findPreference9.setVisible(AndroidDevices.INSTANCE.getHasTsp());
        }
        Preference findPreference10 = findPreference(SettingsKt.POPUP_KEEPSCREEN);
        if (findPreference10 != null) {
            findPreference10.setVisible(!AndroidDevices.INSTANCE.isAndroidTv() && !AndroidUtil.isOOrLater);
        }
        Preference findPreference11 = findPreference(SettingsKt.KEY_VIDEO_DOUBLE_TAP_JUMP_DELAY);
        if (findPreference11 != null) {
            findPreference11.setTitle((CharSequence) getString(AndroidDevices.INSTANCE.isAndroidTv() ? R.string.video_key_jump_delay : R.string.video_double_tap_jump_delay));
        }
        updateHudTimeoutSummary();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Object systemService = ContextCompat.getSystemService(requireActivity, AudioManager.class);
        Intrinsics.checkNotNull(systemService);
        if (AppUtils$$ExternalSyntheticApiModelOutline0.m((AudioManager) systemService)) {
            if (checkBoxPreference != null) {
                checkBoxPreference.setChecked(false);
            }
            if (checkBoxPreference != null) {
                checkBoxPreference.setEnabled(false);
            }
            if (checkBoxPreference != null) {
                checkBoxPreference.setSummary((CharSequence) getString(R.string.system_volume_disabled, checkBoxPreference.getSummary()));
            }
            if (checkBoxPreference2 != null) {
                checkBoxPreference2.setChecked(false);
            }
            if (checkBoxPreference2 != null) {
                checkBoxPreference2.setEnabled(false);
            }
            if (checkBoxPreference2 != null) {
                checkBoxPreference2.setSummary((CharSequence) getString(R.string.system_volume_disabled, checkBoxPreference2.getSummary()));
            }
        }
    }

    private final void updateHudTimeoutSummary() {
        if (Settings.INSTANCE.getVideoHudDelay() == -1) {
            Preference findPreference = findPreference(SettingsKt.VIDEO_HUD_TIMEOUT);
            if (findPreference != null) {
                findPreference.setSummary((CharSequence) getString(R.string.timeout_infinite));
                return;
            }
            return;
        }
        Preference findPreference2 = findPreference(SettingsKt.VIDEO_HUD_TIMEOUT);
        if (findPreference2 != null) {
            findPreference2.setSummary((CharSequence) getString(R.string.video_hud_timeout_summary, String.valueOf(Settings.INSTANCE.getVideoHudDelay())));
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
            FragmentActivity activity = getActivity();
            VideoPlayerActivity videoPlayerActivity = activity instanceof VideoPlayerActivity ? (VideoPlayerActivity) activity : null;
            if (videoPlayerActivity != null) {
                videoPlayerActivity.onChangedControlSetting(str);
            }
            switch (str.hashCode()) {
                case -1808407112:
                    if (str.equals(SettingsKt.KEY_VIDEO_DOUBLE_TAP_JUMP_DELAY)) {
                        Settings.INSTANCE.setVideoDoubleTapJumpDelay(sharedPreferences.getInt(SettingsKt.KEY_VIDEO_DOUBLE_TAP_JUMP_DELAY, 20));
                        return;
                    }
                    return;
                case -1333072778:
                    if (str.equals(SettingsKt.KEY_VIDEO_JUMP_DELAY)) {
                        Settings.INSTANCE.setVideoJumpDelay(sharedPreferences.getInt(SettingsKt.KEY_VIDEO_JUMP_DELAY, 10));
                        return;
                    }
                    return;
                case -1120154408:
                    if (str.equals(SettingsKt.FASTPLAY_SPEED)) {
                        Settings settings = Settings.INSTANCE;
                        String string = sharedPreferences.getString(SettingsKt.FASTPLAY_SPEED, "2");
                        settings.setFastplaySpeed(string != null ? Float.parseFloat(string) : 2.0f);
                        return;
                    }
                    return;
                case 20821009:
                    if (str.equals(SettingsKt.KEY_VIDEO_LONG_JUMP_DELAY)) {
                        Settings.INSTANCE.setVideoLongJumpDelay(sharedPreferences.getInt(SettingsKt.KEY_VIDEO_LONG_JUMP_DELAY, 20));
                        return;
                    }
                    return;
                case 2075284867:
                    if (str.equals(SettingsKt.VIDEO_HUD_TIMEOUT)) {
                        Settings.INSTANCE.setVideoHudDelay(SettingsKt.coerceInOrDefault(sharedPreferences.getInt(SettingsKt.VIDEO_HUD_TIMEOUT, 4), 1, 15, -1));
                        updateHudTimeoutSummary();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
