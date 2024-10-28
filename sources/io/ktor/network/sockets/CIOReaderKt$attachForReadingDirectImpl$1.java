package io.ktor.network.sockets;

import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.WriterScope;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1", f = "CIOReader.kt", i = {0, 1, 2, 3, 3, 4, 4, 5, 5}, l = {98, 110, 111, 98, 110, 111}, m = "invokeSuspend", n = {"timeout", "timeout", "timeout", "timeout", "$this$withTimeout$iv", "timeout", "$this$withTimeout$iv", "timeout", "$this$withTimeout$iv"}, s = {"L$0", "L$0", "L$0", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* compiled from: CIOReader.kt */
final class CIOReaderKt$attachForReadingDirectImpl$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ ReadableByteChannel $nioChannel;
    final /* synthetic */ Selectable $selectable;
    final /* synthetic */ SelectorManager $selector;
    final /* synthetic */ SocketOptions.TCPClientSocketOptions $socketOptions;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOReaderKt$attachForReadingDirectImpl$1(Selectable selectable, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, ByteChannel byteChannel, ReadableByteChannel readableByteChannel, SelectorManager selectorManager, Continuation<? super CIOReaderKt$attachForReadingDirectImpl$1> continuation) {
        super(2, continuation);
        this.$selectable = selectable;
        this.$socketOptions = tCPClientSocketOptions;
        this.$channel = byteChannel;
        this.$nioChannel = readableByteChannel;
        this.$selector = selectorManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CIOReaderKt$attachForReadingDirectImpl$1 cIOReaderKt$attachForReadingDirectImpl$1 = new CIOReaderKt$attachForReadingDirectImpl$1(this.$selectable, this.$socketOptions, this.$channel, this.$nioChannel, this.$selector, continuation);
        cIOReaderKt$attachForReadingDirectImpl$1.L$0 = obj;
        return cIOReaderKt$attachForReadingDirectImpl$1;
    }

    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((CIOReaderKt$attachForReadingDirectImpl$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x021c, code lost:
        if (io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable() != false) goto L_0x021e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x021e, code lost:
        java.nio.channels.SocketChannel unused = ((java.nio.channels.SocketChannel) r12.$nioChannel).shutdownInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0226, code lost:
        ((java.nio.channels.SocketChannel) r12.$nioChannel).socket().shutdownInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0232, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x006a, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00fb, code lost:
        if (r12.$channel.isClosedForWrite() != false) goto L_0x01da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00fd, code lost:
        r6 = r12.$channel;
        r5 = r12.$nioChannel;
        r4 = r12.$selectable;
        r1 = r12.$selector;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0105, code lost:
        if (r7 != null) goto L_0x0167;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0107, code lost:
        r12.L$0 = r7;
        r12.L$1 = r6;
        r12.L$2 = r5;
        r12.L$3 = r4;
        r12.L$4 = r1;
        r12.L$5 = null;
        r12.label = 1;
        r13 = io.ktor.network.sockets.CIOReaderKt.readFrom(r6, r5, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x011d, code lost:
        if (r13 != r0) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x011f, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0120, code lost:
        r13 = ((java.lang.Number) r13).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0126, code lost:
        if (r13 != -1) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0128, code lost:
        io.ktor.utils.io.ByteWriteChannelKt.close(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x012e, code lost:
        if (r13 > 0) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0130, code lost:
        r6.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0133, code lost:
        r12.L$0 = r7;
        r12.L$1 = r6;
        r12.L$2 = r5;
        r12.L$3 = r4;
        r12.L$4 = r1;
        r12.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0144, code lost:
        if (io.ktor.network.sockets.CIOReaderKt.selectForRead(r4, r1, r12) != r0) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0146, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0147, code lost:
        r12.L$0 = r7;
        r12.L$1 = r6;
        r12.L$2 = r5;
        r12.L$3 = r4;
        r12.L$4 = r1;
        r12.label = 3;
        r13 = io.ktor.network.sockets.CIOReaderKt.readFrom(r6, r5, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x015b, code lost:
        if (r13 != r0) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x015d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0164, code lost:
        if (((java.lang.Number) r13).intValue() == 0) goto L_0x0133;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0167, code lost:
        r7.start();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r12.L$0 = r7;
        r12.L$1 = r7;
        r12.L$2 = r6;
        r12.L$3 = r5;
        r12.L$4 = r4;
        r12.L$5 = r1;
        r12.label = 4;
        r13 = io.ktor.network.sockets.CIOReaderKt.readFrom(r6, r5, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0180, code lost:
        if (r13 != r0) goto L_0x0183;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0182, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0183, code lost:
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0184, code lost:
        r13 = ((java.lang.Number) r13).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x018a, code lost:
        if (r13 != -1) goto L_0x0192;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x018c, code lost:
        io.ktor.utils.io.ByteWriteChannelKt.close(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0192, code lost:
        if (r13 > 0) goto L_0x01ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0194, code lost:
        r6.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0197, code lost:
        r12.L$0 = r8;
        r12.L$1 = r7;
        r12.L$2 = r6;
        r12.L$3 = r5;
        r12.L$4 = r4;
        r12.L$5 = r1;
        r12.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01aa, code lost:
        if (io.ktor.network.sockets.CIOReaderKt.selectForRead(r4, r1, r12) != r0) goto L_0x01ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01ac, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01ad, code lost:
        r12.L$0 = r8;
        r12.L$1 = r7;
        r12.L$2 = r6;
        r12.L$3 = r5;
        r12.L$4 = r4;
        r12.L$5 = r1;
        r12.label = 6;
        r13 = io.ktor.network.sockets.CIOReaderKt.readFrom(r6, r5, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01c3, code lost:
        if (r13 != r0) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01c5, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01cc, code lost:
        if (((java.lang.Number) r13).intValue() == 0) goto L_0x0197;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01ce, code lost:
        r13 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        r7.stop();
        r7 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01d6, code lost:
        r7.stop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01d9, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01da, code lost:
        if (r7 == null) goto L_0x01df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01dc, code lost:
        r7.finish();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01df, code lost:
        r13 = r12.$channel.getClosedCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01e5, code lost:
        if (r13 != null) goto L_0x0210;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01e7, code lost:
        io.ktor.utils.io.ByteWriteChannelKt.close(r12.$channel);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01f2, code lost:
        if ((r12.$nioChannel instanceof java.nio.channels.SocketChannel) == false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01f8, code lost:
        if (io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable() == false) goto L_0x0202;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01fa, code lost:
        java.nio.channels.SocketChannel unused = ((java.nio.channels.SocketChannel) r12.$nioChannel).shutdownInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0202, code lost:
        ((java.nio.channels.SocketChannel) r12.$nioChannel).socket().shutdownInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:?, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0211, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0216, code lost:
        if ((r12.$nioChannel instanceof java.nio.channels.SocketChannel) != false) goto L_0x0218;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:5:0x002b, B:15:0x0081] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = -1
            r3 = 0
            switch(r1) {
                case 0: goto L_0x00b7;
                case 1: goto L_0x009f;
                case 2: goto L_0x0086;
                case 3: goto L_0x006d;
                case 4: goto L_0x004d;
                case 5: goto L_0x0030;
                case 6: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0013:
            java.lang.Object r1 = r12.L$5
            io.ktor.network.selector.SelectorManager r1 = (io.ktor.network.selector.SelectorManager) r1
            java.lang.Object r4 = r12.L$4
            io.ktor.network.selector.Selectable r4 = (io.ktor.network.selector.Selectable) r4
            java.lang.Object r5 = r12.L$3
            java.nio.channels.ReadableByteChannel r5 = (java.nio.channels.ReadableByteChannel) r5
            java.lang.Object r6 = r12.L$2
            io.ktor.utils.io.ByteChannel r6 = (io.ktor.utils.io.ByteChannel) r6
            java.lang.Object r7 = r12.L$1
            io.ktor.network.util.Timeout r7 = (io.ktor.network.util.Timeout) r7
            java.lang.Object r8 = r12.L$0
            io.ktor.network.util.Timeout r8 = (io.ktor.network.util.Timeout) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x006a }
            goto L_0x01c6
        L_0x0030:
            java.lang.Object r1 = r12.L$5
            io.ktor.network.selector.SelectorManager r1 = (io.ktor.network.selector.SelectorManager) r1
            java.lang.Object r4 = r12.L$4
            io.ktor.network.selector.Selectable r4 = (io.ktor.network.selector.Selectable) r4
            java.lang.Object r5 = r12.L$3
            java.nio.channels.ReadableByteChannel r5 = (java.nio.channels.ReadableByteChannel) r5
            java.lang.Object r6 = r12.L$2
            io.ktor.utils.io.ByteChannel r6 = (io.ktor.utils.io.ByteChannel) r6
            java.lang.Object r7 = r12.L$1
            io.ktor.network.util.Timeout r7 = (io.ktor.network.util.Timeout) r7
            java.lang.Object r8 = r12.L$0
            io.ktor.network.util.Timeout r8 = (io.ktor.network.util.Timeout) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x006a }
            goto L_0x01ad
        L_0x004d:
            java.lang.Object r1 = r12.L$5
            io.ktor.network.selector.SelectorManager r1 = (io.ktor.network.selector.SelectorManager) r1
            java.lang.Object r4 = r12.L$4
            io.ktor.network.selector.Selectable r4 = (io.ktor.network.selector.Selectable) r4
            java.lang.Object r5 = r12.L$3
            java.nio.channels.ReadableByteChannel r5 = (java.nio.channels.ReadableByteChannel) r5
            java.lang.Object r6 = r12.L$2
            io.ktor.utils.io.ByteChannel r6 = (io.ktor.utils.io.ByteChannel) r6
            java.lang.Object r7 = r12.L$1
            io.ktor.network.util.Timeout r7 = (io.ktor.network.util.Timeout) r7
            java.lang.Object r8 = r12.L$0
            io.ktor.network.util.Timeout r8 = (io.ktor.network.util.Timeout) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x006a }
            goto L_0x0184
        L_0x006a:
            r13 = move-exception
            goto L_0x01d6
        L_0x006d:
            java.lang.Object r1 = r12.L$4
            io.ktor.network.selector.SelectorManager r1 = (io.ktor.network.selector.SelectorManager) r1
            java.lang.Object r4 = r12.L$3
            io.ktor.network.selector.Selectable r4 = (io.ktor.network.selector.Selectable) r4
            java.lang.Object r5 = r12.L$2
            java.nio.channels.ReadableByteChannel r5 = (java.nio.channels.ReadableByteChannel) r5
            java.lang.Object r6 = r12.L$1
            io.ktor.utils.io.ByteChannel r6 = (io.ktor.utils.io.ByteChannel) r6
            java.lang.Object r7 = r12.L$0
            io.ktor.network.util.Timeout r7 = (io.ktor.network.util.Timeout) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0211 }
            goto L_0x015e
        L_0x0086:
            java.lang.Object r1 = r12.L$4
            io.ktor.network.selector.SelectorManager r1 = (io.ktor.network.selector.SelectorManager) r1
            java.lang.Object r4 = r12.L$3
            io.ktor.network.selector.Selectable r4 = (io.ktor.network.selector.Selectable) r4
            java.lang.Object r5 = r12.L$2
            java.nio.channels.ReadableByteChannel r5 = (java.nio.channels.ReadableByteChannel) r5
            java.lang.Object r6 = r12.L$1
            io.ktor.utils.io.ByteChannel r6 = (io.ktor.utils.io.ByteChannel) r6
            java.lang.Object r7 = r12.L$0
            io.ktor.network.util.Timeout r7 = (io.ktor.network.util.Timeout) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0211 }
            goto L_0x0147
        L_0x009f:
            java.lang.Object r1 = r12.L$4
            io.ktor.network.selector.SelectorManager r1 = (io.ktor.network.selector.SelectorManager) r1
            java.lang.Object r4 = r12.L$3
            io.ktor.network.selector.Selectable r4 = (io.ktor.network.selector.Selectable) r4
            java.lang.Object r5 = r12.L$2
            java.nio.channels.ReadableByteChannel r5 = (java.nio.channels.ReadableByteChannel) r5
            java.lang.Object r6 = r12.L$1
            io.ktor.utils.io.ByteChannel r6 = (io.ktor.utils.io.ByteChannel) r6
            java.lang.Object r7 = r12.L$0
            io.ktor.network.util.Timeout r7 = (io.ktor.network.util.Timeout) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0211 }
            goto L_0x0120
        L_0x00b7:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            io.ktor.utils.io.WriterScope r13 = (io.ktor.utils.io.WriterScope) r13
            io.ktor.network.selector.Selectable r1 = r12.$selectable     // Catch:{ all -> 0x0211 }
            io.ktor.network.selector.SelectInterest r4 = io.ktor.network.selector.SelectInterest.READ     // Catch:{ all -> 0x0211 }
            r5 = 0
            r1.interestOp(r4, r5)     // Catch:{ all -> 0x0211 }
            io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions r1 = r12.$socketOptions     // Catch:{ all -> 0x0211 }
            if (r1 == 0) goto L_0x00d3
            long r4 = r1.getSocketTimeout()     // Catch:{ all -> 0x0211 }
            java.lang.Long r1 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)     // Catch:{ all -> 0x0211 }
            goto L_0x00d4
        L_0x00d3:
            r1 = r3
        L_0x00d4:
            if (r1 == 0) goto L_0x00f3
            r4 = r13
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4     // Catch:{ all -> 0x0211 }
            java.lang.String r5 = "reading-direct"
            io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions r13 = r12.$socketOptions     // Catch:{ all -> 0x0211 }
            long r6 = r13.getSocketTimeout()     // Catch:{ all -> 0x0211 }
            io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1$timeout$1 r13 = new io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1$timeout$1     // Catch:{ all -> 0x0211 }
            io.ktor.utils.io.ByteChannel r1 = r12.$channel     // Catch:{ all -> 0x0211 }
            r13.<init>(r1, r3)     // Catch:{ all -> 0x0211 }
            r9 = r13
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9     // Catch:{ all -> 0x0211 }
            r10 = 4
            r11 = 0
            r8 = 0
            io.ktor.network.util.Timeout r13 = io.ktor.network.util.UtilsKt.createTimeout$default(r4, r5, r6, r8, r9, r10, r11)     // Catch:{ all -> 0x0211 }
            goto L_0x00f4
        L_0x00f3:
            r13 = r3
        L_0x00f4:
            r7 = r13
        L_0x00f5:
            io.ktor.utils.io.ByteChannel r13 = r12.$channel     // Catch:{ all -> 0x0211 }
            boolean r13 = r13.isClosedForWrite()     // Catch:{ all -> 0x0211 }
            if (r13 != 0) goto L_0x01da
            io.ktor.utils.io.ByteChannel r6 = r12.$channel     // Catch:{ all -> 0x0211 }
            java.nio.channels.ReadableByteChannel r5 = r12.$nioChannel     // Catch:{ all -> 0x0211 }
            io.ktor.network.selector.Selectable r4 = r12.$selectable     // Catch:{ all -> 0x0211 }
            io.ktor.network.selector.SelectorManager r1 = r12.$selector     // Catch:{ all -> 0x0211 }
            if (r7 != 0) goto L_0x0167
            r13 = r6
            io.ktor.utils.io.ByteWriteChannel r13 = (io.ktor.utils.io.ByteWriteChannel) r13     // Catch:{ all -> 0x0211 }
            r12.L$0 = r7     // Catch:{ all -> 0x0211 }
            r12.L$1 = r6     // Catch:{ all -> 0x0211 }
            r12.L$2 = r5     // Catch:{ all -> 0x0211 }
            r12.L$3 = r4     // Catch:{ all -> 0x0211 }
            r12.L$4 = r1     // Catch:{ all -> 0x0211 }
            r12.L$5 = r3     // Catch:{ all -> 0x0211 }
            r8 = 1
            r12.label = r8     // Catch:{ all -> 0x0211 }
            java.lang.Object r13 = io.ktor.network.sockets.CIOReaderKt.readFrom(r13, r5, r12)     // Catch:{ all -> 0x0211 }
            if (r13 != r0) goto L_0x0120
            return r0
        L_0x0120:
            java.lang.Number r13 = (java.lang.Number) r13     // Catch:{ all -> 0x0211 }
            int r13 = r13.intValue()     // Catch:{ all -> 0x0211 }
            if (r13 != r2) goto L_0x012e
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6     // Catch:{ all -> 0x0211 }
            io.ktor.utils.io.ByteWriteChannelKt.close(r6)     // Catch:{ all -> 0x0211 }
            goto L_0x00f5
        L_0x012e:
            if (r13 > 0) goto L_0x00f5
            r6.flush()     // Catch:{ all -> 0x0211 }
        L_0x0133:
            r12.L$0 = r7     // Catch:{ all -> 0x0211 }
            r12.L$1 = r6     // Catch:{ all -> 0x0211 }
            r12.L$2 = r5     // Catch:{ all -> 0x0211 }
            r12.L$3 = r4     // Catch:{ all -> 0x0211 }
            r12.L$4 = r1     // Catch:{ all -> 0x0211 }
            r13 = 2
            r12.label = r13     // Catch:{ all -> 0x0211 }
            java.lang.Object r13 = io.ktor.network.sockets.CIOReaderKt.selectForRead(r4, r1, r12)     // Catch:{ all -> 0x0211 }
            if (r13 != r0) goto L_0x0147
            return r0
        L_0x0147:
            r13 = r6
            io.ktor.utils.io.ByteWriteChannel r13 = (io.ktor.utils.io.ByteWriteChannel) r13     // Catch:{ all -> 0x0211 }
            r12.L$0 = r7     // Catch:{ all -> 0x0211 }
            r12.L$1 = r6     // Catch:{ all -> 0x0211 }
            r12.L$2 = r5     // Catch:{ all -> 0x0211 }
            r12.L$3 = r4     // Catch:{ all -> 0x0211 }
            r12.L$4 = r1     // Catch:{ all -> 0x0211 }
            r8 = 3
            r12.label = r8     // Catch:{ all -> 0x0211 }
            java.lang.Object r13 = io.ktor.network.sockets.CIOReaderKt.readFrom(r13, r5, r12)     // Catch:{ all -> 0x0211 }
            if (r13 != r0) goto L_0x015e
            return r0
        L_0x015e:
            java.lang.Number r13 = (java.lang.Number) r13     // Catch:{ all -> 0x0211 }
            int r13 = r13.intValue()     // Catch:{ all -> 0x0211 }
            if (r13 == 0) goto L_0x0133
            goto L_0x00f5
        L_0x0167:
            r7.start()     // Catch:{ all -> 0x0211 }
            r13 = r6
            io.ktor.utils.io.ByteWriteChannel r13 = (io.ktor.utils.io.ByteWriteChannel) r13     // Catch:{ all -> 0x006a }
            r12.L$0 = r7     // Catch:{ all -> 0x006a }
            r12.L$1 = r7     // Catch:{ all -> 0x006a }
            r12.L$2 = r6     // Catch:{ all -> 0x006a }
            r12.L$3 = r5     // Catch:{ all -> 0x006a }
            r12.L$4 = r4     // Catch:{ all -> 0x006a }
            r12.L$5 = r1     // Catch:{ all -> 0x006a }
            r8 = 4
            r12.label = r8     // Catch:{ all -> 0x006a }
            java.lang.Object r13 = io.ktor.network.sockets.CIOReaderKt.readFrom(r13, r5, r12)     // Catch:{ all -> 0x006a }
            if (r13 != r0) goto L_0x0183
            return r0
        L_0x0183:
            r8 = r7
        L_0x0184:
            java.lang.Number r13 = (java.lang.Number) r13     // Catch:{ all -> 0x006a }
            int r13 = r13.intValue()     // Catch:{ all -> 0x006a }
            if (r13 != r2) goto L_0x0192
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6     // Catch:{ all -> 0x006a }
            io.ktor.utils.io.ByteWriteChannelKt.close(r6)     // Catch:{ all -> 0x006a }
            goto L_0x01ce
        L_0x0192:
            if (r13 > 0) goto L_0x01ce
            r6.flush()     // Catch:{ all -> 0x006a }
        L_0x0197:
            r12.L$0 = r8     // Catch:{ all -> 0x006a }
            r12.L$1 = r7     // Catch:{ all -> 0x006a }
            r12.L$2 = r6     // Catch:{ all -> 0x006a }
            r12.L$3 = r5     // Catch:{ all -> 0x006a }
            r12.L$4 = r4     // Catch:{ all -> 0x006a }
            r12.L$5 = r1     // Catch:{ all -> 0x006a }
            r13 = 5
            r12.label = r13     // Catch:{ all -> 0x006a }
            java.lang.Object r13 = io.ktor.network.sockets.CIOReaderKt.selectForRead(r4, r1, r12)     // Catch:{ all -> 0x006a }
            if (r13 != r0) goto L_0x01ad
            return r0
        L_0x01ad:
            r13 = r6
            io.ktor.utils.io.ByteWriteChannel r13 = (io.ktor.utils.io.ByteWriteChannel) r13     // Catch:{ all -> 0x006a }
            r12.L$0 = r8     // Catch:{ all -> 0x006a }
            r12.L$1 = r7     // Catch:{ all -> 0x006a }
            r12.L$2 = r6     // Catch:{ all -> 0x006a }
            r12.L$3 = r5     // Catch:{ all -> 0x006a }
            r12.L$4 = r4     // Catch:{ all -> 0x006a }
            r12.L$5 = r1     // Catch:{ all -> 0x006a }
            r9 = 6
            r12.label = r9     // Catch:{ all -> 0x006a }
            java.lang.Object r13 = io.ktor.network.sockets.CIOReaderKt.readFrom(r13, r5, r12)     // Catch:{ all -> 0x006a }
            if (r13 != r0) goto L_0x01c6
            return r0
        L_0x01c6:
            java.lang.Number r13 = (java.lang.Number) r13     // Catch:{ all -> 0x006a }
            int r13 = r13.intValue()     // Catch:{ all -> 0x006a }
            if (r13 == 0) goto L_0x0197
        L_0x01ce:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x006a }
            r7.stop()     // Catch:{ all -> 0x0211 }
            r7 = r8
            goto L_0x00f5
        L_0x01d6:
            r7.stop()     // Catch:{ all -> 0x0211 }
            throw r13     // Catch:{ all -> 0x0211 }
        L_0x01da:
            if (r7 == 0) goto L_0x01df
            r7.finish()     // Catch:{ all -> 0x0211 }
        L_0x01df:
            io.ktor.utils.io.ByteChannel r13 = r12.$channel     // Catch:{ all -> 0x0211 }
            java.lang.Throwable r13 = r13.getClosedCause()     // Catch:{ all -> 0x0211 }
            if (r13 != 0) goto L_0x0210
            io.ktor.utils.io.ByteChannel r13 = r12.$channel     // Catch:{ all -> 0x0211 }
            io.ktor.utils.io.ByteWriteChannel r13 = (io.ktor.utils.io.ByteWriteChannel) r13     // Catch:{ all -> 0x0211 }
            io.ktor.utils.io.ByteWriteChannelKt.close(r13)     // Catch:{ all -> 0x0211 }
            java.nio.channels.ReadableByteChannel r13 = r12.$nioChannel
            boolean r13 = r13 instanceof java.nio.channels.SocketChannel
            if (r13 == 0) goto L_0x020d
            boolean r13 = io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable()     // Catch:{ ClosedChannelException -> 0x020d }
            if (r13 == 0) goto L_0x0202
            java.nio.channels.ReadableByteChannel r13 = r12.$nioChannel     // Catch:{ ClosedChannelException -> 0x020d }
            java.nio.channels.SocketChannel r13 = (java.nio.channels.SocketChannel) r13     // Catch:{ ClosedChannelException -> 0x020d }
            java.nio.channels.SocketChannel unused = r13.shutdownInput()     // Catch:{ ClosedChannelException -> 0x020d }
            goto L_0x020d
        L_0x0202:
            java.nio.channels.ReadableByteChannel r13 = r12.$nioChannel     // Catch:{ ClosedChannelException -> 0x020d }
            java.nio.channels.SocketChannel r13 = (java.nio.channels.SocketChannel) r13     // Catch:{ ClosedChannelException -> 0x020d }
            java.net.Socket r13 = r13.socket()     // Catch:{ ClosedChannelException -> 0x020d }
            r13.shutdownInput()     // Catch:{ ClosedChannelException -> 0x020d }
        L_0x020d:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x0210:
            throw r13     // Catch:{ all -> 0x0211 }
        L_0x0211:
            r13 = move-exception
            java.nio.channels.ReadableByteChannel r0 = r12.$nioChannel
            boolean r0 = r0 instanceof java.nio.channels.SocketChannel
            if (r0 == 0) goto L_0x0231
            boolean r0 = io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable()     // Catch:{ ClosedChannelException -> 0x0231 }
            if (r0 == 0) goto L_0x0226
            java.nio.channels.ReadableByteChannel r0 = r12.$nioChannel     // Catch:{ ClosedChannelException -> 0x0231 }
            java.nio.channels.SocketChannel r0 = (java.nio.channels.SocketChannel) r0     // Catch:{ ClosedChannelException -> 0x0231 }
            java.nio.channels.SocketChannel unused = r0.shutdownInput()     // Catch:{ ClosedChannelException -> 0x0231 }
            goto L_0x0231
        L_0x0226:
            java.nio.channels.ReadableByteChannel r0 = r12.$nioChannel     // Catch:{ ClosedChannelException -> 0x0231 }
            java.nio.channels.SocketChannel r0 = (java.nio.channels.SocketChannel) r0     // Catch:{ ClosedChannelException -> 0x0231 }
            java.net.Socket r0 = r0.socket()     // Catch:{ ClosedChannelException -> 0x0231 }
            r0.shutdownInput()     // Catch:{ ClosedChannelException -> 0x0231 }
        L_0x0231:
            goto L_0x0233
        L_0x0232:
            throw r13
        L_0x0233:
            goto L_0x0232
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOReaderKt$attachForReadingDirectImpl$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
