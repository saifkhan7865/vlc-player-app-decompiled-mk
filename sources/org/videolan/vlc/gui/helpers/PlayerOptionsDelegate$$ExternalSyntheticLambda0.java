package org.videolan.vlc.gui.helpers;

import android.content.DialogInterface;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlayerOptionsDelegate$$ExternalSyntheticLambda0 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ PlayerOptionsDelegate f$0;

    public /* synthetic */ PlayerOptionsDelegate$$ExternalSyntheticLambda0(PlayerOptionsDelegate playerOptionsDelegate) {
        this.f$0 = playerOptionsDelegate;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        PlayerOptionsDelegate.showFragment$lambda$7(this.f$0, dialogInterface);
    }
}
