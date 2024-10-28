package io.ktor.server.websocket;

import io.ktor.serialization.WebsocketContentConverter;
import io.ktor.server.application.Application;
import io.ktor.server.application.BaseApplicationPlugin;
import io.ktor.server.application.DefaultApplicationEventsKt;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.websocket.WebSocketExtension;
import io.ktor.websocket.WebSocketExtensionsConfig;
import io.netty.handler.traffic.AbstractTrafficShapingHandler;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0002 !B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB9\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\b\u0010\u001e\u001a\u00020\u001fH\u0002R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019¨\u0006\""}, d2 = {"Lio/ktor/server/websocket/WebSockets;", "Lkotlinx/coroutines/CoroutineScope;", "pingIntervalMillis", "", "timeoutMillis", "maxFrameSize", "masking", "", "(JJJZ)V", "extensionsConfig", "Lio/ktor/websocket/WebSocketExtensionsConfig;", "contentConverter", "Lio/ktor/serialization/WebsocketContentConverter;", "(JJJZLio/ktor/websocket/WebSocketExtensionsConfig;Lio/ktor/serialization/WebsocketContentConverter;)V", "getContentConverter", "()Lio/ktor/serialization/WebsocketContentConverter;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getExtensionsConfig", "()Lio/ktor/websocket/WebSocketExtensionsConfig;", "getMasking", "()Z", "getMaxFrameSize", "()J", "parent", "Lkotlinx/coroutines/CompletableJob;", "getPingIntervalMillis", "getTimeoutMillis", "shutdown", "", "Plugin", "WebSocketOptions", "ktor-server-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSockets.kt */
public final class WebSockets implements CoroutineScope {
    /* access modifiers changed from: private */
    public static final AttributeKey<List<WebSocketExtension<?>>> EXTENSIONS_KEY = new AttributeKey<>("WebSocket extensions");
    public static final Plugin Plugin = new Plugin((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final AttributeKey<WebSockets> key = new AttributeKey<>("WebSockets");
    private final WebsocketContentConverter contentConverter;
    private final WebSocketExtensionsConfig extensionsConfig;
    private final boolean masking;
    private final long maxFrameSize;
    private final CompletableJob parent;
    private final long pingIntervalMillis;
    private final long timeoutMillis;

    public /* synthetic */ WebSockets(long j, long j2, long j3, boolean z, WebSocketExtensionsConfig webSocketExtensionsConfig, WebsocketContentConverter websocketContentConverter, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, z, webSocketExtensionsConfig, websocketContentConverter);
    }

    private WebSockets(long j, long j2, long j3, boolean z, WebSocketExtensionsConfig webSocketExtensionsConfig, WebsocketContentConverter websocketContentConverter) {
        this.pingIntervalMillis = j;
        this.timeoutMillis = j2;
        this.maxFrameSize = j3;
        this.masking = z;
        this.extensionsConfig = webSocketExtensionsConfig;
        this.contentConverter = websocketContentConverter;
        this.parent = JobKt.Job$default((Job) null, 1, (Object) null);
        if (j < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (j2 < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (j3 <= 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final long getPingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    public final long getTimeoutMillis() {
        return this.timeoutMillis;
    }

    public final long getMaxFrameSize() {
        return this.maxFrameSize;
    }

    public final boolean getMasking() {
        return this.masking;
    }

    public final WebSocketExtensionsConfig getExtensionsConfig() {
        return this.extensionsConfig;
    }

    public final WebsocketContentConverter getContentConverter() {
        return this.contentConverter;
    }

    public WebSockets(long j, long j2, long j3, boolean z) {
        this(j, j2, j3, z, new WebSocketExtensionsConfig(), (WebsocketContentConverter) null);
    }

    public CoroutineContext getCoroutineContext() {
        return this.parent;
    }

    /* access modifiers changed from: private */
    public final void shutdown() {
        this.parent.complete();
    }

    @KtorDsl
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\u001f\u001a\u00020 2\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020 0\"¢\u0006\u0002\b#R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\u001a\u0010\u001c\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018¨\u0006$"}, d2 = {"Lio/ktor/server/websocket/WebSockets$WebSocketOptions;", "", "()V", "contentConverter", "Lio/ktor/serialization/WebsocketContentConverter;", "getContentConverter", "()Lio/ktor/serialization/WebsocketContentConverter;", "setContentConverter", "(Lio/ktor/serialization/WebsocketContentConverter;)V", "extensionsConfig", "Lio/ktor/websocket/WebSocketExtensionsConfig;", "getExtensionsConfig$ktor_server_websockets", "()Lio/ktor/websocket/WebSocketExtensionsConfig;", "masking", "", "getMasking", "()Z", "setMasking", "(Z)V", "maxFrameSize", "", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "pingPeriodMillis", "getPingPeriodMillis", "setPingPeriodMillis", "timeoutMillis", "getTimeoutMillis", "setTimeoutMillis", "extensions", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-server-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WebSockets.kt */
    public static final class WebSocketOptions {
        private WebsocketContentConverter contentConverter;
        private final WebSocketExtensionsConfig extensionsConfig = new WebSocketExtensionsConfig();
        private boolean masking;
        private long maxFrameSize = Long.MAX_VALUE;
        private long pingPeriodMillis;
        private long timeoutMillis = AbstractTrafficShapingHandler.DEFAULT_MAX_TIME;

        public final WebSocketExtensionsConfig getExtensionsConfig$ktor_server_websockets() {
            return this.extensionsConfig;
        }

        public final long getPingPeriodMillis() {
            return this.pingPeriodMillis;
        }

        public final void setPingPeriodMillis(long j) {
            this.pingPeriodMillis = j;
        }

        public final long getTimeoutMillis() {
            return this.timeoutMillis;
        }

        public final void setTimeoutMillis(long j) {
            this.timeoutMillis = j;
        }

        public final long getMaxFrameSize() {
            return this.maxFrameSize;
        }

        public final void setMaxFrameSize(long j) {
            this.maxFrameSize = j;
        }

        public final boolean getMasking() {
            return this.masking;
        }

        public final void setMasking(boolean z) {
            this.masking = z;
        }

        public final WebsocketContentConverter getContentConverter() {
            return this.contentConverter;
        }

        public final void setContentConverter(WebsocketContentConverter websocketContentConverter) {
            this.contentConverter = websocketContentConverter;
        }

        public final void extensions(Function1<? super WebSocketExtensionsConfig, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "block");
            function1.invoke(this.extensionsConfig);
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J)\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00022\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\b\u0013H\u0016R!\u0010\u0006\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0014"}, d2 = {"Lio/ktor/server/websocket/WebSockets$Plugin;", "Lio/ktor/server/application/BaseApplicationPlugin;", "Lio/ktor/server/application/Application;", "Lio/ktor/server/websocket/WebSockets$WebSocketOptions;", "Lio/ktor/server/websocket/WebSockets;", "()V", "EXTENSIONS_KEY", "Lio/ktor/util/AttributeKey;", "", "Lio/ktor/websocket/WebSocketExtension;", "getEXTENSIONS_KEY", "()Lio/ktor/util/AttributeKey;", "key", "getKey", "install", "pipeline", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "ktor-server-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WebSockets.kt */
    public static final class Plugin implements BaseApplicationPlugin<Application, WebSocketOptions, WebSockets> {
        public /* synthetic */ Plugin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Plugin() {
        }

        public AttributeKey<WebSockets> getKey() {
            return WebSockets.key;
        }

        public final AttributeKey<List<WebSocketExtension<?>>> getEXTENSIONS_KEY() {
            return WebSockets.EXTENSIONS_KEY;
        }

        public WebSockets install(Application application, Function1<? super WebSocketOptions, Unit> function1) {
            Intrinsics.checkNotNullParameter(application, "pipeline");
            Intrinsics.checkNotNullParameter(function1, "configure");
            WebSocketOptions webSocketOptions = new WebSocketOptions();
            function1.invoke(webSocketOptions);
            WebSockets webSockets = new WebSockets(webSocketOptions.getPingPeriodMillis(), webSocketOptions.getTimeoutMillis(), webSocketOptions.getMaxFrameSize(), webSocketOptions.getMasking(), webSocketOptions.getExtensionsConfig$ktor_server_websockets(), webSocketOptions.getContentConverter(), (DefaultConstructorMarker) null);
            application.getEnvironment().getMonitor().subscribe(DefaultApplicationEventsKt.getApplicationStopPreparing(), new WebSockets$Plugin$install$1$1(webSockets));
            application.getSendPipeline().intercept(ApplicationSendPipeline.Phases.getTransform(), new WebSockets$Plugin$install$1$2((Continuation<? super WebSockets$Plugin$install$1$2>) null));
            return webSockets;
        }
    }
}
