package org.videolan.television.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.television.R;
import org.videolan.television.ui.FocusableConstraintLayout;
import org.videolan.television.ui.MediaScrapingTvItemAdapter;
import org.videolan.vlc.gui.view.FadableImageView;

public abstract class MovieBrowserTvItemBinding extends ViewDataBinding {
    public final TextView badgeTV;
    public final FocusableConstraintLayout container;
    public final TextView dviIconTv;
    @Bindable
    protected String mBadge;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected String mDescription;
    @Bindable
    protected MediaScrapingTvItemAdapter.MovieItemTVViewHolder mHolder;
    @Bindable
    protected Boolean mIsSquare;
    @Bindable
    protected MediaMetadataWithImages mItem;
    @Bindable
    protected int mMax;
    @Bindable
    protected int mProgress;
    @Bindable
    protected String mProtocol;
    @Bindable
    protected ImageView.ScaleType mScaleType;
    @Bindable
    protected long mSeen;
    public final FadableImageView mediaCover;
    public final ImageView mlItemSeen;
    public final ProgressBar progressBar;
    public final AppCompatTextView subtitle;
    public final AppCompatTextView title;

    public abstract void setBadge(String str);

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setDescription(String str);

    public abstract void setHolder(MediaScrapingTvItemAdapter.MovieItemTVViewHolder movieItemTVViewHolder);

    public abstract void setIsSquare(Boolean bool);

    public abstract void setItem(MediaMetadataWithImages mediaMetadataWithImages);

    public abstract void setMax(int i);

    public abstract void setProgress(int i);

    public abstract void setProtocol(String str);

    public abstract void setScaleType(ImageView.ScaleType scaleType);

    public abstract void setSeen(long j);

    protected MovieBrowserTvItemBinding(Object obj, View view, int i, TextView textView, FocusableConstraintLayout focusableConstraintLayout, TextView textView2, FadableImageView fadableImageView, ImageView imageView, ProgressBar progressBar2, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2) {
        super(obj, view, i);
        this.badgeTV = textView;
        this.container = focusableConstraintLayout;
        this.dviIconTv = textView2;
        this.mediaCover = fadableImageView;
        this.mlItemSeen = imageView;
        this.progressBar = progressBar2;
        this.subtitle = appCompatTextView;
        this.title = appCompatTextView2;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public int getMax() {
        return this.mMax;
    }

    public String getBadge() {
        return this.mBadge;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public long getSeen() {
        return this.mSeen;
    }

    public MediaMetadataWithImages getItem() {
        return this.mItem;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public Boolean getIsSquare() {
        return this.mIsSquare;
    }

    public String getProtocol() {
        return this.mProtocol;
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public MediaScrapingTvItemAdapter.MovieItemTVViewHolder getHolder() {
        return this.mHolder;
    }

    public static MovieBrowserTvItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MovieBrowserTvItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MovieBrowserTvItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.movie_browser_tv_item, viewGroup, z, obj);
    }

    public static MovieBrowserTvItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MovieBrowserTvItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MovieBrowserTvItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.movie_browser_tv_item, (ViewGroup) null, false, obj);
    }

    public static MovieBrowserTvItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MovieBrowserTvItemBinding bind(View view, Object obj) {
        return (MovieBrowserTvItemBinding) bind(obj, view, R.layout.movie_browser_tv_item);
    }
}
