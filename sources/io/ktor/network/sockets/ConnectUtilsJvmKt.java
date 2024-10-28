package io.ktor.network.sockets;

import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline1;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import java.io.Closeable;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a)\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u001e\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000e*\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0000\u001a\u001c\u0010\u0012\u001a\n \u000f*\u0004\u0018\u00010\u00130\u0013*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"bind", "Lio/ktor/network/sockets/ServerSocket;", "selector", "Lio/ktor/network/selector/SelectorManager;", "localAddress", "Lio/ktor/network/sockets/SocketAddress;", "socketOptions", "Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "connect", "Lio/ktor/network/sockets/Socket;", "remoteAddress", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "(Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketAddress;Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openServerSocketChannelFor", "Ljava/nio/channels/ServerSocketChannel;", "kotlin.jvm.PlatformType", "Ljava/nio/channels/spi/SelectorProvider;", "address", "openSocketChannelFor", "Ljava/nio/channels/SocketChannel;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConnectUtilsJvm.kt */
public final class ConnectUtilsJvmKt {
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object connect(io.ktor.network.selector.SelectorManager r7, io.ktor.network.sockets.SocketAddress r8, io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions r9, kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Socket> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.network.sockets.ConnectUtilsJvmKt$connect$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.network.sockets.ConnectUtilsJvmKt$connect$1 r0 = (io.ktor.network.sockets.ConnectUtilsJvmKt$connect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.network.sockets.ConnectUtilsJvmKt$connect$1 r0 = new io.ktor.network.sockets.ConnectUtilsJvmKt$connect$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.lang.Object r7 = r0.L$1
            io.ktor.network.sockets.SocketImpl r7 = (io.ktor.network.sockets.SocketImpl) r7
            java.lang.Object r8 = r0.L$0
            java.io.Closeable r8 = (java.io.Closeable) r8
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0032 }
            goto L_0x007e
        L_0x0032:
            r7 = move-exception
            goto L_0x0081
        L_0x0034:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r10)
            java.nio.channels.spi.SelectorProvider r10 = r7.getProvider()
            java.nio.channels.SocketChannel r10 = openSocketChannelFor(r10, r8)
            java.io.Closeable r10 = (java.io.Closeable) r10
            r2 = r10
            java.nio.channels.SocketChannel r2 = (java.nio.channels.SocketChannel) r2     // Catch:{ all -> 0x007f }
            boolean r4 = r8 instanceof io.ktor.network.sockets.InetSocketAddress     // Catch:{ all -> 0x007f }
            java.lang.String r5 = "connect$lambda$2"
            if (r4 == 0) goto L_0x005e
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)     // Catch:{ all -> 0x007f }
            r4 = r2
            java.nio.channels.SelectableChannel r4 = (java.nio.channels.SelectableChannel) r4     // Catch:{ all -> 0x007f }
            r6 = r9
            io.ktor.network.sockets.SocketOptions r6 = (io.ktor.network.sockets.SocketOptions) r6     // Catch:{ all -> 0x007f }
            io.ktor.network.sockets.JavaSocketOptionsKt.assignOptions(r4, r6)     // Catch:{ all -> 0x007f }
        L_0x005e:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)     // Catch:{ all -> 0x007f }
            r4 = r2
            java.nio.channels.SelectableChannel r4 = (java.nio.channels.SelectableChannel) r4     // Catch:{ all -> 0x007f }
            io.ktor.network.sockets.JavaSocketOptionsKt.nonBlocking(r4)     // Catch:{ all -> 0x007f }
            io.ktor.network.sockets.SocketImpl r4 = new io.ktor.network.sockets.SocketImpl     // Catch:{ all -> 0x007f }
            r4.<init>(r2, r7, r9)     // Catch:{ all -> 0x007f }
            java.net.SocketAddress r7 = io.ktor.network.sockets.JavaSocketAddressUtilsKt.toJavaAddress(r8)     // Catch:{ all -> 0x007f }
            r0.L$0 = r10     // Catch:{ all -> 0x007f }
            r0.L$1 = r4     // Catch:{ all -> 0x007f }
            r0.label = r3     // Catch:{ all -> 0x007f }
            java.lang.Object r7 = r4.connect$ktor_network(r7, r0)     // Catch:{ all -> 0x007f }
            if (r7 != r1) goto L_0x007d
            return r1
        L_0x007d:
            r7 = r4
        L_0x007e:
            return r7
        L_0x007f:
            r7 = move-exception
            r8 = r10
        L_0x0081:
            r8.close()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.ConnectUtilsJvmKt.connect(io.ktor.network.selector.SelectorManager, io.ktor.network.sockets.SocketAddress, io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final SocketChannel openSocketChannelFor(SelectorProvider selectorProvider, SocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(selectorProvider, "<this>");
        Intrinsics.checkNotNullParameter(socketAddress, "address");
        if (socketAddress instanceof InetSocketAddress) {
            return selectorProvider.openSocketChannel();
        }
        if (socketAddress instanceof UnixSocketAddress) {
            Class m$2 = Chip$$ExternalSyntheticApiModelOutline1.m$2();
            StandardProtocolFamily m = Chip$$ExternalSyntheticApiModelOutline1.m("UNIX");
            Object invoke = SelectorProvider.class.getMethod("openSocketChannel", new Class[]{m$2}).invoke(selectorProvider, new Object[]{m});
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type java.nio.channels.SocketChannel");
            return (SocketChannel) invoke;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final ServerSocketChannel openServerSocketChannelFor(SelectorProvider selectorProvider, SocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(selectorProvider, "<this>");
        if (socketAddress == null) {
            return selectorProvider.openServerSocketChannel();
        }
        if (socketAddress instanceof InetSocketAddress) {
            return selectorProvider.openServerSocketChannel();
        }
        if (socketAddress instanceof UnixSocketAddress) {
            Class m$2 = Chip$$ExternalSyntheticApiModelOutline1.m$2();
            StandardProtocolFamily m = Chip$$ExternalSyntheticApiModelOutline1.m("UNIX");
            Object invoke = SelectorProvider.class.getMethod("openServerSocketChannel", new Class[]{m$2}).invoke(selectorProvider, new Object[]{m});
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type java.nio.channels.ServerSocketChannel");
            return (ServerSocketChannel) invoke;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final ServerSocket bind(SelectorManager selectorManager, SocketAddress socketAddress, SocketOptions.AcceptorOptions acceptorOptions) {
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        Intrinsics.checkNotNullParameter(acceptorOptions, "socketOptions");
        Closeable openServerSocketChannelFor = openServerSocketChannelFor(selectorManager.getProvider(), socketAddress);
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) openServerSocketChannelFor;
            if (socketAddress instanceof InetSocketAddress) {
                Intrinsics.checkNotNullExpressionValue(serverSocketChannel, "bind$lambda$5");
                JavaSocketOptionsKt.assignOptions(serverSocketChannel, acceptorOptions);
            }
            Intrinsics.checkNotNullExpressionValue(serverSocketChannel, "bind$lambda$5");
            JavaSocketOptionsKt.nonBlocking(serverSocketChannel);
            ServerSocketImpl serverSocketImpl = new ServerSocketImpl(serverSocketChannel, selectorManager);
            SocketAddress socketAddress2 = null;
            if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                ServerSocketChannel channel = serverSocketImpl.getChannel();
                if (socketAddress != null) {
                    socketAddress2 = JavaSocketAddressUtilsKt.toJavaAddress(socketAddress);
                }
                ServerSocketChannel unused = channel.bind(socketAddress2, acceptorOptions.getBacklogSize());
            } else {
                ServerSocket socket = serverSocketImpl.getChannel().socket();
                if (socketAddress != null) {
                    socketAddress2 = JavaSocketAddressUtilsKt.toJavaAddress(socketAddress);
                }
                socket.bind(socketAddress2, acceptorOptions.getBacklogSize());
            }
            return serverSocketImpl;
        } catch (Throwable th) {
            openServerSocketChannelFor.close();
            throw th;
        }
    }
}
