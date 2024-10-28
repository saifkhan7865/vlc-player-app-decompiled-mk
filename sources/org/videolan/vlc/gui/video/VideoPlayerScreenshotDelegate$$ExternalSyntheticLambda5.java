package org.videolan.vlc.gui.video;

import android.graphics.Bitmap;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ VideoPlayerScreenshotDelegate f$0;
    public final /* synthetic */ Bitmap f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda5(VideoPlayerScreenshotDelegate videoPlayerScreenshotDelegate, Bitmap bitmap, int i, float f, int i2) {
        this.f$0 = videoPlayerScreenshotDelegate;
        this.f$1 = bitmap;
        this.f$2 = i;
        this.f$3 = f;
        this.f$4 = i2;
    }

    public final void run() {
        VideoPlayerScreenshotDelegate.takeScreenshot$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
