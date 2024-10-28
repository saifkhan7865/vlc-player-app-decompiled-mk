package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.spdy.SpdyHttpHeaders;
import io.netty.util.ReferenceCountUtil;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class SpdyHttpResponseStreamIdHandler extends MessageToMessageCodec<Object, HttpMessage> {
    private static final Integer NO_ID = -1;
    private final Queue<Integer> ids = new ArrayDeque();

    public boolean acceptInboundMessage(Object obj) throws Exception {
        return (obj instanceof HttpMessage) || (obj instanceof SpdyRstStreamFrame);
    }

    /* access modifiers changed from: protected */
    public void encode(ChannelHandlerContext channelHandlerContext, HttpMessage httpMessage, List<Object> list) throws Exception {
        Integer poll = this.ids.poll();
        if (!(poll == null || poll.intValue() == NO_ID.intValue() || httpMessage.headers().contains((CharSequence) SpdyHttpHeaders.Names.STREAM_ID))) {
            httpMessage.headers().setInt(SpdyHttpHeaders.Names.STREAM_ID, poll.intValue());
        }
        list.add(ReferenceCountUtil.retain(httpMessage));
    }

    /* access modifiers changed from: protected */
    public void decode(ChannelHandlerContext channelHandlerContext, Object obj, List<Object> list) throws Exception {
        if (obj instanceof HttpMessage) {
            HttpMessage httpMessage = (HttpMessage) obj;
            if (!httpMessage.headers().contains((CharSequence) SpdyHttpHeaders.Names.STREAM_ID)) {
                this.ids.add(NO_ID);
            } else {
                this.ids.add(httpMessage.headers().getInt(SpdyHttpHeaders.Names.STREAM_ID));
            }
        } else if (obj instanceof SpdyRstStreamFrame) {
            this.ids.remove(Integer.valueOf(((SpdyRstStreamFrame) obj).streamId()));
        }
        list.add(ReferenceCountUtil.retain(obj));
    }
}
