package org.videolan.vlc.gui.audio;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.AudioPlayerContainerActivity;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.AudioMediaSwitcher;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016¨\u0006\u000f"}, d2 = {"org/videolan/vlc/gui/audio/AudioPlayer$headerMediaSwitcherListener$1", "Lorg/videolan/vlc/gui/view/AudioMediaSwitcher$AudioMediaSwitcherListener;", "onChapterSwitching", "", "next", "", "onMediaSwitched", "position", "", "onMediaSwitching", "onTextClicked", "onTouchClick", "onTouchDown", "onTouchLongClick", "onTouchUp", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
public final class AudioPlayer$headerMediaSwitcherListener$1 implements AudioMediaSwitcher.AudioMediaSwitcherListener {
    final /* synthetic */ AudioPlayer this$0;

    public void onChapterSwitching(boolean z) {
    }

    public void onMediaSwitching() {
    }

    public void onTextClicked() {
    }

    public void onTouchDown() {
    }

    public void onTouchUp() {
    }

    AudioPlayer$headerMediaSwitcherListener$1(AudioPlayer audioPlayer) {
        this.this$0 = audioPlayer;
    }

    public void onMediaSwitched(int i) {
        if (i == 1) {
            this.this$0.getPlaylistModel().previous(true);
        } else if (i == 3) {
            this.this$0.getPlaylistModel().next();
        }
    }

    public void onTouchClick() {
        FragmentActivity activity = this.this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type org.videolan.vlc.gui.AudioPlayerContainerActivity");
        ((AudioPlayerContainerActivity) activity).slideUpOrDownAudioPlayer();
    }

    public void onTouchLongClick() {
        String title = this.this$0.getPlaylistModel().getTitle();
        if (title != null) {
            if (this.this$0.getPlaylistModel().getVideoTrackCount() > 0) {
                this.this$0.onResumeToVideoClick();
                return;
            }
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            KotlinExtensionsKt.copy(requireActivity, "VLC - song name", title);
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity2 = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
            UiTools.snacker$default(uiTools, requireActivity2, R.string.track_info_copied_to_clipboard, false, 4, (Object) null);
        }
    }
}
