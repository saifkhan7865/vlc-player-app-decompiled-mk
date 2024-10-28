package io.ktor.utils.io;

import io.ktor.utils.io.internal.UtilsKt;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a-\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u001d\u0010\r\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u0014\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u001c\u0010\u0010\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0011\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"readUntilDelimiter", "", "Lio/ktor/utils/io/ByteReadChannel;", "delimiter", "Ljava/nio/ByteBuffer;", "dst", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readUntilDelimiterSuspend", "copied0", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "skipDelimiter", "", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "skipDelimiterSuspend", "startsWithDelimiter", "Lio/ktor/utils/io/LookAheadSession;", "tryCopyUntilDelimiter", "tryEnsureDelimiter", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Delimited.kt */
public final class DelimitedKt {
    public static final Object readUntilDelimiter(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, Continuation<? super Integer> continuation) {
        int i;
        if (!byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (byteBuffer != byteBuffer2) {
            Ref.IntRef intRef = new Ref.IntRef();
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            byteReadChannel.lookAhead(new DelimitedKt$readUntilDelimiter$2(byteBuffer, byteBuffer2, booleanRef, intRef));
            if (intRef.element == 0 && byteReadChannel.isClosedForRead()) {
                i = -1;
            } else if (byteBuffer2.hasRemaining() && !booleanRef.element) {
                return readUntilDelimiterSuspend(byteReadChannel, byteBuffer, byteBuffer2, intRef.element, continuation);
            } else {
                i = intRef.element;
            }
            return Boxing.boxInt(i);
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public static final Object skipDelimiter(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, Continuation<? super Unit> continuation) {
        if (byteBuffer.hasRemaining()) {
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            byteReadChannel.lookAhead(new DelimitedKt$skipDelimiter$2(booleanRef, byteBuffer));
            if (booleanRef.element) {
                return Unit.INSTANCE;
            }
            Object skipDelimiterSuspend = skipDelimiterSuspend(byteReadChannel, byteBuffer, continuation);
            return skipDelimiterSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? skipDelimiterSuspend : Unit.INSTANCE;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* access modifiers changed from: private */
    public static final Object skipDelimiterSuspend(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, Continuation<? super Unit> continuation) {
        Object lookAheadSuspend = byteReadChannel.lookAheadSuspend(new DelimitedKt$skipDelimiterSuspend$2(byteBuffer, (Continuation<? super DelimitedKt$skipDelimiterSuspend$2>) null), continuation);
        return lookAheadSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? lookAheadSuspend : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object readUntilDelimiterSuspend(io.ktor.utils.io.ByteReadChannel r16, java.nio.ByteBuffer r17, java.nio.ByteBuffer r18, int r19, kotlin.coroutines.Continuation<? super java.lang.Integer> r20) {
        /*
            r7 = r16
            r8 = r18
            r0 = r20
            boolean r1 = r0 instanceof io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$1
            if (r1 == 0) goto L_0x001a
            r1 = r0
            io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$1 r1 = (io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x001a
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001f
        L_0x001a:
            io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$1 r1 = new io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$1
            r1.<init>(r0)
        L_0x001f:
            r9 = r1
            java.lang.Object r0 = r9.result
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r11 = 2
            r12 = 1
            if (r1 == 0) goto L_0x004f
            if (r1 == r12) goto L_0x003f
            if (r1 != r11) goto L_0x0037
            int r1 = r9.I$0
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00aa
        L_0x0037:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003f:
            java.lang.Object r1 = r9.L$2
            kotlin.jvm.internal.Ref$BooleanRef r1 = (kotlin.jvm.internal.Ref.BooleanRef) r1
            java.lang.Object r2 = r9.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r3 = r9.L$0
            io.ktor.utils.io.ByteReadChannel r3 = (io.ktor.utils.io.ByteReadChannel) r3
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0083
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r0)
            java.lang.String r0 = "Failed requirement."
            r2 = r17
            if (r2 == r8) goto L_0x00cf
            if (r19 < 0) goto L_0x00c5
            kotlin.jvm.internal.Ref$BooleanRef r13 = new kotlin.jvm.internal.Ref$BooleanRef
            r13.<init>()
            io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$copied$1 r14 = new io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$copied$1
            r6 = 0
            r0 = r14
            r1 = r19
            r2 = r17
            r3 = r18
            r4 = r13
            r5 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6)
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14
            r9.L$0 = r7
            r9.L$1 = r8
            r9.L$2 = r13
            r9.label = r12
            java.lang.Object r0 = r7.lookAheadSuspend(r14, r9)
            if (r0 != r10) goto L_0x0080
            return r10
        L_0x0080:
            r3 = r7
            r2 = r8
            r1 = r13
        L_0x0083:
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            if (r0 <= 0) goto L_0x00b7
            boolean r4 = r3.isClosedForWrite()
            if (r4 == 0) goto L_0x00b7
            boolean r1 = r1.element
            if (r1 != 0) goto L_0x00b7
            r1 = 0
            r9.L$0 = r1
            r9.L$1 = r1
            r9.L$2 = r1
            r9.I$0 = r0
            r9.label = r11
            java.lang.Object r1 = r3.readAvailable((java.nio.ByteBuffer) r2, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r9)
            if (r1 != r10) goto L_0x00a7
            return r10
        L_0x00a7:
            r15 = r1
            r1 = r0
            r0 = r15
        L_0x00aa:
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            r2 = 0
            int r0 = kotlin.ranges.RangesKt.coerceAtLeast((int) r0, (int) r2)
            int r0 = r0 + r1
            goto L_0x00c0
        L_0x00b7:
            if (r0 != 0) goto L_0x00c0
            boolean r1 = r3.isClosedForRead()
            if (r1 == 0) goto L_0x00c0
            r0 = -1
        L_0x00c0:
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            return r0
        L_0x00c5:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x00cf:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.DelimitedKt.readUntilDelimiterSuspend(io.ktor.utils.io.ByteReadChannel, java.nio.ByteBuffer, java.nio.ByteBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final int tryCopyUntilDelimiter(LookAheadSession lookAheadSession, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int i;
        boolean z = false;
        ByteBuffer request = lookAheadSession.request(0, 1);
        if (request == null) {
            return 0;
        }
        int indexOfPartial = UtilsKt.indexOfPartial(request, byteBuffer);
        if (indexOfPartial != -1) {
            int min = Math.min(request.remaining() - indexOfPartial, byteBuffer.remaining());
            int remaining = byteBuffer.remaining() - min;
            if (remaining == 0) {
                i = UtilsKt.putLimited(byteBuffer2, request, request.position() + indexOfPartial);
            } else {
                ByteBuffer duplicate = request.duplicate();
                ByteBuffer request2 = lookAheadSession.request(indexOfPartial + min, 1);
                if (request2 == null) {
                    Intrinsics.checkNotNullExpressionValue(duplicate, "remembered");
                    i = UtilsKt.putLimited(byteBuffer2, duplicate, duplicate.position() + indexOfPartial);
                } else if (!UtilsKt.startsWith(request2, byteBuffer, min)) {
                    Intrinsics.checkNotNullExpressionValue(duplicate, "remembered");
                    i = UtilsKt.putLimited(byteBuffer2, duplicate, duplicate.position() + indexOfPartial + 1);
                } else if (request2.remaining() >= remaining) {
                    Intrinsics.checkNotNullExpressionValue(duplicate, "remembered");
                    i = UtilsKt.putLimited(byteBuffer2, duplicate, duplicate.position() + indexOfPartial);
                } else {
                    Intrinsics.checkNotNullExpressionValue(duplicate, "remembered");
                    i = UtilsKt.putLimited(byteBuffer2, duplicate, duplicate.position() + indexOfPartial);
                }
            }
            z = true;
        } else {
            i = UtilsKt.putAtMost$default(byteBuffer2, request, 0, 2, (Object) null);
        }
        lookAheadSession.consumed(i);
        return z ? -i : i;
    }

    /* access modifiers changed from: private */
    public static final int tryEnsureDelimiter(LookAheadSession lookAheadSession, ByteBuffer byteBuffer) {
        int startsWithDelimiter = startsWithDelimiter(lookAheadSession, byteBuffer);
        if (startsWithDelimiter == -1) {
            throw new IOException("Failed to skip delimiter: actual bytes differ from delimiter bytes");
        } else if (startsWithDelimiter < byteBuffer.remaining()) {
            return startsWithDelimiter;
        } else {
            lookAheadSession.consumed(byteBuffer.remaining());
            return byteBuffer.remaining();
        }
    }

    /* access modifiers changed from: private */
    public static final int startsWithDelimiter(LookAheadSession lookAheadSession, ByteBuffer byteBuffer) {
        ByteBuffer request = lookAheadSession.request(0, 1);
        if (request == null) {
            return 0;
        }
        int indexOfPartial = UtilsKt.indexOfPartial(request, byteBuffer);
        if (indexOfPartial != 0) {
            return -1;
        }
        int min = Math.min(request.remaining() - indexOfPartial, byteBuffer.remaining());
        int remaining = byteBuffer.remaining() - min;
        if (remaining > 0) {
            ByteBuffer request2 = lookAheadSession.request(indexOfPartial + min, remaining);
            if (request2 == null) {
                return min;
            }
            if (!UtilsKt.startsWith(request2, byteBuffer, min)) {
                return -1;
            }
        }
        return byteBuffer.remaining();
    }
}
