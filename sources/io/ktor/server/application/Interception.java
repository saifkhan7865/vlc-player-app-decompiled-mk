package io.ktor.server.application;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0002\u0010\nR)\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/server/application/Interception;", "T", "", "phase", "Lio/ktor/util/pipeline/PipelinePhase;", "action", "Lkotlin/Function1;", "Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/server/application/ApplicationCall;", "", "(Lio/ktor/util/pipeline/PipelinePhase;Lkotlin/jvm/functions/Function1;)V", "getAction", "()Lkotlin/jvm/functions/Function1;", "getPhase", "()Lio/ktor/util/pipeline/PipelinePhase;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Interceptions.kt */
public final class Interception<T> {
    private final Function1<Pipeline<T, ApplicationCall>, Unit> action;
    private final PipelinePhase phase;

    public Interception(PipelinePhase pipelinePhase, Function1<? super Pipeline<T, ApplicationCall>, Unit> function1) {
        Intrinsics.checkNotNullParameter(pipelinePhase, TypedValues.CycleType.S_WAVE_PHASE);
        Intrinsics.checkNotNullParameter(function1, "action");
        this.phase = pipelinePhase;
        this.action = function1;
    }

    public final PipelinePhase getPhase() {
        return this.phase;
    }

    public final Function1<Pipeline<T, ApplicationCall>, Unit> getAction() {
        return this.action;
    }
}
