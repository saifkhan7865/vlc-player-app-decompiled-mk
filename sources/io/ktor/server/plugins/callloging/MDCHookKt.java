package io.ktor.server.plugins.callloging;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.Hook;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aQ\u0010\u0000\u001a@\u0012<\u0012:\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u001a\u0012\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0000ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"MDCHook", "Lio/ktor/server/application/Hook;", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "phase", "Lio/ktor/util/pipeline/PipelinePhase;", "ktor-server-call-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: MDCHook.kt */
public final class MDCHookKt {
    public static final Hook<Function3<ApplicationCall, Function1<? super Continuation<? super Unit>, ? extends Object>, Continuation<? super Unit>, Object>> MDCHook(PipelinePhase pipelinePhase) {
        Intrinsics.checkNotNullParameter(pipelinePhase, TypedValues.CycleType.S_WAVE_PHASE);
        return new MDCHookKt$MDCHook$1(pipelinePhase);
    }
}
