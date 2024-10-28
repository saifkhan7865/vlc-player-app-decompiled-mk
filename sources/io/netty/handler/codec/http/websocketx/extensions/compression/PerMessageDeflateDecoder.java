package io.netty.handler.codec.http.websocketx.extensions.compression;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter;
import java.util.List;

class PerMessageDeflateDecoder extends DeflateDecoder {
    private boolean compressing;

    PerMessageDeflateDecoder(boolean z) {
        super(z, WebSocketExtensionFilter.NEVER_SKIP);
    }

    PerMessageDeflateDecoder(boolean z, WebSocketExtensionFilter webSocketExtensionFilter) {
        super(z, webSocketExtensionFilter);
    }

    public boolean acceptInboundMessage(Object obj) throws Exception {
        if (!super.acceptInboundMessage(obj)) {
            return false;
        }
        WebSocketFrame webSocketFrame = (WebSocketFrame) obj;
        if (extensionDecoderFilter().mustSkip(webSocketFrame)) {
            if (!this.compressing) {
                return false;
            }
            throw new IllegalStateException("Cannot skip per message deflate decoder, compression in progress");
        } else if ((((webSocketFrame instanceof TextWebSocketFrame) || (webSocketFrame instanceof BinaryWebSocketFrame)) && (webSocketFrame.rsv() & 4) > 0) || ((webSocketFrame instanceof ContinuationWebSocketFrame) && this.compressing)) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public int newRsv(WebSocketFrame webSocketFrame) {
        return (webSocketFrame.rsv() & 4) > 0 ? webSocketFrame.rsv() ^ 4 : webSocketFrame.rsv();
    }

    /* access modifiers changed from: protected */
    public boolean appendFrameTail(WebSocketFrame webSocketFrame) {
        return webSocketFrame.isFinalFragment();
    }

    /* access modifiers changed from: protected */
    public void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        super.decode(channelHandlerContext, webSocketFrame, list);
        if (webSocketFrame.isFinalFragment()) {
            this.compressing = false;
        } else if ((webSocketFrame instanceof TextWebSocketFrame) || (webSocketFrame instanceof BinaryWebSocketFrame)) {
            this.compressing = true;
        }
    }
}
