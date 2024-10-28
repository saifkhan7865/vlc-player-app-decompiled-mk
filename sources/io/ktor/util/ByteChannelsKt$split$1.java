package io.ktor.util;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.ByteChannelsKt$split$1", f = "ByteChannels.kt", i = {0, 1, 1, 1}, l = {25, 29}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$use$iv", "closed$iv"}, s = {"L$0", "L$0", "L$1", "I$0"})
/* compiled from: ByteChannels.kt */
final class ByteChannelsKt$split$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteChannel $first;
    final /* synthetic */ ByteChannel $second;
    final /* synthetic */ ByteReadChannel $this_split;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteChannelsKt$split$1(ByteReadChannel byteReadChannel, ByteChannel byteChannel, ByteChannel byteChannel2, Continuation<? super ByteChannelsKt$split$1> continuation) {
        super(2, continuation);
        this.$this_split = byteReadChannel;
        this.$first = byteChannel;
        this.$second = byteChannel2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ByteChannelsKt$split$1 byteChannelsKt$split$1 = new ByteChannelsKt$split$1(this.$this_split, this.$first, this.$second, continuation);
        byteChannelsKt$split$1.L$0 = obj;
        return byteChannelsKt$split$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ByteChannelsKt$split$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r1 = r17
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 == r5) goto L_0x002c
            if (r2 != r4) goto L_0x0024
            java.lang.Object r2 = r1.L$1
            java.io.Closeable r2 = (java.io.Closeable) r2
            java.lang.Object r6 = r1.L$0
            kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
            kotlin.ResultKt.throwOnFailure(r18)     // Catch:{ all -> 0x0020 }
            r7 = r18
            goto L_0x00aa
        L_0x0020:
            r0 = move-exception
            r3 = r0
            goto L_0x00b4
        L_0x0024:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x002c:
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            kotlin.ResultKt.throwOnFailure(r18)     // Catch:{ all -> 0x0036 }
            r6 = r18
            goto L_0x005c
        L_0x0036:
            r0 = move-exception
            goto L_0x00d8
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r18)
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
        L_0x0040:
            io.ktor.utils.io.ByteReadChannel r6 = r1.$this_split     // Catch:{ all -> 0x0036 }
            boolean r6 = r6.isClosedForRead()     // Catch:{ all -> 0x0036 }
            if (r6 != 0) goto L_0x00c0
            io.ktor.utils.io.ByteReadChannel r6 = r1.$this_split     // Catch:{ all -> 0x0036 }
            r7 = r1
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch:{ all -> 0x0036 }
            r1.L$0 = r2     // Catch:{ all -> 0x0036 }
            r1.L$1 = r3     // Catch:{ all -> 0x0036 }
            r1.label = r5     // Catch:{ all -> 0x0036 }
            r8 = 4096(0x1000, double:2.0237E-320)
            java.lang.Object r6 = r6.readRemaining(r8, r7)     // Catch:{ all -> 0x0036 }
            if (r6 != r0) goto L_0x005c
            return r0
        L_0x005c:
            java.io.Closeable r6 = (java.io.Closeable) r6     // Catch:{ all -> 0x0036 }
            io.ktor.utils.io.ByteChannel r7 = r1.$first     // Catch:{ all -> 0x0036 }
            io.ktor.utils.io.ByteChannel r13 = r1.$second     // Catch:{ all -> 0x0036 }
            r14 = r6
            io.ktor.utils.io.core.ByteReadPacket r14 = (io.ktor.utils.io.core.ByteReadPacket) r14     // Catch:{ all -> 0x00b1 }
            kotlinx.coroutines.Deferred[] r15 = new kotlinx.coroutines.Deferred[r4]     // Catch:{ all -> 0x00b1 }
            io.ktor.util.ByteChannelsKt$split$1$1$1 r8 = new io.ktor.util.ByteChannelsKt$split$1$1$1     // Catch:{ all -> 0x00b1 }
            r8.<init>(r7, r14, r3)     // Catch:{ all -> 0x00b1 }
            r10 = r8
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10     // Catch:{ all -> 0x00b1 }
            r11 = 3
            r12 = 0
            r8 = 0
            r9 = 0
            r7 = r2
            kotlinx.coroutines.Deferred r7 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x00b1 }
            r12 = 0
            r15[r12] = r7     // Catch:{ all -> 0x00b1 }
            io.ktor.util.ByteChannelsKt$split$1$1$2 r7 = new io.ktor.util.ByteChannelsKt$split$1$1$2     // Catch:{ all -> 0x00b1 }
            r7.<init>(r13, r14, r3)     // Catch:{ all -> 0x00b1 }
            r10 = r7
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10     // Catch:{ all -> 0x00b1 }
            r11 = 3
            r13 = 0
            r8 = 0
            r9 = 0
            r7 = r2
            r14 = 0
            r12 = r13
            kotlinx.coroutines.Deferred r7 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x00b1 }
            r15[r5] = r7     // Catch:{ all -> 0x00b1 }
            java.util.List r7 = kotlin.collections.CollectionsKt.listOf(r15)     // Catch:{ all -> 0x00b1 }
            java.util.Collection r7 = (java.util.Collection) r7     // Catch:{ all -> 0x00b1 }
            r1.L$0 = r2     // Catch:{ all -> 0x00b1 }
            r1.L$1 = r6     // Catch:{ all -> 0x00b1 }
            r1.I$0 = r14     // Catch:{ all -> 0x00b1 }
            r1.label = r4     // Catch:{ all -> 0x00b1 }
            java.lang.Object r7 = kotlinx.coroutines.AwaitKt.awaitAll(r7, r1)     // Catch:{ all -> 0x00b1 }
            if (r7 != r0) goto L_0x00a5
            return r0
        L_0x00a5:
            r16 = r6
            r6 = r2
            r2 = r16
        L_0x00aa:
            java.util.List r7 = (java.util.List) r7     // Catch:{ all -> 0x0020 }
            r2.close()     // Catch:{ all -> 0x0036 }
            r2 = r6
            goto L_0x0040
        L_0x00b1:
            r0 = move-exception
            r3 = r0
            r2 = r6
        L_0x00b4:
            r2.close()     // Catch:{ all -> 0x00b8 }
            goto L_0x00bd
        L_0x00b8:
            r0 = move-exception
            r2 = r0
            io.ktor.utils.io.core.CloseableJVMKt.addSuppressedInternal(r3, r2)     // Catch:{ all -> 0x00be }
        L_0x00bd:
            throw r3     // Catch:{ all -> 0x00be }
        L_0x00be:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0036 }
        L_0x00c0:
            io.ktor.utils.io.ByteReadChannel r0 = r1.$this_split     // Catch:{ all -> 0x0036 }
            java.lang.Throwable r0 = r0.getClosedCause()     // Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x00d7
        L_0x00c8:
            io.ktor.utils.io.ByteChannel r0 = r1.$first
            io.ktor.utils.io.ByteWriteChannel r0 = (io.ktor.utils.io.ByteWriteChannel) r0
            io.ktor.utils.io.ByteWriteChannelKt.close(r0)
            io.ktor.utils.io.ByteChannel r0 = r1.$second
            io.ktor.utils.io.ByteWriteChannel r0 = (io.ktor.utils.io.ByteWriteChannel) r0
            io.ktor.utils.io.ByteWriteChannelKt.close(r0)
            goto L_0x00e8
        L_0x00d7:
            throw r0     // Catch:{ all -> 0x0036 }
        L_0x00d8:
            io.ktor.utils.io.ByteReadChannel r2 = r1.$this_split     // Catch:{ all -> 0x00eb }
            r2.cancel(r0)     // Catch:{ all -> 0x00eb }
            io.ktor.utils.io.ByteChannel r2 = r1.$first     // Catch:{ all -> 0x00eb }
            r2.cancel(r0)     // Catch:{ all -> 0x00eb }
            io.ktor.utils.io.ByteChannel r2 = r1.$second     // Catch:{ all -> 0x00eb }
            r2.cancel(r0)     // Catch:{ all -> 0x00eb }
            goto L_0x00c8
        L_0x00e8:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00eb:
            r0 = move-exception
            io.ktor.utils.io.ByteChannel r2 = r1.$first
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            io.ktor.utils.io.ByteWriteChannelKt.close(r2)
            io.ktor.utils.io.ByteChannel r2 = r1.$second
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            io.ktor.utils.io.ByteWriteChannelKt.close(r2)
            goto L_0x00fc
        L_0x00fb:
            throw r0
        L_0x00fc:
            goto L_0x00fb
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.ByteChannelsKt$split$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
