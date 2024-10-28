package io.ktor.server.engine;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.ShutDownUrl$EnginePlugin$install$1", f = "ShutDownUrl.kt", i = {}, l = {68}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ShutDownUrl.kt */
final class ShutDownUrl$EnginePlugin$install$1 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ ShutDownUrl $plugin;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ShutDownUrl$EnginePlugin$install$1(ShutDownUrl shutDownUrl, Continuation<? super ShutDownUrl$EnginePlugin$install$1> continuation) {
        super(3, continuation);
        this.$plugin = shutDownUrl;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        ShutDownUrl$EnginePlugin$install$1 shutDownUrl$EnginePlugin$install$1 = new ShutDownUrl$EnginePlugin$install$1(this.$plugin, continuation);
        shutDownUrl$EnginePlugin$install$1.L$0 = pipelineContext;
        return shutDownUrl$EnginePlugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            if (Intrinsics.areEqual((Object) ApplicationRequestPropertiesKt.getUri(((ApplicationCall) pipelineContext.getContext()).getRequest()), (Object) this.$plugin.getUrl())) {
                this.label = 1;
                if (this.$plugin.doShutdown((ApplicationCall) pipelineContext.getContext(), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
