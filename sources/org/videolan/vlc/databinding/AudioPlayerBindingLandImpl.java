package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.AudioPlayer;
import org.videolan.vlc.gui.video.VideoPlayerActivityKt;

public class AudioPlayerBindingLandImpl extends AudioPlayerBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mFragmentOnABRepeatResetClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mFragmentOnABRepeatStopClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl12 mFragmentOnJumpBackAndroidViewViewOnClickListener;
    private OnClickListenerImpl7 mFragmentOnJumpForwardAndroidViewViewOnClickListener;
    private OnClickListenerImpl4 mFragmentOnNextClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl9 mFragmentOnPlayPauseClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mFragmentOnPlaylistSwitchClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mFragmentOnPreviousClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mFragmentOnRepeatClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl6 mFragmentOnSearchClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl11 mFragmentOnShuffleClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mFragmentOnStopClickAndroidViewViewOnLongClickListener;
    private OnClickListenerImpl10 mFragmentOnTimeLabelClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl8 mFragmentShowAdvancedOptionsAndroidViewViewOnClickListener;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ab_repeat_container, 24);
        sparseIntArray.put(R.id.backgroundView, 25);
        sparseIntArray.put(R.id.bottom_gradient, 26);
        sparseIntArray.put(R.id.top_gradient, 27);
        sparseIntArray.put(R.id.guideline8, 28);
        sparseIntArray.put(R.id.guideline9, 29);
        sparseIntArray.put(R.id.progressBar, 30);
        sparseIntArray.put(R.id.guideline13, 31);
        sparseIntArray.put(R.id.header, 32);
        sparseIntArray.put(R.id.header_background, 33);
        sparseIntArray.put(R.id.header_divider, 34);
        sparseIntArray.put(R.id.audio_media_switcher, 35);
        sparseIntArray.put(R.id.playback_chips, 36);
        sparseIntArray.put(R.id.playback_speed_quick_action, 37);
        sparseIntArray.put(R.id.sleep_quick_action, 38);
        sparseIntArray.put(R.id.playlist_search_text, 39);
        sparseIntArray.put(R.id.barrier, 40);
        sparseIntArray.put(R.id.guideline14, 41);
        sparseIntArray.put(R.id.songs_list, 42);
        sparseIntArray.put(R.id.track_info_container, 43);
        sparseIntArray.put(R.id.previous_chapter, 44);
        sparseIntArray.put(R.id.song_title, 45);
        sparseIntArray.put(R.id.next_chapter, 46);
        sparseIntArray.put(R.id.song_subtitle, 47);
        sparseIntArray.put(R.id.song_track_info, 48);
        sparseIntArray.put(R.id.audio_rewind_text, 49);
        sparseIntArray.put(R.id.audio_forward_text, 50);
        sparseIntArray.put(R.id.audio_play_progress, 51);
        sparseIntArray.put(R.id.cover_media_switcher, 52);
        sparseIntArray.put(R.id.timeline, 53);
        sparseIntArray.put(R.id.centerGuideline, 54);
        sparseIntArray.put(R.id.hinge_go_left, 55);
        sparseIntArray.put(R.id.hinge_go_right, 56);
        sparseIntArray.put(R.id.player_options_container, 57);
        sparseIntArray.put(R.id.player_options_stub, 58);
        sparseIntArray.put(R.id.bookmarks_stub, 59);
        sparseIntArray.put(R.id.bookmark_marker_container, 60);
        sparseIntArray.put(R.id.ab_repeat_marker_guideline_container, 61);
        sparseIntArray.put(R.id.ab_repeat_marker_a, 62);
        sparseIntArray.put(R.id.ab_repeat_marker_b, 63);
    }

    public AudioPlayerBindingLandImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 64, sIncludes, sViewsWithIds));
    }

    private AudioPlayerBindingLandImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[24], objArr[62], objArr[63], objArr[22], objArr[23], objArr[61], objArr[6], objArr[7], objArr[10], objArr[14], objArr[50], objArr[35], objArr[51], objArr[13], objArr[49], objArr[25], objArr[40], objArr[60], objArr[59], objArr[26], objArr[54], objArr[0], objArr[52], objArr[31], objArr[41], objArr[28], objArr[29], (Guideline) null, objArr[32], objArr[33], objArr[34], objArr[3], objArr[4], objArr[12], objArr[2], objArr[5], objArr[1], objArr[11], objArr[55], objArr[56], objArr[16], objArr[20], objArr[46], objArr[19], objArr[36], objArr[37], objArr[57], objArr[58], objArr[8], objArr[39], objArr[9], objArr[18], objArr[44], objArr[30], objArr[21], objArr[17], objArr[38], objArr[47], objArr[45], objArr[48], objArr[42], (View) null, objArr[15], objArr[53], objArr[27], objArr[43]);
        this.mDirtyFlags = -1;
        this.abRepeatMarkerGuidelineA.setTag((Object) null);
        this.abRepeatMarkerGuidelineB.setTag((Object) null);
        this.abRepeatReset.setTag((Object) null);
        this.abRepeatStop.setTag((Object) null);
        this.advFunction.setTag((Object) null);
        this.audioForward10.setTag((Object) null);
        this.audioRewind10.setTag((Object) null);
        this.contentLayout.setTag((Object) null);
        this.headerLargePlayPause.setTag((Object) null);
        this.headerNext.setTag((Object) null);
        this.headerPlayPause.setTag((Object) null);
        this.headerPrevious.setTag((Object) null);
        this.headerRepeat.setTag((Object) null);
        this.headerShuffle.setTag((Object) null);
        this.headerTime.setTag((Object) null);
        this.length.setTag((Object) null);
        this.next.setTag((Object) null);
        this.playPause.setTag((Object) null);
        this.playlistSearch.setTag((Object) null);
        this.playlistSwitch.setTag((Object) null);
        this.previous.setTag((Object) null);
        this.repeat.setTag((Object) null);
        this.shuffle.setTag((Object) null);
        this.time.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (BR.fragment == i) {
            setFragment((AudioPlayer) obj);
        } else if (BR.ab_repeat_b == i) {
            setAbRepeatB((Float) obj);
        } else if (BR.can_shuffle == i) {
            setCanShuffle((Boolean) obj);
        } else if (BR.ab_repeat_a != i) {
            return false;
        } else {
            setAbRepeatA((Float) obj);
        }
        return true;
    }

    public void setFragment(AudioPlayer audioPlayer) {
        this.mFragment = audioPlayer;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.fragment);
        super.requestRebind();
    }

    public void setAbRepeatB(Float f) {
        this.mAbRepeatB = f;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.ab_repeat_b);
        super.requestRebind();
    }

    public void setCanShuffle(Boolean bool) {
        this.mCanShuffle = bool;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.can_shuffle);
        super.requestRebind();
    }

    public void setAbRepeatA(Float f) {
        this.mAbRepeatA = f;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.ab_repeat_a);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        OnClickListenerImpl3 onClickListenerImpl3;
        OnClickListenerImpl11 onClickListenerImpl11;
        OnClickListenerImpl10 onClickListenerImpl10;
        OnClickListenerImpl6 onClickListenerImpl6;
        OnClickListenerImpl onClickListenerImpl;
        OnClickListenerImpl5 onClickListenerImpl5;
        OnClickListenerImpl4 onClickListenerImpl4;
        OnClickListenerImpl7 onClickListenerImpl7;
        OnClickListenerImpl9 onClickListenerImpl9;
        OnClickListenerImpl1 onClickListenerImpl1;
        OnLongClickListenerImpl onLongClickListenerImpl;
        OnClickListenerImpl2 onClickListenerImpl2;
        OnClickListenerImpl8 onClickListenerImpl8;
        OnClickListenerImpl12 onClickListenerImpl12;
        int i;
        int i2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        AudioPlayer audioPlayer = this.mFragment;
        Float f = this.mAbRepeatB;
        Boolean bool = this.mCanShuffle;
        Float f2 = this.mAbRepeatA;
        if ((j & 17) == 0 || audioPlayer == null) {
            onClickListenerImpl9 = null;
            onClickListenerImpl12 = null;
            onClickListenerImpl8 = null;
            onClickListenerImpl2 = null;
            onLongClickListenerImpl = null;
            onClickListenerImpl1 = null;
            onClickListenerImpl7 = null;
            onClickListenerImpl4 = null;
            onClickListenerImpl5 = null;
            onClickListenerImpl = null;
            onClickListenerImpl6 = null;
            onClickListenerImpl10 = null;
            onClickListenerImpl11 = null;
            onClickListenerImpl3 = null;
        } else {
            OnClickListenerImpl onClickListenerImpl13 = this.mFragmentOnABRepeatResetClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl13 == null) {
                onClickListenerImpl13 = new OnClickListenerImpl();
                this.mFragmentOnABRepeatResetClickAndroidViewViewOnClickListener = onClickListenerImpl13;
            }
            OnClickListenerImpl value = onClickListenerImpl13.setValue(audioPlayer);
            OnClickListenerImpl1 onClickListenerImpl14 = this.mFragmentOnABRepeatStopClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl14 == null) {
                onClickListenerImpl14 = new OnClickListenerImpl1();
                this.mFragmentOnABRepeatStopClickAndroidViewViewOnClickListener = onClickListenerImpl14;
            }
            OnClickListenerImpl1 value2 = onClickListenerImpl14.setValue(audioPlayer);
            OnLongClickListenerImpl onLongClickListenerImpl2 = this.mFragmentOnStopClickAndroidViewViewOnLongClickListener;
            if (onLongClickListenerImpl2 == null) {
                onLongClickListenerImpl2 = new OnLongClickListenerImpl();
                this.mFragmentOnStopClickAndroidViewViewOnLongClickListener = onLongClickListenerImpl2;
            }
            OnLongClickListenerImpl value3 = onLongClickListenerImpl2.setValue(audioPlayer);
            OnClickListenerImpl2 onClickListenerImpl22 = this.mFragmentOnPreviousClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl22 == null) {
                onClickListenerImpl22 = new OnClickListenerImpl2();
                this.mFragmentOnPreviousClickAndroidViewViewOnClickListener = onClickListenerImpl22;
            }
            OnClickListenerImpl2 value4 = onClickListenerImpl22.setValue(audioPlayer);
            OnClickListenerImpl3 onClickListenerImpl32 = this.mFragmentOnPlaylistSwitchClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl32 == null) {
                onClickListenerImpl32 = new OnClickListenerImpl3();
                this.mFragmentOnPlaylistSwitchClickAndroidViewViewOnClickListener = onClickListenerImpl32;
            }
            OnClickListenerImpl3 value5 = onClickListenerImpl32.setValue(audioPlayer);
            OnClickListenerImpl4 onClickListenerImpl42 = this.mFragmentOnNextClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl42 == null) {
                onClickListenerImpl42 = new OnClickListenerImpl4();
                this.mFragmentOnNextClickAndroidViewViewOnClickListener = onClickListenerImpl42;
            }
            OnClickListenerImpl4 value6 = onClickListenerImpl42.setValue(audioPlayer);
            OnClickListenerImpl5 onClickListenerImpl52 = this.mFragmentOnRepeatClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl52 == null) {
                onClickListenerImpl52 = new OnClickListenerImpl5();
                this.mFragmentOnRepeatClickAndroidViewViewOnClickListener = onClickListenerImpl52;
            }
            OnClickListenerImpl5 value7 = onClickListenerImpl52.setValue(audioPlayer);
            OnClickListenerImpl6 onClickListenerImpl62 = this.mFragmentOnSearchClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl62 == null) {
                onClickListenerImpl62 = new OnClickListenerImpl6();
                this.mFragmentOnSearchClickAndroidViewViewOnClickListener = onClickListenerImpl62;
            }
            OnClickListenerImpl6 value8 = onClickListenerImpl62.setValue(audioPlayer);
            OnClickListenerImpl7 onClickListenerImpl72 = this.mFragmentOnJumpForwardAndroidViewViewOnClickListener;
            if (onClickListenerImpl72 == null) {
                onClickListenerImpl72 = new OnClickListenerImpl7();
                this.mFragmentOnJumpForwardAndroidViewViewOnClickListener = onClickListenerImpl72;
            }
            OnClickListenerImpl7 value9 = onClickListenerImpl72.setValue(audioPlayer);
            onClickListenerImpl6 = value8;
            OnClickListenerImpl8 onClickListenerImpl82 = this.mFragmentShowAdvancedOptionsAndroidViewViewOnClickListener;
            if (onClickListenerImpl82 == null) {
                onClickListenerImpl82 = new OnClickListenerImpl8();
                this.mFragmentShowAdvancedOptionsAndroidViewViewOnClickListener = onClickListenerImpl82;
            }
            OnClickListenerImpl8 value10 = onClickListenerImpl82.setValue(audioPlayer);
            OnClickListenerImpl9 onClickListenerImpl92 = this.mFragmentOnPlayPauseClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl92 == null) {
                onClickListenerImpl92 = new OnClickListenerImpl9();
                this.mFragmentOnPlayPauseClickAndroidViewViewOnClickListener = onClickListenerImpl92;
            }
            OnClickListenerImpl9 value11 = onClickListenerImpl92.setValue(audioPlayer);
            OnClickListenerImpl10 onClickListenerImpl102 = this.mFragmentOnTimeLabelClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl102 == null) {
                onClickListenerImpl102 = new OnClickListenerImpl10();
                this.mFragmentOnTimeLabelClickAndroidViewViewOnClickListener = onClickListenerImpl102;
            }
            OnClickListenerImpl10 value12 = onClickListenerImpl102.setValue(audioPlayer);
            OnClickListenerImpl11 onClickListenerImpl112 = this.mFragmentOnShuffleClickAndroidViewViewOnClickListener;
            if (onClickListenerImpl112 == null) {
                onClickListenerImpl112 = new OnClickListenerImpl11();
                this.mFragmentOnShuffleClickAndroidViewViewOnClickListener = onClickListenerImpl112;
            }
            OnClickListenerImpl11 value13 = onClickListenerImpl112.setValue(audioPlayer);
            OnClickListenerImpl12 onClickListenerImpl122 = this.mFragmentOnJumpBackAndroidViewViewOnClickListener;
            if (onClickListenerImpl122 == null) {
                onClickListenerImpl122 = new OnClickListenerImpl12();
                this.mFragmentOnJumpBackAndroidViewViewOnClickListener = onClickListenerImpl122;
            }
            onClickListenerImpl12 = onClickListenerImpl122.setValue(audioPlayer);
            onClickListenerImpl3 = value5;
            onClickListenerImpl8 = value10;
            onClickListenerImpl10 = value12;
            onClickListenerImpl = value;
            onClickListenerImpl9 = value11;
            onClickListenerImpl11 = value13;
            OnClickListenerImpl1 onClickListenerImpl15 = value2;
            onClickListenerImpl7 = value9;
            onClickListenerImpl2 = value4;
            onClickListenerImpl5 = value7;
            onClickListenerImpl1 = onClickListenerImpl15;
            OnLongClickListenerImpl onLongClickListenerImpl3 = value3;
            onClickListenerImpl4 = value6;
            onLongClickListenerImpl = onLongClickListenerImpl3;
        }
        float safeUnbox = (j & 18) != 0 ? ViewDataBinding.safeUnbox(f) : 0.0f;
        long j2 = j & 20;
        int i3 = 0;
        if (j2 != 0) {
            boolean safeUnbox2 = ViewDataBinding.safeUnbox(bool);
            if (j2 != 0) {
                j |= safeUnbox2 ? 64 : 32;
            }
            if (!safeUnbox2) {
                i3 = 4;
            }
            i = i3;
        } else {
            i = 0;
        }
        long j3 = j & 24;
        float safeUnbox3 = j3 != 0 ? ViewDataBinding.safeUnbox(f2) : 0.0f;
        if (j3 != 0) {
            i2 = i;
            VideoPlayerActivityKt.setConstraintPercent(this.abRepeatMarkerGuidelineA, safeUnbox3);
        } else {
            i2 = i;
        }
        if ((j & 18) != 0) {
            VideoPlayerActivityKt.setConstraintPercent(this.abRepeatMarkerGuidelineB, safeUnbox);
        }
        if ((17 & j) != 0) {
            this.abRepeatReset.setOnClickListener(onClickListenerImpl);
            this.abRepeatStop.setOnClickListener(onClickListenerImpl1);
            this.advFunction.setOnClickListener(onClickListenerImpl8);
            this.audioForward10.setOnClickListener(onClickListenerImpl7);
            this.audioRewind10.setOnClickListener(onClickListenerImpl12);
            this.headerLargePlayPause.setOnClickListener(onClickListenerImpl9);
            this.headerLargePlayPause.setOnLongClickListener(onLongClickListenerImpl);
            this.headerNext.setOnClickListener(onClickListenerImpl4);
            this.headerPlayPause.setOnClickListener(onClickListenerImpl9);
            this.headerPlayPause.setOnLongClickListener(onLongClickListenerImpl);
            this.headerPrevious.setOnClickListener(onClickListenerImpl2);
            this.headerRepeat.setOnClickListener(onClickListenerImpl5);
            OnClickListenerImpl11 onClickListenerImpl113 = onClickListenerImpl11;
            this.headerShuffle.setOnClickListener(onClickListenerImpl113);
            OnClickListenerImpl10 onClickListenerImpl103 = onClickListenerImpl10;
            this.headerTime.setOnClickListener(onClickListenerImpl103);
            this.length.setOnClickListener(onClickListenerImpl103);
            this.next.setOnClickListener(onClickListenerImpl4);
            this.playPause.setOnClickListener(onClickListenerImpl9);
            this.playPause.setOnLongClickListener(onLongClickListenerImpl);
            this.playlistSearch.setOnClickListener(onClickListenerImpl6);
            this.playlistSwitch.setOnClickListener(onClickListenerImpl3);
            this.previous.setOnClickListener(onClickListenerImpl2);
            this.repeat.setOnClickListener(onClickListenerImpl5);
            this.shuffle.setOnClickListener(onClickListenerImpl113);
            this.time.setOnClickListener(onClickListenerImpl103);
        }
        if ((j & 20) != 0) {
            this.shuffle.setVisibility(i2);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onABRepeatResetClick(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl1 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onABRepeatStopClick(view);
        }
    }

    public static class OnLongClickListenerImpl implements View.OnLongClickListener {
        private AudioPlayer value;

        public OnLongClickListenerImpl setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public boolean onLongClick(View view) {
            return this.value.onStopClick(view);
        }
    }

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl2 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onPreviousClick(view);
        }
    }

    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl3 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onPlaylistSwitchClick(view);
        }
    }

    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl4 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onNextClick(view);
        }
    }

    public static class OnClickListenerImpl5 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl5 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onRepeatClick(view);
        }
    }

    public static class OnClickListenerImpl6 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl6 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onSearchClick(view);
        }
    }

    public static class OnClickListenerImpl7 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl7 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onJumpForward(view);
        }
    }

    public static class OnClickListenerImpl8 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl8 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.showAdvancedOptions(view);
        }
    }

    public static class OnClickListenerImpl9 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl9 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onPlayPauseClick(view);
        }
    }

    public static class OnClickListenerImpl10 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl10 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onTimeLabelClick(view);
        }
    }

    public static class OnClickListenerImpl11 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl11 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onShuffleClick(view);
        }
    }

    public static class OnClickListenerImpl12 implements View.OnClickListener {
        private AudioPlayer value;

        public OnClickListenerImpl12 setValue(AudioPlayer audioPlayer) {
            this.value = audioPlayer;
            if (audioPlayer == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onJumpBack(view);
        }
    }
}
