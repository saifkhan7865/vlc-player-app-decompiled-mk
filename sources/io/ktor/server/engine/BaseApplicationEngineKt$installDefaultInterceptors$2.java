package io.ktor.server.engine;

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
@DebugMetadata(c = "io.ktor.server.engine.BaseApplicationEngineKt$installDefaultInterceptors$2", f = "BaseApplicationEngine.kt", i = {}, l = {115}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseApplicationEngine.kt */
final class BaseApplicationEngineKt$installDefaultInterceptors$2 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    BaseApplicationEngineKt$installDefaultInterceptors$2(Continuation<? super BaseApplicationEngineKt$installDefaultInterceptors$2> continuation) {
        super(3, continuation);
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        BaseApplicationEngineKt$installDefaultInterceptors$2 baseApplicationEngineKt$installDefaultInterceptors$2 = new BaseApplicationEngineKt$installDefaultInterceptors$2(continuation);
        baseApplicationEngineKt$installDefaultInterceptors$2.L$0 = pipelineContext;
        return baseApplicationEngineKt$installDefaultInterceptors$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (BaseApplicationEngineKt.verifyHostHeader((PipelineContext) this.L$0, this) == coroutine_suspended) {
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
