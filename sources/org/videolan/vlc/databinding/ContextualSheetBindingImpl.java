package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;

public class ContextualSheetBindingImpl extends ContextualSheetBinding {
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
        sparseIntArray.put(R.id.ctx_cover_card, 4);
        sparseIntArray.put(R.id.ctx_cover_title, 5);
        sparseIntArray.put(R.id.ctx_list, 6);
    }

    public ContextualSheetBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private ContextualSheetBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[4], objArr[1], objArr[5], objArr[6], objArr[3]);
        this.mDirtyFlags = -1;
        this.ctxCover.setTag((Object) null);
        this.ctxCoverLayout.setTag((Object) null);
        this.ctxTitle.setTag((Object) null);
        NestedScrollView nestedScrollView = objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (BR.item == i) {
            setItem((MediaLibraryItem) obj);
        } else if (BR.showCover == i) {
            setShowCover(((Boolean) obj).booleanValue());
        } else if (BR.cover != i) {
            return false;
        } else {
            setCover((BitmapDrawable) obj);
        }
        return true;
    }

    public void setItem(MediaLibraryItem mediaLibraryItem) {
        this.mItem = mediaLibraryItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    public void setShowCover(boolean z) {
        this.mShowCover = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.showCover);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        int i;
        int i2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MediaLibraryItem mediaLibraryItem = this.mItem;
        boolean z = this.mShowCover;
        BitmapDrawable bitmapDrawable = this.mCover;
        long j2 = j & 10;
        if (j2 != 0) {
            if (j2 != 0) {
                j |= z ? 160 : 80;
            }
            i2 = 8;
            i = z ? 8 : 0;
            if (z) {
                i2 = 0;
            }
        } else {
            i2 = 0;
            i = 0;
        }
        if ((12 & j) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.ctxCover, bitmapDrawable);
        }
        if ((9 & j) != 0) {
            ImageLoaderKt.loadImage(this.ctxCover, mediaLibraryItem, 0, false, false);
        }
        if ((j & 10) != 0) {
            this.ctxCoverLayout.setVisibility(i2);
            this.ctxTitle.setVisibility(i);
        }
    }
}
