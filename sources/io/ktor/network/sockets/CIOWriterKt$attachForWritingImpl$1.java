package io.ktor.network.sockets;

import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ReaderScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/ReaderScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingImpl$1", f = "CIOWriter.kt", i = {0, 1, 1, 2, 2, 2}, l = {39, 52, 52}, m = "invokeSuspend", n = {"timeout", "timeout", "rc", "timeout", "rc", "$this$withTimeout$iv"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2"})
/* compiled from: CIOWriter.kt */
final class CIOWriterKt$attachForWritingImpl$1 extends SuspendLambda implements Function2<ReaderScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteBuffer $buffer;
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ WritableByteChannel $nioChannel;
    final /* synthetic */ ObjectPool<ByteBuffer> $pool;
    final /* synthetic */ Selectable $selectable;
    final /* synthetic */ SelectorManager $selector;
    final /* synthetic */ SocketOptions.TCPClientSocketOptions $socketOptions;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOWriterKt$attachForWritingImpl$1(SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, ByteBuffer byteBuffer, ByteChannel byteChannel, Selectable selectable, ObjectPool<ByteBuffer> objectPool, WritableByteChannel writableByteChannel, SelectorManager selectorManager, Continuation<? super CIOWriterKt$attachForWritingImpl$1> continuation) {
        super(2, continuation);
        this.$socketOptions = tCPClientSocketOptions;
        this.$buffer = byteBuffer;
        this.$channel = byteChannel;
        this.$selectable = selectable;
        this.$pool = objectPool;
        this.$nioChannel = writableByteChannel;
        this.$selector = selectorManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CIOWriterKt$attachForWritingImpl$1 cIOWriterKt$attachForWritingImpl$1 = new CIOWriterKt$attachForWritingImpl$1(this.$socketOptions, this.$buffer, this.$channel, this.$selectable, this.$pool, this.$nioChannel, this.$selector, continuation);
        cIOWriterKt$attachForWritingImpl$1.L$0 = obj;
        return cIOWriterKt$attachForWritingImpl$1;
    }

    public final Object invoke(ReaderScope readerScope, Continuation<? super Unit> continuation) {
        return ((CIOWriterKt$attachForWritingImpl$1) create(readerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01a4, code lost:
        r10.stop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01a7, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01a8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01a9, code lost:
        r1.$pool.recycle(r1.$buffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01b4, code lost:
        if ((r1.$nioChannel instanceof java.nio.channels.SocketChannel) != false) goto L_0x01b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01ba, code lost:
        if (io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable() != false) goto L_0x01bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01bc, code lost:
        java.nio.channels.SocketChannel unused = ((java.nio.channels.SocketChannel) r1.$nioChannel).shutdownOutput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01c4, code lost:
        ((java.nio.channels.SocketChannel) r1.$nioChannel).socket().shutdownOutput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01d0, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:6:0x0030, B:12:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ca A[Catch:{ all -> 0x0035, all -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00da A[SYNTHETIC, Splitter:B:36:0x00da] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f6 A[SYNTHETIC, Splitter:B:44:0x00f6] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0103 A[Catch:{ all -> 0x0035, all -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x012b A[Catch:{ all -> 0x0035, all -> 0x01a8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            r19 = this;
            r1 = r19
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x0067
            if (r2 == r5) goto L_0x005d
            if (r2 == r4) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r2 = r1.L$6
            io.ktor.network.selector.SelectorManager r2 = (io.ktor.network.selector.SelectorManager) r2
            java.lang.Object r7 = r1.L$5
            io.ktor.network.selector.Selectable r7 = (io.ktor.network.selector.Selectable) r7
            java.lang.Object r8 = r1.L$4
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r9 = r1.L$3
            java.nio.channels.WritableByteChannel r9 = (java.nio.channels.WritableByteChannel) r9
            java.lang.Object r10 = r1.L$2
            io.ktor.network.util.Timeout r10 = (io.ktor.network.util.Timeout) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$0
            io.ktor.network.util.Timeout r12 = (io.ktor.network.util.Timeout) r12
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ all -> 0x0035 }
            goto L_0x018a
        L_0x0035:
            r0 = move-exception
            goto L_0x01a4
        L_0x0038:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0040:
            java.lang.Object r2 = r1.L$5
            io.ktor.network.selector.SelectorManager r2 = (io.ktor.network.selector.SelectorManager) r2
            java.lang.Object r7 = r1.L$4
            io.ktor.network.selector.Selectable r7 = (io.ktor.network.selector.Selectable) r7
            java.lang.Object r8 = r1.L$3
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r9 = r1.L$2
            java.nio.channels.WritableByteChannel r9 = (java.nio.channels.WritableByteChannel) r9
            java.lang.Object r10 = r1.L$1
            kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
            java.lang.Object r11 = r1.L$0
            io.ktor.network.util.Timeout r11 = (io.ktor.network.util.Timeout) r11
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ all -> 0x01a8 }
            goto L_0x0149
        L_0x005d:
            java.lang.Object r2 = r1.L$0
            io.ktor.network.util.Timeout r2 = (io.ktor.network.util.Timeout) r2
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ all -> 0x01a8 }
            r7 = r20
            goto L_0x00bf
        L_0x0067:
            kotlin.ResultKt.throwOnFailure(r20)
            java.lang.Object r2 = r1.L$0
            io.ktor.utils.io.ReaderScope r2 = (io.ktor.utils.io.ReaderScope) r2
            io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions r7 = r1.$socketOptions     // Catch:{ all -> 0x01a8 }
            if (r7 == 0) goto L_0x007b
            long r7 = r7.getSocketTimeout()     // Catch:{ all -> 0x01a8 }
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)     // Catch:{ all -> 0x01a8 }
            goto L_0x007c
        L_0x007b:
            r7 = r6
        L_0x007c:
            if (r7 == 0) goto L_0x009b
            r8 = r2
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8     // Catch:{ all -> 0x01a8 }
            java.lang.String r9 = "writing"
            io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions r2 = r1.$socketOptions     // Catch:{ all -> 0x01a8 }
            long r10 = r2.getSocketTimeout()     // Catch:{ all -> 0x01a8 }
            io.ktor.network.sockets.CIOWriterKt$attachForWritingImpl$1$timeout$1 r2 = new io.ktor.network.sockets.CIOWriterKt$attachForWritingImpl$1$timeout$1     // Catch:{ all -> 0x01a8 }
            io.ktor.utils.io.ByteChannel r7 = r1.$channel     // Catch:{ all -> 0x01a8 }
            r2.<init>(r7, r6)     // Catch:{ all -> 0x01a8 }
            r13 = r2
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13     // Catch:{ all -> 0x01a8 }
            r14 = 4
            r15 = 0
            r12 = 0
            io.ktor.network.util.Timeout r2 = io.ktor.network.util.UtilsKt.createTimeout$default(r8, r9, r10, r12, r13, r14, r15)     // Catch:{ all -> 0x01a8 }
            goto L_0x009c
        L_0x009b:
            r2 = r6
        L_0x009c:
            java.nio.ByteBuffer r7 = r1.$buffer     // Catch:{ all -> 0x01a8 }
            r7.clear()     // Catch:{ all -> 0x01a8 }
            io.ktor.utils.io.ByteChannel r7 = r1.$channel     // Catch:{ all -> 0x01a8 }
            java.nio.ByteBuffer r8 = r1.$buffer     // Catch:{ all -> 0x01a8 }
            r9 = r1
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9     // Catch:{ all -> 0x01a8 }
            r1.L$0 = r2     // Catch:{ all -> 0x01a8 }
            r1.L$1 = r6     // Catch:{ all -> 0x01a8 }
            r1.L$2 = r6     // Catch:{ all -> 0x01a8 }
            r1.L$3 = r6     // Catch:{ all -> 0x01a8 }
            r1.L$4 = r6     // Catch:{ all -> 0x01a8 }
            r1.L$5 = r6     // Catch:{ all -> 0x01a8 }
            r1.L$6 = r6     // Catch:{ all -> 0x01a8 }
            r1.label = r5     // Catch:{ all -> 0x01a8 }
            java.lang.Object r7 = r7.readAvailable((java.nio.ByteBuffer) r8, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r9)     // Catch:{ all -> 0x01a8 }
            if (r7 != r0) goto L_0x00bf
            return r0
        L_0x00bf:
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ all -> 0x01a8 }
            int r7 = r7.intValue()     // Catch:{ all -> 0x01a8 }
            r8 = -1
            if (r7 != r8) goto L_0x00f6
            if (r2 == 0) goto L_0x00cd
            r2.finish()     // Catch:{ all -> 0x01a8 }
        L_0x00cd:
            io.ktor.utils.io.pool.ObjectPool<java.nio.ByteBuffer> r0 = r1.$pool
            java.nio.ByteBuffer r2 = r1.$buffer
            r0.recycle(r2)
            java.nio.channels.WritableByteChannel r0 = r1.$nioChannel
            boolean r0 = r0 instanceof java.nio.channels.SocketChannel
            if (r0 == 0) goto L_0x00f3
            boolean r0 = io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable()     // Catch:{ ClosedChannelException -> 0x00f3 }
            if (r0 == 0) goto L_0x00e8
            java.nio.channels.WritableByteChannel r0 = r1.$nioChannel     // Catch:{ ClosedChannelException -> 0x00f3 }
            java.nio.channels.SocketChannel r0 = (java.nio.channels.SocketChannel) r0     // Catch:{ ClosedChannelException -> 0x00f3 }
            java.nio.channels.SocketChannel unused = r0.shutdownOutput()     // Catch:{ ClosedChannelException -> 0x00f3 }
            goto L_0x00f3
        L_0x00e8:
            java.nio.channels.WritableByteChannel r0 = r1.$nioChannel     // Catch:{ ClosedChannelException -> 0x00f3 }
            java.nio.channels.SocketChannel r0 = (java.nio.channels.SocketChannel) r0     // Catch:{ ClosedChannelException -> 0x00f3 }
            java.net.Socket r0 = r0.socket()     // Catch:{ ClosedChannelException -> 0x00f3 }
            r0.shutdownOutput()     // Catch:{ ClosedChannelException -> 0x00f3 }
        L_0x00f3:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00f6:
            java.nio.ByteBuffer r7 = r1.$buffer     // Catch:{ all -> 0x01a8 }
            r7.flip()     // Catch:{ all -> 0x01a8 }
        L_0x00fb:
            java.nio.ByteBuffer r7 = r1.$buffer     // Catch:{ all -> 0x01a8 }
            boolean r7 = r7.hasRemaining()     // Catch:{ all -> 0x01a8 }
            if (r7 == 0) goto L_0x009c
            kotlin.jvm.internal.Ref$IntRef r7 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ all -> 0x01a8 }
            r7.<init>()     // Catch:{ all -> 0x01a8 }
            java.nio.channels.WritableByteChannel r8 = r1.$nioChannel     // Catch:{ all -> 0x01a8 }
            java.nio.ByteBuffer r9 = r1.$buffer     // Catch:{ all -> 0x01a8 }
            io.ktor.network.selector.Selectable r10 = r1.$selectable     // Catch:{ all -> 0x01a8 }
            io.ktor.network.selector.SelectorManager r11 = r1.$selector     // Catch:{ all -> 0x01a8 }
            if (r2 != 0) goto L_0x0155
            r16 = r11
            r11 = r2
            r2 = r16
            r17 = r10
            r10 = r7
            r7 = r17
            r18 = r9
            r9 = r8
            r8 = r18
        L_0x0121:
            int r12 = r9.write(r8)     // Catch:{ all -> 0x01a8 }
            r10.element = r12     // Catch:{ all -> 0x01a8 }
            int r12 = r10.element     // Catch:{ all -> 0x01a8 }
            if (r12 != 0) goto L_0x0149
            io.ktor.network.selector.SelectInterest r12 = io.ktor.network.selector.SelectInterest.WRITE     // Catch:{ all -> 0x01a8 }
            r7.interestOp(r12, r5)     // Catch:{ all -> 0x01a8 }
            io.ktor.network.selector.SelectInterest r12 = io.ktor.network.selector.SelectInterest.WRITE     // Catch:{ all -> 0x01a8 }
            r1.L$0 = r11     // Catch:{ all -> 0x01a8 }
            r1.L$1 = r10     // Catch:{ all -> 0x01a8 }
            r1.L$2 = r9     // Catch:{ all -> 0x01a8 }
            r1.L$3 = r8     // Catch:{ all -> 0x01a8 }
            r1.L$4 = r7     // Catch:{ all -> 0x01a8 }
            r1.L$5 = r2     // Catch:{ all -> 0x01a8 }
            r1.L$6 = r6     // Catch:{ all -> 0x01a8 }
            r1.label = r4     // Catch:{ all -> 0x01a8 }
            java.lang.Object r12 = r2.select(r7, r12, r1)     // Catch:{ all -> 0x01a8 }
            if (r12 != r0) goto L_0x0149
            return r0
        L_0x0149:
            boolean r12 = r8.hasRemaining()     // Catch:{ all -> 0x01a8 }
            if (r12 == 0) goto L_0x0153
            int r12 = r10.element     // Catch:{ all -> 0x01a8 }
            if (r12 == 0) goto L_0x0121
        L_0x0153:
            r2 = r11
            goto L_0x019a
        L_0x0155:
            r2.start()     // Catch:{ all -> 0x01a8 }
            r12 = r2
            r2 = r11
            r11 = r7
            r7 = r10
            r10 = r12
            r16 = r9
            r9 = r8
            r8 = r16
        L_0x0162:
            int r13 = r9.write(r8)     // Catch:{ all -> 0x0035 }
            r11.element = r13     // Catch:{ all -> 0x0035 }
            int r13 = r11.element     // Catch:{ all -> 0x0035 }
            if (r13 != 0) goto L_0x018a
            io.ktor.network.selector.SelectInterest r13 = io.ktor.network.selector.SelectInterest.WRITE     // Catch:{ all -> 0x0035 }
            r7.interestOp(r13, r5)     // Catch:{ all -> 0x0035 }
            io.ktor.network.selector.SelectInterest r13 = io.ktor.network.selector.SelectInterest.WRITE     // Catch:{ all -> 0x0035 }
            r1.L$0 = r12     // Catch:{ all -> 0x0035 }
            r1.L$1 = r11     // Catch:{ all -> 0x0035 }
            r1.L$2 = r10     // Catch:{ all -> 0x0035 }
            r1.L$3 = r9     // Catch:{ all -> 0x0035 }
            r1.L$4 = r8     // Catch:{ all -> 0x0035 }
            r1.L$5 = r7     // Catch:{ all -> 0x0035 }
            r1.L$6 = r2     // Catch:{ all -> 0x0035 }
            r1.label = r3     // Catch:{ all -> 0x0035 }
            java.lang.Object r13 = r2.select(r7, r13, r1)     // Catch:{ all -> 0x0035 }
            if (r13 != r0) goto L_0x018a
            return r0
        L_0x018a:
            boolean r13 = r8.hasRemaining()     // Catch:{ all -> 0x0035 }
            if (r13 == 0) goto L_0x0194
            int r13 = r11.element     // Catch:{ all -> 0x0035 }
            if (r13 == 0) goto L_0x0162
        L_0x0194:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0035 }
            r10.stop()     // Catch:{ all -> 0x01a8 }
            r2 = r12
        L_0x019a:
            io.ktor.network.selector.Selectable r7 = r1.$selectable     // Catch:{ all -> 0x01a8 }
            io.ktor.network.selector.SelectInterest r8 = io.ktor.network.selector.SelectInterest.WRITE     // Catch:{ all -> 0x01a8 }
            r9 = 0
            r7.interestOp(r8, r9)     // Catch:{ all -> 0x01a8 }
            goto L_0x00fb
        L_0x01a4:
            r10.stop()     // Catch:{ all -> 0x01a8 }
            throw r0     // Catch:{ all -> 0x01a8 }
        L_0x01a8:
            r0 = move-exception
            io.ktor.utils.io.pool.ObjectPool<java.nio.ByteBuffer> r2 = r1.$pool
            java.nio.ByteBuffer r3 = r1.$buffer
            r2.recycle(r3)
            java.nio.channels.WritableByteChannel r2 = r1.$nioChannel
            boolean r2 = r2 instanceof java.nio.channels.SocketChannel
            if (r2 == 0) goto L_0x01cf
            boolean r2 = io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable()     // Catch:{ ClosedChannelException -> 0x01cf }
            if (r2 == 0) goto L_0x01c4
            java.nio.channels.WritableByteChannel r2 = r1.$nioChannel     // Catch:{ ClosedChannelException -> 0x01cf }
            java.nio.channels.SocketChannel r2 = (java.nio.channels.SocketChannel) r2     // Catch:{ ClosedChannelException -> 0x01cf }
            java.nio.channels.SocketChannel unused = r2.shutdownOutput()     // Catch:{ ClosedChannelException -> 0x01cf }
            goto L_0x01cf
        L_0x01c4:
            java.nio.channels.WritableByteChannel r2 = r1.$nioChannel     // Catch:{ ClosedChannelException -> 0x01cf }
            java.nio.channels.SocketChannel r2 = (java.nio.channels.SocketChannel) r2     // Catch:{ ClosedChannelException -> 0x01cf }
            java.net.Socket r2 = r2.socket()     // Catch:{ ClosedChannelException -> 0x01cf }
            r2.shutdownOutput()     // Catch:{ ClosedChannelException -> 0x01cf }
        L_0x01cf:
            goto L_0x01d1
        L_0x01d0:
            throw r0
        L_0x01d1:
            goto L_0x01d0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOWriterKt$attachForWritingImpl$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
