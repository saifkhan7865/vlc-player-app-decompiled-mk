package org.videolan.television.ui.preferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.Constants;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.PinCodeActivity;
import org.videolan.vlc.gui.PinCodeReason;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.ConfirmAudioPlayQueueDialog;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0005H\u0014J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0017J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001c\u0010\u0014\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016¨\u0006\u001a"}, d2 = {"Lorg/videolan/television/ui/preferences/PreferencesFragment;", "Lorg/videolan/television/ui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "getTitleId", "", "getXml", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesFragment.kt */
public final class PreferencesFragment extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.preferences;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Preference findPreference = findPreference(SettingsKt.SCREEN_ORIENTATION);
        boolean z = false;
        if (findPreference != null) {
            findPreference.setVisible(false);
        }
        Preference findPreference2 = findPreference("casting_category");
        if (findPreference2 != null) {
            findPreference2.setVisible(false);
        }
        Preference findPreference3 = findPreference(SettingsKt.KEY_VIDEO_APP_SWITCH);
        if (findPreference3 != null) {
            findPreference3.setVisible(AndroidDevices.INSTANCE.getHasPiP());
        }
        Preference findPreference4 = findPreference("remote_access_category");
        if (findPreference4 != null) {
            if (Build.VERSION.SDK_INT >= 22) {
                z = true;
            }
            findPreference4.setVisible(z);
        }
    }

    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Deprecated(message = "Deprecated in Java")
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 0) {
            PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference("parental_control");
            Intrinsics.checkNotNull(preferenceScreen);
            onPreferenceTreeClick(preferenceScreen);
        }
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        Activity activity = getActivity();
        if (activity == null) {
            return false;
        }
        String key = preference.getKey();
        if (key != null) {
            switch (key.hashCode()) {
                case -1626208309:
                    if (key.equals(Constants.ID_DIRECTORIES)) {
                        if (Medialibrary.getInstance().isWorking()) {
                            Toast.makeText(activity, getString(R.string.settings_ml_block_scan), 0).show();
                            return true;
                        }
                        Intent intent = new Intent(activity.getApplicationContext(), SecondaryActivity.class);
                        intent.putExtra(SecondaryActivity.KEY_FRAGMENT, SecondaryActivity.STORAGE_BROWSER);
                        startActivity(intent);
                        getActivity().setResult(3);
                        return true;
                    }
                    break;
                case 307853683:
                    if (key.equals("parental_control")) {
                        Settings settings = Settings.INSTANCE;
                        Activity activity2 = getActivity();
                        Intrinsics.checkNotNullExpressionValue(activity2, "getActivity(...)");
                        if (settings.isPinCodeSet(activity2)) {
                            return super.onPreferenceTreeClick(preference);
                        }
                        PinCodeActivity.Companion companion = PinCodeActivity.Companion;
                        Activity activity3 = getActivity();
                        Intrinsics.checkNotNullExpressionValue(activity3, "getActivity(...)");
                        startActivityForResult(companion.getIntent(activity3, PinCodeReason.FIRST_CREATION), 0);
                        return true;
                    }
                    break;
                case 1293657988:
                    if (key.equals(SettingsKt.AUDIO_RESUME_PLAYBACK)) {
                        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(SettingsKt.AUDIO_RESUME_PLAYBACK);
                        if (checkBoxPreference != null && !checkBoxPreference.isChecked()) {
                            ConfirmAudioPlayQueueDialog confirmAudioPlayQueueDialog = new ConfirmAudioPlayQueueDialog();
                            Activity activity4 = getActivity();
                            Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                            confirmAudioPlayQueueDialog.show(((FragmentActivity) activity4).getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(ConfirmAudioPlayQueueDialog.class).getSimpleName());
                            confirmAudioPlayQueueDialog.setListener(new PreferencesFragment$onPreferenceTreeClick$1(this, checkBoxPreference));
                            checkBoxPreference.setChecked(true);
                        }
                        return true;
                    }
                    break;
                case 1741305385:
                    if (key.equals(SettingsKt.VIDEO_RESUME_PLAYBACK)) {
                        Settings settings2 = Settings.INSTANCE;
                        Activity activity5 = getActivity();
                        Intrinsics.checkNotNullExpressionValue(activity5, "getActivity(...)");
                        ((SharedPreferences) settings2.getInstance(activity5)).edit().remove("media_list").remove(Constants.KEY_MEDIA_LAST_PLAYLIST_RESUME).remove(Constants.KEY_CURRENT_MEDIA_RESUME).remove(Constants.KEY_CURRENT_MEDIA).apply();
                        Activity activity6 = getActivity();
                        if (activity6 != null) {
                            activity6.setResult(3);
                        }
                        return true;
                    }
                    break;
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (Intrinsics.areEqual((Object) str, (Object) SettingsKt.PLAYBACK_HISTORY)) {
            Intrinsics.checkNotNull(sharedPreferences);
            if (sharedPreferences.getBoolean(str, true)) {
                CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(SettingsKt.AUDIO_RESUME_PLAYBACK);
                if (checkBoxPreference != null) {
                    checkBoxPreference.setChecked(true);
                }
                CheckBoxPreference checkBoxPreference2 = (CheckBoxPreference) findPreference(SettingsKt.VIDEO_RESUME_PLAYBACK);
                if (checkBoxPreference2 != null) {
                    checkBoxPreference2.setChecked(true);
                }
            }
        }
    }
}
