package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.gui.view.SwipeRefreshLayout;

public abstract class AudioBrowserBinding extends ViewDataBinding {
    public final EmptyLoadingStateView audioEmptyLoading;
    @Bindable
    protected boolean mEmpty;
    public final ViewPager pager;
    public final FastScroller songsFastScroller;
    public final SwipeRefreshLayout swipeLayout;

    public abstract void setEmpty(boolean z);

    protected AudioBrowserBinding(Object obj, View view, int i, EmptyLoadingStateView emptyLoadingStateView, ViewPager viewPager, FastScroller fastScroller, SwipeRefreshLayout swipeRefreshLayout) {
        super(obj, view, i);
        this.audioEmptyLoading = emptyLoadingStateView;
        this.pager = viewPager;
        this.songsFastScroller = fastScroller;
        this.swipeLayout = swipeRefreshLayout;
    }

    public boolean getEmpty() {
        return this.mEmpty;
    }

    public static AudioBrowserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AudioBrowserBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_browser, viewGroup, z, obj);
    }

    public static AudioBrowserBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AudioBrowserBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_browser, (ViewGroup) null, false, obj);
    }

    public static AudioBrowserBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioBrowserBinding bind(View view, Object obj) {
        return (AudioBrowserBinding) bind(obj, view, R.layout.audio_browser);
    }
}
