package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "orientation", "Lorg/videolan/vlc/gui/video/OrientationMode;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOrientationDelegate.kt */
final class VideoPlayerOrientationDelegate$showOrientationOverlay$2$3 extends Lambda implements Function1<OrientationMode, Unit> {
    final /* synthetic */ VideoPlayerOrientationDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerOrientationDelegate$showOrientationOverlay$2$3(VideoPlayerOrientationDelegate videoPlayerOrientationDelegate) {
        super(1);
        this.this$0 = videoPlayerOrientationDelegate;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((OrientationMode) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(OrientationMode orientationMode) {
        Intrinsics.checkNotNullParameter(orientationMode, "orientation");
        this.this$0.player.setOrientation(orientationMode.getValue());
        this.this$0.hideOrientationOverlay();
    }
}
