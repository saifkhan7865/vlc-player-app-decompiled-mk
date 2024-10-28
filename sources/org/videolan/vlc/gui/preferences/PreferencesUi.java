package org.videolan.vlc.gui.preferences;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.tools.LocalePair;
import org.videolan.tools.LocaleUtils;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.BuildConfig;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.SleepTimerDialog;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\tH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001c\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010\u001c\u001a\u00020\fH\u0016J\b\u0010\u001d\u001a\u00020\fH\u0002J\b\u0010\u001e\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/videolan/vlc/gui/preferences/PreferencesUi;", "Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "settings", "Landroid/content/SharedPreferences;", "sleepTimerPreference", "Landroidx/preference/PreferenceScreen;", "getTitleId", "", "getXml", "manageSleepTimerSummary", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreatePreferences", "bundle", "s", "", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onSharedPreferenceChanged", "sharedPreferences", "key", "onStart", "onStop", "prepareLocaleList", "setupTheme", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesUi.kt */
public final class PreferencesUi extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SharedPreferences settings;
    private PreferenceScreen sleepTimerPreference;

    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_ui;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.interface_prefs_screen;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        prepareLocaleList();
        setupTheme();
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        EditTextPreference editTextPreference = (EditTextPreference) getPreferenceManager().findPreference("video_group_size");
        if (editTextPreference != null) {
            editTextPreference.setOnBindEditTextListener(new PreferencesUi$$ExternalSyntheticLambda0());
        }
        if (editTextPreference != null) {
            editTextPreference.setSummaryProvider(new PreferencesUi$$ExternalSyntheticLambda1(this));
        }
        Settings settings2 = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.settings = (SharedPreferences) settings2.getInstance(requireActivity);
        Preference findPreference = findPreference("default_sleep_timer");
        Intrinsics.checkNotNull(findPreference);
        this.sleepTimerPreference = (PreferenceScreen) findPreference;
        manageSleepTimerSummary();
    }

    /* access modifiers changed from: private */
    public static final void onCreatePreferences$lambda$0(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "editText");
        editText.setInputType(2);
    }

    /* access modifiers changed from: private */
    public static final CharSequence onCreatePreferences$lambda$1(PreferencesUi preferencesUi, EditTextPreference editTextPreference) {
        Intrinsics.checkNotNullParameter(preferencesUi, "this$0");
        Intrinsics.checkNotNullParameter(editTextPreference, "preference");
        String text = editTextPreference.getText();
        CharSequence charSequence = text;
        if (charSequence == null || charSequence.length() == 0) {
            return "";
        }
        return preferencesUi.getString(R.string.video_group_size_summary, text);
    }

    private final void setupTheme() {
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        if (!sharedPreferences.contains(SettingsKt.KEY_APP_THEME)) {
            int i = 0;
            if (!sharedPreferences.getBoolean(SettingsKt.KEY_DAYNIGHT, false) || AndroidDevices.INSTANCE.canUseSystemNightMode()) {
                i = sharedPreferences.contains(SettingsKt.KEY_BLACK_THEME) ? sharedPreferences.getBoolean(SettingsKt.KEY_BLACK_THEME, false) ? 2 : 1 : -1;
            }
            SettingsKt.putSingle(sharedPreferences, SettingsKt.KEY_APP_THEME, String.valueOf(i));
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

    public boolean onPreferenceTreeClick(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        if (preference.getKey() == null) {
            return false;
        }
        String key = preference.getKey();
        if (key != null) {
            switch (key.hashCode()) {
                case -2058050492:
                    if (key.equals(SettingsKt.KEY_SHOW_HEADERS)) {
                        Settings.INSTANCE.setShowHeaders(((TwoStatePreference) preference).isChecked());
                        FragmentActivity activity = getActivity();
                        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity).setRestart();
                        return true;
                    }
                    break;
                case -1742781363:
                    if (key.equals(SettingsKt.SHOW_VIDEO_THUMBNAILS)) {
                        Settings.INSTANCE.setShowVideoThumbs(((TwoStatePreference) preference).isChecked());
                        FragmentActivity activity2 = getActivity();
                        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity2).setRestart();
                        return true;
                    }
                    break;
                case 53262047:
                    if (key.equals("default_sleep_timer")) {
                        SleepTimerDialog newInstance = SleepTimerDialog.Companion.newInstance(true);
                        newInstance.setOnDismissListener(new PreferencesUi$$ExternalSyntheticLambda2(this));
                        FragmentActivity activity3 = getActivity();
                        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                        newInstance.show(activity3.getSupportFragmentManager(), RtspHeaders.Values.TIME);
                        break;
                    }
                    break;
                case 110738801:
                    if (key.equals(SettingsKt.PREF_TV_UI)) {
                        Settings.INSTANCE.setTvUI(((TwoStatePreference) preference).isChecked());
                        FragmentActivity activity4 = getActivity();
                        Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity4).setRestartApp();
                        return true;
                    }
                    break;
                case 1121677874:
                    if (key.equals(SettingsKt.KEY_ARTISTS_SHOW_ALL)) {
                        FragmentActivity activity5 = getActivity();
                        Intrinsics.checkNotNull(activity5, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity5).updateArtists();
                        break;
                    }
                    break;
                case 1939826166:
                    if (key.equals("media_seen")) {
                        requireActivity().setResult(5);
                        break;
                    }
                    break;
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    /* access modifiers changed from: private */
    public static final void onPreferenceTreeClick$lambda$2(PreferencesUi preferencesUi, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(preferencesUi, "this$0");
        preferencesUi.manageSleepTimerSummary();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences != null && str != null) {
            switch (str.hashCode()) {
                case -1374946089:
                    if (str.equals("set_locale")) {
                        FragmentActivity activity = getActivity();
                        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity).setRestart();
                        UiTools uiTools = UiTools.INSTANCE;
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                        UiTools.restartDialog$default(uiTools, requireActivity, false, 0, (Object) null, 14, (Object) null);
                        return;
                    }
                    return;
                case 199561221:
                    if (str.equals("video_group_size")) {
                        int i = 6;
                        try {
                            Settings settings2 = Settings.INSTANCE;
                            FragmentActivity requireActivity2 = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                            String string = ((SharedPreferences) settings2.getInstance(requireActivity2)).getString(str, Constants.GROUP_VIDEOS_NAME);
                            if (string != null) {
                                i = Integer.parseInt(string);
                            }
                        } catch (NumberFormatException unused) {
                        }
                        Medialibrary.getInstance().setVideoGroupsPrefixLength(i);
                        FragmentActivity activity2 = getActivity();
                        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity2).setRestart();
                        return;
                    }
                    return;
                case 312988613:
                    if (str.equals(SettingsKt.LIST_TITLE_ELLIPSIZE)) {
                        Settings settings3 = Settings.INSTANCE;
                        String string2 = sharedPreferences.getString(SettingsKt.LIST_TITLE_ELLIPSIZE, Constants.GROUP_VIDEOS_FOLDER);
                        settings3.setListTitleEllipsize(string2 != null ? Integer.parseInt(string2) : 0);
                        FragmentActivity activity3 = getActivity();
                        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity3).setRestart();
                        return;
                    }
                    return;
                case 678188239:
                    if (str.equals(SettingsKt.KEY_INCLUDE_MISSING)) {
                        Settings.INSTANCE.setIncludeMissing(sharedPreferences.getBoolean(str, true));
                        FragmentActivity activity4 = getActivity();
                        Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity4).setRestart();
                        return;
                    }
                    return;
                case 1402163383:
                    if (str.equals(Constants.KEY_GROUP_VIDEOS)) {
                        FragmentActivity activity5 = getActivity();
                        Intrinsics.checkNotNull(activity5, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity5).setRestart();
                        return;
                    }
                    return;
                case 1843099179:
                    if (str.equals(SettingsKt.KEY_APP_THEME)) {
                        CharSequence locale = AppContextProvider.INSTANCE.getLocale();
                        if (locale == null || locale.length() == 0) {
                            FragmentActivity activity6 = getActivity();
                            Intrinsics.checkNotNull(activity6, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
                            ((PreferencesActivity) activity6).exitAndRescan();
                            return;
                        }
                        UiTools uiTools2 = UiTools.INSTANCE;
                        FragmentActivity requireActivity3 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                        UiTools.restartDialog$default(uiTools2, requireActivity3, false, 0, (Object) null, 14, (Object) null);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private final void manageSleepTimerSummary() {
        SharedPreferences sharedPreferences = this.settings;
        PreferenceScreen preferenceScreen = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        long j = sharedPreferences.getLong(SettingsKt.SLEEP_TIMER_DEFAULT_INTERVAL, -1);
        if (j == -1) {
            PreferenceScreen preferenceScreen2 = this.sleepTimerPreference;
            if (preferenceScreen2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sleepTimerPreference");
            } else {
                preferenceScreen = preferenceScreen2;
            }
            preferenceScreen.setSummary((CharSequence) getString(R.string.disabled));
            return;
        }
        SharedPreferences sharedPreferences2 = this.settings;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences2 = null;
        }
        boolean z = sharedPreferences2.getBoolean(SettingsKt.SLEEP_TIMER_DEFAULT_WAIT, false);
        SharedPreferences sharedPreferences3 = this.settings;
        if (sharedPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences3 = null;
        }
        boolean z2 = sharedPreferences3.getBoolean(SettingsKt.SLEEP_TIMER_DEFAULT_RESET_INTERACTION, false);
        PreferenceScreen preferenceScreen3 = this.sleepTimerPreference;
        if (preferenceScreen3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sleepTimerPreference");
        } else {
            preferenceScreen = preferenceScreen3;
        }
        int i = R.string.default_sleep_timer_summary;
        String millisToString = Tools.millisToString(j);
        String str = "true";
        String str2 = z ? str : "false";
        if (!z2) {
            str = "false";
        }
        preferenceScreen.setSummary((CharSequence) getString(i, millisToString, str2, str));
    }

    private final void prepareLocaleList() {
        LocaleUtils localeUtils = LocaleUtils.INSTANCE;
        String[] strArr = BuildConfig.TRANSLATION_ARRAY;
        Intrinsics.checkNotNullExpressionValue(strArr, "TRANSLATION_ARRAY");
        String string = getString(R.string.device_default);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        LocalePair localesUsedInProject = localeUtils.getLocalesUsedInProject(strArr, string);
        ListPreference listPreference = (ListPreference) findPreference("set_locale");
        if (listPreference != null) {
            listPreference.setEntries((CharSequence[]) localesUsedInProject.getLocaleEntries());
        }
        if (listPreference != null) {
            listPreference.setEntryValues((CharSequence[]) localesUsedInProject.getLocaleEntryValues());
        }
    }
}
