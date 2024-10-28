package org.videolan.vlc.gui.audio;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.gui.view.SwipeRefreshLayout;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"org/videolan/vlc/gui/audio/BaseAudioBrowser$scrollListener$1", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrollStateChanged", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "newState", "", "onScrolled", "dx", "dy", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseAudioBrowser.kt */
public final class BaseAudioBrowser$scrollListener$1 extends RecyclerView.OnScrollListener {
    final /* synthetic */ BaseAudioBrowser<T> this$0;

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
    }

    BaseAudioBrowser$scrollListener$1(BaseAudioBrowser<T> baseAudioBrowser) {
        this.this$0 = baseAudioBrowser;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        boolean z = false;
        if (i != 0) {
            this.this$0.getSwipeRefreshLayout().setEnabled(false);
            return;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.this$0.getCurrentRV().getLayoutManager();
        if (linearLayoutManager != null) {
            SwipeRefreshLayout swipeRefreshLayout = this.this$0.getSwipeRefreshLayout();
            if (linearLayoutManager.findFirstVisibleItemPosition() <= 0) {
                z = true;
            }
            swipeRefreshLayout.setEnabled(z);
        }
    }
}
