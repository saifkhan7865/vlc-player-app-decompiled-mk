package io.netty.handler.codec.http.websocketx.extensions.compression;

import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter;

class PerFrameDeflateDecoder extends DeflateDecoder {
    /* access modifiers changed from: protected */
    public boolean appendFrameTail(WebSocketFrame webSocketFrame) {
        return true;
    }

    PerFrameDeflateDecoder(boolean z) {
        super(z, WebSocketExtensionFilter.NEVER_SKIP);
    }

    PerFrameDeflateDecoder(boolean z, WebSocketExtensionFilter webSocketExtensionFilter) {
        super(z, webSocketExtensionFilter);
    }

    public boolean acceptInboundMessage(Object obj) throws Exception {
        if (!super.acceptInboundMessage(obj)) {
            return false;
        }
        WebSocketFrame webSocketFrame = (WebSocketFrame) obj;
        if (extensionDecoderFilter().mustSkip(webSocketFrame)) {
            return false;
        }
        if (((obj instanceof TextWebSocketFrame) || (obj instanceof BinaryWebSocketFrame) || (obj instanceof ContinuationWebSocketFrame)) && (webSocketFrame.rsv() & 4) > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int newRsv(WebSocketFrame webSocketFrame) {
        return webSocketFrame.rsv() ^ 4;
    }
}
