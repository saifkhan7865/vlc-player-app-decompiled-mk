package org.videolan.vlc.gui.video;

import android.content.DialogInterface;
import android.view.KeyEvent;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerActivity$$ExternalSyntheticLambda16 implements DialogInterface.OnKeyListener {
    public final /* synthetic */ VideoPlayerActivity f$0;

    public /* synthetic */ VideoPlayerActivity$$ExternalSyntheticLambda16(VideoPlayerActivity videoPlayerActivity) {
        this.f$0 = videoPlayerActivity;
    }

    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return VideoPlayerActivity.showConfirmResumeDialog$lambda$59$lambda$58(this.f$0, dialogInterface, i, keyEvent);
    }
}
