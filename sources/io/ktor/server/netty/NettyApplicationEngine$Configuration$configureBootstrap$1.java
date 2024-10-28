package io.ktor.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/netty/bootstrap/ServerBootstrap;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationEngine.kt */
final class NettyApplicationEngine$Configuration$configureBootstrap$1 extends Lambda implements Function1<ServerBootstrap, Unit> {
    public static final NettyApplicationEngine$Configuration$configureBootstrap$1 INSTANCE = new NettyApplicationEngine$Configuration$configureBootstrap$1();

    NettyApplicationEngine$Configuration$configureBootstrap$1() {
        super(1);
    }

    public final void invoke(ServerBootstrap serverBootstrap) {
        Intrinsics.checkNotNullParameter(serverBootstrap, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ServerBootstrap) obj);
        return Unit.INSTANCE;
    }
}
