package org.videolan.television.viewmodel;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.television.viewmodel.MediaBrowserViewModel;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"getMediaBrowserModel", "Lorg/videolan/television/viewmodel/MediaBrowserViewModel;", "Landroidx/fragment/app/Fragment;", "category", "", "parent", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "television_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserViewModel.kt */
public final class MediaBrowserViewModelKt {
    public static final MediaBrowserViewModel getMediaBrowserModel(Fragment fragment, long j, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Context requireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        return (MediaBrowserViewModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity, (ViewModelProvider.Factory) new MediaBrowserViewModel.Factory(requireContext, j, mediaLibraryItem)).get(MediaBrowserViewModel.class);
    }

    public static /* synthetic */ MediaBrowserViewModel getMediaBrowserModel$default(Fragment fragment, long j, MediaLibraryItem mediaLibraryItem, int i, Object obj) {
        if ((i & 2) != 0) {
            mediaLibraryItem = null;
        }
        return getMediaBrowserModel(fragment, j, mediaLibraryItem);
    }
}
