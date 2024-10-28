package org.videolan.television.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.television.BR;
import org.videolan.television.R;

public class TvVideoDetailsBindingImpl extends TvVideoDetailsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.textView22, 1);
        sparseIntArray.put(R.id.textView26, 2);
        sparseIntArray.put(R.id.textView27, 3);
        sparseIntArray.put(R.id.textView28, 4);
        sparseIntArray.put(R.id.textView29, 5);
        sparseIntArray.put(R.id.textView30, 6);
        sparseIntArray.put(R.id.textView31, 7);
        sparseIntArray.put(R.id.textView32, 8);
        sparseIntArray.put(R.id.textView33, 9);
        sparseIntArray.put(R.id.textView34, 10);
    }

    public TvVideoDetailsBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private TvVideoDetailsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8], objArr[9], objArr[10]);
        this.mDirtyFlags = -1;
        this.container.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (BR.item != i) {
            return false;
        }
        setItem((MediaMetadata) obj);
        return true;
    }

    public void setItem(MediaMetadata mediaMetadata) {
        this.mItem = mediaMetadata;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
    }
}
