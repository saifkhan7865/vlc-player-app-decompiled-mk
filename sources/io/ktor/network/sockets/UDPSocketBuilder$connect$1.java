package io.ktor.network.sockets;

import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: UDPSocketBuilder.kt */
final class UDPSocketBuilder$connect$1 extends Lambda implements Function1<SocketOptions.UDPSocketOptions, Unit> {
    public static final UDPSocketBuilder$connect$1 INSTANCE = new UDPSocketBuilder$connect$1();

    UDPSocketBuilder$connect$1() {
        super(1);
    }

    public final void invoke(SocketOptions.UDPSocketOptions uDPSocketOptions) {
        Intrinsics.checkNotNullParameter(uDPSocketOptions, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SocketOptions.UDPSocketOptions) obj);
        return Unit.INSTANCE;
    }
}
