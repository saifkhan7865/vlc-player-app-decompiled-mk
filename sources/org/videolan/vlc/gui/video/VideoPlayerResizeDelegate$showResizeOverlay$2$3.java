package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.libvlc.MediaPlayer;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "scale", "Lorg/videolan/libvlc/MediaPlayer$ScaleType;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerResizeDelegate.kt */
final class VideoPlayerResizeDelegate$showResizeOverlay$2$3 extends Lambda implements Function1<MediaPlayer.ScaleType, Unit> {
    final /* synthetic */ VideoPlayerResizeDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerResizeDelegate$showResizeOverlay$2$3(VideoPlayerResizeDelegate videoPlayerResizeDelegate) {
        super(1);
        this.this$0 = videoPlayerResizeDelegate;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MediaPlayer.ScaleType) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(MediaPlayer.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(scaleType, "scale");
        this.this$0.setVideoScale(scaleType);
    }
}
