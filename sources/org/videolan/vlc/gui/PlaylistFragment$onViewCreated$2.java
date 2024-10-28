package org.videolan.vlc.gui;

import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.vlc.gui.audio.AudioBrowserAdapter;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Landroidx/paging/PagedList;", "Lorg/videolan/medialibrary/interfaces/media/Playlist;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistFragment.kt */
final class PlaylistFragment$onViewCreated$2 extends Lambda implements Function1<PagedList<Playlist>, Unit> {
    final /* synthetic */ PlaylistFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistFragment$onViewCreated$2(PlaylistFragment playlistFragment) {
        super(1);
        this.this$0 = playlistFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PagedList<Playlist>) (PagedList) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PagedList<Playlist> pagedList) {
        AudioBrowserAdapter access$getPlaylistAdapter$p = this.this$0.playlistAdapter;
        if (access$getPlaylistAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
            access$getPlaylistAdapter$p = null;
        }
        Intrinsics.checkNotNull(pagedList, "null cannot be cast to non-null type androidx.paging.PagedList<org.videolan.medialibrary.media.MediaLibraryItem>");
        access$getPlaylistAdapter$p.submitList(pagedList);
        this.this$0.updateEmptyView();
    }
}
