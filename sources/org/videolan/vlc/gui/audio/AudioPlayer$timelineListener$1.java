package org.videolan.vlc.gui.audio;

import android.widget.SeekBar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.Tools;
import org.videolan.vlc.databinding.AudioPlayerBinding;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"org/videolan/vlc/gui/audio/AudioPlayer$timelineListener$1", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "onProgressChanged", "", "sb", "Landroid/widget/SeekBar;", "progress", "", "fromUser", "", "onStartTrackingTouch", "seekBar", "onStopTrackingTouch", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
public final class AudioPlayer$timelineListener$1 implements SeekBar.OnSeekBarChangeListener {
    final /* synthetic */ AudioPlayer this$0;

    AudioPlayer$timelineListener$1(AudioPlayer audioPlayer) {
        this.this$0 = audioPlayer;
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
        PlaylistModel.setTime$default(this.this$0.getPlaylistModel(), (long) seekBar.getProgress(), false, 2, (Object) null);
        this.this$0.isDragging = false;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
        this.this$0.isDragging = true;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        Intrinsics.checkNotNullParameter(seekBar, "sb");
        if (z) {
            long j = (long) i;
            this.this$0.getPlaylistModel().setTime(j, true);
            AudioPlayerBinding access$getBinding$p = this.this$0.binding;
            AudioPlayerBinding audioPlayerBinding = null;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p = null;
            }
            access$getBinding$p.time.setText(Tools.millisToString(j));
            AudioPlayerBinding access$getBinding$p2 = this.this$0.binding;
            if (access$getBinding$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p2 = null;
            }
            access$getBinding$p2.headerTime.setText(Tools.millisToString(j));
            AudioPlayerBinding access$getBinding$p3 = this.this$0.binding;
            if (access$getBinding$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                audioPlayerBinding = access$getBinding$p3;
            }
            audioPlayerBinding.timeline.forceAccessibilityUpdate();
        }
    }
}
