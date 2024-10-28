package org.videolan.vlc.gui.video;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerActivity$$ExternalSyntheticLambda18 implements Observer {
    public final /* synthetic */ VideoPlayerActivity f$0;

    public /* synthetic */ VideoPlayerActivity$$ExternalSyntheticLambda18(VideoPlayerActivity videoPlayerActivity) {
        this.f$0 = videoPlayerActivity;
    }

    public final void onChanged(Object obj) {
        VideoPlayerActivity.playlistObserver$lambda$0(this.f$0, (List) obj);
    }
}
