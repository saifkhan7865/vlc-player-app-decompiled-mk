package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/media/PlayerController;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$player$2 extends Lambda implements Function0<PlayerController> {
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$player$2(PlaylistManager playlistManager) {
        super(0);
        this.this$0 = playlistManager;
    }

    public final PlayerController invoke() {
        return new PlayerController(this.this$0.getService().getApplicationContext());
    }
}
