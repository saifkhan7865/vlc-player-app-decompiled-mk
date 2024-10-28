package io.ktor.network.sockets;

import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.network.sockets.UDPSocketBuilder;
import java.io.Closeable;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a.\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¨\u0006\f"}, d2 = {"bindUDP", "Lio/ktor/network/sockets/BoundDatagramSocket;", "Lio/ktor/network/sockets/UDPSocketBuilder$Companion;", "selector", "Lio/ktor/network/selector/SelectorManager;", "localAddress", "Lio/ktor/network/sockets/SocketAddress;", "options", "Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "connectUDP", "Lio/ktor/network/sockets/ConnectedDatagramSocket;", "remoteAddress", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: UDPSocketBuilderJvm.kt */
public final class UDPSocketBuilderJvmKt {
    public static final ConnectedDatagramSocket connectUDP(UDPSocketBuilder.Companion companion, SelectorManager selectorManager, SocketAddress socketAddress, SocketAddress socketAddress2, SocketOptions.UDPSocketOptions uDPSocketOptions) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        Intrinsics.checkNotNullParameter(socketAddress, "remoteAddress");
        Intrinsics.checkNotNullParameter(uDPSocketOptions, "options");
        Closeable openDatagramChannel = selectorManager.getProvider().openDatagramChannel();
        try {
            DatagramChannel datagramChannel = (DatagramChannel) openDatagramChannel;
            Intrinsics.checkNotNullExpressionValue(datagramChannel, "connectUDP$lambda$1");
            JavaSocketOptionsKt.assignOptions(datagramChannel, uDPSocketOptions);
            JavaSocketOptionsKt.nonBlocking(datagramChannel);
            SocketAddress socketAddress3 = null;
            if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                if (socketAddress2 != null) {
                    socketAddress3 = JavaSocketAddressUtilsKt.toJavaAddress(socketAddress2);
                }
                DatagramChannel unused = datagramChannel.bind(socketAddress3);
            } else {
                DatagramSocket socket = datagramChannel.socket();
                if (socketAddress2 != null) {
                    socketAddress3 = JavaSocketAddressUtilsKt.toJavaAddress(socketAddress2);
                }
                socket.bind(socketAddress3);
            }
            datagramChannel.connect(JavaSocketAddressUtilsKt.toJavaAddress(socketAddress));
            return new DatagramSocketImpl(datagramChannel, selectorManager);
        } catch (Throwable th) {
            openDatagramChannel.close();
            throw th;
        }
    }

    public static final BoundDatagramSocket bindUDP(UDPSocketBuilder.Companion companion, SelectorManager selectorManager, SocketAddress socketAddress, SocketOptions.UDPSocketOptions uDPSocketOptions) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        Intrinsics.checkNotNullParameter(uDPSocketOptions, "options");
        Closeable openDatagramChannel = selectorManager.getProvider().openDatagramChannel();
        try {
            DatagramChannel datagramChannel = (DatagramChannel) openDatagramChannel;
            Intrinsics.checkNotNullExpressionValue(datagramChannel, "bindUDP$lambda$3");
            JavaSocketOptionsKt.assignOptions(datagramChannel, uDPSocketOptions);
            JavaSocketOptionsKt.nonBlocking(datagramChannel);
            SocketAddress socketAddress2 = null;
            if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                if (socketAddress != null) {
                    socketAddress2 = JavaSocketAddressUtilsKt.toJavaAddress(socketAddress);
                }
                DatagramChannel unused = datagramChannel.bind(socketAddress2);
            } else {
                DatagramSocket socket = datagramChannel.socket();
                if (socketAddress != null) {
                    socketAddress2 = JavaSocketAddressUtilsKt.toJavaAddress(socketAddress);
                }
                socket.bind(socketAddress2);
            }
            return new DatagramSocketImpl(datagramChannel, selectorManager);
        } catch (Throwable th) {
            openDatagramChannel.close();
            throw th;
        }
    }
}
