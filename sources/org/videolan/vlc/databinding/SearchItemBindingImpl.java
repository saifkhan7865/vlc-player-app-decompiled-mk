package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.generated.callback.OnClickListener;
import org.videolan.vlc.gui.SearchActivity;
import org.videolan.vlc.gui.SearchResultAdapter;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;

public class SearchItemBindingImpl extends SearchItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback3;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public SearchItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private SearchItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[1], objArr[2]);
        this.mDirtyFlags = -1;
        this.itemDescription.setTag((Object) null);
        this.itemImage.setTag((Object) null);
        this.itemTitle.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        this.mCallback3 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
        if (BR.handler == i) {
            setHandler((SearchActivity.ClickHandler) obj);
        } else if (BR.item == i) {
            setItem((MediaLibraryItem) obj);
        } else if (BR.holder == i) {
            setHolder((SearchResultAdapter.ViewHolder) obj);
        } else if (BR.isSquare == i) {
            setIsSquare((Boolean) obj);
        } else if (BR.coverWidth == i) {
            setCoverWidth(((Integer) obj).intValue());
        } else if (BR.description == i) {
            setDescription((String) obj);
        } else if (BR.cover != i) {
            return false;
        } else {
            setCover((BitmapDrawable) obj);
        }
        return true;
    }

    public void setHandler(SearchActivity.ClickHandler clickHandler) {
        this.mHandler = clickHandler;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    public void setItem(MediaLibraryItem mediaLibraryItem) {
        this.mItem = mediaLibraryItem;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    public void setHolder(SearchResultAdapter.ViewHolder viewHolder) {
        this.mHolder = viewHolder;
    }

    public void setIsSquare(Boolean bool) {
        this.mIsSquare = bool;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.isSquare);
        super.requestRebind();
    }

    public void setCoverWidth(int i) {
        this.mCoverWidth = i;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.coverWidth);
        super.requestRebind();
    }

    public void setDescription(String str) {
        this.mDescription = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.description);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SearchActivity.ClickHandler clickHandler = this.mHandler;
        MediaLibraryItem mediaLibraryItem = this.mItem;
        Boolean bool = this.mIsSquare;
        int i = this.mCoverWidth;
        String str = this.mDescription;
        BitmapDrawable bitmapDrawable = this.mCover;
        long j2 = 146 & j;
        String title = (j2 == 0 || (j & 130) == 0 || mediaLibraryItem == null) ? null : mediaLibraryItem.getTitle();
        long j3 = 152 & j;
        if (j3 != 0) {
            z = ViewDataBinding.safeUnbox(bool);
        } else {
            z = false;
        }
        long j4 = j & 192;
        if ((j & 160) != 0) {
            TextViewBindingAdapter.setText(this.itemDescription, str);
        }
        if (j4 != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.itemImage, bitmapDrawable);
        }
        if (j2 != 0) {
            ImageLoaderKt.loadImage(this.itemImage, mediaLibraryItem, i, false, false);
        }
        if (j3 != 0) {
            ImageLoaderKt.constraintRatio(this.itemImage, z, i);
        }
        if ((130 & j) != 0) {
            TextViewBindingAdapter.setText(this.itemTitle, title);
        }
        if ((j & 128) != 0) {
            this.mboundView0.setOnClickListener(this.mCallback3);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        SearchActivity.ClickHandler clickHandler = this.mHandler;
        MediaLibraryItem mediaLibraryItem = this.mItem;
        if (clickHandler != null) {
            clickHandler.onItemClick(mediaLibraryItem);
        }
    }
}
