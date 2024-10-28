package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.HalfCircleView;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/gui/view/HalfCircleView;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTouchDelegate.kt */
final class VideoTouchDelegate$rightContainerBackground$2 extends Lambda implements Function0<HalfCircleView> {
    final /* synthetic */ VideoTouchDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoTouchDelegate$rightContainerBackground$2(VideoTouchDelegate videoTouchDelegate) {
        super(0);
        this.this$0 = videoTouchDelegate;
    }

    public final HalfCircleView invoke() {
        return (HalfCircleView) this.this$0.player.findViewById(R.id.rightContainerBackground);
    }
}
