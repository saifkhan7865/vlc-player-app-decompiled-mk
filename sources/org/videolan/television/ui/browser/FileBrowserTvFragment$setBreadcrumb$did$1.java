package org.videolan.television.ui.browser;

import android.graphics.Rect;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.gui.view.VLCDividerItemDecoration;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"org/videolan/television/ui/browser/FileBrowserTvFragment$setBreadcrumb$did$1", "Lorg/videolan/vlc/gui/view/VLCDividerItemDecoration;", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileBrowserTvFragment.kt */
public final class FileBrowserTvFragment$setBreadcrumb$did$1 extends VLCDividerItemDecoration {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserTvFragment$setBreadcrumb$did$1(FragmentActivity fragmentActivity, VectorDrawableCompat vectorDrawableCompat) {
        super(fragmentActivity, 0, vectorDrawableCompat);
        Intrinsics.checkNotNull(fragmentActivity);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(rect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, OAuth2RequestParameters.State);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (childAdapterPosition == (adapter != null ? adapter.getItemCount() : -1)) {
            rect.setEmpty();
        } else {
            super.getItemOffsets(rect, view, recyclerView, state);
        }
    }
}
