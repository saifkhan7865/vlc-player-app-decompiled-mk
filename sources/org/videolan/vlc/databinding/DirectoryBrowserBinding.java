package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.gui.view.SwipeRefreshLayout;

public abstract class DirectoryBrowserBinding extends ViewDataBinding {
    public final RecyclerView ariane;
    public final FastScroller browserFastScroller;
    public final EmptyLoadingStateView emptyLoading;
    @Bindable
    protected boolean mShowFavorites;
    public final RecyclerView networkList;
    public final SwipeRefreshLayout swipeLayout;

    public abstract void setShowFavorites(boolean z);

    protected DirectoryBrowserBinding(Object obj, View view, int i, RecyclerView recyclerView, FastScroller fastScroller, EmptyLoadingStateView emptyLoadingStateView, RecyclerView recyclerView2, SwipeRefreshLayout swipeRefreshLayout) {
        super(obj, view, i);
        this.ariane = recyclerView;
        this.browserFastScroller = fastScroller;
        this.emptyLoading = emptyLoadingStateView;
        this.networkList = recyclerView2;
        this.swipeLayout = swipeRefreshLayout;
    }

    public boolean getShowFavorites() {
        return this.mShowFavorites;
    }

    public static DirectoryBrowserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DirectoryBrowserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DirectoryBrowserBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.directory_browser, viewGroup, z, obj);
    }

    public static DirectoryBrowserBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DirectoryBrowserBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DirectoryBrowserBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.directory_browser, (ViewGroup) null, false, obj);
    }

    public static DirectoryBrowserBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DirectoryBrowserBinding bind(View view, Object obj) {
        return (DirectoryBrowserBinding) bind(obj, view, R.layout.directory_browser);
    }
}
