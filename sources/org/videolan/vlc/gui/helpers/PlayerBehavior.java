package org.videolan.vlc.gui.helpers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0010\u0018\u0000 ?*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001?B\u0007\b\u0016¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ%\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016J%\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u001cJ%\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020 H\u0016¢\u0006\u0002\u0010!J%\u0010\"\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010#\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010$J5\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0002\u0010+J=\u0010,\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u00022\u0006\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u000200H\u0016¢\u0006\u0002\u00101JE\u0010,\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u00022\u0006\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u0002002\u0006\u00102\u001a\u00020\u0011H\u0016¢\u0006\u0002\u00103J%\u00104\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u0002H\u0016¢\u0006\u0002\u00105J-\u00104\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u00022\u0006\u00102\u001a\u00020\u0011H\u0016¢\u0006\u0002\u00106J%\u00107\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020 H\u0016¢\u0006\u0002\u0010!J\u0006\u00108\u001a\u00020\u000eJ\u0014\u00109\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0010\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u0011H\u0016J)\u0010<\u001a\u00020\u000e2!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000e0\u0010J\u0018\u0010=\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u0002H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R+\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lorg/videolan/vlc/gui/helpers/PlayerBehavior;", "V", "Landroid/view/View;", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "()V", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "layoutDone", "Ljava/util/concurrent/atomic/AtomicBoolean;", "layoutListener", "Lkotlin/Function0;", "", "listener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "top", "lock", "", "layoutDependsOn", "parent", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "child", "dependency", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;)Z", "onDependentViewChanged", "onInterceptTouchEvent", "event", "Landroid/view/MotionEvent;", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/MotionEvent;)Z", "onLayoutChild", "layoutDirection", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;I)Z", "onNestedPreFling", "coordinatorLayout", "target", "velocityX", "", "velocityY", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;FF)Z", "onNestedPreScroll", "dx", "dy", "consumed", "", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;II[I)V", "type", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;II[II)V", "onStopNestedScroll", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;)V", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;I)V", "onTouchEvent", "removePeekHeightListener", "setLayoutListener", "setPeekHeight", "peekHeight", "setPeekHeightListener", "updateSnackbar", "view", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerBehavior.kt */
public final class PlayerBehavior<V extends View> extends BottomSheetBehavior<V> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/BottomSheetBehavior";
    private AtomicBoolean layoutDone = new AtomicBoolean(false);
    private Function0<Unit> layoutListener;
    private Function1<? super Integer, Unit> listener;
    private boolean lock;

    public PlayerBehavior() {
        setState(5);
        setHideable(true);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlayerBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        setState(5);
    }

    public void setPeekHeight(int i) {
        super.setPeekHeight(i);
        Function1<? super Integer, Unit> function1 = this.listener;
        if (function1 != null) {
            function1.invoke(Integer.valueOf(i));
        }
    }

    public final void setPeekHeightListener(Function1<? super Integer, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.listener = function1;
    }

    public final void removePeekHeightListener() {
        this.listener = null;
    }

    public final void setLayoutListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.layoutListener = function0;
    }

    public final void lock(boolean z) {
        this.lock = z;
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, V v, View view) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, "dependency");
        if (view instanceof Snackbar.SnackbarLayout) {
            updateSnackbar(v, view);
        }
        return super.layoutDependsOn(coordinatorLayout, v, view);
    }

    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, V v, View view) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, "dependency");
        if (view instanceof Snackbar.SnackbarLayout) {
            updateSnackbar(v, view);
        }
        return super.onDependentViewChanged(coordinatorLayout, v, view);
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        if (this.lock) {
            return false;
        }
        try {
            return super.onInterceptTouchEvent(coordinatorLayout, v, motionEvent);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    private final void updateSnackbar(View view, View view2) {
        if (view2.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
            CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
            if (layoutParams2.getAnchorId() != view.getId()) {
                layoutParams2.setAnchorId(view.getId());
                layoutParams2.anchorGravity = 48;
                layoutParams2.gravity = 48;
                layoutParams2.bottomMargin = KotlinExtensionsKt.getDp(8);
                view2.setLayoutParams(layoutParams2);
            }
        }
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_TARGET);
        if (!this.lock) {
            try {
                super.onStopNestedScroll(coordinatorLayout, v, view, i);
            } catch (NullPointerException unused) {
            }
        }
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_TARGET);
        if (!this.lock) {
            try {
                super.onStopNestedScroll(coordinatorLayout, v, view);
            } catch (NullPointerException unused) {
            }
        }
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_TARGET);
        Intrinsics.checkNotNullParameter(iArr, "consumed");
        if (!this.lock) {
            try {
                super.onNestedPreScroll(coordinatorLayout, v, view, i, i2, iArr, i3);
            } catch (NullPointerException unused) {
            }
        }
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_TARGET);
        Intrinsics.checkNotNullParameter(iArr, "consumed");
        if (!this.lock) {
            try {
                super.onNestedPreScroll(coordinatorLayout, v, view, i, i2, iArr);
            } catch (NullPointerException unused) {
            }
        }
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_TARGET);
        if (this.lock) {
            return false;
        }
        try {
            return super.onNestedPreFling(coordinatorLayout, v, view, f, f2);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        Function0<Unit> function0;
        Function0<Unit> function02;
        Function0<Unit> function03;
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(v, "child");
        try {
            boolean onLayoutChild = super.onLayoutChild(coordinatorLayout, v, i);
            if (this.layoutDone.getAndSet(true) || (function03 = this.layoutListener) == null) {
                return onLayoutChild;
            }
            function03.invoke();
            return onLayoutChild;
        } catch (IndexOutOfBoundsException unused) {
            if (!this.layoutDone.getAndSet(true) && (function0 = this.layoutListener) != null) {
                function0.invoke();
            }
            return false;
        } catch (Throwable th) {
            if (!this.layoutDone.getAndSet(true) && (function02 = this.layoutListener) != null) {
                function02.invoke();
            }
            throw th;
        }
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        if (this.lock) {
            return false;
        }
        try {
            return super.onTouchEvent(coordinatorLayout, v, motionEvent);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/helpers/PlayerBehavior$Companion;", "", "()V", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlayerBehavior.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
