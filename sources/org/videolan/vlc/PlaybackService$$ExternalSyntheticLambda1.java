package org.videolan.vlc;

import androidx.lifecycle.Observer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlaybackService$$ExternalSyntheticLambda1 implements Observer {
    public final /* synthetic */ PlaybackService f$0;

    public /* synthetic */ PlaybackService$$ExternalSyntheticLambda1(PlaybackService playbackService) {
        this.f$0 = playbackService;
    }

    public final void onChanged(Object obj) {
        PlaybackService.onCreate$lambda$8(this.f$0, ((Boolean) obj).booleanValue());
    }
}
