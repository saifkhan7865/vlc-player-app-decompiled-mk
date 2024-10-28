package org.videolan.vlc.gui.video;

import android.content.DialogInterface;
import android.widget.CheckBox;
import org.videolan.vlc.media.WaitConfirmation;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerActivity$$ExternalSyntheticLambda14 implements DialogInterface.OnClickListener {
    public final /* synthetic */ CheckBox f$0;
    public final /* synthetic */ VideoPlayerActivity f$1;
    public final /* synthetic */ WaitConfirmation f$2;

    public /* synthetic */ VideoPlayerActivity$$ExternalSyntheticLambda14(CheckBox checkBox, VideoPlayerActivity videoPlayerActivity, WaitConfirmation waitConfirmation) {
        this.f$0 = checkBox;
        this.f$1 = videoPlayerActivity;
        this.f$2 = waitConfirmation;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        VideoPlayerActivity.showConfirmResumeDialog$lambda$56(this.f$0, this.f$1, this.f$2, dialogInterface, i);
    }
}
