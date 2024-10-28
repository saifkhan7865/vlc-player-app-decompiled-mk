package io.netty.handler.address;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import java.net.SocketAddress;

public abstract class DynamicAddressConnectHandler extends ChannelOutboundHandlerAdapter {
    /* access modifiers changed from: protected */
    public SocketAddress localAddress(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        return socketAddress2;
    }

    /* access modifiers changed from: protected */
    public SocketAddress remoteAddress(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        return socketAddress;
    }

    public final void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
        try {
            channelHandlerContext.connect(remoteAddress(socketAddress, socketAddress2), localAddress(socketAddress, socketAddress2), channelPromise).addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) {
                    if (channelFuture.isSuccess()) {
                        channelFuture.channel().pipeline().remove((ChannelHandler) DynamicAddressConnectHandler.this);
                    }
                }
            });
        } catch (Exception e) {
            channelPromise.setFailure(e);
        }
    }
}
