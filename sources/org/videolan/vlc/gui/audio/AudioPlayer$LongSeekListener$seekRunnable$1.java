package org.videolan.vlc.gui.audio;

import android.os.Vibrator;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.Tools;
import org.videolan.resources.AppContextProvider;
import org.videolan.vlc.databinding.AudioPlayerBinding;
import org.videolan.vlc.gui.audio.AudioPlayer;

@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001j\u0002`\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"org/videolan/vlc/gui/audio/AudioPlayer$LongSeekListener$seekRunnable$1", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "run", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
public final class AudioPlayer$LongSeekListener$seekRunnable$1 implements Runnable {
    final /* synthetic */ AudioPlayer.LongSeekListener this$0;
    final /* synthetic */ AudioPlayer this$1;

    AudioPlayer$LongSeekListener$seekRunnable$1(AudioPlayer.LongSeekListener longSeekListener, AudioPlayer audioPlayer) {
        this.this$0 = longSeekListener;
        this.this$1 = audioPlayer;
    }

    public void run() {
        if (!this.this$0.getVibrated()) {
            Vibrator vibrator = (Vibrator) ContextCompat.getSystemService(AppContextProvider.INSTANCE.getAppContext(), Vibrator.class);
            if (vibrator != null) {
                vibrator.vibrate(80);
            }
            this.this$0.setVibrated(true);
        }
        if (this.this$0.getForward()) {
            if (this.this$0.getLength() <= 0 || ((long) this.this$0.getPossibleSeek()) < this.this$0.getLength()) {
                AudioPlayer.LongSeekListener longSeekListener = this.this$0;
                longSeekListener.setPossibleSeek(longSeekListener.getPossibleSeek() + 4000);
            }
        } else if (this.this$0.getPossibleSeek() > 4000) {
            AudioPlayer.LongSeekListener longSeekListener2 = this.this$0;
            longSeekListener2.setPossibleSeek(longSeekListener2.getPossibleSeek() - 4000);
        } else if (this.this$0.getPossibleSeek() <= 4000) {
            this.this$0.setPossibleSeek(0);
        }
        AudioPlayerBinding access$getBinding$p = this.this$1.binding;
        AudioPlayerBinding audioPlayerBinding = null;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        access$getBinding$p.time.setText(Tools.millisToString((long) this.this$0.getPossibleSeek()));
        AudioPlayerBinding access$getBinding$p2 = this.this$1.binding;
        if (access$getBinding$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p2 = null;
        }
        access$getBinding$p2.timeline.setProgress(this.this$0.getPossibleSeek());
        AudioPlayerBinding access$getBinding$p3 = this.this$1.binding;
        if (access$getBinding$p3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            audioPlayerBinding = access$getBinding$p3;
        }
        audioPlayerBinding.progressBar.setProgress(this.this$0.getPossibleSeek());
        this.this$1.getHandler().postDelayed(this, 50);
    }
}
