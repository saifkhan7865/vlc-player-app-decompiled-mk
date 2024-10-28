package org.videolan.vlc.gui.view;

import android.animation.ValueAnimator;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/animation/ValueAnimator;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CollapsibleLinearLayout.kt */
final class CollapsibleLinearLayout$animator$2 extends Lambda implements Function0<ValueAnimator> {
    final /* synthetic */ CollapsibleLinearLayout this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CollapsibleLinearLayout$animator$2(CollapsibleLinearLayout collapsibleLinearLayout) {
        super(0);
        this.this$0 = collapsibleLinearLayout;
    }

    public final ValueAnimator invoke() {
        ValueAnimator valueAnimator = new ValueAnimator();
        CollapsibleLinearLayout collapsibleLinearLayout = this.this$0;
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new CollapsibleLinearLayout$animator$2$$ExternalSyntheticLambda0(collapsibleLinearLayout));
        return valueAnimator;
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$1$lambda$0(CollapsibleLinearLayout collapsibleLinearLayout, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(collapsibleLinearLayout, "this$0");
        Intrinsics.checkNotNullParameter(valueAnimator, "animator");
        ViewGroup.LayoutParams layoutParams = collapsibleLinearLayout.getLayoutParams();
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        layoutParams.height = ((Integer) animatedValue).intValue();
        collapsibleLinearLayout.requestLayout();
        Function1 access$getAnimationUpdateListener$p = collapsibleLinearLayout.animationUpdateListener;
        if (access$getAnimationUpdateListener$p != null) {
            access$getAnimationUpdateListener$p.invoke(Float.valueOf(valueAnimator.getAnimatedFraction()));
        }
    }
}
