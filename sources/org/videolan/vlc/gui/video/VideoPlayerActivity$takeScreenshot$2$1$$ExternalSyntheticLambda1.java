package org.videolan.vlc.gui.video;

import android.graphics.Bitmap;
import android.view.PixelCopy;
import android.view.SurfaceView;
import java.io.File;
import org.videolan.vlc.gui.video.VideoPlayerActivity$takeScreenshot$2;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerActivity$takeScreenshot$2$1$$ExternalSyntheticLambda1 implements PixelCopy.OnPixelCopyFinishedListener {
    public final /* synthetic */ VideoPlayerActivity f$0;
    public final /* synthetic */ SurfaceView f$1;
    public final /* synthetic */ Bitmap f$2;
    public final /* synthetic */ File f$3;
    public final /* synthetic */ SurfaceView f$4;

    public /* synthetic */ VideoPlayerActivity$takeScreenshot$2$1$$ExternalSyntheticLambda1(VideoPlayerActivity videoPlayerActivity, SurfaceView surfaceView, Bitmap bitmap, File file, SurfaceView surfaceView2) {
        this.f$0 = videoPlayerActivity;
        this.f$1 = surfaceView;
        this.f$2 = bitmap;
        this.f$3 = file;
        this.f$4 = surfaceView2;
    }

    public final void onPixelCopyFinished(int i) {
        VideoPlayerActivity$takeScreenshot$2.AnonymousClass1.invokeSuspend$lambda$3$lambda$2$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, i);
    }
}
