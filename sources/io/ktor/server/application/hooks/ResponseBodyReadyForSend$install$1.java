package io.ktor.server.application.hooks;

import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.hooks.ResponseBodyReadyForSend;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.application.hooks.ResponseBodyReadyForSend$install$1", f = "CommonHooks.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CommonHooks.kt */
final class ResponseBodyReadyForSend$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, ApplicationCall>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function4<ResponseBodyReadyForSend.Context, ApplicationCall, OutgoingContent, Continuation<? super Unit>, Object> $handler;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ResponseBodyReadyForSend$install$1(Function4<? super ResponseBodyReadyForSend.Context, ? super ApplicationCall, ? super OutgoingContent, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super ResponseBodyReadyForSend$install$1> continuation) {
        super(3, continuation);
        this.$handler = function4;
    }

    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        ResponseBodyReadyForSend$install$1 responseBodyReadyForSend$install$1 = new ResponseBodyReadyForSend$install$1(this.$handler, continuation);
        responseBodyReadyForSend$install$1.L$0 = pipelineContext;
        return responseBodyReadyForSend$install$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            Object subject = pipelineContext.getSubject();
            Intrinsics.checkNotNull(subject, "null cannot be cast to non-null type io.ktor.http.content.OutgoingContent");
            this.label = 1;
            if (this.$handler.invoke(new ResponseBodyReadyForSend.Context(pipelineContext), (ApplicationCall) pipelineContext.getContext(), (OutgoingContent) subject, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
