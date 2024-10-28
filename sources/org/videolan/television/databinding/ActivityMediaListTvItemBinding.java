package org.videolan.television.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.television.R;
import org.videolan.television.ui.details.MediaListAdapter;

public abstract class ActivityMediaListTvItemBinding extends ViewDataBinding {
    public final ConstraintLayout container;
    public final ImageView imCover;
    public final AppCompatImageButton itemAddPlaylist;
    public final AppCompatImageButton itemAppend;
    public final AppCompatImageButton itemInsertNext;
    public final TextView itemLength;
    public final AppCompatImageButton itemMoveDown;
    public final AppCompatImageButton itemMoveUp;
    public final AppCompatImageView itemPlay;
    public final AppCompatImageButton itemRemove;
    public final View itemSelector;
    public final TextView itemSubtitle;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected MediaListAdapter.MediaListViewHolder mHolder;
    @Bindable
    protected MediaWrapper mItem;
    @Bindable
    protected String mSubtitle;
    public final TextView title;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setHolder(MediaListAdapter.MediaListViewHolder mediaListViewHolder);

    public abstract void setItem(MediaWrapper mediaWrapper);

    public abstract void setSubtitle(String str);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityMediaListTvItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, AppCompatImageButton appCompatImageButton, AppCompatImageButton appCompatImageButton2, AppCompatImageButton appCompatImageButton3, TextView textView, AppCompatImageButton appCompatImageButton4, AppCompatImageButton appCompatImageButton5, AppCompatImageView appCompatImageView, AppCompatImageButton appCompatImageButton6, View view2, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.container = constraintLayout;
        this.imCover = imageView;
        this.itemAddPlaylist = appCompatImageButton;
        this.itemAppend = appCompatImageButton2;
        this.itemInsertNext = appCompatImageButton3;
        this.itemLength = textView;
        this.itemMoveDown = appCompatImageButton4;
        this.itemMoveUp = appCompatImageButton5;
        this.itemPlay = appCompatImageView;
        this.itemRemove = appCompatImageButton6;
        this.itemSelector = view2;
        this.itemSubtitle = textView2;
        this.title = textView3;
    }

    public MediaWrapper getItem() {
        return this.mItem;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public MediaListAdapter.MediaListViewHolder getHolder() {
        return this.mHolder;
    }

    public static ActivityMediaListTvItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMediaListTvItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityMediaListTvItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_media_list_tv_item, viewGroup, z, obj);
    }

    public static ActivityMediaListTvItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMediaListTvItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityMediaListTvItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_media_list_tv_item, (ViewGroup) null, false, obj);
    }

    public static ActivityMediaListTvItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMediaListTvItemBinding bind(View view, Object obj) {
        return (ActivityMediaListTvItemBinding) bind(obj, view, R.layout.activity_media_list_tv_item);
    }
}
