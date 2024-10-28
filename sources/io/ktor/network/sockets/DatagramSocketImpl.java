package io.ktor.network.sockets;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.BoundDatagramSocket;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.network.util.PoolsKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.OutputArraysJVMKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0011\u0010 \u001a\u00020\rH@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0019\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020$HPø\u0001\u0000¢\u0006\u0002\u0010%R\u0014\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00158VX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u0015X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lio/ktor/network/sockets/DatagramSocketImpl;", "Lio/ktor/network/sockets/BoundDatagramSocket;", "Lio/ktor/network/sockets/ConnectedDatagramSocket;", "Lio/ktor/network/sockets/NIOSocketImpl;", "Ljava/nio/channels/DatagramChannel;", "channel", "selector", "Lio/ktor/network/selector/SelectorManager;", "(Ljava/nio/channels/DatagramChannel;Lio/ktor/network/selector/SelectorManager;)V", "getChannel", "()Ljava/nio/channels/DatagramChannel;", "incoming", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/network/sockets/Datagram;", "getIncoming", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "localAddress", "Lio/ktor/network/sockets/SocketAddress;", "getLocalAddress", "()Lio/ktor/network/sockets/SocketAddress;", "outgoing", "Lkotlinx/coroutines/channels/SendChannel;", "getOutgoing", "()Lkotlinx/coroutines/channels/SendChannel;", "receiver", "getReceiver$annotations", "()V", "remoteAddress", "getRemoteAddress", "sender", "close", "", "receiveImpl", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveSuspend", "buffer", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DatagramSocketImpl.kt */
public final class DatagramSocketImpl extends NIOSocketImpl<DatagramChannel> implements BoundDatagramSocket, ConnectedDatagramSocket {
    private final DatagramChannel channel;
    private final ReceiveChannel<Datagram> receiver = ProduceKt.produce$default(this, Dispatchers.getIO(), 0, new DatagramSocketImpl$receiver$1(this, (Continuation<? super DatagramSocketImpl$receiver$1>) null), 2, (Object) null);
    private final SendChannel<Datagram> sender = new DatagramSendChannel(getChannel(), this);

    private static /* synthetic */ void getReceiver$annotations() {
    }

    public Object receive(Continuation<? super Datagram> continuation) {
        return BoundDatagramSocket.DefaultImpls.receive(this, continuation);
    }

    public Object send(Datagram datagram, Continuation<? super Unit> continuation) {
        return BoundDatagramSocket.DefaultImpls.send(this, datagram, continuation);
    }

    public DatagramChannel getChannel() {
        return this.channel;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DatagramSocketImpl(DatagramChannel datagramChannel, SelectorManager selectorManager) {
        super(datagramChannel, selectorManager, PoolsKt.getDefaultDatagramByteBufferPool(), (SocketOptions.TCPClientSocketOptions) null, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(datagramChannel, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        this.channel = datagramChannel;
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

    public ReceiveChannel<Datagram> getIncoming() {
        return this.receiver;
    }

    public SendChannel<Datagram> getOutgoing() {
        return this.sender;
    }

    public void close() {
        ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) this.receiver, (CancellationException) null, 1, (Object) null);
        super.close();
        SendChannel.DefaultImpls.close$default(this.sender, (Throwable) null, 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public final Object receiveImpl(Continuation<? super Datagram> continuation) {
        ByteBuffer borrow = PoolsKt.getDefaultDatagramByteBufferPool().borrow();
        try {
            SocketAddress receive = getChannel().receive(borrow);
            if (receive == null) {
                return receiveSuspend(borrow, continuation);
            }
            interestOp(SelectInterest.READ, false);
            borrow.flip();
            BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
            try {
                OutputArraysJVMKt.writeFully(bytePacketBuilder, borrow);
                Datagram datagram = new Datagram(bytePacketBuilder.build(), JavaSocketAddressUtilsKt.toSocketAddress(receive));
                return datagram;
            } catch (Throwable th) {
                bytePacketBuilder.release();
                throw th;
            }
        } finally {
            PoolsKt.getDefaultDatagramByteBufferPool().recycle(borrow);
        }
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0058 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0064  */
    public final java.lang.Object receiveSuspend(java.nio.ByteBuffer r7, kotlin.coroutines.Continuation<? super io.ktor.network.sockets.Datagram> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1 r0 = (io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1 r0 = new io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.network.sockets.DatagramSocketImpl r2 = (io.ktor.network.sockets.DatagramSocketImpl) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0059
        L_0x0032:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
        L_0x003e:
            io.ktor.network.selector.SelectInterest r8 = io.ktor.network.selector.SelectInterest.READ
            r2.interestOp(r8, r3)
            io.ktor.network.selector.SelectorManager r8 = r2.getSelector()
            r4 = r2
            io.ktor.network.selector.Selectable r4 = (io.ktor.network.selector.Selectable) r4
            io.ktor.network.selector.SelectInterest r5 = io.ktor.network.selector.SelectInterest.READ
            r0.L$0 = r2
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r8.select(r4, r5, r0)
            if (r8 != r1) goto L_0x0059
            return r1
        L_0x0059:
            java.nio.channels.DatagramChannel r8 = r2.getChannel()     // Catch:{ all -> 0x0093 }
            java.net.SocketAddress r8 = r8.receive(r7)     // Catch:{ all -> 0x0093 }
            if (r8 != 0) goto L_0x0064
            goto L_0x003e
        L_0x0064:
            io.ktor.network.selector.SelectInterest r0 = io.ktor.network.selector.SelectInterest.READ
            r1 = 0
            r2.interestOp(r0, r1)
            r7.flip()
            io.ktor.utils.io.core.BytePacketBuilder r0 = new io.ktor.utils.io.core.BytePacketBuilder
            r1 = 0
            r0.<init>(r1, r3, r1)
            r1 = r0
            io.ktor.utils.io.core.Output r1 = (io.ktor.utils.io.core.Output) r1     // Catch:{ all -> 0x008e }
            io.ktor.utils.io.core.OutputArraysJVMKt.writeFully(r1, r7)     // Catch:{ all -> 0x008e }
            io.ktor.utils.io.core.ByteReadPacket r0 = r0.build()     // Catch:{ all -> 0x008e }
            io.ktor.network.sockets.SocketAddress r8 = io.ktor.network.sockets.JavaSocketAddressUtilsKt.toSocketAddress(r8)
            io.ktor.network.sockets.Datagram r1 = new io.ktor.network.sockets.Datagram
            r1.<init>(r0, r8)
            io.ktor.utils.io.pool.ObjectPool r8 = io.ktor.network.util.PoolsKt.getDefaultDatagramByteBufferPool()
            r8.recycle(r7)
            return r1
        L_0x008e:
            r7 = move-exception
            r0.release()
            throw r7
        L_0x0093:
            r8 = move-exception
            io.ktor.utils.io.pool.ObjectPool r0 = io.ktor.network.util.PoolsKt.getDefaultDatagramByteBufferPool()
            r0.recycle(r7)
            goto L_0x009d
        L_0x009c:
            throw r8
        L_0x009d:
            goto L_0x009c
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSocketImpl.receiveSuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
