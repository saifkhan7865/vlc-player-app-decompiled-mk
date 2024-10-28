package io.ktor.network.selector;

import java.io.Closeable;
import java.nio.channels.spi.SelectorProvider;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a[\u0010\u0004\u001a\u0002H\u0005\"\f\b\u0000\u0010\u0006*\u00060\u0007j\u0002`\b\"\u0004\b\u0001\u0010\u0005*\u00020\u00012\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u0002H\u00060\n¢\u0006\u0002\b\f2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00050\n¢\u0006\u0002\b\fH\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u0002\u0007\n\u0005\b20\u0001¨\u0006\u000f"}, d2 = {"SelectorManager", "Lio/ktor/network/selector/SelectorManager;", "dispatcher", "Lkotlin/coroutines/CoroutineContext;", "buildOrClose", "R", "C", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "create", "Lkotlin/Function1;", "Ljava/nio/channels/spi/SelectorProvider;", "Lkotlin/ExtensionFunctionType;", "setup", "(Lio/ktor/network/selector/SelectorManager;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SelectorManager.kt */
public final class SelectorManagerKt {
    public static /* synthetic */ SelectorManager SelectorManager$default(CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return SelectorManager(coroutineContext);
    }

    public static final SelectorManager SelectorManager(CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "dispatcher");
        return new ActorSelectorManager(coroutineContext);
    }

    public static final <C extends Closeable, R> R buildOrClose(SelectorManager selectorManager, Function1<? super SelectorProvider, ? extends C> function1, Function1<? super C, ? extends R> function12) {
        Intrinsics.checkNotNullParameter(selectorManager, "<this>");
        Intrinsics.checkNotNullParameter(function1, "create");
        Intrinsics.checkNotNullParameter(function12, "setup");
        Closeable closeable = (Closeable) function1.invoke(selectorManager.getProvider());
        try {
            return function12.invoke(closeable);
        } catch (Throwable th) {
            closeable.close();
            throw th;
        }
    }
}
