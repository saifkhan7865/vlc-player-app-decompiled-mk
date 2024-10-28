package org.videolan.vlc.gui.helpers;

import android.app.Activity;
import android.content.DialogInterface;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UiTools$$ExternalSyntheticLambda26 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Activity f$0;

    public /* synthetic */ UiTools$$ExternalSyntheticLambda26(Activity activity) {
        this.f$0 = activity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        UiTools.confirmExit$lambda$19(this.f$0, dialogInterface, i);
    }
}
