package org.videolan.vlc.gui.video;

import android.content.SharedPreferences;
import android.widget.CompoundButton;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerResizeDelegate$$ExternalSyntheticLambda3 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ VideoPlayerResizeDelegate f$0;
    public final /* synthetic */ SharedPreferences f$1;

    public /* synthetic */ VideoPlayerResizeDelegate$$ExternalSyntheticLambda3(VideoPlayerResizeDelegate videoPlayerResizeDelegate, SharedPreferences sharedPreferences) {
        this.f$0 = videoPlayerResizeDelegate;
        this.f$1 = sharedPreferences;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        VideoPlayerResizeDelegate.showResizeOverlay$lambda$8$lambda$6(this.f$0, this.f$1, compoundButton, z);
    }
}
