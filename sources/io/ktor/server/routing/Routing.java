package io.ktor.server.routing;

import io.ktor.events.EventDefinition;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.BaseApplicationPlugin;
import io.ktor.server.routing.RoutingResolveResult;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelineContext;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@KtorDsl
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\u000bH\u0002J5\u0010\r\u001a\u00020\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J%\u0010\u0015\u001a\u00020\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00100\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0016JV\u0010\u0017\u001a\u0002H\u0018\"\b\b\u0000\u0010\u0019*\u00020\u001a\"\b\b\u0001\u0010\u001b*\u00020\u001a\"\u0014\b\u0002\u0010\u0018*\u000e\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0006\u0010\u001d\u001a\u0002H\u00182\u0006\u0010\u001e\u001a\u0002H\u00182\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00180 H\b¢\u0006\u0002\u0010!J\u001a\u0010\"\u001a\u00020\u000b2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lio/ktor/server/routing/Routing;", "Lio/ktor/server/routing/Route;", "application", "Lio/ktor/server/application/Application;", "(Lio/ktor/server/application/Application;)V", "getApplication", "()Lio/ktor/server/application/Application;", "tracers", "", "Lkotlin/Function1;", "Lio/ktor/server/routing/RoutingResolveTrace;", "", "addDefaultTracing", "executeResult", "context", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "route", "parameters", "Lio/ktor/http/Parameters;", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/server/routing/Route;Lio/ktor/http/Parameters;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "interceptor", "(Lio/ktor/util/pipeline/PipelineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "merge", "P", "Subject", "", "Context", "Lio/ktor/util/pipeline/Pipeline;", "first", "second", "build", "Lkotlin/Function0;", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/util/pipeline/Pipeline;Lkotlin/jvm/functions/Function0;)Lio/ktor/util/pipeline/Pipeline;", "trace", "block", "Plugin", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Routing.kt */
public final class Routing extends Route {
    public static final Plugin Plugin = new Plugin((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final EventDefinition<RoutingApplicationCall> RoutingCallFinished = new EventDefinition<>();
    /* access modifiers changed from: private */
    public static final EventDefinition<RoutingApplicationCall> RoutingCallStarted = new EventDefinition<>();
    /* access modifiers changed from: private */
    public static final AttributeKey<Routing> key = new AttributeKey<>("Routing");
    private final Application application;
    private final List<Function1<RoutingResolveTrace, Unit>> tracers = new ArrayList();

    public final Application getApplication() {
        return this.application;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Routing(Application application2) {
        super((Route) null, new RootRouteSelector(application2.getEnvironment().getRootPath()), application2.getEnvironment().getDevelopmentMode(), application2.getEnvironment());
        Intrinsics.checkNotNullParameter(application2, "application");
        this.application = application2;
        addDefaultTracing();
    }

    private final void addDefaultTracing() {
        if (RoutingKt.getLOGGER().isTraceEnabled()) {
            this.tracers.add(Routing$addDefaultTracing$1.INSTANCE);
        }
    }

    public final void trace(Function1<? super RoutingResolveTrace, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        this.tracers.add(function1);
    }

    public final Object interceptor(PipelineContext<Unit, ApplicationCall> pipelineContext, Continuation<? super Unit> continuation) {
        RoutingResolveResult resolve = new RoutingResolveContext(this, pipelineContext.getContext(), this.tracers).resolve();
        if (resolve instanceof RoutingResolveResult.Success) {
            Object executeResult = executeResult(pipelineContext, resolve.getRoute(), resolve.getParameters(), continuation);
            return executeResult == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? executeResult : Unit.INSTANCE;
        }
        if (resolve instanceof RoutingResolveResult.Failure) {
            pipelineContext.getContext().getAttributes().put(RoutingKt.getRoutingFailureStatusCode(), ((RoutingResolveResult.Failure) resolve).getErrorStatusCode());
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object executeResult(io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r12, io.ktor.server.routing.Route r13, io.ktor.http.Parameters r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            r11 = this;
            boolean r0 = r15 instanceof io.ktor.server.routing.Routing$executeResult$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            io.ktor.server.routing.Routing$executeResult$1 r0 = (io.ktor.server.routing.Routing$executeResult$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            io.ktor.server.routing.Routing$executeResult$1 r0 = new io.ktor.server.routing.Routing$executeResult$1
            r0.<init>(r11, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r12 = r0.L$1
            io.ktor.server.routing.RoutingApplicationCall r12 = (io.ktor.server.routing.RoutingApplicationCall) r12
            java.lang.Object r13 = r0.L$0
            io.ktor.server.routing.Routing r13 = (io.ktor.server.routing.Routing) r13
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x0033 }
            goto L_0x00f4
        L_0x0033:
            r14 = move-exception
            goto L_0x0109
        L_0x0036:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r15)
            io.ktor.server.application.ApplicationCallPipeline r15 = r13.buildPipeline$ktor_server_core()
            java.lang.Object r2 = r12.getContext()
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            io.ktor.server.request.ApplicationRequest r2 = r2.getRequest()
            io.ktor.server.request.ApplicationReceivePipeline r2 = r2.getPipeline()
            io.ktor.util.pipeline.Pipeline r2 = (io.ktor.util.pipeline.Pipeline) r2
            io.ktor.server.request.ApplicationReceivePipeline r4 = r15.getReceivePipeline()
            io.ktor.util.pipeline.Pipeline r4 = (io.ktor.util.pipeline.Pipeline) r4
            boolean r5 = r2.isEmpty()
            if (r5 == 0) goto L_0x0063
            r2 = r4
            goto L_0x007c
        L_0x0063:
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L_0x006a
            goto L_0x007c
        L_0x006a:
            io.ktor.server.request.ApplicationReceivePipeline r5 = new io.ktor.server.request.ApplicationReceivePipeline
            boolean r6 = r11.getDevelopmentMode()
            r5.<init>(r6)
            io.ktor.util.pipeline.Pipeline r5 = (io.ktor.util.pipeline.Pipeline) r5
            r5.merge(r2)
            r5.merge(r4)
            r2 = r5
        L_0x007c:
            r8 = r2
            io.ktor.server.request.ApplicationReceivePipeline r8 = (io.ktor.server.request.ApplicationReceivePipeline) r8
            java.lang.Object r2 = r12.getContext()
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            io.ktor.server.response.ApplicationResponse r2 = r2.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r2 = r2.getPipeline()
            io.ktor.util.pipeline.Pipeline r2 = (io.ktor.util.pipeline.Pipeline) r2
            io.ktor.server.response.ApplicationSendPipeline r4 = r15.getSendPipeline()
            io.ktor.util.pipeline.Pipeline r4 = (io.ktor.util.pipeline.Pipeline) r4
            boolean r5 = r2.isEmpty()
            if (r5 == 0) goto L_0x009d
            r2 = r4
            goto L_0x00b6
        L_0x009d:
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L_0x00a4
            goto L_0x00b6
        L_0x00a4:
            io.ktor.server.response.ApplicationSendPipeline r5 = new io.ktor.server.response.ApplicationSendPipeline
            boolean r6 = r11.getDevelopmentMode()
            r5.<init>(r6)
            io.ktor.util.pipeline.Pipeline r5 = (io.ktor.util.pipeline.Pipeline) r5
            r5.merge(r2)
            r5.merge(r4)
            r2 = r5
        L_0x00b6:
            r9 = r2
            io.ktor.server.response.ApplicationSendPipeline r9 = (io.ktor.server.response.ApplicationSendPipeline) r9
            io.ktor.server.routing.RoutingApplicationCall r2 = new io.ktor.server.routing.RoutingApplicationCall
            java.lang.Object r4 = r12.getContext()
            r5 = r4
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            kotlin.coroutines.CoroutineContext r7 = r12.getCoroutineContext()
            r4 = r2
            r6 = r13
            r10 = r14
            r4.<init>(r5, r6, r7, r8, r9, r10)
            io.ktor.server.application.Application r12 = r11.application
            io.ktor.server.application.ApplicationEnvironment r12 = r12.getEnvironment()
            io.ktor.events.Events r12 = r12.getMonitor()
            io.ktor.events.EventDefinition<io.ktor.server.routing.RoutingApplicationCall> r13 = RoutingCallStarted
            r12.raise(r13, r2)
            io.ktor.util.pipeline.Pipeline r15 = (io.ktor.util.pipeline.Pipeline) r15     // Catch:{ all -> 0x0106 }
            io.ktor.server.routing.Routing$executeResult$$inlined$execute$1 r12 = new io.ktor.server.routing.Routing$executeResult$$inlined$execute$1     // Catch:{ all -> 0x0106 }
            r13 = 0
            r12.<init>(r15, r2, r13)     // Catch:{ all -> 0x0106 }
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12     // Catch:{ all -> 0x0106 }
            r0.L$0 = r11     // Catch:{ all -> 0x0106 }
            r0.L$1 = r2     // Catch:{ all -> 0x0106 }
            r0.label = r3     // Catch:{ all -> 0x0106 }
            java.lang.Object r12 = io.ktor.util.debug.ContextUtilsKt.initContextInDebugMode(r12, r0)     // Catch:{ all -> 0x0106 }
            if (r12 != r1) goto L_0x00f2
            return r1
        L_0x00f2:
            r13 = r11
            r12 = r2
        L_0x00f4:
            io.ktor.server.application.Application r13 = r13.application
            io.ktor.server.application.ApplicationEnvironment r13 = r13.getEnvironment()
            io.ktor.events.Events r13 = r13.getMonitor()
            io.ktor.events.EventDefinition<io.ktor.server.routing.RoutingApplicationCall> r14 = RoutingCallFinished
            r13.raise(r14, r12)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0106:
            r14 = move-exception
            r13 = r11
            r12 = r2
        L_0x0109:
            io.ktor.server.application.Application r13 = r13.application
            io.ktor.server.application.ApplicationEnvironment r13 = r13.getEnvironment()
            io.ktor.events.Events r13 = r13.getMonitor()
            io.ktor.events.EventDefinition<io.ktor.server.routing.RoutingApplicationCall> r15 = RoutingCallFinished
            r13.raise(r15, r12)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.routing.Routing.executeResult(io.ktor.util.pipeline.PipelineContext, io.ktor.server.routing.Route, io.ktor.http.Parameters, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final <Subject, Context, P extends Pipeline<Subject, Context>> P merge(P p, P p2, Function0<? extends P> function0) {
        if (p.isEmpty()) {
            return p2;
        }
        if (p2.isEmpty()) {
            return p;
        }
        P p3 = (Pipeline) function0.invoke();
        p3.merge(p);
        p3.merge(p2);
        return p3;
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J)\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00022\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0002\b\u0015H\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lio/ktor/server/routing/Routing$Plugin;", "Lio/ktor/server/application/BaseApplicationPlugin;", "Lio/ktor/server/application/Application;", "Lio/ktor/server/routing/Routing;", "()V", "RoutingCallFinished", "Lio/ktor/events/EventDefinition;", "Lio/ktor/server/routing/RoutingApplicationCall;", "getRoutingCallFinished", "()Lio/ktor/events/EventDefinition;", "RoutingCallStarted", "getRoutingCallStarted", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "pipeline", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Routing.kt */
    public static final class Plugin implements BaseApplicationPlugin<Application, Routing, Routing> {
        public /* synthetic */ Plugin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Plugin() {
        }

        public final EventDefinition<RoutingApplicationCall> getRoutingCallStarted() {
            return Routing.RoutingCallStarted;
        }

        public final EventDefinition<RoutingApplicationCall> getRoutingCallFinished() {
            return Routing.RoutingCallFinished;
        }

        public AttributeKey<Routing> getKey() {
            return Routing.key;
        }

        public Routing install(Application application, Function1<? super Routing, Unit> function1) {
            Intrinsics.checkNotNullParameter(application, "pipeline");
            Intrinsics.checkNotNullParameter(function1, "configure");
            Routing routing = new Routing(application);
            function1.invoke(routing);
            application.intercept(ApplicationCallPipeline.ApplicationPhase.getCall(), new Routing$Plugin$install$1(routing, (Continuation<? super Routing$Plugin$install$1>) null));
            return routing;
        }
    }
}
