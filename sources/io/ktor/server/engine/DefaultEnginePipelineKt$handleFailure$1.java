package io.ktor.server.engine;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.DefaultEnginePipelineKt", f = "DefaultEnginePipeline.kt", i = {0, 0}, l = {56, 57}, m = "handleFailure", n = {"call", "error"}, s = {"L$0", "L$1"})
/* compiled from: DefaultEnginePipeline.kt */
final class DefaultEnginePipelineKt$handleFailure$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    DefaultEnginePipelineKt$handleFailure$1(Continuation<? super DefaultEnginePipelineKt$handleFailure$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DefaultEnginePipelineKt.handleFailure((ApplicationCall) null, (Throwable) null, this);
    }
}
