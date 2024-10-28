package org.videolan.vlc.gui.video;

import android.view.View;
import java.io.File;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda4 implements View.OnClickListener {
    public final /* synthetic */ VideoPlayerScreenshotDelegate f$0;
    public final /* synthetic */ File f$1;

    public /* synthetic */ VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda4(VideoPlayerScreenshotDelegate videoPlayerScreenshotDelegate, File file) {
        this.f$0 = videoPlayerScreenshotDelegate;
        this.f$1 = file;
    }

    public final void onClick(View view) {
        VideoPlayerScreenshotDelegate.takeScreenshot$lambda$0(this.f$0, this.f$1, view);
    }
}
