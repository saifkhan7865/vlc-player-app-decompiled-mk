package androidx.window.area;

import android.app.Activity;
import android.util.Log;
import androidx.window.core.BuildConfig;
import androidx.window.core.VerificationMode;
import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.extensions.core.util.function.Consumer;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl;", "Landroidx/window/area/WindowAreaController;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "(Landroidx/window/extensions/area/WindowAreaComponent;)V", "currentStatus", "Landroidx/window/area/WindowAreaStatus;", "rearDisplayMode", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "windowAreaSessionCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "rearDisplayStatus", "Lkotlinx/coroutines/flow/Flow;", "Companion", "RearDisplaySessionConsumer", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WindowAreaControllerImpl.kt */
public final class WindowAreaControllerImpl implements WindowAreaController {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String TAG = Reflection.getOrCreateKotlinClass(WindowAreaControllerImpl.class).getSimpleName();
    /* access modifiers changed from: private */
    public WindowAreaStatus currentStatus;
    /* access modifiers changed from: private */
    public final WindowAreaComponent windowAreaComponent;

    public WindowAreaControllerImpl(WindowAreaComponent windowAreaComponent2) {
        Intrinsics.checkNotNullParameter(windowAreaComponent2, "windowAreaComponent");
        this.windowAreaComponent = windowAreaComponent2;
    }

    public Flow<WindowAreaStatus> rearDisplayStatus() {
        return FlowKt.distinctUntilChanged(FlowKt.callbackFlow(new WindowAreaControllerImpl$rearDisplayStatus$1(this, (Continuation<? super WindowAreaControllerImpl$rearDisplayStatus$1>) null)));
    }

    public void rearDisplayMode(Activity activity, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(windowAreaSessionCallback, "windowAreaSessionCallback");
        WindowAreaStatus windowAreaStatus = this.currentStatus;
        if (windowAreaStatus == null || Intrinsics.areEqual((Object) windowAreaStatus, (Object) WindowAreaStatus.AVAILABLE)) {
            this.windowAreaComponent.startRearDisplaySession(activity, new RearDisplaySessionConsumer(executor, windowAreaSessionCallback, this.windowAreaComponent));
            return;
        }
        throw new UnsupportedOperationException("Rear Display mode cannot be enabled currently");
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\rH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl$RearDisplaySessionConsumer;", "Landroidx/window/extensions/core/util/function/Consumer;", "", "executor", "Ljava/util/concurrent/Executor;", "appCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "extensionsComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "(Ljava/util/concurrent/Executor;Landroidx/window/area/WindowAreaSessionCallback;Landroidx/window/extensions/area/WindowAreaComponent;)V", "session", "Landroidx/window/area/WindowAreaSession;", "accept", "", "t", "onSessionFinished", "onSessionStarted", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WindowAreaControllerImpl.kt */
    public static final class RearDisplaySessionConsumer implements Consumer<Integer> {
        private final WindowAreaSessionCallback appCallback;
        private final Executor executor;
        private final WindowAreaComponent extensionsComponent;
        private WindowAreaSession session;

        public RearDisplaySessionConsumer(Executor executor2, WindowAreaSessionCallback windowAreaSessionCallback, WindowAreaComponent windowAreaComponent) {
            Intrinsics.checkNotNullParameter(executor2, "executor");
            Intrinsics.checkNotNullParameter(windowAreaSessionCallback, "appCallback");
            Intrinsics.checkNotNullParameter(windowAreaComponent, "extensionsComponent");
            this.executor = executor2;
            this.appCallback = windowAreaSessionCallback;
            this.extensionsComponent = windowAreaComponent;
        }

        public /* bridge */ /* synthetic */ void accept(Object obj) {
            accept(((Number) obj).intValue());
        }

        public void accept(int i) {
            if (i == 0) {
                onSessionFinished();
            } else if (i != 1) {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.STRICT) {
                    String access$getTAG$cp = WindowAreaControllerImpl.TAG;
                    Log.d(access$getTAG$cp, "Received an unknown session status value: " + i);
                }
                onSessionFinished();
            } else {
                onSessionStarted();
            }
        }

        private final void onSessionStarted() {
            WindowAreaSession rearDisplaySessionImpl = new RearDisplaySessionImpl(this.extensionsComponent);
            this.session = rearDisplaySessionImpl;
            this.executor.execute(new WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda0(this, rearDisplaySessionImpl));
        }

        /* access modifiers changed from: private */
        public static final void onSessionStarted$lambda$1$lambda$0(RearDisplaySessionConsumer rearDisplaySessionConsumer, WindowAreaSession windowAreaSession) {
            Intrinsics.checkNotNullParameter(rearDisplaySessionConsumer, "this$0");
            Intrinsics.checkNotNullParameter(windowAreaSession, "$it");
            rearDisplaySessionConsumer.appCallback.onSessionStarted(windowAreaSession);
        }

        private final void onSessionFinished() {
            this.session = null;
            this.executor.execute(new WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda1(this));
        }

        /* access modifiers changed from: private */
        public static final void onSessionFinished$lambda$2(RearDisplaySessionConsumer rearDisplaySessionConsumer) {
            Intrinsics.checkNotNullParameter(rearDisplaySessionConsumer, "this$0");
            rearDisplaySessionConsumer.appCallback.onSessionEnded();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl$Companion;", "", "()V", "TAG", "", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WindowAreaControllerImpl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
