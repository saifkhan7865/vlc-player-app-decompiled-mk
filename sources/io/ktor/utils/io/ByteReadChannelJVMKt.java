package io.ktor.utils.io;

import io.ktor.utils.io.internal.JoiningState;
import io.ktor.utils.io.internal.SequentialCopyToKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a%\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a%\u0010\b\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a%\u0010\r\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"copyTo", "", "Lio/ktor/utils/io/ByteReadChannel;", "dst", "Lio/ktor/utils/io/ByteWriteChannel;", "limit", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyToImpl", "joinTo", "", "closeOnEnd", "", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinToImplSuspend", "close", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ByteReadChannelJVM.kt */
public final class ByteReadChannelJVMKt {
    public static final Object joinTo(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, boolean z, Continuation<? super Unit> continuation) {
        if (byteWriteChannel == byteReadChannel) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (!(byteReadChannel instanceof ByteBufferChannel) || !(byteWriteChannel instanceof ByteBufferChannel)) {
            Object joinToImplSuspend = joinToImplSuspend(byteReadChannel, byteWriteChannel, z, continuation);
            return joinToImplSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? joinToImplSuspend : Unit.INSTANCE;
        } else {
            Object joinFrom$ktor_io = ((ByteBufferChannel) byteWriteChannel).joinFrom$ktor_io((ByteBufferChannel) byteReadChannel, z, continuation);
            return joinFrom$ktor_io == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? joinFrom$ktor_io : Unit.INSTANCE;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: io.ktor.utils.io.ByteWriteChannel} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object joinToImplSuspend(io.ktor.utils.io.ByteReadChannel r4, io.ktor.utils.io.ByteWriteChannel r5, boolean r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteReadChannelJVMKt$joinToImplSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.utils.io.ByteReadChannelJVMKt$joinToImplSuspend$1 r0 = (io.ktor.utils.io.ByteReadChannelJVMKt$joinToImplSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteReadChannelJVMKt$joinToImplSuspend$1 r0 = new io.ktor.utils.io.ByteReadChannelJVMKt$joinToImplSuspend$1
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
            io.ktor.utils.io.ByteWriteChannel r5 = (io.ktor.utils.io.ByteWriteChannel) r5
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
            java.lang.Object r4 = copyTo(r4, r5, r2, r0)
            if (r4 != r1) goto L_0x004e
            return r1
        L_0x004e:
            if (r6 == 0) goto L_0x0054
            io.ktor.utils.io.ByteWriteChannelKt.close(r5)
            goto L_0x0057
        L_0x0054:
            r5.flush()
        L_0x0057:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelJVMKt.joinToImplSuspend(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object copyTo(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j, Continuation<? super Long> continuation) {
        if (byteReadChannel == byteWriteChannel) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (j == 0) {
            return Boxing.boxLong(0);
        } else {
            if ((byteReadChannel instanceof ByteBufferChannel) && (byteWriteChannel instanceof ByteBufferChannel)) {
                return ((ByteBufferChannel) byteWriteChannel).copyDirect$ktor_io((ByteBufferChannel) byteReadChannel, j, (JoiningState) null, continuation);
            }
            if (!(byteReadChannel instanceof ByteChannelSequentialBase) || !(byteWriteChannel instanceof ByteChannelSequentialBase)) {
                return copyToImpl(byteReadChannel, byteWriteChannel, j, continuation);
            }
            return SequentialCopyToKt.copyToSequentialImpl((ByteChannelSequentialBase) byteReadChannel, (ByteChannelSequentialBase) byteWriteChannel, Long.MAX_VALUE, continuation);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: io.ktor.utils.io.core.internal.ChunkBuffer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v9, resolved type: io.ktor.utils.io.ByteWriteChannel} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0090 A[SYNTHETIC, Splitter:B:23:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c5 A[Catch:{ all -> 0x0069 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ec A[SYNTHETIC, Splitter:B:37:0x00ec] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object copyToImpl(io.ktor.utils.io.ByteReadChannel r19, io.ktor.utils.io.ByteWriteChannel r20, long r21, kotlin.coroutines.Continuation<? super java.lang.Long> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof io.ktor.utils.io.ByteReadChannelJVMKt$copyToImpl$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.utils.io.ByteReadChannelJVMKt$copyToImpl$1 r1 = (io.ktor.utils.io.ByteReadChannelJVMKt$copyToImpl$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.utils.io.ByteReadChannelJVMKt$copyToImpl$1 r1 = new io.ktor.utils.io.ByteReadChannelJVMKt$copyToImpl$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 0
            r6 = 2
            r7 = 1
            if (r3 == 0) goto L_0x006c
            if (r3 == r7) goto L_0x0051
            if (r3 != r6) goto L_0x0049
            int r3 = r1.I$1
            long r8 = r1.J$1
            int r10 = r1.I$0
            long r11 = r1.J$0
            java.lang.Object r13 = r1.L$2
            io.ktor.utils.io.core.internal.ChunkBuffer r13 = (io.ktor.utils.io.core.internal.ChunkBuffer) r13
            java.lang.Object r14 = r1.L$1
            io.ktor.utils.io.ByteWriteChannel r14 = (io.ktor.utils.io.ByteWriteChannel) r14
            java.lang.Object r15 = r1.L$0
            io.ktor.utils.io.ByteReadChannel r15 = (io.ktor.utils.io.ByteReadChannel) r15
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0069 }
            r4 = r1
            r1 = r14
            r0 = r15
            goto L_0x00e8
        L_0x0049:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0051:
            long r8 = r1.J$1
            int r3 = r1.I$0
            long r10 = r1.J$0
            java.lang.Object r12 = r1.L$2
            r13 = r12
            io.ktor.utils.io.core.internal.ChunkBuffer r13 = (io.ktor.utils.io.core.internal.ChunkBuffer) r13
            java.lang.Object r12 = r1.L$1
            r14 = r12
            io.ktor.utils.io.ByteWriteChannel r14 = (io.ktor.utils.io.ByteWriteChannel) r14
            java.lang.Object r12 = r1.L$0
            io.ktor.utils.io.ByteReadChannel r12 = (io.ktor.utils.io.ByteReadChannel) r12
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0069 }
            goto L_0x00bc
        L_0x0069:
            r0 = move-exception
            goto L_0x0113
        L_0x006c:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r0 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r0 = r0.getPool()
            java.lang.Object r0 = r0.borrow()
            io.ktor.utils.io.core.internal.ChunkBuffer r0 = (io.ktor.utils.io.core.internal.ChunkBuffer) r0
            boolean r3 = r20.getAutoFlush()
            r3 = r3 ^ r7
            r8 = r21
            r13 = r0
            r10 = r3
            r11 = r4
            r0 = r19
            r3 = r1
            r1 = r20
        L_0x008a:
            long r14 = r8 - r11
            int r16 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r16 == 0) goto L_0x0104
            int r4 = r13.getCapacity()     // Catch:{ all -> 0x00f6 }
            long r4 = (long) r4     // Catch:{ all -> 0x00f6 }
            long r4 = java.lang.Math.min(r4, r14)     // Catch:{ all -> 0x00f6 }
            int r5 = (int) r4     // Catch:{ all -> 0x00f6 }
            r13.resetForWrite(r5)     // Catch:{ all -> 0x00f6 }
            r3.L$0 = r0     // Catch:{ all -> 0x00f6 }
            r3.L$1 = r1     // Catch:{ all -> 0x00f6 }
            r3.L$2 = r13     // Catch:{ all -> 0x00f6 }
            r3.J$0 = r8     // Catch:{ all -> 0x00f6 }
            r3.I$0 = r10     // Catch:{ all -> 0x00f6 }
            r3.J$1 = r11     // Catch:{ all -> 0x00f6 }
            r3.label = r7     // Catch:{ all -> 0x00f6 }
            java.lang.Object r4 = r0.readAvailable((io.ktor.utils.io.core.internal.ChunkBuffer) r13, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r3)     // Catch:{ all -> 0x00f6 }
            if (r4 != r2) goto L_0x00b2
            return r2
        L_0x00b2:
            r14 = r1
            r1 = r3
            r3 = r10
            r17 = r11
            r12 = r0
            r0 = r4
            r10 = r8
            r8 = r17
        L_0x00bc:
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ all -> 0x0069 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x0069 }
            r4 = -1
            if (r0 == r4) goto L_0x0102
            r4 = r13
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0069 }
            r1.L$0 = r12     // Catch:{ all -> 0x0069 }
            r1.L$1 = r14     // Catch:{ all -> 0x0069 }
            r1.L$2 = r13     // Catch:{ all -> 0x0069 }
            r1.J$0 = r10     // Catch:{ all -> 0x0069 }
            r1.I$0 = r3     // Catch:{ all -> 0x0069 }
            r1.J$1 = r8     // Catch:{ all -> 0x0069 }
            r1.I$1 = r0     // Catch:{ all -> 0x0069 }
            r1.label = r6     // Catch:{ all -> 0x0069 }
            java.lang.Object r4 = r14.writeFully((io.ktor.utils.io.core.Buffer) r4, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r1)     // Catch:{ all -> 0x0069 }
            if (r4 != r2) goto L_0x00df
            return r2
        L_0x00df:
            r4 = r1
            r1 = r14
            r17 = r3
            r3 = r0
            r0 = r12
            r11 = r10
            r10 = r17
        L_0x00e8:
            long r14 = (long) r3
            long r8 = r8 + r14
            if (r10 == 0) goto L_0x00f9
            int r3 = r0.getAvailableForRead()     // Catch:{ all -> 0x00f6 }
            if (r3 != 0) goto L_0x00f9
            r1.flush()     // Catch:{ all -> 0x00f6 }
            goto L_0x00f9
        L_0x00f6:
            r0 = move-exception
            r14 = r1
            goto L_0x0113
        L_0x00f9:
            r3 = r4
            r4 = 0
            r17 = r8
            r8 = r11
            r11 = r17
            goto L_0x008a
        L_0x0102:
            r11 = r8
            goto L_0x0105
        L_0x0104:
            r14 = r1
        L_0x0105:
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r11)     // Catch:{ all -> 0x0069 }
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r1 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r1 = r1.getPool()
            r13.release(r1)
            return r0
        L_0x0113:
            r14.close(r0)     // Catch:{ all -> 0x0117 }
            throw r0     // Catch:{ all -> 0x0117 }
        L_0x0117:
            r0 = move-exception
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r1 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r1 = r1.getPool()
            r13.release(r1)
            goto L_0x0123
        L_0x0122:
            throw r0
        L_0x0123:
            goto L_0x0122
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelJVMKt.copyToImpl(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
