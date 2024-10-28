package org.videolan.vlc;

import androidx.lifecycle.Observer;
import org.videolan.libvlc.MediaPlayer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlaybackService$$ExternalSyntheticLambda3 implements Observer {
    public final /* synthetic */ PlaybackService f$0;

    public /* synthetic */ PlaybackService$$ExternalSyntheticLambda3(PlaybackService playbackService) {
        this.f$0 = playbackService;
    }

    public final void onChanged(Object obj) {
        PlaybackService.onCreate$lambda$10(this.f$0, (MediaPlayer.Equalizer) obj);
    }
}
