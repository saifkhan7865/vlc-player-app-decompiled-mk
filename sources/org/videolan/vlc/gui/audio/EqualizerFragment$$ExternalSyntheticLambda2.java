package org.videolan.vlc.gui.audio;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import org.videolan.libvlc.MediaPlayer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EqualizerFragment$$ExternalSyntheticLambda2 implements View.OnKeyListener {
    public final /* synthetic */ EqualizerFragment f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ EditText f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ MediaPlayer.Equalizer f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ int f$7;

    public /* synthetic */ EqualizerFragment$$ExternalSyntheticLambda2(EqualizerFragment equalizerFragment, AlertDialog alertDialog, EditText editText, String str, MediaPlayer.Equalizer equalizer, boolean z, boolean z2, int i) {
        this.f$0 = equalizerFragment;
        this.f$1 = alertDialog;
        this.f$2 = editText;
        this.f$3 = str;
        this.f$4 = equalizer;
        this.f$5 = z;
        this.f$6 = z2;
        this.f$7 = i;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        return EqualizerFragment.createSaveCustomSetDialog$lambda$4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, view, i, keyEvent);
    }
}