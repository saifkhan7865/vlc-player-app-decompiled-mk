package org.videolan.television.ui.preferences;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.videolan.medialibrary.Tools;
import org.videolan.resources.AppContextProvider;
import org.videolan.television.ui.dialogs.ConfirmationTvActivity;
import org.videolan.tools.LocalePair;
import org.videolan.tools.LocaleUtils;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.BuildConfig;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.FeatureTouchOnlyWarningDialog;
import org.videolan.vlc.gui.dialogs.SleepTimerDialog;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\rH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001c\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0010H\u0016J\u001c\u0010\u001c\u001a\u00020\u00102\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u001f\u001a\u00020\u0010H\u0016J\b\u0010 \u001a\u00020\u0010H\u0016J\b\u0010!\u001a\u00020\u0010H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/videolan/television/ui/preferences/PreferencesUi;", "Lorg/videolan/television/ui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "currentLocale", "", "settings", "Landroid/content/SharedPreferences;", "sleepTimerPreference", "Landroidx/preference/PreferenceScreen;", "tvUiPref", "Landroidx/preference/CheckBoxPreference;", "getTitleId", "", "getXml", "manageSleepTimerSummary", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreatePreferences", "bundle", "s", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onResume", "onSharedPreferenceChanged", "sharedPreferences", "key", "onStart", "onStop", "prepareLocaleList", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesUi.kt */
public final class PreferencesUi extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String currentLocale;
    private SharedPreferences settings;
    private PreferenceScreen sleepTimerPreference;
    /* access modifiers changed from: private */
    public CheckBoxPreference tvUiPref;

    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_ui;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.interface_prefs_screen;
    }

    public void onCreate(Bundle bundle) {
        Settings settings2 = Settings.INSTANCE;
        Activity activity = getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        SharedPreferences sharedPreferences = (SharedPreferences) settings2.getInstance(activity);
        if (!sharedPreferences.contains(SettingsKt.FORCE_PLAY_ALL_VIDEO)) {
            SettingsKt.putSingle(sharedPreferences, SettingsKt.FORCE_PLAY_ALL_VIDEO, true);
        }
        super.onCreate(bundle);
        Preference findPreference = findPreference(SettingsKt.PREF_TV_UI);
        Intrinsics.checkNotNull(findPreference);
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference;
        this.tvUiPref = checkBoxPreference;
        if (checkBoxPreference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvUiPref");
            checkBoxPreference = null;
        }
        checkBoxPreference.setDefaultValue(true);
        Preference findPreference2 = findPreference(SettingsKt.KEY_APP_THEME);
        if (findPreference2 != null) {
            findPreference2.setVisible(false);
        }
        Preference findPreference3 = findPreference(SettingsKt.LIST_TITLE_ELLIPSIZE);
        if (findPreference3 != null) {
            findPreference3.setVisible(false);
        }
        Preference findPreference4 = findPreference(SettingsKt.TV_FOLDERS_FIRST);
        if (findPreference4 != null) {
            findPreference4.setVisible(true);
        }
        Preference findPreference5 = findPreference(SettingsKt.BROWSER_SHOW_HIDDEN_FILES);
        if (findPreference5 != null) {
            findPreference5.setVisible(true);
        }
        prepareLocaleList();
        this.currentLocale = AppContextProvider.INSTANCE.getLocale();
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        Settings settings2 = Settings.INSTANCE;
        Activity activity = getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        this.settings = (SharedPreferences) settings2.getInstance(activity);
        Preference findPreference = findPreference("default_sleep_timer");
        Intrinsics.checkNotNull(findPreference);
        this.sleepTimerPreference = (PreferenceScreen) findPreference;
        manageSleepTimerSummary();
    }

    public void onResume() {
        super.onResume();
        Settings settings2 = Settings.INSTANCE;
        Activity activity = getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        String string = ((SharedPreferences) settings2.getInstance(activity)).getString("set_locale", "");
        if (!Intrinsics.areEqual((Object) this.currentLocale, (Object) string)) {
            Intent intent = new Intent(getActivity(), ConfirmationTvActivity.class);
            intent.putExtra(ConfirmationTvActivity.CONFIRMATION_DIALOG_TITLE, getString(R.string.restart_vlc));
            intent.putExtra(ConfirmationTvActivity.CONFIRMATION_DIALOG_TEXT, getString(R.string.restart_message));
            getActivity().startActivityForResult(intent, 101);
            this.currentLocale = string;
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
            if (hashCode != 110738801) {
                if (hashCode != 982373561) {
                    if (hashCode == 1491861581 && str.equals(SettingsKt.BROWSER_SHOW_HIDDEN_FILES)) {
                        Settings.INSTANCE.setShowHiddenFiles(sharedPreferences.getBoolean(SettingsKt.BROWSER_SHOW_HIDDEN_FILES, false));
                    }
                } else if (str.equals(SettingsKt.TV_FOLDERS_FIRST)) {
                    Settings.INSTANCE.setTvFoldersFirst(sharedPreferences.getBoolean(SettingsKt.TV_FOLDERS_FIRST, true));
                }
            } else if (str.equals(SettingsKt.PREF_TV_UI)) {
                Settings.INSTANCE.setTvUI(sharedPreferences.getBoolean(SettingsKt.PREF_TV_UI, false));
                Activity activity = getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type org.videolan.television.ui.preferences.PreferencesActivity");
                ((PreferencesActivity) activity).setRestartApp();
            }
        }
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
                        Activity activity = getActivity();
                        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type org.videolan.television.ui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity).setRestart();
                        return true;
                    }
                    break;
                case -1742781363:
                    if (key.equals(SettingsKt.SHOW_VIDEO_THUMBNAILS)) {
                        Settings.INSTANCE.setShowVideoThumbs(((TwoStatePreference) preference).isChecked());
                        Activity activity2 = getActivity();
                        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type org.videolan.television.ui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity2).setRestart();
                        return true;
                    }
                    break;
                case 53262047:
                    if (key.equals("default_sleep_timer")) {
                        SleepTimerDialog newInstance = SleepTimerDialog.Companion.newInstance(true);
                        newInstance.setOnDismissListener(new PreferencesUi$$ExternalSyntheticLambda0(this));
                        Activity activity3 = getActivity();
                        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                        newInstance.show(((FragmentActivity) activity3).getSupportFragmentManager(), RtspHeaders.Values.TIME);
                        break;
                    }
                    break;
                case 110738801:
                    if (key.equals(SettingsKt.PREF_TV_UI)) {
                        CheckBoxPreference checkBoxPreference = this.tvUiPref;
                        CheckBoxPreference checkBoxPreference2 = null;
                        if (checkBoxPreference == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("tvUiPref");
                            checkBoxPreference = null;
                        }
                        if (!checkBoxPreference.isChecked() && Settings.INSTANCE.getDevice().isTv()) {
                            CheckBoxPreference checkBoxPreference3 = this.tvUiPref;
                            if (checkBoxPreference3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("tvUiPref");
                            } else {
                                checkBoxPreference2 = checkBoxPreference3;
                            }
                            checkBoxPreference2.setChecked(true);
                            FeatureTouchOnlyWarningDialog newInstance2 = FeatureTouchOnlyWarningDialog.Companion.newInstance(new PreferencesUi$onPreferenceTreeClick$dialog$1(this));
                            Activity activity4 = getActivity();
                            Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                            newInstance2.show(((FragmentActivity) activity4).getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(FeatureTouchOnlyWarningDialog.class).getSimpleName());
                            return true;
                        }
                    }
                    break;
                case 1939826166:
                    if (key.equals("media_seen")) {
                        Activity activity5 = getActivity();
                        Intrinsics.checkNotNull(activity5, "null cannot be cast to non-null type org.videolan.television.ui.preferences.PreferencesActivity");
                        ((PreferencesActivity) activity5).setRestart();
                        break;
                    }
                    break;
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    /* access modifiers changed from: private */
    public static final void onPreferenceTreeClick$lambda$1(PreferencesUi preferencesUi, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(preferencesUi, "this$0");
        preferencesUi.manageSleepTimerSummary();
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
        preferenceScreen.setSummary((CharSequence) getString(i, new Object[]{millisToString, str2, str}));
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
