package org.videolan.vlc.gui.browser;

import androidx.recyclerview.widget.GridLayoutManager;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"org/videolan/vlc/gui/browser/BaseBrowserFragment$manageDisplay$1", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "getSpanSize", "", "position", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserFragment.kt */
public final class BaseBrowserFragment$manageDisplay$1 extends GridLayoutManager.SpanSizeLookup {
    final /* synthetic */ BaseBrowserFragment this$0;

    BaseBrowserFragment$manageDisplay$1(BaseBrowserFragment baseBrowserFragment) {
        this.this$0 = baseBrowserFragment;
    }

    public int getSpanSize(int i) {
        this.this$0.getAdapter().getItemCount();
        return 1;
    }
}
