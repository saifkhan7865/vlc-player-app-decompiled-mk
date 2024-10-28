package androidx.window.layout.adapter.extensions;

import android.app.Activity;
import android.content.Context;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.WindowBackend;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001\u001eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0007J&\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016J\u0016\u0010\u001d\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\b8\u0002X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\b8\u0002X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\b8\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0012\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0004\u0012\u00020\u000f0\b8\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionWindowLayoutInfoBackend;", "Landroidx/window/layout/adapter/WindowBackend;", "component", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "(Landroidx/window/extensions/layout/WindowLayoutComponent;Landroidx/window/core/ConsumerAdapter;)V", "consumerToOemConsumer", "", "Landroidx/window/layout/adapter/extensions/ExtensionWindowLayoutInfoBackend$MulticastConsumer;", "Landroidx/window/extensions/core/util/function/Consumer;", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "consumerToToken", "Landroidx/window/core/ConsumerAdapter$Subscription;", "contextToListeners", "Landroid/content/Context;", "extensionWindowBackendLock", "Ljava/util/concurrent/locks/ReentrantLock;", "listenerToContext", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "hasRegisteredListeners", "", "registerLayoutChangeCallback", "", "context", "executor", "Ljava/util/concurrent/Executor;", "callback", "unregisterLayoutChangeCallback", "MulticastConsumer", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExtensionWindowLayoutInfoBackend.kt */
public final class ExtensionWindowLayoutInfoBackend implements WindowBackend {
    private final WindowLayoutComponent component;
    private final ConsumerAdapter consumerAdapter;
    private final Map<MulticastConsumer, Consumer<WindowLayoutInfo>> consumerToOemConsumer = new LinkedHashMap();
    private final Map<MulticastConsumer, ConsumerAdapter.Subscription> consumerToToken = new LinkedHashMap();
    private final Map<Context, MulticastConsumer> contextToListeners = new LinkedHashMap();
    private final ReentrantLock extensionWindowBackendLock = new ReentrantLock();
    private final Map<androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo>, Context> listenerToContext = new LinkedHashMap();

    public ExtensionWindowLayoutInfoBackend(WindowLayoutComponent windowLayoutComponent, ConsumerAdapter consumerAdapter2) {
        Intrinsics.checkNotNullParameter(windowLayoutComponent, "component");
        Intrinsics.checkNotNullParameter(consumerAdapter2, "consumerAdapter");
        this.component = windowLayoutComponent;
        this.consumerAdapter = consumerAdapter2;
    }

    public void registerLayoutChangeCallback(Context context, Executor executor, androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> consumer) {
        Unit unit;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(consumer, "callback");
        Lock lock = this.extensionWindowBackendLock;
        lock.lock();
        try {
            MulticastConsumer multicastConsumer = this.contextToListeners.get(context);
            if (multicastConsumer != null) {
                multicastConsumer.addListener(consumer);
                this.listenerToContext.put(consumer, context);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                ExtensionWindowLayoutInfoBackend extensionWindowLayoutInfoBackend = this;
                MulticastConsumer multicastConsumer2 = new MulticastConsumer(context);
                this.contextToListeners.put(context, multicastConsumer2);
                this.listenerToContext.put(consumer, context);
                multicastConsumer2.addListener(consumer);
                if (ExtensionsUtil.INSTANCE.getSafeVendorApiLevel() < 2) {
                    Function1 extensionWindowLayoutInfoBackend$registerLayoutChangeCallback$1$2$consumeWindowLayoutInfo$1 = new ExtensionWindowLayoutInfoBackend$registerLayoutChangeCallback$1$2$consumeWindowLayoutInfo$1(multicastConsumer2);
                    if (context instanceof Activity) {
                        this.consumerToToken.put(multicastConsumer2, this.consumerAdapter.createSubscription((Object) this.component, Reflection.getOrCreateKotlinClass(WindowLayoutInfo.class), "addWindowLayoutInfoListener", "removeWindowLayoutInfoListener", (Activity) context, extensionWindowLayoutInfoBackend$registerLayoutChangeCallback$1$2$consumeWindowLayoutInfo$1));
                    } else {
                        multicastConsumer2.accept(new WindowLayoutInfo(CollectionsKt.emptyList()));
                        lock.unlock();
                        return;
                    }
                } else {
                    ExtensionWindowLayoutInfoBackend$$ExternalSyntheticLambda0 extensionWindowLayoutInfoBackend$$ExternalSyntheticLambda0 = new ExtensionWindowLayoutInfoBackend$$ExternalSyntheticLambda0(multicastConsumer2);
                    this.consumerToOemConsumer.put(multicastConsumer2, extensionWindowLayoutInfoBackend$$ExternalSyntheticLambda0);
                    this.component.addWindowLayoutInfoListener(context, extensionWindowLayoutInfoBackend$$ExternalSyntheticLambda0);
                }
            }
            Unit unit2 = Unit.INSTANCE;
        } finally {
            lock.unlock();
        }
    }

    /* access modifiers changed from: private */
    public static final void registerLayoutChangeCallback$lambda$3$lambda$2$lambda$1(MulticastConsumer multicastConsumer, WindowLayoutInfo windowLayoutInfo) {
        Intrinsics.checkNotNullParameter(multicastConsumer, "$consumer");
        Intrinsics.checkNotNullExpressionValue(windowLayoutInfo, "info");
        multicastConsumer.accept(windowLayoutInfo);
    }

    public void unregisterLayoutChangeCallback(androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "callback");
        Lock lock = this.extensionWindowBackendLock;
        lock.lock();
        try {
            Context context = this.listenerToContext.get(consumer);
            if (context != null) {
                MulticastConsumer multicastConsumer = this.contextToListeners.get(context);
                if (multicastConsumer == null) {
                    lock.unlock();
                    return;
                }
                multicastConsumer.removeListener(consumer);
                this.listenerToContext.remove(consumer);
                if (multicastConsumer.isEmpty()) {
                    this.contextToListeners.remove(context);
                    if (ExtensionsUtil.INSTANCE.getSafeVendorApiLevel() < 2) {
                        ConsumerAdapter.Subscription remove = this.consumerToToken.remove(multicastConsumer);
                        if (remove != null) {
                            remove.dispose();
                        }
                    } else {
                        Consumer remove2 = this.consumerToOemConsumer.remove(multicastConsumer);
                        if (remove2 != null) {
                            this.component.removeWindowLayoutInfoListener(remove2);
                        }
                    }
                }
                Unit unit = Unit.INSTANCE;
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }
    }

    public final boolean hasRegisteredListeners() {
        return !this.contextToListeners.isEmpty() || !this.listenerToContext.isEmpty() || !this.consumerToToken.isEmpty();
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0014\u0010\u000f\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\u0014\u0010\u0013\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00010\u000b8\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionWindowLayoutInfoBackend$MulticastConsumer;", "Landroidx/core/util/Consumer;", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "lastKnownValue", "Landroidx/window/layout/WindowLayoutInfo;", "multicastConsumerLock", "Ljava/util/concurrent/locks/ReentrantLock;", "registeredListeners", "", "accept", "", "value", "addListener", "listener", "isEmpty", "", "removeListener", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ExtensionWindowLayoutInfoBackend.kt */
    private static final class MulticastConsumer implements androidx.core.util.Consumer<WindowLayoutInfo> {
        private final Context context;
        private androidx.window.layout.WindowLayoutInfo lastKnownValue;
        private final ReentrantLock multicastConsumerLock = new ReentrantLock();
        private final Set<androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo>> registeredListeners = new LinkedHashSet();

        public MulticastConsumer(Context context2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
        }

        public void accept(WindowLayoutInfo windowLayoutInfo) {
            Intrinsics.checkNotNullParameter(windowLayoutInfo, "value");
            Lock lock = this.multicastConsumerLock;
            lock.lock();
            try {
                this.lastKnownValue = ExtensionsWindowLayoutInfoAdapter.INSTANCE.translate$window_release(this.context, windowLayoutInfo);
                for (androidx.core.util.Consumer accept : this.registeredListeners) {
                    accept.accept(this.lastKnownValue);
                }
                Unit unit = Unit.INSTANCE;
            } finally {
                lock.unlock();
            }
        }

        public final void addListener(androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> consumer) {
            Intrinsics.checkNotNullParameter(consumer, "listener");
            Lock lock = this.multicastConsumerLock;
            lock.lock();
            try {
                androidx.window.layout.WindowLayoutInfo windowLayoutInfo = this.lastKnownValue;
                if (windowLayoutInfo != null) {
                    consumer.accept(windowLayoutInfo);
                }
                this.registeredListeners.add(consumer);
            } finally {
                lock.unlock();
            }
        }

        public final void removeListener(androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> consumer) {
            Intrinsics.checkNotNullParameter(consumer, "listener");
            Lock lock = this.multicastConsumerLock;
            lock.lock();
            try {
                this.registeredListeners.remove(consumer);
            } finally {
                lock.unlock();
            }
        }

        public final boolean isEmpty() {
            return this.registeredListeners.isEmpty();
        }
    }
}
