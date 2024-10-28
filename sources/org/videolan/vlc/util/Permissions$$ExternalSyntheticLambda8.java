package org.videolan.vlc.util;

import android.content.DialogInterface;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Permissions$$ExternalSyntheticLambda8 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ Permissions$$ExternalSyntheticLambda8(Function1 function1) {
        this.f$0 = function1;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Permissions.createExternalManagerDialog$lambda$2(this.f$0, dialogInterface, i);
    }
}
