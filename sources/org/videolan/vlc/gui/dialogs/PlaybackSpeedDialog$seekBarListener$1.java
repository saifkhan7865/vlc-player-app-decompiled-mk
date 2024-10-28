package org.videolan.vlc.gui.dialogs;

import android.widget.SeekBar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"org/videolan/vlc/gui/dialogs/PlaybackSpeedDialog$seekBarListener$1", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "onProgressChanged", "", "seekBar", "Landroid/widget/SeekBar;", "progress", "", "fromUser", "", "onStartTrackingTouch", "onStopTrackingTouch", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackSpeedDialog.kt */
public final class PlaybackSpeedDialog$seekBarListener$1 implements SeekBar.OnSeekBarChangeListener {
    final /* synthetic */ PlaybackSpeedDialog this$0;

    public void onStartTrackingTouch(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
    }

    PlaybackSpeedDialog$seekBarListener$1(PlaybackSpeedDialog playbackSpeedDialog) {
        this.this$0 = playbackSpeedDialog;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
        if (this.this$0.playbackService != null) {
            PlaybackService access$getPlaybackService$p = this.this$0.playbackService;
            Intrinsics.checkNotNull(access$getPlaybackService$p);
            if (access$getPlaybackService$p.getCurrentMediaWrapper() != null && z) {
                double d = (double) i;
                Double.isNaN(d);
                double d2 = (double) 1;
                Double.isNaN(d2);
                PlaybackService access$getPlaybackService$p2 = this.this$0.playbackService;
                Intrinsics.checkNotNull(access$getPlaybackService$p2);
                access$getPlaybackService$p2.setRate((float) Math.pow(8.0d, (d / 100.0d) - d2), true);
                this.this$0.updateInterface();
            }
        }
    }
}
