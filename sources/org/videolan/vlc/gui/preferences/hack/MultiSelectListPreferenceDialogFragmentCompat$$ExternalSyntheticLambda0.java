package org.videolan.vlc.gui.preferences.hack;

import android.content.DialogInterface;
import androidx.preference.MultiSelectListPreference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MultiSelectListPreferenceDialogFragmentCompat$$ExternalSyntheticLambda0 implements DialogInterface.OnMultiChoiceClickListener {
    public final /* synthetic */ MultiSelectListPreferenceDialogFragmentCompat f$0;
    public final /* synthetic */ MultiSelectListPreference f$1;

    public /* synthetic */ MultiSelectListPreferenceDialogFragmentCompat$$ExternalSyntheticLambda0(MultiSelectListPreferenceDialogFragmentCompat multiSelectListPreferenceDialogFragmentCompat, MultiSelectListPreference multiSelectListPreference) {
        this.f$0 = multiSelectListPreferenceDialogFragmentCompat;
        this.f$1 = multiSelectListPreference;
    }

    public final void onClick(DialogInterface dialogInterface, int i, boolean z) {
        MultiSelectListPreferenceDialogFragmentCompat.onPrepareDialogBuilder$lambda$0(this.f$0, this.f$1, dialogInterface, i, z);
    }
}
