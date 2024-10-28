package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.media.PlayerController;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/media/PlayerController;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreviewVideoInputService.kt */
final class PreviewVideoInputService$PreviewSession$player$2 extends Lambda implements Function0<PlayerController> {
    final /* synthetic */ PreviewVideoInputService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreviewVideoInputService$PreviewSession$player$2(PreviewVideoInputService previewVideoInputService) {
        super(0);
        this.this$0 = previewVideoInputService;
    }

    public final PlayerController invoke() {
        return new PlayerController(this.this$0.getApplicationContext());
    }
}
