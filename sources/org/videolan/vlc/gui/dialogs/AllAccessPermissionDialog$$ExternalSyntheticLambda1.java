package org.videolan.vlc.gui.dialogs;

import android.content.SharedPreferences;
import android.widget.CompoundButton;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AllAccessPermissionDialog$$ExternalSyntheticLambda1 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ SharedPreferences f$0;

    public /* synthetic */ AllAccessPermissionDialog$$ExternalSyntheticLambda1(SharedPreferences sharedPreferences) {
        this.f$0 = sharedPreferences;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        AllAccessPermissionDialog.onCreateView$lambda$1(this.f$0, compoundButton, z);
    }
}
