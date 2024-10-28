package androidx.window.layout.adapter.sidecar;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.window.core.Version;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.WindowBackend;
import androidx.window.layout.adapter.sidecar.ExtensionInterfaceCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u001e2\u00020\u0001:\u0003\u001e\u001f B\u0011\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J&\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016J\u0016\u0010\u001d\u001a\u00020\u00102\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarWindowBackend;", "Landroidx/window/layout/adapter/WindowBackend;", "windowExtension", "Landroidx/window/layout/adapter/sidecar/ExtensionInterfaceCompat;", "(Landroidx/window/layout/adapter/sidecar/ExtensionInterfaceCompat;)V", "getWindowExtension", "()Landroidx/window/layout/adapter/sidecar/ExtensionInterfaceCompat;", "setWindowExtension", "windowLayoutChangeCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/window/layout/adapter/sidecar/SidecarWindowBackend$WindowLayoutChangeCallbackWrapper;", "getWindowLayoutChangeCallbacks$annotations", "()V", "getWindowLayoutChangeCallbacks", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "callbackRemovedForActivity", "", "activity", "Landroid/app/Activity;", "isActivityRegistered", "", "registerLayoutChangeCallback", "context", "Landroid/content/Context;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "unregisterLayoutChangeCallback", "Companion", "ExtensionListenerImpl", "WindowLayoutChangeCallbackWrapper", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SidecarWindowBackend.kt */
public final class SidecarWindowBackend implements WindowBackend {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final boolean DEBUG = false;
    private static final String TAG = "WindowServer";
    /* access modifiers changed from: private */
    public static volatile SidecarWindowBackend globalInstance;
    /* access modifiers changed from: private */
    public static final ReentrantLock globalLock = new ReentrantLock();
    private ExtensionInterfaceCompat windowExtension;
    private final CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> windowLayoutChangeCallbacks = new CopyOnWriteArrayList<>();

    public static /* synthetic */ void getWindowLayoutChangeCallbacks$annotations() {
    }

    public SidecarWindowBackend(ExtensionInterfaceCompat extensionInterfaceCompat) {
        this.windowExtension = extensionInterfaceCompat;
        ExtensionInterfaceCompat extensionInterfaceCompat2 = this.windowExtension;
        if (extensionInterfaceCompat2 != null) {
            extensionInterfaceCompat2.setExtensionCallback(new ExtensionListenerImpl());
        }
    }

    public final ExtensionInterfaceCompat getWindowExtension() {
        return this.windowExtension;
    }

    public final void setWindowExtension(ExtensionInterfaceCompat extensionInterfaceCompat) {
        this.windowExtension = extensionInterfaceCompat;
    }

    public final CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> getWindowLayoutChangeCallbacks() {
        return this.windowLayoutChangeCallbacks;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [kotlin.Unit] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void registerLayoutChangeCallback(android.content.Context r6, java.util.concurrent.Executor r7, androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> r8) {
        /*
            r5 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "executor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "callback"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            boolean r0 = r6 instanceof android.app.Activity
            r1 = 0
            if (r0 == 0) goto L_0x0017
            android.app.Activity r6 = (android.app.Activity) r6
            goto L_0x0018
        L_0x0017:
            r6 = r1
        L_0x0018:
            if (r6 == 0) goto L_0x0084
            java.util.concurrent.locks.ReentrantLock r0 = globalLock
            java.util.concurrent.locks.Lock r0 = (java.util.concurrent.locks.Lock) r0
            r0.lock()
            androidx.window.layout.adapter.sidecar.ExtensionInterfaceCompat r2 = r5.windowExtension     // Catch:{ all -> 0x007f }
            if (r2 != 0) goto L_0x0035
            androidx.window.layout.WindowLayoutInfo r6 = new androidx.window.layout.WindowLayoutInfo     // Catch:{ all -> 0x007f }
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ all -> 0x007f }
            r6.<init>(r7)     // Catch:{ all -> 0x007f }
            r8.accept(r6)     // Catch:{ all -> 0x007f }
            r0.unlock()
            return
        L_0x0035:
            boolean r3 = r5.isActivityRegistered(r6)     // Catch:{ all -> 0x007f }
            androidx.window.layout.adapter.sidecar.SidecarWindowBackend$WindowLayoutChangeCallbackWrapper r4 = new androidx.window.layout.adapter.sidecar.SidecarWindowBackend$WindowLayoutChangeCallbackWrapper     // Catch:{ all -> 0x007f }
            r4.<init>(r6, r7, r8)     // Catch:{ all -> 0x007f }
            java.util.concurrent.CopyOnWriteArrayList<androidx.window.layout.adapter.sidecar.SidecarWindowBackend$WindowLayoutChangeCallbackWrapper> r7 = r5.windowLayoutChangeCallbacks     // Catch:{ all -> 0x007f }
            r7.add(r4)     // Catch:{ all -> 0x007f }
            if (r3 != 0) goto L_0x0049
            r2.onWindowLayoutChangeListenerAdded(r6)     // Catch:{ all -> 0x007f }
            goto L_0x0077
        L_0x0049:
            java.util.concurrent.CopyOnWriteArrayList<androidx.window.layout.adapter.sidecar.SidecarWindowBackend$WindowLayoutChangeCallbackWrapper> r7 = r5.windowLayoutChangeCallbacks     // Catch:{ all -> 0x007f }
            java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch:{ all -> 0x007f }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x007f }
        L_0x0051:
            boolean r2 = r7.hasNext()     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x0069
            java.lang.Object r2 = r7.next()     // Catch:{ all -> 0x007f }
            r3 = r2
            androidx.window.layout.adapter.sidecar.SidecarWindowBackend$WindowLayoutChangeCallbackWrapper r3 = (androidx.window.layout.adapter.sidecar.SidecarWindowBackend.WindowLayoutChangeCallbackWrapper) r3     // Catch:{ all -> 0x007f }
            android.app.Activity r3 = r3.getActivity()     // Catch:{ all -> 0x007f }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r3)     // Catch:{ all -> 0x007f }
            if (r3 == 0) goto L_0x0051
            goto L_0x006a
        L_0x0069:
            r2 = r1
        L_0x006a:
            androidx.window.layout.adapter.sidecar.SidecarWindowBackend$WindowLayoutChangeCallbackWrapper r2 = (androidx.window.layout.adapter.sidecar.SidecarWindowBackend.WindowLayoutChangeCallbackWrapper) r2     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x0072
            androidx.window.layout.WindowLayoutInfo r1 = r2.getLastInfo()     // Catch:{ all -> 0x007f }
        L_0x0072:
            if (r1 == 0) goto L_0x0077
            r4.accept(r1)     // Catch:{ all -> 0x007f }
        L_0x0077:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x007f }
            r0.unlock()
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            goto L_0x0084
        L_0x007f:
            r6 = move-exception
            r0.unlock()
            throw r6
        L_0x0084:
            if (r1 != 0) goto L_0x0095
            r6 = r5
            androidx.window.layout.adapter.sidecar.SidecarWindowBackend r6 = (androidx.window.layout.adapter.sidecar.SidecarWindowBackend) r6
            androidx.window.layout.WindowLayoutInfo r6 = new androidx.window.layout.WindowLayoutInfo
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()
            r6.<init>(r7)
            r8.accept(r6)
        L_0x0095:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.sidecar.SidecarWindowBackend.registerLayoutChangeCallback(android.content.Context, java.util.concurrent.Executor, androidx.core.util.Consumer):void");
    }

    private final boolean isActivityRegistered(Activity activity) {
        Iterable<WindowLayoutChangeCallbackWrapper> iterable = this.windowLayoutChangeCallbacks;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (WindowLayoutChangeCallbackWrapper activity2 : iterable) {
            if (Intrinsics.areEqual((Object) activity2.getActivity(), (Object) activity)) {
                return true;
            }
        }
        return false;
    }

    public void unregisterLayoutChangeCallback(Consumer<WindowLayoutInfo> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "callback");
        synchronized (globalLock) {
            if (this.windowExtension != null) {
                List<WindowLayoutChangeCallbackWrapper> arrayList = new ArrayList<>();
                Iterator<WindowLayoutChangeCallbackWrapper> it = this.windowLayoutChangeCallbacks.iterator();
                while (it.hasNext()) {
                    WindowLayoutChangeCallbackWrapper next = it.next();
                    if (next.getCallback() == consumer) {
                        Intrinsics.checkNotNullExpressionValue(next, "callbackWrapper");
                        arrayList.add(next);
                    }
                }
                this.windowLayoutChangeCallbacks.removeAll(arrayList);
                for (WindowLayoutChangeCallbackWrapper activity : arrayList) {
                    callbackRemovedForActivity(activity.getActivity());
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    private final void callbackRemovedForActivity(Activity activity) {
        Iterable<WindowLayoutChangeCallbackWrapper> iterable = this.windowLayoutChangeCallbacks;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            for (WindowLayoutChangeCallbackWrapper activity2 : iterable) {
                if (Intrinsics.areEqual((Object) activity2.getActivity(), (Object) activity)) {
                    return;
                }
            }
        }
        ExtensionInterfaceCompat extensionInterfaceCompat = this.windowExtension;
        if (extensionInterfaceCompat != null) {
            extensionInterfaceCompat.onWindowLayoutChangeListenerRemoved(activity);
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0017¨\u0006\t"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarWindowBackend$ExtensionListenerImpl;", "Landroidx/window/layout/adapter/sidecar/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "(Landroidx/window/layout/adapter/sidecar/SidecarWindowBackend;)V", "onWindowLayoutChanged", "", "activity", "Landroid/app/Activity;", "newLayout", "Landroidx/window/layout/WindowLayoutInfo;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SidecarWindowBackend.kt */
    public final class ExtensionListenerImpl implements ExtensionInterfaceCompat.ExtensionCallbackInterface {
        public ExtensionListenerImpl() {
        }

        public void onWindowLayoutChanged(Activity activity, WindowLayoutInfo windowLayoutInfo) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(windowLayoutInfo, "newLayout");
            Iterator<WindowLayoutChangeCallbackWrapper> it = SidecarWindowBackend.this.getWindowLayoutChangeCallbacks().iterator();
            while (it.hasNext()) {
                WindowLayoutChangeCallbackWrapper next = it.next();
                if (Intrinsics.areEqual((Object) next.getActivity(), (Object) activity)) {
                    next.accept(windowLayoutInfo);
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarWindowBackend$WindowLayoutChangeCallbackWrapper;", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "getActivity", "()Landroid/app/Activity;", "getCallback", "()Landroidx/core/util/Consumer;", "lastInfo", "getLastInfo", "()Landroidx/window/layout/WindowLayoutInfo;", "setLastInfo", "(Landroidx/window/layout/WindowLayoutInfo;)V", "accept", "", "newLayoutInfo", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SidecarWindowBackend.kt */
    public static final class WindowLayoutChangeCallbackWrapper {
        private final Activity activity;
        private final Consumer<WindowLayoutInfo> callback;
        private final Executor executor;
        private WindowLayoutInfo lastInfo;

        public WindowLayoutChangeCallbackWrapper(Activity activity2, Executor executor2, Consumer<WindowLayoutInfo> consumer) {
            Intrinsics.checkNotNullParameter(activity2, "activity");
            Intrinsics.checkNotNullParameter(executor2, "executor");
            Intrinsics.checkNotNullParameter(consumer, "callback");
            this.activity = activity2;
            this.executor = executor2;
            this.callback = consumer;
        }

        public final Activity getActivity() {
            return this.activity;
        }

        public final Consumer<WindowLayoutInfo> getCallback() {
            return this.callback;
        }

        public final WindowLayoutInfo getLastInfo() {
            return this.lastInfo;
        }

        public final void setLastInfo(WindowLayoutInfo windowLayoutInfo) {
            this.lastInfo = windowLayoutInfo;
        }

        public final void accept(WindowLayoutInfo windowLayoutInfo) {
            Intrinsics.checkNotNullParameter(windowLayoutInfo, "newLayoutInfo");
            this.lastInfo = windowLayoutInfo;
            this.executor.execute(new SidecarWindowBackend$WindowLayoutChangeCallbackWrapper$$ExternalSyntheticLambda0(this, windowLayoutInfo));
        }

        /* access modifiers changed from: private */
        public static final void accept$lambda$0(WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper, WindowLayoutInfo windowLayoutInfo) {
            Intrinsics.checkNotNullParameter(windowLayoutChangeCallbackWrapper, "this$0");
            Intrinsics.checkNotNullParameter(windowLayoutInfo, "$newLayoutInfo");
            windowLayoutChangeCallbackWrapper.callback.accept(windowLayoutInfo);
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\rJ\u0012\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarWindowBackend$Companion;", "", "()V", "DEBUG", "", "TAG", "", "globalInstance", "Landroidx/window/layout/adapter/sidecar/SidecarWindowBackend;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "getInstance", "context", "Landroid/content/Context;", "initAndVerifyExtension", "Landroidx/window/layout/adapter/sidecar/ExtensionInterfaceCompat;", "isSidecarVersionSupported", "sidecarVersion", "Landroidx/window/core/Version;", "resetInstance", "", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SidecarWindowBackend.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SidecarWindowBackend getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (SidecarWindowBackend.globalInstance == null) {
                Lock access$getGlobalLock$cp = SidecarWindowBackend.globalLock;
                access$getGlobalLock$cp.lock();
                try {
                    if (SidecarWindowBackend.globalInstance == null) {
                        ExtensionInterfaceCompat initAndVerifyExtension = SidecarWindowBackend.Companion.initAndVerifyExtension(context);
                        Companion companion = SidecarWindowBackend.Companion;
                        SidecarWindowBackend.globalInstance = new SidecarWindowBackend(initAndVerifyExtension);
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                    access$getGlobalLock$cp.unlock();
                }
            }
            SidecarWindowBackend access$getGlobalInstance$cp = SidecarWindowBackend.globalInstance;
            Intrinsics.checkNotNull(access$getGlobalInstance$cp);
            return access$getGlobalInstance$cp;
        }

        public final ExtensionInterfaceCompat initAndVerifyExtension(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                if (!isSidecarVersionSupported(SidecarCompat.Companion.getSidecarVersion())) {
                    return null;
                }
                ExtensionInterfaceCompat sidecarCompat = new SidecarCompat(context);
                if (!((SidecarCompat) sidecarCompat).validateExtensionInterface()) {
                    return null;
                }
                return sidecarCompat;
            } catch (Throwable unused) {
                return null;
            }
        }

        public final boolean isSidecarVersionSupported(Version version) {
            return version != null && version.compareTo(Version.Companion.getVERSION_0_1()) >= 0;
        }

        public final void resetInstance() {
            SidecarWindowBackend.globalInstance = null;
        }
    }
}
