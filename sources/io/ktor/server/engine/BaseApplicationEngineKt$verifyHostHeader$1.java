package io.ktor.server.engine;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.BaseApplicationEngineKt", f = "BaseApplicationEngine.kt", i = {0}, l = {146}, m = "verifyHostHeader", n = {"$this$verifyHostHeader"}, s = {"L$0"})
/* compiled from: BaseApplicationEngine.kt */
final class BaseApplicationEngineKt$verifyHostHeader$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    BaseApplicationEngineKt$verifyHostHeader$1(Continuation<? super BaseApplicationEngineKt$verifyHostHeader$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BaseApplicationEngineKt.verifyHostHeader((PipelineContext<Unit, ApplicationCall>) null, this);
    }
}
