package io.ktor.server.netty;

import io.netty.handler.ssl.SslProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/netty/handler/ssl/SslProvider;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyChannelInitializer.kt */
final class NettyChannelInitializer$Companion$alpnProvider$2 extends Lambda implements Function0<SslProvider> {
    public static final NettyChannelInitializer$Companion$alpnProvider$2 INSTANCE = new NettyChannelInitializer$Companion$alpnProvider$2();

    NettyChannelInitializer$Companion$alpnProvider$2() {
        super(0);
    }

    public final SslProvider invoke() {
        return NettyChannelInitializer.Companion.findAlpnProvider();
    }
}
