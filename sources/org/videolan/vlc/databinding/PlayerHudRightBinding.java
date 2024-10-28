package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.chip.Chip;
import org.videolan.vlc.R;

public abstract class PlayerHudRightBinding extends ViewDataBinding {
    public final Chip audioDelayQuickAction;
    public final TextClock clock;
    public final ConstraintLayout hudRightOverlay;
    public final Barrier iconBarrier;
    public final Chip playbackSpeedQuickAction;
    public final ImageView playerOverlayNavmenu;
    public final TextView playerOverlayTitle;
    public final ImageView playerScreenshot;
    public final ImageView playlistToggle;
    public final HorizontalScrollView quickActionsContainer;
    public final Chip sleepQuickAction;
    public final Chip spuDelayQuickAction;
    public final ImageView videoRenderer;
    public final ImageView videoSecondaryDisplay;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected PlayerHudRightBinding(Object obj, View view, int i, Chip chip, TextClock textClock, ConstraintLayout constraintLayout, Barrier barrier, Chip chip2, ImageView imageView, TextView textView, ImageView imageView2, ImageView imageView3, HorizontalScrollView horizontalScrollView, Chip chip3, Chip chip4, ImageView imageView4, ImageView imageView5) {
        super(obj, view, i);
        this.audioDelayQuickAction = chip;
        this.clock = textClock;
        this.hudRightOverlay = constraintLayout;
        this.iconBarrier = barrier;
        this.playbackSpeedQuickAction = chip2;
        this.playerOverlayNavmenu = imageView;
        this.playerOverlayTitle = textView;
        this.playerScreenshot = imageView2;
        this.playlistToggle = imageView3;
        this.quickActionsContainer = horizontalScrollView;
        this.sleepQuickAction = chip3;
        this.spuDelayQuickAction = chip4;
        this.videoRenderer = imageView4;
        this.videoSecondaryDisplay = imageView5;
    }

    public static PlayerHudRightBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerHudRightBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PlayerHudRightBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.player_hud_right, viewGroup, z, obj);
    }

    public static PlayerHudRightBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerHudRightBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PlayerHudRightBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.player_hud_right, (ViewGroup) null, false, obj);
    }

    public static PlayerHudRightBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerHudRightBinding bind(View view, Object obj) {
        return (PlayerHudRightBinding) bind(obj, view, R.layout.player_hud_right);
    }
}
