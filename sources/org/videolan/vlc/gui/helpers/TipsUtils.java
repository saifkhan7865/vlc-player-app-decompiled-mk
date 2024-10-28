package org.videolan.vlc.gui.helpers;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J5\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2%\b\u0002\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\nJ\u001e\u0010\u0010\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/gui/helpers/TipsUtils;", "", "()V", "animationCount", "", "horizontalSwipe", "Landroid/animation/ObjectAnimator;", "view", "Landroid/view/View;", "updateListener", "Lkotlin/Function1;", "Landroid/animation/ValueAnimator;", "Lkotlin/ParameterName;", "name", "valueAnimator", "", "startTapAnimation", "views", "", "long", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TipsUtils.kt */
public final class TipsUtils {
    public static final TipsUtils INSTANCE = new TipsUtils();
    private static int animationCount = 1;

    private TipsUtils() {
    }

    public static /* synthetic */ ObjectAnimator horizontalSwipe$default(TipsUtils tipsUtils, View view, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        return tipsUtils.horizontalSwipe(view, function1);
    }

    public final ObjectAnimator horizontalSwipe(View view, Function1<? super ValueAnimator, Unit> function1) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTranslationY(0.0f);
        view.clearAnimation();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f});
        ofFloat.setDuration(1600);
        ofFloat.setFloatValues(new float[]{0.0f, 30.0f, -30.0f, 0.0f});
        ofFloat.setInterpolator(new LinearInterpolator());
        if (function1 != null) {
            ofFloat.addUpdateListener(new TipsUtils$$ExternalSyntheticLambda1(function1));
        }
        ofFloat.setStartDelay(1000);
        Intrinsics.checkNotNull(ofFloat);
        ofFloat.addListener(new TipsUtils$horizontalSwipe$$inlined$doOnEnd$1(ofFloat));
        ofFloat.start();
        return ofFloat;
    }

    /* access modifiers changed from: private */
    public static final void horizontalSwipe$lambda$1$lambda$0(Function1 function1, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(function1, "$listener");
        Intrinsics.checkNotNullParameter(valueAnimator, "it");
        function1.invoke(valueAnimator);
    }

    public static /* synthetic */ void startTapAnimation$default(TipsUtils tipsUtils, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        tipsUtils.startTapAnimation(list, z);
    }

    public final void startTapAnimation(List<? extends View> list, boolean z) {
        Intrinsics.checkNotNullParameter(list, "views");
        int i = animationCount + 1;
        animationCount = i;
        float f = i % 2 == 0 ? 0.9f : 1.0f;
        long j = i % 2 == 0 ? 1500 : z ? 800 : 0;
        for (View animate : list) {
            animate.animate().scaleX(f).scaleY(f).setDuration(200).setInterpolator(new AccelerateDecelerateInterpolator()).setStartDelay(j).withEndAction(new TipsUtils$$ExternalSyntheticLambda0(list, z));
        }
    }

    /* access modifiers changed from: private */
    public static final void startTapAnimation$lambda$4$lambda$3(List list, boolean z) {
        Intrinsics.checkNotNullParameter(list, "$views");
        INSTANCE.startTapAnimation(list, z);
    }
}
