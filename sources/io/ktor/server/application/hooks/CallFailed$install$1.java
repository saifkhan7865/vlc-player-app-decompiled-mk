package io.ktor.server.application.hooks;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.application.hooks.CallFailed$install$1", f = "CommonHooks.kt", i = {0, 1, 1}, l = {44, 48}, m = "invokeSuspend", n = {"$this$intercept", "$this$intercept", "cause"}, s = {"L$0", "L$0", "L$1"})
/* compiled from: CommonHooks.kt */
final class CallFailed$install$1 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<ApplicationCall, Throwable, Continuation<? super Unit>, Object> $handler;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CallFailed$install$1(Function3<? super ApplicationCall, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super CallFailed$install$1> continuation) {
        super(3, continuation);
        this.$handler = function3;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        CallFailed$install$1 callFailed$install$1 = new CallFailed$install$1(this.$handler, continuation);
        callFailed$install$1.L$0 = pipelineContext;
        return callFailed$install$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x002f
            if (r1 == r3) goto L_0x0022
            if (r1 != r2) goto L_0x001a
            java.lang.Object r0 = r6.L$1
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.Object r1 = r6.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0064
        L_0x001a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0022:
            java.lang.Object r1 = r6.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x002a }
            goto L_0x0074
        L_0x002a:
            r7 = move-exception
            r5 = r1
            r1 = r7
            r7 = r5
            goto L_0x004d
        L_0x002f:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            io.ktor.util.pipeline.PipelineContext r7 = (io.ktor.util.pipeline.PipelineContext) r7
            io.ktor.server.application.hooks.CallFailed$install$1$1 r1 = new io.ktor.server.application.hooks.CallFailed$install$1$1     // Catch:{ all -> 0x004c }
            r4 = 0
            r1.<init>(r7, r4)     // Catch:{ all -> 0x004c }
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1     // Catch:{ all -> 0x004c }
            r4 = r6
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch:{ all -> 0x004c }
            r6.L$0 = r7     // Catch:{ all -> 0x004c }
            r6.label = r3     // Catch:{ all -> 0x004c }
            java.lang.Object r7 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r1, r4)     // Catch:{ all -> 0x004c }
            if (r7 != r0) goto L_0x0074
            return r0
        L_0x004c:
            r1 = move-exception
        L_0x004d:
            kotlin.jvm.functions.Function3<io.ktor.server.application.ApplicationCall, java.lang.Throwable, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r3 = r6.$handler
            java.lang.Object r4 = r7.getContext()
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            r6.L$0 = r7
            r6.L$1 = r1
            r6.label = r2
            java.lang.Object r2 = r3.invoke(r4, r1, r6)
            if (r2 != r0) goto L_0x0062
            return r0
        L_0x0062:
            r0 = r1
            r1 = r7
        L_0x0064:
            java.lang.Object r7 = r1.getContext()
            io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
            io.ktor.server.response.ApplicationResponse r7 = r7.getResponse()
            boolean r7 = r7.isSent()
            if (r7 == 0) goto L_0x0077
        L_0x0074:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x0077:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.application.hooks.CallFailed$install$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
