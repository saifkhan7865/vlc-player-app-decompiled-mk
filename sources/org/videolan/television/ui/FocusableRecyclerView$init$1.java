package org.videolan.television.ui;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.interfaces.FocusListener;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/television/ui/FocusableRecyclerView$init$1", "Lorg/videolan/resources/interfaces/FocusListener;", "onFocusChanged", "", "position", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusableRecyclerView.kt */
public final class FocusableRecyclerView$init$1 implements FocusListener {
    final /* synthetic */ FocusableRecyclerView this$0;

    FocusableRecyclerView$init$1(FocusableRecyclerView focusableRecyclerView) {
        this.this$0 = focusableRecyclerView;
    }

    public void onFocusChanged(int i) {
        int[] iArr = new int[2];
        RecyclerView.LayoutManager layoutManager = this.this$0.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager);
        View findViewByPosition = layoutManager.findViewByPosition(i);
        if (findViewByPosition != null) {
            FocusableRecyclerView focusableRecyclerView = this.this$0;
            findViewByPosition.getLocationInWindow(iArr);
            focusableRecyclerView.smoothScrollBy(0, (iArr[1] - (focusableRecyclerView.screenHeight / 2)) + (findViewByPosition.getHeight() / 2));
        }
    }
}
