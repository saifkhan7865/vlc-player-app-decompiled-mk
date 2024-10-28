package io.ktor.network.tls;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.network.sockets.Socket;
import io.ktor.network.sockets.SocketAddress;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.ReaderJob;
import io.ktor.utils.io.ReaderScope;
import io.ktor.utils.io.WriterJob;
import io.ktor.utils.io.WriterScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B1\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001eH@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020#H\u0016J\t\u0010&\u001a\u00020\u0019H\u0001J\b\u0010'\u001a\u00020\u0019H\u0016R\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000fX\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u000fX\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u000e\u0010\b\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00020\u0015X\u0005¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"Lio/ktor/network/tls/TLSSocket;", "Lkotlinx/coroutines/CoroutineScope;", "Lio/ktor/network/sockets/Socket;", "input", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/network/tls/TLSRecord;", "output", "Lkotlinx/coroutines/channels/SendChannel;", "socket", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lio/ktor/network/sockets/Socket;Lkotlin/coroutines/CoroutineContext;)V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "localAddress", "Lio/ktor/network/sockets/SocketAddress;", "getLocalAddress", "()Lio/ktor/network/sockets/SocketAddress;", "remoteAddress", "getRemoteAddress", "socketContext", "Lkotlinx/coroutines/Job;", "getSocketContext", "()Lkotlinx/coroutines/Job;", "appDataInputLoop", "", "pipe", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "appDataOutputLoop", "Lio/ktor/utils/io/ByteReadChannel;", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "attachForReading", "Lio/ktor/utils/io/WriterJob;", "channel", "Lio/ktor/utils/io/ByteChannel;", "attachForWriting", "Lio/ktor/utils/io/ReaderJob;", "close", "dispose", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSClientSessionJvm.kt */
final class TLSSocket implements CoroutineScope, Socket {
    private final CoroutineContext coroutineContext;
    private final ReceiveChannel<TLSRecord> input;
    private final SendChannel<TLSRecord> output;
    private final Socket socket;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TLSClientSessionJvm.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TLSRecordType.values().length];
            try {
                iArr[TLSRecordType.ApplicationData.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public void close() {
        this.socket.close();
    }

    public SocketAddress getLocalAddress() {
        return this.socket.getLocalAddress();
    }

    public SocketAddress getRemoteAddress() {
        return this.socket.getRemoteAddress();
    }

    public Job getSocketContext() {
        return this.socket.getSocketContext();
    }

    public TLSSocket(ReceiveChannel<TLSRecord> receiveChannel, SendChannel<? super TLSRecord> sendChannel, Socket socket2, CoroutineContext coroutineContext2) {
        Intrinsics.checkNotNullParameter(receiveChannel, TvContractCompat.PARAM_INPUT);
        Intrinsics.checkNotNullParameter(sendChannel, "output");
        Intrinsics.checkNotNullParameter(socket2, "socket");
        Intrinsics.checkNotNullParameter(coroutineContext2, "coroutineContext");
        this.input = receiveChannel;
        this.output = sendChannel;
        this.socket = socket2;
        this.coroutineContext = coroutineContext2;
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public WriterJob attachForReading(ByteChannel byteChannel) {
        Intrinsics.checkNotNullParameter(byteChannel, TvContractCompat.PARAM_CHANNEL);
        return CoroutinesKt.writer((CoroutineScope) this, getCoroutineContext().plus(new CoroutineName("cio-tls-input-loop")), byteChannel, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new TLSSocket$attachForReading$1(this, (Continuation<? super TLSSocket$attachForReading$1>) null));
    }

    public ReaderJob attachForWriting(ByteChannel byteChannel) {
        Intrinsics.checkNotNullParameter(byteChannel, TvContractCompat.PARAM_CHANNEL);
        return CoroutinesKt.reader((CoroutineScope) this, getCoroutineContext().plus(new CoroutineName("cio-tls-output-loop")), byteChannel, (Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object>) new TLSSocket$attachForWriting$1(this, (Continuation<? super TLSSocket$attachForWriting$1>) null));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00eb, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ef, code lost:
        throw r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f0, code lost:
        r14 = r6;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:45:0x00e0, B:50:0x00ea] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0080 A[Catch:{ all -> 0x0057 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object appDataInputLoop(io.ktor.utils.io.ByteWriteChannel r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            r13 = this;
            boolean r0 = r15 instanceof io.ktor.network.tls.TLSSocket$appDataInputLoop$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            io.ktor.network.tls.TLSSocket$appDataInputLoop$1 r0 = (io.ktor.network.tls.TLSSocket$appDataInputLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            io.ktor.network.tls.TLSSocket$appDataInputLoop$1 r0 = new io.ktor.network.tls.TLSSocket$appDataInputLoop$1
            r0.<init>(r13, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x005a
            if (r2 == r4) goto L_0x0046
            if (r2 != r3) goto L_0x003e
            java.lang.Object r14 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r14 = (kotlinx.coroutines.channels.ChannelIterator) r14
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x0057 }
            r15 = r14
            r14 = r6
            r6 = r5
            goto L_0x00b2
        L_0x003e:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L_0x0046:
            java.lang.Object r14 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r14 = (kotlinx.coroutines.channels.ChannelIterator) r14
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x0057 }
            r7 = r5
            goto L_0x0078
        L_0x0057:
            r14 = move-exception
            goto L_0x00ea
        L_0x005a:
            kotlin.ResultKt.throwOnFailure(r15)
            kotlinx.coroutines.channels.ReceiveChannel<io.ktor.network.tls.TLSRecord> r2 = r13.input     // Catch:{ all -> 0x00f1 }
            kotlinx.coroutines.channels.ChannelIterator r15 = r2.iterator()     // Catch:{ all -> 0x00e7 }
            r6 = r5
        L_0x0064:
            r0.L$0 = r14     // Catch:{ all -> 0x00e7 }
            r0.L$1 = r2     // Catch:{ all -> 0x00e7 }
            r0.L$2 = r15     // Catch:{ all -> 0x00e7 }
            r0.label = r4     // Catch:{ all -> 0x00e7 }
            java.lang.Object r7 = r15.hasNext(r0)     // Catch:{ all -> 0x00e7 }
            if (r7 != r1) goto L_0x0073
            return r1
        L_0x0073:
            r12 = r6
            r6 = r14
            r14 = r15
            r15 = r7
            r7 = r12
        L_0x0078:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x0057 }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x0057 }
            if (r15 == 0) goto L_0x00de
            java.lang.Object r15 = r14.next()     // Catch:{ all -> 0x0057 }
            io.ktor.network.tls.TLSRecord r15 = (io.ktor.network.tls.TLSRecord) r15     // Catch:{ all -> 0x0057 }
            io.ktor.utils.io.core.ByteReadPacket r8 = r15.getPacket()     // Catch:{ all -> 0x0057 }
            long r8 = r8.getRemaining()     // Catch:{ all -> 0x0057 }
            io.ktor.network.tls.TLSRecordType r10 = r15.getType()     // Catch:{ all -> 0x0057 }
            int[] r11 = io.ktor.network.tls.TLSSocket.WhenMappings.$EnumSwitchMapping$0     // Catch:{ all -> 0x0057 }
            int r10 = r10.ordinal()     // Catch:{ all -> 0x0057 }
            r10 = r11[r10]     // Catch:{ all -> 0x0057 }
            if (r10 != r4) goto L_0x00b6
            io.ktor.utils.io.core.ByteReadPacket r15 = r15.getPacket()     // Catch:{ all -> 0x0057 }
            r0.L$0 = r6     // Catch:{ all -> 0x0057 }
            r0.L$1 = r2     // Catch:{ all -> 0x0057 }
            r0.L$2 = r14     // Catch:{ all -> 0x0057 }
            r0.label = r3     // Catch:{ all -> 0x0057 }
            java.lang.Object r15 = r6.writePacket(r15, r0)     // Catch:{ all -> 0x0057 }
            if (r15 != r1) goto L_0x00af
            return r1
        L_0x00af:
            r15 = r14
            r14 = r6
            r6 = r7
        L_0x00b2:
            r14.flush()     // Catch:{ all -> 0x00e7 }
            goto L_0x0064
        L_0x00b6:
            io.ktor.network.tls.TLSException r14 = new io.ktor.network.tls.TLSException     // Catch:{ all -> 0x0057 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0057 }
            r0.<init>()     // Catch:{ all -> 0x0057 }
            java.lang.String r1 = "Unexpected record "
            r0.append(r1)     // Catch:{ all -> 0x0057 }
            io.ktor.network.tls.TLSRecordType r15 = r15.getType()     // Catch:{ all -> 0x0057 }
            r0.append(r15)     // Catch:{ all -> 0x0057 }
            java.lang.String r15 = " ("
            r0.append(r15)     // Catch:{ all -> 0x0057 }
            r0.append(r8)     // Catch:{ all -> 0x0057 }
            java.lang.String r15 = " bytes)"
            r0.append(r15)     // Catch:{ all -> 0x0057 }
            java.lang.String r15 = r0.toString()     // Catch:{ all -> 0x0057 }
            r14.<init>(r15, r5, r3, r5)     // Catch:{ all -> 0x0057 }
            throw r14     // Catch:{ all -> 0x0057 }
        L_0x00de:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0057 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r7)     // Catch:{ all -> 0x00f0 }
            io.ktor.utils.io.ByteWriteChannelKt.close(r6)
            goto L_0x00f4
        L_0x00e7:
            r15 = move-exception
            r6 = r14
            r14 = r15
        L_0x00ea:
            throw r14     // Catch:{ all -> 0x00eb }
        L_0x00eb:
            r15 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r2, r14)     // Catch:{ all -> 0x00f0 }
            throw r15     // Catch:{ all -> 0x00f0 }
        L_0x00f0:
            r14 = r6
        L_0x00f1:
            io.ktor.utils.io.ByteWriteChannelKt.close(r14)
        L_0x00f4:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSSocket.appDataInputLoop(io.ktor.utils.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0097 A[Catch:{ all -> 0x00e9, ClosedSendChannelException -> 0x00f9, all -> 0x006b, all -> 0x0108 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0098 A[Catch:{ all -> 0x00e9, ClosedSendChannelException -> 0x00f9, all -> 0x006b, all -> 0x0108 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ab A[Catch:{ all -> 0x00e9, ClosedSendChannelException -> 0x00f9, all -> 0x006b, all -> 0x0108 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object appDataOutputLoop(io.ktor.utils.io.ByteReadChannel r22, kotlin.coroutines.Continuation<? super kotlin.Unit> r23) {
        /*
            r21 = this;
            r0 = r23
            boolean r1 = r0 instanceof io.ktor.network.tls.TLSSocket$appDataOutputLoop$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            io.ktor.network.tls.TLSSocket$appDataOutputLoop$1 r1 = (io.ktor.network.tls.TLSSocket$appDataOutputLoop$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r21
            goto L_0x001f
        L_0x0018:
            io.ktor.network.tls.TLSSocket$appDataOutputLoop$1 r1 = new io.ktor.network.tls.TLSSocket$appDataOutputLoop$1
            r2 = r21
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 2
            r6 = 1
            r7 = 0
            if (r4 == 0) goto L_0x006e
            if (r4 == r6) goto L_0x0055
            if (r4 != r5) goto L_0x004d
            java.lang.Object r4 = r1.L$4
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            java.lang.Object r8 = r1.L$3
            java.lang.Object r9 = r1.L$2
            io.ktor.utils.io.pool.ObjectPool r9 = (io.ktor.utils.io.pool.ObjectPool) r9
            java.lang.Object r10 = r1.L$1
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            java.lang.Object r11 = r1.L$0
            io.ktor.network.tls.TLSSocket r11 = (io.ktor.network.tls.TLSSocket) r11
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
        L_0x0045:
            r0 = r10
            r19 = r4
            r4 = r1
            r1 = r19
            goto L_0x00e7
        L_0x004d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0055:
            java.lang.Object r4 = r1.L$4
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            java.lang.Object r8 = r1.L$3
            java.lang.Object r9 = r1.L$2
            io.ktor.utils.io.pool.ObjectPool r9 = (io.ktor.utils.io.pool.ObjectPool) r9
            java.lang.Object r10 = r1.L$1
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            java.lang.Object r11 = r1.L$0
            io.ktor.network.tls.TLSSocket r11 = (io.ktor.network.tls.TLSSocket) r11
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            goto L_0x00a2
        L_0x006b:
            r0 = move-exception
            goto L_0x00f2
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.utils.io.pool.ObjectPool r4 = io.ktor.network.util.PoolsKt.getDefaultByteBufferPool()
            java.lang.Object r8 = r4.borrow()
            r0 = r8
            java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0     // Catch:{ all -> 0x010b }
            r11 = r2
            r9 = r4
            r4 = r1
            r1 = r0
            r0 = r22
        L_0x0082:
            r1.clear()     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r4.L$0 = r11     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r4.L$1 = r0     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r4.L$2 = r9     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r4.L$3 = r8     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r4.L$4 = r1     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r4.label = r6     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            java.lang.Object r10 = r0.readAvailable((java.nio.ByteBuffer) r1, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r4)     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            if (r10 != r3) goto L_0x0098
            return r3
        L_0x0098:
            r19 = r10
            r10 = r0
            r0 = r19
            r20 = r4
            r4 = r1
            r1 = r20
        L_0x00a2:
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            int r0 = r0.intValue()     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r12 = -1
            if (r0 == r12) goto L_0x00ee
            r4.flip()     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            kotlinx.coroutines.channels.SendChannel<io.ktor.network.tls.TLSRecord> r0 = r11.output     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            io.ktor.network.tls.TLSRecordType r13 = io.ktor.network.tls.TLSRecordType.ApplicationData     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            io.ktor.utils.io.core.BytePacketBuilder r12 = new io.ktor.utils.io.core.BytePacketBuilder     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r12.<init>(r7, r6, r7)     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r14 = r12
            io.ktor.utils.io.core.Output r14 = (io.ktor.utils.io.core.Output) r14     // Catch:{ all -> 0x00e9 }
            io.ktor.utils.io.core.OutputArraysJVMKt.writeFully(r14, r4)     // Catch:{ all -> 0x00e9 }
            io.ktor.utils.io.core.ByteReadPacket r15 = r12.build()     // Catch:{ all -> 0x00e9 }
            io.ktor.network.tls.TLSRecord r14 = new io.ktor.network.tls.TLSRecord     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r16 = 0
            r17 = 2
            r18 = 0
            r12 = r14
            r6 = r14
            r14 = r16
            r16 = r17
            r17 = r18
            r12.<init>(r13, r14, r15, r16, r17)     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r1.L$0 = r11     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r1.L$1 = r10     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r1.L$2 = r9     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r1.L$3 = r8     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r1.L$4 = r4     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            r1.label = r5     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            java.lang.Object r0 = r0.send(r6, r1)     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            if (r0 != r3) goto L_0x0045
            return r3
        L_0x00e7:
            r6 = 1
            goto L_0x0082
        L_0x00e9:
            r0 = move-exception
            r12.release()     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
            throw r0     // Catch:{ ClosedSendChannelException -> 0x00f9, all -> 0x006b }
        L_0x00ee:
            kotlinx.coroutines.channels.SendChannel<io.ktor.network.tls.TLSRecord> r0 = r11.output     // Catch:{ all -> 0x0108 }
            r1 = 1
            goto L_0x00fc
        L_0x00f2:
            kotlinx.coroutines.channels.SendChannel<io.ktor.network.tls.TLSRecord> r1 = r11.output     // Catch:{ all -> 0x0108 }
            r3 = 1
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r1, r7, r3, r7)     // Catch:{ all -> 0x0108 }
            throw r0     // Catch:{ all -> 0x0108 }
        L_0x00f9:
            kotlinx.coroutines.channels.SendChannel<io.ktor.network.tls.TLSRecord> r0 = r11.output     // Catch:{ all -> 0x0108 }
            r1 = 1
        L_0x00fc:
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r7, r1, r7)     // Catch:{ all -> 0x0108 }
            r4 = r9
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x010b }
            r4.recycle(r8)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0108:
            r0 = move-exception
            r4 = r9
            goto L_0x010c
        L_0x010b:
            r0 = move-exception
        L_0x010c:
            r4.recycle(r8)
            goto L_0x0111
        L_0x0110:
            throw r0
        L_0x0111:
            goto L_0x0110
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSSocket.appDataOutputLoop(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void dispose() {
        this.socket.dispose();
    }
}
