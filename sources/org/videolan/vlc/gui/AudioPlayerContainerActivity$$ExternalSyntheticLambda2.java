package org.videolan.vlc.gui;

import android.content.DialogInterface;
import android.widget.CheckBox;
import org.videolan.vlc.media.WaitConfirmation;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioPlayerContainerActivity$$ExternalSyntheticLambda2 implements DialogInterface.OnClickListener {
    public final /* synthetic */ CheckBox f$0;
    public final /* synthetic */ AudioPlayerContainerActivity f$1;
    public final /* synthetic */ WaitConfirmation f$2;

    public /* synthetic */ AudioPlayerContainerActivity$$ExternalSyntheticLambda2(CheckBox checkBox, AudioPlayerContainerActivity audioPlayerContainerActivity, WaitConfirmation waitConfirmation) {
        this.f$0 = checkBox;
        this.f$1 = audioPlayerContainerActivity;
        this.f$2 = waitConfirmation;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        AudioPlayerContainerActivity.showConfirmResumeDialog$lambda$7(this.f$0, this.f$1, this.f$2, dialogInterface, i);
    }
}
