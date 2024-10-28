package io.ktor.websocket;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/websocket/Frame;", "invoke", "(Lio/ktor/websocket/Frame;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSocketDeflateExtension.kt */
final class WebSocketDeflateExtension$Config$compressCondition$1 extends Lambda implements Function1<Frame, Boolean> {
    public static final WebSocketDeflateExtension$Config$compressCondition$1 INSTANCE = new WebSocketDeflateExtension$Config$compressCondition$1();

    WebSocketDeflateExtension$Config$compressCondition$1() {
        super(1);
    }

    public final Boolean invoke(Frame frame) {
        Intrinsics.checkNotNullParameter(frame, "it");
        return true;
    }
}
