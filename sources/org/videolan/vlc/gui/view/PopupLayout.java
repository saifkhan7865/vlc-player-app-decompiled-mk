package org.videolan.vlc.gui.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.videolan.libvlc.interfaces.IVLCVout;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 <2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001<B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u001f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010$\u001a\u00020%J\u0018\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u000bH\u0002J\u0010\u0010)\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0010\u0010-\u001a\u00020+2\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0010\u0010.\u001a\u00020%2\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0018\u0010/\u001a\u00020+2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0016J\u000e\u00104\u001a\u00020%2\u0006\u00105\u001a\u00020\u000eJ\u000e\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020!J\u0016\u00108\u001a\u00020%2\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000bJ\b\u0010;\u001a\u00020%H\u0002R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lorg/videolan/vlc/gui/view/PopupLayout;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Landroid/view/ScaleGestureDetector$OnScaleGestureListener;", "Landroid/view/View$OnTouchListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "gestureDetector", "Landroidx/core/view/GestureDetectorCompat;", "initialTouchX", "", "initialTouchY", "initialX", "initialY", "mLayoutParams", "Landroid/view/WindowManager$LayoutParams;", "popupHeight", "popupWidth", "scaleFactor", "", "scaleGestureDetector", "Landroid/view/ScaleGestureDetector;", "screenHeight", "screenSize", "Landroid/util/DisplayMetrics;", "screenWidth", "vlcVout", "Lorg/videolan/libvlc/interfaces/IVLCVout;", "windowManager", "Landroid/view/WindowManager;", "close", "", "containInScreen", "width", "height", "init", "onScale", "", "detector", "onScaleBegin", "onScaleEnd", "onTouch", "v", "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "setGestureDetector", "gdc", "setVLCVOut", "vout", "setViewSize", "requestedWidth", "requestedHeight", "updateWindowSize", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PopupLayout.kt */
public final class PopupLayout extends ConstraintLayout implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "VLC/PopupView";
    private GestureDetectorCompat gestureDetector;
    private float initialTouchX;
    private float initialTouchY;
    private int initialX;
    private int initialY;
    private WindowManager.LayoutParams mLayoutParams;
    private int popupHeight;
    private int popupWidth;
    private double scaleFactor = 1.0d;
    private ScaleGestureDetector scaleGestureDetector;
    private int screenHeight;
    private DisplayMetrics screenSize;
    private int screenWidth;
    private IVLCVout vlcVout;
    private WindowManager windowManager;

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector2) {
        Intrinsics.checkNotNullParameter(scaleGestureDetector2, "detector");
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PopupLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PopupLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        init(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PopupLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        init(context);
    }

    public final void setVLCVOut(IVLCVout iVLCVout) {
        Intrinsics.checkNotNullParameter(iVLCVout, "vout");
        this.vlcVout = iVLCVout;
        Intrinsics.checkNotNull(iVLCVout);
        iVLCVout.setWindowSize(this.popupWidth, this.popupHeight);
    }

    public final void close() {
        setKeepScreenOn(false);
        WindowManager windowManager2 = this.windowManager;
        Intrinsics.checkNotNull(windowManager2);
        windowManager2.removeView(this);
        this.windowManager = null;
        this.vlcVout = null;
    }

    public final void setGestureDetector(GestureDetectorCompat gestureDetectorCompat) {
        Intrinsics.checkNotNullParameter(gestureDetectorCompat, "gdc");
        this.gestureDetector = gestureDetectorCompat;
    }

    public final void setViewSize(int i, int i2) {
        int i3 = this.screenWidth;
        if (i > i3) {
            i2 = (i2 * i3) / i;
            i = i3;
        }
        int i4 = this.screenHeight;
        if (i2 > i4) {
            i = (i * i4) / i2;
            i2 = i4;
        }
        containInScreen(i, i2);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        WindowManager.LayoutParams layoutParams2 = null;
        if (layoutParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams = null;
        }
        layoutParams.width = i;
        WindowManager.LayoutParams layoutParams3 = this.mLayoutParams;
        if (layoutParams3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams3 = null;
        }
        layoutParams3.height = i2;
        WindowManager windowManager2 = this.windowManager;
        Intrinsics.checkNotNull(windowManager2);
        View view = this;
        WindowManager.LayoutParams layoutParams4 = this.mLayoutParams;
        if (layoutParams4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
        } else {
            layoutParams2 = layoutParams4;
        }
        windowManager2.updateViewLayout(view, layoutParams2);
        IVLCVout iVLCVout = this.vlcVout;
        if (iVLCVout != null) {
            Intrinsics.checkNotNull(iVLCVout);
            iVLCVout.setWindowSize(this.popupWidth, this.popupHeight);
        }
    }

    private final void init(Context context) {
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.windowManager = (WindowManager) ContextCompat.getSystemService(applicationContext, WindowManager.class);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager2 = this.windowManager;
        Intrinsics.checkNotNull(windowManager2);
        windowManager2.getDefaultDisplay().getMetrics(displayMetrics);
        this.screenSize = displayMetrics;
        this.popupWidth = context.getResources().getDimensionPixelSize(R.dimen.video_pip_width);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.video_pip_height);
        this.popupHeight = dimensionPixelSize;
        float f = ((float) this.popupWidth) / ((float) dimensionPixelSize);
        int i = ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getInt(SettingsKt.CUSTOM_POPUP_HEIGHT, -1);
        if (i != -1) {
            this.popupHeight = i;
            this.popupWidth = (int) (((float) i) * f);
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(this.popupWidth, this.popupHeight, AndroidUtil.isOOrLater ? 2038 : 2002, 8, -1);
        layoutParams.gravity = 8388691;
        layoutParams.x = 50;
        layoutParams.y = 50;
        this.scaleGestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
        WindowManager windowManager3 = this.windowManager;
        Intrinsics.checkNotNull(windowManager3);
        windowManager3.addView(this, layoutParams);
        if (!isInEditMode()) {
            ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
            Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.view.WindowManager.LayoutParams");
            this.mLayoutParams = (WindowManager.LayoutParams) layoutParams2;
        }
        updateWindowSize();
    }

    private final void updateWindowSize() {
        Point point = new Point();
        WindowManager windowManager2 = this.windowManager;
        Intrinsics.checkNotNull(windowManager2);
        windowManager2.getDefaultDisplay().getSize(point);
        this.screenWidth = point.x;
        this.screenHeight = point.y;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r7.isInProgress() == false) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
        /*
            r6 = this;
            java.lang.String r0 = "v"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r7 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r7)
            android.view.WindowManager r7 = r6.windowManager
            r0 = 0
            if (r7 != 0) goto L_0x0010
            return r0
        L_0x0010:
            android.view.ScaleGestureDetector r7 = r6.scaleGestureDetector
            if (r7 == 0) goto L_0x001a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            r7.onTouchEvent(r8)
        L_0x001a:
            androidx.core.view.GestureDetectorCompat r7 = r6.gestureDetector
            r1 = 1
            if (r7 == 0) goto L_0x0029
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            boolean r7 = r7.onTouchEvent(r8)
            if (r7 == 0) goto L_0x0029
            return r1
        L_0x0029:
            int r7 = r8.getAction()
            r2 = 0
            java.lang.String r3 = "mLayoutParams"
            if (r7 == 0) goto L_0x009f
            if (r7 == r1) goto L_0x009e
            r4 = 2
            if (r7 == r4) goto L_0x0038
            goto L_0x0046
        L_0x0038:
            android.view.ScaleGestureDetector r7 = r6.scaleGestureDetector
            if (r7 == 0) goto L_0x0047
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            boolean r7 = r7.isInProgress()
            if (r7 != 0) goto L_0x0046
            goto L_0x0047
        L_0x0046:
            return r0
        L_0x0047:
            android.view.WindowManager$LayoutParams r7 = r6.mLayoutParams
            if (r7 != 0) goto L_0x004f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r7 = r2
        L_0x004f:
            int r0 = r6.initialX
            float r4 = r8.getRawX()
            float r5 = r6.initialTouchX
            float r4 = r4 - r5
            int r4 = (int) r4
            int r0 = r0 + r4
            r7.x = r0
            android.view.WindowManager$LayoutParams r7 = r6.mLayoutParams
            if (r7 != 0) goto L_0x0064
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r7 = r2
        L_0x0064:
            int r0 = r6.initialY
            float r8 = r8.getRawY()
            float r4 = r6.initialTouchY
            float r8 = r8 - r4
            int r8 = (int) r8
            int r0 = r0 - r8
            r7.y = r0
            android.view.WindowManager$LayoutParams r7 = r6.mLayoutParams
            if (r7 != 0) goto L_0x0079
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r7 = r2
        L_0x0079:
            int r7 = r7.width
            android.view.WindowManager$LayoutParams r8 = r6.mLayoutParams
            if (r8 != 0) goto L_0x0083
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r8 = r2
        L_0x0083:
            int r8 = r8.height
            r6.containInScreen(r7, r8)
            android.view.WindowManager r7 = r6.windowManager
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            r8 = r6
            android.view.View r8 = (android.view.View) r8
            android.view.WindowManager$LayoutParams r0 = r6.mLayoutParams
            if (r0 != 0) goto L_0x0098
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x0099
        L_0x0098:
            r2 = r0
        L_0x0099:
            android.view.ViewGroup$LayoutParams r2 = (android.view.ViewGroup.LayoutParams) r2
            r7.updateViewLayout(r8, r2)
        L_0x009e:
            return r1
        L_0x009f:
            android.view.WindowManager$LayoutParams r7 = r6.mLayoutParams
            if (r7 != 0) goto L_0x00a7
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r7 = r2
        L_0x00a7:
            int r7 = r7.x
            r6.initialX = r7
            android.view.WindowManager$LayoutParams r7 = r6.mLayoutParams
            if (r7 != 0) goto L_0x00b3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x00b4
        L_0x00b3:
            r2 = r7
        L_0x00b4:
            int r7 = r2.y
            r6.initialY = r7
            float r7 = r8.getRawX()
            r6.initialTouchX = r7
            float r7 = r8.getRawY()
            r6.initialTouchY = r7
            r6.updateWindowSize()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.view.PopupLayout.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector2) {
        Intrinsics.checkNotNullParameter(scaleGestureDetector2, "detector");
        double d = this.scaleFactor;
        double scaleFactor2 = (double) scaleGestureDetector2.getScaleFactor();
        Double.isNaN(scaleFactor2);
        double d2 = d * scaleFactor2;
        this.scaleFactor = d2;
        this.scaleFactor = RangesKt.coerceIn(d2, 0.1d, 5.0d);
        double width = (double) getWidth();
        double d3 = this.scaleFactor;
        Double.isNaN(width);
        this.popupWidth = (int) (width * d3);
        double height = (double) getHeight();
        double d4 = this.scaleFactor;
        Double.isNaN(height);
        this.popupHeight = (int) (height * d4);
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector2) {
        Intrinsics.checkNotNullParameter(scaleGestureDetector2, "detector");
        setViewSize(this.popupWidth, this.popupHeight);
        Settings settings = Settings.INSTANCE;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        SettingsKt.putSingle((SharedPreferences) settings.getInstance(context), SettingsKt.CUSTOM_POPUP_HEIGHT, Integer.valueOf(this.popupHeight));
        this.scaleFactor = 1.0d;
    }

    private final void containInScreen(int i, int i2) {
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        WindowManager.LayoutParams layoutParams2 = null;
        if (layoutParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams = null;
        }
        WindowManager.LayoutParams layoutParams3 = this.mLayoutParams;
        if (layoutParams3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams3 = null;
        }
        layoutParams.x = RangesKt.coerceAtLeast(layoutParams3.x, 0);
        WindowManager.LayoutParams layoutParams4 = this.mLayoutParams;
        if (layoutParams4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams4 = null;
        }
        WindowManager.LayoutParams layoutParams5 = this.mLayoutParams;
        if (layoutParams5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams5 = null;
        }
        layoutParams4.y = RangesKt.coerceAtLeast(layoutParams5.y, 0);
        WindowManager.LayoutParams layoutParams6 = this.mLayoutParams;
        if (layoutParams6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams6 = null;
        }
        if (layoutParams6.x + i > this.screenWidth) {
            WindowManager.LayoutParams layoutParams7 = this.mLayoutParams;
            if (layoutParams7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
                layoutParams7 = null;
            }
            layoutParams7.x = this.screenWidth - i;
        }
        WindowManager.LayoutParams layoutParams8 = this.mLayoutParams;
        if (layoutParams8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            layoutParams8 = null;
        }
        if (layoutParams8.y + i2 > this.screenHeight) {
            WindowManager.LayoutParams layoutParams9 = this.mLayoutParams;
            if (layoutParams9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mLayoutParams");
            } else {
                layoutParams2 = layoutParams9;
            }
            layoutParams2.y = this.screenHeight - i2;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/view/PopupLayout$Companion;", "", "()V", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PopupLayout.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
