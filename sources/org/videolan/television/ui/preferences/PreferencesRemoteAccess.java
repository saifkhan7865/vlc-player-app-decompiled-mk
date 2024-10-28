package org.videolan.television.ui.preferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.StartActivity;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u000eH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0012\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u001c\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u001c\u0010\u001d\u001a\u00020\u00112\b\u0010\u001e\u001a\u0004\u0018\u00010\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0018H\u0016R\u0012\u0010\u0005\u001a\u00020\u0006X\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lorg/videolan/television/ui/preferences/PreferencesRemoteAccess;", "Lorg/videolan/television/ui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "medialibraryContentPreference", "Landroidx/preference/MultiSelectListPreference;", "settings", "Landroid/content/SharedPreferences;", "getTitleId", "", "getXml", "manageMLContentSummary", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreatePreferences", "bundle", "s", "", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onSharedPreferenceChanged", "sharedPreferences", "key", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesRemoteAccess.kt */
public final class PreferencesRemoteAccess extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();
    private MultiSelectListPreference medialibraryContentPreference;
    private SharedPreferences settings;

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.remote_access;
    }

    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_remote_access;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        SharedPreferences sharedPreferences2 = this.settings;
        SharedPreferences sharedPreferences3 = null;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences2 = null;
        }
        if (!sharedPreferences2.getBoolean(Constants.REMOTE_ACCESS_ONBOARDING, false)) {
            SharedPreferences sharedPreferences4 = this.settings;
            if (sharedPreferences4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settings");
            } else {
                sharedPreferences3 = sharedPreferences4;
            }
            SettingsKt.putSingle(sharedPreferences3, Constants.REMOTE_ACCESS_ONBOARDING, true);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName(getActivity(), Constants.REMOTE_ACCESS_ONBOARDING);
            startActivity(intent);
        }
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        Settings settings2 = Settings.INSTANCE;
        Activity activity = getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        this.settings = (SharedPreferences) settings2.getInstance(activity);
        Preference findPreference = findPreference(SettingsKt.KEY_REMOTE_ACCESS_ML_CONTENT);
        Intrinsics.checkNotNull(findPreference);
        this.medialibraryContentPreference = (MultiSelectListPreference) findPreference;
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference("remote_access_info");
        if (preferenceScreen != null) {
            preferenceScreen.setVisible(true);
        }
        manageMLContentSummary();
    }

    private final void manageMLContentSummary() {
        String str;
        SharedPreferences sharedPreferences = this.settings;
        MultiSelectListPreference multiSelectListPreference = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        String[] stringArray = getResources().getStringArray(R.array.remote_access_content_values);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        Set<String> stringSet = sharedPreferences.getStringSet(SettingsKt.KEY_REMOTE_ACCESS_ML_CONTENT, ArraysKt.toSet((T[]) (Object[]) stringArray));
        Intrinsics.checkNotNull(stringSet);
        String[] stringArray2 = getResources().getStringArray(R.array.remote_access_content_values);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "getStringArray(...)");
        String[] stringArray3 = getResources().getStringArray(R.array.remote_access_content_entries);
        Intrinsics.checkNotNullExpressionValue(stringArray3, "getStringArray(...)");
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (String indexOf : stringSet) {
            String str2 = stringArray3[ArraysKt.indexOf((T[]) stringArray2, indexOf)];
            Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
            arrayList.add(str2);
        }
        for (String str3 : stringArray2) {
            if (!stringSet.contains(str3)) {
                String str4 = stringArray3[ArraysKt.indexOf((T[]) stringArray2, str3)];
                Intrinsics.checkNotNullExpressionValue(str4, "get(...)");
                arrayList2.add(str4);
            }
        }
        String str5 = "-";
        if (arrayList.isEmpty()) {
            str = str5;
        } else {
            str = TextUtils.INSTANCE.separatedString((String[]) arrayList.toArray(new String[0]));
        }
        if (!arrayList2.isEmpty()) {
            str5 = TextUtils.INSTANCE.separatedString((String[]) arrayList2.toArray(new String[0]));
        }
        MultiSelectListPreference multiSelectListPreference2 = this.medialibraryContentPreference;
        if (multiSelectListPreference2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibraryContentPreference");
        } else {
            multiSelectListPreference = multiSelectListPreference2;
        }
        multiSelectListPreference.setSummary((CharSequence) getString(R.string.remote_access_medialibrary_content_summary, new Object[]{str, str5}));
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        if (Intrinsics.areEqual((Object) preference.getKey(), (Object) "remote_access_status")) {
            Activity activity = getActivity();
            Intent intent = new Intent(getActivity(), StartActivity.class);
            intent.setAction("vlc.remoteaccess.share");
            activity.startActivity(intent);
        }
        if (Intrinsics.areEqual((Object) preference.getKey(), (Object) "remote_access_info")) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setClassName(getActivity(), Constants.REMOTE_ACCESS_ONBOARDING);
            startActivity(intent2);
        }
        return super.onPreferenceTreeClick(preference);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        String str2;
        if (str != null) {
            switch (str.hashCode()) {
                case -1797417535:
                    if (str.equals(SettingsKt.KEY_ENABLE_REMOTE_ACCESS)) {
                        boolean z = false;
                        if (sharedPreferences != null) {
                            z = sharedPreferences.getBoolean(SettingsKt.KEY_ENABLE_REMOTE_ACCESS, false);
                        }
                        Settings.INSTANCE.getRemoteAccessEnabled().postValue(Boolean.valueOf(z));
                        if (z) {
                            Activity activity = getActivity();
                            Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
                            ExtensionsKt.startRemoteAccess(activity);
                            return;
                        }
                        Activity activity2 = getActivity();
                        Intrinsics.checkNotNullExpressionValue(activity2, "getActivity(...)");
                        ExtensionsKt.stopRemoteAccess(activity2);
                        return;
                    }
                    return;
                case -1608151153:
                    if (str.equals(SettingsKt.REMOTE_ACCESS_NETWORK_BROWSER_CONTENT)) {
                        Activity activity3 = getActivity();
                        Intrinsics.checkNotNullExpressionValue(activity3, "getActivity(...)");
                        ExtensionsKt.restartRemoteAccess(activity3);
                        return;
                    }
                    return;
                case -1122829293:
                    if (str.equals(SettingsKt.KEY_REMOTE_ACCESS_ML_CONTENT)) {
                        manageMLContentSummary();
                        return;
                    }
                    return;
                case -6995013:
                    str2 = SettingsKt.REMOTE_ACCESS_PLAYBACK_CONTROL;
                    break;
                case 1894239521:
                    str2 = SettingsKt.REMOTE_ACCESS_FILE_BROWSER_CONTENT;
                    break;
                default:
                    return;
            }
            str.equals(str2);
        }
    }
}
