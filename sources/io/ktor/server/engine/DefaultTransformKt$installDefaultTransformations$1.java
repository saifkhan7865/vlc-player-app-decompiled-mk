package io.ktor.server.engine;

import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.http.content.DefaultTransformKt;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "value"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$1", f = "DefaultTransform.kt", i = {}, l = {29}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DefaultTransform.kt */
final class DefaultTransformKt$installDefaultTransformations$1 extends SuspendLambda implements Function3<PipelineContext<Object, ApplicationCall>, Object, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    DefaultTransformKt$installDefaultTransformations$1(Continuation<? super DefaultTransformKt$installDefaultTransformations$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        DefaultTransformKt$installDefaultTransformations$1 defaultTransformKt$installDefaultTransformations$1 = new DefaultTransformKt$installDefaultTransformations$1(continuation);
        defaultTransformKt$installDefaultTransformations$1.L$0 = pipelineContext;
        defaultTransformKt$installDefaultTransformations$1.L$1 = obj;
        return defaultTransformKt$installDefaultTransformations$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            OutgoingContent transformDefaultContent = DefaultTransformKt.transformDefaultContent((ApplicationCall) pipelineContext.getContext(), this.L$1);
            if (transformDefaultContent != null) {
                this.L$0 = null;
                this.label = 1;
                if (pipelineContext.proceedWith(transformDefaultContent, this) == coroutine_suspended) {
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
