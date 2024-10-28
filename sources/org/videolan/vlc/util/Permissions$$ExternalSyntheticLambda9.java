package org.videolan.vlc.util;

import android.content.DialogInterface;
import androidx.fragment.app.FragmentActivity;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Permissions$$ExternalSyntheticLambda9 implements DialogInterface.OnClickListener {
    public final /* synthetic */ FragmentActivity f$0;
    public final /* synthetic */ Function1 f$1;

    public /* synthetic */ Permissions$$ExternalSyntheticLambda9(FragmentActivity fragmentActivity, Function1 function1) {
        this.f$0 = fragmentActivity;
        this.f$1 = function1;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Permissions.createExternalManagerDialog$lambda$3(this.f$0, this.f$1, dialogInterface, i);
    }
}
