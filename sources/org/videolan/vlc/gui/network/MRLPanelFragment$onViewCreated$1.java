package org.videolan.vlc.gui.network;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"org/videolan/vlc/gui/network/MRLPanelFragment$onViewCreated$1", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MRLPanelFragment.kt */
public final class MRLPanelFragment$onViewCreated$1 extends RecyclerView.ItemDecoration {
    final /* synthetic */ MRLPanelFragment this$0;

    MRLPanelFragment$onViewCreated$1(MRLPanelFragment mRLPanelFragment) {
        this.this$0 = mRLPanelFragment;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(rect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, OAuth2RequestParameters.State);
        rect.left = (int) this.this$0.getResources().getDimension(R.dimen.kl_half);
        rect.right = (int) this.this$0.getResources().getDimension(R.dimen.kl_half);
        super.getItemOffsets(rect, view, recyclerView, state);
    }
}
