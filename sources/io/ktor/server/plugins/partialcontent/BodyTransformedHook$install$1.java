package io.ktor.server.plugins.partialcontent;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.partialcontent.BodyTransformedHook;
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

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "message"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.partialcontent.BodyTransformedHook$install$1", f = "BodyTransformedHook.kt", i = {}, l = {30}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BodyTransformedHook.kt */
final class BodyTransformedHook$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, ApplicationCall>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function4<BodyTransformedHook.Context, ApplicationCall, Object, Continuation<? super Unit>, Object> $handler;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BodyTransformedHook$install$1(Function4<? super BodyTransformedHook.Context, ? super ApplicationCall, Object, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super BodyTransformedHook$install$1> continuation) {
        super(3, continuation);
        this.$handler = function4;
    }

    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        BodyTransformedHook$install$1 bodyTransformedHook$install$1 = new BodyTransformedHook$install$1(this.$handler, continuation);
        bodyTransformedHook$install$1.L$0 = pipelineContext;
        bodyTransformedHook$install$1.L$1 = obj;
        return bodyTransformedHook$install$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            Object obj2 = this.L$1;
            this.L$0 = null;
            this.label = 1;
            if (this.$handler.invoke(new BodyTransformedHook.Context(pipelineContext), (ApplicationCall) pipelineContext.getContext(), obj2, this) == coroutine_suspended) {
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
