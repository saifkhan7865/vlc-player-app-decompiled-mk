package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import org.videolan.liveplotgraph.PlotView;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.gui.view.AccessibleSeekBar;
import org.videolan.vlc.gui.view.FocusableTextView;
import org.videolan.vlc.gui.view.SwipeToUnlockView;
import org.videolan.vlc.media.Progress;

public abstract class PlayerHudBinding extends ViewDataBinding {
    public final View abRepeatContainer;
    public final ImageView abRepeatMarkerA;
    public final ImageView abRepeatMarkerB;
    public final Guideline abRepeatMarkerGuidelineA;
    public final Guideline abRepeatMarkerGuidelineB;
    public final ConstraintLayout abRepeatMarkerGuidelineContainer;
    public final ImageView abRepeatReset;
    public final ImageView abRepeatStop;
    public final ConstraintLayout bookmarkMarkerContainer;
    public final ViewStubCompat bookmarksStub;
    public final LinearLayout infoGrids;
    @Bindable
    protected Float mAbRepeatA;
    @Bindable
    protected Float mAbRepeatB;
    @Bindable
    protected VideoPlayerActivity mPlayer;
    @Bindable
    protected LiveData<Progress> mProgress;
    public final ImageView orientationToggle;
    public final ImageView playerOverlayAdvFunction;
    public final ImageView playerOverlayForward;
    public final TextView playerOverlayForwardText;
    public final FocusableTextView playerOverlayLength;
    public final ImageView playerOverlayPlay;
    public final ImageView playerOverlayRewind;
    public final TextView playerOverlayRewindText;
    public final AccessibleSeekBar playerOverlaySeekbar;
    public final FocusableTextView playerOverlayTime;
    public final ImageView playerOverlayTracks;
    public final ImageView playerResize;
    public final Space playerSpaceLeft;
    public final Space playerSpaceRight;
    public final ImageView playlistNext;
    public final ImageView playlistPrevious;
    public final PlotView plotView;
    public final ConstraintLayout progressOverlay;
    public final ImageView statsClose;
    public final ConstraintLayout statsContainer;
    public final ConstraintLayout statsGraphs;
    public final NestedScrollView statsScrollview;
    public final ConstraintLayout statsScrollviewContent;
    public final SwipeToUnlockView swipeToUnlock;
    public final TextView videoStatsTitle;

    public abstract void setAbRepeatA(Float f);

    public abstract void setAbRepeatB(Float f);

    public abstract void setPlayer(VideoPlayerActivity videoPlayerActivity);

    public abstract void setProgress(LiveData<Progress> liveData);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected PlayerHudBinding(Object obj, View view, int i, View view2, ImageView imageView, ImageView imageView2, Guideline guideline, Guideline guideline2, ConstraintLayout constraintLayout, ImageView imageView3, ImageView imageView4, ConstraintLayout constraintLayout2, ViewStubCompat viewStubCompat, LinearLayout linearLayout, ImageView imageView5, ImageView imageView6, ImageView imageView7, TextView textView, FocusableTextView focusableTextView, ImageView imageView8, ImageView imageView9, TextView textView2, AccessibleSeekBar accessibleSeekBar, FocusableTextView focusableTextView2, ImageView imageView10, ImageView imageView11, Space space, Space space2, ImageView imageView12, ImageView imageView13, PlotView plotView2, ConstraintLayout constraintLayout3, ImageView imageView14, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, NestedScrollView nestedScrollView, ConstraintLayout constraintLayout6, SwipeToUnlockView swipeToUnlockView, TextView textView3) {
        super(obj, view, i);
        this.abRepeatContainer = view2;
        this.abRepeatMarkerA = imageView;
        this.abRepeatMarkerB = imageView2;
        this.abRepeatMarkerGuidelineA = guideline;
        this.abRepeatMarkerGuidelineB = guideline2;
        this.abRepeatMarkerGuidelineContainer = constraintLayout;
        this.abRepeatReset = imageView3;
        this.abRepeatStop = imageView4;
        this.bookmarkMarkerContainer = constraintLayout2;
        this.bookmarksStub = viewStubCompat;
        this.infoGrids = linearLayout;
        this.orientationToggle = imageView5;
        this.playerOverlayAdvFunction = imageView6;
        this.playerOverlayForward = imageView7;
        this.playerOverlayForwardText = textView;
        this.playerOverlayLength = focusableTextView;
        this.playerOverlayPlay = imageView8;
        this.playerOverlayRewind = imageView9;
        this.playerOverlayRewindText = textView2;
        this.playerOverlaySeekbar = accessibleSeekBar;
        this.playerOverlayTime = focusableTextView2;
        this.playerOverlayTracks = imageView10;
        this.playerResize = imageView11;
        this.playerSpaceLeft = space;
        this.playerSpaceRight = space2;
        this.playlistNext = imageView12;
        this.playlistPrevious = imageView13;
        this.plotView = plotView2;
        this.progressOverlay = constraintLayout3;
        this.statsClose = imageView14;
        this.statsContainer = constraintLayout4;
        this.statsGraphs = constraintLayout5;
        this.statsScrollview = nestedScrollView;
        this.statsScrollviewContent = constraintLayout6;
        this.swipeToUnlock = swipeToUnlockView;
        this.videoStatsTitle = textView3;
    }

    public LiveData<Progress> getProgress() {
        return this.mProgress;
    }

    public Float getAbRepeatA() {
        return this.mAbRepeatA;
    }

    public Float getAbRepeatB() {
        return this.mAbRepeatB;
    }

    public VideoPlayerActivity getPlayer() {
        return this.mPlayer;
    }

    public static PlayerHudBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerHudBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (PlayerHudBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.player_hud, viewGroup, z, obj);
    }

    public static PlayerHudBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerHudBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (PlayerHudBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.player_hud, (ViewGroup) null, false, obj);
    }

    public static PlayerHudBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PlayerHudBinding bind(View view, Object obj) {
        return (PlayerHudBinding) bind(obj, view, R.layout.player_hud);
    }
}
