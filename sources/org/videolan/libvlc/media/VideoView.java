package org.videolan.libvlc.media;

import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.MediaController;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.interfaces.ILibVLC;

public class VideoView extends SurfaceView implements MediaController.MediaPlayerControl {
    private static ILibVLC sILibVLC;

    public void addSubtitleSource(InputStream inputStream, MediaFormat mediaFormat) {
    }

    public boolean canPause() {
        return false;
    }

    public boolean canSeekBackward() {
        return false;
    }

    public boolean canSeekForward() {
        return false;
    }

    public int getAudioSessionId() {
        return 0;
    }

    public int getBufferPercentage() {
        return 0;
    }

    public int getCurrentPosition() {
        return 0;
    }

    public int getDuration() {
        return -1;
    }

    public boolean isPlaying() {
        return false;
    }

    public void pause() {
    }

    public void resume() {
    }

    public void seekTo(int i) {
    }

    public void setMediaController(MediaController mediaController) {
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
    }

    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
    }

    public void start() {
    }

    public void stopPlayback() {
    }

    public void suspend() {
    }

    public VideoView(Context context) {
        super(context);
        sILibVLC = new LibVLC(context, (List<String>) null);
    }

    public VideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public VideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
    }

    public int resolveAdjustedSize(int i, int i2) {
        return getDefaultSize(i, i2);
    }

    public void setVideoPath(String str) {
        new Media(sILibVLC, str);
    }

    public void setVideoURI(Uri uri) {
        new Media(sILibVLC, uri);
    }

    public void setVideoURI(Uri uri, Map<String, String> map) {
        setVideoURI(uri);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        return super.onTrackballEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }
}
