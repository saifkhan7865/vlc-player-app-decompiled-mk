package org.videolan.television.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.television.R;
import org.videolan.television.ui.FocusableConstraintLayout;
import org.videolan.television.ui.MediaTvItemAdapter;
import org.videolan.vlc.gui.view.FadableImageView;

public abstract class MediaBrowserTvItemListBinding extends ViewDataBinding {
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
    protected MediaTvItemAdapter.AbstractMediaItemViewHolder mHolder;
    @Bindable
    protected int mImageWidth;
    @Bindable
    protected boolean mIsNetwork;
    @Bindable
    protected boolean mIsOTG;
    @Bindable
    protected boolean mIsPresent;
    @Bindable
    protected boolean mIsSD;
    @Bindable
    protected Boolean mIsSquare;
    @Bindable
    protected MediaLibraryItem mItem;
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
    @Bindable
    protected boolean mShowSeen;
    public final FadableImageView mediaCover;
    public final ImageView mlItemSeen;
    public final AppCompatImageView networkMedia;
    public final AppCompatImageView networkMediaOff;
    public final View networkOffOverlay;
    public final AppCompatImageView otgMedia;
    public final ProgressBar progressBar;
    public final AppCompatImageView sdMedia;
    public final AppCompatTextView subtitle;
    public final AppCompatTextView title;

    public abstract void setBadge(String str);

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setDescription(String str);

    public abstract void setHolder(MediaTvItemAdapter.AbstractMediaItemViewHolder abstractMediaItemViewHolder);

    public abstract void setImageWidth(int i);

    public abstract void setIsNetwork(boolean z);

    public abstract void setIsOTG(boolean z);

    public abstract void setIsPresent(boolean z);

    public abstract void setIsSD(boolean z);

    public abstract void setIsSquare(Boolean bool);

    public abstract void setItem(MediaLibraryItem mediaLibraryItem);

    public abstract void setMax(int i);

    public abstract void setProgress(int i);

    public abstract void setProtocol(String str);

    public abstract void setScaleType(ImageView.ScaleType scaleType);

    public abstract void setSeen(long j);

    public abstract void setShowSeen(boolean z);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MediaBrowserTvItemListBinding(Object obj, View view, int i, TextView textView, FocusableConstraintLayout focusableConstraintLayout, TextView textView2, FadableImageView fadableImageView, ImageView imageView, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, View view2, AppCompatImageView appCompatImageView3, ProgressBar progressBar2, AppCompatImageView appCompatImageView4, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2) {
        super(obj, view, i);
        this.badgeTV = textView;
        this.container = focusableConstraintLayout;
        this.dviIconTv = textView2;
        this.mediaCover = fadableImageView;
        this.mlItemSeen = imageView;
        this.networkMedia = appCompatImageView;
        this.networkMediaOff = appCompatImageView2;
        this.networkOffOverlay = view2;
        this.otgMedia = appCompatImageView3;
        this.progressBar = progressBar2;
        this.sdMedia = appCompatImageView4;
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

    public MediaLibraryItem getItem() {
        return this.mItem;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public boolean getIsNetwork() {
        return this.mIsNetwork;
    }

    public boolean getIsSD() {
        return this.mIsSD;
    }

    public boolean getIsOTG() {
        return this.mIsOTG;
    }

    public boolean getIsPresent() {
        return this.mIsPresent;
    }

    public Boolean getIsSquare() {
        return this.mIsSquare;
    }

    public boolean getShowSeen() {
        return this.mShowSeen;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public String getProtocol() {
        return this.mProtocol;
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public MediaTvItemAdapter.AbstractMediaItemViewHolder getHolder() {
        return this.mHolder;
    }

    public static MediaBrowserTvItemListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MediaBrowserTvItemListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MediaBrowserTvItemListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.media_browser_tv_item_list, viewGroup, z, obj);
    }

    public static MediaBrowserTvItemListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MediaBrowserTvItemListBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MediaBrowserTvItemListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.media_browser_tv_item_list, (ViewGroup) null, false, obj);
    }

    public static MediaBrowserTvItemListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MediaBrowserTvItemListBinding bind(View view, Object obj) {
        return (MediaBrowserTvItemListBinding) bind(obj, view, R.layout.media_browser_tv_item_list);
    }
}
