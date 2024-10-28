package io.ktor.server.netty;

import io.ktor.server.netty.NettyApplicationEngine;
import io.netty.handler.codec.http.HttpServerCodec;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationEngine.kt */
/* synthetic */ class NettyApplicationEngine$Configuration$httpServerCodec$1 extends FunctionReferenceImpl implements Function0<HttpServerCodec> {
    NettyApplicationEngine$Configuration$httpServerCodec$1(Object obj) {
        super(0, obj, NettyApplicationEngine.Configuration.class, "defaultHttpServerCodec", "defaultHttpServerCodec()Lio/netty/handler/codec/http/HttpServerCodec;", 0);
    }

    public final HttpServerCodec invoke() {
        return ((NettyApplicationEngine.Configuration) this.receiver).defaultHttpServerCodec();
    }
}
