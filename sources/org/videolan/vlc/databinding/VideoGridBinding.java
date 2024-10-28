package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.AutoFitRecyclerView;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.gui.view.SwipeRefreshLayout;

public abstract class VideoGridBinding extends ViewDataBinding {
    public final EmptyLoadingStateView emptyLoading;
    public final FastScroller fastScroller;
    @Bindable
    protected boolean mEmpty;
    public final SwipeRefreshLayout swipeLayout;
    public final AutoFitRecyclerView videoGrid;

    public abstract void setEmpty(boolean z);

    protected VideoGridBinding(Object obj, View view, int i, EmptyLoadingStateView emptyLoadingStateView, FastScroller fastScroller2, SwipeRefreshLayout swipeRefreshLayout, AutoFitRecyclerView autoFitRecyclerView) {
        super(obj, view, i);
        this.emptyLoading = emptyLoadingStateView;
        this.fastScroller = fastScroller2;
        this.swipeLayout = swipeRefreshLayout;
        this.videoGrid = autoFitRecyclerView;
    }

    public boolean getEmpty() {
        return this.mEmpty;
    }

    public static VideoGridBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoGridBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (VideoGridBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.video_grid, viewGroup, z, obj);
    }

    public static VideoGridBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoGridBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (VideoGridBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.video_grid, (ViewGroup) null, false, obj);
    }

    public static VideoGridBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoGridBinding bind(View view, Object obj) {
        return (VideoGridBinding) bind(obj, view, R.layout.video_grid);
    }
}
