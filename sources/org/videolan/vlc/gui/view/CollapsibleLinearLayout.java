package org.videolan.vlc.gui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rJ\u0006\u0010!\u001a\u00020\u0011J\b\u0010\"\u001a\u00020\u0011H\u0002J\u0006\u0010#\u001a\u00020\u0011J\u0014\u0010$\u001a\u00020\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110 J\u001a\u0010&\u001a\u00020\u00112\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fJ\u0006\u0010'\u001a\u00020\u0011R\u001c\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010 X\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lorg/videolan/vlc/gui/view/CollapsibleLinearLayout;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleAttr", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "animationUpdateListener", "Lkotlin/Function1;", "", "", "animator", "Landroid/animation/ValueAnimator;", "getAnimator", "()Landroid/animation/ValueAnimator;", "animator$delegate", "Lkotlin/Lazy;", "isCollapsed", "", "()Z", "setCollapsed", "(Z)V", "locked", "maxHeight", "onReadyListener", "Lkotlin/Function0;", "collapse", "initialize", "lock", "onReady", "listener", "setAnimationUpdateListener", "toggle", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CollapsibleLinearLayout.kt */
public final class CollapsibleLinearLayout extends LinearLayout {
    /* access modifiers changed from: private */
    public Function1<? super Float, Unit> animationUpdateListener;
    private final Lazy animator$delegate = LazyKt.lazy(new CollapsibleLinearLayout$animator$2(this));
    private boolean isCollapsed = true;
    private boolean locked;
    /* access modifiers changed from: private */
    public int maxHeight = -1;
    /* access modifiers changed from: private */
    public Function0<Unit> onReadyListener;

    public final boolean isCollapsed() {
        return this.isCollapsed;
    }

    public final void setCollapsed(boolean z) {
        this.isCollapsed = z;
    }

    private final ValueAnimator getAnimator() {
        return (ValueAnimator) this.animator$delegate.getValue();
    }

    public final void onReady(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.onReadyListener = function0;
    }

    public final void setAnimationUpdateListener(Function1<? super Float, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.animationUpdateListener = function1;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CollapsibleLinearLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CollapsibleLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CollapsibleLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CollapsibleLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    private final void initialize() {
        if (!(getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            getViewTreeObserver().addOnGlobalLayoutListener(new CollapsibleLinearLayout$initialize$1(this));
            return;
        }
        throw new IllegalStateException("The parent should not be a ConstraintLayout to prevent height issues (when set to 0)");
    }

    public final void toggle() {
        if (!this.locked) {
            boolean z = this.isCollapsed;
            int i = 0;
            int i2 = !z ? this.maxHeight : 0;
            if (z) {
                i = this.maxHeight;
            }
            this.isCollapsed = !z;
            getAnimator().setIntValues(new int[]{i2, i});
            getAnimator().start();
        }
    }

    public final void collapse() {
        if (!this.locked && !this.isCollapsed) {
            toggle();
        }
    }

    public final void lock() {
        if (this.isCollapsed) {
            toggle();
        }
        this.locked = true;
    }
}
