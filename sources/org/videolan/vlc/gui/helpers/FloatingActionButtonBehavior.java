package org.videolan.vlc.gui.helpers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J \u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016JH\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\"H\u0016J8\u0010'\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\"2\u0006\u0010&\u001a\u00020\"H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f8BX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006*"}, d2 = {"Lorg/videolan/vlc/gui/helpers/FloatingActionButtonBehavior;", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton$Behavior;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "onVisibilityChangedListener", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton$OnVisibilityChangedListener;", "player", "Landroid/widget/FrameLayout;", "playerBehavior", "Lorg/videolan/vlc/gui/helpers/PlayerBehavior;", "getPlayerBehavior", "()Lorg/videolan/vlc/gui/helpers/PlayerBehavior;", "shouldNeverShow", "", "getShouldNeverShow", "()Z", "setShouldNeverShow", "(Z)V", "layoutDependsOn", "parent", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "child", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "dependency", "Landroid/view/View;", "onDependentViewChanged", "onNestedScroll", "", "coordinatorLayout", "target", "dxConsumed", "", "dyConsumed", "dxUnconsumed", "dyUnconsumed", "type", "onStartNestedScroll", "directTargetChild", "axes", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FloatingActionButtonBehavior.kt */
public final class FloatingActionButtonBehavior extends FloatingActionButton.Behavior {
    private final FloatingActionButton.OnVisibilityChangedListener onVisibilityChangedListener = new FloatingActionButton.OnVisibilityChangedListener() {
        public void onHidden(FloatingActionButton floatingActionButton) {
            KotlinExtensionsKt.setInvisible(floatingActionButton);
        }
    };
    private FrameLayout player;
    private boolean shouldNeverShow;

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view, View view2, int i, int i2) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(floatingActionButton, "child");
        Intrinsics.checkNotNullParameter(view, "directTargetChild");
        Intrinsics.checkNotNullParameter(view2, TypedValues.AttributesType.S_TARGET);
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
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

    public final boolean getShouldNeverShow() {
        return this.shouldNeverShow;
    }

    public final void setShouldNeverShow(boolean z) {
        this.shouldNeverShow = z;
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(floatingActionButton, "child");
        Intrinsics.checkNotNullParameter(view, "dependency");
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            if (frameLayout.getId() == R.id.audio_player_container) {
                this.player = frameLayout;
            }
        }
        return view.getId() == R.id.audio_player_container || (view instanceof BottomNavigationView) || (view instanceof Snackbar.SnackbarLayout) || (view instanceof RecyclerView) || (view instanceof NestedScrollView);
    }

    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
        PlayerBehavior<?> playerBehavior;
        Intrinsics.checkNotNullParameter(coordinatorLayout, "parent");
        Intrinsics.checkNotNullParameter(floatingActionButton, "child");
        Intrinsics.checkNotNullParameter(view, "dependency");
        if (view.getId() != R.id.audio_player_container || ((playerBehavior = getPlayerBehavior()) != null && playerBehavior.getState() == 5)) {
            return super.onDependentViewChanged(coordinatorLayout, floatingActionButton, view);
        }
        ViewGroup.LayoutParams layoutParams = floatingActionButton.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
        if (layoutParams2.getAnchorId() == view.getId()) {
            return true;
        }
        layoutParams2.setAnchorId(view.getId());
        layoutParams2.anchorGravity = 8388661;
        layoutParams2.gravity = 8388661;
        floatingActionButton.setLayoutParams(layoutParams2);
        return true;
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view, int i, int i2, int i3, int i4, int i5) {
        View view2 = view;
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(floatingActionButton, "child");
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_TARGET);
        super.onNestedScroll(coordinatorLayout, floatingActionButton, view, i, i2, i3, i4, i5);
        if (this.shouldNeverShow) {
            floatingActionButton.hide(this.onVisibilityChangedListener);
            return;
        }
        int i6 = view2 instanceof NestedScrollView ? i4 : i2;
        if (i6 > 0 && floatingActionButton.getVisibility() == 0) {
            floatingActionButton.hide(this.onVisibilityChangedListener);
        } else if (i6 < 0 && floatingActionButton.getVisibility() == 4) {
            floatingActionButton.show();
        }
    }
}
