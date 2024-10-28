package org.videolan.vlc.gui;

import androidx.lifecycle.Observer;
import org.videolan.vlc.media.WaitConfirmation;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioPlayerContainerActivity$$ExternalSyntheticLambda7 implements Observer {
    public final /* synthetic */ AudioPlayerContainerActivity f$0;

    public /* synthetic */ AudioPlayerContainerActivity$$ExternalSyntheticLambda7(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        this.f$0 = audioPlayerContainerActivity;
    }

    public final void onChanged(Object obj) {
        AudioPlayerContainerActivity.observer$lambda$1(this.f$0, (WaitConfirmation) obj);
    }
}
