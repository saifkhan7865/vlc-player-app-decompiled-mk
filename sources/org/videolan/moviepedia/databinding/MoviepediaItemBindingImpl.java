package org.videolan.moviepedia.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.SparseIntArray;
import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.Converters;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import org.videolan.moviepedia.BR;
import org.videolan.moviepedia.generated.callback.OnClickListener;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.moviepedia.ui.MediaScrapingActivity;
import org.videolan.moviepedia.ui.MediaScrapingResultAdapterKt;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;

public class MoviepediaItemBindingImpl extends MoviepediaItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback1;
    private long mDirtyFlags;
    private final CardView mboundView0;
    private final ConstraintLayout mboundView1;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public MoviepediaItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private MoviepediaItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[4], objArr[2], objArr[3]);
        this.mDirtyFlags = -1;
        this.itemDescription.setTag((Object) null);
        this.itemImage.setTag((Object) null);
        this.itemTitle.setTag((Object) null);
        CardView cardView = objArr[0];
        this.mboundView0 = cardView;
        cardView.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        this.mCallback1 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
            setHandler((MediaScrapingActivity.ClickHandler) obj);
        } else if (BR.item == i) {
            setItem((ResolverMedia) obj);
        } else if (BR.imageUrl == i) {
            setImageUrl((Uri) obj);
        } else if (BR.bgColor == i) {
            setBgColor(((Integer) obj).intValue());
        } else if (BR.cover != i) {
            return false;
        } else {
            setCover((BitmapDrawable) obj);
        }
        return true;
    }

    public void setHandler(MediaScrapingActivity.ClickHandler clickHandler) {
        this.mHandler = clickHandler;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    public void setItem(ResolverMedia resolverMedia) {
        this.mItem = resolverMedia;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    public void setImageUrl(Uri uri) {
        this.mImageUrl = uri;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.imageUrl);
        super.requestRebind();
    }

    public void setBgColor(int i) {
        this.mBgColor = i;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.bgColor);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MediaScrapingActivity.ClickHandler clickHandler = this.mHandler;
        ResolverMedia resolverMedia = this.mItem;
        Uri uri = this.mImageUrl;
        int i = this.mBgColor;
        BitmapDrawable bitmapDrawable = this.mCover;
        long j2 = 34 & j;
        if (j2 == 0 || resolverMedia == null) {
            str = null;
        } else {
            str = resolverMedia.title();
        }
        long j3 = 36 & j;
        long j4 = 40 & j;
        long j5 = j & 48;
        if (j2 != 0) {
            MediaScrapingResultAdapterKt.showYear(this.itemDescription, resolverMedia);
            TextViewBindingAdapter.setText(this.itemTitle, str);
        }
        if (j5 != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.itemImage, bitmapDrawable);
        }
        if (j3 != 0) {
            ImageLoaderKt.downloadIcon((View) this.itemImage, uri, false);
        }
        if (j4 != 0) {
            ViewBindingAdapter.setBackground(this.mboundView1, Converters.convertColorToDrawable(i));
        }
        if ((j & 32) != 0) {
            this.mboundView1.setOnClickListener(this.mCallback1);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        MediaScrapingActivity.ClickHandler clickHandler = this.mHandler;
        ResolverMedia resolverMedia = this.mItem;
        if (clickHandler != null) {
            clickHandler.onItemClick(resolverMedia);
        }
    }
}
