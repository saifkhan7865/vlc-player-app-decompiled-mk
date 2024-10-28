package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.jvm.javaio.ReadingKt$toByteReadChannel$2", f = "Reading.kt", i = {0, 0}, l = {90}, m = "invokeSuspend", n = {"$this$writer", "buffer"}, s = {"L$0", "L$1"})
/* compiled from: Reading.kt */
final class ReadingKt$toByteReadChannel$2 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObjectPool<byte[]> $pool;
    final /* synthetic */ InputStream $this_toByteReadChannel;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReadingKt$toByteReadChannel$2(ObjectPool<byte[]> objectPool, InputStream inputStream, Continuation<? super ReadingKt$toByteReadChannel$2> continuation) {
        super(2, continuation);
        this.$pool = objectPool;
        this.$this_toByteReadChannel = inputStream;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ReadingKt$toByteReadChannel$2 readingKt$toByteReadChannel$2 = new ReadingKt$toByteReadChannel$2(this.$pool, this.$this_toByteReadChannel, continuation);
        readingKt$toByteReadChannel$2.L$0 = obj;
        return readingKt$toByteReadChannel$2;
    }

    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((ReadingKt$toByteReadChannel$2) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003b A[Catch:{ all -> 0x0017, all -> 0x006b }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0051 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L_0x0021
            if (r1 != r2) goto L_0x0019
            java.lang.Object r1 = r7.L$1
            byte[] r1 = (byte[]) r1
            java.lang.Object r3 = r7.L$0
            io.ktor.utils.io.WriterScope r3 = (io.ktor.utils.io.WriterScope) r3
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0017 }
            goto L_0x0031
        L_0x0017:
            r8 = move-exception
            goto L_0x0057
        L_0x0019:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0021:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            io.ktor.utils.io.WriterScope r8 = (io.ktor.utils.io.WriterScope) r8
            io.ktor.utils.io.pool.ObjectPool<byte[]> r1 = r7.$pool
            java.lang.Object r1 = r1.borrow()
            byte[] r1 = (byte[]) r1
            r3 = r8
        L_0x0031:
            java.io.InputStream r8 = r7.$this_toByteReadChannel     // Catch:{ all -> 0x0017 }
            int r4 = r1.length     // Catch:{ all -> 0x0017 }
            r5 = 0
            int r8 = r8.read(r1, r5, r4)     // Catch:{ all -> 0x0017 }
            if (r8 < 0) goto L_0x0051
            if (r8 == 0) goto L_0x0031
            io.ktor.utils.io.ByteWriteChannel r4 = r3.getChannel()     // Catch:{ all -> 0x0017 }
            r6 = r7
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch:{ all -> 0x0017 }
            r7.L$0 = r3     // Catch:{ all -> 0x0017 }
            r7.L$1 = r1     // Catch:{ all -> 0x0017 }
            r7.label = r2     // Catch:{ all -> 0x0017 }
            java.lang.Object r8 = r4.writeFully(r1, r5, r8, r6)     // Catch:{ all -> 0x0017 }
            if (r8 != r0) goto L_0x0031
            return r0
        L_0x0051:
            io.ktor.utils.io.pool.ObjectPool<byte[]> r8 = r7.$pool
            r8.recycle(r1)
            goto L_0x0063
        L_0x0057:
            io.ktor.utils.io.ByteWriteChannel r0 = r3.getChannel()     // Catch:{ all -> 0x006b }
            r0.close(r8)     // Catch:{ all -> 0x006b }
            io.ktor.utils.io.pool.ObjectPool<byte[]> r8 = r7.$pool
            r8.recycle(r1)
        L_0x0063:
            java.io.InputStream r8 = r7.$this_toByteReadChannel
            r8.close()
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x006b:
            r8 = move-exception
            io.ktor.utils.io.pool.ObjectPool<byte[]> r0 = r7.$pool
            r0.recycle(r1)
            java.io.InputStream r0 = r7.$this_toByteReadChannel
            r0.close()
            goto L_0x0078
        L_0x0077:
            throw r8
        L_0x0078:
            goto L_0x0077
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.javaio.ReadingKt$toByteReadChannel$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
