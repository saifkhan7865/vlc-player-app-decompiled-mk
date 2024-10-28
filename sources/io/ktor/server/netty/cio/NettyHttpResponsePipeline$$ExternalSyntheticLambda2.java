package io.ktor.server.netty.cio;

import io.ktor.server.netty.NettyApplicationCall;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import kotlin.jvm.functions.Function0;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NettyHttpResponsePipeline$$ExternalSyntheticLambda2 implements GenericFutureListener {
    public final /* synthetic */ NettyHttpResponsePipeline f$0;
    public final /* synthetic */ NettyApplicationCall f$1;
    public final /* synthetic */ Future f$2;
    public final /* synthetic */ Function0 f$3;

    public /* synthetic */ NettyHttpResponsePipeline$$ExternalSyntheticLambda2(NettyHttpResponsePipeline nettyHttpResponsePipeline, NettyApplicationCall nettyApplicationCall, Future future, Function0 function0) {
        this.f$0 = nettyHttpResponsePipeline;
        this.f$1 = nettyApplicationCall;
        this.f$2 = future;
        this.f$3 = function0;
    }

    public final void operationComplete(Future future) {
        NettyHttpResponsePipeline.setOnResponseReadyHandler$lambda$2$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, future);
    }
}
