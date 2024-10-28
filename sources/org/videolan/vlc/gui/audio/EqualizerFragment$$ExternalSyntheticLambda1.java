package org.videolan.vlc.gui.audio;

import android.content.DialogInterface;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EqualizerFragment$$ExternalSyntheticLambda1 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ EqualizerFragment f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ EqualizerFragment$$ExternalSyntheticLambda1(boolean z, EqualizerFragment equalizerFragment, int i) {
        this.f$0 = z;
        this.f$1 = equalizerFragment;
        this.f$2 = i;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        EqualizerFragment.createSaveCustomSetDialog$lambda$3(this.f$0, this.f$1, this.f$2, dialogInterface);
    }
}
