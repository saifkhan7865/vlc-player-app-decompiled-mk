package io.ktor.util.cio;

import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.cio.InputStreamAdaptersKt$toByteReadChannel$1", f = "InputStreamAdapters.kt", i = {0, 0}, l = {34}, m = "invokeSuspend", n = {"$this$writer", "buffer"}, s = {"L$0", "L$1"})
/* compiled from: InputStreamAdapters.kt */
final class InputStreamAdaptersKt$toByteReadChannel$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObjectPool<ByteBuffer> $pool;
    final /* synthetic */ InputStream $this_toByteReadChannel;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InputStreamAdaptersKt$toByteReadChannel$1(ObjectPool<ByteBuffer> objectPool, InputStream inputStream, Continuation<? super InputStreamAdaptersKt$toByteReadChannel$1> continuation) {
        super(2, continuation);
        this.$pool = objectPool;
        this.$this_toByteReadChannel = inputStream;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        InputStreamAdaptersKt$toByteReadChannel$1 inputStreamAdaptersKt$toByteReadChannel$1 = new InputStreamAdaptersKt$toByteReadChannel$1(this.$pool, this.$this_toByteReadChannel, continuation);
        inputStreamAdaptersKt$toByteReadChannel$1.L$0 = obj;
        return inputStreamAdaptersKt$toByteReadChannel$1;
    }

    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((InputStreamAdaptersKt$toByteReadChannel$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004d A[Catch:{ all -> 0x0017, all -> 0x0082 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0075 A[EDGE_INSN: B:25:0x0075->B:19:0x0075 ?: BREAK  , SYNTHETIC] */
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
            java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1
            java.lang.Object r3 = r7.L$0
            io.ktor.utils.io.WriterScope r3 = (io.ktor.utils.io.WriterScope) r3
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0017 }
            goto L_0x0031
        L_0x0017:
            r8 = move-exception
            goto L_0x006e
        L_0x0019:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0021:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            io.ktor.utils.io.WriterScope r8 = (io.ktor.utils.io.WriterScope) r8
            io.ktor.utils.io.pool.ObjectPool<java.nio.ByteBuffer> r1 = r7.$pool
            java.lang.Object r1 = r1.borrow()
            java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1
            r3 = r8
        L_0x0031:
            r1.clear()     // Catch:{ all -> 0x0017 }
            java.io.InputStream r8 = r7.$this_toByteReadChannel     // Catch:{ all -> 0x0017 }
            byte[] r4 = r1.array()     // Catch:{ all -> 0x0017 }
            int r5 = r1.arrayOffset()     // Catch:{ all -> 0x0017 }
            int r6 = r1.position()     // Catch:{ all -> 0x0017 }
            int r5 = r5 + r6
            int r6 = r1.remaining()     // Catch:{ all -> 0x0017 }
            int r8 = r8.read(r4, r5, r6)     // Catch:{ all -> 0x0017 }
            if (r8 < 0) goto L_0x0075
            if (r8 == 0) goto L_0x0031
            int r4 = r1.position()     // Catch:{ all -> 0x0017 }
            int r4 = r4 + r8
            r1.position(r4)     // Catch:{ all -> 0x0017 }
            r1.flip()     // Catch:{ all -> 0x0017 }
            io.ktor.utils.io.ByteWriteChannel r8 = r3.getChannel()     // Catch:{ all -> 0x0017 }
            r4 = r7
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch:{ all -> 0x0017 }
            r7.L$0 = r3     // Catch:{ all -> 0x0017 }
            r7.L$1 = r1     // Catch:{ all -> 0x0017 }
            r7.label = r2     // Catch:{ all -> 0x0017 }
            java.lang.Object r8 = r8.writeFully((java.nio.ByteBuffer) r1, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r4)     // Catch:{ all -> 0x0017 }
            if (r8 != r0) goto L_0x0031
            return r0
        L_0x006e:
            io.ktor.utils.io.ByteWriteChannel r0 = r3.getChannel()     // Catch:{ all -> 0x0082 }
            r0.close(r8)     // Catch:{ all -> 0x0082 }
        L_0x0075:
            io.ktor.utils.io.pool.ObjectPool<java.nio.ByteBuffer> r8 = r7.$pool
            r8.recycle(r1)
            java.io.InputStream r8 = r7.$this_toByteReadChannel
            r8.close()
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0082:
            r8 = move-exception
            io.ktor.utils.io.pool.ObjectPool<java.nio.ByteBuffer> r0 = r7.$pool
            r0.recycle(r1)
            java.io.InputStream r0 = r7.$this_toByteReadChannel
            r0.close()
            goto L_0x008f
        L_0x008e:
            throw r8
        L_0x008f:
            goto L_0x008e
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.cio.InputStreamAdaptersKt$toByteReadChannel$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
