package androidx.leanback.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;

public class MediaControllerAdapter extends PlayerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "MediaControllerAdapter";
    private MediaControllerCompat mController;
    Handler mHandler = new Handler();
    boolean mIsBuffering = false;
    MediaControllerCompat.Callback mMediaControllerCallback = new MediaControllerCompat.Callback() {
        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            if (MediaControllerAdapter.this.mIsBuffering && playbackStateCompat.getState() != 6) {
                MediaControllerAdapter.this.getCallback().onBufferingStateChanged(MediaControllerAdapter.this, false);
                MediaControllerAdapter.this.getCallback().onBufferedPositionChanged(MediaControllerAdapter.this);
                MediaControllerAdapter.this.mIsBuffering = false;
            }
            if (playbackStateCompat.getState() != 0) {
                if (playbackStateCompat.getState() == 1) {
                    MediaControllerAdapter.this.getCallback().onPlayCompleted(MediaControllerAdapter.this);
                } else if (playbackStateCompat.getState() == 2) {
                    MediaControllerAdapter.this.getCallback().onPlayStateChanged(MediaControllerAdapter.this);
                    MediaControllerAdapter.this.getCallback().onCurrentPositionChanged(MediaControllerAdapter.this);
                } else if (playbackStateCompat.getState() == 3) {
                    MediaControllerAdapter.this.getCallback().onPlayStateChanged(MediaControllerAdapter.this);
                    MediaControllerAdapter.this.getCallback().onCurrentPositionChanged(MediaControllerAdapter.this);
                } else if (playbackStateCompat.getState() == 6) {
                    MediaControllerAdapter.this.mIsBuffering = true;
                    MediaControllerAdapter.this.getCallback().onBufferingStateChanged(MediaControllerAdapter.this, true);
                    MediaControllerAdapter.this.getCallback().onBufferedPositionChanged(MediaControllerAdapter.this);
                } else if (playbackStateCompat.getState() == 7) {
                    if (playbackStateCompat.getErrorMessage() == null) {
                        MediaControllerAdapter.this.getCallback().onError(MediaControllerAdapter.this, playbackStateCompat.getErrorCode(), "");
                    } else {
                        MediaControllerAdapter.this.getCallback().onError(MediaControllerAdapter.this, playbackStateCompat.getErrorCode(), playbackStateCompat.getErrorMessage().toString());
                    }
                } else if (playbackStateCompat.getState() == 4) {
                    MediaControllerAdapter.this.getCallback().onPlayStateChanged(MediaControllerAdapter.this);
                    MediaControllerAdapter.this.getCallback().onCurrentPositionChanged(MediaControllerAdapter.this);
                } else if (playbackStateCompat.getState() == 5) {
                    MediaControllerAdapter.this.getCallback().onPlayStateChanged(MediaControllerAdapter.this);
                    MediaControllerAdapter.this.getCallback().onCurrentPositionChanged(MediaControllerAdapter.this);
                }
            }
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaControllerAdapter.this.getCallback().onMetadataChanged(MediaControllerAdapter.this);
        }
    };
    private final Runnable mPositionUpdaterRunnable = new Runnable() {
        public void run() {
            MediaControllerAdapter.this.getCallback().onCurrentPositionChanged(MediaControllerAdapter.this);
            MediaControllerAdapter.this.mHandler.postDelayed(this, (long) MediaControllerAdapter.this.getUpdatePeriod());
        }
    };

    private int mapRepeatActionToRepeatMode(int i) {
        if (i == 0) {
            return 0;
        }
        if (i != 1) {
            return i != 2 ? -1 : 1;
        }
        return 2;
    }

    private int mapShuffleActionToShuffleMode(int i) {
        if (i != 0) {
            return i != 1 ? -1 : 1;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getUpdatePeriod() {
        return 16;
    }

    public MediaControllerAdapter(MediaControllerCompat mediaControllerCompat) {
        if (mediaControllerCompat != null) {
            this.mController = mediaControllerCompat;
            return;
        }
        throw new NullPointerException("Object of MediaControllerCompat is null");
    }

    public MediaControllerCompat getMediaController() {
        return this.mController;
    }

    public void play() {
        this.mController.getTransportControls().play();
    }

    public void pause() {
        this.mController.getTransportControls().pause();
    }

    public void seekTo(long j) {
        this.mController.getTransportControls().seekTo(j);
    }

    public void next() {
        this.mController.getTransportControls().skipToNext();
    }

    public void previous() {
        this.mController.getTransportControls().skipToPrevious();
    }

    public void fastForward() {
        this.mController.getTransportControls().fastForward();
    }

    public void rewind() {
        this.mController.getTransportControls().rewind();
    }

    public void setRepeatAction(int i) {
        this.mController.getTransportControls().setRepeatMode(mapRepeatActionToRepeatMode(i));
    }

    public void setShuffleAction(int i) {
        this.mController.getTransportControls().setShuffleMode(mapShuffleActionToShuffleMode(i));
    }

    public boolean isPlaying() {
        if (this.mController.getPlaybackState() == null) {
            return false;
        }
        if (this.mController.getPlaybackState().getState() == 3 || this.mController.getPlaybackState().getState() == 4 || this.mController.getPlaybackState().getState() == 5) {
            return true;
        }
        return false;
    }

    public long getCurrentPosition() {
        if (this.mController.getPlaybackState() == null) {
            return 0;
        }
        return this.mController.getPlaybackState().getPosition();
    }

    public long getBufferedPosition() {
        if (this.mController.getPlaybackState() == null) {
            return 0;
        }
        return this.mController.getPlaybackState().getBufferedPosition();
    }

    public CharSequence getMediaTitle() {
        if (this.mController.getMetadata() == null) {
            return "";
        }
        return this.mController.getMetadata().getDescription().getTitle();
    }

    public CharSequence getMediaSubtitle() {
        if (this.mController.getMetadata() == null) {
            return "";
        }
        return this.mController.getMetadata().getDescription().getSubtitle();
    }

    public Drawable getMediaArt(Context context) {
        Bitmap iconBitmap;
        if (this.mController.getMetadata() == null || (iconBitmap = this.mController.getMetadata().getDescription().getIconBitmap()) == null) {
            return null;
        }
        return new BitmapDrawable(context.getResources(), iconBitmap);
    }

    public long getDuration() {
        if (this.mController.getMetadata() == null) {
            return 0;
        }
        return (long) ((int) this.mController.getMetadata().getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
    }

    public void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        this.mController.registerCallback(this.mMediaControllerCallback);
    }

    public void onDetachedFromHost() {
        this.mController.unregisterCallback(this.mMediaControllerCallback);
    }

    public void setProgressUpdatingEnabled(boolean z) {
        this.mHandler.removeCallbacks(this.mPositionUpdaterRunnable);
        if (z) {
            this.mHandler.postDelayed(this.mPositionUpdaterRunnable, (long) getUpdatePeriod());
        }
    }

    public long getSupportedActions() {
        if (this.mController.getPlaybackState() == null) {
            return 0;
        }
        long actions = this.mController.getPlaybackState().getActions();
        long j = (actions & 512) != 0 ? 64 : 0;
        if ((actions & 32) != 0) {
            j |= 256;
        }
        if ((actions & 16) != 0) {
            j |= 16;
        }
        if ((64 & actions) != 0) {
            j |= 128;
        }
        if ((8 & actions) != 0) {
            j |= 32;
        }
        if ((PlaybackStateCompat.ACTION_SET_REPEAT_MODE & actions) != 0) {
            j |= 512;
        }
        return (actions & PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) != 0 ? j | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID : j;
    }
}
