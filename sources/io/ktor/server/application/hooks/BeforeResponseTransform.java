package io.ktor.server.application.hooks;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.Hook;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.InternalAPI;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022H\u0012D\u0012B\b\u0001\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00040\u0003B\u0013\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\f¢\u0006\u0002\u0010\rJ`\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112F\u0010\u0012\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0004H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0013R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/server/application/hooks/BeforeResponseTransform;", "T", "", "Lio/ktor/server/application/Hook;", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/ParameterName;", "name", "call", "body", "Lkotlin/coroutines/Continuation;", "clazz", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "install", "", "pipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "handler", "(Lio/ktor/server/application/ApplicationCallPipeline;Lkotlin/jvm/functions/Function3;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@InternalAPI
/* compiled from: CommonHooks.kt */
public final class BeforeResponseTransform<T> implements Hook<Function3<? super ApplicationCall, ? super T, ? super Continuation<? super Object>, ? extends Object>> {
    /* access modifiers changed from: private */
    public final KClass<T> clazz;

    public BeforeResponseTransform(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        this.clazz = kClass;
    }

    public void install(ApplicationCallPipeline applicationCallPipeline, Function3<? super ApplicationCall, ? super T, ? super Continuation<Object>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(applicationCallPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(function3, "handler");
        PipelinePhase pipelinePhase = new PipelinePhase("BeforeTransform");
        applicationCallPipeline.getSendPipeline().insertPhaseBefore(ApplicationSendPipeline.Phases.getTransform(), pipelinePhase);
        applicationCallPipeline.getSendPipeline().intercept(pipelinePhase, new BeforeResponseTransform$install$1(this, function3, (Continuation<? super BeforeResponseTransform$install$1>) null));
    }
}
