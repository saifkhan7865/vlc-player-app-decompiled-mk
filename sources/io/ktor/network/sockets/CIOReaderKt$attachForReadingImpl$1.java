package io.ktor.network.sockets;

import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.CIOReaderKt$attachForReadingImpl$1", f = "CIOReader.kt", i = {0, 0, 1, 1, 1, 2}, l = {45, 45, 56}, m = "invokeSuspend", n = {"timeout", "rc", "timeout", "rc", "$this$withTimeout$iv", "timeout"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0"})
/* compiled from: CIOReader.kt */
final class CIOReaderKt$attachForReadingImpl$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteBuffer $buffer;
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ ReadableByteChannel $nioChannel;
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
    Object L$7;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOReaderKt$attachForReadingImpl$1(SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, ByteChannel byteChannel, Selectable selectable, ByteBuffer byteBuffer, ObjectPool<ByteBuffer> objectPool, ReadableByteChannel readableByteChannel, SelectorManager selectorManager, Continuation<? super CIOReaderKt$attachForReadingImpl$1> continuation) {
        super(2, continuation);
        this.$socketOptions = tCPClientSocketOptions;
        this.$channel = byteChannel;
        this.$selectable = selectable;
        this.$buffer = byteBuffer;
        this.$pool = objectPool;
        this.$nioChannel = readableByteChannel;
        this.$selector = selectorManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CIOReaderKt$attachForReadingImpl$1 cIOReaderKt$attachForReadingImpl$1 = new CIOReaderKt$attachForReadingImpl$1(this.$socketOptions, this.$channel, this.$selectable, this.$buffer, this.$pool, this.$nioChannel, this.$selector, continuation);
        cIOReaderKt$attachForReadingImpl$1.L$0 = obj;
        return cIOReaderKt$attachForReadingImpl$1;
    }

    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((CIOReaderKt$attachForReadingImpl$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x010b A[Catch:{ all -> 0x004a, all -> 0x01ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0132 A[Catch:{ all -> 0x004a, all -> 0x01ab }] */
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
            if (r2 == 0) goto L_0x006e
            if (r2 == r5) goto L_0x004d
            if (r2 == r4) goto L_0x0025
            if (r2 != r3) goto L_0x001d
            java.lang.Object r2 = r1.L$0
            io.ktor.network.util.Timeout r2 = (io.ktor.network.util.Timeout) r2
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ all -> 0x01ab }
            goto L_0x01a0
        L_0x001d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0025:
            java.lang.Object r2 = r1.L$7
            io.ktor.network.selector.SelectorManager r2 = (io.ktor.network.selector.SelectorManager) r2
            java.lang.Object r7 = r1.L$6
            io.ktor.network.selector.Selectable r7 = (io.ktor.network.selector.Selectable) r7
            java.lang.Object r8 = r1.L$5
            io.ktor.utils.io.ByteChannel r8 = (io.ktor.utils.io.ByteChannel) r8
            java.lang.Object r9 = r1.L$4
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r10 = r1.L$3
            java.nio.channels.ReadableByteChannel r10 = (java.nio.channels.ReadableByteChannel) r10
            java.lang.Object r11 = r1.L$2
            io.ktor.network.util.Timeout r11 = (io.ktor.network.util.Timeout) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.internal.Ref$IntRef r12 = (kotlin.jvm.internal.Ref.IntRef) r12
            java.lang.Object r13 = r1.L$0
            io.ktor.network.util.Timeout r13 = (io.ktor.network.util.Timeout) r13
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ all -> 0x004a }
            goto L_0x012e
        L_0x004a:
            r0 = move-exception
            goto L_0x01a7
        L_0x004d:
            java.lang.Object r2 = r1.L$6
            io.ktor.network.selector.SelectorManager r2 = (io.ktor.network.selector.SelectorManager) r2
            java.lang.Object r7 = r1.L$5
            io.ktor.network.selector.Selectable r7 = (io.ktor.network.selector.Selectable) r7
            java.lang.Object r8 = r1.L$4
            io.ktor.utils.io.ByteChannel r8 = (io.ktor.utils.io.ByteChannel) r8
            java.lang.Object r9 = r1.L$3
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r10 = r1.L$2
            java.nio.channels.ReadableByteChannel r10 = (java.nio.channels.ReadableByteChannel) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r1.L$0
            io.ktor.network.util.Timeout r12 = (io.ktor.network.util.Timeout) r12
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ all -> 0x01ab }
            goto L_0x00ee
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r20)
            java.lang.Object r2 = r1.L$0
            io.ktor.utils.io.WriterScope r2 = (io.ktor.utils.io.WriterScope) r2
            io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions r7 = r1.$socketOptions     // Catch:{ all -> 0x01ab }
            if (r7 == 0) goto L_0x0082
            long r7 = r7.getSocketTimeout()     // Catch:{ all -> 0x01ab }
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)     // Catch:{ all -> 0x01ab }
            goto L_0x0083
        L_0x0082:
            r7 = r6
        L_0x0083:
            if (r7 == 0) goto L_0x00a2
            r8 = r2
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8     // Catch:{ all -> 0x01ab }
            java.lang.String r9 = "reading"
            io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions r2 = r1.$socketOptions     // Catch:{ all -> 0x01ab }
            long r10 = r2.getSocketTimeout()     // Catch:{ all -> 0x01ab }
            io.ktor.network.sockets.CIOReaderKt$attachForReadingImpl$1$timeout$1 r2 = new io.ktor.network.sockets.CIOReaderKt$attachForReadingImpl$1$timeout$1     // Catch:{ all -> 0x01ab }
            io.ktor.utils.io.ByteChannel r7 = r1.$channel     // Catch:{ all -> 0x01ab }
            r2.<init>(r7, r6)     // Catch:{ all -> 0x01ab }
            r13 = r2
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13     // Catch:{ all -> 0x01ab }
            r14 = 4
            r15 = 0
            r12 = 0
            io.ktor.network.util.Timeout r2 = io.ktor.network.util.UtilsKt.createTimeout$default(r8, r9, r10, r12, r13, r14, r15)     // Catch:{ all -> 0x01ab }
            goto L_0x00a3
        L_0x00a2:
            r2 = r6
        L_0x00a3:
            kotlin.jvm.internal.Ref$IntRef r7 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ all -> 0x01ab }
            r7.<init>()     // Catch:{ all -> 0x01ab }
            java.nio.channels.ReadableByteChannel r8 = r1.$nioChannel     // Catch:{ all -> 0x01ab }
            java.nio.ByteBuffer r9 = r1.$buffer     // Catch:{ all -> 0x01ab }
            io.ktor.utils.io.ByteChannel r10 = r1.$channel     // Catch:{ all -> 0x01ab }
            io.ktor.network.selector.Selectable r11 = r1.$selectable     // Catch:{ all -> 0x01ab }
            io.ktor.network.selector.SelectorManager r12 = r1.$selector     // Catch:{ all -> 0x01ab }
            if (r2 != 0) goto L_0x00f4
            r16 = r12
            r12 = r2
            r2 = r16
            r17 = r11
            r11 = r7
            r7 = r17
            r18 = r10
            r10 = r8
            r8 = r18
        L_0x00c3:
            int r13 = r10.read(r9)     // Catch:{ all -> 0x01ab }
            r11.element = r13     // Catch:{ all -> 0x01ab }
            int r13 = r11.element     // Catch:{ all -> 0x01ab }
            if (r13 != 0) goto L_0x00ee
            r8.flush()     // Catch:{ all -> 0x01ab }
            io.ktor.network.selector.SelectInterest r13 = io.ktor.network.selector.SelectInterest.READ     // Catch:{ all -> 0x01ab }
            r7.interestOp(r13, r5)     // Catch:{ all -> 0x01ab }
            io.ktor.network.selector.SelectInterest r13 = io.ktor.network.selector.SelectInterest.READ     // Catch:{ all -> 0x01ab }
            r1.L$0 = r12     // Catch:{ all -> 0x01ab }
            r1.L$1 = r11     // Catch:{ all -> 0x01ab }
            r1.L$2 = r10     // Catch:{ all -> 0x01ab }
            r1.L$3 = r9     // Catch:{ all -> 0x01ab }
            r1.L$4 = r8     // Catch:{ all -> 0x01ab }
            r1.L$5 = r7     // Catch:{ all -> 0x01ab }
            r1.L$6 = r2     // Catch:{ all -> 0x01ab }
            r1.label = r5     // Catch:{ all -> 0x01ab }
            java.lang.Object r13 = r2.select(r7, r13, r1)     // Catch:{ all -> 0x01ab }
            if (r13 != r0) goto L_0x00ee
            return r0
        L_0x00ee:
            int r13 = r11.element     // Catch:{ all -> 0x01ab }
            if (r13 == 0) goto L_0x00c3
            r2 = r12
            goto L_0x0139
        L_0x00f4:
            r2.start()     // Catch:{ all -> 0x01ab }
            r13 = r2
            r2 = r12
            r12 = r7
            r7 = r11
            r11 = r13
            r16 = r10
            r10 = r8
            r8 = r16
        L_0x0101:
            int r14 = r10.read(r9)     // Catch:{ all -> 0x004a }
            r12.element = r14     // Catch:{ all -> 0x004a }
            int r14 = r12.element     // Catch:{ all -> 0x004a }
            if (r14 != 0) goto L_0x012e
            r8.flush()     // Catch:{ all -> 0x004a }
            io.ktor.network.selector.SelectInterest r14 = io.ktor.network.selector.SelectInterest.READ     // Catch:{ all -> 0x004a }
            r7.interestOp(r14, r5)     // Catch:{ all -> 0x004a }
            io.ktor.network.selector.SelectInterest r14 = io.ktor.network.selector.SelectInterest.READ     // Catch:{ all -> 0x004a }
            r1.L$0 = r13     // Catch:{ all -> 0x004a }
            r1.L$1 = r12     // Catch:{ all -> 0x004a }
            r1.L$2 = r11     // Catch:{ all -> 0x004a }
            r1.L$3 = r10     // Catch:{ all -> 0x004a }
            r1.L$4 = r9     // Catch:{ all -> 0x004a }
            r1.L$5 = r8     // Catch:{ all -> 0x004a }
            r1.L$6 = r7     // Catch:{ all -> 0x004a }
            r1.L$7 = r2     // Catch:{ all -> 0x004a }
            r1.label = r4     // Catch:{ all -> 0x004a }
            java.lang.Object r14 = r2.select(r7, r14, r1)     // Catch:{ all -> 0x004a }
            if (r14 != r0) goto L_0x012e
            return r0
        L_0x012e:
            int r14 = r12.element     // Catch:{ all -> 0x004a }
            if (r14 == 0) goto L_0x0101
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004a }
            r11.stop()     // Catch:{ all -> 0x01ab }
            r11 = r12
            r2 = r13
        L_0x0139:
            int r7 = r11.element     // Catch:{ all -> 0x01ab }
            r8 = -1
            if (r7 != r8) goto L_0x0173
            io.ktor.utils.io.ByteChannel r0 = r1.$channel     // Catch:{ all -> 0x01ab }
            io.ktor.utils.io.ByteWriteChannel r0 = (io.ktor.utils.io.ByteWriteChannel) r0     // Catch:{ all -> 0x01ab }
            io.ktor.utils.io.ByteWriteChannelKt.close(r0)     // Catch:{ all -> 0x01ab }
            if (r2 == 0) goto L_0x014a
            r2.finish()     // Catch:{ all -> 0x01ab }
        L_0x014a:
            io.ktor.utils.io.pool.ObjectPool<java.nio.ByteBuffer> r0 = r1.$pool
            java.nio.ByteBuffer r2 = r1.$buffer
            r0.recycle(r2)
            java.nio.channels.ReadableByteChannel r0 = r1.$nioChannel
            boolean r0 = r0 instanceof java.nio.channels.SocketChannel
            if (r0 == 0) goto L_0x0170
            boolean r0 = io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable()     // Catch:{ ClosedChannelException -> 0x0170 }
            if (r0 == 0) goto L_0x0165
            java.nio.channels.ReadableByteChannel r0 = r1.$nioChannel     // Catch:{ ClosedChannelException -> 0x0170 }
            java.nio.channels.SocketChannel r0 = (java.nio.channels.SocketChannel) r0     // Catch:{ ClosedChannelException -> 0x0170 }
            java.nio.channels.SocketChannel unused = r0.shutdownInput()     // Catch:{ ClosedChannelException -> 0x0170 }
            goto L_0x0170
        L_0x0165:
            java.nio.channels.ReadableByteChannel r0 = r1.$nioChannel     // Catch:{ ClosedChannelException -> 0x0170 }
            java.nio.channels.SocketChannel r0 = (java.nio.channels.SocketChannel) r0     // Catch:{ ClosedChannelException -> 0x0170 }
            java.net.Socket r0 = r0.socket()     // Catch:{ ClosedChannelException -> 0x0170 }
            r0.shutdownInput()     // Catch:{ ClosedChannelException -> 0x0170 }
        L_0x0170:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0173:
            io.ktor.network.selector.Selectable r7 = r1.$selectable     // Catch:{ all -> 0x01ab }
            io.ktor.network.selector.SelectInterest r8 = io.ktor.network.selector.SelectInterest.READ     // Catch:{ all -> 0x01ab }
            r9 = 0
            r7.interestOp(r8, r9)     // Catch:{ all -> 0x01ab }
            java.nio.ByteBuffer r7 = r1.$buffer     // Catch:{ all -> 0x01ab }
            r7.flip()     // Catch:{ all -> 0x01ab }
            io.ktor.utils.io.ByteChannel r7 = r1.$channel     // Catch:{ all -> 0x01ab }
            java.nio.ByteBuffer r8 = r1.$buffer     // Catch:{ all -> 0x01ab }
            r9 = r1
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9     // Catch:{ all -> 0x01ab }
            r1.L$0 = r2     // Catch:{ all -> 0x01ab }
            r1.L$1 = r6     // Catch:{ all -> 0x01ab }
            r1.L$2 = r6     // Catch:{ all -> 0x01ab }
            r1.L$3 = r6     // Catch:{ all -> 0x01ab }
            r1.L$4 = r6     // Catch:{ all -> 0x01ab }
            r1.L$5 = r6     // Catch:{ all -> 0x01ab }
            r1.L$6 = r6     // Catch:{ all -> 0x01ab }
            r1.L$7 = r6     // Catch:{ all -> 0x01ab }
            r1.label = r3     // Catch:{ all -> 0x01ab }
            java.lang.Object r7 = r7.writeFully((java.nio.ByteBuffer) r8, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r9)     // Catch:{ all -> 0x01ab }
            if (r7 != r0) goto L_0x01a0
            return r0
        L_0x01a0:
            java.nio.ByteBuffer r7 = r1.$buffer     // Catch:{ all -> 0x01ab }
            r7.clear()     // Catch:{ all -> 0x01ab }
            goto L_0x00a3
        L_0x01a7:
            r11.stop()     // Catch:{ all -> 0x01ab }
            throw r0     // Catch:{ all -> 0x01ab }
        L_0x01ab:
            r0 = move-exception
            io.ktor.utils.io.pool.ObjectPool<java.nio.ByteBuffer> r2 = r1.$pool
            java.nio.ByteBuffer r3 = r1.$buffer
            r2.recycle(r3)
            java.nio.channels.ReadableByteChannel r2 = r1.$nioChannel
            boolean r2 = r2 instanceof java.nio.channels.SocketChannel
            if (r2 == 0) goto L_0x01d2
            boolean r2 = io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable()     // Catch:{ ClosedChannelException -> 0x01d2 }
            if (r2 == 0) goto L_0x01c7
            java.nio.channels.ReadableByteChannel r2 = r1.$nioChannel     // Catch:{ ClosedChannelException -> 0x01d2 }
            java.nio.channels.SocketChannel r2 = (java.nio.channels.SocketChannel) r2     // Catch:{ ClosedChannelException -> 0x01d2 }
            java.nio.channels.SocketChannel unused = r2.shutdownInput()     // Catch:{ ClosedChannelException -> 0x01d2 }
            goto L_0x01d2
        L_0x01c7:
            java.nio.channels.ReadableByteChannel r2 = r1.$nioChannel     // Catch:{ ClosedChannelException -> 0x01d2 }
            java.nio.channels.SocketChannel r2 = (java.nio.channels.SocketChannel) r2     // Catch:{ ClosedChannelException -> 0x01d2 }
            java.net.Socket r2 = r2.socket()     // Catch:{ ClosedChannelException -> 0x01d2 }
            r2.shutdownInput()     // Catch:{ ClosedChannelException -> 0x01d2 }
        L_0x01d2:
            goto L_0x01d4
        L_0x01d3:
            throw r0
        L_0x01d4:
            goto L_0x01d3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOReaderKt$attachForReadingImpl$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
