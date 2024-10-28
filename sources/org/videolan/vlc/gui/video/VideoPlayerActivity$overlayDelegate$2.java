package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/gui/video/VideoPlayerOverlayDelegate;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$overlayDelegate$2 extends Lambda implements Function0<VideoPlayerOverlayDelegate> {
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$overlayDelegate$2(VideoPlayerActivity videoPlayerActivity) {
        super(0);
        this.this$0 = videoPlayerActivity;
    }

    public final VideoPlayerOverlayDelegate invoke() {
        return new VideoPlayerOverlayDelegate(this.this$0);
    }
}
