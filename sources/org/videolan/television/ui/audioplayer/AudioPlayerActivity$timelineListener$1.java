package org.videolan.television.ui.audioplayer;

import android.widget.SeekBar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"org/videolan/television/ui/audioplayer/AudioPlayerActivity$timelineListener$1", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "onProgressChanged", "", "sb", "Landroid/widget/SeekBar;", "progress", "", "fromUser", "", "onStartTrackingTouch", "seekBar", "onStopTrackingTouch", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerActivity.kt */
public final class AudioPlayerActivity$timelineListener$1 implements SeekBar.OnSeekBarChangeListener {
    final /* synthetic */ AudioPlayerActivity this$0;

    public void onStartTrackingTouch(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "seekBar");
    }

    AudioPlayerActivity$timelineListener$1(AudioPlayerActivity audioPlayerActivity) {
        this.this$0 = audioPlayerActivity;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        Intrinsics.checkNotNullParameter(seekBar, "sb");
        if (z) {
            PlaylistModel access$getModel$p = this.this$0.model;
            if (access$getModel$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                access$getModel$p = null;
            }
            PlaylistModel.setTime$default(access$getModel$p, (long) i, false, 2, (Object) null);
        }
    }
}
