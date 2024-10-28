package io.ktor.util;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.ByteChannelsKt$copyToBoth$1", f = "ByteChannels.kt", i = {1, 1, 1, 2, 2}, l = {59, 61, 62}, m = "invokeSuspend", n = {"$this$use$iv", "it", "closed$iv", "$this$use$iv", "closed$iv"}, s = {"L$0", "L$4", "I$0", "L$0", "I$0"})
/* compiled from: ByteChannels.kt */
final class ByteChannelsKt$copyToBoth$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteWriteChannel $first;
    final /* synthetic */ ByteWriteChannel $second;
    final /* synthetic */ ByteReadChannel $this_copyToBoth;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteChannelsKt$copyToBoth$1(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, ByteWriteChannel byteWriteChannel2, Continuation<? super ByteChannelsKt$copyToBoth$1> continuation) {
        super(2, continuation);
        this.$this_copyToBoth = byteReadChannel;
        this.$first = byteWriteChannel;
        this.$second = byteWriteChannel2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ByteChannelsKt$copyToBoth$1(this.$this_copyToBoth, this.$first, this.$second, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ByteChannelsKt$copyToBoth$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x007b A[Catch:{ all -> 0x005d, all -> 0x0116 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ba A[Catch:{ all -> 0x0027 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d3 A[Catch:{ all -> 0x0027 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f5 A[Catch:{ all -> 0x005d, all -> 0x0116 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x0060
            if (r1 == r4) goto L_0x0059
            if (r1 == r3) goto L_0x0032
            if (r1 != r2) goto L_0x002a
            java.lang.Object r1 = r11.L$3
            io.ktor.utils.io.ByteReadChannel r1 = (io.ktor.utils.io.ByteReadChannel) r1
            java.lang.Object r6 = r11.L$2
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            java.lang.Object r7 = r11.L$1
            io.ktor.utils.io.ByteWriteChannel r7 = (io.ktor.utils.io.ByteWriteChannel) r7
            java.lang.Object r8 = r11.L$0
            java.io.Closeable r8 = (java.io.Closeable) r8
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x0027 }
            goto L_0x00d4
        L_0x0027:
            r12 = move-exception
            goto L_0x00d7
        L_0x002a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0032:
            int r1 = r11.I$0
            java.lang.Object r6 = r11.L$4
            io.ktor.utils.io.core.ByteReadPacket r6 = (io.ktor.utils.io.core.ByteReadPacket) r6
            java.lang.Object r7 = r11.L$3
            io.ktor.utils.io.ByteReadChannel r7 = (io.ktor.utils.io.ByteReadChannel) r7
            java.lang.Object r8 = r11.L$2
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8
            java.lang.Object r9 = r11.L$1
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            java.lang.Object r10 = r11.L$0
            java.io.Closeable r10 = (java.io.Closeable) r10
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x0052 }
            r12 = r6
            r6 = r8
            r8 = r10
            r10 = r1
            r1 = r7
            r7 = r9
            goto L_0x00bb
        L_0x0052:
            r12 = move-exception
            r1 = r7
            r6 = r8
            r7 = r9
            r8 = r10
            goto L_0x00d7
        L_0x0059:
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x005d }
            goto L_0x0095
        L_0x005d:
            r12 = move-exception
            goto L_0x00ff
        L_0x0060:
            kotlin.ResultKt.throwOnFailure(r12)
        L_0x0063:
            io.ktor.utils.io.ByteReadChannel r12 = r11.$this_copyToBoth     // Catch:{ all -> 0x005d }
            boolean r12 = r12.isClosedForRead()     // Catch:{ all -> 0x005d }
            if (r12 != 0) goto L_0x00f5
            io.ktor.utils.io.ByteWriteChannel r12 = r11.$first     // Catch:{ all -> 0x005d }
            boolean r12 = r12.isClosedForWrite()     // Catch:{ all -> 0x005d }
            if (r12 == 0) goto L_0x007b
            io.ktor.utils.io.ByteWriteChannel r12 = r11.$second     // Catch:{ all -> 0x005d }
            boolean r12 = r12.isClosedForWrite()     // Catch:{ all -> 0x005d }
            if (r12 != 0) goto L_0x00f5
        L_0x007b:
            io.ktor.utils.io.ByteReadChannel r12 = r11.$this_copyToBoth     // Catch:{ all -> 0x005d }
            r1 = r11
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ all -> 0x005d }
            r11.L$0 = r5     // Catch:{ all -> 0x005d }
            r11.L$1 = r5     // Catch:{ all -> 0x005d }
            r11.L$2 = r5     // Catch:{ all -> 0x005d }
            r11.L$3 = r5     // Catch:{ all -> 0x005d }
            r11.L$4 = r5     // Catch:{ all -> 0x005d }
            r11.label = r4     // Catch:{ all -> 0x005d }
            r6 = 4096(0x1000, double:2.0237E-320)
            java.lang.Object r12 = r12.readRemaining(r6, r1)     // Catch:{ all -> 0x005d }
            if (r12 != r0) goto L_0x0095
            return r0
        L_0x0095:
            r8 = r12
            java.io.Closeable r8 = (java.io.Closeable) r8     // Catch:{ all -> 0x005d }
            io.ktor.utils.io.ByteWriteChannel r7 = r11.$first     // Catch:{ all -> 0x005d }
            io.ktor.utils.io.ByteWriteChannel r6 = r11.$second     // Catch:{ all -> 0x005d }
            io.ktor.utils.io.ByteReadChannel r1 = r11.$this_copyToBoth     // Catch:{ all -> 0x005d }
            r12 = r8
            io.ktor.utils.io.core.ByteReadPacket r12 = (io.ktor.utils.io.core.ByteReadPacket) r12     // Catch:{ all -> 0x00e9 }
            io.ktor.utils.io.core.ByteReadPacket r9 = r12.copy()     // Catch:{ all -> 0x0027 }
            r11.L$0 = r8     // Catch:{ all -> 0x0027 }
            r11.L$1 = r7     // Catch:{ all -> 0x0027 }
            r11.L$2 = r6     // Catch:{ all -> 0x0027 }
            r11.L$3 = r1     // Catch:{ all -> 0x0027 }
            r11.L$4 = r12     // Catch:{ all -> 0x0027 }
            r10 = 0
            r11.I$0 = r10     // Catch:{ all -> 0x0027 }
            r11.label = r3     // Catch:{ all -> 0x0027 }
            java.lang.Object r9 = r7.writePacket(r9, r11)     // Catch:{ all -> 0x0027 }
            if (r9 != r0) goto L_0x00bb
            return r0
        L_0x00bb:
            io.ktor.utils.io.core.ByteReadPacket r12 = r12.copy()     // Catch:{ all -> 0x0027 }
            r11.L$0 = r8     // Catch:{ all -> 0x0027 }
            r11.L$1 = r7     // Catch:{ all -> 0x0027 }
            r11.L$2 = r6     // Catch:{ all -> 0x0027 }
            r11.L$3 = r1     // Catch:{ all -> 0x0027 }
            r11.L$4 = r5     // Catch:{ all -> 0x0027 }
            r11.I$0 = r10     // Catch:{ all -> 0x0027 }
            r11.label = r2     // Catch:{ all -> 0x0027 }
            java.lang.Object r12 = r6.writePacket(r12, r11)     // Catch:{ all -> 0x0027 }
            if (r12 != r0) goto L_0x00d4
            return r0
        L_0x00d4:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0027 }
            goto L_0x00e4
        L_0x00d7:
            r1.cancel(r12)     // Catch:{ all -> 0x00e9 }
            r7.close(r12)     // Catch:{ all -> 0x00e9 }
            boolean r12 = r6.close(r12)     // Catch:{ all -> 0x00e9 }
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r12)     // Catch:{ all -> 0x00e9 }
        L_0x00e4:
            r8.close()     // Catch:{ all -> 0x005d }
            goto L_0x0063
        L_0x00e9:
            r12 = move-exception
            r8.close()     // Catch:{ all -> 0x00ee }
            goto L_0x00f2
        L_0x00ee:
            r0 = move-exception
            io.ktor.utils.io.core.CloseableJVMKt.addSuppressedInternal(r12, r0)     // Catch:{ all -> 0x00f3 }
        L_0x00f2:
            throw r12     // Catch:{ all -> 0x00f3 }
        L_0x00f3:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x005d }
        L_0x00f5:
            io.ktor.utils.io.ByteReadChannel r12 = r11.$this_copyToBoth     // Catch:{ all -> 0x005d }
            java.lang.Throwable r12 = r12.getClosedCause()     // Catch:{ all -> 0x005d }
            if (r12 != 0) goto L_0x00fe
            goto L_0x0109
        L_0x00fe:
            throw r12     // Catch:{ all -> 0x005d }
        L_0x00ff:
            io.ktor.utils.io.ByteWriteChannel r0 = r11.$first     // Catch:{ all -> 0x0116 }
            r0.close(r12)     // Catch:{ all -> 0x0116 }
            io.ktor.utils.io.ByteWriteChannel r0 = r11.$second     // Catch:{ all -> 0x0116 }
            r0.close(r12)     // Catch:{ all -> 0x0116 }
        L_0x0109:
            io.ktor.utils.io.ByteWriteChannel r12 = r11.$first
            io.ktor.utils.io.ByteWriteChannelKt.close(r12)
            io.ktor.utils.io.ByteWriteChannel r12 = r11.$second
            io.ktor.utils.io.ByteWriteChannelKt.close(r12)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0116:
            r12 = move-exception
            io.ktor.utils.io.ByteWriteChannel r0 = r11.$first
            io.ktor.utils.io.ByteWriteChannelKt.close(r0)
            io.ktor.utils.io.ByteWriteChannel r0 = r11.$second
            io.ktor.utils.io.ByteWriteChannelKt.close(r0)
            goto L_0x0123
        L_0x0122:
            throw r12
        L_0x0123:
            goto L_0x0122
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.ByteChannelsKt$copyToBoth$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
