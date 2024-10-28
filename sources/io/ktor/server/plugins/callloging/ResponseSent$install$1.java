package io.ktor.server.plugins.callloging;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.callloging.ResponseSent$install$1", f = "MDCHook.kt", i = {0}, l = {28, 29}, m = "invokeSuspend", n = {"$this$intercept"}, s = {"L$0"})
/* compiled from: MDCHook.kt */
final class ResponseSent$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, ApplicationCall>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<ApplicationCall, Continuation<? super Unit>, Object> $handler;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ResponseSent$install$1(Function2<? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super ResponseSent$install$1> continuation) {
        super(3, continuation);
        this.$handler = function2;
    }

    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        ResponseSent$install$1 responseSent$install$1 = new ResponseSent$install$1(this.$handler, continuation);
        responseSent$install$1.L$0 = pipelineContext;
        return responseSent$install$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: io.ktor.util.pipeline.PipelineContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x004c
        L_0x0012:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x001a:
            java.lang.Object r1 = r4.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0038
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r5)
            java.lang.Object r5 = r4.L$0
            r1 = r5
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            r5 = r4
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r4.L$0 = r1
            r4.label = r3
            java.lang.Object r5 = r1.proceed(r5)
            if (r5 != r0) goto L_0x0038
            return r0
        L_0x0038:
            kotlin.jvm.functions.Function2<io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r5 = r4.$handler
            java.lang.Object r1 = r1.getContext()
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1
            r3 = 0
            r4.L$0 = r3
            r4.label = r2
            java.lang.Object r5 = r5.invoke(r1, r4)
            if (r5 != r0) goto L_0x004c
            return r0
        L_0x004c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.callloging.ResponseSent$install$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
