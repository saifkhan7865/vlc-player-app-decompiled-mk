package io.ktor.server.websocket;

import androidx.core.app.NotificationCompat;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.InternalAPI;
import io.ktor.websocket.CloseReason;
import io.ktor.websocket.DefaultWebSocketSession;
import io.ktor.websocket.Frame;
import io.ktor.websocket.WebSocketExtension;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0011\u00104\u001a\u000205HAø\u0001\u0000¢\u0006\u0002\u00106J\u0019\u00107\u001a\u0002052\u0006\u00108\u001a\u00020\u001bHAø\u0001\u0000¢\u0006\u0002\u00109J\u001d\u0010:\u001a\u0002052\u0012\b\u0002\u0010;\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u0015H\u0001J\t\u0010<\u001a\u000205H\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u0015X\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0005¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001e\u001a\u00020\u001fX\u000f¢\u0006\f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0018\u0010$\u001a\u00020%X\u000f¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0018\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001b0+X\u0005¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0018\u0010.\u001a\u00020%X\u000f¢\u0006\f\u001a\u0004\b/\u0010'\"\u0004\b0\u0010)R\u0018\u00101\u001a\u00020%X\u000f¢\u0006\f\u001a\u0004\b2\u0010'\"\u0004\b3\u0010)\u0002\u0004\n\u0002\b\u0019¨\u0006="}, d2 = {"Lio/ktor/server/websocket/DelegatedDefaultWebSocketServerSession;", "Lio/ktor/server/websocket/DefaultWebSocketServerSession;", "Lio/ktor/websocket/DefaultWebSocketSession;", "call", "Lio/ktor/server/application/ApplicationCall;", "delegate", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/websocket/DefaultWebSocketSession;)V", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "closeReason", "Lkotlinx/coroutines/Deferred;", "Lio/ktor/websocket/CloseReason;", "getCloseReason", "()Lkotlinx/coroutines/Deferred;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getDelegate", "()Lio/ktor/websocket/DefaultWebSocketSession;", "extensions", "", "Lio/ktor/websocket/WebSocketExtension;", "getExtensions", "()Ljava/util/List;", "incoming", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/websocket/Frame;", "getIncoming", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "masking", "", "getMasking", "()Z", "setMasking", "(Z)V", "maxFrameSize", "", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "outgoing", "Lkotlinx/coroutines/channels/SendChannel;", "getOutgoing", "()Lkotlinx/coroutines/channels/SendChannel;", "pingIntervalMillis", "getPingIntervalMillis", "setPingIntervalMillis", "timeoutMillis", "getTimeoutMillis", "setTimeoutMillis", "flush", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "send", "frame", "(Lio/ktor/websocket/Frame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "negotiatedExtensions", "terminate", "ktor-server-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSocketServerSession.kt */
final class DelegatedDefaultWebSocketServerSession implements DefaultWebSocketServerSession, DefaultWebSocketSession {
    private final ApplicationCall call;
    private final DefaultWebSocketSession delegate;

    public Object flush(Continuation<? super Unit> continuation) {
        return this.delegate.flush(continuation);
    }

    public Deferred<CloseReason> getCloseReason() {
        return this.delegate.getCloseReason();
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

    public long getPingIntervalMillis() {
        return this.delegate.getPingIntervalMillis();
    }

    public long getTimeoutMillis() {
        return this.delegate.getTimeoutMillis();
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

    public void setPingIntervalMillis(long j) {
        this.delegate.setPingIntervalMillis(j);
    }

    public void setTimeoutMillis(long j) {
        this.delegate.setTimeoutMillis(j);
    }

    @InternalAPI
    public void start(List<? extends WebSocketExtension<?>> list) {
        Intrinsics.checkNotNullParameter(list, "negotiatedExtensions");
        this.delegate.start(list);
    }

    @Deprecated(message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = {"kotlinx.coroutines.cancel"}))
    public void terminate() {
        this.delegate.terminate();
    }

    public DelegatedDefaultWebSocketServerSession(ApplicationCall applicationCall, DefaultWebSocketSession defaultWebSocketSession) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(defaultWebSocketSession, "delegate");
        this.call = applicationCall;
        this.delegate = defaultWebSocketSession;
    }

    public ApplicationCall getCall() {
        return this.call;
    }

    public final DefaultWebSocketSession getDelegate() {
        return this.delegate;
    }
}
