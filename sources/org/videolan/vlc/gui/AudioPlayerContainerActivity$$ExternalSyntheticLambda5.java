package org.videolan.vlc.gui;

import android.content.DialogInterface;
import android.view.KeyEvent;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioPlayerContainerActivity$$ExternalSyntheticLambda5 implements DialogInterface.OnKeyListener {
    public final /* synthetic */ AudioPlayerContainerActivity f$0;

    public /* synthetic */ AudioPlayerContainerActivity$$ExternalSyntheticLambda5(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        this.f$0 = audioPlayerContainerActivity;
    }

    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return AudioPlayerContainerActivity.showConfirmResumeDialog$lambda$11$lambda$10(this.f$0, dialogInterface, i, keyEvent);
    }
}
