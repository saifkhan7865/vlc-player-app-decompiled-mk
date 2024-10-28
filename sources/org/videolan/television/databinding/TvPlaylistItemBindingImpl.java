package org.videolan.television.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.television.BR;
import org.videolan.television.R;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.media.MediaUtils;

public class TvPlaylistItemBindingImpl extends TvPlaylistItemBinding {
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
        sparseIntArray.put(R.id.playing, 4);
    }

    public TvPlaylistItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private TvPlaylistItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[1], objArr[4], objArr[2]);
        this.mDirtyFlags = -1;
        this.artist.setTag((Object) null);
        this.coverImage.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.title.setTag((Object) null);
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
        if (BR.media == i) {
            setMedia((MediaWrapper) obj);
        } else if (BR.scaleType == i) {
            setScaleType((ImageView.ScaleType) obj);
        } else if (BR.cover != i) {
            return false;
        } else {
            setCover((BitmapDrawable) obj);
        }
        return true;
    }

    public void setMedia(MediaWrapper mediaWrapper) {
        this.mMedia = mediaWrapper;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.media);
        super.requestRebind();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.scaleType);
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
        String str;
        String str2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MediaWrapper mediaWrapper = this.mMedia;
        ImageView.ScaleType scaleType = this.mScaleType;
        BitmapDrawable bitmapDrawable = this.mCover;
        long j2 = 9 & j;
        if (j2 != 0) {
            str2 = MediaUtils.INSTANCE.getMediaSubtitle(mediaWrapper);
            str = MediaUtils.INSTANCE.getMediaTitle(mediaWrapper);
        } else {
            str2 = null;
            str = null;
        }
        long j3 = 10 & j;
        long j4 = j & 12;
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.artist, str2);
            ImageLoaderKt.loadImage(this.coverImage, mediaWrapper, 0, false, true);
            TextViewBindingAdapter.setText(this.title, str);
        }
        if (j3 != 0) {
            this.coverImage.setScaleType(scaleType);
        }
        if (j4 != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.coverImage, bitmapDrawable);
        }
    }
}
