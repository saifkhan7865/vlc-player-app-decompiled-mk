package io.ktor.utils.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/LookAheadSuspendSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$copied$1", f = "Delimited.kt", i = {0, 0, 1, 1}, l = {85, 95}, m = "invokeSuspend", n = {"$this$lookAheadSuspend", "copied", "$this$lookAheadSuspend", "copied"}, s = {"L$0", "I$0", "L$0", "I$0"})
/* compiled from: Delimited.kt */
final class DelimitedKt$readUntilDelimiterSuspend$copied$1 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Integer>, Object> {
    final /* synthetic */ int $copied0;
    final /* synthetic */ ByteBuffer $delimiter;
    final /* synthetic */ ByteBuffer $dst;
    final /* synthetic */ Ref.BooleanRef $endFound;
    final /* synthetic */ ByteReadChannel $this_readUntilDelimiterSuspend;
    int I$0;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DelimitedKt$readUntilDelimiterSuspend$copied$1(int i, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, Ref.BooleanRef booleanRef, ByteReadChannel byteReadChannel, Continuation<? super DelimitedKt$readUntilDelimiterSuspend$copied$1> continuation) {
        super(2, continuation);
        this.$copied0 = i;
        this.$delimiter = byteBuffer;
        this.$dst = byteBuffer2;
        this.$endFound = booleanRef;
        this.$this_readUntilDelimiterSuspend = byteReadChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DelimitedKt$readUntilDelimiterSuspend$copied$1 delimitedKt$readUntilDelimiterSuspend$copied$1 = new DelimitedKt$readUntilDelimiterSuspend$copied$1(this.$copied0, this.$delimiter, this.$dst, this.$endFound, this.$this_readUntilDelimiterSuspend, continuation);
        delimitedKt$readUntilDelimiterSuspend$copied$1.L$0 = obj;
        return delimitedKt$readUntilDelimiterSuspend$copied$1;
    }

    public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Integer> continuation) {
        return ((DelimitedKt$readUntilDelimiterSuspend$copied$1) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009a, code lost:
        if (r7.$endFound.element == false) goto L_0x0033;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x002a
            if (r1 == r3) goto L_0x0020
            if (r1 != r2) goto L_0x0018
            int r1 = r7.I$0
            java.lang.Object r4 = r7.L$0
            io.ktor.utils.io.LookAheadSuspendSession r4 = (io.ktor.utils.io.LookAheadSuspendSession) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0083
        L_0x0018:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0020:
            int r1 = r7.I$0
            java.lang.Object r4 = r7.L$0
            io.ktor.utils.io.LookAheadSuspendSession r4 = (io.ktor.utils.io.LookAheadSuspendSession) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0044
        L_0x002a:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            io.ktor.utils.io.LookAheadSuspendSession r8 = (io.ktor.utils.io.LookAheadSuspendSession) r8
            int r1 = r7.$copied0
        L_0x0033:
            r4 = r7
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r7.L$0 = r8
            r7.I$0 = r1
            r7.label = r3
            java.lang.Object r4 = r8.awaitAtLeast(r3, r4)
            if (r4 != r0) goto L_0x0043
            return r0
        L_0x0043:
            r4 = r8
        L_0x0044:
            r8 = r4
            io.ktor.utils.io.LookAheadSession r8 = (io.ktor.utils.io.LookAheadSession) r8
            java.nio.ByteBuffer r5 = r7.$delimiter
            java.nio.ByteBuffer r6 = r7.$dst
            int r5 = io.ktor.utils.io.DelimitedKt.tryCopyUntilDelimiter(r8, r5, r6)
            if (r5 != 0) goto L_0x0085
            java.nio.ByteBuffer r5 = r7.$delimiter
            int r8 = io.ktor.utils.io.DelimitedKt.startsWithDelimiter(r8, r5)
            java.nio.ByteBuffer r5 = r7.$delimiter
            int r5 = r5.remaining()
            if (r8 != r5) goto L_0x0064
            kotlin.jvm.internal.Ref$BooleanRef r8 = r7.$endFound
            r8.element = r3
            goto L_0x009c
        L_0x0064:
            io.ktor.utils.io.ByteReadChannel r8 = r7.$this_readUntilDelimiterSuspend
            boolean r8 = r8.isClosedForWrite()
            if (r8 == 0) goto L_0x006d
            goto L_0x009c
        L_0x006d:
            java.nio.ByteBuffer r8 = r7.$delimiter
            int r8 = r8.remaining()
            r5 = r7
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r7.L$0 = r4
            r7.I$0 = r1
            r7.label = r2
            java.lang.Object r8 = r4.awaitAtLeast(r8, r5)
            if (r8 != r0) goto L_0x0083
            return r0
        L_0x0083:
            r8 = r4
            goto L_0x008e
        L_0x0085:
            if (r5 > 0) goto L_0x008c
            kotlin.jvm.internal.Ref$BooleanRef r8 = r7.$endFound
            r8.element = r3
            int r5 = -r5
        L_0x008c:
            int r1 = r1 + r5
            goto L_0x0083
        L_0x008e:
            java.nio.ByteBuffer r4 = r7.$dst
            boolean r4 = r4.hasRemaining()
            if (r4 == 0) goto L_0x009c
            kotlin.jvm.internal.Ref$BooleanRef r4 = r7.$endFound
            boolean r4 = r4.element
            if (r4 == 0) goto L_0x0033
        L_0x009c:
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$copied$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
