package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.MessageAggregator;

public class WebSocketFrameAggregator extends MessageAggregator<WebSocketFrame, WebSocketFrame, ContinuationWebSocketFrame, WebSocketFrame> {
    /* access modifiers changed from: protected */
    public boolean isContentLengthInvalid(WebSocketFrame webSocketFrame, int i) {
        return false;
    }

    /* access modifiers changed from: protected */
    public Object newContinueResponse(WebSocketFrame webSocketFrame, int i, ChannelPipeline channelPipeline) {
        return null;
    }

    public WebSocketFrameAggregator(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public boolean isStartMessage(WebSocketFrame webSocketFrame) throws Exception {
        return (webSocketFrame instanceof TextWebSocketFrame) || (webSocketFrame instanceof BinaryWebSocketFrame);
    }

    /* access modifiers changed from: protected */
    public boolean isContentMessage(WebSocketFrame webSocketFrame) throws Exception {
        return webSocketFrame instanceof ContinuationWebSocketFrame;
    }

    /* access modifiers changed from: protected */
    public boolean isLastContentMessage(ContinuationWebSocketFrame continuationWebSocketFrame) throws Exception {
        return isContentMessage((WebSocketFrame) continuationWebSocketFrame) && continuationWebSocketFrame.isFinalFragment();
    }

    /* access modifiers changed from: protected */
    public boolean isAggregated(WebSocketFrame webSocketFrame) throws Exception {
        if (webSocketFrame.isFinalFragment()) {
            return !isContentMessage(webSocketFrame);
        }
        if (isStartMessage(webSocketFrame) || isContentMessage(webSocketFrame)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean closeAfterContinueResponse(Object obj) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public boolean ignoreContentAfterContinueResponse(Object obj) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public WebSocketFrame beginAggregation(WebSocketFrame webSocketFrame, ByteBuf byteBuf) throws Exception {
        if (webSocketFrame instanceof TextWebSocketFrame) {
            return new TextWebSocketFrame(true, webSocketFrame.rsv(), byteBuf);
        }
        if (webSocketFrame instanceof BinaryWebSocketFrame) {
            return new BinaryWebSocketFrame(true, webSocketFrame.rsv(), byteBuf);
        }
        throw new Error();
    }
}
