package io.ktor.network.sockets;

import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.Configurable;
import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J-\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0002\b\u0012J5\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0002\b\u0012R\u001a\u0010\u0005\u001a\u00020\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lio/ktor/network/sockets/UDPSocketBuilder;", "Lio/ktor/network/sockets/Configurable;", "Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "selector", "Lio/ktor/network/selector/SelectorManager;", "options", "(Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;)V", "getOptions", "()Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "setOptions", "(Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;)V", "bind", "Lio/ktor/network/sockets/BoundDatagramSocket;", "localAddress", "Lio/ktor/network/sockets/SocketAddress;", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "connect", "Lio/ktor/network/sockets/ConnectedDatagramSocket;", "remoteAddress", "Companion", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: UDPSocketBuilder.kt */
public final class UDPSocketBuilder implements Configurable<UDPSocketBuilder, SocketOptions.UDPSocketOptions> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private SocketOptions.UDPSocketOptions options;
    private final SelectorManager selector;

    public UDPSocketBuilder(SelectorManager selectorManager, SocketOptions.UDPSocketOptions uDPSocketOptions) {
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        Intrinsics.checkNotNullParameter(uDPSocketOptions, "options");
        this.selector = selectorManager;
        this.options = uDPSocketOptions;
    }

    public UDPSocketBuilder configure(Function1<? super SocketOptions.UDPSocketOptions, Unit> function1) {
        return (UDPSocketBuilder) Configurable.DefaultImpls.configure(this, function1);
    }

    public SocketOptions.UDPSocketOptions getOptions() {
        return this.options;
    }

    public void setOptions(SocketOptions.UDPSocketOptions uDPSocketOptions) {
        Intrinsics.checkNotNullParameter(uDPSocketOptions, "<set-?>");
        this.options = uDPSocketOptions;
    }

    public static /* synthetic */ BoundDatagramSocket bind$default(UDPSocketBuilder uDPSocketBuilder, SocketAddress socketAddress, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            socketAddress = null;
        }
        if ((i & 2) != 0) {
            function1 = UDPSocketBuilder$bind$1.INSTANCE;
        }
        return uDPSocketBuilder.bind(socketAddress, function1);
    }

    public final BoundDatagramSocket bind(SocketAddress socketAddress, Function1<? super SocketOptions.UDPSocketOptions, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "configure");
        Companion companion = Companion;
        SelectorManager selectorManager = this.selector;
        SocketOptions.UDPSocketOptions udp$ktor_network = getOptions().udp$ktor_network();
        function1.invoke(udp$ktor_network);
        return UDPSocketBuilderJvmKt.bindUDP(companion, selectorManager, socketAddress, udp$ktor_network);
    }

    public static /* synthetic */ ConnectedDatagramSocket connect$default(UDPSocketBuilder uDPSocketBuilder, SocketAddress socketAddress, SocketAddress socketAddress2, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            socketAddress2 = null;
        }
        if ((i & 4) != 0) {
            function1 = UDPSocketBuilder$connect$1.INSTANCE;
        }
        return uDPSocketBuilder.connect(socketAddress, socketAddress2, function1);
    }

    public final ConnectedDatagramSocket connect(SocketAddress socketAddress, SocketAddress socketAddress2, Function1<? super SocketOptions.UDPSocketOptions, Unit> function1) {
        Intrinsics.checkNotNullParameter(socketAddress, "remoteAddress");
        Intrinsics.checkNotNullParameter(function1, "configure");
        Companion companion = Companion;
        SelectorManager selectorManager = this.selector;
        SocketOptions.UDPSocketOptions udp$ktor_network = getOptions().udp$ktor_network();
        function1.invoke(udp$ktor_network);
        return UDPSocketBuilderJvmKt.connectUDP(companion, selectorManager, socketAddress, socketAddress2, udp$ktor_network);
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/network/sockets/UDPSocketBuilder$Companion;", "", "()V", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: UDPSocketBuilder.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
