package androidx.window.layout;

import android.util.Log;
import androidx.window.core.ConsumerAdapter;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/window/layout/adapter/extensions/ExtensionWindowLayoutInfoBackend;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: WindowInfoTracker.kt */
final class WindowInfoTracker$Companion$extensionBackend$2 extends Lambda implements Function0<ExtensionWindowLayoutInfoBackend> {
    public static final WindowInfoTracker$Companion$extensionBackend$2 INSTANCE = new WindowInfoTracker$Companion$extensionBackend$2();

    WindowInfoTracker$Companion$extensionBackend$2() {
        super(0);
    }

    public final ExtensionWindowLayoutInfoBackend invoke() {
        WindowLayoutComponent windowLayoutComponent;
        try {
            ClassLoader classLoader = WindowInfoTracker.class.getClassLoader();
            SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider = classLoader != null ? new SafeWindowLayoutComponentProvider(classLoader, new ConsumerAdapter(classLoader)) : null;
            if (safeWindowLayoutComponentProvider == null || (windowLayoutComponent = safeWindowLayoutComponentProvider.getWindowLayoutComponent()) == null) {
                return null;
            }
            Intrinsics.checkNotNullExpressionValue(classLoader, "loader");
            return new ExtensionWindowLayoutInfoBackend(windowLayoutComponent, new ConsumerAdapter(classLoader));
        } catch (Throwable unused) {
            if (!WindowInfoTracker.Companion.DEBUG) {
                return null;
            }
            Log.d(WindowInfoTracker.Companion.TAG, "Failed to load WindowExtensions");
            return null;
        }
    }
}
