package org.videolan.vlc.gui.video;

import android.view.MenuItem;
import androidx.appcompat.widget.PopupMenu;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoPlayerActivity$$ExternalSyntheticLambda17 implements PopupMenu.OnMenuItemClickListener {
    public final /* synthetic */ VideoPlayerActivity f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ VideoPlayerActivity$$ExternalSyntheticLambda17(VideoPlayerActivity videoPlayerActivity, int i) {
        this.f$0 = videoPlayerActivity;
        this.f$1 = i;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        return VideoPlayerActivity.onPopupMenu$lambda$41(this.f$0, this.f$1, menuItem);
    }
}
