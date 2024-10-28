package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistAdapter.kt */
final class PlaylistAdapter$onItemDismiss$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MediaWrapper $media;
    final /* synthetic */ int $position;
    final /* synthetic */ PlaylistAdapter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistAdapter$onItemDismiss$2(PlaylistAdapter playlistAdapter, int i, MediaWrapper mediaWrapper) {
        super(0);
        this.this$0 = playlistAdapter;
        this.$position = i;
        this.$media = mediaWrapper;
    }

    public final void invoke() {
        PlaylistModel access$getModel$p = this.this$0.model;
        if (access$getModel$p != null) {
            access$getModel$p.insertMedia(this.$position, this.$media);
        }
    }
}
