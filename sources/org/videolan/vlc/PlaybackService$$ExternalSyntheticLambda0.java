package org.videolan.vlc;

import androidx.lifecycle.Observer;
import org.videolan.libvlc.RendererItem;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlaybackService$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ PlaybackService f$0;

    public /* synthetic */ PlaybackService$$ExternalSyntheticLambda0(PlaybackService playbackService) {
        this.f$0 = playbackService;
    }

    public final void onChanged(Object obj) {
        PlaybackService.onCreate$lambda$7(this.f$0, (RendererItem) obj);
    }
}
