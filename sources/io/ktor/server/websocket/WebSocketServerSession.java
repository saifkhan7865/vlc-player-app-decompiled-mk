package io.ktor.server.websocket;

import io.ktor.server.application.ApplicationCall;
import io.ktor.websocket.Frame;
import io.ktor.websocket.WebSocketSession;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/server/websocket/WebSocketServerSession;", "Lio/ktor/websocket/WebSocketSession;", "call", "Lio/ktor/server/application/ApplicationCall;", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "ktor-server-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSocketServerSession.kt */
public interface WebSocketServerSession extends WebSocketSession {
    ApplicationCall getCall();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WebSocketServerSession.kt */
    public static final class DefaultImpls {
        public static Object send(WebSocketServerSession webSocketServerSession, Frame frame, Continuation<? super Unit> continuation) {
            Object send = WebSocketSession.DefaultImpls.send(webSocketServerSession, frame, continuation);
            return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
        }
    }
}
