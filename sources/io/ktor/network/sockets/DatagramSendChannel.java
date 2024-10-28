package io.ktor.network.sockets;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.network.util.PoolsKt;
import io.ktor.utils.io.core.ByteBuffersKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00150-B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJ4\u0010\u0013\u001a\u00020\f2#\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\f0\u000fH\u0017¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0015H@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J#\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ&\u0010\"\u001a\b\u0012\u0004\u0012\u00020\f0\u001f2\u0006\u0010\u0016\u001a\u00020\u0015H\u0016ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b \u0010!R\u0017\u0010\u0002\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0002\u0010#\u001a\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\t8VX\u0004¢\u0006\f\u0012\u0004\b(\u0010\u000e\u001a\u0004\b&\u0010'R\u0014\u0010*\u001a\u00020)8\u0002X\u0004¢\u0006\u0006\n\u0004\b*\u0010+R&\u00100\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150-0,8VX\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u00101\u001a\u0004\b2\u00103\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00064"}, d2 = {"Lio/ktor/network/sockets/DatagramSendChannel;", "Ljava/nio/channels/DatagramChannel;", "channel", "Lio/ktor/network/sockets/DatagramSocketImpl;", "socket", "<init>", "(Ljava/nio/channels/DatagramChannel;Lio/ktor/network/sockets/DatagramSocketImpl;)V", "", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "", "closeAndCheckHandler", "()V", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "handler", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)V", "Lio/ktor/network/sockets/Datagram;", "element", "send", "(Lio/ktor/network/sockets/Datagram;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/nio/ByteBuffer;", "buffer", "Lio/ktor/network/sockets/SocketAddress;", "address", "sendSuspend", "(Ljava/nio/ByteBuffer;Lio/ktor/network/sockets/SocketAddress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Lio/ktor/network/sockets/Datagram;)Ljava/lang/Object;", "trySend", "Ljava/nio/channels/DatagramChannel;", "getChannel", "()Ljava/nio/channels/DatagramChannel;", "isClosedForSend", "()Z", "isClosedForSend$annotations", "Lkotlinx/coroutines/sync/Mutex;", "lock", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lio/ktor/network/sockets/DatagramSocketImpl;", "getSocket", "()Lio/ktor/network/sockets/DatagramSocketImpl;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DatagramSendChannel.kt */
public final class DatagramSendChannel implements SendChannel<Datagram> {
    private static final /* synthetic */ AtomicIntegerFieldUpdater closed$FU;
    private static final /* synthetic */ AtomicReferenceFieldUpdater onCloseHandler$FU;
    private final DatagramChannel channel;
    private volatile /* synthetic */ int closed = 0;
    private volatile /* synthetic */ Object closedCause = null;
    private final Mutex lock = MutexKt.Mutex$default(false, 1, (Object) null);
    private volatile /* synthetic */ Object onCloseHandler = null;
    private final DatagramSocketImpl socket;

    static {
        Class<DatagramSendChannel> cls = DatagramSendChannel.class;
        onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "onCloseHandler");
        closed$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "closed");
    }

    public static /* synthetic */ void isClosedForSend$annotations() {
    }

    public DatagramSendChannel(DatagramChannel datagramChannel, DatagramSocketImpl datagramSocketImpl) {
        Intrinsics.checkNotNullParameter(datagramChannel, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(datagramSocketImpl, "socket");
        this.channel = datagramChannel;
        this.socket = datagramSocketImpl;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(Datagram datagram) {
        return SendChannel.DefaultImpls.offer(this, datagram);
    }

    public final DatagramChannel getChannel() {
        return this.channel;
    }

    public final DatagramSocketImpl getSocket() {
        return this.socket;
    }

    public boolean isClosedForSend() {
        return this.socket.isClosed();
    }

    public boolean close(Throwable th) {
        if (!closed$FU.compareAndSet(this, 0, 1)) {
            return false;
        }
        this.closedCause = th;
        if (!this.socket.isClosed()) {
            this.socket.close();
        }
        closeAndCheckHandler();
        return true;
    }

    /* renamed from: trySend-JP2dKIU  reason: not valid java name */
    public Object m141trySendJP2dKIU(Datagram datagram) {
        ObjectPool<ByteBuffer> defaultDatagramByteBufferPool;
        ByteBuffer borrow;
        Intrinsics.checkNotNullParameter(datagram, "element");
        if (!Mutex.DefaultImpls.tryLock$default(this.lock, (Object) null, 1, (Object) null)) {
            return ChannelResult.Companion.m2350failurePtdJZtk();
        }
        try {
            defaultDatagramByteBufferPool = PoolsKt.getDefaultDatagramByteBufferPool();
            borrow = defaultDatagramByteBufferPool.borrow();
            ByteBuffer byteBuffer = borrow;
            ByteBuffersKt.readAvailable(datagram.getPacket().copy(), byteBuffer);
            boolean z = this.channel.send(byteBuffer, JavaSocketAddressUtilsKt.toJavaAddress(datagram.getAddress())) == 0;
            Unit unit = Unit.INSTANCE;
            defaultDatagramByteBufferPool.recycle(borrow);
            Mutex.DefaultImpls.unlock$default(this.lock, (Object) null, 1, (Object) null);
            if (z) {
                datagram.getPacket().release();
            }
            return ChannelResult.Companion.m2351successJP2dKIU(Unit.INSTANCE);
        } catch (Throwable th) {
            Mutex.DefaultImpls.unlock$default(this.lock, (Object) null, 1, (Object) null);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object send(io.ktor.network.sockets.Datagram r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.network.sockets.DatagramSendChannel$send$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.network.sockets.DatagramSendChannel$send$1 r0 = (io.ktor.network.sockets.DatagramSendChannel$send$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.network.sockets.DatagramSendChannel$send$1 r0 = new io.ktor.network.sockets.DatagramSendChannel$send$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x004e
            if (r2 == r4) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.sync.Mutex r9 = (kotlinx.coroutines.sync.Mutex) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0032 }
            goto L_0x0080
        L_0x0032:
            r10 = move-exception
            goto L_0x008c
        L_0x0034:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003c:
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.sync.Mutex r9 = (kotlinx.coroutines.sync.Mutex) r9
            java.lang.Object r2 = r0.L$1
            io.ktor.network.sockets.Datagram r2 = (io.ktor.network.sockets.Datagram) r2
            java.lang.Object r4 = r0.L$0
            io.ktor.network.sockets.DatagramSendChannel r4 = (io.ktor.network.sockets.DatagramSendChannel) r4
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r9
            r9 = r2
            goto L_0x0063
        L_0x004e:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.sync.Mutex r10 = r8.lock
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r4
            java.lang.Object r2 = r10.lock(r5, r0)
            if (r2 != r1) goto L_0x0062
            return r1
        L_0x0062:
            r4 = r8
        L_0x0063:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()     // Catch:{ all -> 0x0088 }
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2     // Catch:{ all -> 0x0088 }
            io.ktor.network.sockets.DatagramSendChannel$send$2$1 r6 = new io.ktor.network.sockets.DatagramSendChannel$send$2$1     // Catch:{ all -> 0x0088 }
            r6.<init>(r9, r4, r5)     // Catch:{ all -> 0x0088 }
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6     // Catch:{ all -> 0x0088 }
            r0.L$0 = r10     // Catch:{ all -> 0x0088 }
            r0.L$1 = r5     // Catch:{ all -> 0x0088 }
            r0.L$2 = r5     // Catch:{ all -> 0x0088 }
            r0.label = r3     // Catch:{ all -> 0x0088 }
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)     // Catch:{ all -> 0x0088 }
            if (r9 != r1) goto L_0x007f
            return r1
        L_0x007f:
            r9 = r10
        L_0x0080:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0032 }
            r9.unlock(r5)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x0088:
            r9 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
        L_0x008c:
            r9.unlock(r5)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSendChannel.send(io.ktor.network.sockets.Datagram, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0066 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0073  */
    public final java.lang.Object sendSuspend(java.nio.ByteBuffer r8, io.ktor.network.sockets.SocketAddress r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof io.ktor.network.sockets.DatagramSendChannel$sendSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.network.sockets.DatagramSendChannel$sendSuspend$1 r0 = (io.ktor.network.sockets.DatagramSendChannel$sendSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.network.sockets.DatagramSendChannel$sendSuspend$1 r0 = new io.ktor.network.sockets.DatagramSendChannel$sendSuspend$1
            r0.<init>(r7, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r8 = r0.L$2
            io.ktor.network.sockets.SocketAddress r8 = (io.ktor.network.sockets.SocketAddress) r8
            java.lang.Object r9 = r0.L$1
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.network.sockets.DatagramSendChannel r2 = (io.ktor.network.sockets.DatagramSendChannel) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r6 = r9
            r9 = r8
            r8 = r6
            goto L_0x0067
        L_0x0039:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r7
        L_0x0045:
            io.ktor.network.sockets.DatagramSocketImpl r10 = r2.socket
            io.ktor.network.selector.SelectInterest r4 = io.ktor.network.selector.SelectInterest.WRITE
            r10.interestOp(r4, r3)
            io.ktor.network.sockets.DatagramSocketImpl r10 = r2.socket
            io.ktor.network.selector.SelectorManager r10 = r10.getSelector()
            io.ktor.network.sockets.DatagramSocketImpl r4 = r2.socket
            io.ktor.network.selector.Selectable r4 = (io.ktor.network.selector.Selectable) r4
            io.ktor.network.selector.SelectInterest r5 = io.ktor.network.selector.SelectInterest.WRITE
            r0.L$0 = r2
            r0.L$1 = r8
            r0.L$2 = r9
            r0.label = r3
            java.lang.Object r10 = r10.select(r4, r5, r0)
            if (r10 != r1) goto L_0x0067
            return r1
        L_0x0067:
            java.nio.channels.DatagramChannel r10 = r2.channel
            java.net.SocketAddress r4 = io.ktor.network.sockets.JavaSocketAddressUtilsKt.toJavaAddress(r9)
            int r10 = r10.send(r8, r4)
            if (r10 == 0) goto L_0x0045
            io.ktor.network.sockets.DatagramSocketImpl r8 = r2.socket
            io.ktor.network.selector.SelectInterest r9 = io.ktor.network.selector.SelectInterest.WRITE
            r10 = 0
            r8.interestOp(r9, r10)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSendChannel.sendSuspend(java.nio.ByteBuffer, io.ktor.network.sockets.SocketAddress, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public SelectClause2<Datagram, SendChannel<Datagram>> getOnSend() {
        throw new NotImplementedError("An operation is not implemented: [DatagramSendChannel] doesn't support [onSend] select clause");
    }

    public void invokeOnClose(Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "handler");
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = onCloseHandler$FU;
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, (Object) null, function1)) {
            if (this.onCloseHandler != DatagramSendChannelKt.CLOSED) {
                DatagramSendChannelKt.failInvokeOnClose((Function1) this.onCloseHandler);
            } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, DatagramSendChannelKt.CLOSED, DatagramSendChannelKt.CLOSED_INVOKED)) {
                function1.invoke(this.closedCause);
            } else {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
    }

    private final void closeAndCheckHandler() {
        do {
            Function1 function1 = (Function1) this.onCloseHandler;
            if (function1 == DatagramSendChannelKt.CLOSED_INVOKED) {
                return;
            }
            if (function1 != null) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(onCloseHandler$FU, this, function1, DatagramSendChannelKt.CLOSED_INVOKED)) {
                    function1.invoke(this.closedCause);
                    return;
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(onCloseHandler$FU, this, (Object) null, DatagramSendChannelKt.CLOSED));
    }
}
