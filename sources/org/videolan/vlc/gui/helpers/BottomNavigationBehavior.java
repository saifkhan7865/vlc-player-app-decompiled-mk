package org.videolan.vlc.gui.helpers;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.snackbar.Snackbar;
import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 E*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001EB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u001dJ%\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u001dJ%\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010 \u001a\u00020\nH\u0016¢\u0006\u0002\u0010!J=\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u0017H\u0016¢\u0006\u0002\u0010(JU\u0010)\u001a\u00020*2\u0006\u0010#\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010$\u001a\u00020\u00022\u0006\u0010+\u001a\u00020\n2\u0006\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u00020\n2\u0006\u0010'\u001a\u000200H\u0016¢\u0006\u0002\u00101J%\u00102\u001a\u00020*2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u00103\u001a\u000204H\u0016¢\u0006\u0002\u00105J\u001f\u00106\u001a\u0004\u0018\u0001042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00107J=\u00108\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u00109\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u00022\u0006\u0010:\u001a\u00020\n2\u0006\u0010/\u001a\u00020\nH\u0016¢\u0006\u0002\u0010;J\u0006\u0010<\u001a\u00020*J\u001b\u0010=\u001a\u00020*2\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010>\u001a\u00020\f¢\u0006\u0002\u0010?J\u0015\u0010@\u001a\u00020*2\u0006\u0010\u001b\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010AJ\u0018\u0010B\u001a\u00020*2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010C\u001a\u00020DH\u0002R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00138BX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lorg/videolan/vlc/gui/helpers/BottomNavigationBehavior;", "V", "Landroid/view/View;", "Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "currentState", "", "forceTranslation", "", "height", "offsetAnimator", "Landroid/animation/ValueAnimator;", "player", "Landroid/widget/FrameLayout;", "playerBehavior", "Lorg/videolan/vlc/gui/helpers/PlayerBehavior;", "getPlayerBehavior", "()Lorg/videolan/vlc/gui/helpers/PlayerBehavior;", "stateIsScrolling", "", "layoutDependsOn", "parent", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "child", "dependency", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;)Z", "onDependentViewChanged", "onLayoutChild", "layoutDirection", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;I)Z", "onNestedFling", "coordinatorLayout", "target", "velocityX", "velocityY", "consumed", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;FFZ)Z", "onNestedScroll", "", "dxConsumed", "dyConsumed", "dxUnconsumed", "dyUnconsumed", "type", "", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;IIIII[I)V", "onRestoreInstanceState", "state", "Landroid/os/Parcelable;", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/os/Parcelable;)V", "onSaveInstanceState", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;)Landroid/os/Parcelable;", "onStartNestedScroll", "directTargetChild", "axes", "(Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;Landroid/view/View;II)Z", "setCollapsed", "translate", "fl", "(Landroid/view/View;F)V", "updatePlayer", "(Landroid/view/View;)V", "updateSnackbar", "snackbarLayout", "Lcom/google/android/material/snackbar/Snackbar$SnackbarLayout;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BottomNavigationBehavior.kt */
public final class BottomNavigationBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int currentState = 2;
    private float forceTranslation = -1.0f;
    private int height;
    private ValueAnimator offsetAnimator;
    private FrameLayout player;
    private boolean stateIsScrolling;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BottomNavigationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
    }

    private final PlayerBehavior<?> getPlayerBehavior() {
        FrameLayout frameLayout = this.player;
        ViewGroup.LayoutParams layoutParams = frameLayout != null ? frameLayout.getLayoutParams() : null;
        CoordinatorLayout.LayoutParams layoutParams2 = layoutParams instanceof CoordinatorLayout.LayoutParams ? (CoordinatorLayout.LayoutParams) layoutParams : null;
        CoordinatorLayout.Behavior behavior = layoutParams2 != null ? layoutParams2.getBehavior() : null;
        if (behavior instanceof PlayerBehavior) {
            return (PlayerBehavior) behavior;
        }
        return null;
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(v, "child");
        Parcelable onSaveInstanceState = super.onSaveInstanceState(coordinatorLayout, v);
        return onSaveInstanceState != null ? new BottomNavigationBehaviorState(onSaveInstanceState, v.getTranslationY()) : onSaveInstanceState;
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(parcelable, OAuth2RequestParameters.State);
        BottomNavigationBehaviorState bottomNavigationBehaviorState = (BottomNavigationBehaviorState) parcelable;
        Parcelable superState = bottomNavigationBehaviorState.getSuperState();
        Intrinsics.checkNotNull(superState);
        super.onRestoreInstanceState(coordinatorLayout, v, superState);
        this.forceTranslation = bottomNavigationBehaviorState.getTranslation();
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(v, "child");
        if (this.forceTranslation != -1.0f) {
            float translationY = v.getTranslationY();
            float f = this.forceTranslation;
            if (translationY != f) {
                v.setTranslationY(f);
                this.forceTranslation = -1.0f;
            }
        }
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        this.height = v.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        return super.onLayoutChild(coordinatorLayout, v, i);
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, V v, View view) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, "dependency");
        if (view instanceof Snackbar.SnackbarLayout) {
            updateSnackbar(v, (Snackbar.SnackbarLayout) view);
        }
        if (view.getId() == R.id.audio_player_container) {
            return true;
        }
        return super.layoutDependsOn(coordinatorLayout, v, view);
    }

    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, V v, View view) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, "dependency");
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            if (frameLayout.getId() == R.id.audio_player_container) {
                this.player = frameLayout;
                updatePlayer(v);
            }
        }
        return super.onDependentViewChanged(coordinatorLayout, v, view);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, "directTargetChild");
        Intrinsics.checkNotNullParameter(view2, TypedValues.AttributesType.S_TARGET);
        PlayerBehavior<?> playerBehavior = getPlayerBehavior();
        if (playerBehavior != null && playerBehavior.getState() == 3) {
            return false;
        }
        updatePlayer(v);
        return true;
    }

    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2, boolean z) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_TARGET);
        updatePlayer(v);
        return super.onNestedFling(coordinatorLayout, v, view, f, f2, z);
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(v, "child");
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_TARGET);
        Intrinsics.checkNotNullParameter(iArr, "consumed");
        updatePlayer(v);
        super.onNestedScroll(coordinatorLayout, v, view, i, i2, i3, i4, i5, iArr);
    }

    private final void updateSnackbar(View view, Snackbar.SnackbarLayout snackbarLayout) {
        PlayerBehavior<?> playerBehavior;
        FrameLayout frameLayout = this.player;
        if (((frameLayout != null && frameLayout.getVisibility() == 8) || (playerBehavior = getPlayerBehavior()) == null || playerBehavior.getState() == 5) && (snackbarLayout.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            ViewGroup.LayoutParams layoutParams = snackbarLayout.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
            CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
            if (layoutParams2.getAnchorId() != view.getId()) {
                layoutParams2.setAnchorId(view.getId());
                layoutParams2.anchorGravity = 48;
                layoutParams2.gravity = 48;
                snackbarLayout.setLayoutParams(layoutParams2);
            }
        }
    }

    private final void updatePlayer(V v) {
        FrameLayout frameLayout = this.player;
        if (frameLayout != null && (frameLayout.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            Intrinsics.checkNotNull(behavior, "null cannot be cast to non-null type org.videolan.vlc.gui.helpers.PlayerBehavior<*>");
            ((PlayerBehavior) behavior).setPeekHeight((v.getContext().getResources().getDimensionPixelSize(R.dimen.player_peek_height) + v.getHeight()) - ((this.stateIsScrolling || this.currentState == 1) ? (int) v.getTranslationY() : 0));
        }
    }

    public final void translate(V v, float f) {
        Intrinsics.checkNotNullParameter(v, "child");
        if (this.currentState != 1) {
            v.setTranslationY(f);
            updatePlayer(v);
        }
    }

    public final void setCollapsed() {
        this.currentState = 1;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0004\"\b\b\u0001\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u0002H\u0005¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/helpers/BottomNavigationBehavior$Companion;", "", "()V", "from", "Lorg/videolan/vlc/gui/helpers/BottomNavigationBehavior;", "V", "Landroid/view/View;", "view", "(Landroid/view/View;)Lorg/videolan/vlc/gui/helpers/BottomNavigationBehavior;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BottomNavigationBehavior.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <V extends View> BottomNavigationBehavior<V> from(V v) {
            Intrinsics.checkNotNullParameter(v, "view");
            ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
                if (behavior instanceof BottomNavigationBehavior) {
                    return (BottomNavigationBehavior) behavior;
                }
                throw new IllegalArgumentException("The view is not associated with BottomNavigationBehavior".toString());
            }
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout".toString());
        }
    }
}
