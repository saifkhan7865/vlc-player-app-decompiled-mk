package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.television.R;
import org.videolan.television.ui.FocusableRecyclerView;

public abstract class ActivityMediaListTvBinding extends ViewDataBinding {
    public final AppCompatImageButton addPlaylist;
    public final TextView albumSubtitle;
    public final TextView albumTitle;
    public final TextView albumTotalTime;
    public final AppCompatImageButton append;
    public final ImageView cover;
    public final AppCompatImageButton delete;
    public final HorizontalScrollView horizontalScrollView;
    public final AppCompatImageButton insertNext;
    @Bindable
    protected MediaLibraryItem mItem;
    @Bindable
    protected String mSubtitle;
    @Bindable
    protected String mTitle;
    @Bindable
    protected String mTotalTime;
    public final FocusableRecyclerView mediaList;
    public final AppCompatImageButton play;
    public final Space spacer;

    public abstract void setItem(MediaLibraryItem mediaLibraryItem);

    public abstract void setSubtitle(String str);

    public abstract void setTitle(String str);

    public abstract void setTotalTime(String str);

    protected ActivityMediaListTvBinding(Object obj, View view, int i, AppCompatImageButton appCompatImageButton, TextView textView, TextView textView2, TextView textView3, AppCompatImageButton appCompatImageButton2, ImageView imageView, AppCompatImageButton appCompatImageButton3, HorizontalScrollView horizontalScrollView2, AppCompatImageButton appCompatImageButton4, FocusableRecyclerView focusableRecyclerView, AppCompatImageButton appCompatImageButton5, Space space) {
        super(obj, view, i);
        this.addPlaylist = appCompatImageButton;
        this.albumSubtitle = textView;
        this.albumTitle = textView2;
        this.albumTotalTime = textView3;
        this.append = appCompatImageButton2;
        this.cover = imageView;
        this.delete = appCompatImageButton3;
        this.horizontalScrollView = horizontalScrollView2;
        this.insertNext = appCompatImageButton4;
        this.mediaList = focusableRecyclerView;
        this.play = appCompatImageButton5;
        this.spacer = space;
    }

    public MediaLibraryItem getItem() {
        return this.mItem;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getTotalTime() {
        return this.mTotalTime;
    }

    public static ActivityMediaListTvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMediaListTvBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityMediaListTvBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_media_list_tv, viewGroup, z, obj);
    }

    public static ActivityMediaListTvBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMediaListTvBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityMediaListTvBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_media_list_tv, (ViewGroup) null, false, obj);
    }

    public static ActivityMediaListTvBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMediaListTvBinding bind(View view, Object obj) {
        return (ActivityMediaListTvBinding) bind(obj, view, R.layout.activity_media_list_tv);
    }
}
