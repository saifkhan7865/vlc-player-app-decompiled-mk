package org.videolan.vlc.gui.video;

import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.circularreveal.CircularRevealFrameLayout;
import org.videolan.vlc.gui.view.HalfCircleView;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoTouchDelegate$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ VideoTouchDelegate f$0;
    public final /* synthetic */ ImageView f$1;
    public final /* synthetic */ ImageView f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ CircularRevealFrameLayout f$4;
    public final /* synthetic */ HalfCircleView f$5;
    public final /* synthetic */ TextView f$6;

    public /* synthetic */ VideoTouchDelegate$$ExternalSyntheticLambda2(VideoTouchDelegate videoTouchDelegate, ImageView imageView, ImageView imageView2, boolean z, CircularRevealFrameLayout circularRevealFrameLayout, HalfCircleView halfCircleView, TextView textView) {
        this.f$0 = videoTouchDelegate;
        this.f$1 = imageView;
        this.f$2 = imageView2;
        this.f$3 = z;
        this.f$4 = circularRevealFrameLayout;
        this.f$5 = halfCircleView;
        this.f$6 = textView;
    }

    public final void run() {
        VideoTouchDelegate.showSeek$lambda$4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
