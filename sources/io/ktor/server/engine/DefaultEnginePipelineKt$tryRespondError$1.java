package io.ktor.server.engine;

import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.DefaultEnginePipelineKt", f = "DefaultEnginePipeline.kt", i = {}, l = {127}, m = "tryRespondError", n = {}, s = {})
/* compiled from: DefaultEnginePipeline.kt */
final class DefaultEnginePipelineKt$tryRespondError$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    DefaultEnginePipelineKt$tryRespondError$1(Continuation<? super DefaultEnginePipelineKt$tryRespondError$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DefaultEnginePipelineKt.tryRespondError((ApplicationCall) null, (HttpStatusCode) null, this);
    }
}
