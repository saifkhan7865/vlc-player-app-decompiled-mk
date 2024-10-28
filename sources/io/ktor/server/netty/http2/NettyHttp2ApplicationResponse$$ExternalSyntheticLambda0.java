package io.ktor.server.netty.http2;

import io.ktor.server.response.ResponsePushBuilder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NettyHttp2ApplicationResponse$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ NettyHttp2ApplicationResponse f$0;
    public final /* synthetic */ ResponsePushBuilder f$1;

    public /* synthetic */ NettyHttp2ApplicationResponse$$ExternalSyntheticLambda0(NettyHttp2ApplicationResponse nettyHttp2ApplicationResponse, ResponsePushBuilder responsePushBuilder) {
        this.f$0 = nettyHttp2ApplicationResponse;
        this.f$1 = responsePushBuilder;
    }

    public final void run() {
        NettyHttp2ApplicationResponse.push$lambda$2(this.f$0, this.f$1);
    }
}
