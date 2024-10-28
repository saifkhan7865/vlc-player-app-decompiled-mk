package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.ByteReadChannel;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"copyTo", "", "Lio/ktor/utils/io/ByteReadChannel;", "out", "Ljava/io/OutputStream;", "limit", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/io/OutputStream;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Writing.kt */
public final class WritingKt {
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0 A[Catch:{ all -> 0x00b5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object copyTo(io.ktor.utils.io.ByteReadChannel r18, java.io.OutputStream r19, long r20, kotlin.coroutines.Continuation<? super java.lang.Long> r22) {
        /*
            r0 = r20
            r2 = r22
            boolean r3 = r2 instanceof io.ktor.utils.io.jvm.javaio.WritingKt$copyTo$1
            if (r3 == 0) goto L_0x0018
            r3 = r2
            io.ktor.utils.io.jvm.javaio.WritingKt$copyTo$1 r3 = (io.ktor.utils.io.jvm.javaio.WritingKt$copyTo$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x0018
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001d
        L_0x0018:
            io.ktor.utils.io.jvm.javaio.WritingKt$copyTo$1 r3 = new io.ktor.utils.io.jvm.javaio.WritingKt$copyTo$1
            r3.<init>(r2)
        L_0x001d:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 0
            r7 = 1
            if (r5 == 0) goto L_0x0055
            if (r5 != r7) goto L_0x004d
            long r0 = r3.J$2
            long r8 = r3.J$1
            long r10 = r3.J$0
            java.lang.Object r5 = r3.L$2
            byte[] r5 = (byte[]) r5
            java.lang.Object r12 = r3.L$1
            java.io.OutputStream r12 = (java.io.OutputStream) r12
            java.lang.Object r13 = r3.L$0
            io.ktor.utils.io.ByteReadChannel r13 = (io.ktor.utils.io.ByteReadChannel) r13
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x004a }
            r14 = r8
            r8 = r5
            r5 = r3
            r16 = r0
            r1 = r12
            r0 = r13
            r12 = r16
            goto L_0x0097
        L_0x004a:
            r0 = move-exception
            goto L_0x00c5
        L_0x004d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0055:
            kotlin.ResultKt.throwOnFailure(r2)
            r8 = 0
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x00cd
            io.ktor.utils.io.pool.ObjectPool r2 = io.ktor.utils.io.pool.ByteArrayPoolKt.getByteArrayPool()
            java.lang.Object r2 = r2.borrow()
            r5 = r2
            byte[] r5 = (byte[]) r5
            int r2 = r5.length     // Catch:{ all -> 0x004a }
            long r10 = (long) r2
            r12 = r8
            r8 = r5
            r5 = r3
            r2 = r0
            r0 = r18
            r1 = r19
        L_0x0073:
            int r9 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r9 >= 0) goto L_0x00b8
            long r14 = r2 - r12
            long r14 = java.lang.Math.min(r14, r10)     // Catch:{ all -> 0x00b5 }
            int r9 = (int) r14     // Catch:{ all -> 0x00b5 }
            r5.L$0 = r0     // Catch:{ all -> 0x00b5 }
            r5.L$1 = r1     // Catch:{ all -> 0x00b5 }
            r5.L$2 = r8     // Catch:{ all -> 0x00b5 }
            r5.J$0 = r2     // Catch:{ all -> 0x00b5 }
            r5.J$1 = r12     // Catch:{ all -> 0x00b5 }
            r5.J$2 = r10     // Catch:{ all -> 0x00b5 }
            r5.label = r7     // Catch:{ all -> 0x00b5 }
            java.lang.Object r9 = r0.readAvailable(r8, r6, r9, r5)     // Catch:{ all -> 0x00b5 }
            if (r9 != r4) goto L_0x0093
            return r4
        L_0x0093:
            r14 = r12
            r12 = r10
            r10 = r2
            r2 = r9
        L_0x0097:
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ all -> 0x00b5 }
            int r2 = r2.intValue()     // Catch:{ all -> 0x00b5 }
            r3 = -1
            if (r2 == r3) goto L_0x00b2
            if (r2 <= 0) goto L_0x00ae
            r1.write(r8, r6, r2)     // Catch:{ all -> 0x00b5 }
            long r2 = (long) r2
            long r2 = r2 + r14
            r16 = r10
            r10 = r12
            r12 = r2
            r2 = r16
            goto L_0x0073
        L_0x00ae:
            r2 = r10
            r10 = r12
            r12 = r14
            goto L_0x0073
        L_0x00b2:
            r5 = r8
            r12 = r14
            goto L_0x00b9
        L_0x00b5:
            r0 = move-exception
            r5 = r8
            goto L_0x00c5
        L_0x00b8:
            r5 = r8
        L_0x00b9:
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r12)     // Catch:{ all -> 0x004a }
            io.ktor.utils.io.pool.ObjectPool r1 = io.ktor.utils.io.pool.ByteArrayPoolKt.getByteArrayPool()
            r1.recycle(r5)
            return r0
        L_0x00c5:
            io.ktor.utils.io.pool.ObjectPool r1 = io.ktor.utils.io.pool.ByteArrayPoolKt.getByteArrayPool()
            r1.recycle(r5)
            throw r0
        L_0x00cd:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Limit shouldn't be negative: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            goto L_0x00e6
        L_0x00e5:
            throw r1
        L_0x00e6:
            goto L_0x00e5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.javaio.WritingKt.copyTo(io.ktor.utils.io.ByteReadChannel, java.io.OutputStream, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object copyTo$default(ByteReadChannel byteReadChannel, OutputStream outputStream, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(byteReadChannel, outputStream, j, continuation);
    }
}
