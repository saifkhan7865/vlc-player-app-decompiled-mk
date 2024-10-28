package io.ktor.websocket.serialization;

import io.ktor.serialization.WebsocketContentConverter;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.websocket.WebSocketSession;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.serialization.WebsocketChannelSerializationKt", f = "WebsocketChannelSerialization.kt", i = {0}, l = {50, 55}, m = "sendSerializedBase", n = {"$this$sendSerializedBase"}, s = {"L$0"})
/* compiled from: WebsocketChannelSerialization.kt */
final class WebsocketChannelSerializationKt$sendSerializedBase$2 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    WebsocketChannelSerializationKt$sendSerializedBase$2(Continuation<? super WebsocketChannelSerializationKt$sendSerializedBase$2> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return WebsocketChannelSerializationKt.sendSerializedBase((WebSocketSession) null, (Object) null, (TypeInfo) null, (WebsocketContentConverter) null, (Charset) null, this);
    }
}
