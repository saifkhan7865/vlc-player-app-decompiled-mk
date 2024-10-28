package io.ktor.network.sockets;

import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0012\b\u0000\u0010\u0002*\f\u0012\u0004\u0012\u0002H\u0002\u0012\u0002\b\u00030\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "Lio/ktor/network/sockets/Configurable;", "Lio/ktor/network/sockets/SocketOptions;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Builders.kt */
final class BuildersKt$tcpNoDelay$1 extends Lambda implements Function1<SocketOptions, Unit> {
    public static final BuildersKt$tcpNoDelay$1 INSTANCE = new BuildersKt$tcpNoDelay$1();

    BuildersKt$tcpNoDelay$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SocketOptions) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(SocketOptions socketOptions) {
        Intrinsics.checkNotNullParameter(socketOptions, "$this$configure");
        if (socketOptions instanceof SocketOptions.TCPClientSocketOptions) {
            ((SocketOptions.TCPClientSocketOptions) socketOptions).setNoDelay(true);
        }
    }
}
