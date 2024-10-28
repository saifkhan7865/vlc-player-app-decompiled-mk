package org.videolan.television.ui.browser;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.ui.TvItemAdapter;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"org/videolan/television/ui/browser/BaseBrowserTvFragment$setupLayoutManager$2", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "getSpanSize", "", "position", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserTvFragment.kt */
public final class BaseBrowserTvFragment$setupLayoutManager$2 extends GridLayoutManager.SpanSizeLookup {
    final /* synthetic */ BaseBrowserTvFragment<T> this$0;

    BaseBrowserTvFragment$setupLayoutManager$2(BaseBrowserTvFragment<T> baseBrowserTvFragment) {
        this.this$0 = baseBrowserTvFragment;
    }

    public int getSpanSize(int i) {
        TvItemAdapter adapter = this.this$0.getAdapter();
        Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.Adapter<*>");
        if (i == ((RecyclerView.Adapter) adapter).getItemCount() - 1 || !this.this$0.getViewModel().getProvider().isFirstInSection(i + 1) || !Settings.INSTANCE.getShowHeaders()) {
            return 1;
        }
        return this.this$0.getViewModel().getNbColumns() - ((i - this.this$0.getViewModel().getProvider().getPositionForSection(i)) % this.this$0.getViewModel().getNbColumns());
    }
}
