package androidx.leanback.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.SurfaceHolder;
import androidx.leanback.R;
import androidx.leanback.media.PlayerAdapter;
import java.io.IOException;

public class MediaPlayerAdapter extends PlayerAdapter {
    long mBufferedProgress;
    boolean mBufferingStart;
    Context mContext;
    final Handler mHandler = new Handler();
    boolean mHasDisplay;
    boolean mInitialized = false;
    Uri mMediaSourceUri = null;
    final MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            MediaPlayerAdapter mediaPlayerAdapter = MediaPlayerAdapter.this;
            mediaPlayerAdapter.mBufferedProgress = (mediaPlayerAdapter.getDuration() * ((long) i)) / 100;
            MediaPlayerAdapter.this.getCallback().onBufferedPositionChanged(MediaPlayerAdapter.this);
        }
    };
    final MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            MediaPlayerAdapter.this.getCallback().onPlayStateChanged(MediaPlayerAdapter.this);
            MediaPlayerAdapter.this.getCallback().onPlayCompleted(MediaPlayerAdapter.this);
        }
    };
    final MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener() {
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            PlayerAdapter.Callback callback = MediaPlayerAdapter.this.getCallback();
            MediaPlayerAdapter mediaPlayerAdapter = MediaPlayerAdapter.this;
            callback.onError(mediaPlayerAdapter, i, mediaPlayerAdapter.mContext.getString(R.string.lb_media_player_error, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            return MediaPlayerAdapter.this.onError(i, i2);
        }
    };
    final MediaPlayer.OnInfoListener mOnInfoListener = new MediaPlayer.OnInfoListener() {
        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            boolean z;
            if (i == 701) {
                MediaPlayerAdapter.this.mBufferingStart = true;
                MediaPlayerAdapter.this.notifyBufferingStartEnd();
            } else if (i != 702) {
                z = false;
                boolean onInfo = MediaPlayerAdapter.this.onInfo(i, i2);
                if (z && !onInfo) {
                    return false;
                }
            } else {
                MediaPlayerAdapter.this.mBufferingStart = false;
                MediaPlayerAdapter.this.notifyBufferingStartEnd();
            }
            z = true;
            boolean onInfo2 = MediaPlayerAdapter.this.onInfo(i, i2);
            return z ? true : true;
        }
    };
    MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener() {
        public void onPrepared(MediaPlayer mediaPlayer) {
            MediaPlayerAdapter.this.mInitialized = true;
            MediaPlayerAdapter.this.notifyBufferingStartEnd();
            if (MediaPlayerAdapter.this.mSurfaceHolderGlueHost == null || MediaPlayerAdapter.this.mHasDisplay) {
                MediaPlayerAdapter.this.getCallback().onPreparedStateChanged(MediaPlayerAdapter.this);
            }
        }
    };
    final MediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() {
        public void onSeekComplete(MediaPlayer mediaPlayer) {
            MediaPlayerAdapter.this.onSeekComplete();
        }
    };
    final MediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            MediaPlayerAdapter.this.getCallback().onVideoSizeChanged(MediaPlayerAdapter.this, i, i2);
        }
    };
    final MediaPlayer mPlayer = new MediaPlayer();
    final Runnable mRunnable = new Runnable() {
        public void run() {
            MediaPlayerAdapter.this.getCallback().onCurrentPositionChanged(MediaPlayerAdapter.this);
            MediaPlayerAdapter.this.mHandler.postDelayed(this, (long) MediaPlayerAdapter.this.getProgressUpdatingInterval());
        }
    };
    SurfaceHolderGlueHost mSurfaceHolderGlueHost;

    public int getProgressUpdatingInterval() {
        return 16;
    }

    /* access modifiers changed from: protected */
    public boolean onError(int i, int i2) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onInfo(int i, int i2) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSeekComplete() {
    }

    /* access modifiers changed from: package-private */
    public void notifyBufferingStartEnd() {
        getCallback().onBufferingStateChanged(this, this.mBufferingStart || !this.mInitialized);
    }

    public MediaPlayerAdapter(Context context) {
        this.mContext = context;
    }

    public void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        if (playbackGlueHost instanceof SurfaceHolderGlueHost) {
            SurfaceHolderGlueHost surfaceHolderGlueHost = (SurfaceHolderGlueHost) playbackGlueHost;
            this.mSurfaceHolderGlueHost = surfaceHolderGlueHost;
            surfaceHolderGlueHost.setSurfaceHolderCallback(new VideoPlayerSurfaceHolderCallback());
        }
    }

    public void reset() {
        changeToUnitialized();
        this.mPlayer.reset();
    }

    /* access modifiers changed from: package-private */
    public void changeToUnitialized() {
        if (this.mInitialized) {
            this.mInitialized = false;
            notifyBufferingStartEnd();
            if (this.mHasDisplay) {
                getCallback().onPreparedStateChanged(this);
            }
        }
    }

    public void release() {
        changeToUnitialized();
        this.mHasDisplay = false;
        this.mPlayer.release();
    }

    public void onDetachedFromHost() {
        SurfaceHolderGlueHost surfaceHolderGlueHost = this.mSurfaceHolderGlueHost;
        if (surfaceHolderGlueHost != null) {
            surfaceHolderGlueHost.setSurfaceHolderCallback((SurfaceHolder.Callback) null);
            this.mSurfaceHolderGlueHost = null;
        }
        reset();
        release();
    }

    /* access modifiers changed from: package-private */
    public void setDisplay(SurfaceHolder surfaceHolder) {
        boolean z = this.mHasDisplay;
        boolean z2 = surfaceHolder != null;
        this.mHasDisplay = z2;
        if (z != z2) {
            this.mPlayer.setDisplay(surfaceHolder);
            if (this.mHasDisplay) {
                if (this.mInitialized) {
                    getCallback().onPreparedStateChanged(this);
                }
            } else if (this.mInitialized) {
                getCallback().onPreparedStateChanged(this);
            }
        }
    }

    public void setProgressUpdatingEnabled(boolean z) {
        this.mHandler.removeCallbacks(this.mRunnable);
        if (z) {
            this.mHandler.postDelayed(this.mRunnable, (long) getProgressUpdatingInterval());
        }
    }

    public boolean isPlaying() {
        return this.mInitialized && this.mPlayer.isPlaying();
    }

    public long getDuration() {
        if (this.mInitialized) {
            return (long) this.mPlayer.getDuration();
        }
        return -1;
    }

    public long getCurrentPosition() {
        if (this.mInitialized) {
            return (long) this.mPlayer.getCurrentPosition();
        }
        return -1;
    }

    public void play() {
        if (this.mInitialized && !this.mPlayer.isPlaying()) {
            this.mPlayer.start();
            getCallback().onPlayStateChanged(this);
            getCallback().onCurrentPositionChanged(this);
        }
    }

    public void pause() {
        if (isPlaying()) {
            this.mPlayer.pause();
            getCallback().onPlayStateChanged(this);
        }
    }

    public void seekTo(long j) {
        if (this.mInitialized) {
            this.mPlayer.seekTo((int) j);
        }
    }

    public long getBufferedPosition() {
        return this.mBufferedProgress;
    }

    public boolean setDataSource(Uri uri) {
        Uri uri2 = this.mMediaSourceUri;
        if (uri2 != null) {
            if (uri2.equals(uri)) {
                return false;
            }
        } else if (uri == null) {
            return false;
        }
        this.mMediaSourceUri = uri;
        prepareMediaForPlaying();
        return true;
    }

    private void prepareMediaForPlaying() {
        reset();
        try {
            Uri uri = this.mMediaSourceUri;
            if (uri != null) {
                this.mPlayer.setDataSource(this.mContext, uri);
                this.mPlayer.setAudioStreamType(3);
                this.mPlayer.setOnPreparedListener(this.mOnPreparedListener);
                this.mPlayer.setOnVideoSizeChangedListener(this.mOnVideoSizeChangedListener);
                this.mPlayer.setOnErrorListener(this.mOnErrorListener);
                this.mPlayer.setOnSeekCompleteListener(this.mOnSeekCompleteListener);
                this.mPlayer.setOnCompletionListener(this.mOnCompletionListener);
                this.mPlayer.setOnInfoListener(this.mOnInfoListener);
                this.mPlayer.setOnBufferingUpdateListener(this.mOnBufferingUpdateListener);
                notifyBufferingStartEnd();
                this.mPlayer.prepareAsync();
                getCallback().onPlayStateChanged(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public final MediaPlayer getMediaPlayer() {
        return this.mPlayer;
    }

    public boolean isPrepared() {
        return this.mInitialized && (this.mSurfaceHolderGlueHost == null || this.mHasDisplay);
    }

    class VideoPlayerSurfaceHolderCallback implements SurfaceHolder.Callback {
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        VideoPlayerSurfaceHolderCallback() {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            MediaPlayerAdapter.this.setDisplay(surfaceHolder);
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            MediaPlayerAdapter.this.setDisplay((SurfaceHolder) null);
        }
    }
}
