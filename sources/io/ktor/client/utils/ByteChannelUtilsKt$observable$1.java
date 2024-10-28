package io.ktor.client.utils;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.WriterScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.utils.ByteChannelUtilsKt$observable$1", f = "ByteChannelUtils.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3}, l = {23, 24, 26, 31}, m = "invokeSuspend", n = {"$this$writer", "$this$useInstance$iv", "instance$iv", "byteArray", "total", "bytesSend", "$this$writer", "$this$useInstance$iv", "instance$iv", "byteArray", "total", "bytesSend", "read", "$this$writer", "$this$useInstance$iv", "instance$iv", "byteArray", "total", "bytesSend", "$this$useInstance$iv", "instance$iv"}, s = {"L$0", "L$1", "L$4", "L$5", "J$0", "J$1", "L$0", "L$1", "L$4", "L$5", "J$0", "J$1", "I$0", "L$0", "L$1", "L$4", "L$5", "J$0", "J$1", "L$0", "L$1"})
/* compiled from: ByteChannelUtils.kt */
final class ByteChannelUtilsKt$observable$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Long $contentLength;
    final /* synthetic */ Function3<Long, Long, Continuation<? super Unit>, Object> $listener;
    final /* synthetic */ ByteReadChannel $this_observable;
    int I$0;
    long J$0;
    long J$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteChannelUtilsKt$observable$1(Long l, ByteReadChannel byteReadChannel, Function3<? super Long, ? super Long, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super ByteChannelUtilsKt$observable$1> continuation) {
        super(2, continuation);
        this.$contentLength = l;
        this.$this_observable = byteReadChannel;
        this.$listener = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ByteChannelUtilsKt$observable$1 byteChannelUtilsKt$observable$1 = new ByteChannelUtilsKt$observable$1(this.$contentLength, this.$this_observable, this.$listener, continuation);
        byteChannelUtilsKt$observable$1.L$0 = obj;
        return byteChannelUtilsKt$observable$1;
    }

    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((ByteChannelUtilsKt$observable$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: io.ktor.utils.io.pool.ObjectPool<byte[]>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0156  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            r20 = this;
            r1 = r20
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r2 == 0) goto L_0x00af
            if (r2 == r8) goto L_0x0087
            if (r2 == r7) goto L_0x005e
            if (r2 == r6) goto L_0x002d
            if (r2 != r5) goto L_0x0025
            java.lang.Object r2 = r1.L$1
            java.lang.Object r0 = r1.L$0
            r3 = r0
            io.ktor.utils.io.pool.ObjectPool r3 = (io.ktor.utils.io.pool.ObjectPool) r3
            kotlin.ResultKt.throwOnFailure(r21)     // Catch:{ all -> 0x0022 }
            goto L_0x019c
        L_0x0022:
            r0 = move-exception
            goto L_0x01a6
        L_0x0025:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x002d:
            long r9 = r1.J$1
            long r11 = r1.J$0
            java.lang.Object r2 = r1.L$5
            byte[] r2 = (byte[]) r2
            java.lang.Object r13 = r1.L$4
            java.lang.Object r14 = r1.L$3
            kotlin.jvm.functions.Function3 r14 = (kotlin.jvm.functions.Function3) r14
            java.lang.Object r15 = r1.L$2
            io.ktor.utils.io.ByteReadChannel r15 = (io.ktor.utils.io.ByteReadChannel) r15
            java.lang.Object r5 = r1.L$1
            io.ktor.utils.io.pool.ObjectPool r5 = (io.ktor.utils.io.pool.ObjectPool) r5
            java.lang.Object r3 = r1.L$0
            io.ktor.utils.io.WriterScope r3 = (io.ktor.utils.io.WriterScope) r3
            kotlin.ResultKt.throwOnFailure(r21)     // Catch:{ all -> 0x0059 }
            r6 = r9
            r8 = 3
            r9 = r2
            r2 = r13
            r16 = r14
            r14 = r3
            r3 = r5
            r4 = r11
            r11 = r16
            r12 = r15
            r15 = 2
            goto L_0x0163
        L_0x0059:
            r0 = move-exception
            r3 = r5
            r2 = r13
            goto L_0x01a6
        L_0x005e:
            int r2 = r1.I$0
            long r3 = r1.J$1
            long r9 = r1.J$0
            java.lang.Object r5 = r1.L$5
            byte[] r5 = (byte[]) r5
            java.lang.Object r11 = r1.L$4
            java.lang.Object r12 = r1.L$3
            kotlin.jvm.functions.Function3 r12 = (kotlin.jvm.functions.Function3) r12
            java.lang.Object r13 = r1.L$2
            io.ktor.utils.io.ByteReadChannel r13 = (io.ktor.utils.io.ByteReadChannel) r13
            java.lang.Object r14 = r1.L$1
            io.ktor.utils.io.pool.ObjectPool r14 = (io.ktor.utils.io.pool.ObjectPool) r14
            java.lang.Object r15 = r1.L$0
            io.ktor.utils.io.WriterScope r15 = (io.ktor.utils.io.WriterScope) r15
            kotlin.ResultKt.throwOnFailure(r21)     // Catch:{ all -> 0x0082 }
            r6 = r14
            r14 = r15
            r15 = 2
            goto L_0x0132
        L_0x0082:
            r0 = move-exception
            r2 = r11
            r3 = r14
            goto L_0x01a6
        L_0x0087:
            long r2 = r1.J$1
            long r4 = r1.J$0
            java.lang.Object r9 = r1.L$5
            byte[] r9 = (byte[]) r9
            java.lang.Object r10 = r1.L$4
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.functions.Function3 r11 = (kotlin.jvm.functions.Function3) r11
            java.lang.Object r12 = r1.L$2
            io.ktor.utils.io.ByteReadChannel r12 = (io.ktor.utils.io.ByteReadChannel) r12
            java.lang.Object r13 = r1.L$1
            io.ktor.utils.io.pool.ObjectPool r13 = (io.ktor.utils.io.pool.ObjectPool) r13
            java.lang.Object r14 = r1.L$0
            io.ktor.utils.io.WriterScope r14 = (io.ktor.utils.io.WriterScope) r14
            kotlin.ResultKt.throwOnFailure(r21)     // Catch:{ all -> 0x00aa }
            r6 = r2
            r2 = r10
            r3 = r13
            r10 = r21
            goto L_0x00fd
        L_0x00aa:
            r0 = move-exception
            r2 = r10
            r3 = r13
            goto L_0x01a6
        L_0x00af:
            kotlin.ResultKt.throwOnFailure(r21)
            java.lang.Object r2 = r1.L$0
            io.ktor.utils.io.WriterScope r2 = (io.ktor.utils.io.WriterScope) r2
            io.ktor.utils.io.pool.ObjectPool r3 = io.ktor.utils.io.pool.ByteArrayPoolKt.getByteArrayPool()
            java.lang.Long r4 = r1.$contentLength
            io.ktor.utils.io.ByteReadChannel r5 = r1.$this_observable
            kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r9 = r1.$listener
            java.lang.Object r10 = r3.borrow()
            r11 = r10
            byte[] r11 = (byte[]) r11     // Catch:{ all -> 0x01a4 }
            if (r4 == 0) goto L_0x00ce
            long r12 = r4.longValue()     // Catch:{ all -> 0x01a4 }
            goto L_0x00d0
        L_0x00ce:
            r12 = -1
        L_0x00d0:
            r14 = r2
            r2 = r10
            r6 = 0
            r16 = r12
            r12 = r5
            r4 = r16
            r18 = r11
            r11 = r9
            r9 = r18
        L_0x00de:
            boolean r10 = r12.isClosedForRead()     // Catch:{ all -> 0x0022 }
            if (r10 != 0) goto L_0x016a
            r1.L$0 = r14     // Catch:{ all -> 0x0022 }
            r1.L$1 = r3     // Catch:{ all -> 0x0022 }
            r1.L$2 = r12     // Catch:{ all -> 0x0022 }
            r1.L$3 = r11     // Catch:{ all -> 0x0022 }
            r1.L$4 = r2     // Catch:{ all -> 0x0022 }
            r1.L$5 = r9     // Catch:{ all -> 0x0022 }
            r1.J$0 = r4     // Catch:{ all -> 0x0022 }
            r1.J$1 = r6     // Catch:{ all -> 0x0022 }
            r1.label = r8     // Catch:{ all -> 0x0022 }
            java.lang.Object r10 = io.ktor.utils.io.ByteReadChannelKt.readAvailable(r12, r9, r1)     // Catch:{ all -> 0x0022 }
            if (r10 != r0) goto L_0x00fd
            return r0
        L_0x00fd:
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ all -> 0x0022 }
            int r10 = r10.intValue()     // Catch:{ all -> 0x0022 }
            io.ktor.utils.io.ByteWriteChannel r8 = r14.getChannel()     // Catch:{ all -> 0x0022 }
            r1.L$0 = r14     // Catch:{ all -> 0x0022 }
            r1.L$1 = r3     // Catch:{ all -> 0x0022 }
            r1.L$2 = r12     // Catch:{ all -> 0x0022 }
            r1.L$3 = r11     // Catch:{ all -> 0x0022 }
            r1.L$4 = r2     // Catch:{ all -> 0x0022 }
            r1.L$5 = r9     // Catch:{ all -> 0x0022 }
            r1.J$0 = r4     // Catch:{ all -> 0x0022 }
            r1.J$1 = r6     // Catch:{ all -> 0x0022 }
            r1.I$0 = r10     // Catch:{ all -> 0x0022 }
            r15 = 2
            r1.label = r15     // Catch:{ all -> 0x0022 }
            r13 = 0
            java.lang.Object r8 = r8.writeFully(r9, r13, r10, r1)     // Catch:{ all -> 0x0022 }
            if (r8 != r0) goto L_0x0124
            return r0
        L_0x0124:
            r13 = r12
            r12 = r11
            r11 = r2
            r2 = r10
            r16 = r6
            r6 = r3
            r18 = r4
            r5 = r9
            r9 = r18
            r3 = r16
        L_0x0132:
            long r7 = (long) r2
            long r3 = r3 + r7
            java.lang.Long r2 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r3)     // Catch:{ all -> 0x0166 }
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r9)     // Catch:{ all -> 0x0166 }
            r1.L$0 = r14     // Catch:{ all -> 0x0166 }
            r1.L$1 = r6     // Catch:{ all -> 0x0166 }
            r1.L$2 = r13     // Catch:{ all -> 0x0166 }
            r1.L$3 = r12     // Catch:{ all -> 0x0166 }
            r1.L$4 = r11     // Catch:{ all -> 0x0166 }
            r1.L$5 = r5     // Catch:{ all -> 0x0166 }
            r1.J$0 = r9     // Catch:{ all -> 0x0166 }
            r1.J$1 = r3     // Catch:{ all -> 0x0166 }
            r8 = 3
            r1.label = r8     // Catch:{ all -> 0x0166 }
            java.lang.Object r2 = r12.invoke(r2, r7, r1)     // Catch:{ all -> 0x0166 }
            if (r2 != r0) goto L_0x0156
            return r0
        L_0x0156:
            r2 = r11
            r11 = r12
            r12 = r13
            r16 = r9
            r9 = r5
            r18 = r3
            r3 = r6
            r6 = r18
            r4 = r16
        L_0x0163:
            r8 = 1
            goto L_0x00de
        L_0x0166:
            r0 = move-exception
            r3 = r6
            r2 = r11
            goto L_0x01a6
        L_0x016a:
            java.lang.Throwable r8 = r12.getClosedCause()     // Catch:{ all -> 0x0022 }
            io.ktor.utils.io.ByteWriteChannel r9 = r14.getChannel()     // Catch:{ all -> 0x0022 }
            r9.close(r8)     // Catch:{ all -> 0x0022 }
            if (r8 != 0) goto L_0x019c
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x019c
            java.lang.Long r6 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r6)     // Catch:{ all -> 0x0022 }
            java.lang.Long r4 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)     // Catch:{ all -> 0x0022 }
            r1.L$0 = r3     // Catch:{ all -> 0x0022 }
            r1.L$1 = r2     // Catch:{ all -> 0x0022 }
            r5 = 0
            r1.L$2 = r5     // Catch:{ all -> 0x0022 }
            r1.L$3 = r5     // Catch:{ all -> 0x0022 }
            r1.L$4 = r5     // Catch:{ all -> 0x0022 }
            r1.L$5 = r5     // Catch:{ all -> 0x0022 }
            r5 = 4
            r1.label = r5     // Catch:{ all -> 0x0022 }
            java.lang.Object r4 = r11.invoke(r6, r4, r1)     // Catch:{ all -> 0x0022 }
            if (r4 != r0) goto L_0x019c
            return r0
        L_0x019c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0022 }
            r3.recycle(r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01a4:
            r0 = move-exception
            r2 = r10
        L_0x01a6:
            r3.recycle(r2)
            goto L_0x01ab
        L_0x01aa:
            throw r0
        L_0x01ab:
            goto L_0x01aa
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.utils.ByteChannelUtilsKt$observable$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
