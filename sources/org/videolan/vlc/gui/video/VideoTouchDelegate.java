package org.videolan.vlc.gui.video;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NotificationCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.google.android.material.circularreveal.CircularRevealCompat;
import com.google.android.material.circularreveal.CircularRevealFrameLayout;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import org.slf4j.Marker;
import org.videolan.medialibrary.Tools;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.Strings;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.view.HalfCircleView;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c*\u0001.\u0018\u0000 \u00012\u00020\u0001:\u0002\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010v\u001a\u00020wJ\u000e\u0010x\u001a\u00020\t2\u0006\u0010y\u001a\u00020zJ\u0010\u0010{\u001a\u00020w2\u0006\u0010|\u001a\u00020\u000fH\u0002J!\u0010}\u001a\u00020w2\u0006\u0010~\u001a\u00020\u00052\u0006\u0010\u001a\u00020\u000f2\u0007\u0010\u0001\u001a\u00020\tH\u0002J\u0012\u0010\u0001\u001a\u00020w2\u0007\u0010\u0001\u001a\u00020\u000fH\u0002J\u0012\u0010\u0001\u001a\u00020w2\u0007\u0010\u0001\u001a\u00020\u000fH\u0002J\t\u0010\u0001\u001a\u00020wH\u0002J\u0012\u0010\u0001\u001a\u00020w2\t\b\u0002\u0010\u0001\u001a\u00020\tJ\t\u0010\u0001\u001a\u00020wH\u0002J\u0011\u0010\u0001\u001a\u0004\u0018\u00010wH\u0002¢\u0006\u0003\u0010\u0001J\u001b\u0010\u0001\u001a\u00020\t2\u0007\u0010\u0001\u001a\u00020\u000f2\u0007\u0010\u0001\u001a\u00020\u000fH\u0002J\u0007\u0010\u0001\u001a\u00020\tJ\u000f\u0010\u0001\u001a\u00020\t2\u0006\u0010y\u001a\u00020zJ\u0018\u0010\u0001\u001a\u00020w2\u0007\u0010\u0001\u001a\u00020\u0005H\u0000¢\u0006\u0003\b\u0001J\t\u0010\u0001\u001a\u00020wH\u0002J\u0012\u0010\u0001\u001a\u00020^2\u0007\u0010\u0001\u001a\u00020\tH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020#8BX\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b$\u0010%R\u001b\u0010(\u001a\u00020)8BX\u0002¢\u0006\f\n\u0004\b,\u0010'\u001a\u0004\b*\u0010+R\u0010\u0010-\u001a\u00020.X\u0004¢\u0006\u0004\n\u0002\u0010/R\u000e\u00100\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001a\u00101\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u00106\u001a\u0002078BX\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u001b\u0010:\u001a\u00020#8BX\u0002¢\u0006\f\n\u0004\b<\u0010'\u001a\u0004\b;\u0010%R\u001b\u0010=\u001a\u00020)8BX\u0002¢\u0006\f\n\u0004\b?\u0010'\u001a\u0004\b>\u0010+R\u000e\u0010@\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010A\u001a\u00020B8BX\u0002¢\u0006\f\n\u0004\bE\u0010'\u001a\u0004\bC\u0010DR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u000e\u0010J\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010K\u001a\u00020L8BX\u0002¢\u0006\f\n\u0004\bO\u0010'\u001a\u0004\bM\u0010NR\u001b\u0010P\u001a\u00020Q8BX\u0002¢\u0006\f\n\u0004\bT\u0010'\u001a\u0004\bR\u0010SR\u001b\u0010U\u001a\u00020V8BX\u0002¢\u0006\f\n\u0004\bY\u0010'\u001a\u0004\bW\u0010XR\u001b\u0010Z\u001a\u00020V8BX\u0002¢\u0006\f\n\u0004\b\\\u0010'\u001a\u0004\b[\u0010XR\u001b\u0010]\u001a\u00020^8BX\u0002¢\u0006\f\n\u0004\ba\u0010'\u001a\u0004\b_\u0010`R\u001b\u0010b\u001a\u00020V8BX\u0002¢\u0006\f\n\u0004\bd\u0010'\u001a\u0004\bc\u0010XR\u001b\u0010e\u001a\u00020V8BX\u0002¢\u0006\f\n\u0004\bg\u0010'\u001a\u0004\bf\u0010XR\u001b\u0010h\u001a\u00020^8BX\u0002¢\u0006\f\n\u0004\bj\u0010'\u001a\u0004\bi\u0010`R\u001e\u0010l\u001a\u00020\u00052\u0006\u0010k\u001a\u00020\u0005@BX\u000e¢\u0006\b\n\u0000\"\u0004\bm\u00105R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u00103\"\u0004\bo\u00105R\u001a\u0010p\u001a\u00020\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\u001f\"\u0004\br\u0010!R\u000e\u0010s\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0001"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoTouchDelegate;", "", "player", "Lorg/videolan/vlc/gui/video/VideoPlayerActivity;", "touchControls", "", "screenConfig", "Lorg/videolan/vlc/gui/video/ScreenConfig;", "tv", "", "(Lorg/videolan/vlc/gui/video/VideoPlayerActivity;ILorg/videolan/vlc/gui/video/ScreenConfig;Z)V", "animatorSet", "Landroid/animation/AnimatorSet;", "fastPlayAnimatorSet", "gestureSafetyMargin", "", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "setHandler", "(Landroid/os/Handler;)V", "initInAllowedBounds", "initTouchX", "initTouchY", "isFirstBrightnessGesture", "lastMove", "", "lastSeekWasForward", "lastTapTimeMs", "getLastTapTimeMs", "()J", "setLastTapTimeMs", "(J)V", "leftContainer", "Lcom/google/android/material/circularreveal/CircularRevealFrameLayout;", "getLeftContainer", "()Lcom/google/android/material/circularreveal/CircularRevealFrameLayout;", "leftContainer$delegate", "Lkotlin/Lazy;", "leftContainerBackground", "Lorg/videolan/vlc/gui/view/HalfCircleView;", "getLeftContainerBackground", "()Lorg/videolan/vlc/gui/view/HalfCircleView;", "leftContainerBackground$delegate", "mScaleListener", "org/videolan/vlc/gui/video/VideoTouchDelegate$mScaleListener$1", "Lorg/videolan/vlc/gui/video/VideoTouchDelegate$mScaleListener$1;", "nbTimesTaped", "numberOfTaps", "getNumberOfTaps", "()I", "setNumberOfTaps", "(I)V", "resizeDelegate", "Lorg/videolan/vlc/gui/video/VideoPlayerResizeDelegate;", "getResizeDelegate", "()Lorg/videolan/vlc/gui/video/VideoPlayerResizeDelegate;", "rightContainer", "getRightContainer", "rightContainer$delegate", "rightContainerBackground", "getRightContainerBackground", "rightContainerBackground$delegate", "savedRate", "scaleGestureDetector", "Landroid/view/ScaleGestureDetector;", "getScaleGestureDetector", "()Landroid/view/ScaleGestureDetector;", "scaleGestureDetector$delegate", "getScreenConfig", "()Lorg/videolan/vlc/gui/video/ScreenConfig;", "setScreenConfig", "(Lorg/videolan/vlc/gui/video/ScreenConfig;)V", "seekAnimRunning", "seekBackground", "Landroid/widget/FrameLayout;", "getSeekBackground", "()Landroid/widget/FrameLayout;", "seekBackground$delegate", "seekContainer", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getSeekContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "seekContainer$delegate", "seekForwardFirst", "Landroid/widget/ImageView;", "getSeekForwardFirst", "()Landroid/widget/ImageView;", "seekForwardFirst$delegate", "seekForwardSecond", "getSeekForwardSecond", "seekForwardSecond$delegate", "seekLeftText", "Landroid/widget/TextView;", "getSeekLeftText", "()Landroid/widget/TextView;", "seekLeftText$delegate", "seekRewindFirst", "getSeekRewindFirst", "seekRewindFirst$delegate", "seekRewindSecond", "getSeekRewindSecond", "seekRewindSecond$delegate", "seekRightText", "getSeekRightText", "seekRightText$delegate", "value", "touchAction", "setTouchAction", "getTouchControls", "setTouchControls", "touchDownMs", "getTouchDownMs", "setTouchDownMs", "touchX", "touchY", "verticalTouchActive", "clearTouchAction", "", "dispatchGenericMotionEvent", "event", "Landroid/view/MotionEvent;", "doBrightnessTouch", "ychanged", "doSeekTouch", "coef", "gesturesize", "seek", "doVerticalTouchAction", "y_changed", "doVolumeTouch", "hideFastplay", "hideSeekOverlay", "immediate", "initBrightnessTouch", "initSeekOverlay", "()Lkotlin/Unit;", "isInAllowedBounds", "x", "y", "isSeeking", "onTouchEvent", "seekDelta", "delta", "seekDelta$vlc_android_release", "showFastPlay", "showSeek", "seekForward", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTouchDelegate.kt */
public final class VideoTouchDelegate {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long SEEK_TIMEOUT = 750;
    private static final String TAG = "VLC/VideoTouchDelegate";
    private AnimatorSet animatorSet = new AnimatorSet();
    private AnimatorSet fastPlayAnimatorSet = new AnimatorSet();
    private final float gestureSafetyMargin = ((float) KotlinExtensionsKt.getDp(48));
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean initInAllowedBounds;
    private float initTouchX;
    private float initTouchY;
    private boolean isFirstBrightnessGesture = true;
    private long lastMove;
    private boolean lastSeekWasForward = true;
    private long lastTapTimeMs;
    private final Lazy leftContainer$delegate = LazyKt.lazy(new VideoTouchDelegate$leftContainer$2(this));
    private final Lazy leftContainerBackground$delegate = LazyKt.lazy(new VideoTouchDelegate$leftContainerBackground$2(this));
    /* access modifiers changed from: private */
    public final VideoTouchDelegate$mScaleListener$1 mScaleListener = new VideoTouchDelegate$mScaleListener$1(this);
    private int nbTimesTaped;
    private int numberOfTaps;
    /* access modifiers changed from: private */
    public final VideoPlayerActivity player;
    private final Lazy rightContainer$delegate = LazyKt.lazy(new VideoTouchDelegate$rightContainer$2(this));
    private final Lazy rightContainerBackground$delegate = LazyKt.lazy(new VideoTouchDelegate$rightContainerBackground$2(this));
    private float savedRate = 1.0f;
    private final Lazy scaleGestureDetector$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new VideoTouchDelegate$scaleGestureDetector$2(this));
    private ScreenConfig screenConfig;
    private boolean seekAnimRunning;
    private final Lazy seekBackground$delegate = LazyKt.lazy(new VideoTouchDelegate$seekBackground$2(this));
    private final Lazy seekContainer$delegate = LazyKt.lazy(new VideoTouchDelegate$seekContainer$2(this));
    private final Lazy seekForwardFirst$delegate = LazyKt.lazy(new VideoTouchDelegate$seekForwardFirst$2(this));
    private final Lazy seekForwardSecond$delegate = LazyKt.lazy(new VideoTouchDelegate$seekForwardSecond$2(this));
    private final Lazy seekLeftText$delegate = LazyKt.lazy(new VideoTouchDelegate$seekLeftText$2(this));
    private final Lazy seekRewindFirst$delegate = LazyKt.lazy(new VideoTouchDelegate$seekRewindFirst$2(this));
    private final Lazy seekRewindSecond$delegate = LazyKt.lazy(new VideoTouchDelegate$seekRewindSecond$2(this));
    private final Lazy seekRightText$delegate = LazyKt.lazy(new VideoTouchDelegate$seekRightText$2(this));
    /* access modifiers changed from: private */
    public int touchAction;
    private int touchControls;
    private long touchDownMs;
    private float touchX = -1.0f;
    private float touchY = -1.0f;
    private final boolean tv;
    private boolean verticalTouchActive;

    public VideoTouchDelegate(VideoPlayerActivity videoPlayerActivity, int i, ScreenConfig screenConfig2, boolean z) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "player");
        Intrinsics.checkNotNullParameter(screenConfig2, "screenConfig");
        this.player = videoPlayerActivity;
        this.touchControls = i;
        this.screenConfig = screenConfig2;
        this.tv = z;
    }

    public final int getTouchControls() {
        return this.touchControls;
    }

    public final void setTouchControls(int i) {
        this.touchControls = i;
    }

    public final ScreenConfig getScreenConfig() {
        return this.screenConfig;
    }

    public final void setScreenConfig(ScreenConfig screenConfig2) {
        Intrinsics.checkNotNullParameter(screenConfig2, "<set-?>");
        this.screenConfig = screenConfig2;
    }

    /* access modifiers changed from: private */
    public final VideoPlayerResizeDelegate getResizeDelegate() {
        return this.player.getResizeDelegate();
    }

    public final Handler getHandler() {
        return this.handler;
    }

    public final void setHandler(Handler handler2) {
        Intrinsics.checkNotNullParameter(handler2, "<set-?>");
        this.handler = handler2;
    }

    public final int getNumberOfTaps() {
        return this.numberOfTaps;
    }

    public final void setNumberOfTaps(int i) {
        this.numberOfTaps = i;
    }

    public final long getLastTapTimeMs() {
        return this.lastTapTimeMs;
    }

    public final void setLastTapTimeMs(long j) {
        this.lastTapTimeMs = j;
    }

    public final long getTouchDownMs() {
        return this.touchDownMs;
    }

    public final void setTouchDownMs(long j) {
        this.touchDownMs = j;
    }

    /* access modifiers changed from: private */
    public final void setTouchAction(int i) {
        this.touchAction = i;
        String simpleName = getClass().getSimpleName();
        Log.d(simpleName, "touchAction " + i);
    }

    private final CircularRevealFrameLayout getRightContainer() {
        Object value = this.rightContainer$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (CircularRevealFrameLayout) value;
    }

    private final CircularRevealFrameLayout getLeftContainer() {
        Object value = this.leftContainer$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (CircularRevealFrameLayout) value;
    }

    private final HalfCircleView getRightContainerBackground() {
        Object value = this.rightContainerBackground$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (HalfCircleView) value;
    }

    private final HalfCircleView getLeftContainerBackground() {
        Object value = this.leftContainerBackground$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (HalfCircleView) value;
    }

    private final TextView getSeekRightText() {
        Object value = this.seekRightText$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (TextView) value;
    }

    private final TextView getSeekLeftText() {
        Object value = this.seekLeftText$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (TextView) value;
    }

    private final ImageView getSeekRewindFirst() {
        Object value = this.seekRewindFirst$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ImageView) value;
    }

    private final ImageView getSeekForwardFirst() {
        Object value = this.seekForwardFirst$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ImageView) value;
    }

    private final ImageView getSeekForwardSecond() {
        Object value = this.seekForwardSecond$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ImageView) value;
    }

    private final ImageView getSeekRewindSecond() {
        Object value = this.seekRewindSecond$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ImageView) value;
    }

    private final ConstraintLayout getSeekContainer() {
        Object value = this.seekContainer$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ConstraintLayout) value;
    }

    private final FrameLayout getSeekBackground() {
        Object value = this.seekBackground$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (FrameLayout) value;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoTouchDelegate$Companion;", "", "()V", "SEEK_TIMEOUT", "", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoTouchDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final ScaleGestureDetector getScaleGestureDetector() {
        return (ScaleGestureDetector) this.scaleGestureDetector$delegate.getValue();
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        MotionEvent motionEvent2 = motionEvent;
        Intrinsics.checkNotNullParameter(motionEvent2, NotificationCompat.CATEGORY_EVENT);
        if (this.player.isPlaylistVisible()) {
            setTouchAction(5);
            this.player.getOverlayDelegate().togglePlaylist();
            return true;
        }
        if (!this.player.isLocked() && this.touchAction != 7) {
            getScaleGestureDetector().onTouchEvent(motionEvent2);
            if (getScaleGestureDetector().isInProgress()) {
                setTouchAction(5);
                return true;
            }
        }
        if (!(this.touchControls == 0 && this.player.getFov$vlc_android_release() == 0.0f) && !this.player.isLocked()) {
            float x = (this.touchX == -1.0f || this.touchY == -1.0f) ? 0.0f : motionEvent.getX() - this.touchX;
            float y = (this.touchX == -1.0f || this.touchY == -1.0f) ? 0.0f : motionEvent.getY() - this.touchY;
            float abs = Math.abs(y / x);
            float f = (x / this.screenConfig.getMetrics().xdpi) * 2.54f;
            float coerceAtLeast = RangesKt.coerceAtLeast(((Math.abs(this.initTouchY - motionEvent.getY()) / this.screenConfig.getMetrics().xdpi) + 0.5f) * 2.0f, 1.0f);
            try {
                Pair pair = new Pair(Integer.valueOf(MathKt.roundToInt(motionEvent.getX())), Integer.valueOf(MathKt.roundToInt(motionEvent.getY())));
                int intValue = ((Number) pair.component1()).intValue();
                int intValue2 = ((Number) pair.component2()).intValue();
                long currentTimeMillis = System.currentTimeMillis();
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.touchDownMs = currentTimeMillis;
                    this.verticalTouchActive = false;
                    this.initTouchY = motionEvent.getY();
                    float x2 = motionEvent.getX();
                    this.initTouchX = x2;
                    this.initInAllowedBounds = isInAllowedBounds(x2, this.initTouchY);
                    this.touchY = this.initTouchY;
                    this.player.initAudioVolume$vlc_android_release();
                    if (this.touchAction == 7) {
                        i = 0;
                        VideoPlayerOverlayDelegate.hideOverlay$default(this.player.getOverlayDelegate(), false, false, 2, (Object) null);
                        PlaybackService service = this.player.getService();
                        if (service != null) {
                            service.setRate(this.savedRate, false);
                        }
                        this.player.getOverlayDelegate().hideInfo();
                    } else {
                        i = 0;
                    }
                    setTouchAction(i);
                    this.touchX = motionEvent.getX();
                    this.player.sendMouseEvent$vlc_android_release(i, intValue, intValue2);
                    VideoTouchDelegate$$ExternalSyntheticLambda4 videoTouchDelegate$$ExternalSyntheticLambda4 = new VideoTouchDelegate$$ExternalSyntheticLambda4(this);
                    if ((this.touchControls & 128) != 0 && isInAllowedBounds(this.touchX, this.touchY)) {
                        this.handler.postDelayed(videoTouchDelegate$$ExternalSyntheticLambda4, 250);
                    }
                } else if (action == 1) {
                    int i2 = this.touchAction;
                    if (i2 == 7) {
                        VideoPlayerOverlayDelegate.hideOverlay$default(this.player.getOverlayDelegate(), false, false, 2, (Object) null);
                        PlaybackService service2 = this.player.getService();
                        if (service2 != null) {
                            service2.setRate(this.savedRate, false);
                        }
                        hideFastplay();
                        setTouchAction(0);
                        return true;
                    } else if ((this.touchControls & 32) == 32 && i2 == 6) {
                        this.player.takeScreenshot();
                        return true;
                    } else {
                        int scaledTouchSlop = ViewConfiguration.get(this.player).getScaledTouchSlop();
                        if (this.touchAction == 5) {
                            setTouchAction(0);
                        }
                        this.player.sendMouseEvent$vlc_android_release(1, intValue, intValue2);
                        this.touchX = -1.0f;
                        this.touchY = -1.0f;
                        int i3 = this.touchAction;
                        if (i3 == 4) {
                            doSeekTouch(MathKt.roundToInt(coerceAtLeast), f, true);
                            return true;
                        } else if (i3 == 1 || i3 == 2) {
                            doVerticalTouchAction(y);
                            return true;
                        } else {
                            this.handler.removeCallbacksAndMessages((Object) null);
                            if (currentTimeMillis - this.touchDownMs > ((long) ViewConfiguration.getDoubleTapTimeout())) {
                                this.numberOfTaps = 0;
                                this.lastTapTimeMs = 0;
                            }
                            float f2 = (float) scaledTouchSlop;
                            if (Math.abs(motionEvent.getX() - this.initTouchX) < f2 && Math.abs(motionEvent.getY() - this.initTouchY) < f2) {
                                if (this.numberOfTaps <= 0 || currentTimeMillis - this.lastTapTimeMs >= ((long) ViewConfiguration.getDoubleTapTimeout())) {
                                    this.numberOfTaps = 1;
                                } else {
                                    this.numberOfTaps++;
                                }
                            }
                            this.lastTapTimeMs = currentTimeMillis;
                            if (this.numberOfTaps > 1 && !this.player.isLocked()) {
                                float xRange = (float) (this.screenConfig.getOrientation() == 2 ? this.screenConfig.getXRange() : this.screenConfig.getYRange());
                                if ((this.touchControls & 4) == 0 || motionEvent.getX() >= xRange / 4.0f) {
                                    if ((this.touchControls & 4) != 0) {
                                        double x3 = (double) motionEvent.getX();
                                        double d = (double) xRange;
                                        Double.isNaN(d);
                                        if (x3 > d * 0.75d) {
                                            seekDelta$vlc_android_release(Settings.INSTANCE.getVideoDoubleTapJumpDelay() * 1000);
                                        }
                                    }
                                    if ((this.touchControls & 8) != 0) {
                                        this.player.doPlayPause();
                                    }
                                } else {
                                    seekDelta$vlc_android_release((-Settings.INSTANCE.getVideoDoubleTapJumpDelay()) * 1000);
                                }
                            }
                            VideoTouchDelegate$$ExternalSyntheticLambda5 videoTouchDelegate$$ExternalSyntheticLambda5 = new VideoTouchDelegate$$ExternalSyntheticLambda5(this);
                            if ((this.touchControls & 12) != 0) {
                                this.handler.postDelayed(videoTouchDelegate$$ExternalSyntheticLambda5, (long) ViewConfiguration.getDoubleTapTimeout());
                            } else {
                                videoTouchDelegate$$ExternalSyntheticLambda5.run();
                            }
                        }
                    }
                } else if (action == 2) {
                    if ((this.touchControls & 32) == 32 && motionEvent.getPointerCount() == 3 && this.touchAction != 7) {
                        setTouchAction(6);
                    }
                    int i4 = this.touchAction;
                    if (i4 == 5 || i4 == 7) {
                        return false;
                    }
                    this.player.sendMouseEvent$vlc_android_release(2, intValue, intValue2);
                    if (this.player.getFov$vlc_android_release() != 0.0f) {
                        this.touchY = motionEvent.getY();
                        this.touchX = motionEvent.getX();
                        setTouchAction(3);
                        this.player.updateViewpoint$vlc_android_release((this.player.getFov$vlc_android_release() * (-x)) / ((float) this.screenConfig.getXRange()), (this.player.getFov$vlc_android_release() * (-y)) / ((float) this.screenConfig.getXRange()), 0.0f);
                    } else if (this.touchAction == 4 || abs <= 2.0f || !this.player.isOnPrimaryDisplay$vlc_android_release()) {
                        double d2 = (double) this.screenConfig.getMetrics().widthPixels;
                        Double.isNaN(d2);
                        if (((double) this.initTouchX) < d2 * 0.95d) {
                            doSeekTouch(MathKt.roundToInt(coerceAtLeast), f, false);
                        }
                    } else if (this.verticalTouchActive) {
                        this.touchY = motionEvent.getY();
                        this.touchX = motionEvent.getX();
                        doVerticalTouchAction(y);
                    } else if (((double) Math.abs(y / ((float) this.screenConfig.getYRange()))) < 0.05d) {
                        return false;
                    } else {
                        this.verticalTouchActive = true;
                        this.touchY = motionEvent.getY();
                        this.touchX = motionEvent.getX();
                        return false;
                    }
                }
                if (this.touchAction != 0) {
                    return true;
                }
                return false;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        } else {
            if (motionEvent.getAction() == 1 && this.touchAction != 5) {
                this.player.getOverlayDelegate().toggleOverlay();
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static final void onTouchEvent$lambda$0(VideoTouchDelegate videoTouchDelegate) {
        Intrinsics.checkNotNullParameter(videoTouchDelegate, "this$0");
        if (videoTouchDelegate.touchAction == 0) {
            PlaybackService service = videoTouchDelegate.player.getService();
            Intrinsics.checkNotNull(service);
            videoTouchDelegate.savedRate = service.getRate();
            PlaybackService service2 = videoTouchDelegate.player.getService();
            if (service2 != null) {
                service2.setRate(Settings.INSTANCE.getFastplaySpeed(), false);
            }
            videoTouchDelegate.showFastPlay();
            VideoPlayerOverlayDelegate.hideOverlay$default(videoTouchDelegate.player.getOverlayDelegate(), true, false, 2, (Object) null);
            videoTouchDelegate.setTouchAction(7);
        }
    }

    /* access modifiers changed from: private */
    public static final void onTouchEvent$lambda$1(VideoTouchDelegate videoTouchDelegate) {
        Intrinsics.checkNotNullParameter(videoTouchDelegate, "this$0");
        if (videoTouchDelegate.numberOfTaps == 1) {
            videoTouchDelegate.player.getHandler().sendEmptyMessage(videoTouchDelegate.player.isShowing() ? 9 : 8);
        }
    }

    private final boolean isInAllowedBounds(float f, float f2) {
        float f3 = this.gestureSafetyMargin;
        float f4 = this.gestureSafetyMargin;
        return f2 <= ((float) this.screenConfig.getMetrics().heightPixels) - f4 && f3 <= f2 && f <= ((float) this.screenConfig.getMetrics().widthPixels) - this.gestureSafetyMargin && f4 <= f;
    }

    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        if (!this.player.isLoading$vlc_android_release() && (motionEvent.getSource() & InputDeviceCompat.SOURCE_JOYSTICK) == 16777232 && motionEvent.getAction() == 2) {
            InputDevice device = motionEvent.getDevice();
            float axisValue = motionEvent.getAxisValue(15);
            float axisValue2 = motionEvent.getAxisValue(16);
            if (!(device == null || Math.abs(axisValue) == 1.0f || Math.abs(axisValue2) == 1.0f)) {
                float centeredAxis = AndroidDevices.INSTANCE.getCenteredAxis(motionEvent, device, 0);
                float centeredAxis2 = AndroidDevices.INSTANCE.getCenteredAxis(motionEvent, device, 1);
                float centeredAxis3 = AndroidDevices.INSTANCE.getCenteredAxis(motionEvent, device, 14);
                if (System.currentTimeMillis() - this.lastMove > 300) {
                    if (((double) Math.abs(centeredAxis)) > 0.3d) {
                        if (this.tv) {
                            this.player.navigateDvdMenu$vlc_android_release(centeredAxis > 0.0f ? 22 : 21);
                        } else {
                            seekDelta$vlc_android_release(centeredAxis > 0.0f ? 10000 : -10000);
                        }
                    } else if (((double) Math.abs(centeredAxis2)) > 0.3d) {
                        if (this.tv) {
                            this.player.navigateDvdMenu$vlc_android_release(centeredAxis > 0.0f ? 19 : 20);
                        } else {
                            if (this.isFirstBrightnessGesture) {
                                initBrightnessTouch();
                            }
                            this.player.changeBrightness$vlc_android_release((-centeredAxis2) / 10.0f);
                        }
                    } else if (((double) Math.abs(centeredAxis3)) > 0.3d) {
                        VideoPlayerActivity videoPlayerActivity = this.player;
                        videoPlayerActivity.setVolume$vlc_android_release((float) videoPlayerActivity.getAudiomanager$vlc_android_release().getStreamVolume(3));
                        this.player.setAudioVolume$vlc_android_release(RangesKt.coerceIn(((int) this.player.getVolume$vlc_android_release()) + (-((int) ((centeredAxis3 / ((float) 7)) * ((float) this.player.getAudioMax$vlc_android_release())))), 0, this.player.getAudioMax$vlc_android_release()));
                    }
                    this.lastMove = System.currentTimeMillis();
                }
                return true;
            }
        }
        return false;
    }

    public final boolean isSeeking() {
        return this.touchAction == 4;
    }

    public final void clearTouchAction() {
        setTouchAction(0);
    }

    private final void doVerticalTouchAction(float f) {
        boolean z = ((float) ((int) this.touchX)) > ((float) (this.screenConfig.getMetrics().widthPixels * 4)) / 7.0f;
        if ((!z && ((float) ((int) this.touchX)) < ((float) (this.screenConfig.getMetrics().widthPixels * 3)) / 7.0f) || z) {
            int i = this.touchControls;
            boolean z2 = (i & 1) != 0;
            boolean z3 = (i & 2) != 0;
            if (z2 || z3) {
                if (z) {
                    if (z2) {
                        doVolumeTouch(f);
                    } else {
                        doBrightnessTouch(f);
                    }
                } else if (z3) {
                    doBrightnessTouch(f);
                } else {
                    doVolumeTouch(f);
                }
                VideoPlayerOverlayDelegate.hideOverlay$default(this.player.getOverlayDelegate(), true, false, 2, (Object) null);
            }
        }
    }

    private final void doSeekTouch(int i, float f, boolean z) {
        double d;
        long j;
        if ((this.touchControls & 16) != 0 && this.initInAllowedBounds) {
            int i2 = i == 0 ? 1 : i;
            if (Math.abs(f) >= 1.0f) {
                PlaybackService service = this.player.getService();
                Intrinsics.checkNotNull(service);
                if (service.isSeekable()) {
                    int i3 = this.touchAction;
                    if (i3 == 0 || i3 == 4) {
                        setTouchAction(4);
                        PlaybackService service2 = this.player.getService();
                        Intrinsics.checkNotNull(service2);
                        long length = service2.getLength();
                        PlaybackService service3 = this.player.getService();
                        Intrinsics.checkNotNull(service3);
                        long time = service3.getTime();
                        double signum = (double) Math.signum(f);
                        double d2 = (double) 600000;
                        double pow = Math.pow((double) (f / ((float) 8)), 4.0d);
                        Double.isNaN(d2);
                        double d3 = d2 * pow;
                        double d4 = (double) PathInterpolatorCompat.MAX_NUM_POINTS;
                        Double.isNaN(d4);
                        Double.isNaN(signum);
                        int i4 = i2;
                        double d5 = (double) i4;
                        Double.isNaN(d5);
                        int i5 = (int) ((signum * (d3 + d4)) / d5);
                        if (i5 > 0 && ((long) i5) + time > length) {
                            i5 = (int) (length - time);
                        }
                        if (i5 < 0 && ((long) i5) + time < 0) {
                            i5 = (int) (-time);
                        }
                        if (!z || length <= 0) {
                            d = d5;
                            j = time;
                        } else {
                            d = d5;
                            j = time;
                            VideoPlayerActivity.seek$vlc_android_release$default(this.player, ((long) i5) + time, length, false, false, 12, (Object) null);
                        }
                        if (length > 0) {
                            VideoPlayerOverlayDelegate overlayDelegate = this.player.getOverlayDelegate();
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            String str = "";
                            String str2 = i5 >= 0 ? Marker.ANY_NON_NULL_MARKER : str;
                            long j2 = (long) i5;
                            String millisToString = Tools.millisToString(j2);
                            String millisToString2 = Tools.millisToString(j + j2);
                            if (i4 > 1) {
                                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                                Double.isNaN(d);
                                str = String.format(" x%.1g", Arrays.copyOf(new Object[]{Double.valueOf(1.0d / d)}, 1));
                                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                            }
                            String format = String.format("%s%s (%s)%s", Arrays.copyOf(new Object[]{str2, millisToString, millisToString2, str}, 4));
                            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                            VideoPlayerOverlayDelegate.showInfo$default(overlayDelegate, format, 50, (String) null, 4, (Object) null);
                            return;
                        }
                        VideoPlayerOverlayDelegate.showInfo$default(this.player.getOverlayDelegate(), R.string.unseekable_stream, 1000, 0, 4, (Object) null);
                    }
                }
            }
        }
    }

    private final void doVolumeTouch(float f) {
        if (this.initInAllowedBounds) {
            int i = this.touchAction;
            if (i == 0 || i == 1) {
                int audioMax$vlc_android_release = this.player.getAudioMax$vlc_android_release();
                float f2 = (float) audioMax$vlc_android_release;
                float f3 = -((f / ((float) this.screenConfig.getYRange())) * f2 * 1.25f);
                VideoPlayerActivity videoPlayerActivity = this.player;
                videoPlayerActivity.setVolume$vlc_android_release(videoPlayerActivity.getVolume$vlc_android_release() + f3);
                int coerceIn = RangesKt.coerceIn((int) this.player.getVolume$vlc_android_release(), 0, (this.player.isAudioBoostEnabled$vlc_android_release() ? 2 : 1) * audioMax$vlc_android_release);
                if (f3 < 0.0f) {
                    this.player.setOriginalVol$vlc_android_release((float) coerceIn);
                }
                if (f3 != 0.0f) {
                    if (coerceIn <= audioMax$vlc_android_release) {
                        this.player.setAudioVolume$vlc_android_release(coerceIn);
                        setTouchAction(1);
                    } else if (this.player.isAudioBoostEnabled$vlc_android_release()) {
                        if (this.player.getOriginalVol$vlc_android_release() < f2) {
                            this.player.displayWarningToast();
                            this.player.setAudioVolume$vlc_android_release(audioMax$vlc_android_release);
                        } else {
                            this.player.setAudioVolume$vlc_android_release(coerceIn);
                        }
                        setTouchAction(1);
                    }
                }
            }
        }
    }

    private final void initBrightnessTouch() {
        float f;
        WindowManager.LayoutParams attributes = this.player.getWindow().getAttributes();
        if (attributes.screenBrightness == -1.0f) {
            ContentResolver contentResolver = this.player.getApplicationContext().getContentResolver();
            f = Settings.System.getInt(contentResolver, "screen_brightness_mode", 1) == 1 ? 0.5f : ((float) Settings.System.getInt(contentResolver, "screen_brightness", 128)) / ((float) 255);
        } else {
            f = attributes.screenBrightness;
        }
        attributes.screenBrightness = f;
        this.player.getWindow().setAttributes(attributes);
        this.isFirstBrightnessGesture = false;
    }

    private final void doBrightnessTouch(float f) {
        if (this.initInAllowedBounds) {
            int i = this.touchAction;
            if (i == 0 || i == 2) {
                if (this.isFirstBrightnessGesture) {
                    initBrightnessTouch();
                }
                setTouchAction(2);
                this.player.changeBrightness$vlc_android_release(((-f) / ((float) this.screenConfig.getYRange())) * 1.25f);
            }
        }
    }

    public final void seekDelta$vlc_android_release(int i) {
        PlaybackService service = this.player.getService();
        if (service != null && service.getLength() > 0 && service.isSeekable()) {
            long time = this.player.getTime() + ((long) i);
            if (time < 0) {
                time = 0;
            }
            if (time > service.getLength()) {
                time = service.getLength();
            }
            VideoPlayerActivity.seek$default(this.player, time, false, 2, (Object) null);
            StringBuilder sb = new StringBuilder();
            boolean z = i >= 0;
            initSeekOverlay();
            if (this.lastSeekWasForward != z) {
                this.animatorSet.cancel();
                hideSeekOverlay(true);
            }
            if (!(this.nbTimesTaped == 0 || this.lastSeekWasForward == z)) {
                this.nbTimesTaped = 0;
            }
            this.nbTimesTaped++;
            this.lastSeekWasForward = z;
            if (service.getTime() > 0 && service.getTime() < service.getLength()) {
                int i2 = this.nbTimesTaped;
                int i3 = (int) (((float) i) / 1000.0f);
                if (i2 != -1) {
                    i3 *= i2;
                }
                sb.append(i3);
                sb.append("s ");
            }
            sb.append("(");
            sb.append(Tools.millisToString(service.getTime()));
            sb.append(')');
            showSeek(z).setText(sb.toString());
        }
    }

    private final void showFastPlay() {
        initSeekOverlay();
        View findViewById = this.player.findViewById(R.id.fastPlayContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        LinearLayout linearLayout = (LinearLayout) findViewById;
        View findViewById2 = this.player.findViewById(R.id.fastPlayTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        ((TextView) findViewById2).setText(this.player.getString(R.string.fastplay_title, new Object[]{Strings.readableString(org.videolan.tools.Settings.INSTANCE.getFastplaySpeed())}));
        KotlinExtensionsKt.setVisible(linearLayout);
        linearLayout.animate().alpha(1.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.player.findViewById(R.id.fastPlayForwardFirst), "alpha", new float[]{1.0f, 0.0f, 0.0f});
        ofFloat.setDuration(SEEK_TIMEOUT);
        ofFloat.setRepeatCount(-1);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.player.findViewById(R.id.fastPlayForwardSecond), "alpha", new float[]{0.0f, 1.0f, 0.0f});
        ofFloat2.setDuration(SEEK_TIMEOUT);
        ofFloat2.setRepeatCount(-1);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.fastPlayAnimatorSet = animatorSet2;
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        this.fastPlayAnimatorSet.start();
    }

    private final void hideFastplay() {
        View findViewById = this.player.findViewById(R.id.fastPlayContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        LinearLayout linearLayout = (LinearLayout) findViewById;
        linearLayout.animate().alpha(0.0f).withEndAction(new VideoTouchDelegate$$ExternalSyntheticLambda3(linearLayout));
        this.fastPlayAnimatorSet.cancel();
    }

    /* access modifiers changed from: private */
    public static final void hideFastplay$lambda$3(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "$container");
        KotlinExtensionsKt.setGone(linearLayout);
    }

    private final TextView showSeek(boolean z) {
        initSeekOverlay();
        CircularRevealFrameLayout rightContainer = z ? getRightContainer() : getLeftContainer();
        HalfCircleView rightContainerBackground = z ? getRightContainerBackground() : getLeftContainerBackground();
        TextView seekRightText = z ? getSeekRightText() : getSeekLeftText();
        rightContainer.post(new VideoTouchDelegate$$ExternalSyntheticLambda2(this, z ? getSeekForwardFirst() : getSeekRewindFirst(), z ? getSeekForwardSecond() : getSeekRewindSecond(), z, rightContainer, rightContainerBackground, seekRightText));
        return seekRightText;
    }

    /* access modifiers changed from: private */
    public static final void showSeek$lambda$4(VideoTouchDelegate videoTouchDelegate, ImageView imageView, ImageView imageView2, boolean z, CircularRevealFrameLayout circularRevealFrameLayout, HalfCircleView halfCircleView, TextView textView) {
        VideoTouchDelegate videoTouchDelegate2 = videoTouchDelegate;
        ImageView imageView3 = imageView;
        ImageView imageView4 = imageView2;
        CircularRevealFrameLayout circularRevealFrameLayout2 = circularRevealFrameLayout;
        HalfCircleView halfCircleView2 = halfCircleView;
        TextView textView2 = textView;
        Intrinsics.checkNotNullParameter(videoTouchDelegate2, "this$0");
        Intrinsics.checkNotNullParameter(imageView3, "$imageFirst");
        Intrinsics.checkNotNullParameter(imageView4, "$imageSecond");
        Intrinsics.checkNotNullParameter(circularRevealFrameLayout2, "$container");
        Intrinsics.checkNotNullParameter(halfCircleView2, "$containerBackground");
        Intrinsics.checkNotNullParameter(textView2, "$textView");
        if (AndroidDevices.INSTANCE.isTv()) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(videoTouchDelegate.getSeekContainer());
            constraintSet.connect(R.id.rightContainerBackground, 6, R.id.seekRightContainer, 6);
            constraintSet.connect(R.id.rightContainerBackground, 3, R.id.seekRightContainer, 3);
            constraintSet.connect(R.id.rightContainerBackground, 4, R.id.seekRightContainer, 4);
            constraintSet.setMargin(R.id.seekRightText, 7, videoTouchDelegate2.player.getResources().getDimensionPixelSize(R.dimen.tv_overscan_horizontal));
            constraintSet.connect(R.id.leftContainerBackground, 7, R.id.seekLeftContainer, 7);
            constraintSet.connect(R.id.leftContainerBackground, 3, R.id.seekLeftContainer, 3);
            constraintSet.connect(R.id.leftContainerBackground, 4, R.id.seekLeftContainer, 4);
            constraintSet.setMargin(R.id.seekLeftText, 6, videoTouchDelegate2.player.getResources().getDimensionPixelSize(R.dimen.tv_overscan_horizontal));
            videoTouchDelegate.getSeekForwardFirst().setImageResource(R.drawable.ic_half_seek_forward_tv);
            videoTouchDelegate.getSeekForwardSecond().setImageResource(R.drawable.ic_half_seek_forward_tv);
            videoTouchDelegate.getSeekRewindFirst().setImageResource(R.drawable.ic_half_seek_rewind_tv);
            videoTouchDelegate.getSeekRewindSecond().setImageResource(R.drawable.ic_half_seek_rewind_tv);
            videoTouchDelegate.getSeekRightText().setTextSize(2, 28.0f);
            videoTouchDelegate.getSeekLeftText().setTextSize(2, 28.0f);
            constraintSet.applyTo(videoTouchDelegate.getSeekContainer());
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(videoTouchDelegate.getSeekBackground(), "alpha", new float[]{1.0f});
        ofFloat.setDuration(200);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView3, "alpha", new float[]{1.0f, 0.0f});
        ofFloat2.setDuration(500);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView4, "alpha", new float[]{0.0f, 1.0f, 0.0f});
        ofFloat3.setDuration(SEEK_TIMEOUT);
        int width = circularRevealFrameLayout.getWidth();
        int i = z ? width * 2 : -width;
        videoTouchDelegate2.animatorSet = new AnimatorSet();
        Animator createCircularReveal = CircularRevealCompat.createCircularReveal(circularRevealFrameLayout2, (float) i, (float) (circularRevealFrameLayout.getHeight() / 2), 0.0f, ((float) circularRevealFrameLayout.getWidth()) * ((float) 2));
        Intrinsics.checkNotNullExpressionValue(createCircularReveal, "createCircularReveal(...)");
        createCircularReveal.setDuration(SEEK_TIMEOUT);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(halfCircleView2, "alpha", new float[]{0.0f, 1.0f});
        ofFloat4.setDuration(300);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(textView2, "alpha", new float[]{0.0f, 1.0f});
        ofFloat5.setDuration(300);
        Intrinsics.checkNotNull(ofFloat2);
        Intrinsics.checkNotNull(ofFloat3);
        ArrayList arrayListOf = CollectionsKt.arrayListOf(ofFloat2, ofFloat3);
        if (!AndroidDevices.INSTANCE.isTv()) {
            arrayListOf.add(createCircularReveal);
        }
        if (!videoTouchDelegate2.seekAnimRunning) {
            arrayListOf.add(ofFloat4);
        }
        if (!videoTouchDelegate2.seekAnimRunning) {
            arrayListOf.add(ofFloat5);
        }
        videoTouchDelegate2.seekAnimRunning = true;
        videoTouchDelegate.getSeekRightText().animate().cancel();
        videoTouchDelegate.getSeekLeftText().animate().cancel();
        videoTouchDelegate.getRightContainerBackground().animate().cancel();
        videoTouchDelegate.getLeftContainerBackground().animate().cancel();
        videoTouchDelegate2.animatorSet.playTogether(arrayListOf);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(videoTouchDelegate.getSeekBackground(), "alpha", new float[]{0.0f});
        ofFloat.setDuration(200);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playSequentially(new Animator[]{videoTouchDelegate2.animatorSet, ofFloat6});
        videoTouchDelegate2.player.getHandler().removeMessages(10);
        videoTouchDelegate2.player.getHandler().sendEmptyMessageDelayed(10, SEEK_TIMEOUT);
        if (!AndroidDevices.INSTANCE.isTv()) {
            circularRevealFrameLayout2.setVisibility(0);
        }
        animatorSet2.start();
    }

    public static /* synthetic */ void hideSeekOverlay$default(VideoTouchDelegate videoTouchDelegate, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        videoTouchDelegate.hideSeekOverlay(z);
    }

    public final void hideSeekOverlay(boolean z) {
        this.seekAnimRunning = false;
        getRightContainer().setVisibility(4);
        getLeftContainer().setVisibility(4);
        if (z) {
            getSeekRightText().animate().cancel();
            getSeekLeftText().animate().cancel();
            getRightContainerBackground().animate().cancel();
            getLeftContainerBackground().animate().cancel();
            getSeekRightText().setAlpha(0.0f);
            getSeekLeftText().setAlpha(0.0f);
            getRightContainerBackground().setAlpha(0.0f);
            getLeftContainerBackground().setAlpha(0.0f);
        } else {
            getSeekRightText().animate().alpha(0.0f).withEndAction(new VideoTouchDelegate$$ExternalSyntheticLambda0(this));
            getSeekLeftText().animate().alpha(0.0f).withEndAction(new VideoTouchDelegate$$ExternalSyntheticLambda1(this));
            getRightContainerBackground().animate().alpha(0.0f);
            getLeftContainerBackground().animate().alpha(0.0f);
        }
        this.nbTimesTaped = 0;
        getSeekForwardFirst().setAlpha(0.0f);
        getSeekForwardSecond().setAlpha(0.0f);
        getSeekRewindFirst().setAlpha(0.0f);
        getSeekRewindSecond().setAlpha(0.0f);
    }

    /* access modifiers changed from: private */
    public static final void hideSeekOverlay$lambda$5(VideoTouchDelegate videoTouchDelegate) {
        Intrinsics.checkNotNullParameter(videoTouchDelegate, "this$0");
        videoTouchDelegate.getSeekRightText().setText("");
    }

    /* access modifiers changed from: private */
    public static final void hideSeekOverlay$lambda$6(VideoTouchDelegate videoTouchDelegate) {
        Intrinsics.checkNotNullParameter(videoTouchDelegate, "this$0");
        videoTouchDelegate.getSeekLeftText().setText("");
    }

    private final Unit initSeekOverlay() {
        ViewStubCompat viewStubCompat = (ViewStubCompat) this.player.findViewById(R.id.player_seek_stub);
        if (viewStubCompat == null) {
            return null;
        }
        KotlinExtensionsKt.setVisible(viewStubCompat);
        return Unit.INSTANCE;
    }
}
