package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.SeekBarBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LiveData;
import org.videolan.medialibrary.Tools;
import org.videolan.vlc.BR;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.generated.callback.OnClickListener;
import org.videolan.vlc.generated.callback.OnLongClickListener;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.gui.video.VideoPlayerActivityKt;
import org.videolan.vlc.media.Progress;

public class PlayerHudBindingImpl extends PlayerHudBinding implements OnClickListener.Listener, OnLongClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnLongClickListener mCallback10;
    private final View.OnClickListener mCallback11;
    private final View.OnClickListener mCallback4;
    private final View.OnClickListener mCallback5;
    private final View.OnClickListener mCallback6;
    private final View.OnClickListener mCallback7;
    private final View.OnClickListener mCallback8;
    private final View.OnClickListener mCallback9;
    private long mDirtyFlags;
    private OnClickListenerImpl mPlayerOnAudioSubClickAndroidViewViewOnClickListener;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ab_repeat_container, 12);
        sparseIntArray.put(R.id.stats_container, 13);
        sparseIntArray.put(R.id.stats_scrollview, 14);
        sparseIntArray.put(R.id.stats_scrollview_content, 15);
        sparseIntArray.put(R.id.info_grids, 16);
        sparseIntArray.put(R.id.stats_graphs, 17);
        sparseIntArray.put(R.id.video_stats_title, 18);
        sparseIntArray.put(R.id.plotView, 19);
        sparseIntArray.put(R.id.stats_close, 20);
        sparseIntArray.put(R.id.ab_repeat_reset, 21);
        sparseIntArray.put(R.id.ab_repeat_stop, 22);
        sparseIntArray.put(R.id.bookmarks_stub, 23);
        sparseIntArray.put(R.id.bookmark_marker_container, 24);
        sparseIntArray.put(R.id.ab_repeat_marker_guideline_container, 25);
        sparseIntArray.put(R.id.ab_repeat_marker_a, 26);
        sparseIntArray.put(R.id.ab_repeat_marker_b, 27);
        sparseIntArray.put(R.id.orientation_toggle, 28);
        sparseIntArray.put(R.id.player_space_left, 29);
        sparseIntArray.put(R.id.player_overlay_rewind, 30);
        sparseIntArray.put(R.id.player_overlay_rewind_text, 31);
        sparseIntArray.put(R.id.player_overlay_forward, 32);
        sparseIntArray.put(R.id.player_overlay_forward_text, 33);
        sparseIntArray.put(R.id.player_space_right, 34);
        sparseIntArray.put(R.id.swipe_to_unlock, 35);
    }

    public PlayerHudBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 36, sIncludes, sViewsWithIds));
    }

    private PlayerHudBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[12], objArr[26], objArr[27], objArr[4], objArr[5], objArr[25], objArr[21], objArr[22], objArr[24], objArr[23], objArr[16], objArr[28], objArr[11], objArr[32], objArr[33], objArr[2], objArr[8], objArr[30], objArr[31], objArr[3], objArr[1], objArr[6], objArr[10], objArr[29], objArr[34], objArr[9], objArr[7], objArr[19], objArr[0], objArr[20], objArr[13], objArr[17], objArr[14], objArr[15], objArr[35], objArr[18]);
        this.mDirtyFlags = -1;
        this.abRepeatMarkerGuidelineA.setTag((Object) null);
        this.abRepeatMarkerGuidelineB.setTag((Object) null);
        this.playerOverlayAdvFunction.setTag((Object) null);
        this.playerOverlayLength.setTag((Object) null);
        this.playerOverlayPlay.setTag((Object) null);
        this.playerOverlaySeekbar.setTag((Object) null);
        this.playerOverlayTime.setTag((Object) null);
        this.playerOverlayTracks.setTag((Object) null);
        this.playerResize.setTag((Object) null);
        this.playlistNext.setTag((Object) null);
        this.playlistPrevious.setTag((Object) null);
        this.progressOverlay.setTag((Object) null);
        setRootTag(view);
        this.mCallback11 = new OnClickListener(this, 8);
        this.mCallback6 = new OnClickListener(this, 3);
        this.mCallback9 = new OnClickListener(this, 6);
        this.mCallback5 = new OnClickListener(this, 2);
        this.mCallback8 = new OnClickListener(this, 5);
        this.mCallback4 = new OnClickListener(this, 1);
        this.mCallback10 = new OnLongClickListener(this, 7);
        this.mCallback7 = new OnClickListener(this, 4);
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
        if (BR.ab_repeat_b == i) {
            setAbRepeatB((Float) obj);
        } else if (BR.progress == i) {
            setProgress((LiveData) obj);
        } else if (BR.ab_repeat_a == i) {
            setAbRepeatA((Float) obj);
        } else if (BR.player != i) {
            return false;
        } else {
            setPlayer((VideoPlayerActivity) obj);
        }
        return true;
    }

    public void setAbRepeatB(Float f) {
        this.mAbRepeatB = f;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.ab_repeat_b);
        super.requestRebind();
    }

    public void setProgress(LiveData<Progress> liveData) {
        updateLiveDataRegistration(0, liveData);
        this.mProgress = liveData;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.progress);
        super.requestRebind();
    }

    public void setAbRepeatA(Float f) {
        this.mAbRepeatA = f;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.ab_repeat_a);
        super.requestRebind();
    }

    public void setPlayer(VideoPlayerActivity videoPlayerActivity) {
        this.mPlayer = videoPlayerActivity;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.player);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeProgress((LiveData) obj, i2);
    }

    private boolean onChangeProgress(LiveData<Progress> liveData, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        long j2;
        OnClickListenerImpl onClickListenerImpl;
        long j3;
        int i;
        String str;
        int i2;
        synchronized (this) {
            j = this.mDirtyFlags;
            j2 = 0;
            this.mDirtyFlags = 0;
        }
        Float f = this.mAbRepeatB;
        LiveData liveData = this.mProgress;
        Float f2 = this.mAbRepeatA;
        VideoPlayerActivity videoPlayerActivity = this.mPlayer;
        long j4 = 18 & j;
        float safeUnbox = j4 != 0 ? ViewDataBinding.safeUnbox(f) : 0.0f;
        long j5 = 25 & j;
        int i3 = 0;
        OnClickListenerImpl onClickListenerImpl2 = null;
        if (j5 != 0) {
            Progress progress = liveData != null ? (Progress) liveData.getValue() : null;
            PlaybackService service = videoPlayerActivity != null ? videoPlayerActivity.getService() : null;
            j3 = progress != null ? progress.getTime() : 0;
            i = service != null ? service.getTime(j3) : 0;
            long j6 = j & 17;
            String millisToString = j6 != 0 ? Tools.millisToString(j3) : null;
            if (j6 != 0) {
                if (progress != null) {
                    j2 = progress.getLength();
                }
                i3 = (int) j2;
            } else {
                j2 = 0;
            }
            if (!((j & 24) == 0 || videoPlayerActivity == null)) {
                OnClickListenerImpl onClickListenerImpl3 = this.mPlayerOnAudioSubClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl3 == null) {
                    onClickListenerImpl3 = new OnClickListenerImpl();
                    this.mPlayerOnAudioSubClickAndroidViewViewOnClickListener = onClickListenerImpl3;
                }
                onClickListenerImpl2 = onClickListenerImpl3.setValue(videoPlayerActivity);
            }
            i2 = i3;
            onClickListenerImpl = onClickListenerImpl2;
            str = millisToString;
        } else {
            str = null;
            onClickListenerImpl = null;
            j2 = 0;
            i2 = 0;
            i = 0;
            j3 = 0;
        }
        long j7 = j & 20;
        float safeUnbox2 = j7 != 0 ? ViewDataBinding.safeUnbox(f2) : 0.0f;
        int i4 = (j7 > 0 ? 1 : (j7 == 0 ? 0 : -1));
        int i5 = i;
        if (i4 != 0) {
            VideoPlayerActivityKt.setConstraintPercent(this.abRepeatMarkerGuidelineA, safeUnbox2);
        }
        if (j4 != 0) {
            VideoPlayerActivityKt.setConstraintPercent(this.abRepeatMarkerGuidelineB, safeUnbox);
        }
        if ((16 & j) != 0) {
            this.playerOverlayAdvFunction.setOnClickListener(this.mCallback11);
            this.playerOverlayLength.setOnClickListener(this.mCallback5);
            this.playerOverlayPlay.setOnClickListener(this.mCallback7);
            this.playerOverlayTime.setOnClickListener(this.mCallback4);
            this.playerResize.setOnClickListener(this.mCallback9);
            this.playerResize.setOnLongClickListener(this.mCallback10);
            this.playlistNext.setOnClickListener(this.mCallback8);
            this.playlistPrevious.setOnClickListener(this.mCallback6);
        }
        if ((j & 17) != 0) {
            VideoPlayerActivityKt.setPlaybackTime(this.playerOverlayLength, j2, j3);
            VideoPlayerActivityKt.setProgressMax(this.playerOverlaySeekbar, (long) i2);
            TextViewBindingAdapter.setText(this.playerOverlayTime, str);
        }
        if (j5 != 0) {
            SeekBarBindingAdapter.setProgress(this.playerOverlaySeekbar, i5);
        }
        if ((j & 24) != 0) {
            this.playerOverlayTracks.setOnClickListener(onClickListenerImpl);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private VideoPlayerActivity value;

        public OnClickListenerImpl setValue(VideoPlayerActivity videoPlayerActivity) {
            this.value = videoPlayerActivity;
            if (videoPlayerActivity == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onAudioSubClick(view);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        switch (i) {
            case 1:
                VideoPlayerActivity videoPlayerActivity = this.mPlayer;
                if (videoPlayerActivity != null) {
                    videoPlayerActivity.toggleTimeDisplay();
                    return;
                }
                return;
            case 2:
                VideoPlayerActivity videoPlayerActivity2 = this.mPlayer;
                if (videoPlayerActivity2 != null) {
                    videoPlayerActivity2.toggleTimeDisplay();
                    return;
                }
                return;
            case 3:
                VideoPlayerActivity videoPlayerActivity3 = this.mPlayer;
                if (videoPlayerActivity3 != null) {
                    videoPlayerActivity3.previous();
                    return;
                }
                return;
            case 4:
                VideoPlayerActivity videoPlayerActivity4 = this.mPlayer;
                if (videoPlayerActivity4 != null) {
                    videoPlayerActivity4.doPlayPause();
                    return;
                }
                return;
            case 5:
                VideoPlayerActivity videoPlayerActivity5 = this.mPlayer;
                if (videoPlayerActivity5 != null) {
                    videoPlayerActivity5.next();
                    return;
                }
                return;
            case 6:
                VideoPlayerActivity videoPlayerActivity6 = this.mPlayer;
                if (videoPlayerActivity6 != null) {
                    videoPlayerActivity6.resizeVideo();
                    return;
                }
                return;
            case 8:
                VideoPlayerActivity videoPlayerActivity7 = this.mPlayer;
                if (videoPlayerActivity7 != null) {
                    videoPlayerActivity7.showAdvancedOptions();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final boolean _internalCallbackOnLongClick(int i, View view) {
        VideoPlayerActivity videoPlayerActivity = this.mPlayer;
        if (videoPlayerActivity != null) {
            return videoPlayerActivity.displayResize();
        }
        return false;
    }
}
