package org.videolan.television.ui.preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.FeatureFlagWarningDialog;
import org.videolan.vlc.gui.dialogs.RenameDialog;
import org.videolan.vlc.util.FeatureFlag;
import org.videolan.vlc.util.FeatureFlagManager;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\nH\u0014J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001c\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\rH\u0016J\b\u0010\u001a\u001a\u00020\rH\u0016R\u0012\u0010\u0005\u001a\u00020\u0006X\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"Lorg/videolan/television/ui/preferences/PreferencesOptional;", "Lorg/videolan/television/ui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getTitleId", "", "getXml", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "onStop", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesOptional.kt */
public final class PreferencesOptional extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_optional;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.optional_features;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference("optional_features");
        for (FeatureFlag featureFlag : FeatureFlag.values()) {
            CheckBoxPreference checkBoxPreference = new CheckBoxPreference(getActivity());
            FeatureFlagManager featureFlagManager = FeatureFlagManager.INSTANCE;
            Activity activity = getActivity();
            Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
            checkBoxPreference.setChecked(featureFlagManager.isEnabled(activity, featureFlag));
            checkBoxPreference.setTitle((CharSequence) getString(featureFlag.getTitle()));
            checkBoxPreference.setKey(featureFlag.getKey());
            if (preferenceScreen != null) {
                preferenceScreen.addPreference(checkBoxPreference);
            }
            FeatureFlag dependsOn = featureFlag.getDependsOn();
            if (dependsOn != null) {
                checkBoxPreference.setDependency(dependsOn.getKey());
            }
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
            Preference findPreference = findPreference(str);
            Intrinsics.checkNotNull(findPreference);
            boolean isChecked = ((CheckBoxPreference) findPreference).isChecked();
            FeatureFlag byKey = FeatureFlagManager.INSTANCE.getByKey(str);
            if (byKey != null) {
                FeatureFlagManager featureFlagManager = FeatureFlagManager.INSTANCE;
                Activity activity = getActivity();
                Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
                featureFlagManager.enable(activity, byKey, isChecked);
            }
        }
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        if (preference.getKey() == null) {
            return super.onPreferenceTreeClick(preference);
        }
        FeatureFlagManager featureFlagManager = FeatureFlagManager.INSTANCE;
        String key = preference.getKey();
        Intrinsics.checkNotNullExpressionValue(key, "getKey(...)");
        FeatureFlag byKey = featureFlagManager.getByKey(key);
        if (byKey == null || byKey.getWarning() == null) {
            return super.onPreferenceTreeClick(preference);
        }
        Preference findPreference = findPreference(preference.getKey());
        Intrinsics.checkNotNull(findPreference);
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference;
        if (!checkBoxPreference.isChecked()) {
            return true;
        }
        checkBoxPreference.setChecked(false);
        FeatureFlagWarningDialog newInstance = FeatureFlagWarningDialog.Companion.newInstance(byKey, new PreferencesOptional$onPreferenceTreeClick$1$dialog$1(checkBoxPreference));
        Activity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        newInstance.show(((FragmentActivity) activity).getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
        return true;
    }
}
