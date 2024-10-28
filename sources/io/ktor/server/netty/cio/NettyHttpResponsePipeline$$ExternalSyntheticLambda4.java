package io.ktor.server.netty.cio;

import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NettyHttpResponsePipeline$$ExternalSyntheticLambda4 implements GenericFutureListener {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ NettyHttpResponsePipeline f$1;
    public final /* synthetic */ ChannelFuture f$2;

    public /* synthetic */ NettyHttpResponsePipeline$$ExternalSyntheticLambda4(boolean z, NettyHttpResponsePipeline nettyHttpResponsePipeline, ChannelFuture channelFuture) {
        this.f$0 = z;
        this.f$1 = nettyHttpResponsePipeline;
        this.f$2 = channelFuture;
    }

    public final void operationComplete(Future future) {
        NettyHttpResponsePipeline.handleLastResponseMessage$lambda$3(this.f$0, this.f$1, this.f$2, future);
    }
}
