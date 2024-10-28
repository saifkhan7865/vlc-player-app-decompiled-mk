package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.gui.view.SwipeRefreshLayout;

public abstract class PlaylistsFragmentBinding extends ViewDataBinding {
    public final EmptyLoadingStateView emptyLoading;
    public final View playlistList;
    public final FastScroller songsFastScrollerPlaylist;
    public final SwipeRefreshLayout swipeLayout;

    protected PlaylistsFragmentBinding(Object obj, View view, int i, EmptyLoadingStateView emptyLoadingStateView, View view2, FastScroller fastScroller, SwipeRefreshLayout swipeRefreshLayout) {
        super(obj, view, i);
        this.emptyLoading = emptyLoadingStateView;
        this.playlistList = view2;
        this.songsFastScrollerPlaylist = fastScroller;
        this.swipeLayout = swipeRefreshLayout;
    }

    public static PlaylistsFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlaylistsFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PlaylistsFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.playlists_fragment, viewGroup, z, obj);
    }

    public static PlaylistsFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlaylistsFragmentBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PlaylistsFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.playlists_fragment, (ViewGroup) null, false, obj);
    }

    public static PlaylistsFragmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlaylistsFragmentBinding bind(View view, Object obj) {
        return (PlaylistsFragmentBinding) bind(obj, view, R.layout.playlists_fragment);
    }
}
