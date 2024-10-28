package org.videolan.vlc.gui.audio;

import android.content.SharedPreferences;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.AudioPlayerContainerActivity;
import org.videolan.vlc.gui.helpers.PlayerBehavior;
import org.videolan.vlc.gui.view.AudioMediaSwitcher;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\t\u0010\u000b\u001a\u00020\u0003H\u0001J\t\u0010\f\u001a\u00020\u0003H\u0001J\t\u0010\r\u001a\u00020\u0003H\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u0001¨\u0006\u000f"}, d2 = {"org/videolan/vlc/gui/audio/AudioPlayer$coverMediaSwitcherListener$1", "Lorg/videolan/vlc/gui/view/AudioMediaSwitcher$AudioMediaSwitcherListener;", "onChapterSwitching", "", "next", "", "onMediaSwitched", "position", "", "onMediaSwitching", "onTextClicked", "onTouchClick", "onTouchDown", "onTouchLongClick", "onTouchUp", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
public final class AudioPlayer$coverMediaSwitcherListener$1 implements AudioMediaSwitcher.AudioMediaSwitcherListener {
    private final /* synthetic */ AudioMediaSwitcher.EmptySwitcherListener $$delegate_0 = AudioMediaSwitcher.EmptySwitcherListener.INSTANCE;
    final /* synthetic */ AudioPlayer this$0;

    public void onTouchClick() {
        this.$$delegate_0.onTouchClick();
    }

    public void onTouchDown() {
        this.$$delegate_0.onTouchDown();
    }

    public void onTouchLongClick() {
        this.$$delegate_0.onTouchLongClick();
    }

    public void onTouchUp() {
        this.$$delegate_0.onTouchUp();
    }

    AudioPlayer$coverMediaSwitcherListener$1(AudioPlayer audioPlayer) {
        this.this$0 = audioPlayer;
    }

    public void onMediaSwitching() {
        PlayerBehavior<?> playerBehavior;
        FragmentActivity activity = this.this$0.getActivity();
        AudioPlayerContainerActivity audioPlayerContainerActivity = activity instanceof AudioPlayerContainerActivity ? (AudioPlayerContainerActivity) activity : null;
        if (audioPlayerContainerActivity != null && (playerBehavior = audioPlayerContainerActivity.getPlayerBehavior()) != null) {
            playerBehavior.lock(true);
        }
    }

    public void onMediaSwitched(int i) {
        PlayerBehavior<?> playerBehavior;
        if (i == 1) {
            this.this$0.getPlaylistModel().previous(true);
        } else if (i == 3) {
            this.this$0.getPlaylistModel().next();
        }
        FragmentActivity activity = this.this$0.getActivity();
        AudioPlayerContainerActivity audioPlayerContainerActivity = activity instanceof AudioPlayerContainerActivity ? (AudioPlayerContainerActivity) activity : null;
        if (audioPlayerContainerActivity != null && (playerBehavior = audioPlayerContainerActivity.getPlayerBehavior()) != null) {
            playerBehavior.lock(false);
        }
    }

    public void onTextClicked() {
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = this.this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SettingsKt.putSingle((SharedPreferences) settings.getInstance(requireActivity), SettingsKt.KEY_SHOW_TRACK_INFO, Boolean.valueOf(!Settings.INSTANCE.getShowAudioTrackInfo()));
        Settings.INSTANCE.setShowAudioTrackInfo(!Settings.INSTANCE.getShowAudioTrackInfo());
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new AudioPlayer$coverMediaSwitcherListener$1$onTextClicked$1(this.this$0, (Continuation<? super AudioPlayer$coverMediaSwitcherListener$1$onTextClicked$1>) null), 3, (Object) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001b, code lost:
        r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3.getFirst();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onChapterSwitching(boolean r10) {
        /*
            r9 = this;
            org.videolan.vlc.gui.audio.AudioPlayer r0 = r9.this$0
            org.videolan.vlc.viewmodels.PlaylistModel r0 = r0.getPlaylistModel()
            org.videolan.vlc.PlaybackService r0 = r0.getService()
            r1 = -1
            if (r0 == 0) goto L_0x004f
            org.videolan.vlc.gui.audio.AudioPlayer r2 = r9.this$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r0.getCurrentMediaWrapper()
            if (r0 == 0) goto L_0x004f
            kotlin.Pair r3 = r2.currentChapters
            if (r3 == 0) goto L_0x0028
            java.lang.Object r3 = r3.getFirst()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            if (r3 == 0) goto L_0x0028
            android.net.Uri r3 = r3.getUri()
            goto L_0x0029
        L_0x0028:
            r3 = 0
        L_0x0029:
            android.net.Uri r4 = r0.getUri()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r3 != 0) goto L_0x004f
            org.videolan.vlc.viewmodels.PlaylistModel r3 = r2.getPlaylistModel()
            org.videolan.vlc.PlaybackService r3 = r3.getService()
            if (r3 == 0) goto L_0x004f
            org.videolan.libvlc.MediaPlayer$Chapter[] r3 = r3.getChapters(r1)
            if (r3 == 0) goto L_0x004f
            kotlin.Pair r4 = new kotlin.Pair
            java.util.List r3 = kotlin.collections.ArraysKt.toList((T[]) r3)
            r4.<init>(r0, r3)
            r2.currentChapters = r4
        L_0x004f:
            org.videolan.vlc.gui.audio.AudioPlayer r0 = r9.this$0
            kotlin.Pair r0 = r0.currentChapters
            if (r0 == 0) goto L_0x00da
            java.lang.Object r0 = r0.getSecond()
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x00da
            org.videolan.vlc.gui.audio.AudioPlayer r2 = r9.this$0
            org.videolan.vlc.viewmodels.PlaylistModel r3 = r2.getPlaylistModel()
            org.videolan.vlc.PlaybackService r3 = r3.getService()
            if (r3 == 0) goto L_0x00da
            org.videolan.vlc.viewmodels.PlaylistModel r4 = r2.getPlaylistModel()
            org.videolan.vlc.PlaybackService r4 = r4.getService()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            int r4 = r4.getChapterIdx()
            if (r10 != 0) goto L_0x00b8
            int r10 = r3.getChapterIdx()
            java.lang.Object r10 = r0.get(r10)
            org.videolan.libvlc.MediaPlayer$Chapter r10 = (org.videolan.libvlc.MediaPlayer.Chapter) r10
            long r5 = r10.timeOffset
            r10 = 5000(0x1388, float:7.006E-42)
            long r7 = (long) r10
            long r5 = r5 + r7
            long r7 = r3.getTime()
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 <= 0) goto L_0x00a9
            org.videolan.vlc.viewmodels.PlaylistModel r10 = r2.getPlaylistModel()
            org.videolan.vlc.PlaybackService r10 = r10.getService()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            int r4 = r4 + r1
            r0 = 0
            int r0 = kotlin.ranges.RangesKt.coerceAtLeast((int) r4, (int) r0)
            r10.setChapterIdx(r0)
            goto L_0x00da
        L_0x00a9:
            org.videolan.vlc.viewmodels.PlaylistModel r10 = r2.getPlaylistModel()
            org.videolan.vlc.PlaybackService r10 = r10.getService()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            r10.setChapterIdx(r4)
            goto L_0x00da
        L_0x00b8:
            int r10 = r0.size()
            int r10 = r10 + -1
            if (r4 == r10) goto L_0x00da
            org.videolan.vlc.viewmodels.PlaylistModel r10 = r2.getPlaylistModel()
            org.videolan.vlc.PlaybackService r10 = r10.getService()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            int r4 = r4 + 1
            int r0 = r0.size()
            int r0 = r0 + -1
            int r0 = kotlin.ranges.RangesKt.coerceAtMost((int) r4, (int) r0)
            r10.setChapterIdx(r0)
        L_0x00da:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayer$coverMediaSwitcherListener$1.onChapterSwitching(boolean):void");
    }
}
