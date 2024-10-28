package io.ktor.utils.io.jvm.nio;

import io.ktor.utils.io.ByteReadChannel;
import java.nio.channels.Pipe;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0005\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"copyTo", "", "Lio/ktor/utils/io/ByteReadChannel;", "pipe", "Ljava/nio/channels/Pipe;", "limit", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/channels/Pipe;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "channel", "Ljava/nio/channels/WritableByteChannel;", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/channels/WritableByteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Writing.kt */
public final class WritingKt {
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009e, code lost:
        if (r7.isClosedForRead() == false) goto L_0x0082;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object copyTo(io.ktor.utils.io.ByteReadChannel r7, java.nio.channels.WritableByteChannel r8, long r9, kotlin.coroutines.Continuation<? super java.lang.Long> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.utils.io.jvm.nio.WritingKt$copyTo$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.utils.io.jvm.nio.WritingKt$copyTo$1 r0 = (io.ktor.utils.io.jvm.nio.WritingKt$copyTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.jvm.nio.WritingKt$copyTo$1 r0 = new io.ktor.utils.io.jvm.nio.WritingKt$copyTo$1
            r0.<init>(r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 != r3) goto L_0x003d
            long r7 = r0.J$0
            java.lang.Object r9 = r0.L$2
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
            java.lang.Object r10 = r0.L$1
            kotlin.jvm.internal.Ref$LongRef r10 = (kotlin.jvm.internal.Ref.LongRef) r10
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r2 = (io.ktor.utils.io.ByteReadChannel) r2
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r10
            r6 = r2
            r2 = r9
            r9 = r7
            r7 = r6
            goto L_0x009a
        L_0x003d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r11)
            r4 = 0
            int r11 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r11 < 0) goto L_0x00ae
            boolean r11 = r8 instanceof java.nio.channels.SelectableChannel
            if (r11 == 0) goto L_0x0064
            r11 = r8
            java.nio.channels.SelectableChannel r11 = (java.nio.channels.SelectableChannel) r11
            boolean r11 = r11.isBlocking()
            if (r11 == 0) goto L_0x005c
            goto L_0x0064
        L_0x005c:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Non-blocking channels are not supported"
            r7.<init>(r8)
            throw r7
        L_0x0064:
            boolean r11 = r7.isClosedForRead()
            if (r11 == 0) goto L_0x0076
            java.lang.Throwable r7 = r7.getClosedCause()
            if (r7 != 0) goto L_0x0075
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)
            return r7
        L_0x0075:
            throw r7
        L_0x0076:
            kotlin.jvm.internal.Ref$LongRef r11 = new kotlin.jvm.internal.Ref$LongRef
            r11.<init>()
            io.ktor.utils.io.jvm.nio.WritingKt$copyTo$copy$1 r2 = new io.ktor.utils.io.jvm.nio.WritingKt$copyTo$copy$1
            r2.<init>(r9, r11, r8)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
        L_0x0082:
            long r4 = r11.element
            int r8 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r8 >= 0) goto L_0x00a0
            r0.L$0 = r7
            r0.L$1 = r11
            r0.L$2 = r2
            r0.J$0 = r9
            r0.label = r3
            r8 = 0
            java.lang.Object r8 = r7.read(r8, r2, r0)
            if (r8 != r1) goto L_0x009a
            return r1
        L_0x009a:
            boolean r8 = r7.isClosedForRead()
            if (r8 == 0) goto L_0x0082
        L_0x00a0:
            java.lang.Throwable r7 = r7.getClosedCause()
            if (r7 != 0) goto L_0x00ad
            long r7 = r11.element
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)
            return r7
        L_0x00ad:
            throw r7
        L_0x00ae:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Limit shouldn't be negative: "
            r7.<init>(r8)
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r7 = r7.toString()
            r8.<init>(r7)
            goto L_0x00c7
        L_0x00c6:
            throw r8
        L_0x00c7:
            goto L_0x00c6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.nio.WritingKt.copyTo(io.ktor.utils.io.ByteReadChannel, java.nio.channels.WritableByteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object copyTo$default(ByteReadChannel byteReadChannel, WritableByteChannel writableByteChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(byteReadChannel, writableByteChannel, j, (Continuation<? super Long>) continuation);
    }

    public static final Object copyTo(ByteReadChannel byteReadChannel, Pipe pipe, long j, Continuation<? super Long> continuation) {
        Pipe.SinkChannel sink = pipe.sink();
        Intrinsics.checkNotNullExpressionValue(sink, "pipe.sink()");
        return copyTo(byteReadChannel, (WritableByteChannel) sink, j, continuation);
    }

    public static /* synthetic */ Object copyTo$default(ByteReadChannel byteReadChannel, Pipe pipe, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(byteReadChannel, pipe, j, (Continuation<? super Long>) continuation);
    }
}
