package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class PlayerOverlayTracksBinding extends ViewDataBinding {
    public final PlayerOverlayTrackItemBinding audioTracks;
    public final ConstraintLayout playerOverlayTracks;
    public final PlayerOverlayTrackItemBinding subtitleTracks;
    public final View tracksSeparator2;
    public final View tracksSeparator3;
    public final PlayerOverlayTrackItemBinding videoTracks;

    protected PlayerOverlayTracksBinding(Object obj, View view, int i, PlayerOverlayTrackItemBinding playerOverlayTrackItemBinding, ConstraintLayout constraintLayout, PlayerOverlayTrackItemBinding playerOverlayTrackItemBinding2, View view2, View view3, PlayerOverlayTrackItemBinding playerOverlayTrackItemBinding3) {
        super(obj, view, i);
        this.audioTracks = playerOverlayTrackItemBinding;
        this.playerOverlayTracks = constraintLayout;
        this.subtitleTracks = playerOverlayTrackItemBinding2;
        this.tracksSeparator2 = view2;
        this.tracksSeparator3 = view3;
        this.videoTracks = playerOverlayTrackItemBinding3;
    }

    public static PlayerOverlayTracksBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerOverlayTracksBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PlayerOverlayTracksBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.player_overlay_tracks, viewGroup, z, obj);
    }

    public static PlayerOverlayTracksBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerOverlayTracksBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PlayerOverlayTracksBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.player_overlay_tracks, (ViewGroup) null, false, obj);
    }

    public static PlayerOverlayTracksBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerOverlayTracksBinding bind(View view, Object obj) {
        return (PlayerOverlayTracksBinding) bind(obj, view, R.layout.player_overlay_tracks);
    }
}
