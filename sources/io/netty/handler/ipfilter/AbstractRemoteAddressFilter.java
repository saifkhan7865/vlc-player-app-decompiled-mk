package io.netty.handler.ipfilter;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.SocketAddress;

public abstract class AbstractRemoteAddressFilter<T extends SocketAddress> extends ChannelInboundHandlerAdapter {
    /* access modifiers changed from: protected */
    public abstract boolean accept(ChannelHandlerContext channelHandlerContext, T t) throws Exception;

    /* access modifiers changed from: protected */
    public void channelAccepted(ChannelHandlerContext channelHandlerContext, T t) {
    }

    /* access modifiers changed from: protected */
    public ChannelFuture channelRejected(ChannelHandlerContext channelHandlerContext, T t) {
        return null;
    }

    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        handleNewChannel(channelHandlerContext);
        channelHandlerContext.fireChannelRegistered();
    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (handleNewChannel(channelHandlerContext)) {
            channelHandlerContext.fireChannelActive();
            return;
        }
        throw new IllegalStateException("cannot determine to accept or reject a channel: " + channelHandlerContext.channel());
    }

    private boolean handleNewChannel(ChannelHandlerContext channelHandlerContext) throws Exception {
        SocketAddress remoteAddress = channelHandlerContext.channel().remoteAddress();
        if (remoteAddress == null) {
            return false;
        }
        channelHandlerContext.pipeline().remove((ChannelHandler) this);
        if (accept(channelHandlerContext, remoteAddress)) {
            channelAccepted(channelHandlerContext, remoteAddress);
            return true;
        }
        ChannelFuture channelRejected = channelRejected(channelHandlerContext, remoteAddress);
        if (channelRejected != null) {
            channelRejected.addListener(ChannelFutureListener.CLOSE);
            return true;
        }
        channelHandlerContext.close();
        return true;
    }
}
