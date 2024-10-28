package org.videolan.vlc.gui.video;

import androidx.appcompat.content.res.AppCompatResources;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.libvlc.RendererItem;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "rendererItem", "Lorg/videolan/libvlc/RendererItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOverlayDelegate.kt */
final class VideoPlayerOverlayDelegate$initOverlay$1$7 extends Lambda implements Function1<RendererItem, Unit> {
    final /* synthetic */ VideoPlayerOverlayDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerOverlayDelegate$initOverlay$1$7(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate) {
        super(1);
        this.this$0 = videoPlayerOverlayDelegate;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((RendererItem) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(RendererItem rendererItem) {
        this.this$0.getHudRightBinding().videoRenderer.setImageDrawable(AppCompatResources.getDrawable(this.this$0.player, rendererItem == null ? R.drawable.ic_player_renderer : R.drawable.ic_player_renderer_on));
    }
}
