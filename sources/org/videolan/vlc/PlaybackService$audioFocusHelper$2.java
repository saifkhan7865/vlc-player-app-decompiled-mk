package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.util.VLCAudioFocusHelper;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/util/VLCAudioFocusHelper;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackService.kt */
final class PlaybackService$audioFocusHelper$2 extends Lambda implements Function0<VLCAudioFocusHelper> {
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$audioFocusHelper$2(PlaybackService playbackService) {
        super(0);
        this.this$0 = playbackService;
    }

    public final VLCAudioFocusHelper invoke() {
        return new VLCAudioFocusHelper(this.this$0);
    }
}
