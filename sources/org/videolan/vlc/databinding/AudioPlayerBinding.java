package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.AudioPlayer;
import org.videolan.vlc.gui.view.AccessibleSeekBar;
import org.videolan.vlc.gui.view.CoverMediaSwitcher;
import org.videolan.vlc.gui.view.HeaderMediaSwitcher;

public abstract class AudioPlayerBinding extends ViewDataBinding {
    public final View abRepeatContainer;
    public final ImageView abRepeatMarkerA;
    public final ImageView abRepeatMarkerB;
    public final Guideline abRepeatMarkerGuidelineA;
    public final Guideline abRepeatMarkerGuidelineB;
    public final ConstraintLayout abRepeatMarkerGuidelineContainer;
    public final ImageView abRepeatReset;
    public final ImageView abRepeatStop;
    public final ImageView advFunction;
    public final ImageView audioForward10;
    public final TextView audioForwardText;
    public final HeaderMediaSwitcher audioMediaSwitcher;
    public final TextView audioPlayProgress;
    public final ImageView audioRewind10;
    public final TextView audioRewindText;
    public final ImageView backgroundView;
    public final Barrier barrier;
    public final ConstraintLayout bookmarkMarkerContainer;
    public final ViewStubCompat bookmarksStub;
    public final View bottomGradient;
    public final Guideline centerGuideline;
    public final ConstraintLayout contentLayout;
    public final CoverMediaSwitcher coverMediaSwitcher;
    public final Guideline guideline13;
    public final Guideline guideline14;
    public final Guideline guideline8;
    public final Guideline guideline9;
    public final Guideline guidelineHeaderBottom;
    public final ConstraintLayout header;
    public final View headerBackground;
    public final View headerDivider;
    public final AppCompatImageView headerLargePlayPause;
    public final ImageView headerNext;
    public final ImageView headerPlayPause;
    public final ImageView headerPrevious;
    public final ImageView headerRepeat;
    public final ImageView headerShuffle;
    public final TextView headerTime;
    public final ImageView hingeGoLeft;
    public final ImageView hingeGoRight;
    public final TextView length;
    @Bindable
    protected Float mAbRepeatA;
    @Bindable
    protected Float mAbRepeatB;
    @Bindable
    protected Boolean mCanShuffle;
    @Bindable
    protected AudioPlayer mFragment;
    public final ImageView next;
    public final ImageView nextChapter;
    public final AppCompatImageView playPause;
    public final ChipGroup playbackChips;
    public final Chip playbackSpeedQuickAction;
    public final FrameLayout playerOptionsContainer;
    public final ViewStubCompat playerOptionsStub;
    public final ImageView playlistSearch;
    public final TextInputLayout playlistSearchText;
    public final ImageView playlistSwitch;
    public final ImageView previous;
    public final ImageView previousChapter;
    public final ProgressBar progressBar;
    public final ImageView repeat;
    public final ImageView shuffle;
    public final Chip sleepQuickAction;
    public final TextView songSubtitle;
    public final TextView songTitle;
    public final TextView songTrackInfo;
    public final RecyclerView songsList;
    public final View songsListGuide;
    public final TextView time;
    public final AccessibleSeekBar timeline;
    public final View topGradient;
    public final ConstraintLayout trackInfoContainer;

    public abstract void setAbRepeatA(Float f);

    public abstract void setAbRepeatB(Float f);

    public abstract void setCanShuffle(Boolean bool);

    public abstract void setFragment(AudioPlayer audioPlayer);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudioPlayerBinding(Object obj, View view, int i, View view2, ImageView imageView, ImageView imageView2, Guideline guideline, Guideline guideline2, ConstraintLayout constraintLayout, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, TextView textView, HeaderMediaSwitcher headerMediaSwitcher, TextView textView2, ImageView imageView7, TextView textView3, ImageView imageView8, Barrier barrier2, ConstraintLayout constraintLayout2, ViewStubCompat viewStubCompat, View view3, Guideline guideline3, ConstraintLayout constraintLayout3, CoverMediaSwitcher coverMediaSwitcher2, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline10, ConstraintLayout constraintLayout4, View view4, View view5, AppCompatImageView appCompatImageView, ImageView imageView9, ImageView imageView10, ImageView imageView11, ImageView imageView12, ImageView imageView13, TextView textView4, ImageView imageView14, ImageView imageView15, TextView textView5, ImageView imageView16, ImageView imageView17, AppCompatImageView appCompatImageView2, ChipGroup chipGroup, Chip chip, FrameLayout frameLayout, ViewStubCompat viewStubCompat2, ImageView imageView18, TextInputLayout textInputLayout, ImageView imageView19, ImageView imageView20, ImageView imageView21, ProgressBar progressBar2, ImageView imageView22, ImageView imageView23, Chip chip2, TextView textView6, TextView textView7, TextView textView8, RecyclerView recyclerView, View view6, TextView textView9, AccessibleSeekBar accessibleSeekBar, View view7, ConstraintLayout constraintLayout5) {
        super(obj, view, i);
        this.abRepeatContainer = view2;
        this.abRepeatMarkerA = imageView;
        this.abRepeatMarkerB = imageView2;
        this.abRepeatMarkerGuidelineA = guideline;
        this.abRepeatMarkerGuidelineB = guideline2;
        this.abRepeatMarkerGuidelineContainer = constraintLayout;
        this.abRepeatReset = imageView3;
        this.abRepeatStop = imageView4;
        this.advFunction = imageView5;
        this.audioForward10 = imageView6;
        this.audioForwardText = textView;
        this.audioMediaSwitcher = headerMediaSwitcher;
        this.audioPlayProgress = textView2;
        this.audioRewind10 = imageView7;
        this.audioRewindText = textView3;
        this.backgroundView = imageView8;
        this.barrier = barrier2;
        this.bookmarkMarkerContainer = constraintLayout2;
        this.bookmarksStub = viewStubCompat;
        this.bottomGradient = view3;
        this.centerGuideline = guideline3;
        this.contentLayout = constraintLayout3;
        this.coverMediaSwitcher = coverMediaSwitcher2;
        this.guideline13 = guideline4;
        this.guideline14 = guideline5;
        this.guideline8 = guideline6;
        this.guideline9 = guideline7;
        this.guidelineHeaderBottom = guideline10;
        this.header = constraintLayout4;
        this.headerBackground = view4;
        this.headerDivider = view5;
        this.headerLargePlayPause = appCompatImageView;
        this.headerNext = imageView9;
        this.headerPlayPause = imageView10;
        this.headerPrevious = imageView11;
        this.headerRepeat = imageView12;
        this.headerShuffle = imageView13;
        this.headerTime = textView4;
        this.hingeGoLeft = imageView14;
        this.hingeGoRight = imageView15;
        this.length = textView5;
        this.next = imageView16;
        this.nextChapter = imageView17;
        this.playPause = appCompatImageView2;
        this.playbackChips = chipGroup;
        this.playbackSpeedQuickAction = chip;
        this.playerOptionsContainer = frameLayout;
        this.playerOptionsStub = viewStubCompat2;
        this.playlistSearch = imageView18;
        this.playlistSearchText = textInputLayout;
        this.playlistSwitch = imageView19;
        this.previous = imageView20;
        this.previousChapter = imageView21;
        this.progressBar = progressBar2;
        this.repeat = imageView22;
        this.shuffle = imageView23;
        this.sleepQuickAction = chip2;
        this.songSubtitle = textView6;
        this.songTitle = textView7;
        this.songTrackInfo = textView8;
        this.songsList = recyclerView;
        this.songsListGuide = view6;
        this.time = textView9;
        this.timeline = accessibleSeekBar;
        this.topGradient = view7;
        this.trackInfoContainer = constraintLayout5;
    }

    public AudioPlayer getFragment() {
        return this.mFragment;
    }

    public Float getAbRepeatA() {
        return this.mAbRepeatA;
    }

    public Float getAbRepeatB() {
        return this.mAbRepeatB;
    }

    public Boolean getCanShuffle() {
        return this.mCanShuffle;
    }

    public static AudioPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AudioPlayerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_player, viewGroup, z, obj);
    }

    public static AudioPlayerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioPlayerBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AudioPlayerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.audio_player, (ViewGroup) null, false, obj);
    }

    public static AudioPlayerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudioPlayerBinding bind(View view, Object obj) {
        return (AudioPlayerBinding) bind(obj, view, R.layout.audio_player);
    }
}
