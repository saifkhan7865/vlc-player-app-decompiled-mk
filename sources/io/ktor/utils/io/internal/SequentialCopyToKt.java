package io.ktor.utils.io.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a%\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a%\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"copyToSequentialImpl", "", "Lio/ktor/utils/io/ByteChannelSequentialBase;", "dst", "limit", "(Lio/ktor/utils/io/ByteChannelSequentialBase;Lio/ktor/utils/io/ByteChannelSequentialBase;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyToTail", "joinToImpl", "", "closeOnEnd", "", "(Lio/ktor/utils/io/ByteChannelSequentialBase;Lio/ktor/utils/io/ByteChannelSequentialBase;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SequentialCopyTo.kt */
public final class SequentialCopyToKt {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: io.ktor.utils.io.ByteChannelSequentialBase} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object joinToImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, io.ktor.utils.io.ByteChannelSequentialBase r5, boolean r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.internal.SequentialCopyToKt$joinToImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.utils.io.internal.SequentialCopyToKt$joinToImpl$1 r0 = (io.ktor.utils.io.internal.SequentialCopyToKt$joinToImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.internal.SequentialCopyToKt$joinToImpl$1 r0 = new io.ktor.utils.io.internal.SequentialCopyToKt$joinToImpl$1
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            boolean r6 = r0.Z$0
            java.lang.Object r4 = r0.L$0
            r5 = r4
            io.ktor.utils.io.ByteChannelSequentialBase r5 = (io.ktor.utils.io.ByteChannelSequentialBase) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x004e
        L_0x0031:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.Z$0 = r6
            r0.label = r3
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Object r4 = copyToSequentialImpl(r4, r5, r2, r0)
            if (r4 != r1) goto L_0x004e
            return r1
        L_0x004e:
            if (r6 == 0) goto L_0x0055
            io.ktor.utils.io.ByteWriteChannel r5 = (io.ktor.utils.io.ByteWriteChannel) r5
            io.ktor.utils.io.ByteWriteChannelKt.close(r5)
        L_0x0055:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.SequentialCopyToKt.joinToImpl(io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.ByteChannelSequentialBase, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object copyToSequentialImpl(io.ktor.utils.io.ByteChannelSequentialBase r18, io.ktor.utils.io.ByteChannelSequentialBase r19, long r20, kotlin.coroutines.Continuation<? super java.lang.Long> r22) {
        /*
            r0 = r19
            r1 = r22
            boolean r2 = r1 instanceof io.ktor.utils.io.internal.SequentialCopyToKt$copyToSequentialImpl$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            io.ktor.utils.io.internal.SequentialCopyToKt$copyToSequentialImpl$1 r2 = (io.ktor.utils.io.internal.SequentialCopyToKt$copyToSequentialImpl$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            io.ktor.utils.io.internal.SequentialCopyToKt$copyToSequentialImpl$1 r2 = new io.ktor.utils.io.internal.SequentialCopyToKt$copyToSequentialImpl$1
            r2.<init>(r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 3
            r6 = 2
            r7 = 1
            r8 = 0
            if (r4 == 0) goto L_0x0070
            if (r4 == r7) goto L_0x005e
            if (r4 == r6) goto L_0x004d
            if (r4 != r5) goto L_0x0045
            long r10 = r2.J$2
            long r12 = r2.J$1
            long r14 = r2.J$0
            java.lang.Object r0 = r2.L$1
            io.ktor.utils.io.ByteChannelSequentialBase r0 = (io.ktor.utils.io.ByteChannelSequentialBase) r0
            java.lang.Object r4 = r2.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00f4
        L_0x0045:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004d:
            long r10 = r2.J$1
            long r12 = r2.J$0
            java.lang.Object r0 = r2.L$1
            io.ktor.utils.io.ByteChannelSequentialBase r0 = (io.ktor.utils.io.ByteChannelSequentialBase) r0
            java.lang.Object r4 = r2.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00ce
        L_0x005e:
            long r10 = r2.J$1
            long r12 = r2.J$0
            java.lang.Object r0 = r2.L$1
            io.ktor.utils.io.ByteChannelSequentialBase r0 = (io.ktor.utils.io.ByteChannelSequentialBase) r0
            java.lang.Object r4 = r2.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            kotlin.ResultKt.throwOnFailure(r1)
            r14 = r12
            r12 = r10
            goto L_0x00a8
        L_0x0070:
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r18
            if (r1 == r0) goto L_0x010a
            java.lang.Throwable r4 = r18.getClosedCause()
            if (r4 == 0) goto L_0x0089
            java.lang.Throwable r1 = r18.getClosedCause()
            r0.close(r1)
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r8)
            return r0
        L_0x0089:
            r10 = r20
            r12 = r10
        L_0x008c:
            int r4 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r4 <= 0) goto L_0x0104
            r2.L$0 = r1
            r2.L$1 = r0
            r2.J$0 = r12
            r2.J$1 = r10
            r2.label = r7
            java.lang.Object r4 = r1.awaitInternalAtLeast1$ktor_io(r2)
            if (r4 != r3) goto L_0x00a1
            return r3
        L_0x00a1:
            r14 = r12
            r12 = r10
            r16 = r4
            r4 = r1
            r1 = r16
        L_0x00a8:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x00b3
            r10 = r12
            r12 = r14
            goto L_0x0104
        L_0x00b3:
            long r10 = r4.transferTo$ktor_io(r0, r12)
            int r1 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r1 != 0) goto L_0x00db
            r2.L$0 = r4
            r2.L$1 = r0
            r2.J$0 = r14
            r2.J$1 = r12
            r2.label = r6
            java.lang.Object r1 = copyToTail(r4, r0, r12, r2)
            if (r1 != r3) goto L_0x00cc
            return r3
        L_0x00cc:
            r10 = r12
            r12 = r14
        L_0x00ce:
            java.lang.Number r1 = (java.lang.Number) r1
            long r14 = r1.longValue()
            int r1 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r1 != 0) goto L_0x00d9
            goto L_0x0104
        L_0x00d9:
            r1 = r4
            goto L_0x00fb
        L_0x00db:
            int r1 = r0.getAvailableForWrite()
            if (r1 != 0) goto L_0x00f4
            r2.L$0 = r4
            r2.L$1 = r0
            r2.J$0 = r14
            r2.J$1 = r12
            r2.J$2 = r10
            r2.label = r5
            java.lang.Object r1 = r0.awaitAtLeastNBytesAvailableForWrite$ktor_io(r7, r2)
            if (r1 != r3) goto L_0x00f4
            return r3
        L_0x00f4:
            r1 = r4
            r16 = r12
            r12 = r14
            r14 = r10
            r10 = r16
        L_0x00fb:
            long r10 = r10 - r14
            int r4 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r4 <= 0) goto L_0x008c
            r0.flush()
            goto L_0x008c
        L_0x0104:
            long r12 = r12 - r10
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r12)
            return r0
        L_0x010a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Failed requirement."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            goto L_0x0117
        L_0x0116:
            throw r0
        L_0x0117:
            goto L_0x0116
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.SequentialCopyToKt.copyToSequentialImpl(io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.ByteChannelSequentialBase, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: io.ktor.utils.io.core.internal.ChunkBuffer} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0081 A[Catch:{ all -> 0x00bd }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object copyToTail(io.ktor.utils.io.ByteChannelSequentialBase r8, io.ktor.utils.io.ByteChannelSequentialBase r9, long r10, kotlin.coroutines.Continuation<? super java.lang.Long> r12) {
        /*
            boolean r0 = r12 instanceof io.ktor.utils.io.internal.SequentialCopyToKt$copyToTail$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            io.ktor.utils.io.internal.SequentialCopyToKt$copyToTail$1 r0 = (io.ktor.utils.io.internal.SequentialCopyToKt$copyToTail$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.internal.SequentialCopyToKt$copyToTail$1 r0 = new io.ktor.utils.io.internal.SequentialCopyToKt$copyToTail$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004f
            if (r2 == r4) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.core.internal.ChunkBuffer r9 = (io.ktor.utils.io.core.internal.ChunkBuffer) r9
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x004d }
            goto L_0x00ae
        L_0x0034:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003c:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            io.ktor.utils.io.core.internal.ChunkBuffer r9 = (io.ktor.utils.io.core.internal.ChunkBuffer) r9
            java.lang.Object r8 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r8 = (io.ktor.utils.io.ByteChannelSequentialBase) r8
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x004d }
            r7 = r9
            r9 = r8
            r8 = r12
            r12 = r7
            goto L_0x0078
        L_0x004d:
            r8 = move-exception
            goto L_0x00bf
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r12)
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r12 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r12 = r12.getPool()
            java.lang.Object r12 = r12.borrow()
            io.ktor.utils.io.core.internal.ChunkBuffer r12 = (io.ktor.utils.io.core.internal.ChunkBuffer) r12
            int r2 = r12.getCapacity()     // Catch:{ all -> 0x00bd }
            long r5 = (long) r2     // Catch:{ all -> 0x00bd }
            long r10 = kotlin.ranges.RangesKt.coerceAtMost((long) r10, (long) r5)     // Catch:{ all -> 0x00bd }
            int r11 = (int) r10     // Catch:{ all -> 0x00bd }
            r12.resetForWrite(r11)     // Catch:{ all -> 0x00bd }
            r0.L$0 = r9     // Catch:{ all -> 0x00bd }
            r0.L$1 = r12     // Catch:{ all -> 0x00bd }
            r0.label = r4     // Catch:{ all -> 0x00bd }
            java.lang.Object r8 = r8.readAvailable(r12, r0)     // Catch:{ all -> 0x00bd }
            if (r8 != r1) goto L_0x0078
            return r1
        L_0x0078:
            java.lang.Number r8 = (java.lang.Number) r8     // Catch:{ all -> 0x00bd }
            int r8 = r8.intValue()     // Catch:{ all -> 0x00bd }
            r10 = -1
            if (r8 != r10) goto L_0x009a
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r8 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion     // Catch:{ all -> 0x00bd }
            io.ktor.utils.io.pool.ObjectPool r8 = r8.getPool()     // Catch:{ all -> 0x00bd }
            r12.release(r8)     // Catch:{ all -> 0x00bd }
            r8 = 0
            java.lang.Long r8 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r8)     // Catch:{ all -> 0x00bd }
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r9 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r9 = r9.getPool()
            r12.release(r9)
            return r8
        L_0x009a:
            r10 = r12
            io.ktor.utils.io.core.Buffer r10 = (io.ktor.utils.io.core.Buffer) r10     // Catch:{ all -> 0x00bd }
            r0.L$0 = r12     // Catch:{ all -> 0x00bd }
            r11 = 0
            r0.L$1 = r11     // Catch:{ all -> 0x00bd }
            r0.I$0 = r8     // Catch:{ all -> 0x00bd }
            r0.label = r3     // Catch:{ all -> 0x00bd }
            java.lang.Object r9 = r9.writeFully(r10, r0)     // Catch:{ all -> 0x00bd }
            if (r9 != r1) goto L_0x00ad
            return r1
        L_0x00ad:
            r9 = r12
        L_0x00ae:
            long r10 = (long) r8
            java.lang.Long r8 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r10)     // Catch:{ all -> 0x004d }
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r10 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r10 = r10.getPool()
            r9.release(r10)
            return r8
        L_0x00bd:
            r8 = move-exception
            r9 = r12
        L_0x00bf:
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r10 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r10 = r10.getPool()
            r9.release(r10)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.SequentialCopyToKt.copyToTail(io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.ByteChannelSequentialBase, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
