package io.ktor.server.engine;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.DefaultEnginePipelineKt$logError$2", f = "DefaultEnginePipeline.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DefaultEnginePipeline.kt */
final class DefaultEnginePipelineKt$logError$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ ApplicationCall $call;
    final /* synthetic */ Throwable $error;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DefaultEnginePipelineKt$logError$2(ApplicationCall applicationCall, Throwable th, Continuation<? super DefaultEnginePipelineKt$logError$2> continuation) {
        super(1, continuation);
        this.$call = applicationCall;
        this.$error = th;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DefaultEnginePipelineKt$logError$2(this.$call, this.$error, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((DefaultEnginePipelineKt$logError$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            DefaultEnginePipelineKt.logFailure(this.$call.getApplication().getEnvironment(), this.$call, this.$error);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
