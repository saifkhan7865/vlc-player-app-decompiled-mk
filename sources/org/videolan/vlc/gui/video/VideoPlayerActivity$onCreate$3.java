package org.videolan.vlc.gui.video;

import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.PlayerOptionsDelegate;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.util.AccessibilityHelperKt;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"org/videolan/vlc/gui/video/VideoPlayerActivity$onCreate$3", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
public final class VideoPlayerActivity$onCreate$3 extends OnBackPressedCallback {
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$onCreate$3(VideoPlayerActivity videoPlayerActivity) {
        super(true);
        this.this$0 = videoPlayerActivity;
    }

    public void handleOnBackPressed() {
        PlaybackService service;
        PlaylistManager playlistManager;
        MutableLiveData<Boolean> videoStatsOn;
        PlaylistManager playlistManager2;
        MutableLiveData<Boolean> videoStatsOn2;
        PlayerOptionsDelegate access$getOptionsDelegate$p = this.this$0.optionsDelegate;
        if (access$getOptionsDelegate$p != null && access$getOptionsDelegate$p.isShowing()) {
            PlayerOptionsDelegate access$getOptionsDelegate$p2 = this.this$0.optionsDelegate;
            if (access$getOptionsDelegate$p2 != null) {
                access$getOptionsDelegate$p2.hide();
            }
        } else if (this.this$0.getResizeDelegate().isShowing()) {
            this.this$0.getResizeDelegate().hideResizeOverlay();
        } else if (this.this$0.getOrientationDelegate().isShowing()) {
            this.this$0.getOrientationDelegate().hideOrientationOverlay();
        } else if (this.this$0.getLockBackButton()) {
            this.this$0.setLockBackButton(false);
            this.this$0.getHandler().sendEmptyMessageDelayed(5, 2000);
            Toast.makeText(this.this$0.getApplicationContext(), this.this$0.getString(R.string.back_quit_lock), 0).show();
        } else if (this.this$0.isPlaylistVisible()) {
            this.this$0.getOverlayDelegate().togglePlaylist();
        } else if (this.this$0.isPlaybackSettingActive$vlc_android_release()) {
            this.this$0.getDelayDelegate().endPlaybackSetting();
        } else if (this.this$0.isShowing() && (service = this.this$0.getService()) != null && (playlistManager = service.getPlaylistManager()) != null && (videoStatsOn = playlistManager.getVideoStatsOn()) != null && Intrinsics.areEqual((Object) videoStatsOn.getValue(), (Object) true)) {
            PlaybackService service2 = this.this$0.getService();
            if (service2 != null && (playlistManager2 = service2.getPlaylistManager()) != null && (videoStatsOn2 = playlistManager2.getVideoStatsOn()) != null) {
                videoStatsOn2.postValue(false);
            }
        } else if (this.this$0.getOverlayDelegate().isBookmarkShown()) {
            this.this$0.getOverlayDelegate().hideBookmarks();
        } else if ((AndroidDevices.INSTANCE.isAndroidTv() || AccessibilityHelperKt.isTalkbackIsEnabled(this.this$0)) && this.this$0.isShowing() && !this.this$0.isLocked()) {
            VideoPlayerOverlayDelegate.hideOverlay$default(this.this$0.getOverlayDelegate(), true, false, 2, (Object) null);
        } else {
            this.this$0.exitOK();
        }
    }
}
