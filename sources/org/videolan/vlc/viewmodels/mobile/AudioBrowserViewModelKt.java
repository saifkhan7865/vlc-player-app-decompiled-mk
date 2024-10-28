package org.videolan.vlc.viewmodels.mobile;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.gui.audio.AudioBrowserFragment;
import org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"getViewModel", "Lorg/videolan/vlc/viewmodels/mobile/AudioBrowserViewModel;", "Lorg/videolan/vlc/gui/audio/AudioBrowserFragment;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioBrowserViewModel.kt */
public final class AudioBrowserViewModelKt {
    public static final AudioBrowserViewModel getViewModel(AudioBrowserFragment audioBrowserFragment) {
        Intrinsics.checkNotNullParameter(audioBrowserFragment, "<this>");
        FragmentActivity requireActivity = audioBrowserFragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        Context requireContext = audioBrowserFragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        return (AudioBrowserViewModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity, (ViewModelProvider.Factory) new AudioBrowserViewModel.Factory(requireContext)).get(AudioBrowserViewModel.class);
    }
}
