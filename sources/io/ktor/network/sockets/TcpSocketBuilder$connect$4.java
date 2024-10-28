package io.ktor.network.sockets;

import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: TcpSocketBuilder.kt */
final class TcpSocketBuilder$connect$4 extends Lambda implements Function1<SocketOptions.TCPClientSocketOptions, Unit> {
    public static final TcpSocketBuilder$connect$4 INSTANCE = new TcpSocketBuilder$connect$4();

    TcpSocketBuilder$connect$4() {
        super(1);
    }

    public final void invoke(SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        Intrinsics.checkNotNullParameter(tCPClientSocketOptions, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SocketOptions.TCPClientSocketOptions) obj);
        return Unit.INSTANCE;
    }
}
