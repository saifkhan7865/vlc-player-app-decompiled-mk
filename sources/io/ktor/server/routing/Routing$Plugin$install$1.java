package io.ktor.server.routing;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.routing.Routing$Plugin$install$1", f = "Routing.kt", i = {}, l = {140}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Routing.kt */
final class Routing$Plugin$install$1 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ Routing $routing;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Routing$Plugin$install$1(Routing routing, Continuation<? super Routing$Plugin$install$1> continuation) {
        super(3, continuation);
        this.$routing = routing;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        Routing$Plugin$install$1 routing$Plugin$install$1 = new Routing$Plugin$install$1(this.$routing, continuation);
        routing$Plugin$install$1.L$0 = pipelineContext;
        return routing$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Routing routing = this.$routing;
            this.label = 1;
            if (routing.interceptor((PipelineContext) this.L$0, this) == coroutine_suspended) {
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
