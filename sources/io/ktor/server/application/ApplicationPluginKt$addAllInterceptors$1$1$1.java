package io.ktor.server.application;

import io.ktor.server.routing.RoutingApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003\"\u0004\b\u0002\u0010\u0005\"\u0004\b\u0003\u0010\u0006\"\u0014\b\u0004\u0010\u0007*\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\b*\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\t2\u0006\u0010\n\u001a\u0002H\u0005H@"}, d2 = {"<anonymous>", "", "B", "", "F", "TSubject", "TContext", "P", "Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/util/pipeline/PipelineContext;", "subject"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.application.ApplicationPluginKt$addAllInterceptors$1$1$1", f = "ApplicationPlugin.kt", i = {}, l = {169}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ApplicationPlugin.kt */
final class ApplicationPluginKt$addAllInterceptors$1$1$1 extends SuspendLambda implements Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object> $interceptor;
    final /* synthetic */ BaseRouteScopedPlugin<B, F> $plugin;
    final /* synthetic */ F $pluginInstance;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ApplicationPluginKt$addAllInterceptors$1$1$1(BaseRouteScopedPlugin<B, F> baseRouteScopedPlugin, F f, Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super ApplicationPluginKt$addAllInterceptors$1$1$1> continuation) {
        super(3, continuation);
        this.$plugin = baseRouteScopedPlugin;
        this.$pluginInstance = f;
        this.$interceptor = function3;
    }

    public final Object invoke(PipelineContext<TSubject, TContext> pipelineContext, TSubject tsubject, Continuation<? super Unit> continuation) {
        ApplicationPluginKt$addAllInterceptors$1$1$1 applicationPluginKt$addAllInterceptors$1$1$1 = new ApplicationPluginKt$addAllInterceptors$1$1$1(this.$plugin, this.$pluginInstance, this.$interceptor, continuation);
        applicationPluginKt$addAllInterceptors$1$1$1.L$0 = pipelineContext;
        applicationPluginKt$addAllInterceptors$1$1$1.L$1 = tsubject;
        return applicationPluginKt$addAllInterceptors$1$1$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            Object obj2 = this.L$1;
            Object context = pipelineContext.getContext();
            if ((context instanceof RoutingApplicationCall) && Intrinsics.areEqual(RouteScopedPluginKt.findPluginInRoute(((RoutingApplicationCall) context).getRoute(), this.$plugin), (Object) this.$pluginInstance)) {
                Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object> function3 = this.$interceptor;
                this.L$0 = null;
                this.label = 1;
                if (function3.invoke(pipelineContext, obj2, this) == coroutine_suspended) {
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
