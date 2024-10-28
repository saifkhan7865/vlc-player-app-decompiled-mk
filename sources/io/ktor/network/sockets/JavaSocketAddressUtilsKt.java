package io.ktor.network.sockets;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0000¨\u0006\u0004"}, d2 = {"toJavaAddress", "Ljava/net/SocketAddress;", "Lio/ktor/network/sockets/SocketAddress;", "toSocketAddress", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: JavaSocketAddressUtils.kt */
public final class JavaSocketAddressUtilsKt {
    public static final SocketAddress toJavaAddress(SocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(socketAddress, "<this>");
        return socketAddress.getAddress$ktor_network();
    }

    public static final SocketAddress toSocketAddress(SocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(socketAddress, "<this>");
        if (socketAddress instanceof InetSocketAddress) {
            return new InetSocketAddress((InetSocketAddress) socketAddress);
        }
        if (Intrinsics.areEqual((Object) socketAddress.getClass().getName(), (Object) SocketAddressJvmKt.UNIX_DOMAIN_SOCKET_ADDRESS_CLASS)) {
            return new UnixSocketAddress(socketAddress);
        }
        throw new IllegalStateException("Unknown socket address type".toString());
    }
}
