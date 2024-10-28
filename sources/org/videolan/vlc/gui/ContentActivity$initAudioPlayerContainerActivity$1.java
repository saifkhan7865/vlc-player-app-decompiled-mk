package org.videolan.vlc.gui;

import android.view.MenuItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.libvlc.RendererItem;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/libvlc/RendererItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContentActivity.kt */
final class ContentActivity$initAudioPlayerContainerActivity$1 extends Lambda implements Function1<RendererItem, Unit> {
    final /* synthetic */ ContentActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ContentActivity$initAudioPlayerContainerActivity$1(ContentActivity contentActivity) {
        super(1);
        this.this$0 = contentActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((RendererItem) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(RendererItem rendererItem) {
        MenuItem findItem = this.this$0.getToolbar().getMenu().findItem(R.id.ml_menu_renderers);
        if (findItem != null) {
            findItem.setVisible(!this.this$0.hideRenderers() && this.this$0.showRenderers);
            findItem.setIcon(!PlaybackService.Companion.hasRenderer() ? R.drawable.ic_renderer : R.drawable.ic_renderer_on);
        }
    }
}
