package org.videolan.television.viewmodel;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.viewmodel.MediaScrapingBrowserViewModel;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"getMoviepediaBrowserModel", "Lorg/videolan/television/viewmodel/MediaScrapingBrowserViewModel;", "Landroidx/fragment/app/Fragment;", "category", "", "television_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingBrowserViewModel.kt */
public final class MediaScrapingBrowserViewModelKt {
    public static final MediaScrapingBrowserViewModel getMoviepediaBrowserModel(Fragment fragment, long j) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Context requireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        return (MediaScrapingBrowserViewModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity, (ViewModelProvider.Factory) new MediaScrapingBrowserViewModel.Factory(requireContext, j)).get(MediaScrapingBrowserViewModel.class);
    }
}
