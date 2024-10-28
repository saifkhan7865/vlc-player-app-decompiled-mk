package io.ktor.server.netty.http2;

import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2StreamChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NettyHttp2Handler$$ExternalSyntheticLambda0 implements GenericFutureListener {
    public final /* synthetic */ NettyHttp2Handler f$0;
    public final /* synthetic */ Http2StreamChannel f$1;
    public final /* synthetic */ DefaultHttp2Headers f$2;

    public /* synthetic */ NettyHttp2Handler$$ExternalSyntheticLambda0(NettyHttp2Handler nettyHttp2Handler, Http2StreamChannel http2StreamChannel, DefaultHttp2Headers defaultHttp2Headers) {
        this.f$0 = nettyHttp2Handler;
        this.f$1 = http2StreamChannel;
        this.f$2 = defaultHttp2Headers;
    }

    public final void operationComplete(Future future) {
        NettyHttp2Handler.startHttp2PushPromise$lambda$4(this.f$0, this.f$1, this.f$2, future);
    }
}
