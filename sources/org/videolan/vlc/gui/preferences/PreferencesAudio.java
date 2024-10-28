package org.videolan.vlc.gui.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.libvlc.util.HWDecoderUtil;
import org.videolan.resources.AndroidDevices;
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

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\u0007H\u0014J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001c\u0010\u0016\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016J\b\u0010\u001d\u001a\u00020\nH\u0002J\u000e\u0010\u001e\u001a\u00020\nH@¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\nH\u0002J\b\u0010!\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/videolan/vlc/gui/preferences/PreferencesAudio;", "Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "preferredAudioTrack", "Landroidx/preference/ListPreference;", "getTitleId", "", "getXml", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "onStop", "prepareLocaleList", "restartLibVLC", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePassThroughSummary", "updatePreferredAudioTrack", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesAudio.kt */
public final class PreferencesAudio extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private ListPreference preferredAudioTrack;

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
        Preference findPreference2 = findPreference(SettingsKt.AUDIO_DUCKING);
        if (findPreference2 != null) {
            findPreference2.setVisible(!AndroidUtil.isOOrLater);
        }
        Preference findPreference3 = findPreference(SettingsKt.RESUME_PLAYBACK);
        if (findPreference3 != null) {
            findPreference3.setVisible(AndroidDevices.INSTANCE.isPhone());
        }
        ListPreference listPreference = (ListPreference) findPreference("aout");
        if (!(HWDecoderUtil.getAudioOutputFromDevice() == HWDecoderUtil.AudioOutput.ALL || listPreference == null)) {
            listPreference.setVisible(false);
        }
        if (MainVersionKt.isVLC4() && Build.VERSION.SDK_INT >= 28) {
            if (listPreference != null) {
                listPreference.setEntryValues((CharSequence[]) requireActivity().getResources().getStringArray(R.array.aouts_complete_values));
            }
            if (listPreference != null) {
                listPreference.setEntries((CharSequence[]) requireActivity().getResources().getStringArray(R.array.aouts_complete));
            }
        }
        updatePassThroughSummary();
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        if (Intrinsics.areEqual((Object) "2", (Object) sharedPreferences.getString("aout", Constants.GROUP_VIDEOS_FOLDER)) && (findPreference = findPreference("audio_digital_output")) != null) {
            findPreference.setVisible(false);
        }
        String[] strArr = {"audio-replay-gain-default", "audio-replay-gain-preamp"};
        for (int i = 0; i < 2; i++) {
            EditTextPreference editTextPreference = (EditTextPreference) findPreference(strArr[i]);
            if (editTextPreference != null) {
                editTextPreference.setOnBindEditTextListener(new PreferencesAudio$$ExternalSyntheticLambda0());
            }
        }
        Preference findPreference4 = findPreference(SettingsKt.AUDIO_PREFERRED_LANGUAGE);
        Intrinsics.checkNotNull(findPreference4);
        this.preferredAudioTrack = (ListPreference) findPreference4;
        updatePreferredAudioTrack();
        prepareLocaleList();
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "it");
        editText.setInputType(12290);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        editText.setSelection(editText.getEditableText().length());
    }

    private final void updatePreferredAudioTrack() {
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        ListPreference listPreference = null;
        String string = ((SharedPreferences) settings.getInstance(requireActivity)).getString(SettingsKt.AUDIO_PREFERRED_LANGUAGE, (String) null);
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
        listPreference.setSummary(getString(R.string.track_preference, LocaleUtil.INSTANCE.getLocaleName(string)));
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
        if (Intrinsics.areEqual((Object) key, (Object) "enable_headset_detection")) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type org.videolan.vlc.gui.preferences.PreferencesActivity");
            ((PreferencesActivity) requireActivity).detectHeadset(((TwoStatePreference) preference).isChecked());
            return true;
        }
        if (Intrinsics.areEqual((Object) key, (Object) "soundfont")) {
            Intent intent = new Intent(requireContext(), FilePickerActivity.class);
            intent.putExtra(BaseBrowserFragmentKt.KEY_PICKER_TYPE, PickerType.SOUNDFONT.ordinal());
            startActivityForResult(intent, 10000);
        }
        return super.onPreferenceTreeClick(preference);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Intent intent2 = intent;
        super.onActivityResult(i, i2, intent);
        if (intent2 != null && i == 10000 && intent2.hasExtra(FilePickerFragmentKt.EXTRA_MRL)) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PreferencesAudio$onActivityResult$1(this, intent2, (Continuation<? super PreferencesAudio$onActivityResult$1>) null), 3, (Object) null);
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            UiTools.restartDialog$default(uiTools, requireActivity, false, 0, (Object) null, 14, (Object) null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00d6, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) "audio-replay-gain-default") == false) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d8, code lost:
        r1 = "-7.0";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00db, code lost:
        r1 = "0.0";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00dd, code lost:
        r2 = r12.getString(r13, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r3 = new java.text.DecimalFormat("###0.0###", new java.text.DecimalFormatSymbols(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ef, code lost:
        if (r2 == null) goto L_0x00fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f1, code lost:
        r5 = java.lang.Double.valueOf(java.lang.Double.parseDouble(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00fa, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00fb, code lost:
        r3 = r3.format(r5);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, "format(...)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0108, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r2) != false) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x010a, code lost:
        r12 = r12.edit();
        r12.putString(r13, r3);
        r13 = (androidx.preference.EditTextPreference) findPreference(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0119, code lost:
        if (r13 == null) goto L_0x011e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x011b, code lost:
        r13.setText(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011e, code lost:
        r12.apply();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0123, code lost:
        r5 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r11);
        r12 = new org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4(r11, (kotlin.coroutines.Continuation<? super org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4>) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0132, code lost:
        kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, (kotlin.coroutines.CoroutineContext) null, (kotlinx.coroutines.CoroutineStart) null, r12, 3, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x013e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0140, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r6 = new java.lang.StringBuilder("Could not parse value: ");
        r6.append(r2);
        r6.append(". Setting ");
        r6.append(r13);
        r6.append(" to ");
        r0 = r1;
        r6.append(r1);
        android.util.Log.w("VLC/PreferencesAudio", r6.toString(), r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x016b, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2) == false) goto L_0x016d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x016d, code lost:
        r12 = r12.edit();
        r0 = r1;
        r12.putString(r13, r1);
        r13 = (androidx.preference.EditTextPreference) findPreference(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x017f, code lost:
        if (r13 != null) goto L_0x0181;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0181, code lost:
        r13.setText(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0185, code lost:
        r5 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r11);
        r12 = new org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4(r11, (kotlin.coroutines.Continuation<? super org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4>) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0199, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2) == false) goto L_0x019b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x019b, code lost:
        r12 = r12.edit();
        r2 = r1;
        r12.putString(r13, r1);
        r13 = (androidx.preference.EditTextPreference) findPreference(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01ad, code lost:
        if (r13 != null) goto L_0x01af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01af, code lost:
        r13.setText(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01b2, code lost:
        r12.apply();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01b6, code lost:
        kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r11), (kotlin.coroutines.CoroutineContext) null, (kotlinx.coroutines.CoroutineStart) null, new org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4(r11, (kotlin.coroutines.Continuation<? super org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4>) null), 3, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01cf, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01d9, code lost:
        kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r11), (kotlin.coroutines.CoroutineContext) null, (kotlinx.coroutines.CoroutineStart) null, new org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$2(r11, (kotlin.coroutines.Continuation<? super org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$2>) null), 3, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onSharedPreferenceChanged(android.content.SharedPreferences r12, java.lang.String r13) {
        /*
            r11 = this;
            java.lang.String r0 = "Could not parse value: "
            if (r12 == 0) goto L_0x01f2
            if (r13 == 0) goto L_0x01f2
            androidx.fragment.app.FragmentActivity r1 = r11.getActivity()
            if (r1 != 0) goto L_0x000e
            goto L_0x01f2
        L_0x000e:
            int r1 = r13.hashCode()
            java.lang.String r2 = "audio-replay-gain-default"
            java.lang.String r3 = "audio_digital_output"
            r4 = 0
            switch(r1) {
                case -960388376: goto L_0x01d0;
                case -191150686: goto L_0x00ca;
                case 3000141: goto L_0x006d;
                case 10218978: goto L_0x0063;
                case 417433745: goto L_0x0056;
                case 623523935: goto L_0x0047;
                case 1000457730: goto L_0x003d;
                case 1319190720: goto L_0x0033;
                case 1572272419: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x01f2
        L_0x001c:
            java.lang.String r0 = "playback_speed"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x0026
            goto L_0x01f2
        L_0x0026:
            r13 = 1065353216(0x3f800000, float:1.0)
            java.lang.Float r13 = java.lang.Float.valueOf(r13)
            java.lang.String r0 = "playback_rate"
            org.videolan.tools.SettingsKt.putSingle(r12, r0, r13)
            goto L_0x01f2
        L_0x0033:
            java.lang.String r1 = "audio-replay-gain-preamp"
            boolean r1 = r13.equals(r1)
            if (r1 != 0) goto L_0x00d2
            goto L_0x01f2
        L_0x003d:
            java.lang.String r12 = "audio-replay-gain-enable"
            boolean r12 = r13.equals(r12)
            if (r12 != 0) goto L_0x01d9
            goto L_0x01f2
        L_0x0047:
            java.lang.String r12 = "audio_preferred_language"
            boolean r12 = r13.equals(r12)
            if (r12 != 0) goto L_0x0051
            goto L_0x01f2
        L_0x0051:
            r11.updatePreferredAudioTrack()
            goto L_0x01f2
        L_0x0056:
            boolean r12 = r13.equals(r3)
            if (r12 != 0) goto L_0x005e
            goto L_0x01f2
        L_0x005e:
            r11.updatePassThroughSummary()
            goto L_0x01f2
        L_0x0063:
            java.lang.String r12 = "audio-replay-gain-mode"
            boolean r12 = r13.equals(r12)
            if (r12 != 0) goto L_0x01d9
            goto L_0x01f2
        L_0x006d:
            java.lang.String r12 = "aout"
            boolean r13 = r13.equals(r12)
            if (r13 != 0) goto L_0x0077
            goto L_0x01f2
        L_0x0077:
            r13 = r11
            androidx.lifecycle.LifecycleOwner r13 = (androidx.lifecycle.LifecycleOwner) r13
            androidx.lifecycle.LifecycleCoroutineScope r13 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r13)
            r5 = r13
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$1 r13 = new org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$1
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
            if (r12 == 0) goto L_0x00b9
            r13 = r3
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            androidx.preference.Preference r13 = r11.findPreference(r13)
            androidx.preference.CheckBoxPreference r13 = (androidx.preference.CheckBoxPreference) r13
            if (r13 != 0) goto L_0x00b5
            goto L_0x00b9
        L_0x00b5:
            r0 = 0
            r13.setChecked(r0)
        L_0x00b9:
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            androidx.preference.Preference r13 = r11.findPreference(r3)
            if (r13 != 0) goto L_0x00c3
            goto L_0x01f2
        L_0x00c3:
            r12 = r12 ^ 1
            r13.setVisible(r12)
            goto L_0x01f2
        L_0x00ca:
            boolean r1 = r13.equals(r2)
            if (r1 != 0) goto L_0x00d2
            goto L_0x01f2
        L_0x00d2:
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x00db
            java.lang.String r1 = "-7.0"
            goto L_0x00dd
        L_0x00db:
            java.lang.String r1 = "0.0"
        L_0x00dd:
            java.lang.String r2 = r12.getString(r13, r1)
            java.text.DecimalFormat r3 = new java.text.DecimalFormat     // Catch:{ IllegalArgumentException -> 0x0140 }
            java.lang.String r5 = "###0.0###"
            java.text.DecimalFormatSymbols r6 = new java.text.DecimalFormatSymbols     // Catch:{ IllegalArgumentException -> 0x0140 }
            java.util.Locale r7 = java.util.Locale.ENGLISH     // Catch:{ IllegalArgumentException -> 0x0140 }
            r6.<init>(r7)     // Catch:{ IllegalArgumentException -> 0x0140 }
            r3.<init>(r5, r6)     // Catch:{ IllegalArgumentException -> 0x0140 }
            if (r2 == 0) goto L_0x00fa
            double r5 = java.lang.Double.parseDouble(r2)     // Catch:{ IllegalArgumentException -> 0x0140 }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ IllegalArgumentException -> 0x0140 }
            goto L_0x00fb
        L_0x00fa:
            r5 = r4
        L_0x00fb:
            java.lang.String r3 = r3.format(r5)     // Catch:{ IllegalArgumentException -> 0x0140 }
            java.lang.String r5 = "format(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch:{ IllegalArgumentException -> 0x0140 }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r2)
            if (r0 != 0) goto L_0x0123
            android.content.SharedPreferences$Editor r12 = r12.edit()
            r12.putString(r13, r3)
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            androidx.preference.Preference r13 = r11.findPreference(r13)
            androidx.preference.EditTextPreference r13 = (androidx.preference.EditTextPreference) r13
            if (r13 == 0) goto L_0x011e
            r13.setText(r3)
        L_0x011e:
            r12.apply()
            goto L_0x01f2
        L_0x0123:
            r12 = r11
            androidx.lifecycle.LifecycleOwner r12 = (androidx.lifecycle.LifecycleOwner) r12
            androidx.lifecycle.LifecycleCoroutineScope r12 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r12)
            r5 = r12
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4 r12 = new org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4
            r12.<init>(r11, r4)
        L_0x0132:
            r8 = r12
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
            goto L_0x01f2
        L_0x013e:
            r0 = move-exception
            goto L_0x0195
        L_0x0140:
            r3 = move-exception
            java.lang.String r5 = "VLC/PreferencesAudio"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x013e }
            r6.<init>(r0)     // Catch:{ all -> 0x013e }
            r6.append(r2)     // Catch:{ all -> 0x013e }
            java.lang.String r0 = ". Setting "
            r6.append(r0)     // Catch:{ all -> 0x013e }
            r6.append(r13)     // Catch:{ all -> 0x013e }
            java.lang.String r0 = " to "
            r6.append(r0)     // Catch:{ all -> 0x013e }
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x013e }
            r6.append(r1)     // Catch:{ all -> 0x013e }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x013e }
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch:{ all -> 0x013e }
            android.util.Log.w(r5, r0, r3)     // Catch:{ all -> 0x013e }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r0 != 0) goto L_0x0185
            android.content.SharedPreferences$Editor r12 = r12.edit()
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
            r12.putString(r13, r1)
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            androidx.preference.Preference r13 = r11.findPreference(r13)
            androidx.preference.EditTextPreference r13 = (androidx.preference.EditTextPreference) r13
            if (r13 == 0) goto L_0x011e
            r13.setText(r1)
            goto L_0x011e
        L_0x0185:
            r12 = r11
            androidx.lifecycle.LifecycleOwner r12 = (androidx.lifecycle.LifecycleOwner) r12
            androidx.lifecycle.LifecycleCoroutineScope r12 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r12)
            r5 = r12
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4 r12 = new org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4
            r12.<init>(r11, r4)
            goto L_0x0132
        L_0x0195:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r2 != 0) goto L_0x01b6
            android.content.SharedPreferences$Editor r12 = r12.edit()
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2
            r12.putString(r13, r1)
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            androidx.preference.Preference r13 = r11.findPreference(r13)
            androidx.preference.EditTextPreference r13 = (androidx.preference.EditTextPreference) r13
            if (r13 == 0) goto L_0x01b2
            r13.setText(r1)
        L_0x01b2:
            r12.apply()
            goto L_0x01cf
        L_0x01b6:
            r12 = r11
            androidx.lifecycle.LifecycleOwner r12 = (androidx.lifecycle.LifecycleOwner) r12
            androidx.lifecycle.LifecycleCoroutineScope r12 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r12)
            r5 = r12
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4 r12 = new org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$4
            r12.<init>(r11, r4)
            r8 = r12
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
        L_0x01cf:
            throw r0
        L_0x01d0:
            java.lang.String r12 = "audio-replay-gain-peak-protection"
            boolean r12 = r13.equals(r12)
            if (r12 != 0) goto L_0x01d9
            goto L_0x01f2
        L_0x01d9:
            r12 = r11
            androidx.lifecycle.LifecycleOwner r12 = (androidx.lifecycle.LifecycleOwner) r12
            androidx.lifecycle.LifecycleCoroutineScope r12 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r12)
            r5 = r12
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$2 r12 = new org.videolan.vlc.gui.preferences.PreferencesAudio$onSharedPreferenceChanged$2
            r12.<init>(r11, r4)
            r8 = r12
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
        L_0x01f2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.PreferencesAudio.onSharedPreferenceChanged(android.content.SharedPreferences, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object restartLibVLC(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof org.videolan.vlc.gui.preferences.PreferencesAudio$restartLibVLC$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.vlc.gui.preferences.PreferencesAudio$restartLibVLC$1 r0 = (org.videolan.vlc.gui.preferences.PreferencesAudio$restartLibVLC$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.gui.preferences.PreferencesAudio$restartLibVLC$1 r0 = new org.videolan.vlc.gui.preferences.PreferencesAudio$restartLibVLC$1
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
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.PreferencesAudio.restartLibVLC(kotlin.coroutines.Continuation):java.lang.Object");
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
