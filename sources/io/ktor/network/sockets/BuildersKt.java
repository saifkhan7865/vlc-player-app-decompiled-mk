package io.ktor.network.sockets;

import io.ktor.network.selector.SelectorManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a#\u0010\u0004\u001a\u0002H\u0005\"\u0012\b\u0000\u0010\u0005*\f\u0012\u0004\u0012\u0002H\u0005\u0012\u0002\b\u00030\u0006*\u0002H\u0005¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"aSocket", "Lio/ktor/network/sockets/SocketBuilder;", "selector", "Lio/ktor/network/selector/SelectorManager;", "tcpNoDelay", "T", "Lio/ktor/network/sockets/Configurable;", "(Lio/ktor/network/sockets/Configurable;)Lio/ktor/network/sockets/Configurable;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Builders.kt */
public final class BuildersKt {
    public static final SocketBuilder aSocket(SelectorManager selectorManager) {
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        return new SocketBuilder(selectorManager, SocketOptions.Companion.create$ktor_network());
    }

    public static final <T extends Configurable<? extends T, ?>> T tcpNoDelay(T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        return t.configure(BuildersKt$tcpNoDelay$1.INSTANCE);
    }
}
