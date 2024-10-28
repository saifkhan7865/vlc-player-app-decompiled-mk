package org.videolan.vlc.gui.video;

import org.videolan.vlc.PlaybackService;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerActivity$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ PlaybackService f$0;
    public final /* synthetic */ VideoPlayerActivity f$1;

    public /* synthetic */ VideoPlayerActivity$$ExternalSyntheticLambda23(PlaybackService playbackService, VideoPlayerActivity videoPlayerActivity) {
        this.f$0 = playbackService;
        this.f$1 = videoPlayerActivity;
    }

    public final void run() {
        VideoPlayerActivity.onServiceChanged$lambda$60(this.f$0, this.f$1);
    }
}
