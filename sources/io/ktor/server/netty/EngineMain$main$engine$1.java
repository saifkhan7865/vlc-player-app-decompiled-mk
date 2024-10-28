package io.ktor.server.netty;

import io.ktor.server.engine.ApplicationEngineEnvironment;
import io.ktor.server.netty.NettyApplicationEngine;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/netty/NettyApplicationEngine$Configuration;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineMain.kt */
final class EngineMain$main$engine$1 extends Lambda implements Function1<NettyApplicationEngine.Configuration, Unit> {
    final /* synthetic */ ApplicationEngineEnvironment $applicationEnvironment;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EngineMain$main$engine$1(ApplicationEngineEnvironment applicationEngineEnvironment) {
        super(1);
        this.$applicationEnvironment = applicationEngineEnvironment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((NettyApplicationEngine.Configuration) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(NettyApplicationEngine.Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "$this$$receiver");
        EngineMain.INSTANCE.loadConfiguration$ktor_server_netty(configuration, this.$applicationEnvironment.getConfig());
    }
}
