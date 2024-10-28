package io.ktor.server.netty;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/server/netty/NettyDispatcher;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationEngine.kt */
final class NettyApplicationEngine$nettyDispatcher$2 extends Lambda implements Function0<NettyDispatcher> {
    public static final NettyApplicationEngine$nettyDispatcher$2 INSTANCE = new NettyApplicationEngine$nettyDispatcher$2();

    NettyApplicationEngine$nettyDispatcher$2() {
        super(0);
    }

    public final NettyDispatcher invoke() {
        return NettyDispatcher.INSTANCE;
    }
}
