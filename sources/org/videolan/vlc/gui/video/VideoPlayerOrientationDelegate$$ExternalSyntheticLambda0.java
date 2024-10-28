package org.videolan.vlc.gui.video;

import android.view.View;
import androidx.leanback.widget.BrowseFrameLayout;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerOrientationDelegate$$ExternalSyntheticLambda0 implements BrowseFrameLayout.OnFocusSearchListener {
    public final /* synthetic */ VideoPlayerOrientationDelegate f$0;

    public /* synthetic */ VideoPlayerOrientationDelegate$$ExternalSyntheticLambda0(VideoPlayerOrientationDelegate videoPlayerOrientationDelegate) {
        this.f$0 = videoPlayerOrientationDelegate;
    }

    public final View onFocusSearch(View view, int i) {
        return VideoPlayerOrientationDelegate.showOrientationOverlay$lambda$4$lambda$1(this.f$0, view, i);
    }
}
