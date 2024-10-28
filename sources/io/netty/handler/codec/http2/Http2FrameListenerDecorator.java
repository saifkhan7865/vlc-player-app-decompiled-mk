package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.ObjectUtil;

public class Http2FrameListenerDecorator implements Http2FrameListener {
    protected final Http2FrameListener listener;

    public Http2FrameListenerDecorator(Http2FrameListener http2FrameListener) {
        this.listener = (Http2FrameListener) ObjectUtil.checkNotNull(http2FrameListener, "listener");
    }

    public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) throws Http2Exception {
        return this.listener.onDataRead(channelHandlerContext, i, byteBuf, i2, z);
    }

    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z) throws Http2Exception {
        this.listener.onHeadersRead(channelHandlerContext, i, http2Headers, i2, z);
    }

    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) throws Http2Exception {
        this.listener.onHeadersRead(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2);
    }

    public void onPriorityRead(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z) throws Http2Exception {
        this.listener.onPriorityRead(channelHandlerContext, i, i2, s, z);
    }

    public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) throws Http2Exception {
        this.listener.onRstStreamRead(channelHandlerContext, i, j);
    }

    public void onSettingsAckRead(ChannelHandlerContext channelHandlerContext) throws Http2Exception {
        this.listener.onSettingsAckRead(channelHandlerContext);
    }

    public void onSettingsRead(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) throws Http2Exception {
        this.listener.onSettingsRead(channelHandlerContext, http2Settings);
    }

    public void onPingRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
        this.listener.onPingRead(channelHandlerContext, j);
    }

    public void onPingAckRead(ChannelHandlerContext channelHandlerContext, long j) throws Http2Exception {
        this.listener.onPingAckRead(channelHandlerContext, j);
    }

    public void onPushPromiseRead(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3) throws Http2Exception {
        this.listener.onPushPromiseRead(channelHandlerContext, i, i2, http2Headers, i3);
    }

    public void onGoAwayRead(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf) throws Http2Exception {
        this.listener.onGoAwayRead(channelHandlerContext, i, j, byteBuf);
    }

    public void onWindowUpdateRead(ChannelHandlerContext channelHandlerContext, int i, int i2) throws Http2Exception {
        this.listener.onWindowUpdateRead(channelHandlerContext, i, i2);
    }

    public void onUnknownFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf) throws Http2Exception {
        this.listener.onUnknownFrame(channelHandlerContext, b, i, http2Flags, byteBuf);
    }
}
