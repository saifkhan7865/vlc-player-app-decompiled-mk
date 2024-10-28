package org.videolan.vlc.gui.video;

import android.view.View;
import androidx.leanback.widget.BrowseFrameLayout;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerResizeDelegate$$ExternalSyntheticLambda0 implements BrowseFrameLayout.OnFocusSearchListener {
    public final /* synthetic */ VideoPlayerResizeDelegate f$0;

    public /* synthetic */ VideoPlayerResizeDelegate$$ExternalSyntheticLambda0(VideoPlayerResizeDelegate videoPlayerResizeDelegate) {
        this.f$0 = videoPlayerResizeDelegate;
    }

    public final View onFocusSearch(View view, int i) {
        return VideoPlayerResizeDelegate.showResizeOverlay$lambda$8$lambda$1(this.f$0, view, i);
    }
}
