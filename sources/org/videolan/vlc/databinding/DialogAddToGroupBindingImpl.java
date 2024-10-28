package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;

public class DialogAddToGroupBindingImpl extends DialogAddToGroupBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.textView8, 2);
        sparseIntArray.put(R.id.dialog_list_container, 3);
        sparseIntArray.put(16908298, 4);
        sparseIntArray.put(16908292, 5);
    }

    public DialogAddToGroupBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private DialogAddToGroupBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[5], objArr[4], objArr[1], objArr[2]);
        this.mDirtyFlags = -1;
        NestedScrollView nestedScrollView = objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag((Object) null);
        this.progressBar2.setTag((Object) null);
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
        if (BR.isLoading != i) {
            return false;
        }
        setIsLoading((Boolean) obj);
        return true;
    }

    public void setIsLoading(Boolean bool) {
        this.mIsLoading = bool;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.isLoading);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Boolean bool = this.mIsLoading;
        long j2 = j & 3;
        int i = 0;
        if (j2 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (j2 != 0) {
                j |= safeUnbox ? 8 : 4;
            }
            if (!safeUnbox) {
                i = 8;
            }
        }
        if ((j & 3) != 0) {
            this.progressBar2.setVisibility(i);
        }
    }
}
