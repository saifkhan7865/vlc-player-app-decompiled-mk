package io.ktor.server.netty.cio;

import io.ktor.server.netty.NettyApplicationCall;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import kotlin.jvm.functions.Function0;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NettyHttpResponsePipeline$$ExternalSyntheticLambda3 implements GenericFutureListener {
    public final /* synthetic */ NettyApplicationCall f$0;
    public final /* synthetic */ NettyHttpResponsePipeline f$1;
    public final /* synthetic */ Function0 f$2;

    public /* synthetic */ NettyHttpResponsePipeline$$ExternalSyntheticLambda3(NettyApplicationCall nettyApplicationCall, NettyHttpResponsePipeline nettyHttpResponsePipeline, Function0 function0) {
        this.f$0 = nettyApplicationCall;
        this.f$1 = nettyHttpResponsePipeline;
        this.f$2 = function0;
    }

    public final void operationComplete(Future future) {
        NettyHttpResponsePipeline.setOnResponseReadyHandler$lambda$2(this.f$0, this.f$1, this.f$2, future);
    }
}
