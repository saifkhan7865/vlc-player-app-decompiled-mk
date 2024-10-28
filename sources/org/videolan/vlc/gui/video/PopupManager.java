package org.videolan.vlc.gui.video;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IVLCVout;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.NotificationHelperKt;
import org.videolan.vlc.gui.view.PopupLayout;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016*\u0001\u0010\u0018\u0000 K2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001KB\r\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010%\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#H\u0016J*\u0010&\u001a\u00020\u000b2\b\u0010'\u001a\u0004\u0018\u00010#2\u0006\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*H\u0016J\u0010\u0010,\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010-\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u00100\u001a\u00020\u001c2\u0006\u0010.\u001a\u000201H\u0016J@\u00102\u001a\u00020\u001c2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u0002062\u0006\u00109\u001a\u0002062\u0006\u0010:\u001a\u0002062\u0006\u0010;\u001a\u000206H\u0016J*\u0010<\u001a\u00020\u000b2\b\u0010=\u001a\u0004\u0018\u00010#2\u0006\u0010>\u001a\u00020#2\u0006\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020*H\u0016J\u0010\u0010A\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010B\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010C\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010D\u001a\u00020\u001c2\u0006\u00103\u001a\u000204H\u0016J\u0010\u0010E\u001a\u00020\u001c2\u0006\u00103\u001a\u000204H\u0016J\u0006\u0010F\u001a\u00020\u001cJ\b\u0010G\u001a\u00020\u001cH\u0002J\u0006\u0010H\u001a\u00020\u001cJ\b\u0010I\u001a\u00020\u001cH\u0002J\b\u0010J\u001a\u00020\u001cH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0004\n\u0002\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lorg/videolan/vlc/gui/video/PopupManager;", "Lorg/videolan/vlc/PlaybackService$Callback;", "Landroid/view/GestureDetector$OnDoubleTapListener;", "Landroid/view/View$OnClickListener;", "Landroid/view/GestureDetector$OnGestureListener;", "Lorg/videolan/libvlc/interfaces/IVLCVout$OnNewVideoLayoutListener;", "Lorg/videolan/libvlc/interfaces/IVLCVout$Callback;", "service", "Lorg/videolan/vlc/PlaybackService;", "(Lorg/videolan/vlc/PlaybackService;)V", "alwaysOn", "", "closeButton", "Landroid/widget/ImageView;", "expandButton", "handler", "org/videolan/vlc/gui/video/PopupManager$handler$1", "Lorg/videolan/vlc/gui/video/PopupManager$handler$1;", "observer", "Landroidx/lifecycle/Observer;", "getObserver", "()Landroidx/lifecycle/Observer;", "setObserver", "(Landroidx/lifecycle/Observer;)V", "playPauseButton", "rootView", "Lorg/videolan/vlc/gui/view/PopupLayout;", "expandToVideoPlayer", "", "hideNotification", "onClick", "v", "Landroid/view/View;", "onDoubleTap", "e", "Landroid/view/MotionEvent;", "onDoubleTapEvent", "onDown", "onFling", "e1", "e2", "velocityX", "", "velocityY", "onLongPress", "onMediaEvent", "event", "Lorg/videolan/libvlc/interfaces/IMedia$Event;", "onMediaPlayerEvent", "Lorg/videolan/libvlc/MediaPlayer$Event;", "onNewVideoLayout", "vlcVout", "Lorg/videolan/libvlc/interfaces/IVLCVout;", "width", "", "height", "visibleWidth", "visibleHeight", "sarNum", "sarDen", "onScroll", "p0", "p1", "p2", "p3", "onShowPress", "onSingleTapConfirmed", "onSingleTapUp", "onSurfacesCreated", "onSurfacesDestroyed", "removePopup", "showNotification", "showPopup", "stopPlayback", "update", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PopupManager.kt */
public final class PopupManager implements PlaybackService.Callback, GestureDetector.OnDoubleTapListener, View.OnClickListener, GestureDetector.OnGestureListener, IVLCVout.OnNewVideoLayoutListener, IVLCVout.Callback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final float FLING_STOP_VELOCITY = 3000.0f;
    private static final int HIDE_BUTTONS = 1;
    private static final long MSG_DELAY = 3000;
    private static final int SHOW_BUTTONS = 0;
    private static final String TAG = "VLC/PopupManager";
    private final boolean alwaysOn;
    /* access modifiers changed from: private */
    public ImageView closeButton;
    /* access modifiers changed from: private */
    public ImageView expandButton;
    private final PopupManager$handler$1 handler = new PopupManager$handler$1(this, Looper.getMainLooper());
    private Observer<Boolean> observer = new PopupManager$$ExternalSyntheticLambda0(this);
    /* access modifiers changed from: private */
    public ImageView playPauseButton;
    private PopupLayout rootView;
    private final PlaybackService service;

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
        return false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
    }

    public void onMediaEvent(IMedia.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        Intrinsics.checkNotNullParameter(motionEvent2, "p1");
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
        return false;
    }

    public void update() {
    }

    public PopupManager(PlaybackService playbackService) {
        Intrinsics.checkNotNullParameter(playbackService, NotificationCompat.CATEGORY_SERVICE);
        this.service = playbackService;
        this.alwaysOn = ((SharedPreferences) Settings.INSTANCE.getInstance(playbackService)).getBoolean(SettingsKt.POPUP_KEEPSCREEN, false);
    }

    public final Observer<Boolean> getObserver() {
        return this.observer;
    }

    public final void setObserver(Observer<Boolean> observer2) {
        Intrinsics.checkNotNullParameter(observer2, "<set-?>");
        this.observer = observer2;
    }

    /* access modifiers changed from: private */
    public static final void observer$lambda$0(PopupManager popupManager, boolean z) {
        Intrinsics.checkNotNullParameter(popupManager, "this$0");
        if (!z) {
            popupManager.removePopup();
        }
    }

    public final void removePopup() {
        this.service.isInPiPMode().removeObserver(this.observer);
        hideNotification();
        PopupLayout popupLayout = this.rootView;
        if (popupLayout != null) {
            this.service.m2457removeCallbackJP2dKIU(this);
            IVLCVout vout = this.service.getVout();
            if (vout != null) {
                vout.detachViews();
            }
            popupLayout.close();
            this.rootView = null;
        }
    }

    public final void showPopup() {
        this.service.m2455addCallbackJP2dKIU(this);
        this.service.isInPiPMode().setValue(true);
        this.service.isInPiPMode().observeForever(this.observer);
        ImageView imageView = null;
        View inflate = LayoutInflater.from(this.service.getApplicationContext()).inflate(R.layout.video_popup, (ViewGroup) null);
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type org.videolan.vlc.gui.view.PopupLayout");
        PopupLayout popupLayout = (PopupLayout) inflate;
        this.rootView = popupLayout;
        if (popupLayout != null) {
            if (this.alwaysOn) {
                popupLayout.setKeepScreenOn(true);
            }
            View findViewById = popupLayout.findViewById(R.id.video_play_pause);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.playPauseButton = (ImageView) findViewById;
            View findViewById2 = popupLayout.findViewById(R.id.popup_close);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.closeButton = (ImageView) findViewById2;
            View findViewById3 = popupLayout.findViewById(R.id.popup_expand);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.expandButton = (ImageView) findViewById3;
            ImageView imageView2 = this.playPauseButton;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                imageView2 = null;
            }
            View.OnClickListener onClickListener = this;
            imageView2.setOnClickListener(onClickListener);
            ImageView imageView3 = this.closeButton;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("closeButton");
                imageView3 = null;
            }
            imageView3.setOnClickListener(onClickListener);
            ImageView imageView4 = this.expandButton;
            if (imageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("expandButton");
            } else {
                imageView = imageView4;
            }
            imageView.setOnClickListener(onClickListener);
            GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(this.service, this);
            gestureDetectorCompat.setOnDoubleTapListener(this);
            popupLayout.setGestureDetector(gestureDetectorCompat);
            IVLCVout vout = this.service.getVout();
            if (vout != null) {
                View findViewById4 = popupLayout.findViewById(R.id.player_surface);
                Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.view.SurfaceView");
                vout.setVideoView((SurfaceView) findViewById4);
                vout.addCallback(this);
                vout.attachViews(this);
                popupLayout.setVLCVOut(vout);
            }
        }
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
        ImageView imageView = this.playPauseButton;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
            imageView = null;
        }
        if (imageView.getVisibility() == 0) {
            return false;
        }
        this.handler.sendEmptyMessage(0);
        this.handler.sendEmptyMessageDelayed(1, MSG_DELAY);
        return true;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
        expandToVideoPlayer();
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        Intrinsics.checkNotNullParameter(motionEvent2, "e2");
        if (Math.abs(f) <= FLING_STOP_VELOCITY && f2 <= FLING_STOP_VELOCITY) {
            return false;
        }
        stopPlayback();
        return true;
    }

    public void onNewVideoLayout(IVLCVout iVLCVout, int i, int i2, int i3, int i4, int i5, int i6) {
        double d;
        Intrinsics.checkNotNullParameter(iVLCVout, "vlcVout");
        PopupLayout popupLayout = this.rootView;
        if (popupLayout != null) {
            int width = popupLayout.getWidth();
            int height = popupLayout.getHeight();
            if (width * height == 0) {
                Log.e(TAG, "Invalid surface size");
            } else if (i == 0 || i2 == 0) {
                popupLayout.setViewSize(width, height);
            } else {
                double d2 = (double) width;
                double d3 = (double) height;
                if (i6 == i5) {
                    double d4 = (double) i3;
                    double d5 = (double) i4;
                    Double.isNaN(d4);
                    Double.isNaN(d5);
                    d = d4 / d5;
                } else {
                    double d6 = (double) i3;
                    double d7 = (double) i5;
                    Double.isNaN(d6);
                    Double.isNaN(d7);
                    double d8 = (double) i6;
                    Double.isNaN(d8);
                    double d9 = (double) i4;
                    Double.isNaN(d9);
                    d = ((d6 * d7) / d8) / d9;
                }
                Double.isNaN(d2);
                Double.isNaN(d3);
                if (d2 / d3 < d) {
                    Double.isNaN(d2);
                    d3 = d2 / d;
                } else {
                    Double.isNaN(d3);
                    d2 = d3 * d;
                }
                popupLayout.setViewSize((int) Math.floor(d2), (int) Math.floor(d3));
            }
        }
    }

    public void onMediaPlayerEvent(MediaPlayer.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        int i = event.type;
        ImageView imageView = null;
        if (i == 260) {
            PopupLayout popupLayout = this.rootView;
            if (popupLayout != null) {
                if (!this.alwaysOn) {
                    popupLayout.setKeepScreenOn(true);
                }
                ImageView imageView2 = this.playPauseButton;
                if (imageView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                } else {
                    imageView = imageView2;
                }
                imageView.setImageResource(R.drawable.ic_popup_pause);
            }
            showNotification();
        } else if (i == 261) {
            PopupLayout popupLayout2 = this.rootView;
            if (popupLayout2 != null) {
                if (!this.alwaysOn) {
                    popupLayout2.setKeepScreenOn(false);
                }
                ImageView imageView3 = this.playPauseButton;
                if (imageView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                } else {
                    imageView = imageView3;
                }
                imageView.setImageResource(R.drawable.ic_popup_play);
            }
            showNotification();
        }
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        int id = view.getId();
        if (id == R.id.video_play_pause) {
            if (!this.service.hasMedia()) {
                return;
            }
            if (this.service.isPlaying()) {
                this.service.pause();
            } else {
                this.service.play();
            }
        } else if (id == R.id.popup_close) {
            stopPlayback();
        } else if (id == R.id.popup_expand) {
            expandToVideoPlayer();
        }
    }

    private final void expandToVideoPlayer() {
        MediaWrapper currentMediaWrapper;
        removePopup();
        if (this.service.hasMedia() && !this.service.isPlaying() && (currentMediaWrapper = this.service.getCurrentMediaWrapper()) != null) {
            currentMediaWrapper.setFlags(4);
        }
        this.service.isInPiPMode().setValue(false);
        this.service.switchToVideo();
    }

    private final void stopPlayback() {
        long time = this.service.getTime();
        if (time != -1) {
            long j = this.service.getLength() - time < CoroutineLiveDataKt.DEFAULT_TIMEOUT ? 0 : 2000;
            if (this.service.isSeekable()) {
                SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(this.service), SettingsKt.VIDEO_RESUME_TIME, Long.valueOf(j));
                String currentMediaLocation = this.service.getCurrentMediaLocation();
                if (currentMediaLocation != null) {
                    SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(this.service), SettingsKt.VIDEO_RESUME_URI, currentMediaLocation);
                }
            }
        }
        PlaybackService.stop$default(this.service, false, false, 3, (Object) null);
    }

    private final void showNotification() {
        NotificationCompat.Builder deleteIntent = new NotificationCompat.Builder((Context) this.service, NotificationHelperKt.MISC_CHANNEL_ID).setSmallIcon(R.drawable.ic_notif_video).setVisibility(1).setContentTitle(this.service.getTitle()).setContentText(this.service.getString(R.string.popup_playback)).setAutoCancel(false).setOngoing(true).setContentIntent(this.service.getSessionPendingIntent()).setDeleteIntent(KextensionsKt.getPendingIntent(this.service, new Intent(Constants.ACTION_REMOTE_STOP)));
        Intrinsics.checkNotNullExpressionValue(deleteIntent, "setDeleteIntent(...)");
        PendingIntent pendingIntent = KextensionsKt.getPendingIntent(this.service, new Intent(Constants.ACTION_REMOTE_SWITCH_VIDEO));
        PendingIntent pendingIntent2 = KextensionsKt.getPendingIntent(this.service, new Intent(Constants.ACTION_REMOTE_PLAYPAUSE));
        if (this.service.isPlaying()) {
            deleteIntent.addAction(R.drawable.ic_popup_pause, this.service.getString(R.string.pause), pendingIntent2);
        } else {
            deleteIntent.addAction(R.drawable.ic_popup_play, this.service.getString(R.string.play), pendingIntent2);
        }
        deleteIntent.addAction(R.drawable.ic_popup_fullscreen, this.service.getString(R.string.popup_expand), pendingIntent);
        Notification build = deleteIntent.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        ExtensionsKt.startForegroundCompat(this.service, 42, build, 2);
    }

    private final void hideNotification() {
        ExtensionsKt.stopForegroundCompat$default(this.service, false, 1, (Object) null);
    }

    public void onSurfacesCreated(IVLCVout iVLCVout) {
        Intrinsics.checkNotNullParameter(iVLCVout, "vlcVout");
        ImageView imageView = null;
        this.service.setVideoAspectRatio((String) null);
        this.service.setVideoScale(0.0f);
        this.service.setVideoTrackEnabled(true);
        if (this.service.hasMedia()) {
            this.service.flush();
            ImageView imageView2 = this.playPauseButton;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
            } else {
                imageView = imageView2;
            }
            imageView.setImageResource(this.service.isPlaying() ? R.drawable.ic_popup_pause : R.drawable.ic_popup_play);
        } else {
            PlaybackService playbackService = this.service;
            PlaybackService.playIndex$default(playbackService, playbackService.getCurrentMediaPosition(), 0, 2, (Object) null);
        }
        showNotification();
    }

    public void onSurfacesDestroyed(IVLCVout iVLCVout) {
        Intrinsics.checkNotNullParameter(iVLCVout, "vlcVout");
        iVLCVout.removeCallback(this);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/gui/video/PopupManager$Companion;", "", "()V", "FLING_STOP_VELOCITY", "", "HIDE_BUTTONS", "", "MSG_DELAY", "", "SHOW_BUTTONS", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PopupManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
