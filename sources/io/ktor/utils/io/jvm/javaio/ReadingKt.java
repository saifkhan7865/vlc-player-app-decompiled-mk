package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ByteArrayPoolKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u001a+\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u000e0\fH\u0007¢\u0006\u0002\b\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"copyTo", "", "Ljava/io/InputStream;", "channel", "Lio/ktor/utils/io/ByteWriteChannel;", "limit", "(Ljava/io/InputStream;Lio/ktor/utils/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toByteReadChannel", "Lio/ktor/utils/io/ByteReadChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "", "toByteReadChannelWithArrayPool", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Reading.kt */
public final class ReadingKt {
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b2 A[EDGE_INSN: B:47:0x00b2->B:35:0x00b2 ?: BREAK  , SYNTHETIC] */
    public static final java.lang.Object copyTo(java.io.InputStream r19, io.ktor.utils.io.ByteWriteChannel r20, long r21, kotlin.coroutines.Continuation<? super java.lang.Long> r23) {
        /*
            r0 = r21
            r2 = r23
            boolean r3 = r2 instanceof io.ktor.utils.io.jvm.javaio.ReadingKt$copyTo$1
            if (r3 == 0) goto L_0x0018
            r3 = r2
            io.ktor.utils.io.jvm.javaio.ReadingKt$copyTo$1 r3 = (io.ktor.utils.io.jvm.javaio.ReadingKt$copyTo$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x0018
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001d
        L_0x0018:
            io.ktor.utils.io.jvm.javaio.ReadingKt$copyTo$1 r3 = new io.ktor.utils.io.jvm.javaio.ReadingKt$copyTo$1
            r3.<init>(r2)
        L_0x001d:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 1
            if (r5 == 0) goto L_0x0058
            if (r5 != r6) goto L_0x0050
            int r0 = r3.I$0
            long r7 = r3.J$2
            long r9 = r3.J$1
            long r11 = r3.J$0
            java.lang.Object r1 = r3.L$2
            byte[] r1 = (byte[]) r1
            java.lang.Object r5 = r3.L$1
            io.ktor.utils.io.ByteWriteChannel r5 = (io.ktor.utils.io.ByteWriteChannel) r5
            java.lang.Object r13 = r3.L$0
            java.io.InputStream r13 = (java.io.InputStream) r13
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x004d }
            r16 = r5
            r5 = r1
            r1 = r16
            r17 = r7
            r7 = r3
            r2 = r11
            r11 = r17
            goto L_0x00a9
        L_0x004d:
            r0 = move-exception
            goto L_0x00c3
        L_0x0050:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0058:
            kotlin.ResultKt.throwOnFailure(r2)
            r7 = 0
            int r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r2 < 0) goto L_0x00cb
            io.ktor.utils.io.pool.ObjectPool r2 = io.ktor.utils.io.pool.ByteArrayPoolKt.getByteArrayPool()
            java.lang.Object r2 = r2.borrow()
            byte[] r2 = (byte[]) r2
            int r5 = r2.length     // Catch:{ all -> 0x00c1 }
            long r9 = (long) r5
            r5 = r2
            r11 = r7
            r7 = r3
            r2 = r0
            r0 = r19
            r1 = r20
        L_0x0075:
            int r8 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r8 >= 0) goto L_0x00b2
            long r13 = r2 - r11
            long r13 = java.lang.Math.min(r13, r9)     // Catch:{ all -> 0x00be }
            int r8 = (int) r13     // Catch:{ all -> 0x00be }
            r13 = 0
            int r8 = r0.read(r5, r13, r8)     // Catch:{ all -> 0x00be }
            r14 = -1
            if (r8 != r14) goto L_0x0089
            goto L_0x00b2
        L_0x0089:
            if (r8 <= 0) goto L_0x0075
            r7.L$0 = r0     // Catch:{ all -> 0x00be }
            r7.L$1 = r1     // Catch:{ all -> 0x00be }
            r7.L$2 = r5     // Catch:{ all -> 0x00be }
            r7.J$0 = r2     // Catch:{ all -> 0x00be }
            r7.J$1 = r11     // Catch:{ all -> 0x00be }
            r7.J$2 = r9     // Catch:{ all -> 0x00be }
            r7.I$0 = r8     // Catch:{ all -> 0x00be }
            r7.label = r6     // Catch:{ all -> 0x00be }
            java.lang.Object r13 = r1.writeFully(r5, r13, r8, r7)     // Catch:{ all -> 0x00be }
            if (r13 != r4) goto L_0x00a2
            return r4
        L_0x00a2:
            r13 = r0
            r0 = r8
            r16 = r9
            r9 = r11
            r11 = r16
        L_0x00a9:
            long r14 = (long) r0     // Catch:{ all -> 0x00be }
            long r9 = r9 + r14
            r0 = r13
            r16 = r9
            r9 = r11
            r11 = r16
            goto L_0x0075
        L_0x00b2:
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r11)     // Catch:{ all -> 0x00be }
            io.ktor.utils.io.pool.ObjectPool r1 = io.ktor.utils.io.pool.ByteArrayPoolKt.getByteArrayPool()
            r1.recycle(r5)
            return r0
        L_0x00be:
            r0 = move-exception
            r1 = r5
            goto L_0x00c3
        L_0x00c1:
            r0 = move-exception
            r1 = r2
        L_0x00c3:
            io.ktor.utils.io.pool.ObjectPool r2 = io.ktor.utils.io.pool.ByteArrayPoolKt.getByteArrayPool()
            r2.recycle(r1)
            throw r0
        L_0x00cb:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Limit shouldn't be negative: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            goto L_0x00e4
        L_0x00e3:
            throw r1
        L_0x00e4:
            goto L_0x00e3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.javaio.ReadingKt.copyTo(java.io.InputStream, io.ktor.utils.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object copyTo$default(InputStream inputStream, ByteWriteChannel byteWriteChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(inputStream, byteWriteChannel, j, continuation);
    }

    public static /* synthetic */ ByteReadChannel toByteReadChannel$default(InputStream inputStream, CoroutineContext coroutineContext, ObjectPool objectPool, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getIO();
        }
        return toByteReadChannel(inputStream, coroutineContext, objectPool);
    }

    public static final ByteReadChannel toByteReadChannel(InputStream inputStream, CoroutineContext coroutineContext, ObjectPool<ByteBuffer> objectPool) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        Intrinsics.checkNotNullParameter(objectPool, "pool");
        return CoroutinesKt.writer((CoroutineScope) GlobalScope.INSTANCE, coroutineContext, true, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new ReadingKt$toByteReadChannel$1(objectPool, inputStream, (Continuation<? super ReadingKt$toByteReadChannel$1>) null)).getChannel();
    }

    public static /* synthetic */ ByteReadChannel toByteReadChannelWithArrayPool$default(InputStream inputStream, CoroutineContext coroutineContext, ObjectPool<byte[]> objectPool, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getIO();
        }
        if ((i & 2) != 0) {
            objectPool = ByteArrayPoolKt.getByteArrayPool();
        }
        return toByteReadChannelWithArrayPool(inputStream, coroutineContext, objectPool);
    }

    public static final ByteReadChannel toByteReadChannelWithArrayPool(InputStream inputStream, CoroutineContext coroutineContext, ObjectPool<byte[]> objectPool) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        Intrinsics.checkNotNullParameter(objectPool, "pool");
        return CoroutinesKt.writer((CoroutineScope) GlobalScope.INSTANCE, coroutineContext, true, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new ReadingKt$toByteReadChannel$2(objectPool, inputStream, (Continuation<? super ReadingKt$toByteReadChannel$2>) null)).getChannel();
    }
}
