package io.ktor.server.application;

import io.ktor.util.AttributeKey;
import io.ktor.util.debug.ContextUtilsKt;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u000e\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0002\u0010\u0006*\u00020\u00032\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t0\bH\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "T", "", "ContextT", "Lio/ktor/server/application/CallContext;", "PluginConfig", "pipeline", "Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/server/application/ApplicationCall;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PluginBuilder.kt */
final class PluginBuilder$onDefaultPhaseWithMessage$1 extends Lambda implements Function1<Pipeline<T, ApplicationCall>, Unit> {
    final /* synthetic */ Function4<ContextT, ApplicationCall, T, Continuation<? super Unit>, Object> $block;
    final /* synthetic */ Function2<PluginConfig, PipelineContext<T, ApplicationCall>, ContextT> $contextInit;
    final /* synthetic */ String $handlerName;
    final /* synthetic */ PipelinePhase $phase;
    final /* synthetic */ PluginBuilder<PluginConfig> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PluginBuilder$onDefaultPhaseWithMessage$1(PipelinePhase pipelinePhase, PluginBuilder<PluginConfig> pluginBuilder, String str, Function4<? super ContextT, ? super ApplicationCall, ? super T, ? super Continuation<? super Unit>, ? extends Object> function4, Function2<? super PluginConfig, ? super PipelineContext<T, ApplicationCall>, ? extends ContextT> function2) {
        super(1);
        this.$phase = pipelinePhase;
        this.this$0 = pluginBuilder;
        this.$handlerName = str;
        this.$block = function4;
        this.$contextInit = function2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Pipeline) obj);
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u000e\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0002\u0010\u0006*\u00020\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u0002H\u0002H@"}, d2 = {"<anonymous>", "", "T", "", "ContextT", "Lio/ktor/server/application/CallContext;", "PluginConfig", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.server.application.PluginBuilder$onDefaultPhaseWithMessage$1$1", f = "PluginBuilder.kt", i = {}, l = {194}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.ktor.server.application.PluginBuilder$onDefaultPhaseWithMessage$1$1  reason: invalid class name */
    /* compiled from: PluginBuilder.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<PipelineContext<T, ApplicationCall>, T, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public final Object invoke(PipelineContext<T, ApplicationCall> pipelineContext, T t, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(pluginBuilder, str, function4, function2, continuation);
            r0.L$0 = pipelineContext;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final PipelineContext pipelineContext = (PipelineContext) this.L$0;
                final AttributeKey<PluginInstance> key$ktor_server_core = pluginBuilder.getKey$ktor_server_core();
                final PluginConfig pluginConfig = pluginBuilder.getPluginConfig();
                String name = key$ktor_server_core.getName();
                final String str = str;
                final Function4<ContextT, ApplicationCall, T, Continuation<? super Unit>, Object> function4 = function4;
                final Function2<PluginConfig, PipelineContext<T, ApplicationCall>, ContextT> function2 = function2;
                this.label = 1;
                if (ContextUtilsKt.addToContextInDebugMode(name, new Function1<Continuation<? super Unit>, Object>((Continuation<? super AnonymousClass1>) null) {
                    int label;

                    public final Continuation<Unit> create(Continuation<?> continuation) {
                        return 

                        public final void invoke(Pipeline<T, ApplicationCall> pipeline) {
                            Intrinsics.checkNotNullParameter(pipeline, "pipeline");
                            PipelinePhase pipelinePhase = this.$phase;
                            final PluginBuilder<PluginConfig> pluginBuilder = this.this$0;
                            final String str = this.$handlerName;
                            final Function4<ContextT, ApplicationCall, T, Continuation<? super Unit>, Object> function4 = this.$block;
                            final Function2<PluginConfig, PipelineContext<T, ApplicationCall>, ContextT> function2 = this.$contextInit;
                            pipeline.intercept(pipelinePhase, new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
                        }
                    }
