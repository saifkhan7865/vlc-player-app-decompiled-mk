package org.videolan.vlc.gui.audio;

import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "tracks", "Landroidx/paging/PagedList;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioAlbumsSongsFragment.kt */
final class AudioAlbumsSongsFragment$onViewCreated$3 extends Lambda implements Function1<PagedList<MediaWrapper>, Unit> {
    final /* synthetic */ AudioAlbumsSongsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioAlbumsSongsFragment$onViewCreated$3(AudioAlbumsSongsFragment audioAlbumsSongsFragment) {
        super(1);
        this.this$0 = audioAlbumsSongsFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PagedList<MediaWrapper>) (PagedList) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PagedList<MediaWrapper> pagedList) {
        AudioBrowserAdapter audioBrowserAdapter = null;
        if (pagedList == null) {
            pagedList = null;
        }
        if (pagedList != null) {
            AudioBrowserAdapter access$getSongsAdapter$p = this.this$0.songsAdapter;
            if (access$getSongsAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("songsAdapter");
            } else {
                audioBrowserAdapter = access$getSongsAdapter$p;
            }
            audioBrowserAdapter.submitList(pagedList);
        }
    }
}
