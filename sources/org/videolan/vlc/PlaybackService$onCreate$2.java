package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.media.MediaSessionBrowser;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackService.kt */
final class PlaybackService$onCreate$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$onCreate$2(PlaybackService playbackService) {
        super(0);
        this.this$0 = playbackService;
    }

    public final void invoke() {
        String access$getLastParentId$p = this.this$0.lastParentId;
        if (Intrinsics.areEqual((Object) access$getLastParentId$p, (Object) MediaSessionBrowser.ID_HOME) || Intrinsics.areEqual((Object) access$getLastParentId$p, (Object) MediaSessionBrowser.ID_HISTORY)) {
            PlaybackService playbackService = this.this$0;
            playbackService.notifyChildrenChanged(playbackService.lastParentId);
        }
    }
}
