package org.videolan.vlc.gui.preferences;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.Constants;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.DebugLogActivity;
import org.videolan.vlc.gui.browser.BaseBrowserFragmentKt;
import org.videolan.vlc.gui.browser.FilePickerActivity;
import org.videolan.vlc.gui.browser.FilePickerFragmentKt;
import org.videolan.vlc.gui.dialogs.ConfirmDeleteDialog;
import org.videolan.vlc.gui.dialogs.RenameDialog;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.providers.PickerType;
import org.videolan.vlc.util.FeatureFlag;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0005H\u0014J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001c\u0010\u0014\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016J\u000e\u0010\u001b\u001a\u00020\bH@¢\u0006\u0002\u0010\u001c¨\u0006\u001d"}, d2 = {"Lorg/videolan/vlc/gui/preferences/PreferencesAdvanced;", "Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "getTitleId", "", "getXml", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "onStop", "restartLibVLC", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesAdvanced.kt */
public final class PreferencesAdvanced extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_adv;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.advanced_prefs_category;
    }

    public void onCreate(Bundle bundle) {
        Preference findPreference;
        super.onCreate(bundle);
        if ((!(FeatureFlag.values().length == 0)) && (findPreference = findPreference("optional_features")) != null) {
            findPreference.setVisible(true);
        }
        EditTextPreference editTextPreference = (EditTextPreference) findPreference("network_caching");
        if (editTextPreference != null) {
            editTextPreference.setOnBindEditTextListener(new PreferencesAdvanced$$ExternalSyntheticLambda1());
        }
        Preference findPreference2 = findPreference(SettingsKt.KEY_SHOW_UPDATE);
        if (findPreference2 != null) {
            findPreference2.setVisible(false);
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "it");
        editText.setInputType(2);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        editText.setSelection(editText.getEditableText().length());
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
                case -1840454629:
                    if (key.equals("debug_logs")) {
                        startActivity(new Intent(requireContext(), DebugLogActivity.class));
                        return true;
                    }
                    break;
                case -1812683614:
                    if (key.equals("clear_history")) {
                        ConfirmDeleteDialog.Companion companion = ConfirmDeleteDialog.Companion;
                        String string = getString(R.string.clear_playback_history);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        String string2 = getString(R.string.clear_history_message);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        String string3 = getString(R.string.clear_history);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                        ConfirmDeleteDialog newInstance$default = ConfirmDeleteDialog.Companion.newInstance$default(companion, (ArrayList) null, string, string2, string3, 1, (Object) null);
                        FragmentActivity activity = getActivity();
                        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                        newInstance$default.show(activity.getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
                        newInstance$default.setListener(new PreferencesAdvanced$onPreferenceTreeClick$2(this));
                        return true;
                    }
                    break;
                case -1344135161:
                    if (key.equals("dump_app_db")) {
                        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onPreferenceTreeClick$7(this, new File(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY() + Constants.ROOM_DATABASE), (Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$7>) null), 3, (Object) null);
                        return true;
                    }
                    break;
                case -1051063247:
                    if (key.equals("quit_app")) {
                        Process.killProcess(Process.myPid());
                        return true;
                    }
                    break;
                case -996855644:
                    if (key.equals("dump_media_db")) {
                        if (Medialibrary.getInstance().isWorking()) {
                            UiTools uiTools = UiTools.INSTANCE;
                            FragmentActivity requireActivity = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                            String string4 = getString(R.string.settings_ml_block_scan);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                            uiTools.snacker(requireActivity, string4);
                        } else {
                            Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onPreferenceTreeClick$6(this, new File(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY() + Medialibrary.VLC_MEDIA_DB_NAME), (Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$6>) null), 3, (Object) null);
                        }
                        return true;
                    }
                    break;
                case -800712012:
                    if (key.equals("restore_settings")) {
                        Intent intent = new Intent(requireContext(), FilePickerActivity.class);
                        intent.putExtra(BaseBrowserFragmentKt.KEY_PICKER_TYPE, PickerType.SETTINGS.ordinal());
                        startActivityForResult(intent, 10000);
                        return true;
                    }
                    break;
                case -212524287:
                    if (key.equals("nightly_install")) {
                        new AlertDialog.Builder(requireActivity()).setTitle(getResources().getString(R.string.install_nightly)).setMessage(getResources().getString(R.string.install_nightly_alert)).setPositiveButton(R.string.ok, new PreferencesAdvanced$$ExternalSyntheticLambda0(this)).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
                        return true;
                    }
                    break;
                case 70420587:
                    if (key.equals("clear_media_db")) {
                        Medialibrary instance = Medialibrary.getInstance();
                        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
                        if (instance.isWorking()) {
                            FragmentActivity activity2 = getActivity();
                            if (activity2 != null) {
                                Toast.makeText(activity2, R.string.settings_ml_block_scan, 1).show();
                                break;
                            }
                        } else {
                            ConfirmDeleteDialog.Companion companion2 = ConfirmDeleteDialog.Companion;
                            String string5 = getString(R.string.clear_media_db);
                            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                            String string6 = getString(R.string.clear_media_db_message);
                            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                            String string7 = getString(R.string.clear);
                            Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                            ConfirmDeleteDialog newInstance$default2 = ConfirmDeleteDialog.Companion.newInstance$default(companion2, (ArrayList) null, string5, string6, string7, 1, (Object) null);
                            newInstance$default2.show(requireActivity().getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
                            newInstance$default2.setListener(new PreferencesAdvanced$onPreferenceTreeClick$4(this, instance));
                            return true;
                        }
                    }
                    break;
                case 733386300:
                    if (key.equals("optional_features")) {
                        loadFragment(new PreferencesOptional());
                        return true;
                    }
                    break;
                case 1558692942:
                    if (key.equals("export_settings")) {
                        Job unused3 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new PreferencesAdvanced$onPreferenceTreeClick$8(this, new File(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY() + Constants.EXPORT_SETTINGS_FILE), (Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$8>) null), 2, (Object) null);
                        return true;
                    }
                    break;
                case 2138545338:
                    if (key.equals("clear_app_data")) {
                        if (Build.VERSION.SDK_INT >= 19) {
                            ConfirmDeleteDialog.Companion companion3 = ConfirmDeleteDialog.Companion;
                            String string8 = getString(R.string.clear_app_data);
                            Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                            String string9 = getString(R.string.clear_app_data_message);
                            Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                            String string10 = getString(R.string.clear);
                            Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                            ConfirmDeleteDialog newInstance$default3 = ConfirmDeleteDialog.Companion.newInstance$default(companion3, (ArrayList) null, string8, string9, string10, 1, (Object) null);
                            newInstance$default3.show(requireActivity().getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
                            newInstance$default3.setListener(new PreferencesAdvanced$onPreferenceTreeClick$5(this));
                        } else {
                            Intent intent2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent2.addCategory("android.intent.category.DEFAULT");
                            intent2.setData(Uri.fromParts(Constants.SCHEME_PACKAGE, requireActivity().getApplicationContext().getPackageName(), (String) null));
                            startActivity(intent2);
                        }
                        return true;
                    }
                    break;
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    /* access modifiers changed from: private */
    public static final void onPreferenceTreeClick$lambda$1(PreferencesAdvanced preferencesAdvanced, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(preferencesAdvanced, "this$0");
        FragmentActivity requireActivity = preferencesAdvanced.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(requireActivity), (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onPreferenceTreeClick$1$1(preferencesAdvanced, (Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$1$1>) null), 3, (Object) null);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Intent intent2 = intent;
        super.onActivityResult(i, i2, intent);
        if (intent2 != null && i == 10000 && intent2.hasExtra(FilePickerFragmentKt.EXTRA_MRL)) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onActivityResult$1(this, intent2, (Continuation<? super PreferencesAdvanced$onActivityResult$1>) null), 3, (Object) null);
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            UiTools.restartDialog$default(uiTools, requireActivity, false, 0, (Object) null, 14, (Object) null);
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        String str2 = str;
        if (sharedPreferences2 != null && str2 != null) {
            switch (str.hashCode()) {
                case -1541584566:
                    if (!str2.equals("enable_time_stretching_audio")) {
                        return;
                    }
                    break;
                case -1010579281:
                    if (!str2.equals("opengl")) {
                        return;
                    }
                    break;
                case -1005361226:
                    if (!str2.equals("deblocking")) {
                        return;
                    }
                    break;
                case -415976523:
                    if (str2.equals("custom_libvlc_options")) {
                        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onSharedPreferenceChanged$3(this, sharedPreferences2, (Continuation<? super PreferencesAdvanced$onSharedPreferenceChanged$3>) null), 3, (Object) null);
                        return;
                    }
                    return;
                case 467108173:
                    if (!str2.equals("enable_frame_skip")) {
                        return;
                    }
                    break;
                case 538429387:
                    if (str2.equals(SettingsKt.DAV1D_THREAD_NUMBER)) {
                        String string = sharedPreferences2.getString(str2, "");
                        if (string == null) {
                            string = "";
                        }
                        if (!Intrinsics.areEqual((Object) string, (Object) "")) {
                            CharSequence charSequence = string;
                            if ((TextUtils.isDigitsOnly(charSequence) && Integer.parseInt(string) < 1) || !TextUtils.isDigitsOnly(charSequence)) {
                                UiTools uiTools = UiTools.INSTANCE;
                                FragmentActivity requireActivity = requireActivity();
                                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                                UiTools.snacker$default(uiTools, requireActivity, R.string.dav1d_thread_number_invalid, false, 4, (Object) null);
                                SettingsKt.putSingle(sharedPreferences2, SettingsKt.DAV1D_THREAD_NUMBER, "");
                                return;
                            }
                            return;
                        }
                        EditTextPreference editTextPreference = (EditTextPreference) findPreference(str2);
                        if (editTextPreference != null && editTextPreference.callChangeListener("")) {
                            editTextPreference.setText("");
                            return;
                        }
                        return;
                    }
                    return;
                case 1389290196:
                    if (str2.equals("prefer_smbv1")) {
                        Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onSharedPreferenceChanged$5((Continuation<? super PreferencesAdvanced$onSharedPreferenceChanged$5>) null), 3, (Object) null);
                        UiTools uiTools2 = UiTools.INSTANCE;
                        FragmentActivity requireActivity2 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                        UiTools.restartDialog$default(uiTools2, requireActivity2, false, 0, (Object) null, 14, (Object) null);
                        return;
                    }
                    return;
                case 1559619246:
                    if (str2.equals("network_caching")) {
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        try {
                            String string2 = sharedPreferences2.getString(str2, Constants.GROUP_VIDEOS_FOLDER);
                            if (string2 == null) {
                                string2 = Constants.GROUP_VIDEOS_FOLDER;
                            }
                            int parseInt = Integer.parseInt(string2);
                            int coerceIn = RangesKt.coerceIn(parseInt, 0, 60000);
                            edit.putInt("network_caching_value", coerceIn);
                            EditTextPreference editTextPreference2 = (EditTextPreference) findPreference(str2);
                            if (editTextPreference2 != null) {
                                editTextPreference2.setText(String.valueOf(coerceIn));
                            }
                            if (parseInt != coerceIn) {
                                UiTools uiTools3 = UiTools.INSTANCE;
                                FragmentActivity requireActivity3 = requireActivity();
                                Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                                UiTools.snacker$default(uiTools3, requireActivity3, R.string.network_caching_popup, false, 4, (Object) null);
                            }
                        } catch (NumberFormatException unused3) {
                            edit.putInt("network_caching_value", 0);
                            EditTextPreference editTextPreference3 = (EditTextPreference) findPreference(str2);
                            if (editTextPreference3 != null) {
                                editTextPreference3.setText(Constants.GROUP_VIDEOS_FOLDER);
                            }
                            UiTools uiTools4 = UiTools.INSTANCE;
                            FragmentActivity requireActivity4 = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity(...)");
                            UiTools.snacker$default(uiTools4, requireActivity4, R.string.network_caching_popup, false, 4, (Object) null);
                        }
                        edit.apply();
                        Job unused4 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onSharedPreferenceChanged$2(this, (Continuation<? super PreferencesAdvanced$onSharedPreferenceChanged$2>) null), 3, (Object) null);
                        return;
                    }
                    return;
                case 1638312828:
                    if (!str2.equals("enable_verbose_mode")) {
                        return;
                    }
                    break;
                default:
                    return;
            }
            Job unused5 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onSharedPreferenceChanged$4(this, (Continuation<? super PreferencesAdvanced$onSharedPreferenceChanged$4>) null), 3, (Object) null);
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
            boolean r0 = r6 instanceof org.videolan.vlc.gui.preferences.PreferencesAdvanced$restartLibVLC$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.vlc.gui.preferences.PreferencesAdvanced$restartLibVLC$1 r0 = (org.videolan.vlc.gui.preferences.PreferencesAdvanced$restartLibVLC$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.gui.preferences.PreferencesAdvanced$restartLibVLC$1 r0 = new org.videolan.vlc.gui.preferences.PreferencesAdvanced$restartLibVLC$1
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
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.PreferencesAdvanced.restartLibVLC(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
