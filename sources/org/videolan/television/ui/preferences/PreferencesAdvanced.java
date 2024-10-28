package org.videolan.television.ui.preferences;

import android.app.Activity;
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
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceDialogFragment;
import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
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

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\nH\u0014J\"\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001c\u0010\u001a\u001a\u00020\r2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\rH\u0016J\b\u0010 \u001a\u00020\rH\u0016J\u000e\u0010!\u001a\u00020\rH@¢\u0006\u0002\u0010\"R\u0012\u0010\u0005\u001a\u00020\u0006X\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006#"}, d2 = {"Lorg/videolan/television/ui/preferences/PreferencesAdvanced;", "Lorg/videolan/television/ui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getTitleId", "", "getXml", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDisplayPreferenceDialog", "preference", "Landroidx/preference/Preference;", "onPreferenceTreeClick", "", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "onStop", "restartLibVLC", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesAdvanced.kt */
public final class PreferencesAdvanced extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

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
    }

    public void onDisplayPreferenceDialog(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        PreferenceDialogFragment buildPreferenceDialogFragment = super.buildPreferenceDialogFragment(preference);
        if (!(buildPreferenceDialogFragment instanceof CustomEditTextPreferenceDialogFragment)) {
            Preference findPreference = findPreference(SettingsKt.KEY_SHOW_UPDATE);
            if (findPreference != null) {
                findPreference.setVisible(false);
            }
            super.onDisplayPreferenceDialog(preference);
        } else if (Intrinsics.areEqual((Object) preference.getKey(), (Object) "network_caching")) {
            CustomEditTextPreferenceDialogFragment customEditTextPreferenceDialogFragment = (CustomEditTextPreferenceDialogFragment) buildPreferenceDialogFragment;
            customEditTextPreferenceDialogFragment.setInputType(2);
            customEditTextPreferenceDialogFragment.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
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
        Activity activity = getActivity();
        if (preference.getKey() == null || activity == null) {
            return false;
        }
        String key = preference.getKey();
        if (key != null) {
            switch (key.hashCode()) {
                case -1840454629:
                    if (key.equals("debug_logs")) {
                        startActivity(new Intent(activity, DebugLogActivity.class));
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
                        Activity activity2 = getActivity();
                        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                        newInstance$default.show(((FragmentActivity) activity2).getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
                        newInstance$default.setListener(new PreferencesAdvanced$onPreferenceTreeClick$2(this));
                        return true;
                    }
                    break;
                case -1344135161:
                    if (key.equals("dump_app_db")) {
                        Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onPreferenceTreeClick$8(this, new File(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY() + Constants.ROOM_DATABASE), (Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$8>) null), 3, (Object) null);
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
                            Activity activity3 = getActivity();
                            if (activity3 != null) {
                                Toast.makeText(activity3, R.string.settings_ml_block_scan, 1).show();
                            }
                        } else {
                            Job unused2 = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onPreferenceTreeClick$7(this, new File(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY() + Medialibrary.VLC_MEDIA_DB_NAME), (Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$7>) null), 3, (Object) null);
                        }
                        return true;
                    }
                    break;
                case -800712012:
                    if (key.equals("restore_settings")) {
                        Intent intent = new Intent(getActivity(), FilePickerActivity.class);
                        intent.putExtra(BaseBrowserFragmentKt.KEY_PICKER_TYPE, PickerType.SETTINGS.ordinal());
                        startActivityForResult(intent, 10000);
                        return true;
                    }
                    break;
                case -212524287:
                    if (key.equals("nightly_install")) {
                        Activity activity4 = getActivity();
                        Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type org.videolan.television.ui.preferences.PreferencesActivity");
                        PreferencesActivity preferencesActivity = (PreferencesActivity) activity4;
                        new AlertDialog.Builder(preferencesActivity).setTitle(getResources().getString(R.string.install_nightly)).setMessage(getResources().getString(R.string.install_nightly_alert)).setPositiveButton(R.string.ok, new PreferencesAdvanced$$ExternalSyntheticLambda0(preferencesActivity)).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
                        return true;
                    }
                    break;
                case 70420587:
                    if (key.equals("clear_media_db")) {
                        Medialibrary instance = Medialibrary.getInstance();
                        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
                        if (instance.isWorking()) {
                            Activity activity5 = getActivity();
                            if (activity5 != null) {
                                Toast.makeText(activity5, R.string.settings_ml_block_scan, 1).show();
                            }
                        } else {
                            ConfirmDeleteDialog.Companion companion2 = ConfirmDeleteDialog.Companion;
                            String string4 = getString(R.string.clear_media_db);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                            String string5 = getString(R.string.clear_media_db_message);
                            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                            String string6 = getString(R.string.clear);
                            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                            ConfirmDeleteDialog newInstance$default2 = ConfirmDeleteDialog.Companion.newInstance$default(companion2, (ArrayList) null, string4, string5, string6, 1, (Object) null);
                            Activity activity6 = getActivity();
                            Intrinsics.checkNotNull(activity6, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                            newInstance$default2.show(((FragmentActivity) activity6).getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
                            newInstance$default2.setListener(new PreferencesAdvanced$onPreferenceTreeClick$5(this, instance));
                        }
                        return true;
                    }
                    break;
                case 1558692942:
                    if (key.equals("export_settings")) {
                        Job unused3 = BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), (CoroutineStart) null, new PreferencesAdvanced$onPreferenceTreeClick$9(this, new File(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY() + Constants.EXPORT_SETTINGS_FILE), (Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$9>) null), 2, (Object) null);
                        return true;
                    }
                    break;
                case 2138545338:
                    if (key.equals("clear_app_data")) {
                        if (Build.VERSION.SDK_INT >= 19) {
                            ConfirmDeleteDialog.Companion companion3 = ConfirmDeleteDialog.Companion;
                            String string7 = getString(R.string.clear_app_data);
                            Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                            String string8 = getString(R.string.clear_app_data_message);
                            Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                            String string9 = getString(R.string.clear);
                            Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                            ConfirmDeleteDialog newInstance$default3 = ConfirmDeleteDialog.Companion.newInstance$default(companion3, (ArrayList) null, string7, string8, string9, 1, (Object) null);
                            Activity activity7 = getActivity();
                            Intrinsics.checkNotNull(activity7, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                            newInstance$default3.show(((FragmentActivity) activity7).getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
                            newInstance$default3.setListener(new PreferencesAdvanced$onPreferenceTreeClick$3(this));
                        } else {
                            Intent intent2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent2.addCategory("android.intent.category.DEFAULT");
                            intent2.setData(Uri.fromParts(Constants.SCHEME_PACKAGE, getActivity().getApplicationContext().getPackageName(), (String) null));
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
    public static final void onPreferenceTreeClick$lambda$0(PreferencesActivity preferencesActivity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(preferencesActivity, "$appCompatActivity");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(preferencesActivity), (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onPreferenceTreeClick$1$1(preferencesActivity, (Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$1$1>) null), 3, (Object) null);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i == 10000 && intent.hasExtra(FilePickerFragmentKt.EXTRA_MRL)) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onActivityResult$1(this, intent, (Continuation<? super PreferencesAdvanced$onActivityResult$1>) null), 3, (Object) null);
            UiTools uiTools = UiTools.INSTANCE;
            Activity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            uiTools.restartDialog(activity, true, BasePreferenceFragmentKt.RESTART_CODE, this);
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        Activity activity;
        if (sharedPreferences != null && str != null) {
            switch (str.hashCode()) {
                case -1541584566:
                    if (!str.equals("enable_time_stretching_audio")) {
                        return;
                    }
                    break;
                case -1010579281:
                    if (!str.equals("opengl")) {
                        return;
                    }
                    break;
                case -1005361226:
                    if (!str.equals("deblocking")) {
                        return;
                    }
                    break;
                case -415976523:
                    if (str.equals("custom_libvlc_options")) {
                        Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onSharedPreferenceChanged$3(this, sharedPreferences, (Continuation<? super PreferencesAdvanced$onSharedPreferenceChanged$3>) null), 3, (Object) null);
                        return;
                    }
                    return;
                case 467108173:
                    if (!str.equals("enable_frame_skip")) {
                        return;
                    }
                    break;
                case 538429387:
                    if (str.equals(SettingsKt.DAV1D_THREAD_NUMBER)) {
                        String string = sharedPreferences.getString(str, "");
                        if (string == null) {
                            string = "";
                        }
                        if (!Intrinsics.areEqual((Object) string, (Object) "")) {
                            CharSequence charSequence = string;
                            if ((TextUtils.isDigitsOnly(charSequence) && Integer.parseInt(string) < 1) || !TextUtils.isDigitsOnly(charSequence)) {
                                UiTools uiTools = UiTools.INSTANCE;
                                Activity activity2 = getActivity();
                                Intrinsics.checkNotNullExpressionValue(activity2, "getActivity(...)");
                                UiTools.snacker$default(uiTools, activity2, R.string.dav1d_thread_number_invalid, false, 4, (Object) null);
                                SettingsKt.putSingle(sharedPreferences, SettingsKt.DAV1D_THREAD_NUMBER, "");
                                return;
                            }
                            return;
                        }
                        EditTextPreference editTextPreference = (EditTextPreference) findPreference(str);
                        if (editTextPreference != null && editTextPreference.callChangeListener("")) {
                            editTextPreference.setText("");
                            return;
                        }
                        return;
                    }
                    return;
                case 1389290196:
                    if (!str.equals("prefer_smbv1")) {
                        return;
                    }
                    break;
                case 1559619246:
                    if (str.equals("network_caching")) {
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        try {
                            int parseInt = Integer.parseInt(sharedPreferences.getString(str, Constants.GROUP_VIDEOS_FOLDER));
                            int coerceIn = RangesKt.coerceIn(parseInt, 0, 60000);
                            edit.putInt("network_caching_value", coerceIn);
                            EditTextPreference editTextPreference2 = (EditTextPreference) findPreference(str);
                            if (editTextPreference2 != null) {
                                editTextPreference2.setText(String.valueOf(coerceIn));
                            }
                            if (!(parseInt == coerceIn || (activity = getActivity()) == null)) {
                                Intrinsics.checkNotNull(activity);
                                Toast.makeText(activity, R.string.network_caching_popup, 0).show();
                            }
                        } catch (NumberFormatException unused2) {
                            edit.putInt("network_caching_value", 0);
                            EditTextPreference editTextPreference3 = (EditTextPreference) findPreference(str);
                            if (editTextPreference3 != null) {
                                editTextPreference3.setText(Constants.GROUP_VIDEOS_FOLDER);
                            }
                            Activity activity3 = getActivity();
                            if (activity3 != null) {
                                Intrinsics.checkNotNull(activity3);
                                Toast.makeText(activity3, R.string.network_caching_popup, 0).show();
                            }
                        }
                        edit.apply();
                        Job unused3 = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onSharedPreferenceChanged$2(this, (Continuation<? super PreferencesAdvanced$onSharedPreferenceChanged$2>) null), 3, (Object) null);
                        return;
                    }
                    return;
                case 1638312828:
                    if (!str.equals("enable_verbose_mode")) {
                        return;
                    }
                    break;
                default:
                    return;
            }
            Job unused4 = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PreferencesAdvanced$onSharedPreferenceChanged$4(this, (Continuation<? super PreferencesAdvanced$onSharedPreferenceChanged$4>) null), 3, (Object) null);
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
            boolean r0 = r6 instanceof org.videolan.television.ui.preferences.PreferencesAdvanced$restartLibVLC$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.television.ui.preferences.PreferencesAdvanced$restartLibVLC$1 r0 = (org.videolan.television.ui.preferences.PreferencesAdvanced$restartLibVLC$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.television.ui.preferences.PreferencesAdvanced$restartLibVLC$1 r0 = new org.videolan.television.ui.preferences.PreferencesAdvanced$restartLibVLC$1
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
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.preferences.PreferencesAdvanced.restartLibVLC(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
