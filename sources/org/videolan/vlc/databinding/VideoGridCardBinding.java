package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.video.VideoListAdapter;

public abstract class VideoGridCardBinding extends ViewDataBinding {
    public final ImageView absentMedia;
    public final MaterialCardView container;
    public final ImageView itemMore;
    @Bindable
    protected int mBgColor;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected VideoListAdapter.ViewHolder mHolder;
    @Bindable
    protected boolean mInSelection;
    @Bindable
    protected boolean mIsFavorite;
    @Bindable
    protected boolean mIsNetwork;
    @Bindable
    protected boolean mIsOTG;
    @Bindable
    protected boolean mIsPresent;
    @Bindable
    protected boolean mIsSD;
    @Bindable
    protected int mMax;
    @Bindable
    protected MediaLibraryItem mMedia;
    @Bindable
    protected int mProgress;
    @Bindable
    protected String mResolution;
    @Bindable
    protected ImageView.ScaleType mScaleType;
    @Bindable
    protected long mSeen;
    @Bindable
    protected boolean mSelected;
    @Bindable
    protected boolean mShowItemProgress;
    @Bindable
    protected boolean mShowProgress;
    @Bindable
    protected String mTime;
    public final ImageView mediaFavorite;
    public final ImageView mlItemOverlay;
    public final ProgressBar mlItemProgress;
    public final TextView mlItemResolution;
    public final ImageView mlItemSeen;
    public final ImageView mlItemThumbnail;
    public final TextView mlItemTime;
    public final TextView mlItemTitle;
    public final ImageView networkMedia;
    public final View networkOffOverlay;
    public final ImageView otgMedia;
    public final ImageView sdMedia;
    public final ImageView selectedCheck;
    public final ProgressBar thumbProgress;

    public abstract void setBgColor(int i);

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setHolder(VideoListAdapter.ViewHolder viewHolder);

    public abstract void setInSelection(boolean z);

    public abstract void setIsFavorite(boolean z);

    public abstract void setIsNetwork(boolean z);

    public abstract void setIsOTG(boolean z);

    public abstract void setIsPresent(boolean z);

    public abstract void setIsSD(boolean z);

    public abstract void setMax(int i);

    public abstract void setMedia(MediaLibraryItem mediaLibraryItem);

    public abstract void setProgress(int i);

    public abstract void setResolution(String str);

    public abstract void setScaleType(ImageView.ScaleType scaleType);

    public abstract void setSeen(long j);

    public abstract void setSelected(boolean z);

    public abstract void setShowItemProgress(boolean z);

    public abstract void setShowProgress(boolean z);

    public abstract void setTime(String str);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected VideoGridCardBinding(Object obj, View view, int i, ImageView imageView, MaterialCardView materialCardView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ProgressBar progressBar, TextView textView, ImageView imageView5, ImageView imageView6, TextView textView2, TextView textView3, ImageView imageView7, View view2, ImageView imageView8, ImageView imageView9, ImageView imageView10, ProgressBar progressBar2) {
        super(obj, view, i);
        this.absentMedia = imageView;
        this.container = materialCardView;
        this.itemMore = imageView2;
        this.mediaFavorite = imageView3;
        this.mlItemOverlay = imageView4;
        this.mlItemProgress = progressBar;
        this.mlItemResolution = textView;
        this.mlItemSeen = imageView5;
        this.mlItemThumbnail = imageView6;
        this.mlItemTime = textView2;
        this.mlItemTitle = textView3;
        this.networkMedia = imageView7;
        this.networkOffOverlay = view2;
        this.otgMedia = imageView8;
        this.sdMedia = imageView9;
        this.selectedCheck = imageView10;
        this.thumbProgress = progressBar2;
    }

    public MediaLibraryItem getMedia() {
        return this.mMedia;
    }

    public boolean getIsNetwork() {
        return this.mIsNetwork;
    }

    public boolean getIsOTG() {
        return this.mIsOTG;
    }

    public boolean getIsSD() {
        return this.mIsSD;
    }

    public boolean getIsPresent() {
        return this.mIsPresent;
    }

    public boolean getIsFavorite() {
        return this.mIsFavorite;
    }

    public boolean getShowProgress() {
        return this.mShowProgress;
    }

    public boolean getShowItemProgress() {
        return this.mShowItemProgress;
    }

    public boolean getInSelection() {
        return this.mInSelection;
    }

    public boolean getSelected() {
        return this.mSelected;
    }

    public long getSeen() {
        return this.mSeen;
    }

    public String getTime() {
        return this.mTime;
    }

    public String getResolution() {
        return this.mResolution;
    }

    public int getMax() {
        return this.mMax;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public int getBgColor() {
        return this.mBgColor;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public VideoListAdapter.ViewHolder getHolder() {
        return this.mHolder;
    }

    public static VideoGridCardBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoGridCardBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (VideoGridCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.video_grid_card, viewGroup, z, obj);
    }

    public static VideoGridCardBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoGridCardBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (VideoGridCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.video_grid_card, (ViewGroup) null, false, obj);
    }

    public static VideoGridCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoGridCardBinding bind(View view, Object obj) {
        return (VideoGridCardBinding) bind(obj, view, R.layout.video_grid_card);
    }
}
