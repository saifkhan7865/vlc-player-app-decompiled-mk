package io.ktor.network.sockets;

import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.Configurable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eR\u001a\u0010\u0005\u001a\u00020\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lio/ktor/network/sockets/SocketBuilder;", "Lio/ktor/network/sockets/Configurable;", "Lio/ktor/network/sockets/SocketOptions;", "selector", "Lio/ktor/network/selector/SelectorManager;", "options", "(Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions;)V", "getOptions", "()Lio/ktor/network/sockets/SocketOptions;", "setOptions", "(Lio/ktor/network/sockets/SocketOptions;)V", "tcp", "Lio/ktor/network/sockets/TcpSocketBuilder;", "udp", "Lio/ktor/network/sockets/UDPSocketBuilder;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Builders.kt */
public final class SocketBuilder implements Configurable<SocketBuilder, SocketOptions> {
    private SocketOptions options;
    private final SelectorManager selector;

    public SocketBuilder(SelectorManager selectorManager, SocketOptions socketOptions) {
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        Intrinsics.checkNotNullParameter(socketOptions, "options");
        this.selector = selectorManager;
        this.options = socketOptions;
    }

    public SocketBuilder configure(Function1<? super SocketOptions, Unit> function1) {
        return (SocketBuilder) Configurable.DefaultImpls.configure(this, function1);
    }

    public SocketOptions getOptions() {
        return this.options;
    }

    public void setOptions(SocketOptions socketOptions) {
        Intrinsics.checkNotNullParameter(socketOptions, "<set-?>");
        this.options = socketOptions;
    }

    public final TcpSocketBuilder tcp() {
        return new TcpSocketBuilder(this.selector, getOptions().peer$ktor_network());
    }

    public final UDPSocketBuilder udp() {
        return new UDPSocketBuilder(this.selector, getOptions().peer$ktor_network().udp$ktor_network());
    }
}
