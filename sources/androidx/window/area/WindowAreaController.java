package androidx.window.area;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import androidx.window.core.BuildConfig;
import androidx.window.core.VerificationMode;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.area.WindowAreaComponent;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000 \r2\u00020\u0001:\u0001\rJ \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Landroidx/window/area/WindowAreaController;", "", "rearDisplayMode", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "windowAreaSessionCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "rearDisplayStatus", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/window/area/WindowAreaStatus;", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WindowAreaController.kt */
public interface WindowAreaController {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* renamed from: androidx.window.area.WindowAreaController$-CC  reason: invalid class name */
    /* compiled from: WindowAreaController.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = WindowAreaController.Companion;
        }

        @JvmStatic
        public static WindowAreaController getOrCreate() {
            return WindowAreaController.Companion.getOrCreate();
        }

        @JvmStatic
        public static void overrideDecorator(WindowAreaControllerDecorator windowAreaControllerDecorator) {
            WindowAreaController.Companion.overrideDecorator(windowAreaControllerDecorator);
        }

        @JvmStatic
        public static void reset() {
            WindowAreaController.Companion.reset();
        }
    }

    void rearDisplayMode(Activity activity, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback);

    Flow<WindowAreaStatus> rearDisplayStatus();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J\b\u0010\f\u001a\u00020\nH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/area/WindowAreaController$Companion;", "", "()V", "TAG", "", "decorator", "Landroidx/window/area/WindowAreaControllerDecorator;", "getOrCreate", "Landroidx/window/area/WindowAreaController;", "overrideDecorator", "", "overridingDecorator", "reset", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WindowAreaController.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String TAG = Reflection.getOrCreateKotlinClass(WindowAreaController.class).getSimpleName();
        private static WindowAreaControllerDecorator decorator = EmptyDecorator.INSTANCE;

        private Companion() {
        }

        @JvmStatic
        public final WindowAreaController getOrCreate() {
            WindowAreaComponent windowAreaComponent;
            WindowAreaController windowAreaController;
            try {
                windowAreaComponent = WindowExtensionsProvider.getWindowExtensions().getWindowAreaComponent();
            } catch (Throwable unused) {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.STRICT) {
                    Log.d(TAG, "Failed to load WindowExtensions");
                }
                windowAreaComponent = null;
            }
            if (Build.VERSION.SDK_INT < 24 || windowAreaComponent == null) {
                windowAreaController = new EmptyWindowAreaControllerImpl();
            } else {
                windowAreaController = new WindowAreaControllerImpl(windowAreaComponent);
            }
            return decorator.decorate(windowAreaController);
        }

        @JvmStatic
        public final void overrideDecorator(WindowAreaControllerDecorator windowAreaControllerDecorator) {
            Intrinsics.checkNotNullParameter(windowAreaControllerDecorator, "overridingDecorator");
            decorator = windowAreaControllerDecorator;
        }

        @JvmStatic
        public final void reset() {
            decorator = EmptyDecorator.INSTANCE;
        }
    }
}
