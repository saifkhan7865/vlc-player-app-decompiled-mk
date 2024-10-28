package org.videolan.vlc.gui.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.StartActivity;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\tH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001c\u0010\u0015\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001c\u0010!\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010\u00072\b\u0010#\u001a\u0004\u0018\u00010\u0018H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/videolan/vlc/gui/preferences/PreferencesRemoteAccess;", "Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "medialibraryContentPreference", "Landroidx/preference/MultiSelectListPreference;", "settings", "Landroid/content/SharedPreferences;", "getTitleId", "", "getXml", "manageMLContentSummary", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreatePreferences", "bundle", "s", "", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPreferenceTreeClick", "preference", "Landroidx/preference/Preference;", "onPrepareOptionsMenu", "onSharedPreferenceChanged", "sharedPreferences", "key", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesRemoteAccess.kt */
public final class PreferencesRemoteAccess extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private MultiSelectListPreference medialibraryContentPreference;
    private SharedPreferences settings;

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
        setHasOptionsMenu(true);
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
            intent.setClassName(requireActivity(), Constants.REMOTE_ACCESS_ONBOARDING);
            startActivity(intent);
        }
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        Settings settings2 = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.settings = (SharedPreferences) settings2.getInstance(requireActivity);
        Preference findPreference = findPreference(SettingsKt.KEY_REMOTE_ACCESS_ML_CONTENT);
        Intrinsics.checkNotNull(findPreference);
        this.medialibraryContentPreference = (MultiSelectListPreference) findPreference;
        manageMLContentSummary();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == R.id.menu_remote_access_onboarding) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName(requireActivity(), Constants.REMOTE_ACCESS_ONBOARDING);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "inflater");
        menu.findItem(R.id.menu_remote_access_onboarding).setVisible(true);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.findItem(R.id.menu_remote_access_onboarding).setVisible(true);
        super.onPrepareOptionsMenu(menu);
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
        multiSelectListPreference.setSummary((CharSequence) getString(R.string.remote_access_medialibrary_content_summary, str, str5));
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        if (Intrinsics.areEqual((Object) preference.getKey(), (Object) "remote_access_status")) {
            FragmentActivity requireActivity = requireActivity();
            Intent intent = new Intent(requireActivity(), StartActivity.class);
            intent.setAction("vlc.remoteaccess.share");
            requireActivity.startActivity(intent);
        }
        return super.onPreferenceTreeClick(preference);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -1797417535) {
                if (hashCode != -1608151153) {
                    if (hashCode == -1122829293 && str.equals(SettingsKt.KEY_REMOTE_ACCESS_ML_CONTENT)) {
                        manageMLContentSummary();
                    }
                } else if (str.equals(SettingsKt.REMOTE_ACCESS_NETWORK_BROWSER_CONTENT)) {
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                    ExtensionsKt.restartRemoteAccess(requireActivity);
                }
            } else if (!str.equals(SettingsKt.KEY_ENABLE_REMOTE_ACCESS)) {
            } else {
                if (sharedPreferences == null || !sharedPreferences.getBoolean(SettingsKt.KEY_ENABLE_REMOTE_ACCESS, false)) {
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                    ExtensionsKt.stopRemoteAccess(requireActivity2);
                    return;
                }
                FragmentActivity requireActivity3 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                ExtensionsKt.startRemoteAccess(requireActivity3);
            }
        }
    }
}
