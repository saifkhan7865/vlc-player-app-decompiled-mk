package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.PlaylistAdapter;
import org.videolan.vlc.gui.view.MiniVisualizer;

public abstract class PlaylistItemBinding extends ViewDataBinding {
    public final TextView audioItemSubtitle;
    public final TextView audioItemTitle;
    public final ImageView coverImage;
    public final ConstraintLayout itemContainer;
    public final AppCompatImageView itemDelete;
    public final ImageButton itemMore;
    public final AppCompatImageView itemMoveDown;
    public final AppCompatImageView itemMoveUp;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected PlaylistAdapter.ViewHolder mHolder;
    @Bindable
    protected Boolean mMasked;
    @Bindable
    protected MediaWrapper mMedia;
    @Bindable
    protected ImageView.ScaleType mScaleType;
    @Bindable
    protected Boolean mStopAfterThis;
    @Bindable
    protected String mSubTitle;
    public final MiniVisualizer playing;
    public final View selector;
    public final AppCompatImageView stopAfter;
    public final View tipsOverlay;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setHolder(PlaylistAdapter.ViewHolder viewHolder);

    public abstract void setMasked(Boolean bool);

    public abstract void setMedia(MediaWrapper mediaWrapper);

    public abstract void setScaleType(ImageView.ScaleType scaleType);

    public abstract void setStopAfterThis(Boolean bool);

    public abstract void setSubTitle(String str);

    protected PlaylistItemBinding(Object obj, View view, int i, TextView textView, TextView textView2, ImageView imageView, ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, ImageButton imageButton, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, MiniVisualizer miniVisualizer, View view2, AppCompatImageView appCompatImageView4, View view3) {
        super(obj, view, i);
        this.audioItemSubtitle = textView;
        this.audioItemTitle = textView2;
        this.coverImage = imageView;
        this.itemContainer = constraintLayout;
        this.itemDelete = appCompatImageView;
        this.itemMore = imageButton;
        this.itemMoveDown = appCompatImageView2;
        this.itemMoveUp = appCompatImageView3;
        this.playing = miniVisualizer;
        this.selector = view2;
        this.stopAfter = appCompatImageView4;
        this.tipsOverlay = view3;
    }

    public PlaylistAdapter.ViewHolder getHolder() {
        return this.mHolder;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public MediaWrapper getMedia() {
        return this.mMedia;
    }

    public String getSubTitle() {
        return this.mSubTitle;
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public Boolean getStopAfterThis() {
        return this.mStopAfterThis;
    }

    public Boolean getMasked() {
        return this.mMasked;
    }

    public static PlaylistItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlaylistItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PlaylistItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.playlist_item, viewGroup, z, obj);
    }

    public static PlaylistItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlaylistItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PlaylistItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.playlist_item, (ViewGroup) null, false, obj);
    }

    public static PlaylistItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlaylistItemBinding bind(View view, Object obj) {
        return (PlaylistItemBinding) bind(obj, view, R.layout.playlist_item);
    }
}
