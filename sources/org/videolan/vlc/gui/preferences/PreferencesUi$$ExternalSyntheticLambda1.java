package org.videolan.vlc.gui.preferences;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PreferencesUi$$ExternalSyntheticLambda1 implements Preference.SummaryProvider {
    public final /* synthetic */ PreferencesUi f$0;

    public /* synthetic */ PreferencesUi$$ExternalSyntheticLambda1(PreferencesUi preferencesUi) {
        this.f$0 = preferencesUi;
    }

    public final CharSequence provideSummary(Preference preference) {
        return PreferencesUi.onCreatePreferences$lambda$1(this.f$0, (EditTextPreference) preference);
    }
}
