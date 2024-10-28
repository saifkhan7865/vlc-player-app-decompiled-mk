package io.ktor.http.cio;

import io.ktor.utils.io.LookAheadSuspendSession;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/LookAheadSuspendSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$2", f = "Multipart.kt", i = {0, 1}, l = {576, 576}, m = "invokeSuspend", n = {"$this$lookAheadSuspend", "$this$lookAheadSuspend"}, s = {"L$0", "L$0"})
/* compiled from: Multipart.kt */
final class MultipartKt$trySkipDelimiterSuspend$2 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteBuffer $delimiter;
    final /* synthetic */ Ref.BooleanRef $result;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MultipartKt$trySkipDelimiterSuspend$2(ByteBuffer byteBuffer, Ref.BooleanRef booleanRef, Continuation<? super MultipartKt$trySkipDelimiterSuspend$2> continuation) {
        super(2, continuation);
        this.$delimiter = byteBuffer;
        this.$result = booleanRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MultipartKt$trySkipDelimiterSuspend$2 multipartKt$trySkipDelimiterSuspend$2 = new MultipartKt$trySkipDelimiterSuspend$2(this.$delimiter, this.$result, continuation);
        multipartKt$trySkipDelimiterSuspend$2.L$0 = obj;
        return multipartKt$trySkipDelimiterSuspend$2;
    }

    public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Unit> continuation) {
        return ((MultipartKt$trySkipDelimiterSuspend$2) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0026
            if (r1 == r3) goto L_0x001e
            if (r1 != r2) goto L_0x0016
            java.lang.Object r0 = r6.L$0
            io.ktor.utils.io.LookAheadSuspendSession r0 = (io.ktor.utils.io.LookAheadSuspendSession) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x005b
        L_0x0016:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x001e:
            java.lang.Object r1 = r6.L$0
            io.ktor.utils.io.LookAheadSuspendSession r1 = (io.ktor.utils.io.LookAheadSuspendSession) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0044
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            io.ktor.utils.io.LookAheadSuspendSession r7 = (io.ktor.utils.io.LookAheadSuspendSession) r7
            java.nio.ByteBuffer r1 = r6.$delimiter
            int r1 = r1.remaining()
            r4 = r6
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r6.L$0 = r7
            r6.label = r3
            java.lang.Object r1 = r7.awaitAtLeast(r1, r4)
            if (r1 != r0) goto L_0x0041
            return r0
        L_0x0041:
            r5 = r1
            r1 = r7
            r7 = r5
        L_0x0044:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L_0x006c
            r7 = r6
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r6.L$0 = r1
            r6.label = r2
            java.lang.Object r7 = r1.awaitAtLeast(r3, r7)
            if (r7 != r0) goto L_0x005a
            return r0
        L_0x005a:
            r0 = r1
        L_0x005b:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L_0x006b
            kotlin.jvm.internal.Ref$BooleanRef r7 = r6.$result
            r0 = 0
            r7.element = r0
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x006b:
            r1 = r0
        L_0x006c:
            io.ktor.utils.io.LookAheadSession r1 = (io.ktor.utils.io.LookAheadSession) r1
            java.nio.ByteBuffer r7 = r6.$delimiter
            int r7 = io.ktor.http.cio.MultipartKt.tryEnsureDelimiter(r1, r7)
            java.nio.ByteBuffer r0 = r6.$delimiter
            int r0 = r0.remaining()
            if (r7 != r0) goto L_0x007f
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x007f:
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r0 = "Broken delimiter occurred"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
