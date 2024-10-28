package org.videolan.vlc.util;

import android.media.AudioManager;
import android.os.Handler;
import androidx.lifecycle.CoroutineLiveDataKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.media.PlayerController;

@Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"org/videolan/vlc/util/VLCAudioFocusHelper$createOnAudioFocusChangeListener$1", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "delayedPodcastRunnable", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "Lkotlin/Lazy;", "lossTime", "", "lossTransientVolume", "", "wasPlaying", "", "onAudioFocusChange", "", "focusChange", "pausePlayback", "resumePlayback", "schedulePlayback", "delay", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCAudioFocusHelper.kt */
public final class VLCAudioFocusHelper$createOnAudioFocusChangeListener$1 implements AudioManager.OnAudioFocusChangeListener {
    private final Runnable delayedPodcastRunnable;
    private final Lazy handler$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, VLCAudioFocusHelper$createOnAudioFocusChangeListener$1$handler$2.INSTANCE);
    private long lossTime;
    private int lossTransientVolume = -1;
    final /* synthetic */ VLCAudioFocusHelper this$0;
    private boolean wasPlaying;

    VLCAudioFocusHelper$createOnAudioFocusChangeListener$1(VLCAudioFocusHelper vLCAudioFocusHelper) {
        this.this$0 = vLCAudioFocusHelper;
        this.delayedPodcastRunnable = new VLCAudioFocusHelper$createOnAudioFocusChangeListener$1$$ExternalSyntheticLambda0(vLCAudioFocusHelper, this);
    }

    private final Handler getHandler() {
        return (Handler) this.handler$delegate.getValue();
    }

    public void onAudioFocusChange(int i) {
        if (i != -3) {
            if (i == -2) {
                pausePlayback();
            } else if (i == -1) {
                this.this$0.changeAudioFocus$vlc_android_release(false);
                this.this$0.service.pause();
            } else if (i == 1) {
                if (this.lossTransientVolume != -1) {
                    this.this$0.service.setVolume(this.lossTransientVolume);
                    this.lossTransientVolume = -1;
                }
                if (this.this$0.getLossTransient$vlc_android_release() && this.wasPlaying) {
                    if (this.this$0.podcastPlaying) {
                        if (System.currentTimeMillis() - this.lossTime > 1000) {
                            schedulePlayback(2000);
                        } else {
                            resumePlayback();
                        }
                    } else if (this.this$0.service.getSettings$vlc_android_release().getBoolean(SettingsKt.RESUME_PLAYBACK, true)) {
                        resumePlayback();
                    }
                }
            }
        } else if (!this.this$0.service.isPlaying()) {
        } else {
            if (AndroidDevices.INSTANCE.isAmazon() || this.this$0.podcastPlaying) {
                pausePlayback();
            } else if (this.this$0.service.getSettings$vlc_android_release().getBoolean(SettingsKt.AUDIO_DUCKING, true)) {
                int volume = this.this$0.service.getVolume();
                this.lossTransientVolume = volume;
                this.this$0.service.setVolume(volume / 3);
            }
        }
    }

    private final void pausePlayback() {
        if (!this.this$0.getLossTransient$vlc_android_release()) {
            this.this$0.setLossTransient$vlc_android_release(true);
            boolean isPlaying = this.this$0.service.isPlaying();
            this.wasPlaying = isPlaying;
            if (isPlaying) {
                this.this$0.service.pause();
                this.lossTime = System.currentTimeMillis();
            }
        }
    }

    private final void schedulePlayback(long j) {
        getHandler().removeCallbacks(this.delayedPodcastRunnable);
        getHandler().postDelayed(this.delayedPodcastRunnable, j);
    }

    /* access modifiers changed from: private */
    public static final void delayedPodcastRunnable$lambda$0(VLCAudioFocusHelper vLCAudioFocusHelper, VLCAudioFocusHelper$createOnAudioFocusChangeListener$1 vLCAudioFocusHelper$createOnAudioFocusChangeListener$1) {
        Intrinsics.checkNotNullParameter(vLCAudioFocusHelper, "this$0");
        Intrinsics.checkNotNullParameter(vLCAudioFocusHelper$createOnAudioFocusChangeListener$1, "this$1");
        long coerceAtLeast = RangesKt.coerceAtLeast(vLCAudioFocusHelper.service.getTime() - CoroutineLiveDataKt.DEFAULT_TIMEOUT, 0);
        vLCAudioFocusHelper$createOnAudioFocusChangeListener$1.resumePlayback();
        long j = coerceAtLeast;
        PlaybackService.seek$default(vLCAudioFocusHelper.service, j, 0.0d, true, false, 10, (Object) null);
        PlayerController.updateProgress$default(vLCAudioFocusHelper.service.getPlaylistManager().getPlayer(), j, 0, 2, (Object) null);
    }

    private final void resumePlayback() {
        this.this$0.service.play();
        this.this$0.setLossTransient$vlc_android_release(false);
    }
}
