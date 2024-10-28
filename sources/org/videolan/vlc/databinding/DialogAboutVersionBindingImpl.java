package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public class DialogAboutVersionBindingImpl extends DialogAboutVersionBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.version, 1);
        sparseIntArray.put(R.id.medias2, 2);
        sparseIntArray.put(R.id.changelog, 3);
        sparseIntArray.put(R.id.moreButton, 4);
        sparseIntArray.put(R.id.remote_access, 5);
        sparseIntArray.put(R.id.remote_access_hash_title, 6);
        sparseIntArray.put(R.id.textView28, 7);
        sparseIntArray.put(R.id.textView30, 8);
        sparseIntArray.put(R.id.textView31, 9);
        sparseIntArray.put(R.id.divider, 10);
        sparseIntArray.put(R.id.textView29, 11);
        sparseIntArray.put(R.id.textView35, 12);
        sparseIntArray.put(R.id.textView2, 13);
        sparseIntArray.put(R.id.divider2, 14);
        sparseIntArray.put(R.id.revision, 15);
        sparseIntArray.put(R.id.vlc_revision, 16);
        sparseIntArray.put(R.id.libvlc_revision, 17);
        sparseIntArray.put(R.id.libvlc_version, 18);
        sparseIntArray.put(R.id.compiled_by, 19);
        sparseIntArray.put(R.id.signed_by, 20);
        sparseIntArray.put(R.id.remote_access_version, 21);
        sparseIntArray.put(R.id.remote_access_revision, 22);
        sparseIntArray.put(R.id.compilation_barrier, 23);
    }

    public DialogAboutVersionBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 24, sIncludes, sViewsWithIds));
    }

    private DialogAboutVersionBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[23], objArr[19], objArr[10], objArr[14], objArr[17], objArr[18], objArr[2], objArr[4], objArr[5], objArr[6], objArr[22], objArr[21], objArr[15], objArr[20], objArr[13], objArr[7], objArr[11], objArr[8], objArr[9], objArr[12], objArr[1], objArr[16]);
        this.mDirtyFlags = -1;
        NestedScrollView nestedScrollView = objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
    }
}
