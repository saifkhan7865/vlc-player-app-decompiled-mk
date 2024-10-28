package io.ktor.server.netty;

import io.ktor.server.engine.ApplicationEngineEnvironment;
import io.ktor.server.engine.ApplicationEngineFactory;
import io.ktor.server.netty.NettyApplicationEngine;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J)\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000bH\u0016¨\u0006\f"}, d2 = {"Lio/ktor/server/netty/Netty;", "Lio/ktor/server/engine/ApplicationEngineFactory;", "Lio/ktor/server/netty/NettyApplicationEngine;", "Lio/ktor/server/netty/NettyApplicationEngine$Configuration;", "()V", "create", "environment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Embedded.kt */
public final class Netty implements ApplicationEngineFactory<NettyApplicationEngine, NettyApplicationEngine.Configuration> {
    public static final Netty INSTANCE = new Netty();

    private Netty() {
    }

    public NettyApplicationEngine create(ApplicationEngineEnvironment applicationEngineEnvironment, Function1<? super NettyApplicationEngine.Configuration, Unit> function1) {
        Intrinsics.checkNotNullParameter(applicationEngineEnvironment, "environment");
        Intrinsics.checkNotNullParameter(function1, "configure");
        return new NettyApplicationEngine(applicationEngineEnvironment, function1);
    }
}
