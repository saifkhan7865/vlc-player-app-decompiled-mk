package io.ktor.websocket;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.WebSocketReader", f = "WebSocketReader.kt", i = {0, 0, 1, 1}, l = {92, 100}, m = "parseLoop", n = {"this", "buffer", "this", "buffer"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: WebSocketReader.kt */
final class WebSocketReader$parseLoop$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WebSocketReader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WebSocketReader$parseLoop$1(WebSocketReader webSocketReader, Continuation<? super WebSocketReader$parseLoop$1> continuation) {
        super(continuation);
        this.this$0 = webSocketReader;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.parseLoop((ByteBuffer) null, this);
    }
}
