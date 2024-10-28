package org.videolan.moviepedia.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.moviepedia.R;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.moviepedia.ui.MediaScrapingActivity;

public abstract class MoviepediaItemBinding extends ViewDataBinding {
    public final TextView itemDescription;
    public final ImageView itemImage;
    public final TextView itemTitle;
    @Bindable
    protected int mBgColor;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected MediaScrapingActivity.ClickHandler mHandler;
    @Bindable
    protected Uri mImageUrl;
    @Bindable
    protected ResolverMedia mItem;

    public abstract void setBgColor(int i);

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setHandler(MediaScrapingActivity.ClickHandler clickHandler);

    public abstract void setImageUrl(Uri uri);

    public abstract void setItem(ResolverMedia resolverMedia);

    protected MoviepediaItemBinding(Object obj, View view, int i, TextView textView, ImageView imageView, TextView textView2) {
        super(obj, view, i);
        this.itemDescription = textView;
        this.itemImage = imageView;
        this.itemTitle = textView2;
    }

    public ResolverMedia getItem() {
        return this.mItem;
    }

    public MediaScrapingActivity.ClickHandler getHandler() {
        return this.mHandler;
    }

    public int getBgColor() {
        return this.mBgColor;
    }

    public Uri getImageUrl() {
        return this.mImageUrl;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public static MoviepediaItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MoviepediaItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MoviepediaItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.moviepedia_item, viewGroup, z, obj);
    }

    public static MoviepediaItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MoviepediaItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MoviepediaItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.moviepedia_item, (ViewGroup) null, false, obj);
    }

    public static MoviepediaItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MoviepediaItemBinding bind(View view, Object obj) {
        return (MoviepediaItemBinding) bind(obj, view, R.layout.moviepedia_item);
    }
}
