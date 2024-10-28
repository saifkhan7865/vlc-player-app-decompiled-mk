package io.ktor.network.sockets;

import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"connect", "Lio/ktor/network/sockets/Socket;", "Lio/ktor/network/sockets/TcpSocketBuilder;", "remoteAddress", "Lio/ktor/network/sockets/SocketAddress;", "configure", "Lkotlin/Function1;", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/network/sockets/TcpSocketBuilder;Lio/ktor/network/sockets/SocketAddress;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: TCPSocketBuilderJvm.kt */
public final class TCPSocketBuilderJvmKt {
    public static /* synthetic */ Object connect$default(TcpSocketBuilder tcpSocketBuilder, SocketAddress socketAddress, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = TCPSocketBuilderJvmKt$connect$2.INSTANCE;
        }
        return connect(tcpSocketBuilder, socketAddress, function1, continuation);
    }

    public static final Object connect(TcpSocketBuilder tcpSocketBuilder, SocketAddress socketAddress, Function1<? super SocketOptions.TCPClientSocketOptions, Unit> function1, Continuation<? super Socket> continuation) {
        return tcpSocketBuilder.connect(socketAddress, function1, continuation);
    }
}
