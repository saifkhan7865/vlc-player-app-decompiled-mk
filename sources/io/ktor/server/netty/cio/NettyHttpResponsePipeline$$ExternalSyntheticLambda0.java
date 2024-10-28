package io.ktor.server.netty.cio;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NettyHttpResponsePipeline$$ExternalSyntheticLambda0 implements GenericFutureListener {
    public final /* synthetic */ NettyHttpResponsePipeline f$0;

    public /* synthetic */ NettyHttpResponsePipeline$$ExternalSyntheticLambda0(NettyHttpResponsePipeline nettyHttpResponsePipeline) {
        this.f$0 = nettyHttpResponsePipeline;
    }

    public final void operationComplete(Future future) {
        NettyHttpResponsePipeline.close$lambda$4(this.f$0, future);
    }
}
