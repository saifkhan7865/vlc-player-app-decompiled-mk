package io.ktor.server.websocket;

import androidx.core.app.NotificationCompat;
import io.ktor.server.application.ApplicationCall;
import io.ktor.websocket.Frame;
import io.ktor.websocket.WebSocketExtension;
import io.ktor.websocket.WebSocketSession;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0011\u0010)\u001a\u00020*HAø\u0001\u0000¢\u0006\u0002\u0010+J\u0019\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020\u0016HAø\u0001\u0000¢\u0006\u0002\u0010.J\t\u0010/\u001a\u00020*H\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010X\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u00020\u001aX\u000f¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0018\u0010\u001f\u001a\u00020 X\u000f¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0018\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160&X\u0005¢\u0006\u0006\u001a\u0004\b'\u0010(\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Lio/ktor/server/websocket/DelegatedWebSocketServerSession;", "Lio/ktor/server/websocket/WebSocketServerSession;", "Lio/ktor/websocket/WebSocketSession;", "call", "Lio/ktor/server/application/ApplicationCall;", "delegate", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/websocket/WebSocketSession;)V", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getDelegate", "()Lio/ktor/websocket/WebSocketSession;", "extensions", "", "Lio/ktor/websocket/WebSocketExtension;", "getExtensions", "()Ljava/util/List;", "incoming", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/websocket/Frame;", "getIncoming", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "masking", "", "getMasking", "()Z", "setMasking", "(Z)V", "maxFrameSize", "", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "outgoing", "Lkotlinx/coroutines/channels/SendChannel;", "getOutgoing", "()Lkotlinx/coroutines/channels/SendChannel;", "flush", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "send", "frame", "(Lio/ktor/websocket/Frame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "terminate", "ktor-server-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSocketServerSession.kt */
final class DelegatedWebSocketServerSession implements WebSocketServerSession, WebSocketSession {
    private final ApplicationCall call;
    private final WebSocketSession delegate;

    public Object flush(Continuation<? super Unit> continuation) {
        return this.delegate.flush(continuation);
    }

    public CoroutineContext getCoroutineContext() {
        return this.delegate.getCoroutineContext();
    }

    public List<WebSocketExtension<?>> getExtensions() {
        return this.delegate.getExtensions();
    }

    public ReceiveChannel<Frame> getIncoming() {
        return this.delegate.getIncoming();
    }

    public boolean getMasking() {
        return this.delegate.getMasking();
    }

    public long getMaxFrameSize() {
        return this.delegate.getMaxFrameSize();
    }

    public SendChannel<Frame> getOutgoing() {
        return this.delegate.getOutgoing();
    }

    public Object send(Frame frame, Continuation<? super Unit> continuation) {
        return this.delegate.send(frame, continuation);
    }

    public void setMasking(boolean z) {
        this.delegate.setMasking(z);
    }

    public void setMaxFrameSize(long j) {
        this.delegate.setMaxFrameSize(j);
    }

    @Deprecated(message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = {"kotlinx.coroutines.cancel"}))
    public void terminate() {
        this.delegate.terminate();
    }

    public DelegatedWebSocketServerSession(ApplicationCall applicationCall, WebSocketSession webSocketSession) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(webSocketSession, "delegate");
        this.call = applicationCall;
        this.delegate = webSocketSession;
    }

    public ApplicationCall getCall() {
        return this.call;
    }

    public final WebSocketSession getDelegate() {
        return this.delegate;
    }
}
