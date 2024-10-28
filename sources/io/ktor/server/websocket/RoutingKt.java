package io.ktor.server.websocket;

import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ResponseTypeKt;
import io.ktor.server.routing.Route;
import io.ktor.server.routing.RoutingBuilderKt;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.websocket.WebSocketSession;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aF\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u0015\u0010\f\u001a\u00020\u0001*\u00020\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a>\u0010\u000f\u001a\u00020\u0001*\u00020\u00102'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001aT\u0010\u0012\u001a\u00020\u0001*\u00020\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001aO\u0010\u0019\u001a\u00020\u0001*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001aQ\u0010\u0019\u001a\u00020\u0001*\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u00142'\u0010\u001e\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\n2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 H\u0007ø\u0001\u0000¢\u0006\u0002\u0010!\u001aG\u0010\u0019\u001a\u00020\u0001*\u00020\u001a2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010\"\u001a/\u0010\u001d\u001a\u00020\u0001*\u00020\u001a2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0017\u0010#\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010$¢\u0006\u0002\b\nH\u0002\u001aY\u0010%\u001a\u00020\u0001*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010&\u001aO\u0010%\u001a\u00020\u0001*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001aQ\u0010%\u001a\u00020\u0001*\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u00142'\u0010\u001e\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\n2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 H\u0007ø\u0001\u0000¢\u0006\u0002\u0010!\u001aQ\u0010%\u001a\u00020\u0001*\u00020\u001a2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010'\u001aG\u0010%\u001a\u00020\u0001*\u00020\u001a2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142'\u0010\u0005\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010\"\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"handleServerSession", "", "Lio/ktor/websocket/DefaultWebSocketSession;", "call", "Lio/ktor/server/application/ApplicationCall;", "handler", "Lkotlin/Function2;", "Lio/ktor/server/websocket/DefaultWebSocketServerSession;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/websocket/DefaultWebSocketSession;Lio/ktor/server/application/ApplicationCall;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinSession", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "proceedWebSocket", "Lio/ktor/server/websocket/WebSocketServerSession;", "(Lio/ktor/server/websocket/WebSocketServerSession;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondWebSocketRaw", "protocol", "", "negotiateExtensions", "", "Lio/ktor/websocket/WebSocketSession;", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "webSocket", "Lio/ktor/server/routing/Route;", "path", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "webSocketProtocol", "webSocketHandler", "nothing", "", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Ljava/lang/Void;)V", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "block", "Lkotlin/Function1;", "webSocketRaw", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function2;)V", "(Lio/ktor/server/routing/Route;Ljava/lang/String;ZLkotlin/jvm/functions/Function2;)V", "ktor-server-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Routing.kt */
public final class RoutingKt {
    public static /* synthetic */ void webSocketRaw$default(Route route, String str, String str2, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        webSocketRaw(route, str, str2, (Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object>) function2);
    }

    public static final void webSocketRaw(Route route, String str, String str2, Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function2, "handler");
        webSocketRaw(route, str, str2, false, function2);
    }

    public static /* synthetic */ void webSocketRaw$default(Route route, String str, String str2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        webSocketRaw(route, str, str2, z, function2);
    }

    public static final void webSocketRaw(Route route, String str, String str2, boolean z, Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function2, "handler");
        ApplicationPluginKt.plugin(io.ktor.server.routing.RoutingKt.getApplication(route), WebSockets.Plugin);
        RoutingBuilderKt.route(route, str, HttpMethod.Companion.getGet(), new RoutingKt$webSocketRaw$1(str2, z, function2));
    }

    public static /* synthetic */ void webSocketRaw$default(Route route, String str, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        webSocketRaw(route, str, function2);
    }

    public static final void webSocketRaw(Route route, String str, Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function2, "handler");
        webSocketRaw(route, str, false, function2);
    }

    public static /* synthetic */ void webSocketRaw$default(Route route, String str, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        webSocketRaw(route, str, z, (Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object>) function2);
    }

    public static final void webSocketRaw(Route route, String str, boolean z, Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function2, "handler");
        ApplicationPluginKt.plugin(io.ktor.server.routing.RoutingKt.getApplication(route), WebSockets.Plugin);
        RoutingBuilderKt.header(route, HttpHeaders.INSTANCE.getConnection(), "Upgrade", new RoutingKt$webSocketRaw$2(str, z, function2));
    }

    public static /* synthetic */ void webSocketRaw$default(Route route, String str, Function2 function2, Void voidR, int i, Object obj) {
        if ((i & 4) != 0) {
            voidR = null;
        }
        webSocketRaw(route, str, (Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object>) function2, voidR);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use webSocketRaw(protocol = protocol, handler = handler) instead.", replaceWith = @ReplaceWith(expression = "webSocketRaw(protocol = webSocketProtocol, handler = webSocketHandler)", imports = {}))
    public static final void webSocketRaw(Route route, String str, Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2, Void voidR) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, "webSocketProtocol");
        Intrinsics.checkNotNullParameter(function2, "webSocketHandler");
        webSocketRaw(route, str, function2);
    }

    public static /* synthetic */ void webSocket$default(Route route, String str, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        webSocket(route, str, function2);
    }

    public static final void webSocket(Route route, String str, Function2<? super DefaultWebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function2, "handler");
        webSocketRaw(route, str, true, (Function2<? super WebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object>) new RoutingKt$webSocket$1(function2, (Continuation<? super RoutingKt$webSocket$1>) null));
    }

    public static /* synthetic */ void webSocket$default(Route route, String str, Function2 function2, Void voidR, int i, Object obj) {
        if ((i & 4) != 0) {
            voidR = null;
        }
        webSocket(route, str, (Function2<? super DefaultWebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object>) function2, voidR);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use webSocket(protocol = protocol, handler = handler) instead.", replaceWith = @ReplaceWith(expression = "webSocket(protocol = webSocketProtocol, handler = webSocketHandler)", imports = {}))
    public static final void webSocket(Route route, String str, Function2<? super DefaultWebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2, Void voidR) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, "webSocketProtocol");
        Intrinsics.checkNotNullParameter(function2, "webSocketHandler");
        webSocket(route, str, function2);
    }

    public static /* synthetic */ void webSocket$default(Route route, String str, String str2, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        webSocket(route, str, str2, (Function2<? super DefaultWebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object>) function2);
    }

    public static final void webSocket(Route route, String str, String str2, Function2<? super DefaultWebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function2, "handler");
        webSocketRaw(route, str, str2, true, new RoutingKt$webSocket$2(function2, (Continuation<? super RoutingKt$webSocket$2>) null));
    }

    static /* synthetic */ Object respondWebSocketRaw$default(ApplicationCall applicationCall, String str, boolean z, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return respondWebSocketRaw(applicationCall, str, z, function2, continuation);
    }

    /* access modifiers changed from: private */
    public static final Object respondWebSocketRaw(ApplicationCall applicationCall, String str, boolean z, Function2<? super WebSocketSession, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        WebSocketUpgrade webSocketUpgrade = new WebSocketUpgrade(applicationCall, str, z, function2);
        if (!(webSocketUpgrade instanceof OutgoingContent) && !(webSocketUpgrade instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            KType typeOf = Reflection.typeOf(WebSocketUpgrade.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(WebSocketUpgrade.class), typeOf));
        }
        Object execute = applicationCall.getResponse().getPipeline().execute(applicationCall, webSocketUpgrade, continuation);
        if (execute == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return execute;
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void webSocketProtocol(Route route, String str, Function1<? super Route, Unit> function1) {
        if (str == null) {
            function1.invoke(route);
        } else {
            function1.invoke(route.createChild(new WebSocketProtocolsSelector(str)));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0093 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object proceedWebSocket(io.ktor.server.websocket.WebSocketServerSession r9, kotlin.jvm.functions.Function2<? super io.ktor.server.websocket.DefaultWebSocketServerSession, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.server.websocket.RoutingKt$proceedWebSocket$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.server.websocket.RoutingKt$proceedWebSocket$1 r0 = (io.ktor.server.websocket.RoutingKt$proceedWebSocket$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.server.websocket.RoutingKt$proceedWebSocket$1 r0 = new io.ktor.server.websocket.RoutingKt$proceedWebSocket$1
            r0.<init>(r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0094
        L_0x002d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0035:
            java.lang.Object r9 = r0.L$0
            io.ktor.websocket.DefaultWebSocketSession r9 = (io.ktor.websocket.DefaultWebSocketSession) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0086
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r11)
            io.ktor.server.application.Application r11 = io.ktor.server.websocket.WebSocketServerSessionKt.getApplication(r9)
            io.ktor.util.pipeline.Pipeline r11 = (io.ktor.util.pipeline.Pipeline) r11
            io.ktor.server.websocket.WebSockets$Plugin r2 = io.ktor.server.websocket.WebSockets.Plugin
            io.ktor.server.application.Plugin r2 = (io.ktor.server.application.Plugin) r2
            java.lang.Object r11 = io.ktor.server.application.ApplicationPluginKt.plugin(r11, r2)
            io.ktor.server.websocket.WebSockets r11 = (io.ktor.server.websocket.WebSockets) r11
            r2 = r9
            io.ktor.websocket.WebSocketSession r2 = (io.ktor.websocket.WebSocketSession) r2
            long r5 = r11.getPingIntervalMillis()
            long r7 = r11.getTimeoutMillis()
            io.ktor.websocket.DefaultWebSocketSession r11 = io.ktor.websocket.DefaultWebSocketSessionKt.DefaultWebSocketSession(r2, r5, r7)
            io.ktor.server.application.ApplicationCall r2 = r9.getCall()
            io.ktor.util.Attributes r2 = r2.getAttributes()
            io.ktor.server.websocket.WebSockets$Plugin r5 = io.ktor.server.websocket.WebSockets.Plugin
            io.ktor.util.AttributeKey r5 = r5.getEXTENSIONS_KEY()
            java.lang.Object r2 = r2.get(r5)
            java.util.List r2 = (java.util.List) r2
            r11.start(r2)
            io.ktor.server.application.ApplicationCall r9 = r9.getCall()
            r0.L$0 = r11
            r0.label = r4
            java.lang.Object r9 = handleServerSession(r11, r9, r10, r0)
            if (r9 != r1) goto L_0x0085
            return r1
        L_0x0085:
            r9 = r11
        L_0x0086:
            kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
            r10 = 0
            r0.L$0 = r10
            r0.label = r3
            java.lang.Object r9 = joinSession(r9, r0)
            if (r9 != r1) goto L_0x0094
            return r1
        L_0x0094:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.websocket.RoutingKt.proceedWebSocket(io.ktor.server.websocket.WebSocketServerSession, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Object joinSession(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        CoroutineContext.Element element = coroutineScope.getCoroutineContext().get(Job.Key);
        Intrinsics.checkNotNull(element);
        Object join = ((Job) element).join(continuation);
        return join == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? join : Unit.INSTANCE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: io.ktor.server.application.ApplicationCall} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: io.ktor.server.application.ApplicationCall} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0087 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object handleServerSession(io.ktor.websocket.DefaultWebSocketSession r6, io.ktor.server.application.ApplicationCall r7, kotlin.jvm.functions.Function2<? super io.ktor.server.websocket.DefaultWebSocketServerSession, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            java.lang.String r0 = "Starting websocket session for "
            boolean r1 = r9 instanceof io.ktor.server.websocket.RoutingKt$handleServerSession$1
            if (r1 == 0) goto L_0x0016
            r1 = r9
            io.ktor.server.websocket.RoutingKt$handleServerSession$1 r1 = (io.ktor.server.websocket.RoutingKt$handleServerSession$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r9 = r1.label
            int r9 = r9 - r3
            r1.label = r9
            goto L_0x001b
        L_0x0016:
            io.ktor.server.websocket.RoutingKt$handleServerSession$1 r1 = new io.ktor.server.websocket.RoutingKt$handleServerSession$1
            r1.<init>(r9)
        L_0x001b:
            java.lang.Object r9 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x0049
            if (r3 == r5) goto L_0x003c
            if (r3 != r4) goto L_0x0034
            java.lang.Object r6 = r1.L$0
            r7 = r6
            io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            goto L_0x0088
        L_0x0034:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003c:
            java.lang.Object r6 = r1.L$1
            r7 = r6
            io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
            java.lang.Object r6 = r1.L$0
            io.ktor.websocket.DefaultWebSocketSession r6 = (io.ktor.websocket.DefaultWebSocketSession) r6
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            goto L_0x0078
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r9)
            org.slf4j.Logger r9 = io.ktor.server.websocket.WebSocketsKt.getLOGGER()     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            r3.<init>(r0)     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            io.ktor.server.request.ApplicationRequest r0 = r7.getRequest()     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            java.lang.String r0 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r0)     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            r3.append(r0)     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            java.lang.String r0 = r3.toString()     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            r9.trace(r0)     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            io.ktor.server.websocket.DefaultWebSocketServerSession r9 = io.ktor.server.websocket.WebSocketServerSessionKt.toServerSession((io.ktor.websocket.DefaultWebSocketSession) r6, (io.ktor.server.application.ApplicationCall) r7)     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            r1.L$0 = r6     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            r1.L$1 = r7     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            r1.label = r5     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            java.lang.Object r8 = r8.invoke(r9, r1)     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            if (r8 != r2) goto L_0x0078
            return r2
        L_0x0078:
            io.ktor.websocket.WebSocketSession r6 = (io.ktor.websocket.WebSocketSession) r6     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            r1.L$0 = r7     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            r8 = 0
            r1.L$1 = r8     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            r1.label = r4     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            java.lang.Object r6 = io.ktor.websocket.WebSocketSessionKt.close$default(r6, r8, r1, r5, r8)     // Catch:{ CancellationException -> 0x009c, ChannelIOException -> 0x009a, all -> 0x008b }
            if (r6 != r2) goto L_0x0088
            return r2
        L_0x0088:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x008b:
            r6 = move-exception
            io.ktor.server.application.Application r7 = r7.getApplication()
            org.slf4j.Logger r7 = io.ktor.server.application.ApplicationKt.getLog(r7)
            java.lang.String r8 = "Websocket handler failed"
            r7.error((java.lang.String) r8, (java.lang.Throwable) r6)
            throw r6
        L_0x009a:
            r6 = move-exception
            throw r6
        L_0x009c:
            r6 = move-exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.websocket.RoutingKt.handleServerSession(io.ktor.websocket.DefaultWebSocketSession, io.ktor.server.application.ApplicationCall, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
