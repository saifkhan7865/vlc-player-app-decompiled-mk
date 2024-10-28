package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/gui/video/VideoStatsDelegate;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$statsDelegate$2 extends Lambda implements Function0<VideoStatsDelegate> {
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$statsDelegate$2(VideoPlayerActivity videoPlayerActivity) {
        super(0);
        this.this$0 = videoPlayerActivity;
    }

    public final VideoStatsDelegate invoke() {
        VideoPlayerActivity videoPlayerActivity = this.this$0;
        final VideoPlayerActivity videoPlayerActivity2 = this.this$0;
        final VideoPlayerActivity videoPlayerActivity3 = this.this$0;
        return new VideoStatsDelegate(videoPlayerActivity, new Function0<Unit>() {
            public final void invoke() {
                videoPlayerActivity2.getOverlayDelegate().showOverlayTimeout(-1);
            }
        }, new Function0<Unit>() {
            public final void invoke() {
                videoPlayerActivity3.getOverlayDelegate().showOverlay(true);
            }
        });
    }
}
