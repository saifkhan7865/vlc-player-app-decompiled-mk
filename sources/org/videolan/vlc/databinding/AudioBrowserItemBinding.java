package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.AudioBrowserAdapter;
import org.videolan.vlc.gui.view.FadableImageView;
import org.videolan.vlc.gui.view.MiniVisualizer;

public abstract class AudioBrowserItemBinding extends ViewDataBinding {
    public final ImageView itemMore;
    public final ImageView itemMove;
    public final LinearLayout linearLayout6;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected AudioBrowserAdapter.MediaItemViewHolder mHolder;
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
    protected boolean mSelected;
    public final FadableImageView mediaCover;
    public final ImageView mediaFavorite;
    public final ImageView mlItemOverlay;
    public final ImageView networkMedia;
    public final ImageView networkMediaOff;
    public final View networkOffOverlay;
    public final ImageView otgMedia;
    public final MiniVisualizer playing;
    public final ImageView sdMedia;
    public final ImageView selectedCheck;
    public final ImageView selectorImage;
    public final TextView subtitle;
    public final TextView title;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setHolder(AudioBrowserAdapter.MediaItemViewHolder mediaItemViewHolder);

    public abstract void setImageWidth(int i);

    public abstract void setInSelection(boolean z);

    public abstract void setIsFavorite(boolean z);

    public abstract void setIsNetwork(boolean z);

    public abstract void setIsOTG(boolean z);

    public abstract void setIsPresent(boolean z);

    public abstract void setIsSD(boolean z);

    public abstract void setItem(MediaLibraryItem mediaLibraryItem);

    public abstract void setSelected(boolean z);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudioBrowserItemBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, FadableImageView fadableImageView, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, View view2, ImageView imageView7, MiniVisualizer miniVisualizer, ImageView imageView8, ImageView imageView9, ImageView imageView10, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.itemMore = imageView;
        this.itemMove = imageView2;
        this.linearLayout6 = linearLayout;
        this.mediaCover = fadableImageView;
        this.mediaFavorite = imageView3;
        this.mlItemOverlay = imageView4;
        this.networkMedia = imageView5;
        this.networkMediaOff = imageView6;
        this.networkOffOverlay = view2;
        this.otgMedia = imageView7;
        this.playing = miniVisualizer;
        this.sdMedia = imageView8;
        this.selectedCheck = imageView9;
        this.selectorImage = imageView10;
        this.subtitle = textView;
        this.title = textView2;
    }

    public MediaLibraryItem getItem() {
        return this.mItem;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public AudioBrowserAdapter.MediaItemViewHolder getHolder() {
        return this.mHolder;
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

    public static AudioBrowserItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AudioBrowserItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_browser_item, viewGroup, z, obj);
    }

    public static AudioBrowserItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AudioBrowserItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_browser_item, (ViewGroup) null, false, obj);
    }

    public static AudioBrowserItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserItemBinding bind(View view, Object obj) {
        return (AudioBrowserItemBinding) bind(obj, view, R.layout.audio_browser_item);
    }
}
