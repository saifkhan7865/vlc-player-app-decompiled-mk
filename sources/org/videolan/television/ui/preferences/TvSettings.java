package org.videolan.television.ui.preferences;

import android.app.Fragment;
import androidx.leanback.preference.LeanbackPreferenceFragment;
import androidx.leanback.preference.LeanbackSettingsFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceDialogFragment;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceScreen;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/videolan/television/ui/preferences/TvSettings;", "Landroidx/leanback/preference/LeanbackSettingsFragment;", "()V", "onPreferenceStartFragment", "", "caller", "Landroidx/preference/PreferenceFragment;", "pref", "Landroidx/preference/Preference;", "onPreferenceStartInitialScreen", "", "onPreferenceStartScreen", "Landroidx/preference/PreferenceScreen;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvSettings.kt */
public final class TvSettings extends LeanbackSettingsFragment {
    public boolean onPreferenceStartScreen(PreferenceFragment preferenceFragment, PreferenceScreen preferenceScreen) {
        Intrinsics.checkNotNullParameter(preferenceFragment, "caller");
        Intrinsics.checkNotNullParameter(preferenceScreen, "pref");
        return false;
    }

    public void onPreferenceStartInitialScreen() {
        startPreferenceFragment(new PreferencesFragment());
    }

    public boolean onPreferenceStartFragment(PreferenceFragment preferenceFragment, Preference preference) {
        Intrinsics.checkNotNullParameter(preferenceFragment, "caller");
        Intrinsics.checkNotNullParameter(preference, "pref");
        Fragment instantiate = LeanbackPreferenceFragment.instantiate(getActivity(), preference.getFragment(), preference.getExtras());
        instantiate.setTargetFragment(preferenceFragment, 0);
        if ((instantiate instanceof PreferenceFragment) || (instantiate instanceof PreferenceDialogFragment)) {
            startPreferenceFragment(instantiate);
            return true;
        }
        startImmersiveFragment(instantiate);
        return true;
    }
}
