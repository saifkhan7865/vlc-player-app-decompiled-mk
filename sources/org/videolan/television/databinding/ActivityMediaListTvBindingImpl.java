package org.videolan.television.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.television.BR;
import org.videolan.television.R;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;

public class ActivityMediaListTvBindingImpl extends ActivityMediaListTvBinding {
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
        sparseIntArray.put(R.id.spacer, 5);
        sparseIntArray.put(R.id.horizontalScrollView, 6);
        sparseIntArray.put(R.id.play, 7);
        sparseIntArray.put(R.id.delete, 8);
        sparseIntArray.put(R.id.insert_next, 9);
        sparseIntArray.put(R.id.append, 10);
        sparseIntArray.put(R.id.add_playlist, 11);
        sparseIntArray.put(R.id.media_list, 12);
    }

    public ActivityMediaListTvBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private ActivityMediaListTvBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[11], objArr[3], objArr[2], objArr[4], objArr[10], objArr[1], objArr[8], objArr[6], objArr[9], objArr[12], objArr[7], objArr[5]);
        this.mDirtyFlags = -1;
        this.albumSubtitle.setTag((Object) null);
        this.albumTitle.setTag((Object) null);
        this.albumTotalTime.setTag((Object) null);
        this.cover.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        } else if (BR.totalTime == i) {
            setTotalTime((String) obj);
        } else if (BR.title == i) {
            setTitle((String) obj);
        } else if (BR.subtitle != i) {
            return false;
        } else {
            setSubtitle((String) obj);
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

    public void setTotalTime(String str) {
        this.mTotalTime = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.totalTime);
        super.requestRebind();
    }

    public void setTitle(String str) {
        this.mTitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.title);
        super.requestRebind();
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.subtitle);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MediaLibraryItem mediaLibraryItem = this.mItem;
        String str = this.mTotalTime;
        String str2 = this.mTitle;
        String str3 = this.mSubtitle;
        long j2 = 17 & j;
        long j3 = 18 & j;
        long j4 = 20 & j;
        if ((j & 24) != 0) {
            TextViewBindingAdapter.setText(this.albumSubtitle, str3);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.albumTitle, str2);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.albumTotalTime, str);
        }
        if (j2 != 0) {
            ImageLoaderKt.loadImage(this.cover, mediaLibraryItem, 0, true, false);
        }
    }
}
