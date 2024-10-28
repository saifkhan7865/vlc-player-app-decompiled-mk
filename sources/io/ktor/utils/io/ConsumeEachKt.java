package io.ktor.utils.io;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aQ\u0010\u0000\u001a\u00020\u0001*\u00020\u00022:\u0010\u0003\u001a6\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\t0\u0004j\u0002`\u000bHHø\u0001\u0000¢\u0006\u0002\u0010\f*j\u0010\r\"2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\t0\u000422\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\t0\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"consumeEachBufferRange", "", "Lio/ktor/utils/io/ByteReadChannel;", "visitor", "Lkotlin/Function2;", "Ljava/nio/ByteBuffer;", "Lkotlin/ParameterName;", "name", "buffer", "", "last", "Lio/ktor/utils/io/ConsumeEachBufferVisitor;", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ConsumeEachBufferVisitor", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConsumeEach.kt */
public final class ConsumeEachKt {
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d7 A[Catch:{ all -> 0x013e }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00dd A[Catch:{ all -> 0x013e }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f3 A[Catch:{ all -> 0x013e }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f5 A[Catch:{ all -> 0x013e }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0157 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object consumeEachBufferRange(io.ktor.utils.io.ByteReadChannel r18, kotlin.jvm.functions.Function2<? super java.nio.ByteBuffer, ? super java.lang.Boolean, java.lang.Boolean> r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof io.ktor.utils.io.ConsumeEachKt$consumeEachBufferRange$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.utils.io.ConsumeEachKt$consumeEachBufferRange$1 r1 = (io.ktor.utils.io.ConsumeEachKt$consumeEachBufferRange$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.utils.io.ConsumeEachKt$consumeEachBufferRange$1 r1 = new io.ktor.utils.io.ConsumeEachKt$consumeEachBufferRange$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 3
            r5 = 2
            r6 = 0
            r7 = 1
            r8 = 0
            if (r3 == 0) goto L_0x008a
            if (r3 == r7) goto L_0x006c
            if (r3 == r5) goto L_0x0041
            if (r3 == r4) goto L_0x0038
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0038:
            java.lang.Object r1 = r1.L$0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0158
        L_0x0041:
            int r3 = r1.I$0
            java.lang.Object r3 = r1.L$5
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3
            java.lang.Object r9 = r1.L$4
            io.ktor.utils.io.ByteReadChannel r9 = (io.ktor.utils.io.ByteReadChannel) r9
            java.lang.Object r10 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r1.L$2
            kotlin.jvm.internal.Ref$BooleanRef r11 = (kotlin.jvm.internal.Ref.BooleanRef) r11
            java.lang.Object r12 = r1.L$1
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r13 = r1.L$0
            io.ktor.utils.io.ByteReadChannel r13 = (io.ktor.utils.io.ByteReadChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0064 }
            r9 = r1
            r3 = r11
            r1 = r12
            r0 = r13
            goto L_0x0128
        L_0x0064:
            r0 = move-exception
        L_0x0065:
            r17 = r1
            r1 = r0
            r0 = r17
            goto L_0x0142
        L_0x006c:
            java.lang.Object r3 = r1.L$4
            io.ktor.utils.io.ByteReadChannel r3 = (io.ktor.utils.io.ByteReadChannel) r3
            java.lang.Object r9 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r10 = r1.L$2
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r1.L$1
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$0
            io.ktor.utils.io.ByteReadChannel r12 = (io.ktor.utils.io.ByteReadChannel) r12
            kotlin.ResultKt.throwOnFailure(r0)
            r17 = r9
            r9 = r3
            r3 = r10
            r10 = r17
            goto L_0x00ba
        L_0x008a:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$BooleanRef r0 = new kotlin.jvm.internal.Ref$BooleanRef
            r0.<init>()
            kotlin.jvm.internal.Ref$BooleanRef r3 = new kotlin.jvm.internal.Ref$BooleanRef
            r3.<init>()
            r9 = r1
            r10 = r3
            r1 = r19
            r3 = r0
            r0 = r18
        L_0x009e:
            r3.element = r6
            r9.L$0 = r0
            r9.L$1 = r1
            r9.L$2 = r3
            r9.L$3 = r10
            r9.L$4 = r0
            r9.L$5 = r8
            r9.label = r7
            java.lang.Object r11 = io.ktor.utils.io.ReadSessionKt.requestBuffer(r0, r7, r9)
            if (r11 != r2) goto L_0x00b5
            return r2
        L_0x00b5:
            r12 = r0
            r0 = r11
            r11 = r1
            r1 = r9
            r9 = r12
        L_0x00ba:
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0
            if (r0 != 0) goto L_0x00c4
            io.ktor.utils.io.core.Buffer$Companion r0 = io.ktor.utils.io.core.Buffer.Companion
            io.ktor.utils.io.core.Buffer r0 = r0.getEmpty()
        L_0x00c4:
            r13 = r0
            java.nio.ByteBuffer r0 = r13.m155getMemorySK3TCg8()     // Catch:{ all -> 0x013e }
            int r14 = r13.getReadPosition()     // Catch:{ all -> 0x013e }
            long r14 = (long) r14     // Catch:{ all -> 0x013e }
            int r7 = r13.getWritePosition()     // Catch:{ all -> 0x013e }
            long r6 = (long) r7     // Catch:{ all -> 0x013e }
            int r16 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r16 <= 0) goto L_0x00dd
            long r6 = r6 - r14
            java.nio.ByteBuffer r0 = io.ktor.utils.io.bits.Memory.m1520slice87lwejk((java.nio.ByteBuffer) r0, (long) r14, (long) r6)     // Catch:{ all -> 0x013e }
            goto L_0x00e3
        L_0x00dd:
            io.ktor.utils.io.bits.Memory$Companion r0 = io.ktor.utils.io.bits.Memory.Companion     // Catch:{ all -> 0x013e }
            java.nio.ByteBuffer r0 = r0.m1525getEmptySK3TCg8()     // Catch:{ all -> 0x013e }
        L_0x00e3:
            int r6 = r0.remaining()     // Catch:{ all -> 0x013e }
            int r7 = r12.getAvailableForRead()     // Catch:{ all -> 0x013e }
            if (r6 != r7) goto L_0x00f5
            boolean r6 = r12.isClosedForWrite()     // Catch:{ all -> 0x013e }
            if (r6 == 0) goto L_0x00f5
            r6 = 1
            goto L_0x00f6
        L_0x00f5:
            r6 = 0
        L_0x00f6:
            r10.element = r6     // Catch:{ all -> 0x013e }
            boolean r6 = r10.element     // Catch:{ all -> 0x013e }
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)     // Catch:{ all -> 0x013e }
            java.lang.Object r6 = r11.invoke(r0, r6)     // Catch:{ all -> 0x013e }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x013e }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x013e }
            r3.element = r6     // Catch:{ all -> 0x013e }
            int r0 = r0.position()     // Catch:{ all -> 0x013e }
            r1.L$0 = r12     // Catch:{ all -> 0x013e }
            r1.L$1 = r11     // Catch:{ all -> 0x013e }
            r1.L$2 = r3     // Catch:{ all -> 0x013e }
            r1.L$3 = r10     // Catch:{ all -> 0x013e }
            r1.L$4 = r9     // Catch:{ all -> 0x013e }
            r1.L$5 = r13     // Catch:{ all -> 0x013e }
            r1.I$0 = r0     // Catch:{ all -> 0x013e }
            r1.label = r5     // Catch:{ all -> 0x013e }
            java.lang.Object r0 = io.ktor.utils.io.ReadSessionKt.completeReadingFromBuffer(r9, r13, r0, r1)     // Catch:{ all -> 0x013e }
            if (r0 != r2) goto L_0x0125
            return r2
        L_0x0125:
            r9 = r1
            r1 = r11
            r0 = r12
        L_0x0128:
            boolean r6 = r10.element
            if (r6 == 0) goto L_0x0133
            boolean r6 = r0.isClosedForRead()
            if (r6 == 0) goto L_0x0133
            goto L_0x0137
        L_0x0133:
            boolean r6 = r3.element
            if (r6 != 0) goto L_0x013a
        L_0x0137:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x013a:
            r6 = 0
            r7 = 1
            goto L_0x009e
        L_0x013e:
            r0 = move-exception
            r3 = r13
            goto L_0x0065
        L_0x0142:
            r0.L$0 = r1
            r0.L$1 = r8
            r0.L$2 = r8
            r0.L$3 = r8
            r0.L$4 = r8
            r0.L$5 = r8
            r0.label = r4
            r4 = 0
            java.lang.Object r0 = io.ktor.utils.io.ReadSessionKt.completeReadingFromBuffer(r9, r3, r4, r0)
            if (r0 != r2) goto L_0x0158
            return r2
        L_0x0158:
            goto L_0x015a
        L_0x0159:
            throw r1
        L_0x015a:
            goto L_0x0159
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ConsumeEachKt.consumeEachBufferRange(io.ktor.utils.io.ByteReadChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0070 A[Catch:{ all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0072 A[Catch:{ all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x001d  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0054 A[Catch:{ all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x005a A[Catch:{ all -> 0x00b3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object consumeEachBufferRange$$forInline(io.ktor.utils.io.ByteReadChannel r11, kotlin.jvm.functions.Function2<? super java.nio.ByteBuffer, ? super java.lang.Boolean, java.lang.Boolean> r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            kotlin.jvm.internal.Ref$BooleanRef r0 = new kotlin.jvm.internal.Ref$BooleanRef
            r0.<init>()
            kotlin.jvm.internal.Ref$BooleanRef r1 = new kotlin.jvm.internal.Ref$BooleanRef
            r1.<init>()
        L_0x000a:
            r2 = 0
            r0.element = r2
            kotlin.jvm.internal.InlineMarker.mark((int) r2)
            r3 = 1
            java.lang.Object r4 = io.ktor.utils.io.ReadSessionKt.requestBuffer(r11, r3, r13)
            kotlin.jvm.internal.InlineMarker.mark((int) r3)
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4
            if (r4 == 0) goto L_0x001d
            goto L_0x0023
        L_0x001d:
            io.ktor.utils.io.core.Buffer$Companion r4 = io.ktor.utils.io.core.Buffer.Companion
            io.ktor.utils.io.core.Buffer r4 = r4.getEmpty()
        L_0x0023:
            java.nio.ByteBuffer r5 = r4.m155getMemorySK3TCg8()     // Catch:{ all -> 0x00b3 }
            io.ktor.utils.io.bits.Memory r5 = io.ktor.utils.io.bits.Memory.m1508boximpl(r5)     // Catch:{ all -> 0x00b3 }
            int r6 = r4.getReadPosition()     // Catch:{ all -> 0x00b3 }
            long r6 = (long) r6     // Catch:{ all -> 0x00b3 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x00b3 }
            int r7 = r4.getWritePosition()     // Catch:{ all -> 0x00b3 }
            long r7 = (long) r7     // Catch:{ all -> 0x00b3 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x00b3 }
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ all -> 0x00b3 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x00b3 }
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ all -> 0x00b3 }
            long r9 = r6.longValue()     // Catch:{ all -> 0x00b3 }
            r6 = r5
            io.ktor.utils.io.bits.Memory r6 = (io.ktor.utils.io.bits.Memory) r6     // Catch:{ all -> 0x00b3 }
            java.nio.ByteBuffer r5 = r5.m1524unboximpl()     // Catch:{ all -> 0x00b3 }
            int r6 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r6 <= 0) goto L_0x005a
            long r7 = r7 - r9
            java.nio.ByteBuffer r5 = io.ktor.utils.io.bits.Memory.m1520slice87lwejk((java.nio.ByteBuffer) r5, (long) r9, (long) r7)     // Catch:{ all -> 0x00b3 }
            goto L_0x0060
        L_0x005a:
            io.ktor.utils.io.bits.Memory$Companion r5 = io.ktor.utils.io.bits.Memory.Companion     // Catch:{ all -> 0x00b3 }
            java.nio.ByteBuffer r5 = r5.m1525getEmptySK3TCg8()     // Catch:{ all -> 0x00b3 }
        L_0x0060:
            int r6 = r5.remaining()     // Catch:{ all -> 0x00b3 }
            int r7 = r11.getAvailableForRead()     // Catch:{ all -> 0x00b3 }
            if (r6 != r7) goto L_0x0072
            boolean r6 = r11.isClosedForWrite()     // Catch:{ all -> 0x00b3 }
            if (r6 == 0) goto L_0x0072
            r6 = 1
            goto L_0x0073
        L_0x0072:
            r6 = 0
        L_0x0073:
            r1.element = r6     // Catch:{ all -> 0x00b3 }
            boolean r6 = r1.element     // Catch:{ all -> 0x00b3 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x00b3 }
            java.lang.Object r6 = r12.invoke(r5, r6)     // Catch:{ all -> 0x00b3 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x00b3 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x00b3 }
            r0.element = r6     // Catch:{ all -> 0x00b3 }
            int r5 = r5.position()     // Catch:{ all -> 0x00b3 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x00b3 }
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ all -> 0x00b3 }
            int r5 = r5.intValue()     // Catch:{ all -> 0x00b3 }
            kotlin.jvm.internal.InlineMarker.mark((int) r2)     // Catch:{ all -> 0x00b3 }
            io.ktor.utils.io.ReadSessionKt.completeReadingFromBuffer(r11, r4, r5, r13)     // Catch:{ all -> 0x00b3 }
            kotlin.jvm.internal.InlineMarker.mark((int) r3)     // Catch:{ all -> 0x00b3 }
            java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x00b3 }
            boolean r2 = r1.element
            if (r2 == 0) goto L_0x00ac
            boolean r2 = r11.isClosedForRead()
            if (r2 == 0) goto L_0x00ac
            goto L_0x00b0
        L_0x00ac:
            boolean r2 = r0.element
            if (r2 != 0) goto L_0x000a
        L_0x00b0:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00b3:
            r12 = move-exception
            kotlin.jvm.internal.InlineMarker.mark((int) r2)
            io.ktor.utils.io.ReadSessionKt.completeReadingFromBuffer(r11, r4, r2, r13)
            kotlin.jvm.internal.InlineMarker.mark((int) r3)
            goto L_0x00bf
        L_0x00be:
            throw r12
        L_0x00bf:
            goto L_0x00be
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ConsumeEachKt.consumeEachBufferRange$$forInline(io.ktor.utils.io.ByteReadChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
