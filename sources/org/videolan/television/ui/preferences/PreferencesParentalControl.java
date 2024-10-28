package org.videolan.television.ui.preferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.preference.Preference;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.PinCodeActivity;
import org.videolan.vlc.gui.PinCodeReason;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0005H\u0014J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0017J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001c\u0010\u0014\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\bH\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016¨\u0006\u001b"}, d2 = {"Lorg/videolan/television/ui/preferences/PreferencesParentalControl;", "Lorg/videolan/television/ui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "getTitleId", "", "getXml", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "onStop", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesParentalControl.kt */
public final class PreferencesParentalControl extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_parental_control;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.parental_control;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Settings settings = Settings.INSTANCE;
        Activity activity = getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        if (!settings.isPinCodeSet(activity)) {
            PinCodeActivity.Companion companion = PinCodeActivity.Companion;
            Activity activity2 = getActivity();
            Intrinsics.checkNotNullExpressionValue(activity2, "getActivity(...)");
            startActivityForResult(companion.getIntent(activity2, PinCodeReason.FIRST_CREATION), 1);
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
        if (Intrinsics.areEqual((Object) preference.getKey(), (Object) "modify_pin_code")) {
            PinCodeActivity.Companion companion = PinCodeActivity.Companion;
            Activity activity = getActivity();
            Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
            startActivityForResult(companion.getIntent(activity, PinCodeReason.MODIFY), 0);
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Deprecated(message = "Deprecated in Java")
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 0) {
            Toast.makeText(getActivity(), R.string.pin_code_modified, 1).show();
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences != null && str != null && Intrinsics.areEqual((Object) str, (Object) SettingsKt.KEY_SAFE_MODE)) {
            Settings settings = Settings.INSTANCE;
            boolean z = false;
            if (sharedPreferences.getBoolean(str, false)) {
                Settings settings2 = Settings.INSTANCE;
                Activity activity = getActivity();
                Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
                if (settings2.isPinCodeSet(activity)) {
                    z = true;
                }
            }
            settings.setSafeMode(z);
        }
    }
}
