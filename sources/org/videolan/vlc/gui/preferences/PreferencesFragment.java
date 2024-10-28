package org.videolan.vlc.gui.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.os.BundleKt;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.preferences.search.PreferenceItem;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\rH\u0014J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0010H\u0016J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\u001a\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R(\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006!"}, d2 = {"Lorg/videolan/vlc/gui/preferences/PreferencesFragment;", "Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "pinCodeResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getPinCodeResult", "()Landroidx/activity/result/ActivityResultLauncher;", "setPinCodeResult", "(Landroidx/activity/result/ActivityResultLauncher;)V", "getTitleId", "", "getXml", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "onStop", "onViewCreated", "view", "Landroid/view/View;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesFragment.kt */
public final class PreferencesFragment extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private ActivityResultLauncher<Intent> pinCodeResult;

    public PreferencesFragment() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new PreferencesFragment$$ExternalSyntheticLambda0(this));
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.pinCodeResult = registerForActivityResult;
    }

    public final ActivityResultLauncher<Intent> getPinCodeResult() {
        return this.pinCodeResult;
    }

    public final void setPinCodeResult(ActivityResultLauncher<Intent> activityResultLauncher) {
        Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
        this.pinCodeResult = activityResultLauncher;
    }

    /* access modifiers changed from: private */
    public static final void pinCodeResult$lambda$0(PreferencesFragment preferencesFragment, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(preferencesFragment, "this$0");
        if (activityResult.getResultCode() == -1) {
            preferencesFragment.loadFragment(new PreferencesParentalControl());
        }
    }

    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.preferences;
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

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Preference findPreference = findPreference("remote_access_category");
        if (findPreference != null) {
            findPreference.setVisible(Build.VERSION.SDK_INT >= 22);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        Parcelable parcelable;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(arguments, PreferencesActivityKt.EXTRA_PREF_END_POINT, PreferenceItem.class);
            } else {
                Parcelable parcelable2 = arguments.getParcelable(PreferencesActivityKt.EXTRA_PREF_END_POINT);
                if (!(parcelable2 instanceof PreferenceItem)) {
                    parcelable2 = null;
                }
                parcelable = (PreferenceItem) parcelable2;
            }
            PreferenceItem preferenceItem = (PreferenceItem) parcelable;
            if (preferenceItem != null) {
                int parentScreen = preferenceItem.getParentScreen();
                if (parentScreen == R.xml.preferences_ui) {
                    PreferencesUi preferencesUi = new PreferencesUi();
                    preferencesUi.setArguments(BundleKt.bundleOf(TuplesKt.to(PreferencesActivityKt.EXTRA_PREF_END_POINT, preferenceItem)));
                    loadFragment(preferencesUi);
                } else if (parentScreen == R.xml.preferences_video) {
                    PreferencesVideo preferencesVideo = new PreferencesVideo();
                    preferencesVideo.setArguments(BundleKt.bundleOf(TuplesKt.to(PreferencesActivityKt.EXTRA_PREF_END_POINT, preferenceItem)));
                    loadFragment(preferencesVideo);
                } else if (parentScreen == R.xml.preferences_subtitles) {
                    PreferencesSubtitles preferencesSubtitles = new PreferencesSubtitles();
                    preferencesSubtitles.setArguments(BundleKt.bundleOf(TuplesKt.to(PreferencesActivityKt.EXTRA_PREF_END_POINT, preferenceItem)));
                    loadFragment(preferencesSubtitles);
                } else if (parentScreen == R.xml.preferences_audio) {
                    PreferencesAudio preferencesAudio = new PreferencesAudio();
                    preferencesAudio.setArguments(BundleKt.bundleOf(TuplesKt.to(PreferencesActivityKt.EXTRA_PREF_END_POINT, preferenceItem)));
                    loadFragment(preferencesAudio);
                } else if (parentScreen == R.xml.preferences_adv) {
                    PreferencesAdvanced preferencesAdvanced = new PreferencesAdvanced();
                    preferencesAdvanced.setArguments(BundleKt.bundleOf(TuplesKt.to(PreferencesActivityKt.EXTRA_PREF_END_POINT, preferenceItem)));
                    loadFragment(preferencesAdvanced);
                } else if (parentScreen == R.xml.preferences_casting) {
                    PreferencesCasting preferencesCasting = new PreferencesCasting();
                    preferencesCasting.setArguments(BundleKt.bundleOf(TuplesKt.to(PreferencesActivityKt.EXTRA_PREF_END_POINT, preferenceItem)));
                    loadFragment(preferencesCasting);
                } else if (parentScreen == R.xml.preferences_remote_access) {
                    PreferencesRemoteAccess preferencesRemoteAccess = new PreferencesRemoteAccess();
                    preferencesRemoteAccess.setArguments(BundleKt.bundleOf(TuplesKt.to(PreferencesActivityKt.EXTRA_PREF_END_POINT, preferenceItem)));
                    loadFragment(preferencesRemoteAccess);
                }
                setArguments((Bundle) null);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01d6, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreferenceTreeClick(androidx.preference.Preference r6) {
        /*
            r5 = this;
            java.lang.String r0 = "preference"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = r6.getKey()
            if (r0 == 0) goto L_0x01d7
            int r1 = r0.hashCode()
            r2 = 3
            java.lang.String r3 = "requireActivity(...)"
            r4 = 1
            switch(r1) {
                case -1715795072: goto L_0x01c3;
                case -1638055097: goto L_0x01af;
                case -1626208309: goto L_0x0162;
                case -1546144702: goto L_0x014c;
                case -1243115766: goto L_0x0136;
                case -1235825310: goto L_0x0120;
                case 307853683: goto L_0x00e1;
                case 1168288122: goto L_0x00cb;
                case 1192035913: goto L_0x00b5;
                case 1293657988: goto L_0x006e;
                case 1741305385: goto L_0x002c;
                case 2060654192: goto L_0x0018;
                default: goto L_0x0016;
            }
        L_0x0016:
            goto L_0x01d7
        L_0x0018:
            java.lang.String r1 = "playback_history"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0022
            goto L_0x01d7
        L_0x0022:
            androidx.fragment.app.FragmentActivity r6 = r5.getActivity()
            if (r6 == 0) goto L_0x002b
            r6.setResult(r2)
        L_0x002b:
            return r4
        L_0x002c:
            java.lang.String r1 = "video_resume_playback"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0036
            goto L_0x01d7
        L_0x0036:
            org.videolan.tools.Settings r6 = org.videolan.tools.Settings.INSTANCE
            androidx.fragment.app.FragmentActivity r0 = r5.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            java.lang.Object r6 = r6.getInstance(r0)
            android.content.SharedPreferences r6 = (android.content.SharedPreferences) r6
            android.content.SharedPreferences$Editor r6 = r6.edit()
            java.lang.String r0 = "media_list"
            android.content.SharedPreferences$Editor r6 = r6.remove(r0)
            java.lang.String r0 = "media_list_resume"
            android.content.SharedPreferences$Editor r6 = r6.remove(r0)
            java.lang.String r0 = "current_media_resume"
            android.content.SharedPreferences$Editor r6 = r6.remove(r0)
            java.lang.String r0 = "current_media"
            android.content.SharedPreferences$Editor r6 = r6.remove(r0)
            r6.apply()
            androidx.fragment.app.FragmentActivity r6 = r5.getActivity()
            if (r6 == 0) goto L_0x006d
            r6.setResult(r2)
        L_0x006d:
            return r4
        L_0x006e:
            java.lang.String r1 = "audio_resume_playback"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0078
            goto L_0x01d7
        L_0x0078:
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            androidx.preference.Preference r6 = r5.findPreference(r1)
            androidx.preference.CheckBoxPreference r6 = (androidx.preference.CheckBoxPreference) r6
            if (r6 == 0) goto L_0x00b4
            boolean r0 = r6.isChecked()
            if (r0 != 0) goto L_0x00b4
            org.videolan.vlc.gui.dialogs.ConfirmAudioPlayQueueDialog r0 = new org.videolan.vlc.gui.dialogs.ConfirmAudioPlayQueueDialog
            r0.<init>()
            androidx.fragment.app.FragmentActivity r1 = r5.getActivity()
            java.lang.String r2 = "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
            androidx.fragment.app.FragmentManager r1 = r1.getSupportFragmentManager()
            java.lang.Class<org.videolan.vlc.gui.dialogs.ConfirmAudioPlayQueueDialog> r2 = org.videolan.vlc.gui.dialogs.ConfirmAudioPlayQueueDialog.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            java.lang.String r2 = r2.getSimpleName()
            r0.show((androidx.fragment.app.FragmentManager) r1, (java.lang.String) r2)
            org.videolan.vlc.gui.preferences.PreferencesFragment$onPreferenceTreeClick$1 r1 = new org.videolan.vlc.gui.preferences.PreferencesFragment$onPreferenceTreeClick$1
            r1.<init>(r5, r6)
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            r0.setListener(r1)
            r6.setChecked(r4)
        L_0x00b4:
            return r4
        L_0x00b5:
            java.lang.String r1 = "ui_category"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00bf
            goto L_0x01d7
        L_0x00bf:
            org.videolan.vlc.gui.preferences.PreferencesUi r6 = new org.videolan.vlc.gui.preferences.PreferencesUi
            r6.<init>()
            androidx.fragment.app.Fragment r6 = (androidx.fragment.app.Fragment) r6
            r5.loadFragment(r6)
            goto L_0x01d6
        L_0x00cb:
            java.lang.String r1 = "casting_category"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00d5
            goto L_0x01d7
        L_0x00d5:
            org.videolan.vlc.gui.preferences.PreferencesCasting r6 = new org.videolan.vlc.gui.preferences.PreferencesCasting
            r6.<init>()
            androidx.fragment.app.Fragment r6 = (androidx.fragment.app.Fragment) r6
            r5.loadFragment(r6)
            goto L_0x01d6
        L_0x00e1:
            java.lang.String r1 = "parental_control"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00eb
            goto L_0x01d7
        L_0x00eb:
            org.videolan.tools.Settings r6 = org.videolan.tools.Settings.INSTANCE
            androidx.fragment.app.FragmentActivity r0 = r5.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            android.content.Context r0 = (android.content.Context) r0
            boolean r6 = r6.isPinCodeSet(r0)
            if (r6 == 0) goto L_0x0108
            org.videolan.vlc.gui.preferences.PreferencesParentalControl r6 = new org.videolan.vlc.gui.preferences.PreferencesParentalControl
            r6.<init>()
            androidx.fragment.app.Fragment r6 = (androidx.fragment.app.Fragment) r6
            r5.loadFragment(r6)
            goto L_0x01d6
        L_0x0108:
            org.videolan.vlc.gui.PinCodeActivity$Companion r6 = org.videolan.vlc.gui.PinCodeActivity.Companion
            androidx.fragment.app.FragmentActivity r0 = r5.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            android.content.Context r0 = (android.content.Context) r0
            org.videolan.vlc.gui.PinCodeReason r1 = org.videolan.vlc.gui.PinCodeReason.FIRST_CREATION
            android.content.Intent r6 = r6.getIntent(r0, r1)
            androidx.activity.result.ActivityResultLauncher<android.content.Intent> r0 = r5.pinCodeResult
            r0.launch(r6)
            goto L_0x01d6
        L_0x0120:
            java.lang.String r1 = "subtitles_category"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x012a
            goto L_0x01d7
        L_0x012a:
            org.videolan.vlc.gui.preferences.PreferencesSubtitles r6 = new org.videolan.vlc.gui.preferences.PreferencesSubtitles
            r6.<init>()
            androidx.fragment.app.Fragment r6 = (androidx.fragment.app.Fragment) r6
            r5.loadFragment(r6)
            goto L_0x01d6
        L_0x0136:
            java.lang.String r1 = "adv_category"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0140
            goto L_0x01d7
        L_0x0140:
            org.videolan.vlc.gui.preferences.PreferencesAdvanced r6 = new org.videolan.vlc.gui.preferences.PreferencesAdvanced
            r6.<init>()
            androidx.fragment.app.Fragment r6 = (androidx.fragment.app.Fragment) r6
            r5.loadFragment(r6)
            goto L_0x01d6
        L_0x014c:
            java.lang.String r1 = "video_category"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0156
            goto L_0x01d7
        L_0x0156:
            org.videolan.vlc.gui.preferences.PreferencesVideo r6 = new org.videolan.vlc.gui.preferences.PreferencesVideo
            r6.<init>()
            androidx.fragment.app.Fragment r6 = (androidx.fragment.app.Fragment) r6
            r5.loadFragment(r6)
            goto L_0x01d6
        L_0x0162:
            java.lang.String r1 = "directories"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x016b
            goto L_0x01d7
        L_0x016b:
            org.videolan.medialibrary.interfaces.Medialibrary r6 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            boolean r6 = r6.isWorking()
            if (r6 == 0) goto L_0x018f
            org.videolan.vlc.gui.helpers.UiTools r6 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            androidx.fragment.app.FragmentActivity r0 = r5.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            android.app.Activity r0 = (android.app.Activity) r0
            int r1 = org.videolan.vlc.R.string.settings_ml_block_scan
            java.lang.String r1 = r5.getString(r1)
            java.lang.String r2 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r6.snacker(r0, r1)
            goto L_0x01ae
        L_0x018f:
            androidx.fragment.app.FragmentActivity r6 = r5.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r3)
            android.content.Intent r0 = new android.content.Intent
            android.content.Context r1 = r6.getApplicationContext()
            java.lang.Class<org.videolan.vlc.gui.SecondaryActivity> r3 = org.videolan.vlc.gui.SecondaryActivity.class
            r0.<init>(r1, r3)
            java.lang.String r1 = "fragment"
            java.lang.String r3 = "storage_browser"
            r0.putExtra(r1, r3)
            r5.startActivity(r0)
            r6.setResult(r2)
        L_0x01ae:
            return r4
        L_0x01af:
            java.lang.String r1 = "audio_category"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01b8
            goto L_0x01d7
        L_0x01b8:
            org.videolan.vlc.gui.preferences.PreferencesAudio r6 = new org.videolan.vlc.gui.preferences.PreferencesAudio
            r6.<init>()
            androidx.fragment.app.Fragment r6 = (androidx.fragment.app.Fragment) r6
            r5.loadFragment(r6)
            goto L_0x01d6
        L_0x01c3:
            java.lang.String r1 = "remote_access_category"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01cc
            goto L_0x01d7
        L_0x01cc:
            org.videolan.vlc.gui.preferences.PreferencesRemoteAccess r6 = new org.videolan.vlc.gui.preferences.PreferencesRemoteAccess
            r6.<init>()
            androidx.fragment.app.Fragment r6 = (androidx.fragment.app.Fragment) r6
            r5.loadFragment(r6)
        L_0x01d6:
            return r4
        L_0x01d7:
            boolean r6 = super.onPreferenceTreeClick(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.PreferencesFragment.onPreferenceTreeClick(androidx.preference.Preference):boolean");
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        FragmentActivity activity = getActivity();
        if (activity != null && sharedPreferences != null && str != null) {
            if (Intrinsics.areEqual((Object) str, (Object) SettingsKt.KEY_VIDEO_APP_SWITCH)) {
                if (!AndroidUtil.isOOrLater) {
                    ListPreference listPreference = (ListPreference) findPreference(str);
                    if (Intrinsics.areEqual((Object) listPreference != null ? listPreference.getValue() : null, (Object) "2") && !Permissions.INSTANCE.canDrawOverlays(activity)) {
                        Permissions.INSTANCE.checkDrawOverlaysPermission(activity);
                    }
                }
            } else if (Intrinsics.areEqual((Object) str, (Object) SettingsKt.PLAYBACK_HISTORY) && sharedPreferences.getBoolean(str, true)) {
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
