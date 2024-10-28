package org.videolan.vlc.gui.video;

import androidx.lifecycle.Observer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerActivity$$ExternalSyntheticLambda20 implements Observer {
    public final /* synthetic */ VideoPlayerActivity f$0;

    public /* synthetic */ VideoPlayerActivity$$ExternalSyntheticLambda20(VideoPlayerActivity videoPlayerActivity) {
        this.f$0 = videoPlayerActivity;
    }

    public final void onChanged(Object obj) {
        VideoPlayerActivity.downloadedSubtitleObserver$lambda$3(this.f$0, (List) obj);
    }
}
