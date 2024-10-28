package org.videolan.vlc.viewmodels.mobile;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.vlc.gui.video.VideoGridFragment;
import org.videolan.vlc.viewmodels.mobile.VideosViewModel;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000¨\u0006\t"}, d2 = {"getViewModel", "Lorg/videolan/vlc/viewmodels/mobile/VideosViewModel;", "Lorg/videolan/vlc/gui/video/VideoGridFragment;", "type", "Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;", "folder", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "group", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideosViewModel.kt */
public final class VideosViewModelKt {
    public static final VideosViewModel getViewModel(VideoGridFragment videoGridFragment, VideoGroupingType videoGroupingType, Folder folder, VideoGroup videoGroup) {
        Intrinsics.checkNotNullParameter(videoGridFragment, "<this>");
        Intrinsics.checkNotNullParameter(videoGroupingType, "type");
        FragmentActivity requireActivity = videoGridFragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Context requireContext = videoGridFragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        return (VideosViewModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity, (ViewModelProvider.Factory) new VideosViewModel.Factory(requireContext, videoGroupingType, folder, videoGroup)).get(VideosViewModel.class);
    }

    public static /* synthetic */ VideosViewModel getViewModel$default(VideoGridFragment videoGridFragment, VideoGroupingType videoGroupingType, Folder folder, VideoGroup videoGroup, int i, Object obj) {
        if ((i & 1) != 0) {
            videoGroupingType = VideoGroupingType.NONE;
        }
        return getViewModel(videoGridFragment, videoGroupingType, folder, videoGroup);
    }
}
