package androidx.leanback.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.collection.CircularIntArray;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.leanback.widget.Grid;
import androidx.leanback.widget.ItemAlignmentFacet;
import androidx.leanback.widget.WindowAlignment;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class GridLayoutManager extends RecyclerView.LayoutManager {
    static final boolean DEBUG = false;
    static final int DEFAULT_MAX_PENDING_MOVES = 10;
    static final int MIN_MS_SMOOTH_SCROLL_MAIN_SCREEN = 30;
    private static final int NEXT_ITEM = 1;
    private static final int NEXT_ROW = 3;
    static final int PF_FAST_RELAYOUT = 4;
    static final int PF_FAST_RELAYOUT_UPDATED_SELECTED_POSITION = 8;
    static final int PF_FOCUS_OUT_END = 4096;
    static final int PF_FOCUS_OUT_FRONT = 2048;
    static final int PF_FOCUS_OUT_MASKS = 6144;
    static final int PF_FOCUS_OUT_SIDE_END = 16384;
    static final int PF_FOCUS_OUT_SIDE_MASKS = 24576;
    static final int PF_FOCUS_OUT_SIDE_START = 8192;
    static final int PF_FOCUS_SEARCH_DISABLED = 32768;
    static final int PF_FORCE_FULL_LAYOUT = 256;
    static final int PF_IN_LAYOUT_SEARCH_FOCUS = 16;
    static final int PF_IN_SELECTION = 32;
    static final int PF_LAYOUT_EATEN_IN_SLIDING = 128;
    static final int PF_LAYOUT_ENABLED = 512;
    static final int PF_PRUNE_CHILD = 65536;
    static final int PF_REVERSE_FLOW_MASK = 786432;
    static final int PF_REVERSE_FLOW_PRIMARY = 262144;
    static final int PF_REVERSE_FLOW_SECONDARY = 524288;
    static final int PF_ROW_SECONDARY_SIZE_REFRESH = 1024;
    static final int PF_SCROLL_ENABLED = 131072;
    static final int PF_SLIDING = 64;
    static final int PF_STAGE_LAYOUT = 1;
    static final int PF_STAGE_MASK = 3;
    static final int PF_STAGE_SCROLL = 2;
    private static final int PREV_ITEM = 0;
    private static final int PREV_ROW = 2;
    private static final String TAG = "GridLayoutManager";
    static final boolean TRACE = false;
    private static final Rect sTempRect = new Rect();
    static int[] sTwoInts = new int[2];
    final BaseGridView mBaseGridView;
    OnChildLaidOutListener mChildLaidOutListener = null;
    private OnChildSelectedListener mChildSelectedListener = null;
    private ArrayList<OnChildViewHolderSelectedListener> mChildViewHolderSelectedListeners = null;
    int mChildVisibility;
    final ViewsStateBundle mChildrenStates = new ViewsStateBundle();
    GridLinearSmoothScroller mCurrentSmoothScroller;
    int[] mDisappearingPositions;
    private int mExtraLayoutSpace;
    int mExtraLayoutSpaceInPreLayout;
    private FacetProviderAdapter mFacetProviderAdapter;
    private int mFixedRowSizeSecondary;
    int mFlag = 221696;
    int mFocusPosition = -1;
    private int mFocusPositionOffset = 0;
    private int mFocusScrollStrategy = 0;
    private int mGravity = 8388659;
    Grid mGrid;
    private Grid.Provider mGridProvider = new Grid.Provider() {
        public int getMinIndex() {
            return GridLayoutManager.this.mPositionDeltaInPreLayout;
        }

        public int getCount() {
            return GridLayoutManager.this.mState.getItemCount() + GridLayoutManager.this.mPositionDeltaInPreLayout;
        }

        /* JADX WARNING: type inference failed for: r8v0, types: [java.lang.Object[]] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int createItem(int r6, boolean r7, java.lang.Object[] r8, boolean r9) {
            /*
                r5 = this;
                androidx.leanback.widget.GridLayoutManager r0 = androidx.leanback.widget.GridLayoutManager.this
                int r1 = r0.mPositionDeltaInPreLayout
                int r1 = r6 - r1
                android.view.View r0 = r0.getViewForPosition(r1)
                android.view.ViewGroup$LayoutParams r1 = r0.getLayoutParams()
                androidx.leanback.widget.GridLayoutManager$LayoutParams r1 = (androidx.leanback.widget.GridLayoutManager.LayoutParams) r1
                androidx.leanback.widget.GridLayoutManager r2 = androidx.leanback.widget.GridLayoutManager.this
                androidx.leanback.widget.BaseGridView r2 = r2.mBaseGridView
                androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r2.getChildViewHolder(r0)
                androidx.leanback.widget.GridLayoutManager r3 = androidx.leanback.widget.GridLayoutManager.this
                java.lang.Class<androidx.leanback.widget.ItemAlignmentFacet> r4 = androidx.leanback.widget.ItemAlignmentFacet.class
                java.lang.Object r2 = r3.getFacet(r2, r4)
                androidx.leanback.widget.ItemAlignmentFacet r2 = (androidx.leanback.widget.ItemAlignmentFacet) r2
                r1.setItemAlignmentFacet(r2)
                boolean r1 = r1.isItemRemoved()
                r2 = 0
                if (r1 != 0) goto L_0x00df
                if (r9 == 0) goto L_0x003c
                if (r7 == 0) goto L_0x0036
                androidx.leanback.widget.GridLayoutManager r7 = androidx.leanback.widget.GridLayoutManager.this
                r7.addDisappearingView(r0)
                goto L_0x0049
            L_0x0036:
                androidx.leanback.widget.GridLayoutManager r7 = androidx.leanback.widget.GridLayoutManager.this
                r7.addDisappearingView(r0, r2)
                goto L_0x0049
            L_0x003c:
                if (r7 == 0) goto L_0x0044
                androidx.leanback.widget.GridLayoutManager r7 = androidx.leanback.widget.GridLayoutManager.this
                r7.addView(r0)
                goto L_0x0049
            L_0x0044:
                androidx.leanback.widget.GridLayoutManager r7 = androidx.leanback.widget.GridLayoutManager.this
                r7.addView(r0, r2)
            L_0x0049:
                androidx.leanback.widget.GridLayoutManager r7 = androidx.leanback.widget.GridLayoutManager.this
                int r7 = r7.mChildVisibility
                r9 = -1
                if (r7 == r9) goto L_0x0057
                androidx.leanback.widget.GridLayoutManager r7 = androidx.leanback.widget.GridLayoutManager.this
                int r7 = r7.mChildVisibility
                r0.setVisibility(r7)
            L_0x0057:
                androidx.leanback.widget.GridLayoutManager r7 = androidx.leanback.widget.GridLayoutManager.this
                androidx.leanback.widget.GridLayoutManager$PendingMoveSmoothScroller r7 = r7.mPendingMoveSmoothScroller
                if (r7 == 0) goto L_0x0064
                androidx.leanback.widget.GridLayoutManager r7 = androidx.leanback.widget.GridLayoutManager.this
                androidx.leanback.widget.GridLayoutManager$PendingMoveSmoothScroller r7 = r7.mPendingMoveSmoothScroller
                r7.consumePendingMovesBeforeLayout()
            L_0x0064:
                androidx.leanback.widget.GridLayoutManager r7 = androidx.leanback.widget.GridLayoutManager.this
                android.view.View r9 = r0.findFocus()
                int r7 = r7.getSubPositionByView(r0, r9)
                androidx.leanback.widget.GridLayoutManager r9 = androidx.leanback.widget.GridLayoutManager.this
                int r9 = r9.mFlag
                r9 = r9 & 3
                r1 = 1
                if (r9 == r1) goto L_0x008f
                androidx.leanback.widget.GridLayoutManager r9 = androidx.leanback.widget.GridLayoutManager.this
                int r9 = r9.mFocusPosition
                if (r6 != r9) goto L_0x00da
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                int r6 = r6.mSubFocusPosition
                if (r7 != r6) goto L_0x00da
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                androidx.leanback.widget.GridLayoutManager$PendingMoveSmoothScroller r6 = r6.mPendingMoveSmoothScroller
                if (r6 != 0) goto L_0x00da
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                r6.dispatchChildSelected()
                goto L_0x00da
            L_0x008f:
                androidx.leanback.widget.GridLayoutManager r9 = androidx.leanback.widget.GridLayoutManager.this
                int r9 = r9.mFlag
                r9 = r9 & 4
                if (r9 != 0) goto L_0x00da
                androidx.leanback.widget.GridLayoutManager r9 = androidx.leanback.widget.GridLayoutManager.this
                int r9 = r9.mFlag
                r9 = r9 & 16
                if (r9 != 0) goto L_0x00b1
                androidx.leanback.widget.GridLayoutManager r9 = androidx.leanback.widget.GridLayoutManager.this
                int r9 = r9.mFocusPosition
                if (r6 != r9) goto L_0x00b1
                androidx.leanback.widget.GridLayoutManager r9 = androidx.leanback.widget.GridLayoutManager.this
                int r9 = r9.mSubFocusPosition
                if (r7 != r9) goto L_0x00b1
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                r6.dispatchChildSelected()
                goto L_0x00da
            L_0x00b1:
                androidx.leanback.widget.GridLayoutManager r9 = androidx.leanback.widget.GridLayoutManager.this
                int r9 = r9.mFlag
                r9 = r9 & 16
                if (r9 == 0) goto L_0x00da
                androidx.leanback.widget.GridLayoutManager r9 = androidx.leanback.widget.GridLayoutManager.this
                int r9 = r9.mFocusPosition
                if (r6 < r9) goto L_0x00da
                boolean r9 = r0.hasFocusable()
                if (r9 == 0) goto L_0x00da
                androidx.leanback.widget.GridLayoutManager r9 = androidx.leanback.widget.GridLayoutManager.this
                r9.mFocusPosition = r6
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                r6.mSubFocusPosition = r7
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                int r7 = r6.mFlag
                r7 = r7 & -17
                r6.mFlag = r7
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                r6.dispatchChildSelected()
            L_0x00da:
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                r6.measureChild(r0)
            L_0x00df:
                r8[r2] = r0
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                int r6 = r6.mOrientation
                if (r6 != 0) goto L_0x00ee
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                int r6 = r6.getDecoratedMeasuredWidthWithMargin(r0)
                goto L_0x00f4
            L_0x00ee:
                androidx.leanback.widget.GridLayoutManager r6 = androidx.leanback.widget.GridLayoutManager.this
                int r6 = r6.getDecoratedMeasuredHeightWithMargin(r0)
            L_0x00f4:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.AnonymousClass2.createItem(int, boolean, java.lang.Object[], boolean):int");
        }

        public void addItem(Object obj, int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            long j;
            View view = (View) obj;
            if (i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE) {
                if (!GridLayoutManager.this.mGrid.isReversedFlow()) {
                    i4 = GridLayoutManager.this.mWindowAlignment.mainAxis().getPaddingMin();
                } else {
                    i4 = GridLayoutManager.this.mWindowAlignment.mainAxis().getSize() - GridLayoutManager.this.mWindowAlignment.mainAxis().getPaddingMax();
                }
            }
            if (!GridLayoutManager.this.mGrid.isReversedFlow()) {
                i5 = i2 + i4;
                i6 = i4;
            } else {
                i6 = i4 - i2;
                i5 = i4;
            }
            int rowStartSecondary = (GridLayoutManager.this.getRowStartSecondary(i3) + GridLayoutManager.this.mWindowAlignment.secondAxis().getPaddingMin()) - GridLayoutManager.this.mScrollOffsetSecondary;
            GridLayoutManager.this.mChildrenStates.loadView(view, i);
            GridLayoutManager.this.layoutChild(i3, view, i6, i5, rowStartSecondary);
            if (!GridLayoutManager.this.mState.isPreLayout()) {
                GridLayoutManager.this.updateScrollLimits();
            }
            if (!((GridLayoutManager.this.mFlag & 3) == 1 || GridLayoutManager.this.mPendingMoveSmoothScroller == null)) {
                GridLayoutManager.this.mPendingMoveSmoothScroller.consumePendingMovesAfterLayout();
            }
            if (GridLayoutManager.this.mChildLaidOutListener != null) {
                RecyclerView.ViewHolder childViewHolder = GridLayoutManager.this.mBaseGridView.getChildViewHolder(view);
                OnChildLaidOutListener onChildLaidOutListener = GridLayoutManager.this.mChildLaidOutListener;
                BaseGridView baseGridView = GridLayoutManager.this.mBaseGridView;
                if (childViewHolder == null) {
                    j = -1;
                } else {
                    j = childViewHolder.getItemId();
                }
                onChildLaidOutListener.onChildLaidOut(baseGridView, view, i, j);
            }
        }

        public void removeItem(int i) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            View findViewByPosition = gridLayoutManager.findViewByPosition(i - gridLayoutManager.mPositionDeltaInPreLayout);
            if ((GridLayoutManager.this.mFlag & 3) == 1) {
                GridLayoutManager gridLayoutManager2 = GridLayoutManager.this;
                gridLayoutManager2.detachAndScrapView(findViewByPosition, gridLayoutManager2.mRecycler);
                return;
            }
            GridLayoutManager gridLayoutManager3 = GridLayoutManager.this;
            gridLayoutManager3.removeAndRecycleView(findViewByPosition, gridLayoutManager3.mRecycler);
        }

        public int getEdge(int i) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            View findViewByPosition = gridLayoutManager.findViewByPosition(i - gridLayoutManager.mPositionDeltaInPreLayout);
            return (GridLayoutManager.this.mFlag & 262144) != 0 ? GridLayoutManager.this.getViewMax(findViewByPosition) : GridLayoutManager.this.getViewMin(findViewByPosition);
        }

        public int getSize(int i) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            return gridLayoutManager.getViewPrimarySize(gridLayoutManager.findViewByPosition(i - gridLayoutManager.mPositionDeltaInPreLayout));
        }
    };
    private int mHorizontalSpacing;
    private final ItemAlignment mItemAlignment = new ItemAlignment();
    OnLayoutCompleteListener mLayoutCompleteListener;
    int mMaxPendingMoves = 10;
    private int mMaxSizeSecondary;
    private int[] mMeasuredDimension = new int[2];
    int mNumRows;
    private int mNumRowsRequested = 1;
    int mOrientation = 0;
    private OrientationHelper mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
    PendingMoveSmoothScroller mPendingMoveSmoothScroller;
    int mPositionDeltaInPreLayout;
    final SparseIntArray mPositionToRowInPostLayout = new SparseIntArray();
    private int mPrimaryScrollExtra;
    RecyclerView.Recycler mRecycler;
    private final Runnable mRequestLayoutRunnable = new Runnable() {
        public void run() {
            GridLayoutManager.this.requestLayout();
        }
    };
    private int[] mRowSizeSecondary;
    private int mRowSizeSecondaryRequested;
    int mScrollOffsetSecondary;
    private int mSizePrimary;
    private int mSpacingPrimary;
    private int mSpacingSecondary;
    RecyclerView.State mState;
    int mSubFocusPosition = 0;
    private int mVerticalSpacing;
    final WindowAlignment mWindowAlignment = new WindowAlignment();

    public static class OnLayoutCompleteListener {
        public void onLayoutCompleted(RecyclerView.State state) {
        }
    }

    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z) {
        return false;
    }

    public boolean supportsPredictiveItemAnimations() {
        return true;
    }

    static final class LayoutParams extends RecyclerView.LayoutParams {
        private int[] mAlignMultiple;
        private int mAlignX;
        private int mAlignY;
        private ItemAlignmentFacet mAlignmentFacet;
        int mBottomInset;
        int mLeftInset;
        int mRightInset;
        int mTopInset;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((RecyclerView.LayoutParams) layoutParams);
        }

        /* access modifiers changed from: package-private */
        public int getAlignX() {
            return this.mAlignX;
        }

        /* access modifiers changed from: package-private */
        public int getAlignY() {
            return this.mAlignY;
        }

        /* access modifiers changed from: package-private */
        public int getOpticalLeft(View view) {
            return view.getLeft() + this.mLeftInset;
        }

        /* access modifiers changed from: package-private */
        public int getOpticalTop(View view) {
            return view.getTop() + this.mTopInset;
        }

        /* access modifiers changed from: package-private */
        public int getOpticalRight(View view) {
            return view.getRight() - this.mRightInset;
        }

        /* access modifiers changed from: package-private */
        public int getOpticalBottom(View view) {
            return view.getBottom() - this.mBottomInset;
        }

        /* access modifiers changed from: package-private */
        public int getOpticalWidth(View view) {
            return (view.getWidth() - this.mLeftInset) - this.mRightInset;
        }

        /* access modifiers changed from: package-private */
        public int getOpticalHeight(View view) {
            return (view.getHeight() - this.mTopInset) - this.mBottomInset;
        }

        /* access modifiers changed from: package-private */
        public int getOpticalLeftInset() {
            return this.mLeftInset;
        }

        /* access modifiers changed from: package-private */
        public int getOpticalRightInset() {
            return this.mRightInset;
        }

        /* access modifiers changed from: package-private */
        public int getOpticalTopInset() {
            return this.mTopInset;
        }

        /* access modifiers changed from: package-private */
        public int getOpticalBottomInset() {
            return this.mBottomInset;
        }

        /* access modifiers changed from: package-private */
        public void setAlignX(int i) {
            this.mAlignX = i;
        }

        /* access modifiers changed from: package-private */
        public void setAlignY(int i) {
            this.mAlignY = i;
        }

        /* access modifiers changed from: package-private */
        public void setItemAlignmentFacet(ItemAlignmentFacet itemAlignmentFacet) {
            this.mAlignmentFacet = itemAlignmentFacet;
        }

        /* access modifiers changed from: package-private */
        public ItemAlignmentFacet getItemAlignmentFacet() {
            return this.mAlignmentFacet;
        }

        /* access modifiers changed from: package-private */
        public void calculateItemAlignments(int i, View view) {
            ItemAlignmentFacet.ItemAlignmentDef[] alignmentDefs = this.mAlignmentFacet.getAlignmentDefs();
            int[] iArr = this.mAlignMultiple;
            if (iArr == null || iArr.length != alignmentDefs.length) {
                this.mAlignMultiple = new int[alignmentDefs.length];
            }
            for (int i2 = 0; i2 < alignmentDefs.length; i2++) {
                this.mAlignMultiple[i2] = ItemAlignmentFacetHelper.getAlignmentPosition(view, alignmentDefs[i2], i);
            }
            if (i == 0) {
                this.mAlignX = this.mAlignMultiple[0];
            } else {
                this.mAlignY = this.mAlignMultiple[0];
            }
        }

        /* access modifiers changed from: package-private */
        public int[] getAlignMultiple() {
            return this.mAlignMultiple;
        }

        /* access modifiers changed from: package-private */
        public void setOpticalInsets(int i, int i2, int i3, int i4) {
            this.mLeftInset = i;
            this.mTopInset = i2;
            this.mRightInset = i3;
            this.mBottomInset = i4;
        }
    }

    abstract class GridLinearSmoothScroller extends LinearSmoothScroller {
        boolean mSkipOnStopInternal;

        GridLinearSmoothScroller() {
            super(GridLayoutManager.this.mBaseGridView.getContext());
        }

        /* access modifiers changed from: protected */
        public void onStop() {
            super.onStop();
            if (!this.mSkipOnStopInternal) {
                onStopInternal();
            }
            if (GridLayoutManager.this.mCurrentSmoothScroller == this) {
                GridLayoutManager.this.mCurrentSmoothScroller = null;
            }
            if (GridLayoutManager.this.mPendingMoveSmoothScroller == this) {
                GridLayoutManager.this.mPendingMoveSmoothScroller = null;
            }
        }

        /* access modifiers changed from: protected */
        public void onStopInternal() {
            View findViewByPosition = findViewByPosition(getTargetPosition());
            if (findViewByPosition != null) {
                if (GridLayoutManager.this.mFocusPosition != getTargetPosition()) {
                    GridLayoutManager.this.mFocusPosition = getTargetPosition();
                }
                if (GridLayoutManager.this.hasFocus()) {
                    GridLayoutManager.this.mFlag |= 32;
                    findViewByPosition.requestFocus();
                    GridLayoutManager.this.mFlag &= -33;
                }
                GridLayoutManager.this.dispatchChildSelected();
                GridLayoutManager.this.dispatchChildSelectedAndPositioned();
            } else if (getTargetPosition() >= 0) {
                GridLayoutManager.this.scrollToSelection(getTargetPosition(), 0, false, 0);
            }
        }

        /* access modifiers changed from: protected */
        public int calculateTimeForScrolling(int i) {
            int calculateTimeForScrolling = super.calculateTimeForScrolling(i);
            if (GridLayoutManager.this.mWindowAlignment.mainAxis().getSize() <= 0) {
                return calculateTimeForScrolling;
            }
            float size = (30.0f / ((float) GridLayoutManager.this.mWindowAlignment.mainAxis().getSize())) * ((float) i);
            return ((float) calculateTimeForScrolling) < size ? (int) size : calculateTimeForScrolling;
        }

        /* access modifiers changed from: protected */
        public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            int i;
            int i2;
            if (GridLayoutManager.this.getScrollPosition(view, (View) null, GridLayoutManager.sTwoInts)) {
                if (GridLayoutManager.this.mOrientation == 0) {
                    i2 = GridLayoutManager.sTwoInts[0];
                    i = GridLayoutManager.sTwoInts[1];
                } else {
                    i2 = GridLayoutManager.sTwoInts[1];
                    i = GridLayoutManager.sTwoInts[0];
                }
                action.update(i2, i, calculateTimeForDeceleration((int) Math.sqrt((double) ((i2 * i2) + (i * i)))), this.mDecelerateInterpolator);
            }
        }
    }

    final class PendingMoveSmoothScroller extends GridLinearSmoothScroller {
        static final int TARGET_UNDEFINED = -2;
        private int mPendingMoves;
        private final boolean mStaggeredGrid;

        PendingMoveSmoothScroller(int i, boolean z) {
            super();
            this.mPendingMoves = i;
            this.mStaggeredGrid = z;
            setTargetPosition(-2);
        }

        /* access modifiers changed from: package-private */
        public void increasePendingMoves() {
            if (this.mPendingMoves < GridLayoutManager.this.mMaxPendingMoves) {
                this.mPendingMoves++;
            }
        }

        /* access modifiers changed from: package-private */
        public void decreasePendingMoves() {
            if (this.mPendingMoves > (-GridLayoutManager.this.mMaxPendingMoves)) {
                this.mPendingMoves--;
            }
        }

        /* access modifiers changed from: package-private */
        public void consumePendingMovesBeforeLayout() {
            int i;
            View findViewByPosition;
            if (!this.mStaggeredGrid && (i = this.mPendingMoves) != 0) {
                int i2 = i > 0 ? GridLayoutManager.this.mFocusPosition + GridLayoutManager.this.mNumRows : GridLayoutManager.this.mFocusPosition - GridLayoutManager.this.mNumRows;
                View view = null;
                while (this.mPendingMoves != 0 && (findViewByPosition = findViewByPosition(i2)) != null) {
                    if (GridLayoutManager.this.canScrollTo(findViewByPosition)) {
                        GridLayoutManager.this.mFocusPosition = i2;
                        GridLayoutManager.this.mSubFocusPosition = 0;
                        int i3 = this.mPendingMoves;
                        if (i3 > 0) {
                            this.mPendingMoves = i3 - 1;
                        } else {
                            this.mPendingMoves = i3 + 1;
                        }
                        view = findViewByPosition;
                    }
                    i2 = this.mPendingMoves > 0 ? i2 + GridLayoutManager.this.mNumRows : i2 - GridLayoutManager.this.mNumRows;
                }
                if (view != null && GridLayoutManager.this.hasFocus()) {
                    GridLayoutManager.this.mFlag |= 32;
                    view.requestFocus();
                    GridLayoutManager.this.mFlag &= -33;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void consumePendingMovesAfterLayout() {
            int i;
            if (this.mStaggeredGrid && (i = this.mPendingMoves) != 0) {
                this.mPendingMoves = GridLayoutManager.this.processSelectionMoves(true, i);
            }
            int i2 = this.mPendingMoves;
            if (i2 == 0 || ((i2 > 0 && GridLayoutManager.this.hasCreatedLastItem()) || (this.mPendingMoves < 0 && GridLayoutManager.this.hasCreatedFirstItem()))) {
                setTargetPosition(GridLayoutManager.this.mFocusPosition);
                stop();
            }
        }

        /* access modifiers changed from: protected */
        public void updateActionForInterimTarget(RecyclerView.SmoothScroller.Action action) {
            if (this.mPendingMoves != 0) {
                super.updateActionForInterimTarget(action);
            }
        }

        public PointF computeScrollVectorForPosition(int i) {
            if (this.mPendingMoves == 0) {
                return null;
            }
            int i2 = ((GridLayoutManager.this.mFlag & 262144) == 0 ? this.mPendingMoves >= 0 : this.mPendingMoves <= 0) ? 1 : -1;
            if (GridLayoutManager.this.mOrientation == 0) {
                return new PointF((float) i2, 0.0f);
            }
            return new PointF(0.0f, (float) i2);
        }

        /* access modifiers changed from: protected */
        public void onStopInternal() {
            super.onStopInternal();
            this.mPendingMoves = 0;
            View findViewByPosition = findViewByPosition(getTargetPosition());
            if (findViewByPosition != null) {
                GridLayoutManager.this.scrollToView(findViewByPosition, true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String getTag() {
        return "GridLayoutManager:" + this.mBaseGridView.getId();
    }

    public GridLayoutManager(BaseGridView baseGridView) {
        this.mBaseGridView = baseGridView;
        this.mChildVisibility = -1;
        setItemPrefetchEnabled(false);
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            this.mOrientation = i;
            this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, i);
            this.mWindowAlignment.setOrientation(i);
            this.mItemAlignment.setOrientation(i);
            this.mFlag |= 256;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0019  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0018 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onRtlPropertiesChanged(int r6) {
        /*
            r5 = this;
            int r0 = r5.mOrientation
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x000d
            if (r6 != r2) goto L_0x000b
            r0 = 262144(0x40000, float:3.67342E-40)
            goto L_0x0011
        L_0x000b:
            r0 = 0
            goto L_0x0011
        L_0x000d:
            if (r6 != r2) goto L_0x000b
            r0 = 524288(0x80000, float:7.34684E-40)
        L_0x0011:
            int r3 = r5.mFlag
            r4 = 786432(0xc0000, float:1.102026E-39)
            r4 = r4 & r3
            if (r4 != r0) goto L_0x0019
            return
        L_0x0019:
            r4 = -786433(0xfffffffffff3ffff, float:NaN)
            r3 = r3 & r4
            r0 = r0 | r3
            r0 = r0 | 256(0x100, float:3.59E-43)
            r5.mFlag = r0
            androidx.leanback.widget.WindowAlignment r0 = r5.mWindowAlignment
            androidx.leanback.widget.WindowAlignment$Axis r0 = r0.horizontal
            if (r6 != r2) goto L_0x0029
            r1 = 1
        L_0x0029:
            r0.setReversedFlow(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.onRtlPropertiesChanged(int):void");
    }

    public int getFocusScrollStrategy() {
        return this.mFocusScrollStrategy;
    }

    public void setFocusScrollStrategy(int i) {
        this.mFocusScrollStrategy = i;
    }

    public void setWindowAlignment(int i) {
        this.mWindowAlignment.mainAxis().setWindowAlignment(i);
    }

    public int getWindowAlignment() {
        return this.mWindowAlignment.mainAxis().getWindowAlignment();
    }

    public void setWindowAlignmentOffset(int i) {
        this.mWindowAlignment.mainAxis().setWindowAlignmentOffset(i);
    }

    public int getWindowAlignmentOffset() {
        return this.mWindowAlignment.mainAxis().getWindowAlignmentOffset();
    }

    public void setWindowAlignmentOffsetPercent(float f) {
        this.mWindowAlignment.mainAxis().setWindowAlignmentOffsetPercent(f);
    }

    public float getWindowAlignmentOffsetPercent() {
        return this.mWindowAlignment.mainAxis().getWindowAlignmentOffsetPercent();
    }

    public void setItemAlignmentOffset(int i) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffset(i);
        updateChildAlignments();
    }

    public int getItemAlignmentOffset() {
        return this.mItemAlignment.mainAxis().getItemAlignmentOffset();
    }

    public void setItemAlignmentOffsetWithPadding(boolean z) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffsetWithPadding(z);
        updateChildAlignments();
    }

    public boolean isItemAlignmentOffsetWithPadding() {
        return this.mItemAlignment.mainAxis().isItemAlignmentOffsetWithPadding();
    }

    public void setItemAlignmentOffsetPercent(float f) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffsetPercent(f);
        updateChildAlignments();
    }

    public float getItemAlignmentOffsetPercent() {
        return this.mItemAlignment.mainAxis().getItemAlignmentOffsetPercent();
    }

    public void setItemAlignmentViewId(int i) {
        this.mItemAlignment.mainAxis().setItemAlignmentViewId(i);
        updateChildAlignments();
    }

    public int getItemAlignmentViewId() {
        return this.mItemAlignment.mainAxis().getItemAlignmentViewId();
    }

    public void setFocusOutAllowed(boolean z, boolean z2) {
        int i = 0;
        int i2 = (z ? 2048 : 0) | (this.mFlag & -6145);
        if (z2) {
            i = 4096;
        }
        this.mFlag = i2 | i;
    }

    public void setFocusOutSideAllowed(boolean z, boolean z2) {
        int i = 0;
        int i2 = (z ? 8192 : 0) | (this.mFlag & -24577);
        if (z2) {
            i = 16384;
        }
        this.mFlag = i2 | i;
    }

    public void setNumRows(int i) {
        if (i >= 0) {
            this.mNumRowsRequested = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setRowHeight(int i) {
        if (i >= 0 || i == -2) {
            this.mRowSizeSecondaryRequested = i;
            return;
        }
        throw new IllegalArgumentException("Invalid row height: " + i);
    }

    public void setItemSpacing(int i) {
        this.mHorizontalSpacing = i;
        this.mVerticalSpacing = i;
        this.mSpacingSecondary = i;
        this.mSpacingPrimary = i;
    }

    public void setVerticalSpacing(int i) {
        if (this.mOrientation == 1) {
            this.mVerticalSpacing = i;
            this.mSpacingPrimary = i;
            return;
        }
        this.mVerticalSpacing = i;
        this.mSpacingSecondary = i;
    }

    public void setHorizontalSpacing(int i) {
        if (this.mOrientation == 0) {
            this.mHorizontalSpacing = i;
            this.mSpacingPrimary = i;
            return;
        }
        this.mHorizontalSpacing = i;
        this.mSpacingSecondary = i;
    }

    public int getVerticalSpacing() {
        return this.mVerticalSpacing;
    }

    public int getHorizontalSpacing() {
        return this.mHorizontalSpacing;
    }

    public void setGravity(int i) {
        this.mGravity = i;
    }

    /* access modifiers changed from: protected */
    public boolean hasDoneFirstLayout() {
        return this.mGrid != null;
    }

    public void setOnChildSelectedListener(OnChildSelectedListener onChildSelectedListener) {
        this.mChildSelectedListener = onChildSelectedListener;
    }

    public void setOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        if (onChildViewHolderSelectedListener == null) {
            this.mChildViewHolderSelectedListeners = null;
            return;
        }
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList == null) {
            this.mChildViewHolderSelectedListeners = new ArrayList<>();
        } else {
            arrayList.clear();
        }
        this.mChildViewHolderSelectedListeners.add(onChildViewHolderSelectedListener);
    }

    public void addOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        if (this.mChildViewHolderSelectedListeners == null) {
            this.mChildViewHolderSelectedListeners = new ArrayList<>();
        }
        this.mChildViewHolderSelectedListeners.add(onChildViewHolderSelectedListener);
    }

    public void removeOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList != null) {
            arrayList.remove(onChildViewHolderSelectedListener);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasOnChildViewHolderSelectedListener() {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        return arrayList != null && arrayList.size() > 0;
    }

    /* access modifiers changed from: package-private */
    public void fireOnChildViewHolderSelected(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, int i2) {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                this.mChildViewHolderSelectedListeners.get(size).onChildViewHolderSelected(recyclerView, viewHolder, i, i2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void fireOnChildViewHolderSelectedAndPositioned(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, int i2) {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                this.mChildViewHolderSelectedListeners.get(size).onChildViewHolderSelectedAndPositioned(recyclerView, viewHolder, i, i2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setOnChildLaidOutListener(OnChildLaidOutListener onChildLaidOutListener) {
        this.mChildLaidOutListener = onChildLaidOutListener;
    }

    private int getAdapterPositionByView(View view) {
        LayoutParams layoutParams;
        if (view == null || (layoutParams = (LayoutParams) view.getLayoutParams()) == null || layoutParams.isItemRemoved()) {
            return -1;
        }
        return layoutParams.getViewAdapterPosition();
    }

    /* access modifiers changed from: package-private */
    public int getSubPositionByView(View view, View view2) {
        ItemAlignmentFacet itemAlignmentFacet;
        if (!(view == null || view2 == null || (itemAlignmentFacet = ((LayoutParams) view.getLayoutParams()).getItemAlignmentFacet()) == null)) {
            ItemAlignmentFacet.ItemAlignmentDef[] alignmentDefs = itemAlignmentFacet.getAlignmentDefs();
            if (alignmentDefs.length > 1) {
                while (view2 != view) {
                    int id = view2.getId();
                    if (id != -1) {
                        for (int i = 1; i < alignmentDefs.length; i++) {
                            if (alignmentDefs[i].getItemAlignmentFocusViewId() == id) {
                                return i;
                            }
                        }
                        continue;
                    }
                    view2 = (View) view2.getParent();
                }
            }
        }
        return 0;
    }

    private int getAdapterPositionByIndex(int i) {
        return getAdapterPositionByView(getChildAt(i));
    }

    /* access modifiers changed from: package-private */
    public void dispatchChildSelected() {
        long j;
        if (this.mChildSelectedListener != null || hasOnChildViewHolderSelectedListener()) {
            int i = this.mFocusPosition;
            View findViewByPosition = i == -1 ? null : findViewByPosition(i);
            if (findViewByPosition != null) {
                RecyclerView.ViewHolder childViewHolder = this.mBaseGridView.getChildViewHolder(findViewByPosition);
                OnChildSelectedListener onChildSelectedListener = this.mChildSelectedListener;
                if (onChildSelectedListener != null) {
                    BaseGridView baseGridView = this.mBaseGridView;
                    int i2 = this.mFocusPosition;
                    if (childViewHolder == null) {
                        j = -1;
                    } else {
                        j = childViewHolder.getItemId();
                    }
                    onChildSelectedListener.onChildSelected(baseGridView, findViewByPosition, i2, j);
                }
                fireOnChildViewHolderSelected(this.mBaseGridView, childViewHolder, this.mFocusPosition, this.mSubFocusPosition);
            } else {
                OnChildSelectedListener onChildSelectedListener2 = this.mChildSelectedListener;
                if (onChildSelectedListener2 != null) {
                    onChildSelectedListener2.onChildSelected(this.mBaseGridView, (View) null, -1, -1);
                }
                fireOnChildViewHolderSelected(this.mBaseGridView, (RecyclerView.ViewHolder) null, -1, 0);
            }
            if ((this.mFlag & 3) != 1 && !this.mBaseGridView.isLayoutRequested()) {
                int childCount = getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    if (getChildAt(i3).isLayoutRequested()) {
                        forceRequestLayout();
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchChildSelectedAndPositioned() {
        if (hasOnChildViewHolderSelectedListener()) {
            int i = this.mFocusPosition;
            View findViewByPosition = i == -1 ? null : findViewByPosition(i);
            if (findViewByPosition != null) {
                fireOnChildViewHolderSelectedAndPositioned(this.mBaseGridView, this.mBaseGridView.getChildViewHolder(findViewByPosition), this.mFocusPosition, this.mSubFocusPosition);
                return;
            }
            OnChildSelectedListener onChildSelectedListener = this.mChildSelectedListener;
            if (onChildSelectedListener != null) {
                onChildSelectedListener.onChildSelected(this.mBaseGridView, (View) null, -1, -1);
            }
            fireOnChildViewHolderSelectedAndPositioned(this.mBaseGridView, (RecyclerView.ViewHolder) null, -1, 0);
        }
    }

    public boolean canScrollHorizontally() {
        return this.mOrientation == 0 || this.mNumRows > 1;
    }

    public boolean canScrollVertically() {
        return this.mOrientation == 1 || this.mNumRows > 1;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            return new LayoutParams((RecyclerView.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public View getViewForPosition(int i) {
        return this.mRecycler.getViewForPosition(i);
    }

    /* access modifiers changed from: package-private */
    public final int getOpticalLeft(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalLeft(view);
    }

    /* access modifiers changed from: package-private */
    public final int getOpticalRight(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalRight(view);
    }

    /* access modifiers changed from: package-private */
    public final int getOpticalTop(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalTop(view);
    }

    /* access modifiers changed from: package-private */
    public final int getOpticalBottom(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalBottom(view);
    }

    public int getDecoratedLeft(View view) {
        return super.getDecoratedLeft(view) + ((LayoutParams) view.getLayoutParams()).mLeftInset;
    }

    public int getDecoratedTop(View view) {
        return super.getDecoratedTop(view) + ((LayoutParams) view.getLayoutParams()).mTopInset;
    }

    public int getDecoratedRight(View view) {
        return super.getDecoratedRight(view) - ((LayoutParams) view.getLayoutParams()).mRightInset;
    }

    public int getDecoratedBottom(View view) {
        return super.getDecoratedBottom(view) - ((LayoutParams) view.getLayoutParams()).mBottomInset;
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        super.getDecoratedBoundsWithMargins(view, rect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        rect.left += layoutParams.mLeftInset;
        rect.top += layoutParams.mTopInset;
        rect.right -= layoutParams.mRightInset;
        rect.bottom -= layoutParams.mBottomInset;
    }

    /* access modifiers changed from: package-private */
    public int getViewMin(View view) {
        return this.mOrientationHelper.getDecoratedStart(view);
    }

    /* access modifiers changed from: package-private */
    public int getViewMax(View view) {
        return this.mOrientationHelper.getDecoratedEnd(view);
    }

    /* access modifiers changed from: package-private */
    public int getViewPrimarySize(View view) {
        Rect rect = sTempRect;
        getDecoratedBoundsWithMargins(view, rect);
        return this.mOrientation == 0 ? rect.width() : rect.height();
    }

    private int getViewCenter(View view) {
        return this.mOrientation == 0 ? getViewCenterX(view) : getViewCenterY(view);
    }

    private int getAdjustedViewCenter(View view) {
        View findFocus;
        if (!view.hasFocus() || (findFocus = view.findFocus()) == null || findFocus == view) {
            return getViewCenter(view);
        }
        return getAdjustedPrimaryAlignedScrollDistance(getViewCenter(view), view, findFocus);
    }

    private int getViewCenterSecondary(View view) {
        return this.mOrientation == 0 ? getViewCenterY(view) : getViewCenterX(view);
    }

    private int getViewCenterX(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.getOpticalLeft(view) + layoutParams.getAlignX();
    }

    private int getViewCenterY(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.getOpticalTop(view) + layoutParams.getAlignY();
    }

    private void saveContext(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!(this.mRecycler == null && this.mState == null)) {
            Log.e(TAG, "Recycler information was not released, bug!");
        }
        this.mRecycler = recycler;
        this.mState = state;
        this.mPositionDeltaInPreLayout = 0;
        this.mExtraLayoutSpaceInPreLayout = 0;
    }

    private void leaveContext() {
        this.mRecycler = null;
        this.mState = null;
        this.mPositionDeltaInPreLayout = 0;
        this.mExtraLayoutSpaceInPreLayout = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0074, code lost:
        if (((r5.mFlag & 262144) != 0) != r5.mGrid.isReversedFlow()) goto L_0x0076;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean layoutInit() {
        /*
            r5 = this;
            androidx.recyclerview.widget.RecyclerView$State r0 = r5.mState
            int r0 = r0.getItemCount()
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 != 0) goto L_0x0010
            r5.mFocusPosition = r1
            r5.mSubFocusPosition = r3
            goto L_0x0022
        L_0x0010:
            int r4 = r5.mFocusPosition
            if (r4 < r0) goto L_0x001a
            int r0 = r0 - r2
            r5.mFocusPosition = r0
            r5.mSubFocusPosition = r3
            goto L_0x0022
        L_0x001a:
            if (r4 != r1) goto L_0x0022
            if (r0 <= 0) goto L_0x0022
            r5.mFocusPosition = r3
            r5.mSubFocusPosition = r3
        L_0x0022:
            androidx.recyclerview.widget.RecyclerView$State r0 = r5.mState
            boolean r0 = r0.didStructureChange()
            if (r0 != 0) goto L_0x0052
            androidx.leanback.widget.Grid r0 = r5.mGrid
            if (r0 == 0) goto L_0x0052
            int r0 = r0.getFirstVisibleIndex()
            if (r0 < 0) goto L_0x0052
            int r0 = r5.mFlag
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 != 0) goto L_0x0052
            androidx.leanback.widget.Grid r0 = r5.mGrid
            int r0 = r0.getNumRows()
            int r1 = r5.mNumRows
            if (r0 != r1) goto L_0x0052
            r5.updateScrollController()
            r5.updateSecondaryScrollLimits()
            androidx.leanback.widget.Grid r0 = r5.mGrid
            int r1 = r5.mSpacingPrimary
            r0.setSpacing(r1)
            return r2
        L_0x0052:
            int r0 = r5.mFlag
            r0 = r0 & -257(0xfffffffffffffeff, float:NaN)
            r5.mFlag = r0
            androidx.leanback.widget.Grid r0 = r5.mGrid
            r1 = 262144(0x40000, float:3.67342E-40)
            if (r0 == 0) goto L_0x0076
            int r4 = r5.mNumRows
            int r0 = r0.getNumRows()
            if (r4 != r0) goto L_0x0076
            int r0 = r5.mFlag
            r0 = r0 & r1
            if (r0 == 0) goto L_0x006d
            r0 = 1
            goto L_0x006e
        L_0x006d:
            r0 = 0
        L_0x006e:
            androidx.leanback.widget.Grid r4 = r5.mGrid
            boolean r4 = r4.isReversedFlow()
            if (r0 == r4) goto L_0x008f
        L_0x0076:
            int r0 = r5.mNumRows
            androidx.leanback.widget.Grid r0 = androidx.leanback.widget.Grid.createGrid(r0)
            r5.mGrid = r0
            androidx.leanback.widget.Grid$Provider r4 = r5.mGridProvider
            r0.setProvider(r4)
            androidx.leanback.widget.Grid r0 = r5.mGrid
            int r4 = r5.mFlag
            r1 = r1 & r4
            if (r1 == 0) goto L_0x008b
            goto L_0x008c
        L_0x008b:
            r2 = 0
        L_0x008c:
            r0.setReversedFlow(r2)
        L_0x008f:
            r5.initScrollController()
            r5.updateSecondaryScrollLimits()
            androidx.leanback.widget.Grid r0 = r5.mGrid
            int r1 = r5.mSpacingPrimary
            r0.setSpacing(r1)
            androidx.recyclerview.widget.RecyclerView$Recycler r0 = r5.mRecycler
            r5.detachAndScrapAttachedViews(r0)
            androidx.leanback.widget.Grid r0 = r5.mGrid
            r0.resetVisibleIndex()
            androidx.leanback.widget.WindowAlignment r0 = r5.mWindowAlignment
            androidx.leanback.widget.WindowAlignment$Axis r0 = r0.mainAxis()
            r0.invalidateScrollMin()
            androidx.leanback.widget.WindowAlignment r0 = r5.mWindowAlignment
            androidx.leanback.widget.WindowAlignment$Axis r0 = r0.mainAxis()
            r0.invalidateScrollMax()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.layoutInit():boolean");
    }

    private int getRowSizeSecondary(int i) {
        int i2 = this.mFixedRowSizeSecondary;
        if (i2 != 0) {
            return i2;
        }
        int[] iArr = this.mRowSizeSecondary;
        if (iArr == null) {
            return 0;
        }
        return iArr[i];
    }

    /* access modifiers changed from: package-private */
    public int getRowStartSecondary(int i) {
        int i2 = 0;
        if ((this.mFlag & 524288) != 0) {
            for (int i3 = this.mNumRows - 1; i3 > i; i3--) {
                i2 += getRowSizeSecondary(i3) + this.mSpacingSecondary;
            }
            return i2;
        }
        int i4 = 0;
        while (i2 < i) {
            i4 += getRowSizeSecondary(i2) + this.mSpacingSecondary;
            i2++;
        }
        return i4;
    }

    private int getSizeSecondary() {
        int i = (this.mFlag & 524288) != 0 ? 0 : this.mNumRows - 1;
        return getRowStartSecondary(i) + getRowSizeSecondary(i);
    }

    /* access modifiers changed from: package-private */
    public int getDecoratedMeasuredWidthWithMargin(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return getDecoratedMeasuredWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
    }

    /* access modifiers changed from: package-private */
    public int getDecoratedMeasuredHeightWithMargin(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return getDecoratedMeasuredHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    private void measureScrapChild(int i, int i2, int i3, int[] iArr) {
        View viewForPosition = this.mRecycler.getViewForPosition(i);
        if (viewForPosition != null) {
            LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
            Rect rect = sTempRect;
            calculateItemDecorationsForChild(viewForPosition, rect);
            viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin + rect.left + rect.right, layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top + rect.bottom, layoutParams.height));
            iArr[0] = getDecoratedMeasuredWidthWithMargin(viewForPosition);
            iArr[1] = getDecoratedMeasuredHeightWithMargin(viewForPosition);
            this.mRecycler.recycleView(viewForPosition);
        }
    }

    private boolean processRowSizeSecondary(boolean z) {
        CircularIntArray circularIntArray;
        int i;
        int i2;
        int i3;
        if (this.mFixedRowSizeSecondary != 0 || this.mRowSizeSecondary == null) {
            return false;
        }
        Grid grid = this.mGrid;
        CircularIntArray[] itemPositionsInRows = grid == null ? null : grid.getItemPositionsInRows();
        boolean z2 = false;
        int i4 = -1;
        for (int i5 = 0; i5 < this.mNumRows; i5++) {
            if (itemPositionsInRows == null) {
                circularIntArray = null;
            } else {
                circularIntArray = itemPositionsInRows[i5];
            }
            if (circularIntArray == null) {
                i = 0;
            } else {
                i = circularIntArray.size();
            }
            int i6 = -1;
            for (int i7 = 0; i7 < i; i7 += 2) {
                int i8 = circularIntArray.get(i7 + 1);
                for (int i9 = circularIntArray.get(i7); i9 <= i8; i9++) {
                    View findViewByPosition = findViewByPosition(i9 - this.mPositionDeltaInPreLayout);
                    if (findViewByPosition != null) {
                        if (z) {
                            measureChild(findViewByPosition);
                        }
                        if (this.mOrientation == 0) {
                            i3 = getDecoratedMeasuredHeightWithMargin(findViewByPosition);
                        } else {
                            i3 = getDecoratedMeasuredWidthWithMargin(findViewByPosition);
                        }
                        if (i3 > i6) {
                            i6 = i3;
                        }
                    }
                }
            }
            int itemCount = this.mState.getItemCount();
            if (!this.mBaseGridView.hasFixedSize() && z && i6 < 0 && itemCount > 0) {
                if (i4 < 0) {
                    int i10 = this.mFocusPosition;
                    if (i10 < 0) {
                        i10 = 0;
                    } else if (i10 >= itemCount) {
                        i10 = itemCount - 1;
                    }
                    if (getChildCount() > 0) {
                        int layoutPosition = this.mBaseGridView.getChildViewHolder(getChildAt(0)).getLayoutPosition();
                        int layoutPosition2 = this.mBaseGridView.getChildViewHolder(getChildAt(getChildCount() - 1)).getLayoutPosition();
                        if (i2 >= layoutPosition && i2 <= layoutPosition2) {
                            i2 = i2 - layoutPosition <= layoutPosition2 - i2 ? layoutPosition - 1 : layoutPosition2 + 1;
                            if (i2 < 0 && layoutPosition2 < itemCount - 1) {
                                i2 = layoutPosition2 + 1;
                            } else if (i2 >= itemCount && layoutPosition > 0) {
                                i2 = layoutPosition - 1;
                            }
                        }
                    }
                    if (i2 >= 0 && i2 < itemCount) {
                        measureScrapChild(i2, View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0), this.mMeasuredDimension);
                        i4 = this.mOrientation == 0 ? this.mMeasuredDimension[1] : this.mMeasuredDimension[0];
                    }
                }
                if (i4 >= 0) {
                    i6 = i4;
                }
            }
            if (i6 < 0) {
                i6 = 0;
            }
            int[] iArr = this.mRowSizeSecondary;
            if (iArr[i5] != i6) {
                iArr[i5] = i6;
                z2 = true;
            }
        }
        return z2;
    }

    private void updateRowSecondarySizeRefresh() {
        int i = this.mFlag & -1025;
        int i2 = 0;
        if (processRowSizeSecondary(false)) {
            i2 = 1024;
        }
        int i3 = i | i2;
        this.mFlag = i3;
        if ((i3 & 1024) != 0) {
            forceRequestLayout();
        }
    }

    private void forceRequestLayout() {
        ViewCompat.postOnAnimation(this.mBaseGridView, this.mRequestLayoutRunnable);
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        saveContext(recycler, state);
        if (this.mOrientation == 0) {
            i6 = View.MeasureSpec.getSize(i);
            i5 = View.MeasureSpec.getSize(i2);
            i4 = View.MeasureSpec.getMode(i2);
            i3 = getPaddingTop();
            i7 = getPaddingBottom();
        } else {
            i5 = View.MeasureSpec.getSize(i);
            i6 = View.MeasureSpec.getSize(i2);
            i4 = View.MeasureSpec.getMode(i);
            i3 = getPaddingLeft();
            i7 = getPaddingRight();
        }
        int i8 = i3 + i7;
        this.mMaxSizeSecondary = i5;
        int i9 = this.mRowSizeSecondaryRequested;
        if (i9 == -2) {
            int i10 = this.mNumRowsRequested;
            if (i10 == 0) {
                i10 = 1;
            }
            this.mNumRows = i10;
            this.mFixedRowSizeSecondary = 0;
            int[] iArr = this.mRowSizeSecondary;
            if (iArr == null || iArr.length != i10) {
                this.mRowSizeSecondary = new int[i10];
            }
            if (this.mState.isPreLayout()) {
                updatePositionDeltaInPreLayout();
            }
            processRowSizeSecondary(true);
            if (i4 == Integer.MIN_VALUE) {
                i5 = Math.min(getSizeSecondary() + i8, this.mMaxSizeSecondary);
            } else if (i4 == 0) {
                i5 = getSizeSecondary() + i8;
            } else if (i4 == 1073741824) {
                i5 = this.mMaxSizeSecondary;
            } else {
                throw new IllegalStateException("wrong spec");
            }
        } else {
            if (i4 != Integer.MIN_VALUE) {
                if (i4 == 0) {
                    if (i9 == 0) {
                        i9 = i5 - i8;
                    }
                    this.mFixedRowSizeSecondary = i9;
                    int i11 = this.mNumRowsRequested;
                    if (i11 == 0) {
                        i11 = 1;
                    }
                    this.mNumRows = i11;
                    i5 = (i9 * i11) + (this.mSpacingSecondary * (i11 - 1)) + i8;
                } else if (i4 != 1073741824) {
                    throw new IllegalStateException("wrong spec");
                }
            }
            int i12 = this.mNumRowsRequested;
            if (i12 == 0 && i9 == 0) {
                this.mNumRows = 1;
                this.mFixedRowSizeSecondary = i5 - i8;
            } else if (i12 == 0) {
                this.mFixedRowSizeSecondary = i9;
                int i13 = this.mSpacingSecondary;
                this.mNumRows = (i5 + i13) / (i9 + i13);
            } else if (i9 == 0) {
                this.mNumRows = i12;
                this.mFixedRowSizeSecondary = ((i5 - i8) - (this.mSpacingSecondary * (i12 - 1))) / i12;
            } else {
                this.mNumRows = i12;
                this.mFixedRowSizeSecondary = i9;
            }
            if (i4 == Integer.MIN_VALUE) {
                int i14 = this.mFixedRowSizeSecondary;
                int i15 = this.mNumRows;
                int i16 = (i14 * i15) + (this.mSpacingSecondary * (i15 - 1)) + i8;
                if (i16 < i5) {
                    i5 = i16;
                }
            }
        }
        if (this.mOrientation == 0) {
            setMeasuredDimension(i6, i5);
        } else {
            setMeasuredDimension(i5, i6);
        }
        leaveContext();
    }

    /* access modifiers changed from: package-private */
    public void measureChild(View view) {
        int i;
        int i2;
        int i3;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = sTempRect;
        calculateItemDecorationsForChild(view, rect);
        int i4 = layoutParams.leftMargin + layoutParams.rightMargin + rect.left + rect.right;
        int i5 = layoutParams.topMargin + layoutParams.bottomMargin + rect.top + rect.bottom;
        if (this.mRowSizeSecondaryRequested == -2) {
            i = View.MeasureSpec.makeMeasureSpec(0, 0);
        } else {
            i = View.MeasureSpec.makeMeasureSpec(this.mFixedRowSizeSecondary, 1073741824);
        }
        if (this.mOrientation == 0) {
            i2 = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), i4, layoutParams.width);
            i3 = ViewGroup.getChildMeasureSpec(i, i5, layoutParams.height);
        } else {
            int childMeasureSpec = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), i5, layoutParams.height);
            i2 = ViewGroup.getChildMeasureSpec(i, i4, layoutParams.width);
            i3 = childMeasureSpec;
        }
        view.measure(i2, i3);
    }

    /* access modifiers changed from: package-private */
    public <E> E getFacet(RecyclerView.ViewHolder viewHolder, Class<? extends E> cls) {
        FacetProviderAdapter facetProviderAdapter;
        FacetProvider facetProvider;
        E facet = viewHolder instanceof FacetProvider ? ((FacetProvider) viewHolder).getFacet(cls) : null;
        return (facet != null || (facetProviderAdapter = this.mFacetProviderAdapter) == null || (facetProvider = facetProviderAdapter.getFacetProvider(viewHolder.getItemViewType())) == null) ? facet : facetProvider.getFacet(cls);
    }

    /* access modifiers changed from: package-private */
    public void layoutChild(int i, View view, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        if (this.mOrientation == 0) {
            i5 = getDecoratedMeasuredHeightWithMargin(view);
        } else {
            i5 = getDecoratedMeasuredWidthWithMargin(view);
        }
        int i8 = this.mFixedRowSizeSecondary;
        if (i8 > 0) {
            i5 = Math.min(i5, i8);
        }
        int i9 = this.mGravity;
        int i10 = i9 & 112;
        int absoluteGravity = (this.mFlag & PF_REVERSE_FLOW_MASK) != 0 ? Gravity.getAbsoluteGravity(i9 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK, 1) : i9 & 7;
        int i11 = this.mOrientation;
        if (!((i11 == 0 && i10 == 48) || (i11 == 1 && absoluteGravity == 3))) {
            if ((i11 == 0 && i10 == 80) || (i11 == 1 && absoluteGravity == 5)) {
                i7 = getRowSizeSecondary(i) - i5;
            } else if ((i11 == 0 && i10 == 16) || (i11 == 1 && absoluteGravity == 1)) {
                i7 = (getRowSizeSecondary(i) - i5) / 2;
            }
            i4 += i7;
        }
        if (this.mOrientation == 0) {
            i6 = i5 + i4;
        } else {
            int i12 = i5 + i4;
            int i13 = i4;
            i4 = i2;
            i2 = i13;
            int i14 = i12;
            i6 = i3;
            i3 = i14;
        }
        layoutDecoratedWithMargins(view, i2, i4, i3, i6);
        Rect rect = sTempRect;
        super.getDecoratedBoundsWithMargins(view, rect);
        ((LayoutParams) view.getLayoutParams()).setOpticalInsets(i2 - rect.left, i4 - rect.top, rect.right - i3, rect.bottom - i6);
        updateChildAlignments(view);
    }

    private void updateChildAlignments(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.getItemAlignmentFacet() == null) {
            layoutParams.setAlignX(this.mItemAlignment.horizontal.getAlignmentPosition(view));
            layoutParams.setAlignY(this.mItemAlignment.vertical.getAlignmentPosition(view));
            return;
        }
        layoutParams.calculateItemAlignments(this.mOrientation, view);
        if (this.mOrientation == 0) {
            layoutParams.setAlignY(this.mItemAlignment.vertical.getAlignmentPosition(view));
        } else {
            layoutParams.setAlignX(this.mItemAlignment.horizontal.getAlignmentPosition(view));
        }
    }

    private void updateChildAlignments() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            updateChildAlignments(getChildAt(i));
        }
    }

    /* access modifiers changed from: package-private */
    public void setExtraLayoutSpace(int i) {
        int i2 = this.mExtraLayoutSpace;
        if (i2 != i) {
            if (i2 >= 0) {
                this.mExtraLayoutSpace = i;
                requestLayout();
                return;
            }
            throw new IllegalArgumentException("ExtraLayoutSpace must >= 0");
        }
    }

    /* access modifiers changed from: package-private */
    public int getExtraLayoutSpace() {
        return this.mExtraLayoutSpace;
    }

    private void removeInvisibleViewsAtEnd() {
        int i = this.mFlag;
        if ((65600 & i) == 65536) {
            this.mGrid.removeInvisibleItemsAtEnd(this.mFocusPosition, (i & 262144) != 0 ? -this.mExtraLayoutSpace : this.mSizePrimary + this.mExtraLayoutSpace);
        }
    }

    private void removeInvisibleViewsAtFront() {
        int i = this.mFlag;
        if ((65600 & i) == 65536) {
            this.mGrid.removeInvisibleItemsAtFront(this.mFocusPosition, (i & 262144) != 0 ? this.mSizePrimary + this.mExtraLayoutSpace : -this.mExtraLayoutSpace);
        }
    }

    private boolean appendOneColumnVisibleItems() {
        return this.mGrid.appendOneColumnVisibleItems();
    }

    /* access modifiers changed from: package-private */
    public void slideIn() {
        int i = this.mFlag;
        if ((i & 64) != 0) {
            this.mFlag = i & -65;
            int i2 = this.mFocusPosition;
            if (i2 >= 0) {
                scrollToSelection(i2, this.mSubFocusPosition, true, this.mPrimaryScrollExtra);
            } else {
                this.mFlag = i & -193;
                requestLayout();
            }
            int i3 = this.mFlag;
            if ((i3 & 128) != 0) {
                this.mFlag = i3 & -129;
                if (this.mBaseGridView.getScrollState() != 0 || isSmoothScrolling()) {
                    this.mBaseGridView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                            if (i == 0) {
                                GridLayoutManager.this.mBaseGridView.removeOnScrollListener(this);
                                GridLayoutManager.this.requestLayout();
                            }
                        }
                    });
                } else {
                    requestLayout();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getSlideOutDistance() {
        int i;
        int i2;
        int right;
        if (this.mOrientation == 1) {
            i2 = -getHeight();
            if (getChildCount() <= 0 || (i = getChildAt(0).getTop()) >= 0) {
                return i2;
            }
        } else if ((this.mFlag & 262144) != 0) {
            int width = getWidth();
            if (getChildCount() <= 0 || (right = getChildAt(0).getRight()) <= width) {
                return width;
            }
            return right;
        } else {
            i2 = -getWidth();
            if (getChildCount() <= 0 || (i = getChildAt(0).getLeft()) >= 0) {
                return i2;
            }
        }
        return i2 + i;
    }

    /* access modifiers changed from: package-private */
    public boolean isSlidingChildViews() {
        return (this.mFlag & 64) != 0;
    }

    /* access modifiers changed from: package-private */
    public void slideOut() {
        int i = this.mFlag;
        if ((i & 64) == 0) {
            this.mFlag = i | 64;
            if (getChildCount() != 0) {
                if (this.mOrientation == 1) {
                    this.mBaseGridView.smoothScrollBy(0, getSlideOutDistance(), new AccelerateDecelerateInterpolator());
                } else {
                    this.mBaseGridView.smoothScrollBy(getSlideOutDistance(), 0, new AccelerateDecelerateInterpolator());
                }
            }
        }
    }

    private boolean prependOneColumnVisibleItems() {
        return this.mGrid.prependOneColumnVisibleItems();
    }

    private void appendVisibleItems() {
        this.mGrid.appendVisibleItems((this.mFlag & 262144) != 0 ? (-this.mExtraLayoutSpace) - this.mExtraLayoutSpaceInPreLayout : this.mSizePrimary + this.mExtraLayoutSpace + this.mExtraLayoutSpaceInPreLayout);
    }

    private void prependVisibleItems() {
        this.mGrid.prependVisibleItems((this.mFlag & 262144) != 0 ? this.mSizePrimary + this.mExtraLayoutSpace + this.mExtraLayoutSpaceInPreLayout : (-this.mExtraLayoutSpace) - this.mExtraLayoutSpaceInPreLayout);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b9 A[LOOP:3: B:26:0x00b9->B:29:0x00c7, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fastRelayout() {
        /*
            r13 = this;
            int r0 = r13.getChildCount()
            androidx.leanback.widget.Grid r1 = r13.mGrid
            int r1 = r1.getFirstVisibleIndex()
            int r2 = r13.mFlag
            r2 = r2 & -9
            r13.mFlag = r2
            r2 = 0
        L_0x0011:
            if (r2 >= r0) goto L_0x00d0
            android.view.View r3 = r13.getChildAt(r2)
            int r4 = r13.getAdapterPositionByView(r3)
            if (r1 == r4) goto L_0x001e
            goto L_0x007e
        L_0x001e:
            androidx.leanback.widget.Grid r4 = r13.mGrid
            androidx.leanback.widget.Grid$Location r4 = r4.getLocation(r1)
            if (r4 != 0) goto L_0x0027
            goto L_0x007e
        L_0x0027:
            int r5 = r4.row
            int r5 = r13.getRowStartSecondary(r5)
            androidx.leanback.widget.WindowAlignment r6 = r13.mWindowAlignment
            androidx.leanback.widget.WindowAlignment$Axis r6 = r6.secondAxis()
            int r6 = r6.getPaddingMin()
            int r5 = r5 + r6
            int r6 = r13.mScrollOffsetSecondary
            int r12 = r5 - r6
            int r10 = r13.getViewMin(r3)
            int r5 = r13.getViewPrimarySize(r3)
            android.view.ViewGroup$LayoutParams r6 = r3.getLayoutParams()
            androidx.leanback.widget.GridLayoutManager$LayoutParams r6 = (androidx.leanback.widget.GridLayoutManager.LayoutParams) r6
            boolean r6 = r6.viewNeedsUpdate()
            if (r6 == 0) goto L_0x0062
            int r6 = r13.mFlag
            r6 = r6 | 8
            r13.mFlag = r6
            androidx.recyclerview.widget.RecyclerView$Recycler r6 = r13.mRecycler
            r13.detachAndScrapView(r3, r6)
            android.view.View r3 = r13.getViewForPosition(r1)
            r13.addView(r3, r2)
        L_0x0062:
            r9 = r3
            r13.measureChild(r9)
            int r3 = r13.mOrientation
            if (r3 != 0) goto L_0x006f
            int r3 = r13.getDecoratedMeasuredWidthWithMargin(r9)
            goto L_0x0073
        L_0x006f:
            int r3 = r13.getDecoratedMeasuredHeightWithMargin(r9)
        L_0x0073:
            int r6 = r10 + r3
            r11 = r6
            int r8 = r4.row
            r7 = r13
            r7.layoutChild(r8, r9, r10, r11, r12)
            if (r5 == r3) goto L_0x00ca
        L_0x007e:
            androidx.leanback.widget.Grid r3 = r13.mGrid
            int r3 = r3.getLastVisibleIndex()
            int r0 = r0 + -1
        L_0x0086:
            if (r0 < r2) goto L_0x0094
            android.view.View r4 = r13.getChildAt(r0)
            androidx.recyclerview.widget.RecyclerView$Recycler r5 = r13.mRecycler
            r13.detachAndScrapView(r4, r5)
            int r0 = r0 + -1
            goto L_0x0086
        L_0x0094:
            androidx.leanback.widget.Grid r0 = r13.mGrid
            r0.invalidateItemsAfter(r1)
            int r0 = r13.mFlag
            r1 = 65536(0x10000, float:9.18355E-41)
            r0 = r0 & r1
            if (r0 == 0) goto L_0x00b9
            r13.appendVisibleItems()
            int r0 = r13.mFocusPosition
            if (r0 < 0) goto L_0x00d0
            if (r0 > r3) goto L_0x00d0
        L_0x00a9:
            androidx.leanback.widget.Grid r0 = r13.mGrid
            int r0 = r0.getLastVisibleIndex()
            int r1 = r13.mFocusPosition
            if (r0 >= r1) goto L_0x00d0
            androidx.leanback.widget.Grid r0 = r13.mGrid
            r0.appendOneColumnVisibleItems()
            goto L_0x00a9
        L_0x00b9:
            androidx.leanback.widget.Grid r0 = r13.mGrid
            boolean r0 = r0.appendOneColumnVisibleItems()
            if (r0 == 0) goto L_0x00d0
            androidx.leanback.widget.Grid r0 = r13.mGrid
            int r0 = r0.getLastVisibleIndex()
            if (r0 >= r3) goto L_0x00d0
            goto L_0x00b9
        L_0x00ca:
            int r2 = r2 + 1
            int r1 = r1 + 1
            goto L_0x0011
        L_0x00d0:
            r13.updateScrollLimits()
            r13.updateSecondaryScrollLimits()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.fastRelayout():void");
    }

    public void removeAndRecycleAllViews(RecyclerView.Recycler recycler) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            removeAndRecycleViewAt(childCount, recycler);
        }
    }

    private void focusToViewInLayout(boolean z, boolean z2, int i, int i2) {
        View findViewByPosition = findViewByPosition(this.mFocusPosition);
        if (findViewByPosition != null && z2) {
            scrollToView(findViewByPosition, false, i, i2);
        }
        if (findViewByPosition != null && z && !findViewByPosition.hasFocus()) {
            findViewByPosition.requestFocus();
        } else if (!z && !this.mBaseGridView.hasFocus()) {
            if (findViewByPosition == null || !findViewByPosition.hasFocusable()) {
                int childCount = getChildCount();
                int i3 = 0;
                while (true) {
                    if (i3 < childCount) {
                        findViewByPosition = getChildAt(i3);
                        if (findViewByPosition != null && findViewByPosition.hasFocusable()) {
                            this.mBaseGridView.focusableViewAvailable(findViewByPosition);
                            break;
                        }
                        i3++;
                    } else {
                        break;
                    }
                }
            } else {
                this.mBaseGridView.focusableViewAvailable(findViewByPosition);
            }
            if (z2 && findViewByPosition != null && findViewByPosition.hasFocus()) {
                scrollToView(findViewByPosition, false, i, i2);
            }
        }
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        OnLayoutCompleteListener onLayoutCompleteListener = this.mLayoutCompleteListener;
        if (onLayoutCompleteListener != null) {
            onLayoutCompleteListener.onLayoutCompleted(state);
        }
    }

    /* access modifiers changed from: package-private */
    public void updatePositionToRowMapInPostLayout() {
        Grid.Location location;
        this.mPositionToRowInPostLayout.clear();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int oldPosition = this.mBaseGridView.getChildViewHolder(getChildAt(i)).getOldPosition();
            if (oldPosition >= 0 && (location = this.mGrid.getLocation(oldPosition)) != null) {
                this.mPositionToRowInPostLayout.put(oldPosition, location.row);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void fillScrapViewsInPostLayout() {
        int i;
        List<RecyclerView.ViewHolder> scrapList = this.mRecycler.getScrapList();
        int size = scrapList.size();
        if (size != 0) {
            int[] iArr = this.mDisappearingPositions;
            if (iArr == null || size > iArr.length) {
                if (iArr == null) {
                    i = 16;
                } else {
                    i = iArr.length;
                }
                while (i < size) {
                    i <<= 1;
                }
                this.mDisappearingPositions = new int[i];
            }
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int adapterPosition = scrapList.get(i3).getAdapterPosition();
                if (adapterPosition >= 0) {
                    this.mDisappearingPositions[i2] = adapterPosition;
                    i2++;
                }
            }
            if (i2 > 0) {
                Arrays.sort(this.mDisappearingPositions, 0, i2);
                this.mGrid.fillDisappearingItems(this.mDisappearingPositions, i2, this.mPositionToRowInPostLayout);
            }
            this.mPositionToRowInPostLayout.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void updatePositionDeltaInPreLayout() {
        if (getChildCount() > 0) {
            this.mPositionDeltaInPreLayout = this.mGrid.getFirstVisibleIndex() - ((LayoutParams) getChildAt(0).getLayoutParams()).getViewLayoutPosition();
        } else {
            this.mPositionDeltaInPreLayout = 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:86:0x0172 A[LOOP:1: B:86:0x0172->B:89:0x017c, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayoutChildren(androidx.recyclerview.widget.RecyclerView.Recycler r14, androidx.recyclerview.widget.RecyclerView.State r15) {
        /*
            r13 = this;
            int r0 = r13.mNumRows
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            int r0 = r15.getItemCount()
            if (r0 >= 0) goto L_0x000c
            return
        L_0x000c:
            int r0 = r13.mFlag
            r0 = r0 & 64
            if (r0 == 0) goto L_0x001f
            int r0 = r13.getChildCount()
            if (r0 <= 0) goto L_0x001f
            int r14 = r13.mFlag
            r14 = r14 | 128(0x80, float:1.794E-43)
            r13.mFlag = r14
            return
        L_0x001f:
            int r0 = r13.mFlag
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 != 0) goto L_0x002c
            r13.discardLayoutInfo()
            r13.removeAndRecycleAllViews(r14)
            return
        L_0x002c:
            r0 = r0 & -4
            r1 = 1
            r0 = r0 | r1
            r13.mFlag = r0
            r13.saveContext(r14, r15)
            boolean r14 = r15.isPreLayout()
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = 0
            if (r14 == 0) goto L_0x00d8
            r13.updatePositionDeltaInPreLayout()
            int r14 = r13.getChildCount()
            androidx.leanback.widget.Grid r15 = r13.mGrid
            if (r15 == 0) goto L_0x00ce
            if (r14 <= 0) goto L_0x00ce
            androidx.leanback.widget.BaseGridView r15 = r13.mBaseGridView
            android.view.View r1 = r13.getChildAt(r2)
            androidx.recyclerview.widget.RecyclerView$ViewHolder r15 = r15.getChildViewHolder(r1)
            int r15 = r15.getOldPosition()
            androidx.leanback.widget.BaseGridView r1 = r13.mBaseGridView
            int r3 = r14 + -1
            android.view.View r3 = r13.getChildAt(r3)
            androidx.recyclerview.widget.RecyclerView$ViewHolder r1 = r1.getChildViewHolder(r3)
            int r1 = r1.getOldPosition()
            r3 = 2147483647(0x7fffffff, float:NaN)
        L_0x006c:
            if (r2 >= r14) goto L_0x00c3
            android.view.View r4 = r13.getChildAt(r2)
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            androidx.leanback.widget.GridLayoutManager$LayoutParams r5 = (androidx.leanback.widget.GridLayoutManager.LayoutParams) r5
            androidx.leanback.widget.BaseGridView r6 = r13.mBaseGridView
            int r6 = r6.getChildAdapterPosition(r4)
            boolean r7 = r5.isItemChanged()
            if (r7 != 0) goto L_0x00b0
            boolean r7 = r5.isItemRemoved()
            if (r7 != 0) goto L_0x00b0
            boolean r7 = r4.isLayoutRequested()
            if (r7 != 0) goto L_0x00b0
            boolean r7 = r4.hasFocus()
            if (r7 != 0) goto L_0x009e
            int r7 = r13.mFocusPosition
            int r8 = r5.getViewAdapterPosition()
            if (r7 == r8) goto L_0x00b0
        L_0x009e:
            boolean r7 = r4.hasFocus()
            if (r7 == 0) goto L_0x00ac
            int r7 = r13.mFocusPosition
            int r5 = r5.getViewAdapterPosition()
            if (r7 != r5) goto L_0x00b0
        L_0x00ac:
            if (r6 < r15) goto L_0x00b0
            if (r6 <= r1) goto L_0x00c0
        L_0x00b0:
            int r5 = r13.getViewMin(r4)
            int r3 = java.lang.Math.min(r3, r5)
            int r4 = r13.getViewMax(r4)
            int r0 = java.lang.Math.max(r0, r4)
        L_0x00c0:
            int r2 = r2 + 1
            goto L_0x006c
        L_0x00c3:
            if (r0 <= r3) goto L_0x00c8
            int r0 = r0 - r3
            r13.mExtraLayoutSpaceInPreLayout = r0
        L_0x00c8:
            r13.appendVisibleItems()
            r13.prependVisibleItems()
        L_0x00ce:
            int r14 = r13.mFlag
            r14 = r14 & -4
            r13.mFlag = r14
            r13.leaveContext()
            return
        L_0x00d8:
            boolean r14 = r15.willRunPredictiveAnimations()
            if (r14 == 0) goto L_0x00e1
            r13.updatePositionToRowMapInPostLayout()
        L_0x00e1:
            boolean r14 = r13.isSmoothScrolling()
            if (r14 != 0) goto L_0x00ec
            int r14 = r13.mFocusScrollStrategy
            if (r14 != 0) goto L_0x00ec
            goto L_0x00ed
        L_0x00ec:
            r1 = 0
        L_0x00ed:
            int r14 = r13.mFocusPosition
            r3 = -1
            if (r14 == r3) goto L_0x00fb
            int r4 = r13.mFocusPositionOffset
            if (r4 == r0) goto L_0x00fb
            int r14 = r14 + r4
            r13.mFocusPosition = r14
            r13.mSubFocusPosition = r2
        L_0x00fb:
            r13.mFocusPositionOffset = r2
            int r14 = r13.mFocusPosition
            android.view.View r14 = r13.findViewByPosition(r14)
            int r0 = r13.mFocusPosition
            int r4 = r13.mSubFocusPosition
            androidx.leanback.widget.BaseGridView r5 = r13.mBaseGridView
            boolean r5 = r5.hasFocus()
            androidx.leanback.widget.Grid r6 = r13.mGrid
            if (r6 == 0) goto L_0x0116
            int r6 = r6.getFirstVisibleIndex()
            goto L_0x0117
        L_0x0116:
            r6 = -1
        L_0x0117:
            androidx.leanback.widget.Grid r7 = r13.mGrid
            if (r7 == 0) goto L_0x0120
            int r7 = r7.getLastVisibleIndex()
            goto L_0x0121
        L_0x0120:
            r7 = -1
        L_0x0121:
            int r8 = r13.mOrientation
            if (r8 != 0) goto L_0x012e
            int r8 = r15.getRemainingScrollHorizontal()
            int r9 = r15.getRemainingScrollVertical()
            goto L_0x0136
        L_0x012e:
            int r9 = r15.getRemainingScrollHorizontal()
            int r8 = r15.getRemainingScrollVertical()
        L_0x0136:
            boolean r10 = r13.layoutInit()
            r11 = 16
            if (r10 == 0) goto L_0x014f
            int r2 = r13.mFlag
            r2 = r2 | 4
            r13.mFlag = r2
            androidx.leanback.widget.Grid r2 = r13.mGrid
            int r3 = r13.mFocusPosition
            r2.setStart(r3)
            r13.fastRelayout()
            goto L_0x017f
        L_0x014f:
            int r10 = r13.mFlag
            r12 = r10 & -5
            r13.mFlag = r12
            r10 = r10 & -21
            if (r5 == 0) goto L_0x015b
            r2 = 16
        L_0x015b:
            r2 = r2 | r10
            r13.mFlag = r2
            if (r1 == 0) goto L_0x016b
            if (r6 < 0) goto L_0x0168
            int r2 = r13.mFocusPosition
            if (r2 > r7) goto L_0x0168
            if (r2 >= r6) goto L_0x016b
        L_0x0168:
            int r6 = r13.mFocusPosition
            r7 = r6
        L_0x016b:
            androidx.leanback.widget.Grid r2 = r13.mGrid
            r2.setStart(r6)
            if (r7 == r3) goto L_0x017f
        L_0x0172:
            boolean r2 = r13.appendOneColumnVisibleItems()
            if (r2 == 0) goto L_0x017f
            android.view.View r2 = r13.findViewByPosition(r7)
            if (r2 != 0) goto L_0x017f
            goto L_0x0172
        L_0x017f:
            r13.updateScrollLimits()
            androidx.leanback.widget.Grid r2 = r13.mGrid
            int r2 = r2.getFirstVisibleIndex()
            androidx.leanback.widget.Grid r3 = r13.mGrid
            int r3 = r3.getLastVisibleIndex()
            int r6 = -r8
            int r7 = -r9
            r13.focusToViewInLayout(r5, r1, r6, r7)
            r13.appendVisibleItems()
            r13.prependVisibleItems()
            androidx.leanback.widget.Grid r6 = r13.mGrid
            int r6 = r6.getFirstVisibleIndex()
            if (r6 != r2) goto L_0x017f
            androidx.leanback.widget.Grid r2 = r13.mGrid
            int r2 = r2.getLastVisibleIndex()
            if (r2 != r3) goto L_0x017f
            r13.removeInvisibleViewsAtFront()
            r13.removeInvisibleViewsAtEnd()
            boolean r15 = r15.willRunPredictiveAnimations()
            if (r15 == 0) goto L_0x01b8
            r13.fillScrapViewsInPostLayout()
        L_0x01b8:
            int r15 = r13.mFlag
            r1 = r15 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L_0x01c3
            r15 = r15 & -1025(0xfffffffffffffbff, float:NaN)
            r13.mFlag = r15
            goto L_0x01c6
        L_0x01c3:
            r13.updateRowSecondarySizeRefresh()
        L_0x01c6:
            int r15 = r13.mFlag
            r15 = r15 & 4
            if (r15 == 0) goto L_0x01e4
            int r15 = r13.mFocusPosition
            if (r15 != r0) goto L_0x01e0
            int r0 = r13.mSubFocusPosition
            if (r0 != r4) goto L_0x01e0
            android.view.View r15 = r13.findViewByPosition(r15)
            if (r15 != r14) goto L_0x01e0
            int r14 = r13.mFlag
            r14 = r14 & 8
            if (r14 == 0) goto L_0x01e4
        L_0x01e0:
            r13.dispatchChildSelected()
            goto L_0x01ed
        L_0x01e4:
            int r14 = r13.mFlag
            r14 = r14 & 20
            if (r14 != r11) goto L_0x01ed
            r13.dispatchChildSelected()
        L_0x01ed:
            r13.dispatchChildSelectedAndPositioned()
            int r14 = r13.mFlag
            r14 = r14 & 64
            if (r14 == 0) goto L_0x01fd
            int r14 = r13.getSlideOutDistance()
            r13.scrollDirectionPrimary(r14)
        L_0x01fd:
            int r14 = r13.mFlag
            r14 = r14 & -4
            r13.mFlag = r14
            r13.leaveContext()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.onLayoutChildren(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State):void");
    }

    private void offsetChildrenSecondary(int i) {
        int childCount = getChildCount();
        int i2 = 0;
        if (this.mOrientation == 0) {
            while (i2 < childCount) {
                getChildAt(i2).offsetTopAndBottom(i);
                i2++;
            }
            return;
        }
        while (i2 < childCount) {
            getChildAt(i2).offsetLeftAndRight(i);
            i2++;
        }
    }

    private void offsetChildrenPrimary(int i) {
        int childCount = getChildCount();
        int i2 = 0;
        if (this.mOrientation == 1) {
            while (i2 < childCount) {
                getChildAt(i2).offsetTopAndBottom(i);
                i2++;
            }
            return;
        }
        while (i2 < childCount) {
            getChildAt(i2).offsetLeftAndRight(i);
            i2++;
        }
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        if ((this.mFlag & 512) == 0 || !hasDoneFirstLayout()) {
            return 0;
        }
        saveContext(recycler, state);
        this.mFlag = (this.mFlag & -4) | 2;
        if (this.mOrientation == 0) {
            i2 = scrollDirectionPrimary(i);
        } else {
            i2 = scrollDirectionSecondary(i);
        }
        leaveContext();
        this.mFlag &= -4;
        return i2;
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        if ((this.mFlag & 512) == 0 || !hasDoneFirstLayout()) {
            return 0;
        }
        this.mFlag = (this.mFlag & -4) | 2;
        saveContext(recycler, state);
        if (this.mOrientation == 1) {
            i2 = scrollDirectionPrimary(i);
        } else {
            i2 = scrollDirectionSecondary(i);
        }
        leaveContext();
        this.mFlag &= -4;
        return i2;
    }

    private int scrollDirectionPrimary(int i) {
        int i2;
        int i3 = this.mFlag;
        boolean z = true;
        if ((i3 & 64) == 0 && (i3 & 3) != 1 && (i <= 0 ? !(i >= 0 || this.mWindowAlignment.mainAxis().isMinUnknown() || i >= (i2 = this.mWindowAlignment.mainAxis().getMinScroll())) : !(this.mWindowAlignment.mainAxis().isMaxUnknown() || i <= (i2 = this.mWindowAlignment.mainAxis().getMaxScroll())))) {
            i = i2;
        }
        if (i == 0) {
            return 0;
        }
        offsetChildrenPrimary(-i);
        if ((this.mFlag & 3) == 1) {
            updateScrollLimits();
            return i;
        }
        int childCount = getChildCount();
        if ((this.mFlag & 262144) == 0 ? i >= 0 : i <= 0) {
            appendVisibleItems();
        } else {
            prependVisibleItems();
        }
        boolean z2 = getChildCount() > childCount;
        int childCount2 = getChildCount();
        if ((262144 & this.mFlag) == 0 ? i >= 0 : i <= 0) {
            removeInvisibleViewsAtFront();
        } else {
            removeInvisibleViewsAtEnd();
        }
        if (getChildCount() >= childCount2) {
            z = false;
        }
        if (z2 || z) {
            updateRowSecondarySizeRefresh();
        }
        this.mBaseGridView.invalidate();
        updateScrollLimits();
        return i;
    }

    private int scrollDirectionSecondary(int i) {
        if (i == 0) {
            return 0;
        }
        offsetChildrenSecondary(-i);
        this.mScrollOffsetSecondary += i;
        updateSecondaryScrollLimits();
        this.mBaseGridView.invalidate();
        return i;
    }

    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        try {
            saveContext((RecyclerView.Recycler) null, state);
            if (this.mOrientation != 0) {
                i = i2;
            }
            if (getChildCount() != 0) {
                if (i != 0) {
                    this.mGrid.collectAdjacentPrefetchPositions(i < 0 ? -this.mExtraLayoutSpace : this.mSizePrimary + this.mExtraLayoutSpace, i, layoutPrefetchRegistry);
                    leaveContext();
                }
            }
        } finally {
            leaveContext();
        }
    }

    public void collectInitialPrefetchPositions(int i, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2 = this.mBaseGridView.mInitialPrefetchItemCount;
        if (i != 0 && i2 != 0) {
            int max = Math.max(0, Math.min(this.mFocusPosition - ((i2 - 1) / 2), i - i2));
            int i3 = max;
            while (i3 < i && i3 < max + i2) {
                layoutPrefetchRegistry.addPosition(i3, 0);
                i3++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateScrollLimits() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        if (this.mState.getItemCount() != 0) {
            if ((this.mFlag & 262144) == 0) {
                i4 = this.mGrid.getLastVisibleIndex();
                i3 = this.mState.getItemCount() - 1;
                i = this.mGrid.getFirstVisibleIndex();
                i2 = 0;
            } else {
                i4 = this.mGrid.getFirstVisibleIndex();
                i = this.mGrid.getLastVisibleIndex();
                i2 = this.mState.getItemCount() - 1;
                i3 = 0;
            }
            if (i4 >= 0 && i >= 0) {
                boolean z = i4 == i3;
                boolean z2 = i == i2;
                if (z || !this.mWindowAlignment.mainAxis().isMaxUnknown() || z2 || !this.mWindowAlignment.mainAxis().isMinUnknown()) {
                    if (z) {
                        i6 = this.mGrid.findRowMax(true, sTwoInts);
                        View findViewByPosition = findViewByPosition(sTwoInts[1]);
                        i5 = getViewCenter(findViewByPosition);
                        int[] alignMultiple = ((LayoutParams) findViewByPosition.getLayoutParams()).getAlignMultiple();
                        if (alignMultiple != null && alignMultiple.length > 0) {
                            i5 += alignMultiple[alignMultiple.length - 1] - alignMultiple[0];
                        }
                    } else {
                        i6 = Integer.MAX_VALUE;
                        i5 = Integer.MAX_VALUE;
                    }
                    if (z2) {
                        i8 = this.mGrid.findRowMin(false, sTwoInts);
                        i7 = getViewCenter(findViewByPosition(sTwoInts[1]));
                    } else {
                        i8 = Integer.MIN_VALUE;
                        i7 = Integer.MIN_VALUE;
                    }
                    this.mWindowAlignment.mainAxis().updateMinMax(i8, i6, i7, i5);
                }
            }
        }
    }

    private void updateSecondaryScrollLimits() {
        WindowAlignment.Axis secondAxis = this.mWindowAlignment.secondAxis();
        int paddingMin = secondAxis.getPaddingMin() - this.mScrollOffsetSecondary;
        int sizeSecondary = getSizeSecondary() + paddingMin;
        secondAxis.updateMinMax(paddingMin, sizeSecondary, paddingMin, sizeSecondary);
    }

    private void initScrollController() {
        this.mWindowAlignment.reset();
        this.mWindowAlignment.horizontal.setSize(getWidth());
        this.mWindowAlignment.vertical.setSize(getHeight());
        this.mWindowAlignment.horizontal.setPadding(getPaddingLeft(), getPaddingRight());
        this.mWindowAlignment.vertical.setPadding(getPaddingTop(), getPaddingBottom());
        this.mSizePrimary = this.mWindowAlignment.mainAxis().getSize();
        this.mScrollOffsetSecondary = 0;
    }

    private void updateScrollController() {
        this.mWindowAlignment.horizontal.setSize(getWidth());
        this.mWindowAlignment.vertical.setSize(getHeight());
        this.mWindowAlignment.horizontal.setPadding(getPaddingLeft(), getPaddingRight());
        this.mWindowAlignment.vertical.setPadding(getPaddingTop(), getPaddingBottom());
        this.mSizePrimary = this.mWindowAlignment.mainAxis().getSize();
    }

    public void scrollToPosition(int i) {
        setSelection(i, 0, false, 0);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        setSelection(i, 0, true, 0);
    }

    public void setSelection(int i, int i2) {
        setSelection(i, 0, false, i2);
    }

    public void setSelectionSmooth(int i) {
        setSelection(i, 0, true, 0);
    }

    public void setSelectionWithSub(int i, int i2, int i3) {
        setSelection(i, i2, false, i3);
    }

    public void setSelectionSmoothWithSub(int i, int i2) {
        setSelection(i, i2, true, 0);
    }

    public int getSelection() {
        return this.mFocusPosition;
    }

    public int getSubSelection() {
        return this.mSubFocusPosition;
    }

    public void setSelection(int i, int i2, boolean z, int i3) {
        if ((this.mFocusPosition != i && i != -1) || i2 != this.mSubFocusPosition || i3 != this.mPrimaryScrollExtra) {
            scrollToSelection(i, i2, z, i3);
        }
    }

    /* access modifiers changed from: package-private */
    public void scrollToSelection(int i, int i2, boolean z, int i3) {
        this.mPrimaryScrollExtra = i3;
        View findViewByPosition = findViewByPosition(i);
        boolean z2 = !isSmoothScrolling();
        if (!z2 || this.mBaseGridView.isLayoutRequested() || findViewByPosition == null || getAdapterPositionByView(findViewByPosition) != i) {
            int i4 = this.mFlag;
            if ((i4 & 512) == 0 || (i4 & 64) != 0) {
                this.mFocusPosition = i;
                this.mSubFocusPosition = i2;
                this.mFocusPositionOffset = Integer.MIN_VALUE;
            } else if (!z || this.mBaseGridView.isLayoutRequested()) {
                if (!z2) {
                    skipSmoothScrollerOnStopInternal();
                    this.mBaseGridView.stopScroll();
                }
                if (this.mBaseGridView.isLayoutRequested() || findViewByPosition == null || getAdapterPositionByView(findViewByPosition) != i) {
                    this.mFocusPosition = i;
                    this.mSubFocusPosition = i2;
                    this.mFocusPositionOffset = Integer.MIN_VALUE;
                    this.mFlag |= 256;
                    requestLayout();
                    return;
                }
                this.mFlag |= 32;
                scrollToView(findViewByPosition, z);
                this.mFlag &= -33;
            } else {
                this.mFocusPosition = i;
                this.mSubFocusPosition = i2;
                this.mFocusPositionOffset = Integer.MIN_VALUE;
                if (!hasDoneFirstLayout()) {
                    Log.w(getTag(), "setSelectionSmooth should not be called before first layout pass");
                    return;
                }
                int startPositionSmoothScroller = startPositionSmoothScroller(i);
                if (startPositionSmoothScroller != this.mFocusPosition) {
                    this.mFocusPosition = startPositionSmoothScroller;
                    this.mSubFocusPosition = 0;
                }
            }
        } else {
            this.mFlag |= 32;
            scrollToView(findViewByPosition, z);
            this.mFlag &= -33;
        }
    }

    /* access modifiers changed from: package-private */
    public int startPositionSmoothScroller(int i) {
        AnonymousClass4 r0 = new GridLinearSmoothScroller() {
            public PointF computeScrollVectorForPosition(int i) {
                if (getChildCount() == 0) {
                    return null;
                }
                GridLayoutManager gridLayoutManager = GridLayoutManager.this;
                int position = gridLayoutManager.getPosition(gridLayoutManager.getChildAt(0));
                int i2 = ((GridLayoutManager.this.mFlag & 262144) == 0 ? i >= position : i <= position) ? 1 : -1;
                if (GridLayoutManager.this.mOrientation == 0) {
                    return new PointF((float) i2, 0.0f);
                }
                return new PointF(0.0f, (float) i2);
            }
        };
        r0.setTargetPosition(i);
        startSmoothScroll(r0);
        return r0.getTargetPosition();
    }

    /* access modifiers changed from: package-private */
    public void skipSmoothScrollerOnStopInternal() {
        GridLinearSmoothScroller gridLinearSmoothScroller = this.mCurrentSmoothScroller;
        if (gridLinearSmoothScroller != null) {
            gridLinearSmoothScroller.mSkipOnStopInternal = true;
        }
    }

    public void startSmoothScroll(RecyclerView.SmoothScroller smoothScroller) {
        skipSmoothScrollerOnStopInternal();
        super.startSmoothScroll(smoothScroller);
        if (!smoothScroller.isRunning() || !(smoothScroller instanceof GridLinearSmoothScroller)) {
            this.mCurrentSmoothScroller = null;
            this.mPendingMoveSmoothScroller = null;
            return;
        }
        GridLinearSmoothScroller gridLinearSmoothScroller = (GridLinearSmoothScroller) smoothScroller;
        this.mCurrentSmoothScroller = gridLinearSmoothScroller;
        if (gridLinearSmoothScroller instanceof PendingMoveSmoothScroller) {
            this.mPendingMoveSmoothScroller = (PendingMoveSmoothScroller) gridLinearSmoothScroller;
        } else {
            this.mPendingMoveSmoothScroller = null;
        }
    }

    private void processPendingMovement(boolean z) {
        if (z) {
            if (hasCreatedLastItem()) {
                return;
            }
        } else if (hasCreatedFirstItem()) {
            return;
        }
        PendingMoveSmoothScroller pendingMoveSmoothScroller = this.mPendingMoveSmoothScroller;
        if (pendingMoveSmoothScroller == null) {
            this.mBaseGridView.stopScroll();
            boolean z2 = true;
            int i = z ? 1 : -1;
            if (this.mNumRows <= 1) {
                z2 = false;
            }
            PendingMoveSmoothScroller pendingMoveSmoothScroller2 = new PendingMoveSmoothScroller(i, z2);
            this.mFocusPositionOffset = 0;
            startSmoothScroll(pendingMoveSmoothScroller2);
        } else if (z) {
            pendingMoveSmoothScroller.increasePendingMoves();
        } else {
            pendingMoveSmoothScroller.decreasePendingMoves();
        }
    }

    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        Grid grid;
        int i3;
        if (!(this.mFocusPosition == -1 || (grid = this.mGrid) == null || grid.getFirstVisibleIndex() < 0 || (i3 = this.mFocusPositionOffset) == Integer.MIN_VALUE || i > this.mFocusPosition + i3)) {
            this.mFocusPositionOffset = i3 + i2;
        }
        this.mChildrenStates.clear();
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.mFocusPositionOffset = 0;
        this.mChildrenStates.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
        r1 = r4.mFocusPosition;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemsRemoved(androidx.recyclerview.widget.RecyclerView r5, int r6, int r7) {
        /*
            r4 = this;
            int r5 = r4.mFocusPosition
            r0 = -1
            if (r5 == r0) goto L_0x002a
            androidx.leanback.widget.Grid r5 = r4.mGrid
            if (r5 == 0) goto L_0x002a
            int r5 = r5.getFirstVisibleIndex()
            if (r5 < 0) goto L_0x002a
            int r5 = r4.mFocusPositionOffset
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r5 == r0) goto L_0x002a
            int r1 = r4.mFocusPosition
            int r2 = r1 + r5
            if (r6 > r2) goto L_0x002a
            int r3 = r6 + r7
            if (r3 <= r2) goto L_0x0027
            int r6 = r6 - r2
            int r5 = r5 + r6
            int r1 = r1 + r5
            r4.mFocusPosition = r1
            r4.mFocusPositionOffset = r0
            goto L_0x002a
        L_0x0027:
            int r5 = r5 - r7
            r4.mFocusPositionOffset = r5
        L_0x002a:
            androidx.leanback.widget.ViewsStateBundle r5 = r4.mChildrenStates
            r5.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.onItemsRemoved(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        int i4;
        int i5 = this.mFocusPosition;
        if (!(i5 == -1 || (i4 = this.mFocusPositionOffset) == Integer.MIN_VALUE)) {
            int i6 = i5 + i4;
            if (i <= i6 && i6 < i + i3) {
                this.mFocusPositionOffset = i4 + (i2 - i);
            } else if (i < i6 && i2 > i6 - i3) {
                this.mFocusPositionOffset = i4 - i3;
            } else if (i > i6 && i2 < i6) {
                this.mFocusPositionOffset = i4 + i3;
            }
        }
        this.mChildrenStates.clear();
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            this.mChildrenStates.remove(i);
            i++;
        }
    }

    public boolean onRequestChildFocus(RecyclerView recyclerView, View view, View view2) {
        if ((this.mFlag & 32768) == 0 && getAdapterPositionByView(view) != -1 && (this.mFlag & 35) == 0) {
            scrollToView(view, view2, true);
        }
        return true;
    }

    public void getViewSelectedOffsets(View view, int[] iArr) {
        if (this.mOrientation == 0) {
            iArr[0] = getPrimaryAlignedScrollDistance(view);
            iArr[1] = getSecondaryScrollDistance(view);
            return;
        }
        iArr[1] = getPrimaryAlignedScrollDistance(view);
        iArr[0] = getSecondaryScrollDistance(view);
    }

    private int getPrimaryAlignedScrollDistance(View view) {
        return this.mWindowAlignment.mainAxis().getScroll(getViewCenter(view));
    }

    private int getAdjustedPrimaryAlignedScrollDistance(int i, View view, View view2) {
        int subPositionByView = getSubPositionByView(view, view2);
        if (subPositionByView == 0) {
            return i;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return i + (layoutParams.getAlignMultiple()[subPositionByView] - layoutParams.getAlignMultiple()[0]);
    }

    private int getSecondaryScrollDistance(View view) {
        return this.mWindowAlignment.secondAxis().getScroll(getViewCenterSecondary(view));
    }

    /* access modifiers changed from: package-private */
    public void scrollToView(View view, boolean z) {
        scrollToView(view, view == null ? null : view.findFocus(), z);
    }

    /* access modifiers changed from: package-private */
    public void scrollToView(View view, boolean z, int i, int i2) {
        scrollToView(view, view == null ? null : view.findFocus(), z, i, i2);
    }

    private void scrollToView(View view, View view2, boolean z) {
        scrollToView(view, view2, z, 0, 0);
    }

    private void scrollToView(View view, View view2, boolean z, int i, int i2) {
        if ((this.mFlag & 64) == 0) {
            int adapterPositionByView = getAdapterPositionByView(view);
            int subPositionByView = getSubPositionByView(view, view2);
            if (!(adapterPositionByView == this.mFocusPosition && subPositionByView == this.mSubFocusPosition)) {
                this.mFocusPosition = adapterPositionByView;
                this.mSubFocusPosition = subPositionByView;
                this.mFocusPositionOffset = 0;
                if ((this.mFlag & 3) != 1) {
                    dispatchChildSelected();
                }
                if (this.mBaseGridView.isChildrenDrawingOrderEnabledInternal()) {
                    this.mBaseGridView.invalidate();
                }
            }
            if (view != null) {
                if (!view.hasFocus() && this.mBaseGridView.hasFocus()) {
                    view.requestFocus();
                }
                if ((this.mFlag & 131072) == 0 && z) {
                    return;
                }
                if (getScrollPosition(view, view2, sTwoInts) || i != 0 || i2 != 0) {
                    int[] iArr = sTwoInts;
                    scrollGrid(iArr[0] + i, iArr[1] + i2, z);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getScrollPosition(View view, View view2, int[] iArr) {
        int i = this.mFocusScrollStrategy;
        if (i == 1 || i == 2) {
            return getNoneAlignedPosition(view, iArr);
        }
        return getAlignedPosition(view, view2, iArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009f, code lost:
        if (r2 != null) goto L_0x00a5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c3 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean getNoneAlignedPosition(android.view.View r13, int[] r14) {
        /*
            r12 = this;
            int r0 = r12.getAdapterPositionByView(r13)
            int r1 = r12.getViewMin(r13)
            int r2 = r12.getViewMax(r13)
            androidx.leanback.widget.WindowAlignment r3 = r12.mWindowAlignment
            androidx.leanback.widget.WindowAlignment$Axis r3 = r3.mainAxis()
            int r3 = r3.getPaddingMin()
            androidx.leanback.widget.WindowAlignment r4 = r12.mWindowAlignment
            androidx.leanback.widget.WindowAlignment$Axis r4 = r4.mainAxis()
            int r4 = r4.getClientSize()
            androidx.leanback.widget.Grid r5 = r12.mGrid
            int r5 = r5.getRowIndex(r0)
            r6 = 1
            r7 = 0
            r8 = 2
            r9 = 0
            if (r1 >= r3) goto L_0x006f
            int r1 = r12.mFocusScrollStrategy
            if (r1 != r8) goto L_0x006c
            r1 = r13
        L_0x0031:
            boolean r10 = r12.prependOneColumnVisibleItems()
            if (r10 == 0) goto L_0x0069
            androidx.leanback.widget.Grid r1 = r12.mGrid
            int r10 = r1.getFirstVisibleIndex()
            androidx.collection.CircularIntArray[] r1 = r1.getItemPositionsInRows(r10, r0)
            r1 = r1[r5]
            int r10 = r1.get(r7)
            android.view.View r10 = r12.findViewByPosition(r10)
            int r11 = r12.getViewMin(r10)
            int r11 = r2 - r11
            if (r11 <= r4) goto L_0x0067
            int r0 = r1.size()
            if (r0 <= r8) goto L_0x0064
            int r0 = r1.get(r8)
            android.view.View r0 = r12.findViewByPosition(r0)
            r2 = r9
            r9 = r0
            goto L_0x00a5
        L_0x0064:
            r2 = r9
            r9 = r10
            goto L_0x00a5
        L_0x0067:
            r1 = r10
            goto L_0x0031
        L_0x0069:
            r2 = r9
            r9 = r1
            goto L_0x00a5
        L_0x006c:
            r2 = r9
        L_0x006d:
            r9 = r13
            goto L_0x00a5
        L_0x006f:
            int r10 = r4 + r3
            if (r2 <= r10) goto L_0x00a4
            int r2 = r12.mFocusScrollStrategy
            if (r2 != r8) goto L_0x00a2
        L_0x0077:
            androidx.leanback.widget.Grid r2 = r12.mGrid
            int r8 = r2.getLastVisibleIndex()
            androidx.collection.CircularIntArray[] r2 = r2.getItemPositionsInRows(r0, r8)
            r2 = r2[r5]
            int r8 = r2.size()
            int r8 = r8 - r6
            int r2 = r2.get(r8)
            android.view.View r2 = r12.findViewByPosition(r2)
            int r8 = r12.getViewMax(r2)
            int r8 = r8 - r1
            if (r8 <= r4) goto L_0x0099
            r2 = r9
            goto L_0x009f
        L_0x0099:
            boolean r8 = r12.appendOneColumnVisibleItems()
            if (r8 != 0) goto L_0x0077
        L_0x009f:
            if (r2 == 0) goto L_0x006d
            goto L_0x00a5
        L_0x00a2:
            r2 = r13
            goto L_0x00a5
        L_0x00a4:
            r2 = r9
        L_0x00a5:
            if (r9 == 0) goto L_0x00ad
            int r0 = r12.getViewMin(r9)
        L_0x00ab:
            int r0 = r0 - r3
            goto L_0x00b6
        L_0x00ad:
            if (r2 == 0) goto L_0x00b5
            int r0 = r12.getViewMax(r2)
            int r3 = r3 + r4
            goto L_0x00ab
        L_0x00b5:
            r0 = 0
        L_0x00b6:
            if (r9 == 0) goto L_0x00ba
            r13 = r9
            goto L_0x00bd
        L_0x00ba:
            if (r2 == 0) goto L_0x00bd
            r13 = r2
        L_0x00bd:
            int r13 = r12.getSecondaryScrollDistance(r13)
            if (r0 != 0) goto L_0x00c7
            if (r13 == 0) goto L_0x00c6
            goto L_0x00c7
        L_0x00c6:
            return r7
        L_0x00c7:
            r14[r7] = r0
            r14[r6] = r13
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.getNoneAlignedPosition(android.view.View, int[]):boolean");
    }

    private boolean getAlignedPosition(View view, View view2, int[] iArr) {
        int primaryAlignedScrollDistance = getPrimaryAlignedScrollDistance(view);
        if (view2 != null) {
            primaryAlignedScrollDistance = getAdjustedPrimaryAlignedScrollDistance(primaryAlignedScrollDistance, view, view2);
        }
        int secondaryScrollDistance = getSecondaryScrollDistance(view);
        int i = primaryAlignedScrollDistance + this.mPrimaryScrollExtra;
        if (i == 0 && secondaryScrollDistance == 0) {
            iArr[0] = 0;
            iArr[1] = 0;
            return false;
        }
        iArr[0] = i;
        iArr[1] = secondaryScrollDistance;
        return true;
    }

    private void scrollGrid(int i, int i2, boolean z) {
        if ((this.mFlag & 3) == 1) {
            scrollDirectionPrimary(i);
            scrollDirectionSecondary(i2);
            return;
        }
        if (this.mOrientation != 0) {
            int i3 = i2;
            i2 = i;
            i = i3;
        }
        if (z) {
            this.mBaseGridView.smoothScrollBy(i, i2);
            return;
        }
        this.mBaseGridView.scrollBy(i, i2);
        dispatchChildSelectedAndPositioned();
    }

    public void setPruneChild(boolean z) {
        int i = this.mFlag;
        int i2 = 65536;
        if (((i & 65536) != 0) != z) {
            int i3 = i & -65537;
            if (!z) {
                i2 = 0;
            }
            this.mFlag = i3 | i2;
            if (z) {
                requestLayout();
            }
        }
    }

    public boolean getPruneChild() {
        return (this.mFlag & 65536) != 0;
    }

    public void setScrollEnabled(boolean z) {
        int i;
        int i2 = this.mFlag;
        int i3 = 0;
        if (((i2 & 131072) != 0) != z) {
            int i4 = i2 & -131073;
            if (z) {
                i3 = 131072;
            }
            int i5 = i4 | i3;
            this.mFlag = i5;
            if ((i5 & 131072) != 0 && this.mFocusScrollStrategy == 0 && (i = this.mFocusPosition) != -1) {
                scrollToSelection(i, this.mSubFocusPosition, true, this.mPrimaryScrollExtra);
            }
        }
    }

    public boolean isScrollEnabled() {
        return (this.mFlag & 131072) != 0;
    }

    private int findImmediateChildIndex(View view) {
        View findContainingItemView;
        BaseGridView baseGridView = this.mBaseGridView;
        if (baseGridView == null || view == baseGridView || (findContainingItemView = findContainingItemView(view)) == null) {
            return -1;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i) == findContainingItemView) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        if (z) {
            int i2 = this.mFocusPosition;
            while (true) {
                View findViewByPosition = findViewByPosition(i2);
                if (findViewByPosition != null) {
                    if (findViewByPosition.getVisibility() != 0 || !findViewByPosition.hasFocusable()) {
                        i2++;
                    } else {
                        findViewByPosition.requestFocus();
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setFocusSearchDisabled(boolean z) {
        this.mFlag = (z ? 32768 : 0) | (this.mFlag & -32769);
    }

    /* access modifiers changed from: package-private */
    public boolean isFocusSearchDisabled() {
        return (this.mFlag & 32768) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x00cb A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onInterceptFocusSearch(android.view.View r8, int r9) {
        /*
            r7 = this;
            int r0 = r7.mFlag
            r1 = 32768(0x8000, float:4.5918E-41)
            r0 = r0 & r1
            if (r0 == 0) goto L_0x0009
            return r8
        L_0x0009:
            android.view.FocusFinder r0 = android.view.FocusFinder.getInstance()
            r1 = 0
            r2 = 2
            r3 = 1
            if (r9 == r2) goto L_0x001c
            if (r9 != r3) goto L_0x0015
            goto L_0x001c
        L_0x0015:
            androidx.leanback.widget.BaseGridView r4 = r7.mBaseGridView
            android.view.View r0 = r0.findNextFocus(r4, r8, r9)
            goto L_0x0055
        L_0x001c:
            boolean r4 = r7.canScrollVertically()
            if (r4 == 0) goto L_0x0030
            if (r9 != r2) goto L_0x0027
            r4 = 130(0x82, float:1.82E-43)
            goto L_0x0029
        L_0x0027:
            r4 = 33
        L_0x0029:
            androidx.leanback.widget.BaseGridView r5 = r7.mBaseGridView
            android.view.View r4 = r0.findNextFocus(r5, r8, r4)
            goto L_0x0031
        L_0x0030:
            r4 = 0
        L_0x0031:
            boolean r5 = r7.canScrollHorizontally()
            if (r5 == 0) goto L_0x0054
            int r4 = r7.getLayoutDirection()
            if (r4 != r3) goto L_0x003f
            r4 = 1
            goto L_0x0040
        L_0x003f:
            r4 = 0
        L_0x0040:
            if (r9 != r2) goto L_0x0044
            r5 = 1
            goto L_0x0045
        L_0x0044:
            r5 = 0
        L_0x0045:
            r4 = r4 ^ r5
            if (r4 == 0) goto L_0x004b
            r4 = 66
            goto L_0x004d
        L_0x004b:
            r4 = 17
        L_0x004d:
            androidx.leanback.widget.BaseGridView r5 = r7.mBaseGridView
            android.view.View r0 = r0.findNextFocus(r5, r8, r4)
            goto L_0x0055
        L_0x0054:
            r0 = r4
        L_0x0055:
            if (r0 == 0) goto L_0x0058
            return r0
        L_0x0058:
            androidx.leanback.widget.BaseGridView r4 = r7.mBaseGridView
            int r4 = r4.getDescendantFocusability()
            r5 = 393216(0x60000, float:5.51013E-40)
            if (r4 != r5) goto L_0x006d
            androidx.leanback.widget.BaseGridView r0 = r7.mBaseGridView
            android.view.ViewParent r0 = r0.getParent()
            android.view.View r8 = r0.focusSearch(r8, r9)
            return r8
        L_0x006d:
            int r4 = r7.getMovement(r9)
            androidx.leanback.widget.BaseGridView r5 = r7.mBaseGridView
            int r5 = r5.getScrollState()
            if (r5 == 0) goto L_0x007b
            r5 = 1
            goto L_0x007c
        L_0x007b:
            r5 = 0
        L_0x007c:
            r6 = 131072(0x20000, float:1.83671E-40)
            if (r4 != r3) goto L_0x0098
            if (r5 != 0) goto L_0x0088
            int r1 = r7.mFlag
            r1 = r1 & 4096(0x1000, float:5.74E-42)
            if (r1 != 0) goto L_0x0089
        L_0x0088:
            r0 = r8
        L_0x0089:
            int r1 = r7.mFlag
            r1 = r1 & r6
            if (r1 == 0) goto L_0x00c9
            boolean r1 = r7.hasCreatedLastItem()
            if (r1 != 0) goto L_0x00c9
            r7.processPendingMovement(r3)
            goto L_0x00c8
        L_0x0098:
            if (r4 != 0) goto L_0x00b2
            if (r5 != 0) goto L_0x00a2
            int r2 = r7.mFlag
            r2 = r2 & 2048(0x800, float:2.87E-42)
            if (r2 != 0) goto L_0x00a3
        L_0x00a2:
            r0 = r8
        L_0x00a3:
            int r2 = r7.mFlag
            r2 = r2 & r6
            if (r2 == 0) goto L_0x00c9
            boolean r2 = r7.hasCreatedFirstItem()
            if (r2 != 0) goto L_0x00c9
            r7.processPendingMovement(r1)
            goto L_0x00c8
        L_0x00b2:
            r1 = 3
            if (r4 != r1) goto L_0x00be
            if (r5 != 0) goto L_0x00c8
            int r1 = r7.mFlag
            r1 = r1 & 16384(0x4000, float:2.2959E-41)
            if (r1 != 0) goto L_0x00c9
            goto L_0x00c8
        L_0x00be:
            if (r4 != r2) goto L_0x00c9
            if (r5 != 0) goto L_0x00c8
            int r1 = r7.mFlag
            r1 = r1 & 8192(0x2000, float:1.14794E-41)
            if (r1 != 0) goto L_0x00c9
        L_0x00c8:
            r0 = r8
        L_0x00c9:
            if (r0 == 0) goto L_0x00cc
            return r0
        L_0x00cc:
            androidx.leanback.widget.BaseGridView r0 = r7.mBaseGridView
            android.view.ViewParent r0 = r0.getParent()
            android.view.View r9 = r0.focusSearch(r8, r9)
            if (r9 == 0) goto L_0x00d9
            return r9
        L_0x00d9:
            if (r8 == 0) goto L_0x00dc
            goto L_0x00de
        L_0x00dc:
            androidx.leanback.widget.BaseGridView r8 = r7.mBaseGridView
        L_0x00de:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.onInterceptFocusSearch(android.view.View, int):android.view.View");
    }

    /* access modifiers changed from: package-private */
    public boolean hasPreviousViewInSameRow(int i) {
        Grid grid = this.mGrid;
        if (!(grid == null || i == -1 || grid.getFirstVisibleIndex() < 0)) {
            if (this.mGrid.getFirstVisibleIndex() > 0) {
                return true;
            }
            int i2 = this.mGrid.getLocation(i).row;
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                int adapterPositionByIndex = getAdapterPositionByIndex(childCount);
                Grid.Location location = this.mGrid.getLocation(adapterPositionByIndex);
                if (location != null && location.row == i2 && adapterPositionByIndex < i) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
        View view;
        int i3;
        ArrayList<View> arrayList2 = arrayList;
        int i4 = i;
        int i5 = i2;
        if ((this.mFlag & 32768) != 0) {
            return true;
        }
        if (!recyclerView.hasFocus()) {
            int size = arrayList.size();
            if (this.mFocusScrollStrategy != 0) {
                int paddingMin = this.mWindowAlignment.mainAxis().getPaddingMin();
                int clientSize = this.mWindowAlignment.mainAxis().getClientSize() + paddingMin;
                int childCount = getChildCount();
                for (int i6 = 0; i6 < childCount; i6++) {
                    View childAt = getChildAt(i6);
                    if (childAt.getVisibility() == 0 && getViewMin(childAt) >= paddingMin && getViewMax(childAt) <= clientSize) {
                        childAt.addFocusables(arrayList2, i4, i5);
                    }
                }
                if (arrayList.size() == size) {
                    int childCount2 = getChildCount();
                    for (int i7 = 0; i7 < childCount2; i7++) {
                        View childAt2 = getChildAt(i7);
                        if (childAt2.getVisibility() == 0) {
                            childAt2.addFocusables(arrayList2, i4, i5);
                        }
                    }
                }
            } else {
                View findViewByPosition = findViewByPosition(this.mFocusPosition);
                if (findViewByPosition != null) {
                    findViewByPosition.addFocusables(arrayList2, i4, i5);
                }
            }
            if (arrayList.size() == size && recyclerView.isFocusable()) {
                arrayList2.add(recyclerView);
            }
        } else if (this.mPendingMoveSmoothScroller != null) {
            return true;
        } else {
            int movement = getMovement(i4);
            int findImmediateChildIndex = findImmediateChildIndex(recyclerView.findFocus());
            int adapterPositionByIndex = getAdapterPositionByIndex(findImmediateChildIndex);
            if (adapterPositionByIndex == -1) {
                view = null;
            } else {
                view = findViewByPosition(adapterPositionByIndex);
            }
            if (view != null) {
                view.addFocusables(arrayList2, i4, i5);
            }
            if (this.mGrid == null || getChildCount() == 0) {
                return true;
            }
            if ((movement == 3 || movement == 2) && this.mGrid.getNumRows() <= 1) {
                return true;
            }
            Grid grid = this.mGrid;
            int i8 = (grid == null || view == null) ? -1 : grid.getLocation(adapterPositionByIndex).row;
            int size2 = arrayList.size();
            int i9 = (movement == 1 || movement == 3) ? 1 : -1;
            int childCount3 = i9 > 0 ? getChildCount() - 1 : 0;
            if (findImmediateChildIndex == -1) {
                i3 = i9 > 0 ? 0 : getChildCount() - 1;
            } else {
                i3 = findImmediateChildIndex + i9;
            }
            int i10 = i3;
            while (true) {
                if (i9 <= 0) {
                    if (i10 < childCount3) {
                        break;
                    }
                } else if (i10 > childCount3) {
                    break;
                }
                View childAt3 = getChildAt(i10);
                if (childAt3.getVisibility() == 0 && childAt3.hasFocusable()) {
                    if (view == null) {
                        childAt3.addFocusables(arrayList2, i4, i5);
                        if (arrayList.size() > size2) {
                            break;
                        }
                    } else {
                        int adapterPositionByIndex2 = getAdapterPositionByIndex(i10);
                        Grid.Location location = this.mGrid.getLocation(adapterPositionByIndex2);
                        if (location == null) {
                            continue;
                        } else if (movement == 1) {
                            if (location.row == i8 && adapterPositionByIndex2 > adapterPositionByIndex) {
                                childAt3.addFocusables(arrayList2, i4, i5);
                                if (arrayList.size() > size2) {
                                    break;
                                }
                            }
                        } else if (movement == 0) {
                            if (location.row == i8 && adapterPositionByIndex2 < adapterPositionByIndex) {
                                childAt3.addFocusables(arrayList2, i4, i5);
                                if (arrayList.size() > size2) {
                                    break;
                                }
                            }
                        } else if (movement == 3) {
                            if (location.row == i8) {
                                continue;
                            } else if (location.row < i8) {
                                break;
                            } else {
                                childAt3.addFocusables(arrayList2, i4, i5);
                            }
                        } else if (movement == 2 && location.row != i8) {
                            if (location.row > i8) {
                                break;
                            }
                            childAt3.addFocusables(arrayList2, i4, i5);
                        }
                    }
                }
                i10 += i9;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean hasCreatedLastItem() {
        int itemCount = getItemCount();
        if (itemCount == 0 || this.mBaseGridView.findViewHolderForAdapterPosition(itemCount - 1) != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean hasCreatedFirstItem() {
        return getItemCount() == 0 || this.mBaseGridView.findViewHolderForAdapterPosition(0) != null;
    }

    /* access modifiers changed from: package-private */
    public boolean isItemFullyVisible(int i) {
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mBaseGridView.findViewHolderForAdapterPosition(i);
        if (findViewHolderForAdapterPosition != null && findViewHolderForAdapterPosition.itemView.getLeft() >= 0 && findViewHolderForAdapterPosition.itemView.getRight() <= this.mBaseGridView.getWidth() && findViewHolderForAdapterPosition.itemView.getTop() >= 0 && findViewHolderForAdapterPosition.itemView.getBottom() <= this.mBaseGridView.getHeight()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean canScrollTo(View view) {
        return view.getVisibility() == 0 && (!hasFocus() || view.hasFocusable());
    }

    /* access modifiers changed from: package-private */
    public boolean gridOnRequestFocusInDescendants(RecyclerView recyclerView, int i, Rect rect) {
        int i2 = this.mFocusScrollStrategy;
        if (i2 == 1 || i2 == 2) {
            return gridOnRequestFocusInDescendantsUnaligned(recyclerView, i, rect);
        }
        return gridOnRequestFocusInDescendantsAligned(recyclerView, i, rect);
    }

    private boolean gridOnRequestFocusInDescendantsAligned(RecyclerView recyclerView, int i, Rect rect) {
        View findViewByPosition = findViewByPosition(this.mFocusPosition);
        if (findViewByPosition != null) {
            return findViewByPosition.requestFocus(i, rect);
        }
        return false;
    }

    private boolean gridOnRequestFocusInDescendantsUnaligned(RecyclerView recyclerView, int i, Rect rect) {
        int i2;
        int i3;
        int i4;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i4 = childCount;
            i2 = 0;
            i3 = 1;
        } else {
            i2 = childCount - 1;
            i4 = -1;
            i3 = -1;
        }
        int paddingMin = this.mWindowAlignment.mainAxis().getPaddingMin();
        int clientSize = this.mWindowAlignment.mainAxis().getClientSize() + paddingMin;
        while (i2 != i4) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && getViewMin(childAt) >= paddingMin && getViewMax(childAt) <= clientSize && childAt.requestFocus(i, rect)) {
                return true;
            }
            i2 += i3;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0023 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0038 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getMovement(int r10) {
        /*
            r9 = this;
            int r0 = r9.mOrientation
            r1 = 130(0x82, float:1.82E-43)
            r2 = 66
            r3 = 33
            r4 = 0
            r5 = 3
            r6 = 2
            r7 = 1
            r8 = 17
            if (r0 != 0) goto L_0x002b
            r0 = 262144(0x40000, float:3.67342E-40)
            if (r10 == r8) goto L_0x0025
            if (r10 == r3) goto L_0x0023
            if (r10 == r2) goto L_0x001d
            if (r10 == r1) goto L_0x001b
            goto L_0x0046
        L_0x001b:
            r4 = 3
            goto L_0x0048
        L_0x001d:
            int r10 = r9.mFlag
            r10 = r10 & r0
            if (r10 != 0) goto L_0x0048
            goto L_0x0038
        L_0x0023:
            r4 = 2
            goto L_0x0048
        L_0x0025:
            int r10 = r9.mFlag
            r10 = r10 & r0
            if (r10 != 0) goto L_0x0038
            goto L_0x0048
        L_0x002b:
            if (r0 != r7) goto L_0x0046
            r0 = 524288(0x80000, float:7.34684E-40)
            if (r10 == r8) goto L_0x0040
            if (r10 == r3) goto L_0x0048
            if (r10 == r2) goto L_0x003a
            if (r10 == r1) goto L_0x0038
            goto L_0x0046
        L_0x0038:
            r4 = 1
            goto L_0x0048
        L_0x003a:
            int r10 = r9.mFlag
            r10 = r10 & r0
            if (r10 != 0) goto L_0x0023
            goto L_0x001b
        L_0x0040:
            int r10 = r9.mFlag
            r10 = r10 & r0
            if (r10 != 0) goto L_0x001b
            goto L_0x0023
        L_0x0046:
            r4 = 17
        L_0x0048:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.getMovement(int):int");
    }

    /* access modifiers changed from: package-private */
    public int getChildDrawingOrder(RecyclerView recyclerView, int i, int i2) {
        int indexOfChild;
        View findViewByPosition = findViewByPosition(this.mFocusPosition);
        if (findViewByPosition != null && i2 >= (indexOfChild = recyclerView.indexOfChild(findViewByPosition))) {
            return i2 < i + -1 ? ((indexOfChild + i) - 1) - i2 : indexOfChild;
        }
        return i2;
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        if (adapter != null) {
            discardLayoutInfo();
            this.mFocusPosition = -1;
            this.mFocusPositionOffset = 0;
            this.mChildrenStates.clear();
        }
        if (adapter2 instanceof FacetProviderAdapter) {
            this.mFacetProviderAdapter = (FacetProviderAdapter) adapter2;
        } else {
            this.mFacetProviderAdapter = null;
        }
        super.onAdapterChanged(adapter, adapter2);
    }

    private void discardLayoutInfo() {
        this.mGrid = null;
        this.mRowSizeSecondary = null;
        this.mFlag &= -1025;
    }

    public void setLayoutEnabled(boolean z) {
        int i = this.mFlag;
        int i2 = 0;
        if (((i & 512) != 0) != z) {
            int i3 = i & -513;
            if (z) {
                i2 = 512;
            }
            this.mFlag = i3 | i2;
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void setChildrenVisibility(int i) {
        this.mChildVisibility = i;
        if (i != -1) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                getChildAt(i2).setVisibility(this.mChildVisibility);
            }
        }
    }

    static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        Bundle childStates = Bundle.EMPTY;
        int index;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.index);
            parcel.writeBundle(this.childStates);
        }

        SavedState(Parcel parcel) {
            this.index = parcel.readInt();
            this.childStates = parcel.readBundle(GridLayoutManager.class.getClassLoader());
        }

        SavedState() {
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.index = getSelection();
        Bundle saveAsBundle = this.mChildrenStates.saveAsBundle();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int adapterPositionByView = getAdapterPositionByView(childAt);
            if (adapterPositionByView != -1) {
                saveAsBundle = this.mChildrenStates.saveOnScreenView(saveAsBundle, childAt, adapterPositionByView);
            }
        }
        savedState.childStates = saveAsBundle;
        return savedState;
    }

    /* access modifiers changed from: package-private */
    public void onChildRecycled(RecyclerView.ViewHolder viewHolder) {
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition != -1) {
            this.mChildrenStates.saveOffscreenView(viewHolder.itemView, adapterPosition);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mFocusPosition = savedState.index;
            this.mFocusPositionOffset = 0;
            this.mChildrenStates.loadFromBundle(savedState.childStates);
            this.mFlag |= 256;
            requestLayout();
        }
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Grid grid;
        if (this.mOrientation != 0 || (grid = this.mGrid) == null) {
            return super.getRowCountForAccessibility(recycler, state);
        }
        return grid.getNumRows();
    }

    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Grid grid;
        if (this.mOrientation != 1 || (grid = this.mGrid) == null) {
            return super.getColumnCountForAccessibility(recycler, state);
        }
        return grid.getNumRows();
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (this.mGrid != null && (layoutParams instanceof LayoutParams)) {
            int viewAdapterPosition = ((LayoutParams) layoutParams).getViewAdapterPosition();
            int rowIndex = viewAdapterPosition >= 0 ? this.mGrid.getRowIndex(viewAdapterPosition) : -1;
            if (rowIndex >= 0) {
                int numRows = viewAdapterPosition / this.mGrid.getNumRows();
                if (this.mOrientation == 0) {
                    accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(rowIndex, 1, numRows, 1, false, false));
                } else {
                    accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(numRows, 1, rowIndex, 1, false, false));
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        if (r5 != false) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        if (r5 != false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004b, code lost:
        if (r7 == androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN.getId()) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        r7 = 4096;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean performAccessibilityAction(androidx.recyclerview.widget.RecyclerView.Recycler r5, androidx.recyclerview.widget.RecyclerView.State r6, int r7, android.os.Bundle r8) {
        /*
            r4 = this;
            boolean r8 = r4.isScrollEnabled()
            r0 = 1
            if (r8 != 0) goto L_0x0008
            return r0
        L_0x0008:
            r4.saveContext(r5, r6)
            int r5 = r4.mFlag
            r6 = 262144(0x40000, float:3.67342E-40)
            r5 = r5 & r6
            r6 = 0
            if (r5 == 0) goto L_0x0015
            r5 = 1
            goto L_0x0016
        L_0x0015:
            r5 = 0
        L_0x0016:
            int r8 = android.os.Build.VERSION.SDK_INT
            r1 = 23
            r2 = 8192(0x2000, float:1.14794E-41)
            r3 = 4096(0x1000, float:5.74E-42)
            if (r8 < r1) goto L_0x004f
            int r8 = r4.mOrientation
            if (r8 != 0) goto L_0x003a
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r8 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT
            int r8 = r8.getId()
            if (r7 != r8) goto L_0x002f
            if (r5 == 0) goto L_0x0042
            goto L_0x004d
        L_0x002f:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r8 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT
            int r8 = r8.getId()
            if (r7 != r8) goto L_0x004f
            if (r5 == 0) goto L_0x004d
            goto L_0x0042
        L_0x003a:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r5 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP
            int r5 = r5.getId()
            if (r7 != r5) goto L_0x0045
        L_0x0042:
            r7 = 8192(0x2000, float:1.14794E-41)
            goto L_0x004f
        L_0x0045:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r5 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN
            int r5 = r5.getId()
            if (r7 != r5) goto L_0x004f
        L_0x004d:
            r7 = 4096(0x1000, float:5.74E-42)
        L_0x004f:
            if (r7 == r3) goto L_0x005c
            if (r7 == r2) goto L_0x0054
            goto L_0x0062
        L_0x0054:
            r4.processPendingMovement(r6)
            r5 = -1
            r4.processSelectionMoves(r6, r5)
            goto L_0x0062
        L_0x005c:
            r4.processPendingMovement(r0)
            r4.processSelectionMoves(r6, r0)
        L_0x0062:
            r4.leaveContext()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.performAccessibilityAction(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, int, android.os.Bundle):boolean");
    }

    /* access modifiers changed from: package-private */
    public int processSelectionMoves(boolean z, int i) {
        Grid grid = this.mGrid;
        if (grid == null) {
            return i;
        }
        int i2 = this.mFocusPosition;
        int rowIndex = i2 != -1 ? grid.getRowIndex(i2) : -1;
        int childCount = getChildCount();
        View view = null;
        for (int i3 = 0; i3 < childCount && i != 0; i3++) {
            int i4 = i > 0 ? i3 : (childCount - 1) - i3;
            View childAt = getChildAt(i4);
            if (canScrollTo(childAt)) {
                int adapterPositionByIndex = getAdapterPositionByIndex(i4);
                int rowIndex2 = this.mGrid.getRowIndex(adapterPositionByIndex);
                if (rowIndex == -1) {
                    i2 = adapterPositionByIndex;
                    view = childAt;
                    rowIndex = rowIndex2;
                } else if (rowIndex2 == rowIndex && ((i > 0 && adapterPositionByIndex > i2) || (i < 0 && adapterPositionByIndex < i2))) {
                    i = i > 0 ? i - 1 : i + 1;
                    i2 = adapterPositionByIndex;
                    view = childAt;
                }
            }
        }
        if (view != null) {
            if (z) {
                if (hasFocus()) {
                    this.mFlag |= 32;
                    view.requestFocus();
                    this.mFlag &= -33;
                }
                this.mFocusPosition = i2;
                this.mSubFocusPosition = 0;
            } else {
                scrollToView(view, true);
            }
        }
        return i;
    }

    public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        saveContext(recycler, state);
        int itemCount = state.getItemCount();
        boolean z = (this.mFlag & 262144) != 0;
        if (itemCount > 1 && !isItemFullyVisible(0)) {
            if (Build.VERSION.SDK_INT < 23) {
                accessibilityNodeInfoCompat.addAction(8192);
            } else if (this.mOrientation == 0) {
                accessibilityNodeInfoCompat.addAction(z ? AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT : AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT);
            } else {
                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP);
            }
            accessibilityNodeInfoCompat.setScrollable(true);
        }
        if (itemCount > 1 && !isItemFullyVisible(itemCount - 1)) {
            if (Build.VERSION.SDK_INT < 23) {
                accessibilityNodeInfoCompat.addAction(4096);
            } else if (this.mOrientation == 0) {
                accessibilityNodeInfoCompat.addAction(z ? AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT : AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT);
            } else {
                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN);
            }
            accessibilityNodeInfoCompat.setScrollable(true);
        }
        accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        leaveContext();
    }
}
