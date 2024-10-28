package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.AudioBrowserAdapter;
import org.videolan.vlc.gui.view.FadableImageView;
import org.videolan.vlc.gui.view.MiniVisualizer;

public abstract class AudioBrowserCardItemBinding extends ViewDataBinding {
    public final ConstraintLayout container;
    public final AppCompatImageView itemMore;
    @Bindable
    protected int mBgColor;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected AudioBrowserAdapter.MediaItemCardViewHolder mHolder;
    @Bindable
    protected int mImageWidth;
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
    protected MediaLibraryItem mItem;
    @Bindable
    protected ImageView.ScaleType mScaleType;
    @Bindable
    protected boolean mSelected;
    public final FadableImageView mainActionButton;
    public final FadableImageView mediaCover;
    public final MaterialCardView mediaCoverContainer;
    public final ImageView mediaFavorite;
    public final ImageView mlItemOverlay;
    public final ImageView networkMedia;
    public final ImageView networkMediaOff;
    public final View networkOffOverlay;
    public final ImageView otgMedia;
    public final MiniVisualizer playing;
    public final ImageView sdMedia;
    public final AppCompatImageView selectedCheck;
    public final TextView subtitle;
    public final TextView title;

    public abstract void setBgColor(int i);

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setHolder(AudioBrowserAdapter.MediaItemCardViewHolder mediaItemCardViewHolder);

    public abstract void setImageWidth(int i);

    public abstract void setInSelection(boolean z);

    public abstract void setIsFavorite(boolean z);

    public abstract void setIsNetwork(boolean z);

    public abstract void setIsOTG(boolean z);

    public abstract void setIsPresent(boolean z);

    public abstract void setIsSD(boolean z);

    public abstract void setItem(MediaLibraryItem mediaLibraryItem);

    public abstract void setScaleType(ImageView.ScaleType scaleType);

    public abstract void setSelected(boolean z);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudioBrowserCardItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, FadableImageView fadableImageView, FadableImageView fadableImageView2, MaterialCardView materialCardView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, View view2, ImageView imageView5, MiniVisualizer miniVisualizer, ImageView imageView6, AppCompatImageView appCompatImageView2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.container = constraintLayout;
        this.itemMore = appCompatImageView;
        this.mainActionButton = fadableImageView;
        this.mediaCover = fadableImageView2;
        this.mediaCoverContainer = materialCardView;
        this.mediaFavorite = imageView;
        this.mlItemOverlay = imageView2;
        this.networkMedia = imageView3;
        this.networkMediaOff = imageView4;
        this.networkOffOverlay = view2;
        this.otgMedia = imageView5;
        this.playing = miniVisualizer;
        this.sdMedia = imageView6;
        this.selectedCheck = appCompatImageView2;
        this.subtitle = textView;
        this.title = textView2;
    }

    public MediaLibraryItem getItem() {
        return this.mItem;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public int getBgColor() {
        return this.mBgColor;
    }

    public AudioBrowserAdapter.MediaItemCardViewHolder getHolder() {
        return this.mHolder;
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
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

    public boolean getInSelection() {
        return this.mInSelection;
    }

    public boolean getSelected() {
        return this.mSelected;
    }

    public static AudioBrowserCardItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserCardItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AudioBrowserCardItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_browser_card_item, viewGroup, z, obj);
    }

    public static AudioBrowserCardItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserCardItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AudioBrowserCardItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_browser_card_item, (ViewGroup) null, false, obj);
    }

    public static AudioBrowserCardItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserCardItemBinding bind(View view, Object obj) {
        return (AudioBrowserCardItemBinding) bind(obj, view, R.layout.audio_browser_card_item);
    }
}
