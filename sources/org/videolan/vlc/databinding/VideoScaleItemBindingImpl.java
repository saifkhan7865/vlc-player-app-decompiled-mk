package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.UiToolsKt;

public class VideoScaleItemBindingImpl extends VideoScaleItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public VideoScaleItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private VideoScaleItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[0], objArr[2]);
        this.mDirtyFlags = -1;
        this.imageView11.setTag((Object) null);
        this.trackContainer.setTag((Object) null);
        this.trackTitle.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        if (BR.scaleName == i) {
            setScaleName((String) obj);
        } else if (BR.selected != i) {
            return false;
        } else {
            setSelected((Boolean) obj);
        }
        return true;
    }

    public void setScaleName(String str) {
        this.mScaleName = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.scaleName);
        super.requestRebind();
    }

    public void setSelected(Boolean bool) {
        this.mSelected = bool;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.selected);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        int i;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mScaleName;
        Boolean bool = this.mSelected;
        long j2 = j & 6;
        int i2 = 0;
        if (j2 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (j2 != 0) {
                j |= safeUnbox ? 80 : 40;
            }
            i = getColorFromResource(this.trackTitle, safeUnbox ? R.color.white : R.color.white_transparent_50);
            if (!safeUnbox) {
                i2 = 4;
            }
        } else {
            i = 0;
        }
        if ((6 & j) != 0) {
            this.imageView11.setVisibility(i2);
            this.trackTitle.setTextColor(i);
            UiToolsKt.isSelected(this.trackTitle, bool);
        }
        if ((5 & j) != 0) {
            TextViewBindingAdapter.setText(this.trackTitle, str);
        }
        if ((j & 4) != 0) {
            UiToolsKt.setEllipsizeModeByPref(this.trackTitle, true);
        }
    }
}
