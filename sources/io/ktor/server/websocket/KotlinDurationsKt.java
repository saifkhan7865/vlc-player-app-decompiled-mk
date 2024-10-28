package io.ktor.server.websocket;

import kotlin.Metadata;
import kotlin.time.Duration;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a5\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"WebSockets", "Lio/ktor/server/websocket/WebSockets;", "pingInterval", "Lkotlin/time/Duration;", "timeout", "maxFrameSize", "", "masking", "", "WebSockets-EBYhdyk", "(Lkotlin/time/Duration;JJZ)Lio/ktor/server/websocket/WebSockets;", "ktor-server-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: KotlinDurations.kt */
public final class KotlinDurationsKt {
    /* renamed from: WebSockets-EBYhdyk  reason: not valid java name */
    public static final WebSockets m1483WebSocketsEBYhdyk(Duration duration, long j, long j2, boolean z) {
        return new WebSockets(duration != null ? Duration.m1010getInWholeMillisecondsimpl(duration.m1047unboximpl()) : 0, Duration.m1010getInWholeMillisecondsimpl(j), j2, z);
    }
}
