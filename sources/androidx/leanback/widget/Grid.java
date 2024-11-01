package androidx.leanback.widget;

import android.util.SparseIntArray;
import androidx.collection.CircularIntArray;
import androidx.recyclerview.widget.RecyclerView;
import java.io.PrintWriter;
import java.util.Arrays;

abstract class Grid {
    public static final int START_DEFAULT = -1;
    protected int mFirstVisibleIndex = -1;
    protected int mLastVisibleIndex = -1;
    protected int mNumRows;
    protected Provider mProvider;
    protected boolean mReversedFlow;
    protected int mSpacing;
    protected int mStartIndex = -1;
    Object[] mTmpItem = new Object[1];
    protected CircularIntArray[] mTmpItemPositionsInRows;

    public interface Provider {
        void addItem(Object obj, int i, int i2, int i3, int i4);

        int createItem(int i, boolean z, Object[] objArr, boolean z2);

        int getCount();

        int getEdge(int i);

        int getMinIndex();

        int getSize(int i);

        void removeItem(int i);
    }

    /* access modifiers changed from: protected */
    public abstract boolean appendVisibleItems(int i, boolean z);

    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
    }

    public abstract void debugPrint(PrintWriter printWriter);

    /* access modifiers changed from: protected */
    public abstract int findRowMax(boolean z, int i, int[] iArr);

    /* access modifiers changed from: protected */
    public abstract int findRowMin(boolean z, int i, int[] iArr);

    public abstract CircularIntArray[] getItemPositionsInRows(int i, int i2);

    public abstract Location getLocation(int i);

    /* access modifiers changed from: protected */
    public abstract boolean prependVisibleItems(int i, boolean z);

    Grid() {
    }

    public static class Location {
        public int row;

        public Location(int i) {
            this.row = i;
        }
    }

    public static Grid createGrid(int i) {
        if (i == 1) {
            return new SingleRow();
        }
        StaggeredGridDefault staggeredGridDefault = new StaggeredGridDefault();
        staggeredGridDefault.setNumRows(i);
        return staggeredGridDefault;
    }

    public final void setSpacing(int i) {
        this.mSpacing = i;
    }

    public final void setReversedFlow(boolean z) {
        this.mReversedFlow = z;
    }

    public boolean isReversedFlow() {
        return this.mReversedFlow;
    }

    public void setProvider(Provider provider) {
        this.mProvider = provider;
    }

    public void setStart(int i) {
        this.mStartIndex = i;
    }

    public int getNumRows() {
        return this.mNumRows;
    }

    /* access modifiers changed from: package-private */
    public void setNumRows(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        } else if (this.mNumRows != i) {
            this.mNumRows = i;
            this.mTmpItemPositionsInRows = new CircularIntArray[i];
            for (int i2 = 0; i2 < this.mNumRows; i2++) {
                this.mTmpItemPositionsInRows[i2] = new CircularIntArray();
            }
        }
    }

    public final int getFirstVisibleIndex() {
        return this.mFirstVisibleIndex;
    }

    public final int getLastVisibleIndex() {
        return this.mLastVisibleIndex;
    }

    public void resetVisibleIndex() {
        this.mLastVisibleIndex = -1;
        this.mFirstVisibleIndex = -1;
    }

    public void invalidateItemsAfter(int i) {
        int i2;
        if (i >= 0 && (i2 = this.mLastVisibleIndex) >= 0) {
            if (i2 >= i) {
                this.mLastVisibleIndex = i - 1;
            }
            resetVisibleIndexIfEmpty();
            if (getFirstVisibleIndex() < 0) {
                setStart(i);
            }
        }
    }

    public final int getRowIndex(int i) {
        Location location = getLocation(i);
        if (location == null) {
            return -1;
        }
        return location.row;
    }

    public final int findRowMin(boolean z, int[] iArr) {
        return findRowMin(z, this.mReversedFlow ? this.mLastVisibleIndex : this.mFirstVisibleIndex, iArr);
    }

    public final int findRowMax(boolean z, int[] iArr) {
        return findRowMax(z, this.mReversedFlow ? this.mFirstVisibleIndex : this.mLastVisibleIndex, iArr);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean checkAppendOverLimit(int r5) {
        /*
            r4 = this;
            int r0 = r4.mLastVisibleIndex
            r1 = 0
            if (r0 >= 0) goto L_0x0006
            return r1
        L_0x0006:
            boolean r0 = r4.mReversedFlow
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0016
            int r0 = r4.findRowMin(r3, r2)
            int r2 = r4.mSpacing
            int r5 = r5 + r2
            if (r0 > r5) goto L_0x0020
            goto L_0x001f
        L_0x0016:
            int r0 = r4.findRowMax(r1, r2)
            int r2 = r4.mSpacing
            int r5 = r5 - r2
            if (r0 < r5) goto L_0x0020
        L_0x001f:
            r1 = 1
        L_0x0020:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.Grid.checkAppendOverLimit(int):boolean");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean checkPrependOverLimit(int r5) {
        /*
            r4 = this;
            int r0 = r4.mLastVisibleIndex
            r1 = 0
            if (r0 >= 0) goto L_0x0006
            return r1
        L_0x0006:
            boolean r0 = r4.mReversedFlow
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0016
            int r0 = r4.findRowMax(r1, r2)
            int r2 = r4.mSpacing
            int r5 = r5 - r2
            if (r0 < r5) goto L_0x0020
            goto L_0x001f
        L_0x0016:
            int r0 = r4.findRowMin(r3, r2)
            int r2 = r4.mSpacing
            int r5 = r5 + r2
            if (r0 > r5) goto L_0x0020
        L_0x001f:
            r1 = 1
        L_0x0020:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.Grid.checkPrependOverLimit(int):boolean");
    }

    public final CircularIntArray[] getItemPositionsInRows() {
        return getItemPositionsInRows(getFirstVisibleIndex(), getLastVisibleIndex());
    }

    public final boolean prependOneColumnVisibleItems() {
        return prependVisibleItems(this.mReversedFlow ? Integer.MIN_VALUE : Integer.MAX_VALUE, true);
    }

    public final void prependVisibleItems(int i) {
        prependVisibleItems(i, false);
    }

    public boolean appendOneColumnVisibleItems() {
        return appendVisibleItems(this.mReversedFlow ? Integer.MAX_VALUE : Integer.MIN_VALUE, true);
    }

    public final void appendVisibleItems(int i) {
        appendVisibleItems(i, false);
    }

    public void removeInvisibleItemsAtEnd(int i, int i2) {
        while (true) {
            int i3 = this.mLastVisibleIndex;
            if (i3 < this.mFirstVisibleIndex || i3 <= i) {
                break;
            }
            if (this.mReversedFlow) {
                if (this.mProvider.getEdge(i3) > i2) {
                    break;
                }
            } else if (this.mProvider.getEdge(i3) < i2) {
                break;
            }
            this.mProvider.removeItem(this.mLastVisibleIndex);
            this.mLastVisibleIndex--;
        }
        resetVisibleIndexIfEmpty();
    }

    public void removeInvisibleItemsAtFront(int i, int i2) {
        while (true) {
            int i3 = this.mLastVisibleIndex;
            int i4 = this.mFirstVisibleIndex;
            if (i3 < i4 || i4 >= i) {
                break;
            }
            int size = this.mProvider.getSize(i4);
            if (this.mReversedFlow) {
                if (this.mProvider.getEdge(this.mFirstVisibleIndex) - size < i2) {
                    break;
                }
            } else if (this.mProvider.getEdge(this.mFirstVisibleIndex) + size > i2) {
                break;
            }
            this.mProvider.removeItem(this.mFirstVisibleIndex);
            this.mFirstVisibleIndex++;
        }
        resetVisibleIndexIfEmpty();
    }

    private void resetVisibleIndexIfEmpty() {
        if (this.mLastVisibleIndex < this.mFirstVisibleIndex) {
            resetVisibleIndex();
        }
    }

    public void fillDisappearingItems(int[] iArr, int i, SparseIntArray sparseIntArray) {
        int i2;
        int i3;
        int i4;
        int lastVisibleIndex = getLastVisibleIndex();
        int binarySearch = lastVisibleIndex >= 0 ? Arrays.binarySearch(iArr, 0, i, lastVisibleIndex) : 0;
        if (binarySearch < 0) {
            if (this.mReversedFlow) {
                i4 = (this.mProvider.getEdge(lastVisibleIndex) - this.mProvider.getSize(lastVisibleIndex)) - this.mSpacing;
            } else {
                i4 = this.mProvider.getEdge(lastVisibleIndex) + this.mProvider.getSize(lastVisibleIndex) + this.mSpacing;
            }
            int i5 = i4;
            for (int i6 = (-binarySearch) - 1; i6 < i; i6++) {
                int i7 = iArr[i6];
                int i8 = sparseIntArray.get(i7);
                int i9 = i8 < 0 ? 0 : i8;
                int createItem = this.mProvider.createItem(i7, true, this.mTmpItem, true);
                this.mProvider.addItem(this.mTmpItem[0], i7, createItem, i9, i5);
                if (this.mReversedFlow) {
                    i5 = (i5 - createItem) - this.mSpacing;
                } else {
                    i5 = i5 + createItem + this.mSpacing;
                }
            }
        }
        int firstVisibleIndex = getFirstVisibleIndex();
        int binarySearch2 = firstVisibleIndex >= 0 ? Arrays.binarySearch(iArr, 0, i, firstVisibleIndex) : 0;
        if (binarySearch2 < 0) {
            if (this.mReversedFlow) {
                i2 = this.mProvider.getEdge(firstVisibleIndex);
            } else {
                i2 = this.mProvider.getEdge(firstVisibleIndex);
            }
            for (int i10 = (-binarySearch2) - 2; i10 >= 0; i10--) {
                int i11 = iArr[i10];
                int i12 = sparseIntArray.get(i11);
                int i13 = i12 < 0 ? 0 : i12;
                int createItem2 = this.mProvider.createItem(i11, false, this.mTmpItem, true);
                if (this.mReversedFlow) {
                    i3 = i2 + this.mSpacing + createItem2;
                } else {
                    i3 = (i2 - this.mSpacing) - createItem2;
                }
                this.mProvider.addItem(this.mTmpItem[0], i11, createItem2, i13, i2);
            }
        }
    }
}
