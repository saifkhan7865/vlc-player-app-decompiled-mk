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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.AudioAlbumTracksAdapter;
import org.videolan.vlc.gui.view.MiniVisualizer;

public abstract class AudioAlbumTrackItemBinding extends ViewDataBinding {
    public final ImageView itemMore;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected AudioAlbumTracksAdapter.TrackItemViewHolder mHolder;
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
    protected MediaWrapper mItem;
    @Bindable
    protected boolean mSelected;
    public final ImageView mediaAbsent;
    public final ImageView mediaFavorite;
    public final ImageView mediaNetwork;
    public final ImageView mediaSd;
    public final ImageView otgMedia;
    public final MiniVisualizer playing;
    public final ImageView selectedCheck;
    public final TextView subtitle;
    public final TextView title;
    public final TextView trackNumber;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setHolder(AudioAlbumTracksAdapter.TrackItemViewHolder trackItemViewHolder);

    public abstract void setImageWidth(int i);

    public abstract void setInSelection(boolean z);

    public abstract void setIsFavorite(boolean z);

    public abstract void setIsNetwork(boolean z);

    public abstract void setIsOTG(boolean z);

    public abstract void setIsPresent(boolean z);

    public abstract void setIsSD(boolean z);

    public abstract void setItem(MediaWrapper mediaWrapper);

    public abstract void setSelected(boolean z);

    protected AudioAlbumTrackItemBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, MiniVisualizer miniVisualizer, ImageView imageView7, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.itemMore = imageView;
        this.mediaAbsent = imageView2;
        this.mediaFavorite = imageView3;
        this.mediaNetwork = imageView4;
        this.mediaSd = imageView5;
        this.otgMedia = imageView6;
        this.playing = miniVisualizer;
        this.selectedCheck = imageView7;
        this.subtitle = textView;
        this.title = textView2;
        this.trackNumber = textView3;
    }

    public MediaWrapper getItem() {
        return this.mItem;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public AudioAlbumTracksAdapter.TrackItemViewHolder getHolder() {
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

    public static AudioAlbumTrackItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioAlbumTrackItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AudioAlbumTrackItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_album_track_item, viewGroup, z, obj);
    }

    public static AudioAlbumTrackItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioAlbumTrackItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AudioAlbumTrackItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_album_track_item, (ViewGroup) null, false, obj);
    }

    public static AudioAlbumTrackItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioAlbumTrackItemBinding bind(View view, Object obj) {
        return (AudioAlbumTrackItemBinding) bind(obj, view, R.layout.audio_album_track_item);
    }
}
