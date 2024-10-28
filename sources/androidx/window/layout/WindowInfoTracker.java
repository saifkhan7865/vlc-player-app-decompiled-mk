package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import androidx.window.layout.adapter.WindowBackend;
import androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend;
import androidx.window.layout.adapter.sidecar.SidecarWindowBackend;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \t2\u00020\u0001:\u0001\tJ\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0017ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/window/layout/WindowInfoTracker;", "", "windowLayoutInfo", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/window/layout/WindowLayoutInfo;", "activity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WindowInfoTracker.kt */
public interface WindowInfoTracker {
    public static final Companion Companion = Companion.$$INSTANCE;

    Flow<WindowLayoutInfo> windowLayoutInfo(Activity activity);

    Flow<WindowLayoutInfo> windowLayoutInfo(Context context);

    /* renamed from: androidx.window.layout.WindowInfoTracker$-CC  reason: invalid class name */
    /* compiled from: WindowInfoTracker.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = WindowInfoTracker.Companion;
        }

        @JvmStatic
        public static WindowInfoTracker getOrCreate(Context context) {
            return WindowInfoTracker.Companion.getOrCreate(context);
        }

        @JvmStatic
        public static void overrideDecorator(WindowInfoTrackerDecorator windowInfoTrackerDecorator) {
            WindowInfoTracker.Companion.overrideDecorator(windowInfoTrackerDecorator);
        }

        @JvmStatic
        public static void reset() {
            WindowInfoTracker.Companion.reset();
        }

        public static Flow $default$windowLayoutInfo(WindowInfoTracker _this, Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Flow<WindowLayoutInfo> windowLayoutInfo = _this.windowLayoutInfo((Activity) context);
            if (windowLayoutInfo != null) {
                return windowLayoutInfo;
            }
            throw new NotImplementedError("Must override windowLayoutInfo(context) and provide an implementation.");
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bH\u0007J\b\u0010\u0017\u001a\u00020\u0015H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R#\u0010\t\u001a\u0004\u0018\u00010\n8@X\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Landroidx/window/layout/WindowInfoTracker$Companion;", "", "()V", "DEBUG", "", "TAG", "", "decorator", "Landroidx/window/layout/WindowInfoTrackerDecorator;", "extensionBackend", "Landroidx/window/layout/adapter/WindowBackend;", "getExtensionBackend$window_release$annotations", "getExtensionBackend$window_release", "()Landroidx/window/layout/adapter/WindowBackend;", "extensionBackend$delegate", "Lkotlin/Lazy;", "getOrCreate", "Landroidx/window/layout/WindowInfoTracker;", "context", "Landroid/content/Context;", "overrideDecorator", "", "overridingDecorator", "reset", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WindowInfoTracker.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        /* access modifiers changed from: private */
        public static final boolean DEBUG = false;
        /* access modifiers changed from: private */
        public static final String TAG = Reflection.getOrCreateKotlinClass(WindowInfoTracker.class).getSimpleName();
        private static WindowInfoTrackerDecorator decorator = EmptyDecorator.INSTANCE;
        private static final Lazy<ExtensionWindowLayoutInfoBackend> extensionBackend$delegate = LazyKt.lazy(WindowInfoTracker$Companion$extensionBackend$2.INSTANCE);

        public static /* synthetic */ void getExtensionBackend$window_release$annotations() {
        }

        private Companion() {
        }

        public final WindowBackend getExtensionBackend$window_release() {
            return extensionBackend$delegate.getValue();
        }

        @JvmStatic
        public final WindowInfoTracker getOrCreate(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            WindowBackend extensionBackend$window_release = getExtensionBackend$window_release();
            if (extensionBackend$window_release == null) {
                extensionBackend$window_release = SidecarWindowBackend.Companion.getInstance(context);
            }
            return decorator.decorate(new WindowInfoTrackerImpl(WindowMetricsCalculatorCompat.INSTANCE, extensionBackend$window_release));
        }

        @JvmStatic
        public final void overrideDecorator(WindowInfoTrackerDecorator windowInfoTrackerDecorator) {
            Intrinsics.checkNotNullParameter(windowInfoTrackerDecorator, "overridingDecorator");
            decorator = windowInfoTrackerDecorator;
        }

        @JvmStatic
        public final void reset() {
            decorator = EmptyDecorator.INSTANCE;
        }
    }
}
