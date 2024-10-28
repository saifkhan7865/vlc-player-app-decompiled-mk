package org.videolan.vlc.gui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\b&\u0018\u0000 >2\u00020\u0001:\u0001>B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J0\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020\fH\u0014J\u0018\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\fH\u0014J(\u00100\u001a\u00020\"2\u0006\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\f2\u0006\u00103\u001a\u00020\f2\u0006\u00104\u001a\u00020\fH\u0014J(\u00105\u001a\u00020\"2\u0006\u00106\u001a\u00020\f2\u0006\u00101\u001a\u00020\f2\u0006\u00107\u001a\u00020\f2\u0006\u00103\u001a\u00020\fH\u0014J\u0010\u00108\u001a\u00020$2\u0006\u00109\u001a\u00020&H\u0016J\u000e\u0010:\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\fJ\u000e\u0010;\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\fJ\b\u0010<\u001a\u00020\"H\u0002J\u0010\u0010=\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\fH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006?"}, d2 = {"Lorg/videolan/vlc/gui/view/FlingViewGroup;", "Landroid/view/ViewGroup;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "initialMotionEventX", "", "initialMotionX", "initialMotionY", "interceptTouchState", "", "lastActionDownMillis", "", "lastInterceptDownY", "lastX", "maximumVelocity", "position", "getPosition", "()I", "setPosition", "(I)V", "scroller", "Landroid/widget/Scroller;", "touchSlop", "touchState", "velocityTracker", "Landroid/view/VelocityTracker;", "viewSwitchListener", "Lorg/videolan/vlc/gui/view/ViewSwitchListener;", "getViewSwitchListener", "()Lorg/videolan/vlc/gui/view/ViewSwitchListener;", "computeScroll", "", "onInterceptTouchEvent", "", "ev", "Landroid/view/MotionEvent;", "onLayout", "changed", "l", "t", "r", "b", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onScrollChanged", "h", "v", "oldh", "oldv", "onSizeChanged", "w", "oldw", "onTouchEvent", "event", "scrollTo", "smoothScrollTo", "snapToDestination", "snapToScreen", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FlingViewGroup.kt */
public abstract class FlingViewGroup extends ViewGroup {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/FlingViewGroup";
    private static final int TOUCH_STATE_MOVE = 0;
    private static final int TOUCH_STATE_REST = 1;
    private float initialMotionEventX;
    private float initialMotionX;
    private float initialMotionY;
    private int interceptTouchState = 1;
    private long lastActionDownMillis;
    private float lastInterceptDownY;
    private float lastX;
    private final int maximumVelocity;
    private int position;
    private final Scroller scroller;
    private final int touchSlop;
    private int touchState = 1;
    private VelocityTracker velocityTracker;

    public abstract ViewSwitchListener getViewSwitchListener();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlingViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
        this.scroller = new Scroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        this.maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth() + i5;
                childAt.layout(i5, 0, measuredWidth, childAt.getMeasuredHeight());
                i5 = measuredWidth;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            int childCount = getChildCount();
            int i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(i, i2);
                i3 = RangesKt.coerceAtLeast(i3, childAt.getMeasuredHeight());
            }
            setMeasuredDimension(getMeasuredWidth(), i3);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!this.scroller.isFinished()) {
            this.scroller.abortAnimation();
        }
        super.onSizeChanged(i, i2, i3, i4);
        scrollTo(this.position * i, 0);
        requestLayout();
    }

    public void computeScroll() {
        if (this.scroller.computeScrollOffset()) {
            scrollTo(this.scroller.getCurrX(), this.scroller.getCurrY());
            postInvalidate();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        if (r3 != 3) goto L_0x0064;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            java.lang.String r0 = "ev"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            int r0 = r5.getChildCount()
            r1 = 0
            if (r0 != 0) goto L_0x000d
            return r1
        L_0x000d:
            float r0 = r6.getX()
            float r2 = r6.getY()
            int r3 = r6.getAction()
            r4 = 1
            if (r3 == 0) goto L_0x004e
            if (r3 == r4) goto L_0x004b
            r6 = 2
            if (r3 == r6) goto L_0x0025
            r6 = 3
            if (r3 == r6) goto L_0x004b
            goto L_0x0064
        L_0x0025:
            int r6 = r5.interceptTouchState
            if (r6 != 0) goto L_0x002a
            return r1
        L_0x002a:
            float r6 = r5.lastInterceptDownY
            float r6 = r6 - r2
            float r6 = java.lang.Math.abs(r6)
            int r2 = r5.touchSlop
            float r2 = (float) r2
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x003a
            r5.interceptTouchState = r1
        L_0x003a:
            float r6 = r5.lastX
            float r6 = r6 - r0
            float r6 = java.lang.Math.abs(r6)
            int r0 = r5.touchSlop
            float r0 = (float) r0
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 <= 0) goto L_0x0064
            r5.touchState = r1
            goto L_0x0064
        L_0x004b:
            r5.interceptTouchState = r4
            goto L_0x0064
        L_0x004e:
            r5.lastX = r0
            float r6 = r6.getY()
            r5.lastInterceptDownY = r6
            r5.initialMotionX = r0
            r5.initialMotionY = r2
            android.widget.Scroller r6 = r5.scroller
            boolean r6 = r6.isFinished()
            r5.touchState = r6
            r5.interceptTouchState = r4
        L_0x0064:
            int r6 = r5.touchState
            if (r6 != 0) goto L_0x0069
            r1 = 1
        L_0x0069:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.view.FlingViewGroup.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
        if (r0 != 3) goto L_0x0125;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            int r0 = r6.getChildCount()
            r1 = 0
            if (r0 != 0) goto L_0x000d
            return r1
        L_0x000d:
            android.view.VelocityTracker r0 = r6.velocityTracker
            if (r0 != 0) goto L_0x0017
            android.view.VelocityTracker r0 = android.view.VelocityTracker.obtain()
            r6.velocityTracker = r0
        L_0x0017:
            android.view.VelocityTracker r0 = r6.velocityTracker
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r0.addMovement(r7)
            int r0 = r7.getAction()
            float r2 = r7.getX()
            float r3 = r7.getY()
            r4 = 1
            if (r0 == 0) goto L_0x0109
            if (r0 == r4) goto L_0x0070
            r5 = 2
            if (r0 == r5) goto L_0x0038
            r1 = 3
            if (r0 == r1) goto L_0x0070
            goto L_0x0125
        L_0x0038:
            float r7 = r6.lastX
            float r7 = r7 - r2
            int r7 = (int) r7
            r6.lastX = r2
            int r0 = r6.getScrollX()
            if (r7 >= 0) goto L_0x0050
            if (r0 <= 0) goto L_0x0125
            int r0 = -r0
            int r7 = kotlin.ranges.RangesKt.coerceAtLeast((int) r7, (int) r0)
            r6.scrollBy(r7, r1)
            goto L_0x0125
        L_0x0050:
            if (r7 <= 0) goto L_0x0125
            int r2 = r6.getChildCount()
            int r2 = r2 - r4
            android.view.View r2 = r6.getChildAt(r2)
            int r2 = r2.getRight()
            int r2 = r2 - r0
            int r0 = r6.getWidth()
            int r2 = r2 - r0
            if (r2 <= 0) goto L_0x0125
            int r7 = kotlin.ranges.RangesKt.coerceAtMost((int) r7, (int) r2)
            r6.scrollBy(r7, r1)
            goto L_0x0125
        L_0x0070:
            android.view.VelocityTracker r0 = r6.velocityTracker
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int r1 = r6.maximumVelocity
            float r1 = (float) r1
            r5 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r5, r1)
            float r0 = r0.getXVelocity()
            int r0 = (int) r0
            float r1 = r6.initialMotionX
            float r2 = r2 - r1
            float r1 = r6.initialMotionY
            float r3 = r3 - r1
            r1 = 0
            int r1 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x00a0
            int r1 = r6.position
            if (r1 != 0) goto L_0x00a0
            int r1 = r6.touchSlop
            float r1 = (float) r1
            int r1 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x00a0
            org.videolan.vlc.gui.view.ViewSwitchListener r0 = r6.getViewSwitchListener()
            r0.onBackSwitched()
            goto L_0x00c2
        L_0x00a0:
            if (r0 <= r5) goto L_0x00ab
            int r1 = r6.position
            if (r1 <= 0) goto L_0x00ab
            int r1 = r1 - r4
            r6.snapToScreen(r1)
            goto L_0x00c2
        L_0x00ab:
            r1 = -1000(0xfffffffffffffc18, float:NaN)
            if (r0 >= r1) goto L_0x00bf
            int r0 = r6.position
            int r1 = r6.getChildCount()
            int r1 = r1 - r4
            if (r0 >= r1) goto L_0x00bf
            int r0 = r6.position
            int r0 = r0 + r4
            r6.snapToScreen(r0)
            goto L_0x00c2
        L_0x00bf:
            r6.snapToDestination()
        L_0x00c2:
            android.view.VelocityTracker r0 = r6.velocityTracker
            if (r0 == 0) goto L_0x00c9
            r0.recycle()
        L_0x00c9:
            r0 = 0
            r6.velocityTracker = r0
            org.videolan.vlc.gui.view.ViewSwitchListener r0 = r6.getViewSwitchListener()
            r0.onTouchUp()
            float r2 = r2 * r2
            float r3 = r3 * r3
            float r2 = r2 + r3
            int r0 = r6.touchSlop
            int r0 = r0 * r0
            float r0 = (float) r0
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0125
            long r0 = r6.lastActionDownMillis
            r2 = 0
            r6.lastActionDownMillis = r2
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 <= 0) goto L_0x0101
            long r2 = r7.getEventTime()
            long r2 = r2 - r0
            int r7 = android.view.ViewConfiguration.getLongPressTimeout()
            long r0 = (long) r7
            int r7 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r7 <= 0) goto L_0x0101
            org.videolan.vlc.gui.view.ViewSwitchListener r7 = r6.getViewSwitchListener()
            r7.onTouchLongClick()
            goto L_0x0125
        L_0x0101:
            org.videolan.vlc.gui.view.ViewSwitchListener r7 = r6.getViewSwitchListener()
            r7.onTouchClick()
            goto L_0x0125
        L_0x0109:
            android.widget.Scroller r0 = r6.scroller
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x0116
            android.widget.Scroller r0 = r6.scroller
            r0.abortAnimation()
        L_0x0116:
            r6.lastX = r2
            long r0 = r7.getEventTime()
            r6.lastActionDownMillis = r0
            org.videolan.vlc.gui.view.ViewSwitchListener r7 = r6.getViewSwitchListener()
            r7.onTouchDown()
        L_0x0125:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.view.FlingViewGroup.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (Math.abs(i3 - i) > Math.abs(i4 - i2)) {
            float width = ((float) i) / ((float) (getWidth() * (getChildCount() - 1)));
            if (i != this.position * getWidth()) {
                getViewSwitchListener().onSwitching(width);
            } else {
                getViewSwitchListener().onSwitched(this.position);
            }
        }
    }

    private final void snapToDestination() {
        int width = getWidth();
        snapToScreen((getScrollX() + (width / 2)) / width);
    }

    private final void snapToScreen(int i) {
        this.position = i;
        int width = (i * getWidth()) - getScrollX();
        this.scroller.startScroll(getScrollX(), 0, width, 0, Math.abs(width));
        invalidate();
    }

    public final void scrollTo(int i) {
        this.position = i;
        this.scroller.startScroll(getScrollX(), 0, (i * getWidth()) - getScrollX(), 0, 1);
        invalidate();
    }

    public final void smoothScrollTo(int i) {
        this.position = i;
        this.scroller.startScroll(getScrollX(), 0, (i * getWidth()) - getScrollX(), 0, MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION);
        invalidate();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/gui/view/FlingViewGroup$Companion;", "", "()V", "TAG", "", "TOUCH_STATE_MOVE", "", "TOUCH_STATE_REST", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FlingViewGroup.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
