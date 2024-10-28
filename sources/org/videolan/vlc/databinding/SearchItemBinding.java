package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SearchActivity;
import org.videolan.vlc.gui.SearchResultAdapter;

public abstract class SearchItemBinding extends ViewDataBinding {
    public final TextView itemDescription;
    public final ImageView itemImage;
    public final TextView itemTitle;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected int mCoverWidth;
    @Bindable
    protected String mDescription;
    @Bindable
    protected SearchActivity.ClickHandler mHandler;
    @Bindable
    protected SearchResultAdapter.ViewHolder mHolder;
    @Bindable
    protected Boolean mIsSquare;
    @Bindable
    protected MediaLibraryItem mItem;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setCoverWidth(int i);

    public abstract void setDescription(String str);

    public abstract void setHandler(SearchActivity.ClickHandler clickHandler);

    public abstract void setHolder(SearchResultAdapter.ViewHolder viewHolder);

    public abstract void setIsSquare(Boolean bool);

    public abstract void setItem(MediaLibraryItem mediaLibraryItem);

    protected SearchItemBinding(Object obj, View view, int i, TextView textView, ImageView imageView, TextView textView2) {
        super(obj, view, i);
        this.itemDescription = textView;
        this.itemImage = imageView;
        this.itemTitle = textView2;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public MediaLibraryItem getItem() {
        return this.mItem;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public SearchResultAdapter.ViewHolder getHolder() {
        return this.mHolder;
    }

    public int getCoverWidth() {
        return this.mCoverWidth;
    }

    public Boolean getIsSquare() {
        return this.mIsSquare;
    }

    public SearchActivity.ClickHandler getHandler() {
        return this.mHandler;
    }

    public static SearchItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SearchItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SearchItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.search_item, viewGroup, z, obj);
    }

    public static SearchItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SearchItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SearchItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.search_item, (ViewGroup) null, false, obj);
    }

    public static SearchItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SearchItemBinding bind(View view, Object obj) {
        return (SearchItemBinding) bind(obj, view, R.layout.search_item);
    }
}
