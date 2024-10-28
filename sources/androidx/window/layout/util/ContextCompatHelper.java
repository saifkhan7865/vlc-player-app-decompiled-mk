package androidx.window.layout.util;

import android.content.Context;
import android.graphics.Rect;
import android.view.WindowInsets;
import android.view.WindowManager;
import androidx.core.view.WindowInsetsCompat;
import androidx.transition.WindowIdApi18$$ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Landroidx/window/layout/util/ContextCompatHelper;", "", "()V", "currentWindowBounds", "Landroid/graphics/Rect;", "context", "Landroid/content/Context;", "currentWindowInsets", "Landroidx/core/view/WindowInsetsCompat;", "maximumWindowBounds", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ContextCompatHelper.kt */
public final class ContextCompatHelper {
    public static final ContextCompatHelper INSTANCE = new ContextCompatHelper();

    private ContextCompatHelper() {
    }

    public final Rect currentWindowBounds(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Rect m = WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(((WindowManager) context.getSystemService(WindowManager.class)).getCurrentWindowMetrics());
        Intrinsics.checkNotNullExpressionValue(m, "wm.currentWindowMetrics.bounds");
        return m;
    }

    public final Rect maximumWindowBounds(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Rect m = WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(((WindowManager) context.getSystemService(WindowManager.class)).getMaximumWindowMetrics());
        Intrinsics.checkNotNullExpressionValue(m, "wm.maximumWindowMetrics.bounds");
        return m;
    }

    public final WindowInsetsCompat currentWindowInsets(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        WindowInsets m = WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(((WindowManager) context.getSystemService(WindowManager.class)).getCurrentWindowMetrics());
        Intrinsics.checkNotNullExpressionValue(m, "wm.currentWindowMetrics.windowInsets");
        WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(m);
        Intrinsics.checkNotNullExpressionValue(windowInsetsCompat, "toWindowInsetsCompat(platformInsets)");
        return windowInsetsCompat;
    }
}
