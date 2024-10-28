package org.videolan.vlc.util;

import android.content.DialogInterface;
import androidx.fragment.app.FragmentActivity;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Permissions$$ExternalSyntheticLambda5 implements DialogInterface.OnClickListener {
    public final /* synthetic */ FragmentActivity f$0;

    public /* synthetic */ Permissions$$ExternalSyntheticLambda5(FragmentActivity fragmentActivity) {
        this.f$0 = fragmentActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Permissions.createDialogCompat$lambda$6(this.f$0, dialogInterface, i);
    }
}
