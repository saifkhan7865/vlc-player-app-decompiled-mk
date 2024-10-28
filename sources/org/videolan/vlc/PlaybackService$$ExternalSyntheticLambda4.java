package org.videolan.vlc;

import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.AbstractVLCEvent;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlaybackService$$ExternalSyntheticLambda4 implements MediaPlayer.EventListener {
    public final /* synthetic */ PlaybackService f$0;

    public /* synthetic */ PlaybackService$$ExternalSyntheticLambda4(PlaybackService playbackService) {
        this.f$0 = playbackService;
    }

    public final void onEvent(AbstractVLCEvent abstractVLCEvent) {
        PlaybackService.mediaPlayerListener$lambda$1(this.f$0, (MediaPlayer.Event) abstractVLCEvent);
    }
}
