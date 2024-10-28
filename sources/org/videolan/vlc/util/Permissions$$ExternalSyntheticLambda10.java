package org.videolan.vlc.util;

import android.app.Activity;
import android.content.DialogInterface;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Permissions$$ExternalSyntheticLambda10 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ Permissions$$ExternalSyntheticLambda10(Activity activity, String str) {
        this.f$0 = activity;
        this.f$1 = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Permissions.createSettingsDialogCompat$lambda$9(this.f$0, this.f$1, dialogInterface, i);
    }
}
