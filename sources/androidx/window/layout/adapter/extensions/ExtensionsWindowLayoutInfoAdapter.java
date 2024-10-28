package androidx.window.layout.adapter.extensions;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import androidx.window.core.Bounds;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.HardwareFoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculatorCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ\u001f\u0010\u0003\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\tJ\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0013"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionsWindowLayoutInfoAdapter;", "", "()V", "translate", "Landroidx/window/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "info", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "translate$window_release", "Landroidx/window/layout/FoldingFeature;", "windowMetrics", "Landroidx/window/layout/WindowMetrics;", "oemFeature", "Landroidx/window/extensions/layout/FoldingFeature;", "validBounds", "", "bounds", "Landroidx/window/core/Bounds;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExtensionsWindowLayoutInfoAdapter.kt */
public final class ExtensionsWindowLayoutInfoAdapter {
    public static final ExtensionsWindowLayoutInfoAdapter INSTANCE = new ExtensionsWindowLayoutInfoAdapter();

    private ExtensionsWindowLayoutInfoAdapter() {
    }

    public final FoldingFeature translate$window_release(WindowMetrics windowMetrics, androidx.window.extensions.layout.FoldingFeature foldingFeature) {
        HardwareFoldingFeature.Type type;
        FoldingFeature.State state;
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        Intrinsics.checkNotNullParameter(foldingFeature, "oemFeature");
        int type2 = foldingFeature.getType();
        if (type2 == 1) {
            type = HardwareFoldingFeature.Type.Companion.getFOLD();
        } else if (type2 != 2) {
            return null;
        } else {
            type = HardwareFoldingFeature.Type.Companion.getHINGE();
        }
        int state2 = foldingFeature.getState();
        if (state2 == 1) {
            state = FoldingFeature.State.FLAT;
        } else if (state2 != 2) {
            return null;
        } else {
            state = FoldingFeature.State.HALF_OPENED;
        }
        Rect bounds = foldingFeature.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "oemFeature.bounds");
        if (!validBounds(windowMetrics, new Bounds(bounds))) {
            return null;
        }
        Rect bounds2 = foldingFeature.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds2, "oemFeature.bounds");
        return new HardwareFoldingFeature(new Bounds(bounds2), type, state);
    }

    public final WindowLayoutInfo translate$window_release(Context context, androidx.window.extensions.layout.WindowLayoutInfo windowLayoutInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(windowLayoutInfo, "info");
        if (Build.VERSION.SDK_INT >= 30) {
            return translate$window_release(WindowMetricsCalculatorCompat.INSTANCE.computeCurrentWindowMetrics(context), windowLayoutInfo);
        }
        if (Build.VERSION.SDK_INT >= 29 && (context instanceof Activity)) {
            return translate$window_release(WindowMetricsCalculatorCompat.INSTANCE.computeCurrentWindowMetrics((Activity) context), windowLayoutInfo);
        }
        throw new UnsupportedOperationException("Display Features are only supported after Q. Display features for non-Activity contexts are not expected to be reported on devices running Q.");
    }

    public final WindowLayoutInfo translate$window_release(WindowMetrics windowMetrics, androidx.window.extensions.layout.WindowLayoutInfo windowLayoutInfo) {
        FoldingFeature foldingFeature;
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        Intrinsics.checkNotNullParameter(windowLayoutInfo, "info");
        List<androidx.window.extensions.layout.FoldingFeature> displayFeatures = windowLayoutInfo.getDisplayFeatures();
        Intrinsics.checkNotNullExpressionValue(displayFeatures, "info.displayFeatures");
        Collection arrayList = new ArrayList();
        for (androidx.window.extensions.layout.FoldingFeature foldingFeature2 : displayFeatures) {
            if (foldingFeature2 instanceof androidx.window.extensions.layout.FoldingFeature) {
                ExtensionsWindowLayoutInfoAdapter extensionsWindowLayoutInfoAdapter = INSTANCE;
                Intrinsics.checkNotNullExpressionValue(foldingFeature2, "feature");
                foldingFeature = extensionsWindowLayoutInfoAdapter.translate$window_release(windowMetrics, foldingFeature2);
            } else {
                foldingFeature = null;
            }
            if (foldingFeature != null) {
                arrayList.add(foldingFeature);
            }
        }
        return new WindowLayoutInfo((List) arrayList);
    }

    private final boolean validBounds(WindowMetrics windowMetrics, Bounds bounds) {
        Rect bounds2 = windowMetrics.getBounds();
        if (bounds.isZero()) {
            return false;
        }
        if (bounds.getWidth() != bounds2.width() && bounds.getHeight() != bounds2.height()) {
            return false;
        }
        if (bounds.getWidth() < bounds2.width() && bounds.getHeight() < bounds2.height()) {
            return false;
        }
        if (bounds.getWidth() == bounds2.width() && bounds.getHeight() == bounds2.height()) {
            return false;
        }
        return true;
    }
}
