package io.ktor.websocket;

import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.ktor.websocket.CloseReason;
import io.netty.handler.traffic.AbstractTrafficShapingHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineName;
import org.slf4j.Logger;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"IncomingProcessorCoroutineName", "Lkotlinx/coroutines/CoroutineName;", "LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "getLOGGER", "()Lorg/slf4j/Logger;", "NORMAL_CLOSE", "Lio/ktor/websocket/CloseReason;", "OutgoingProcessorCoroutineName", "DefaultWebSocketSession", "Lio/ktor/websocket/DefaultWebSocketSession;", "session", "Lio/ktor/websocket/WebSocketSession;", "pingInterval", "", "timeoutMillis", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultWebSocketSession.kt */
public final class DefaultWebSocketSessionKt {
    /* access modifiers changed from: private */
    public static final CoroutineName IncomingProcessorCoroutineName = new CoroutineName("ws-incoming-processor");
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.websocket.WebSocket");
    /* access modifiers changed from: private */
    public static final CloseReason NORMAL_CLOSE = new CloseReason(CloseReason.Codes.NORMAL, "OK");
    /* access modifiers changed from: private */
    public static final CoroutineName OutgoingProcessorCoroutineName = new CoroutineName("ws-outgoing-processor");

    public static final Logger getLOGGER() {
        return LOGGER;
    }

    public static /* synthetic */ DefaultWebSocketSession DefaultWebSocketSession$default(WebSocketSession webSocketSession, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = -1;
        }
        if ((i & 4) != 0) {
            j2 = AbstractTrafficShapingHandler.DEFAULT_MAX_TIME;
        }
        return DefaultWebSocketSession(webSocketSession, j, j2);
    }

    public static final DefaultWebSocketSession DefaultWebSocketSession(WebSocketSession webSocketSession, long j, long j2) {
        Intrinsics.checkNotNullParameter(webSocketSession, "session");
        if (!(webSocketSession instanceof DefaultWebSocketSession)) {
            return new DefaultWebSocketSessionImpl(webSocketSession, j, j2);
        }
        throw new IllegalArgumentException("Cannot wrap other DefaultWebSocketSession".toString());
    }
}
