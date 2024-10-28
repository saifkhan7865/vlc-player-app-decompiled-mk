package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.util.PlaylistFilterDelegate;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/util/PlaylistFilterDelegate;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistModel.kt */
final class PlaylistModel$filter$2 extends Lambda implements Function0<PlaylistFilterDelegate> {
    final /* synthetic */ PlaylistModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistModel$filter$2(PlaylistModel playlistModel) {
        super(0);
        this.this$0 = playlistModel;
    }

    public final PlaylistFilterDelegate invoke() {
        return new PlaylistFilterDelegate(this.this$0.getDataset());
    }
}
