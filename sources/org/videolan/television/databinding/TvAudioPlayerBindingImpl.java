package org.videolan.television.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.SeekBarBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LiveData;
import org.videolan.television.BR;
import org.videolan.television.R;
import org.videolan.vlc.viewmodels.PlaybackProgress;

public class TvAudioPlayerBindingImpl extends TvAudioPlayerBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.background, 4);
        sparseIntArray.put(R.id.playlist, 5);
        sparseIntArray.put(R.id.album_cover, 6);
        sparseIntArray.put(R.id.media_title, 7);
        sparseIntArray.put(R.id.media_artist, 8);
        sparseIntArray.put(R.id.bookmarks_stub, 9);
        sparseIntArray.put(R.id.guideline8, 10);
        sparseIntArray.put(R.id.playback_speed_quick_action, 11);
        sparseIntArray.put(R.id.playback_speed_quick_action_text, 12);
        sparseIntArray.put(R.id.sleep_quick_action, 13);
        sparseIntArray.put(R.id.sleep_quick_action_text, 14);
        sparseIntArray.put(R.id.barrier, 15);
        sparseIntArray.put(R.id.bookmark_marker_container, 16);
        sparseIntArray.put(R.id.button_shuffle, 17);
        sparseIntArray.put(R.id.button_previous, 18);
        sparseIntArray.put(R.id.button_play, 19);
        sparseIntArray.put(R.id.button_next, 20);
        sparseIntArray.put(R.id.button_repeat, 21);
        sparseIntArray.put(R.id.button_more, 22);
        sparseIntArray.put(R.id.player_options_stub, 23);
    }

    public TvAudioPlayerBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 24, sIncludes, sViewsWithIds));
    }

    private TvAudioPlayerBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[6], objArr[4], objArr[15], objArr[16], objArr[9], objArr[22], objArr[20], objArr[19], objArr[18], objArr[21], objArr[17], objArr[10], objArr[8], objArr[3], objArr[2], objArr[1], objArr[7], objArr[11], objArr[12], objArr[23], objArr[5], objArr[13], objArr[14]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.mediaLength.setTag((Object) null);
        this.mediaProgress.setTag((Object) null);
        this.mediaTime.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (BR.progress != i) {
            return false;
        }
        setProgress((LiveData) obj);
        return true;
    }

    public void setProgress(LiveData<PlaybackProgress> liveData) {
        updateLiveDataRegistration(0, liveData);
        this.mProgress = liveData;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.progress);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeProgress((LiveData) obj, i2);
    }

    private boolean onChangeProgress(LiveData<PlaybackProgress> liveData, int i) {
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
        int i;
        String str;
        int i2;
        long j2;
        String str2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        LiveData liveData = this.mProgress;
        long j4 = j & 3;
        String str3 = null;
        if (j4 != 0) {
            PlaybackProgress playbackProgress = liveData != null ? (PlaybackProgress) liveData.getValue() : null;
            if (playbackProgress != null) {
                str3 = playbackProgress.getTimeText();
                j3 = playbackProgress.getLength();
                str2 = playbackProgress.getLengthText();
                j2 = playbackProgress.getTime();
            } else {
                j3 = 0;
                j2 = 0;
                str2 = null;
            }
            i2 = (int) j3;
            i = (int) j2;
            str = str3;
            str3 = str2;
        } else {
            i2 = 0;
            str = null;
            i = 0;
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mediaLength, str3);
            this.mediaProgress.setMax(i2);
            SeekBarBindingAdapter.setProgress(this.mediaProgress, i);
            TextViewBindingAdapter.setText(this.mediaTime, str);
        }
    }
}
