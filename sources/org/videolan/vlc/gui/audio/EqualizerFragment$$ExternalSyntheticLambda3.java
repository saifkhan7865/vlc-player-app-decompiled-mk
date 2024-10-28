package org.videolan.vlc.gui.audio;

import android.content.DialogInterface;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import org.videolan.libvlc.MediaPlayer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EqualizerFragment$$ExternalSyntheticLambda3 implements DialogInterface.OnShowListener {
    public final /* synthetic */ EqualizerFragment f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ EditText f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ MediaPlayer.Equalizer f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ int f$7;

    public /* synthetic */ EqualizerFragment$$ExternalSyntheticLambda3(EqualizerFragment equalizerFragment, AlertDialog alertDialog, EditText editText, String str, MediaPlayer.Equalizer equalizer, boolean z, boolean z2, int i) {
        this.f$0 = equalizerFragment;
        this.f$1 = alertDialog;
        this.f$2 = editText;
        this.f$3 = str;
        this.f$4 = equalizer;
        this.f$5 = z;
        this.f$6 = z2;
        this.f$7 = i;
    }

    public final void onShow(DialogInterface dialogInterface) {
        EqualizerFragment.createSaveCustomSetDialog$lambda$6(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, dialogInterface);
    }
}
