package org.videolan.vlc.viewmodels.mobile;

import android.content.Context;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.vlc.gui.PlaylistFragment;
import org.videolan.vlc.viewmodels.mobile.PlaylistsViewModel;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"getViewModel", "Lorg/videolan/vlc/viewmodels/mobile/PlaylistsViewModel;", "Lorg/videolan/vlc/gui/PlaylistFragment;", "type", "Lorg/videolan/medialibrary/interfaces/media/Playlist$Type;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistsViewModel.kt */
public final class PlaylistsViewModelKt {
    public static final PlaylistsViewModel getViewModel(PlaylistFragment playlistFragment, Playlist.Type type) {
        Intrinsics.checkNotNullParameter(playlistFragment, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        Context requireContext = playlistFragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        return (PlaylistsViewModel) new ViewModelProvider((ViewModelStoreOwner) playlistFragment, (ViewModelProvider.Factory) new PlaylistsViewModel.Factory(requireContext, type)).get(PlaylistsViewModel.class);
    }
}
