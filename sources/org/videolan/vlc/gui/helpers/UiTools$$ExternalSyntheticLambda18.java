package org.videolan.vlc.gui.helpers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UiTools$$ExternalSyntheticLambda18 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ UiTools$$ExternalSyntheticLambda18(Activity activity, Intent intent) {
        this.f$0 = activity;
        this.f$1 = intent;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        UiTools.newStorageDetected$lambda$21(this.f$0, this.f$1, dialogInterface, i);
    }
}
