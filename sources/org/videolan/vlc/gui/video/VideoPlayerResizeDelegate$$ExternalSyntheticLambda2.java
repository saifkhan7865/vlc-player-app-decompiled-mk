package org.videolan.vlc.gui.video;

import android.content.SharedPreferences;
import android.widget.CompoundButton;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerResizeDelegate$$ExternalSyntheticLambda2 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ SharedPreferences f$0;
    public final /* synthetic */ VideoPlayerResizeDelegate f$1;

    public /* synthetic */ VideoPlayerResizeDelegate$$ExternalSyntheticLambda2(SharedPreferences sharedPreferences, VideoPlayerResizeDelegate videoPlayerResizeDelegate) {
        this.f$0 = sharedPreferences;
        this.f$1 = videoPlayerResizeDelegate;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        VideoPlayerResizeDelegate.showResizeOverlay$lambda$8$lambda$4(this.f$0, this.f$1, compoundButton, z);
    }
}
