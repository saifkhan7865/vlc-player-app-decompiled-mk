package org.videolan.vlc.viewmodels.mobile;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.audio.AudioAlbumsSongsFragment;
import org.videolan.vlc.viewmodels.mobile.AlbumSongsViewModel;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"getViewModel", "Lorg/videolan/vlc/viewmodels/mobile/AlbumSongsViewModel;", "Lorg/videolan/vlc/gui/audio/AudioAlbumsSongsFragment;", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: AlbumSongsViewModel.kt */
public final class AlbumSongsViewModelKt {
    public static final AlbumSongsViewModel getViewModel(AudioAlbumsSongsFragment audioAlbumsSongsFragment, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(audioAlbumsSongsFragment, "<this>");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        FragmentActivity requireActivity = audioAlbumsSongsFragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Context requireContext = audioAlbumsSongsFragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        return (AlbumSongsViewModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity, (ViewModelProvider.Factory) new AlbumSongsViewModel.Factory(requireContext, mediaLibraryItem)).get(AlbumSongsViewModel.class);
    }
}
