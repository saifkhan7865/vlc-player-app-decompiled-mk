package org.videolan.television.ui.preferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceDialogFragment;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.libvlc.util.HWDecoderUtil;
import org.videolan.resources.Constants;
import org.videolan.tools.LocalePair;
import org.videolan.tools.LocaleUtils;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.BuildConfig;
import org.videolan.vlc.MainVersionKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.browser.BaseBrowserFragmentKt;
import org.videolan.vlc.gui.browser.FilePickerActivity;
import org.videolan.vlc.gui.browser.FilePickerFragmentKt;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.providers.PickerType;
import org.videolan.vlc.util.LocaleUtil;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\fH\u0014J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u001c\u0010\u001c\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u000fH\u0016J\b\u0010\"\u001a\u00020\u000fH\u0002J\u000e\u0010#\u001a\u00020\u000fH@¢\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u000fH\u0002J\b\u0010&\u001a\u00020\u000fH\u0002R\u0012\u0010\u0005\u001a\u00020\u0006X\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/videolan/television/ui/preferences/PreferencesAudio;", "Lorg/videolan/television/ui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "preferredAudioTrack", "Landroidx/preference/ListPreference;", "getTitleId", "", "getXml", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDisplayPreferenceDialog", "preference", "Landroidx/preference/Preference;", "onPreferenceTreeClick", "", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "prepareLocaleList", "restartLibVLC", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePassThroughSummary", "updatePreferredAudioTrack", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesAudio.kt */
public final class PreferencesAudio extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();
    private ListPreference preferredAudioTrack;

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_audio;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.audio_prefs_category;
    }

    public void onCreate(Bundle bundle) {
        Preference findPreference;
        super.onCreate(bundle);
        Preference findPreference2 = findPreference("enable_headset_detection");
        if (findPreference2 != null) {
            findPreference2.setVisible(false);
        }
        Preference findPreference3 = findPreference("enable_play_on_headset_insertion");
        if (findPreference3 != null) {
            findPreference3.setVisible(false);
        }
        Preference findPreference4 = findPreference("ignore_headset_media_button_presses");
        if (findPreference4 != null) {
            findPreference4.setVisible(false);
        }
        Preference findPreference5 = findPreference("headset_prefs_category");
        if (findPreference5 != null) {
            findPreference5.setVisible(false);
        }
        ListPreference listPreference = (ListPreference) findPreference("aout");
        Preference findPreference6 = findPreference(SettingsKt.RESUME_PLAYBACK);
        if (findPreference6 != null) {
            findPreference6.setVisible(false);
        }
        Preference findPreference7 = findPreference(SettingsKt.AUDIO_DUCKING);
        if (findPreference7 != null) {
            findPreference7.setVisible(!AndroidUtil.isOOrLater);
        }
        if (!(HWDecoderUtil.getAudioOutputFromDevice() == HWDecoderUtil.AudioOutput.ALL || listPreference == null)) {
            listPreference.setVisible(false);
        }
        if (MainVersionKt.isVLC4() && Build.VERSION.SDK_INT >= 28) {
            if (listPreference != null) {
                listPreference.setEntryValues((CharSequence[]) getActivity().getResources().getStringArray(R.array.aouts_complete_values));
            }
            if (listPreference != null) {
                listPreference.setEntries((CharSequence[]) getActivity().getResources().getStringArray(R.array.aouts_complete));
            }
        }
        updatePassThroughSummary();
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        if (Intrinsics.areEqual((Object) "2", (Object) sharedPreferences.getString("aout", Constants.GROUP_VIDEOS_FOLDER)) && (findPreference = findPreference("audio_digital_output")) != null) {
            findPreference.setVisible(false);
        }
        Preference findPreference8 = findPreference(SettingsKt.AUDIO_PREFERRED_LANGUAGE);
        Intrinsics.checkNotNull(findPreference8);
        this.preferredAudioTrack = (ListPreference) findPreference8;
        updatePreferredAudioTrack();
        prepareLocaleList();
    }

    public void onDisplayPreferenceDialog(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        PreferenceDialogFragment buildPreferenceDialogFragment = super.buildPreferenceDialogFragment(preference);
        if (buildPreferenceDialogFragment instanceof CustomEditTextPreferenceDialogFragment) {
            String key = preference.getKey();
            if (Intrinsics.areEqual((Object) key, (Object) "audio-replay-gain-default") || Intrinsics.areEqual((Object) key, (Object) "audio-replay-gain-preamp")) {
                CustomEditTextPreferenceDialogFragment customEditTextPreferenceDialogFragment = (CustomEditTextPreferenceDialogFragment) buildPreferenceDialogFragment;
                customEditTextPreferenceDialogFragment.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                customEditTextPreferenceDialogFragment.setInputType(12290);
                return;
            }
            return;
        }
        super.onDisplayPreferenceDialog(preference);
    }

    private final void updatePreferredAudioTrack() {
        Settings settings = Settings.INSTANCE;
        Activity activity = getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        ListPreference listPreference = null;
        String string = ((SharedPreferences) settings.getInstance(activity)).getString(SettingsKt.AUDIO_PREFERRED_LANGUAGE, (String) null);
        CharSequence charSequence = string;
        if (charSequence == null || charSequence.length() == 0) {
            ListPreference listPreference2 = this.preferredAudioTrack;
            if (listPreference2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("preferredAudioTrack");
            } else {
                listPreference = listPreference2;
            }
            listPreference.setSummary(getString(R.string.no_track_preference));
            return;
        }
        ListPreference listPreference3 = this.preferredAudioTrack;
        if (listPreference3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferredAudioTrack");
        } else {
            listPreference = listPreference3;
        }
        listPreference.setSummary(getString(R.string.track_preference, new Object[]{LocaleUtil.INSTANCE.getLocaleName(string)}));
    }

    private final void updatePassThroughSummary() {
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        boolean z = sharedPreferences.getBoolean("audio_digital_output", false);
        Preference findPreference = findPreference("audio_digital_output");
        if (findPreference != null) {
            findPreference.setSummary(z ? R.string.audio_digital_output_enabled : R.string.audio_digital_output_disabled);
        }
    }

    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c9, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) "audio-replay-gain-default") == false) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00cb, code lost:
        r1 = "-7.0";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ce, code lost:
        r1 = "0.0";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d0, code lost:
        r2 = r12.getString(r13, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r3 = new java.text.DecimalFormat("###0.0###", new java.text.DecimalFormatSymbols(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e2, code lost:
        if (r2 == null) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e4, code lost:
        r5 = java.lang.Double.valueOf(java.lang.Double.parseDouble(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ed, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ee, code lost:
        r3 = r3.format(r5);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, "format(...)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00fb, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r2) != false) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00fd, code lost:
        r12 = r12.edit();
        r12.putString(r13, r3);
        r13 = (androidx.preference.EditTextPreference) findPreference(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x010c, code lost:
        if (r13 == null) goto L_0x0111;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x010e, code lost:
        r13.setText(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0111, code lost:
        r12.apply();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0116, code lost:
        kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r11, (kotlin.coroutines.CoroutineContext) null, (kotlinx.coroutines.CoroutineStart) null, new org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4(r11, (kotlin.coroutines.Continuation<? super org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4>) null), 3, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x012b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x012d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r6 = new java.lang.StringBuilder("Could not parse value: ");
        r6.append(r2);
        r6.append(". Setting ");
        r6.append(r13);
        r6.append(" to ");
        r0 = r1;
        r6.append(r1);
        android.util.Log.w("VLC/PreferencesAudio", r6.toString(), r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0158, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2) == false) goto L_0x015a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x015a, code lost:
        r12 = r12.edit();
        r0 = r1;
        r12.putString(r13, r1);
        r13 = (androidx.preference.EditTextPreference) findPreference(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x016c, code lost:
        if (r13 != null) goto L_0x016e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x016e, code lost:
        r13.setText(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0172, code lost:
        kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r11, (kotlin.coroutines.CoroutineContext) null, (kotlinx.coroutines.CoroutineStart) null, new org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4(r11, (kotlin.coroutines.Continuation<? super org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4>) null), 3, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0189, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2) == false) goto L_0x018b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x018b, code lost:
        r12 = r12.edit();
        r2 = r1;
        r12.putString(r13, r1);
        r13 = (androidx.preference.EditTextPreference) findPreference(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x019d, code lost:
        if (r13 != null) goto L_0x019f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x019f, code lost:
        r13.setText(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01a2, code lost:
        r12.apply();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a6, code lost:
        kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r11, (kotlin.coroutines.CoroutineContext) null, (kotlinx.coroutines.CoroutineStart) null, new org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4(r11, (kotlin.coroutines.Continuation<? super org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4>) null), 3, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01b8, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01c2, code lost:
        kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r11, (kotlin.coroutines.CoroutineContext) null, (kotlinx.coroutines.CoroutineStart) null, new org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$2(r11, (kotlin.coroutines.Continuation<? super org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$2>) null), 3, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onSharedPreferenceChanged(android.content.SharedPreferences r12, java.lang.String r13) {
        /*
            r11 = this;
            java.lang.String r0 = "Could not parse value: "
            if (r12 == 0) goto L_0x01d4
            if (r13 != 0) goto L_0x0008
            goto L_0x01d4
        L_0x0008:
            int r1 = r13.hashCode()
            java.lang.String r2 = "audio-replay-gain-default"
            java.lang.String r3 = "audio_digital_output"
            r4 = 0
            switch(r1) {
                case -960388376: goto L_0x01b9;
                case -191150686: goto L_0x00bd;
                case 3000141: goto L_0x0067;
                case 10218978: goto L_0x005d;
                case 417433745: goto L_0x0050;
                case 623523935: goto L_0x0041;
                case 1000457730: goto L_0x0037;
                case 1319190720: goto L_0x002d;
                case 1572272419: goto L_0x0016;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x01d4
        L_0x0016:
            java.lang.String r0 = "playback_speed"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x0020
            goto L_0x01d4
        L_0x0020:
            r13 = 1065353216(0x3f800000, float:1.0)
            java.lang.Float r13 = java.lang.Float.valueOf(r13)
            java.lang.String r0 = "playback_rate"
            org.videolan.tools.SettingsKt.putSingle(r12, r0, r13)
            goto L_0x01d4
        L_0x002d:
            java.lang.String r1 = "audio-replay-gain-preamp"
            boolean r1 = r13.equals(r1)
            if (r1 != 0) goto L_0x00c5
            goto L_0x01d4
        L_0x0037:
            java.lang.String r12 = "audio-replay-gain-enable"
            boolean r12 = r13.equals(r12)
            if (r12 != 0) goto L_0x01c2
            goto L_0x01d4
        L_0x0041:
            java.lang.String r12 = "audio_preferred_language"
            boolean r12 = r13.equals(r12)
            if (r12 != 0) goto L_0x004b
            goto L_0x01d4
        L_0x004b:
            r11.updatePreferredAudioTrack()
            goto L_0x01d4
        L_0x0050:
            boolean r12 = r13.equals(r3)
            if (r12 != 0) goto L_0x0058
            goto L_0x01d4
        L_0x0058:
            r11.updatePassThroughSummary()
            goto L_0x01d4
        L_0x005d:
            java.lang.String r12 = "audio-replay-gain-mode"
            boolean r12 = r13.equals(r12)
            if (r12 != 0) goto L_0x01c2
            goto L_0x01d4
        L_0x0067:
            java.lang.String r12 = "aout"
            boolean r13 = r13.equals(r12)
            if (r13 != 0) goto L_0x0071
            goto L_0x01d4
        L_0x0071:
            r5 = r11
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$1 r13 = new org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$1
            r13.<init>(r11, r4)
            r8 = r13
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
            androidx.preference.PreferenceManager r13 = r11.getPreferenceManager()
            android.content.SharedPreferences r13 = r13.getSharedPreferences()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
            java.lang.String r0 = "0"
            java.lang.String r12 = r13.getString(r12, r0)
            java.lang.String r13 = "2"
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r12)
            if (r12 == 0) goto L_0x00ac
            r13 = r3
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            androidx.preference.Preference r13 = r11.findPreference(r13)
            androidx.preference.CheckBoxPreference r13 = (androidx.preference.CheckBoxPreference) r13
            if (r13 != 0) goto L_0x00a8
            goto L_0x00ac
        L_0x00a8:
            r0 = 0
            r13.setChecked(r0)
        L_0x00ac:
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            androidx.preference.Preference r13 = r11.findPreference(r3)
            if (r13 != 0) goto L_0x00b6
            goto L_0x01d4
        L_0x00b6:
            r12 = r12 ^ 1
            r13.setVisible(r12)
            goto L_0x01d4
        L_0x00bd:
            boolean r1 = r13.equals(r2)
            if (r1 != 0) goto L_0x00c5
            goto L_0x01d4
        L_0x00c5:
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x00ce
            java.lang.String r1 = "-7.0"
            goto L_0x00d0
        L_0x00ce:
            java.lang.String r1 = "0.0"
        L_0x00d0:
            java.lang.String r2 = r12.getString(r13, r1)
            java.text.DecimalFormat r3 = new java.text.DecimalFormat     // Catch:{ IllegalArgumentException -> 0x012d }
            java.lang.String r5 = "###0.0###"
            java.text.DecimalFormatSymbols r6 = new java.text.DecimalFormatSymbols     // Catch:{ IllegalArgumentException -> 0x012d }
            java.util.Locale r7 = java.util.Locale.ENGLISH     // Catch:{ IllegalArgumentException -> 0x012d }
            r6.<init>(r7)     // Catch:{ IllegalArgumentException -> 0x012d }
            r3.<init>(r5, r6)     // Catch:{ IllegalArgumentException -> 0x012d }
            if (r2 == 0) goto L_0x00ed
            double r5 = java.lang.Double.parseDouble(r2)     // Catch:{ IllegalArgumentException -> 0x012d }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ IllegalArgumentException -> 0x012d }
            goto L_0x00ee
        L_0x00ed:
            r5 = r4
        L_0x00ee:
            java.lang.String r3 = r3.format(r5)     // Catch:{ IllegalArgumentException -> 0x012d }
            java.lang.String r5 = "format(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch:{ IllegalArgumentException -> 0x012d }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r2)
            if (r0 != 0) goto L_0x0116
            android.content.SharedPreferences$Editor r12 = r12.edit()
            r12.putString(r13, r3)
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            androidx.preference.Preference r13 = r11.findPreference(r13)
            androidx.preference.EditTextPreference r13 = (androidx.preference.EditTextPreference) r13
            if (r13 == 0) goto L_0x0111
            r13.setText(r3)
        L_0x0111:
            r12.apply()
            goto L_0x01d4
        L_0x0116:
            r12 = r11
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4 r13 = new org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4
            r13.<init>(r11, r4)
            r7 = r13
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 3
            r9 = 0
            r5 = 0
            r6 = 0
            r4 = r12
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r4, r5, r6, r7, r8, r9)
            goto L_0x01d4
        L_0x012b:
            r0 = move-exception
            goto L_0x0185
        L_0x012d:
            r3 = move-exception
            java.lang.String r5 = "VLC/PreferencesAudio"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x012b }
            r6.<init>(r0)     // Catch:{ all -> 0x012b }
            r6.append(r2)     // Catch:{ all -> 0x012b }
            java.lang.String r0 = ". Setting "
            r6.append(r0)     // Catch:{ all -> 0x012b }
            r6.append(r13)     // Catch:{ all -> 0x012b }
            java.lang.String r0 = " to "
            r6.append(r0)     // Catch:{ all -> 0x012b }
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x012b }
            r6.append(r1)     // Catch:{ all -> 0x012b }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x012b }
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch:{ all -> 0x012b }
            android.util.Log.w(r5, r0, r3)     // Catch:{ all -> 0x012b }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r0 != 0) goto L_0x0172
            android.content.SharedPreferences$Editor r12 = r12.edit()
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
            r12.putString(r13, r1)
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            androidx.preference.Preference r13 = r11.findPreference(r13)
            androidx.preference.EditTextPreference r13 = (androidx.preference.EditTextPreference) r13
            if (r13 == 0) goto L_0x0111
            r13.setText(r1)
            goto L_0x0111
        L_0x0172:
            r2 = r11
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4 r12 = new org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4
            r12.<init>(r11, r4)
            r5 = r12
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 3
            r7 = 0
            r3 = 0
            r4 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
            goto L_0x01d4
        L_0x0185:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r2 != 0) goto L_0x01a6
            android.content.SharedPreferences$Editor r12 = r12.edit()
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2
            r12.putString(r13, r1)
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            androidx.preference.Preference r13 = r11.findPreference(r13)
            androidx.preference.EditTextPreference r13 = (androidx.preference.EditTextPreference) r13
            if (r13 == 0) goto L_0x01a2
            r13.setText(r1)
        L_0x01a2:
            r12.apply()
            goto L_0x01b8
        L_0x01a6:
            r2 = r11
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4 r12 = new org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$4
            r12.<init>(r11, r4)
            r5 = r12
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 3
            r7 = 0
            r3 = 0
            r4 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
        L_0x01b8:
            throw r0
        L_0x01b9:
            java.lang.String r12 = "audio-replay-gain-peak-protection"
            boolean r12 = r13.equals(r12)
            if (r12 != 0) goto L_0x01c2
            goto L_0x01d4
        L_0x01c2:
            r5 = r11
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$2 r12 = new org.videolan.television.ui.preferences.PreferencesAudio$onSharedPreferenceChanged$2
            r12.<init>(r11, r4)
            r8 = r12
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
        L_0x01d4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.preferences.PreferencesAudio.onSharedPreferenceChanged(android.content.SharedPreferences, java.lang.String):void");
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        if (preference.getKey() == null) {
            return false;
        }
        if (Intrinsics.areEqual((Object) preference.getKey(), (Object) "soundfont")) {
            Intent intent = new Intent(getActivity(), FilePickerActivity.class);
            intent.putExtra(BaseBrowserFragmentKt.KEY_PICKER_TYPE, PickerType.SOUNDFONT.ordinal());
            startActivityForResult(intent, 10000);
        }
        return super.onPreferenceTreeClick(preference);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i == 10000 && intent.hasExtra(FilePickerFragmentKt.EXTRA_MRL)) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PreferencesAudio$onActivityResult$1(this, intent, (Continuation<? super PreferencesAudio$onActivityResult$1>) null), 3, (Object) null);
            UiTools uiTools = UiTools.INSTANCE;
            Activity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            uiTools.restartDialog(activity, true, BasePreferenceFragmentKt.RESTART_CODE, this);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object restartLibVLC(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof org.videolan.television.ui.preferences.PreferencesAudio$restartLibVLC$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.television.ui.preferences.PreferencesAudio$restartLibVLC$1 r0 = (org.videolan.television.ui.preferences.PreferencesAudio$restartLibVLC$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.television.ui.preferences.PreferencesAudio$restartLibVLC$1 r0 = new org.videolan.television.ui.preferences.PreferencesAudio$restartLibVLC$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0050
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0047
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r6)
            org.videolan.resources.VLCInstance r6 = org.videolan.resources.VLCInstance.INSTANCE
            r0.label = r4
            java.lang.Object r6 = r6.restart(r0)
            if (r6 != r1) goto L_0x0047
            return r1
        L_0x0047:
            r0.label = r3
            java.lang.Object r6 = org.videolan.vlc.gui.helpers.PreferenceUtilsKt.restartMediaPlayer(r0)
            if (r6 != r1) goto L_0x0050
            return r1
        L_0x0050:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.preferences.PreferencesAudio.restartLibVLC(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void prepareLocaleList() {
        LocaleUtils localeUtils = LocaleUtils.INSTANCE;
        String[] strArr = BuildConfig.TRANSLATION_ARRAY;
        Intrinsics.checkNotNullExpressionValue(strArr, "TRANSLATION_ARRAY");
        String string = getString(R.string.no_track_preference);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        LocalePair localesUsedInProject = localeUtils.getLocalesUsedInProject(strArr, string);
        ListPreference listPreference = this.preferredAudioTrack;
        ListPreference listPreference2 = null;
        if (listPreference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferredAudioTrack");
            listPreference = null;
        }
        listPreference.setEntries((CharSequence[]) localesUsedInProject.getLocaleEntries());
        ListPreference listPreference3 = this.preferredAudioTrack;
        if (listPreference3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferredAudioTrack");
        } else {
            listPreference2 = listPreference3;
        }
        listPreference2.setEntryValues((CharSequence[]) localesUsedInProject.getLocaleEntryValues());
    }
}
