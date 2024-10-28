package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.gui.view.SwipeRefreshLayout;

public abstract class HeaderMediaListActivityBinding extends ViewDataBinding {
    public final AppBarLayout appbar;
    public final ViewStubCompat audioPlayerTips;
    public final ImageView backgroundView;
    public final Barrier barrier;
    public final FastScroller browserFastScroller;
    public final ImageView btnAddPlaylist;
    public final ImageView btnFavorite;
    public final ImageView btnShuffle;
    public final CollapsingToolbarLayout collapsingToolbar;
    public final CoordinatorLayout coordinator;
    public final TextView duration;
    public final ConstraintLayout headerLayout;
    public final TextView headerListArtist;
    public final TextView headerListTitle;
    @Bindable
    protected BitmapDrawable mCover;
    @Bindable
    protected MediaLibraryItem mPlaylist;
    @Bindable
    protected String mReleaseYear;
    @Bindable
    protected Integer mTopmargin;
    @Bindable
    protected Long mTotalDuration;
    public final Toolbar mainToolbar;
    public final Button playBtn;
    public final ImageView playlistCover;
    public final TextView releaseDate;
    public final RecyclerView songs;
    public final SwipeRefreshLayout swipeLayout;

    public abstract void setCover(BitmapDrawable bitmapDrawable);

    public abstract void setPlaylist(MediaLibraryItem mediaLibraryItem);

    public abstract void setReleaseYear(String str);

    public abstract void setTopmargin(Integer num);

    public abstract void setTotalDuration(Long l);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected HeaderMediaListActivityBinding(Object obj, View view, int i, AppBarLayout appBarLayout, ViewStubCompat viewStubCompat, ImageView imageView, Barrier barrier2, FastScroller fastScroller, ImageView imageView2, ImageView imageView3, ImageView imageView4, CollapsingToolbarLayout collapsingToolbarLayout, CoordinatorLayout coordinatorLayout, TextView textView, ConstraintLayout constraintLayout, TextView textView2, TextView textView3, Toolbar toolbar, Button button, ImageView imageView5, TextView textView4, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        super(obj, view, i);
        this.appbar = appBarLayout;
        this.audioPlayerTips = viewStubCompat;
        this.backgroundView = imageView;
        this.barrier = barrier2;
        this.browserFastScroller = fastScroller;
        this.btnAddPlaylist = imageView2;
        this.btnFavorite = imageView3;
        this.btnShuffle = imageView4;
        this.collapsingToolbar = collapsingToolbarLayout;
        this.coordinator = coordinatorLayout;
        this.duration = textView;
        this.headerLayout = constraintLayout;
        this.headerListArtist = textView2;
        this.headerListTitle = textView3;
        this.mainToolbar = toolbar;
        this.playBtn = button;
        this.playlistCover = imageView5;
        this.releaseDate = textView4;
        this.songs = recyclerView;
        this.swipeLayout = swipeRefreshLayout;
    }

    public MediaLibraryItem getPlaylist() {
        return this.mPlaylist;
    }

    public BitmapDrawable getCover() {
        return this.mCover;
    }

    public Long getTotalDuration() {
        return this.mTotalDuration;
    }

    public String getReleaseYear() {
        return this.mReleaseYear;
    }

    public Integer getTopmargin() {
        return this.mTopmargin;
    }

    public static HeaderMediaListActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HeaderMediaListActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (HeaderMediaListActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.header_media_list_activity, viewGroup, z, obj);
    }

    public static HeaderMediaListActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HeaderMediaListActivityBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (HeaderMediaListActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.header_media_list_activity, (ViewGroup) null, false, obj);
    }

    public static HeaderMediaListActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HeaderMediaListActivityBinding bind(View view, Object obj) {
        return (HeaderMediaListActivityBinding) bind(obj, view, R.layout.header_media_list_activity);
    }
}
