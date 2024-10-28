package androidx.leanback.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import androidx.leanback.media.PlaybackGlue;
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ControlButtonPresenterSelector;
import androidx.leanback.widget.OnActionClickedListener;
import androidx.leanback.widget.PlaybackControlsRow;
import androidx.leanback.widget.PlaybackControlsRowPresenter;
import androidx.leanback.widget.PlaybackRowPresenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.SparseArrayObjectAdapter;
import java.lang.ref.WeakReference;
import java.util.List;

public abstract class PlaybackControlGlue extends PlaybackGlue implements OnActionClickedListener, View.OnKeyListener {
    public static final int ACTION_CUSTOM_LEFT_FIRST = 1;
    public static final int ACTION_CUSTOM_RIGHT_FIRST = 4096;
    public static final int ACTION_FAST_FORWARD = 128;
    public static final int ACTION_PLAY_PAUSE = 64;
    public static final int ACTION_REWIND = 32;
    public static final int ACTION_SKIP_TO_NEXT = 256;
    public static final int ACTION_SKIP_TO_PREVIOUS = 16;
    static final boolean DEBUG = false;
    static final int MSG_UPDATE_PLAYBACK_STATE = 100;
    private static final int NUMBER_OF_SEEK_SPEEDS = 5;
    public static final int PLAYBACK_SPEED_FAST_L0 = 10;
    public static final int PLAYBACK_SPEED_FAST_L1 = 11;
    public static final int PLAYBACK_SPEED_FAST_L2 = 12;
    public static final int PLAYBACK_SPEED_FAST_L3 = 13;
    public static final int PLAYBACK_SPEED_FAST_L4 = 14;
    public static final int PLAYBACK_SPEED_INVALID = -1;
    public static final int PLAYBACK_SPEED_NORMAL = 1;
    public static final int PLAYBACK_SPEED_PAUSED = 0;
    static final String TAG = "PlaybackControlGlue";
    private static final int UPDATE_PLAYBACK_STATE_DELAY_MS = 2000;
    static final Handler sHandler = new UpdatePlaybackStateHandler();
    private PlaybackControlsRow mControlsRow;
    private PlaybackRowPresenter mControlsRowPresenter;
    private boolean mFadeWhenPlaying;
    private PlaybackControlsRow.FastForwardAction mFastForwardAction;
    private final int[] mFastForwardSpeeds;
    final WeakReference<PlaybackControlGlue> mGlueWeakReference;
    private PlaybackControlsRow.PlayPauseAction mPlayPauseAction;
    private int mPlaybackSpeed;
    private PlaybackControlsRow.RewindAction mRewindAction;
    private final int[] mRewindSpeeds;
    private PlaybackControlsRow.SkipNextAction mSkipNextAction;
    private PlaybackControlsRow.SkipPreviousAction mSkipPreviousAction;

    public void enableProgressUpdating(boolean z) {
    }

    public abstract int getCurrentPosition();

    public abstract int getCurrentSpeedId();

    public abstract Drawable getMediaArt();

    public abstract int getMediaDuration();

    public abstract CharSequence getMediaSubtitle();

    public abstract CharSequence getMediaTitle();

    public abstract long getSupportedActions();

    public int getUpdatePeriod() {
        return 500;
    }

    public abstract boolean hasValidMedia();

    public abstract boolean isMediaPlaying();

    /* access modifiers changed from: protected */
    public void onCreatePrimaryActions(SparseArrayObjectAdapter sparseArrayObjectAdapter) {
    }

    /* access modifiers changed from: protected */
    public void onCreateSecondaryActions(ArrayObjectAdapter arrayObjectAdapter) {
    }

    public void play(int i) {
    }

    static class UpdatePlaybackStateHandler extends Handler {
        UpdatePlaybackStateHandler() {
        }

        public void handleMessage(Message message) {
            PlaybackControlGlue playbackControlGlue;
            if (message.what == 100 && (playbackControlGlue = (PlaybackControlGlue) ((WeakReference) message.obj).get()) != null) {
                playbackControlGlue.updatePlaybackState();
            }
        }
    }

    public PlaybackControlGlue(Context context, int[] iArr) {
        this(context, iArr, iArr);
    }

    public PlaybackControlGlue(Context context, int[] iArr, int[] iArr2) {
        super(context);
        this.mPlaybackSpeed = 1;
        this.mFadeWhenPlaying = true;
        this.mGlueWeakReference = new WeakReference<>(this);
        if (iArr.length == 0 || iArr.length > 5) {
            throw new IllegalStateException("invalid fastForwardSpeeds array size");
        }
        this.mFastForwardSpeeds = iArr;
        if (iArr2.length == 0 || iArr2.length > 5) {
            throw new IllegalStateException("invalid rewindSpeeds array size");
        }
        this.mRewindSpeeds = iArr2;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        super.onAttachedToHost(playbackGlueHost);
        playbackGlueHost.setOnKeyInterceptListener(this);
        playbackGlueHost.setOnActionClickedListener(this);
        if (getControlsRow() == null || getPlaybackRowPresenter() == null) {
            onCreateControlsRowAndPresenter();
        }
        playbackGlueHost.setPlaybackRowPresenter(getPlaybackRowPresenter());
        playbackGlueHost.setPlaybackRow(getControlsRow());
    }

    /* access modifiers changed from: protected */
    public void onHostStart() {
        enableProgressUpdating(true);
    }

    /* access modifiers changed from: protected */
    public void onHostStop() {
        enableProgressUpdating(false);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromHost() {
        enableProgressUpdating(false);
        super.onDetachedFromHost();
    }

    /* access modifiers changed from: protected */
    public void onCreateControlsRowAndPresenter() {
        if (getControlsRow() == null) {
            setControlsRow(new PlaybackControlsRow(this));
        }
        if (getPlaybackRowPresenter() == null) {
            setPlaybackRowPresenter(new PlaybackControlsRowPresenter(new AbstractDetailsDescriptionPresenter() {
                /* access modifiers changed from: protected */
                public void onBindDescription(AbstractDetailsDescriptionPresenter.ViewHolder viewHolder, Object obj) {
                    PlaybackControlGlue playbackControlGlue = (PlaybackControlGlue) obj;
                    if (playbackControlGlue.hasValidMedia()) {
                        viewHolder.getTitle().setText(playbackControlGlue.getMediaTitle());
                        viewHolder.getSubtitle().setText(playbackControlGlue.getMediaSubtitle());
                        return;
                    }
                    viewHolder.getTitle().setText("");
                    viewHolder.getSubtitle().setText("");
                }
            }) {
                /* access modifiers changed from: protected */
                public void onBindRowViewHolder(RowPresenter.ViewHolder viewHolder, Object obj) {
                    super.onBindRowViewHolder(viewHolder, obj);
                    viewHolder.setOnKeyListener(PlaybackControlGlue.this);
                }

                /* access modifiers changed from: protected */
                public void onUnbindRowViewHolder(RowPresenter.ViewHolder viewHolder) {
                    super.onUnbindRowViewHolder(viewHolder);
                    viewHolder.setOnKeyListener((View.OnKeyListener) null);
                }
            });
        }
    }

    public int[] getFastForwardSpeeds() {
        return this.mFastForwardSpeeds;
    }

    public int[] getRewindSpeeds() {
        return this.mRewindSpeeds;
    }

    public void setFadingEnabled(boolean z) {
        this.mFadeWhenPlaying = z;
        if (!z && getHost() != null) {
            getHost().setControlsOverlayAutoHideEnabled(false);
        }
    }

    public boolean isFadingEnabled() {
        return this.mFadeWhenPlaying;
    }

    public void setControlsRow(PlaybackControlsRow playbackControlsRow) {
        this.mControlsRow = playbackControlsRow;
        playbackControlsRow.setPrimaryActionsAdapter(createPrimaryActionsAdapter(new ControlButtonPresenterSelector()));
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter((PresenterSelector) new ControlButtonPresenterSelector());
        onCreateSecondaryActions(arrayObjectAdapter);
        getControlsRow().setSecondaryActionsAdapter(arrayObjectAdapter);
        updateControlsRow();
    }

    /* access modifiers changed from: protected */
    public SparseArrayObjectAdapter createPrimaryActionsAdapter(PresenterSelector presenterSelector) {
        SparseArrayObjectAdapter sparseArrayObjectAdapter = new SparseArrayObjectAdapter(presenterSelector);
        onCreatePrimaryActions(sparseArrayObjectAdapter);
        return sparseArrayObjectAdapter;
    }

    @Deprecated
    public void setControlsRowPresenter(PlaybackControlsRowPresenter playbackControlsRowPresenter) {
        this.mControlsRowPresenter = playbackControlsRowPresenter;
    }

    public PlaybackControlsRow getControlsRow() {
        return this.mControlsRow;
    }

    @Deprecated
    public PlaybackControlsRowPresenter getControlsRowPresenter() {
        PlaybackRowPresenter playbackRowPresenter = this.mControlsRowPresenter;
        if (playbackRowPresenter instanceof PlaybackControlsRowPresenter) {
            return (PlaybackControlsRowPresenter) playbackRowPresenter;
        }
        return null;
    }

    public void setPlaybackRowPresenter(PlaybackRowPresenter playbackRowPresenter) {
        this.mControlsRowPresenter = playbackRowPresenter;
    }

    public PlaybackRowPresenter getPlaybackRowPresenter() {
        return this.mControlsRowPresenter;
    }

    public void updateProgress() {
        int currentPosition = getCurrentPosition();
        PlaybackControlsRow playbackControlsRow = this.mControlsRow;
        if (playbackControlsRow != null) {
            playbackControlsRow.setCurrentTime(currentPosition);
        }
    }

    public void onActionClicked(Action action) {
        dispatchAction(action, (KeyEvent) null);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (!(i == 4 || i == 111)) {
            switch (i) {
                case 19:
                case 20:
                case 21:
                case 22:
                    break;
                default:
                    SparseArrayObjectAdapter sparseArrayObjectAdapter = (SparseArrayObjectAdapter) this.mControlsRow.getPrimaryActionsAdapter();
                    Action actionForKeyCode = this.mControlsRow.getActionForKeyCode(sparseArrayObjectAdapter, i);
                    if (actionForKeyCode == null || (actionForKeyCode != sparseArrayObjectAdapter.lookup(64) && actionForKeyCode != sparseArrayObjectAdapter.lookup(32) && actionForKeyCode != sparseArrayObjectAdapter.lookup(128) && actionForKeyCode != sparseArrayObjectAdapter.lookup(16) && actionForKeyCode != sparseArrayObjectAdapter.lookup(256))) {
                        return false;
                    }
                    if (keyEvent.getAction() == 0) {
                        dispatchAction(actionForKeyCode, keyEvent);
                    }
                    return true;
            }
        }
        int i2 = this.mPlaybackSpeed;
        if (i2 < 10 && i2 > -10) {
            return false;
        }
        this.mPlaybackSpeed = 1;
        play(1);
        updatePlaybackStatusAfterUserAction();
        if (i == 4 || i == 111) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean dispatchAction(Action action, KeyEvent keyEvent) {
        if (action == this.mPlayPauseAction) {
            boolean z = keyEvent == null || keyEvent.getKeyCode() == 85 || keyEvent.getKeyCode() == 126;
            if (keyEvent == null || keyEvent.getKeyCode() == 85 || keyEvent.getKeyCode() == 127) {
                int i = this.mPlaybackSpeed;
                if (!z ? i != 0 : i == 1) {
                    this.mPlaybackSpeed = 0;
                    pause();
                    updatePlaybackStatusAfterUserAction();
                    return true;
                }
            }
            if (z && this.mPlaybackSpeed != 1) {
                this.mPlaybackSpeed = 1;
                play(1);
            }
            updatePlaybackStatusAfterUserAction();
            return true;
        } else if (action == this.mSkipNextAction) {
            next();
            return true;
        } else if (action == this.mSkipPreviousAction) {
            previous();
            return true;
        } else if (action == this.mFastForwardAction) {
            if (this.mPlaybackSpeed >= getMaxForwardSpeedId()) {
                return true;
            }
            int i2 = this.mPlaybackSpeed;
            switch (i2) {
                case 10:
                case 11:
                case 12:
                case 13:
                    this.mPlaybackSpeed = i2 + 1;
                    break;
                default:
                    this.mPlaybackSpeed = 10;
                    break;
            }
            play(this.mPlaybackSpeed);
            updatePlaybackStatusAfterUserAction();
            return true;
        } else if (action != this.mRewindAction) {
            return false;
        } else {
            if (this.mPlaybackSpeed <= (-getMaxRewindSpeedId())) {
                return true;
            }
            int i3 = this.mPlaybackSpeed;
            switch (i3) {
                case -13:
                case -12:
                case -11:
                case -10:
                    this.mPlaybackSpeed = i3 - 1;
                    break;
                default:
                    this.mPlaybackSpeed = -10;
                    break;
            }
            play(this.mPlaybackSpeed);
            updatePlaybackStatusAfterUserAction();
            return true;
        }
    }

    private int getMaxForwardSpeedId() {
        return this.mFastForwardSpeeds.length + 9;
    }

    private int getMaxRewindSpeedId() {
        return this.mRewindSpeeds.length + 9;
    }

    private void updateControlsRow() {
        updateRowMetadata();
        updateControlButtons();
        sHandler.removeMessages(100, this.mGlueWeakReference);
        updatePlaybackState();
    }

    private void updatePlaybackStatusAfterUserAction() {
        updatePlaybackState(this.mPlaybackSpeed);
        Handler handler = sHandler;
        handler.removeMessages(100, this.mGlueWeakReference);
        handler.sendMessageDelayed(handler.obtainMessage(100, this.mGlueWeakReference), 2000);
    }

    public final void play() {
        play(1);
    }

    private void updateRowMetadata() {
        if (this.mControlsRow != null) {
            if (!hasValidMedia()) {
                this.mControlsRow.setImageDrawable((Drawable) null);
                this.mControlsRow.setTotalTime(0);
                this.mControlsRow.setCurrentTime(0);
            } else {
                this.mControlsRow.setImageDrawable(getMediaArt());
                this.mControlsRow.setTotalTime(getMediaDuration());
                this.mControlsRow.setCurrentTime(getCurrentPosition());
            }
            if (getHost() != null) {
                getHost().notifyPlaybackRowChanged();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updatePlaybackState() {
        if (hasValidMedia()) {
            int currentSpeedId = getCurrentSpeedId();
            this.mPlaybackSpeed = currentSpeedId;
            updatePlaybackState(currentSpeedId);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateControlButtons() {
        SparseArrayObjectAdapter sparseArrayObjectAdapter = (SparseArrayObjectAdapter) getControlsRow().getPrimaryActionsAdapter();
        long supportedActions = getSupportedActions();
        long j = 16 & supportedActions;
        if (j != 0 && this.mSkipPreviousAction == null) {
            PlaybackControlsRow.SkipPreviousAction skipPreviousAction = new PlaybackControlsRow.SkipPreviousAction(getContext());
            this.mSkipPreviousAction = skipPreviousAction;
            sparseArrayObjectAdapter.set(16, skipPreviousAction);
        } else if (j == 0 && this.mSkipPreviousAction != null) {
            sparseArrayObjectAdapter.clear(16);
            this.mSkipPreviousAction = null;
        }
        long j2 = 32 & supportedActions;
        if (j2 != 0 && this.mRewindAction == null) {
            PlaybackControlsRow.RewindAction rewindAction = new PlaybackControlsRow.RewindAction(getContext(), this.mRewindSpeeds.length);
            this.mRewindAction = rewindAction;
            sparseArrayObjectAdapter.set(32, rewindAction);
        } else if (j2 == 0 && this.mRewindAction != null) {
            sparseArrayObjectAdapter.clear(32);
            this.mRewindAction = null;
        }
        long j3 = 64 & supportedActions;
        if (j3 != 0 && this.mPlayPauseAction == null) {
            PlaybackControlsRow.PlayPauseAction playPauseAction = new PlaybackControlsRow.PlayPauseAction(getContext());
            this.mPlayPauseAction = playPauseAction;
            sparseArrayObjectAdapter.set(64, playPauseAction);
        } else if (j3 == 0 && this.mPlayPauseAction != null) {
            sparseArrayObjectAdapter.clear(64);
            this.mPlayPauseAction = null;
        }
        long j4 = 128 & supportedActions;
        if (j4 != 0 && this.mFastForwardAction == null) {
            PlaybackControlsRow.FastForwardAction fastForwardAction = new PlaybackControlsRow.FastForwardAction(getContext(), this.mFastForwardSpeeds.length);
            this.mFastForwardAction = fastForwardAction;
            sparseArrayObjectAdapter.set(128, fastForwardAction);
        } else if (j4 == 0 && this.mFastForwardAction != null) {
            sparseArrayObjectAdapter.clear(128);
            this.mFastForwardAction = null;
        }
        long j5 = supportedActions & 256;
        if (j5 != 0 && this.mSkipNextAction == null) {
            PlaybackControlsRow.SkipNextAction skipNextAction = new PlaybackControlsRow.SkipNextAction(getContext());
            this.mSkipNextAction = skipNextAction;
            sparseArrayObjectAdapter.set(256, skipNextAction);
        } else if (j5 == 0 && this.mSkipNextAction != null) {
            sparseArrayObjectAdapter.clear(256);
            this.mSkipNextAction = null;
        }
    }

    private void updatePlaybackState(int i) {
        if (this.mControlsRow != null) {
            SparseArrayObjectAdapter sparseArrayObjectAdapter = (SparseArrayObjectAdapter) getControlsRow().getPrimaryActionsAdapter();
            PlaybackControlsRow.FastForwardAction fastForwardAction = this.mFastForwardAction;
            if (fastForwardAction != null) {
                int i2 = i >= 10 ? i - 9 : 0;
                if (fastForwardAction.getIndex() != i2) {
                    this.mFastForwardAction.setIndex(i2);
                    notifyItemChanged(sparseArrayObjectAdapter, this.mFastForwardAction);
                }
            }
            PlaybackControlsRow.RewindAction rewindAction = this.mRewindAction;
            if (rewindAction != null) {
                int i3 = i <= -10 ? (-i) - 9 : 0;
                if (rewindAction.getIndex() != i3) {
                    this.mRewindAction.setIndex(i3);
                    notifyItemChanged(sparseArrayObjectAdapter, this.mRewindAction);
                }
            }
            int i4 = 1;
            if (i == 0) {
                updateProgress();
                enableProgressUpdating(false);
            } else {
                enableProgressUpdating(true);
            }
            if (this.mFadeWhenPlaying && getHost() != null) {
                getHost().setControlsOverlayAutoHideEnabled(i == 1);
            }
            PlaybackControlsRow.PlayPauseAction playPauseAction = this.mPlayPauseAction;
            if (playPauseAction != null) {
                if (i == 0) {
                    i4 = 0;
                }
                if (playPauseAction.getIndex() != i4) {
                    this.mPlayPauseAction.setIndex(i4);
                    notifyItemChanged(sparseArrayObjectAdapter, this.mPlayPauseAction);
                }
            }
            List<PlaybackGlue.PlayerCallback> playerCallbacks = getPlayerCallbacks();
            if (playerCallbacks != null) {
                int size = playerCallbacks.size();
                for (int i5 = 0; i5 < size; i5++) {
                    playerCallbacks.get(i5).onPlayStateChanged(this);
                }
            }
        }
    }

    private static void notifyItemChanged(SparseArrayObjectAdapter sparseArrayObjectAdapter, Object obj) {
        int indexOf = sparseArrayObjectAdapter.indexOf(obj);
        if (indexOf >= 0) {
            sparseArrayObjectAdapter.notifyArrayItemRangeChanged(indexOf, 1);
        }
    }

    private static String getSpeedString(int i) {
        if (i == -1) {
            return "PLAYBACK_SPEED_INVALID";
        }
        if (i == 0) {
            return "PLAYBACK_SPEED_PAUSED";
        }
        if (i == 1) {
            return "PLAYBACK_SPEED_NORMAL";
        }
        switch (i) {
            case -14:
                return "-PLAYBACK_SPEED_FAST_L4";
            case -13:
                return "-PLAYBACK_SPEED_FAST_L3";
            case -12:
                return "-PLAYBACK_SPEED_FAST_L2";
            case -11:
                return "-PLAYBACK_SPEED_FAST_L1";
            case -10:
                return "-PLAYBACK_SPEED_FAST_L0";
            default:
                switch (i) {
                    case 10:
                        return "PLAYBACK_SPEED_FAST_L0";
                    case 11:
                        return "PLAYBACK_SPEED_FAST_L1";
                    case 12:
                        return "PLAYBACK_SPEED_FAST_L2";
                    case 13:
                        return "PLAYBACK_SPEED_FAST_L3";
                    case 14:
                        return "PLAYBACK_SPEED_FAST_L4";
                    default:
                        return null;
                }
        }
    }

    public boolean isPlaying() {
        return isMediaPlaying();
    }

    /* access modifiers changed from: protected */
    public void onStateChanged() {
        if (hasValidMedia()) {
            Handler handler = sHandler;
            if (handler.hasMessages(100, this.mGlueWeakReference)) {
                handler.removeMessages(100, this.mGlueWeakReference);
                if (getCurrentSpeedId() != this.mPlaybackSpeed) {
                    handler.sendMessageDelayed(handler.obtainMessage(100, this.mGlueWeakReference), 2000);
                } else {
                    updatePlaybackState();
                }
            } else {
                updatePlaybackState();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMetadataChanged() {
        updateRowMetadata();
    }
}
