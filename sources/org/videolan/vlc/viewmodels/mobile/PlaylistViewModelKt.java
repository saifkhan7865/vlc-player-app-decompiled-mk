package org.videolan.vlc.viewmodels.mobile;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.gui.HeaderMediaListActivity;
import org.videolan.vlc.viewmodels.mobile.PlaylistViewModel;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"getViewModel", "Lorg/videolan/vlc/viewmodels/mobile/PlaylistViewModel;", "Lorg/videolan/vlc/gui/HeaderMediaListActivity;", "playlist", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistViewModel.kt */
public final class PlaylistViewModelKt {
    public static final PlaylistViewModel getViewModel(HeaderMediaListActivity headerMediaListActivity, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(headerMediaListActivity, "<this>");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, ArtworkProvider.PLAYLIST);
        return (PlaylistViewModel) new ViewModelProvider((ViewModelStoreOwner) headerMediaListActivity, (ViewModelProvider.Factory) new PlaylistViewModel.Factory(headerMediaListActivity, mediaLibraryItem)).get(PlaylistViewModel.class);
    }
}
