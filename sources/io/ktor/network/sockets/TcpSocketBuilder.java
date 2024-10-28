package io.ktor.network.sockets;

import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.Configurable;
import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J-\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013J5\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013J4\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000e2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ<\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u001a\u0010\u0005\u001a\u00020\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/network/sockets/TcpSocketBuilder;", "Lio/ktor/network/sockets/Configurable;", "Lio/ktor/network/sockets/SocketOptions;", "selector", "Lio/ktor/network/selector/SelectorManager;", "options", "(Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions;)V", "getOptions", "()Lio/ktor/network/sockets/SocketOptions;", "setOptions", "(Lio/ktor/network/sockets/SocketOptions;)V", "bind", "Lio/ktor/network/sockets/ServerSocket;", "localAddress", "Lio/ktor/network/sockets/SocketAddress;", "configure", "Lkotlin/Function1;", "Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "", "Lkotlin/ExtensionFunctionType;", "hostname", "", "port", "", "connect", "Lio/ktor/network/sockets/Socket;", "remoteAddress", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "(Lio/ktor/network/sockets/SocketAddress;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TcpSocketBuilder.kt */
public final class TcpSocketBuilder implements Configurable<TcpSocketBuilder, SocketOptions> {
    private SocketOptions options;
    private final SelectorManager selector;

    public TcpSocketBuilder(SelectorManager selectorManager, SocketOptions socketOptions) {
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        Intrinsics.checkNotNullParameter(socketOptions, "options");
        this.selector = selectorManager;
        this.options = socketOptions;
    }

    public TcpSocketBuilder configure(Function1<? super SocketOptions, Unit> function1) {
        return (TcpSocketBuilder) Configurable.DefaultImpls.configure(this, function1);
    }

    public SocketOptions getOptions() {
        return this.options;
    }

    public void setOptions(SocketOptions socketOptions) {
        Intrinsics.checkNotNullParameter(socketOptions, "<set-?>");
        this.options = socketOptions;
    }

    public static /* synthetic */ Object connect$default(TcpSocketBuilder tcpSocketBuilder, String str, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            function1 = TcpSocketBuilder$connect$2.INSTANCE;
        }
        return tcpSocketBuilder.connect(str, i, function1, continuation);
    }

    public final Object connect(String str, int i, Function1<? super SocketOptions.TCPClientSocketOptions, Unit> function1, Continuation<? super Socket> continuation) {
        return connect(new InetSocketAddress(str, i), function1, continuation);
    }

    public static /* synthetic */ ServerSocket bind$default(TcpSocketBuilder tcpSocketBuilder, String str, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "0.0.0.0";
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            function1 = TcpSocketBuilder$bind$1.INSTANCE;
        }
        return tcpSocketBuilder.bind(str, i, function1);
    }

    public final ServerSocket bind(String str, int i, Function1<? super SocketOptions.AcceptorOptions, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "hostname");
        Intrinsics.checkNotNullParameter(function1, "configure");
        return bind(new InetSocketAddress(str, i), function1);
    }

    public static /* synthetic */ Object connect$default(TcpSocketBuilder tcpSocketBuilder, SocketAddress socketAddress, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = TcpSocketBuilder$connect$4.INSTANCE;
        }
        return tcpSocketBuilder.connect(socketAddress, function1, continuation);
    }

    public final Object connect(SocketAddress socketAddress, Function1<? super SocketOptions.TCPClientSocketOptions, Unit> function1, Continuation<? super Socket> continuation) {
        SelectorManager selectorManager = this.selector;
        SocketOptions.TCPClientSocketOptions tcp$ktor_network = getOptions().peer$ktor_network().tcp$ktor_network();
        function1.invoke(tcp$ktor_network);
        return ConnectUtilsJvmKt.connect(selectorManager, socketAddress, tcp$ktor_network, continuation);
    }

    public static /* synthetic */ ServerSocket bind$default(TcpSocketBuilder tcpSocketBuilder, SocketAddress socketAddress, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            socketAddress = null;
        }
        if ((i & 2) != 0) {
            function1 = TcpSocketBuilder$bind$2.INSTANCE;
        }
        return tcpSocketBuilder.bind(socketAddress, function1);
    }

    public final ServerSocket bind(SocketAddress socketAddress, Function1<? super SocketOptions.AcceptorOptions, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "configure");
        SelectorManager selectorManager = this.selector;
        SocketOptions.AcceptorOptions acceptor$ktor_network = getOptions().peer$ktor_network().acceptor$ktor_network();
        function1.invoke(acceptor$ktor_network);
        return ConnectUtilsJvmKt.bind(selectorManager, socketAddress, acceptor$ktor_network);
    }
}
