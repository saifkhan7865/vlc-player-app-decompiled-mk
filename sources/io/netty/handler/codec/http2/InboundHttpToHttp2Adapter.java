package io.netty.handler.codec.http2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpScheme;
import io.netty.handler.codec.http2.HttpConversionUtil;

public class InboundHttpToHttp2Adapter extends ChannelInboundHandlerAdapter {
    private final Http2Connection connection;
    private final Http2FrameListener listener;

    public InboundHttpToHttp2Adapter(Http2Connection http2Connection, Http2FrameListener http2FrameListener) {
        this.connection = http2Connection;
        this.listener = http2FrameListener;
    }

    private static int getStreamId(Http2Connection http2Connection, HttpHeaders httpHeaders) {
        return httpHeaders.getInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_ID.text(), http2Connection.remote().incrementAndGetNextStreamId());
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof FullHttpMessage) {
            handle(channelHandlerContext, this.connection, this.listener, (FullHttpMessage) obj);
        } else {
            super.channelRead(channelHandlerContext, obj);
        }
    }

    static void handle(ChannelHandlerContext channelHandlerContext, Http2Connection http2Connection, Http2FrameListener http2FrameListener, FullHttpMessage fullHttpMessage) throws Http2Exception {
        try {
            int streamId = getStreamId(http2Connection, fullHttpMessage.headers());
            Http2Stream stream = http2Connection.stream(streamId);
            if (stream == null) {
                stream = http2Connection.remote().createStream(streamId, false);
            }
            Http2Stream http2Stream = stream;
            fullHttpMessage.headers().set((CharSequence) HttpConversionUtil.ExtensionHeaderNames.SCHEME.text(), (Object) HttpScheme.HTTP.name());
            Http2Headers http2Headers = HttpConversionUtil.toHttp2Headers((HttpMessage) fullHttpMessage, true);
            boolean isReadable = fullHttpMessage.content().isReadable();
            boolean z = !fullHttpMessage.trailingHeaders().isEmpty();
            http2FrameListener.onHeadersRead(channelHandlerContext, streamId, http2Headers, 0, !isReadable && !z);
            if (isReadable) {
                http2FrameListener.onDataRead(channelHandlerContext, streamId, fullHttpMessage.content(), 0, !z);
            }
            if (z) {
                http2FrameListener.onHeadersRead(channelHandlerContext, streamId, HttpConversionUtil.toHttp2Headers(fullHttpMessage.trailingHeaders(), true), 0, true);
            }
            http2Stream.closeRemoteSide();
        } finally {
            fullHttpMessage.release();
        }
    }
}