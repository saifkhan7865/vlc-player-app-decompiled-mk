package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.WindowMetrics;
import androidx.core.view.WindowInsetsCompat;
import androidx.transition.WindowIdApi18$$ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \t2\u00020\u0001:\u0001\tJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/window/layout/WindowMetricsCalculator;", "", "computeCurrentWindowMetrics", "Landroidx/window/layout/WindowMetrics;", "activity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "computeMaximumWindowMetrics", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WindowMetricsCalculator.kt */
public interface WindowMetricsCalculator {
    public static final Companion Companion = Companion.$$INSTANCE;

    WindowMetrics computeCurrentWindowMetrics(Activity activity);

    WindowMetrics computeCurrentWindowMetrics(Context context);

    WindowMetrics computeMaximumWindowMetrics(Activity activity);

    WindowMetrics computeMaximumWindowMetrics(Context context);

    /* renamed from: androidx.window.layout.WindowMetricsCalculator$-CC  reason: invalid class name */
    /* compiled from: WindowMetricsCalculator.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = WindowMetricsCalculator.Companion;
        }

        @JvmStatic
        public static WindowMetricsCalculator getOrCreate() {
            return WindowMetricsCalculator.Companion.getOrCreate();
        }

        @JvmStatic
        public static void overrideDecorator(WindowMetricsCalculatorDecorator windowMetricsCalculatorDecorator) {
            WindowMetricsCalculator.Companion.overrideDecorator(windowMetricsCalculatorDecorator);
        }

        @JvmStatic
        public static void reset() {
            WindowMetricsCalculator.Companion.reset();
        }

        public static WindowMetrics $default$computeCurrentWindowMetrics(WindowMetricsCalculator _this, Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            throw new NotImplementedError("Must override computeCurrentWindowMetrics(context) and provide an implementation.");
        }

        public static WindowMetrics $default$computeMaximumWindowMetrics(WindowMetricsCalculator _this, Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            throw new NotImplementedError("Must override computeMaximumWindowMetrics(context) and provide an implementation.");
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\bH\u0007J\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0010R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/layout/WindowMetricsCalculator$Companion;", "", "()V", "decorator", "Lkotlin/Function1;", "Landroidx/window/layout/WindowMetricsCalculator;", "getOrCreate", "overrideDecorator", "", "overridingDecorator", "Landroidx/window/layout/WindowMetricsCalculatorDecorator;", "reset", "translateWindowMetrics", "Landroidx/window/layout/WindowMetrics;", "windowMetrics", "Landroid/view/WindowMetrics;", "translateWindowMetrics$window_release", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WindowMetricsCalculator.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static Function1<? super WindowMetricsCalculator, ? extends WindowMetricsCalculator> decorator = WindowMetricsCalculator$Companion$decorator$1.INSTANCE;

        private Companion() {
        }

        @JvmStatic
        public final WindowMetricsCalculator getOrCreate() {
            return (WindowMetricsCalculator) decorator.invoke(WindowMetricsCalculatorCompat.INSTANCE);
        }

        @JvmStatic
        public final void overrideDecorator(WindowMetricsCalculatorDecorator windowMetricsCalculatorDecorator) {
            Intrinsics.checkNotNullParameter(windowMetricsCalculatorDecorator, "overridingDecorator");
            decorator = new WindowMetricsCalculator$Companion$overrideDecorator$1(windowMetricsCalculatorDecorator);
        }

        @JvmStatic
        public final void reset() {
            decorator = WindowMetricsCalculator$Companion$reset$1.INSTANCE;
        }

        public final WindowMetrics translateWindowMetrics$window_release(WindowMetrics windowMetrics) {
            Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
            Rect m = WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(windowMetrics);
            Intrinsics.checkNotNullExpressionValue(m, "windowMetrics.bounds");
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(windowMetrics));
            Intrinsics.checkNotNullExpressionValue(windowInsetsCompat, "toWindowInsetsCompat(windowMetrics.windowInsets)");
            return new WindowMetrics(m, windowInsetsCompat);
        }
    }
}
