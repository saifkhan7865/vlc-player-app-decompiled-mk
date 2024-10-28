package io.ktor.server.netty;

import io.ktor.server.netty.http2.NettyHttp2Handler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NettyChannelInitializer$$ExternalSyntheticLambda0 implements GenericFutureListener {
    public final /* synthetic */ NettyHttp2Handler f$0;

    public /* synthetic */ NettyChannelInitializer$$ExternalSyntheticLambda0(NettyHttp2Handler nettyHttp2Handler) {
        this.f$0 = nettyHttp2Handler;
    }

    public final void operationComplete(Future future) {
        NettyChannelInitializer.configurePipeline$lambda$5(this.f$0, future);
    }
}
