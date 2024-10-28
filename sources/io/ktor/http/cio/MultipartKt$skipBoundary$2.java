package io.ktor.http.cio;

import io.ktor.utils.io.LookAheadSuspendSession;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/LookAheadSuspendSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.MultipartKt$skipBoundary$2", f = "Multipart.kt", i = {0, 1}, l = {204, 215}, m = "invokeSuspend", n = {"$this$lookAheadSuspend", "$this$lookAheadSuspend"}, s = {"L$0", "L$0"})
/* compiled from: Multipart.kt */
final class MultipartKt$skipBoundary$2 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.BooleanRef $result;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MultipartKt$skipBoundary$2(Ref.BooleanRef booleanRef, Continuation<? super MultipartKt$skipBoundary$2> continuation) {
        super(2, continuation);
        this.$result = booleanRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MultipartKt$skipBoundary$2 multipartKt$skipBoundary$2 = new MultipartKt$skipBoundary$2(this.$result, continuation);
        multipartKt$skipBoundary$2.L$0 = obj;
        return multipartKt$skipBoundary$2;
    }

    public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Unit> continuation) {
        return ((MultipartKt$skipBoundary$2) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            java.lang.String r2 = "Failed to pass multipart boundary: unexpected end of stream"
            r3 = 45
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x002b
            if (r1 == r5) goto L_0x0022
            if (r1 != r4) goto L_0x001a
            java.lang.Object r0 = r7.L$0
            io.ktor.utils.io.LookAheadSuspendSession r0 = (io.ktor.utils.io.LookAheadSuspendSession) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x007e
        L_0x001a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0022:
            java.lang.Object r1 = r7.L$0
            io.ktor.utils.io.LookAheadSuspendSession r1 = (io.ktor.utils.io.LookAheadSuspendSession) r1
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r1
            goto L_0x0040
        L_0x002b:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            io.ktor.utils.io.LookAheadSuspendSession r8 = (io.ktor.utils.io.LookAheadSuspendSession) r8
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r7.L$0 = r8
            r7.label = r5
            java.lang.Object r1 = r8.awaitAtLeast(r5, r1)
            if (r1 != r0) goto L_0x0040
            return r0
        L_0x0040:
            r1 = 0
            java.nio.ByteBuffer r1 = r8.request(r1, r5)
            if (r1 == 0) goto L_0x00a1
            int r6 = r1.position()
            byte r6 = r1.get(r6)
            if (r6 == r3) goto L_0x0054
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0054:
            int r6 = r1.remaining()
            if (r6 <= r5) goto L_0x006f
            int r6 = r1.position()
            int r6 = r6 + r5
            byte r1 = r1.get(r6)
            if (r1 != r3) goto L_0x006f
            kotlin.jvm.internal.Ref$BooleanRef r0 = r7.$result
            r0.element = r5
            r8.consumed(r4)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x006f:
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r7.L$0 = r8
            r7.label = r4
            java.lang.Object r1 = r8.awaitAtLeast(r4, r1)
            if (r1 != r0) goto L_0x007d
            return r0
        L_0x007d:
            r0 = r8
        L_0x007e:
            java.nio.ByteBuffer r8 = r0.request(r5, r5)
            if (r8 == 0) goto L_0x009b
            int r1 = r8.position()
            byte r8 = r8.get(r1)
            if (r8 != r3) goto L_0x0098
            kotlin.jvm.internal.Ref$BooleanRef r8 = r7.$result
            r8.element = r5
            r0.consumed(r4)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0098:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x009b:
            java.io.IOException r8 = new java.io.IOException
            r8.<init>(r2)
            throw r8
        L_0x00a1:
            java.io.IOException r8 = new java.io.IOException
            r8.<init>(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt$skipBoundary$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
