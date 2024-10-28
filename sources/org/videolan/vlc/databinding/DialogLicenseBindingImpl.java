package org.videolan.vlc.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.vlc.BR;
import org.videolan.vlc.gui.LibraryWithLicense;

public class DialogLicenseBindingImpl extends DialogLicenseBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public DialogLicenseBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private DialogLicenseBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[2], objArr[4], objArr[1]);
        this.mDirtyFlags = -1;
        this.copyright.setTag((Object) null);
        this.licenseButton.setTag((Object) null);
        NestedScrollView nestedScrollView = objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag((Object) null);
        this.textView28.setTag((Object) null);
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
        int i = 0;
        String str4 = null;
        if (j2 != 0) {
            if (libraryWithLicense != null) {
                String licenseDescription = libraryWithLicense.getLicenseDescription();
                str = libraryWithLicense.getLicenseTitle();
                str3 = libraryWithLicense.getCopyright();
                String str5 = licenseDescription;
                str4 = libraryWithLicense.getLicenseLink();
                str2 = str5;
            } else {
                str2 = null;
                str = null;
                str3 = null;
            }
            boolean isEmpty = TextUtils.isEmpty(str4);
            if (j2 != 0) {
                j |= isEmpty ? 8 : 4;
            }
            if (isEmpty) {
                i = 8;
            }
            str4 = str3;
        } else {
            str2 = null;
            str = null;
        }
        if ((j & 3) != 0) {
            TextViewBindingAdapter.setText(this.copyright, str4);
            this.licenseButton.setVisibility(i);
            TextViewBindingAdapter.setText(this.textView28, str2);
            TextViewBindingAdapter.setText(this.title, str);
        }
    }
}
