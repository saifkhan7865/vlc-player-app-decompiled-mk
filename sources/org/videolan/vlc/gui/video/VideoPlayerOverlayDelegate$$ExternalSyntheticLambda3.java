package org.videolan.vlc.gui.video;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerOverlayDelegate$$ExternalSyntheticLambda3 implements View.OnTouchListener {
    public final /* synthetic */ VideoPlayerOverlayDelegate f$0;

    public /* synthetic */ VideoPlayerOverlayDelegate$$ExternalSyntheticLambda3(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate) {
        this.f$0 = videoPlayerOverlayDelegate;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return VideoPlayerOverlayDelegate.setListeners$lambda$23(this.f$0, view, motionEvent);
    }
}
