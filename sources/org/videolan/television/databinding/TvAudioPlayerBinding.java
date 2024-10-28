package org.videolan.television.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import org.videolan.television.R;
import org.videolan.vlc.gui.view.AccessibleSeekBar;
import org.videolan.vlc.viewmodels.PlaybackProgress;

public abstract class TvAudioPlayerBinding extends ViewDataBinding {
    public final AppCompatImageView albumCover;
    public final AppCompatImageView background;
    public final Barrier barrier;
    public final ConstraintLayout bookmarkMarkerContainer;
    public final ViewStubCompat bookmarksStub;
    public final AppCompatImageView buttonMore;
    public final AppCompatImageView buttonNext;
    public final AppCompatImageView buttonPlay;
    public final AppCompatImageView buttonPrevious;
    public final AppCompatImageView buttonRepeat;
    public final AppCompatImageView buttonShuffle;
    public final Guideline guideline8;
    @Bindable
    protected LiveData<PlaybackProgress> mProgress;
    public final TextView mediaArtist;
    public final TextView mediaLength;
    public final AccessibleSeekBar mediaProgress;
    public final TextView mediaTime;
    public final TextView mediaTitle;
    public final LinearLayout playbackSpeedQuickAction;
    public final TextView playbackSpeedQuickActionText;
    public final ViewStubCompat playerOptionsStub;
    public final RecyclerView playlist;
    public final LinearLayout sleepQuickAction;
    public final TextView sleepQuickActionText;

    public abstract void setProgress(LiveData<PlaybackProgress> liveData);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected TvAudioPlayerBinding(Object obj, View view, int i, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, Barrier barrier2, ConstraintLayout constraintLayout, ViewStubCompat viewStubCompat, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, AppCompatImageView appCompatImageView5, AppCompatImageView appCompatImageView6, AppCompatImageView appCompatImageView7, AppCompatImageView appCompatImageView8, Guideline guideline, TextView textView, TextView textView2, AccessibleSeekBar accessibleSeekBar, TextView textView3, TextView textView4, LinearLayout linearLayout, TextView textView5, ViewStubCompat viewStubCompat2, RecyclerView recyclerView, LinearLayout linearLayout2, TextView textView6) {
        super(obj, view, i);
        this.albumCover = appCompatImageView;
        this.background = appCompatImageView2;
        this.barrier = barrier2;
        this.bookmarkMarkerContainer = constraintLayout;
        this.bookmarksStub = viewStubCompat;
        this.buttonMore = appCompatImageView3;
        this.buttonNext = appCompatImageView4;
        this.buttonPlay = appCompatImageView5;
        this.buttonPrevious = appCompatImageView6;
        this.buttonRepeat = appCompatImageView7;
        this.buttonShuffle = appCompatImageView8;
        this.guideline8 = guideline;
        this.mediaArtist = textView;
        this.mediaLength = textView2;
        this.mediaProgress = accessibleSeekBar;
        this.mediaTime = textView3;
        this.mediaTitle = textView4;
        this.playbackSpeedQuickAction = linearLayout;
        this.playbackSpeedQuickActionText = textView5;
        this.playerOptionsStub = viewStubCompat2;
        this.playlist = recyclerView;
        this.sleepQuickAction = linearLayout2;
        this.sleepQuickActionText = textView6;
    }

    public LiveData<PlaybackProgress> getProgress() {
        return this.mProgress;
    }

    public static TvAudioPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TvAudioPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (TvAudioPlayerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tv_audio_player, viewGroup, z, obj);
    }

    public static TvAudioPlayerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TvAudioPlayerBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (TvAudioPlayerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tv_audio_player, (ViewGroup) null, false, obj);
    }

    public static TvAudioPlayerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TvAudioPlayerBinding bind(View view, Object obj) {
        return (TvAudioPlayerBinding) bind(obj, view, R.layout.tv_audio_player);
    }
}
