package io.ktor.server.netty.http2;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"http2frameLoop", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/netty/handler/codec/http2/Http2DataFrame;", "bc", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-netty"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpFrameAdapter.kt */
public final class HttpFrameAdapterKt {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: io.ktor.utils.io.ByteWriteChannel} */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009d, code lost:
        r2.flush();
        r12.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a7, code lost:
        if (r14.isEndStream() == false) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00aa, code lost:
        r12 = r13;
        r13 = r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074 A[Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007f A[Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x009c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x009d A[EDGE_INSN: B:52:0x009d->B:36:0x009d ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object http2frameLoop(kotlinx.coroutines.channels.ReceiveChannel<? extends io.netty.handler.codec.http2.Http2DataFrame> r12, io.ktor.utils.io.ByteWriteChannel r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            boolean r0 = r14 instanceof io.ktor.server.netty.http2.HttpFrameAdapterKt$http2frameLoop$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            io.ktor.server.netty.http2.HttpFrameAdapterKt$http2frameLoop$1 r0 = (io.ktor.server.netty.http2.HttpFrameAdapterKt$http2frameLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            io.ktor.server.netty.http2.HttpFrameAdapterKt$http2frameLoop$1 r0 = new io.ktor.server.netty.http2.HttpFrameAdapterKt$http2frameLoop$1
            r0.<init>(r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0056
            if (r2 == r4) goto L_0x0047
            if (r2 != r3) goto L_0x003f
            java.lang.Object r12 = r0.L$3
            io.netty.buffer.ByteBuf r12 = (io.netty.buffer.ByteBuf) r12
            java.lang.Object r13 = r0.L$2
            io.netty.handler.codec.http2.Http2DataFrame r13 = (io.netty.handler.codec.http2.Http2DataFrame) r13
            java.lang.Object r2 = r0.L$1
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            r14 = r13
            r13 = r5
            goto L_0x0079
        L_0x003f:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0047:
            java.lang.Object r12 = r0.L$1
            r2 = r12
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            java.lang.Object r12 = r0.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            goto L_0x006c
        L_0x0054:
            r12 = move-exception
            goto L_0x00af
        L_0x0056:
            kotlin.ResultKt.throwOnFailure(r14)
        L_0x0059:
            r0.L$0 = r12     // Catch:{ ClosedReceiveChannelException -> 0x00b8, all -> 0x00ad }
            r0.L$1 = r13     // Catch:{ ClosedReceiveChannelException -> 0x00b8, all -> 0x00ad }
            r14 = 0
            r0.L$2 = r14     // Catch:{ ClosedReceiveChannelException -> 0x00b8, all -> 0x00ad }
            r0.L$3 = r14     // Catch:{ ClosedReceiveChannelException -> 0x00b8, all -> 0x00ad }
            r0.label = r4     // Catch:{ ClosedReceiveChannelException -> 0x00b8, all -> 0x00ad }
            java.lang.Object r14 = r12.receive(r0)     // Catch:{ ClosedReceiveChannelException -> 0x00b8, all -> 0x00ad }
            if (r14 != r1) goto L_0x006b
            return r1
        L_0x006b:
            r2 = r13
        L_0x006c:
            io.netty.handler.codec.http2.Http2DataFrame r14 = (io.netty.handler.codec.http2.Http2DataFrame) r14     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            io.netty.buffer.ByteBuf r13 = r14.content()     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            if (r13 != 0) goto L_0x0076
            io.netty.buffer.ByteBuf r13 = io.netty.buffer.Unpooled.EMPTY_BUFFER     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
        L_0x0076:
            r11 = r13
            r13 = r12
            r12 = r11
        L_0x0079:
            int r5 = r12.readableBytes()     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            if (r5 <= 0) goto L_0x009d
            io.ktor.server.netty.http2.HttpFrameAdapterKt$http2frameLoop$2 r5 = new io.ktor.server.netty.http2.HttpFrameAdapterKt$http2frameLoop$2     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            r5.<init>(r12)     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            r7 = r5
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            r0.L$0 = r13     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            r0.L$1 = r2     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            r0.L$2 = r14     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            r0.L$3 = r12     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            r0.label = r3     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            r6 = 0
            r9 = 1
            r10 = 0
            r5 = r2
            r8 = r0
            java.lang.Object r5 = io.ktor.utils.io.ByteWriteChannel.DefaultImpls.write$default(r5, r6, r7, r8, r9, r10)     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            if (r5 != r1) goto L_0x0079
            return r1
        L_0x009d:
            r2.flush()     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            r12.release()     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            boolean r12 = r14.isEndStream()     // Catch:{ ClosedReceiveChannelException -> 0x00b9, all -> 0x0054 }
            if (r12 == 0) goto L_0x00aa
            goto L_0x00b9
        L_0x00aa:
            r12 = r13
            r13 = r2
            goto L_0x0059
        L_0x00ad:
            r12 = move-exception
            r2 = r13
        L_0x00af:
            r2.close(r12)     // Catch:{ all -> 0x00b3 }
            goto L_0x00b9
        L_0x00b3:
            r12 = move-exception
            io.ktor.utils.io.ByteWriteChannelKt.close(r2)
            throw r12
        L_0x00b8:
            r2 = r13
        L_0x00b9:
            io.ktor.utils.io.ByteWriteChannelKt.close(r2)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http2.HttpFrameAdapterKt.http2frameLoop(kotlinx.coroutines.channels.ReceiveChannel, io.ktor.utils.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
