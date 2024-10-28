package org.videolan.television.ui.details;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.viewmodels.mobile.PlaylistViewModel;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"getViewModel", "Lorg/videolan/vlc/viewmodels/mobile/PlaylistViewModel;", "Lorg/videolan/television/ui/details/MediaListActivity;", "playlist", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "television_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaListActivity.kt */
public final class MediaListActivityKt {
    public static final PlaylistViewModel getViewModel(MediaListActivity mediaListActivity, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaListActivity, "<this>");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, ArtworkProvider.PLAYLIST);
        return (PlaylistViewModel) new ViewModelProvider((ViewModelStoreOwner) mediaListActivity, (ViewModelProvider.Factory) new PlaylistViewModel.Factory(mediaListActivity, mediaLibraryItem)).get(PlaylistViewModel.class);
    }
}
