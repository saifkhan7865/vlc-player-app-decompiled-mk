package org.videolan.vlc.gui.helpers;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.gui.helpers.hf.PinCodeDelegate;
import org.videolan.vlc.interfaces.SwipeDragHelperAdapter;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001d\u001a\u00020\u0005H\u0016J\b\u0010\u001e\u001a\u00020\u0005H\u0016J \u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001bH\u0016J\u0018\u0010!\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\tH\u0016R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lorg/videolan/vlc/gui/helpers/SwipeDragItemTouchHelperCallback;", "Landroidx/recyclerview/widget/ItemTouchHelper$Callback;", "mAdapter", "Lorg/videolan/vlc/interfaces/SwipeDragHelperAdapter;", "longPressDragEnable", "", "lockedInSafeMode", "(Lorg/videolan/vlc/interfaces/SwipeDragHelperAdapter;ZZ)V", "dragFrom", "", "dragTo", "swipeAttemptListener", "Lkotlin/Function0;", "", "getSwipeAttemptListener", "()Lkotlin/jvm/functions/Function0;", "setSwipeAttemptListener", "(Lkotlin/jvm/functions/Function0;)V", "swipeEnabled", "getSwipeEnabled", "()Z", "setSwipeEnabled", "(Z)V", "clearView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getMovementFlags", "isItemViewSwipeEnabled", "isLongPressDragEnabled", "onMove", "target", "onSwiped", "direction", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SwipeDragItemTouchHelperCallback.kt */
public final class SwipeDragItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private int dragFrom;
    private int dragTo;
    private final boolean lockedInSafeMode;
    private final boolean longPressDragEnable;
    private final SwipeDragHelperAdapter mAdapter;
    private Function0<Unit> swipeAttemptListener;
    private boolean swipeEnabled;

    public SwipeDragItemTouchHelperCallback(SwipeDragHelperAdapter swipeDragHelperAdapter, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(swipeDragHelperAdapter, "mAdapter");
        this.mAdapter = swipeDragHelperAdapter;
        this.longPressDragEnable = z;
        this.lockedInSafeMode = z2;
        this.dragFrom = -1;
        this.dragTo = -1;
        this.swipeEnabled = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SwipeDragItemTouchHelperCallback(SwipeDragHelperAdapter swipeDragHelperAdapter, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(swipeDragHelperAdapter, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2);
    }

    public final boolean getSwipeEnabled() {
        return this.swipeEnabled;
    }

    public final void setSwipeEnabled(boolean z) {
        this.swipeEnabled = z;
    }

    public final Function0<Unit> getSwipeAttemptListener() {
        return this.swipeAttemptListener;
    }

    public final void setSwipeAttemptListener(Function0<Unit> function0) {
        this.swipeAttemptListener = function0;
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        return ItemTouchHelper.Callback.makeMovementFlags(3, 48);
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(viewHolder2, TypedValues.AttributesType.S_TARGET);
        if (!this.lockedInSafeMode || !Intrinsics.areEqual((Object) PinCodeDelegate.Companion.getPinUnlocked().getValue(), (Object) false)) {
            this.mAdapter.onItemMove(viewHolder.getLayoutPosition(), viewHolder2.getLayoutPosition());
            int layoutPosition = viewHolder.getLayoutPosition();
            int layoutPosition2 = viewHolder2.getLayoutPosition();
            if (this.dragFrom == -1) {
                this.dragFrom = layoutPosition;
            }
            this.dragTo = layoutPosition2;
            return true;
        }
        Function0<Unit> function0 = this.swipeAttemptListener;
        if (function0 != null) {
            function0.invoke();
        }
        return false;
    }

    public boolean isLongPressDragEnabled() {
        return this.longPressDragEnable;
    }

    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int i;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        int i2 = this.dragFrom;
        if (!(i2 == -1 || (i = this.dragTo) == -1 || i2 == i)) {
            this.mAdapter.onItemMoved(i2, i);
        }
        this.dragTo = -1;
        this.dragFrom = -1;
        super.clearView(recyclerView, viewHolder);
    }

    public boolean isItemViewSwipeEnabled() {
        return this.swipeEnabled;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        this.mAdapter.onItemDismiss(viewHolder.getLayoutPosition());
    }
}
