package io.ktor.server.engine;

import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.http.content.HttpStatusCodeContent;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "subject"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.BaseApplicationEngineKt$installDefaultTransformationChecker$2", f = "BaseApplicationEngine.kt", i = {}, l = {134}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseApplicationEngine.kt */
final class BaseApplicationEngineKt$installDefaultTransformationChecker$2 extends SuspendLambda implements Function3<PipelineContext<Object, ApplicationCall>, Object, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    BaseApplicationEngineKt$installDefaultTransformationChecker$2(Continuation<? super BaseApplicationEngineKt$installDefaultTransformationChecker$2> continuation) {
        super(3, continuation);
    }

    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        BaseApplicationEngineKt$installDefaultTransformationChecker$2 baseApplicationEngineKt$installDefaultTransformationChecker$2 = new BaseApplicationEngineKt$installDefaultTransformationChecker$2(continuation);
        baseApplicationEngineKt$installDefaultTransformationChecker$2.L$0 = pipelineContext;
        baseApplicationEngineKt$installDefaultTransformationChecker$2.L$1 = obj;
        return baseApplicationEngineKt$installDefaultTransformationChecker$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            if (!(this.L$1 instanceof OutgoingContent)) {
                this.L$0 = null;
                this.label = 1;
                if (pipelineContext.proceedWith(new HttpStatusCodeContent(HttpStatusCode.Companion.getNotAcceptable()), this) == coroutine_suspended) {
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
