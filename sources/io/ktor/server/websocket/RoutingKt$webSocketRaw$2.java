package io.ktor.server.websocket;

import io.ktor.http.HttpHeaders;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.routing.Route;
import io.ktor.server.routing.RoutingBuilderKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.websocket.WebSocketSession;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/routing/Route;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Routing.kt */
final class RoutingKt$webSocketRaw$2 extends Lambda implements Function1<Route, Unit> {
    final /* synthetic */ Function2<WebSocketServerSession, Continuation<? super Unit>, Object> $handler;
    final /* synthetic */ boolean $negotiateExtensions;
    final /* synthetic */ String $protocol;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoutingKt$webSocketRaw$2(String str, boolean z, Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2) {
        super(1);
        this.$protocol = str;
        this.$negotiateExtensions = z;
        this.$handler = function2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Route) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Route route) {
        Intrinsics.checkNotNullParameter(route, "$this$header");
        String upgrade = HttpHeaders.INSTANCE.getUpgrade();
        final String str = this.$protocol;
        final boolean z = this.$negotiateExtensions;
        final Function2<WebSocketServerSession, Continuation<? super Unit>, Object> function2 = this.$handler;
        RoutingBuilderKt.header(route, upgrade, "websocket", new Function1<Route, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Route) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Route route) {
                Intrinsics.checkNotNullParameter(route, "$this$header");
                String str = str;
                final String str2 = str;
                final boolean z = z;
                final Function2<WebSocketServerSession, Continuation<? super Unit>, Object> function2 = function2;
                RoutingKt.webSocketProtocol(route, str, new Function1<Route, Unit>() {

                    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
                    @DebugMetadata(c = "io.ktor.server.websocket.RoutingKt$webSocketRaw$2$1$1$1", f = "Routing.kt", i = {}, l = {105}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: io.ktor.server.websocket.RoutingKt$webSocketRaw$2$1$1$1  reason: invalid class name */
                    /* compiled from: Routing.kt */
                    static final class AnonymousClass1 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
                        private /* synthetic */ Object L$0;
                        int label;

                        public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
                            AnonymousClass1 r5 = new AnonymousClass1(str, z, function2, continuation);
                            r5.L$0 = pipelineContext;
                            return r5.invokeSuspend(Unit.INSTANCE);
                        }

                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                final PipelineContext pipelineContext = (PipelineContext) this.L$0;
                                String str = str;
                                boolean z = z;
                                final Function2<WebSocketServerSession, Continuation<? super Unit>, Object> function2 = function2;
                                this.label = 1;
                                if (RoutingKt.respondWebSocketRaw((ApplicationCall) pipelineContext.getContext(), str, z, new Function2<WebSocketSession, Continuation<? super Unit>, Object>((Continuation<? super AnonymousClass1>) null) {
                                    private /* synthetic */ Object L$0;
                                    int label;

                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        AnonymousClass1 r0 = 

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                            invoke((Route) obj);
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Route route) {
                                            Intrinsics.checkNotNullParameter(route, "$this$webSocketProtocol");
                                            final String str = str2;
                                            final boolean z = z;
                                            final Function2<WebSocketServerSession, Continuation<? super Unit>, Object> function2 = function2;
                                            route.handle(new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
                                        }
                                    });
                                }
                            });
                        }
                    }
