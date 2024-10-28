package org.videolan.television.databinding;

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
import org.videolan.television.R;
import org.videolan.vlc.gui.view.MiniVisualizer;

public abstract class TvPlaylistItemBinding extends ViewDataBinding {
    public final TextView artist;
    public final ImageView coverImage;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected MediaWrapper mMedia;
    @Bindable
    protected ImageView.ScaleType mScaleType;
    public final MiniVisualizer playing;
    public final TextView title;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setMedia(MediaWrapper mediaWrapper);

    public abstract void setScaleType(ImageView.ScaleType scaleType);

    protected TvPlaylistItemBinding(Object obj, View view, int i, TextView textView, ImageView imageView, MiniVisualizer miniVisualizer, TextView textView2) {
        super(obj, view, i);
        this.artist = textView;
        this.coverImage = imageView;
        this.playing = miniVisualizer;
        this.title = textView2;
    }

    public MediaWrapper getMedia() {
        return this.mMedia;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public static TvPlaylistItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TvPlaylistItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (TvPlaylistItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tv_playlist_item, viewGroup, z, obj);
    }

    public static TvPlaylistItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TvPlaylistItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (TvPlaylistItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tv_playlist_item, (ViewGroup) null, false, obj);
    }

    public static TvPlaylistItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TvPlaylistItemBinding bind(View view, Object obj) {
        return (TvPlaylistItemBinding) bind(obj, view, R.layout.tv_playlist_item);
    }
}
