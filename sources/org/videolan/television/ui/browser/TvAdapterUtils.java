package org.videolan.television.ui.browser;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.ViewPropertyAnimator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.television.ui.FocusableConstraintLayout;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\rH\u0007¨\u0006\u000e"}, d2 = {"Lorg/videolan/television/ui/browser/TvAdapterUtils;", "", "()V", "itemFocusChange", "", "hasFocus", "", "itemSize", "", "container", "Lorg/videolan/television/ui/FocusableConstraintLayout;", "isList", "listener", "Lkotlin/Function0;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvAdapterUtils.kt */
public final class TvAdapterUtils {
    public static final TvAdapterUtils INSTANCE = new TvAdapterUtils();

    private TvAdapterUtils() {
    }

    public final void itemFocusChange(boolean z, int i, FocusableConstraintLayout focusableConstraintLayout, boolean z2, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(focusableConstraintLayout, "container");
        Intrinsics.checkNotNullParameter(function0, "listener");
        if (z) {
            double d = z2 ? 1.05d : 1.1d;
            double d2 = (double) i;
            Double.isNaN(d2);
            int i2 = (int) (d2 * d);
            if (i2 % 2 == 1) {
                i2--;
            }
            float f = ((float) i2) / ((float) i);
            if (AndroidUtil.isLolliPopOrLater) {
                ViewPropertyAnimator unused = focusableConstraintLayout.animate().scaleX(f).scaleY(f).translationZ(f);
            } else {
                focusableConstraintLayout.animate().scaleX(f).scaleY(f);
            }
            function0.invoke();
        } else if (AndroidUtil.isLolliPopOrLater) {
            ViewPropertyAnimator unused2 = focusableConstraintLayout.animate().scaleX(1.0f).scaleY(1.0f).translationZ(1.0f);
        } else {
            focusableConstraintLayout.animate().scaleX(1.0f).scaleY(1.0f);
        }
        Drawable background = focusableConstraintLayout.getBackground();
        TransitionDrawable transitionDrawable = background instanceof TransitionDrawable ? (TransitionDrawable) background : null;
        if (transitionDrawable == null) {
            return;
        }
        if (z) {
            transitionDrawable.startTransition(250);
        } else {
            transitionDrawable.reverseTransition(250);
        }
    }
}
