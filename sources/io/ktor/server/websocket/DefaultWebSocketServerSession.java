package io.ktor.server.websocket;

import io.ktor.websocket.DefaultWebSocketSession;
import io.ktor.websocket.Frame;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/websocket/DefaultWebSocketServerSession;", "Lio/ktor/websocket/DefaultWebSocketSession;", "Lio/ktor/server/websocket/WebSocketServerSession;", "ktor-server-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSocketServerSession.kt */
public interface DefaultWebSocketServerSession extends DefaultWebSocketSession, WebSocketServerSession {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WebSocketServerSession.kt */
    public static final class DefaultImpls {
        public static Object send(DefaultWebSocketServerSession defaultWebSocketServerSession, Frame frame, Continuation<? super Unit> continuation) {
            Object send = DefaultWebSocketSession.DefaultImpls.send(defaultWebSocketServerSession, frame, continuation);
            return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
        }
    }
}
