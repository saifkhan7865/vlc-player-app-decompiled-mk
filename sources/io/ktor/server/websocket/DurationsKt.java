package io.ktor.server.websocket;

import io.ktor.server.websocket.WebSockets;
import io.netty.handler.codec.rtsp.RtspHeaders;
import j$.time.Duration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u001a/\u0010\b\u001a\u00020\u00072\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\".\u0010\u0001\u001a\u0004\u0018\u00010\u0000*\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00008Æ\u0002@Æ\u0002X\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\"*\u0010\u0002\u001a\u00020\u0000*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00008Æ\u0002@Æ\u0002X\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f\"\u0018\u0010\u0001\u001a\u0004\u0018\u00010\u0000*\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\u0012\"\u0016\u0010\u0002\u001a\u00020\u0000*\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012\"4\u0010\u001b\u001a\u0004\u0018\u00010\u0000*\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00008Æ\u0002@Æ\u0002X\u000e¢\u0006\u0012\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\"0\u0010\u0002\u001a\u00020\u0000*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00008Æ\u0002@Æ\u0002X\u000e¢\u0006\u0012\u0012\u0004\b\u001c\u0010\u001a\u001a\u0004\b\u0010\u0010\u0016\"\u0004\b\u0011\u0010\u0018¨\u0006\u001d"}, d2 = {"j$/time/Duration", "pingInterval", "timeout", "", "maxFrameSize", "", "masking", "Lio/ktor/server/websocket/WebSockets;", "WebSockets", "(Lj$/time/Duration;Lj$/time/Duration;JZ)Lio/ktor/server/websocket/WebSockets;", "Lio/ktor/server/websocket/DefaultWebSocketServerSession;", "newDuration", "getPingInterval", "(Lio/ktor/server/websocket/DefaultWebSocketServerSession;)Lj$/time/Duration;", "setPingInterval", "(Lio/ktor/server/websocket/DefaultWebSocketServerSession;Lj$/time/Duration;)V", "getTimeout", "setTimeout", "(Lio/ktor/server/websocket/WebSockets;)Lj$/time/Duration;", "Lio/ktor/server/websocket/WebSockets$WebSocketOptions;", "new", "getPingPeriod", "(Lio/ktor/server/websocket/WebSockets$WebSocketOptions;)Lj$/time/Duration;", "setPingPeriod", "(Lio/ktor/server/websocket/WebSockets$WebSocketOptions;Lj$/time/Duration;)V", "getPingPeriod$annotations", "(Lio/ktor/server/websocket/WebSockets$WebSocketOptions;)V", "pingPeriod", "getTimeout$annotations", "ktor-server-websockets"}, k = 2, mv = {1, 8, 0})
/* compiled from: Durations.kt */
public final class DurationsKt {
    public static /* synthetic */ void getPingPeriod$annotations(WebSockets.WebSocketOptions webSocketOptions) {
    }

    public static /* synthetic */ void getTimeout$annotations(WebSockets.WebSocketOptions webSocketOptions) {
    }

    public static final Duration getPingInterval(DefaultWebSocketServerSession defaultWebSocketServerSession) {
        Intrinsics.checkNotNullParameter(defaultWebSocketServerSession, "<this>");
        Long valueOf = Long.valueOf(defaultWebSocketServerSession.getPingIntervalMillis());
        if (valueOf.longValue() < 0) {
            valueOf = null;
        }
        if (valueOf != null) {
            return Duration.ofMillis(valueOf.longValue());
        }
        return null;
    }

    public static final void setPingInterval(DefaultWebSocketServerSession defaultWebSocketServerSession, Duration duration) {
        Intrinsics.checkNotNullParameter(defaultWebSocketServerSession, "<this>");
        defaultWebSocketServerSession.setPingIntervalMillis(duration != null ? duration.toMillis() : -1);
    }

    public static final Duration getTimeout(DefaultWebSocketServerSession defaultWebSocketServerSession) {
        Intrinsics.checkNotNullParameter(defaultWebSocketServerSession, "<this>");
        Duration ofMillis = Duration.ofMillis(defaultWebSocketServerSession.getTimeoutMillis());
        Intrinsics.checkNotNullExpressionValue(ofMillis, "ofMillis(timeoutMillis)");
        return ofMillis;
    }

    public static final void setTimeout(DefaultWebSocketServerSession defaultWebSocketServerSession, Duration duration) {
        Intrinsics.checkNotNullParameter(defaultWebSocketServerSession, "<this>");
        Intrinsics.checkNotNullParameter(duration, "newDuration");
        defaultWebSocketServerSession.setTimeoutMillis(duration.toMillis());
    }

    public static final WebSockets WebSockets(Duration duration, Duration duration2, long j, boolean z) {
        Intrinsics.checkNotNullParameter(duration2, RtspHeaders.Values.TIMEOUT);
        return new WebSockets(duration != null ? duration.toMillis() : 0, duration2.toMillis(), j, z);
    }

    public static final Duration getPingInterval(WebSockets webSockets) {
        Intrinsics.checkNotNullParameter(webSockets, "<this>");
        if (webSockets.getPingIntervalMillis() == 0) {
            return null;
        }
        return Duration.ofMillis(webSockets.getPingIntervalMillis());
    }

    public static final Duration getTimeout(WebSockets webSockets) {
        Intrinsics.checkNotNullParameter(webSockets, "<this>");
        Duration ofMillis = Duration.ofMillis(webSockets.getTimeoutMillis());
        Intrinsics.checkNotNullExpressionValue(ofMillis, "ofMillis(timeoutMillis)");
        return ofMillis;
    }

    public static final Duration getPingPeriod(WebSockets.WebSocketOptions webSocketOptions) {
        Intrinsics.checkNotNullParameter(webSocketOptions, "<this>");
        if (webSocketOptions.getPingPeriodMillis() == 0) {
            return null;
        }
        return Duration.ofMillis(webSocketOptions.getPingPeriodMillis());
    }

    public static final void setPingPeriod(WebSockets.WebSocketOptions webSocketOptions, Duration duration) {
        Intrinsics.checkNotNullParameter(webSocketOptions, "<this>");
        webSocketOptions.setPingPeriodMillis(duration == null ? 0 : duration.toMillis());
    }

    public static final Duration getTimeout(WebSockets.WebSocketOptions webSocketOptions) {
        Intrinsics.checkNotNullParameter(webSocketOptions, "<this>");
        Duration ofMillis = Duration.ofMillis(webSocketOptions.getTimeoutMillis());
        Intrinsics.checkNotNullExpressionValue(ofMillis, "ofMillis(timeoutMillis)");
        return ofMillis;
    }

    public static final void setTimeout(WebSockets.WebSocketOptions webSocketOptions, Duration duration) {
        Intrinsics.checkNotNullParameter(webSocketOptions, "<this>");
        Intrinsics.checkNotNullParameter(duration, "new");
        webSocketOptions.setTimeoutMillis(duration.toMillis());
    }
}
