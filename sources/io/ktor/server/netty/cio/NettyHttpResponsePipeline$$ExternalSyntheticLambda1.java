package io.ktor.server.netty.cio;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NettyHttpResponsePipeline$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ NettyHttpResponsePipeline f$0;

    public /* synthetic */ NettyHttpResponsePipeline$$ExternalSyntheticLambda1(NettyHttpResponsePipeline nettyHttpResponsePipeline) {
        this.f$0 = nettyHttpResponsePipeline;
    }

    public final void run() {
        NettyHttpResponsePipeline.scheduleFlush$lambda$5(this.f$0);
    }
}
