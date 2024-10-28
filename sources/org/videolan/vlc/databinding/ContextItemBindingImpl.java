package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.vlc.BR;
import org.videolan.vlc.gui.dialogs.CtxMenuItem;

public class ContextItemBindingImpl extends ContextItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public ContextItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private ContextItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[2]);
        this.mDirtyFlags = -1;
        this.contextOptionIcon.setTag((Object) null);
        this.contextOptionTitle.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
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
        if (BR.menuItem != i) {
            return false;
        }
        setMenuItem((CtxMenuItem) obj);
        return true;
    }

    public void setMenuItem(CtxMenuItem ctxMenuItem) {
        this.mMenuItem = ctxMenuItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.menuItem);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        int i;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        CtxMenuItem ctxMenuItem = this.mMenuItem;
        long j2 = j & 3;
        int i2 = 0;
        String str2 = null;
        if (j2 != 0) {
            if (ctxMenuItem != null) {
                i = ctxMenuItem.getIcon();
                str = ctxMenuItem.getTitle();
            } else {
                str = null;
                i = 0;
            }
            boolean z = i != 0;
            if (j2 != 0) {
                j |= z ? 8 : 4;
            }
            if (!z) {
                i2 = 8;
            }
            str2 = str;
        }
        if ((j & 3) != 0) {
            this.contextOptionIcon.setVisibility(i2);
            TextViewBindingAdapter.setText(this.contextOptionTitle, str2);
        }
    }
}
