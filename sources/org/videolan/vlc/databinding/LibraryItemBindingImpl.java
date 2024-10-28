package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.LibraryWithLicense;

public class LibraryItemBindingImpl extends LibraryItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.imageView17, 4);
    }

    public LibraryItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private LibraryItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[4], objArr[2], objArr[3], objArr[1]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.textView3.setTag((Object) null);
        this.textView31.setTag((Object) null);
        this.title.setTag((Object) null);
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
        if (BR.library != i) {
            return false;
        }
        setLibrary((LibraryWithLicense) obj);
        return true;
    }

    public void setLibrary(LibraryWithLicense libraryWithLicense) {
        this.mLibrary = libraryWithLicense;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.library);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        LibraryWithLicense libraryWithLicense = this.mLibrary;
        long j2 = j & 3;
        if (j2 == 0 || libraryWithLicense == null) {
            str2 = null;
            str3 = null;
            str = null;
        } else {
            str2 = libraryWithLicense.getLicenseTitle();
            str = libraryWithLicense.getCopyright();
            str3 = libraryWithLicense.getTitle();
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.textView3, str);
            TextViewBindingAdapter.setText(this.textView31, str2);
            TextViewBindingAdapter.setText(this.title, str3);
        }
    }
}
