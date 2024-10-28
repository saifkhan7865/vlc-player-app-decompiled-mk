package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.CollapsibleLinearLayout;

public abstract class PlayerOverlayTrackItemBinding extends ViewDataBinding {
    public final TextView emptyView;
    public final CollapsibleLinearLayout options;
    public final FrameLayout optionsContainer;
    public final ConstraintLayout trackContainer;
    public final RecyclerView trackList;
    public final ImageView trackMore;
    public final TextView trackTitle;

    protected PlayerOverlayTrackItemBinding(Object obj, View view, int i, TextView textView, CollapsibleLinearLayout collapsibleLinearLayout, FrameLayout frameLayout, ConstraintLayout constraintLayout, RecyclerView recyclerView, ImageView imageView, TextView textView2) {
        super(obj, view, i);
        this.emptyView = textView;
        this.options = collapsibleLinearLayout;
        this.optionsContainer = frameLayout;
        this.trackContainer = constraintLayout;
        this.trackList = recyclerView;
        this.trackMore = imageView;
        this.trackTitle = textView2;
    }

    public static PlayerOverlayTrackItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerOverlayTrackItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PlayerOverlayTrackItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.player_overlay_track_item, viewGroup, z, obj);
    }

    public static PlayerOverlayTrackItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerOverlayTrackItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PlayerOverlayTrackItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.player_overlay_track_item, (ViewGroup) null, false, obj);
    }

    public static PlayerOverlayTrackItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerOverlayTrackItemBinding bind(View view, Object obj) {
        return (PlayerOverlayTrackItemBinding) bind(obj, view, R.layout.player_overlay_track_item);
    }
}
