package org.videolan.vlc.gui.helpers;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007B\u0015\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ(\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\b\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/gui/helpers/ItemOffsetDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "resources", "Landroid/content/res/Resources;", "offsetLeftRightId", "", "offsetTopBottomId", "(Landroid/content/res/Resources;II)V", "offsetLeftRight", "offsetTopBottom", "(II)V", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ItemOffsetDecoration.kt */
public final class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private final int offsetLeftRight;
    private final int offsetTopBottom;

    public ItemOffsetDecoration(int i, int i2) {
        this.offsetLeftRight = i;
        this.offsetTopBottom = i2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ItemOffsetDecoration(Resources resources, int i, int i2) {
        this(resources.getDimensionPixelSize(i), resources.getDimensionPixelSize(i2));
        Intrinsics.checkNotNullParameter(resources, "resources");
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(rect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, OAuth2RequestParameters.State);
        int i = this.offsetLeftRight;
        int i2 = this.offsetTopBottom;
        rect.set(i, i2, i, i2);
    }
}
