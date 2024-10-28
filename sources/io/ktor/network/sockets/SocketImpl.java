package io.ktor.network.sockets;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.pool.ObjectPool;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SocketChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B!\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u001b\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001aH\u0002R\u0016\u0010\u0005\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/network/sockets/SocketImpl;", "S", "Ljava/nio/channels/SocketChannel;", "Lio/ktor/network/sockets/NIOSocketImpl;", "Lio/ktor/network/sockets/Socket;", "channel", "selector", "Lio/ktor/network/selector/SelectorManager;", "socketOptions", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "(Ljava/nio/channels/SocketChannel;Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;)V", "getChannel", "()Ljava/nio/channels/SocketChannel;", "Ljava/nio/channels/SocketChannel;", "localAddress", "Lio/ktor/network/sockets/SocketAddress;", "getLocalAddress", "()Lio/ktor/network/sockets/SocketAddress;", "remoteAddress", "getRemoteAddress", "connect", "target", "Ljava/net/SocketAddress;", "connect$ktor_network", "(Ljava/net/SocketAddress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selfConnect", "", "wantConnect", "", "state", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SocketImpl.kt */
public final class SocketImpl<S extends SocketChannel> extends NIOSocketImpl<S> implements Socket {
    private final S channel;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SocketImpl(SocketChannel socketChannel, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(socketChannel, selectorManager, (i & 4) != 0 ? null : tCPClientSocketOptions);
    }

    public S getChannel() {
        return this.channel;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SocketImpl(S s, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        super((SelectableChannel) s, selectorManager, (ObjectPool<ByteBuffer>) null, tCPClientSocketOptions);
        Intrinsics.checkNotNullParameter(s, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        this.channel = s;
        if (!(!getChannel().isBlocking())) {
            throw new IllegalArgumentException("Channel need to be configured as non-blocking.".toString());
        }
    }

    public SocketAddress getLocalAddress() {
        SocketAddress socketAddress;
        SocketAddress socketAddress2;
        if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
            socketAddress = getChannel().getLocalAddress();
        } else {
            socketAddress = getChannel().socket().getLocalSocketAddress();
        }
        if (socketAddress != null && (socketAddress2 = JavaSocketAddressUtilsKt.toSocketAddress(socketAddress)) != null) {
            return socketAddress2;
        }
        throw new IllegalStateException("Channel is not yet bound");
    }

    public SocketAddress getRemoteAddress() {
        SocketAddress socketAddress;
        SocketAddress socketAddress2;
        if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
            socketAddress = getChannel().getRemoteAddress();
        } else {
            socketAddress = getChannel().socket().getRemoteSocketAddress();
        }
        if (socketAddress != null && (socketAddress2 = JavaSocketAddressUtilsKt.toSocketAddress(socketAddress)) != null) {
            return socketAddress2;
        }
        throw new IllegalStateException("Channel is not yet connected");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object connect$ktor_network(java.net.SocketAddress r7, kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Socket> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.network.sockets.SocketImpl$connect$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.network.sockets.SocketImpl$connect$1 r0 = (io.ktor.network.sockets.SocketImpl$connect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.network.sockets.SocketImpl$connect$1 r0 = new io.ktor.network.sockets.SocketImpl$connect$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 == r4) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            goto L_0x0032
        L_0x002a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0032:
            java.lang.Object r7 = r0.L$0
            io.ktor.network.sockets.SocketImpl r7 = (io.ktor.network.sockets.SocketImpl) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0060
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r8)
            java.nio.channels.SocketChannel r8 = r6.getChannel()
            boolean r7 = r8.connect(r7)
            if (r7 == 0) goto L_0x0048
            return r6
        L_0x0048:
            r6.wantConnect(r4)
            io.ktor.network.selector.SelectorManager r7 = r6.getSelector()
            r8 = r6
            io.ktor.network.selector.Selectable r8 = (io.ktor.network.selector.Selectable) r8
            io.ktor.network.selector.SelectInterest r2 = io.ktor.network.selector.SelectInterest.CONNECT
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r7.select(r8, r2, r0)
            if (r7 != r1) goto L_0x005f
            return r1
        L_0x005f:
            r7 = r6
        L_0x0060:
            java.nio.channels.SocketChannel r8 = r7.getChannel()
            boolean r8 = r8.finishConnect()
            if (r8 == 0) goto L_0x008f
            boolean r8 = r7.selfConnect()
            if (r8 == 0) goto L_0x008a
            boolean r8 = io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable()
            if (r8 == 0) goto L_0x007e
            java.nio.channels.SocketChannel r8 = r7.getChannel()
            r8.close()
            goto L_0x0060
        L_0x007e:
            java.nio.channels.SocketChannel r8 = r7.getChannel()
            java.net.Socket r8 = r8.socket()
            r8.close()
            goto L_0x0060
        L_0x008a:
            r8 = 0
            r7.wantConnect(r8)
            return r7
        L_0x008f:
            r7.wantConnect(r4)
            io.ktor.network.selector.SelectorManager r8 = r7.getSelector()
            r2 = r7
            io.ktor.network.selector.Selectable r2 = (io.ktor.network.selector.Selectable) r2
            io.ktor.network.selector.SelectInterest r5 = io.ktor.network.selector.SelectInterest.CONNECT
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r8.select(r2, r5, r0)
            if (r8 != r1) goto L_0x0060
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.SocketImpl.connect$ktor_network(java.net.SocketAddress, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ void wantConnect$default(SocketImpl socketImpl, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        socketImpl.wantConnect(z);
    }

    private final void wantConnect(boolean z) {
        interestOp(SelectInterest.CONNECT, z);
    }

    private final boolean selfConnect() {
        SocketAddress socketAddress;
        SocketAddress socketAddress2;
        InetAddress address;
        InetAddress address2;
        InetAddress address3;
        if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
            socketAddress = getChannel().getLocalAddress();
        } else {
            socketAddress = getChannel().socket().getLocalSocketAddress();
        }
        if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
            socketAddress2 = getChannel().getRemoteAddress();
        } else {
            socketAddress2 = getChannel().socket().getRemoteSocketAddress();
        }
        if (socketAddress == null || socketAddress2 == null) {
            throw new IllegalStateException("localAddress and remoteAddress should not be null.");
        }
        Integer num = null;
        InetSocketAddress inetSocketAddress = socketAddress instanceof InetSocketAddress ? (InetSocketAddress) socketAddress : null;
        InetSocketAddress inetSocketAddress2 = socketAddress2 instanceof InetSocketAddress ? (InetSocketAddress) socketAddress2 : null;
        String hostAddress = (inetSocketAddress == null || (address3 = inetSocketAddress.getAddress()) == null) ? null : address3.getHostAddress();
        String str = "";
        if (hostAddress == null) {
            hostAddress = str;
        }
        String hostAddress2 = (inetSocketAddress2 == null || (address2 = inetSocketAddress2.getAddress()) == null) ? null : address2.getHostAddress();
        if (hostAddress2 != null) {
            str = hostAddress2;
        }
        boolean isAnyLocalAddress = (inetSocketAddress2 == null || (address = inetSocketAddress2.getAddress()) == null) ? false : address.isAnyLocalAddress();
        Integer valueOf = inetSocketAddress != null ? Integer.valueOf(inetSocketAddress.getPort()) : null;
        if (inetSocketAddress2 != null) {
            num = Integer.valueOf(inetSocketAddress2.getPort());
        }
        if (!Intrinsics.areEqual((Object) valueOf, (Object) num)) {
            return false;
        }
        if (isAnyLocalAddress || Intrinsics.areEqual((Object) hostAddress, (Object) str)) {
            return true;
        }
        return false;
    }
}
