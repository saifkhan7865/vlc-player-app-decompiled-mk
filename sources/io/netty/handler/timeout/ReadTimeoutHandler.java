package io.netty.handler.timeout;

import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.TimeUnit;

public class ReadTimeoutHandler extends IdleStateHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean closed;

    public ReadTimeoutHandler(int i) {
        this((long) i, TimeUnit.SECONDS);
    }

    public ReadTimeoutHandler(long j, TimeUnit timeUnit) {
        super(j, 0, 0, timeUnit);
    }

    /* access modifiers changed from: protected */
    public final void channelIdle(ChannelHandlerContext channelHandlerContext, IdleStateEvent idleStateEvent) throws Exception {
        readTimedOut(channelHandlerContext);
    }

    /* access modifiers changed from: protected */
    public void readTimedOut(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.closed) {
            channelHandlerContext.fireExceptionCaught(ReadTimeoutException.INSTANCE);
            channelHandlerContext.close();
            this.closed = true;
        }
    }
}
