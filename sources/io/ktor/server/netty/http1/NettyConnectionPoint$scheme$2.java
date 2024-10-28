package io.ktor.server.netty.http1;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyConnectionPoint.kt */
final class NettyConnectionPoint$scheme$2 extends Lambda implements Function0<String> {
    final /* synthetic */ NettyConnectionPoint this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyConnectionPoint$scheme$2(NettyConnectionPoint nettyConnectionPoint) {
        super(0);
        this.this$0 = nettyConnectionPoint;
    }

    public final String invoke() {
        return this.this$0.context.pipeline().context("ssl") == null ? "http" : "https";
    }
}
