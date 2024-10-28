package io.netty.handler.codec.http.websocketx.extensions.compression;

import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter;

class PerFrameDeflateEncoder extends DeflateEncoder {
    /* access modifiers changed from: protected */
    public boolean removeFrameTail(WebSocketFrame webSocketFrame) {
        return true;
    }

    PerFrameDeflateEncoder(int i, int i2, boolean z) {
        super(i, i2, z, WebSocketExtensionFilter.NEVER_SKIP);
    }

    PerFrameDeflateEncoder(int i, int i2, boolean z, WebSocketExtensionFilter webSocketExtensionFilter) {
        super(i, i2, z, webSocketExtensionFilter);
    }

    public boolean acceptOutboundMessage(Object obj) throws Exception {
        if (!super.acceptOutboundMessage(obj)) {
            return false;
        }
        WebSocketFrame webSocketFrame = (WebSocketFrame) obj;
        if (extensionEncoderFilter().mustSkip(webSocketFrame)) {
            return false;
        }
        if (((obj instanceof TextWebSocketFrame) || (obj instanceof BinaryWebSocketFrame) || (obj instanceof ContinuationWebSocketFrame)) && webSocketFrame.content().readableBytes() > 0 && (webSocketFrame.rsv() & 4) == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int rsv(WebSocketFrame webSocketFrame) {
        return webSocketFrame.rsv() | 4;
    }
}
