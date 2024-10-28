package io.ktor.server.netty;

import io.ktor.server.engine.ApplicationEngineEnvironment;
import io.ktor.server.engine.EngineConnectorConfig;
import io.netty.bootstrap.ServerBootstrap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/netty/bootstrap/ServerBootstrap;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationEngine.kt */
final class NettyApplicationEngine$bootstraps$2 extends Lambda implements Function0<List<? extends ServerBootstrap>> {
    final /* synthetic */ ApplicationEngineEnvironment $environment;
    final /* synthetic */ NettyApplicationEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyApplicationEngine$bootstraps$2(ApplicationEngineEnvironment applicationEngineEnvironment, NettyApplicationEngine nettyApplicationEngine) {
        super(0);
        this.$environment = applicationEngineEnvironment;
        this.this$0 = nettyApplicationEngine;
    }

    public final List<ServerBootstrap> invoke() {
        Iterable<EngineConnectorConfig> connectors = this.$environment.getConnectors();
        NettyApplicationEngine nettyApplicationEngine = this.this$0;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(connectors, 10));
        for (EngineConnectorConfig access$createBootstrap : connectors) {
            arrayList.add(nettyApplicationEngine.createBootstrap(access$createBootstrap));
        }
        return (List) arrayList;
    }
}
