package org.videolan.television.ui.browser;

import android.graphics.Rect;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.ui.MediaTvItemAdapter;

@Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0018\u00010\u0005R\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nH\u0016J0\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\nH\u0016¨\u0006\u0012"}, d2 = {"org/videolan/television/ui/browser/BaseBrowserTvFragment$setupLayoutManager$1", "Landroidx/recyclerview/widget/GridLayoutManager;", "onLayoutChildren", "", "recycler", "Landroidx/recyclerview/widget/RecyclerView$Recycler;", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "requestChildRectangleOnScreen", "", "parent", "child", "Landroid/view/View;", "rect", "Landroid/graphics/Rect;", "immediate", "focusedChildVisible", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserTvFragment.kt */
public final class BaseBrowserTvFragment$setupLayoutManager$1 extends GridLayoutManager {
    final /* synthetic */ BaseBrowserTvFragment<T> this$0;

    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z) {
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(view, "child");
        Intrinsics.checkNotNullParameter(rect, "rect");
        return false;
    }

    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(view, "child");
        Intrinsics.checkNotNullParameter(rect, "rect");
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserTvFragment$setupLayoutManager$1(BaseBrowserTvFragment<T> baseBrowserTvFragment, FragmentActivity fragmentActivity, int i) {
        super(fragmentActivity, i);
        this.this$0 = baseBrowserTvFragment;
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (this.this$0.previouslySelectedItem != -1) {
            RecyclerView.Adapter adapter = this.this$0.getBinding().list.getAdapter();
            if ((adapter != null ? adapter.getItemCount() : 0) > this.this$0.previouslySelectedItem) {
                scrollToPosition(this.this$0.previouslySelectedItem);
                RecyclerView.ViewHolder findViewHolderForLayoutPosition = this.this$0.getBinding().list.findViewHolderForLayoutPosition(this.this$0.previouslySelectedItem);
                if (findViewHolderForLayoutPosition != null) {
                    BaseBrowserTvFragment<T> baseBrowserTvFragment = this.this$0;
                    ((MediaTvItemAdapter.AbstractMediaItemViewHolder) findViewHolderForLayoutPosition).getView().requestFocus();
                    baseBrowserTvFragment.previouslySelectedItem = -1;
                }
            }
        }
    }
}
